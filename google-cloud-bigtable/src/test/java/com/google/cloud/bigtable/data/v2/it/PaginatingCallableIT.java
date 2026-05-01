/*
 * Copyright 2026 Google LLC
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
package com.google.cloud.bigtable.data.v2.it;

import static com.google.common.truth.Truth.assertThat;

import com.google.cloud.bigtable.data.v2.BigtableDataClient;
import com.google.cloud.bigtable.test_helpers.env.TestEnvRule;
import com.google.cloud.bigtable.data.v2.models.Query;
import com.google.cloud.bigtable.data.v2.models.Row;
import com.google.cloud.bigtable.data.v2.models.RowMutation;
import com.google.protobuf.ByteString;
import java.util.Iterator;
import org.junit.ClassRule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

@RunWith(JUnit4.class)
public class PaginatingCallableIT {
  @ClassRule public static final TestEnvRule testEnvRule = new TestEnvRule();

  @Test
  public void testPaginatingCallable() throws Exception {
    org.junit.Assume.assumeTrue(
        "Large row read errors are not supported by emulator",
        !(testEnvRule.env() instanceof com.google.cloud.bigtable.test_helpers.env.EmulatorEnv));

    BigtableDataClient client = testEnvRule.env().getDataClient();
    String tableId = testEnvRule.env().getTableId().getTableId();

    ByteString rowKey = ByteString.copyFromUtf8("paginating-large-row");

    String familyId = testEnvRule.env().getFamilyId();

    // 1. Create a large row (e.g. 30 cells of 10MB each)
    byte[] largeBytes = new byte[10 * 1024 * 1024];
    for (int i = 0; i < 30; i++) {
      client.mutateRow(
          com.google.cloud.bigtable.data.v2.models.RowMutation.create(tableId, rowKey)
              .setCell(familyId, ByteString.copyFromUtf8("col-" + i), ByteString.copyFrom(largeBytes)));
    }

    // 2. Read using the custom paginating callable!
    Query query = Query.create(tableId).rowKey(rowKey);

    System.out.println("Reading large row using paginatingReadRowsCallable...");
    Iterator<Row> it = client.paginatingReadRowsCallable().call(query).iterator();
    
    assertThat(it.hasNext()).isTrue();
    Row result = it.next();
    
    System.out.println("Successfully paginated row! Cell count: " + result.getCells().size());
    assertThat(result.getCells()).hasSize(30);
    assertThat(it.hasNext()).isFalse();
  }
}
