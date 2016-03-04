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

package kiea.proj.sdc.anal.tools.yms.check.v01.MURA_AIB.m07.s01;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;

import kiea.kr.co.tain.base.Flag;
import kiea.proj.sdc.anal.common.Connection;
import oracle.jdbc.OracleConnection;

/**
 * @author KangSeok
 * @date 2014. 8. 7.
 * @file_name AIB_MURA_HIST_EXTRC.java
 *
 */
public class AIB_MURA_HIST_EXTRC
{
	/*
	 * 
	 *     MURA_HIST
	 *
	 */
	
	///////////////////////////////////////////////////////////////////////////

	private static void line56LC()
	{
		if (Flag.TRUE) {
			try {
				String query = String.format("" +
						"/* MURA_HIST : L5FAB, L6FAB - LC */                                                              \n" +
						"WITH insp_temp1 AS (                                                                             \n" +
						"    SELECT /*+ PARALLEL(2) */                                                                    \n" +
						"        DISTINCT                                                                                 \n" +
						"        CASE                                                                                     \n" +
						"            WHEN SUBSTR (t1.glassid, 8, 1) BETWEEN 'A' AND 'Z'                                   \n" +
						"            THEN SUBSTR (t1.glassid, 1, 7)|| ''|| SUBSTR (t1.glassid, 8)                         \n" +
						"            ELSE SUBSTR (t1.glassid, 1, 7)|| ''|| SUBSTR (t1.glassid, 8,2)                       \n" +
						"        END GLASSID,                                                                             \n" +
						"        t1.siteid                                                                                \n" +
						"    FROM yms_dm.h_meas_rundata_cell t1, yms_dm.d_product d                                       \n" +
						"    WHERE t1.dcoldate >= TO_DATE (%s, 'YYYYMMDDHH24MISS')                                        \n" +   // FROM_ETL_DT
						"        AND t1.dcoldate < TO_DATE (%s, 'YYYYMMDDHH24MISS')                                       \n" +   // TO_ETL_DT
						"        AND t1.siteid = %s                                                                       \n" +   // LINE
						"        AND t1.orgstepid like 'L__080'                                                           \n" +
						"        AND T1.PROCID IN ( %s )                                                                  \n" +   // USER_GROUP_CODE
						"        AND t1.ITEMID IN ( %s )                                                                  \n" +   // DEFECT_GROUP_CODE
						"        AND t1.prodid = d.prodid                                                                 \n" +
						")                                                                                                \n" +
						"SELECT /*+ ORDERED USE_NL(T2 T3) INDEX(T2 DW_GLASSCELL_EVENTHIST_PK) INDEX(T3 DW_MD_STEP_PK) */  \n" +
						"    T2.OWNINGSITEID                      AS LINE_CODE,                                           \n" +
						"    T2.GLASSCELLID                       AS GLASS_ID,                                            \n" +
						"    CASE WHEN T2.WORKERID = 'FDC' THEN 'FDC' ELSE 'SPC' END EVENT_OCCUR_CODE,                    \n" +
						"    CASE WHEN T2.WORKERID = 'FDC' THEN 'FDC' ELSE 'SPC' END EVENT_OCCUR_DETIAL_CODE,             \n" +
						"    T2.STEPID                            AS STEP_ID,                                             \n" +
						"    CASE                                                                                         \n" +
						"        WHEN T2.STEPID LIKE '%%BANK'  THEN T2.STEPID                                             \n" +
						"        WHEN T2.STEPID LIKE '%%STOCK' THEN T2.STEPID                                             \n" +
						"        WHEN T2.STEPID LIKE '%%PACK'  THEN T2.STEPID                                             \n" +
						"        WHEN T2.STEPID LIKE 'SM010'  THEN T2.STEPID                                              \n" +
						"        ELSE SUBSTR(T2.STEPID,1,1)||'__'||SUBSTR(T2.STEPID,4,3)                                  \n" +
						"    END                                  AS MAINSTEPID,                                          \n" +
						"    T2.EQPID                             AS EQP_ID,                                              \n" +
						"    T2.COMMENTDESC                       AS COMMENTS,                                            \n" +
						"    T2.GLASSSTATUS                       AS WORK_STATUS,                                         \n" +
						"    T2.EVENTVALUE                        AS EVENT_OCCUR_ID,                                      \n" +
						"    T2.EVENTDATE                         AS EVENT_OCCUR_DT                                       \n" +
						"FROM insp_temp1 T1, YMS_DW.DW_GLASSCELL_EVENTHIST  T2 , YMS_DW.DW_MD_STEP T3                     \n" +
						"WHERE 1=1                                                                                        \n" +
						"    AND T1.SITEID     = T2.SITEID                                                                \n" +
						"    AND T1.GLASSID    = T2.GLASSCELLID                                                           \n" +
						"    AND T2.ACTIVITY   = 'Hold'                                                                   \n" +
						"    AND T2.WORKERID   IN ('FDC','SPC')                                                           \n" +
						"    AND T2.SITEID     = T3.SOURCESITEID                                                          \n" +
						"    AND T2.PROCESSID  = T3.PROCESSID                                                             \n" +
						"    AND T2.STEPID     = T3.STEPID                                                                \n" +
						""
						, "'20140805090000'", "'20140806085959'", "'L6FAB'", "'L6156H11'", "'POR'");

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
	
	private static void line78LC()
	{
		if (Flag.TRUE) {
			try {
				String query = String.format("" +
						"/* MURA_HIST : L7AFAB, L7BFAB, L8AFAB, L8ZFAB - LC */                                           \n" +
						"WITH insp_temp1 AS (                                                                            \n" +
						"    SELECT  /*+ PARALLEL(2) */                                                                  \n" +
						"        DISTINCT                                                                                \n" +
						"        CASE                                                                                    \n" +
						"            WHEN SUBSTR (t1.glassid, 1, 1) IN ('7', 'G')                                        \n" +
						"                THEN SUBSTR (t1.glassid, 1, 9)                                                  \n" +
						"            WHEN SUBSTR (t1.glassid, 1, 1) IN ('8', 'H')                                        \n" +
						"                THEN SUBSTR (t1.glassid, 1, 8)                                                  \n" +
						"        END glassid,                                                                            \n" +
						"        /* glassid 구하는 방법 변경 2013.04.17 CHOI TO YEOM */                                  \n" +
						"        /* CASE                                             */                                  \n" +
						"        /*   WHEN TO_NUMBER (d.inch) <= 18                  */                                  \n" +
						"        /*      THEN SUBSTR (glassid, 1, 9)                 */                                  \n" +
						"        /*   ELSE SUBSTR (glassid, 1, 8)                    */                                  \n" +
						"        /* END glassid                                      */                                  \n" +
						"        t1.siteid                                                                               \n" +
						"    FROM yms_dm.h_meas_rundata_cell t1, yms_dm.d_product d                                      \n" +
						"    WHERE t1.dcoldate >=TO_DATE(%s, 'YYYYMMDDHH24MISS')                                         \n" +   // FROM_ETL_DT
						"        AND t1.dcoldate < TO_DATE (%s, 'YYYYMMDDHH24MISS')                                      \n" +   // TO_ETL_DT
						"        AND t1.siteid = %s                                                                      \n" +   // LINE
						"        AND t1.measstepgrpid LIKE 'L%%'                                                         \n" +
						"        AND t1.procid IN ( %s )                                                                 \n" +   // USER_GROUP_CODE
						"        AND t1.itemid IN ( %s )                                                                 \n" +   // DEFECT_GROUP_CODE
						"        AND t1.prodid = d.prodid                                                                \n" +
						")                                                                                               \n" +
						"SELECT /*+ ORDERED USE_NL(T2 T3) INDEX(T2 DW_GLASSCELL_EVENTHIST_PK) INDEX(T3 DW_MD_STEP_PK) */ \n" +
						"    t2.owningsiteid AS line_code, t2.glasscellid AS glass_id,                                   \n" +
						"    CASE WHEN t2.workerid = 'FDC' THEN 'FDC'  ELSE 'SPC' END event_occur_code,                  \n" +
						"    CASE WHEN t2.workerid = 'FDC' THEN 'FDC'  ELSE 'SPC' END event_occur_detial_code,           \n" +
						"    t2.stepid AS step_id,                                                                       \n" +
						"    CASE                                                                                        \n" +
						"        WHEN t2.stepid LIKE '%%BANK' THEN t2.stepid                                             \n" +
						"        WHEN t2.stepid LIKE '%%STOCK' THEN t2.stepid                                            \n" +
						"        WHEN t2.stepid LIKE '%%PACK' THEN t2.stepid                                             \n" +
						"        WHEN t2.stepid LIKE 'SM010' THEN t2.stepid                                              \n" +
						"        ELSE    SUBSTR (t2.stepid, 1, 1)|| '__'|| SUBSTR (t2.stepid, 4, 3)                      \n" +
						"    END AS mainstepid,                                                                          \n" +
						"    t2.eqpid AS eqp_id,                                                                         \n" +
						"    t2.commentdesc AS comments,                                                                 \n" +
						"    t2.glassstatus AS work_status,                                                              \n" +
						"    t2.eventvalue AS event_occur_id,                                                            \n" +
						"    t2.eventdate AS event_occur_dt                                                              \n" +
						"FROM insp_temp1 t1, yms_dw.dw_glasscell_eventhist t2, yms_dw.dw_md_step t3                      \n" +
						"WHERE 1 = 1                                                                                     \n" +
						"    AND t1.siteid = t2.siteid                                                                   \n" +
						"    AND t1.glassid = t2.glasscellid                                                             \n" +
						"    AND t2.activity = 'Hold'                                                                    \n" +
						"    AND t2.workerid IN ('FDC', 'SPC')                                                           \n" +
						"    AND t2.siteid = t3.sourcesiteid                                                             \n" +
						"    AND t2.processid = t3.processid                                                             \n" +
						"    AND t2.stepid = t3.stepid                                                                   \n" +
						""
						, "'20140702110000'", "'20140703105959'", "'L8AFAB'", "'L8480F71'", "'32PR'");

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
						"/* MURA_HIST : L7AFAB, L7BFAB, L8AFAB, L8ZFAB - MOD */                                             \n" +
						"WITH insp_temp1 AS (                                                                               \n" +
						"    SELECT /*+ PARALLEL(2) */                                                                      \n" +
						"        DISTINCT                                                                                   \n" +
						"        CASE                                                                                       \n" +
						"            WHEN SUBSTR(t1.glassid,1,1) IN ('7','G') THEN SUBSTR(t1.glassid,1,9)                   \n" +
						"            WHEN SUBSTR(t1.glassid,1,1) IN ('8','H') THEN SUBSTR(t1.glassid,1,8)                   \n" +
						"        END glassid,                                                                               \n" +
						"        t1.siteid                                                                                  \n" +
						"    FROM yms_dm.h_meas_rundata_cell t1, yms_dm.d_product d                                         \n" +
						"    WHERE t1.dcoldate >= TO_DATE (%s, 'YYYYMMDDHH24MISS')                                          \n" +   // FROM_ETL_DT
						"        AND t1.dcoldate < TO_DATE (%s, 'YYYYMMDDHH24MISS')                                         \n" +   // TO_ETL_DT
						"        AND t1.siteid = %s                                                                         \n" +   // LINE
						"        AND t1.measstepgrpid = '7M020'                                                             \n" +
						"        AND T1.PROCID IN ( %s )                                                                    \n" +   // USER_GROUP_CODE
						"        AND t1.ITEMID IN ( %s )                                                                    \n" +   // DEFECT_GROUP_CODE
						"        AND t1.prodid = d.prodid                                                                   \n" +
						")                                                                                                  \n" +
						"SELECT /*+ ORDERED USE_NL(T2 T3) INDEX(T2 DW_MF_GLASSCELL_EVENTHIST_PK) INDEX(T3 DW_MD_STEP_PK) */ \n" +
						"    T2.OWNINGSITEID                      AS LINE_CODE,                                             \n" +
						"    T2.GLASSCELLID                       AS GLASS_ID,                                              \n" +
						"    CASE WHEN T2.WORKERID = 'FDC' THEN 'FDC' ELSE 'SPC' END EVENT_OCCUR_CODE,                      \n" +
						"    CASE WHEN T2.WORKERID = 'FDC' THEN 'FDC' ELSE 'SPC' END EVENT_OCCUR_DETIAL_CODE,               \n" +
						"    T2.STEPID                            AS STEP_ID,                                               \n" +
						"    CASE                                                                                           \n" +
						"        WHEN T2.STEPID LIKE '%%BANK'  THEN T2.STEPID                                               \n" +
						"        WHEN T2.STEPID LIKE '%%STOCK' THEN T2.STEPID                                               \n" +
						"        WHEN T2.STEPID LIKE '%%PACK'  THEN T2.STEPID                                               \n" +
						"        WHEN T2.STEPID LIKE 'SM010'  THEN T2.STEPID                                                \n" +
						"        ELSE SUBSTR(T2.STEPID,1,1)||'__'||SUBSTR(T2.STEPID,4,3)                                    \n" +
						"    END                                  AS MAINSTEPID,                                            \n" +
						"    T2.EQPID                             AS EQP_ID,                                                \n" +
						"    T2.COMMENTDESC                       AS COMMENTS,                                              \n" +
						"    T2.GLASSSTATUS                       AS WORK_STATUS,                                           \n" +
						"    T2.EVENTVALUE                        AS EVENT_OCCUR_ID,                                        \n" +
						"    T2.EVENTDATE                         AS EVENT_OCCUR_DT                                         \n" +
						"FROM insp_temp1 T1, YMS_DW.DW_GLASSCELL_EVENTHIST  T2 , YMS_DW.DW_MD_STEP T3                       \n" +
						"WHERE 1=1                                                                                          \n" +
						"    AND T1.SITEID     = T2.SITEID                                                                  \n" +
						"    AND T1.GLASSID    = T2.GLASSCELLID                                                             \n" +
						"    AND T2.ACTIVITY   = 'Hold'                                                                     \n" +
						"    AND T2.WORKERID   IN ('FDC','SPC')                                                             \n" +
						"    AND T2.SITEID     = T3.SOURCESITEID                                                            \n" +
						"    AND T2.PROCESSID  = T3.PROCESSID                                                               \n" +
						"    AND T2.STEPID     = T3.STEPID                                                                  \n" +
						""
						, "'20140702070000'", "'20140703065959'", "'L8AFAB'", "'LSC480HN02-G01'", "'32BOR'");

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
		if (Flag.TRUE) line78LC();    // L7AFAB, L7BFAB, L8AFAB, L8ZFAB - LC
		if (!Flag.TRUE) line78MOD();   // L7AFAB, L7BFAB, L8AFAB, L8ZFAB - MOD
	}
}
