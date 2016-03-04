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
public class YmsModEqpSmmryTestMain
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
							"/*********************/                                           \n" +
							"/*** MOD EQP SMMRY ***/                                           \n" +
							"/*********************/                                           \n" +
							"SELECT /*+INDEX(T1 H_INSPDEFECT_IDX01)*/                          \n" +
							"      T1.SITEID                       AS LINE_CODE                \n" +
							"    , T2.PRODGRPNAME                  AS USER_GROUP_CODE          \n" +
							"    , T2.PROCID                       AS PROCESS_ID               \n" +
							"    , T1.PRODID                       AS PRODUCT_ID               \n" +
							"    , T1.PRODTYPE                     AS PRODUCT_TYPE             \n" +
							"    , CASE                                                        \n" +
							"        WHEN INSPSTEPID LIKE 'T%%' THEN 'TFT'                     \n" +
							"        WHEN INSPSTEPID LIKE 'F%%' THEN 'CF'                      \n" +
							"        WHEN INSPSTEPID LIKE 'L%%' THEN 'LC'                      \n" +
							"        ELSE 'MOD'                                                \n" +
							"        END                           AS AREA_CODE                \n" +
							"    , T1.INSPSTEPTYPE                 AS SUB_AREA_CODE            \n" +
							"    , T1.INSPSTEPID                   AS STEP_ID                  \n" +
							"    , T1.INSPEQPID                    AS EQP_ID                   \n" +
							"    , T1.GLASSID                      AS GLASS_ID                 \n" +
							"    , T1.CELLID                       AS CELL_ID                  \n" +
							"    , REPLACE (CELLID, GLASSID, '')   AS CELL_LOC_ID              \n" +
							"    , T1.DEFECTCD                     AS DEFECT_GROUP_CODE        \n" +
							"    , T1.DECGRADECD                   AS DECISION_CODE            \n" +
							"    , T1.WORKERID                     AS INSPECTOR_ID             \n" +
							"    , T1.INSPDATE                     AS INSPECT_DT               \n" +
							"    , T1.INSPHOUR                     AS INSPECT_HOUR             \n" +
							"    , TO_CHAR(T1.INSPDATE, 'YYYYMMDD HH24')    AS INSPECT_HOUR2   \n" +
							"FROM                                                              \n" +
							"    yms_dm.H_INSPDEFECT T1                                        \n" +
							"    , yms_dm.D_PRODUCT  T2                                        \n" +
							"WHERE                                                             \n" +
							"    1 = 1                                                         \n" +
							"    AND ROWNUM <= 100                                             \n" +
							"    AND T1.SITEID    = %s                                         \n" +  // LINE
							"    AND T1.INSPHOUR >= %s                                         \n" +  // FROM_ETL_HOUR
							"    AND T1.INSPHOUR <= %s                                         \n" +  // TO_ETL_HOUR
							"    AND T2.SITEID = T1.SITEID                                     \n" +
							"    AND T2.PRODID = T1.PRODID                                     \n" +
							"    AND T1.PRODTYPE IN ( %s )                                     \n" +  // PRODUCT_TYPE
							"    AND T1.INSPSTEPTYPE IN ( %s )                                 \n" +  // SUB_AREA_CODE
							"    AND T2.PRODGRPNAME  IN ( %s )                                 \n" +  // USER_GROUP_CODE
							""
							, StrUtil.quote(Parameter.getInstance().getStrLine())
							, StrUtil.quote(Parameter.getInstance().getStrFromDateTime())
							, StrUtil.quote(Parameter.getInstance().getStrToDateTime())
							, StrUtil.quote(Parameter.getInstance().getStrProductType())
							, StrUtil.quote(Parameter.getInstance().getStrSubAreaCode())
							, StrUtil.quote(Parameter.getInstance().getStrUserGroupCode())
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
