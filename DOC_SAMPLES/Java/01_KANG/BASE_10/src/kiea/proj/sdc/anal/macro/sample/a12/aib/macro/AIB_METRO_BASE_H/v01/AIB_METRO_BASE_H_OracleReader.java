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

package kiea.proj.sdc.anal.macro.sample.a12.aib.macro.AIB_METRO_BASE_H.v01;

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
 * @file_name AIB_METRO_BASE_H_OracleReader.java
 *
 */
public class AIB_METRO_BASE_H_OracleReader extends AbstractOracleReader
{
	private List<AIB_METRO_BASE_H_ReadBean> list = new ArrayList<AIB_METRO_BASE_H_ReadBean>();
	
	/**
	 * Sample02OracleReader
	 */
	public AIB_METRO_BASE_H_OracleReader()
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
				"/* METRO_BASE_H */                                                         \n" +
				"SELECT /*+ PARALLEL(2)*/                                                   \n" +
				"    T1.SITEID        LINE_CODE,                                            \n" +
				"    T1.PROCID        PROCESS_ID,                                           \n" +
				"    T1.PRODID        PRODUCT_ID,                                           \n" +
				"    CASE                                                                   \n" +
				"        WHEN T1.ORGSTEPID LIKE 'T%%' THEN 'TFT'                            \n" +
				"        WHEN T1.ORGSTEPID LIKE 'F%%' THEN 'CF'                             \n" +
				"        WHEN T1.ORGSTEPID LIKE 'L%%' THEN 'LC'                             \n" +
				"        ELSE 'MOD' END AS AREA_CODE,                                       \n" +
				"    T1.ORGSTEPID     STEP_ID,                                              \n" +
				"    T1.MEASEQPUNITID EQP_ID,                                               \n" +
				"    T1.GLASSID       GLASS_ID,                                             \n" +
				"    T1.ITEMID        ITEM_ID,                                              \n" +
				"    T1.SUBITEMID     SUBITEM_ID,                                           \n" +
				"    T1.DATAVALUE     DATAVALUE,                                            \n" +
				"    T1.DCOLDATE      DCOLTIME,                                             \n" +
				"    T1.USL           SPEC_UCL,                                             \n" +
				"    T1.LSL           SPEC_LCL,                                             \n" +
				"    T1.TARGET        SPEC_TGT                                              \n" +
				"FROM                                                                       \n" +
				"    YMS_DM.D_PRODUCT            T2,                                        \n" +
				"    yms_dm.h_meas_rundata_cell  T1                                         \n" +
				"WHERE 1=1                                                                  \n" +
				"    /*AND T1.SITEID=*/                                                     \n" +
				"    AND T1.SITEID      =  %s                                               \n" +  // LINE
				"    AND T1.DCOLDATE    >= TO_DATE(%s, 'YYYYMMDDHH24MISS')                  \n" +  // WIP_FROM_DATETIME
				"    AND T1.DCOLDATE    <= TO_DATE(%s, 'YYYYMMDDHH24MISS')                  \n" +  // WIP_TO_DATETIME
				"    AND T1.DCOLDATE >= TO_DATE('20140501000000', 'YYYYMMDDHH24MISS')       \n" +
				"    AND T1.DCOLDATE <  TO_DATE('20140501120000', 'YYYYMMDDHH24MISS')       \n" +
				"    AND T1.PRODID      =  T2.PRODID                                        \n" +
				"    AND T2.PRODGRPNAME IN ( %s )                                           \n" +  // USER_GROUP_CODE
				""
				, StrUtil.quote(Parameter.getInstance().getStrLine())
				, StrUtil.quote(Parameter.getInstance().getStrFromDateTime())
				, StrUtil.quote(Parameter.getInstance().getStrToDateTime())
				, StrUtil.quote(Parameter.getInstance().getStrUserGroupCode())
				);

		return query;
	}

	/**
	 * getList
	 */
	@Override
	public List<AIB_METRO_BASE_H_ReadBean> getList()
	{
		return getList(false);
	}
	
	public List<AIB_METRO_BASE_H_ReadBean> getList(boolean flgRead)
	{
		if (Flag.TRUE) {
			if (!flgRead) {
				/*
				 * if flgRead = true, 초기에 읽지않는다.
				 */
				list = new ArrayList<AIB_METRO_BASE_H_ReadBean>();
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
							System.out.println(String.format("    LINE_CODE  = [%s]", rs.getString("LINE_CODE" )));
							System.out.println(String.format("    PROCESS_ID = [%s]", rs.getString("PROCESS_ID")));
							System.out.println(String.format("    PRODUCT_ID = [%s]", rs.getString("PRODUCT_ID")));
							System.out.println(String.format("    AREA_CODE  = [%s]", rs.getString("AREA_CODE" )));
							System.out.println(String.format("    STEP_ID    = [%s]", rs.getString("STEP_ID"   )));
							System.out.println(String.format("    EQP_ID     = [%s]", rs.getString("EQP_ID"    )));
							System.out.println(String.format("    GLASS_ID   = [%s]", rs.getString("GLASS_ID"  )));
							System.out.println(String.format("    ITEM_ID    = [%s]", rs.getString("ITEM_ID"   )));
							System.out.println(String.format("    SUBITEM_ID = [%s]", rs.getString("SUBITEM_ID")));
							System.out.println(String.format("    DATAVALUE  = [%s]", rs.getString("DATAVALUE" )));
							System.out.println(String.format("    DCOLTIME   = [%s]", rs.getString("DCOLTIME"  )));
							System.out.println(String.format("    SPEC_UCL   = [%s]", rs.getString("SPEC_UCL"  )));
							System.out.println(String.format("    SPEC_LCL   = [%s]", rs.getString("SPEC_LCL"  )));
							System.out.println(String.format("    SPEC_TGT   = [%s]", rs.getString("SPEC_TGT"  )));
						}
						
						if (Flag.TRUE) {
							AIB_METRO_BASE_H_ReadBean bean = new AIB_METRO_BASE_H_ReadBean();

							bean.setLineCode (rs.getString("LINE_CODE" ));
							bean.setProcessId(rs.getString("PROCESS_ID"));
							bean.setProductId(rs.getString("PRODUCT_ID"));
							bean.setAreaCode (rs.getString("AREA_CODE" ));
							bean.setStepId   (rs.getString("STEP_ID"   ));
							bean.setEqpId    (rs.getString("EQP_ID"    ));
							bean.setGlassId  (rs.getString("GLASS_ID"  ));
							bean.setItemId   (rs.getString("ITEM_ID"   ));
							bean.setSubItemId(rs.getString("SUBITEM_ID"));
							bean.setDataValue(rs.getString("DATAVALUE" ));
							bean.setDcolTime (rs.getString("DCOLTIME"  ));
							bean.setSpecUcl  (rs.getString("SPEC_UCL"  ));
							bean.setSpecLcl  (rs.getString("SPEC_LCL"  ));
							bean.setSpecTgt  (rs.getString("SPEC_TGT"  ));
							
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
			Parameter.getInstance(5);
			AIB_METRO_BASE_H_OracleReader reader = new AIB_METRO_BASE_H_OracleReader();
			
			for (AIB_METRO_BASE_H_ReadBean bean : reader.getList(true)) {
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
