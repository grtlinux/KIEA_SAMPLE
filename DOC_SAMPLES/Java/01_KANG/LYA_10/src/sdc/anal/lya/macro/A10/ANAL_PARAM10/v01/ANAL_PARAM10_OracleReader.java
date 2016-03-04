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

package sdc.anal.lya.macro.A10.ANAL_PARAM10.v01;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import kiea.kr.co.tain.base.Flag;
import kiea.kr.co.tain.util.SystemProperties;
import kiea.proj.sdc.anal.common.Connection04;
import kiea.proj.sdc.anal.common.Parameter;
import kiea.proj.sdc.anal.macro.impl.io.AbstractOracleReader;
import kiea.proj.sdc.anal.util.BasePath;
import kiea.proj.sdc.anal.util.StrUtil;
import kiea.proj.sdc.anal.util.log.v02.Logger;
import oracle.jdbc.OracleConnection;

/**
 * @author KangSeok
 * @date 2014. 8. 8.
 * @file_name ANAL_INDEX_OracleReader.java
 *
 */
public class ANAL_PARAM10_OracleReader extends AbstractOracleReader
{
	private List<ANAL_PARAM10_ReadBean> list = new ArrayList<ANAL_PARAM10_ReadBean>();
	
	/**
	 * Sample02OracleReader
	 */
	public ANAL_PARAM10_OracleReader()
	{
		if (Flag.TRUE) {
		}
	}

	/**
	 * 
	 * getLine56LC
	 *
	 * @return
	 */
	private String getQuery()
	{
		String query = null;
		
		query = String.format(""
				+ "WITH TBL AS (                            \n"
				+ "    SELECT                               \n"
				+ "        job_id                           \n"
				+ "        , seq                            \n"
				+ "        , param_nm                       \n"
				+ "        , param_val                      \n"
				+ "        , reg_dt                         \n"
				+ "    FROM                                 \n"
				+ "        anal_param                       \n"
				+ "    WHERE 1=1                            \n"
				+ "        AND job_id = %s                  \n"
				+ "    ORDER BY                             \n"
				+ "        seq                              \n"
				+ ")                                        \n"
				+ "SELECT                                   \n"
				+ "    job_id                               \n"
				+ "    , seq                                \n"
				+ "    , param_nm                           \n"
				+ "    , param_val                          \n"
				+ "    , reg_dt                             \n"
				+ "FROM                                     \n"
				+ "    TBL                                  \n"
				+ "WHERE 1=1                                \n"
				//+ "    AND ROWNUM <= 50                      \n"
				+ ""
				, StrUtil.quote(Parameter.getInstance().getStrJobId())
				);

		return query;
	}
	
	/**
	 * getList
	 */
	@Override
	public List<ANAL_PARAM10_ReadBean> getList()
	{
		return getList(false);
	}
	
	public List<ANAL_PARAM10_ReadBean> getList(boolean flgRead)
	{
		if (Flag.TRUE) {
			if (!flgRead) {
				/*
				 * if flgRead = true, 초기에 읽지않는다.
				 */
				list = new ArrayList<ANAL_PARAM10_ReadBean>();
				return list;
			}
			
			try {
				String query = getQuery();
				if (!Flag.TRUE) System.out.println("[" + query + "]");
				if (Flag.TRUE) Logger.info("[" + query + "]");
				
				OracleConnection conn = Connection04.getInstance().getConn();

				if (Flag.TRUE) {
					Statement stmt = conn.createStatement();
					ResultSet rs = stmt.executeQuery(query);
					for (int i=0; i < 1000000 && rs.next(); i++) {
						if (!Flag.TRUE) {
							System.out.println(String.format("%3d) --------------------------------------", i));
							System.out.println(String.format("    JOB_ID    = [%s]", rs.getString("JOB_ID"   )));
							System.out.println(String.format("    SEQ       = [%s]", rs.getString("SEQ"      )));
							System.out.println(String.format("    PARAM_NM  = [%s]", rs.getString("PARAM_NM" )));
							System.out.println(String.format("    PARAM_VAL = [%s]", rs.getString("PARAM_VAL")));
							System.out.println(String.format("    REG_DT    = [%s]", rs.getString("REG_DT"   )));
						}
						
						if (Flag.TRUE) {
							StringBuffer sb = new StringBuffer();
							sb.append(String.format("%3d) --------------------------------------\n", i));
							sb.append(String.format("    JOB_ID    = [%s]\n", rs.getString("JOB_ID"   )));
							sb.append(String.format("    SEQ       = [%s]\n", rs.getString("SEQ"      )));
							sb.append(String.format("    PARAM_NM  = [%s]\n", rs.getString("PARAM_NM" )));
							sb.append(String.format("    PARAM_VAL = [%s]\n", rs.getString("PARAM_VAL")));
							sb.append(String.format("    REG_DT    = [%s]\n", rs.getString("REG_DT"   )));
							Logger.info(sb.toString());
						}

						if (Flag.TRUE) {
							ANAL_PARAM10_ReadBean bean = new ANAL_PARAM10_ReadBean();

							bean.setJobId   (rs.getString("JOB_ID"   ));
							bean.setSeq     (rs.getString("SEQ"      ));
							bean.setParamNm (rs.getString("PARAM_NM" ));
							bean.setParamVal(rs.getString("PARAM_VAL"));
							bean.setRegDt   (rs.getString("REG_DT"   ));
							
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
				
				Connection04.getInstance().close();
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
			SystemProperties.setTesting("010");
			BasePath.getInstance();
			try {
				Logger.getInstance(ANAL_PARAM10_Main.jobId, ANAL_PARAM10_Main.dsName + ".log");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		if (Flag.TRUE) {
			Parameter.getInstance(ANAL_PARAM10_Main.jobId);
			
			ANAL_PARAM10_OracleReader reader = new ANAL_PARAM10_OracleReader();
			
			for (ANAL_PARAM10_ReadBean bean : reader.getList(true)) {
				System.out.println(bean);
			}
			
			System.out.println("the read ok !!!");
		}
		
		Logger.exit();
	}
	
	public static void main(String[] args)
	{
		if (Flag.TRUE) test01();
	}
}
