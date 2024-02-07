/*
 * Copyright 2022 Google LLC
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
package com.google.cloud.bigtable.data.v2.stub.metrics;

import static com.google.api.gax.tracing.ApiTracerFactory.OperationType;
import static com.google.cloud.bigtable.data.v2.stub.metrics.BuiltinMetricsConstants.CLIENT_NAME_KEY;
import static com.google.cloud.bigtable.data.v2.stub.metrics.BuiltinMetricsConstants.CLUSTER_ID_KEY;
import static com.google.cloud.bigtable.data.v2.stub.metrics.BuiltinMetricsConstants.METHOD_KEY;
import static com.google.cloud.bigtable.data.v2.stub.metrics.BuiltinMetricsConstants.STATUS_KEY;
import static com.google.cloud.bigtable.data.v2.stub.metrics.BuiltinMetricsConstants.STREAMING_KEY;
import static com.google.cloud.bigtable.data.v2.stub.metrics.BuiltinMetricsConstants.TABLE_ID_KEY;
import static com.google.cloud.bigtable.data.v2.stub.metrics.BuiltinMetricsConstants.ZONE_ID_KEY;

import com.google.api.gax.retrying.ServerStreamingAttemptException;
import com.google.api.gax.tracing.SpanName;
import com.google.common.base.Stopwatch;
import com.google.common.math.IntMath;
import com.google.common.util.concurrent.AtomicDouble;
import io.opentelemetry.api.common.Attributes;
import io.opentelemetry.api.metrics.DoubleHistogram;
import io.opentelemetry.api.metrics.LongCounter;
import io.opentelemetry.api.metrics.LongHistogram;
import java.util.concurrent.CancellationException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;
import javax.annotation.Nullable;
import org.threeten.bp.Duration;

/**
 * A {@link BigtableTracer} that records built-in metrics and publish under the
 * bigtable.googleapis.com/client namespace
 */
class BuiltinMetricsTracer extends BigtableTracer {

  private static final String NAME = "java-bigtable";
  private final OperationType operationType;
  private final SpanName spanName;

  // Operation level metrics
  private final AtomicBoolean opFinished = new AtomicBoolean();
  private final Stopwatch operationTimer = Stopwatch.createStarted();
  private final Stopwatch firstResponsePerOpTimer = Stopwatch.createStarted();

  // Attempt level metrics
  private int attemptCount = 0;
  private Stopwatch attemptTimer;
  private volatile int attempt = 0;

  // Total server latency needs to be atomic because it's accessed from different threads. E.g.
  // request() from user thread and attempt failed from grpc thread. We're only measuring the extra
  // time application spent blocking grpc buffer, which will be operationLatency - serverLatency.
  private final AtomicLong totalServerLatencyNano = new AtomicLong(0);
  // Stopwatch is not thread safe so this is a workaround to check if the stopwatch changes is
  // flushed to memory.
  private final Stopwatch serverLatencyTimer = Stopwatch.createUnstarted();
  private boolean serverLatencyTimerIsRunning = false;
  private final Object timerLock = new Object();

  private boolean flowControlIsDisabled = false;

  private final AtomicInteger requestLeft = new AtomicInteger(0);

  // Monitored resource labels
  private String tableId = "unspecified";
  private String zone = "global";
  private String cluster = "unspecified";
  private AtomicDouble totalClientBlockingTime = new AtomicDouble(0);

  private final Attributes baseAttributes;

  private Long serverLatencies = null;

  private final DoubleHistogram operationLatenciesHistogram;
  private final DoubleHistogram attemptLatenciesHistogram;
  private final DoubleHistogram serverLatenciesHistogram;
  private final DoubleHistogram firstResponseLatenciesHistogram;
  private final DoubleHistogram clientBlockingLatenciesHistogram;
  private final DoubleHistogram applicationBlockingLatenciesHistogram;
  private final LongCounter connectivityErrorCounter;
  private final LongCounter retryCounter;

