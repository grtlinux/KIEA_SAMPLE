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

package kiea.proj.sdc.anal.macro.sample.a03.io;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import kiea.kr.co.tain.base.Flag;
import kiea.proj.sdc.anal.common.Connection;
import kiea.proj.sdc.anal.macro.impl.io.AbstractOracleReader;
import kiea.proj.sdc.anal.macro.sample.a03.bean.Sample03ReadBean;
import oracle.jdbc.OracleConnection;

/**
 * @author KangSeok
 * @date 2014. 8. 7.
 * @file_name Sample03OracleReader.java
 *
 */
public class Sample03OracleReader extends AbstractOracleReader
{
	private List<Sample03ReadBean> list = new ArrayList<Sample03ReadBean>();
	
	/**
	 * Sample02OracleReader
	 */
	public Sample03OracleReader() {}

	/**
	 * getList
	 */
	public List<Sample03ReadBean> getList()
	{
		if (Flag.TRUE) {
			try {
				String query;
				
				if (!Flag.TRUE) {
					query = String.format("" +
							"SELECT                                     " +
							"    A.JOB_ID                               " +
							"    , A.ANAL_EVENT_TYPE                    " +
							"    , A.LINE_ID                            " +
							"    , A.INSPECT_AREA                       " +
							"    , A.INSPECT_STEP                       " +
							"    , A.FROM_DATE                          " +
							"    , A.TO_DATE                            " +
							"    , C.PRODUCT_GRP                        " +
							"    , C.PRODUCT_TYPE                       " +
							"    , B.FAIL_CD                            " +
							"    , B.DECISION_CD                        " +
							"FROM                                       " +
							"    YMS_LYA.SYMS_LYA_JOB_MASTER A          " +  // <--
							"    , YMS_LYA.SYMS_LYA_JOB_FAIL_CODE B     " +  // <--
							"    , YMS_LYA.SYMS_LYA_JOB_PRODUCT_GRP C   " +  // <--
							"WHERE 1=1                                  " +
							"    AND A.JOB_ID IN ( %s )                 " +
							"    AND A.JOB_ID = B.JOB_ID                " +
							"    AND A.JOB_ID = C.JOB_ID                " +
							""
							, "'LYAAU1408043181','LYAAU1408043178','LYAAU1408043120','LYAAU1408043128','LYAAU1408043080','LYAAU1408043171'");
				} else {
					query = String.format("" +
							"SELECT                                     " +
							"    A.JOB_ID                               " +
							"    , A.ANAL_EVENT_TYPE                    " +
							"    , A.LINE_ID                            " +
							"    , A.INSPECT_AREA                       " +
							"    , A.INSPECT_STEP                       " +
							"    , A.FROM_DATE                          " +
							"    , A.TO_DATE                            " +
							"    , C.PRODUCT_GRP                        " +
							"    , C.PRODUCT_TYPE                       " +
							"    , B.FAIL_CD                            " +
							"    , B.DECISION_CD                        " +
							"FROM                                       " +
							"    SYMS_LYA_JOB_MASTER A                  " +  // <--
							"    , SYMS_LYA_JOB_FAIL_CODE B             " +  // <--
							"    , SYMS_LYA_JOB_PRODUCT_GRP C           " +  // <--
							"WHERE 1=1                                  " +
							"    AND A.JOB_ID IN ( %s )                 " +
							"    AND A.JOB_ID = B.JOB_ID                " +
							"    AND A.JOB_ID = C.JOB_ID                " +
							""
							, "'LYAAU1408045001','LYAAU1408045002','LYAAU1408045003','LYAAU1408045004','LYAAU1408045005','LYAAU1408045006'");
				}
				
				OracleConnection conn = Connection.getOracleConnection();

				if (Flag.TRUE) {
					Statement stmt = conn.createStatement();
					ResultSet rs = stmt.executeQuery(query);
					for (int i=0; i < 10000 && rs.next(); i++) {
						if (!Flag.TRUE) {
							System.out.println(String.format("%3d) --------------------------------------", i));
							System.out.println(String.format("    JOB_ID          = [%s]", rs.getString("JOB_ID"         )));
							System.out.println(String.format("    ANAL_EVENT_TYPE = [%s]", rs.getString("ANAL_EVENT_TYPE")));
							System.out.println(String.format("    LINE_ID         = [%s]", rs.getString("LINE_ID"        )));
							System.out.println(String.format("    INSPECT_AREA    = [%s]", rs.getString("INSPECT_AREA"   )));
							System.out.println(String.format("    INSPECT_STEP    = [%s]", rs.getString("INSPECT_STEP"   )));
							System.out.println(String.format("    FROM_DATE       = [%s]", rs.getString("FROM_DATE"      )));
							System.out.println(String.format("    TO_DATE         = [%s]", rs.getString("TO_DATE"        )));
							System.out.println(String.format("    PRODUCT_GRP     = [%s]", rs.getString("PRODUCT_GRP"    )));
							System.out.println(String.format("    PRODUCT_TYPE    = [%s]", rs.getString("PRODUCT_TYPE"   )));
							System.out.println(String.format("    FAIL_CD         = [%s]", rs.getString("FAIL_CD"        )));
							System.out.println(String.format("    DECISION_CD     = [%s]", rs.getString("DECISION_CD"    )));
						}
						
						Sample03ReadBean bean = new Sample03ReadBean();

						bean.setJobId        (rs.getString("JOB_ID"         ));
						bean.setAnalEventType(rs.getString("ANAL_EVENT_TYPE"));
						bean.setLineId       (rs.getString("LINE_ID"        ));
						bean.setInspectArea  (rs.getString("INSPECT_AREA"   ));
						bean.setInspectStep  (rs.getString("INSPECT_STEP"   ));
						bean.setFromDate     (rs.getString("FROM_DATE"      ));
						bean.setToDate       (rs.getString("TO_DATE"        ));
						bean.setProductGrp   (rs.getString("PRODUCT_GRP"    ));
						bean.setProductType  (rs.getString("PRODUCT_TYPE"   ));
						bean.setFailCd       (rs.getString("FAIL_CD"        ));
						bean.setDecisionCd   (rs.getString("DECISION_CD"    ));
						
						list.add(bean);
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
			Sample03OracleReader reader = new Sample03OracleReader();
			reader.getList();
		}
	}
	
	public static void main(String[] args)
	{
		if (Flag.TRUE) test01();
	}
}
