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

package kiea.proj.sdc.anal.macro.sample.a12.aib.macro.AIB_DEFECT_HIST.v01;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import kiea.kr.co.tain.base.Flag;
import kiea.proj.sdc.anal.common.Connection;
import kiea.proj.sdc.anal.common.Parameter;
import kiea.proj.sdc.anal.macro.impl.io.AbstractOracleReader;
import kiea.proj.sdc.anal.util.StrUtil;
import oracle.jdbc.OracleConnection;

/**
 * @author KangSeok
 * @date 2014. 8. 8.
 * @file_name AIB_DEFECT_HIST_OracleReader.java
 *
 */
public class AIB_DEFECT_HIST_OracleReader extends AbstractOracleReader
{
	private List<AIB_DEFECT_HIST_ReadBean> list = new ArrayList<AIB_DEFECT_HIST_ReadBean>();
	
	/**
	 * Sample02OracleReader
	 */
	public AIB_DEFECT_HIST_OracleReader()
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
				"/* 천안 : 5,6 : TFT */                                                                                                             \n" +
				"WITH insp1 AS (                                                                                                                    \n" +
				"    SELECT /*+ PARALLEL(2) */                                                                                                      \n" +
				"        t1.siteid AS line_code,                                                                                                    \n" +
				"        t2.prodgrpname AS user_group_code,                                                                                         \n" +
				"        t2.procid AS process_id,                                                                                                   \n" +
				"        t1.prodid AS product_id,                                                                                                   \n" +
				"        t1.prodtype AS product_type,                                                                                               \n" +
				"        CASE                                                                                                                       \n" +
				"            WHEN inspstepid LIKE 'T%%' THEN 'TFT'                                                                                  \n" +
				"            WHEN inspstepid LIKE 'F%%' THEN 'CF'                                                                                   \n" +
				"            WHEN inspstepid LIKE 'L%%' THEN 'LC'                                                                                   \n" +
				"            ELSE 'MOD'                                                                                                             \n" +
				"            END AS area_code,                                                                                                      \n" +
				"        t1.inspsteptype AS sub_area_code,                                                                                          \n" +
				"        t1.inspstepid   AS step_id,                                                                                                \n" +
				"        t1.inspeqpid    AS eqp_id,                                                                                                 \n" +
				"        t1.glassid      AS glass_id,                                                                                               \n" +
				"        t1.cellid       AS cell_id,                                                                                                \n" +
				"        t1.inspdate     AS inspect_dt,                                                                                             \n" +
				"        REPLACE (cellid, glassid, '') AS cell_loc_id,                                                                              \n" +
				"        t1.defectcd     AS defect_group_code,                                                                                      \n" +
				"        t1.decgradecd   AS decision_code,                                                                                          \n" +
				"        t1.workerid     AS inspector_id,                                                                                           \n" +
				"        t1.defectsysid                                                                                                             \n" +
				"    FROM                                                                                                                           \n" +
				"        yms_dm.d_product t2, yms_dm.h_inspdefect t1                                                                                \n" +
				"    WHERE                                                                                                                          \n" +
				"        /* LINE 'L6' => 'L6FAB' 데이터가 변경 됨 프로시저 변경 혹은 매크로 변수 변경필요 */                                        \n" +
				"        t1.siteid =  %s                                                                                                            \n" +  // LINE
				"        AND t1.insphour >=  %s                                                                                                     \n" +  // FROM_ETL_HOUR
				"        AND t1.insphour <= %s                                                                                                      \n" +  // TO_ETL_HOUR
				"        AND t2.prodgrpname IN (%s)                                                                                                 \n" +  // USER_GROUP_CODE
				"        AND t1.prodtype IN (%s)                                                                                                    \n" +  // PRODUCT_TYPE
				"        AND t1.inspsteptype IN (%s)                                                                                                \n" +  // SUB_AREA_CODE
				"        AND t2.siteid IN (%s)                                                                                                      \n" +  // LINE
				"        AND t1.prodid = t2.prodid                                                                                                  \n" +
				"        AND t1.defectcd IN (%s)                                                                                                    \n" +  // DEFECT_GROUP_CODE
				"        AND t1.decgradecd IN (%s)                                                                                                  \n" +  // DECISION_CODE
				")                                                                                                                                  \n" +
				"SELECT /*+ ORDERED INDEX(T2 DW_RDP_DEFECT_INFO_PK) */                                                                              \n" +
				"    t1.line_code,                                                                                                                  \n" +
				"    t1.user_group_code,                                                                                                            \n" +
				"    t1.process_id,                                                                                                                 \n" +
				"    t1.product_id,                                                                                                                 \n" +
				"    t1.product_type,                                                                                                               \n" +
				"    t1.area_code,                                                                                                                  \n" +
				"    t1.sub_area_code,                                                                                                              \n" +
				"    t1.step_id,                                                                                                                    \n" +
				"    t1.eqp_id,                                                                                                                     \n" +
				"    t1.glass_id,                                                                                                                   \n" +
				"    t1.cell_id,                                                                                                                    \n" +
				"    to_char(t2.NO) AS defect_seq,                                                                                                  \n" +
				"    t1.inspect_dt,                                                                                                                 \n" +
				"    t1.cell_loc_id,                                                                                                                \n" +
				"    t2.coordx AS x_value,                                                                                                          \n" +
				"    t2.coordy AS y_value,                                                                                                          \n" +
				"    t1.defect_group_code,                                                                                                          \n" +
				"    t1.decision_code,                                                                                                              \n" +
				"    t1.inspector_id,                                                                                                               \n" +
				"    t2.dataline,                                                                                                                   \n" +
				"    t2.gateline,                                                                                                                   \n" +
				"    REPLACE                                                                                                                        \n" +
				"      (CASE                                                                                                                        \n" +
				"          WHEN t2.imagefilepath LIKE '%%l7b%%' THEN '/filesrv_7b'|| SUBSTR (t2.imagefilepath,INSTR (t2.imagefilepath, '/', 2))     \n" +
				"          WHEN t2.imagefilepath LIKE '%%l8a%%' THEN '/filesrv_8a_new'|| SUBSTR (t2.imagefilepath,INSTR (t2.imagefilepath, '/', 2)) \n" +
				"          /*2012.10.15 filesrv_8a -> filesrv_8a_new 변경 CHOI to YEOM*/                                                            \n" +
				"          WHEN t2.imagefilepath LIKE '%%l8b%%' THEN '/filesrv_8b_new'|| SUBSTR (t2.imagefilepath,INSTR (t2.imagefilepath, '/', 2)) \n" +
				"          /*2014.08.20 filesrv_8b -> filesrv_8b_new 변경 CHOI to YEOM*/                                                            \n" +
				"          WHEN t2.imagefilepath LIKE '%%l8z%%' THEN '/filesrv_8z'|| SUBSTR (t2.imagefilepath,INSTR (t2.imagefilepath, '/', 2))     \n" +
				"          WHEN t2.imagefilepath LIKE '%%l8y%%' THEN    '/filesrv_8y'|| SUBSTR (t2.imagefilepath,INSTR (t2.imagefilepath, '/', 2))  \n" +
				"          WHEN t2.imagefilepath LIKE '%%filesrv%%' AND t2.imagefilepath NOT LIKE '%%nas56%%'                                       \n" +
				"              THEN    '/filesrv_7a' || SUBSTR (t2.imagefilepath,INSTR (t2.imagefilepath, '/', 2))                                  \n" +
				"          ELSE 'http://12.91.30.32:8088/img56'|| REPLACE (LOWER (t2.imagefilepath), '/mnt/nas56/img', '')                          \n" +
				"       END, 'raw', 'img' ) AS imagepath                                                                                            \n" +
				"FROM                                                                                                                               \n" +
				"    insp1                     t1,                                                                                                  \n" +
				"    yms_dw.dw_rdp_defect_info t2,                                                                                                  \n" +
				"    yms_dm.d_defect           t4                                                                                                   \n" +
				"WHERE                                                                                                                              \n" +
				"    t1.line_code             = t2.siteid                                                                                           \n" +
				"    AND t1.cell_id           = REPLACE (t2.ID, '.', '')                                                                            \n" +
				"    AND t1.defectsysid       = t2.sysid                                                                                            \n" +
				"    AND t1.defect_group_code = t4.defectcd                                                                                         \n" +
				""
				, StrUtil.quote(Parameter.getInstance().getStrLine())
				, StrUtil.quote(Parameter.getInstance().getStrFromDateTime())
				, StrUtil.quote(Parameter.getInstance().getStrToDateTime())
				, StrUtil.quote(Parameter.getInstance().getStrUserGroupCode())
				, StrUtil.quote(Parameter.getInstance().getStrProductType())
				, StrUtil.quote(Parameter.getInstance().getStrSubAreaCode())
				, StrUtil.quote(Parameter.getInstance().getStrLine())
				
