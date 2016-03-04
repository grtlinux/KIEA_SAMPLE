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
public class YmsModWorkidTestMain
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
				            "/*****************************/                                  \n" +
				            "/*** 설비별, 사원별 불량율 ***/                                  \n" +
				            "/*****************************/                                  \n" +
				            "SELECT   /*+ PARALLEL(2) */                                      \n" +
				            "    M.INSPSTEPTYPE  AS INSPSTEPTYPE                              \n" +
				            "    , M.INSPEQPID   AS INSPEQPID                                 \n" +
				            "    , M.WORKERID    AS WORKERID                                  \n" +
				            "    , COUNT(*)      AS TOT                                       \n" +
				            "    , SUM(CASE WHEN                                              \n" +
				            "        DECGRADECD IN (%s)                                       \n" +  // DECISION_CODE
				            "        AND DEFECTCD = %s                                        \n" +  // DEFECT_GROUP_CODE
				            "        THEN 1 ELSE 0 END)  AS BAD                               \n" +
				            "FROM                                                             \n" +
				            "    yms_dm.H_INSPDEFECT      M                                   \n" +
				            "WHERE                                                            \n" +
				            "    1=1                                                          \n" +
				            "    AND M.SITEID   =   %s                                        \n" +  // LINE
				            "    AND M.INSPHOUR >=  %s                                        \n" +  // FROM_ETL_HOUR
				            "    AND M.INSPHOUR <=  %s                                        \n" +  // TO_ETL_HOUR
				            "    AND M.INSPSTEPTYPE IN ( %s )                                 \n" +  // SUB_AREA_CODE
				            "    AND ( M.INSPSTEPTYPE IN ( 'MMT','MFT','SDSZ_FT','SDSZ_MT' )  \n" +
				            "        OR  M.INSPSTEPTYPE LIKE '%%FT' )                         \n" +
				            "GROUP BY                                                         \n" +
				            "    M.INSPSTEPTYPE                                               \n" +
				            "    , M.INSPEQPID                                                \n" +
				            "    , M.WORKERID                                                 \n" +
							""
							, StrUtil.quote(Parameter.getInstance().getStrDecisionCode())
							, StrUtil.quote(Parameter.getInstance().getStrDefectGroupCode())
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
