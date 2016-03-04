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

package kiea.proj.sdc.anal.tools.yms.check.v01.t01;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.Properties;

import kiea.kr.co.tain.base.Flag;
import oracle.jdbc.OracleConnection;
import oracle.jdbc.OracleDriver;

/**
 * @author KangSeok
 * @date 2014. 8. 6.
 * @file_name TestMain.java
 *
 */
public class TestMain
{
	private static OracleConnection getConnection() throws Exception
	{
		if (Flag.TRUE) {
			
			String strUser;
			String strPassword;

			if (!Flag.TRUE) {
				/*
				 * DEVTRC 계정연결
				 */
				strUser = "DEVTRC";
				strPassword = "wkehdghk1";
			} else {
				/*
				 * YMS_TEST 계정연결
				 */
				strUser = "YMS_TEST";
				strPassword = "test11gyms#";
			}

			String strUrl = "jdbc:oracle:thin:@" +
					"(DESCRIPTION=" +
					"    (ADDRESS_LIST=(LOAD_BALANCE=ON)" +
					"        (ADDRESS=(PROTOCOL=TCP)(HOST=12.96.13.221)(PORT=1521))" +
					"        (ADDRESS=(PROTOCOL=TCP)(HOST=12.96.13.222)(PORT=1521))" +
					"        (ADDRESS=(PROTOCOL=TCP)(HOST=12.96.13.223)(PORT=1521))" +
					"        (ADDRESS=(PROTOCOL=TCP)(HOST=12.96.13.241)(PORT=1521))" +
					"        (ADDRESS=(PROTOCOL=TCP)(HOST=12.96.13.242)(PORT=1521))" +
					"        (ADDRESS=(PROTOCOL=TCP)(HOST=12.96.13.243)(PORT=1521))" +
					"    )" +
					"    (CONNECT_DATA =(SERVER=DEDICATED)" +
					"        (SERVICE_NAME=ymsdb)" +
					"    )" +
					")";
			
			OracleDriver oracleDriver = new OracleDriver();
			
			Properties prop = new Properties();
			prop.setProperty("user", strUser);
			prop.setProperty("password", strPassword);
			
			return (OracleConnection) oracleDriver.connect(strUrl, prop);
		}
		
		return null;
	}
	
	///////////////////////////////////////////////////////////////////////////

	private static void test01()
	{
		if (Flag.TRUE) {
			try {
				String query = String.format("" +
						"SELECT                                   " +
						"    A.JOB_ID                             " +
						"    , A.ANAL_EVENT_TYPE                  " +
						"    , A.LINE_ID                          " +
						"    , A.INSPECT_AREA                     " +
						"    , A.INSPECT_STEP                     " +
						"    , A.FROM_DATE                        " +
						"    , A.TO_DATE                          " +
						"    , C.PRODUCT_GRP                      " +
						"    , C.PRODUCT_TYPE                     " +
						"    , B.FAIL_CD                          " +
						"    , B.DECISION_CD                      " +
						"FROM                                     " +
						"    YMS_LYA.SYMS_LYA_JOB_MASTER A        " +
						"    , YMS_LYA.SYMS_LYA_JOB_FAIL_CODE B   " +
						"    , YMS_LYA.SYMS_LYA_JOB_PRODUCT_GRP C " +
						"WHERE 1=1                                " +
						"    AND A.JOB_ID IN (%s)                 " +
						"    AND A.JOB_ID = B.JOB_ID              " +
						"    AND A.JOB_ID = C.JOB_ID              " +
						""
						, "'LYAAU1408043181','LYAAU1408043178','LYAAU1408043120','LYAAU1408043128','LYAAU1408043080','LYAAU1408043171'");

				OracleConnection conn = getConnection();
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
						System.out.println(String.format("%d) [%s] [%s] [%s]", i, md.getColumnName(i), md.getColumnLabel(i), md.getColumnClassName(i)));
					}
				}
				
				conn.close();
				if (Flag.TRUE) System.out.println("##### OK!");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		if (Flag.TRUE) {
			try {
				String query = String.format("" +
						"SELECT                                    " +
						"    A.JOB_ID                              " +
						"    , A.ANAL_EVENT_TYPE                   " +
						"    , A.LINE_ID                           " +
						"    , A.INSPECT_AREA                      " +
						"    , A.INSPECT_STEP                      " +
						"    , A.FROM_DATE                         " +
						"    , A.TO_DATE                           " +
						"    , C.PRODUCT_GRP                       " +
						"    , C.PRODUCT_TYPE                      " +
						"    , B.FAIL_CD                           " +
						"    , B.DECISION_CD                       " +
						"FROM                                      " +
						"    SYMS_LYA_JOB_MASTER A                 " +
						"    , SYMS_LYA_JOB_FAIL_CODE B            " +
						"    , SYMS_LYA_JOB_PRODUCT_GRP C          " +
						"WHERE 1=1                                 " +
						"    AND A.JOB_ID IN (%s)                  " +
						"    AND A.JOB_ID = B.JOB_ID               " +
						"    AND A.JOB_ID = C.JOB_ID               " +
						""
						, "'LYAAU1408045001','LYAAU1408045002','LYAAU1408045003','LYAAU1408045004','LYAAU1408045005','LYAAU1408045006'");

				OracleConnection conn = getConnection();
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
						System.out.println(String.format("%d) [%s] [%s] [%s]", i, md.getColumnName(i), md.getColumnLabel(i), md.getColumnClassName(i)));
					}
				}
				
				conn.close();
				if (Flag.TRUE) System.out.println("##### OK!");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void main(String[] args)
	{
		if (Flag.TRUE) test01();
	}
}
