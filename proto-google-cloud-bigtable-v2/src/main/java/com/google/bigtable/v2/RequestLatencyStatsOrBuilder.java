/*
 * Copyright 2024 Google LLC
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
// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: google/bigtable/v2/request_stats.proto

// Protobuf Java Version: 3.25.2
package com.google.bigtable.v2;

public interface RequestLatencyStatsOrBuilder
    extends
    // @@protoc_insertion_point(interface_extends:google.bigtable.v2.RequestLatencyStats)
    com.google.protobuf.MessageOrBuilder {

  /**
   *
   *
   * <pre>
   * The latency measured by the frontend server handling this request, from
   * when the request was received, to when this value is sent back in the
   * response. For more context on the component that is measuring this latency,
   * see: https://cloud.google.com/bigtable/docs/overview
   *
   * Note: This value may be slightly shorter than the value reported into
   * aggregate latency metrics in Monitoring for this request
   * (https://cloud.google.com/bigtable/docs/monitoring-instance) as this value
   * needs to be sent in the response before the latency measurement including
   * that transmission is finalized.
   *
   * Note: This value includes the end-to-end latency of contacting nodes in
   * the targeted cluster, e.g. measuring from when the first byte arrives at
   * the frontend server, to when this value is sent back as the last value in
   * the response, including any latency incurred by contacting nodes, waiting
   * for results from nodes, and finally sending results from nodes back to the
   * caller.
   * </pre>
   *
   * <code>.google.protobuf.Duration frontend_server_latency = 1;</code>
   *
   * @return Whether the frontendServerLatency field is set.
   */
  boolean hasFrontendServerLatency();
  /**
   *
   *
   * <pre>
   * The latency measured by the frontend server handling this request, from
   * when the request was received, to when this value is sent back in the
   * response. For more context on the component that is measuring this latency,
   * see: https://cloud.google.com/bigtable/docs/overview
   *
   * Note: This value may be slightly shorter than the value reported into
   * aggregate latency metrics in Monitoring for this request
   * (https://cloud.google.com/bigtable/docs/monitoring-instance) as this value
   * needs to be sent in the response before the latency measurement including
   * that transmission is finalized.
   *
   * Note: This value includes the end-to-end latency of contacting nodes in
   * the targeted cluster, e.g. measuring from when the first byte arrives at
   * the frontend server, to when this value is sent back as the last value in
   * the response, including any latency incurred by contacting nodes, waiting
   * for results from nodes, and finally sending results from nodes back to the
   * caller.
   * </pre>
   *
   * <code>.google.protobuf.Duration frontend_server_latency = 1;</code>
   *
   * @return The frontendServerLatency.
   */
  com.google.protobuf.Duration getFrontendServerLatency();
  /**
   *
   *
   * <pre>
   * The latency measured by the frontend server handling this request, from
   * when the request was received, to when this value is sent back in the
   * response. For more context on the component that is measuring this latency,
   * see: https://cloud.google.com/bigtable/docs/overview
   *
   * Note: This value may be slightly shorter than the value reported into
   * aggregate latency metrics in Monitoring for this request
   * (https://cloud.google.com/bigtable/docs/monitoring-instance) as this value
   * needs to be sent in the response before the latency measurement including
   * that transmission is finalized.
   *
   * Note: This value includes the end-to-end latency of contacting nodes in
   * the targeted cluster, e.g. measuring from when the first byte arrives at
   * the frontend server, to when this value is sent back as the last value in
   * the response, including any latency incurred by contacting nodes, waiting
   * for results from nodes, and finally sending results from nodes back to the
   * caller.
   * </pre>
   *
   * <code>.google.protobuf.Duration frontend_server_latency = 1;</code>
   */
  com.google.protobuf.DurationOrBuilder getFrontendServerLatencyOrBuilder();
}
