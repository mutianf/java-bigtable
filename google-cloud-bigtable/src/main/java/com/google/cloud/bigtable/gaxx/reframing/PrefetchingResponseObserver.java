/*
 * Copyright 2021 Google LLC
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

package com.google.cloud.bigtable.gaxx.reframing;

import com.google.api.gax.rpc.ResponseObserver;
import com.google.api.gax.rpc.StateCheckingResponseObserver;
import com.google.api.gax.rpc.StreamController;
import com.google.common.base.Preconditions;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;

public class PrefetchingResponseObserver<T>
    extends StateCheckingResponseObserver<T> {

  private final AtomicInteger lock = new AtomicInteger(0);

  private final ResponseObserver<T> outerResponseObserver;
  private StreamController innerController;

  // Buffer the response
  private ConcurrentLinkedQueue<T> buffer;
  // Keep track if the caller is waiting for a response
  private AtomicInteger counter;

  private final AtomicReference<Throwable> cancellation = new AtomicReference<>();

  private Throwable error;
  private volatile boolean done;

  public PrefetchingResponseObserver(ResponseObserver<T> responseObserver) {
    this.outerResponseObserver = responseObserver;
    this.counter = new AtomicInteger(0);
    this.buffer = new ConcurrentLinkedQueue<>();
  }

  @Override
  protected void onStartImpl(StreamController controller) {
    innerController = controller;
    innerController.disableAutoInboundFlowControl();

    final AtomicBoolean disableCalled = new AtomicBoolean(false);

    outerResponseObserver.onStart(
        new StreamController() {
          @Override
          public void disableAutoInboundFlowControl() {
            disableCalled.set(true);
          }

          @Override
          public void request(int count) {
            PrefetchingResponseObserver.this.onRequest(count);
          }

          @Override
          public void cancel() {
            PrefetchingResponseObserver.this.onCancel();
          }
        });

    Preconditions.checkState(disableCalled.get(), "disabled should be called");
    deliver();
  }

  private void onRequest(int count) {
    Preconditions.checkArgument(count == 1, "should only request 1");
    counter.addAndGet(count);
    deliver();
  }

  @Override
  protected void onResponseImpl(T response) {
    buffer.offer(response);
    deliver();
  }

  private void onCancel() {
    if (cancellation.compareAndSet(null, new CancellationException("User cancelled stream"))) {
      innerController.cancel();
    }

    deliver();
  }

  @Override
  protected void onErrorImpl(Throwable t) {
    error = t;
    done = true;
    deliver();
  }

  @Override
  protected void onCompleteImpl() {
    done = true;
    deliver();
  }

  private void deliver() {
    if (lock.incrementAndGet() != 1) {
      return;
    }

    do {
      if (maybeFinish()) {
        return;
      }

      // TODO do we need to consider demand that's been requested
      if (buffer.size() <= counter.get()) {
        // prefetch
        innerController.request(1);
      }
      // If the caller asked for a response, return 1 from the buffer
      if (counter.get() > 0) {
        counter.decrementAndGet();
        outerResponseObserver.onResponse(buffer.poll());
      }
    } while (lock.decrementAndGet() > 0);
  }

  private boolean maybeFinish() {
    // Check for cancellations
    Throwable localError = this.cancellation.get();
    if (localError != null) {
      outerResponseObserver.onError(localError);
      return true;
    }

    // Check for upstream termination and exhaustion of local buffers
    // TODO: do we need to check counter here?
    if (done && buffer.isEmpty()) {
      if (error != null) {
        outerResponseObserver.onError(error);
      } else {
        outerResponseObserver.onComplete();
      }
      return true;
    }

    // No termination conditions found, go back to business as usual
    return false;
  }
}