				, StrUtil.quote(Parameter.getInstance().getStrDefectGroupCode())
				, StrUtil.quote(Parameter.getInstance().getStrDecisionCode())
		);

		return query;
	}
	
	/**
	 * 
	 * getLine56LC
	 *
	 * @return
	 */
	private String getLine56LC()
	{
		String query = null;
		
		query = String.format("" +
				"/* 천안 : 5,6 : LC */                                                                                          \n" +
				"WITH avi AS (                                                                                                  \n" +
				"    SELECT /*+ PARALLEL(2) */                                                                                  \n" +
				"        t1.siteid AS line_code,                                                                                \n" +
				"        t2.prodgrpname AS user_group_code,                                                                     \n" +
				"        t2.procid AS process_id,                                                                               \n" +
				"        t1.prodid AS product_id,                                                                               \n" +
				"        t1.prodtype AS product_type,                                                                           \n" +
				"        CASE                                                                                                   \n" +
				"            WHEN inspstepid LIKE 'T%%' THEN 'TFT'                                                              \n" +
				"            WHEN inspstepid LIKE 'F%%' THEN 'CF'                                                               \n" +
				"            WHEN inspstepid LIKE 'L%%' THEN 'LC'                                                               \n" +
				"            ELSE 'MOD'                                                                                         \n" +
				"            END AS area_code,                                                                                  \n" +
				"        t1.inspsteptype AS sub_area_code,                                                                      \n" +
				"        t1.inspstepid AS step_id,                                                                              \n" +
				"        t1.inspeqpid AS eqp_id,                                                                                \n" +
				"        t1.glassid AS glass_id,                                                                                \n" +
				"        t1.cellid AS cell_id,                                                                                  \n" +
				"        t1.inspdate AS inspect_dt,                                                                             \n" +
				"        REPLACE (cellid, glassid, '') AS cell_loc_id,                                                          \n" +
				"        t1.defectcd AS defect_group_code,                                                                      \n" +
				"        t1.decgradecd AS decision_code,                                                                        \n" +
				"        t1.workerid AS inspector_id                                                                            \n" +
				"    FROM                                                                                                       \n" +
				"        yms_dm.d_product t2, yms_dm.h_inspdefect t1                                                            \n" +
				"    WHERE                                                                                                      \n" +
				"        /* LINE 'L6' => 'L6FAB' 데이터가 변경 됨 프로시저 변경 혹은 매크로 변수 변경필요 */                    \n" +
				"        /* DEFECT_GROUP_CODE 'E06_L6' => 'E06' 데이터가 변경 됨 프로시저 변경 혹은 매크로 변수 변경필요 */     \n" +
				"        t1.siteid = t2.siteid                                                                                  \n" +
				"        AND t1.insphour      >= %s                                                                             \n" +  // FROM_ETL_HOUR
				"        AND t1.insphour      <= %s                                                                             \n" +  // TO_ETL_HOUR
				"        AND t2.siteid        IN (%s)                                                                           \n" +  // LINE
				"        AND t2.prodgrpname   IN (%s)                                                                           \n" +  // USER_GROUP_CODE
				"        AND t1.prodtype      IN (%s)                                                                           \n" +  // PRODUCT_TYPE
				"        AND t1.inspsteptype  IN (%s)                                                                           \n" +  // SUB_AREA_CODE
				"        AND t1.defectcd      IN (%s)                                                                           \n" +  // DEFECT_GROUP_CODE
				"        AND t1.decgradecd    IN (%s)                                                                           \n" +  // DECISION_CODE
				"        AND t1.prodid = t2.prodid                                                                              \n" +
				")                                                                                                              \n" +
				"SELECT t1.line_code, t1.user_group_code, t1.process_id, t1.product_id,                                         \n" +
				"       t1.product_type, t1.area_code, t1.sub_area_code, t1.step_id, t1.eqp_id,                                 \n" +
				"       t1.glass_id, t1.cell_id, to_char(t2.no) AS defect_seq, t1.inspect_dt,                                   \n" +
				"       t1.cell_loc_id,                                                                                         \n" +
				"       CASE                                                                                                    \n" +
				"          WHEN t3.width - t3.height < 0                                                                        \n" +
				"             THEN CASE                                                                                         \n" +
				"                    WHEN t3.x_logic1 = 'POINT_X + GATE * PIXCEL_X'                                             \n" +
				"                       THEN t3.point_x + t2.gateline * t3.pixcel_x                                             \n" +
				"                    WHEN t3.x_logic1 = 'POINT_X + DATA * PIXCEL_X'                                             \n" +
				"                       THEN t3.point_x + t2.dataline * t3.pixcel_x                                             \n" +
				"                 END                                                                                           \n" +
				"          ELSE CASE                                                                                            \n" +
				"          WHEN t3.x_logic2 = 'POINT_X + WIDTH - DATA * PIXCEL_X'                                               \n" +
				"             THEN t3.point_x + t3.width - t2.dataline * t3.pixcel_x                                            \n" +
				"          WHEN t3.x_logic2 = 'POINT_X + DATA * PIXCEL_X'                                                       \n" +
				"             THEN t3.point_x + t2.dataline * t3.pixcel_x                                                       \n" +
				"       END                                                                                                     \n" +
				"       END x_value,                                                                                            \n" +
				"       CASE                                                                                                    \n" +
				"          WHEN t3.width - t3.height < 0                                                                        \n" +
				"             THEN CASE                                                                                         \n" +
				"                    WHEN t3.y_logic1 =                                                                         \n" +
				"                                 'POINT_Y + HEIGHT - DATA * PIXCEL_Y'                                          \n" +
				"                       THEN   t3.point_y                                                                       \n" +
				"                            + t3.height                                                                        \n" +
				"                            - t2.dataline * t3.pixcel_y                                                        \n" +
				"                    WHEN t3.y_logic1 = 'POINT_Y + GATE * PIXCEL_Y'                                             \n" +
				"                       THEN t3.point_y + t2.gateline * t3.pixcel_y                                             \n" +
				"                 END                                                                                           \n" +
				"          ELSE CASE                                                                                            \n" +
				"          WHEN t3.y_logic2 = 'POINT_Y + HEIGHT - GATE * PIXCEL_Y'                                              \n" +
				"             THEN t3.point_y + t3.height - t2.gateline * t3.pixcel_y                                           \n" +
				"          WHEN t3.y_logic2 = 'POINT_Y + GATE * PIXCEL_Y'                                                       \n" +
				"             THEN t3.point_y + t2.gateline * t3.pixcel_y                                                       \n" +
				"       END                                                                                                     \n" +
				"       END y_value,                                                                                            \n" +
				"       t1.defect_group_code, t1.decision_code, t1.inspector_id,                                                \n" +
				"       t2.dataline AS dataline, t2.gateline AS gateline,                                                       \n" +
				"       REPLACE                                                                                                 \n" +
				"          (CASE                                                                                                \n" +
				"              WHEN t2.imagefilepath LIKE '%%l7b%%'                                                             \n" +
				"                 THEN    '/filesrv_7b'                                                                         \n" +
				"                      || SUBSTR (t2.imagefilepath, INSTR (t2.imagefilepath, '/', 2))                           \n" +
				"              WHEN t2.imagefilepath LIKE '%%l8a%%'                                                             \n" +
				"                 THEN    '/filesrv_8a_new'                                                                     \n" +
				"                      || SUBSTR                                                                                \n" +
				"                            (t2.imagefilepath, INSTR (t2.imagefilepath, '/', 2))                               \n" +
				"              /*2012.10.15 filesrv_8a -> filesrv_8a_new 변경 CHOI to YEOM*/                                    \n" +
				"              WHEN t2.imagefilepath LIKE '%%l8b%%'                                                             \n" +
				"                 THEN    '/filesrv_8b_new'                                                                     \n" +
				"                      || SUBSTR (t2.imagefilepath, INSTR (t2.imagefilepath, '/', 2))                           \n" +
				"              /*2014.08.20 filesrv_8b -> filesrv_8b_new 변경 CHOI to YEOM*/                                    \n" +
				"              WHEN t2.imagefilepath LIKE '%%l8z%%'                                                             \n" +
				"                 THEN    '/filesrv_8z'                                                                         \n" +
				"                      || SUBSTR (t2.imagefilepath, INSTR (t2.imagefilepath, '/', 2))                           \n" +
				"              WHEN t2.imagefilepath LIKE '%%5l8y%%'                                                            \n" +
				"                 THEN    '/filesrv_8y'                                                                         \n" +
				"                      || SUBSTR (t2.imagefilepath, INSTR (t2.imagefilepath, '/', 2))                           \n" +
				"              WHEN t2.imagefilepath LIKE '%%filesrv%%'                                                         \n" +
				"              AND t2.imagefilepath NOT LIKE '%%nas56%%'                                                        \n" +
				"                 THEN    '/filesrv_7a'                                                                         \n" +
				"                      || SUBSTR (t2.imagefilepath, INSTR (t2.imagefilepath, '/', 2))                           \n" +
				"              ELSE    'http://12.91.30.32:8088/img56'                                                          \n" +
				"                   || REPLACE (LOWER (t2.imagefilepath), '/mnt/nas56/img', '')                                 \n" +
				"           END,                                                                                                \n" +
				"           'raw',                                                                                              \n" +
				"           'img'                                                                                               \n" +
				"          ) AS imagepath                                                                                       \n" +
				"FROM                                                                                                           \n" +
				"    (                                                                                                          \n" +
				"        SELECT t3.*, TRIM (t5.defectname) defectname                                                           \n" +
				"        FROM   avi t3, yms_dm.d_defect t5                                                                      \n" +
				"        WHERE  t3.defect_group_code = t5.defectcd                                                              \n" +
				"        )                      t1,                                                                             \n" +
				"    YMS_DW.DW_RDP_DEFECT_INFO  t2,                                                                             \n" +
				"    yms_dm.d_cellconvert       t3,                                                                             \n" +
				"    yms_dm.d_defect            t4                                                                              \n" +
				"WHERE                                                                                                          \n" +
				"    /*'7A' => 'L7AFAB' 데이터가 변경 됨 프로시저 변경 혹은 매크로 변수 변경필요 */                             \n" +
				"    t2.siteid          = %s                                                                                    \n" +  // LINE
				"    AND t1.line_code   = t2.siteid                                                                             \n" +
				"    AND t1.cell_id     = SUBSTR (t2.id, 1, 7) || SUBSTR (t2.id, 9, 3)                                          \n" +
				"    AND t1.process_id LIKE t3.procid || '%'                                                                    \n" +
				"    AND t1.cell_loc_id = t3.celllocid                                                                          \n" +
				"    AND t4.siteid      = t2.siteid                                                                             \n" +
				"    AND t1.defectname  = TRIM (t4.defectname)                                                                  \n" +
				"    AND t2.eventtime   >= TO_DATE (%s, 'YYYYMMDDHH24MISS') - 1                                                 \n" +  // FROM_ETL_DT
				"    AND t2.eventtime   <= TO_DATE (%s, 'YYYYMMDDHH24MISS')                                                     \n" +  // TO_ETL_DT
				""
				, StrUtil.quote(Parameter.getInstance().getStrFromDateTime())
				, StrUtil.quote(Parameter.getInstance().getStrToDateTime())
				, StrUtil.quote(Parameter.getInstance().getStrLine())
				, StrUtil.quote(Parameter.getInstance().getStrUserGroupCode())
				, StrUtil.quote(Parameter.getInstance().getStrProductType())
				, StrUtil.quote(Parameter.getInstance().getStrSubAreaCode())
				, StrUtil.quote(Parameter.getInstance().getStrDefectGroupCode())
				, StrUtil.quote(Parameter.getInstance().getStrDecisionCode())
				
				, StrUtil.quote(Parameter.getInstance().getStrLine())
				, StrUtil.quote(Parameter.getInstance().getStrFromDateTime())
				, StrUtil.quote(Parameter.getInstance().getStrToDateTime())
		);

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
				"/* 천안 : 5,6 : MOD */           \n" +
				"SELECT                           \n" +
				"      '' AS LINE_CODE            \n" +
				"    , '' AS USER_GROUP_CODE      \n" +
				"    , '' AS PROCESS_ID           \n" +
				"    , '' AS PRODUCT_ID           \n" +
				"    , '' AS PRODUCT_TYPE         \n" +
				"    , '' AS AREA_CODE            \n" +
				"    , '' AS SUB_AREA_CODE        \n" +
				"    , '' AS GLASS_ID             \n" +
				"    , '' AS CELL_ID              \n" +
				"    , '' AS STEP_ID              \n" +
				"    , '' AS EQP_ID               \n" +
				"    , to_char(0)  AS DEFECT_SEQ  \n" +
				"    , 0  AS INSPECT_DT           \n" +
				"    , 0  AS X_VALUE              \n" +
				"    , 0  AS Y_VALUE              \n" +
				"    , '' AS DEFECT_GROUP_CODE    \n" +
				"    , '' AS DECISION_CODE        \n" +
				"    , '' AS INSPECTOR_ID         \n" +
				"    , '' AS CELL_LOC_ID          \n" +
				"    , '' AS IMAGEPATH            \n" +
				"FROM                             \n" +
				"    DUAL                         \n" +
				"WHERE                            \n" +
				"    1 = 1                        \n" +
				"    AND ROWNUM < 1               \n" +
				""
				, StrUtil.quote(Parameter.getInstance().getStrUserGroupCode())
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
				"/* 아산 : 7,8 : TFT */                                                                                                \n" +
				"WITH insp_temp1 AS (                                                                                                  \n" +
				"    SELECT /*+ use_hash(t1 t2) full (t1) ordered */                                                                   \n" +
				"        t1.siteid AS line_code,                                                                                       \n" +
				"        t2.prodgrpname AS user_group_code,                                                                            \n" +
				"        t2.procid AS process_id,                                                                                      \n" +
				"        t1.prodid AS product_id,                                                                                      \n" +
				"        t1.prodtype AS product_type,                                                                                  \n" +
				"        CASE                                                                                                          \n" +
				"            WHEN inspstepid LIKE 'T%%' THEN 'TFT'                                                                     \n" +
				"            WHEN inspstepid LIKE 'F%%' THEN 'CF'                                                                      \n" +
				"            WHEN inspstepid LIKE 'L%%' THEN 'LC'                                                                      \n" +
				"            ELSE 'MOD'                                                                                                \n" +
				"            END AS area_code,                                                                                         \n" +
				"        t1.inspsteptype AS sub_area_code,                                                                             \n" +
				"        t1.inspstepid AS step_id,                                                                                     \n" +
				"        t1.inspeqpid AS eqp_id,                                                                                       \n" +
				"        t1.glassid AS glass_id,                                                                                       \n" +
				"        t1.cellid AS cell_id,                                                                                         \n" +
				"        t1.inspdate AS inspect_dt,                                                                                    \n" +
				"        REPLACE (cellid, glassid, '') AS cell_loc_id,                                                                 \n" +
				"        t1.defectcd AS defect_group_code,                                                                             \n" +
				"        t1.decgradecd AS decision_code,                                                                               \n" +
				"        t1.workerid AS inspector_id,                                                                                  \n" +
				"        t1.defectsysid                                                                                                \n" +
				"    FROM                                                                                                              \n" +
				"        yms_dm.d_product t2, yms_dm.h_inspdefect t1                                                                   \n" +
				"    WHERE                                                                                                             \n" +
				"        /* LINE '7A' => 'L7AFAB' 데이터가 변경 됨 프로시저 변경 혹은 매크로 변수 변경필요 */                          \n" +
				"        /* DEFECT_GROUP_CODE 'LRLA_L7AFAB' => 'LRLA' 데이터가 변경 됨 프로시저 변경 혹은 매크로 변수 변경필요 */      \n" +
				"        t1.siteid = &LINE.                                                                                            \n" +
				"        AND t1.insphour >= %s                                                                                         \n" +  // FROM_ETL_HOUR
				"        AND t1.insphour <= %s                                                                                         \n" +  // TO_ETL_HOUR
				"        AND t2.prodgrpname  IN (%s)                                                                                   \n" +  // USER_GROUP_CODE
				"        AND t1.prodtype     IN (%s)                                                                                   \n" +  // PRODUCT_TYPE
				"        AND t1.inspsteptype IN (%s)                                                                                   \n" +  // SUB_AREA_CODE
				"        AND t2.siteid       IN (%s)                                                                                   \n" +  // LINE
				"        AND t1.prodid       = t2.prodid                                                                               \n" +
				"        AND t1.defectcd     IN (%s)                                                                                   \n" +  // DEFECT_GROUP_CODE
				"        AND t1.decgradecd   IN (%s)                                                                                   \n" +  // DECISION_CODE
				"),                                                                                                                    \n" +
				"insp_temp2 AS (                                                                                                       \n" +
				"    SELECT /*+ PARALLEL(2) use_hash(t1 t2) full(t2) ordered */                                                        \n" +
				"        t1.line_code,                                                                                                 \n" +
				"        t1.user_group_code,                                                                                           \n" +
				"        t1.process_id,                                                                                                \n" +
				"        t1.product_id,                                                                                                \n" +
				"        t1.product_type,                                                                                              \n" +
				"        t1.area_code,                                                                                                 \n" +
				"        t1.sub_area_code,                                                                                             \n" +
				"        t1.step_id,                                                                                                   \n" +
				"        t1.eqp_id,                                                                                                    \n" +
				"        t1.glass_id,                                                                                                  \n" +
				"        t1.cell_id,                                                                                                   \n" +
				"        to_char(t2.seq) AS defect_seq,                                                                                \n" +
				"        t1.inspect_dt,                                                                                                \n" +
				"        t1.cell_loc_id,                                                                                               \n" +
				"        t2.xcoord AS x_value,                                                                                         \n" +
				"        t2.ycoord AS y_value,                                                                                         \n" +
				"        t1.defect_group_code,                                                                                         \n" +
				"        t1.decision_code,                                                                                             \n" +
				"        t2.defectcd,                                                                                                  \n" +
				"        t1.inspector_id,                                                                                              \n" +
				"        t2.dataline,                                                                                                  \n" +
				"        t2.gateline,                                                                                                  \n" +
				"        REPLACE                                                                                                       \n" +
				"        (CASE WHEN t2.imagepath LIKE '%%l7b%%' THEN '/filesrv_7b' || SUBSTR (t2.imagepath,INSTR (t2.imagepath,'/',2)) \n" +
				"          WHEN t2.imagepath LIKE '%%l8a%%' THEN '/filesrv_8a_new' || SUBSTR(t2.imagepath, INSTR (t2.imagepath,'/',2)) \n" +
				"          WHEN t2.imagepath LIKE '%%l8b%%' THEN '/filesrv_8b_new' || SUBSTR (t2.imagepath,INSTR (t2.imagepath,'/',2)) \n" +
				"          /*2014.08.20 filesrv_8b -> filesrv_8b_new 변경 CHOI to YEOM*/                                               \n" +
				"          WHEN t2.imagepath LIKE '%%l8z%%' THEN '/filesrv_8z' || SUBSTR (t2.imagepath,INSTR (t2.imagepath, '/', 2))   \n" +
				"          WHEN t2.imagepath LIKE '%%l8y%%' THEN '/filesrv_8y' || SUBSTR (t2.imagepath,INSTR (t2.imagepath, '/', 2))   \n" +
				"          WHEN t2.imagepath LIKE '%%filesrv%%' AND t2.imagepath NOT LIKE '%%nas56%%'                                  \n" +
				"            THEN '/filesrv_7a' || SUBSTR (t2.imagepath,INSTR (t2.imagepath, '/', 2))                                  \n" +
				"          ELSE 'http://12.91.30.32:8088/img56'|| REPLACE (LOWER (t2.imagepath),'/mnt/nas56/img','')                   \n" +
				"        END,'raw','img'                                                                                               \n" +
				"        ) AS imagepath                                                                                                \n" +
				"    FROM                                                                                                              \n" +
				"        insp_temp1 t1, yms_dw.dw_defect_insphist t2                                                                   \n" +
				"    WHERE                                                                                                             \n" +
				"        t1.line_code = t2.siteid                                                                                      \n" +
				"        AND t1.cell_id = t2.cellid                                                                                    \n" +
				"        AND t1.defectsysid = t2.inspectsysid                                                                          \n" +
				"        AND t1.decision_code = t2.decisiongrade                                                                       \n" +
				"        AND t2.inspectdate > TO_DATE (%s, 'YYYYMMDDHH24MISS')                                                         \n" +  // FROM_ETL_DT
				"        AND t2.inspectdate <= TO_DATE (%s, 'YYYYMMDDHH24MISS')                                                        \n" +  // TO_ETL_DT
				"        AND t2.siteid = &LINE.                                                                                        \n" +
				")                                                                                                                     \n" +
				"SELECT /*+ use_hash(t1 t2) full(t2) ordered */                                                                        \n" +
				"    t1.*                                                                                                              \n" +
				"FROM                                                                                                                  \n" +
				"    insp_temp2 t1, yms_dm.d_defect t2                                                                                 \n" +
				"WHERE                                                                                                                 \n" +
				"    t1.defect_group_code = t2.defectcd                                                                                \n" +
				"    AND t1.line_code = t2.siteid                                                                                      \n" +
				"    AND t2.defectcd = t1.defectcd                                                                                     \n" +
				""
				, StrUtil.quote(Parameter.getInstance().getStrFromDateTime())
				, StrUtil.quote(Parameter.getInstance().getStrToDateTime())
				, StrUtil.quote(Parameter.getInstance().getStrUserGroupCode())
				, StrUtil.quote(Parameter.getInstance().getStrProductType())
				, StrUtil.quote(Parameter.getInstance().getStrSubAreaCode())
				, StrUtil.quote(Parameter.getInstance().getStrLine())
				, StrUtil.quote(Parameter.getInstance().getStrDefectGroupCode())
				, StrUtil.quote(Parameter.getInstance().getStrDecisionCode())
				
				, StrUtil.quote(Parameter.getInstance().getStrFromDateTime())
				, StrUtil.quote(Parameter.getInstance().getStrToDateTime())
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
				"/* 아산 : 7,8 : LC */                                                                                                             \n" +
				"WITH insp AS (                                                                                                                    \n" +
				"    SELECT /*+ USE_HASH( T1 T2) FULL(T1) */                                                                                       \n" +
				"        t1.siteid AS line_code,                                                                                                   \n" +
				"        t2.prodgrpname AS user_group_code,                                                                                        \n" +
				"        t2.procid AS process_id,                                                                                                  \n" +
				"        t1.prodid AS product_id,                                                                                                  \n" +
				"        t1.prodtype AS product_type,                                                                                              \n" +
				"        CASE                                                                                                                      \n" +
				"            WHEN inspstepid LIKE 'T%%' THEN 'TFT'                                                                                 \n" +
				"            WHEN inspstepid LIKE 'F%%' THEN 'CF'                                                                                  \n" +
				"            WHEN inspstepid LIKE 'L%%' THEN 'LC'                                                                                  \n" +
				"            ELSE 'MOD'                                                                                                            \n" +
				"            END AS area_code,                                                                                                     \n" +
				"        t1.inspsteptype AS sub_area_code,                                                                                         \n" +
				"        t1.inspstepid AS step_id,                                                                                                 \n" +
				"        t1.inspeqpid AS eqp_id,                                                                                                   \n" +
				"        t1.glassid AS glass_id,                                                                                                   \n" +
				"        t1.cellid AS cell_id,                                                                                                     \n" +
				"        t1.inspdate AS inspect_dt,                                                                                                \n" +
				"        REPLACE (cellid, glassid, '') AS cell_loc_id,                                                                             \n" +
				"        t1.defectcd AS defect_group_code,                                                                                         \n" +
				"        t1.decgradecd AS decision_code,                                                                                           \n" +
				"        t1.workerid AS inspector_id                                                                                               \n" +
				"    FROM                                                                                                                          \n" +
				"        yms_dm.d_product t2, yms_dm.h_inspdefect t1                                                                               \n" +
				"    WHERE                                                                                                                         \n" +
				"        /* LINE '7A' => 'L7AFAB' 데이터가 변경 됨 프로시저 변경 혹은 매크로 변수 변경필요 */                                      \n" +
				"        /*'DEFECT_GROUP_CODE LRLA_L7AFAB' => 'LRLA' 데이터가 변경 됨 프로시저 변경 혹은 매크로 변수 변경필요 */                   \n" +
				"        t1.siteid = t2.siteid                                                                                                     \n" +
				"        AND t1.insphour >= %s                                                                                                     \n" +  // FROM_ETL_HOUR
				"        AND t1.insphour <= %s                                                                                                     \n" +  // TO_ETL_HOUR
				"        AND t2.siteid IN (%s)                                                                                                     \n" +  // LINE
				"        AND t2.prodgrpname IN (%s)                                                                                                \n" +  // USER_GROUP_CODE
				"        AND t1.prodtype IN (%s)                                                                                                   \n" +  // PRODUCT_TYPE
				"        AND t1.inspsteptype IN (%s)                                                                                               \n" +  // SUB_AREA_CODE
				"        AND t1.defectcd IN (%s)                                                                                                   \n" +  // DEFECT_GROUP_CODE
				"        AND t1.decgradecd IN (%s)                                                                                                 \n" +  // DECISION_CODE
				"        AND t1.prodid = t2.prodid                                                                                                 \n" +
				")                                                                                                                                 \n" +
				", avi AS (                                                                                                                        \n" +
				"    SELECT /*+ USE_HASH( T1 T2) FULL(T2) */                                                                                       \n" +
				"        t1.*, t2.defectsysid                                                                                                      \n" +
				"    FROM                                                                                                                          \n" +
				"        insp t1, yms_dm.h_inspdefect t2                                                                                           \n" +
				"    WHERE                                                                                                                         \n" +
				"        /* LINE '7A' => 'L7AFAB' 데이터가 변경 됨 프로시저 변경 혹은 매크로 변수 변경필요 */                                      \n" +
				"        t2.siteid = %s                                                                                                            \n" +  // LINE
				"        AND t2.insphour >= %s                                                                                                     \n" +  // FROM_ETL_HOUR
				"        and t2.insphour <= %s                                                                                                     \n" +  // TO_ETL_HOUR
				"        AND t1.line_code = t2.siteid                                                                                              \n" +
				"        AND t2.inspsteptype IN (%s)                                                                                               \n" +  // SUB_AREA_CODE
				"        AND t1.cell_id = t2.cellid                                                                                                \n" +
				"        AND t1.glass_id = t2.glassid                                                                                              \n" +
				")                                                                                                                                 \n" +
				"SELECT                                                                                                                            \n" +
				"    t1.line_code,                                                                                                                 \n" +
				"    t1.user_group_code,                                                                                                           \n" +
				"    t1.process_id,                                                                                                                \n" +
				"    t1.product_id,                                                                                                                \n" +
				"    t1.product_type,                                                                                                              \n" +
				"    t1.area_code,                                                                                                                 \n" +
				"    t1.sub_area_code,                                                                                                             \n" +
				"    t1.step_id,                                                                                                                   \n" +
				"    t1.eqp_id,                                                                                                                    \n" +
				"    t1.glass_id,                                                                                                                  \n" +
				"    t1.cell_id,                                                                                                                   \n" +
				"    to_char(t2.seq) AS defect_seq,                                                                                                \n" +
				"    t1.inspect_dt,                                                                                                                \n" +
				"    t1.cell_loc_id,                                                                                                               \n" +
				"    CASE                                                                                                                          \n" +
				"       WHEN t3.width - t3.height < 0                                                                                              \n" +
				"          THEN CASE                                                                                                               \n" +
				"                 WHEN t3.x_logic1 = 'POINT_X + GATE * PIXCEL_X'                                                                   \n" +
				"                    THEN t3.point_x + t2.gateline * t3.pixcel_x                                                                   \n" +
				"                 WHEN t3.x_logic1 = 'POINT_X + DATA * PIXCEL_X'                                                                   \n" +
				"                    THEN t3.point_x + t2.dataline * t3.pixcel_x                                                                   \n" +
				"              END                                                                                                                 \n" +
				"       ELSE CASE                                                                                                                  \n" +
				"       WHEN t3.x_logic2 = 'POINT_X + WIDTH - DATA * PIXCEL_X'                                                                     \n" +
				"          THEN t3.point_x + t3.width - t2.dataline * t3.pixcel_x                                                                  \n" +
				"       WHEN t3.x_logic2 = 'POINT_X + DATA * PIXCEL_X'                                                                             \n" +
				"          THEN t3.point_x + t2.dataline * t3.pixcel_x                                                                             \n" +
				"    END                                                                                                                           \n" +
				"    END x_value,                                                                                                                  \n" +
				"    CASE                                                                                                                          \n" +
				"       WHEN t3.width - t3.height < 0                                                                                              \n" +
				"          THEN CASE                                                                                                               \n" +
				"                 WHEN t3.y_logic1 =                                                                                               \n" +
				"                              'POINT_Y + HEIGHT - DATA * PIXCEL_Y'                                                                \n" +
				"                    THEN   t3.point_y                                                                                             \n" +
				"                         + t3.height                                                                                              \n" +
				"                         - t2.dataline * t3.pixcel_y                                                                              \n" +
				"                 WHEN t3.y_logic1 = 'POINT_Y + GATE * PIXCEL_Y'                                                                   \n" +
				"                    THEN t3.point_y + t2.gateline * t3.pixcel_y                                                                   \n" +
				"              END                                                                                                                 \n" +
				"       ELSE CASE                                                                                                                  \n" +
				"       WHEN t3.y_logic2 = 'POINT_Y + HEIGHT - GATE * PIXCEL_Y'                                                                    \n" +
				"          THEN t3.point_y + t3.height - t2.gateline * t3.pixcel_y                                                                 \n" +
				"       WHEN t3.y_logic2 = 'POINT_Y + GATE * PIXCEL_Y'                                                                             \n" +
				"          THEN t3.point_y + t2.gateline * t3.pixcel_y                                                                             \n" +
				"    END                                                                                                                           \n" +
				"    END y_value,                                                                                                                  \n" +
				"    t1.defect_group_code,                                                                                                         \n" +
				"    t1.decision_code,                                                                                                             \n" +
				"    t1.inspector_id,                                                                                                              \n" +
				"    t2.dataline,                                                                                                                  \n" +
				"    t2.gateline,                                                                                                                  \n" +
				"    REPLACE (CASE WHEN t2.imagepath LIKE '%%l7b%%' THEN '/filesrv_7b' || SUBSTR (t2.imagepath, INSTR (t2.imagepath, '/', 2))      \n" +
				"        /*2012.10.15 filesrv_8a -> filesrv_8a_new 변경 CHOI to YEOM*/                                                             \n" +
				"        WHEN t2.imagepath LIKE '%%l8a%%' THEN '/filesrv_8a_new' || SUBSTR (t2.imagepath, INSTR (t2.imagepath, '/', 2))            \n" +
				"        /*2014.08.20 filesrv_8b -> filesrv_8b_new 변경 CHOI to YEOM*/                                                             \n" +
				"        WHEN t2.imagepath LIKE '%%l8b%%' THEN '/filesrv_8b_new' || SUBSTR (t2.imagepath, INSTR (t2.imagepath, '/', 2))            \n" +
				"        WHEN t2.imagepath LIKE '%%l8z%%' THEN '/filesrv_8z' || SUBSTR (t2.imagepath, INSTR (t2.imagepath, '/', 2))                \n" +
				"        WHEN t2.imagepath LIKE '%%l8y%%' THEN '/filesrv_8y' || SUBSTR (t2.imagepath, INSTR (t2.imagepath, '/', 2))                \n" +
				"        WHEN t2.imagepath like '%%filesrv%%' and t2.imagepath not like '%%nas56%%'                                                \n" +
				"            THEN '/filesrv_7a' || SUBSTR (t2.imagepath, INSTR (t2.imagepath, '/', 2))                                             \n" +
				"        ELSE 'http://12.91.30.32:8088/img56' || replace(lower(t2.imagepath),'/mnt/nas56/img','') END, 'raw', 'img') AS imagepath  \n" +
				"FROM                                                                                                                              \n" +
				"    (                                                                                                                             \n" +
				"        SELECT /*+use_hash( t3 t5) full (t5)*/                                                                                    \n" +
				"            t3.*, TRIM (t5.defectname) defectname                                                                                 \n" +
				"        FROM                                                                                                                      \n" +
				"            avi t3, yms_dm.d_defect t5                                                                                            \n" +
				"        WHERE 1=1                                                                                                                 \n" +
				"            AND t3.line_code = t5.siteid                                                                                          \n" +
				"            AND t3.defect_group_code = t5.defectcd                                                                                \n" +
				"        )                     t1,                                                                                                 \n" +
				"    YMS_DW.DW_DEFECT_INSPHIST t2,                                                                                                 \n" +
				"    yms_dm.d_cellconvert      t3,                                                                                                 \n" +
				"    yms_dm.d_defect           t4                                                                                                  \n" +
				"WHERE                                                                                                                             \n" +
				"    /* LINE '7A' => 'L7AFAB' 데이터가 변경 됨 프로시저 변경 혹은 매크로 변수 변경필요 */                                          \n" +
				"    t2.siteid = %s                                                                                                                \n" +  // LINE
				"    AND t1.line_code   = t2.siteid                                                                                                \n" +
				"    AND t1.cell_id     = t2.cellid                                                                                                \n" +
				"    AND t1.defectsysid = t2.inspectsysid                                                                                          \n" +
				"    AND t1.process_id LIKE t3.procid || '%%'                                                                                      \n" +
				"    AND t1.cell_loc_id = t3.celllocid                                                                                             \n" +
				"    AND t2.defectcd    = t4.defectcd                                                                                              \n" +
				"    AND t4.siteid      = t2.siteid                                                                                                \n" +
				"    AND t1.defectname  = TRIM (t4.defectname)                                                                                     \n" +
				"    AND t2.inspectdate > TO_DATE (%s, 'YYYYMMDDHH24MISS') - 1                                                                     \n" +  // FROM_ETL_DT
				"    AND t2.inspectdate <= TO_DATE (%s, 'YYYYMMDDHH24MISS')                                                                        \n" +  // TO_ETL_DT
				""
				, StrUtil.quote(Parameter.getInstance().getStrFromDateTime())
				, StrUtil.quote(Parameter.getInstance().getStrToDateTime())
				, StrUtil.quote(Parameter.getInstance().getStrLine())
				, StrUtil.quote(Parameter.getInstance().getStrUserGroupCode())
				, StrUtil.quote(Parameter.getInstance().getStrProductType())
				, StrUtil.quote(Parameter.getInstance().getStrSubAreaCode())
				, StrUtil.quote(Parameter.getInstance().getStrDefectGroupCode())
				, StrUtil.quote(Parameter.getInstance().getStrDecisionCode())

				, StrUtil.quote(Parameter.getInstance().getStrLine())
				, StrUtil.quote(Parameter.getInstance().getStrFromDateTime())
				, StrUtil.quote(Parameter.getInstance().getStrToDateTime())
				, StrUtil.quote(Parameter.getInstance().getStrSubAreaCode())

				, StrUtil.quote(Parameter.getInstance().getStrLine())
				, StrUtil.quote(Parameter.getInstance().getStrFromDateTime())
				, StrUtil.quote(Parameter.getInstance().getStrToDateTime())
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
				"/* 아산 : 7,8 : MOD */            \n" +
				"SELECT                            \n" +
				"      '' AS LINE_CODE             \n" +
				"    , '' AS USER_GROUP_CODE       \n" +
				"    , '' AS PROCESS_ID            \n" +
				"    , '' AS PRODUCT_ID            \n" +
				"    , '' AS PRODUCT_TYPE          \n" +
				"    , '' AS AREA_CODE             \n" +
				"    , '' AS SUB_AREA_CODE         \n" +
				"    , '' AS GLASS_ID              \n" +
				"    , '' AS CELL_ID               \n" +
				"    , '' AS STEP_ID               \n" +
				"    , '' AS EQP_ID                \n" +
				"    , to_char(0)  AS DEFECT_SEQ   \n" +
				"    , 0  AS INSPECT_DT            \n" +
				"    , 0  AS X_VALUE               \n" +
				"    , 0  AS Y_VALUE               \n" +
				"    , '' AS DEFECT_GROUP_CODE     \n" +
				"    , '' AS DECISION_CODE         \n" +
				"    , '' AS INSPECTOR_ID          \n" +
				"    , '' AS CELL_LOC_ID           \n" +
				"    , '' AS IMAGEPATH             \n" +
				"FROM                              \n" +
				"    DUAL                          \n" +
				"WHERE                             \n" +
				"    1 = 1                         \n" +
				"    AND ROWNUM < 1                \n" +
				""
		);

		return query;
	}
	
	/**
	 * getList
	 */
	@Override
	public List<AIB_DEFECT_HIST_ReadBean> getList()
	{
		return getList(false);
	}
	
	public List<AIB_DEFECT_HIST_ReadBean> getList(boolean flgRead)
	{
		if (Flag.TRUE) {
			if (!flgRead) {
				/*
				 * if flgRead = true, 초기에 읽지않는다.
				 */
				list = new ArrayList<AIB_DEFECT_HIST_ReadBean>();
				return list;
			}
			
			try {
				String query = null;
				
				if ("L5FAB".equals(Parameter.getInstance().getStrLine()) || "L6FAB".equals(Parameter.getInstance().getStrLine())) {
					if ("TFT".equals(Parameter.getInstance().getStrAreaCode())) {
						query = getLine56TFT();
					} else if ("LC".equals(Parameter.getInstance().getStrAreaCode())) {
						query = getLine56LC();
					} else if ("MOD".equals(Parameter.getInstance().getStrAreaCode())) {
						query = getLine56MOD();
					} else {
						return list;
					}
				} else if ("L7AFAB".equals(Parameter.getInstance().getStrLine()) || "L7BFAB".equals(Parameter.getInstance().getStrLine()) || "L8AFAB".equals(Parameter.getInstance().getStrLine()) || "L8ZFAB".equals(Parameter.getInstance().getStrLine())) {
					if ("TFT".equals(Parameter.getInstance().getStrAreaCode())) {
						query = getLine78TFT();
					} else if ("LC".equals(Parameter.getInstance().getStrAreaCode())) {
						query = getLine78LC();
					} else if ("MOD".equals(Parameter.getInstance().getStrAreaCode())) {
						query = getLine78MOD();
					} else {
						return list;
					}
				} else {
					return list;
				}
				
				if (!Flag.TRUE) System.out.println("[" + query + "]");
				
				OracleConnection conn = Connection.getOracleConnection();

				if (Flag.TRUE) {
					Statement stmt = conn.createStatement();
					ResultSet rs = stmt.executeQuery(query);
					for (int i=0; i < 1000000 && rs.next(); i++) {
						if (!Flag.TRUE) {
							System.out.println(String.format("%3d) --------------------------------------", i));
							System.out.println(String.format("    LINE_CODE         = [%s]", rs.getString("LINE_CODE"        )));
							System.out.println(String.format("    USER_GROUP_CODE   = [%s]", rs.getString("USER_GROUP_CODE"  )));
							System.out.println(String.format("    PROCESS_ID        = [%s]", rs.getString("PROCESS_ID"       )));
							System.out.println(String.format("    PRODUCT_ID        = [%s]", rs.getString("PRODUCT_ID"       )));
							System.out.println(String.format("    PRODUCT_TYPE      = [%s]", rs.getString("PRODUCT_TYPE"     )));
							System.out.println(String.format("    AREA_CODE         = [%s]", rs.getString("AREA_CODE"        )));
							System.out.println(String.format("    SUB_AREA_CODE     = [%s]", rs.getString("SUB_AREA_CODE"    )));
							System.out.println(String.format("    GLASS_ID          = [%s]", rs.getString("GLASS_ID"         )));
							System.out.println(String.format("    CELL_ID           = [%s]", rs.getString("CELL_ID"          )));
							System.out.println(String.format("    STEP_ID           = [%s]", rs.getString("STEP_ID"          )));
							System.out.println(String.format("    EQP_ID            = [%s]", rs.getString("EQP_ID"           )));
							System.out.println(String.format("    DEFECT_SEQ        = [%s]", rs.getString("DEFECT_SEQ"       )));
							System.out.println(String.format("    INSPECT_DT        = [%s]", rs.getString("INSPECT_DT"       )));
							System.out.println(String.format("    X_VALUE           = [%s]", rs.getString("X_VALUE"          )));
							System.out.println(String.format("    Y_VALUE           = [%s]", rs.getString("Y_VALUE"          )));
							System.out.println(String.format("    DEFECT_GROUP_CODE = [%s]", rs.getString("DEFECT_GROUP_CODE")));
							System.out.println(String.format("    DECISION_CODE     = [%s]", rs.getString("DECISION_CODE"    )));
							System.out.println(String.format("    INSPECTOR_ID      = [%s]", rs.getString("INSPECTOR_ID"     )));
							System.out.println(String.format("    CELL_LOC_ID       = [%s]", rs.getString("CELL_LOC_ID"      )));
							System.out.println(String.format("    IMAGEPATH         = [%s]", rs.getString("IMAGEPATH"        )));
						}
						
						if (Flag.TRUE) {
							AIB_DEFECT_HIST_ReadBean bean = new AIB_DEFECT_HIST_ReadBean();

							bean.setLineCode       (rs.getString("LINE_CODE"        ));
							bean.setUserGroupCode  (rs.getString("USER_GROUP_CODE"  ));
							bean.setProcessId      (rs.getString("PROCESS_ID"       ));
							bean.setProductId      (rs.getString("PRODUCT_ID"       ));
							bean.setProductType    (rs.getString("PRODUCT_TYPE"     ));
							bean.setAreaCode       (rs.getString("AREA_CODE"        ));
							bean.setSubAreaCode    (rs.getString("SUB_AREA_CODE"    ));
							bean.setGlassId        (rs.getString("GLASS_ID"         ));
							bean.setCellId         (rs.getString("CELL_ID"          ));
							bean.setStepId         (rs.getString("STEP_ID"          ));
							bean.setEqpId          (rs.getString("EQP_ID"           ));
							bean.setDefectSeq      (rs.getString("DEFECT_SEQ"       ));
							bean.setInspectDt      (rs.getString("INSPECT_DT"       ));
							bean.setXValue         (rs.getString("X_VALUE"          ));
							bean.setYValue         (rs.getString("Y_VALUE"          ));
							bean.setDefectGroupCode(rs.getString("DEFECT_GROUP_CODE"));
							bean.setDecisionCode   (rs.getString("DECISION_CODE"    ));
							bean.setInspectorId    (rs.getString("INSPECTOR_ID"     ));
							bean.setCellLocId      (rs.getString("CELL_LOC_ID"      ));
							bean.setImagepath      (rs.getString("IMAGEPATH"        ));
							
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
				
				conn.close();
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
			Parameter.getInstance(2);
			AIB_DEFECT_HIST_OracleReader reader = new AIB_DEFECT_HIST_OracleReader();
			
			for (AIB_DEFECT_HIST_ReadBean bean : reader.getList(true)) {
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
