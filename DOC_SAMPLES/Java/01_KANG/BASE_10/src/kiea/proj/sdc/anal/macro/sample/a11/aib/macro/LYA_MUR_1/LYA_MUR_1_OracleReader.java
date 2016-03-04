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

package kiea.proj.sdc.anal.macro.sample.a11.aib.macro.LYA_MUR_1;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import kiea.kr.co.tain.base.Flag;
import kiea.proj.sdc.anal.common.Connection;
import kiea.proj.sdc.anal.macro.impl.io.AbstractOracleReader;
import kiea.proj.sdc.anal.util.StrUtil;
import oracle.jdbc.OracleConnection;

/**
 * @author KangSeok
 * @date 2014. 8. 8.
 * @file_name LYA_MUR_1_OracleReader.java
 *
 */
@SuppressWarnings("unused")
public class LYA_MUR_1_OracleReader extends AbstractOracleReader
{
	private List<LYA_MUR_1_ReadBean> list = new ArrayList<LYA_MUR_1_ReadBean>();
	
	private String strFromDateTime    = null;
	private String strToDateTime      = null;
	private String strLine            = null;
	private String strAreaCode        = null;
	private String strUserGroupCode   = null;
	private String strDefectGroupCode = null;
	
	/**
	 * Sample02OracleReader
	 */
	public LYA_MUR_1_OracleReader(String strFromDateTime, String strToDateTime, String strLine, String strAreaCode, String strUserGroupCode, String strDefectGroupCode)
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
	 * getJobDipHistory
	 *
	 * @return
	 */
	private String getJobDipHistory()
	{
		String query = null;
		
		query = String.format("" +
				"/* LYA_MUR_1   */                             \n" +
				"SELECT                                        \n" +
				"    T1.QAF_JOB_BU,                            \n" +
				"    T1.QAF_JOB_ID,                            \n" +
				"    T1.QAF_JOB_DIP_NAME,                      \n" +
				"    T1.QAF_JOB_DIP_VALUE,                     \n" +
				"    T1.QAF_JOB_DIP_CONDITION                  \n" +
				"FROM                                          \n" +
				"    QAF_ID_JOB_DIP_HISTORY T1                 \n" +
				"WHERE 1 = 1                                   \n" +
				"    AND ROWNUM <= 100                         \n" +
				"    AND T1.QAF_JOB_ID = %s                    \n" +   // AUTO_JOB_ID
				"ORDER BY                                      \n" +
				"    T1.QAF_JOB_DIP_NAME, T1.QAF_JOB_DIP_VALUE \n" +
				""
				, StrUtil.quote("LYAAU1401033254")
				);

		return query;
	}

	/**
	 * getList
	 */
	public List<LYA_MUR_1_ReadBean> getList()
	{
		if (Flag.TRUE) {
			try {
				String query = null;
				
				query = getJobDipHistory();
				
				if (!Flag.TRUE) System.out.println("[" + query + "]");
				
				OracleConnection conn = Connection.getOracleConnection();

				if (Flag.TRUE) {
					Statement stmt = conn.createStatement();
					ResultSet rs = stmt.executeQuery(query);
					for (int i=0; i < 1000000 && rs.next(); i++) {
						if (!Flag.TRUE) {
							System.out.println(String.format("%3d) --------------------------------------", i));
							System.out.println(String.format("    QAF_JOB_BU            = [%s]", rs.getString("QAF_JOB_BU"           )));
							System.out.println(String.format("    QAF_JOB_ID            = [%s]", rs.getString("QAF_JOB_ID"           )));
							System.out.println(String.format("    QAF_JOB_DIP_NAME      = [%s]", rs.getString("QAF_JOB_DIP_NAME"     )));
							System.out.println(String.format("    QAF_JOB_DIP_VALUE     = [%s]", rs.getString("QAF_JOB_DIP_VALUE"    )));
							System.out.println(String.format("    QAF_JOB_DIP_CONDITION = [%s]", rs.getString("QAF_JOB_DIP_CONDITION")));
						}
						
						if (Flag.TRUE) {
							LYA_MUR_1_ReadBean bean = new LYA_MUR_1_ReadBean();

							bean.setQafjobBu          (rs.getString("QAF_JOB_BU"           ));
							bean.setQafjobId          (rs.getString("QAF_JOB_ID"           ));
							bean.setQafjobDipName     (rs.getString("QAF_JOB_DIP_NAME"     ));
							bean.setQafjobDipValue    (rs.getString("QAF_JOB_DIP_VALUE"    ));
							bean.setQafjobDipCondition(rs.getString("QAF_JOB_DIP_CONDITION"));
							
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

			if (!Flag.TRUE) {
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

			if (Flag.TRUE) {
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

			if (!Flag.TRUE) {
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

			LYA_MUR_1_OracleReader reader = new LYA_MUR_1_OracleReader(strFromDateTime, strToDateTime, strLine, strAreaCode, strUserGroupCode, strDefectGroupCode);
			reader.getList();
		}
	}
	
	public static void main(String[] args)
	{
		if (Flag.TRUE) test01();
	}
}
