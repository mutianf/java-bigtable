/*
 * Copyright 2020 Google LLC
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

package com.google.bigtable.admin.v2;

public final class BigtableTableAdminProto {
  private BigtableTableAdminProto() {}

  public static void registerAllExtensions(com.google.protobuf.ExtensionRegistryLite registry) {}

  public static void registerAllExtensions(com.google.protobuf.ExtensionRegistry registry) {
    registerAllExtensions((com.google.protobuf.ExtensionRegistryLite) registry);
  }

  static final com.google.protobuf.Descriptors.Descriptor
      internal_static_google_bigtable_admin_v2_CreateTableRequest_descriptor;
  static final com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_google_bigtable_admin_v2_CreateTableRequest_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
      internal_static_google_bigtable_admin_v2_CreateTableRequest_Split_descriptor;
  static final com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_google_bigtable_admin_v2_CreateTableRequest_Split_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
      internal_static_google_bigtable_admin_v2_CreateTableFromSnapshotRequest_descriptor;
  static final com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_google_bigtable_admin_v2_CreateTableFromSnapshotRequest_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
      internal_static_google_bigtable_admin_v2_DropRowRangeRequest_descriptor;
  static final com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_google_bigtable_admin_v2_DropRowRangeRequest_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
      internal_static_google_bigtable_admin_v2_ListTablesRequest_descriptor;
  static final com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_google_bigtable_admin_v2_ListTablesRequest_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
      internal_static_google_bigtable_admin_v2_ListTablesResponse_descriptor;
  static final com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_google_bigtable_admin_v2_ListTablesResponse_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
      internal_static_google_bigtable_admin_v2_GetTableRequest_descriptor;
  static final com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_google_bigtable_admin_v2_GetTableRequest_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
      internal_static_google_bigtable_admin_v2_DeleteTableRequest_descriptor;
  static final com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_google_bigtable_admin_v2_DeleteTableRequest_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
      internal_static_google_bigtable_admin_v2_ModifyColumnFamiliesRequest_descriptor;
  static final com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_google_bigtable_admin_v2_ModifyColumnFamiliesRequest_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
      internal_static_google_bigtable_admin_v2_ModifyColumnFamiliesRequest_Modification_descriptor;
  static final com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_google_bigtable_admin_v2_ModifyColumnFamiliesRequest_Modification_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
      internal_static_google_bigtable_admin_v2_GenerateConsistencyTokenRequest_descriptor;
  static final com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_google_bigtable_admin_v2_GenerateConsistencyTokenRequest_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
      internal_static_google_bigtable_admin_v2_GenerateConsistencyTokenResponse_descriptor;
  static final com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_google_bigtable_admin_v2_GenerateConsistencyTokenResponse_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
      internal_static_google_bigtable_admin_v2_CheckConsistencyRequest_descriptor;
  static final com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_google_bigtable_admin_v2_CheckConsistencyRequest_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
      internal_static_google_bigtable_admin_v2_CheckConsistencyResponse_descriptor;
  static final com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_google_bigtable_admin_v2_CheckConsistencyResponse_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
      internal_static_google_bigtable_admin_v2_SnapshotTableRequest_descriptor;
  static final com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_google_bigtable_admin_v2_SnapshotTableRequest_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
      internal_static_google_bigtable_admin_v2_GetSnapshotRequest_descriptor;
  static final com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_google_bigtable_admin_v2_GetSnapshotRequest_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
      internal_static_google_bigtable_admin_v2_ListSnapshotsRequest_descriptor;
  static final com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_google_bigtable_admin_v2_ListSnapshotsRequest_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
      internal_static_google_bigtable_admin_v2_ListSnapshotsResponse_descriptor;
  static final com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_google_bigtable_admin_v2_ListSnapshotsResponse_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
      internal_static_google_bigtable_admin_v2_DeleteSnapshotRequest_descriptor;
  static final com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_google_bigtable_admin_v2_DeleteSnapshotRequest_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
      internal_static_google_bigtable_admin_v2_SnapshotTableMetadata_descriptor;
  static final com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_google_bigtable_admin_v2_SnapshotTableMetadata_fieldAccessorTable;
  static final com.google.protobuf.Descriptors.Descriptor
      internal_static_google_bigtable_admin_v2_CreateTableFromSnapshotMetadata_descriptor;
  static final com.google.protobuf.GeneratedMessageV3.FieldAccessorTable
      internal_static_google_bigtable_admin_v2_CreateTableFromSnapshotMetadata_fieldAccessorTable;

  public static com.google.protobuf.Descriptors.FileDescriptor getDescriptor() {
    return descriptor;
  }

  private static com.google.protobuf.Descriptors.FileDescriptor descriptor;

  static {
    java.lang.String[] descriptorData = {
      "\n3google/bigtable/admin/v2/bigtable_tabl"
          + "e_admin.proto\022\030google.bigtable.admin.v2\032"
          + "\034google/api/annotations.proto\032\027google/ap"
          + "i/client.proto\032\037google/api/field_behavio"
          + "r.proto\032\031google/api/resource.proto\032$goog"
          + "le/bigtable/admin/v2/table.proto\032\036google"
          + "/iam/v1/iam_policy.proto\032\032google/iam/v1/"
          + "policy.proto\032#google/longrunning/operati"
          + "ons.proto\032\036google/protobuf/duration.prot"
          + "o\032\033google/protobuf/empty.proto\032\037google/p"
          + "rotobuf/timestamp.proto\"\374\001\n\022CreateTableR"
          + "equest\0228\n\006parent\030\001 \001(\tB(\340A\002\372A\"\n bigtable"
          + ".googleapis.com/Instance\022\025\n\010table_id\030\002 \001"
          + "(\tB\003\340A\002\0223\n\005table\030\003 \001(\0132\037.google.bigtable"
          + ".admin.v2.TableB\003\340A\002\022J\n\016initial_splits\030\004"
          + " \003(\01322.google.bigtable.admin.v2.CreateTa"
          + "bleRequest.Split\032\024\n\005Split\022\013\n\003key\030\001 \001(\014\"\264"
          + "\001\n\036CreateTableFromSnapshotRequest\0228\n\006par"
          + "ent\030\001 \001(\tB(\340A\002\372A\"\n bigtable.googleapis.c"
          + "om/Instance\022\025\n\010table_id\030\002 \001(\tB\003\340A\002\022A\n\017so"
          + "urce_snapshot\030\003 \001(\tB(\340A\002\372A\"\n bigtable.go"
          + "ogleapis.com/Snapshot\"\224\001\n\023DropRowRangeRe"
          + "quest\0223\n\004name\030\001 \001(\tB%\340A\002\372A\037\n\035bigtable.go"
          + "ogleapis.com/Table\022\030\n\016row_key_prefix\030\002 \001"
          + "(\014H\000\022$\n\032delete_all_data_from_table\030\003 \001(\010"
          + "H\000B\010\n\006target\"\250\001\n\021ListTablesRequest\0228\n\006pa"
          + "rent\030\001 \001(\tB(\340A\002\372A\"\n bigtable.googleapis."
          + "com/Instance\0222\n\004view\030\002 \001(\0162$.google.bigt"
          + "able.admin.v2.Table.View\022\021\n\tpage_size\030\004 "
          + "\001(\005\022\022\n\npage_token\030\003 \001(\t\"^\n\022ListTablesRes"
          + "ponse\022/\n\006tables\030\001 \003(\0132\037.google.bigtable."
          + "admin.v2.Table\022\027\n\017next_page_token\030\002 \001(\t\""
          + "z\n\017GetTableRequest\0223\n\004name\030\001 \001(\tB%\340A\002\372A\037"
          + "\n\035bigtable.googleapis.com/Table\0222\n\004view\030"
          + "\002 \001(\0162$.google.bigtable.admin.v2.Table.V"
          + "iew\"I\n\022DeleteTableRequest\0223\n\004name\030\001 \001(\tB"
          + "%\340A\002\372A\037\n\035bigtable.googleapis.com/Table\"\332"
          + "\002\n\033ModifyColumnFamiliesRequest\0223\n\004name\030\001"
          + " \001(\tB%\340A\002\372A\037\n\035bigtable.googleapis.com/Ta"
          + "ble\022^\n\rmodifications\030\002 \003(\0132B.google.bigt"
          + "able.admin.v2.ModifyColumnFamiliesReques"
          + "t.ModificationB\003\340A\002\032\245\001\n\014Modification\022\n\n\002"
          + "id\030\001 \001(\t\0228\n\006create\030\002 \001(\0132&.google.bigtab"
          + "le.admin.v2.ColumnFamilyH\000\0228\n\006update\030\003 \001"
          + "(\0132&.google.bigtable.admin.v2.ColumnFami"
          + "lyH\000\022\016\n\004drop\030\004 \001(\010H\000B\005\n\003mod\"V\n\037GenerateC"
          + "onsistencyTokenRequest\0223\n\004name\030\001 \001(\tB%\340A"
          + "\002\372A\037\n\035bigtable.googleapis.com/Table\"=\n G"
          + "enerateConsistencyTokenResponse\022\031\n\021consi"
          + "stency_token\030\001 \001(\t\"n\n\027CheckConsistencyRe"
          + "quest\0223\n\004name\030\001 \001(\tB%\340A\002\372A\037\n\035bigtable.go"
          + "ogleapis.com/Table\022\036\n\021consistency_token\030"
          + "\002 \001(\tB\003\340A\002\".\n\030CheckConsistencyResponse\022\022"
          + "\n\nconsistent\030\001 \001(\010\"\207\001\n\024SnapshotTableRequ"
          + "est\022\014\n\004name\030\001 \001(\t\022\017\n\007cluster\030\002 \001(\t\022\023\n\013sn"
          + "apshot_id\030\003 \001(\t\022&\n\003ttl\030\004 \001(\0132\031.google.pr"
          + "otobuf.Duration\022\023\n\013description\030\005 \001(\t\"L\n\022"
          + "GetSnapshotRequest\0226\n\004name\030\001 \001(\tB(\340A\002\372A\""
          + "\n bigtable.googleapis.com/Snapshot\"v\n\024Li"
          + "stSnapshotsRequest\0227\n\006parent\030\001 \001(\tB\'\340A\002\372"
          + "A!\n\037bigtable.googleapis.com/Cluster\022\021\n\tp"
          + "age_size\030\002 \001(\005\022\022\n\npage_token\030\003 \001(\t\"g\n\025Li"
          + "stSnapshotsResponse\0225\n\tsnapshots\030\001 \003(\0132\""
          + ".google.bigtable.admin.v2.Snapshot\022\027\n\017ne"
          + "xt_page_token\030\002 \001(\t\"O\n\025DeleteSnapshotReq"
          + "uest\0226\n\004name\030\001 \001(\tB(\340A\002\372A\"\n bigtable.goo"
          + "gleapis.com/Snapshot\"\304\001\n\025SnapshotTableMe"
          + "tadata\022H\n\020original_request\030\001 \001(\0132..googl"
          + "e.bigtable.admin.v2.SnapshotTableRequest"
          + "\0220\n\014request_time\030\002 \001(\0132\032.google.protobuf"
          + ".Timestamp\022/\n\013finish_time\030\003 \001(\0132\032.google"
          + ".protobuf.Timestamp\"\330\001\n\037CreateTableFromS"
          + "napshotMetadata\022R\n\020original_request\030\001 \001("
          + "\01328.google.bigtable.admin.v2.CreateTable"
          + "FromSnapshotRequest\0220\n\014request_time\030\002 \001("
          + "\0132\032.google.protobuf.Timestamp\022/\n\013finish_"
          + "time\030\003 \001(\0132\032.google.protobuf.Timestamp2\351"
          + "\033\n\022BigtableTableAdmin\022\253\001\n\013CreateTable\022,."
          + "google.bigtable.admin.v2.CreateTableRequ"
          + "est\032\037.google.bigtable.admin.v2.Table\"M\202\323"
          + "\344\223\002/\"*/v2/{parent=projects/*/instances/*"
          + "}/tables:\001*\332A\025parent,table_id,table\022\212\002\n\027"
          + "CreateTableFromSnapshot\0228.google.bigtabl"
          + "e.admin.v2.CreateTableFromSnapshotReques"
          + "t\032\035.google.longrunning.Operation\"\225\001\202\323\344\223\002"
          + "B\"=/v2/{parent=projects/*/instances/*}/t"
          + "ables:createFromSnapshot:\001*\332A\037parent,tab"
          + "le_id,source_snapshot\312A(\n\005Table\022\037CreateT"
          + "ableFromSnapshotMetadata\022\244\001\n\nListTables\022"
          + "+.google.bigtable.admin.v2.ListTablesReq"
          + "uest\032,.google.bigtable.admin.v2.ListTabl"
          + "esResponse\";\202\323\344\223\002,\022*/v2/{parent=projects"
          + "/*/instances/*}/tables\332A\006parent\022\221\001\n\010GetT"
          + "able\022).google.bigtable.admin.v2.GetTable"
          + "Request\032\037.google.bigtable.admin.v2.Table"
          + "\"9\202\323\344\223\002,\022*/v2/{name=projects/*/instances"
          + "/*/tables/*}\332A\004name\022\216\001\n\013DeleteTable\022,.go"
          + "ogle.bigtable.admin.v2.DeleteTableReques"
          + "t\032\026.google.protobuf.Empty\"9\202\323\344\223\002,**/v2/{"
          + "name=projects/*/instances/*/tables/*}\332A\004"
          + "name\022\317\001\n\024ModifyColumnFamilies\0225.google.b"
          + "igtable.admin.v2.ModifyColumnFamiliesReq"
          + "uest\032\037.google.bigtable.admin.v2.Table\"_\202"
          + "\323\344\223\002D\"?/v2/{name=projects/*/instances/*/"
          + "tables/*}:modifyColumnFamilies:\001*\332A\022name"
          + ",modifications\022\231\001\n\014DropRowRange\022-.google"
          + ".bigtable.admin.v2.DropRowRangeRequest\032\026"
          + ".google.protobuf.Empty\"B\202\323\344\223\002<\"7/v2/{nam"
          + "e=projects/*/instances/*/tables/*}:dropR"
          + "owRange:\001*\022\350\001\n\030GenerateConsistencyToken\022"
          + "9.google.bigtable.admin.v2.GenerateConsi"
          + "stencyTokenRequest\032:.google.bigtable.adm"
          + "in.v2.GenerateConsistencyTokenResponse\"U"
          + "\202\323\344\223\002H\"C/v2/{name=projects/*/instances/*"
          + "/tables/*}:generateConsistencyToken:\001*\332A"
          + "\004name\022\332\001\n\020CheckConsistency\0221.google.bigt"
          + "able.admin.v2.CheckConsistencyRequest\0322."
          + "google.bigtable.admin.v2.CheckConsistenc"
          + "yResponse\"_\202\323\344\223\002@\";/v2/{name=projects/*/"
          + "instances/*/tables/*}:checkConsistency:\001"
          + "*\332A\026name,consistency_token\022\352\001\n\rSnapshotT"
          + "able\022..google.bigtable.admin.v2.Snapshot"
          + "TableRequest\032\035.google.longrunning.Operat"
          + "ion\"\211\001\202\323\344\223\0028\"3/v2/{name=projects/*/insta"
          + "nces/*/tables/*}:snapshot:\001*\332A$name,clus"
          + "ter,snapshot_id,description\312A!\n\010Snapshot"
          + "\022\025SnapshotTableMetadata\022\250\001\n\013GetSnapshot\022"
          + ",.google.bigtable.admin.v2.GetSnapshotRe"
          + "quest\032\".google.bigtable.admin.v2.Snapsho"
          + "t\"G\202\323\344\223\002:\0228/v2/{name=projects/*/instance"
          + "s/*/clusters/*/snapshots/*}\332A\004name\022\273\001\n\rL"
          + "istSnapshots\022..google.bigtable.admin.v2."
          + "ListSnapshotsRequest\032/.google.bigtable.a"
          + "dmin.v2.ListSnapshotsResponse\"I\202\323\344\223\002:\0228/"
          + "v2/{parent=projects/*/instances/*/cluste"
          + "rs/*}/snapshots\332A\006parent\022\242\001\n\016DeleteSnaps"
          + "hot\022/.google.bigtable.admin.v2.DeleteSna"
          + "pshotRequest\032\026.google.protobuf.Empty\"G\202\323"
          + "\344\223\002:*8/v2/{name=projects/*/instances/*/c"
          + "lusters/*/snapshots/*}\332A\004name\022\234\001\n\014GetIam"
          + "Policy\022\".google.iam.v1.GetIamPolicyReque"
          + "st\032\025.google.iam.v1.Policy\"Q\202\323\344\223\002@\";/v2/{"
          + "resource=projects/*/instances/*/tables/*"
          + "}:getIamPolicy:\001*\332A\010resource\022\363\001\n\014SetIamP"
          + "olicy\022\".google.iam.v1.SetIamPolicyReques"
          + "t\032\025.google.iam.v1.Policy\"\247\001\202\323\344\223\002\216\001\";/v2/"
          + "{resource=projects/*/instances/*/tables/"
          + "*}:setIamPolicy:\001*ZL\"G/v2/{resource=proj"
          + "ects/*/instances/*/clusters/*/backups/*}"
          + ":setIamPolicy:\001*\332A\017resource,policy\022\244\002\n\022T"
          + "estIamPermissions\022(.google.iam.v1.TestIa"
          + "mPermissionsRequest\032).google.iam.v1.Test"
          + "IamPermissionsResponse\"\270\001\202\323\344\223\002\232\001\"A/v2/{r"
          + "esource=projects/*/instances/*/tables/*}"
          + ":testIamPermissions:\001*ZR\"M/v2/{resource="
          + "projects/*/instances/*/clusters/*/backup"
          + "s/*}:testIamPermissions:\001*\332A\024resource,pe"
          + "rmissions\032\336\002\312A\034bigtableadmin.googleapis."
          + "com\322A\273\002https://www.googleapis.com/auth/b"
          + "igtable.admin,https://www.googleapis.com"
          + "/auth/bigtable.admin.table,https://www.g"
          + "oogleapis.com/auth/cloud-bigtable.admin,"
          + "https://www.googleapis.com/auth/cloud-bi"
          + "gtable.admin.table,https://www.googleapi"
          + "s.com/auth/cloud-platform,https://www.go"
          + "ogleapis.com/auth/cloud-platform.read-on"
          + "lyB\272\001\n\034com.google.bigtable.admin.v2B\027Big"
          + "tableTableAdminProtoP\001Z=google.golang.or"
          + "g/genproto/googleapis/bigtable/admin/v2;"
          + "admin\252\002\036Google.Cloud.Bigtable.Admin.V2\312\002"
          + "\036Google\\Cloud\\Bigtable\\Admin\\V2b\006proto3"
    };
    descriptor =
        com.google.protobuf.Descriptors.FileDescriptor.internalBuildGeneratedFileFrom(
            descriptorData,
            new com.google.protobuf.Descriptors.FileDescriptor[] {
              com.google.api.AnnotationsProto.getDescriptor(),
              com.google.api.ClientProto.getDescriptor(),
              com.google.api.FieldBehaviorProto.getDescriptor(),
              com.google.api.ResourceProto.getDescriptor(),
              com.google.bigtable.admin.v2.TableProto.getDescriptor(),
              com.google.iam.v1.IamPolicyProto.getDescriptor(),
              com.google.iam.v1.PolicyProto.getDescriptor(),
              com.google.longrunning.OperationsProto.getDescriptor(),
              com.google.protobuf.DurationProto.getDescriptor(),
              com.google.protobuf.EmptyProto.getDescriptor(),
              com.google.protobuf.TimestampProto.getDescriptor(),
            });
    internal_static_google_bigtable_admin_v2_CreateTableRequest_descriptor =
        getDescriptor().getMessageTypes().get(0);
    internal_static_google_bigtable_admin_v2_CreateTableRequest_fieldAccessorTable =
        new com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
            internal_static_google_bigtable_admin_v2_CreateTableRequest_descriptor,
            new java.lang.String[] {
              "Parent", "TableId", "Table", "InitialSplits",
            });
    internal_static_google_bigtable_admin_v2_CreateTableRequest_Split_descriptor =
        internal_static_google_bigtable_admin_v2_CreateTableRequest_descriptor
            .getNestedTypes()
            .get(0);
    internal_static_google_bigtable_admin_v2_CreateTableRequest_Split_fieldAccessorTable =
        new com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
            internal_static_google_bigtable_admin_v2_CreateTableRequest_Split_descriptor,
            new java.lang.String[] {
              "Key",
            });
    internal_static_google_bigtable_admin_v2_CreateTableFromSnapshotRequest_descriptor =
        getDescriptor().getMessageTypes().get(1);
    internal_static_google_bigtable_admin_v2_CreateTableFromSnapshotRequest_fieldAccessorTable =
        new com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
            internal_static_google_bigtable_admin_v2_CreateTableFromSnapshotRequest_descriptor,
            new java.lang.String[] {
              "Parent", "TableId", "SourceSnapshot",
            });
    internal_static_google_bigtable_admin_v2_DropRowRangeRequest_descriptor =
        getDescriptor().getMessageTypes().get(2);
    internal_static_google_bigtable_admin_v2_DropRowRangeRequest_fieldAccessorTable =
        new com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
            internal_static_google_bigtable_admin_v2_DropRowRangeRequest_descriptor,
            new java.lang.String[] {
              "Name", "RowKeyPrefix", "DeleteAllDataFromTable", "Target",
            });
    internal_static_google_bigtable_admin_v2_ListTablesRequest_descriptor =
        getDescriptor().getMessageTypes().get(3);
    internal_static_google_bigtable_admin_v2_ListTablesRequest_fieldAccessorTable =
        new com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
            internal_static_google_bigtable_admin_v2_ListTablesRequest_descriptor,
            new java.lang.String[] {
              "Parent", "View", "PageSize", "PageToken",
            });
    internal_static_google_bigtable_admin_v2_ListTablesResponse_descriptor =
        getDescriptor().getMessageTypes().get(4);
    internal_static_google_bigtable_admin_v2_ListTablesResponse_fieldAccessorTable =
        new com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
            internal_static_google_bigtable_admin_v2_ListTablesResponse_descriptor,
            new java.lang.String[] {
              "Tables", "NextPageToken",
            });
    internal_static_google_bigtable_admin_v2_GetTableRequest_descriptor =
        getDescriptor().getMessageTypes().get(5);
    internal_static_google_bigtable_admin_v2_GetTableRequest_fieldAccessorTable =
        new com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
            internal_static_google_bigtable_admin_v2_GetTableRequest_descriptor,
            new java.lang.String[] {
              "Name", "View",
            });
    internal_static_google_bigtable_admin_v2_DeleteTableRequest_descriptor =
        getDescriptor().getMessageTypes().get(6);
    internal_static_google_bigtable_admin_v2_DeleteTableRequest_fieldAccessorTable =
        new com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
            internal_static_google_bigtable_admin_v2_DeleteTableRequest_descriptor,
            new java.lang.String[] {
              "Name",
            });
    internal_static_google_bigtable_admin_v2_ModifyColumnFamiliesRequest_descriptor =
        getDescriptor().getMessageTypes().get(7);
    internal_static_google_bigtable_admin_v2_ModifyColumnFamiliesRequest_fieldAccessorTable =
        new com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
            internal_static_google_bigtable_admin_v2_ModifyColumnFamiliesRequest_descriptor,
            new java.lang.String[] {
              "Name", "Modifications",
            });
    internal_static_google_bigtable_admin_v2_ModifyColumnFamiliesRequest_Modification_descriptor =
        internal_static_google_bigtable_admin_v2_ModifyColumnFamiliesRequest_descriptor
            .getNestedTypes()
            .get(0);
    internal_static_google_bigtable_admin_v2_ModifyColumnFamiliesRequest_Modification_fieldAccessorTable =
        new com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
            internal_static_google_bigtable_admin_v2_ModifyColumnFamiliesRequest_Modification_descriptor,
            new java.lang.String[] {
              "Id", "Create", "Update", "Drop", "Mod",
            });
    internal_static_google_bigtable_admin_v2_GenerateConsistencyTokenRequest_descriptor =
        getDescriptor().getMessageTypes().get(8);
    internal_static_google_bigtable_admin_v2_GenerateConsistencyTokenRequest_fieldAccessorTable =
        new com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
            internal_static_google_bigtable_admin_v2_GenerateConsistencyTokenRequest_descriptor,
            new java.lang.String[] {
              "Name",
            });
    internal_static_google_bigtable_admin_v2_GenerateConsistencyTokenResponse_descriptor =
        getDescriptor().getMessageTypes().get(9);
    internal_static_google_bigtable_admin_v2_GenerateConsistencyTokenResponse_fieldAccessorTable =
        new com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
            internal_static_google_bigtable_admin_v2_GenerateConsistencyTokenResponse_descriptor,
            new java.lang.String[] {
              "ConsistencyToken",
            });
    internal_static_google_bigtable_admin_v2_CheckConsistencyRequest_descriptor =
        getDescriptor().getMessageTypes().get(10);
    internal_static_google_bigtable_admin_v2_CheckConsistencyRequest_fieldAccessorTable =
        new com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
            internal_static_google_bigtable_admin_v2_CheckConsistencyRequest_descriptor,
            new java.lang.String[] {
              "Name", "ConsistencyToken",
            });
    internal_static_google_bigtable_admin_v2_CheckConsistencyResponse_descriptor =
        getDescriptor().getMessageTypes().get(11);
    internal_static_google_bigtable_admin_v2_CheckConsistencyResponse_fieldAccessorTable =
        new com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
            internal_static_google_bigtable_admin_v2_CheckConsistencyResponse_descriptor,
            new java.lang.String[] {
              "Consistent",
            });
    internal_static_google_bigtable_admin_v2_SnapshotTableRequest_descriptor =
        getDescriptor().getMessageTypes().get(12);
    internal_static_google_bigtable_admin_v2_SnapshotTableRequest_fieldAccessorTable =
        new com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
            internal_static_google_bigtable_admin_v2_SnapshotTableRequest_descriptor,
            new java.lang.String[] {
              "Name", "Cluster", "SnapshotId", "Ttl", "Description",
            });
    internal_static_google_bigtable_admin_v2_GetSnapshotRequest_descriptor =
        getDescriptor().getMessageTypes().get(13);
    internal_static_google_bigtable_admin_v2_GetSnapshotRequest_fieldAccessorTable =
        new com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
            internal_static_google_bigtable_admin_v2_GetSnapshotRequest_descriptor,
            new java.lang.String[] {
              "Name",
            });
    internal_static_google_bigtable_admin_v2_ListSnapshotsRequest_descriptor =
        getDescriptor().getMessageTypes().get(14);
    internal_static_google_bigtable_admin_v2_ListSnapshotsRequest_fieldAccessorTable =
        new com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
            internal_static_google_bigtable_admin_v2_ListSnapshotsRequest_descriptor,
            new java.lang.String[] {
              "Parent", "PageSize", "PageToken",
            });
    internal_static_google_bigtable_admin_v2_ListSnapshotsResponse_descriptor =
        getDescriptor().getMessageTypes().get(15);
    internal_static_google_bigtable_admin_v2_ListSnapshotsResponse_fieldAccessorTable =
        new com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
            internal_static_google_bigtable_admin_v2_ListSnapshotsResponse_descriptor,
            new java.lang.String[] {
              "Snapshots", "NextPageToken",
            });
    internal_static_google_bigtable_admin_v2_DeleteSnapshotRequest_descriptor =
        getDescriptor().getMessageTypes().get(16);
    internal_static_google_bigtable_admin_v2_DeleteSnapshotRequest_fieldAccessorTable =
        new com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
            internal_static_google_bigtable_admin_v2_DeleteSnapshotRequest_descriptor,
            new java.lang.String[] {
              "Name",
            });
    internal_static_google_bigtable_admin_v2_SnapshotTableMetadata_descriptor =
        getDescriptor().getMessageTypes().get(17);
    internal_static_google_bigtable_admin_v2_SnapshotTableMetadata_fieldAccessorTable =
        new com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
            internal_static_google_bigtable_admin_v2_SnapshotTableMetadata_descriptor,
            new java.lang.String[] {
              "OriginalRequest", "RequestTime", "FinishTime",
            });
    internal_static_google_bigtable_admin_v2_CreateTableFromSnapshotMetadata_descriptor =
        getDescriptor().getMessageTypes().get(18);
    internal_static_google_bigtable_admin_v2_CreateTableFromSnapshotMetadata_fieldAccessorTable =
        new com.google.protobuf.GeneratedMessageV3.FieldAccessorTable(
            internal_static_google_bigtable_admin_v2_CreateTableFromSnapshotMetadata_descriptor,
            new java.lang.String[] {
              "OriginalRequest", "RequestTime", "FinishTime",
            });
    com.google.protobuf.ExtensionRegistry registry =
        com.google.protobuf.ExtensionRegistry.newInstance();
    registry.add(com.google.api.ClientProto.defaultHost);
    registry.add(com.google.api.FieldBehaviorProto.fieldBehavior);
    registry.add(com.google.api.AnnotationsProto.http);
    registry.add(com.google.api.ClientProto.methodSignature);
    registry.add(com.google.api.ClientProto.oauthScopes);
    registry.add(com.google.api.ResourceProto.resourceReference);
    registry.add(com.google.longrunning.OperationsProto.operationInfo);
    com.google.protobuf.Descriptors.FileDescriptor.internalUpdateFileDescriptor(
        descriptor, registry);
    com.google.api.AnnotationsProto.getDescriptor();
    com.google.api.ClientProto.getDescriptor();
    com.google.api.FieldBehaviorProto.getDescriptor();
    com.google.api.ResourceProto.getDescriptor();
    com.google.bigtable.admin.v2.TableProto.getDescriptor();
    com.google.iam.v1.IamPolicyProto.getDescriptor();
    com.google.iam.v1.PolicyProto.getDescriptor();
    com.google.longrunning.OperationsProto.getDescriptor();
    com.google.protobuf.DurationProto.getDescriptor();
    com.google.protobuf.EmptyProto.getDescriptor();
    com.google.protobuf.TimestampProto.getDescriptor();
  }

  // @@protoc_insertion_point(outer_class_scope)
}
