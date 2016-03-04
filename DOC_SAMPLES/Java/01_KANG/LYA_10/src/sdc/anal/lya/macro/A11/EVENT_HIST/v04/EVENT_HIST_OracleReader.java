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

package sdc.anal.lya.macro.A11.EVENT_HIST.v04;

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
 * @file_name AIB_EVENT_HIST_OracleReader.java
 *
 */
public class EVENT_HIST_OracleReader extends AbstractOracleReader
{
	private List<EVENT_HIST_ReadBean> list = new ArrayList<EVENT_HIST_ReadBean>();
	
	/**
	 * Sample02OracleReader
	 */
	public EVENT_HIST_OracleReader()
	{
		if (Flag.TRUE) {
		}
	}

	/**
	 * 
	 * getLine56
	 *
	 * @return
	 */
	private String getLine56()
	{
		String query = null;
		
		query = String.format("" +
				"/* EVENT_HIST : 천안 : 5,6라인 */                                                  \n" +
				"WITH insp AS (                                                                     \n" +
				"    SELECT /*+ PARALLEL(2) */                                                      \n" +
				"        'LCD' || SUBSTR (t1.siteid, 2, 1) AS line_code,                            \n" +
				"        t2.prodgrpname AS user_group_code, t2.procid AS process_id,                \n" +
				"        t1.prodid AS product_id, t1.prodtype AS product_type,                      \n" +
				"        CASE                                                                       \n" +
				"           WHEN inspstepid LIKE 'T%%' THEN 'TFT'                                   \n" +
				"           WHEN inspstepid LIKE 'F%%' THEN 'CF'                                    \n" +
				"           WHEN inspstepid LIKE 'L%%' THEN 'LC'                                    \n" +
				"           ELSE 'MOD'                                                              \n" +
				"        END AS area_code,                                                          \n" +
				"        t1.inspsteptype AS sub_area_code, t1.inspstepid AS step_id,                \n" +
				"        t1.inspeqpid AS eqp_id, t1.glassid AS glass_id,                            \n" +
				"        SUBSTR (t1.glassid, 1, 7) || '.' || SUBSTR (t1.glassid, 8) AS glass_id2,   \n" +
				"        t1.cellid AS cell_id, t1.inspdate AS inspect_dt,                           \n" +
				"        REPLACE (cellid, glassid, '') AS cell_loc_id,                              \n" +
				"        t1.defectcd AS defect_group_code, t1.decgradecd AS decision_code,          \n" +
				"        t1.workerid AS inspector_id                                                \n" +
				"    FROM                                                                           \n" +
				"        yms_dm.d_product      t2,                                                  \n" +
				"        yms_dm.h_inspdefect   t1                                                   \n" +
				"    WHERE                                                                          \n" +
				"        /* LINE L6 => L6FAB 으로 변경 프로시저 변경 혹은 매크로 변수 변경 필요 */  \n" +
				"        /* DEFECT_GROUP_CODE E06_L6FAB => E06 으로 변경 됨*/                       \n" +
				"        t1.siteid            = %s                                                  \n" +  // LINE
				"        AND t1.insphour      >= %s                                                 \n" +  // FROM_ETL_HOUR
				"        AND t1.insphour      <= %s                                                 \n" +  // TO_ETL_HOUR
				"        AND t2.prodgrpname   IN (%s)                                               \n" +  // USER_GROUP_CODE
				"        AND t1.prodtype      IN (%s)                                               \n" +  // PRODUCT_TYPE
				"        AND t1.inspsteptype  IN (%s)                                               \n" +  // SUB_AREA_CODE
				"        AND t1.defectcd      IN (%s)                                               \n" +  // DEFECT_GROUP_CODE
				"        AND t1.decgradecd    IN (%s)                                               \n" +  // DECISION_CODE
				"        AND t2.siteid        IN (%s)                                               \n" +  // LINE
				"        AND t1.prodid        = t2.prodid                                           \n" +
				")                                                                                  \n" +
				"SELECT                                                                             \n" +
				"    t1.user_group_code, t1.process_id, t1.product_id, t1.product_type,             \n" +
				"    t1.area_code, t1.sub_area_code, t2.lineid line_code,                           \n" +
				"    REPLACE (t2.glasscellid, '.', '') glass_id,                                    \n" +
				"    CASE                                                                           \n" +
				"        WHEN t2.activity = 'Rework' THEN 'REWORK'                                  \n" +
				"        WHEN t2.eventvalue = 'FDC'  THEN 'FDC'                                     \n" +
				"        ELSE 'SPC'                                                                 \n" +
				"        END event_occur_code,                                                      \n" +
				"    CASE                                                                           \n" +
				"        WHEN t2.activity = 'Rework' THEN 'REWORK'                                  \n" +
				"        WHEN t2.eventvalue = 'FDC' THEN 'FDC'                                      \n" +
				"        ELSE 'SPC'                                                                 \n" +
				"        END event_occur_detial_code,                                               \n" +
				"    t2.stepid step_id, t2.eqpid eqp_id, t2.commentdesc comments,                   \n" +
				"    t2.activity work_status, t2.eventvalue event_occur_id,                         \n" +
				"    t2.eventtime event_occur_dt, '-' unit_id, t3.layerid layer_code,               \n" +
				"    TO_DATE ('99991231', 'YYYYMMDD') work_start_dt,                                \n" +
				"    TO_DATE ('99991231', 'YYYYMMDD') work_end_dt                                   \n" +
				"FROM                                                                               \n" +
				"    insp                               t1,                                         \n" +
				"    yms_dw.dw_glasscell_eventhist_ca   t2,                                         \n" +
				"    yms_dw.dw_md_step                  t3                                          \n" +
				"WHERE 1 = 1                                                                        \n" +
				"    AND t1.line_code = t2.lineid                                                   \n" +
				"    AND t1.glass_id = t2.glasscellid                                               \n" +
				"    AND (                                                                          \n" +
				"        t2.activity = 'Rework'                                                     \n" +
				"        OR (t2.activity = 'Hold' AND t2.eventvalue IN ('FDC', 'SPC'))              \n" +
				"    )                                                                              \n" +
				"    AND t2.processid = t3.processid                                                \n" +
				"    AND t2.stepid = t3.stepid                                                      \n" +
				""
				, StrUtil.quote(Parameter.getInstance().getStrLine())
				, StrUtil.quote(Parameter.getInstance().getStrFromDateTime())
				, StrUtil.quote(Parameter.getInstance().getStrToDateTime())
				, StrUtil.quote(Parameter.getInstance().getStrUserGroupCode())
				, StrUtil.quote(Parameter.getInstance().getStrProductType())
				, StrUtil.quote(Parameter.getInstance().getStrSubAreaCode())
				, StrUtil.quote(Parameter.getInstance().getStrDefectGroupCode())
				, StrUtil.quote(Parameter.getInstance().getStrDecisionCode())
				, StrUtil.quote(Parameter.getInstance().getStrLine())
		);

		return query;
	}
	
