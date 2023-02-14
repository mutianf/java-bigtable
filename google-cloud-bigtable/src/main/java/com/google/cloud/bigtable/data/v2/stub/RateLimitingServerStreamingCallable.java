/*
 * Copyright 2019 Google LLC
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
package com.google.cloud.bigtable.data.v2.stub;

import com.google.api.gax.rpc.ApiCallContext;
import com.google.api.gax.rpc.DeadlineExceededException;
import com.google.api.gax.rpc.ResponseObserver;
import com.google.api.gax.rpc.UnavailableException;
import com.google.bigtable.v2.MutateRowsRequest;
import com.google.bigtable.v2.MutateRowsResponse;
import com.google.api.gax.rpc.ServerStreamingCallable;
import com.google.api.gax.rpc.StreamController;
import com.google.common.base.Preconditions;
import com.google.common.util.concurrent.RateLimiter;
import java.util.Arrays;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.atomic.AtomicLong;
import java.util.logging.Logger;
import java.util.stream.DoubleStream;
import javax.annotation.Nonnull;
import org.checkerframework.checker.units.qual.A;

public class RateLimitingServerStreamingCallable
    extends ServerStreamingCallable<MutateRowsRequest, MutateRowsResponse> {
  private static Logger LOG = Logger.getLogger(RateLimitingServerStreamingCallable.class.toString());

  private final static long DEFAULT_QPS = 10;
  private final static long minimumTimeMsBetweenUpdates = 60_000;

  private final int targetCpuPercent;

  private RateLimiter limiter;
  private RateLimitingStats stats;
  private AtomicLong lastCpuRecordTime = new AtomicLong();
  private AtomicLong lastQpsChangeTime = new AtomicLong(System.currentTimeMillis());

  private AtomicLong responseCount = new AtomicLong();

  private final ServerStreamingCallable<MutateRowsRequest, MutateRowsResponse> innerCallable;

  private final int id = ThreadLocalRandom.current().nextInt(100, 999);

  public RateLimitingServerStreamingCallable(
      @Nonnull ServerStreamingCallable<MutateRowsRequest, MutateRowsResponse> innerCallable, int targetCpuPercent) {
    this.targetCpuPercent = targetCpuPercent;
    this.limiter =  RateLimiter.create(DEFAULT_QPS);
    this.stats = new RateLimitingStats(id);
    this.innerCallable = Preconditions.checkNotNull(
        innerCallable, "Inner callable must be set");

    LOG.warning("kk_init RateLimiting Callable id=" + id + " target_cpu=" + targetCpuPercent + " ms_between_updates=" + minimumTimeMsBetweenUpdates);
  }
  @Override
  public void call(
      MutateRowsRequest request, ResponseObserver<MutateRowsResponse> responseObserver, ApiCallContext context) {
    limiter.acquire();

    CpuMetadataResponseObserver innerObserver =
        new CpuMetadataResponseObserver(responseObserver);
    innerCallable.call(request, innerObserver, context);
  }

  private class CpuMetadataResponseObserver extends SafeResponseObserver<MutateRowsResponse> {
    private final ResponseObserver<MutateRowsResponse> outerObserver;

    CpuMetadataResponseObserver(
        ResponseObserver<MutateRowsResponse> observer) {
      super(observer);

      this.outerObserver = observer;
    }

    @Override
    protected void onStartImpl(final StreamController controller) {
      outerObserver.onStart(controller);
    }

    @Override
    protected void onResponseImpl(MutateRowsResponse response) {
      responseCount.getAndIncrement();

      // System.out.println("kk_response response size=" + response.getEntriesCount() + " cpu_stats size=" + response.getServerStats().getCpuStatsCount());

      long now = System.currentTimeMillis();

      double[] cpus = RateLimitingStats.getCpuList(response);
      if (cpus.length == 0) {
        // TODO: report error
        outerObserver.onResponse(response);
        return;
      }

      double avgCpu = DoubleStream.of(cpus).average().getAsDouble();

      long lastCpuRecordTimeLocal = lastCpuRecordTime.get();
      if (lastCpuRecordTimeLocal + 1000 < now) {
        if (lastCpuRecordTime.compareAndSet(lastCpuRecordTimeLocal, now)) {
          stats.recordCpu(avgCpu, responseCount.get());
        }
      }

      // System.out.println("kk_cpu_stats id=" + id + " size=" + cpus.length + "stats=" + Arrays.toString(cpus));

      // Ensure enough time has passed since updates to QPS
      long lastTime = lastQpsChangeTime.get();
      if (lastTime + minimumTimeMsBetweenUpdates > System.currentTimeMillis()) {
        outerObserver.onResponse(response);
        return;
      } else {
        if (!lastQpsChangeTime.compareAndSet(lastTime, System.currentTimeMillis())) {
          // some other thread has just updated the QPS
          outerObserver.onResponse(response);
          return;
        }
        // else update QPS
      }

      double currentRate = limiter.getRate();
      // double newQps = RateLimitingStats.calculateNewQps(avgCpu, targetCpuPercent, currentRate, id);
      double newQps = RateLimitingStats.calculateNewQps(stats.getLastMinCpu(), targetCpuPercent, currentRate, id);
      limiter.setRate(newQps);

      outerObserver.onResponse(response);
    }

    @Override
    protected void onErrorImpl(Throwable t) {
      LOG.warning("kk89 ratelimiter onErrorImpl");

      // TODO: finish onError case

      // When server is returning DEADLINE_EXCEEDED or UNAVAILABLE, assume cbt server is overloaded
      if (t instanceof DeadlineExceededException || t instanceof UnavailableException) {
        outerObserver.onError(t);
        return;
      }
      outerObserver.onError(t);
    }

    @Override
    protected void onCompleteImpl() {
      outerObserver.onComplete();
    }
  }
}