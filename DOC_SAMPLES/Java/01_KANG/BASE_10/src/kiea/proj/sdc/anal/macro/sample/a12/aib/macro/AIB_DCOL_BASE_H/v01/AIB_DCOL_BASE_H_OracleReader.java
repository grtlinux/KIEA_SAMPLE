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

package kiea.proj.sdc.anal.macro.sample.a12.aib.macro.AIB_DCOL_BASE_H.v01;

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
 * @file_name AIB_DCOL_BASE_H_OracleReader.java
 *
 */
public class AIB_DCOL_BASE_H_OracleReader extends AbstractOracleReader
{
	private List<AIB_DCOL_BASE_H_ReadBean> list = new ArrayList<AIB_DCOL_BASE_H_ReadBean>();
	
	/**
	 * Sample02OracleReader
	 */
	public AIB_DCOL_BASE_H_OracleReader()
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
				"/* DCOL_BASE_H : 5,6 LINE */                                                 \n" +
				"SELECT /*+parallel 2*/                                                       \n" +
				"    t1.siteid      line_code,                                                \n" +
				"    t3.compid      glass_id,                                                 \n" +
				"    t7.procid      process_id,                                               \n" +
				"    t7.prodid      product_id,                                               \n" +
				"    t1.stepseq     step_id,                                                  \n" +
				"    t6.eqpid       eqp_id,                                                   \n" +
				"    ''             eqpunit_id,                                               \n" +
				"    /* impteqpunitid eqpunit_id, 제공불가 */                                 \n" +
				"    t4.itemid      item_id,                                                  \n" +
				"    t4.subitemid   subitem_id,                                               \n" +
				"    t5.datavalue,                                                            \n" +
				"    t2.tkintime    dcoltime                                                  \n" +
				"    /* t2.tkouttime dcoltime */                                              \n" +
				"FROM                                                                         \n" +
				"    mdw_st.st_stepseq_l5eas   t1,                                            \n" +
				"    mdw_st.st_runmaster       t2,                                            \n" +
				"    mdw_st.st_compmaster      t3,                                            \n" +
				"    mdw_st.st_rundataspec     t4,                                            \n" +
				"    mdw_st.st_rundata         t5,                                            \n" +
				"    mdw_st.st_equipment_l5eas t6,                                            \n" +
				"    yms_dm.d_product          t7                                             \n" +
				"WHERE 1 = 1                                                                  \n" +
				"    AND t1.siteid         = t2.siteid                                        \n" +
				"    AND t1.stepseqkey     = t2.stepseqkey                                    \n" +
				"    AND t6.siteid         = t2.siteid                                        \n" +
				"    AND t6.eqpkey         = t2.eqpkey                                        \n" +
				"    AND t7.prodid         = t2.partid                                        \n" +
				"    AND t2.runmasterkey   = t3.runmasterkey                                  \n" +
				"    AND t2.siteid         = t3.siteid                                        \n" +
				"    AND t2.tkouttime      = t3.tkouttime                                     \n" +
				"    AND t3.compmasterkey  = t5.compmasterkey                                 \n" +
				"    AND t3.siteid         = t5.siteid                                        \n" +
				"    AND t3.tkintime       = t5.tkintime                                      \n" +
				"    AND t5.dcspeckey      = t4.dcspeckey                                     \n" +
				"    AND t1.siteid         = %s||'FAB'                                        \n" +  // LINE
				"    /* AND t2.tkouttime = TO_DATE ('20120507 001421','YYYYMMDD HH24MISS') */ \n" +
				"    /*                                                                       \n" +
				"    AND t2.tkouttime >= TO_DATE (%s,'YYYYMMDDHH24MISS')                      \n" +  // FROM_ETL_DT
				"    AND t2.tkouttime <= TO_DATE (%s,'YYYYMMDDHH24MISS')                      \n" +  // TO_ETL_DT
				"    */                                                                       \n" +
				"    AND t2.tkouttime      >= TO_DATE ('20120501000000','YYYYMMDDHH24MISS')   \n" +
				"    AND t2.tkouttime      <= TO_DATE ('20120501010000','YYYYMMDDHH24MISS')   \n" +
				"    AND t7.prodid IN (                                                       \n" +
				"        SELECT prodid                                                        \n" +
				"        FROM yms_dm.d_product                                                \n" +
				"        WHERE prodgrpname = %s                                               \n" +  // USER_GRP_CODE
				"    )                                                                        \n" +
				""
				, StrUtil.quote(Parameter.getInstance().getStrLine())
				, StrUtil.quote(Parameter.getInstance().getStrFromDateTime())
				, StrUtil.quote(Parameter.getInstance().getStrToDateTime())
				, StrUtil.quote(Parameter.getInstance().getStrUserGroupCode())
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
				"/* DCOL_BASE_H : 7,8 LINE */                                                \n" +
				"SELECT /*+PARALLEL(2) USE_HASH(T1 T2) FULL(T1) ORDERED*/                    \n" +
				"    T1.SITEID      LINE_CODE,                                               \n" +
				"    T1.GLASSID     GLASS_ID,                                                \n" +
				"    T1.PROCESSID   PROCESS_ID,                                              \n" +
				"    T1.PRODUCTID   PRODUCT_ID,                                              \n" +
				"    T1.STEPID      STEP_ID,                                                 \n" +
				"    T1.EQPID       EQP_ID,                                                  \n" +
				"    T1.EQPUNITID   EQPUNIT_ID,                                              \n" +
				"    T2.ITEMID      ITEM_ID,                                                 \n" +
				"    T2.SUBITEMID   SUBITEM_ID,                                              \n" +
				"    T2.DATAVALUE,                                                           \n" +
				"    T2.DCOLTIME                                                             \n" +
				"FROM                                                                        \n" +
				"    MDW_ST.ST_EAS_COMP_MASTER   T1,                                         \n" +
				"    MDW_ST.ST_EAS_RUN_DATA      T2                                          \n" +
				"WHERE 1=1                                                                   \n" +
				"    AND T1.SITEID        = 'L'|| %s ||'FAB'                                 \n" +  // LINE
				"    AND T1.SITEID        = T2.SITEID                                        \n" +
				"    AND T1.DCOLTIME      = T2.DCOLTIME                                      \n" +
				"    AND T1.COMPMASTERKEY = T2.COMPMASTERKEY                                 \n" +
				"    /*                                                                      \n" +
				"    AND T1.DCOLTIME >= to_date(%s, 'YYYYMMDDHH24MISS')                      \n" +  // WIP_FROM_DATETIME
				"    AND T1.DCOLTIME <  to_date(%s  , 'YYYYMMDDHH24MISS')                    \n" +  // WIP_TO_DATETIME
				"    */                                                                      \n" +
				"    AND T1.DCOLTIME >= to_date('20120501000000', 'YYYYMMDDHH24MISS')        \n" +
				"    AND T1.DCOLTIME <  to_date('20120501010000', 'YYYYMMDDHH24MISS')        \n" +
				"    AND T1.PRODUCTID IN (                                                   \n" +
				"        SELECT PRODID                                                       \n" +
				"        FROM YMS_DM.D_PRODUCT                                               \n" +
				"        WHERE PRODGRPNAME = %s                                              \n" +  // USER_GROUP_CODE
				"    )                                                                       \n" +
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
	public List<AIB_DCOL_BASE_H_ReadBean> getList()
	{
		return getList(false);
	}
	
