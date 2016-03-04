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

package kiea.proj.sdc.anal.tools.yms.check.v01.MURA_WIP.m06.s03;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;

import kiea.kr.co.tain.base.Flag;
import kiea.proj.sdc.anal.common.Connection;
import kiea.proj.sdc.anal.common.Parameter;
import kiea.proj.sdc.anal.util.StrUtil;
import oracle.jdbc.OracleConnection;

/**
 * @author KangSeok
 * @date 2014. 8. 11.
 * @file_name EQP_MURA_RAW.java
 *
 */
public class EQP_MURA_RAW
{
	/*
	 * 
	 *     EQP_MURA_RAW
	 *
	 */
	
	///////////////////////////////////////////////////////////////////////////

	private static void line56()
	{
		if (Flag.TRUE) {
			try {
				String query = String.format("" +
						"/* EQP_MURA_RAW : L5FAB, L6FAB  */                              \n" +
						"SELECT                                                          \n" +
						"    *                                                           \n" +
						"FROM                                                            \n" +
						"    yms_dm.h_meas_rundata_cell                                  \n" +
						"WHERE                                                           \n" +
						"    siteid = %s                                                 \n" +  // LINE
						"    AND TO_DATE (%s, 'yyyymmddhh24miss') <= dcoldate            \n" +  // FROM_ETL_DT
						"    AND TO_DATE (%s, 'yyyymmddhh24miss') > dcoldate             \n" +  // TO_ETL_DT
						"    AND orgstepid like 'L__080'                                 \n" +
						"    AND itemid = %s                                             \n" +  // DEFECT_GROUP_CODE
						"    AND PROCID = %s                                             \n" +  // USER_GROUP_CODE
						""
						, StrUtil.quote(Parameter.getInstance().getStrLine           ())
						, StrUtil.quote(Parameter.getInstance().getStrFromDateTime   ())
						, StrUtil.quote(Parameter.getInstance().getStrToDateTime     ())
						, StrUtil.quote(Parameter.getInstance().getStrDefectGroupCode())
						, StrUtil.quote(Parameter.getInstance().getStrUserGroupCode  ())
						);

				OracleConnection conn = Connection.getOracleConnection();
				Statement stmt = conn.createStatement();
				
				if (Flag.TRUE) {
					ResultSet rs = stmt.executeQuery(query);
					ResultSetMetaData md = rs.getMetaData();

					for (int i=0; i < 1000 && rs.next(); i++) {
						
						StringBuffer sb = new StringBuffer();
						sb.append(String.format("(%4d)   ", i));
						
						for (int col=1; col <= md.getColumnCount(); col++) {
							sb.append("\t").append(rs.getString(col));
						}
						
						System.out.println(sb.toString());
					}
					
					System.out.println("------------- META DATA -----------------------");
					
					for (int i=1; i <= md.getColumnCount(); i++) {
						System.out.println(String.format("%2d) [%-30s] [%-50s]", i, md.getColumnName(i), md.getColumnClassName(i)));
					}
				}
				
				conn.close();
				if (Flag.TRUE) System.out.println("##### OK!");
				if (!Flag.TRUE) System.out.println("[" + query + "]");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	private static void line78()
	{
		if (Flag.TRUE) {
			try {
				String query = String.format("" +
						"/* EQP_MURA_RAW : L7AFAB, L7BFAB, L8AFAB, L8ZFAB  */            \n" +
						"SELECT                                                          \n" +
						"    *                                                           \n" +
						"FROM                                                            \n" +
						"    yms_dm.h_meas_rundata_cell                                  \n" +
						"WHERE                                                           \n" +
						"    siteid = %s                                                 \n" +  // LINE
						"    AND TO_DATE (%s, 'yyyymmddhh24miss') <= dcoldate            \n" +  // FROM_ETL_DT
						"    AND TO_DATE (%s, 'yyyymmddhh24miss') > dcoldate             \n" +  // TO_ETL_DT
						"    AND measstepgrpid LIKE 'L%%'                                \n" +
						"    AND itemid = %s                                             \n" +  // DEFECT_GROUP_CODE
						"    AND PROCID = %s                                             \n" +  // USER_GROUP_CODE
						""
						, StrUtil.quote(Parameter.getInstance().getStrLine           ())
						, StrUtil.quote(Parameter.getInstance().getStrFromDateTime   ())
						, StrUtil.quote(Parameter.getInstance().getStrToDateTime     ())
						, StrUtil.quote(Parameter.getInstance().getStrDefectGroupCode())
						, StrUtil.quote(Parameter.getInstance().getStrUserGroupCode  ())
						);

				OracleConnection conn = Connection.getOracleConnection();
				Statement stmt = conn.createStatement();
				
				if (Flag.TRUE) {
					ResultSet rs = stmt.executeQuery(query);
					ResultSetMetaData md = rs.getMetaData();

					for (int i=0; i < 10000 && rs.next(); i++) {
						
						StringBuffer sb = new StringBuffer();
						sb.append(String.format("(%4d)   ", i));
						
						for (int col=1; col <= md.getColumnCount(); col++) {
							sb.append("\t").append(rs.getString(col));
						}
						
						System.out.println(sb.toString());
					}
					
					System.out.println("------------- META DATA -----------------------");
					
					for (int i=1; i <= md.getColumnCount(); i++) {
						System.out.println(String.format("%2d) [%-30s] [%-50s]", i, md.getColumnName(i), md.getColumnClassName(i)));
					}
				}
				
				conn.close();
				if (Flag.TRUE) System.out.println("##### OK!");
				if (!Flag.TRUE) System.out.println("[" + query + "]");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void main(String[] args)
	{
		if (!Flag.TRUE) {
			if (Flag.TRUE) line56();    // L5FAB, L6FAB
			if (!Flag.TRUE) line78();    // L7AFAB, L7BFAB, L8AFAB, L8ZFAB
		}

		if (Flag.TRUE) {
			String strLine = Parameter.getInstance().getStrLine();
			
			if ("L5FAB".equals(strLine) || "L6FAB".equals(strLine)) {
				line56();
			} else if ("L7AFAB".equals(strLine) || "L7BFAB".equals(strLine) || "L8AFAB".equals(strLine) || "L8ZFAB".equals(strLine)) {
				line78();
			}
		}
	}
}
