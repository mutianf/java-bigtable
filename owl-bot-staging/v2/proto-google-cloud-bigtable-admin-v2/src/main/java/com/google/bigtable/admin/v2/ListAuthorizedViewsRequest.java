// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: google/bigtable/admin/v2/bigtable_table_admin.proto

// Protobuf Java Version: 3.25.2
package com.google.bigtable.admin.v2;

/**
 * <pre>
 * Request message for
 * [google.bigtable.admin.v2.BigtableTableAdmin.ListAuthorizedViews][google.bigtable.admin.v2.BigtableTableAdmin.ListAuthorizedViews]
 * </pre>
 *
 * Protobuf type {@code google.bigtable.admin.v2.ListAuthorizedViewsRequest}
 */
public final class ListAuthorizedViewsRequest extends
    com.google.protobuf.GeneratedMessageV3 implements
    // @@protoc_insertion_point(message_implements:google.bigtable.admin.v2.ListAuthorizedViewsRequest)
    ListAuthorizedViewsRequestOrBuilder {
private static final long serialVersionUID = 0L;
  // Use ListAuthorizedViewsRequest.newBuilder() to construct.
  private ListAuthorizedViewsRequest(com.google.protobuf.GeneratedMessageV3.Builder<?> builder) {
    super(builder);
  }
  private ListAuthorizedViewsRequest() {
    parent_ = "";
    pageToken_ = "";
    view_ = 0;
  }

  @java.lang.Override
  @SuppressWarnings({"unused"})
  protected java.lang.Object newInstance(
      UnusedPrivateParameter unused) {
    return new ListAuthorizedViewsRequest();
  }

  public static final com.google.protobuf.Descriptors.Descriptor
      getDescriptor() {
    return com.google.bigtable.admin.v2.BigtableTableAdminProto.internal_static_google_bigtable_admin_v2_ListAuthorizedViewsRequest_descriptor;
  }

  @java.lang.Override
  protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internalGetFieldAccessorTable() {
    return com.google.bigtable.admin.v2.BigtableTableAdminProto.internal_static_google_bigtable_admin_v2_ListAuthorizedViewsRequest_fieldAccessorTable
        .ensureFieldAccessorsInitialized(
            com.google.bigtable.admin.v2.ListAuthorizedViewsRequest.class, com.google.bigtable.admin.v2.ListAuthorizedViewsRequest.Builder.class);
  }

  public static final int PARENT_FIELD_NUMBER = 1;
  @SuppressWarnings("serial")
  private volatile java.lang.Object parent_ = "";
  /**
   * <pre>
   * Required. The unique name of the table for which AuthorizedViews should be
   * listed. Values are of the form
   * `projects/{project}/instances/{instance}/tables/{table}`.
   * </pre>
   *
   * <code>string parent = 1 [(.google.api.field_behavior) = REQUIRED, (.google.api.resource_reference) = { ... }</code>
   * @return The parent.
   */
  @java.lang.Override
  public java.lang.String getParent() {
    java.lang.Object ref = parent_;
    if (ref instanceof java.lang.String) {
      return (java.lang.String) ref;
    } else {
      com.google.protobuf.ByteString bs = 
          (com.google.protobuf.ByteString) ref;
      java.lang.String s = bs.toStringUtf8();
      parent_ = s;
      return s;
    }
  }
  /**
   * <pre>
   * Required. The unique name of the table for which AuthorizedViews should be
   * listed. Values are of the form
   * `projects/{project}/instances/{instance}/tables/{table}`.
   * </pre>
   *
   * <code>string parent = 1 [(.google.api.field_behavior) = REQUIRED, (.google.api.resource_reference) = { ... }</code>
   * @return The bytes for parent.
   */
  @java.lang.Override
  public com.google.protobuf.ByteString
      getParentBytes() {
    java.lang.Object ref = parent_;
    if (ref instanceof java.lang.String) {
      com.google.protobuf.ByteString b = 
          com.google.protobuf.ByteString.copyFromUtf8(
              (java.lang.String) ref);
      parent_ = b;
      return b;
    } else {
      return (com.google.protobuf.ByteString) ref;
    }
  }

  public static final int PAGE_SIZE_FIELD_NUMBER = 2;
  private int pageSize_ = 0;
  /**
   * <pre>
   * Optional. Maximum number of results per page.
   *
   * A page_size of zero lets the server choose the number of items to return.
   * A page_size which is strictly positive will return at most that many items.
   * A negative page_size will cause an error.
   *
   * Following the first request, subsequent paginated calls are not required
   * to pass a page_size. If a page_size is set in subsequent calls, it must
   * match the page_size given in the first request.
   * </pre>
   *
   * <code>int32 page_size = 2 [(.google.api.field_behavior) = OPTIONAL];</code>
   * @return The pageSize.
   */
  @java.lang.Override
  public int getPageSize() {
    return pageSize_;
  }

  public static final int PAGE_TOKEN_FIELD_NUMBER = 3;
  @SuppressWarnings("serial")
  private volatile java.lang.Object pageToken_ = "";
  /**
   * <pre>
   * Optional. The value of `next_page_token` returned by a previous call.
   * </pre>
   *
   * <code>string page_token = 3 [(.google.api.field_behavior) = OPTIONAL];</code>
   * @return The pageToken.
   */
  @java.lang.Override
  public java.lang.String getPageToken() {
    java.lang.Object ref = pageToken_;
    if (ref instanceof java.lang.String) {
      return (java.lang.String) ref;
    } else {
      com.google.protobuf.ByteString bs = 
          (com.google.protobuf.ByteString) ref;
      java.lang.String s = bs.toStringUtf8();
      pageToken_ = s;
      return s;
    }
  }
  /**
   * <pre>
   * Optional. The value of `next_page_token` returned by a previous call.
   * </pre>
   *
   * <code>string page_token = 3 [(.google.api.field_behavior) = OPTIONAL];</code>
   * @return The bytes for pageToken.
   */
  @java.lang.Override
  public com.google.protobuf.ByteString
      getPageTokenBytes() {
    java.lang.Object ref = pageToken_;
    if (ref instanceof java.lang.String) {
      com.google.protobuf.ByteString b = 
          com.google.protobuf.ByteString.copyFromUtf8(
              (java.lang.String) ref);
      pageToken_ = b;
      return b;
    } else {
      return (com.google.protobuf.ByteString) ref;
    }
  }

  public static final int VIEW_FIELD_NUMBER = 4;
  private int view_ = 0;
  /**
   * <pre>
   * Optional. The resource_view to be applied to the returned views' fields.
   * Default to NAME_ONLY.
   * </pre>
   *
   * <code>.google.bigtable.admin.v2.AuthorizedView.ResponseView view = 4 [(.google.api.field_behavior) = OPTIONAL];</code>
   * @return The enum numeric value on the wire for view.
   */
  @java.lang.Override public int getViewValue() {
    return view_;
  }
  /**
   * <pre>
   * Optional. The resource_view to be applied to the returned views' fields.
   * Default to NAME_ONLY.
   * </pre>
   *
   * <code>.google.bigtable.admin.v2.AuthorizedView.ResponseView view = 4 [(.google.api.field_behavior) = OPTIONAL];</code>
   * @return The view.
   */
  @java.lang.Override public com.google.bigtable.admin.v2.AuthorizedView.ResponseView getView() {
    com.google.bigtable.admin.v2.AuthorizedView.ResponseView result = com.google.bigtable.admin.v2.AuthorizedView.ResponseView.forNumber(view_);
    return result == null ? com.google.bigtable.admin.v2.AuthorizedView.ResponseView.UNRECOGNIZED : result;
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
  public void writeTo(com.google.protobuf.CodedOutputStream output)
                      throws java.io.IOException {
    if (!com.google.protobuf.GeneratedMessageV3.isStringEmpty(parent_)) {
      com.google.protobuf.GeneratedMessageV3.writeString(output, 1, parent_);
    }
    if (pageSize_ != 0) {
      output.writeInt32(2, pageSize_);
    }
    if (!com.google.protobuf.GeneratedMessageV3.isStringEmpty(pageToken_)) {
      com.google.protobuf.GeneratedMessageV3.writeString(output, 3, pageToken_);
    }
    if (view_ != com.google.bigtable.admin.v2.AuthorizedView.ResponseView.RESPONSE_VIEW_UNSPECIFIED.getNumber()) {
      output.writeEnum(4, view_);
    }
    getUnknownFields().writeTo(output);
  }

  @java.lang.Override
  public int getSerializedSize() {
    int size = memoizedSize;
    if (size != -1) return size;

    size = 0;
    if (!com.google.protobuf.GeneratedMessageV3.isStringEmpty(parent_)) {
      size += com.google.protobuf.GeneratedMessageV3.computeStringSize(1, parent_);
    }
    if (pageSize_ != 0) {
      size += com.google.protobuf.CodedOutputStream
        .computeInt32Size(2, pageSize_);
    }
    if (!com.google.protobuf.GeneratedMessageV3.isStringEmpty(pageToken_)) {
      size += com.google.protobuf.GeneratedMessageV3.computeStringSize(3, pageToken_);
    }
    if (view_ != com.google.bigtable.admin.v2.AuthorizedView.ResponseView.RESPONSE_VIEW_UNSPECIFIED.getNumber()) {
      size += com.google.protobuf.CodedOutputStream
        .computeEnumSize(4, view_);
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
    if (!(obj instanceof com.google.bigtable.admin.v2.ListAuthorizedViewsRequest)) {
      return super.equals(obj);
    }
    com.google.bigtable.admin.v2.ListAuthorizedViewsRequest other = (com.google.bigtable.admin.v2.ListAuthorizedViewsRequest) obj;

    if (!getParent()
        .equals(other.getParent())) return false;
    if (getPageSize()
        != other.getPageSize()) return false;
    if (!getPageToken()
        .equals(other.getPageToken())) return false;
    if (view_ != other.view_) return false;
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
    hash = (37 * hash) + PARENT_FIELD_NUMBER;
    hash = (53 * hash) + getParent().hashCode();
    hash = (37 * hash) + PAGE_SIZE_FIELD_NUMBER;
    hash = (53 * hash) + getPageSize();
    hash = (37 * hash) + PAGE_TOKEN_FIELD_NUMBER;
    hash = (53 * hash) + getPageToken().hashCode();
    hash = (37 * hash) + VIEW_FIELD_NUMBER;
    hash = (53 * hash) + view_;
    hash = (29 * hash) + getUnknownFields().hashCode();
    memoizedHashCode = hash;
    return hash;
  }

  public static com.google.bigtable.admin.v2.ListAuthorizedViewsRequest parseFrom(
      java.nio.ByteBuffer data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static com.google.bigtable.admin.v2.ListAuthorizedViewsRequest parseFrom(
      java.nio.ByteBuffer data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static com.google.bigtable.admin.v2.ListAuthorizedViewsRequest parseFrom(
      com.google.protobuf.ByteString data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static com.google.bigtable.admin.v2.ListAuthorizedViewsRequest parseFrom(
      com.google.protobuf.ByteString data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static com.google.bigtable.admin.v2.ListAuthorizedViewsRequest parseFrom(byte[] data)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data);
  }
  public static com.google.bigtable.admin.v2.ListAuthorizedViewsRequest parseFrom(
      byte[] data,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws com.google.protobuf.InvalidProtocolBufferException {
    return PARSER.parseFrom(data, extensionRegistry);
  }
  public static com.google.bigtable.admin.v2.ListAuthorizedViewsRequest parseFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static com.google.bigtable.admin.v2.ListAuthorizedViewsRequest parseFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }

  public static com.google.bigtable.admin.v2.ListAuthorizedViewsRequest parseDelimitedFrom(java.io.InputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input);
  }

  public static com.google.bigtable.admin.v2.ListAuthorizedViewsRequest parseDelimitedFrom(
      java.io.InputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseDelimitedWithIOException(PARSER, input, extensionRegistry);
  }
  public static com.google.bigtable.admin.v2.ListAuthorizedViewsRequest parseFrom(
      com.google.protobuf.CodedInputStream input)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input);
  }
  public static com.google.bigtable.admin.v2.ListAuthorizedViewsRequest parseFrom(
      com.google.protobuf.CodedInputStream input,
      com.google.protobuf.ExtensionRegistryLite extensionRegistry)
      throws java.io.IOException {
    return com.google.protobuf.GeneratedMessageV3
        .parseWithIOException(PARSER, input, extensionRegistry);
  }

  @java.lang.Override
  public Builder newBuilderForType() { return newBuilder(); }
  public static Builder newBuilder() {
    return DEFAULT_INSTANCE.toBuilder();
  }
  public static Builder newBuilder(com.google.bigtable.admin.v2.ListAuthorizedViewsRequest prototype) {
    return DEFAULT_INSTANCE.toBuilder().mergeFrom(prototype);
  }
  @java.lang.Override
  public Builder toBuilder() {
    return this == DEFAULT_INSTANCE
        ? new Builder() : new Builder().mergeFrom(this);
  }

  @java.lang.Override
  protected Builder newBuilderForType(
      com.google.protobuf.GeneratedMessageV3.BuilderParent parent) {
    Builder builder = new Builder(parent);
    return builder;
  }
  /**
   * <pre>
   * Request message for
   * [google.bigtable.admin.v2.BigtableTableAdmin.ListAuthorizedViews][google.bigtable.admin.v2.BigtableTableAdmin.ListAuthorizedViews]
   * </pre>
   *
   * Protobuf type {@code google.bigtable.admin.v2.ListAuthorizedViewsRequest}
   */
  public static final class Builder extends
      com.google.protobuf.GeneratedMessageV3.Builder<Builder> implements
      // @@protoc_insertion_point(builder_implements:google.bigtable.admin.v2.ListAuthorizedViewsRequest)
      com.google.bigtable.admin.v2.ListAuthorizedViewsRequestOrBuilder {
    public static final com.google.protobuf.Descriptors.Descriptor
        getDescriptor() {
      return com.google.bigtable.admin.v2.BigtableTableAdminProto.internal_static_google_bigtable_admin_v2_ListAuthorizedViewsRequest_descriptor;
    }

    @java.lang.Override
    protected com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
        internalGetFieldAccessorTable() {
      return com.google.bigtable.admin.v2.BigtableTableAdminProto.internal_static_google_bigtable_admin_v2_ListAuthorizedViewsRequest_fieldAccessorTable
          .ensureFieldAccessorsInitialized(
              com.google.bigtable.admin.v2.ListAuthorizedViewsRequest.class, com.google.bigtable.admin.v2.ListAuthorizedViewsRequest.Builder.class);
    }

    // Construct using com.google.bigtable.admin.v2.ListAuthorizedViewsRequest.newBuilder()
    private Builder() {

    }

    private Builder(
        com.google.protobuf.GeneratedMessageV3.BuilderParent parent) {
      super(parent);

    }
    @java.lang.Override
    public Builder clear() {
      super.clear();
      bitField0_ = 0;
      parent_ = "";
      pageSize_ = 0;
      pageToken_ = "";
      view_ = 0;
      return this;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.Descriptor
        getDescriptorForType() {
      return com.google.bigtable.admin.v2.BigtableTableAdminProto.internal_static_google_bigtable_admin_v2_ListAuthorizedViewsRequest_descriptor;
    }

    @java.lang.Override
    public com.google.bigtable.admin.v2.ListAuthorizedViewsRequest getDefaultInstanceForType() {
      return com.google.bigtable.admin.v2.ListAuthorizedViewsRequest.getDefaultInstance();
    }

    @java.lang.Override
    public com.google.bigtable.admin.v2.ListAuthorizedViewsRequest build() {
      com.google.bigtable.admin.v2.ListAuthorizedViewsRequest result = buildPartial();
      if (!result.isInitialized()) {
        throw newUninitializedMessageException(result);
      }
      return result;
    }

    @java.lang.Override
    public com.google.bigtable.admin.v2.ListAuthorizedViewsRequest buildPartial() {
      com.google.bigtable.admin.v2.ListAuthorizedViewsRequest result = new com.google.bigtable.admin.v2.ListAuthorizedViewsRequest(this);
      if (bitField0_ != 0) { buildPartial0(result); }
      onBuilt();
      return result;
    }

    private void buildPartial0(com.google.bigtable.admin.v2.ListAuthorizedViewsRequest result) {
      int from_bitField0_ = bitField0_;
      if (((from_bitField0_ & 0x00000001) != 0)) {
        result.parent_ = parent_;
      }
      if (((from_bitField0_ & 0x00000002) != 0)) {
        result.pageSize_ = pageSize_;
      }
      if (((from_bitField0_ & 0x00000004) != 0)) {
        result.pageToken_ = pageToken_;
      }
      if (((from_bitField0_ & 0x00000008) != 0)) {
        result.view_ = view_;
      }
    }

    @java.lang.Override
    public Builder clone() {
      return super.clone();
    }
    @java.lang.Override
    public Builder setField(
        com.google.protobuf.Descriptors.FieldDescriptor field,
        java.lang.Object value) {
      return super.setField(field, value);
    }
    @java.lang.Override
    public Builder clearField(
        com.google.protobuf.Descriptors.FieldDescriptor field) {
      return super.clearField(field);
    }
    @java.lang.Override
    public Builder clearOneof(
        com.google.protobuf.Descriptors.OneofDescriptor oneof) {
      return super.clearOneof(oneof);
    }
    @java.lang.Override
    public Builder setRepeatedField(
        com.google.protobuf.Descriptors.FieldDescriptor field,
        int index, java.lang.Object value) {
      return super.setRepeatedField(field, index, value);
    }
    @java.lang.Override
    public Builder addRepeatedField(
        com.google.protobuf.Descriptors.FieldDescriptor field,
        java.lang.Object value) {
      return super.addRepeatedField(field, value);
    }
    @java.lang.Override
    public Builder mergeFrom(com.google.protobuf.Message other) {
      if (other instanceof com.google.bigtable.admin.v2.ListAuthorizedViewsRequest) {
        return mergeFrom((com.google.bigtable.admin.v2.ListAuthorizedViewsRequest)other);
      } else {
        super.mergeFrom(other);
        return this;
      }
    }

    public Builder mergeFrom(com.google.bigtable.admin.v2.ListAuthorizedViewsRequest other) {
      if (other == com.google.bigtable.admin.v2.ListAuthorizedViewsRequest.getDefaultInstance()) return this;
      if (!other.getParent().isEmpty()) {
        parent_ = other.parent_;
        bitField0_ |= 0x00000001;
        onChanged();
      }
      if (other.getPageSize() != 0) {
        setPageSize(other.getPageSize());
      }
      if (!other.getPageToken().isEmpty()) {
        pageToken_ = other.pageToken_;
        bitField0_ |= 0x00000004;
        onChanged();
      }
      if (other.view_ != 0) {
        setViewValue(other.getViewValue());
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
            case 10: {
              parent_ = input.readStringRequireUtf8();
              bitField0_ |= 0x00000001;
              break;
            } // case 10
            case 16: {
              pageSize_ = input.readInt32();
              bitField0_ |= 0x00000002;
              break;
            } // case 16
            case 26: {
              pageToken_ = input.readStringRequireUtf8();
              bitField0_ |= 0x00000004;
              break;
            } // case 26
            case 32: {
              view_ = input.readEnum();
              bitField0_ |= 0x00000008;
              break;
            } // case 32
            default: {
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

    private java.lang.Object parent_ = "";
    /**
     * <pre>
     * Required. The unique name of the table for which AuthorizedViews should be
     * listed. Values are of the form
     * `projects/{project}/instances/{instance}/tables/{table}`.
     * </pre>
     *
     * <code>string parent = 1 [(.google.api.field_behavior) = REQUIRED, (.google.api.resource_reference) = { ... }</code>
     * @return The parent.
     */
    public java.lang.String getParent() {
      java.lang.Object ref = parent_;
      if (!(ref instanceof java.lang.String)) {
        com.google.protobuf.ByteString bs =
            (com.google.protobuf.ByteString) ref;
        java.lang.String s = bs.toStringUtf8();
        parent_ = s;
        return s;
      } else {
        return (java.lang.String) ref;
      }
    }
    /**
     * <pre>
     * Required. The unique name of the table for which AuthorizedViews should be
     * listed. Values are of the form
     * `projects/{project}/instances/{instance}/tables/{table}`.
     * </pre>
     *
     * <code>string parent = 1 [(.google.api.field_behavior) = REQUIRED, (.google.api.resource_reference) = { ... }</code>
     * @return The bytes for parent.
     */
    public com.google.protobuf.ByteString
        getParentBytes() {
      java.lang.Object ref = parent_;
      if (ref instanceof String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8(
                (java.lang.String) ref);
        parent_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }
    /**
     * <pre>
     * Required. The unique name of the table for which AuthorizedViews should be
     * listed. Values are of the form
     * `projects/{project}/instances/{instance}/tables/{table}`.
     * </pre>
     *
     * <code>string parent = 1 [(.google.api.field_behavior) = REQUIRED, (.google.api.resource_reference) = { ... }</code>
     * @param value The parent to set.
     * @return This builder for chaining.
     */
    public Builder setParent(
        java.lang.String value) {
      if (value == null) { throw new NullPointerException(); }
      parent_ = value;
      bitField0_ |= 0x00000001;
      onChanged();
      return this;
    }
    /**
     * <pre>
     * Required. The unique name of the table for which AuthorizedViews should be
     * listed. Values are of the form
     * `projects/{project}/instances/{instance}/tables/{table}`.
     * </pre>
     *
     * <code>string parent = 1 [(.google.api.field_behavior) = REQUIRED, (.google.api.resource_reference) = { ... }</code>
     * @return This builder for chaining.
     */
    public Builder clearParent() {
      parent_ = getDefaultInstance().getParent();
      bitField0_ = (bitField0_ & ~0x00000001);
      onChanged();
      return this;
    }
    /**
     * <pre>
     * Required. The unique name of the table for which AuthorizedViews should be
     * listed. Values are of the form
     * `projects/{project}/instances/{instance}/tables/{table}`.
     * </pre>
     *
     * <code>string parent = 1 [(.google.api.field_behavior) = REQUIRED, (.google.api.resource_reference) = { ... }</code>
     * @param value The bytes for parent to set.
     * @return This builder for chaining.
     */
    public Builder setParentBytes(
        com.google.protobuf.ByteString value) {
      if (value == null) { throw new NullPointerException(); }
      checkByteStringIsUtf8(value);
      parent_ = value;
      bitField0_ |= 0x00000001;
      onChanged();
      return this;
    }

    private int pageSize_ ;
    /**
     * <pre>
     * Optional. Maximum number of results per page.
     *
     * A page_size of zero lets the server choose the number of items to return.
     * A page_size which is strictly positive will return at most that many items.
     * A negative page_size will cause an error.
     *
     * Following the first request, subsequent paginated calls are not required
     * to pass a page_size. If a page_size is set in subsequent calls, it must
     * match the page_size given in the first request.
     * </pre>
     *
     * <code>int32 page_size = 2 [(.google.api.field_behavior) = OPTIONAL];</code>
     * @return The pageSize.
     */
    @java.lang.Override
    public int getPageSize() {
      return pageSize_;
    }
    /**
     * <pre>
     * Optional. Maximum number of results per page.
     *
     * A page_size of zero lets the server choose the number of items to return.
     * A page_size which is strictly positive will return at most that many items.
     * A negative page_size will cause an error.
     *
     * Following the first request, subsequent paginated calls are not required
     * to pass a page_size. If a page_size is set in subsequent calls, it must
     * match the page_size given in the first request.
     * </pre>
     *
     * <code>int32 page_size = 2 [(.google.api.field_behavior) = OPTIONAL];</code>
     * @param value The pageSize to set.
     * @return This builder for chaining.
     */
    public Builder setPageSize(int value) {

      pageSize_ = value;
      bitField0_ |= 0x00000002;
      onChanged();
      return this;
    }
    /**
     * <pre>
     * Optional. Maximum number of results per page.
     *
     * A page_size of zero lets the server choose the number of items to return.
     * A page_size which is strictly positive will return at most that many items.
     * A negative page_size will cause an error.
     *
     * Following the first request, subsequent paginated calls are not required
     * to pass a page_size. If a page_size is set in subsequent calls, it must
     * match the page_size given in the first request.
     * </pre>
     *
     * <code>int32 page_size = 2 [(.google.api.field_behavior) = OPTIONAL];</code>
     * @return This builder for chaining.
     */
    public Builder clearPageSize() {
      bitField0_ = (bitField0_ & ~0x00000002);
      pageSize_ = 0;
      onChanged();
      return this;
    }

    private java.lang.Object pageToken_ = "";
    /**
     * <pre>
     * Optional. The value of `next_page_token` returned by a previous call.
     * </pre>
     *
     * <code>string page_token = 3 [(.google.api.field_behavior) = OPTIONAL];</code>
     * @return The pageToken.
     */
    public java.lang.String getPageToken() {
      java.lang.Object ref = pageToken_;
      if (!(ref instanceof java.lang.String)) {
        com.google.protobuf.ByteString bs =
            (com.google.protobuf.ByteString) ref;
        java.lang.String s = bs.toStringUtf8();
        pageToken_ = s;
        return s;
      } else {
        return (java.lang.String) ref;
      }
    }
    /**
     * <pre>
     * Optional. The value of `next_page_token` returned by a previous call.
     * </pre>
     *
     * <code>string page_token = 3 [(.google.api.field_behavior) = OPTIONAL];</code>
     * @return The bytes for pageToken.
     */
    public com.google.protobuf.ByteString
        getPageTokenBytes() {
      java.lang.Object ref = pageToken_;
      if (ref instanceof String) {
        com.google.protobuf.ByteString b = 
            com.google.protobuf.ByteString.copyFromUtf8(
                (java.lang.String) ref);
        pageToken_ = b;
        return b;
      } else {
        return (com.google.protobuf.ByteString) ref;
      }
    }
    /**
     * <pre>
     * Optional. The value of `next_page_token` returned by a previous call.
     * </pre>
     *
     * <code>string page_token = 3 [(.google.api.field_behavior) = OPTIONAL];</code>
     * @param value The pageToken to set.
     * @return This builder for chaining.
     */
    public Builder setPageToken(
        java.lang.String value) {
      if (value == null) { throw new NullPointerException(); }
      pageToken_ = value;
      bitField0_ |= 0x00000004;
      onChanged();
      return this;
    }
    /**
     * <pre>
     * Optional. The value of `next_page_token` returned by a previous call.
     * </pre>
     *
     * <code>string page_token = 3 [(.google.api.field_behavior) = OPTIONAL];</code>
     * @return This builder for chaining.
     */
    public Builder clearPageToken() {
      pageToken_ = getDefaultInstance().getPageToken();
      bitField0_ = (bitField0_ & ~0x00000004);
      onChanged();
      return this;
    }
    /**
     * <pre>
     * Optional. The value of `next_page_token` returned by a previous call.
     * </pre>
     *
     * <code>string page_token = 3 [(.google.api.field_behavior) = OPTIONAL];</code>
     * @param value The bytes for pageToken to set.
     * @return This builder for chaining.
     */
    public Builder setPageTokenBytes(
        com.google.protobuf.ByteString value) {
      if (value == null) { throw new NullPointerException(); }
      checkByteStringIsUtf8(value);
      pageToken_ = value;
      bitField0_ |= 0x00000004;
      onChanged();
      return this;
    }

    private int view_ = 0;
    /**
     * <pre>
     * Optional. The resource_view to be applied to the returned views' fields.
     * Default to NAME_ONLY.
     * </pre>
     *
     * <code>.google.bigtable.admin.v2.AuthorizedView.ResponseView view = 4 [(.google.api.field_behavior) = OPTIONAL];</code>
     * @return The enum numeric value on the wire for view.
     */
    @java.lang.Override public int getViewValue() {
      return view_;
    }
    /**
     * <pre>
     * Optional. The resource_view to be applied to the returned views' fields.
     * Default to NAME_ONLY.
     * </pre>
     *
     * <code>.google.bigtable.admin.v2.AuthorizedView.ResponseView view = 4 [(.google.api.field_behavior) = OPTIONAL];</code>
     * @param value The enum numeric value on the wire for view to set.
     * @return This builder for chaining.
     */
    public Builder setViewValue(int value) {
      view_ = value;
      bitField0_ |= 0x00000008;
      onChanged();
      return this;
    }
    /**
     * <pre>
     * Optional. The resource_view to be applied to the returned views' fields.
     * Default to NAME_ONLY.
     * </pre>
     *
     * <code>.google.bigtable.admin.v2.AuthorizedView.ResponseView view = 4 [(.google.api.field_behavior) = OPTIONAL];</code>
     * @return The view.
     */
    @java.lang.Override
    public com.google.bigtable.admin.v2.AuthorizedView.ResponseView getView() {
      com.google.bigtable.admin.v2.AuthorizedView.ResponseView result = com.google.bigtable.admin.v2.AuthorizedView.ResponseView.forNumber(view_);
      return result == null ? com.google.bigtable.admin.v2.AuthorizedView.ResponseView.UNRECOGNIZED : result;
    }
    /**
     * <pre>
     * Optional. The resource_view to be applied to the returned views' fields.
     * Default to NAME_ONLY.
     * </pre>
     *
     * <code>.google.bigtable.admin.v2.AuthorizedView.ResponseView view = 4 [(.google.api.field_behavior) = OPTIONAL];</code>
     * @param value The view to set.
     * @return This builder for chaining.
     */
    public Builder setView(com.google.bigtable.admin.v2.AuthorizedView.ResponseView value) {
      if (value == null) {
        throw new NullPointerException();
      }
      bitField0_ |= 0x00000008;
      view_ = value.getNumber();
      onChanged();
      return this;
    }
    /**
     * <pre>
     * Optional. The resource_view to be applied to the returned views' fields.
     * Default to NAME_ONLY.
     * </pre>
     *
     * <code>.google.bigtable.admin.v2.AuthorizedView.ResponseView view = 4 [(.google.api.field_behavior) = OPTIONAL];</code>
     * @return This builder for chaining.
     */
    public Builder clearView() {
      bitField0_ = (bitField0_ & ~0x00000008);
      view_ = 0;
      onChanged();
      return this;
    }
    @java.lang.Override
    public final Builder setUnknownFields(
        final com.google.protobuf.UnknownFieldSet unknownFields) {
      return super.setUnknownFields(unknownFields);
    }

    @java.lang.Override
    public final Builder mergeUnknownFields(
        final com.google.protobuf.UnknownFieldSet unknownFields) {
      return super.mergeUnknownFields(unknownFields);
    }


    // @@protoc_insertion_point(builder_scope:google.bigtable.admin.v2.ListAuthorizedViewsRequest)
  }

  // @@protoc_insertion_point(class_scope:google.bigtable.admin.v2.ListAuthorizedViewsRequest)
  private static final com.google.bigtable.admin.v2.ListAuthorizedViewsRequest DEFAULT_INSTANCE;
  static {
    DEFAULT_INSTANCE = new com.google.bigtable.admin.v2.ListAuthorizedViewsRequest();
  }

  public static com.google.bigtable.admin.v2.ListAuthorizedViewsRequest getDefaultInstance() {
    return DEFAULT_INSTANCE;
  }

  private static final com.google.protobuf.Parser<ListAuthorizedViewsRequest>
      PARSER = new com.google.protobuf.AbstractParser<ListAuthorizedViewsRequest>() {
    @java.lang.Override
    public ListAuthorizedViewsRequest parsePartialFrom(
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

  public static com.google.protobuf.Parser<ListAuthorizedViewsRequest> parser() {
    return PARSER;
  }

  @java.lang.Override
  public com.google.protobuf.Parser<ListAuthorizedViewsRequest> getParserForType() {
    return PARSER;
  }

  @java.lang.Override
  public com.google.bigtable.admin.v2.ListAuthorizedViewsRequest getDefaultInstanceForType() {
    return DEFAULT_INSTANCE;
  }

}
