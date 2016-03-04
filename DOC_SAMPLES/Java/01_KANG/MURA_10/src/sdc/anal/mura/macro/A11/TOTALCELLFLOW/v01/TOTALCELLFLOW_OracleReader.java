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

package sdc.anal.mura.macro.A11.TOTALCELLFLOW.v01;

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
 * @file_name TOTALCELLFLOW_OracleReader.java
 *
 */
public class TOTALCELLFLOW_OracleReader extends AbstractOracleReader
{
	private List<TOTALCELLFLOW_ReadBean> list = new ArrayList<TOTALCELLFLOW_ReadBean>();
	
	/**
	 * Sample02OracleReader
	 */
	public TOTALCELLFLOW_OracleReader()
	{
		if (Flag.TRUE) {
		}
	}

	/**
	 * 
	 * getLine56MOD
	 *
	 * @return
	 */
	private String getLine56LC()
	{
		String query = null;
		
		query = String.format("" +
				"/* TOTALCELLFLOW : L5FAB, L6FAB - LC */     \n" +
				"SELECT /*+ FULL (F)  PARALLEL(2) */         \n" +
				"    IMPTSTEPGRPID,                          \n" +
				"    IMPTEQPID,                              \n" +
				"    PROCHOUR,                               \n" +
				"    SUM(DECQTY)  AS SUM_DECQTY              \n" +
				"FROM YMS_DM.F_INSPIMPTDEFECTEQP F           \n" +
				"WHERE INSPHOUR >= %s                        \n" +   // FROM_ETL_DT
				"   AND INSPHOUR <= %s                       \n" +   // TO_ETL_DT
				"   AND INSPSTEPTYPE = 'GTF'                 \n" +
				"   AND PRODID IN ( %s )                     \n" +   // USER_GROUP_CODE
				"   AND SITEID = %s                          \n" +   // LINE
				"GROUP BY IMPTSTEPGRPID, IMPTEQPID, PROCHOUR \n" +
				"ORDER BY IMPTSTEPGRPID, IMPTEQPID, PROCHOUR \n" +
				""
				//, "'20140701110000'", "'20140702105959'", "'LTL097QL01-A01'", "'L6FAB'", "'L6FABSTDALONE'", "'32PR'");
				, StrUtil.quote(Parameter.getInstance().getStrFromDateTime())
				, StrUtil.quote(Parameter.getInstance().getStrToDateTime())
				, StrUtil.quote(Parameter.getInstance().getStrUserGroupCode())
				, StrUtil.quote(Parameter.getInstance().getStrLine())
		);

		return query;
	}
	
	/**
	 * 
	 * getLine78MOD
	 *
	 * @return
	 */
	private String getLine78LC()
	{
		String query = null;
		
		query = String.format("" +
				"/* TOTALCELLFLOW : L7AFAB, L7BFAB, L8AFAB, L8ZFAB  - LC */ \n" +
				"SELECT /*+ FULL(F)  PARALLEL(2) */                         \n" +
				"        IMPTSTEPGRPID,                                     \n" +
				"        IMPTEQPID,                                         \n" +
				"        PROCHOUR,                                          \n" +
				"        SUM(DECQTY)  AS SUM_DECQTY                         \n" +
				"FROM YMS_DM.F_INSPIMPTDEFECTEQP F                          \n" +
				"WHERE INSPHOUR >= %s                                       \n" +   // FROM_ETL_DT
				"    AND INSPHOUR <= %s                                     \n" +   // TO_ETL_DT
				"    AND INSPSTEPTYPE = 'GTF'                               \n" +
				"    AND PRODID IN ( %s )                                   \n" +   // USER_GROUP_CODE
				"    AND SITEID = %s                                        \n" +   // LINE
				"GROUP BY IMPTSTEPGRPID, IMPTEQPID, PROCHOUR                \n" +
				"ORDER BY IMPTSTEPGRPID, IMPTEQPID, PROCHOUR                \n" +
				""
				//, "'20140702070000'", "'20140703065959'", "'LSC480HN02-G01'", "'L8AFAB'", "'8AFAB'", "'L8AFAB'", "'L8AFAB'", "'L8AFAB'");
				, StrUtil.quote(Parameter.getInstance().getStrFromDateTime())
				, StrUtil.quote(Parameter.getInstance().getStrToDateTime())
				, StrUtil.quote(Parameter.getInstance().getStrUserGroupCode())
				, StrUtil.quote(Parameter.getInstance().getStrLine())
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
				"/* TOTALCELLFLOW : L7AFAB, L7BFAB, L8AFAB, L8ZFAB  - MOD */ \n" +
				"SELECT /*+ FULL (F)  PARALLEL(2) */                         \n" +
				"        IMPTSTEPGRPID,                                      \n" +
				"        IMPTEQPID,                                          \n" +
				"        PROCHOUR,                                           \n" +
				"        SUM(DECQTY) AS SUM_DECQTY                           \n" +
				"FROM YMS_DM.F_INSPIMPTDEFECTEQP F                           \n" +
				"WHERE INSPHOUR >= %s                                        \n" +   // FROM_ETL_DT
				"    AND INSPHOUR <= %s                                      \n" +   // TO_ETL_DT
				"    AND INSPSTEPTYPE = 'MMT'                                \n" +
				"    AND PRODID IN ( %s )                                    \n" +   // USER_GROUP_CODE
				"    AND SITEID = %s                                         \n" +   // LINE
				"    AND DECGRADECD IN( 'OK','TG','RJ')                      \n" +
				"GROUP BY IMPTSTEPGRPID, IMPTEQPID, PROCHOUR                 \n" +
				"ORDER BY IMPTSTEPGRPID, IMPTEQPID, PROCHOUR                 \n" +
				""
				//, "'20140702070000'", "'20140703065959'", "'LSC480HN02-G01'", "'L8AFAB'", "'8AFAB'", "'L8AFAB'", "'L8AFAB'", "'L8AFAB'");
				, StrUtil.quote(Parameter.getInstance().getStrFromDateTime())
				, StrUtil.quote(Parameter.getInstance().getStrToDateTime())
				, StrUtil.quote(Parameter.getInstance().getStrUserGroupCode())
				, StrUtil.quote(Parameter.getInstance().getStrLine())
		);

		return query;
	}
	
