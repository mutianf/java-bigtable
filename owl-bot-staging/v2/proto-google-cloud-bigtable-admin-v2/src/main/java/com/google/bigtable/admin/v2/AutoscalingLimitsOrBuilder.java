// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: google/bigtable/admin/v2/instance.proto

// Protobuf Java Version: 3.25.2
package com.google.bigtable.admin.v2;

public interface AutoscalingLimitsOrBuilder extends
    // @@protoc_insertion_point(interface_extends:google.bigtable.admin.v2.AutoscalingLimits)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <pre>
   * Required. Minimum number of nodes to scale down to.
   * </pre>
   *
   * <code>int32 min_serve_nodes = 1 [(.google.api.field_behavior) = REQUIRED];</code>
   * @return The minServeNodes.
   */
  int getMinServeNodes();

  /**
   * <pre>
   * Required. Maximum number of nodes to scale up to.
   * </pre>
   *
   * <code>int32 max_serve_nodes = 2 [(.google.api.field_behavior) = REQUIRED];</code>
   * @return The maxServeNodes.
   */
  int getMaxServeNodes();
}