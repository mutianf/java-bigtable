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

public interface RowFilterOrBuilder
    extends
    // @@protoc_insertion_point(interface_extends:google.bigtable.v2.RowFilter)
    com.google.protobuf.MessageOrBuilder {

  /**
   *
   *
   * <pre>
   * Applies several RowFilters to the data in sequence, progressively
   * narrowing the results.
   * </pre>
   *
   * <code>.google.bigtable.v2.RowFilter.Chain chain = 1;</code>
   *
   * @return Whether the chain field is set.
   */
  boolean hasChain();
  /**
   *
   *
   * <pre>
   * Applies several RowFilters to the data in sequence, progressively
   * narrowing the results.
   * </pre>
   *
   * <code>.google.bigtable.v2.RowFilter.Chain chain = 1;</code>
   *
   * @return The chain.
   */
  com.google.bigtable.v2.RowFilter.Chain getChain();
  /**
   *
   *
   * <pre>
   * Applies several RowFilters to the data in sequence, progressively
   * narrowing the results.
   * </pre>
   *
   * <code>.google.bigtable.v2.RowFilter.Chain chain = 1;</code>
   */
  com.google.bigtable.v2.RowFilter.ChainOrBuilder getChainOrBuilder();

  /**
   *
   *
   * <pre>
   * Applies several RowFilters to the data in parallel and combines the
   * results.
   * </pre>
   *
   * <code>.google.bigtable.v2.RowFilter.Interleave interleave = 2;</code>
   *
   * @return Whether the interleave field is set.
   */
  boolean hasInterleave();
  /**
   *
   *
   * <pre>
   * Applies several RowFilters to the data in parallel and combines the
   * results.
   * </pre>
   *
   * <code>.google.bigtable.v2.RowFilter.Interleave interleave = 2;</code>
   *
   * @return The interleave.
   */
  com.google.bigtable.v2.RowFilter.Interleave getInterleave();
  /**
   *
   *
   * <pre>
   * Applies several RowFilters to the data in parallel and combines the
   * results.
   * </pre>
   *
   * <code>.google.bigtable.v2.RowFilter.Interleave interleave = 2;</code>
   */
  com.google.bigtable.v2.RowFilter.InterleaveOrBuilder getInterleaveOrBuilder();

  /**
   *
   *
   * <pre>
   * Applies one of two possible RowFilters to the data based on the output of
   * a predicate RowFilter.
   * </pre>
   *
   * <code>.google.bigtable.v2.RowFilter.Condition condition = 3;</code>
   *
   * @return Whether the condition field is set.
   */
  boolean hasCondition();
  /**
   *
   *
   * <pre>
   * Applies one of two possible RowFilters to the data based on the output of
   * a predicate RowFilter.
   * </pre>
   *
   * <code>.google.bigtable.v2.RowFilter.Condition condition = 3;</code>
   *
   * @return The condition.
   */
  com.google.bigtable.v2.RowFilter.Condition getCondition();
  /**
   *
   *
   * <pre>
   * Applies one of two possible RowFilters to the data based on the output of
   * a predicate RowFilter.
   * </pre>
   *
   * <code>.google.bigtable.v2.RowFilter.Condition condition = 3;</code>
   */
  com.google.bigtable.v2.RowFilter.ConditionOrBuilder getConditionOrBuilder();

  /**
   *
   *
   * <pre>
   * ADVANCED USE ONLY.
   * Hook for introspection into the RowFilter. Outputs all cells directly to
   * the output of the read rather than to any parent filter. Consider the
   * following example:
   *     Chain(
   *       FamilyRegex("A"),
   *       Interleave(
   *         All(),
   *         Chain(Label("foo"), Sink())
   *       ),
   *       QualifierRegex("B")
   *     )
   *                         A,A,1,w
   *                         A,B,2,x
   *                         B,B,4,z
   *                            |
   *                     FamilyRegex("A")
   *                            |
   *                         A,A,1,w
   *                         A,B,2,x
   *                            |
   *               +------------+-------------+
   *               |                          |
   *             All()                    Label(foo)
   *               |                          |
   *            A,A,1,w              A,A,1,w,labels:[foo]
   *            A,B,2,x              A,B,2,x,labels:[foo]
   *               |                          |
   *               |                        Sink() --------------+
   *               |                          |                  |
   *               +------------+      x------+          A,A,1,w,labels:[foo]
   *                            |                        A,B,2,x,labels:[foo]
   *                         A,A,1,w                             |
   *                         A,B,2,x                             |
   *                            |                                |
   *                    QualifierRegex("B")                      |
   *                            |                                |
   *                         A,B,2,x                             |
   *                            |                                |
   *                            +--------------------------------+
   *                            |
   *                         A,A,1,w,labels:[foo]
   *                         A,B,2,x,labels:[foo]  // could be switched
   *                         A,B,2,x               // could be switched
   * Despite being excluded by the qualifier filter, a copy of every cell
   * that reaches the sink is present in the final result.
   * As with an [Interleave][google.bigtable.v2.RowFilter.Interleave],
   * duplicate cells are possible, and appear in an unspecified mutual order.
   * In this case we have a duplicate with column "A:B" and timestamp 2,
   * because one copy passed through the all filter while the other was
   * passed through the label and sink. Note that one copy has label "foo",
   * while the other does not.
   * Cannot be used within the `predicate_filter`, `true_filter`, or
   * `false_filter` of a [Condition][google.bigtable.v2.RowFilter.Condition].
   * </pre>
   *
   * <code>bool sink = 16;</code>
   *
   * @return Whether the sink field is set.
   */
  boolean hasSink();
  /**
   *
   *
   * <pre>
   * ADVANCED USE ONLY.
   * Hook for introspection into the RowFilter. Outputs all cells directly to
   * the output of the read rather than to any parent filter. Consider the
   * following example:
   *     Chain(
   *       FamilyRegex("A"),
   *       Interleave(
   *         All(),
   *         Chain(Label("foo"), Sink())
   *       ),
   *       QualifierRegex("B")
   *     )
   *                         A,A,1,w
   *                         A,B,2,x
   *                         B,B,4,z
   *                            |
   *                     FamilyRegex("A")
   *                            |
   *                         A,A,1,w
   *                         A,B,2,x
   *                            |
   *               +------------+-------------+
   *               |                          |
   *             All()                    Label(foo)
   *               |                          |
   *            A,A,1,w              A,A,1,w,labels:[foo]
   *            A,B,2,x              A,B,2,x,labels:[foo]
   *               |                          |
   *               |                        Sink() --------------+
   *               |                          |                  |
   *               +------------+      x------+          A,A,1,w,labels:[foo]
   *                            |                        A,B,2,x,labels:[foo]
   *                         A,A,1,w                             |
   *                         A,B,2,x                             |
   *                            |                                |
   *                    QualifierRegex("B")                      |
   *                            |                                |
   *                         A,B,2,x                             |
   *                            |                                |
   *                            +--------------------------------+
   *                            |
   *                         A,A,1,w,labels:[foo]
   *                         A,B,2,x,labels:[foo]  // could be switched
   *                         A,B,2,x               // could be switched
   * Despite being excluded by the qualifier filter, a copy of every cell
   * that reaches the sink is present in the final result.
   * As with an [Interleave][google.bigtable.v2.RowFilter.Interleave],
   * duplicate cells are possible, and appear in an unspecified mutual order.
   * In this case we have a duplicate with column "A:B" and timestamp 2,
   * because one copy passed through the all filter while the other was
   * passed through the label and sink. Note that one copy has label "foo",
   * while the other does not.
   * Cannot be used within the `predicate_filter`, `true_filter`, or
   * `false_filter` of a [Condition][google.bigtable.v2.RowFilter.Condition].
   * </pre>
   *
   * <code>bool sink = 16;</code>
   *
   * @return The sink.
   */
  boolean getSink();

  /**
   *
   *
   * <pre>
   * Matches all cells, regardless of input. Functionally equivalent to
   * leaving `filter` unset, but included for completeness.
   * </pre>
   *
   * <code>bool pass_all_filter = 17;</code>
   *
   * @return Whether the passAllFilter field is set.
   */
  boolean hasPassAllFilter();
  /**
   *
   *
   * <pre>
   * Matches all cells, regardless of input. Functionally equivalent to
   * leaving `filter` unset, but included for completeness.
   * </pre>
   *
   * <code>bool pass_all_filter = 17;</code>
   *
   * @return The passAllFilter.
   */
  boolean getPassAllFilter();

  /**
   *
   *
   * <pre>
   * Does not match any cells, regardless of input. Useful for temporarily
   * disabling just part of a filter.
   * </pre>
   *
   * <code>bool block_all_filter = 18;</code>
   *
   * @return Whether the blockAllFilter field is set.
   */
  boolean hasBlockAllFilter();
  /**
   *
   *
   * <pre>
   * Does not match any cells, regardless of input. Useful for temporarily
   * disabling just part of a filter.
   * </pre>
   *
   * <code>bool block_all_filter = 18;</code>
   *
   * @return The blockAllFilter.
   */
  boolean getBlockAllFilter();

  /**
   *
   *
   * <pre>
   * Matches only cells from rows whose keys satisfy the given RE2 regex. In
   * other words, passes through the entire row when the key matches, and
   * otherwise produces an empty row.
   * Note that, since row keys can contain arbitrary bytes, the `&#92;C` escape
   * sequence must be used if a true wildcard is desired. The `.` character
   * will not match the new line character `&#92;n`, which may be present in a
   * binary key.
   * </pre>
   *
   * <code>bytes row_key_regex_filter = 4;</code>
   *
   * @return Whether the rowKeyRegexFilter field is set.
   */
  boolean hasRowKeyRegexFilter();
  /**
   *
   *
   * <pre>
   * Matches only cells from rows whose keys satisfy the given RE2 regex. In
   * other words, passes through the entire row when the key matches, and
   * otherwise produces an empty row.
   * Note that, since row keys can contain arbitrary bytes, the `&#92;C` escape
   * sequence must be used if a true wildcard is desired. The `.` character
   * will not match the new line character `&#92;n`, which may be present in a
   * binary key.
   * </pre>
   *
   * <code>bytes row_key_regex_filter = 4;</code>
   *
   * @return The rowKeyRegexFilter.
   */
  com.google.protobuf.ByteString getRowKeyRegexFilter();

  /**
   *
   *
   * <pre>
   * Matches all cells from a row with probability p, and matches no cells
   * from the row with probability 1-p.
   * </pre>
   *
   * <code>double row_sample_filter = 14;</code>
   *
   * @return Whether the rowSampleFilter field is set.
   */
  boolean hasRowSampleFilter();
  /**
   *
   *
   * <pre>
   * Matches all cells from a row with probability p, and matches no cells
   * from the row with probability 1-p.
   * </pre>
   *
   * <code>double row_sample_filter = 14;</code>
   *
   * @return The rowSampleFilter.
   */
  double getRowSampleFilter();

  /**
   *
   *
   * <pre>
   * Matches only cells from columns whose families satisfy the given RE2
   * regex. For technical reasons, the regex must not contain the `:`
   * character, even if it is not being used as a literal.
   * Note that, since column families cannot contain the new line character
   * `&#92;n`, it is sufficient to use `.` as a full wildcard when matching
   * column family names.
   * </pre>
   *
   * <code>string family_name_regex_filter = 5;</code>
   *
   * @return Whether the familyNameRegexFilter field is set.
   */
  boolean hasFamilyNameRegexFilter();
  /**
   *
   *
   * <pre>
   * Matches only cells from columns whose families satisfy the given RE2
   * regex. For technical reasons, the regex must not contain the `:`
   * character, even if it is not being used as a literal.
   * Note that, since column families cannot contain the new line character
   * `&#92;n`, it is sufficient to use `.` as a full wildcard when matching
   * column family names.
   * </pre>
   *
   * <code>string family_name_regex_filter = 5;</code>
   *
   * @return The familyNameRegexFilter.
   */
  java.lang.String getFamilyNameRegexFilter();
  /**
   *
   *
   * <pre>
   * Matches only cells from columns whose families satisfy the given RE2
   * regex. For technical reasons, the regex must not contain the `:`
   * character, even if it is not being used as a literal.
   * Note that, since column families cannot contain the new line character
   * `&#92;n`, it is sufficient to use `.` as a full wildcard when matching
   * column family names.
   * </pre>
   *
   * <code>string family_name_regex_filter = 5;</code>
   *
   * @return The bytes for familyNameRegexFilter.
   */
  com.google.protobuf.ByteString getFamilyNameRegexFilterBytes();

  /**
   *
   *
   * <pre>
   * Matches only cells from columns whose qualifiers satisfy the given RE2
   * regex.
   * Note that, since column qualifiers can contain arbitrary bytes, the `&#92;C`
   * escape sequence must be used if a true wildcard is desired. The `.`
   * character will not match the new line character `&#92;n`, which may be
   * present in a binary qualifier.
   * </pre>
   *
   * <code>bytes column_qualifier_regex_filter = 6;</code>
   *
   * @return Whether the columnQualifierRegexFilter field is set.
   */
  boolean hasColumnQualifierRegexFilter();
  /**
   *
   *
   * <pre>
   * Matches only cells from columns whose qualifiers satisfy the given RE2
   * regex.
   * Note that, since column qualifiers can contain arbitrary bytes, the `&#92;C`
   * escape sequence must be used if a true wildcard is desired. The `.`
   * character will not match the new line character `&#92;n`, which may be
   * present in a binary qualifier.
   * </pre>
   *
   * <code>bytes column_qualifier_regex_filter = 6;</code>
   *
   * @return The columnQualifierRegexFilter.
   */
  com.google.protobuf.ByteString getColumnQualifierRegexFilter();

  /**
   *
   *
   * <pre>
   * Matches only cells from columns within the given range.
   * </pre>
   *
   * <code>.google.bigtable.v2.ColumnRange column_range_filter = 7;</code>
   *
   * @return Whether the columnRangeFilter field is set.
   */
  boolean hasColumnRangeFilter();
  /**
   *
   *
   * <pre>
   * Matches only cells from columns within the given range.
   * </pre>
   *
   * <code>.google.bigtable.v2.ColumnRange column_range_filter = 7;</code>
   *
   * @return The columnRangeFilter.
   */
  com.google.bigtable.v2.ColumnRange getColumnRangeFilter();
  /**
   *
   *
   * <pre>
   * Matches only cells from columns within the given range.
   * </pre>
   *
   * <code>.google.bigtable.v2.ColumnRange column_range_filter = 7;</code>
   */
  com.google.bigtable.v2.ColumnRangeOrBuilder getColumnRangeFilterOrBuilder();

  /**
   *
   *
   * <pre>
   * Matches only cells with timestamps within the given range.
   * </pre>
   *
   * <code>.google.bigtable.v2.TimestampRange timestamp_range_filter = 8;</code>
   *
   * @return Whether the timestampRangeFilter field is set.
   */
  boolean hasTimestampRangeFilter();
  /**
   *
   *
   * <pre>
   * Matches only cells with timestamps within the given range.
   * </pre>
   *
   * <code>.google.bigtable.v2.TimestampRange timestamp_range_filter = 8;</code>
   *
   * @return The timestampRangeFilter.
   */
  com.google.bigtable.v2.TimestampRange getTimestampRangeFilter();
  /**
   *
   *
   * <pre>
   * Matches only cells with timestamps within the given range.
   * </pre>
   *
   * <code>.google.bigtable.v2.TimestampRange timestamp_range_filter = 8;</code>
   */
  com.google.bigtable.v2.TimestampRangeOrBuilder getTimestampRangeFilterOrBuilder();

  /**
   *
   *
   * <pre>
   * Matches only cells with values that satisfy the given regular expression.
   * Note that, since cell values can contain arbitrary bytes, the `&#92;C` escape
   * sequence must be used if a true wildcard is desired. The `.` character
   * will not match the new line character `&#92;n`, which may be present in a
   * binary value.
   * </pre>
   *
   * <code>bytes value_regex_filter = 9;</code>
   *
   * @return Whether the valueRegexFilter field is set.
   */
  boolean hasValueRegexFilter();
  /**
   *
   *
   * <pre>
   * Matches only cells with values that satisfy the given regular expression.
   * Note that, since cell values can contain arbitrary bytes, the `&#92;C` escape
   * sequence must be used if a true wildcard is desired. The `.` character
   * will not match the new line character `&#92;n`, which may be present in a
   * binary value.
   * </pre>
   *
   * <code>bytes value_regex_filter = 9;</code>
   *
   * @return The valueRegexFilter.
   */
  com.google.protobuf.ByteString getValueRegexFilter();

  /**
   *
   *
   * <pre>
   * Matches only cells with values that fall within the given range.
   * </pre>
   *
   * <code>.google.bigtable.v2.ValueRange value_range_filter = 15;</code>
   *
   * @return Whether the valueRangeFilter field is set.
   */
  boolean hasValueRangeFilter();
  /**
   *
   *
   * <pre>
   * Matches only cells with values that fall within the given range.
   * </pre>
   *
   * <code>.google.bigtable.v2.ValueRange value_range_filter = 15;</code>
   *
   * @return The valueRangeFilter.
   */
  com.google.bigtable.v2.ValueRange getValueRangeFilter();
  /**
   *
   *
   * <pre>
   * Matches only cells with values that fall within the given range.
   * </pre>
   *
   * <code>.google.bigtable.v2.ValueRange value_range_filter = 15;</code>
   */
  com.google.bigtable.v2.ValueRangeOrBuilder getValueRangeFilterOrBuilder();

  /**
   *
   *
   * <pre>
   * Skips the first N cells of each row, matching all subsequent cells.
   * If duplicate cells are present, as is possible when using an Interleave,
   * each copy of the cell is counted separately.
   * </pre>
   *
   * <code>int32 cells_per_row_offset_filter = 10;</code>
   *
   * @return Whether the cellsPerRowOffsetFilter field is set.
   */
  boolean hasCellsPerRowOffsetFilter();
  /**
   *
   *
   * <pre>
   * Skips the first N cells of each row, matching all subsequent cells.
   * If duplicate cells are present, as is possible when using an Interleave,
   * each copy of the cell is counted separately.
   * </pre>
   *
   * <code>int32 cells_per_row_offset_filter = 10;</code>
   *
   * @return The cellsPerRowOffsetFilter.
   */
  int getCellsPerRowOffsetFilter();

  /**
   *
   *
   * <pre>
   * Matches only the first N cells of each row.
   * If duplicate cells are present, as is possible when using an Interleave,
   * each copy of the cell is counted separately.
   * </pre>
   *
   * <code>int32 cells_per_row_limit_filter = 11;</code>
   *
   * @return Whether the cellsPerRowLimitFilter field is set.
   */
  boolean hasCellsPerRowLimitFilter();
  /**
   *
   *
   * <pre>
   * Matches only the first N cells of each row.
   * If duplicate cells are present, as is possible when using an Interleave,
   * each copy of the cell is counted separately.
   * </pre>
   *
   * <code>int32 cells_per_row_limit_filter = 11;</code>
   *
   * @return The cellsPerRowLimitFilter.
   */
  int getCellsPerRowLimitFilter();

  /**
   *
   *
   * <pre>
   * Matches only the most recent N cells within each column. For example,
   * if N=2, this filter would match column `foo:bar` at timestamps 10 and 9,
   * skip all earlier cells in `foo:bar`, and then begin matching again in
   * column `foo:bar2`.
   * If duplicate cells are present, as is possible when using an Interleave,
   * each copy of the cell is counted separately.
   * </pre>
   *
   * <code>int32 cells_per_column_limit_filter = 12;</code>
   *
   * @return Whether the cellsPerColumnLimitFilter field is set.
   */
  boolean hasCellsPerColumnLimitFilter();
  /**
   *
   *
   * <pre>
   * Matches only the most recent N cells within each column. For example,
   * if N=2, this filter would match column `foo:bar` at timestamps 10 and 9,
   * skip all earlier cells in `foo:bar`, and then begin matching again in
   * column `foo:bar2`.
   * If duplicate cells are present, as is possible when using an Interleave,
   * each copy of the cell is counted separately.
   * </pre>
   *
   * <code>int32 cells_per_column_limit_filter = 12;</code>
   *
   * @return The cellsPerColumnLimitFilter.
   */
  int getCellsPerColumnLimitFilter();

  /**
   *
   *
   * <pre>
   * Replaces each cell's value with the empty string.
   * </pre>
   *
   * <code>bool strip_value_transformer = 13;</code>
   *
   * @return Whether the stripValueTransformer field is set.
   */
  boolean hasStripValueTransformer();
  /**
   *
   *
   * <pre>
   * Replaces each cell's value with the empty string.
   * </pre>
   *
   * <code>bool strip_value_transformer = 13;</code>
   *
   * @return The stripValueTransformer.
   */
  boolean getStripValueTransformer();

  /**
   *
   *
   * <pre>
   * Applies the given label to all cells in the output row. This allows
   * the client to determine which results were produced from which part of
   * the filter.
   * Values must be at most 15 characters in length, and match the RE2
   * pattern `[a-z0-9&#92;&#92;-]+`
   * Due to a technical limitation, it is not currently possible to apply
   * multiple labels to a cell. As a result, a Chain may have no more than
   * one sub-filter which contains a `apply_label_transformer`. It is okay for
   * an Interleave to contain multiple `apply_label_transformers`, as they
   * will be applied to separate copies of the input. This may be relaxed in
   * the future.
   * </pre>
   *
   * <code>string apply_label_transformer = 19;</code>
   *
   * @return Whether the applyLabelTransformer field is set.
   */
  boolean hasApplyLabelTransformer();
  /**
   *
   *
   * <pre>
   * Applies the given label to all cells in the output row. This allows
   * the client to determine which results were produced from which part of
   * the filter.
   * Values must be at most 15 characters in length, and match the RE2
   * pattern `[a-z0-9&#92;&#92;-]+`
   * Due to a technical limitation, it is not currently possible to apply
   * multiple labels to a cell. As a result, a Chain may have no more than
   * one sub-filter which contains a `apply_label_transformer`. It is okay for
   * an Interleave to contain multiple `apply_label_transformers`, as they
   * will be applied to separate copies of the input. This may be relaxed in
   * the future.
   * </pre>
   *
   * <code>string apply_label_transformer = 19;</code>
   *
   * @return The applyLabelTransformer.
   */
  java.lang.String getApplyLabelTransformer();
  /**
   *
   *
   * <pre>
   * Applies the given label to all cells in the output row. This allows
   * the client to determine which results were produced from which part of
   * the filter.
   * Values must be at most 15 characters in length, and match the RE2
   * pattern `[a-z0-9&#92;&#92;-]+`
   * Due to a technical limitation, it is not currently possible to apply
   * multiple labels to a cell. As a result, a Chain may have no more than
   * one sub-filter which contains a `apply_label_transformer`. It is okay for
   * an Interleave to contain multiple `apply_label_transformers`, as they
   * will be applied to separate copies of the input. This may be relaxed in
   * the future.
   * </pre>
   *
   * <code>string apply_label_transformer = 19;</code>
   *
   * @return The bytes for applyLabelTransformer.
   */
  com.google.protobuf.ByteString getApplyLabelTransformerBytes();

  public com.google.bigtable.v2.RowFilter.FilterCase getFilterCase();
}
