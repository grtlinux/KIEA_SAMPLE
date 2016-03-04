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

package kiea.proj.sdc.anal.macro.sample.a11.aib.io;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import kiea.kr.co.tain.base.Flag;
import kiea.proj.sdc.anal.common.Connection;
import kiea.proj.sdc.anal.macro.impl.io.AbstractOracleReader;
import kiea.proj.sdc.anal.macro.sample.a11.aib.bean.TOTALCELLFLOW_ReadBean;
import kiea.proj.sdc.anal.util.StrUtil;
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
	
	private String strFromDateTime    = null;
	private String strToDateTime      = null;
	private String strLine            = null;
	private String strAreaCode        = null;
	private String strUserGroupCode   = null;
	@SuppressWarnings("unused")
	private String strDefectGroupCode = null;
	
	/**
	 * Sample02OracleReader
	 */
	public TOTALCELLFLOW_OracleReader(String strFromDateTime, String strToDateTime, String strLine, String strAreaCode, String strUserGroupCode, String strDefectGroupCode)
	{
		if (Flag.TRUE) {
			this.strFromDateTime    = strFromDateTime;
			this.strToDateTime      = strToDateTime;
			this.strLine            = strLine;
			this.strAreaCode        = strAreaCode;
			this.strUserGroupCode   = strUserGroupCode;
			this.strDefectGroupCode = strDefectGroupCode;
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
				"    SUM(DECQTY)                             \n" +
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
				, StrUtil.quote(strFromDateTime)
				, StrUtil.quote(strToDateTime)
				, StrUtil.quote(strUserGroupCode)
				, StrUtil.quote(strLine)
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
				"        SUM(DECQTY)                                        \n" +
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
				, StrUtil.quote(strFromDateTime)
				, StrUtil.quote(strToDateTime)
				, StrUtil.quote(strUserGroupCode)
				, StrUtil.quote(strLine)
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
				"        SUM(DECQTY)                                         \n" +
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
				, StrUtil.quote(strFromDateTime)
				, StrUtil.quote(strToDateTime)
				, StrUtil.quote(strUserGroupCode)
				, StrUtil.quote(strLine)
		);

		return query;
	}
	
	/**
	 * getList
	 */
	public List<TOTALCELLFLOW_ReadBean> getList()
	{
		if (Flag.TRUE) {
			try {
				String query = null;
				
				if ("L5FAB".equals(strLine) || "L6FAB".equals(strLine)) {
					if ("LC".equals(strAreaCode)) {
						query = getLine56LC();
					} else if ("MOD".equals(strAreaCode)) {
						return list;
					}
				} else if ("L7AFAB".equals(strLine) || "L7BFAB".equals(strLine) || "L8AFAB".equals(strLine) || "L8ZFAB".equals(strLine)) {
					if ("LC".equals(strAreaCode)) {
						query = getLine78LC();
					} else if ("MOD".equals(strAreaCode)) {
						query = getLine78MOD();
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
							System.out.println(String.format("    IMPTSTEPGRPID = [%s]", rs.getString("IMPTSTEPGRPID")));
							System.out.println(String.format("    IMPTEQPID     = [%s]", rs.getString("IMPTEQPID"    )));
							System.out.println(String.format("    PROCHOUR      = [%s]", rs.getString("PROCHOUR"     )));
							System.out.println(String.format("    SUM_DECQTY    = [%s]", rs.getString("SUM(DECQTY)"  )));
						}
						
						if (Flag.TRUE) {
							TOTALCELLFLOW_ReadBean bean = new TOTALCELLFLOW_ReadBean();

							bean.setImptStepGrpId(rs.getString("IMPTSTEPGRPID"));
							bean.setImptEqpId    (rs.getString("IMPTEQPID"    ));
							bean.setProcHour     (rs.getString("PROCHOUR"     ));
							bean.setSumDecQty    (rs.getString("SUM(DECQTY)"  ));
							
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
	
	@SuppressWarnings("unused")
	private static void test01()
	{
		if (Flag.TRUE) {

			String strFromDateTime    = null;
			String strToDateTime      = null;
			String strLine            = null;
			String strAreaCode        = null;
			String strUserGroupCode   = null;
			String strDefectGroupCode = null;

			if (!Flag.TRUE) {
				/*
				 * L5FAB, L6FAB - LC
				 */
				strFromDateTime    = "20140805090000";
				strToDateTime      = "20140806085959";
				strLine            = "L6FAB";
				strAreaCode        = "LC";
				strUserGroupCode   = "L6156H11";
				strDefectGroupCode = "POR";
			}

			if (!Flag.TRUE && Flag.FALSE) {
				/*
				 * L5FAB, L6FAB - MOD
				 */
				strFromDateTime    = "20140701110000";
				strToDateTime      = "20140702105959";
				strLine            = "L6FAB";
				strAreaCode        = "MOD";
				strUserGroupCode   = "LTL097QL01-A01";
				strDefectGroupCode = "32PR";
			}

			if (!Flag.TRUE) {
				/*
				 * L7AFAB, L7BFAB, L8AFAB, L8ZFAB - LC
				 */
				strFromDateTime    = "20140702110000";
				strToDateTime      = "20140703105959";
				strLine            = "L8AFAB";
				strAreaCode        = "LC";
				strUserGroupCode   = "L8480F71";
				strDefectGroupCode = "32PR";
			}

			if (Flag.TRUE) {
				/*
				 * L7AFAB, L7BFAB, L8AFAB, L8ZFAB - MOD
				 */
				strFromDateTime    = "20140702070000";
				strToDateTime      = "20140703065959";
				strLine            = "L8AFAB";
				strAreaCode        = "MOD";
				strUserGroupCode   = "LSC480HN02-G01";
				strDefectGroupCode = "32BOR";
			}

			TOTALCELLFLOW_OracleReader reader = new TOTALCELLFLOW_OracleReader(strFromDateTime, strToDateTime, strLine, strAreaCode, strUserGroupCode, strDefectGroupCode);
			reader.getList();
		}
	}
	
	public static void main(String[] args)
	{
		if (Flag.TRUE) test01();
	}
}
