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

package sdc.anal.lya.macro.A11.TFT_INSPCT_HIST.v03;

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
 * @file_name AIB_TFT_INSPCT_HIST_OracleReader.java
 *
 */
public class TFT_INSPCT_HIST_OracleReader extends AbstractOracleReader
{
	private List<TFT_INSPCT_HIST_ReadBean> list = new ArrayList<TFT_INSPCT_HIST_ReadBean>();
	
	/**
	 * Sample02OracleReader
	 */
	public TFT_INSPCT_HIST_OracleReader()
	{
		if (Flag.TRUE) {
		}
	}

	/**
	 * 
	 * getQuery
	 *
	 * @return
	 */
	private String getQuery()
	{
		String query = null;
		
		query = String.format("" +
				"/* AIB_TFT_INSPCT_HIST : 7,8 LINE  */                                      \n" +
				"/* 탕정Defect분석쿼리2_수정.txt : 적용일: 2012/06/02 from Choi to Kang */  \n" +
				"WITH insp AS (                                                             \n" +
				"    SELECT /*+ PARALLEL(2) */                                              \n" +
				"        t1.siteid AS line_code,                                            \n" +
				"        t2.prodgrpname AS user_group_code, t2.procid AS process_id,        \n" +
				"        t1.prodid AS product_id, t1.prodtype AS product_type,              \n" +
				"        CASE                                                               \n" +
				"            WHEN inspstepid LIKE 'T%%' THEN 'TFT'                          \n" +
				"            WHEN inspstepid LIKE 'F%%' THEN 'CF'                           \n" +
				"            WHEN inspstepid LIKE 'L%%' THEN 'LC'                           \n" +
				"            ELSE 'MOD'                                                     \n" +
				"            END AS area_code,                                              \n" +
				"        t1.inspsteptype AS sub_area_code, t1.inspstepid AS step_id,        \n" +
				"        t1.inspeqpid AS eqp_id, t1.glassid AS glass_id,                    \n" +
				"        t1.cellid AS cell_id, t1.inspdate AS inspect_dt,                   \n" +
				"        REPLACE (cellid, glassid, '') AS cell_loc_id,                      \n" +
				"        t1.defectcd AS defect_group_code,                                  \n" +
				"        t1.decgradecd AS decision_code, t1.workerid AS inspector_id,       \n" +
				"        t1.defectsysid                                                     \n" +
				"    FROM                                                                   \n" +
				"        yms_dm.d_product t2,                                               \n" +
				"        yms_dm.h_inspdefect t1                                             \n" +
				"    WHERE                                                                  \n" +
				"        t1.siteid            =  %s                                         \n" +  // LINE
				"        AND t1.insphour      >= %s                                         \n" +  // FROM_ETL_HOUR
				"        AND t1.insphour      <= %s                                         \n" +  // TO_ETL_HOUR
				"        AND t2.siteid        =  %s                                         \n" +  // LINE
				"        AND t2.prodgrpname   IN (%s)                                       \n" +  // USER_GROUP_CODE
				"        AND t1.prodtype      IN (%s)                                       \n" +  // PRODUCT_TYPE
				"        AND t1.inspsteptype  IN (%s)                                       \n" +  // SUB_AREA_CODE
				"        AND t1.defectcd      IN (%s)                                       \n" +  // DEFECT_GROUP_CODE
				"        AND t1.decgradecd    IN (%s)                                       \n" +  // DECISION_CODE
				"        AND t1.siteid        = t2.siteid                                   \n" +
				"        AND t1.prodid        = t2.prodid                                   \n" +
				"),                                                                         \n" +
				"inspstep AS (                                                              \n" +
				"    SELECT /*+ parallel(2) */                                              \n" +
				"        t3.*, t5.eqptype                                                   \n" +
				"    FROM                                                                   \n" +
				"        insp                  t3,                                          \n" +
				"        yms_dm.d_inspsteptype t4,                                          \n" +
				"        (                                                                  \n" +
				"            SELECT                                                         \n" +
				"                DISTINCT eqpunitid, 'AOI' eqptype                          \n" +
				"            FROM                                                           \n" +
				"                yms_dm.d_inspstep_defecttrace t1                           \n" +
				"            WHERE                                                          \n" +
				"                inspstepgrptype = 'AOI'                                    \n" +
				"        ) t5                                                               \n" +
				"    WHERE                                                                  \n" +
				"        t4.siteid            =  %s                                         \n" +  // LINE
				"        AND t3.sub_area_code =  t4.inspsteptype                            \n" +
				"        AND t4.areaid        IN ( %s )                                     \n" +  // AREA_CODE
				"        AND t3.eqp_id        =  t5.eqpunitid(+)                            \n" +
				"),                                                                         \n" +
				"preinsp AS (                                                               \n" +
				"    SELECT /*+ parallel(2)*/                                               \n" +
				"        t1.line_code, t1.user_group_code, t1.product_id, t1.process_id,    \n" +
				"        t1.product_type, t1.area_code, t1.sub_area_code,                   \n" +
				"        t4.inspstepgrpid insp_step_grp_code,                               \n" +
				"        t4.inspstepgrpname insp_step_grp_name,                             \n" +
				"        t4.inspstepgrptype eqptype, t2.stepid step_id,                     \n" +
				"        t2.eqpunitid eqp_id, t1.glass_id, t1.cell_id,                      \n" +
				"        t2.inspectdate inspect_dt, t1.cell_loc_id,                         \n" +
				"        t3.decisiongrade decision_code,                                    \n" +
				"        t3.defectcd || '_' || %s defect_group_code,                        \n" +  // LINE
				"        t2.inspectempid inspector_id, t2.inspectsysid defectsysid          \n" +
				"    FROM                                                                   \n" +
				"        inspstep                         t1,                               \n" +
				"        YMS_DW.DW_GLASSCELL_INSPEQPHIST  t2,                               \n" +
				"        YMS_DW.DW_CELL_INSPHIST          t3,                               \n" +
				"        yms_dm.D_INSPSTEP_DEFECTTRACE    t4                                \n" +
				"    WHERE                                                                  \n" +
				"        t2.siteid         = %s                                             \n" +  // LINE
				"        AND t1.line_code  = t2.siteid                                      \n" +
				"        AND t1.glass_id   = t3.glassid                                     \n" +
				"        AND t1.cell_id    = t3.cellid                                      \n" +
				"        AND t2.siteid     = t4.siteid                                      \n" +
				"        AND t2.siteid     = t3.siteid                                      \n" +
				"        AND t3.siteid     = t4.siteid                                      \n" +
				"        AND t2.inspectsysid = t3.inspectsysid                              \n" +
				"        AND t2.inspectdate  = t3.inspectdate                               \n" +
				"        AND t2.eqpunitid    = t4.eqpunitid                                 \n" +
				"        AND t2.stepid LIKE t4.inspstepgrpid                                \n" +
				"        AND t4.siteid     = %s                                             \n" +  // LINE
				"        AND t4.areaid     = %s                                             \n" +  // AREA_CODE
				"),                                                                         \n" +
				"cell AS (                                                                  \n" +
				"    SELECT                                                                 \n" +
				"        t1.*, t3.width, t3.height, t3.x_logic1, t3.y_logic1, t3.x_logic2,  \n" +
				"        t3.y_logic2, pixcel_x, pixcel_y, point_x, point_y                  \n" +
				"    FROM                                                                   \n" +
				"        yms_dm.d_cellconvert  t3,                                          \n" +
				"        preinsp               t1                                           \n" +
				"    WHERE                                                                  \n" +
				"        t3.siteid IN ('L7', 'L8')                                          \n" +
				"        AND t1.process_id LIKE t3.procid || '%%'                           \n" +
				"        AND t1.cell_loc_id = t3.celllocid                                  \n" +
				")                                                                          \n" +
				"SELECT                                                                     \n" +
				"    t3.line_code,                                                          \n" +
				"    t3.user_group_code,                                                    \n" +
				"    t3.product_id,                                                         \n" +
				"    t3.process_id,                                                         \n" +
				"    t3.product_type,                                                       \n" +
				"    t3.area_code,                                                          \n" +
				"    t3.sub_area_code,                                                      \n" +
				"    t3.insp_step_grp_code,                                                 \n" +
				"    t3.insp_step_grp_name,                                                 \n" +
				"    t3.eqptype,                                                            \n" +
				"    t3.step_id,                                                            \n" +
				"    t3.eqp_id,                                                             \n" +
				"    t3.glass_id,                                                           \n" +
				"    t3.cell_id,                                                            \n" +
				"    t3.inspect_dt,                                                         \n" +
				"    t3.cell_loc_id,                                                        \n" +
				"    t3.decision_code,                                                      \n" +
				"    t3.defect_group_code,                                                  \n" +
				"    t3.inspector_id,                                                       \n" +
				"    t3.defectsysid,                                                        \n" +
				"    to_char(t2.seq) defect_seq,                                            \n" +
				"    t2.xcoord AS x_value,                                                  \n" +
				"    t2.ycoord AS y_value                                                   \n" +
				"FROM                                                                       \n" +
				"    cell t3,                                                               \n" +
				"    YMS_DW.DW_DEFECT_INSPHIST t2                                           \n" +
				"WHERE                                                                      \n" +
				"    t2.siteid          = %s                                                \n" +  // LINE
				"    AND t3.defectsysid = t2.inspectsysid                                   \n" +
				"    AND t3.cell_id     = t2.cellid                                         \n" +
				"    AND t3.line_code   = t2.siteid                                         \n" +
				""
				, StrUtil.quote(Parameter.getInstance().getStrLine())
				, StrUtil.quote(Parameter.getInstance().getStrFromDateTime())
				, StrUtil.quote(Parameter.getInstance().getStrToDateTime())
				, StrUtil.quote(Parameter.getInstance().getStrLine())
				, StrUtil.quote(Parameter.getInstance().getStrUserGroupCode())
				, StrUtil.quote(Parameter.getInstance().getStrProductType())
				, StrUtil.quote(Parameter.getInstance().getStrSubAreaCode())
				, StrUtil.quote(Parameter.getInstance().getStrDefectGroupCode())
				, StrUtil.quote(Parameter.getInstance().getStrDecisionCode())
				, StrUtil.quote(Parameter.getInstance().getStrLine())
				, StrUtil.quote(Parameter.getInstance().getStrAreaCode())
				, StrUtil.quote(Parameter.getInstance().getStrLine())
				, StrUtil.quote(Parameter.getInstance().getStrLine())
				, StrUtil.quote(Parameter.getInstance().getStrLine())
				, StrUtil.quote(Parameter.getInstance().getStrAreaCode())
				, StrUtil.quote(Parameter.getInstance().getStrLine())
				);

		return query;
	}