	public List<AIB_DCOL_BASE_H_ReadBean> getList(boolean flgRead)
	{
		if (Flag.TRUE) {
			if (!flgRead) {
				/*
				 * if flgRead = true, 초기에 읽지않는다.
				 */
				list = new ArrayList<AIB_DCOL_BASE_H_ReadBean>();
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
				
				OracleConnection conn = Connection.getOracleConnection();

				if (Flag.TRUE) {
					Statement stmt = conn.createStatement();
					ResultSet rs = stmt.executeQuery(query);
					for (int i=0; i < 1000000 && rs.next(); i++) {
						if (!Flag.TRUE) {
							System.out.println(String.format("%3d) --------------------------------------", i));
							System.out.println(String.format("    LINE_CODE  = [%s]", rs.getString("LINE_CODE" )));
							System.out.println(String.format("    GLASS_ID   = [%s]", rs.getString("GLASS_ID"  )));
							System.out.println(String.format("    PROCESS_ID = [%s]", rs.getString("PROCESS_ID")));
							System.out.println(String.format("    PRODUCT_ID = [%s]", rs.getString("PRODUCT_ID")));
							System.out.println(String.format("    STEP_ID    = [%s]", rs.getString("STEP_ID"   )));
							System.out.println(String.format("    EQP_ID     = [%s]", rs.getString("EQP_ID"    )));
							System.out.println(String.format("    EQPUNIT_ID = [%s]", rs.getString("EQPUNIT_ID")));
							System.out.println(String.format("    ITEM_ID    = [%s]", rs.getString("ITEM_ID"   )));
							System.out.println(String.format("    SUBITEM_ID = [%s]", rs.getString("SUBITEM_ID")));
							System.out.println(String.format("    DATAVALUE  = [%s]", rs.getString("DATAVALUE" )));
							System.out.println(String.format("    DCOLTIME   = [%s]", rs.getString("DCOLTIME"  )));
						}
						
						if (Flag.TRUE) {
							AIB_DCOL_BASE_H_ReadBean bean = new AIB_DCOL_BASE_H_ReadBean();

							bean.setLineCode (rs.getString("LINE_CODE" ));
							bean.setGlassId  (rs.getString("GLASS_ID"  ));
							bean.setProcessId(rs.getString("PROCESS_ID"));
							bean.setProductId(rs.getString("PRODUCT_ID"));
							bean.setStepId   (rs.getString("STEP_ID"   ));
							bean.setEqpId    (rs.getString("EQP_ID"    ));
							bean.setEqpUnitId(rs.getString("EQPUNIT_ID"));
							bean.setItemId   (rs.getString("ITEM_ID"   ));
							bean.setSubItemId(rs.getString("SUBITEM_ID"));
							bean.setDataValue(rs.getString("DATAVALUE" ));
							bean.setDcolTime (rs.getString("DCOLTIME"  ));
							
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
			AIB_DCOL_BASE_H_OracleReader reader = new AIB_DCOL_BASE_H_OracleReader();
			
			for (AIB_DCOL_BASE_H_ReadBean bean : reader.getList(true)) {
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
