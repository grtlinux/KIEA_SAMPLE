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

package kiea.proj.sdc.anal.macro.sample.a02.io;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import kiea.kr.co.tain.base.Flag;
import kiea.proj.sdc.anal.macro.impl.io.AbstractOracleReader;
import kiea.proj.sdc.anal.macro.sample.a02.bean.Sample02ReadBean;
import oracle.jdbc.OracleConnection;
import oracle.jdbc.OracleDriver;

/**
 * @author KangSeok
 * @date 2014. 8. 5.
 * @file_name Sample02OracleReader.java
 *
 */
public class Sample02OracleReader extends AbstractOracleReader
{
	private List<Sample02ReadBean> list = new ArrayList<Sample02ReadBean>();
	
	/**
	 * Sample02OracleReader
	 */
	public Sample02OracleReader() {}

	/**
	 * getList
	 */
	public List<Sample02ReadBean> getList()
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
				
				String query = String.format("" +
						"SELECT /*+ PARALLEL(2) */      " +
						"    M.INSPHOUR                 " +
						"    , M.CELLID                 " +
						"    , M.INSPSTEPTYPE           " +
						"    , M.DECGRADECD             " +
						"    , M.DEFECTCD               " +
						"    , L.BINGRADECD             " +
						"    , ROW_NUMBER() OVER (PARTITION BY L.CELLID ORDER BY L.INSPDATE DESC) RN  " +
						"FROM                           " +
						"    yms_dm.H_INSPDEFECT M      " +
						"    , yms_dm.H_INSPDEFECT L    " +
						"WHERE                          " +
						"    M.SITEID     = '%s'        " +
						"    AND M.SITEID = L.SITEID    " +
						"    AND M.CELLID = L.CELLID    " +
						"    AND M.INSPHOUR >= '%s'     " +
						"    AND M.INSPHOUR <= '%s'     " +
						"    AND M.INSPSTEPTYPE IN ( 'MMT' )                                                                   " +
						"    /* AND ( M.INSPSTEPTYPE IN ( 'MMT','MFT','SESL_FT','SESL_MT' ) OR M.INSPSTEPTYPE LIKE '%%FT' ) */ " +
						"    AND L.INSPSTEPTYPE IN ('GTF','GTL','VIGT_DRS','CT2F','CT2L') /* FIXED */                          " +
						"    AND L.BINGRADECD IN ('B1','B2','B3','B4','B5','B6') " +
						""
						, "L7BFAB", "2014080511", "2014080514");
				
				OracleDriver oracleDriver = new OracleDriver();
				
				Properties prop = new Properties();
				prop.setProperty("user", strUser);
				prop.setProperty("password", strPassword);
				
				OracleConnection conn = (OracleConnection) oracleDriver.connect(strUrl, prop);

				if (Flag.TRUE) {
					Statement stmt = conn.createStatement();
					ResultSet rs = stmt.executeQuery(query);
					for (int i=0; i < 10000 && rs.next(); i++) {
						if (!Flag.TRUE) {
							System.out.println(String.format("%3d) --------------------------------------", i));
							System.out.println(String.format("    INSPHOUR     = [%s]", rs.getString("INSPHOUR")));
							System.out.println(String.format("    CELLID       = [%s]", rs.getString("CELLID")));
							System.out.println(String.format("    INSPSTEPTYPE = [%s]", rs.getString("INSPSTEPTYPE")));
							System.out.println(String.format("    DECGRADECD   = [%s]", rs.getString("DECGRADECD")));
							System.out.println(String.format("    DEFECTCD     = [%s]", rs.getString("DEFECTCD")));
							System.out.println(String.format("    BINGRADECD   = [%s]", rs.getString("BINGRADECD")));
							System.out.println(String.format("    RN           = [%s]", rs.getString("RN")));
						}
						
						Sample02ReadBean bean = new Sample02ReadBean();

						bean.setInspHour     (rs.getString("INSPHOUR")    );
						bean.setCellId       (rs.getString("CELLID")      );
						bean.setInspStepType (rs.getString("INSPSTEPTYPE"));
						bean.setDecGradeCd   (rs.getString("DECGRADECD")  );
						bean.setDefectCd     (rs.getString("DEFECTCD")    );
						bean.setBinGradeCd   (rs.getString("BINGRADECD")  );
						bean.setRn           (rs.getInt   ("RN")          );
						
						list.add(bean);
					}
					
					if (!Flag.TRUE) {
						System.out.println("FetchSize = " + rs.getFetchSize());
						
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
}
