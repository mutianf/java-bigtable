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

import com.google.bigtable.v2.MutateRowsResponse;
import com.google.bigtable.v2.ServerStats;
import java.util.logging.Logger;

/** Utilities to help calculate average CPU and new QPS. */
public class RateLimitingUtils {
  private static Logger LOG = Logger.getLogger(RateLimitingUtils.class.toString());
  public static double lowerQpsBound = 0.1;
  public static double upperQpsBound = 100_000;
  public static double MAX_QPS_CHANGE_RATE = 1.3;
  public static double MIN_QPS_CHANGE_RATE = 0.7;

  // This function is to calculate the QPS based on current CPU
  // return value is positive for increasing rate
  static double calculateNewQps(double avgCpu, double target, double currentRate, int logId) {
    // If no CPU data is returned, don't change rate
    if (avgCpu == 0) {
      LOG.warning(logId + " - can't calculate QPS when avg CPU is 0");
      return currentRate;
    }

    // Our theory is that CPU in CBT would be proportional to QPS, where the coefficient is unknown
    // CPU = QPS * e.
    // Coefficient e depends on the specific workload and the number of clients hitting the same TS
    // at the same time.
    // E.g. if there's 1 client writing 1kb rows to a TS, we expect the TS can take 10K QPS and CPU
    // is roughly 80%. And 5K QPS would result in 40% CPU. e = 0.00008.
    // If there're 2 clients, then each can write 5K QPS to the TS and CPU would be 80%. So e is
    // cut in half.
    // For a given number of clients hitting the server and the given workload, we assume the
    // coefficient is stable and the QPS could be adjusted proportionally.

    // If current CPU is close enough to the target, don't change rate
    if (Math.abs(avgCpu - target) < 2) {
      return currentRate;
    }

    double newRateChange =
        Math.max(Math.min(target / avgCpu, MAX_QPS_CHANGE_RATE), MIN_QPS_CHANGE_RATE);
    double newRate = Math.max(Math.min(currentRate * newRateChange, upperQpsBound), lowerQpsBound);

    LOG.fine(
        logId
            + " - updated QPS. Current avg CPU="
            + avgCpu
            + " target CPU="
            + target
            + " current QPS="
            + currentRate
            + " new QPS="
            + newRate);
    return newRate;
  }

  static double[] getCpuList(MutateRowsResponse response) {
    if (response != null && response.hasServerStats()) {
      ServerStats stats = response.getServerStats();

      double[] cpus = new double[stats.getCpuStatsList().size()];
      for (int i = 0; i < stats.getCpuStatsList().size(); i++) {
        double currentCPU =
            100
                * ((double) stats.getCpuStats(i).getRecentGcuMillisecondsPerSecond()
                    / stats.getCpuStats(i).getMilligcuLimit());

        // It's possible for CPU to be over 100, this will limit the CPU to be within [0, 100)
        cpus[i] = (currentCPU > 100) ? 99.9 : currentCPU;
      }
      return cpus;
    }
    return new double[] {};
  }
}
