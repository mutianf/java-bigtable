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
// source: google/bigtable/v2/data.proto

package com.google.bigtable.v2;

/**
 *
 *
 * <pre>
 * Specifies a non-contiguous set of rows.
 * </pre>
 *
 * Protobuf type {@code google.bigtable.v2.RowSet}
 */
public final class RowSet extends com.google.protobuf.GeneratedMessageV3
    implements
    // @@protoc_insertion_point(message_implements:google.bigtable.v2.RowSet)
    RowSetOrBuilder {
  private static final long serialVersionUID = 0L;
  // Use RowSet.newBuilder() to construct.
  private RowSet(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }

  private RowSet() {
    rowKeys_ = java.util.Collections.emptyList();
    rowRanges_ = java.util.Collections.emptyList();
  }

  @java.lang.Override
  @SuppressWarnings({"unused"})
  protected java.lang.Object newInstance(UnusedPrivateParameter unused) {
    return new RowSet();
  }

  @java.lang.Override
  public final com.google.protobuf.UnknownFieldSet getUnknownFields() {
    return this.unknownFields;
  }

  public static final com.google.protobuf.Descriptors.Descriptor getDescriptor() {
    return com.google.bigtable.v2.DataProto.internal_static_google_bigtable_v2_RowSet_descriptor;
  }

  @java.lang.Override
  protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internalGetFieldAccessorTable() {
    return com.google.bigtable.v2.DataProto
        .internal_static_google_bigtable_v2_RowSet_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            com.google.bigtable.v2.RowSet.class, com.google.bigtable.v2.RowSet.Builder.class);
  }

  public static final int ROW_KEYS_FIELD_NUMBER = 1;

  @SuppressWarnings("serial")
  private java.util.List<com.google.protobuf.ByteString> rowKeys_;
  /**
   *
   *
   * <pre>
   * Single rows included in the set.
   * </pre>
   *
   * <code>repeated bytes row_keys = 1;</code>
   *
   * @return A list containing the rowKeys.
   */
  @java.lang.Override
  public java.util.List<com.google.protobuf.ByteString> getRowKeysList() {
    return rowKeys_;
  }
  /**
   *
   *
   * <pre>
   * Single rows included in the set.
   * </pre>
   *
   * <code>repeated bytes row_keys = 1;</code>
   *
   * @return The count of rowKeys.
   */
  public int getRowKeysCount() {
    return rowKeys_.size();
  }
  /**
   *
   *
   * <pre>
   * Single rows included in the set.
   * </pre>
   *
   * <code>repeated bytes row_keys = 1;</code>
   *
   * @param index The index of the element to return.
   * @return The rowKeys at the given index.
   */
  public com.google.protobuf.ByteString getRowKeys(int index) {
    return rowKeys_.get(index);
  }

  public static final int ROW_RANGES_FIELD_NUMBER = 2;

  @SuppressWarnings("serial")
  private java.util.List<com.google.bigtable.v2.RowRange> rowRanges_;
  /**
   *
   *
   * <pre>
   * Contiguous row ranges included in the set.
   * </pre>
   *
   * <code>repeated .google.bigtable.v2.RowRange row_ranges = 2;</code>
   */
  @java.lang.Override
  public java.util.List<com.google.bigtable.v2.RowRange> getRowRangesList() {
    return rowRanges_;
  }
  /**
   *
   *
   * <pre>
   * Contiguous row ranges included in the set.
   * </pre>
   *
   * <code>repeated .google.bigtable.v2.RowRange row_ranges = 2;</code>
   */
  @java.lang.Override
  public java.util.List<? extends com.google.bigtable.v2.RowRangeOrBuilder>
      getRowRangesOrBuilderList() {
    return rowRanges_;
  }
  /**
   *
   *
   * <pre>
   * Contiguous row ranges included in the set.
   * </pre>
   *
   * <code>repeated .google.bigtable.v2.RowRange row_ranges = 2;</code>
   */
  @java.lang.Override
  public int getRowRangesCount() {
    return rowRanges_.size();
  }
  /**
   *
   *
   * <pre>
   * Contiguous row ranges included in the set.
   * </pre>
   *
   * <code>repeated .google.bigtable.v2.RowRange row_ranges = 2;</code>
   */
  @java.lang.Override
  public com.google.bigtable.v2.RowRange getRowRanges(int index) {
    return rowRanges_.get(index);
  }
  /**
   *
   *
   * <pre>
   * Contiguous row ranges included in the set.
   * </pre>
   *
   * <code>repeated .google.bigtable.v2.RowRange row_ranges = 2;</code>
   */
  @java.lang.Override
  public com.google.bigtable.v2.RowRangeOrBuilder getRowRangesOrBuilder(int index) {
    return rowRanges_.get(index);
  }

  private byte memoizedIsInitialized = -1;

  @java.lang.Override
  public final boolean isInitialized() {
    byte isInitialized = memoizedIsInitialized;
    if (isInitialized == 1) return true;
    if (isInitialized == 0) return false;

    memoizedIsInitialized = 1;
    return true;
  }

  @java.lang.Override
  public void writeTo(com.google.protobuf.CodedOutputStream output) throws java.io.IOException {
    for (int i = 0; i < rowKeys_.size(); i++) {
      output.writeBytes(1, rowKeys_.get(i));
    }
    for (int i = 0; i < rowRanges_.size(); i++) {
      output.writeMessage(2, rowRanges_.get(i));
    }
    getUnknownFields().writeTo(output);
  }

  @java.lang.Override
  public int getSerializedSize() {
    int size = memoizedSize;
    if (size != -1) return size;

    size = 0;
    {
      int dataSize = 0;
      for (int i = 0; i < rowKeys_.size(); i++) {
        dataSize += com.google.protobuf.CodedOutputStream.computeBytesSizeNoTag(rowKeys_.get(i));
      }
      size += dataSize;
      size += 1 * getRowKeysList().size();
    }
    for (int i = 0; i < rowRanges_.size(); i++) {
      size += com.google.protobuf.CodedOutputStream.computeMessageSize(2, rowRanges_.get(i));
    }
    size += getUnknownFields().getSerializedSize();
    memoizedSize = size;
    return size;
  }

  @java.lang.Override
  public boolean equals(final java.lang.Object obj) {
    if (obj == this) {
      return true;
    }
    if (!(obj instanceof com.google.bigtable.v2.RowSet)) {
      return super.equals(obj);
    }
    com.google.bigtable.v2.RowSet other = (com.google.bigtable.v2.RowSet) obj;

    if (!getRowKeysList().equals(other.getRowKeysList())) return false;
    if (!getRowRangesList().equals(other.getRowRangesList())) return false;
    if (!getUnknownFields().equals(other.getUnknownFields())) return false;
    return true;
  }

  @java.lang.Override
  public int hashCode() {
    if (memoizedHashCode != 0) {
      return memoizedHashCode;
    }
    int hash = 41;
    hash = (19 * hash) + getDescriptor().hashCode();
    if (getRowKeysCount() > 0) {
      hash = (37 * hash) + ROW_KEYS_FIELD_NUMBER;
      hash = (53 * hash) + getRowKeysList().hashCode();
    }
    if (getRowRangesCount() > 0) {
      hash = (37 * hash) + ROW_RANGES_FIELD_NUMBER;
      hash = (53 * hash) + getRowRangesList().hashCode();
    }
    hash = (29 * hash) + getUnknownFields().hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static com.google.bigtable.v2.RowSet parseFrom(java.nio.ByteBuffer data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }

  public static com.google.bigtable.v2.RowSet parseFrom(
      java.nio.ByteBuffer data, com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }

  public static com.google.bigtable.v2.RowSet parseFrom(com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }

  public static com.google.bigtable.v2.RowSet parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }

  public static com.google.bigtable.v2.RowSet parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }

  public static com.google.bigtable.v2.RowSet parseFrom(
      byte[] data, com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }

  public static com.google.bigtable.v2.RowSet parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3.parseWithIOException(PARSER, input);
  }

  public static com.google.bigtable.v2.RowSet parseFrom(
      java.io.InputStream input, com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3.parseWithIOException(
        PARSER, input, extensionRegistry);
  }

  public static com.google.bigtable.v2.RowSet parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3.parseDelimitedWithIOException(PARSER, input);
  }

  public static com.google.bigtable.v2.RowSet parseDelimitedFrom(
      java.io.InputStream input, com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3.parseDelimitedWithIOException(
        PARSER, input, extensionRegistry);
  }

  public static com.google.bigtable.v2.RowSet parseFrom(com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3.parseWithIOException(PARSER, input);
  }

  public static com.google.bigtable.v2.RowSet parseFrom(
      com.google.protobuf.CodedInputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3.parseWithIOException(
        PARSER, input, extensionRegistry);
  }

  @java.lang.Override
  public Builder newBuilderForType() {
    return newBuilder();
  }

  public static Builder newBuilder() {
    return DEFAULT_INSTANCE.toBuilder();
  }

  public static Builder newBuilder(com.google.bigtable.v2.RowSet prototype) {
    return DEFAULT_INSTANCE.toBuilder().mergeFrom(prototype);
  }

  @java.lang.Override
  public Builder toBuilder() {
    return this == DEFAULT_INSTANCE ? new Builder() : new Builder().mergeFrom(this);
  }

  @java.lang.Override
  protected Builder newBuilderForType(com.google.protobuf.GeneratedMessageV3.BuilderParent parent) {
    Builder builder = new Builder(parent);
    return builder;
  }
  /**
   *
   *
   * <pre>
   * Specifies a non-contiguous set of rows.
   * </pre>
   *
   * Protobuf type {@code google.bigtable.v2.RowSet}
   */
  public static final class Builder extends com.google.protobuf.GeneratedMessageV3.Builder<Builder>
      implements
      // @@protoc_insertion_point(builder_implements:google.bigtable.v2.RowSet)
      com.google.bigtable.v2.RowSetOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor getDescriptor() {
      return com.google.bigtable.v2.DataProto.internal_static_google_bigtable_v2_RowSet_descriptor;
    }

    @java.lang.Override
    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return com.google.bigtable.v2.DataProto
          .internal_static_google_bigtable_v2_RowSet_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              com.google.bigtable.v2.RowSet.class, com.google.bigtable.v2.RowSet.Builder.class);
    }

    // Construct using com.google.bigtable.v2.RowSet.newBuilder()
    private Builder() {}

    private Builder(com.google.protobuf.GeneratedMessageV3.BuilderParent parent) {
      super(parent);
    }

    @java.lang.Override
    public Builder clear() {
      super.clear();
      bitField0_ = 0;
      rowKeys_ = java.util.Collections.emptyList();
      if (rowRangesBuilder_ == null) {
        rowRanges_ = java.util.Collections.emptyList();
      } else {
        rowRanges_ = null;
        rowRangesBuilder_.clear();
      }
      bitField0_ = (bitField0_ & ~0x00000002);
      return this;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.Descriptor getDescriptorForType() {
      return com.google.bigtable.v2.DataProto.internal_static_google_bigtable_v2_RowSet_descriptor;
    }

    @java.lang.Override
    public com.google.bigtable.v2.RowSet getDefaultInstanceForType() {
      return com.google.bigtable.v2.RowSet.getDefaultInstance();
    }

    @java.lang.Override
    public com.google.bigtable.v2.RowSet build() {
      com.google.bigtable.v2.RowSet result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    @java.lang.Override
    public com.google.bigtable.v2.RowSet buildPartial() {
      com.google.bigtable.v2.RowSet result = new com.google.bigtable.v2.RowSet(this);
      buildPartialRepeatedFields(result);
      if (bitField0_ != 0) {
        buildPartial0(result);
      }
      onBuilt();
      return result;
    }

    private void buildPartialRepeatedFields(com.google.bigtable.v2.RowSet result) {
      if (((bitField0_ & 0x00000001) != 0)) {
        rowKeys_ = java.util.Collections.unmodifiableList(rowKeys_);
        bitField0_ = (bitField0_ & ~0x00000001);
      }
      result.rowKeys_ = rowKeys_;
      if (rowRangesBuilder_ == null) {
        if (((bitField0_ & 0x00000002) != 0)) {
          rowRanges_ = java.util.Collections.unmodifiableList(rowRanges_);
          bitField0_ = (bitField0_ & ~0x00000002);
        }
        result.rowRanges_ = rowRanges_;
      } else {
        result.rowRanges_ = rowRangesBuilder_.build();
      }
    }

    private void buildPartial0(com.google.bigtable.v2.RowSet result) {
      int from_bitField0_ = bitField0_;
    }

    @java.lang.Override
    public Builder clone() {
      return super.clone();
    }

    @java.lang.Override
    public Builder setField(
        com.google.protobuf.Descriptors.FieldDescriptor field, java.lang.Object value) {
      return super.setField(field, value);
    }

    @java.lang.Override
    public Builder clearField(com.google.protobuf.Descriptors.FieldDescriptor field) {
      return super.clearField(field);
    }

    @java.lang.Override
    public Builder clearOneof(com.google.protobuf.Descriptors.OneofDescriptor oneof) {
      return super.clearOneof(oneof);
    }

    @java.lang.Override
    public Builder setRepeatedField(
        com.google.protobuf.Descriptors.FieldDescriptor field, int index, java.lang.Object value) {
      return super.setRepeatedField(field, index, value);
    }

    @java.lang.Override
    public Builder addRepeatedField(
        com.google.protobuf.Descriptors.FieldDescriptor field, java.lang.Object value) {
      return super.addRepeatedField(field, value);
    }

    @java.lang.Override
    public Builder mergeFrom(com.google.protobuf.Message other) {
      if (other instanceof com.google.bigtable.v2.RowSet) {
        return mergeFrom((com.google.bigtable.v2.RowSet) other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(com.google.bigtable.v2.RowSet other) {
      if (other == com.google.bigtable.v2.RowSet.getDefaultInstance()) return this;
      if (!other.rowKeys_.isEmpty()) {
        if (rowKeys_.isEmpty()) {
          rowKeys_ = other.rowKeys_;
          bitField0_ = (bitField0_ & ~0x00000001);
        } else {
          ensureRowKeysIsMutable();
          rowKeys_.addAll(other.rowKeys_);
        }
        onChanged();
      }
      if (rowRangesBuilder_ == null) {
        if (!other.rowRanges_.isEmpty()) {
          if (rowRanges_.isEmpty()) {
            rowRanges_ = other.rowRanges_;
            bitField0_ = (bitField0_ & ~0x00000002);
          } else {
            ensureRowRangesIsMutable();
            rowRanges_.addAll(other.rowRanges_);
          }
          onChanged();
        }
      } else {
        if (!other.rowRanges_.isEmpty()) {
          if (rowRangesBuilder_.isEmpty()) {
            rowRangesBuilder_.dispose();
            rowRangesBuilder_ = null;
            rowRanges_ = other.rowRanges_;
            bitField0_ = (bitField0_ & ~0x00000002);
            rowRangesBuilder_ =
                com.google.protobuf.GeneratedMessageV3.alwaysUseFieldBuilders
                    ? getRowRangesFieldBuilder()
                    : null;
          } else {
            rowRangesBuilder_.addAllMessages(other.rowRanges_);
          }
        }
      }
      this.mergeUnknownFields(other.getUnknownFields());
      onChanged();
      return this;
    }

    @java.lang.Override
    public final boolean isInitialized() {
      return true;
    }

    @java.lang.Override
    public Builder mergeFrom(
        com.google.protobuf.CodedInputStream input,
        com.google.protobuf.ExtensionRegistryLite extensionRegistry)
        throws java.io.IOException {
      if (extensionRegistry == null) {
        throw new java.lang.NullPointerException();
      }
      try {
        boolean done = false;
        while (!done) {
          int tag = input.readTag();
          switch (tag) {
            case 0:
              done = true;
              break;
            case 10:
              {
                com.google.protobuf.ByteString v = input.readBytes();
                ensureRowKeysIsMutable();
                rowKeys_.add(v);
                break;
              } // case 10
            case 18:
              {
                com.google.bigtable.v2.RowRange m =
                    input.readMessage(com.google.bigtable.v2.RowRange.parser(), extensionRegistry);
                if (rowRangesBuilder_ == null) {
                  ensureRowRangesIsMutable();
                  rowRanges_.add(m);
                } else {
                  rowRangesBuilder_.addMessage(m);
                }
                break;
              } // case 18
            default:
              {
                if (!super.parseUnknownField(input, extensionRegistry, tag)) {
                  done = true; // was an endgroup tag
                }
                break;
              } // default:
          } // switch (tag)
        } // while (!done)
      } catch (com.google.protobuf.InvalidProtocolBufferException e) {
        throw e.unwrapIOException();
      } finally {
        onChanged();
      } // finally
      return this;
    }

    private int bitField0_;

    private java.util.List<com.google.protobuf.ByteString> rowKeys_ =
        java.util.Collections.emptyList();

    private void ensureRowKeysIsMutable() {
      if (!((bitField0_ & 0x00000001) != 0)) {
        rowKeys_ = new java.util.ArrayList<com.google.protobuf.ByteString>(rowKeys_);
        bitField0_ |= 0x00000001;
      }
    }
    /**
     *
     *
     * <pre>
     * Single rows included in the set.
     * </pre>
     *
     * <code>repeated bytes row_keys = 1;</code>
     *
     * @return A list containing the rowKeys.
     */
    public java.util.List<com.google.protobuf.ByteString> getRowKeysList() {
      return ((bitField0_ & 0x00000001) != 0)
          ? java.util.Collections.unmodifiableList(rowKeys_)
          : rowKeys_;
    }
    /**
     *
     *
     * <pre>
     * Single rows included in the set.
     * </pre>
     *
     * <code>repeated bytes row_keys = 1;</code>
     *
     * @return The count of rowKeys.
     */
    public int getRowKeysCount() {
      return rowKeys_.size();
    }
    /**
     *
     *
     * <pre>
     * Single rows included in the set.
     * </pre>
     *
     * <code>repeated bytes row_keys = 1;</code>
     *
     * @param index The index of the element to return.
     * @return The rowKeys at the given index.
     */
    public com.google.protobuf.ByteString getRowKeys(int index) {
      return rowKeys_.get(index);
    }
    /**
     *
     *
     * <pre>
     * Single rows included in the set.
     * </pre>
     *
     * <code>repeated bytes row_keys = 1;</code>
     *
     * @param index The index to set the value at.
     * @param value The rowKeys to set.
     * @return This builder for chaining.
     */
    public Builder setRowKeys(int index, com.google.protobuf.ByteString value) {
      if (value == null) {
        throw new NullPointerException();
      }
      ensureRowKeysIsMutable();
      rowKeys_.set(index, value);
      onChanged();
      return this;
    }
    /**
     *
     *
     * <pre>
     * Single rows included in the set.
     * </pre>
     *
     * <code>repeated bytes row_keys = 1;</code>
     *
     * @param value The rowKeys to add.
     * @return This builder for chaining.
     */
    public Builder addRowKeys(com.google.protobuf.ByteString value) {
      if (value == null) {
        throw new NullPointerException();
      }
      ensureRowKeysIsMutable();
      rowKeys_.add(value);
      onChanged();
      return this;
    }
    /**
     *
     *
     * <pre>
     * Single rows included in the set.
     * </pre>
     *
     * <code>repeated bytes row_keys = 1;</code>
     *
     * @param values The rowKeys to add.
     * @return This builder for chaining.
     */
    public Builder addAllRowKeys(
        java.lang.Iterable<? extends com.google.protobuf.ByteString> values) {
      ensureRowKeysIsMutable();
      com.google.protobuf.AbstractMessageLite.Builder.addAll(values, rowKeys_);
      onChanged();
      return this;
    }
    /**
     *
     *
     * <pre>
     * Single rows included in the set.
     * </pre>
     *
     * <code>repeated bytes row_keys = 1;</code>
     *
     * @return This builder for chaining.
     */
    public Builder clearRowKeys() {
      rowKeys_ = java.util.Collections.emptyList();
      bitField0_ = (bitField0_ & ~0x00000001);
      onChanged();
      return this;
    }

    private java.util.List<com.google.bigtable.v2.RowRange> rowRanges_ =
        java.util.Collections.emptyList();

    private void ensureRowRangesIsMutable() {
      if (!((bitField0_ & 0x00000002) != 0)) {
        rowRanges_ = new java.util.ArrayList<com.google.bigtable.v2.RowRange>(rowRanges_);
        bitField0_ |= 0x00000002;
      }
    }

    private com.google.protobuf.RepeatedFieldBuilderV3<
            com.google.bigtable.v2.RowRange,
            com.google.bigtable.v2.RowRange.Builder,
            com.google.bigtable.v2.RowRangeOrBuilder>
        rowRangesBuilder_;

    /**
     *
     *
     * <pre>
     * Contiguous row ranges included in the set.
     * </pre>
     *
     * <code>repeated .google.bigtable.v2.RowRange row_ranges = 2;</code>
     */
    public java.util.List<com.google.bigtable.v2.RowRange> getRowRangesList() {
      if (rowRangesBuilder_ == null) {
        return java.util.Collections.unmodifiableList(rowRanges_);
      } else {
        return rowRangesBuilder_.getMessageList();
      }
    }
    /**
     *
     *
     * <pre>
     * Contiguous row ranges included in the set.
     * </pre>
     *
     * <code>repeated .google.bigtable.v2.RowRange row_ranges = 2;</code>
     */
    public int getRowRangesCount() {
      if (rowRangesBuilder_ == null) {
        return rowRanges_.size();
      } else {
        return rowRangesBuilder_.getCount();
      }
    }
    /**
     *
     *
     * <pre>
     * Contiguous row ranges included in the set.
     * </pre>
     *
     * <code>repeated .google.bigtable.v2.RowRange row_ranges = 2;</code>
     */
    public com.google.bigtable.v2.RowRange getRowRanges(int index) {
      if (rowRangesBuilder_ == null) {
        return rowRanges_.get(index);
      } else {
        return rowRangesBuilder_.getMessage(index);
      }
    }
    /**
     *
     *
     * <pre>
     * Contiguous row ranges included in the set.
     * </pre>
     *
     * <code>repeated .google.bigtable.v2.RowRange row_ranges = 2;</code>
     */
    public Builder setRowRanges(int index, com.google.bigtable.v2.RowRange value) {
      if (rowRangesBuilder_ == null) {
        if (value == null) {
          throw new NullPointerException();
        }
        ensureRowRangesIsMutable();
        rowRanges_.set(index, value);
        onChanged();
      } else {
        rowRangesBuilder_.setMessage(index, value);
      }
      return this;
    }
    /**
     *
     *
     * <pre>
     * Contiguous row ranges included in the set.
     * </pre>
     *
     * <code>repeated .google.bigtable.v2.RowRange row_ranges = 2;</code>
     */
    public Builder setRowRanges(
        int index, com.google.bigtable.v2.RowRange.Builder builderForValue) {
      if (rowRangesBuilder_ == null) {
        ensureRowRangesIsMutable();
        rowRanges_.set(index, builderForValue.build());
        onChanged();
      } else {
        rowRangesBuilder_.setMessage(index, builderForValue.build());
      }
      return this;
    }
    /**
     *
     *
     * <pre>
     * Contiguous row ranges included in the set.
     * </pre>
     *
     * <code>repeated .google.bigtable.v2.RowRange row_ranges = 2;</code>
     */
    public Builder addRowRanges(com.google.bigtable.v2.RowRange value) {
      if (rowRangesBuilder_ == null) {
        if (value == null) {
          throw new NullPointerException();
        }
        ensureRowRangesIsMutable();
        rowRanges_.add(value);
        onChanged();
      } else {
        rowRangesBuilder_.addMessage(value);
      }
      return this;
    }
    /**
     *
     *
     * <pre>
     * Contiguous row ranges included in the set.
     * </pre>
     *
     * <code>repeated .google.bigtable.v2.RowRange row_ranges = 2;</code>
     */
    public Builder addRowRanges(int index, com.google.bigtable.v2.RowRange value) {
      if (rowRangesBuilder_ == null) {
        if (value == null) {
          throw new NullPointerException();
        }
        ensureRowRangesIsMutable();
        rowRanges_.add(index, value);
        onChanged();
      } else {
        rowRangesBuilder_.addMessage(index, value);
      }
      return this;
    }
    /**
     *
     *
     * <pre>
     * Contiguous row ranges included in the set.
     * </pre>
     *
     * <code>repeated .google.bigtable.v2.RowRange row_ranges = 2;</code>
     */
    public Builder addRowRanges(com.google.bigtable.v2.RowRange.Builder builderForValue) {
      if (rowRangesBuilder_ == null) {
        ensureRowRangesIsMutable();
        rowRanges_.add(builderForValue.build());
        onChanged();
      } else {
        rowRangesBuilder_.addMessage(builderForValue.build());
      }
      return this;
    }
    /**
     *
     *
     * <pre>
     * Contiguous row ranges included in the set.
     * </pre>
     *
     * <code>repeated .google.bigtable.v2.RowRange row_ranges = 2;</code>
     */
    public Builder addRowRanges(
        int index, com.google.bigtable.v2.RowRange.Builder builderForValue) {
      if (rowRangesBuilder_ == null) {
        ensureRowRangesIsMutable();
        rowRanges_.add(index, builderForValue.build());
        onChanged();
      } else {
        rowRangesBuilder_.addMessage(index, builderForValue.build());
      }
      return this;
    }
    /**
     *
     *
     * <pre>
     * Contiguous row ranges included in the set.
     * </pre>
     *
     * <code>repeated .google.bigtable.v2.RowRange row_ranges = 2;</code>
     */
    public Builder addAllRowRanges(
        java.lang.Iterable<? extends com.google.bigtable.v2.RowRange> values) {
      if (rowRangesBuilder_ == null) {
        ensureRowRangesIsMutable();
        com.google.protobuf.AbstractMessageLite.Builder.addAll(values, rowRanges_);
        onChanged();
      } else {
        rowRangesBuilder_.addAllMessages(values);
      }
      return this;
    }
    /**
     *
     *
     * <pre>
     * Contiguous row ranges included in the set.
     * </pre>
     *
     * <code>repeated .google.bigtable.v2.RowRange row_ranges = 2;</code>
     */
    public Builder clearRowRanges() {
      if (rowRangesBuilder_ == null) {
        rowRanges_ = java.util.Collections.emptyList();
        bitField0_ = (bitField0_ & ~0x00000002);
        onChanged();
      } else {
        rowRangesBuilder_.clear();
      }
      return this;
    }
    /**
     *
     *
     * <pre>
     * Contiguous row ranges included in the set.
     * </pre>
     *
     * <code>repeated .google.bigtable.v2.RowRange row_ranges = 2;</code>
     */
    public Builder removeRowRanges(int index) {
      if (rowRangesBuilder_ == null) {
        ensureRowRangesIsMutable();
        rowRanges_.remove(index);
        onChanged();
      } else {
        rowRangesBuilder_.remove(index);
      }
      return this;
    }
    /**
     *
     *
     * <pre>
     * Contiguous row ranges included in the set.
     * </pre>
     *
     * <code>repeated .google.bigtable.v2.RowRange row_ranges = 2;</code>
     */
    public com.google.bigtable.v2.RowRange.Builder getRowRangesBuilder(int index) {
      return getRowRangesFieldBuilder().getBuilder(index);
    }
    /**
     *
     *
     * <pre>
     * Contiguous row ranges included in the set.
     * </pre>
     *
     * <code>repeated .google.bigtable.v2.RowRange row_ranges = 2;</code>
     */
    public com.google.bigtable.v2.RowRangeOrBuilder getRowRangesOrBuilder(int index) {
      if (rowRangesBuilder_ == null) {
        return rowRanges_.get(index);
      } else {
        return rowRangesBuilder_.getMessageOrBuilder(index);
      }
    }
    /**
     *
     *
     * <pre>
     * Contiguous row ranges included in the set.
     * </pre>
     *
     * <code>repeated .google.bigtable.v2.RowRange row_ranges = 2;</code>
     */
    public java.util.List<? extends com.google.bigtable.v2.RowRangeOrBuilder>
        getRowRangesOrBuilderList() {
      if (rowRangesBuilder_ != null) {
        return rowRangesBuilder_.getMessageOrBuilderList();
      } else {
        return java.util.Collections.unmodifiableList(rowRanges_);
      }
    }
    /**
     *
     *
     * <pre>
     * Contiguous row ranges included in the set.
     * </pre>
     *
     * <code>repeated .google.bigtable.v2.RowRange row_ranges = 2;</code>
     */
    public com.google.bigtable.v2.RowRange.Builder addRowRangesBuilder() {
      return getRowRangesFieldBuilder()
          .addBuilder(com.google.bigtable.v2.RowRange.getDefaultInstance());
    }
    /**
     *
     *
     * <pre>
     * Contiguous row ranges included in the set.
     * </pre>
     *
     * <code>repeated .google.bigtable.v2.RowRange row_ranges = 2;</code>
     */
    public com.google.bigtable.v2.RowRange.Builder addRowRangesBuilder(int index) {
      return getRowRangesFieldBuilder()
          .addBuilder(index, com.google.bigtable.v2.RowRange.getDefaultInstance());
    }
    /**
     *
     *
     * <pre>
     * Contiguous row ranges included in the set.
     * </pre>
     *
     * <code>repeated .google.bigtable.v2.RowRange row_ranges = 2;</code>
     */
    public java.util.List<com.google.bigtable.v2.RowRange.Builder> getRowRangesBuilderList() {
      return getRowRangesFieldBuilder().getBuilderList();
    }

    private com.google.protobuf.RepeatedFieldBuilderV3<
            com.google.bigtable.v2.RowRange,
            com.google.bigtable.v2.RowRange.Builder,
            com.google.bigtable.v2.RowRangeOrBuilder>
        getRowRangesFieldBuilder() {
      if (rowRangesBuilder_ == null) {
        rowRangesBuilder_ =
            new com.google.protobuf.RepeatedFieldBuilderV3<
                com.google.bigtable.v2.RowRange,
                com.google.bigtable.v2.RowRange.Builder,
                com.google.bigtable.v2.RowRangeOrBuilder>(
                rowRanges_, ((bitField0_ & 0x00000002) != 0), getParentForChildren(), isClean());
        rowRanges_ = null;
      }
      return rowRangesBuilder_;
    }

    @java.lang.Override
    public final Builder setUnknownFields(final com.google.protobuf.UnknownFieldSet unknownFields) {
      return super.setUnknownFields(unknownFields);
    }

    @java.lang.Override
    public final Builder mergeUnknownFields(
        final com.google.protobuf.UnknownFieldSet unknownFields) {
      return super.mergeUnknownFields(unknownFields);
    }

    // @@protoc_insertion_point(builder_scope:google.bigtable.v2.RowSet)
  }

  // @@protoc_insertion_point(class_scope:google.bigtable.v2.RowSet)
  private static final com.google.bigtable.v2.RowSet DEFAULT_INSTANCE;

  static {
    DEFAULT_INSTANCE = new com.google.bigtable.v2.RowSet();
  }

  public static com.google.bigtable.v2.RowSet getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  private static final com.google.protobuf.Parser<RowSet> PARSER =
      new com.google.protobuf.AbstractParser<RowSet>() {
        @java.lang.Override
        public RowSet parsePartialFrom(
            com.google.protobuf.CodedInputStream input,
            com.google.protobuf.ExtensionRegistryLite extensionRegistry)
            throws com.google.protobuf.InvalidProtocolBufferException {
          Builder builder = newBuilder();
          try {
            builder.mergeFrom(input, extensionRegistry);
          } catch (com.google.protobuf.InvalidProtocolBufferException e) {
            throw e.setUnfinishedMessage(builder.buildPartial());
          } catch (com.google.protobuf.UninitializedMessageException e) {
            throw e.asInvalidProtocolBufferException().setUnfinishedMessage(builder.buildPartial());
          } catch (java.io.IOException e) {
            throw new com.google.protobuf.InvalidProtocolBufferException(e)
                .setUnfinishedMessage(builder.buildPartial());
          }
          return builder.buildPartial();
        }
      };

  public static com.google.protobuf.Parser<RowSet> parser() {
    return PARSER;
  }

  @java.lang.Override
  public com.google.protobuf.Parser<RowSet> getParserForType() {
    return PARSER;
  }

  @java.lang.Override
  public com.google.bigtable.v2.RowSet getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }
}
