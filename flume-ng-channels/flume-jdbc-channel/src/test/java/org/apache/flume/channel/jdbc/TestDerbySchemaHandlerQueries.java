/**
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.apache.flume.channel.jdbc;

import org.apache.flume.channel.jdbc.impl.DerbySchemaHandler;
import org.junit.Assert;
import org.junit.Test;

public class TestDerbySchemaHandlerQueries {

  public static final String EXPECTED_QUERY_CREATE_SCHEMA_FLUME
       = "CREATE SCHEMA FLUME";

  public static final String EXPECTED_QUERY_CREATE_TABLE_FL_EVENT
       = "CREATE TABLE FLUME.FL_EVENT ( FLE_ID BIGINT GENERATED ALWAYS AS "
           + "IDENTITY (START WITH 1, INCREMENT BY 1) PRIMARY KEY, FLE_PAYLOAD "
           + "VARCHAR(16384) FOR BIT DATA, FLE_CHANNEL VARCHAR(64), "
           + "FLE_SPILL BOOLEAN)";

  public static final String EXPECTED_QUERY_CREATE_TABLE_FL_PLSPILL
       = "CREATE TABLE FLUME.FL_PLSPILL ( FLP_EVENT BIGINT, FLP_SPILL BLOB, "
           + "FOREIGN KEY (FLP_EVENT) REFERENCES FLUME.FL_EVENT (FLE_ID))";

  public static final String EXPECTED_QUERY_CREATE_TABLE_FL_HEADER
       = "CREATE TABLE FLUME.FL_HEADER ( FLH_ID BIGINT GENERATED ALWAYS AS "
           + "IDENTITY (START WITH 1, INCREMENT BY 1) PRIMARY KEY, "
           + "FLH_EVENT BIGINT, FLH_NAME VARCHAR(251), "
           + "FLH_VALUE VARCHAR(251), FLH_NMSPILL BOOLEAN, "
           + "FLH_VLSPILL BOOLEAN, FOREIGN KEY (FLH_EVENT) "
           + "REFERENCES FLUME.FL_EVENT (FLE_ID))";

  public static final String EXPECTED_QUERY_CREATE_TABLE_FL_NMSPILL
       = "CREATE TABLE FLUME.FL_NMSPILL ( FLN_HEADER BIGINT, FLN_SPILL "
           + "VARCHAR(32517), FOREIGN KEY (FLN_HEADER) REFERENCES "
           + "FLUME.FL_HEADER (FLH_ID))";

  public static final String EXPECTED_QUERY_CREATE_TABLE_FL_VLSPILL
       = "CREATE TABLE FLUME.FL_VLSPILL ( FLV_HEADER BIGINT, FLV_SPILL "
            + "VARCHAR(32517), FOREIGN KEY (FLV_HEADER) REFERENCES "
            + "FLUME.FL_HEADER (FLH_ID))";

  @Test
  public void testCreateQueries() {

    Assert.assertEquals(DerbySchemaHandler.QUERY_CREATE_SCHEMA_FLUME,
        EXPECTED_QUERY_CREATE_SCHEMA_FLUME);

    Assert.assertEquals(DerbySchemaHandler.QUERY_CREATE_TABLE_FL_EVENT,
        EXPECTED_QUERY_CREATE_TABLE_FL_EVENT);

    Assert.assertEquals(DerbySchemaHandler.QUERY_CREATE_TABLE_FL_PLSPILL,
        EXPECTED_QUERY_CREATE_TABLE_FL_PLSPILL);

    Assert.assertEquals(DerbySchemaHandler.QUERY_CREATE_TABLE_FL_HEADER,
        EXPECTED_QUERY_CREATE_TABLE_FL_HEADER);

    Assert.assertEquals(DerbySchemaHandler.QUERY_CREATE_TABLE_FL_NMSPILL,
        EXPECTED_QUERY_CREATE_TABLE_FL_NMSPILL);

    Assert.assertEquals(DerbySchemaHandler.QUERY_CREATE_TABLE_FL_VLSPILL,
        EXPECTED_QUERY_CREATE_TABLE_FL_VLSPILL);

  }

}