	/**
	 * getList
	 */
	@Override
	public List<TOTALCELLFLOW_ReadBean> getList()
	{
		return getList(false);
	}
	
	public List<TOTALCELLFLOW_ReadBean> getList(boolean flgRead)
	{
		if (Flag.TRUE) {
			if (!flgRead) {
				/*
				 * if flgRead = true, 초기에 읽지않는다.
				 */
				list = new ArrayList<TOTALCELLFLOW_ReadBean>();
				return list;
			}
			
			try {
				String query = null;
				
				if ("L5FAB".equals(Parameter.getInstance().getStrLine()) || "L6FAB".equals(Parameter.getInstance().getStrLine())) {
					if ("LC".equals(Parameter.getInstance().getStrAreaCode())) {
						query = getLine56LC();
					} else if ("MOD".equals(Parameter.getInstance().getStrAreaCode())) {
						return list;
					}
				} else if ("L7AFAB".equals(Parameter.getInstance().getStrLine()) || "L7BFAB".equals(Parameter.getInstance().getStrLine()) || "L8AFAB".equals(Parameter.getInstance().getStrLine()) || "L8ZFAB".equals(Parameter.getInstance().getStrLine())) {
					if ("LC".equals(Parameter.getInstance().getStrAreaCode())) {
						query = getLine78LC();
					} else if ("MOD".equals(Parameter.getInstance().getStrAreaCode())) {
						query = getLine78MOD();
					}
				} else {
					return list;
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
							System.out.println(String.format("    IMPTSTEPGRPID = [%s]", rs.getString("IMPTSTEPGRPID")));
							System.out.println(String.format("    IMPTEQPID     = [%s]", rs.getString("IMPTEQPID"    )));
							System.out.println(String.format("    PROCHOUR      = [%s]", rs.getString("PROCHOUR"     )));
							System.out.println(String.format("    SUM_DECQTY    = [%s]", rs.getString("SUM_DECQTY"   )));
						}
						
						if (Flag.TRUE) {
							TOTALCELLFLOW_ReadBean bean = new TOTALCELLFLOW_ReadBean();

							bean.setImptStepGrpId(rs.getString("IMPTSTEPGRPID"));
							bean.setImptEqpId    (rs.getString("IMPTEQPID"    ));
							bean.setProcHour     (rs.getString("PROCHOUR"     ));
							bean.setSumDecQty    (rs.getString("SUM_DECQTY"   ));
							
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
				
				try { Thread.sleep(1000); } catch (InterruptedException ex) {};
				
				Connection04.getInstance().close();;
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
			SystemProperties.setTesting("020");
			BasePath.getInstance();
			try {
				Logger.getInstance(TOTALCELLFLOW_Main.jobId, TOTALCELLFLOW_Main.dsName + ".log");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		if (Flag.TRUE) {
			Parameter.getInstance(TOTALCELLFLOW_Main.jobId);
			
			TOTALCELLFLOW_OracleReader reader = new TOTALCELLFLOW_OracleReader();
			
			for (TOTALCELLFLOW_ReadBean bean : reader.getList(true)) {
				System.out.println(bean);
			}
			
			System.out.println("the read ok !!!");
		}

		Logger.exit();
	}
	
	public static void main(String[] args)
	{
		if (Flag.TRUE) test01();
	}
}
