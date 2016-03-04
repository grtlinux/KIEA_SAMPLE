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

package kiea.z01.ztest.t01.oracle.thin;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.Properties;

import kiea.kr.co.tain.base.Flag;
import kiea.proj.sdc.anal.common.Parameter;
import kiea.proj.sdc.anal.util.StrUtil;
import oracle.jdbc.OracleConnection;
import oracle.jdbc.OracleDriver;

/**
 * @author KangSeok
 * @date 2014. 8. 5.
 * @file_name YmsModBinTestMain.java
 *
 */
public class YmsModBinTestMain
{
	private static void test01()
	{
		if (Flag.TRUE) {
			try {
				//String strUser = "YMS_TEST";
				//String strPassword = "test11gyms#";
				String strUser = "DEVTRC";
				String strPassword = "wkehdghk1";
				
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
				
				OracleConnection conn = (OracleConnection) oracleDriver.connect(strUrl, prop);

				if (Flag.TRUE) {
					Parameter.getInstance(7);
					String query = String.format("" +
							"SELECT /*+ PARALLEL(2) */      \n" +
							"    M.INSPHOUR                 \n" +
							"    , M.CELLID                 \n" +
							"    , M.INSPSTEPTYPE           \n" +
							"    , M.DECGRADECD             \n" +
							"    , M.DEFECTCD               \n" +
							"    , L.BINGRADECD             \n" +
							"    , ROW_NUMBER() OVER (PARTITION BY L.CELLID ORDER BY L.INSPDATE DESC) RN  \n" +
							"FROM                           \n" +
							"    yms_dm.H_INSPDEFECT M      \n" +
							"    , yms_dm.H_INSPDEFECT L    \n" +
							"WHERE 1=1                      \n" +
							"    AND ROWNUM <= 100          \n" +
							"    AND M.SITEID = %s          \n" +
							"    AND M.SITEID = L.SITEID    \n" +
							"    AND M.CELLID = L.CELLID    \n" +
							"    AND M.INSPHOUR >= %s       \n" +
							"    AND M.INSPHOUR <= %s       \n" +
							"    AND M.INSPSTEPTYPE IN ( %s )                                                                      \n" +
							"    /* AND ( M.INSPSTEPTYPE IN ( 'MMT','MFT','SESL_FT','SESL_MT' ) OR M.INSPSTEPTYPE LIKE '%%FT' ) */ \n" +
							"    AND L.INSPSTEPTYPE IN ('GTF','GTL','VIGT_DRS','CT2F','CT2L') /* FIXED */                          \n" +
							"    AND L.BINGRADECD IN ('B1','B2','B3','B4','B5','B6')                                               \n" +
							""
							, StrUtil.quote(Parameter.getInstance().getStrLine())
							, StrUtil.quote(Parameter.getInstance().getStrFromDateTime())
							, StrUtil.quote(Parameter.getInstance().getStrToDateTime())
							, StrUtil.quote(Parameter.getInstance().getStrSubAreaCode())
							);
					System.out.println("[" + query + "]");
					
					Statement stmt = conn.createStatement();
					ResultSet rs = stmt.executeQuery(query);
					ResultSetMetaData md = rs.getMetaData();

					System.out.println("------------- QUERY DATA -----------------------");

					for (int i=0; i < 100000 && rs.next(); i++) {
						
						StringBuffer sb = new StringBuffer();
						sb.append(String.format("(%4d)   ", i));
						
						for (int col=1; col <= md.getColumnCount(); col++) {
							sb.append("\t").append(rs.getString(col));
						}
						
						System.out.println(sb.toString());
					}
					
					System.out.println("------------- META DATA -----------------------");
					
					for (int i=1; i <= md.getColumnCount(); i++) {
						System.out.println(String.format("%2d) [%-30s] [%s] [%s]", i, md.getColumnName(i), md.getColumnLabel(i), md.getColumnClassName(i)));
					}
				}

				conn.close();
				System.out.println("OK!");
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