	/**
	 * getList
	 */
	@Override
	public List<TFT_INSPCT_HIST_ReadBean> getList()
	{
		return getList(false);
	}
	
	public List<TFT_INSPCT_HIST_ReadBean> getList(boolean flgRead)
	{
		if (Flag.TRUE) {
			if (!flgRead) {
				/*
				 * if flgRead = true, 초기에 읽지않는다.
				 */
				list = new ArrayList<TFT_INSPCT_HIST_ReadBean>();
				return list;
			}
			
			try {
				String query = null;
				
				query = getQuery();
				
				if (!Flag.TRUE) System.out.println("[" + query + "]");
				if (Flag.TRUE) Logger.info("[" + query + "]");
				
				OracleConnection conn = Connection04.getInstance().getConn();

				if (Flag.TRUE) {
					Statement stmt = conn.createStatement();
					ResultSet rs = stmt.executeQuery(query);
					for (int i=0; i < 1000000 && rs.next(); i++) {
						if (!Flag.TRUE) {
							System.out.println(String.format("    LINE_CODE          = [%s]", rs.getString("LINE_CODE"         )));
							System.out.println(String.format("    USER_GROUP_CODE    = [%s]", rs.getString("USER_GROUP_CODE"   )));
							System.out.println(String.format("    PRODUCT_ID         = [%s]", rs.getString("PRODUCT_ID"        )));
							System.out.println(String.format("    PROCESS_ID         = [%s]", rs.getString("PROCESS_ID"        )));
							System.out.println(String.format("    PRODUCT_TYPE       = [%s]", rs.getString("PRODUCT_TYPE"      )));
							System.out.println(String.format("    AREA_CODE          = [%s]", rs.getString("AREA_CODE"         )));
							System.out.println(String.format("    SUB_AREA_CODE      = [%s]", rs.getString("SUB_AREA_CODE"     )));
							System.out.println(String.format("    INSP_STEP_GRP_CODE = [%s]", rs.getString("INSP_STEP_GRP_CODE")));
							System.out.println(String.format("    INSP_STEP_GRP_NAME = [%s]", rs.getString("INSP_STEP_GRP_NAME")));
							System.out.println(String.format("    EQPTYPE            = [%s]", rs.getString("EQPTYPE"           )));
							System.out.println(String.format("    STEP_ID            = [%s]", rs.getString("STEP_ID"           )));
							System.out.println(String.format("    EQP_ID             = [%s]", rs.getString("EQP_ID"            )));
							System.out.println(String.format("    GLASS_ID           = [%s]", rs.getString("GLASS_ID"          )));
							System.out.println(String.format("    CELL_ID            = [%s]", rs.getString("CELL_ID"           )));
							System.out.println(String.format("    INSPECT_DT         = [%s]", rs.getString("INSPECT_DT"        )));
							System.out.println(String.format("    CELL_LOC_ID        = [%s]", rs.getString("CELL_LOC_ID"       )));
							System.out.println(String.format("    DECISION_CODE      = [%s]", rs.getString("DECISION_CODE"     )));
							System.out.println(String.format("    DEFECT_GROUP_CODE  = [%s]", rs.getString("DEFECT_GROUP_CODE" )));
							System.out.println(String.format("    INSPECTOR_ID       = [%s]", rs.getString("INSPECTOR_ID"      )));
							System.out.println(String.format("    DEFECTSYSID        = [%s]", rs.getString("DEFECTSYSID"       )));
							System.out.println(String.format("    DEFECT_SEQ         = [%s]", rs.getString("DEFECT_SEQ"        )));
							System.out.println(String.format("    X_VALUE            = [%s]", rs.getString("X_VALUE"           )));
							System.out.println(String.format("    Y_VALUE            = [%s]", rs.getString("Y_VALUE"           )));
						}
						
						if (Flag.TRUE) {
							TFT_INSPCT_HIST_ReadBean bean = new TFT_INSPCT_HIST_ReadBean();

							bean.setLineCode       (rs.getString("LINE_CODE"         ));
							bean.setUserGroup_code (rs.getString("USER_GROUP_CODE"   ));
							bean.setProductId      (rs.getString("PRODUCT_ID"        ));
							bean.setProcessId      (rs.getString("PROCESS_ID"        ));
							bean.setProductType    (rs.getString("PRODUCT_TYPE"      ));
							bean.setAreaCode       (rs.getString("AREA_CODE"         ));
							bean.setSubAreaCode    (rs.getString("SUB_AREA_CODE"     ));
							bean.setInspStepGrpCode(rs.getString("INSP_STEP_GRP_CODE"));
							bean.setInspStepGrpName(rs.getString("INSP_STEP_GRP_NAME"));
							bean.setEqpType        (rs.getString("EQPTYPE"           ));
							bean.setStepId         (rs.getString("STEP_ID"           ));
							bean.setEqpId          (rs.getString("EQP_ID"            ));
							bean.setGlassId        (rs.getString("GLASS_ID"          ));
							bean.setCellId         (rs.getString("CELL_ID"           ));
							bean.setInspectDt      (rs.getString("INSPECT_DT"        ));
							bean.setCellLocId      (rs.getString("CELL_LOC_ID"       ));
							bean.setDecisionCode   (rs.getString("DECISION_CODE"     ));
							bean.setDefectGroupCode(rs.getString("DEFECT_GROUP_CODE" ));
							bean.setInspectorId    (rs.getString("INSPECTOR_ID"      ));
							bean.setDefectSysId    (rs.getString("DEFECTSYSID"       ));
							bean.setDefectSeq      (rs.getString("DEFECT_SEQ"        ));
							bean.setXValue         (rs.getString("X_VALUE"           ));
							bean.setYValue         (rs.getString("Y_VALUE"           ));
							
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
				Logger.getInstance(TFT_INSPCT_HIST_Main.jobId, TFT_INSPCT_HIST_Main.dsName + ".log");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		if (Flag.TRUE) {
			Parameter.getInstance(TFT_INSPCT_HIST_Main.jobId);
			TFT_INSPCT_HIST_OracleReader reader = new TFT_INSPCT_HIST_OracleReader();
			
			for (TFT_INSPCT_HIST_ReadBean bean : reader.getList(true)) {
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
