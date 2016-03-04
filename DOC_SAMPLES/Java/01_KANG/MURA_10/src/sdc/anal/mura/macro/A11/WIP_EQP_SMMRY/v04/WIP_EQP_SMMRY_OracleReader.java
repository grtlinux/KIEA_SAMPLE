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

package sdc.anal.mura.macro.A11.WIP_EQP_SMMRY.v04;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import kiea.kr.co.tain.base.Flag;
import kiea.kr.co.tain.util.SystemProperties;
import kiea.proj.sdc.anal.common.Connection04;
import kiea.proj.sdc.anal.common.Parameter;
import kiea.proj.sdc.anal.macro.impl.io.AbstractOracleReader;
import kiea.proj.sdc.anal.util.BasePath;
import kiea.proj.sdc.anal.util.StrUtil;
import kiea.proj.sdc.anal.util.log.v02.Logger;
import oracle.jdbc.OracleConnection;

/**
 * @author KangSeok
 * @date 2014. 8. 8.
 * @file_name WIP_EQP_SMMRY_OracleReader.java
 *
 */
public class WIP_EQP_SMMRY_OracleReader extends AbstractOracleReader
{
	private List<WIP_EQP_SMMRY_ReadBean> list = new ArrayList<WIP_EQP_SMMRY_ReadBean>();
	
	/**
	 * Sample02OracleReader
	 */
	public WIP_EQP_SMMRY_OracleReader()
	{
		if (Flag.TRUE) {
		}
	}

	/**
	 * 
	 * getLine56LC
	 *
	 * @return
	 */
	private String getLine56LC()
	{
		String query;
		
		if (Flag.TRUE) {
			query = String.format("" +
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
					"            '' glass_defect_code_ratio, '' base_cell_num, '' defect_cell_num,      \n" +  // <-
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
					"            '' glass_defect_code_ratio, '' base_cell_num, '' defect_cell_num,      \n" +  // <-
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
					, StrUtil.quote(Parameter.getInstance().getStrFromDateTime())
					, StrUtil.quote(Parameter.getInstance().getStrToDateTime())
					, StrUtil.quote(Parameter.getInstance().getStrLine())
					, StrUtil.quote("L6FABSTDALONE")
					, StrUtil.quote(Parameter.getInstance().getStrUserGroupCode())
					, StrUtil.quote(Parameter.getInstance().getStrDefectGroupCode())
			);
		}
		
		return query;
	}
	
	/**
	 * 
	 * getLine56MOD
	 *
	 * @return
	 */
	private String getLine56MOD()
	{
		String query = null;
		
		query = String.format("" +
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
				"         '' cell_id,                                                                 \n" +  // <-
				"         '' glass_defect_code_ratio, '' base_cell_num, '' defect_cell_num,           \n" +  // <-
				"                                                   t3.tkintime tkindate,             \n" +
				"         t3.eventtime AS tkoutdate, '' matchlottype, '' bingradecd,                  \n" +
				"         t1.defect_group_code,                                                       \n" +
				"                               /*t1.datavalue, */                                    \n" +
				"         '' decision_code,                                                           \n" +
				"         '' datavalue,                                                               \n" +  // <-
				"         t3.tkintime AS pre_trk_out                                                  \n" +
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
				"         REPLACE (t1.glassid, '.', '') glasscellid,                                  \n" +
				"         '' cell_id,                                                                 \n" +  // <-
				"         '' glass_defect_code_ratio, '' base_cell_num, '' defect_cell_num,           \n" +  // <-
				"         t3.tkintime tkindate,                                                       \n" +
				"         t3.eventtime AS tkoutdate, '' matchlottype, '' bingradecd,                  \n" +
				"         '' defect_group_code, '' decision_code,                                     \n" +
				"         '' datavalue,                                                               \n" +  // <-
				"         t3.tkintime AS pre_trk_out                                                  \n" +
				"    FROM insp2 t1, yms_dw.dw_glasscell_eventhist_ca t3, yms_dw.dw_md_step t4         \n" +
				"   WHERE 1 = 1                                                                       \n" +
				"     AND t1.cfglassid = t3.glasscellid                                               \n" +
				"     AND t3.eventtime < t1.inspect_dt                                                \n" +
				"     AND t3.activity = 'TrackOut'                                                    \n" +
				"     AND t3.processid = t4.processid                                                 \n" +
				"     AND t3.stepid = t4.stepid                                                       \n" +
				""
				//, "'20140701110000'", "'20140702105959'", "'LTL097QL01-A01'", "'L6FAB'", "'L6FABSTDALONE'", "'32PR'");
				, StrUtil.quote(Parameter.getInstance().getStrFromDateTime())
				, StrUtil.quote(Parameter.getInstance().getStrToDateTime())
				, StrUtil.quote(Parameter.getInstance().getStrUserGroupCode())
				, StrUtil.quote(Parameter.getInstance().getStrLine())
				, StrUtil.quote("L6FABSTDALONE")
				, StrUtil.quote(Parameter.getInstance().getStrDefectGroupCode())
		);

		return query;
	}
	
