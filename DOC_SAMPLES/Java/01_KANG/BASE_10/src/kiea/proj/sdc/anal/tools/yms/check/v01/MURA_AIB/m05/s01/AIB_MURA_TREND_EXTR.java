/* Copyright (c) 2008-2014, KangSeok
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 * Redistributions of source code must retain the above copyright notice, this
 * list of conditions and the following disclaimer.
 *
 * Redistributions in binary form must reproduce the above copyright notice,
 * this list of conditions and the following disclaimer in the documentation
 * and/or other materials provided with the distribution.
 *
 * Neither the name of the HSQL Development Group nor the names of its
 * contributors may be used to endorse or promote products derived from this
 * software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL HSQL DEVELOPMENT GROUP, HSQLDB.ORG,
 * OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL,
 * EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO,
 * PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
 * LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 * ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

package kiea.proj.sdc.anal.tools.yms.check.v01.MURA_AIB.m05.s01;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;

import kiea.kr.co.tain.base.Flag;
import kiea.proj.sdc.anal.common.Connection;
import oracle.jdbc.OracleConnection;

/**
 * @author KangSeok
 * @date 2014. 8. 7.
 * @file_name AIB_MURA_TREND_EXTR.java
 *
 */
public class AIB_MURA_TREND_EXTR
{
	/*
	 * 
	 *     MURA_TREND
	 *
	 */
	
	///////////////////////////////////////////////////////////////////////////

