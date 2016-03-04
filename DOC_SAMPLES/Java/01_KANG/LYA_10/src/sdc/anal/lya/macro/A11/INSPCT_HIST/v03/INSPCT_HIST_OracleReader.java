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

package sdc.anal.lya.macro.A11.INSPCT_HIST.v03;

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
 * @file_name AIB_INSPCT_HIST_OracleReader.java
 *
 */
public class INSPCT_HIST_OracleReader extends AbstractOracleReader
{
	private List<INSPCT_HIST_ReadBean> list = new ArrayList<INSPCT_HIST_ReadBean>();
	
	/**
	 * Sample02OracleReader
	 */
	public INSPCT_HIST_OracleReader()
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
	private String getQueryMOD()
	{
		String query = null;
		
		query = String.format("" +
				"/* AIB_INSPCT_HIST : MOD */                        \n" +
				"SELECT /*+ PARALLEL(2) */                          \n" +
				"    t1.siteid AS line_code,                        \n" +
				"    t2.prodgrpname AS user_group_code,             \n" +
				"    t2.procid AS process_id,                       \n" +
				"    t1.prodid AS product_id,                       \n" +
				"    t1.prodtype AS product_type,                   \n" +
				"    CASE                                           \n" +
				"        WHEN inspstepid LIKE 'T%%' THEN 'TFT'      \n" +
				"        WHEN inspstepid LIKE 'F%%' THEN 'CF'       \n" +
				"        WHEN inspstepid LIKE 'L%%' THEN 'LC'       \n" +
				"        ELSE 'MOD'                                 \n" +
				"        END AS area_code,                          \n" +
				"    t1.inspsteptype AS sub_area_code,              \n" +
				"    t1.inspstepid AS step_id,                      \n" +
				"    t1.inspeqpid AS eqp_id,                        \n" +
				"    t1.glassid AS glass_id,                        \n" +
				"    t1.cellid AS cell_id,                          \n" +
				"    t1.inspdate AS inspect_dt,                     \n" +
				"    REPLACE (cellid, glassid,'') AS cell_loc_id,   \n" +
				"    t1.defectcd AS defect_group_code,              \n" +
				"    t1.decgradecd AS decision_code,                \n" +
				"    t1.workerid AS inspector_id                    \n" +
				"FROM                                               \n" +
				"    yms_dm.d_product t2,                           \n" +
				"    yms_dm.h_inspdefect t1                         \n" +
				"WHERE                                              \n" +
				"    t1.siteid           =  %s                      \n" +  // LINE
				"    AND t1.insphour     >= %s                      \n" +  // FROM_ETL_HOUR
				"    AND t1.insphour     <= %s                      \n" +  // TO_ETL_HOUR
				"    AND t2.prodgrpname  IN (%s)                    \n" +  // USER_GROUP_CODE
				"    AND t1.prodtype     IN (%s)                    \n" +  // PRODUCT_TYPE
				"    AND t1.inspsteptype IN (%s)                    \n" +  // SUB_AREA_CODE
				"    /* AND t2.siteid    IN (%s) */                 \n" +  // LINE
				"    AND t1.prodid       =  t2.prodid               \n" +
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
	 * getLine78
	 *
	 * @return
	 */
	private String getQueryOther()
	{
		String query = null;
		
		query = String.format("" +
				"/* AIB_INSPCT_HIST : MOD 이외 */                     \n" +
				"SELECT /*+ PARALLEL(2) */                            \n" +
				"    t1.siteid AS line_code,                          \n" +
				"    t2.prodgrpname AS user_group_code,               \n" +
				"    t2.procid AS process_id,                         \n" +
				"    t1.prodid AS product_id,                         \n" +
				"    t1.prodtype AS product_type,                     \n" +
				"    CASE                                             \n" +
				"        WHEN inspstepid LIKE 'T%%' THEN 'TFT'        \n" +
				"        WHEN inspstepid LIKE 'F%%' THEN 'CF'         \n" +
				"        WHEN inspstepid LIKE 'L%%' THEN 'LC'         \n" +
				"        ELSE 'MOD'                                   \n" +
				"        END AS area_code,                            \n" +
				"    t1.inspsteptype AS sub_area_code,                \n" +
				"    t1.inspstepid AS step_id,                        \n" +
				"    t1.inspeqpid AS eqp_id,                          \n" +
				"    t1.glassid AS glass_id,                          \n" +
				"    t1.cellid AS cell_id,                            \n" +
				"    t1.inspdate AS inspect_dt,                       \n" +
				"    REPLACE (cellid, glassid,'') AS cell_loc_id,     \n" +
				"    t1.defectcd AS defect_group_code,                \n" +
				"    t1.decgradecd AS decision_code,                  \n" +
				"    t1.workerid AS inspector_id                      \n" +
				"FROM                                                 \n" +
				"    yms_dm.d_product      t2,                        \n" +
				"    yms_dm.h_inspdefect   t1                         \n" +
				"WHERE                                                \n" +
				"    t1.siteid            =  %s                       \n" +  // LINE
				"    AND t1.insphour      >= %s                       \n" +  // FROM_ETL_HOUR
				"    AND t1.insphour      <= %s                       \n" +  // TO_ETL_HOUR
				"    AND t2.prodgrpname   IN (%s)                     \n" +  // USER_GROUP_CODE
				"    AND t1.prodtype      IN (%s)                     \n" +  // PRODUCT_TYPE
				"    AND t1.inspsteptype  IN (%s)                     \n" +  // SUB_AREA_CODE
				"    AND t2.siteid        IN (%s)                     \n" +  // LINE
				"    AND t1.prodid        =  t2.prodid                \n" +
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
	 * getList
	 */
	@Override
	public List<INSPCT_HIST_ReadBean> getList()
	{
		return getList(false);
	}
	
	public List<INSPCT_HIST_ReadBean> getList(boolean flgRead)
	{
		if (Flag.TRUE) {
			if (!flgRead) {
				/*
				 * if flgRead = true, 초기에 읽지않는다.
				 */
				list = new ArrayList<INSPCT_HIST_ReadBean>();
				return list;
			}
			
			try {
				String query = null;

				if ("MOD".equals(Parameter.getInstance().getStrAreaCode())) {
					query = getQueryMOD();
				} else {
					query = getQueryOther();
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
							System.out.println(String.format("    LINE_CODE         = [%s]", rs.getString("LINE_CODE"        )));
							System.out.println(String.format("    USER_GROUP_CODE   = [%s]", rs.getString("USER_GROUP_CODE"  )));
							System.out.println(String.format("    PROCESS_ID        = [%s]", rs.getString("PROCESS_ID"       )));
							System.out.println(String.format("    PRODUCT_ID        = [%s]", rs.getString("PRODUCT_ID"       )));
							System.out.println(String.format("    PRODUCT_TYPE      = [%s]", rs.getString("PRODUCT_TYPE"     )));
							System.out.println(String.format("    AREA_CODE         = [%s]", rs.getString("AREA_CODE"        )));
							System.out.println(String.format("    SUB_AREA_CODE     = [%s]", rs.getString("SUB_AREA_CODE"    )));
							System.out.println(String.format("    STEP_ID           = [%s]", rs.getString("STEP_ID"          )));
							System.out.println(String.format("    EQP_ID            = [%s]", rs.getString("EQP_ID"           )));
							System.out.println(String.format("    GLASS_ID          = [%s]", rs.getString("GLASS_ID"         )));
							System.out.println(String.format("    CELL_ID           = [%s]", rs.getString("CELL_ID"          )));
							System.out.println(String.format("    INSPECT_DT        = [%s]", rs.getString("INSPECT_DT"       )));
							System.out.println(String.format("    CELL_LOC_ID       = [%s]", rs.getString("CELL_LOC_ID"      )));
							System.out.println(String.format("    DEFECT_GROUP_CODE = [%s]", rs.getString("DEFECT_GROUP_CODE")));
							System.out.println(String.format("    DECISION_CODE     = [%s]", rs.getString("DECISION_CODE"    )));
							System.out.println(String.format("    INSPECTOR_ID      = [%s]", rs.getString("INSPECTOR_ID"     )));
						}
						
						if (Flag.TRUE) {
							INSPCT_HIST_ReadBean bean = new INSPCT_HIST_ReadBean();

							bean.setLineCode       (rs.getString("LINE_CODE"        ));
							bean.setUserGroupCode  (rs.getString("USER_GROUP_CODE"  ));
							bean.setProcessId      (rs.getString("PROCESS_ID"       ));
							bean.setProductId      (rs.getString("PRODUCT_ID"       ));
							bean.setProductType    (rs.getString("PRODUCT_TYPE"     ));
							bean.setAreaCode       (rs.getString("AREA_CODE"        ));
							bean.setSubAreaCode    (rs.getString("SUB_AREA_CODE"    ));
							bean.setStepId         (rs.getString("STEP_ID"          ));
							bean.setEqpId          (rs.getString("EQP_ID"           ));
							bean.setGlassId        (rs.getString("GLASS_ID"         ));
							bean.setCellId         (rs.getString("CELL_ID"          ));
							bean.setInspectDt      (rs.getString("INSPECT_DT"       ));
							bean.setCellLocId      (rs.getString("CELL_LOC_ID"      ));
							bean.setDefectGroupCode(rs.getString("DEFECT_GROUP_CODE"));
							bean.setDecisionCode   (rs.getString("DECISION_CODE"    ));
							bean.setInspectorId    (rs.getString("INSPECTOR_ID"     ));
							
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
				Logger.getInstance(INSPCT_HIST_Main.jobId, INSPCT_HIST_Main.dsName + ".log");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		if (Flag.TRUE) {
			Parameter.getInstance(INSPCT_HIST_Main.jobId);
			INSPCT_HIST_OracleReader reader = new INSPCT_HIST_OracleReader();
			
			for (INSPCT_HIST_ReadBean bean : reader.getList(true)) {
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