	/**
	 * 
	 * getLine78LC
	 *
	 * @return
	 */
	private String getLine78LC()
	{
		String query = null;
		
		query = String.format("" +
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
				"       t1.defect_group_code,                                                \n" +
				"       '' decision_code,                                                    \n" +  // <-
				"       t1.datavalue,                                                        \n" +
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
				"       t1.defect_group_code,                                                \n" +
				"       '' decision_code,                                                    \n" +  // <-
				"       t1.datavalue,                                                        \n" +
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
				"       t1.defect_group_code,                                                \n" +
				"       '' decision_code,                                                    \n" +  // <-
				"       t1.datavalue,                                                        \n" +
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
				//, "'20140702110000'", "'20140703105959'", "'L8AFAB'", "'L8480F71'", "'32PR'", "'L8AFAB'", "'L8AFAB'", "'L8AFAB'");
				, StrUtil.quote(Parameter.getInstance().getStrFromDateTime())
				, StrUtil.quote(Parameter.getInstance().getStrToDateTime())
				, StrUtil.quote(Parameter.getInstance().getStrLine())
				, StrUtil.quote(Parameter.getInstance().getStrUserGroupCode())
				, StrUtil.quote(Parameter.getInstance().getStrDefectGroupCode())
				, StrUtil.quote(Parameter.getInstance().getStrLine())
				, StrUtil.quote(Parameter.getInstance().getStrLine())
				, StrUtil.quote(Parameter.getInstance().getStrLine())
		);

		return query;
	}
	
	/**
	 * 
	 * getLine78MOD
	 *
	 * @return
	 */
	private String getLine78MOD()
	{
		String query = null;
		
		query = String.format("" +
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
				"           /* AND d.siteid_eqptype = %s */                                   \n" +   // LINE_ID
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
				"       '' datavalue,                                                         \n" +  // <-
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
				"       '' datavalue,                                                         \n" +  // <-
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
				"       '' datavalue,                                                         \n" +  // <-
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
				//, "'20140702070000'", "'20140703065959'", "'LSC480HN02-G01'", "'L8AFAB'", "'8AFAB'", "'L8AFAB'", "'L8AFAB'", "'L8AFAB'");
				, StrUtil.quote(Parameter.getInstance().getStrFromDateTime())
				, StrUtil.quote(Parameter.getInstance().getStrToDateTime())
				, StrUtil.quote(Parameter.getInstance().getStrUserGroupCode())
				, StrUtil.quote(Parameter.getInstance().getStrLine())
				, StrUtil.quote("8AFAB")
				, StrUtil.quote(Parameter.getInstance().getStrLine())
				, StrUtil.quote(Parameter.getInstance().getStrLine())
				, StrUtil.quote(Parameter.getInstance().getStrLine())
		);

		return query;
	}
	
	/**
	 * getList
	 */
	@Override
	public List<WIP_EQP_SMMRY_ReadBean> getList()
	{
		return getList(false);
	}
	
