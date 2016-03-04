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

package kiea.proj.sdc.anal.macro.sample.a12.aib.macro.AIB_DCOL_WITH_INSP.v01;

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
 * @file_name AIB_DCOL_WITH_INSP_OracleReader.java
 *
 */
public class AIB_DCOL_WITH_INSP_OracleReader extends AbstractOracleReader
{
	private List<AIB_DCOL_WITH_INSP_ReadBean> list = new ArrayList<AIB_DCOL_WITH_INSP_ReadBean>();
	
	/**
	 * Sample02OracleReader
	 */
	public AIB_DCOL_WITH_INSP_OracleReader()
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
				"/* DCOL_WITH_INSP */                                                    \n" +
				"SELECT /*+ORDERED INDEX(T1 H_INSPECT_HIST_IDX1)*/                       \n" +
				"    'L'||t1.siteid||'FAB'     AS LINE_CODE,                             \n" +
				"    T2.PRODGRPNAME            AS USER_GROUP_CODE,                       \n" +
				"    T2.PROCID                 AS PROCESS_ID,                            \n" +
				"    T1.PRODID                 AS PRODUCT_ID,                            \n" +
				"    T1.PRODTYPE               AS PRODUCT_TYPE,                          \n" +
				"    T1.INSPSTEPTYPE           AS SUB_AREA_CODE,                         \n" +
				"    T1.GLASSID                AS GLASS_ID,                              \n" +
				"    T1.CELLID                 AS CELL_ID,                               \n" +
				"    CASE WHEN t1.inspsteptype = 'EDS' THEN '-'                          \n" +
				"        ELSE NVL(t3.cfglassid,'-') END cfglassid,                       \n" +
				"    T2.CELLQTY                AS BASE_CELL_NUM,                         \n" +
				"    T1.INSPTIME               AS INSPECT_DT,                            \n" +
				"    REPLACE(CELLID,GLASSID,'') AS CELL_LOC_ID,                          \n" +
				"    T1.DEFECTCD               AS DEFECT_GROUP_CODE,                     \n" +
				"    SUM(CASE WHEN DECGRADECD IN (&G_DECISION_CODE) THEN 1 ELSE 0 END)   \n" +
				"        OVER(PARTITION BY GLASSID) DEFECT_CELL_NUM,                     \n" +
				"    T1.DECGRADECD             AS DECISION_CODE,                         \n" +
				"    T1.WORKERID               AS INSPECTOR_ID,                          \n" +
				"    T1.BINGRADECD ,                                                     \n" +
				"    T1.MATCHLOTTYPE                                                     \n" +
				"FROM                                                                    \n" +
				"    yms_dm.D_PRODUCT          T2,                                       \n" +
				"    yms_dm.h_inspdefect       t1,                                       \n" +
				"    mdw_dw.dw_mf_glass_match  t3                                        \n" +
				"WHERE 1 = 1                                                             \n" +
				"    AND T1.SITEID       =  %s                                           \n" +  // LINE
				"    AND T1.INSPHOUR     >= %s                                           \n" +  // FROM_ETL_HOUR
				"    AND T1.INSPHOUR     <= %s                                           \n" +  // TO_ETL_HOUR
				"    AND T2.PRODGRPNAME  IN (%s)                                         \n" +  // USER_GROUP_CODE
				"    AND T1.PRODTYPE     IN (%s)                                         \n" +  // PRODUCT_TYPE
				"    AND T1.INSPSTEPTYPE IN (%s)                                         \n" +  // SUB_AREA_CODE
				"    AND T1.DEFECTCD     IN (%s)                                         \n" +  // DEFECT_GROUP_CODE
				"    AND T1.DECGRADECD   IN (%s)                                         \n" +  // DECISION_CODE
				"    AND T1.PRODID       =  T2.PRODID                                    \n" +
				"    AND t1.glassid      =  t3.tftglassid(+)                             \n" +
				""
				, StrUtil.quote(Parameter.getInstance().getStrLine())
				, StrUtil.quote(Parameter.getInstance().getStrFromDateTime())
				, StrUtil.quote(Parameter.getInstance().getStrToDateTime())
				, StrUtil.quote(Parameter.getInstance().getStrUserGroupCode())
				, StrUtil.quote(Parameter.getInstance().getStrProductType())
				, StrUtil.quote(Parameter.getInstance().getStrSubAreaCode())
				, StrUtil.quote(Parameter.getInstance().getStrDefectGroupCode())
				, StrUtil.quote(Parameter.getInstance().getStrDecisionCode())
				);

		return query;
	}

	/**
	 * getList
	 */
	@Override
	public List<AIB_DCOL_WITH_INSP_ReadBean> getList()
	{
		return getList(false);
	}
	
	public List<AIB_DCOL_WITH_INSP_ReadBean> getList(boolean flgRead)
	{
		if (Flag.TRUE) {
			if (!flgRead) {
				/*
				 * if flgRead = true, 초기에 읽지않는다.
				 */
				list = new ArrayList<AIB_DCOL_WITH_INSP_ReadBean>();
				return list;
			}
			
			try {
				String query = null;
				
				query = getQuery();
				
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
							System.out.println(String.format("    SUB_AREA_CODE     = [%s]", rs.getString("SUB_AREA_CODE"    )));
							System.out.println(String.format("    GLASS_ID          = [%s]", rs.getString("GLASS_ID"         )));
							System.out.println(String.format("    CELL_ID           = [%s]", rs.getString("CELL_ID"          )));
							System.out.println(String.format("    CFGLASSID         = [%s]", rs.getString("CFGLASSID"        )));
							System.out.println(String.format("    BASE_CELL_NUM     = [%s]", rs.getString("BASE_CELL_NUM"    )));
							System.out.println(String.format("    INSPECT_DT        = [%s]", rs.getString("INSPECT_DT"       )));
							System.out.println(String.format("    CELL_LOC_ID       = [%s]", rs.getString("CELL_LOC_ID"      )));
							System.out.println(String.format("    DEFECT_GROUP_CODE = [%s]", rs.getString("DEFECT_GROUP_CODE")));
							System.out.println(String.format("    DEFECT_CELL_NUM   = [%s]", rs.getString("DEFECT_CELL_NUM"  )));
							System.out.println(String.format("    DECISION_CODE     = [%s]", rs.getString("DECISION_CODE"    )));
							System.out.println(String.format("    INSPECTOR_ID      = [%s]", rs.getString("INSPECTOR_ID"     )));
							System.out.println(String.format("    BINGRADECD        = [%s]", rs.getString("BINGRADECD"       )));
							System.out.println(String.format("    MATCHLOTTYPE      = [%s]", rs.getString("MATCHLOTTYPE"     )));
						}
						
						if (Flag.TRUE) {
							AIB_DCOL_WITH_INSP_ReadBean bean = new AIB_DCOL_WITH_INSP_ReadBean();

							bean.setLineCode       (rs.getString("LINE_CODE"        ));
							bean.setUserGroupCode  (rs.getString("USER_GROUP_CODE"  ));
							bean.setProcessId      (rs.getString("PROCESS_ID"       ));
							bean.setProductId      (rs.getString("PRODUCT_ID"       ));
							bean.setProductType    (rs.getString("PRODUCT_TYPE"     ));
							bean.setSubAreaCode    (rs.getString("SUB_AREA_CODE"    ));
							bean.setGlassId        (rs.getString("GLASS_ID"         ));
							bean.setCellId         (rs.getString("CELL_ID"          ));
							bean.setCfGlassId      (rs.getString("CFGLASSID"        ));
							bean.setBaseCellNum    (rs.getString("BASE_CELL_NUM"    ));
							bean.setInspectDt      (rs.getString("INSPECT_DT"       ));
							bean.setCellLocId      (rs.getString("CELL_LOC_ID"      ));
							bean.setDefectGroupCode(rs.getString("DEFECT_GROUP_CODE"));
							bean.setDefectCellNum  (rs.getString("DEFECT_CELL_NUM"  ));
							bean.setDecisionCode   (rs.getString("DECISION_CODE"    ));
							bean.setInspectorId    (rs.getString("INSPECTOR_ID"     ));
							bean.setBinGradeCd     (rs.getString("BINGRADECD"       ));
							bean.setMatchLotType   (rs.getString("MATCHLOTTYPE"     ));
							
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
			AIB_DCOL_WITH_INSP_OracleReader reader = new AIB_DCOL_WITH_INSP_OracleReader();
			
			for (AIB_DCOL_WITH_INSP_ReadBean bean : reader.getList(true)) {
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
