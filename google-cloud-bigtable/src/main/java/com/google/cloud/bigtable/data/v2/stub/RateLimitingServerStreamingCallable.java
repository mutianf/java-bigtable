/*
 * Copyright 2023 Google LLC
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *       https://www.apache.org/licenses/LICENSE-2.0
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
import com.google.api.gax.rpc.ServerStreamingCallable;
import com.google.api.gax.rpc.StreamController;
import com.google.api.gax.rpc.UnavailableException;
import com.google.bigtable.v2.MutateRowsRequest;
import com.google.bigtable.v2.MutateRowsResponse;
import com.google.common.annotations.VisibleForTesting;
import com.google.common.base.Preconditions;
import com.google.common.util.concurrent.RateLimiter;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.atomic.AtomicLong;
import java.util.logging.Logger;
import java.util.stream.DoubleStream;
import javax.annotation.Nonnull;

class RateLimitingServerStreamingCallable
    extends ServerStreamingCallable<MutateRowsRequest, MutateRowsResponse> {
  private static Logger LOG =
      Logger.getLogger(RateLimitingServerStreamingCallable.class.toString());

  private static final long DEFAULT_QPS = 10;
  private static final long minimumTimeMsBetweenUpdates = 10_000;

  private final int targetCpuPercent;

  private final RateLimiter limiter;
  private final AtomicLong lastQpsChangeTime = new AtomicLong(System.currentTimeMillis());

  private final ServerStreamingCallable<MutateRowsRequest, MutateRowsResponse> innerCallable;

  private final int id = ThreadLocalRandom.current().nextInt(100, 999);

  RateLimitingServerStreamingCallable(
      @Nonnull ServerStreamingCallable<MutateRowsRequest, MutateRowsResponse> innerCallable,
      int targetCpuPercent) {
    this.targetCpuPercent = targetCpuPercent;
    this.limiter = RateLimiter.create(DEFAULT_QPS);
    this.innerCallable = Preconditions.checkNotNull(innerCallable, "Inner callable must be set");

    LOG.fine(id + " - Initializing CPU based throttling with CPU target " + targetCpuPercent);
  }

  @Override
  public void call(
      MutateRowsRequest request,
      ResponseObserver<MutateRowsResponse> responseObserver,
      ApiCallContext context) {
    limiter.acquire();

    RateLimitingResponseObserver innerObserver = new RateLimitingResponseObserver(responseObserver);
    innerCallable.call(request, innerObserver, context);
  }

  class RateLimitingResponseObserver extends SafeResponseObserver<MutateRowsResponse> {
    private final ResponseObserver<MutateRowsResponse> outerObserver;

    RateLimitingResponseObserver(ResponseObserver<MutateRowsResponse> observer) {
      super(observer);

      this.outerObserver = observer;
    }

    @Override
    protected void onStartImpl(final StreamController controller) {
      outerObserver.onStart(controller);
    }

    @Override
    protected void onResponseImpl(MutateRowsResponse response) {
      double[] cpus = RateLimitingUtils.getCpuList(response);
      if (cpus.length == 0) {
        LOG.warning(id + " - received response without any CPU data.");
        outerObserver.onResponse(response);
        return;
      }

      double avgCpu = DoubleStream.of(cpus).average().getAsDouble();

      updateQps(avgCpu);

      outerObserver.onResponse(response);
    }

    @Override
    protected void onErrorImpl(Throwable t) {
      // When server is returning DEADLINE_EXCEEDED or UNAVAILABLE, assume cbt server is overloaded
      if (t instanceof DeadlineExceededException || t instanceof UnavailableException) {
        updateQps(99.9);
      }
      outerObserver.onError(t);
    }

    @Override
    protected void onCompleteImpl() {
      outerObserver.onComplete();
    }

    private void updateQps(double currentCpu) {
      long lastTime = lastQpsChangeTime.get();
      long now = System.currentTimeMillis();
      // Make sure there's enough time passed since we last updated the QPS
      // and no other thread has made changes to the QPS.
      if (lastTime + minimumTimeMsBetweenUpdates < now
          && lastQpsChangeTime.compareAndSet(lastTime, now)) {
        double currentRate = limiter.getRate();
        double newQps =
            RateLimitingUtils.calculateNewQps(currentCpu, targetCpuPercent, currentRate, id);
        limiter.setRate(newQps);
      }
    }
  }

  @VisibleForTesting
  AtomicLong getLastQpsChangeTime() {
    return lastQpsChangeTime;
  }

  @VisibleForTesting
  double getCurrentRate() {
    return limiter.getRate();
  }
}
