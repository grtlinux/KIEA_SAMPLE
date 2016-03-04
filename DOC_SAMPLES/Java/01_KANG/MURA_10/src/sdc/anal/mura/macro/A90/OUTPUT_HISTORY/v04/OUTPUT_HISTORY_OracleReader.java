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

package sdc.anal.mura.macro.A90.OUTPUT_HISTORY.v04;

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
public class OUTPUT_HISTORY_OracleReader extends AbstractOracleReader
{
	private List<OUTPUT_HISTORY_ReadBean> list = new ArrayList<OUTPUT_HISTORY_ReadBean>();
	
	/**
	 * Sample02OracleReader
	 */
	public OUTPUT_HISTORY_OracleReader()
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
		
		query = String.format("\n"
				+ "SELECT                                \n"
				+ "    *                                 \n"
				+ "FROM                                  \n"
				+ "    anal_csv                          \n"
				+ "WHERE 1=1                             \n"
				+ "    AND job_id = %s                   \n"
				+ "ORDER BY                              \n"
				+ "    job_id DESC                       \n"
				+ "    , s_time                          \n"
				+ "    , e_time DESC                     \n"
				+ ""
				, StrUtil.quote(Parameter.getInstance().getStrJobId())
				);

		return query;
	}
	
	/**
	 * getList
	 */
	@Override
	public List<OUTPUT_HISTORY_ReadBean> getList()
	{
		return getList(false);
	}
	
	public List<OUTPUT_HISTORY_ReadBean> getList(boolean flgRead)
	{
		if (Flag.TRUE) {
			if (!flgRead) {
				/*
				 * if flgRead = true, 초기에 읽지않는다.
				 */
				list = new ArrayList<OUTPUT_HISTORY_ReadBean>();
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
							System.out.println(String.format("    JOB_ID      = [%s]", rs.getString("JOB_ID"     )));
							System.out.println(String.format("    CMD_CODE    = [%s]", rs.getString("CMD_CODE"   )));
							System.out.println(String.format("    PROG_NM     = [%s]", rs.getString("PROG_NM"    )));
							System.out.println(String.format("    CSV_NM      = [%s]", rs.getString("CSV_NM"     )));
							System.out.println(String.format("    MAIN_CLASS  = [%s]", rs.getString("MAIN_CLASS" )));
							System.out.println(String.format("    LIST_CNT    = [%s]", rs.getString("LIST_CNT"   )));
							System.out.println(String.format("    S_TIME      = [%s]", rs.getString("S_TIME"     )));
							System.out.println(String.format("    E_TIME      = [%s]", rs.getString("E_TIME"     )));
							System.out.println(String.format("    R_MSEC      = [%s]", rs.getString("R_MSEC"     )));
							System.out.println(String.format("    CSV_STATUS  = [%s]", rs.getString("CSV_STATUS" )));
							System.out.println(String.format("    LOG_MESSAGE = [%s]", rs.getString("LOG_MESSAGE")));
						}
						
						if (Flag.TRUE) {
							OUTPUT_HISTORY_ReadBean bean = new OUTPUT_HISTORY_ReadBean();

							bean.setJobId     (rs.getString("JOB_ID"     ));
							bean.setCmdCode   (rs.getString("CMD_CODE"   ));
							bean.setProgNm    (rs.getString("PROG_NM"    ));
							bean.setCsvNm     (rs.getString("CSV_NM"     ));
							bean.setMainClass (rs.getString("MAIN_CLASS" ));
							bean.setListCnt   (rs.getString("LIST_CNT"   ));
							bean.setSTime     (rs.getString("S_TIME"     ));
							bean.setETime     (rs.getString("E_TIME"     ));
							bean.setRMsec     (rs.getString("R_MSEC"     ));
							bean.setCsvStatus (rs.getString("CSV_STATUS" ));
							bean.setLogMessage(rs.getString("LOG_MESSAGE"));
							
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
				
				Connection04.getInstance().close();;
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
				Logger.getInstance(OUTPUT_HISTORY_Main.jobId, OUTPUT_HISTORY_Main.dsName + ".log");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		if (Flag.TRUE) {
			Parameter.getInstance(OUTPUT_HISTORY_Main.jobId);
			
			OUTPUT_HISTORY_OracleReader reader = new OUTPUT_HISTORY_OracleReader();
			
			for (OUTPUT_HISTORY_ReadBean bean : reader.getList(true)) {
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
