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

package kiea.proj.sdc.anal.macro.sample.a11.mod.io;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import kiea.kr.co.tain.base.Flag;
import kiea.proj.sdc.anal.common.Connection;
import kiea.proj.sdc.anal.common.Parameter;
import kiea.proj.sdc.anal.macro.impl.io.AbstractOracleReader;
import kiea.proj.sdc.anal.macro.sample.a11.mod.bean.MOD_EQP_SMMRY_ReadBean;
import kiea.proj.sdc.anal.util.StrUtil;
import oracle.jdbc.OracleConnection;

/**
 * @author KangSeok
 * @date 2014. 8. 8.
 * @file_name MOD_EQP_SMMRY_OracleReader.java
 *
 */
public class MOD_EQP_SMMRY_OracleReader extends AbstractOracleReader
{
	private List<MOD_EQP_SMMRY_ReadBean> list = new ArrayList<MOD_EQP_SMMRY_ReadBean>();
	
	/**
	 * Sample02OracleReader
	 */
	public MOD_EQP_SMMRY_OracleReader()
	{
		if (Flag.TRUE) {
		}
	}

	/**
	 * 
	 * getLineMOD
	 *
	 * @return
	 */
	private String getLineMOD()
	{
		String query = null;
		
		query = String.format("" +
				"/*********************/                                           \n" +
				"/*** MOD EQP SMMRY ***/                                           \n" +
				"/*********************/                                           \n" +
				//"SELECT /*+INDEX(T1 H_INSPDEFECT_IDX01)*/                          \n" +  // TODO : 2014.08.26 : 속도느림, 아래수정
				"SELECT /*+ PARALLEL(2) */                                         \n" +
				"      T1.SITEID                       AS LINE_CODE                \n" +
				"    , T2.PRODGRPNAME                  AS USER_GROUP_CODE          \n" +
				"    , T2.PROCID                       AS PROCESS_ID               \n" +
				"    , T1.PRODID                       AS PRODUCT_ID               \n" +
				"    , T1.PRODTYPE                     AS PRODUCT_TYPE             \n" +
				"    , CASE                                                        \n" +
				"        WHEN INSPSTEPID LIKE 'T%%' THEN 'TFT'                     \n" +
				"        WHEN INSPSTEPID LIKE 'F%%' THEN 'CF'                      \n" +
				"        WHEN INSPSTEPID LIKE 'L%%' THEN 'LC'                      \n" +
				"        ELSE 'MOD'                                                \n" +
				"        END                           AS AREA_CODE                \n" +
				"    , T1.INSPSTEPTYPE                 AS SUB_AREA_CODE            \n" +
				"    , T1.INSPSTEPID                   AS STEP_ID                  \n" +
				"    , T1.INSPEQPID                    AS EQP_ID                   \n" +
				"    , T1.GLASSID                      AS GLASS_ID                 \n" +
				"    , T1.CELLID                       AS CELL_ID                  \n" +
				"    , REPLACE (CELLID, GLASSID, '')   AS CELL_LOC_ID              \n" +
				"    , T1.DEFECTCD                     AS DEFECT_GROUP_CODE        \n" +
				"    , T1.DECGRADECD                   AS DECISION_CODE            \n" +
				"    , T1.WORKERID                     AS INSPECTOR_ID             \n" +
				"    , T1.INSPDATE                     AS INSPECT_DT               \n" +
				"    , T1.INSPHOUR                     AS INSPECT_HOUR             \n" +
				"    , TO_CHAR(T1.INSPDATE, 'YYYYMMDD HH24')    AS INSPECT_HOUR2   \n" +
				"FROM                                                              \n" +
				"    yms_dm.H_INSPDEFECT T1                                        \n" +
				"    , yms_dm.D_PRODUCT  T2                                        \n" +
				"WHERE 1=1                                                         \n" +
				//"    AND ROWNUM <= 100                                             \n" +
				"    AND T1.SITEID    = %s                                         \n" +  // LINE
				"    AND T1.INSPHOUR >= %s                                         \n" +  // FROM_ETL_HOUR
				"    AND T1.INSPHOUR <= %s                                         \n" +  // TO_ETL_HOUR
				"    AND T2.SITEID = T1.SITEID                                     \n" +
				"    AND T2.PRODID = T1.PRODID                                     \n" +
				"    AND T1.PRODTYPE IN ( %s )                                     \n" +  // PRODUCT_TYPE
				"    AND T1.INSPSTEPTYPE IN ( %s )                                 \n" +  // SUB_AREA_CODE
				"    AND T2.PRODGRPNAME  IN ( %s )                                 \n" +  // USER_GROUP_CODE
				""
				, StrUtil.quote(Parameter.getInstance().getStrLine())
				, StrUtil.quote(Parameter.getInstance().getStrFromDateTime())
				, StrUtil.quote(Parameter.getInstance().getStrToDateTime())
				, StrUtil.quote(Parameter.getInstance().getStrProductType())
				, StrUtil.quote(Parameter.getInstance().getStrSubAreaCode())
				, StrUtil.quote(Parameter.getInstance().getStrUserGroupCode())
				);

		return query;
	}

