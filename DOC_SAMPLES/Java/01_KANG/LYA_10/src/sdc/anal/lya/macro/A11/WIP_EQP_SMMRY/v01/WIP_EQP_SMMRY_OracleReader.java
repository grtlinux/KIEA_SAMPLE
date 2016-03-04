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

package sdc.anal.lya.macro.A11.WIP_EQP_SMMRY.v01;

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
 * @file_name AIB_WIP_EQP_SMMRY_OracleReader.java
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
	 * getLine56TFT
	 *
	 * @return
	 */
	private String getLine56TFT()
	{
		String query = null;
		
		query = String.format("" +
				"/* AIB_WIP_EQP_SMMRY : 5,6 : WIP-TFT-EQP */                                 \n" +
				"WITH insp AS (                                                              \n" +
				"    SELECT   /*+ PARALLEL(2)  */                                            \n" +
				"        t1.siteid AS line_code,                                             \n" +
				"        t2.prodgrpname AS user_group_code,                                  \n" +
				"        t2.procid AS process_id,                                            \n" +
				"        MAX (t1.prodid) AS product_id,                                      \n" +
				"        t1.prodtype AS product_type,                                        \n" +
				"        t1.inspsteptype AS sub_area_code,                                   \n" +
				"        CASE WHEN SUBSTR (t1.glassid, 8, 1) BETWEEN 'A' AND 'Z'             \n" +
				"            THEN SUBSTR(t1.glassid, 1, 7)||''||SUBSTR(t1.glassid, 8)        \n" +
				"            ELSE SUBSTR(t1.glassid, 1, 7)||''||SUBSTR(t1.glassid, 8)        \n" +
				"            END AS glass_id,                                                \n" +
				"        t2.cellqty base_cell_num,                                           \n" +
				"        t1.glassid,                                                         \n" +
				"        t1.inspsteptype,                                                    \n" +
				"        MAX (t1.inspdate) AS inspect_dt,                                    \n" +
				"        decgradecd                                                          \n" +
				"    FROM                                                                    \n" +
				"        yms_dm.d_product    t2,                                             \n" +
				"        yms_dm.h_inspdefect t1                                              \n" +
				"    WHERE                                                                   \n" +
				"        t1.siteid           =  %s /* L6 => L6FAB 형태로 바뀜 */             \n" +  // LINE
				"        AND t1.insphour     >= %s                                           \n" +  // FROM_ETL_HOUR
				"        AND t1.insphour     <= %s                                           \n" +  // TO_ETL_HOUR
				"        AND t2.prodgrpname  IN (%s)                                         \n" +  // USER_GROUP_CODE
				"        AND t1.prodtype     IN (%s)                                         \n" +  // PRODUCT_TYPE
				"        AND t1.inspsteptype IN (%s)                                         \n" +  // SUB_AREA_CODE
				"        AND t1.prodid       =  t2.prodid                                    \n" +
				"    GROUP BY                                                                \n" +
				"        t1.siteid,                                                          \n" +
				"        t2.prodgrpname,                                                     \n" +
				"        t2.procid,                                                          \n" +
				"        t1.prodtype,                                                        \n" +
				"        t1.inspsteptype,                                                    \n" +
				"        CASE WHEN SUBSTR (t1.glassid, 8, 1) BETWEEN 'A' AND 'Z'             \n" +
				"            THEN SUBSTR(t1.glassid, 1, 7)||''||SUBSTR(t1.glassid, 8)        \n" +
				"            ELSE SUBSTR(t1.glassid, 1, 7)||''||SUBSTR(t1.glassid, 8)        \n" +
				"            END,                                                            \n" +
				"        t2.cellqty,                                                         \n" +
				"        t1.glassid,                                                         \n" +
				"        t1.inspsteptype,                                                    \n" +
				"                                                                            \n" +
				"        t1.siteid || 'FAB',                                                 \n" +
				"        t2.prodgrpname,                                                     \n" +
				"        t2.procid,                                                          \n" +
				"        t1.prodtype,                                                        \n" +
				"        t1.inspsteptype,                                                    \n" +
				"        CASE WHEN SUBSTR (t1.glassid, 8, 1) BETWEEN 'A' AND 'Z'             \n" +
				"            THEN SUBSTR(t1.glassid, 1, 7)||'.'||SUBSTR(t1.glassid, 8)       \n" +
				"            ELSE SUBSTR(t1.glassid, 1, 7)||'.'||SUBSTR(t1.glassid, 8)       \n" +
				"            END,                                                            \n" +
				"        t2.cellqty,                                                         \n" +
				"        t1.glassid,                                                         \n" +
				"        t1.inspsteptype,                                                    \n" +
				"        decgradecd                                                          \n" +
				"),                                                                          \n" +
				"insp2 AS (                                                                  \n" +
				"    SELECT  /*+ USE_HASH ( T1 T2 ) */                                       \n" +
				"        DISTINCT t1.line_code, t1.user_group_code, t1.process_id,           \n" +
				"        t1.product_id, t1.product_type, t1.sub_area_code,                   \n" +
				"        t1.glass_id, t1.glassid, '' cell_id,                                \n" +
				"        CASE WHEN t1.inspsteptype = 'EDS' THEN '-'                          \n" +
				"            ELSE t3.glassid                                                 \n" +
				"            END cfglassid,                                                  \n" +
				"        t1.base_cell_num, t1.inspect_dt, '' cell_loc_id,                    \n" +
				"        '' defect_group_code,                                               \n" +
				"        COUNT (CASE WHEN decgradecd IN ('RJ', 'NF', 'NR', 'LR', 'DF', 'DR') \n" +
				"            THEN 1                                                          \n" +
				"            ELSE 0                                                          \n" +
				"            END                                                             \n" +
				"        ) OVER (PARTITION BY t1.glassid) defect_cell_num,                   \n" +
				"        '' decision_code, '' inspector_id, '' bingradecd,                   \n" +
				"        '' matchlottype                                                     \n" +
				"    FROM                                                                    \n" +
				"        insp                     t1,                                        \n" +
				"        yms_dw.dw_glass_match_ca t3                                         \n" +
				"    WHERE                                                                   \n" +
				"        t1.glassid = t3.glassid(+)                                          \n" +
				")                                                                           \n" +
				"SELECT /*+ ORDERED */                                                       \n" +
				"    t1.line_code, t1.user_group_code, t1.process_id, t1.product_id,         \n" +
				"    t1.product_type,                                                        \n" +
				"    CASE                                                                    \n" +
				"        WHEN (t3.stepid LIKE 'T%%' OR t3.stepid LIKE 'A%%') THEN 'TFT'      \n" +
				"        WHEN (                                                              \n" +
				"            t3.stepid    LIKE 'F%%'                                         \n" +
				"            OR t3.stepid LIKE 'B%%'                                         \n" +
				"            OR t3.stepid LIKE 'E%%'                                         \n" +
				"        )                                                                   \n" +
				"        THEN 'CF'                                                           \n" +
				"        WHEN (t3.stepid LIKE 'L%%' OR t3.stepid LIKE 'C%%') THEN 'LC'       \n" +
				"        ELSE 'MOD'                                                          \n" +
				"        END AS area_code,                                                   \n" +
				"    t1.sub_area_code, t4.stepgroupid step_id, t3.eqpid AS eqp_id,           \n" +
				"    REPLACE (t1.glass_id, '.', '') glass_id, '' cell_id,                    \n" +
				"    defect_cell_num / t1.base_cell_num AS glass_defect_code_ratio,          \n" +
				"    t1.base_cell_num, defect_cell_num, t3.tkintime trck_in_time,            \n" +
				"    t3.eventtime AS trck_out_time,                                          \n" +
				"    '' match_lot_type,                                                      \n" +
				"    '' bin_grade_code,                                                      \n" +
				"    '' defect_group_code, '' decision_code, t3.tkintime AS pre_trk_out      \n" +
				"FROM                                                                        \n" +
				"    insp2                             t1,                                   \n" +
				"    yms_dw.dw_glasscell_eventhist_ca  t3,                                   \n" +
				"    yms_dw.dw_md_step                 t4                                    \n" +
				"WHERE 1 = 1                                                                 \n" +
				"    AND t1.glass_id  = t3.glasscellid                                       \n" +
				"    AND t3.eventtime < t1.inspect_dt                                        \n" +
				"    AND t3.activity  = 'TrackOut'                                           \n" +
				"    AND t3.processid = t4.processid                                         \n" +
				"    AND t3.stepid    = t4.stepid                                            \n" +
				""
				, StrUtil.quote(Parameter.getInstance().getStrLine())
				, StrUtil.quote(Parameter.getInstance().getStrFromDateTime())
				, StrUtil.quote(Parameter.getInstance().getStrToDateTime())
				, StrUtil.quote(Parameter.getInstance().getStrUserGroupCode())
				, StrUtil.quote(Parameter.getInstance().getStrProductType())
				, StrUtil.quote(Parameter.getInstance().getStrSubAreaCode())
				);

		return query;
	}

	/**
	 * 
	 * getLine56LCMOD
	 *
	 * @return
	 */
	private String getLine56LCMOD()
	{
		String query = null;
		
		query = String.format("" +
				"/* AIB_WIP_EQP_SMMRY : 5,6 : WIP-LC/MOD-EQP */                            \n" +
				"WITH insp AS (                                                            \n" +
				"    SELECT   /*+ PARALLEL(2) */                                           \n" +
				"        t1.siteid AS line_code,                                           \n" +
				"        t2.prodgrpname AS user_group_code,                                \n" +
				"        t2.procid AS process_id,                                          \n" +
				"        MAX (t1.prodid) AS product_id,                                    \n" +
				"        t1.prodtype AS product_type,                                      \n" +
				"        t1.inspsteptype AS sub_area_code,                                 \n" +
				"        CASE                                                              \n" +
				"          WHEN SUBSTR(t1.glassid, 8, 1) BETWEEN 'A' AND 'Z'               \n" +
				"          THEN SUBSTR(t1.glassid, 1, 7)||'.'||SUBSTR(t1.glassid, 8)       \n" +
				"          ELSE SUBSTR(t1.glassid, 1, 7)||'.'||SUBSTR(t1.glassid, 8)       \n" +
				"        END AS glass_id,                                                  \n" +
				"        t2.cellqty base_cell_num,                                         \n" +
				"        t1.glassid,                                                       \n" +
				"        t1.inspsteptype,                                                  \n" +
				"        MAX (t1.inspdate) AS inspect_dt,                                  \n" +
				"        decgradecd                                                        \n" +
				"    FROM                                                                  \n" +
				"        yms_dm.d_product t2,                                              \n" +
				"        yms_dm.h_inspdefect t1                                            \n" +
				"    WHERE                                                                 \n" +
				"        t1.siteid           =  %s /* L6 => L6FAB */                       \n" +  // LINE
				"        AND t1.insphour     >= %s                                         \n" +  // FROM_ETL_HOUR
				"        AND t1.insphour     <= %s                                         \n" +  // TO_ETL_HOUR
				"        AND t2.prodgrpname  IN (%s)                                       \n" +  // USER_GROUP_CODE
				"        AND t1.prodtype     IN (%s)                                       \n" +  // PRODUCT_TYPE
				"        AND t1.inspsteptype IN (%s)                                       \n" +  // SUB_AREA_CODE
				"        AND t1.prodid       =  t2.prodid                                  \n" +
				"    GROUP BY                                                              \n" +
				"        t1.siteid,                                                        \n" +
				"        t2.prodgrpname,                                                   \n" +
				"        t2.procid,                                                        \n" +
				"        t1.prodtype,                                                      \n" +
				"        t1.inspsteptype,                                                  \n" +
				"        CASE                                                              \n" +
				"          WHEN SUBSTR(t1.glassid, 8, 1) BETWEEN 'A' AND 'Z'               \n" +
				"          THEN SUBSTR(t1.glassid, 1, 7)||'.'||SUBSTR(t1.glassid, 8)       \n" +
				"          ELSE SUBSTR(t1.glassid, 1, 7)||'.'||SUBSTR(t1.glassid, 8)       \n" +
				"        END,                                                              \n" +
				"        t2.cellqty,                                                       \n" +
				"        t1.glassid,                                                       \n" +
				"        t1.inspsteptype,                                                  \n" +
				"        t1.siteid,                                                        \n" +
				"        t2.prodgrpname,                                                   \n" +
				"        t2.procid,                                                        \n" +
				"        t1.prodtype,                                                      \n" +
				"        t1.inspsteptype,                                                  \n" +
				"        CASE                                                              \n" +
				"          WHEN SUBSTR(t1.glassid, 8, 1) BETWEEN 'A' AND 'Z'               \n" +
				"          THEN SUBSTR(t1.glassid, 1, 7)||'.'||SUBSTR(t1.glassid, 8)       \n" +
				"          ELSE SUBSTR(t1.glassid, 1, 7)||'.'||SUBSTR(t1.glassid, 8)       \n" +
				"        END,                                                              \n" +
				"        t2.cellqty,                                                       \n" +
				"        t1.glassid,                                                       \n" +
				"        t1.inspsteptype,                                                  \n" +
				"        decgradecd                                                        \n" +
				"),                                                                        \n" +
				"insp2 AS (                                                                \n" +
				"    SELECT          /*+ USE_HASH ( T1 T2 )  */                            \n" +
				"        DISTINCT t1.line_code, t1.user_group_code, t1.process_id,         \n" +
				"        t1.product_id, t1.product_type, t1.sub_area_code,                 \n" +
				"        t1.glass_id, t1.glassid, '' cell_id,                              \n" +
				"        t3.matglassid AS cfglassid, t1.base_cell_num,                     \n" +
				"        t1.inspect_dt, '' cell_loc_id, '' defect_group_code,              \n" +
				"        COUNT (CASE                                                       \n" +
				"            WHEN decgradecd IN ('RJ', 'NF', 'NR', 'LR', 'DF', 'DR')       \n" +
				"                THEN 1 ELSE 0                                             \n" +
				"            END                                                           \n" +
				"            ) OVER (PARTITION BY t1.glassid) defect_cell_num,             \n" +
				"        '' decision_code, '' inspector_id, '' bingradecd,                 \n" +
				"        '' matchlottype                                                   \n" +
				"    FROM                                                                  \n" +
				"        insp t1,                                                          \n" +
				"        yms_dw.dw_glass_match_ca t3                                       \n" +
				"    WHERE                                                                 \n" +
				"        t1.glassid = t3.glassid(+)                                        \n" +
				")                                                                         \n" +
				"SELECT /*+ ORDERED */                                                     \n" +
				"    t1.line_code, t1.user_group_code, t1.process_id, t1.product_id,       \n" +
				"    t1.product_type,                                                      \n" +
				"    CASE                                                                  \n" +
				"        WHEN (t3.stepid LIKE 'T%%' OR t3.stepid LIKE 'A%%') THEN 'TFT'    \n" +
				"        WHEN (                                                            \n" +
				"            t3.stepid    LIKE 'F%%'                                       \n" +
				"            OR t3.stepid LIKE 'B%%'                                       \n" +
				"            OR t3.stepid LIKE 'E%%'                                       \n" +
				"           ) THEN 'CF'                                                    \n" +
				"        WHEN (t3.stepid LIKE 'L%%' OR t3.stepid LIKE 'C%%') THEN 'LC'     \n" +
				"        ELSE 'MOD'                                                        \n" +
				"        END AS area_code,                                                 \n" +
				"    t1.sub_area_code, t4.stepgroupid step_id, t3.eqpid AS eqp_id,         \n" +
				"    REPLACE (t1.glass_id, '.', '') glass_id, '' cell_id,                  \n" +
				"    defect_cell_num / t1.base_cell_num AS glass_defect_code_ratio,        \n" +
				"    t1.base_cell_num, defect_cell_num, t3.tkintime trck_in_time,          \n" +
				"    t3.eventtime AS trck_out_time,                                        \n" +
				"    '' match_lot_type,                                                    \n" +
				"    '' bin_grade_code,                                                    \n" +
				"    '' defect_group_code, '' decision_code, t3.tkintime AS pre_trk_out    \n" +
				"FROM                                                                      \n" +
				"    insp2                             t1,                                 \n" +
				"    yms_dw.dw_glasscell_eventhist_ca  t3,                                 \n" +
				"    yms_dw.dw_md_step                 t4                                  \n" +
				"WHERE 1 = 1                                                               \n" +
				"    AND t1.glass_id  = t3.glasscellid                                     \n" +
				"    AND t3.eventtime < t1.inspect_dt                                      \n" +
				"    AND t3.activity  = 'TrackOut'                                         \n" +
				"    AND t3.processid = t4.processid                                       \n" +
				"    AND t3.stepid    = t4.stepid                                          \n" +
				"UNION ALL                                                                 \n" +
				"SELECT /*+ ORDERED */                                                     \n" +
				"    t1.line_code, t1.user_group_code, t3.processid, t3.productid,         \n" +
				"    t3.productiontype, 'CF' area_code, t1.sub_area_code,                  \n" +
				"    t4.stepgroupid step_id, t3.eqpid AS eqp_id,                           \n" +
				"    REPLACE (t1.glass_id, '.', '') glass_id, t1.cell_id,                  \n" +
				"    defect_cell_num / t1.base_cell_num AS glass_defect_code_ratio,        \n" +
				"    t1.base_cell_num, defect_cell_num, t3.tkintime trck_in_time,          \n" +
				"    t3.eventtime AS trck_out_time,                                        \n" +
				"    t1.matchlottype as match_lot_type,                                    \n" +
				"    t1.bingradecd as bin_grade_code,                                      \n" +
				"    t1.defect_group_code, t1.decision_code, t3.tkintime AS pre_trk_out    \n" +
				"FROM                                                                      \n" +
				"    insp2                              t1,                                \n" +
				"    yms_dw.dw_glasscell_eventhist_ca   t3,                                \n" +
				"    yms_dw.dw_md_step                  t4                                 \n" +
				"WHERE 1 = 1                                                               \n" +
				"    AND t1.cfglassid = t3.glasscellid                                     \n" +
				"    AND t3.eventtime < t1.inspect_dt                                      \n" +
				"    AND t3.activity  = 'TrackOut'                                         \n" +
				"    AND t3.processid = t4.processid                                       \n" +
				"    AND t3.stepid    = t4.stepid                                          \n" +
				""
				, StrUtil.quote(Parameter.getInstance().getStrLine())
				, StrUtil.quote(Parameter.getInstance().getStrFromDateTime())
				, StrUtil.quote(Parameter.getInstance().getStrToDateTime())
				, StrUtil.quote(Parameter.getInstance().getStrUserGroupCode())
				, StrUtil.quote(Parameter.getInstance().getStrProductType())
				, StrUtil.quote(Parameter.getInstance().getStrSubAreaCode())
				);

		return query;
	}

	/**
	 * 
	 * getLine78TFT
	 *
	 * @return
	 */
	private String getLine78TFT()
	{
		String query = null;
		
		query = String.format("" +
				"/* AIB_WIP_EQP_SMMRY : 7,8 : WIP-TFT-EQP */                          \n" +
				"WITH insp_temp as (                                                  \n" +
				"    select /*+ PARALLEL(2) */                                        \n" +
				"        t1.*, t2.prodgrpname, t2.cellqty                             \n" +
				"    from                                                             \n" +
				"        yms_dm.d_product t2,                                         \n" +
				"        yms_dm.h_inspdefect t1                                       \n" +
				"    where                                                            \n" +
				"        t1.siteid           =  %s /* 8A => L8AFAB 으로 변경 */       \n" +  // LINE
				"        AND t1.insphour     >= %s                                    \n" +  // FROM_ETL_HOUR
				"        AND t1.insphour     <= %s                                    \n" +  // TO_ETL_HOUR
				"        AND t2.prodgrpname  IN (%s)                                  \n" +  // USER_GROUP_CODE
				"        AND t1.prodtype     IN (%s)                                  \n" +  // PRODUCT_TYPE
				"        AND t1.inspsteptype IN (%s)                                  \n" +  // SUB_AREA_CODE
				"        AND t1.prodid       =  t2.prodid                             \n" +
				")                                                                    \n" +
				", insp_temp2 AS (                                                    \n" +
				"    SELECT /*+ PARALLEL(2) */                                        \n" +
				"        t1.siteid AS line_code,                                      \n" +
				"        t1.prodgrpname AS user_group_code,                           \n" +
				"        t1.procid AS process_id,                                     \n" +
				"        t1.prodid AS product_id,                                     \n" +
				"        t1.prodtype AS product_type,                                 \n" +
				"        t1.inspsteptype AS sub_area_code,                            \n" +
				"        t1.glassid AS glass_id,                                      \n" +
				"        t1.cellid AS cell_id,                                        \n" +
				"        CASE WHEN t1.inspsteptype = 'EDS' THEN '-'                   \n" +
				"            ELSE NVL(t2.cfglassid,'-') END cfglassid,                \n" +
				"        t1.cellqty base_cell_num,                                    \n" +
				"        t1.inspdate AS inspect_dt,                                   \n" +
				"        REPLACE (cellid, glassid, '') AS cell_loc_id,                \n" +
				"        t1.defectcd AS defect_group_code,                            \n" +
				"        SUM (CASE                                                    \n" +
				"            WHEN decgradecd IN ('RJ', 'NF', 'NR', 'LR', 'DF', 'DR')  \n" +
				"            THEN 1 ELSE 0                                            \n" +
				"            END                                                      \n" +
				"        ) OVER (PARTITION BY glassid) defect_cell_num,               \n" +
				"        t1.decgradecd AS decision_code,                              \n" +
				"        t1.workerid AS inspector_id,                                 \n" +
				"        t1.bingradecd,                                               \n" +
				"        t1.matchlottype                                              \n" +
				"    FROM                                                             \n" +
				"        insp_temp              t1,                                   \n" +
				"        YMS_DW.DW_GLASS_MATCH  t2                                    \n" +
				"    WHERE                                                            \n" +
				"        t1.glassid = t2.tftglassid(+)                                \n" +
				")                                                                    \n" +
				"SELECT  /*+ PARALLEL(2) */                                           \n" +
				"    t1.line_code,                                                    \n" +
				"    t1.user_group_code,                                              \n" +
				"    t1.process_id,                                                   \n" +
				"    t1.product_id,                                                   \n" +
				"    t1.product_type,                                                 \n" +
				"    CASE                                                             \n" +
				"        WHEN t2.imptstepgrpid LIKE 'T%%' THEN 'TFT'                  \n" +
				"        WHEN t2.imptstepgrpid LIKE 'F%%' THEN 'CF'                   \n" +
				"        WHEN t2.imptstepgrpid LIKE 'L%%' THEN 'LC'                   \n" +
				"        ELSE 'MOD'                                                   \n" +
				"        END AS area_code,                                            \n" +
				"    t1.sub_area_code,                                                \n" +
				"    t2.imptstepgrpid step_id,                                        \n" +
				"    t2.impteqpid eqp_id,                                             \n" +
				"    t1.glass_id,                                                     \n" +
				"    t1.cell_id,                                                      \n" +
				"    defect_cell_num / t1.base_cell_num AS glass_defect_code_ratio,   \n" +
				"    t1.base_cell_num,                                                \n" +
				"    defect_cell_num,                                                 \n" +
				"    t3.tkindate as trck_in_time,                                     \n" +
				"    t3.eventdate as trck_out_time,                                   \n" +
				"    t1.matchlottype as match_lot_type,                               \n" +
				"    t1.bingradecd as bin_grade_code,                                 \n" +
				"    t1.defect_group_code,                                            \n" +
				"    t1.decision_code,                                                \n" +
				"    t3.preprocesstkoutdate as pre_trk_out                            \n" +
				"FROM                                                                 \n" +
				"    insp_temp2                      t1,                              \n" +
				"    yms_dm.h_imptdefecteqp          t2,                              \n" +
				"    YMS_DW.DW_GLASSCELL_EVENTHIST   t3                               \n" +
				"WHERE 1 = 1                                                          \n" +
				"    AND t1.line_code   = t2.siteid                                   \n" +
				"    AND t1.glass_id    = t2.glasscellid                              \n" +
				"    AND t3.eventdate   < t1.inspect_dt                               \n" +
				"    AND t2.siteid      = %s /* 8A => L8AFAB 으로 변경 */             \n" +  // LINE
				"    AND t2.glasscellid = t3.glasscellid                              \n" +
				"    AND T3.STEPID LIKE T2.IMPTSTEPGRPID                              \n" +
				"    AND t3.activity    = 'TrackOut'                                  \n" +
				""
				, StrUtil.quote(Parameter.getInstance().getStrLine())
				, StrUtil.quote(Parameter.getInstance().getStrFromDateTime())
				, StrUtil.quote(Parameter.getInstance().getStrToDateTime())
				, StrUtil.quote(Parameter.getInstance().getStrUserGroupCode())
				, StrUtil.quote(Parameter.getInstance().getStrProductType())
				, StrUtil.quote(Parameter.getInstance().getStrSubAreaCode())
				, StrUtil.quote(Parameter.getInstance().getStrLine())
				);

		return query;
	}

	/**
	 * 
	 * getLine78LCMOD
	 *
	 * @return
	 */
	private String getLine78LCMOD()
	{
		String query = null;
		
		query = String.format("" +
				"/* AIB_WIP_EQP_SMMRY : 7,8 : WIP-LC/MOD-EQP */                            \n" +
				"WITH insp_temp1 AS (                                                      \n" +
				"    SELECT /*+ PARALLEL(2) full(t2) */                                    \n" +
				"        t1.*, t2.prodgrpname, t2.cellqty                                  \n" +
				"    FROM                                                                  \n" +
				"        yms_dm.d_product    t2,                                           \n" +
				"        yms_dm.h_inspdefect t1                                            \n" +
				"    WHERE                                                                 \n" +
				"        t1.siteid           =  %s /*7A => L7AFAB 으로 변경됨 */           \n" +  // LINE
				"        AND t1.insphour     >= %s                                         \n" +  // FROM_ETL_HOUR
				"        AND t1.insphour     <= %s                                         \n" +  // TO_ETL_HOUR
				"        AND t2.prodgrpname  IN (%s)                                       \n" +  // USER_GROUP_CODE
				"        AND t1.prodtype     IN (%s)                                       \n" +  // PRODUCT_TYPE
				"        AND t1.inspsteptype IN (%s)                                       \n" +  // SUB_AREA_CODE
				"        AND t1.prodid       =  t2.prodid                                  \n" +
				"),                                                                        \n" +
				"insp_temp2 AS (                                                           \n" +
				"    SELECT /*+ PARALLEL(2) */                                             \n" +
				"        t1.siteid AS line_code, t1.prodgrpname AS user_group_code,        \n" +
				"        t1.procid AS process_id, t1.prodid AS product_id,                 \n" +
				"        t1.prodtype AS product_type, t1.inspsteptype AS sub_area_code,    \n" +
				"        t1.glassid AS glass_id, t1.cellid AS cell_id,                     \n" +
				"        CASE                                                              \n" +
				"            WHEN t1.inspsteptype = 'EDS' THEN '-'                         \n" +
				"            ELSE NVL (t2.cfglassid, '-')                                  \n" +
				"            END cfglassid,                                                \n" +
				"        t1.cellqty base_cell_num, t1.inspdate AS inspect_dt,              \n" +
				"        REPLACE (cellid, glassid, '') AS cell_loc_id,                     \n" +
				"        t1.defectcd AS defect_group_code,                                 \n" +
				"        SUM (CASE                                                         \n" +
				"            WHEN decgradecd IN ('RJ', 'NF', 'NR', 'LR', 'DF', 'DR')       \n" +
				"            THEN 1 ELSE 0                                                 \n" +
				"            END                                                           \n" +
				"            ) OVER (PARTITION BY glassid) defect_cell_num,                \n" +
				"        t1.decgradecd AS decision_code, t1.workerid AS inspector_id,      \n" +
				"        t1.bingradecd, t1.matchlottype                                    \n" +
				"    FROM                                                                  \n" +
				"        insp_temp1 t1,                                                    \n" +
				"        yms_dw.dw_glass_match t2                                          \n" +
				"    WHERE 1 = 1                                                           \n" +
				"        AND t1.glassid = t2.tftglassid(+)                                 \n" +
				")                                                                         \n" +
				"SELECT                                                                    \n" +
				"    t1.line_code, t1.user_group_code, t1.process_id, t1.product_id,       \n" +
				"    t1.product_type,                                                      \n" +
				"    CASE                                                                  \n" +
				"        WHEN t2.imptstepgrpid LIKE 'T%%' THEN 'TFT'                       \n" +
				"        WHEN t2.imptstepgrpid LIKE 'F%%' THEN 'CF'                        \n" +
				"        WHEN t2.imptstepgrpid LIKE 'L%%' THEN 'LC'                        \n" +
				"        ELSE 'MOD'                                                        \n" +
				"        END AS area_code,                                                 \n" +
				"    t1.sub_area_code, t2.imptstepgrpid step_id, t2.impteqpid eqp_id,      \n" +
				"    t1.glass_id, t1.cell_id,                                              \n" +
				"    defect_cell_num / t1.base_cell_num AS glass_defect_code_ratio,        \n" +
				"    t1.base_cell_num, defect_cell_num, t3.tkindate as trck_in_time,       \n" +
				"    t3.eventdate AS trck_out_time,                                        \n" +
				"    t1.matchlottype as match_lot_type,                                    \n" +
				"    t1.bingradecd as bin_grade_code,                                      \n" +
				"    t1.defect_group_code, t1.decision_code,                               \n" +
				"    t3.preprocesstkoutdate AS pre_trk_out                                 \n" +
				"FROM                                                                      \n" +
				"    insp_temp2 t1,                                                        \n" +
				"    yms_dm.h_imptdefecteqp t2,                                            \n" +
				"    yms_dw.dw_glasscell_eventhist t3                                      \n" +
				"WHERE 1 = 1                                                               \n" +
				"    AND t1.line_code   = t2.siteid                                        \n" +
				"    AND t1.line_code   = t3.siteid                                        \n" +
				"    AND t1.glass_id    = t2.glasscellid                                   \n" +
				"    AND t2.imptstepgrpid LIKE 'T%%'                                       \n" +
				"    AND t3.eventdate   < t1.inspect_dt                                    \n" +
				"    AND t2.glasscellid = t3.glasscellid                                   \n" +
				"    AND t3.stepid LIKE t2.imptstepgrpid                                   \n" +
				"    AND t3.activity    = 'TrackOut'                                       \n" +
				"UNION ALL                                                                 \n" +
				"SELECT                                                                    \n" +
				"    t1.line_code,                                                         \n" +
				"    t1.user_group_code,                                                   \n" +
				"    t1.process_id,                                                        \n" +
				"    t1.product_id,                                                        \n" +
				"    t1.product_type,                                                      \n" +
				"    CASE                                                                  \n" +
				"        WHEN t2.imptstepgrpid LIKE 'T%%' THEN 'TFT'                       \n" +
				"        WHEN t2.imptstepgrpid LIKE 'F%%' THEN 'CF'                        \n" +
				"        WHEN t2.imptstepgrpid LIKE 'L%%' THEN 'LC'                        \n" +
				"        ELSE 'MOD'                                                        \n" +
				"        END AS area_code,                                                 \n" +
				"    t1.sub_area_code,                                                     \n" +
				"    t2.imptstepgrpid step_id,                                             \n" +
				"    t2.impteqpid eqp_id,                                                  \n" +
				"    t1.glass_id,                                                          \n" +
				"    t1.cell_id,                                                           \n" +
				"    defect_cell_num / t1.base_cell_num AS glass_defect_code_ratio,        \n" +
				"    t1.base_cell_num,                                                     \n" +
				"    defect_cell_num,                                                      \n" +
				"    t3.tkindate as trck_in_time,                                          \n" +
				"    t3.eventdate as trck_out_time,                                        \n" +
				"    t1.matchlottype as match_lot_type,                                    \n" +
				"    t1.bingradecd as bin_grade_code,                                      \n" +
				"    t1.defect_group_code,                                                 \n" +
				"    t1.decision_code,                                                     \n" +
				"    t3.preprocesstkoutdate as pre_trk_out                                 \n" +
				"FROM                                                                      \n" +
				"    insp_temp2                    t1,                                     \n" +
				"    yms_dm.h_imptdefecteqp        t2,                                     \n" +
				"    yms_dw.dw_glasscell_eventhist t3                                      \n" +
				"WHERE 1 = 1                                                               \n" +
				"    AND t1.line_code   = t2.siteid                                        \n" +
				"    AND t1.glass_id    = t2.glasscellid                                   \n" +
				"    AND t2.imptstepgrpid LIKE 'F%%'                                       \n" +
				"    AND t3.eventdate   < t1.inspect_dt                                    \n" +
				"    AND t2.siteid      = %s                                               \n" +  // LINE
				"    AND t2.glasscellid = t3.glasscellid                                   \n" +
				"    AND T3.STEPID LIKE T2.IMPTSTEPGRPID                                   \n" +
				"    AND t3.activity    = 'TrackOut'                                       \n" +
				"UNION ALL                                                                 \n" +
				"SELECT                                                                    \n" +
				"    t1.line_code,                                                         \n" +
				"    t1.user_group_code,                                                   \n" +
				"    t1.process_id,                                                        \n" +
				"    t1.product_id,                                                        \n" +
				"    t1.product_type,                                                      \n" +
				"    CASE                                                                  \n" +
				"        WHEN t2.imptstepgrpid LIKE 'T%%' THEN 'TFT'                       \n" +
				"        WHEN t2.imptstepgrpid LIKE 'F%%' THEN 'CF'                        \n" +
				"        WHEN t2.imptstepgrpid LIKE 'L%%' THEN 'LC'                        \n" +
				"        ELSE 'MOD'                                                        \n" +
				"        END AS area_code,                                                 \n" +
				"    t1.sub_area_code,                                                     \n" +
				"    t2.imptstepgrpid step_id,                                             \n" +
				"    t2.impteqpid eqp_id,                                                  \n" +
				"    t1.glass_id,                                                          \n" +
				"    t1.cell_id,                                                           \n" +
				"    defect_cell_num / t1.base_cell_num AS glass_defect_code_ratio,        \n" +
				"    t1.base_cell_num,                                                     \n" +
				"    defect_cell_num,                                                      \n" +
				"    t3.tkindate as trck_in_time,                                          \n" +
				"    t3.eventdate as trck_out_time,                                        \n" +
				"    t1.matchlottype as match_lot_type,                                    \n" +
				"    t1.bingradecd as bin_grade_code,                                      \n" +
				"    t1.defect_group_code,                                                 \n" +
				"    t1.decision_code,                                                     \n" +
				"    t3.preprocesstkoutdate as pre_trk_out                                 \n" +
				"FROM                                                                      \n" +
				"    insp_temp2                     t1,                                    \n" +
				"    yms_dm.h_imptdefecteqp         t2,                                    \n" +
				"    yms_dw.dw_glasscell_eventhist  t3                                     \n" +
				"WHERE 1 = 1                                                               \n" +
				"    AND t1.line_code   = t2.siteid                                        \n" +
				"    AND t1.glass_id    = t2.glasscellid                                   \n" +
				"    AND t2.imptstepgrpid LIKE 'L%%'                                       \n" +
				"    AND t3.eventdate   < t1.inspect_dt                                    \n" +
				"    AND t2.siteid      = %s                                               \n" +  // LINE
				"    AND t2.glasscellid = t3.glasscellid                                   \n" +
				"    AND T3.STEPID LIKE T2.IMPTSTEPGRPID                                   \n" +
				"    AND t3.activity    = 'TrackOut'                                       \n" +
				""
				, StrUtil.quote(Parameter.getInstance().getStrLine())
				, StrUtil.quote(Parameter.getInstance().getStrFromDateTime())
				, StrUtil.quote(Parameter.getInstance().getStrToDateTime())
				, StrUtil.quote(Parameter.getInstance().getStrUserGroupCode())
				, StrUtil.quote(Parameter.getInstance().getStrProductType())
				, StrUtil.quote(Parameter.getInstance().getStrSubAreaCode())
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
					if ("TFT".equals(Parameter.getInstance().getStrAreaCode())) {
						query = getLine56TFT();
					} else if ("LC".equals(Parameter.getInstance().getStrAreaCode()) || "MOD".equals(Parameter.getInstance().getStrAreaCode())) {
						query = getLine56LCMOD();
					} else {
						return list;
					}
				} else if ("L7AFAB".equals(Parameter.getInstance().getStrLine()) || "L7BFAB".equals(Parameter.getInstance().getStrLine()) || "L8AFAB".equals(Parameter.getInstance().getStrLine()) || "L8ZFAB".equals(Parameter.getInstance().getStrLine())) {
					if ("TFT".equals(Parameter.getInstance().getStrAreaCode())) {
						query = getLine78TFT();
					} else if ("LC".equals(Parameter.getInstance().getStrAreaCode()) || "MOD".equals(Parameter.getInstance().getStrAreaCode())) {
						query = getLine78LCMOD();
					} else {
						return list;
					}
				} else {
					return list;
				}
				
				if (!Flag.TRUE) System.out.println("[" + query + "]");
				
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
							System.out.println(String.format("    GLASS_ID                = [%s]", rs.getString("GLASS_ID"               )));
							System.out.println(String.format("    CELL_ID                 = [%s]", rs.getString("CELL_ID"                )));
							System.out.println(String.format("    GLASS_DEFECT_CODE_RATIO = [%s]", rs.getString("GLASS_DEFECT_CODE_RATIO")));
							System.out.println(String.format("    BASE_CELL_NUM           = [%s]", rs.getString("BASE_CELL_NUM"          )));
							System.out.println(String.format("    DEFECT_CELL_NUM         = [%s]", rs.getString("DEFECT_CELL_NUM"        )));
							System.out.println(String.format("    TRCK_IN_TIME            = [%s]", rs.getString("TRCK_IN_TIME"           )));
							System.out.println(String.format("    TRCK_OUT_TIME           = [%s]", rs.getString("TRCK_OUT_TIME"          )));
							System.out.println(String.format("    MATCH_LOT_TYPE          = [%s]", rs.getString("MATCH_LOT_TYPE"         )));
							System.out.println(String.format("    BIN_GRADE_CODE          = [%s]", rs.getString("BIN_GRADE_CODE"         )));
							System.out.println(String.format("    DEFECT_GROUP_CODE       = [%s]", rs.getString("DEFECT_GROUP_CODE"      )));
							System.out.println(String.format("    DECISION_CODE           = [%s]", rs.getString("DECISION_CODE"          )));
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
							bean.setGlassId             (rs.getString("GLASS_ID"               ));
							bean.setCellId              (rs.getString("CELL_ID"                ));
							bean.setGlassDefectCodeRatio(rs.getString("GLASS_DEFECT_CODE_RATIO"));
							bean.setBaseCellNum         (rs.getString("BASE_CELL_NUM"          ));
							bean.setDefectCellNum       (rs.getString("DEFECT_CELL_NUM"        ));
							bean.setTrckInTime          (rs.getString("TRCK_IN_TIME"           ));
							bean.setTrckOutTime         (rs.getString("TRCK_OUT_TIME"          ));
							bean.setMatchLotType        (rs.getString("MATCH_LOT_TYPE"         ));
							bean.setBinGradeCode        (rs.getString("BIN_GRADE_CODE"         ));
							bean.setDefectGroupCode     (rs.getString("DEFECT_GROUP_CODE"      ));
							bean.setDecisionCode        (rs.getString("DECISION_CODE"          ));
							
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
			SystemProperties.setTesting("010");
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
	}
	
	public static void main(String[] args)
	{
		if (Flag.TRUE) test01();
	}
}
