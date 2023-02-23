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

import static com.google.common.truth.Truth.assertThat;

import com.google.api.gax.grpc.GrpcCallContext;
import com.google.api.gax.grpc.GrpcStatusCode;
import com.google.api.gax.rpc.ApiCallContext;
import com.google.api.gax.rpc.DeadlineExceededException;
import com.google.api.gax.rpc.ResponseObserver;
import com.google.api.gax.rpc.ServerStreamingCallable;
import com.google.api.gax.rpc.StreamController;
import com.google.api.gax.rpc.UnavailableException;
import com.google.bigtable.v2.MutateRowsRequest;
import com.google.bigtable.v2.MutateRowsResponse;
import com.google.bigtable.v2.ServerStats;
import io.grpc.Status;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.mockito.Mockito;

@RunWith(JUnit4.class)
public class RateLimitingResponseObserverTest {
  private final MutateRowsRequest request =
      MutateRowsRequest.newBuilder().getDefaultInstanceForType();
  private final ResponseObserver responseObserver = Mockito.mock(ResponseObserver.class);
  private final ApiCallContext context = GrpcCallContext.createDefault();
  private MockCallable innerCallable;
  RateLimitingServerStreamingCallable callableToTest;

  @Before
  public void setup() throws Exception {
    innerCallable = new MockCallable();
    callableToTest = new RateLimitingServerStreamingCallable(innerCallable, 70);
  }

  @Test
  public void testScalingDown() throws Exception {
    callableToTest = new RateLimitingServerStreamingCallable(innerCallable, 70);
    callableToTest.call(request, responseObserver, context);

    // make sure QPS will be updated
    callableToTest.getLastQpsChangeTime().set(1000);
    double oldQps = callableToTest.getCurrentRate();

    ServerStats serverStats =
        ServerStats.newBuilder()
            .addCpuStats(
                ServerStats.CPUStats.newBuilder()
                    .setMilligcuLimit(100)
                    .setRecentGcuMillisecondsPerSecond(90))
            .build();

    MutateRowsResponse response =
        MutateRowsResponse.newBuilder().setServerStats(serverStats).build();

    innerCallable.getObserver().onResponse(response);

    // Give the thread sometime to update the QPS
    Thread.sleep(100);
    double newQps = callableToTest.getCurrentRate();

    assertThat(newQps).isLessThan(oldQps);

    innerCallable.getObserver().onComplete();
  }

  @Test
  public void testScalingUp() throws Exception {
    callableToTest = new RateLimitingServerStreamingCallable(innerCallable, 70);
    callableToTest.call(request, responseObserver, context);

    // make sure QPS will be updated
    callableToTest.getLastQpsChangeTime().set(1000);
    double oldQps = callableToTest.getCurrentRate();

    ServerStats serverStats =
        ServerStats.newBuilder()
            .addCpuStats(
                ServerStats.CPUStats.newBuilder()
                    .setMilligcuLimit(100)
                    .setRecentGcuMillisecondsPerSecond(40))
            .build();

    MutateRowsResponse response =
        MutateRowsResponse.newBuilder().setServerStats(serverStats).build();

    innerCallable.getObserver().onResponse(response);

    // Give the thread sometime to update the QPS
    Thread.sleep(100);
    double newQps = callableToTest.getCurrentRate();

    assertThat(newQps).isGreaterThan(oldQps);

    innerCallable.getObserver().onComplete();
  }

  @Test
  public void testMaintainQps() throws Exception {
    callableToTest = new RateLimitingServerStreamingCallable(innerCallable, 70);
    callableToTest.call(request, responseObserver, context);

    // make sure QPS will be updated
    callableToTest.getLastQpsChangeTime().set(1000);
    double oldQps = callableToTest.getCurrentRate();

    ServerStats serverStats =
        ServerStats.newBuilder()
            .addCpuStats(
                ServerStats.CPUStats.newBuilder()
                    .setMilligcuLimit(100)
                    .setRecentGcuMillisecondsPerSecond(71))
            .build();

    MutateRowsResponse response =
        MutateRowsResponse.newBuilder().setServerStats(serverStats).build();

    innerCallable.getObserver().onResponse(response);

    // Give the thread sometime to update the QPS
    Thread.sleep(100);
    double newQps = callableToTest.getCurrentRate();

    assertThat(newQps).isEqualTo(oldQps);

    innerCallable.getObserver().onComplete();
  }

