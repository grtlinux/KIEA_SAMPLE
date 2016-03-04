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

package kiea.proj.sdc.anal.tools.yms.check.v01.MURA_AIB.m01.s01;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;

import kiea.kr.co.tain.base.Flag;
import kiea.proj.sdc.anal.common.Connection;
import oracle.jdbc.OracleConnection;

/**
 * @author KangSeok
 * @date 2014. 8. 7.
 * @file_name AIB_MURA_WIP_SUMMARY_EXTRC.java
 *
 */
public class AIB_MURA_WIP_SUMMARY_EXTRC
{
	/*
	 * 
	 *     WIP_EQP_SMMRY
	 *
	 */
	
	///////////////////////////////////////////////////////////////////////////

	private static void line56LC()
	{
		if (Flag.TRUE) {
			try {
				String query = String.format("" +
						"     /* WIP_EQP_SMMRY : L5FAB, L6FAB - LC */                                       \n" +
						"     WITH insp AS                                                                  \n" +
						"          (SELECT /*+ PARALLEL(2) */                                               \n" +
						"                  t1.procid,                                                       \n" +
						"                  t1.siteid,                                                       \n" +
						"                  t1.glassid AS cellid,                                            \n" +
						"                  t1.celllocid glassid,                                            \n" +
						"                  d.prodgrpname,                                                   \n" +
						"                  t1.prodid,                                                       \n" +
						"                  orgstepid,                                                       \n" +
						"                  measeqpunitid,                                                   \n" +
						"                  dcoldate AS insptime,                                            \n" +
						"                  itemid,                                                          \n" +
						"                  subitemid,                                                       \n" +
						"                  datavalue,                                                       \n" +
						"                  code_x gateline,                                                 \n" +
						"                  code_y dataline,                                                 \n" +
						"                  code_x2 gateline2,                                               \n" +
						"                  code_y2 dataline2,                                               \n" +
						"                  t1.prodtype,                                                     \n" +
						"                  CASE                                                             \n" +
						"                     WHEN SUBSTR (t1.glassid, 8, 1) BETWEEN 'A' AND 'Z'            \n" +
						"                        THEN SUBSTR (t1.glassid, -2)                               \n" +
						"                     ELSE SUBSTR (t1.glassid, -1)                                  \n" +
						"                  END cell_loc_id                                                  \n" +
						"             FROM yms_dm.h_meas_rundata_cell t1, yms_dm.d_product d                \n" +
						"            WHERE t1.dcoldate >= TO_DATE(%s,'YYYYMMDDHH24MISS')                    \n" +    // FROM_ETL_DT
						"              AND t1.dcoldate < TO_DATE(%s,'YYYYMMDDHH24MISS')                     \n" +    // TO_ETL_DT
						"              AND t1.siteid = %s                                                   \n" +    // LINE
						"              AND t1.SITEID_EQPTYPE = %s                                           \n" +    // L6FABSTDALONE
						"              AND t1.orgstepid LIKE 'L__080'                                       \n" +
						"              AND t1.procid IN (%s)                                                \n" +    // USER_GROUP_CODE
						"              AND t1.itemid IN (%s)                                                \n" +    // DEFECT_GROUP_CODE
						"              AND t1.prodid = d.prodid),                                           \n" +
						"          insp2 AS                                                                 \n" +
						"          (SELECT          /*+ USE_HASH ( T1 T2 ) */                               \n" +
						"                  DISTINCT t1.siteid AS line_code,                                 \n" +
						"                           t1.prodgrpname AS user_group_code,                      \n" +
						"                           t1.procid AS process_id, t1.prodid AS product_id,       \n" +
						"                           t1.prodtype AS product_type, 'AVI' sub_area_code,       \n" +
						"                           t1.glassid, t1.cellid, t3.matglassid AS cfglassid,      \n" +
						"                           t1.insptime AS inspect_dt,                              \n" +
						"                           t1.itemid AS defect_group_code, t1.datavalue,           \n" +
						"                           '' inspector_id, '' bingradecd, '' matchlottype         \n" +
						"                      FROM insp t1, yms_dw.dw_glass_match_ca t3                    \n" +
						"                     WHERE t1.glassid = t3.glassid(+))                             \n" +
						"     SELECT /*+ ORDERED */                                                         \n" +
						"            t1.line_code, t1.user_group_code, t1.process_id, t1.product_id,        \n" +
						"            t1.product_type,                                                       \n" +
						"            CASE                                                                   \n" +
						"               WHEN (t3.stepid LIKE 'T%%' OR t3.stepid LIKE 'A%%')                 \n" +
						"                  THEN 'TFT'                                                       \n" +
						"               WHEN (   t3.stepid LIKE 'F%%'                                       \n" +
						"                     OR t3.stepid LIKE 'B%%'                                       \n" +
						"                     OR t3.stepid LIKE 'E%%'                                       \n" +
						"                    )                                                              \n" +
						"                  THEN 'CF'                                                        \n" +
						"               WHEN (t3.stepid LIKE 'L%%' OR t3.stepid LIKE 'C%%')                 \n" +
						"                  THEN 'LC'                                                        \n" +
						"               ELSE 'MOD'                                                          \n" +
						"            END AS area_code,                                                      \n" +
						"            t1.sub_area_code, t4.stepgroupid step_id, t3.eqpid AS eqp_id,          \n" +
						"            REPLACE (t1.glassid, '.', '') glasscellid, t1.cellid AS cell_id,       \n" +
						"            t3.tkintime tkindate, t3.eventtime AS tkoutdate, '' matchlottype,      \n" +
						"            '' bingradecd, t1.defect_group_code, t1.datavalue, '' decision_code,   \n" +
						"            t3.tkintime AS pre_trk_out                                             \n" +
						"       FROM insp2 t1, yms_dw.dw_glasscell_eventhist_ca t3, yms_dw.dw_md_step t4    \n" +
						"      WHERE 1 = 1                                                                  \n" +
						"        AND t1.glassid = t3.glasscellid                                            \n" +
						"        AND t3.eventtime < t1.inspect_dt                                           \n" +
						"        AND t3.activity = 'TrackOut'                                               \n" +
						"        AND t3.processid = t4.processid                                            \n" +
						"        AND t3.stepid = t4.stepid                                                  \n" +
						"     UNION ALL                                                                     \n" +
						"     SELECT /*+ ORDERED */                                                         \n" +
						"            t1.line_code, t1.user_group_code, t3.processid, t3.productid,          \n" +
						"            t1.product_type, 'CF' area_code, t1.sub_area_code,                     \n" +
						"            t4.stepgroupid step_id, t3.eqpid AS eqp_id,                            \n" +
						"            REPLACE (t1.glassid, '.', '') glasscellid, t1.cellid AS cell_id,       \n" +
						"            t3.tkintime tkindate, t3.eventtime AS tkoutdate, '' matchlottype,      \n" +
						"            '' bingradecd, '' defect_group_code, t1.datavalue, '' decision_code,   \n" +
						"            t3.tkintime AS pre_trk_out                                             \n" +
						"       FROM insp2 t1, yms_dw.dw_glasscell_eventhist_ca t3, yms_dw.dw_md_step t4    \n" +
						"      WHERE 1 = 1                                                                  \n" +
						"        AND t1.cfglassid = t3.glasscellid                                          \n" +
						"        AND t3.eventtime < t1.inspect_dt                                           \n" +
						"        AND t3.activity = 'TrackOut'                                               \n" +
						"        AND t3.processid = t4.processid                                            \n" +
						"        AND t3.stepid = t4.stepid                                                  \n" +
						""
						, "'20140805090000'", "'20140806085959'", "'L6FAB'", "'L6FABSTDALONE'", "'L6156H11'", "'POR'");

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
						"  /* WIP_EQP_SMMRY : L5FAB, L6FAB - MOD */                                           \n" +
						"  WITH insp AS                                                                       \n" +
						"       (SELECT   /*+ FULL(D) PARALLEL (2) */                                         \n" +
						"                 d.siteid siteid,                                                    \n" +
						"                 d.procid,                                                           \n" +
						"                 d.celllocid glassid,                                                \n" +
						"                 MAX (dcoldate) insptime,                                            \n" +
						"                 MAX (t.cellqty) cellqty,                                            \n" +
						"                 MAX (t.prodgrpname) prodgrpname,                                    \n" +
						"                 MAX (t.prodid) prodid,                                              \n" +
						"                 MAX (d.orgstepid) orgstepid,                                        \n" +
						"                 MAX (d.measstepgrpid) measstepgrpid,                                \n" +
						"                 MAX (d.measeqpunitid) measeqpunitid,                                \n" +
						"                 MAX (d.itemid) itemid,                                              \n" +
						"                 MAX (d.dcoldate) dcoltime,                                          \n" +
						"                 MAX (d.subitemid) subitemid,                                        \n" +
						"                 MAX (d.prodtype) prodtype                                           \n" +
						"            FROM yms_dm.h_meas_rundata_cell d, yms_dm.d_product t                    \n" +
						"           WHERE d.dcoldate >= TO_DATE (%s, 'YYYYMMDDHH24MISS')                      \n" +   // FROM_ETL_DT
						"             AND d.dcoldate < TO_DATE (%s, 'YYYYMMDDHH24MISS')                       \n" +   // TO_ETL_DT
						"             AND d.prodid = %s            /* PRODID 입력 */                          \n" +   // USER_GROUP_CODE
						"             AND d.prodid = t.prodid                                                 \n" +
						"             AND d.siteid = %s                                                       \n" +   // LINE
						"             AND d.siteid_eqptype = %s                                               \n" +   // LINE_ID
						"             AND measstepgrpid = '7M020'                                             \n" +
						"             AND itemid IN ( %s )                                                    \n" +   // DEFECT_GROUP_CODE
						"        GROUP BY d.siteid, d.procid, d.celllocid),                                   \n" +
						"       insp2 AS                                                                      \n" +
						"       (SELECT          /*+ USE_HASH ( T1 T2 ) */                                    \n" +
						"               DISTINCT t1.siteid AS line_code,                                      \n" +
						"                        t1.prodgrpname AS user_group_code,                           \n" +
						"                        t1.procid AS process_id, t1.prodid AS product_id,            \n" +
						"                        t1.prodtype AS product_type, 'AVI' sub_area_code,            \n" +
						"                        t1.glassid,                                                  \n" +
						"                                   /*t1.cellid, */                                   \n" +
						"                                   t3.matglassid AS cfglassid,                       \n" +
						"                        t1.insptime AS inspect_dt,                                   \n" +
						"                        /*'' cell_loc_id, */                                         \n" +
						"                        t1.itemid AS defect_group_code,                              \n" +
						"                                                        /*t1.datavalue,*/            \n" +
						"                        '' inspector_id, '' bingradecd, '' matchlottype              \n" +
						"                   FROM insp t1, yms_dw.dw_glass_match_ca t3                         \n" +
						"                  WHERE t1.glassid = t3.glassid(+))                                  \n" +
						"  SELECT /*+ ORDERED */                                                              \n" +
						"         t1.line_code, t1.user_group_code, t1.process_id, t1.product_id,             \n" +
						"         t1.product_type,                                                            \n" +
						"         CASE                                                                        \n" +
						"            WHEN (t3.stepid LIKE 'T%%' OR t3.stepid LIKE 'A%%')                      \n" +
						"               THEN 'TFT'                                                            \n" +
						"            WHEN (   t3.stepid LIKE 'F%%'                                            \n" +
						"                  OR t3.stepid LIKE 'B%%'                                            \n" +
						"                  OR t3.stepid LIKE 'E%%'                                            \n" +
						"                 )                                                                   \n" +
						"               THEN 'CF'                                                             \n" +
						"            WHEN (t3.stepid LIKE 'L%%' OR t3.stepid LIKE 'C%%')                      \n" +
						"               THEN 'LC'                                                             \n" +
						"            ELSE 'MOD'                                                               \n" +
						"         END AS area_code,                                                           \n" +
						"         t1.sub_area_code, t4.stepgroupid step_id, t3.eqpid AS eqp_id,               \n" +
						"         /*t1.glassid AS glasscellid, */                                             \n" +
						"         /* wip 분석시 . 있어야 조인 가능함. YEOM 혹은 clustering 로직 분기필요*/    \n" +
						"         REPLACE (t1.glassid, '.', '') glasscellid,                                  \n" +
						"                                                   /*t1.cellid as cell_id, */        \n" +
						"                                                   t3.tkintime tkindate,             \n" +
						"         t3.eventtime AS tkoutdate, '' matchlottype, '' bingradecd,                  \n" +
						"         t1.defect_group_code,                                                       \n" +
						"                               /*t1.datavalue, */                                    \n" +
						"         '' decision_code, t3.tkintime AS pre_trk_out                                \n" +
						"    FROM insp2 t1, yms_dw.dw_glasscell_eventhist_ca t3, yms_dw.dw_md_step t4         \n" +
						"   WHERE 1 = 1                                                                       \n" +
						"     AND t1.glassid = t3.glasscellid                                                 \n" +
						"     AND t3.eventtime < t1.inspect_dt                                                \n" +
						"     AND t3.activity = 'TrackOut'                                                    \n" +
						"     AND t3.processid = t4.processid                                                 \n" +
						"     AND t3.stepid = t4.stepid                                                       \n" +
						"  UNION ALL                                                                          \n" +
						"  SELECT /*+ ORDERED */                                                              \n" +
						"         t1.line_code, t1.user_group_code, t3.processid, t3.productid,               \n" +
						"         t1.product_type, 'CF' area_code, t1.sub_area_code,                          \n" +
						"         t4.stepgroupid step_id, t3.eqpid AS eqp_id,                                 \n" +
						"         /*t1.glassid AS glasscellid, */                                             \n" +
						"         /* wip 분석시 . 있어야 조인 가능함. YEOM 혹은 clustering 로직 분기필요*/    \n" +
						"         REPLACE (t1.glassid, '.', '') glasscellid, t3.tkintime tkindate,            \n" +
						"         t3.eventtime AS tkoutdate, '' matchlottype, '' bingradecd,                  \n" +
						"         '' defect_group_code, '' decision_code, t3.tkintime AS pre_trk_out          \n" +
						"    FROM insp2 t1, yms_dw.dw_glasscell_eventhist_ca t3, yms_dw.dw_md_step t4         \n" +
						"   WHERE 1 = 1                                                                       \n" +
						"     AND t1.cfglassid = t3.glasscellid                                               \n" +
						"     AND t3.eventtime < t1.inspect_dt                                                \n" +
						"     AND t3.activity = 'TrackOut'                                                    \n" +
						"     AND t3.processid = t4.processid                                                 \n" +
						"     AND t3.stepid = t4.stepid                                                       \n" +
						""
						, "'20140701110000'", "'20140702105959'", "'LTL097QL01-A01'", "'L6FAB'", "'L6FABSTDALONE'", "'32PR'");

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
						"/* WIP_EQP_SMMRY : L7AFAB, L7BFAB, L8AFAB, L8ZFAB - LC */                   \n" +
						"WITH insp_temp1 AS                                                          \n" +
						"     (SELECT /*+ PARALLEL(2) */                                             \n" +
						"             t1.procid,                                                     \n" +
						"             t1.siteid,                                                     \n" +
						"             CASE                                                           \n" +
						"                WHEN SUBSTR (t1.glassid, 1, 1) IN ('7', 'G')                \n" +
						"                   THEN SUBSTR (t1.glassid, 1, 9)                           \n" +
						"                WHEN SUBSTR (t1.glassid, 1, 1) IN ('8', 'H')                \n" +
						"                   THEN SUBSTR (t1.glassid, 1, 8)                           \n" +
						"             END glassid,                                                   \n" +
						"             t1.glassid AS cellid,                                          \n" +
						"             d.prodgrpname,                                                 \n" +
						"             t1.prodid,                                                     \n" +
						"             orgstepid,                                                     \n" +
						"             measeqpunitid,                                                 \n" +
						"             dcoldate AS insptime,                                          \n" +
						"             '' AS cellqty,                                                 \n" +
						"             itemid,                                                        \n" +
						"             subitemid,                                                     \n" +
						"             datavalue,                                                     \n" +
						"             code_x gateline,                                               \n" +
						"             code_y dataline,                                               \n" +
						"             code_x2 gateline2,                                             \n" +
						"             code_y2 dataline2,                                             \n" +
						"             t1.prodtype,                                                   \n" +
						"             CASE                                                           \n" +
						"                WHEN SUBSTR (t1.glassid, 1, 1) IN ('7', 'G')                \n" +
						"                   THEN SUBSTR (t1.glassid, 10, 1)                          \n" +
						"                WHEN SUBSTR (t1.glassid, 1, 1) IN ('8', 'H')                \n" +
						"                   THEN SUBSTR (t1.glassid, 9, 2)                           \n" +
						"             END cell_loc_id                                                \n" +
						"             /* CASE WHEN substr(t1.glassid,1,1) = 7 then  */               \n" +
						"             /* SUBSTR(T1.GLASSID,10,1) else SUBSTR */                      \n" +
						"             /* (t1.glassid, 9, 2) end cell_loc_id */                       \n" +
						"             /* CELL_LCD_ID 값 구하는 방법 변경 2013.04.17 CHOI TO YEOM */  \n" +
						"             /* L7156A1 제외 */                                             \n" +
						"      FROM   yms_dm.h_meas_rundata_cell t1, yms_dm.d_product d              \n" +
						"       WHERE t1.dcoldate >= TO_DATE (%s, 'YYYYMMDDHH24MISS')                \n" +   // FROM_ETL_DT
						"         AND t1.dcoldate < TO_DATE (%s, 'YYYYMMDDHH24MISS')                 \n" +   // TO_ETL_DT
						"         AND t1.siteid = %s    /* L7AFAB, L7BFAB, L8AFAB, L8ZFAB */         \n" +   // LINE
						"         AND t1.measstepgrpid LIKE 'L%%'                                    \n" +
						"         AND t1.procid IN ( %s )                                            \n" +   // USER_GROUP_CODE
						"         AND t1.itemid IN ( %s )                                            \n" +   // DEFECT_GROUP_CODE
						"         AND t1.prodid = d.prodid                                           \n" +
						"         /*AND t1.prodgrpname IN ('46-FHD-240-MB3-SPR7a-A7PP-SNB-RD-8Z') */ \n" +
						"         /*  LC 얼룩지수코드 */                                             \n" +
						"         /*AND t1.ITEMID IN ('8PR','16PR','32PR','RPR',*/                   \n" +
						"         /*'GPR','BPR','8OR','16OR','32OR','ROR','GOR',*/                   \n" +
						"         /*'BOR','8BOR','16BOR','32BOR','RBOR','GBOR','BBOR') */            \n" +
						"     ),                                                                     \n" +
						"     insp_temp2 AS                                                          \n" +
						"     (SELECT /*+ PARALLEL(2) */                                             \n" +
						"             t1.siteid AS line_code, t1.prodgrpname AS user_group_code,     \n" +
						"             t1.procid AS process_id, t1.prodid AS product_id,              \n" +
						"             t1.prodtype AS product_type, 'AMT' sub_area_code,              \n" +
						"             t1.glassid AS glass_id, t1.cellid AS cell_id,                  \n" +
						"             t2.cfglassid AS cfglassid, t1.cellqty base_cell_num,           \n" +
						"             t1.insptime AS inspect_dt,                                     \n" +
						"             REPLACE (cellid, glassid, '') AS cell_loc_id,                  \n" +
						"             itemid AS defect_group_code, '' defect_cell_num, datavalue,    \n" +
						"             '' inspector_id, '' bingradecd, '' matchlottype                \n" +
						"        FROM insp_temp1 t1, YMS_DW.DW_GLASS_MATCH t2                        \n" +
						"       WHERE 1 = 1 AND t1.glassid = t2.tftglassid(+))                       \n" +
						"SELECT t1.line_code, t1.user_group_code, t1.process_id, t1.product_id,      \n" +
						"       t1.product_type,                                                     \n" +
						"       CASE                                                                 \n" +
						"          WHEN t2.imptstepgrpid LIKE 'T%%'                                  \n" +
						"             THEN 'TFT'                                                     \n" +
						"          WHEN t2.imptstepgrpid LIKE 'F%%'                                  \n" +
						"             THEN 'CF'                                                      \n" +
						"          WHEN t2.imptstepgrpid LIKE 'L%%'                                  \n" +
						"             THEN 'LC'                                                      \n" +
						"          ELSE 'MOD'                                                        \n" +
						"       END AS area_code,                                                    \n" +
						"       t1.sub_area_code, t2.imptstepgrpid step_id, t2.impteqpid eqp_id,     \n" +
						"       t1.glass_id AS glasscellid, t1.cell_id,                              \n" +
						"       defect_cell_num / t1.base_cell_num AS glass_defect_code_ratio,       \n" +
						"       t1.base_cell_num, defect_cell_num, t3.tkindate,                      \n" +
						"       t3.eventdate AS tkoutdate, t1.matchlottype, t1.bingradecd,           \n" +
						"       t1.defect_group_code, t1.datavalue,                                  \n" +
						"       t3.preprocesstkoutdate AS pre_trk_out                                \n" +
						"  FROM insp_temp2 t1,                                                       \n" +
						"       yms_dm.h_imptdefecteqp t2,                                           \n" +
						"       YMS_DW.DW_GLASSCELL_EVENTHIST t3                                     \n" +
						" WHERE 1 = 1                                                                \n" +
						"   AND t1.line_code = t2.siteid                                             \n" +
						"   AND t1.glass_id = t2.glasscellid                                         \n" +
						"   AND t2.imptstepgrpid LIKE 'T%%'                                          \n" +
						"   AND t3.eventdate < t1.inspect_dt                                         \n" +
						"   AND t2.siteid = %s                                                       \n" +   // LINE
						"   AND t2.glasscellid = t3.glasscellid                                      \n" +
						"   AND t2.proctime = t3.eventdate                                           \n" +
						"   AND t3.activity = 'TrackOut'                                             \n" +
						"UNION ALL                                                                   \n" +
						"SELECT t1.line_code, t1.user_group_code, t1.process_id, t1.product_id,      \n" +
						"       t1.product_type,                                                     \n" +
						"       CASE                                                                 \n" +
						"          WHEN t2.imptstepgrpid LIKE 'T%%'                                  \n" +
						"             THEN 'TFT'                                                     \n" +
						"          WHEN t2.imptstepgrpid LIKE 'F%%'                                  \n" +
						"             THEN 'CF'                                                      \n" +
						"          WHEN t2.imptstepgrpid LIKE 'L%%'                                  \n" +
						"             THEN 'LC'                                                      \n" +
						"          ELSE 'MOD'                                                        \n" +
						"       END AS area_code,                                                    \n" +
						"       t1.sub_area_code, t2.imptstepgrpid step_id, t2.impteqpid eqp_id,     \n" +
						"       t1.cfglassid AS glasscellid, t1.cfglassid,                           \n" +
						"       defect_cell_num / t1.base_cell_num AS glass_defect_code_ratio,       \n" +
						"       t1.base_cell_num, defect_cell_num, t3.tkindate,                      \n" +
						"       t3.eventdate AS tkoutdate, t1.matchlottype, t1.bingradecd,           \n" +
						"       t1.defect_group_code, t1.datavalue,                                  \n" +
						"       t3.preprocesstkoutdate AS pre_trk_out                                \n" +
						"  FROM insp_temp2 t1,                                                       \n" +
						"       yms_dm.h_imptdefecteqp t2,                                           \n" +
						"       YMS_DW.DW_GLASSCELL_EVENTHIST t3                                     \n" +
						" WHERE 1 = 1                                                                \n" +
						"   AND t1.line_code = t2.siteid                                             \n" +
						"   AND t1.cfglassid = t2.glasscellid                                        \n" +
						"   AND t2.imptstepgrpid LIKE 'F%%'                                          \n" +
						"   AND t3.eventdate < t1.inspect_dt                                         \n" +
						"   AND t2.siteid = %s                                                       \n" +   // LINE
						"   AND t2.glasscellid = t3.glasscellid                                      \n" +
						"   AND t2.proctime = t3.eventdate                                           \n" +
						"   AND t3.activity = 'TrackOut'                                             \n" +
						"UNION ALL                                                                   \n" +
						"SELECT t1.line_code, t1.user_group_code, t1.process_id, t1.product_id,      \n" +
						"       t1.product_type,                                                     \n" +
						"       CASE                                                                 \n" +
						"          WHEN t2.imptstepgrpid LIKE 'T%%'                                  \n" +
						"             THEN 'TFT'                                                     \n" +
						"          WHEN t2.imptstepgrpid LIKE 'F%%'                                  \n" +
						"             THEN 'CF'                                                      \n" +
						"          WHEN t2.imptstepgrpid LIKE 'L%%'                                  \n" +
						"             THEN 'LC'                                                      \n" +
						"          ELSE 'MOD'                                                        \n" +
						"       END AS area_code,                                                    \n" +
						"       t1.sub_area_code, t2.imptstepgrpid step_id, t2.impteqpid eqp_id,     \n" +
						"       t1.glass_id AS glasscellid, t1.cell_id,                              \n" +
						"       defect_cell_num / t1.base_cell_num AS glass_defect_code_ratio,       \n" +
						"       t1.base_cell_num, defect_cell_num, t3.tkindate,                      \n" +
						"       t3.eventdate AS tkoutdate, t1.matchlottype, t1.bingradecd,           \n" +
						"       t1.defect_group_code, t1.datavalue,                                  \n" +
						"       t3.preprocesstkoutdate AS pre_trk_out                                \n" +
						"  FROM insp_temp2 t1,                                                       \n" +
						"       yms_dm.h_imptdefecteqp t2,                                           \n" +
						"       YMS_DW.DW_GLASSCELL_EVENTHIST t3                                     \n" +
						" WHERE 1 = 1                                                                \n" +
						"   AND t1.line_code = t2.siteid                                             \n" +
						"   AND t1.glass_id = t2.glasscellid                                         \n" +
						"   AND t2.imptstepgrpid LIKE 'L%%'                                          \n" +
						"   AND t3.eventdate < t1.inspect_dt                                         \n" +
						"   AND t2.siteid = %s                                                       \n" +   // LINE
						"   AND t2.glasscellid = t3.glasscellid                                      \n" +
						"   AND t2.proctime = t3.eventdate                                           \n" +
						"   AND t3.activity = 'TrackOut'                                             \n" +
						""
						, "'20140702110000'", "'20140703105959'", "'L8AFAB'", "'L8480F71'", "'32PR'", "'L8AFAB'", "'L8AFAB'", "'L8AFAB'");

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
						"/* WIP_EQP_SMMRY : L7AFAB, L7BFAB, L8AFAB, L8ZFAB - MOD */                   \n" +
						"WITH insp_temp1 AS                                                           \n" +
						"     (SELECT   /*+ FULL(D) PARALLEL (2) */                                   \n" +
						"               t.siteid siteid,                                              \n" +
						"               d.procid,                                                     \n" +
						"               d.celllocid glassid,                                          \n" +
						"               d.glassid AS cellid,                                          \n" +
						"               MAX (dcoldate) insptime,                                      \n" +
						"               MAX (t.cellqty) cellqty,                                      \n" +
						"               MAX (t.prodgrpname) prodgrpname,                              \n" +
						"               MAX (t.prodid) prodid,                                        \n" +
						"               MAX (d.orgstepid) orgstepid,                                  \n" +
						"               MAX (d.measstepgrpid) measstepgrpid,                          \n" +
						"               MAX (d.measeqpunitid) measeqpunitid,                          \n" +
						"               MAX (d.itemid) itemid,                                        \n" +
						"               MAX (d.dcoldate) dcoltime,                                    \n" +
						"               MAX (d.subitemid) subitemid,                                  \n" +
						"               MAX (d.prodtype) prodtype                                     \n" +
						"          FROM yms_dm.h_meas_rundata_cell d, yms_dm.d_product t              \n" +
						"         WHERE dcoldate >= TO_DATE (%s, 'YYYYMMDDHH24MISS')                  \n" +   // FROM_ETL_DT
						"           AND dcoldate <= TO_DATE (%s, 'YYYYMMDDHH24MISS')                  \n" +   // TO_ETL_DT
						"           AND d.prodid = %s          /* PRODID 입력 */                      \n" +   // USER_GROUP_CODE
						"           AND d.prodid = t.prodid                                           \n" +
						"           AND d.siteid = %s                                                 \n" +   // LINE
						"           /* AND d.siteid_eqptype = %s */                                        \n" +   // LINE_ID
						"           AND measstepgrpid = '7M020'                                       \n" +
						"           AND itemid IN                                                     \n" +
						"                  ('APR','BOR','BPR','DDR','DR','OR'                         \n" +
						"                   ,'PR','6.5APR','6.5BOR','6.5BPR'                          \n" +
						"                   ,'6.5DDR','6.5DR','6.5OR','6.5PR'                         \n" +
						"                   ,'16APR','16BOR','16BPR','16DDR'                          \n" +
						"                   ,'16DR','16OR','16PR','32APR'                             \n" +
						"                   ,'32BOR','32BPR','32DDR','32DR','32OR','32PR')            \n" +
						"      GROUP BY t.siteid, d.procid, d.celllocid, d.glassid),                  \n" +
						"     insp_temp2 AS                                                           \n" +
						"     (SELECT /*+ PARALLEL(2) */                                              \n" +
						"             t1.siteid AS line_code, t1.prodgrpname AS user_group_code,      \n" +
						"             t1.procid AS process_id, t1.prodid AS product_id,               \n" +
						"             t1.prodtype AS product_type, 'AMT' sub_area_code,               \n" +
						"             t1.glassid AS glass_id, t1.cellid AS cell_id,                   \n" +
						"             t2.cfglassid cfglassid, t1.cellqty base_cell_num,               \n" +
						"             t1.insptime AS inspect_dt,                                      \n" +
						"             REPLACE (cellid, glassid, '') AS cell_loc_id,                   \n" +
						"             '' AS defect_group_code, '' defect_cell_num, '' decision_code,  \n" +
						"             '' inspector_id, '' bingradecd, '' matchlottype                 \n" +
						"        FROM insp_temp1 t1, yms_dw.dw_glass_match t2                         \n" +
						"       WHERE 1 = 1 AND t1.glassid = t2.tftglassid(+))                        \n" +
						"SELECT t1.line_code, t1.user_group_code, t1.process_id, t1.product_id,       \n" +
						"       t1.product_type,                                                      \n" +
						"       CASE                                                                  \n" +
						"          WHEN t2.imptstepgrpid LIKE 'T%%'                                   \n" +
						"             THEN 'TFT'                                                      \n" +
						"          WHEN t2.imptstepgrpid LIKE 'F%%'                                   \n" +
						"             THEN 'CF'                                                       \n" +
						"          WHEN t2.imptstepgrpid LIKE 'L%%'                                   \n" +
						"             THEN 'LC'                                                       \n" +
						"          ELSE 'MOD'                                                         \n" +
						"       END AS area_code,                                                     \n" +
						"       t1.sub_area_code, t2.imptstepgrpid step_id, t2.impteqpid eqp_id,      \n" +
						"       t1.glass_id AS glasscellid, t1.glass_id cell_id,                      \n" +
						"       defect_cell_num / t1.base_cell_num AS glass_defect_code_ratio,        \n" +
						"       t1.base_cell_num, defect_cell_num, t3.tkindate,                       \n" +
						"       t3.eventdate AS tkoutdate, t1.matchlottype, t1.bingradecd,            \n" +
						"       t1.defect_group_code, t1.decision_code,                               \n" +
						"       t3.preprocesstkoutdate AS pre_trk_out                                 \n" +
						"  FROM insp_temp2 t1,                                                        \n" +
						"       yms_dm.h_imptdefecteqp t2,                                            \n" +
						"       yms_dw.dw_glasscell_eventhist t3                                      \n" +
						" WHERE 1 = 1                                                                 \n" +
						"   AND t1.line_code = t2.siteid                                              \n" +
						"   AND t1.glass_id = t2.glasscellid                                          \n" +
						"   AND t2.imptstepgrpid LIKE 'T%%'                                           \n" +
						"   AND t3.eventdate < t1.inspect_dt                                          \n" +
						"   AND t2.siteid = %s                                                        \n" +   // LINE
						"   AND t2.glasscellid = t3.glasscellid                                       \n" +
						"   AND t2.proctime = t3.eventdate                                            \n" +
						"   AND t3.activity = 'TrackOut'                                              \n" +
						"UNION ALL                                                                    \n" +
						"SELECT t1.line_code, t1.user_group_code, t1.process_id, t1.product_id,       \n" +
						"       t1.product_type,                                                      \n" +
						"       CASE                                                                  \n" +
						"          WHEN t2.imptstepgrpid LIKE 'T%%'                                   \n" +
						"             THEN 'TFT'                                                      \n" +
						"          WHEN t2.imptstepgrpid LIKE 'F%%'                                   \n" +
						"             THEN 'CF'                                                       \n" +
						"          WHEN t2.imptstepgrpid LIKE 'L%%'                                   \n" +
						"             THEN 'LC'                                                       \n" +
						"          ELSE 'MOD'                                                         \n" +
						"       END AS area_code,                                                     \n" +
						"       t1.sub_area_code, t2.imptstepgrpid step_id, t2.impteqpid eqp_id,      \n" +
						"       t1.cfglassid AS glasscellid, t1.cfglassid,                            \n" +
						"       defect_cell_num / t1.base_cell_num AS glass_defect_code_ratio,        \n" +
						"       t1.base_cell_num, defect_cell_num, t3.tkindate,                       \n" +
						"       t3.eventdate AS tkoutdate, t1.matchlottype, t1.bingradecd,            \n" +
						"       t1.defect_group_code, t1.decision_code,                               \n" +
						"       t3.preprocesstkoutdate AS pre_trk_out                                 \n" +
						"  FROM insp_temp2 t1,                                                        \n" +
						"       yms_dm.h_imptdefecteqp t2,                                            \n" +
						"       yms_dw.dw_glasscell_eventhist t3                                      \n" +
						" WHERE 1 = 1                                                                 \n" +
						"   AND t1.line_code = t2.siteid                                              \n" +
						"   AND t1.cfglassid = t2.glasscellid                                         \n" +
						"   AND t2.imptstepgrpid LIKE 'F%%'                                           \n" +
						"   AND t3.eventdate < t1.inspect_dt                                          \n" +
						"   AND t2.siteid = %s                                                        \n" +   // LINE
						"   AND t2.glasscellid = t3.glasscellid                                       \n" +
						"   AND t2.proctime = t3.eventdate                                            \n" +
						"   AND t3.activity = 'TrackOut'                                              \n" +
						"UNION ALL                                                                    \n" +
						"SELECT t1.line_code, t1.user_group_code, t1.process_id, t1.product_id,       \n" +
						"       t1.product_type,                                                      \n" +
						"       CASE                                                                  \n" +
						"          WHEN t2.imptstepgrpid LIKE 'T%%'                                   \n" +
						"             THEN 'TFT'                                                      \n" +
						"          WHEN t2.imptstepgrpid LIKE 'F%%'                                   \n" +
						"             THEN 'CF'                                                       \n" +
						"          WHEN t2.imptstepgrpid LIKE 'L%%'                                   \n" +
						"             THEN 'LC'                                                       \n" +
						"          ELSE 'MOD'                                                         \n" +
						"       END AS area_code,                                                     \n" +
						"       t1.sub_area_code, t2.imptstepgrpid step_id, t2.impteqpid eqp_id,      \n" +
						"       t1.glass_id AS glasscellid, t1.cell_id,                               \n" +
						"       defect_cell_num / t1.base_cell_num AS glass_defect_code_ratio,        \n" +
						"       t1.base_cell_num, defect_cell_num, t3.tkindate,                       \n" +
						"       t3.eventdate AS tkoutdate, t1.matchlottype, t1.bingradecd,            \n" +
						"       t1.defect_group_code, t1.decision_code,                               \n" +
						"       t3.preprocesstkoutdate AS pre_trk_out                                 \n" +
						"  FROM insp_temp2 t1,                                                        \n" +
						"       yms_dm.h_imptdefecteqp t2,                                            \n" +
						"       yms_dw.dw_glasscell_eventhist t3                                      \n" +
						" WHERE 1 = 1                                                                 \n" +
						"   AND t1.line_code = t2.siteid                                              \n" +
						"   AND t1.glass_id = t2.glasscellid                                          \n" +
						"   AND t2.imptstepgrpid LIKE 'L%%'                                           \n" +
						"   AND t3.eventdate < t1.inspect_dt                                          \n" +
						"   AND t2.siteid = %s                                                        \n" +   // LINE
						"   AND t2.glasscellid = t3.glasscellid                                       \n" +
						"   AND t2.proctime = t3.eventdate                                            \n" +
						"   AND t3.activity = 'TrackOut'                                              \n" +
						""
						, "'20140702070000'", "'20140703065959'", "'LSC480HN02-G01'", "'L8AFAB'", "'8AFAB'", "'L8AFAB'", "'L8AFAB'", "'L8AFAB'");

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