	/**
	 * 
	 * getLine78
	 *
	 * @return
	 */
	private String getLine78()
	{
		String query = null;
		
		query = String.format("" +
				"/* EVENT_HIST : 아산 : 7,8 라인 */                                                        \n" +
				"WITH insp AS (                                                                            \n" +
				"    SELECT /*+ PARALLEL(2) */                                                             \n" +
				"        t1.siteid AS line_code, t2.prodgrpname AS user_group_code,                        \n" +
				"        t2.procid AS process_id, t1.prodid AS product_id,                                 \n" +
				"        t1.prodtype AS product_type,                                                      \n" +
				"        CASE                                                                              \n" +
				"            WHEN inspstepid LIKE 'T%%' THEN 'TFT'                                         \n" +
				"            WHEN inspstepid LIKE 'F%%' THEN 'CF'                                          \n" +
				"            WHEN inspstepid LIKE 'L%%' THEN 'LC'                                          \n" +
				"            ELSE 'MOD'                                                                    \n" +
				"            END AS area_code,                                                             \n" +
				"        t1.inspsteptype AS sub_area_code, t1.inspstepid AS step_id,                       \n" +
				"        t1.inspeqpid AS eqp_id, t1.glassid AS glass_id,                                   \n" +
				"        t1.cellid AS cell_id, t1.inspdate AS inspect_dt,                                  \n" +
				"        REPLACE (cellid, glassid, '') AS cell_loc_id,                                     \n" +
				"        t1.defectcd AS defect_group_code, t1.decgradecd AS decision_code,                 \n" +
				"        t1.workerid AS inspector_id                                                       \n" +
				"    FROM                                                                                  \n" +
				"        yms_dm.d_product     t2,                                                          \n" +
				"        yms_dm.h_inspdefect  t1                                                           \n" +
				"    WHERE                                                                                 \n" +
				"        /* LINE 7A => L7AFAB 으로 변경 됨 */                                              \n" +
				"        /* DEFECT_GROUP_CODE LRLA_7BFAB => LRLA 으로 변경됨*/                             \n" +
				"        t1.siteid            = %s                                                         \n" +  // LINE
				"        AND t1.insphour      >= %s                                                        \n" +  // FROM_ETL_HOUR
				"        AND t1.insphour      <= %s                                                        \n" +  // TO_ETL_HOUR
				"        AND t2.prodgrpname   IN (%s)                                                      \n" +  // USER_GROUP_CODE
				"        AND t1.prodtype      IN (%s)                                                      \n" +  // PRODUCT_TYPE
				"        AND t1.inspsteptype  IN (%s)                                                      \n" +  // SUB_AREA_CODE
				"        AND t1.defectcd      IN (%s)                                                      \n" +  // DEFECT_GROUP_CODE
				"        /* AND t1.decgradecd IN ('O','X','DF', 'DR', 'NF', 'NR') */                       \n" +
				"        AND t1.decgradecd    IN (%s)                                                      \n" +  // DECISION_CODE
				"        AND t2.siteid        IN (%s)                                                      \n" +  // LINE
				"        AND t1.prodid        = t2.prodid                                                  \n" +
				")                                                                                         \n" +
				"SELECT                                                                                    \n" +
				"    t1.user_group_code, t1.process_id, t1.product_id, t1.product_type,                    \n" +
				"    t1.area_code, t1.sub_area_code, t2.owningsiteid line_code,                            \n" +
				"    t2.glasscellid glass_id,                                                              \n" +
				"    CASE                                                                                  \n" +
				"        WHEN t2.workerid = 'FDC' THEN 'FDC'                                               \n" +
				"        ELSE 'SPC'                                                                        \n" +
				"        END event_occur_code,                                                             \n" +
				"    CASE                                                                                  \n" +
				"        WHEN t2.workerid = 'FDC' THEN 'FDC'                                               \n" +
				"        ELSE 'SPC'                                                                        \n" +
				"        END event_occur_detial_code, t2.stepid step_id, t2.eqpid eqp_id,                  \n" +
				"    t2.commentdesc comments, t2.glassstatus work_status,                                  \n" +
				"    t2.eventvalue event_occur_id, t2.eventdate event_occur_dt, '-' unit_id,               \n" +
				"    t3.layerid layer_code, TO_DATE ('99991231', 'YYYYMMDD') work_start_dt,                \n" +
				"    TO_DATE ('99991231', 'YYYYMMDD') work_end_dt                                          \n" +
				"FROM                                                                                      \n" +
				"    insp                             t1,                                                  \n" +
				"    yms_dw.dw_glasscell_eventhist    t2,                                                  \n" +
				"    yms_dw.dw_md_step                t3                                                   \n" +
				"WHERE 1 = 1                                                                               \n" +
				"    AND t1.line_code = t2.siteid                                                          \n" +
				"    AND t1.glass_id = t2.glasscellid                                                      \n" +
				"    AND t2.activity = 'Hold'                                                              \n" +
				"    AND t2.workerid IN ('FDC', 'SPC')                                                     \n" +
				"    AND t2.siteid = t3.sourcesiteid                                                       \n" +
				"    AND t2.processid = t3.processid                                                       \n" +
				"    AND t2.stepid = t3.stepid                                                             \n" +
				"UNION ALL                                                                                 \n" +
				"SELECT                                                                                    \n" +
				"    t1.user_group_code, t1.process_id, t1.product_id, t1.product_type,                    \n" +
				"    t1.area_code, t1.sub_area_code, t2.siteid line_code,                                  \n" +
				"    t2.glasscellid glass_id, 'REWORK' event_occur_code,                                   \n" +
				"    'REWORK' event_occur_detial_code, t2.stepid step_id, t2.eqpid eqp_id,                 \n" +
				"    REPLACE (t2.steptype, 'Rework') || '_Rework:' || NVL (t3.defectname, '') comments,    \n" +
				"    'Rework' work_status, '-' event_occur_id, t2.eventdate event_occur_dt,                \n" +
				"    t2.eqpunitid unit_id, t4.layerid layer_code,                                          \n" +
				"    TO_DATE ('99991231', 'YYYYMMDD') work_start_dt,                                       \n" +
				"    TO_DATE ('99991231', 'YYYYMMDD') work_end_dt                                          \n" +
				"FROM                                                                                      \n" +
				"    insp                      t1,                                                         \n" +
				"    yms_dm.h_glasscell_rework t2,                                                         \n" +
				"    yms_dm.d_defect           t3,                                                         \n" +
				"    yms_dw.dw_md_step         t4                                                          \n" +
				"WHERE 1 = 1                                                                               \n" +
				"    AND t1.line_code = t2.siteid                                                          \n" +
				"    AND t1.glass_id = t2.glasscellid                                                      \n" +
				"    AND t2.mainsteptype = 'N'                                                             \n" +
				"    AND t2.siteid = t4.sourcesiteid                                                       \n" +
				"    AND t2.procid = t4.processid                                                          \n" +
				"    AND t2.stepid = t4.stepid                                                             \n" +
				"    AND t2.defectcd = t3.defectcd(+)                                                      \n" +
				""
				, StrUtil.quote(Parameter.getInstance().getStrLine())
				, StrUtil.quote(Parameter.getInstance().getStrFromDateTime())
				, StrUtil.quote(Parameter.getInstance().getStrToDateTime())
				, StrUtil.quote(Parameter.getInstance().getStrUserGroupCode())
				, StrUtil.quote(Parameter.getInstance().getStrProductType())
				, StrUtil.quote(Parameter.getInstance().getStrSubAreaCode())
				, StrUtil.quote(Parameter.getInstance().getStrDefectGroupCode())
				, StrUtil.quote(Parameter.getInstance().getStrDecisionCode())
				, StrUtil.quote(Parameter.getInstance().getStrLine())
		);

		return query;
	}
	