  @Test
  public void testNotUpdatingQps() throws Exception {
    callableToTest = new RateLimitingServerStreamingCallable(innerCallable, 70);
    callableToTest.call(request, responseObserver, context);

    double oldQps = callableToTest.getCurrentRate();

    ServerStats serverStats =
        ServerStats.newBuilder()
            .addCpuStats(
                ServerStats.CPUStats.newBuilder()
                    .setMilligcuLimit(100)
                    .setRecentGcuMillisecondsPerSecond(71))
            .build();

    MutateRowsResponse response =
        MutateRowsResponse.newBuilder().setServerStats(serverStats).build();

    // set last qps update time to now
    callableToTest.getLastQpsChangeTime().set(System.currentTimeMillis());

    innerCallable.getObserver().onResponse(response);

    // Give the thread sometime to update the QPS
    Thread.sleep(100);
    double newQps = callableToTest.getCurrentRate();

    assertThat(newQps).isEqualTo(oldQps);

    innerCallable.getObserver().onComplete();
  }

  @Test
  public void testDeadlineExceeded() throws Exception {
    callableToTest = new RateLimitingServerStreamingCallable(innerCallable, 70);
    callableToTest.call(request, responseObserver, context);

    // make sure QPS will be updated
    callableToTest.getLastQpsChangeTime().set(1000);
    double oldQps = callableToTest.getCurrentRate();

    DeadlineExceededException error =
        new DeadlineExceededException(
            "Deadline exceeded", null, GrpcStatusCode.of(Status.Code.DEADLINE_EXCEEDED), true);

    innerCallable.getObserver().onError(error);

    // Give the thread sometime to update the QPS
    Thread.sleep(100);
    double newQps = callableToTest.getCurrentRate();

    assertThat(newQps).isLessThan(oldQps);
  }

  @Test
  public void testUnavailable() throws Exception {
    callableToTest = new RateLimitingServerStreamingCallable(innerCallable, 70);
    callableToTest.call(request, responseObserver, context);

    // make sure QPS will be updated
    callableToTest.getLastQpsChangeTime().set(1000);
    double oldQps = callableToTest.getCurrentRate();

    UnavailableException error =
        new UnavailableException(
            "Unavailable", null, GrpcStatusCode.of(Status.Code.UNAVAILABLE), true);

    innerCallable.getObserver().onError(error);

    // Give the thread sometime to update the QPS
    Thread.sleep(100);
    double newQps = callableToTest.getCurrentRate();

    assertThat(newQps).isLessThan(oldQps);
  }

  class MockResponseObserver implements ResponseObserver {

    private ResponseObserver observer;

    MockResponseObserver(ResponseObserver responseObserver) {
      this.observer = responseObserver;
    }

    @Override
    public void onStart(StreamController streamController) {
      observer.onStart(streamController);
    }

    @Override
    public void onResponse(Object o) {
      observer.onResponse(o);
    }

    @Override
    public void onError(Throwable throwable) {
      observer.onError(throwable);
    }

    @Override
    public void onComplete() {
      observer.onComplete();
    }
  }

  class MockCallable extends ServerStreamingCallable<MutateRowsRequest, MutateRowsResponse> {

    private ResponseObserver observer;

    @Override
    public void call(
        MutateRowsRequest mutateRowsRequest,
        ResponseObserver<MutateRowsResponse> responseObserver,
        ApiCallContext apiCallContext) {
      observer = new MockResponseObserver(responseObserver);
    }

    ResponseObserver getObserver() {
      return observer;
    }
  }
}
