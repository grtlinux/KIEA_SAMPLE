/*

Derby - Class org.apache.derbyDemo.vtis.example.WorldDBSnapshot

Licensed to the Apache Software Foundation (ASF) under one or more
contributor license agreements.  See the NOTICE file distributed with
this work for additional information regarding copyright ownership.
The ASF licenses this file to You under the Apache License, Version 2.0
(the "License"); you may not use this file except in compliance with
the License.  You may obtain a copy of the License at

http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.

*/

package kiea.priv.zzz.book.jdk_7u21.t01.demo.db.programs.vtis.java.org.apache.derbyDemo.vtis.example;

import java.sql.ResultSet;
import java.sql.SQLException;

import kiea.priv.zzz.book.jdk_7u21.t01.demo.db.programs.vtis.java.org.apache.derbyDemo.vtis.snapshot.SnapshotQuery;
import kiea.priv.zzz.book.jdk_7u21.t01.demo.db.programs.vtis.java.org.apache.derbyDemo.vtis.snapshot.Subscription;
import kiea.priv.zzz.book.jdk_7u21.t01.demo.db.programs.vtis.java.org.apache.derbyDemo.vtis.snapshot.SubscriptionSignature;

/**
 * <p>
 * This is a parameterized subscription to a slice of the world database managed
 * by a MySQL server.
 * </p>
 *
 */
@SubscriptionSignature
    (
     jdbcDriverName = "com.mysql.jdbc.Driver",
     parameters = { "populationMin", "populationMax" },
     refreshProcedureName = "refreshWorldDB"
     )
public  class   WorldDBSnapshot extends Subscription
{
    @SnapshotQuery
        (
         parameters = { "populationMin", "populationMax" },
         query = "select * from City where Population between ? and ?"
         )
    public  static  ResultSet   City() throws SQLException  { return instantiateSnapshotQueryVTI(); }

    @SnapshotQuery
        (
         parameters = { "populationMin", "populationMax" },
         query = "select * from Country where Code in ( select CountryCode from City where Population between ? and ? )"
         )
    public  static  ResultSet   Country() throws SQLException  { return instantiateSnapshotQueryVTI(); }

    @SnapshotQuery
        (
         parameters = { "populationMin", "populationMax" },
         query = "select * from CountryLanguage where CountryCode in ( select CountryCode from City where Population between ? and ? )"
         )
    public  static  ResultSet   CountryLanguage() throws SQLException  { return instantiateSnapshotQueryVTI(); }

}