	/**
	 * getList
	 */
	public List<MOD_EQP_SMMRY_ReadBean> getList()
	{
		if (Flag.TRUE) {
			try {
				String query = null;
				
				if ("MOD".equals(Parameter.getInstance().getStrAreaCode())) {
					query = getLineMOD();
				} else {
					return list;
				}
				
				if (!Flag.TRUE) System.out.println("[" + query + "]");
				
				OracleConnection conn = Connection.getOracleConnection();

				if (Flag.TRUE) {
					Statement stmt = conn.createStatement();
					ResultSet rs = stmt.executeQuery(query);
					for (int i=0; i < 100000 && rs.next(); i++) {                     // TODO : 2014.08.26 : 최대건수 처리
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
							System.out.println(String.format("    CELL_LOC_ID       = [%s]", rs.getString("CELL_LOC_ID"      )));
							System.out.println(String.format("    DEFECT_GROUP_CODE = [%s]", rs.getString("DEFECT_GROUP_CODE")));
							System.out.println(String.format("    DECISION_CODE     = [%s]", rs.getString("DECISION_CODE"    )));
							System.out.println(String.format("    INSPECTOR_ID      = [%s]", rs.getString("INSPECTOR_ID"     )));
							System.out.println(String.format("    INSPECT_DT        = [%s]", rs.getString("INSPECT_DT"       )));
							System.out.println(String.format("    INSPECT_HOUR      = [%s]", rs.getString("INSPECT_HOUR"     )));
							System.out.println(String.format("    INSPECT_HOUR2     = [%s]", rs.getString("INSPECT_HOUR2"    )));
						}
						
						if (Flag.TRUE) {
							MOD_EQP_SMMRY_ReadBean bean = new MOD_EQP_SMMRY_ReadBean();

							bean.setLineCode       (rs.getString("LINE_CODE"        ));
							bean.setUserGroupCode  (rs.getString("USER_GROUP_CODE"  ));
							bean.setProcessId      (rs.getString("PROCESS_ID"       ));
							bean.setProductId      (rs.getString("PRODUCT_ID"       ));
							bean.setProductType    (rs.getString("PRODUCT_TYPE"     ));
							bean.setAreaAode       (rs.getString("AREA_CODE"        ));
							bean.setSubAreaCode    (rs.getString("SUB_AREA_CODE"    ));
							bean.setStepId         (rs.getString("STEP_ID"          ));
							bean.setEqpId          (rs.getString("EQP_ID"           ));
							bean.setGlassId        (rs.getString("GLASS_ID"         ));
							bean.setCellId         (rs.getString("CELL_ID"          ));
							bean.setCellLocId      (rs.getString("CELL_LOC_ID"      ));
							bean.setDefectGroupCode(rs.getString("DEFECT_GROUP_CODE"));
							bean.setDecisionCode   (rs.getString("DECISION_CODE"    ));
							bean.setInspectorId    (rs.getString("INSPECTOR_ID"     ));
							bean.setInspectDt      (rs.getString("INSPECT_DT"       ));
							bean.setInspectHour    (rs.getString("INSPECT_HOUR"     ));
							bean.setInspectHour2   (rs.getString("INSPECT_HOUR2"    ));
							
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
			Parameter.getInstance(7);
			MOD_EQP_SMMRY_OracleReader reader = new MOD_EQP_SMMRY_OracleReader();
			reader.getList();
		}
	}
	
	public static void main(String[] args)
	{
		if (Flag.TRUE) test01();
	}
}
