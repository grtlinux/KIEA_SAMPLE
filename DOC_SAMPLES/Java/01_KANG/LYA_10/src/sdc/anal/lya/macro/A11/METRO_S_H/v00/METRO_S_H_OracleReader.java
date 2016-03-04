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

package sdc.anal.lya.macro.A11.METRO_S_H.v00;

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
 * @file_name AIB_METRO_S_H_OracleReader.java
 *
 */
public class METRO_S_H_OracleReader extends AbstractOracleReader
{
	private List<METRO_S_H_ReadBean> list = new ArrayList<METRO_S_H_ReadBean>();
	
	/**
	 * Sample02OracleReader
	 */
	public METRO_S_H_OracleReader()
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
				"SELECT                                                                 \n" +
				"    T2.SITEID      LINE_CODE ,                                         \n" +
				"    T1.PRODGRPNAME USER_GROUP_CODE ,                                   \n" +
				"    T1.PROCID      PROCESS_ID ,                                        \n" +
				"    T2.PRODUCTID   PRODUCT_ID ,                                        \n" +
				"    T2.EQPUNITID   EQP_ID ,                                            \n" +
				"    T2.PRODTYPE    PRODUCT_TYPE ,                                      \n" +
				"    T2.STEPID      STEP_ID ,                                           \n" +
				"    '*'            AREA_CODE ,                                         \n" +
				"    '*'            SUB_AREA_CODE ,                                     \n" +
				"    T2.GLASSID     GLASS_ID ,                                          \n" +
				"    '*'            CELL_ID ,                                           \n" +
				"    T2.ITEMID      ITEM_ID ,                                           \n" +
				"    'AVG'          SUBITEM_ID ,                                        \n" +
				"    T2.AVG_VAL     MEASURE_VALUE ,                                     \n" +
				"    T2.AUSL        SPEC_UCL,                                           \n" +
				"    T2.TARGET      SPEC_TGT,                                           \n" +
				"    T2.ALSL        SPEC_LCL,                                           \n" +
				"    T2.INSPDATE    METRO_END_DT,                                       \n" +
				"    '*' DEFECT_GROUP_CODE,                                             \n" +
				"    '*' DECISION_CODE                                                  \n" +
				"FROM                                                                   \n" +
				"    YMS_DM.D_PRODUCT T1                                                \n" +
				"    , YMS_LYA.H_MEAS_SUM  T2                                           \n" +
				"WHERE 1=1                                                              \n" +
				"    AND 'L'||T1.SITEID||'FAB' = T2.SITEID                              \n" +
				"    AND T1.PRODID = T2.PRODUCTID                                       \n" +
				"    AND T1.PRODGRPNAME = %s                                            \n" +  // USER_GROUP_CODE
				"    AND T1.SITEID      = %s                                            \n" +  // LINE
				"    AND INSPDATE       >= TO_DATE(%s,'YYYYMMDDHH24MISS')-1/24*72       \n" +  // FROM_ETL_DT
				"    AND INSPDATE       <= TO_DATE(%s,'YYYYMMDDHH24MISS')+1/24*72       \n" +  // TO_ETL_DT
				""
				, StrUtil.quote(Parameter.getInstance().getStrUserGroupCode())
				, StrUtil.quote(Parameter.getInstance().getStrLine())
				, StrUtil.quote(Parameter.getInstance().getStrFromDateTime())
				, StrUtil.quote(Parameter.getInstance().getStrToDateTime())
				);

		return query;
	}

	/**
	 * getList
	 */
	@Override
	public List<METRO_S_H_ReadBean> getList()
	{
		return getList(false);
	}
	
	public List<METRO_S_H_ReadBean> getList(boolean flgRead)
	{
		if (Flag.TRUE) {
			if (!flgRead) {
				/*
				 * if flgRead = true, 초기에 읽지않는다.
				 */
				list = new ArrayList<METRO_S_H_ReadBean>();
				return list;
			}
			
			try {
				String query = null;
				
				query = getQuery();
				
				if (Flag.TRUE) System.out.println("[" + query + "]");
				
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
							System.out.println(String.format("    EQP_ID            = [%s]", rs.getString("EQP_ID"           )));
							System.out.println(String.format("    PRODUCT_TYPE      = [%s]", rs.getString("PRODUCT_TYPE"     )));
							System.out.println(String.format("    STEP_ID           = [%s]", rs.getString("STEP_ID"          )));
							System.out.println(String.format("    AREA_CODE         = [%s]", rs.getString("AREA_CODE"        )));
							System.out.println(String.format("    SUB_AREA_CODE     = [%s]", rs.getString("SUB_AREA_CODE"    )));
							System.out.println(String.format("    GLASS_ID          = [%s]", rs.getString("GLASS_ID"         )));
							System.out.println(String.format("    CELL_ID           = [%s]", rs.getString("CELL_ID"          )));
							System.out.println(String.format("    ITEM_ID           = [%s]", rs.getString("ITEM_ID"          )));
							System.out.println(String.format("    SUBITEM_ID        = [%s]", rs.getString("SUBITEM_ID"       )));
							System.out.println(String.format("    MEASURE_VALUE     = [%s]", rs.getString("MEASURE_VALUE"    )));
							System.out.println(String.format("    SPEC_UCL          = [%s]", rs.getString("SPEC_UCL"         )));
							System.out.println(String.format("    SPEC_TGT          = [%s]", rs.getString("SPEC_TGT"         )));
							System.out.println(String.format("    SPEC_LCL          = [%s]", rs.getString("SPEC_LCL"         )));
							System.out.println(String.format("    METRO_END_DT      = [%s]", rs.getString("METRO_END_DT"     )));
							System.out.println(String.format("    DEFECT_GROUP_CODE = [%s]", rs.getString("DEFECT_GROUP_CODE")));
							System.out.println(String.format("    DECISION_CODE     = [%s]", rs.getString("DECISION_CODE"    )));
						}
						
						if (Flag.TRUE) {
							METRO_S_H_ReadBean bean = new METRO_S_H_ReadBean();

							bean.setLineCode       (rs.getString("LINE_CODE"        ));
							bean.setUserGroupCode  (rs.getString("USER_GROUP_CODE"  ));
							bean.setProcessId      (rs.getString("PROCESS_ID"       ));
							bean.setProductId      (rs.getString("PRODUCT_ID"       ));
							bean.setEqpId          (rs.getString("EQP_ID"           ));
							bean.setProductType    (rs.getString("PRODUCT_TYPE"     ));
							bean.setStepId         (rs.getString("STEP_ID"          ));
							bean.setAreaCode       (rs.getString("AREA_CODE"        ));
							bean.setSubAreaCode    (rs.getString("SUB_AREA_CODE"    ));
							bean.setGlassId        (rs.getString("GLASS_ID"         ));
							bean.setCellId         (rs.getString("CELL_ID"          ));
							bean.setItemId         (rs.getString("ITEM_ID"          ));
							bean.setSubItemI       (rs.getString("SUBITEM_ID"       ));
							bean.setMeasureValue   (rs.getString("MEASURE_VALUE"    ));
							bean.setSpecUcl        (rs.getString("SPEC_UCL"         ));
							bean.setSpecTgt        (rs.getString("SPEC_TGT"         ));
							bean.setSpecLcl        (rs.getString("SPEC_LCL"         ));
							bean.setMetroEndDt     (rs.getString("METRO_END_DT"     ));
							bean.setDefectGroupCode(rs.getString("DEFECT_GROUP_CODE"));
							bean.setDecisionCode   (rs.getString("DECISION_CODE"    ));
							
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
				Logger.getInstance(METRO_S_H_Main.jobId, METRO_S_H_Main.dsName + ".log");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		if (Flag.TRUE) {
			Parameter.getInstance(METRO_S_H_Main.jobId);
			METRO_S_H_OracleReader reader = new METRO_S_H_OracleReader();
			
			for (METRO_S_H_ReadBean bean : reader.getList(true)) {
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
