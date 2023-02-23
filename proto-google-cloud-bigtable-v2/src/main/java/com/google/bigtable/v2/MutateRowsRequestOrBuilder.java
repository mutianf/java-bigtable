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

// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: google/bigtable/v2/bigtable.proto

package com.google.bigtable.v2;

public interface MutateRowsRequestOrBuilder
    extends
    // @@protoc_insertion_point(interface_extends:google.bigtable.v2.MutateRowsRequest)
    com.google.protobuf.MessageOrBuilder {

  /**
   *
   *
   * <pre>
   * Required. The unique name of the table to which the mutations should be
   * applied.
   * </pre>
   *
   * <code>
   * string table_name = 1 [(.google.api.field_behavior) = REQUIRED, (.google.api.resource_reference) = { ... }
   * </code>
   *
   * @return The tableName.
   */
  java.lang.String getTableName();
  /**
   *
   *
   * <pre>
   * Required. The unique name of the table to which the mutations should be
   * applied.
   * </pre>
   *
   * <code>
   * string table_name = 1 [(.google.api.field_behavior) = REQUIRED, (.google.api.resource_reference) = { ... }
   * </code>
   *
   * @return The bytes for tableName.
   */
  com.google.protobuf.ByteString getTableNameBytes();

  /**
   *
   *
   * <pre>
   * This value specifies routing for replication. If not specified, the
   * "default" application profile will be used.
   * </pre>
   *
   * <code>string app_profile_id = 3;</code>
   *
   * @return The appProfileId.
   */
  java.lang.String getAppProfileId();
  /**
   *
   *
   * <pre>
   * This value specifies routing for replication. If not specified, the
   * "default" application profile will be used.
   * </pre>
   *
   * <code>string app_profile_id = 3;</code>
   *
   * @return The bytes for appProfileId.
   */
  com.google.protobuf.ByteString getAppProfileIdBytes();

  /**
   *
   *
   * <pre>
   * Required. The row keys and corresponding mutations to be applied in bulk.
   * Each entry is applied as an atomic mutation, but the entries may be
   * applied in arbitrary order (even between entries for the same row).
   * At least one entry must be specified, and in total the entries can
   * contain at most 100000 mutations.
   * </pre>
   *
   * <code>
   * repeated .google.bigtable.v2.MutateRowsRequest.Entry entries = 2 [(.google.api.field_behavior) = REQUIRED];
   * </code>
   */
  java.util.List<com.google.bigtable.v2.MutateRowsRequest.Entry> getEntriesList();
  /**
   *
   *
   * <pre>
   * Required. The row keys and corresponding mutations to be applied in bulk.
   * Each entry is applied as an atomic mutation, but the entries may be
   * applied in arbitrary order (even between entries for the same row).
   * At least one entry must be specified, and in total the entries can
   * contain at most 100000 mutations.
   * </pre>
   *
   * <code>
   * repeated .google.bigtable.v2.MutateRowsRequest.Entry entries = 2 [(.google.api.field_behavior) = REQUIRED];
   * </code>
   */
  com.google.bigtable.v2.MutateRowsRequest.Entry getEntries(int index);
  /**
   *
   *
   * <pre>
   * Required. The row keys and corresponding mutations to be applied in bulk.
   * Each entry is applied as an atomic mutation, but the entries may be
   * applied in arbitrary order (even between entries for the same row).
   * At least one entry must be specified, and in total the entries can
   * contain at most 100000 mutations.
   * </pre>
   *
   * <code>
   * repeated .google.bigtable.v2.MutateRowsRequest.Entry entries = 2 [(.google.api.field_behavior) = REQUIRED];
   * </code>
   */
  int getEntriesCount();
  /**
   *
   *
   * <pre>
   * Required. The row keys and corresponding mutations to be applied in bulk.
   * Each entry is applied as an atomic mutation, but the entries may be
   * applied in arbitrary order (even between entries for the same row).
   * At least one entry must be specified, and in total the entries can
   * contain at most 100000 mutations.
   * </pre>
   *
   * <code>
   * repeated .google.bigtable.v2.MutateRowsRequest.Entry entries = 2 [(.google.api.field_behavior) = REQUIRED];
   * </code>
   */
  java.util.List<? extends com.google.bigtable.v2.MutateRowsRequest.EntryOrBuilder>
      getEntriesOrBuilderList();
  /**
   *
   *
   * <pre>
   * Required. The row keys and corresponding mutations to be applied in bulk.
   * Each entry is applied as an atomic mutation, but the entries may be
   * applied in arbitrary order (even between entries for the same row).
   * At least one entry must be specified, and in total the entries can
   * contain at most 100000 mutations.
   * </pre>
   *
   * <code>
   * repeated .google.bigtable.v2.MutateRowsRequest.Entry entries = 2 [(.google.api.field_behavior) = REQUIRED];
   * </code>
   */
  com.google.bigtable.v2.MutateRowsRequest.EntryOrBuilder getEntriesOrBuilder(int index);
}
