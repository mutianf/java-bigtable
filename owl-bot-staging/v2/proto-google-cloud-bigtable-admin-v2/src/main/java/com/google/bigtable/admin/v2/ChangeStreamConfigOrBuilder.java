// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: google/bigtable/admin/v2/table.proto

// Protobuf Java Version: 3.25.2
package com.google.bigtable.admin.v2;

public interface ChangeStreamConfigOrBuilder extends
    // @@protoc_insertion_point(interface_extends:google.bigtable.admin.v2.ChangeStreamConfig)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <pre>
   * How long the change stream should be retained. Change stream data older
   * than the retention period will not be returned when reading the change
   * stream from the table.
   * Values must be at least 1 day and at most 7 days, and will be truncated to
   * microsecond granularity.
   * </pre>
   *
   * <code>.google.protobuf.Duration retention_period = 1;</code>
   * @return Whether the retentionPeriod field is set.
   */
  boolean hasRetentionPeriod();
  /**
   * <pre>
   * How long the change stream should be retained. Change stream data older
   * than the retention period will not be returned when reading the change
   * stream from the table.
   * Values must be at least 1 day and at most 7 days, and will be truncated to
   * microsecond granularity.
   * </pre>
   *
   * <code>.google.protobuf.Duration retention_period = 1;</code>
   * @return The retentionPeriod.
   */
  com.google.protobuf.Duration getRetentionPeriod();
  /**
   * <pre>
   * How long the change stream should be retained. Change stream data older
   * than the retention period will not be returned when reading the change
   * stream from the table.
   * Values must be at least 1 day and at most 7 days, and will be truncated to
   * microsecond granularity.
   * </pre>
   *
   * <code>.google.protobuf.Duration retention_period = 1;</code>
   */
  com.google.protobuf.DurationOrBuilder getRetentionPeriodOrBuilder();
}