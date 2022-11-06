package com.google.cloud.bigtable.data.v2.it;

import com.google.cloud.bigtable.data.v2.BigtableDataClient;
import com.google.cloud.bigtable.data.v2.BigtableDataSettings;
import com.google.cloud.bigtable.data.v2.models.Query;
import com.google.cloud.bigtable.test_helpers.env.EmulatorEnv;
import com.google.cloud.bigtable.test_helpers.env.TestEnvRule;
import com.google.cloud.monitoring.v3.MetricServiceClient;
import com.google.common.collect.Lists;
import com.google.common.util.concurrent.RateLimiter;
import com.google.monitoring.v3.ListTimeSeriesRequest;
import com.google.monitoring.v3.ListTimeSeriesResponse;
import com.google.monitoring.v3.ProjectName;
import com.google.monitoring.v3.TimeInterval;
import com.google.monitoring.v3.TimeSeries;
import com.google.protobuf.util.Timestamps;
import org.junit.BeforeClass;
import org.junit.ClassRule;
import org.junit.Test;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.time.Duration;

import static com.google.common.truth.TruthJUnit.assume;

public class RegressionIT {
    @ClassRule
    public static TestEnvRule testEnvRule = new TestEnvRule();
    public static MetricServiceClient metricClient;

    @BeforeClass
    public static void setUpClass() throws IOException {
        assume()
                .withMessage("Regression test is not supported by emulator")
                .that(testEnvRule.env())
                .isNotInstanceOf(EmulatorEnv.class);

        // Enable built in metrics
        BigtableDataSettings.enableBuiltinMetrics();

        // Create a cloud monitoring client
        metricClient = MetricServiceClient.create();
    }

    @Test
    public void test() {
        String hostname = null;
        try {
            hostname = InetAddress.getLocalHost().getHostName();
            System.out.println("hostname is: " + hostname);
        } catch (UnknownHostException e) {
            System.out.println("failed to detect host name");
        }

        // 1. get the CPU usage before running the test
        String cpuMetricFilter =
                String.format(
                        "metric.type=\"compute.googleapis.com/instance/cpu/utilization\" "
                                + "AND resource.labels.instance_name=\"%s\"", hostname);

        String memoryMetricFilter =
                String.format("metric.type=\"agent.googleapis.com/memory/bytes_used\" "
                        + "AND resource.labels.instance_name=\"%s\"", hostname);

        ProjectName name = ProjectName.of(testEnvRule.env().getProjectId());

        // Restrict time to last 10 minutes
        long startMillis = System.currentTimeMillis() - Duration.ofMinutes(10).toMillis();
        TimeInterval interval =
                TimeInterval.newBuilder()
                        .setStartTime(Timestamps.fromMillis(startMillis))
                        .setEndTime(Timestamps.fromMillis(System.currentTimeMillis()))
                        .build();

        ListTimeSeriesRequest.Builder requestBuilder =
                ListTimeSeriesRequest.newBuilder()
                        .setName(name.toString())
                        .setFilter(cpuMetricFilter)
                        .setInterval(interval)
                        .setView(ListTimeSeriesRequest.TimeSeriesView.FULL);
        ListTimeSeriesResponse response =
                metricClient.listTimeSeriesCallable().call(requestBuilder.build());


        // TODO: calculate average cpu
        long avgCPU = 0;
        for (TimeSeries timeSeries : response.getTimeSeriesList()) {
            timeSeries.getPoints(0);
        }

        long avgMem = 0;


        // Start the client with 1000 QPS, run for 10 minutes
        RateLimiter rateLimiter = RateLimiter.create(1000);

        BigtableDataClient client = testEnvRule.env().getDataClient();

        for (int i = 0; i < 10 * 60 * 1000; i++) {
            rateLimiter.acquire();
            Lists.newArrayList(client.readRows(Query.create(testEnvRule.env().getTableId()).limit(10)));
        }

        // Get cpu usage again
        response =
                metricClient.listTimeSeriesCallable().call(requestBuilder.build());

        // calculate cpu usage again

    }
}
