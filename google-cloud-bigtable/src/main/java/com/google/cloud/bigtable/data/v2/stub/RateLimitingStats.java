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
package com.google.cloud.bigtable.data.v2.stub;

import com.google.bigtable.v2.MutateRowsResponse;
import com.google.bigtable.v2.ServerStats;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.DoubleStream;

public class RateLimitingStats {
  // private long lastQpsUpdateTime;
  // private double currentQps = -1;
  public static double lowerQpsBound = 0.1;
  public static double upperQpsBound = 100_000;
  public static double MAX_QPS_CHANGE_RATE = 1.3;
  public static double MIN_QPS_CHANGE_RATE = 0.7;
  // public static double PERCENT_CHANGE_LIMIT = .3;


  private List<Long> cpuHistoryTimestamp = Lists.newArrayList();
  private List<Double> cpuHistory = Lists.newArrayList();
  // private long lastCpuUpdateTime = 0;

  private int id;

  public RateLimitingStats(int id) {
    // this.lastQpsUpdateTime = System.currentTimeMillis();
    this.id = id;
  }

  public void recordCpu(double cpu, long count) {
    long now = System.currentTimeMillis();
    cpuHistoryTimestamp.add(now);
    cpuHistory.add(cpu);

    DateFormat simple = new SimpleDateFormat(
        "dd MMM yyyy HH:mm:ss:SSS Z");
    Date result = new Date(now);
    System.out.println("kk_cpu id=" + id + " " + simple.format(result) + " " + cpu + " length=" + cpuHistory.size() + " count=" + count);
  }

  // public long getLastQpsUpdateTime() {
  //   return lastQpsUpdateTime;
  // }

  // public void updateLastQpsUpdateTime(long newQpsUpdateTime) {
  //   lastQpsUpdateTime = newQpsUpdateTime;
  // }

  // public double updateQps(double newQps) {
  //   this.currentQps = newQps;
  //   return newQps;
  // }

  // This function is to calculate the QPS based on current CPU
  // return value is positive for increasing rate
  static double calculateNewQps(double avgCpu, double target, double currentRate, int logId) {
    // If no CPU data is returned, don't change rate
    if (avgCpu == 0) {
      // TODO: report error
      return currentRate;
    }

    // Our theory is that CPU in CBT would be proportional to QPS, where the coefficient is unknown
    // CPU = QPS * e
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

    double newRateChange = Math.max(Math.min(target / avgCpu, MAX_QPS_CHANGE_RATE), MIN_QPS_CHANGE_RATE);
    double newRate = Math.max(Math.min(currentRate * newRateChange, upperQpsBound), lowerQpsBound);

    System.out.println("kk_qps id=" + logId + " avgCpu=" + avgCpu + " target=" + target +
        " change=" + target / avgCpu + " currentQps=" + currentRate + " newQps=" + newRate);
    return newRate;

    // // When the CPU is above the target threshold, reduce the rate by a percentage from the target
    // // If the average CPU is within 5% of the target, maintain the currentRate
    // // If the average CPU is below the target, continue to increase till a maintainable CPU is met
    // if (cpuDelta > 0) {
    //   double percentChange = 1 - Math.min(cpuDelta / (100 - target), PERCENT_CHANGE_LIMIT);
    //   newRate = (long)(percentChange * currentRate);
    // } else if (Math.abs(cpuDelta) > 5){
    //   newRate = currentRate + (currentRate * PERCENT_CHANGE_LIMIT);
    // }
    //
    // if (newRate < lowerQpsBound) {
    //   return lowerQpsBound;
    // } else if (newRate > upperQpsBound) {
    //   return upperQpsBound;
    // }
    // return newRate;
  }

  static double[] getCpuList(MutateRowsResponse response) {
    if (response != null && response.hasServerStats()) {
      ServerStats stats = response.getServerStats();

      double[] cpus = new double[stats.getCpuStatsList().size()];
      for (int i = 0; i < stats.getCpuStatsList().size(); i++) {
        double currentCPU = 100 * ((double)stats.getCpuStats(i).getRecentGcuMillisecondsPerSecond() / stats.getCpuStats(i).getMilligcuLimit());

        // It's possible for CPU to be over 100, this will limit the CPU to be within [0, 100)
        cpus[i] = (currentCPU > 100) ? 99.9 : currentCPU;
      }
      return cpus;
    }
    return new double[]{};
  }
}