	private static void line56LC()
	{
		if (Flag.TRUE) {
			try {
				String query = String.format("" +
						"/* MURA_TREND : L5FAB, L6FAB - LC */                               \n" +
						"SELECT /*+ PARALLEL(2) */                                          \n" +
						"       t1.procid                                                   \n" +
						"       , t1.siteid                                                 \n" +
						"       , t1.glassid AS cellid                                      \n" +
						"       , SUBSTR (t1.glassid, 1, 8) AS glassid                      \n" +
						"       , d.prodgrpname                                             \n" +
						"       , t1.prodid                                                 \n" +
						"       , orgstepid                                                 \n" +
						"       , measeqpunitid                                             \n" +
						"       , dcoldate as DCOLTIME                                      \n" +
						"       , itemid                                                    \n" +
						"       , subitemid                                                 \n" +
						"       , datavalue                                                 \n" +
						"       , code_x gateline                                           \n" +
						"       , code_y dataline                                           \n" +
						"       , code_x2 gateline2                                         \n" +
						"       , code_y2 dataline2                                         \n" +
						"       , t1.prodtype                                               \n" +
						"       , t1.measstepgrpid                                          \n" +
						"       , CASE                                                      \n" +
						"          WHEN SUBSTR (t1.glassid, 8, 1) BETWEEN 'A' AND 'Z'       \n" +
						"             THEN SUBSTR (t1.glassid, -2)                          \n" +
						"          ELSE SUBSTR (t1.glassid, -1)                             \n" +
						"       END cell_loc_id                                             \n" +
						"  FROM yms_dm.h_meas_rundata_cell t1, yms_dm.d_product d           \n" +
						" WHERE t1.dcoldate >= TO_DATE (%s, 'YYYYMMDDHH24MISS')             \n" +   // FROM_ETL_DT
						"   AND t1.dcoldate < TO_DATE (%s, 'YYYYMMDDHH24MISS')              \n" +   // TO_ETL_DT
						"   AND t1.siteid = %s                                              \n" +   // LINE
						"   AND t1.prodid = d.prodid                                        \n" +
						"   AND t1.orgstepid LIKE 'L__080'                                  \n" +
						"   AND t1.procid IN ( %s )                                         \n" +   // USER_GROUP_CODE
						""
						, "'20140805090000'", "'20140806085959'", "'L6FAB'", "'L6156H11'");

				OracleConnection conn = Connection.getOracleConnection();
				Statement stmt = conn.createStatement();
				
				if (Flag.TRUE) {
					ResultSet rs = stmt.executeQuery(query);
					ResultSetMetaData md = rs.getMetaData();

					for (int i=0; i < 30000 && rs.next(); i++) {
						
						StringBuffer sb = new StringBuffer();
						sb.append(String.format("(%4d)   ", i));
						
						for (int col=1; col <= md.getColumnCount(); col++) {
							sb.append("\t").append(rs.getString(col));
						}
						
						System.out.println(sb.toString());
					}
					
					System.out.println("------------- META DATA -----------------------");
					
					for (int i=1; i <= md.getColumnCount(); i++) {
						System.out.println(String.format("%d) [%s] [%s] [%s]", i, md.getColumnName(i), md.getColumnLabel(i), md.getColumnClassName(i)));
					}
				}
				
				conn.close();
				if (Flag.TRUE) System.out.println("##### OK!");
				if (!Flag.TRUE) System.out.println("[" + query + "]");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	private static void line56MOD()
	{
		if (Flag.TRUE) {
			try {
				String query = String.format("" +
						"/* MURA_TREND : L5FAB, L6FAB - MOD */                            \n" +
						"SELECT /*+ PARALLEL(2) */                                        \n" +
						"       d.procid                                                  \n" +
						"      , t1.siteid                                                \n" +
						"      , glassid AS cellid                                        \n" +
						"      , SUBSTR (t1.glassid, 1, 8) AS glassid                     \n" +
						"      , d.prodgrpname                                            \n" +
						"      , t1.prodid                                                \n" +
						"      , orgstepid                                                \n" +
						"      , measeqpunitid                                            \n" +
						"      , dcoldate as DCOLTIME                                     \n" +
						"      , itemid                                                   \n" +
						"      , subitemid                                                \n" +
						"      , datavalue                                                \n" +
						"      , code_x gateline                                          \n" +
						"      , code_y dataline                                          \n" +
						"      , code_x2 gateline2                                        \n" +
						"      , code_y2 dataline2                                        \n" +
						"      , t1.prodtype                                              \n" +
						"      , '' AS measstepgrpid                                      \n" +
						"      , SUBSTR (t1.glassid, 10, 2) cell_loc_id                   \n" +
						" FROM yms_dm.h_meas_rundata_cell t1, yms_dm.d_product d          \n" +
						"WHERE t1.dcoldate > TO_DATE (%s, 'YYYYMMDDHH24MISS')             \n" +   // FROM_ETL_DT
						"  AND t1.dcoldate < TO_DATE (%s, 'YYYYMMDDHH24MISS')             \n" +   // TO_ETL_DT
						"  AND t1.siteid = %s                                             \n" +   // LINE
						"  AND t1.measstepgrpid = '7M020'                                 \n" +
						"  AND t1.prodid = d.prodid                                       \n" +
						"  AND t1.prodid IN ( %s )                                        \n" +   // USER_GROUP_CODE
						""
						, "'20140701110000'", "'20140702105959'", "'L6FAB'", "'LTL097QL01-A01'");

				OracleConnection conn = Connection.getOracleConnection();
				Statement stmt = conn.createStatement();
				
				if (Flag.TRUE) {
					ResultSet rs = stmt.executeQuery(query);
					ResultSetMetaData md = rs.getMetaData();

					for (int i=0; i < 200000 && rs.next(); i++) {
						
						StringBuffer sb = new StringBuffer();
						sb.append(String.format("(%4d)   ", i));
						
						for (int col=1; col <= md.getColumnCount(); col++) {
							sb.append("\t").append(rs.getString(col));
						}
						
						System.out.println(sb.toString());
					}
					
					System.out.println("------------- META DATA -----------------------");
					
					for (int i=1; i <= md.getColumnCount(); i++) {
						System.out.println(String.format("%d) [%s] [%s] [%s]", i, md.getColumnName(i), md.getColumnLabel(i), md.getColumnClassName(i)));
					}
				}
				
				conn.close();
				if (Flag.TRUE) System.out.println("##### OK!");
				if (!Flag.TRUE) System.out.println("[" + query + "]");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	private static void line78LC()
	{
		if (Flag.TRUE) {
			try {
				String query = String.format("" +
						"/* MURA_TREND : L7AFAB, L7BFAB, L8AFAB, L8ZFAB - LC */                 \n" +
						"SELECT /*+ PARALLEL(2) */                                              \n" +
						"       t1.procid                                                       \n" +
						"       , t1.siteid                                                     \n" +
						"       , t1.glassid AS cellid                                          \n" +
						"       , SUBSTR (t1.glassid, 1, 8) AS glassid                          \n" +
						"       , d.prodgrpname                                                 \n" +
						"       , t1.prodid                                                     \n" +
						"       , orgstepid                                                     \n" +
						"       , measeqpunitid                                                 \n" +
						"       , dcoldate as DCOLTIME                                          \n" +
						"       , itemid                                                        \n" +
						"       , subitemid                                                     \n" +
						"       , datavalue                                                     \n" +
						"       , code_x gateline                                               \n" +
						"       , code_y dataline                                               \n" +
						"       , code_x2 gateline2                                             \n" +
						"       , code_y2 dataline2                                             \n" +
						"       , t1.prodtype                                                   \n" +
						"       , t1.measstepgrpid                                              \n" +
						"       , CASE                                                          \n" +
						"          WHEN SUBSTR (t1.glassid, 1, 1) IN ('7', 'G')                 \n" +
						"             THEN SUBSTR (t1.glassid, 10, 1)                           \n" +
						"          WHEN SUBSTR (t1.glassid, 1, 1) IN ('8', 'H')                 \n" +
						"             THEN SUBSTR (t1.glassid, 9, 2)                            \n" +
						"         END cell_loc_id                                               \n" +
						"FROM   yms_dm.h_meas_rundata_cell t1 , yms_dm.d_product d              \n" +
						" WHERE t1.dcoldate >= TO_DATE (%s, 'YYYYMMDDHH24MISS')                 \n" +   // FROM_ETL_DT
						"   AND t1.dcoldate < TO_DATE (%s, 'YYYYMMDDHH24MISS')                  \n" +   // TO_ETL_DT
						"   AND t1.siteid = %s                                                  \n" +   // LINE
						"   AND t1.prodid = d.prodid                                            \n" +
						"   AND t1.measstepgrpid LIKE 'L%%'                                     \n" +
						"   AND t1.procid IN ( %s )                                             \n" +   // USER_GROUP_CODE
						"   UNION ALL                                                           \n" +
						"    SELECT /*+ PARALLEL(2) */                                          \n" +
						"           t1.procid, t1.siteid, t1.glassid AS cellid,                 \n" +
						"           SUBSTR (t1.glassid, 1, 8) AS glassid, d.prodgrpname,        \n" +
						"           t1.prodid, orgstepid, measeqpunitid, dcoldate,              \n" +
						"           itemid, subitemid, datavalue, code_x gateline,              \n" +
						"           code_y dataline, code_x2 gateline2,                         \n" +
						"           code_y2 dataline2, t1.prodtype, t1.measstepgrpid,           \n" +
						"           CASE                                                        \n" +
						"              WHEN SUBSTR (t1.glassid, 1, 1) IN ('7', 'G')             \n" +
						"                 THEN SUBSTR (t1.glassid, 10, 1)                       \n" +
						"              WHEN SUBSTR (t1.glassid, 1, 1) IN ('8', 'H')             \n" +
						"                 THEN SUBSTR (t1.glassid, 9, 2)                        \n" +
						"           END cell_loc_id                                             \n" +
						"      FROM yms_dm.h_meas_rundata_glass t1, yms_dm.d_product d          \n" +
						"     WHERE t1.dcoldate >= TO_DATE (%s, 'YYYYMMDDHH24MISS')             \n" +   // FROM_ETL_DT
						"       AND t1.dcoldate < TO_DATE (%s, 'YYYYMMDDHH24MISS')              \n" +   // TO_ETL_DT
						"       AND t1.siteid = %s                                              \n" +   // LINE
						"       AND t1.prodid = d.prodid                                        \n" +
						"       AND t1.measstepgrpid LIKE 'L%%'                                 \n" +
						"       AND t1.procid IN ( %s )                                         \n" +   // USER_GROUP_CODE
						""
						, "'20140702110000'", "'20140703105959'", "'L8AFAB'", "'L8480F71'", "'20140702110000'", "'20140703105959'", "'L8AFAB'", "'L8480F71'");

				OracleConnection conn = Connection.getOracleConnection();
				Statement stmt = conn.createStatement();
				
				if (Flag.TRUE) {
					ResultSet rs = stmt.executeQuery(query);
					ResultSetMetaData md = rs.getMetaData();

					for (int i=0; i < 10000 && rs.next(); i++) {
						
						StringBuffer sb = new StringBuffer();
						sb.append(String.format("(%4d)   ", i));
						
						for (int col=1; col <= md.getColumnCount(); col++) {
							sb.append("\t").append(rs.getString(col));
						}
						
						System.out.println(sb.toString());
					}
					
					System.out.println("------------- META DATA -----------------------");
					
					for (int i=1; i <= md.getColumnCount(); i++) {
						System.out.println(String.format("%d) [%s] [%s] [%s]", i, md.getColumnName(i), md.getColumnLabel(i), md.getColumnClassName(i)));
					}
				}
				
				conn.close();
				if (Flag.TRUE) System.out.println("##### OK!");
				if (!Flag.TRUE) System.out.println("[" + query + "]");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	private static void line78MOD()
	{
		if (Flag.TRUE) {
			try {
				String query = String.format("" +
						"/* MURA_TREND : L7AFAB, L7BFAB, L8AFAB, L8ZFAB - MOD */            \n" +
						"SELECT /*+ PARALLEL(2) */                                          \n" +
						"        d.procid                                                   \n" +
						"       , t1.siteid                                                 \n" +
						"       , glassid AS cellid                                         \n" +
						"       , SUBSTR (t1.glassid, 1, 8) AS glassid                      \n" +
						"       , d.prodgrpname                                             \n" +
						"       , t1.prodid                                                 \n" +
						"       , orgstepid                                                 \n" +
						"       , measeqpunitid                                             \n" +
						"       , dcoldate as DCOLTIME                                      \n" +
						"       , itemid                                                    \n" +
						"       , subitemid                                                 \n" +
						"       , datavalue                                                 \n" +
						"       , code_x gateline                                           \n" +
						"       , code_y dataline                                           \n" +
						"       , code_x2 gateline2                                         \n" +
						"       , code_y2 dataline2                                         \n" +
						"       , t1.prodtype                                               \n" +
						"       , '' AS measstepgrpid                                       \n" +
						"       , CASE                                                      \n" +
						"          WHEN SUBSTR (t1.glassid, 1, 1) = 7                       \n" +
						"             THEN SUBSTR (t1.glassid, 10, 1)                       \n" +
						"          ELSE SUBSTR (t1.glassid, 9, 2)                           \n" +
						"       END cell_loc_id                                             \n" +
						"FROM   yms_dm.h_meas_rundata_cell t1, yms_dm.d_product d           \n" +
						" WHERE t1.dcoldate > TO_DATE (%s , 'YYYYMMDDHH24MISS')             \n" +   // FROM_ETL_DT
						"   AND t1.dcoldate < TO_DATE (%s , 'YYYYMMDDHH24MISS')             \n" +   // TO_ETL_DT
						"   AND t1.siteid = %s                                              \n" +   // LINE
						"   AND t1.measstepgrpid = '7M020'                                  \n" +
						"   AND t1.prodid = d.prodid                                        \n" +
						"   AND t1.prodid IN (%s)                                           \n" +   // USER_GROUP_CODE
						""
						, "'20140702070000'", "'20140703065959'", "'L8AFAB'", "'LSC480HN02-G01'");

				OracleConnection conn = Connection.getOracleConnection();
				Statement stmt = conn.createStatement();
				
				if (Flag.TRUE) {
					ResultSet rs = stmt.executeQuery(query);
					ResultSetMetaData md = rs.getMetaData();

					for (int i=0; i < 10000 && rs.next(); i++) {
						
						StringBuffer sb = new StringBuffer();
						sb.append(String.format("(%4d)   ", i));
						
						for (int col=1; col <= md.getColumnCount(); col++) {
							sb.append("\t").append(rs.getString(col));
						}
						
						System.out.println(sb.toString());
					}
					
					System.out.println("------------- META DATA -----------------------");
					
					for (int i=1; i <= md.getColumnCount(); i++) {
						System.out.println(String.format("%d) [%s] [%s] [%s]", i, md.getColumnName(i), md.getColumnLabel(i), md.getColumnClassName(i)));
					}
				}
				
				conn.close();
				if (Flag.TRUE) System.out.println("##### OK!");
				if (!Flag.TRUE) System.out.println("[" + query + "]");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public static void main(String[] args)
	{
		if (!Flag.TRUE) line56LC();    // L5FAB, L6FAB - LC
		if (!Flag.TRUE) line56MOD();   // L5FAB, L6FAB - MOD
		if (!Flag.TRUE) line78LC();    // L7AFAB, L7BFAB, L8AFAB, L8ZFAB - LC
		if (Flag.TRUE) line78MOD();   // L7AFAB, L7BFAB, L8AFAB, L8ZFAB - MOD
	}
}