  BuiltinMetricsTracer(
      OperationType operationType,
      SpanName spanName,
      Attributes attributes,
      DoubleHistogram operationLatenciesHistogram,
      DoubleHistogram attemptLatenciesHistogram,
      DoubleHistogram serverLatenciesHistogram,
      DoubleHistogram firstResponseLatenciesHistogram,
      DoubleHistogram clientBlockingLatenciesHistogram,
      DoubleHistogram applicationBlockingLatenciesHistogram,
      LongCounter connectivityErrorCounter,
      LongCounter retryCounter) {
    this.operationType = operationType;
    this.spanName = spanName;
    this.baseAttributes = attributes;

    this.operationLatenciesHistogram = operationLatenciesHistogram;
    this.attemptLatenciesHistogram = attemptLatenciesHistogram;
    this.serverLatenciesHistogram = serverLatenciesHistogram;
    this.firstResponseLatenciesHistogram = firstResponseLatenciesHistogram;
    this.clientBlockingLatenciesHistogram = clientBlockingLatenciesHistogram;
    this.applicationBlockingLatenciesHistogram = applicationBlockingLatenciesHistogram;
    this.connectivityErrorCounter = connectivityErrorCounter;
    this.retryCounter = retryCounter;
  }

  @Override
  public Scope inScope() {
    return () -> {};
  }

  @Override
  public void operationSucceeded() {
    recordOperationCompletion(null);
  }

  @Override
  public void operationCancelled() {
    recordOperationCompletion(new CancellationException());
  }

  @Override
  public void operationFailed(Throwable error) {
    recordOperationCompletion(error);
  }

  @Override
  public void attemptStarted(int attemptNumber) {
    attemptStarted(null, attemptNumber);
  }

  @Override
  public void attemptStarted(Object request, int attemptNumber) {
    this.attempt = attemptNumber;
    attemptCount++;
    attemptTimer = Stopwatch.createStarted();
    if (request != null) {
      this.tableId = Util.extractTableId(request);
    }
    if (!flowControlIsDisabled) {
      synchronized (timerLock) {
        if (!serverLatencyTimerIsRunning) {
          serverLatencyTimer.start();
          serverLatencyTimerIsRunning = true;
        }
      }
    }
  }

  @Override
  public void attemptSucceeded() {
    recordAttemptCompletion(null);
  }

  @Override
  public void attemptCancelled() {
    recordAttemptCompletion(new CancellationException());
  }

  @Override
  public void attemptFailed(Throwable error, Duration delay) {
    recordAttemptCompletion(error);
  }

  @Override
  public void attemptPermanentFailure(Throwable throwable) {
    recordAttemptCompletion(throwable);
  }

  @Override
  public void onRequest(int requestCount) {
    requestLeft.accumulateAndGet(requestCount, IntMath::saturatedAdd);
    if (flowControlIsDisabled) {
      // On request is only called when auto flow control is disabled. When auto flow control is
      // disabled, server latency is measured between onRequest and onResponse.
      synchronized (timerLock) {
        if (!serverLatencyTimerIsRunning) {
          serverLatencyTimer.start();
          serverLatencyTimerIsRunning = true;
        }
      }
    }
  }

  @Override
  public void responseReceived() {
    // When auto flow control is enabled, server latency is measured between afterResponse and
    // responseReceived.
    // When auto flow control is disabled, server latency is measured between onRequest and
    // responseReceived.
    // When auto flow control is disabled and application requested multiple responses, server
    // latency is measured between afterResponse and responseReceived.
    // In all the cases, we want to stop the serverLatencyTimer here.
    synchronized (timerLock) {
      if (serverLatencyTimerIsRunning) {
        totalServerLatencyNano.addAndGet(serverLatencyTimer.elapsed(TimeUnit.NANOSECONDS));
        serverLatencyTimer.reset();
        serverLatencyTimerIsRunning = false;
      }
    }
  }

  @Override
  public void afterResponse(long applicationLatency) {
    if (!flowControlIsDisabled || requestLeft.decrementAndGet() > 0) {
      // When auto flow control is enabled, request will never be called, so server latency is
      // measured between after the last response is processed and before the next response is
      // received. If flow control is disabled but requestLeft is greater than 0,
      // also start the timer to count the time between afterResponse and responseReceived.
      synchronized (timerLock) {
        if (!serverLatencyTimerIsRunning) {
          serverLatencyTimer.start();
          serverLatencyTimerIsRunning = true;
        }
      }
    }
  }

  @Override
  public int getAttempt() {
    return attempt;
  }

  @Override
  public void recordGfeMetadata(@Nullable Long latency, @Nullable Throwable throwable) {
    if (latency != null) {
      serverLatencies = latency;
    }
  }

  @Override
  public void setLocations(String zone, String cluster) {
    this.zone = zone;
    this.cluster = cluster;
  }

