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
// source: google/bigtable/admin/v2/bigtable_table_admin.proto

// Protobuf Java Version: 3.25.2
package com.google.bigtable.admin.v2;

/**
 *
 *
 * <pre>
 * Response message for
 * [google.bigtable.admin.v2.BigtableTableAdmin.ListAuthorizedViews][google.bigtable.admin.v2.BigtableTableAdmin.ListAuthorizedViews]
 * </pre>
 *
 * Protobuf type {@code google.bigtable.admin.v2.ListAuthorizedViewsResponse}
 */
public final class ListAuthorizedViewsResponse extends com.google.protobuf.GeneratedMessageV3
    implements
    // @@protoc_insertion_point(message_implements:google.bigtable.admin.v2.ListAuthorizedViewsResponse)
    ListAuthorizedViewsResponseOrBuilder {
  private static final long serialVersionUID = 0L;
  // Use ListAuthorizedViewsResponse.newBuilder() to construct.
  private ListAuthorizedViewsResponse(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }

  private ListAuthorizedViewsResponse() {
    authorizedViews_ = java.util.Collections.emptyList();
    nextPageToken_ = "";
  }

  @java.lang.Override
  @SuppressWarnings({"unused"})
  protected java.lang.Object newInstance(UnusedPrivateParameter unused) {
    return new ListAuthorizedViewsResponse();
  }

  public static final com.google.protobuf.Descriptors.Descriptor getDescriptor() {
    return com.google.bigtable.admin.v2.BigtableTableAdminProto
        .internal_static_google_bigtable_admin_v2_ListAuthorizedViewsResponse_descriptor;
  }

  @java.lang.Override
  protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internalGetFieldAccessorTable() {
    return com.google.bigtable.admin.v2.BigtableTableAdminProto
        .internal_static_google_bigtable_admin_v2_ListAuthorizedViewsResponse_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            com.google.bigtable.admin.v2.ListAuthorizedViewsResponse.class,
            com.google.bigtable.admin.v2.ListAuthorizedViewsResponse.Builder.class);
  }

  public static final int AUTHORIZED_VIEWS_FIELD_NUMBER = 1;

  @SuppressWarnings("serial")
  private java.util.List<com.google.bigtable.admin.v2.AuthorizedView> authorizedViews_;
  /**
   *
   *
   * <pre>
   * The AuthorizedViews present in the requested table.
   * </pre>
   *
   * <code>repeated .google.bigtable.admin.v2.AuthorizedView authorized_views = 1;</code>
   */
  @java.lang.Override
  public java.util.List<com.google.bigtable.admin.v2.AuthorizedView> getAuthorizedViewsList() {
    return authorizedViews_;
  }
  /**
   *
   *
   * <pre>
   * The AuthorizedViews present in the requested table.
   * </pre>
   *
   * <code>repeated .google.bigtable.admin.v2.AuthorizedView authorized_views = 1;</code>
   */
  @java.lang.Override
  public java.util.List<? extends com.google.bigtable.admin.v2.AuthorizedViewOrBuilder>
      getAuthorizedViewsOrBuilderList() {
    return authorizedViews_;
  }
  /**
   *
   *
   * <pre>
   * The AuthorizedViews present in the requested table.
   * </pre>
   *
   * <code>repeated .google.bigtable.admin.v2.AuthorizedView authorized_views = 1;</code>
   */
  @java.lang.Override
  public int getAuthorizedViewsCount() {
    return authorizedViews_.size();
  }
  /**
   *
   *
   * <pre>
   * The AuthorizedViews present in the requested table.
   * </pre>
   *
   * <code>repeated .google.bigtable.admin.v2.AuthorizedView authorized_views = 1;</code>
   */
  @java.lang.Override
  public com.google.bigtable.admin.v2.AuthorizedView getAuthorizedViews(int index) {
    return authorizedViews_.get(index);
  }
  /**
   *
   *
   * <pre>
   * The AuthorizedViews present in the requested table.
   * </pre>
   *
   * <code>repeated .google.bigtable.admin.v2.AuthorizedView authorized_views = 1;</code>
   */
  @java.lang.Override
  public com.google.bigtable.admin.v2.AuthorizedViewOrBuilder getAuthorizedViewsOrBuilder(
      int index) {
    return authorizedViews_.get(index);
  }

  public static final int NEXT_PAGE_TOKEN_FIELD_NUMBER = 2;

  @SuppressWarnings("serial")
  private volatile java.lang.Object nextPageToken_ = "";
  /**
   *
   *
   * <pre>
   * Set if not all tables could be returned in a single response.
   * Pass this value to `page_token` in another request to get the next
   * page of results.
   * </pre>
   *
   * <code>string next_page_token = 2;</code>
   *
   * @return The nextPageToken.
   */
  @java.lang.Override
  public java.lang.String getNextPageToken() {
    java.lang.Object ref = nextPageToken_;
    if (ref instanceof java.lang.String) {
      return (java.lang.String) ref;
    } else {
      com.google.protobuf.ByteString bs = (com.google.protobuf.ByteString) ref;
      java.lang.String s = bs.toStringUtf8();
      nextPageToken_ = s;
      return s;
    }
  }
  /**
   *
   *
   * <pre>
   * Set if not all tables could be returned in a single response.
   * Pass this value to `page_token` in another request to get the next
   * page of results.
   * </pre>
   *
   * <code>string next_page_token = 2;</code>
   *
   * @return The bytes for nextPageToken.
   */
  @java.lang.Override
  public com.google.protobuf.ByteString getNextPageTokenBytes() {
    java.lang.Object ref = nextPageToken_;
    if (ref instanceof java.lang.String) {
      com.google.protobuf.ByteString b =
          com.google.protobuf.ByteString.copyFromUtf8((java.lang.String) ref);
      nextPageToken_ = b;
      return b;
    } else {
      return (com.google.protobuf.ByteString) ref;
    }
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
    for (int i = 0; i < authorizedViews_.size(); i++) {
      output.writeMessage(1, authorizedViews_.get(i));
    }
    if (!com.google.protobuf.GeneratedMessageV3.isStringEmpty(nextPageToken_)) {
      com.google.protobuf.GeneratedMessageV3.writeString(output, 2, nextPageToken_);
    }
    getUnknownFields().writeTo(output);
  }

  @java.lang.Override
  public int getSerializedSize() {
    int size = memoizedSize;
    if (size != -1) return size;

    size = 0;
    for (int i = 0; i < authorizedViews_.size(); i++) {
      size += com.google.protobuf.CodedOutputStream.computeMessageSize(1, authorizedViews_.get(i));
    }
    if (!com.google.protobuf.GeneratedMessageV3.isStringEmpty(nextPageToken_)) {
      size += com.google.protobuf.GeneratedMessageV3.computeStringSize(2, nextPageToken_);
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
    if (!(obj instanceof com.google.bigtable.admin.v2.ListAuthorizedViewsResponse)) {
      return super.equals(obj);
    }
    com.google.bigtable.admin.v2.ListAuthorizedViewsResponse other =
        (com.google.bigtable.admin.v2.ListAuthorizedViewsResponse) obj;

    if (!getAuthorizedViewsList().equals(other.getAuthorizedViewsList())) return false;
    if (!getNextPageToken().equals(other.getNextPageToken())) return false;
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
    if (getAuthorizedViewsCount() > 0) {
      hash = (37 * hash) + AUTHORIZED_VIEWS_FIELD_NUMBER;
      hash = (53 * hash) + getAuthorizedViewsList().hashCode();
    }
    hash = (37 * hash) + NEXT_PAGE_TOKEN_FIELD_NUMBER;
    hash = (53 * hash) + getNextPageToken().hashCode();
    hash = (29 * hash) + getUnknownFields().hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static com.google.bigtable.admin.v2.ListAuthorizedViewsResponse parseFrom(
      java.nio.ByteBuffer data) throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }

  public static com.google.bigtable.admin.v2.ListAuthorizedViewsResponse parseFrom(
      java.nio.ByteBuffer data, com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }

  public static com.google.bigtable.admin.v2.ListAuthorizedViewsResponse parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }

  public static com.google.bigtable.admin.v2.ListAuthorizedViewsResponse parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }

  public static com.google.bigtable.admin.v2.ListAuthorizedViewsResponse parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }

  public static com.google.bigtable.admin.v2.ListAuthorizedViewsResponse parseFrom(
      byte[] data, com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }

  public static com.google.bigtable.admin.v2.ListAuthorizedViewsResponse parseFrom(
      java.io.InputStream input) throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3.parseWithIOException(PARSER, input);
  }

  public static com.google.bigtable.admin.v2.ListAuthorizedViewsResponse parseFrom(
      java.io.InputStream input, com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3.parseWithIOException(
        PARSER, input, extensionRegistry);
  }

  public static com.google.bigtable.admin.v2.ListAuthorizedViewsResponse parseDelimitedFrom(
      java.io.InputStream input) throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3.parseDelimitedWithIOException(PARSER, input);
  }

  public static com.google.bigtable.admin.v2.ListAuthorizedViewsResponse parseDelimitedFrom(
      java.io.InputStream input, com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3.parseDelimitedWithIOException(
        PARSER, input, extensionRegistry);
  }

  public static com.google.bigtable.admin.v2.ListAuthorizedViewsResponse parseFrom(
      com.google.protobuf.CodedInputStream input) throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3.parseWithIOException(PARSER, input);
  }

  public static com.google.bigtable.admin.v2.ListAuthorizedViewsResponse parseFrom(
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

  public static Builder newBuilder(
      com.google.bigtable.admin.v2.ListAuthorizedViewsResponse prototype) {
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
   * Response message for
   * [google.bigtable.admin.v2.BigtableTableAdmin.ListAuthorizedViews][google.bigtable.admin.v2.BigtableTableAdmin.ListAuthorizedViews]
   * </pre>
   *
   * Protobuf type {@code google.bigtable.admin.v2.ListAuthorizedViewsResponse}
   */
  public static final class Builder extends com.google.protobuf.GeneratedMessageV3.Builder<Builder>
      implements
      // @@protoc_insertion_point(builder_implements:google.bigtable.admin.v2.ListAuthorizedViewsResponse)
      com.google.bigtable.admin.v2.ListAuthorizedViewsResponseOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor getDescriptor() {
      return com.google.bigtable.admin.v2.BigtableTableAdminProto
          .internal_static_google_bigtable_admin_v2_ListAuthorizedViewsResponse_descriptor;
    }

    @java.lang.Override
    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return com.google.bigtable.admin.v2.BigtableTableAdminProto
          .internal_static_google_bigtable_admin_v2_ListAuthorizedViewsResponse_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              com.google.bigtable.admin.v2.ListAuthorizedViewsResponse.class,
              com.google.bigtable.admin.v2.ListAuthorizedViewsResponse.Builder.class);
    }

    // Construct using com.google.bigtable.admin.v2.ListAuthorizedViewsResponse.newBuilder()
    private Builder() {}

    private Builder(com.google.protobuf.GeneratedMessageV3.BuilderParent parent) {
      super(parent);
    }

    @java.lang.Override
    public Builder clear() {
      super.clear();
      bitField0_ = 0;
      if (authorizedViewsBuilder_ == null) {
        authorizedViews_ = java.util.Collections.emptyList();
      } else {
        authorizedViews_ = null;
        authorizedViewsBuilder_.clear();
      }
      bitField0_ = (bitField0_ & ~0x00000001);
      nextPageToken_ = "";
      return this;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.Descriptor getDescriptorForType() {
      return com.google.bigtable.admin.v2.BigtableTableAdminProto
          .internal_static_google_bigtable_admin_v2_ListAuthorizedViewsResponse_descriptor;
    }

    @java.lang.Override
    public com.google.bigtable.admin.v2.ListAuthorizedViewsResponse getDefaultInstanceForType() {
      return com.google.bigtable.admin.v2.ListAuthorizedViewsResponse.getDefaultInstance();
    }

    @java.lang.Override
    public com.google.bigtable.admin.v2.ListAuthorizedViewsResponse build() {
      com.google.bigtable.admin.v2.ListAuthorizedViewsResponse result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    @java.lang.Override
    public com.google.bigtable.admin.v2.ListAuthorizedViewsResponse buildPartial() {
      com.google.bigtable.admin.v2.ListAuthorizedViewsResponse result =
          new com.google.bigtable.admin.v2.ListAuthorizedViewsResponse(this);
      buildPartialRepeatedFields(result);
      if (bitField0_ != 0) {
        buildPartial0(result);
      }
      onBuilt();
      return result;
    }

    private void buildPartialRepeatedFields(
        com.google.bigtable.admin.v2.ListAuthorizedViewsResponse result) {
      if (authorizedViewsBuilder_ == null) {
        if (((bitField0_ & 0x00000001) != 0)) {
          authorizedViews_ = java.util.Collections.unmodifiableList(authorizedViews_);
          bitField0_ = (bitField0_ & ~0x00000001);
        }
        result.authorizedViews_ = authorizedViews_;
      } else {
        result.authorizedViews_ = authorizedViewsBuilder_.build();
      }
    }

    private void buildPartial0(com.google.bigtable.admin.v2.ListAuthorizedViewsResponse result) {
      int from_bitField0_ = bitField0_;
      if (((from_bitField0_ & 0x00000002) != 0)) {
        result.nextPageToken_ = nextPageToken_;
      }
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
      if (other instanceof com.google.bigtable.admin.v2.ListAuthorizedViewsResponse) {
        return mergeFrom((com.google.bigtable.admin.v2.ListAuthorizedViewsResponse) other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(com.google.bigtable.admin.v2.ListAuthorizedViewsResponse other) {
      if (other == com.google.bigtable.admin.v2.ListAuthorizedViewsResponse.getDefaultInstance())
        return this;
      if (authorizedViewsBuilder_ == null) {
        if (!other.authorizedViews_.isEmpty()) {
          if (authorizedViews_.isEmpty()) {
            authorizedViews_ = other.authorizedViews_;
            bitField0_ = (bitField0_ & ~0x00000001);
          } else {
            ensureAuthorizedViewsIsMutable();
            authorizedViews_.addAll(other.authorizedViews_);
          }
          onChanged();
        }
      } else {
        if (!other.authorizedViews_.isEmpty()) {
          if (authorizedViewsBuilder_.isEmpty()) {
            authorizedViewsBuilder_.dispose();
            authorizedViewsBuilder_ = null;
            authorizedViews_ = other.authorizedViews_;
            bitField0_ = (bitField0_ & ~0x00000001);
            authorizedViewsBuilder_ =
                com.google.protobuf.GeneratedMessageV3.alwaysUseFieldBuilders
                    ? getAuthorizedViewsFieldBuilder()
                    : null;
          } else {
            authorizedViewsBuilder_.addAllMessages(other.authorizedViews_);
          }
        }
      }
      if (!other.getNextPageToken().isEmpty()) {
        nextPageToken_ = other.nextPageToken_;
        bitField0_ |= 0x00000002;
        onChanged();
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
                com.google.bigtable.admin.v2.AuthorizedView m =
                    input.readMessage(
                        com.google.bigtable.admin.v2.AuthorizedView.parser(), extensionRegistry);
                if (authorizedViewsBuilder_ == null) {
                  ensureAuthorizedViewsIsMutable();
                  authorizedViews_.add(m);
                } else {
                  authorizedViewsBuilder_.addMessage(m);
                }
                break;
              } // case 10
            case 18:
              {
                nextPageToken_ = input.readStringRequireUtf8();
                bitField0_ |= 0x00000002;
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

    private java.util.List<com.google.bigtable.admin.v2.AuthorizedView> authorizedViews_ =
        java.util.Collections.emptyList();

    private void ensureAuthorizedViewsIsMutable() {
      if (!((bitField0_ & 0x00000001) != 0)) {
        authorizedViews_ =
            new java.util.ArrayList<com.google.bigtable.admin.v2.AuthorizedView>(authorizedViews_);
        bitField0_ |= 0x00000001;
      }
    }

    private com.google.protobuf.RepeatedFieldBuilderV3<
            com.google.bigtable.admin.v2.AuthorizedView,
            com.google.bigtable.admin.v2.AuthorizedView.Builder,
            com.google.bigtable.admin.v2.AuthorizedViewOrBuilder>
        authorizedViewsBuilder_;

    /**
     *
     *
     * <pre>
     * The AuthorizedViews present in the requested table.
     * </pre>
     *
     * <code>repeated .google.bigtable.admin.v2.AuthorizedView authorized_views = 1;</code>
     */
    public java.util.List<com.google.bigtable.admin.v2.AuthorizedView> getAuthorizedViewsList() {
      if (authorizedViewsBuilder_ == null) {
        return java.util.Collections.unmodifiableList(authorizedViews_);
      } else {
        return authorizedViewsBuilder_.getMessageList();
      }
    }
    /**
     *
     *
     * <pre>
     * The AuthorizedViews present in the requested table.
     * </pre>
     *
     * <code>repeated .google.bigtable.admin.v2.AuthorizedView authorized_views = 1;</code>
     */
    public int getAuthorizedViewsCount() {
      if (authorizedViewsBuilder_ == null) {
        return authorizedViews_.size();
      } else {
        return authorizedViewsBuilder_.getCount();
      }
    }
    /**
     *
     *
     * <pre>
     * The AuthorizedViews present in the requested table.
     * </pre>
     *
     * <code>repeated .google.bigtable.admin.v2.AuthorizedView authorized_views = 1;</code>
     */
    public com.google.bigtable.admin.v2.AuthorizedView getAuthorizedViews(int index) {
      if (authorizedViewsBuilder_ == null) {
        return authorizedViews_.get(index);
      } else {
        return authorizedViewsBuilder_.getMessage(index);
      }
    }
    /**
     *
     *
     * <pre>
     * The AuthorizedViews present in the requested table.
     * </pre>
     *
     * <code>repeated .google.bigtable.admin.v2.AuthorizedView authorized_views = 1;</code>
     */
    public Builder setAuthorizedViews(
        int index, com.google.bigtable.admin.v2.AuthorizedView value) {
      if (authorizedViewsBuilder_ == null) {
        if (value == null) {
          throw new NullPointerException();
        }
        ensureAuthorizedViewsIsMutable();
        authorizedViews_.set(index, value);
        onChanged();
      } else {
        authorizedViewsBuilder_.setMessage(index, value);
      }
      return this;
    }
    /**
     *
     *
     * <pre>
     * The AuthorizedViews present in the requested table.
     * </pre>
     *
     * <code>repeated .google.bigtable.admin.v2.AuthorizedView authorized_views = 1;</code>
     */
    public Builder setAuthorizedViews(
        int index, com.google.bigtable.admin.v2.AuthorizedView.Builder builderForValue) {
      if (authorizedViewsBuilder_ == null) {
        ensureAuthorizedViewsIsMutable();
        authorizedViews_.set(index, builderForValue.build());
        onChanged();
      } else {
        authorizedViewsBuilder_.setMessage(index, builderForValue.build());
      }
      return this;
    }
    /**
     *
     *
     * <pre>
     * The AuthorizedViews present in the requested table.
     * </pre>
     *
     * <code>repeated .google.bigtable.admin.v2.AuthorizedView authorized_views = 1;</code>
     */
    public Builder addAuthorizedViews(com.google.bigtable.admin.v2.AuthorizedView value) {
      if (authorizedViewsBuilder_ == null) {
        if (value == null) {
          throw new NullPointerException();
        }
        ensureAuthorizedViewsIsMutable();
        authorizedViews_.add(value);
        onChanged();
      } else {
        authorizedViewsBuilder_.addMessage(value);
      }
      return this;
    }
    /**
     *
     *
     * <pre>
     * The AuthorizedViews present in the requested table.
     * </pre>
     *
     * <code>repeated .google.bigtable.admin.v2.AuthorizedView authorized_views = 1;</code>
     */
    public Builder addAuthorizedViews(
        int index, com.google.bigtable.admin.v2.AuthorizedView value) {
      if (authorizedViewsBuilder_ == null) {
        if (value == null) {
          throw new NullPointerException();
        }
        ensureAuthorizedViewsIsMutable();
        authorizedViews_.add(index, value);
        onChanged();
      } else {
        authorizedViewsBuilder_.addMessage(index, value);
      }
      return this;
    }
    /**
     *
     *
     * <pre>
     * The AuthorizedViews present in the requested table.
     * </pre>
     *
     * <code>repeated .google.bigtable.admin.v2.AuthorizedView authorized_views = 1;</code>
     */
    public Builder addAuthorizedViews(
        com.google.bigtable.admin.v2.AuthorizedView.Builder builderForValue) {
      if (authorizedViewsBuilder_ == null) {
        ensureAuthorizedViewsIsMutable();
        authorizedViews_.add(builderForValue.build());
        onChanged();
      } else {
        authorizedViewsBuilder_.addMessage(builderForValue.build());
      }
      return this;
    }
    /**
     *
     *
     * <pre>
     * The AuthorizedViews present in the requested table.
     * </pre>
     *
     * <code>repeated .google.bigtable.admin.v2.AuthorizedView authorized_views = 1;</code>
     */
    public Builder addAuthorizedViews(
        int index, com.google.bigtable.admin.v2.AuthorizedView.Builder builderForValue) {
      if (authorizedViewsBuilder_ == null) {
        ensureAuthorizedViewsIsMutable();
        authorizedViews_.add(index, builderForValue.build());
        onChanged();
      } else {
        authorizedViewsBuilder_.addMessage(index, builderForValue.build());
      }
      return this;
    }
    /**
     *
     *
     * <pre>
     * The AuthorizedViews present in the requested table.
     * </pre>
     *
     * <code>repeated .google.bigtable.admin.v2.AuthorizedView authorized_views = 1;</code>
     */
    public Builder addAllAuthorizedViews(
        java.lang.Iterable<? extends com.google.bigtable.admin.v2.AuthorizedView> values) {
      if (authorizedViewsBuilder_ == null) {
        ensureAuthorizedViewsIsMutable();
        com.google.protobuf.AbstractMessageLite.Builder.addAll(values, authorizedViews_);
        onChanged();
      } else {
        authorizedViewsBuilder_.addAllMessages(values);
      }
      return this;
    }
    /**
     *
     *
     * <pre>
     * The AuthorizedViews present in the requested table.
     * </pre>
     *
     * <code>repeated .google.bigtable.admin.v2.AuthorizedView authorized_views = 1;</code>
     */
    public Builder clearAuthorizedViews() {
      if (authorizedViewsBuilder_ == null) {
        authorizedViews_ = java.util.Collections.emptyList();
        bitField0_ = (bitField0_ & ~0x00000001);
        onChanged();
      } else {
        authorizedViewsBuilder_.clear();
      }
      return this;
    }
    /**
     *
     *
     * <pre>
     * The AuthorizedViews present in the requested table.
     * </pre>
     *
     * <code>repeated .google.bigtable.admin.v2.AuthorizedView authorized_views = 1;</code>
     */
    public Builder removeAuthorizedViews(int index) {
      if (authorizedViewsBuilder_ == null) {
        ensureAuthorizedViewsIsMutable();
        authorizedViews_.remove(index);
        onChanged();
      } else {
        authorizedViewsBuilder_.remove(index);
      }
      return this;
    }
    /**
     *
     *
     * <pre>
     * The AuthorizedViews present in the requested table.
     * </pre>
     *
     * <code>repeated .google.bigtable.admin.v2.AuthorizedView authorized_views = 1;</code>
     */
    public com.google.bigtable.admin.v2.AuthorizedView.Builder getAuthorizedViewsBuilder(
        int index) {
      return getAuthorizedViewsFieldBuilder().getBuilder(index);
    }
    /**
     *
     *
     * <pre>
     * The AuthorizedViews present in the requested table.
     * </pre>
     *
     * <code>repeated .google.bigtable.admin.v2.AuthorizedView authorized_views = 1;</code>
     */
    public com.google.bigtable.admin.v2.AuthorizedViewOrBuilder getAuthorizedViewsOrBuilder(
        int index) {
      if (authorizedViewsBuilder_ == null) {
        return authorizedViews_.get(index);
      } else {
        return authorizedViewsBuilder_.getMessageOrBuilder(index);
      }
    }
    /**
     *
     *
     * <pre>
     * The AuthorizedViews present in the requested table.
     * </pre>
     *
     * <code>repeated .google.bigtable.admin.v2.AuthorizedView authorized_views = 1;</code>
     */
    public java.util.List<? extends com.google.bigtable.admin.v2.AuthorizedViewOrBuilder>
        getAuthorizedViewsOrBuilderList() {
      if (authorizedViewsBuilder_ != null) {
        return authorizedViewsBuilder_.getMessageOrBuilderList();
      } else {
        return java.util.Collections.unmodifiableList(authorizedViews_);
      }
    }
    /**
     *
     *
     * <pre>
     * The AuthorizedViews present in the requested table.
     * </pre>
     *
     * <code>repeated .google.bigtable.admin.v2.AuthorizedView authorized_views = 1;</code>
     */
    public com.google.bigtable.admin.v2.AuthorizedView.Builder addAuthorizedViewsBuilder() {
      return getAuthorizedViewsFieldBuilder()
          .addBuilder(com.google.bigtable.admin.v2.AuthorizedView.getDefaultInstance());
    }
    /**
     *
     *
     * <pre>
     * The AuthorizedViews present in the requested table.
     * </pre>
     *
     * <code>repeated .google.bigtable.admin.v2.AuthorizedView authorized_views = 1;</code>
     */
    public com.google.bigtable.admin.v2.AuthorizedView.Builder addAuthorizedViewsBuilder(
        int index) {
      return getAuthorizedViewsFieldBuilder()
          .addBuilder(index, com.google.bigtable.admin.v2.AuthorizedView.getDefaultInstance());
    }
    /**
     *
     *
     * <pre>
     * The AuthorizedViews present in the requested table.
     * </pre>
     *
     * <code>repeated .google.bigtable.admin.v2.AuthorizedView authorized_views = 1;</code>
     */
    public java.util.List<com.google.bigtable.admin.v2.AuthorizedView.Builder>
        getAuthorizedViewsBuilderList() {
      return getAuthorizedViewsFieldBuilder().getBuilderList();
    }

    private com.google.protobuf.RepeatedFieldBuilderV3<
            com.google.bigtable.admin.v2.AuthorizedView,
            com.google.bigtable.admin.v2.AuthorizedView.Builder,
            com.google.bigtable.admin.v2.AuthorizedViewOrBuilder>
        getAuthorizedViewsFieldBuilder() {
      if (authorizedViewsBuilder_ == null) {
        authorizedViewsBuilder_ =
            new com.google.protobuf.RepeatedFieldBuilderV3<
                com.google.bigtable.admin.v2.AuthorizedView,
                com.google.bigtable.admin.v2.AuthorizedView.Builder,
                com.google.bigtable.admin.v2.AuthorizedViewOrBuilder>(
                authorizedViews_,
                ((bitField0_ & 0x00000001) != 0),
                getParentForChildren(),
                isClean());
        authorizedViews_ = null;
      }
      return authorizedViewsBuilder_;
    }

    private java.lang.Object nextPageToken_ = "";
    /**
     *
     *
     * <pre>
     * Set if not all tables could be returned in a single response.
     * Pass this value to `page_token` in another request to get the next
     * page of results.
     * </pre>
     *
     * <code>string next_page_token = 2;</code>
     *
     * @return The nextPageToken.
     */
    public java.lang.String getNextPageToken() {
      java.lang.Object ref = nextPageToken_;
      if (!(ref instanceof java.lang.String)) {
        com.google.protobuf.ByteString bs = (com.google.protobuf.ByteString) ref;
        java.lang.String s = bs.toStringUtf8();
        nextPageToken_ = s;
        return s;
      } else {
        return (java.lang.String) ref;
      }
    }
    /**
     *
     *
     * <pre>
     * Set if not all tables could be returned in a single response.
     * Pass this value to `page_token` in another request to get the next
     * page of results.
     * </pre>
     *
     * <code>string next_page_token = 2;</code>
     *
     * @return The bytes for nextPageToken.
     */
    public com.google.protobuf.ByteString getNextPageTokenBytes() {
      java.lang.Object ref = nextPageToken_;
      if (ref instanceof String) {
        com.google.protobuf.ByteString b =
            com.google.protobuf.ByteString.copyFromUtf8((java.lang.String) ref);
        nextPageToken_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }
    /**
     *
     *
     * <pre>
     * Set if not all tables could be returned in a single response.
     * Pass this value to `page_token` in another request to get the next
     * page of results.
     * </pre>
     *
     * <code>string next_page_token = 2;</code>
     *
     * @param value The nextPageToken to set.
     * @return This builder for chaining.
     */
    public Builder setNextPageToken(java.lang.String value) {
      if (value == null) {
        throw new NullPointerException();
      }
      nextPageToken_ = value;
      bitField0_ |= 0x00000002;
      onChanged();
      return this;
    }
    /**
     *
     *
     * <pre>
     * Set if not all tables could be returned in a single response.
     * Pass this value to `page_token` in another request to get the next
     * page of results.
     * </pre>
     *
     * <code>string next_page_token = 2;</code>
     *
     * @return This builder for chaining.
     */
    public Builder clearNextPageToken() {
      nextPageToken_ = getDefaultInstance().getNextPageToken();
      bitField0_ = (bitField0_ & ~0x00000002);
      onChanged();
      return this;
    }
    /**
     *
     *
     * <pre>
     * Set if not all tables could be returned in a single response.
     * Pass this value to `page_token` in another request to get the next
     * page of results.
     * </pre>
     *
     * <code>string next_page_token = 2;</code>
     *
     * @param value The bytes for nextPageToken to set.
     * @return This builder for chaining.
     */
    public Builder setNextPageTokenBytes(com.google.protobuf.ByteString value) {
      if (value == null) {
        throw new NullPointerException();
      }
      checkByteStringIsUtf8(value);
      nextPageToken_ = value;
      bitField0_ |= 0x00000002;
      onChanged();
      return this;
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

    // @@protoc_insertion_point(builder_scope:google.bigtable.admin.v2.ListAuthorizedViewsResponse)
  }

  // @@protoc_insertion_point(class_scope:google.bigtable.admin.v2.ListAuthorizedViewsResponse)
  private static final com.google.bigtable.admin.v2.ListAuthorizedViewsResponse DEFAULT_INSTANCE;

  static {
    DEFAULT_INSTANCE = new com.google.bigtable.admin.v2.ListAuthorizedViewsResponse();
  }

  public static com.google.bigtable.admin.v2.ListAuthorizedViewsResponse getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  private static final com.google.protobuf.Parser<ListAuthorizedViewsResponse> PARSER =
      new com.google.protobuf.AbstractParser<ListAuthorizedViewsResponse>() {
        @java.lang.Override
        public ListAuthorizedViewsResponse parsePartialFrom(
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

  public static com.google.protobuf.Parser<ListAuthorizedViewsResponse> parser() {
    return PARSER;
  }

  @java.lang.Override
  public com.google.protobuf.Parser<ListAuthorizedViewsResponse> getParserForType() {
    return PARSER;
  }

  @java.lang.Override
  public com.google.bigtable.admin.v2.ListAuthorizedViewsResponse getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }
}
