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

package kiea.proj.sdc.anal.macro.sample.a11.mod.io;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import kiea.kr.co.tain.base.Flag;
import kiea.proj.sdc.anal.common.Connection;
import kiea.proj.sdc.anal.common.Parameter;
import kiea.proj.sdc.anal.macro.impl.io.AbstractOracleReader;
import kiea.proj.sdc.anal.macro.sample.a11.mod.bean.MOD_BIN_ReadBean;
import kiea.proj.sdc.anal.util.StrUtil;
import oracle.jdbc.OracleConnection;

/**
 * @author KangSeok
 * @date 2014. 8. 8.
 * @file_name MOD_BIN_OracleReader.java
 *
 */
public class MOD_BIN_OracleReader extends AbstractOracleReader
{
	private List<MOD_BIN_ReadBean> list = new ArrayList<MOD_BIN_ReadBean>();
	
	/**
	 * Sample02OracleReader
	 */
	public MOD_BIN_OracleReader()
	{
		if (Flag.TRUE) {
		}
	}

	/**
	 * 
	 * getLineMOD
	 *
	 * @return
	 */
	private String getLineMOD()
	{
		String query = null;
		
		query = String.format("" +
				"/****************************/                                                                                      \n" +
				"/*** 시간별 BIN별 유의차  ***/                                                                                      \n" +
				"/****************************/                                                                                      \n" +
				"SELECT                                                                                                              \n" +
				"    'HOUR' AS INSPHOUR                                                                                              \n" +
				"    , BINGRADECD                                                                                                    \n" +
				"    , COUNT(*)    AS TOT                                                                                            \n" +
				"    , SUM(CASE WHEN DECGRADECD IN (%s) AND DEFECTCD = %s THEN 1 ELSE 0 END) AS BAD                                  \n" +  // DECISION_CODE, DEFECT_GROUP_CODE
				"    , SUM(CASE WHEN DECGRADECD IN (%s) AND DEFECTCD = %s THEN 0 ELSE 1 END) AS GOOD                                 \n" +  // DECISION_CODE, DEFECT_GROUP_CODE
				"FROM                                                                                                                \n" +
				"(                                                                                                                   \n" +
				"    SELECT /*+ PARALLEL(2) */                                                                                       \n" +
				"        M.INSPHOUR                                                                                                  \n" +
				"        , M.CELLID                                                                                                  \n" +
				"        , M.INSPSTEPTYPE                                                                                            \n" +
				"        , M.DECGRADECD                                                                                              \n" +
				"        , M.DEFECTCD                                                                                                \n" +
				"        , L.BINGRADECD                                                                                              \n" +
				"        , ROW_NUMBER() OVER (PARTITION BY L.CELLID ORDER BY L.INSPDATE DESC) RN                                     \n" +
				"    FROM                                                                                                            \n" +
				"        yms_dm.H_INSPDEFECT M                                                                                       \n" +
				"        , yms_dm.H_INSPDEFECT L                                                                                     \n" +
				"    WHERE 1=1                                                                                                       \n" +
				//"        AND ROWNUM <= 100                                                                                           \n" +
				"        AND M.SITEID = %s                                                                                           \n" +  // LINE
				"        AND M.SITEID = L.SITEID                                                                                     \n" +
				"        AND M.CELLID = L.CELLID                                                                                     \n" +
				"        AND M.INSPHOUR >= %s                                                                                        \n" +  // FROM_ETL_HOUR
				"        AND M.INSPHOUR <= %s                                                                                        \n" +  // TO_ETL_HOUR
				"        AND M.INSPSTEPTYPE IN ( %s )                                                                                \n" +  // SUB_AREA_CODE
				"        /* AND ( M.INSPSTEPTYPE IN ( 'MMT','MFT','SESL_FT','SESL_MT' ) OR  M.INSPSTEPTYPE LIKE '%%FT' ) */          \n" +
				"        AND L.INSPSTEPTYPE IN ('GTF','GTL','VIGT_DRS','CT2F','CT2L') /* FIXED */                                    \n" +
				"        AND L.BINGRADECD IN ('B1','B2','B3','B4','B5','B6')                                                         \n" +
				")                                                                                                                   \n" +
				"WHERE RN = 1                                                                                                        \n" +
				"/*                                                                                                                  \n" +
				"GROUP BY INSPHOUR, BINGRADECD                                                                                       \n" +
				"ORDER BY INSPHOUR, BINGRADECD                                                                                       \n" +
				"*/                                                                                                                  \n" +
				"GROUP BY BINGRADECD                                                                                                 \n" +
				"ORDER BY BINGRADECD                                                                                                 \n" +
				""
				, StrUtil.quote(Parameter.getInstance().getStrDecisionCode())
				, StrUtil.quote(Parameter.getInstance().getStrDefectGroupCode())
				, StrUtil.quote(Parameter.getInstance().getStrDecisionCode())
				, StrUtil.quote(Parameter.getInstance().getStrDefectGroupCode())
				, StrUtil.quote(Parameter.getInstance().getStrLine())
				, StrUtil.quote(Parameter.getInstance().getStrFromDateTime())
				, StrUtil.quote(Parameter.getInstance().getStrToDateTime())
				, StrUtil.quote(Parameter.getInstance().getStrSubAreaCode())
				);

		return query;
	}

	/**
	 * getList
	 */
	public List<MOD_BIN_ReadBean> getList()
	{
		if (Flag.TRUE) {
			try {
				String query = null;
				
				if ("MOD".equals(Parameter.getInstance().getStrAreaCode())) {
					query = getLineMOD();
				} else {
					return list;
				}
				
				if (!Flag.TRUE) System.out.println("[" + query + "]");
				
				OracleConnection conn = Connection.getOracleConnection();

				if (Flag.TRUE) {
					Statement stmt = conn.createStatement();
					ResultSet rs = stmt.executeQuery(query);
					for (int i=0; i < 1000 && rs.next(); i++) {                     // TODO : 2014.08.26 : 최대건수 처리
						if (!Flag.TRUE) {
							System.out.println(String.format("%3d) --------------------------------------", i));
							System.out.println(String.format("    INSPHOUR   = [%s]", rs.getString("INSPHOUR"  )));
							System.out.println(String.format("    BINGRADECD = [%s]", rs.getString("BINGRADECD")));
							System.out.println(String.format("    TOT        = [%s]", rs.getString("TOT"       )));
							System.out.println(String.format("    BAD        = [%s]", rs.getString("BAD"       )));
							System.out.println(String.format("    GOOD       = [%s]", rs.getString("GOOD"      )));
						}
						
						if (Flag.TRUE) {
							MOD_BIN_ReadBean bean = new MOD_BIN_ReadBean();

							bean.setInspHour  (rs.getString("INSPHOUR"  ));
							bean.setBinGradeCd(rs.getString("BINGRADECD"));
							bean.setTot       (rs.getString("TOT"       ));
							bean.setBad       (rs.getString("BAD"       ));
							bean.setGood      (rs.getString("GOOD"      ));
							
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
			MOD_BIN_OracleReader reader = new MOD_BIN_OracleReader();
			reader.getList();
		}
	}
	
	public static void main(String[] args)
	{
		if (Flag.TRUE) test01();
	}
}
