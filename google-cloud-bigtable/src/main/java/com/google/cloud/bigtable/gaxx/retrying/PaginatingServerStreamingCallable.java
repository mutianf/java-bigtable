/*
 * Copyright 2026 Google LLC
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.google.cloud.bigtable.gaxx.retrying;

import com.google.api.gax.rpc.ApiCallContext;
import com.google.api.gax.rpc.ResponseObserver;
import com.google.api.gax.rpc.ServerStreamingCallable;
import com.google.api.gax.rpc.StreamController;
import com.google.bigtable.v2.ReadRowsRequest;
import com.google.bigtable.v2.RowRange;
import com.google.bigtable.v2.RowSet;
import com.google.cloud.bigtable.data.v2.models.Filters;
import com.google.cloud.bigtable.data.v2.models.LargeRowException;
import com.google.cloud.bigtable.data.v2.models.Row;
import com.google.cloud.bigtable.data.v2.models.RowCell;
import com.google.common.collect.Lists;
import com.google.protobuf.ByteString;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicReference;

public class PaginatingServerStreamingCallable extends ServerStreamingCallable<ReadRowsRequest, Row> {
  private final ServerStreamingCallable<ReadRowsRequest, Row> innerCallable;

  public PaginatingServerStreamingCallable(ServerStreamingCallable<ReadRowsRequest, Row> innerCallable) {
    this.innerCallable = innerCallable;
  }

  @Override
  public void call(ReadRowsRequest request, ResponseObserver<Row> responseObserver, ApiCallContext context) {
    innerCallable.call(request, new PaginatingObserver(innerCallable, request, responseObserver, context), context);
  }

  private static class PaginatingObserver implements ResponseObserver<Row> {
    private final ServerStreamingCallable<ReadRowsRequest, Row> innerCallable;
    private final ReadRowsRequest originalRequest;
    private final ResponseObserver<Row> outerObserver;
    private final ApiCallContext context;

    public PaginatingObserver(
        ServerStreamingCallable<ReadRowsRequest, Row> innerCallable,
        ReadRowsRequest originalRequest,
        ResponseObserver<Row> outerObserver,
        ApiCallContext context) {
      this.innerCallable = innerCallable;
      this.originalRequest = originalRequest;
      this.outerObserver = outerObserver;
      this.context = context;
    }

    @Override
    public void onStart(StreamController controller) {
      outerObserver.onStart(controller);
    }

    @Override
    public void onResponse(Row response) {
      outerObserver.onResponse(response);
    }

    @Override
    public void onError(Throwable t) {
      Throwable cause = t;
      LargeRowException largeRowException = null;
      while (cause != null) {
        if (cause instanceof LargeRowException) {
          largeRowException = (LargeRowException) cause;
          break;
        }
        cause = cause.getCause();
      }

      if (largeRowException != null) {
        try {
          ByteString largeRowKey = largeRowException.getLargeRowKeys().get(0);
          Row paginatedRow = fetchPaginatedRow(largeRowKey);
          if (paginatedRow != null) {
            outerObserver.onResponse(paginatedRow);
          }

          ByteString nextKey = largeRowKey.concat(ByteString.copyFrom(new byte[]{0}));
          ReadRowsRequest resumedRequest = originalRequest.toBuilder()
              .clearRows()
              .setRows(RowSet.newBuilder().addRowRanges(RowRange.newBuilder().setStartKeyClosed(nextKey).build()))
              .build();

          innerCallable.call(resumedRequest, new PaginatingObserver(innerCallable, resumedRequest, outerObserver, context), context);
        } catch (Exception e) {
          outerObserver.onError(e);
        }
      } else {
        outerObserver.onError(t);
      }
    }

    @Override
    public void onComplete() {
      outerObserver.onComplete();
    }

    private Row fetchPaginatedRow(ByteString rowKey) throws Exception {
      Filters.ChainFilter stripChain = Filters.FILTERS.chain();
      stripChain.filter(Filters.FILTERS.value().strip());

      ReadRowsRequest countReq = originalRequest.toBuilder()
          .clearRows()
          .setRows(RowSet.newBuilder().addRowKeys(rowKey))
          .setFilter(stripChain.toProto())
          .build();

      Row countRow = blockingReadSingleRow(countReq);
      if (countRow == null) {
        return null;
      }
      int totalCells = countRow.getCells().size();

      List<RowCell> resultCells = Lists.newArrayList();
      int offset = 0;
      int limit = totalCells;

      while (offset < totalCells) {
        try {
          Filters.ChainFilter chain = Filters.FILTERS.chain();
          if (offset > 0) {
            chain.filter(Filters.FILTERS.offset().cellsPerRow(offset));
          }
          if (limit > 0) {
            chain.filter(Filters.FILTERS.limit().cellsPerRow(limit));
          }

          ReadRowsRequest chunkReq = originalRequest.toBuilder()
              .clearRows()
              .setRows(RowSet.newBuilder().addRowKeys(rowKey))
              .setFilter(chain.toProto())
              .build();

          Row partialRow = blockingReadSingleRow(chunkReq);
          if (partialRow != null) {
            resultCells.addAll(partialRow.getCells());
            offset += partialRow.getCells().size();
            limit = totalCells - offset;
          } else {
            break;
          }
        } catch (Exception e) {
          limit = limit / 2;
          if (limit == 0) {
            throw new RuntimeException("Cell too large", e);
          }
        }
      }
      return Row.create(rowKey, resultCells);
    }

    private Row blockingReadSingleRow(ReadRowsRequest req) throws Exception {
      CountDownLatch latch = new CountDownLatch(1);
      AtomicReference<Row> rowRef = new AtomicReference<>();
      AtomicReference<Throwable> errRef = new AtomicReference<>();

      innerCallable.call(req, new BlockingObserver(latch, rowRef, errRef), context);

      latch.await();
      if (errRef.get() != null) {
        throw new Exception("Inner stream failed", errRef.get());
      }
      return rowRef.get();
    }
  }

  private static class BlockingObserver implements ResponseObserver<Row> {
    private final CountDownLatch latch;
    private final AtomicReference<Row> rowRef;
    private final AtomicReference<Throwable> errRef;

    BlockingObserver(CountDownLatch latch, AtomicReference<Row> rowRef, AtomicReference<Throwable> errRef) {
      this.latch = latch;
      this.rowRef = rowRef;
      this.errRef = errRef;
    }

    @Override
    public void onStart(StreamController controller) {}

    @Override
    public void onResponse(Row response) {
      rowRef.set(response);
    }

    @Override
    public void onError(Throwable t) {
      errRef.set(t);
      latch.countDown();
    }

    @Override
    public void onComplete() {
      latch.countDown();
    }
  }
}
