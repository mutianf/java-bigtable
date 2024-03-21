// Generated by the protocol buffer compiler.  DO NOT EDIT!
// source: google/bigtable/admin/v2/bigtable_instance_admin.proto

// Protobuf Java Version: 3.25.2
package com.google.bigtable.admin.v2;

public interface ListHotTabletsResponseOrBuilder extends
    // @@protoc_insertion_point(interface_extends:google.bigtable.admin.v2.ListHotTabletsResponse)
    com.google.protobuf.MessageOrBuilder {

  /**
   * <pre>
   * List of hot tablets in the tables of the requested cluster that fall
   * within the requested time range. Hot tablets are ordered by node cpu usage
   * percent. If there are multiple hot tablets that correspond to the same
   * tablet within a 15-minute interval, only the hot tablet with the highest
   * node cpu usage will be included in the response.
   * </pre>
   *
   * <code>repeated .google.bigtable.admin.v2.HotTablet hot_tablets = 1;</code>
   */
  java.util.List<com.google.bigtable.admin.v2.HotTablet> 
      getHotTabletsList();
  /**
   * <pre>
   * List of hot tablets in the tables of the requested cluster that fall
   * within the requested time range. Hot tablets are ordered by node cpu usage
   * percent. If there are multiple hot tablets that correspond to the same
   * tablet within a 15-minute interval, only the hot tablet with the highest
   * node cpu usage will be included in the response.
   * </pre>
   *
   * <code>repeated .google.bigtable.admin.v2.HotTablet hot_tablets = 1;</code>
   */
  com.google.bigtable.admin.v2.HotTablet getHotTablets(int index);
  /**
   * <pre>
   * List of hot tablets in the tables of the requested cluster that fall
   * within the requested time range. Hot tablets are ordered by node cpu usage
   * percent. If there are multiple hot tablets that correspond to the same
   * tablet within a 15-minute interval, only the hot tablet with the highest
   * node cpu usage will be included in the response.
   * </pre>
   *
   * <code>repeated .google.bigtable.admin.v2.HotTablet hot_tablets = 1;</code>
   */
  int getHotTabletsCount();
  /**
   * <pre>
   * List of hot tablets in the tables of the requested cluster that fall
   * within the requested time range. Hot tablets are ordered by node cpu usage
   * percent. If there are multiple hot tablets that correspond to the same
   * tablet within a 15-minute interval, only the hot tablet with the highest
   * node cpu usage will be included in the response.
   * </pre>
   *
   * <code>repeated .google.bigtable.admin.v2.HotTablet hot_tablets = 1;</code>
   */
  java.util.List<? extends com.google.bigtable.admin.v2.HotTabletOrBuilder> 
      getHotTabletsOrBuilderList();
  /**
   * <pre>
   * List of hot tablets in the tables of the requested cluster that fall
   * within the requested time range. Hot tablets are ordered by node cpu usage
   * percent. If there are multiple hot tablets that correspond to the same
   * tablet within a 15-minute interval, only the hot tablet with the highest
   * node cpu usage will be included in the response.
   * </pre>
   *
   * <code>repeated .google.bigtable.admin.v2.HotTablet hot_tablets = 1;</code>
   */
  com.google.bigtable.admin.v2.HotTabletOrBuilder getHotTabletsOrBuilder(
      int index);

  /**
   * <pre>
   * Set if not all hot tablets could be returned in a single response.
   * Pass this value to `page_token` in another request to get the next
   * page of results.
   * </pre>
   *
   * <code>string next_page_token = 2;</code>
   * @return The nextPageToken.
   */
  java.lang.String getNextPageToken();
  /**
   * <pre>
   * Set if not all hot tablets could be returned in a single response.
   * Pass this value to `page_token` in another request to get the next
   * page of results.
   * </pre>
   *
   * <code>string next_page_token = 2;</code>
   * @return The bytes for nextPageToken.
   */
  com.google.protobuf.ByteString
      getNextPageTokenBytes();
}