  @Override
  public void batchRequestThrottled(long throttledTimeNanos) {
    totalClientBlockingTime.addAndGet(throttledTimeNanos / 10e6);
  }

  @Override
  public void grpcChannelQueuedLatencies(long queuedTimeNanos) {
    totalClientBlockingTime.addAndGet(queuedTimeNanos);
  }

  @Override
  public void disableFlowControl() {
    flowControlIsDisabled = true;
  }

  private void recordOperationCompletion(@Nullable Throwable status) {
    if (!opFinished.compareAndSet(false, true)) {
      return;
    }
    operationTimer.stop();

    boolean isStreaming = operationType == OperationType.ServerStreaming;
    String statusStr = Util.extractStatus(status);

    Attributes attributes =
        baseAttributes
            .toBuilder()
            .put(TABLE_ID_KEY, tableId)
            .put(CLUSTER_ID_KEY, cluster)
            .put(ZONE_ID_KEY, zone)
            .put(METHOD_KEY, spanName.toString())
            .put(CLIENT_NAME_KEY, NAME)
            .build();

    long operationLatencyNano = operationTimer.elapsed(TimeUnit.NANOSECONDS);

    // Only record when retry count is greater than 0 so the retry
    // graph will be less confusing
    if (attemptCount > 1) {
      retryCounter.add(attemptCount - 1, attributes.toBuilder().put(STATUS_KEY, statusStr).build());
    }

    // serverLatencyTimer should already be stopped in recordAttemptCompletion
    operationLatenciesHistogram.record(operationLatencyNano / 10e6,
        attributes.toBuilder().put(STREAMING_KEY, isStreaming).put(STATUS_KEY, statusStr).build());

    long applicationLatencyNano = operationLatencyNano - totalServerLatencyNano.get();
    applicationBlockingLatenciesHistogram.record(applicationLatencyNano / 10e6, attributes);

    if (operationType == OperationType.ServerStreaming
        && spanName.getMethodName().equals("ReadRows")) {
      firstResponseLatenciesHistogram.record(
          firstResponsePerOpTimer.elapsed(TimeUnit.NANOSECONDS) / 10e6,
          attributes.toBuilder().put(STATUS_KEY, Util.extractStatus(status)).build());
    }
  }

  private void recordAttemptCompletion(@Nullable Throwable status) {
    // If the attempt failed, the time spent in retry should be counted in application latency.
    // Stop the stopwatch and decrement requestLeft.
    synchronized (timerLock) {
      if (serverLatencyTimerIsRunning) {
        requestLeft.decrementAndGet();
        totalServerLatencyNano.addAndGet(serverLatencyTimer.elapsed(TimeUnit.NANOSECONDS));
        serverLatencyTimer.reset();
        serverLatencyTimerIsRunning = false;
      }
    }

    boolean isStreaming = operationType == OperationType.ServerStreaming;

    Attributes attributes =
        baseAttributes
            .toBuilder()
            .put(TABLE_ID_KEY, tableId)
            .put(CLUSTER_ID_KEY, cluster)
            .put(ZONE_ID_KEY, zone)
            .put(METHOD_KEY, spanName.toString())
            .put(CLIENT_NAME_KEY, NAME)
            .build();

    clientBlockingLatenciesHistogram.record(
        totalClientBlockingTime.get() / 10e6, attributes);

    // Patch the status until it's fixed in gax. When an attempt failed,
    // it'll throw a ServerStreamingAttemptException. Unwrap the exception
    // so it could get processed by extractStatus
    if (status instanceof ServerStreamingAttemptException) {
      status = status.getCause();
    }

    String statusStr = Util.extractStatus(status);

    attemptLatenciesHistogram.record(
        attemptTimer.elapsed(TimeUnit.NANOSECONDS) / 10e6,
        attributes.toBuilder().put(STREAMING_KEY, isStreaming).put(STATUS_KEY, statusStr).build());

    if (serverLatencies != null) {
      serverLatenciesHistogram.record(
          serverLatencies, attributes.toBuilder().put(STATUS_KEY, statusStr).build());
      connectivityErrorCounter.add(0, attributes.toBuilder().put(STATUS_KEY, statusStr).build());
    } else {
      connectivityErrorCounter.add(1, attributes.toBuilder().put(STATUS_KEY, statusStr).build());
    }
  }
}