	/**
	 * getList
	 */
	@Override
	public List<EVENT_HIST_ReadBean> getList()
	{
		return getList(false);
	}
	
	public List<EVENT_HIST_ReadBean> getList(boolean flgRead)
	{
		if (Flag.TRUE) {
			if (!flgRead) {
				/*
				 * if flgRead = true, 초기에 읽지않는다.
				 */
				list = new ArrayList<EVENT_HIST_ReadBean>();
				return list;
			}
			
			try {
				String query = null;

				if ("L5FAB".equals(Parameter.getInstance().getStrLine()) || "L6FAB".equals(Parameter.getInstance().getStrLine())) {
					query = getLine56();
				} else if ("L7AFAB".equals(Parameter.getInstance().getStrLine()) || "L7BFAB".equals(Parameter.getInstance().getStrLine()) || "L8AFAB".equals(Parameter.getInstance().getStrLine()) || "L8ZFAB".equals(Parameter.getInstance().getStrLine())) {
					query = getLine78();
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
							System.out.println(String.format("    USER_GROUP_CODE         = [%s]", rs.getString("USER_GROUP_CODE"        )));
							System.out.println(String.format("    PROCESS_ID              = [%s]", rs.getString("PROCESS_ID"             )));
							System.out.println(String.format("    PRODUCT_ID              = [%s]", rs.getString("PRODUCT_ID"             )));
							System.out.println(String.format("    PRODUCT_TYPE            = [%s]", rs.getString("PRODUCT_TYPE"           )));
							System.out.println(String.format("    AREA_CODE               = [%s]", rs.getString("AREA_CODE"              )));
							System.out.println(String.format("    SUB_AREA_CODE           = [%s]", rs.getString("SUB_AREA_CODE"          )));
							System.out.println(String.format("    LINE_CODE               = [%s]", rs.getString("LINE_CODE"              )));
							System.out.println(String.format("    GLASS_ID                = [%s]", rs.getString("GLASS_ID"               )));
							System.out.println(String.format("    EVENT_OCCUR_CODE        = [%s]", rs.getString("EVENT_OCCUR_CODE"       )));
							System.out.println(String.format("    EVENT_OCCUR_DETIAL_CODE = [%s]", rs.getString("EVENT_OCCUR_DETIAL_CODE")));
							System.out.println(String.format("    STEP_ID                 = [%s]", rs.getString("STEP_ID"                )));
							System.out.println(String.format("    EQP_ID                  = [%s]", rs.getString("EQP_ID"                 )));
							System.out.println(String.format("    COMMENTS                = [%s]", rs.getString("COMMENTS"               )));
							System.out.println(String.format("    WORK_STATUS             = [%s]", rs.getString("WORK_STATUS"            )));
							System.out.println(String.format("    EVENT_OCCUR_ID          = [%s]", rs.getString("EVENT_OCCUR_ID"         )));
							System.out.println(String.format("    EVENT_OCCUR_DT          = [%s]", rs.getString("EVENT_OCCUR_DT"         )));
							System.out.println(String.format("    UNIT_ID                 = [%s]", rs.getString("UNIT_ID"                )));
							System.out.println(String.format("    LAYER_CODE              = [%s]", rs.getString("LAYER_CODE"             )));
							System.out.println(String.format("    WORK_START_DT           = [%s]", rs.getString("WORK_START_DT"          )));
							System.out.println(String.format("    WORK_END_DT             = [%s]", rs.getString("WORK_END_DT"            )));
						}
						
						if (Flag.TRUE) {
							EVENT_HIST_ReadBean bean = new EVENT_HIST_ReadBean();

							bean.setUserGroupCode       (rs.getString("USER_GROUP_CODE"        ));
							bean.setProcessId           (rs.getString("PROCESS_ID"             ));
							bean.setProductId           (rs.getString("PRODUCT_ID"             ));
							bean.setProductType         (rs.getString("PRODUCT_TYPE"           ));
							bean.setAreaCode            (rs.getString("AREA_CODE"              ));
							bean.setSubAreaCode         (rs.getString("SUB_AREA_CODE"          ));
							bean.setLineCode            (rs.getString("LINE_CODE"              ));
							bean.setGlassId             (rs.getString("GLASS_ID"               ));
							bean.setEventOccurCode      (rs.getString("EVENT_OCCUR_CODE"       ));
							bean.setEventOccurDetialCode(rs.getString("EVENT_OCCUR_DETIAL_CODE"));
							bean.setStepId              (rs.getString("STEP_ID"                ));
							bean.setEqpId               (rs.getString("EQP_ID"                 ));
							bean.setComments            (rs.getString("COMMENTS"               ));
							bean.setWorkStatus          (rs.getString("WORK_STATUS"            ));
							bean.setEventOccurId        (rs.getString("EVENT_OCCUR_ID"         ));
							bean.setEventOccurDt        (rs.getString("EVENT_OCCUR_DT"         ));
							bean.setUnitId              (rs.getString("UNIT_ID"                ));
							bean.setLayerCode           (rs.getString("LAYER_CODE"             ));
							bean.setWorkStartDt         (rs.getString("WORK_START_DT"          ));
							bean.setWorkEndDt           (rs.getString("WORK_END_DT"            ));
							
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
				Logger.getInstance(EVENT_HIST_Main.jobId, EVENT_HIST_Main.dsName + ".log");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		if (Flag.TRUE) {
			Parameter.getInstance(EVENT_HIST_Main.jobId);
			EVENT_HIST_OracleReader reader = new EVENT_HIST_OracleReader();
			
			for (EVENT_HIST_ReadBean bean : reader.getList(true)) {
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