	public List<WIP_EQP_SMMRY_ReadBean> getList(boolean flgRead)
	{
		if (Flag.TRUE) {
			if (!flgRead) {
				/*
				 * if flgRead = true, 초기에 읽지않는다.
				 */
				list = new ArrayList<WIP_EQP_SMMRY_ReadBean>();
				return list;
			}
			
			try {
				String query = null;
				
				if ("L5FAB".equals(Parameter.getInstance().getStrLine()) || "L6FAB".equals(Parameter.getInstance().getStrLine())) {
					if ("LC".equals(Parameter.getInstance().getStrAreaCode())) {
						query = getLine56LC();
					} else if ("MOD".equals(Parameter.getInstance().getStrAreaCode())) {
						query = getLine56MOD();
					}
				} else if ("L7AFAB".equals(Parameter.getInstance().getStrLine()) || "L7BFAB".equals(Parameter.getInstance().getStrLine()) || "L8AFAB".equals(Parameter.getInstance().getStrLine()) || "L8ZFAB".equals(Parameter.getInstance().getStrLine())) {
					if ("LC".equals(Parameter.getInstance().getStrAreaCode())) {
						query = getLine78LC();
					} else if ("MOD".equals(Parameter.getInstance().getStrAreaCode())) {
						query = getLine78MOD();
					}
				} else {
					return list;
				}
				
				if (!Flag.TRUE) System.out.println("[" + query + "]");
				if (Flag.TRUE) Logger.info("[" + query + "]");
				
				OracleConnection conn = Connection04.getInstance().getConn();

				if (Flag.TRUE) {
					Statement stmt = conn.createStatement();
					ResultSet rs = stmt.executeQuery(query);
					for (int i=0; i < 1000000 && rs.next(); i++) {
						if (!Flag.TRUE) {
							System.out.println(String.format("%3d) --------------------------------------", i));
							System.out.println(String.format("    LINE_CODE               = [%s]", rs.getString("LINE_CODE"              )));
							System.out.println(String.format("    USER_GROUP_CODE         = [%s]", rs.getString("USER_GROUP_CODE"        )));
							System.out.println(String.format("    PROCESS_ID              = [%s]", rs.getString("PROCESS_ID"             )));
							System.out.println(String.format("    PRODUCT_ID              = [%s]", rs.getString("PRODUCT_ID"             )));
							System.out.println(String.format("    PRODUCT_TYPE            = [%s]", rs.getString("PRODUCT_TYPE"           )));
							System.out.println(String.format("    AREA_CODE               = [%s]", rs.getString("AREA_CODE"              )));
							System.out.println(String.format("    SUB_AREA_CODE           = [%s]", rs.getString("SUB_AREA_CODE"          )));
							System.out.println(String.format("    STEP_ID                 = [%s]", rs.getString("STEP_ID"                )));
							System.out.println(String.format("    EQP_ID                  = [%s]", rs.getString("EQP_ID"                 )));
							System.out.println(String.format("    GLASSCELLID             = [%s]", rs.getString("GLASSCELLID"            )));
							System.out.println(String.format("    CELL_ID                 = [%s]", rs.getString("CELL_ID"                )));
							System.out.println(String.format("    GLASS_DEFECT_CODE_RATIO = [%s]", rs.getString("GLASS_DEFECT_CODE_RATIO")));
							System.out.println(String.format("    BASE_CELL_NUM           = [%s]", rs.getString("BASE_CELL_NUM"          )));
							System.out.println(String.format("    DEFECT_CELL_NUM         = [%s]", rs.getString("DEFECT_CELL_NUM"        )));
							System.out.println(String.format("    TKINDATE                = [%s]", rs.getString("TKINDATE"               )));
							System.out.println(String.format("    TKOUTDATE               = [%s]", rs.getString("TKOUTDATE"              )));
							System.out.println(String.format("    MATCHLOTTYPE            = [%s]", rs.getString("MATCHLOTTYPE"           )));
							System.out.println(String.format("    BINGRADECD              = [%s]", rs.getString("BINGRADECD"             )));
							System.out.println(String.format("    DEFECT_GROUP_CODE       = [%s]", rs.getString("DEFECT_GROUP_CODE"      )));
							System.out.println(String.format("    DECISION_CODE           = [%s]", rs.getString("DECISION_CODE"          )));
							System.out.println(String.format("    DATAVALUE               = [%s]", rs.getString("DATAVALUE"              )));
							System.out.println(String.format("    PRE_TRK_OUT             = [%s]", rs.getString("PRE_TRK_OUT"            )));
						}
						
						if (Flag.TRUE) {
							WIP_EQP_SMMRY_ReadBean bean = new WIP_EQP_SMMRY_ReadBean();

							bean.setLineCode            (rs.getString("LINE_CODE"              ));
							bean.setUserGroupCode       (rs.getString("USER_GROUP_CODE"        ));
							bean.setProcessId           (rs.getString("PROCESS_ID"             ));
							bean.setProductId           (rs.getString("PRODUCT_ID"             ));
							bean.setProductType         (rs.getString("PRODUCT_TYPE"           ));
							bean.setAreaCode            (rs.getString("AREA_CODE"              ));
							bean.setSubAreaCode         (rs.getString("SUB_AREA_CODE"          ));
							bean.setStepId              (rs.getString("STEP_ID"                ));
							bean.setEqpId               (rs.getString("EQP_ID"                 ));
							bean.setGlassCellId         (rs.getString("GLASSCELLID"            ));
							bean.setCellId              (rs.getString("CELL_ID"                ));
							bean.setGlassDefectCodeRatio(rs.getString("GLASS_DEFECT_CODE_RATIO"));
							bean.setBaseCellNum         (rs.getString("BASE_CELL_NUM"          ));
							bean.setDefectCellNum       (rs.getString("DEFECT_CELL_NUM"        ));
							bean.setTkInDate            (rs.getString("TKINDATE"               ));
							bean.setTkOutDate           (rs.getString("TKOUTDATE"              ));
							bean.setMatchLotType        (rs.getString("MATCHLOTTYPE"           ));
							bean.setBinGradeCd          (rs.getString("BINGRADECD"             ));
							bean.setDefectGroupCode     (rs.getString("DEFECT_GROUP_CODE"      ));
							bean.setDecisionCode        (rs.getString("DECISION_CODE"          ));
							bean.setDataValue           (rs.getString("DATAVALUE"              ));
							bean.setPreTrkOut           (rs.getString("PRE_TRK_OUT"            ));
							
							list.add(bean);
						}
					}
					
					if (!Flag.TRUE) {
						System.out.println();
						ResultSetMetaData md = rs.getMetaData();
						for (int i=1; i <= md.getColumnCount(); i++) {
							System.out.println(String.format("%d) [%s] [%s] [%s] [%s]", i, md.getColumnName(i), md.getColumnLabel(i), md.getColumnClassName(i), md.getCatalogName(i)));
						}
					}
				}
				
				try { Thread.sleep(1000); } catch (InterruptedException ex) {};
				
				Connection04.getInstance().close();
				if (!Flag.TRUE) System.out.println("OK!");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		return list;
	}
	
	///////////////////////////////////////////////////////////////////////////
	
	private static void test01()
	{
		if (Flag.TRUE) {
			SystemProperties.setTesting("020");
			BasePath.getInstance();
			try {
				Logger.getInstance(WIP_EQP_SMMRY_Main.jobId, WIP_EQP_SMMRY_Main.dsName + ".log");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		if (Flag.TRUE) {
			Parameter.getInstance(WIP_EQP_SMMRY_Main.jobId);
			
			WIP_EQP_SMMRY_OracleReader reader = new WIP_EQP_SMMRY_OracleReader();
			
			for (WIP_EQP_SMMRY_ReadBean bean : reader.getList(true)) {
				System.out.println(bean);
			}
			
			System.out.println("the read ok !!!");
		}

		Logger.exit();
	}
	
	public static void main(String[] args)
	{
		if (Flag.TRUE) test01();
	}
}
