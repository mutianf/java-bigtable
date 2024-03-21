// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: google/bigtable/admin/v2/instance.proto

// Protobuf Java Version: 3.25.2
package com.google.bigtable.admin.v2;

public interface ClusterOrBuilder extends
    // @@protoc_insertion_point(interface_extends:google.bigtable.admin.v2.Cluster)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <pre>
   * The unique name of the cluster. Values are of the form
   * `projects/{project}/instances/{instance}/clusters/[a-z][-a-z0-9]*`.
   * </pre>
   *
   * <code>string name = 1;</code>
   * @return The name.
   */
  java.lang.String getName();
  /**
   * <pre>
   * The unique name of the cluster. Values are of the form
   * `projects/{project}/instances/{instance}/clusters/[a-z][-a-z0-9]*`.
   * </pre>
   *
   * <code>string name = 1;</code>
   * @return The bytes for name.
   */
  com.google.protobuf.ByteString
      getNameBytes();

  /**
   * <pre>
   * Immutable. The location where this cluster's nodes and storage reside. For
   * best performance, clients should be located as close as possible to this
   * cluster. Currently only zones are supported, so values should be of the
   * form `projects/{project}/locations/{zone}`.
   * </pre>
   *
   * <code>string location = 2 [(.google.api.field_behavior) = IMMUTABLE, (.google.api.resource_reference) = { ... }</code>
   * @return The location.
   */
  java.lang.String getLocation();
  /**
   * <pre>
   * Immutable. The location where this cluster's nodes and storage reside. For
   * best performance, clients should be located as close as possible to this
   * cluster. Currently only zones are supported, so values should be of the
   * form `projects/{project}/locations/{zone}`.
   * </pre>
   *
   * <code>string location = 2 [(.google.api.field_behavior) = IMMUTABLE, (.google.api.resource_reference) = { ... }</code>
   * @return The bytes for location.
   */
  com.google.protobuf.ByteString
      getLocationBytes();

  /**
   * <pre>
   * Output only. The current state of the cluster.
   * </pre>
   *
   * <code>.google.bigtable.admin.v2.Cluster.State state = 3 [(.google.api.field_behavior) = OUTPUT_ONLY];</code>
   * @return The enum numeric value on the wire for state.
   */
  int getStateValue();
  /**
   * <pre>
   * Output only. The current state of the cluster.
   * </pre>
   *
   * <code>.google.bigtable.admin.v2.Cluster.State state = 3 [(.google.api.field_behavior) = OUTPUT_ONLY];</code>
   * @return The state.
   */
  com.google.bigtable.admin.v2.Cluster.State getState();

  /**
   * <pre>
   * The number of nodes allocated to this cluster. More nodes enable higher
   * throughput and more consistent performance.
   * </pre>
   *
   * <code>int32 serve_nodes = 4;</code>
   * @return The serveNodes.
   */
  int getServeNodes();

  /**
   * <pre>
   * Configuration for this cluster.
   * </pre>
   *
   * <code>.google.bigtable.admin.v2.Cluster.ClusterConfig cluster_config = 7;</code>
   * @return Whether the clusterConfig field is set.
   */
  boolean hasClusterConfig();
  /**
   * <pre>
   * Configuration for this cluster.
   * </pre>
   *
   * <code>.google.bigtable.admin.v2.Cluster.ClusterConfig cluster_config = 7;</code>
   * @return The clusterConfig.
   */
  com.google.bigtable.admin.v2.Cluster.ClusterConfig getClusterConfig();
  /**
   * <pre>
   * Configuration for this cluster.
   * </pre>
   *
   * <code>.google.bigtable.admin.v2.Cluster.ClusterConfig cluster_config = 7;</code>
   */
  com.google.bigtable.admin.v2.Cluster.ClusterConfigOrBuilder getClusterConfigOrBuilder();

  /**
   * <pre>
   * Immutable. The type of storage used by this cluster to serve its
   * parent instance's tables, unless explicitly overridden.
   * </pre>
   *
   * <code>.google.bigtable.admin.v2.StorageType default_storage_type = 5 [(.google.api.field_behavior) = IMMUTABLE];</code>
   * @return The enum numeric value on the wire for defaultStorageType.
   */
  int getDefaultStorageTypeValue();
  /**
   * <pre>
   * Immutable. The type of storage used by this cluster to serve its
   * parent instance's tables, unless explicitly overridden.
   * </pre>
   *
   * <code>.google.bigtable.admin.v2.StorageType default_storage_type = 5 [(.google.api.field_behavior) = IMMUTABLE];</code>
   * @return The defaultStorageType.
   */
  com.google.bigtable.admin.v2.StorageType getDefaultStorageType();

  /**
   * <pre>
   * Immutable. The encryption configuration for CMEK-protected clusters.
   * </pre>
   *
   * <code>.google.bigtable.admin.v2.Cluster.EncryptionConfig encryption_config = 6 [(.google.api.field_behavior) = IMMUTABLE];</code>
   * @return Whether the encryptionConfig field is set.
   */
  boolean hasEncryptionConfig();
  /**
   * <pre>
   * Immutable. The encryption configuration for CMEK-protected clusters.
   * </pre>
   *
   * <code>.google.bigtable.admin.v2.Cluster.EncryptionConfig encryption_config = 6 [(.google.api.field_behavior) = IMMUTABLE];</code>
   * @return The encryptionConfig.
   */
  com.google.bigtable.admin.v2.Cluster.EncryptionConfig getEncryptionConfig();
  /**
   * <pre>
   * Immutable. The encryption configuration for CMEK-protected clusters.
   * </pre>
   *
   * <code>.google.bigtable.admin.v2.Cluster.EncryptionConfig encryption_config = 6 [(.google.api.field_behavior) = IMMUTABLE];</code>
   */
  com.google.bigtable.admin.v2.Cluster.EncryptionConfigOrBuilder getEncryptionConfigOrBuilder();

  com.google.bigtable.admin.v2.Cluster.ConfigCase getConfigCase();
}