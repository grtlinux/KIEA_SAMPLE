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

package sdc.anal.mura.macro.A12.RP_MUR_TREND_SUMM.v04;

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
 * @file_name BUBBLE_INDEX_OracleReader.java
 *
 */
public class RP_MUR_TREND_SUMM_OracleReader extends AbstractOracleReader
{
	private List<RP_MUR_TREND_SUMM_ReadBean> list = new ArrayList<RP_MUR_TREND_SUMM_ReadBean>();
	
	/**
	 * Sample02OracleReader
	 */
	public RP_MUR_TREND_SUMM_OracleReader()
	{
		if (Flag.TRUE) {
		}
	}

	/**
	 * 
	 * getQuery
	 *
	 * @return
	 */
	private String getQuery()
	{
		String query = null;
		
		if (!Flag.TRUE) {
			/*
			 * OLD : 2014.12.27 : 아래 쿼리로 변경됨.
			 */
			query = String.format("\n"
					+ "SELECT                                                                               \n"
					+ "    TO_CHAR(SORT_TIME, 'YYYY-MM-DD')||'T'||TO_CHAR(SORT_TIME, 'HH24') AS D_TIME      \n"
					+ "    , ROUND(SAMP_SIZE) AS TOTCNT                                                     \n"
					+ "    , 0 AS MURA                                                                      \n"
					+ "    , AVG AS Y_AXIS                                                                  \n"
					+ "FROM                                                                                 \n"
					+ "    YMS_SPC.I_RSLT                                                                   \n"
					+ "WHERE 1 = 1                                                                          \n"
					+ "    AND SITEID = %s                                                                  \n"  //   LINE
					+ "    AND INDEX_TYPE = 'MURA'                                                          \n"
					+ "    AND INDEX_ID = 'IM_ALGO'                                                         \n"
					+ "    AND PRM_ID = (                                                                   \n"
					+ "        SELECT                                                                       \n"
					+ "            SUBSTR(CLSEVENTID,INSTR(CLSEVENTID,'_',1)+1,INSTR(CLSEVENTID,'_',1,3)-1) \n"
					+ "        FROM                                                                         \n"
					+ "            SYMS_LYA_JOB_MASTER                                                      \n"
					+ "        WHERE 1=1                                                                    \n"
					+ "            AND JOB_ID = %s                                                          \n"  // auto_job_id
					+ "    )                                                                                \n"
					+ "    AND SORT_TIME > TO_DATE(%s, 'YYYYMMDDHH24MISS')                                  \n"  // FROM_ETL_DT
					+ "    AND SORT_TIME <= TO_DATE(%s, 'YYYYMMDDHH24MISS') + 1/24                          \n"  // TO_ETL_DT
					+ "ORDER BY                                                                             \n"
					+ "    SORT_TIME ASC                                                                    \n"
					+ ""
					, StrUtil.quote(Parameter.getInstance().getStrLine())
					, StrUtil.quote(Parameter.getInstance().getStrJobId())
					, StrUtil.quote(Parameter.getInstance().getStrFromDateTime())
					, StrUtil.quote(Parameter.getInstance().getStrToDateTime())
			);
		}

		if (Flag.TRUE) {
			query = String.format("\n"
					+ "SELECT                                                                           \n"
					+ "    TO_CHAR(SORT_TIME, 'YYYY-MM-DD')||' '||TO_CHAR(SORT_TIME, 'HH24') AS D_TIME  \n"
					+ "    , ROUND(SAMP_SIZE) AS TOTCNT                                                 \n"
					+ "    , 0 AS MURA                                                                  \n"
					+ "    , AVG AS Y_AXIS                                                              \n"
					+ "FROM                                                                             \n"
					+ "    YMS_SPC.I_RSLT                                                               \n"
					+ "WHERE 1 = 1                                                                      \n"
					+ "    AND SITEID = %s                                                              \n"
					+ "    AND INDEX_TYPE = 'MURA'                                                      \n"
					+ "    AND INDEX_ID = 'IM_ALGO'                                                     \n"
					+ "    AND PRM_ID = (                                                               \n"
					+ "        SELECT                                                                   \n"
					+ "            SUBSTR(T1.CLSEVENTID, INSTR(T1.CLSEVENTID, '_')+1, 19)               \n"
					+ "        FROM                                                                     \n"
					+ "            SYMS_LYA_JOB_MASTER     T1                                           \n"
					+ "        WHERE 1=1                                                                \n"
					+ "            AND T1.JOB_ID = %s                                                   \n"  // <- TOBE : 2014.12.27
					+ "            --AND T1.JOB_ID = 'AR020141227A3064'                                 \n"
					+ "            --AND T1.JOB_ID = 'LYAAU1412273064'                                  \n"
					+ "            AND T1.USER_ID = 'MURA_SPC'                                          \n"
					+ "    )                                                                            \n"
					+ "    AND SORT_TIME > TO_DATE(%s, 'YYYYMMDDHH24MISS')                              \n"
					+ "    AND SORT_TIME <= TO_DATE(%s, 'YYYYMMDDHH24MISS') + 1/24                      \n"
					+ "ORDER BY SORT_TIME ASC                                                           \n"
					+ ""
					, StrUtil.quote(Parameter.getInstance().getStrLine())
					, StrUtil.quote(Parameter.getInstance().getStrJobId())
					, StrUtil.quote(Parameter.getInstance().getStrFromDateTime())
					, StrUtil.quote(Parameter.getInstance().getStrToDateTime())
			);
		}

		return query;
	}
	
	/**
	 * getList
	 */
	@Override
	public List<RP_MUR_TREND_SUMM_ReadBean> getList()
	{
		return getList(false);
	}
	
	public List<RP_MUR_TREND_SUMM_ReadBean> getList(boolean flgRead)
	{
		if (Flag.TRUE) {
			if (!flgRead) {
				/*
				 * if flgRead = true, 초기에 읽지않는다.
				 */
				list = new ArrayList<RP_MUR_TREND_SUMM_ReadBean>();
				return list;
			}

			try {
				String query = null;
				
				query = getQuery();
				
				if (!Flag.TRUE) System.out.println("[" + query + "]");
				
				OracleConnection conn = Connection04.getInstance().getConn();

				if (Flag.TRUE) {
					Statement stmt = conn.createStatement();
					ResultSet rs = stmt.executeQuery(query);
					for (int i=0; i < 1000000 && rs.next(); i++) {
						if (!Flag.TRUE) {
							System.out.println(String.format("%3d) --------------------------------------", i));
							System.out.println(String.format("    D_TIME = [%s]", rs.getString("D_TIME")));
							System.out.println(String.format("    TOTCNT = [%s]", rs.getString("TOTCNT")));
							System.out.println(String.format("    MURA   = [%s]", rs.getString("MURA"  )));
							System.out.println(String.format("    Y_AXIS = [%s]", rs.getString("Y_AXIS")));
						}
						
						if (Flag.TRUE) {
							RP_MUR_TREND_SUMM_ReadBean bean = new RP_MUR_TREND_SUMM_ReadBean();

							bean.setDTime (rs.getString("D_TIME"));
							bean.setTotCnt(rs.getString("TOTCNT"));
							bean.setMura  (rs.getString("MURA"  ));
							bean.setYAxis (rs.getString("Y_AXIS"));
							
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
				
				try { Thread.sleep(1000); } catch (InterruptedException ex) {};
				
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
			SystemProperties.setTesting("020");
			BasePath.getInstance();
			try {
				Logger.getInstance(RP_MUR_TREND_SUMM_Main.jobId, RP_MUR_TREND_SUMM_Main.dsName + ".log");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		if (Flag.TRUE) {
			Parameter.getInstance(RP_MUR_TREND_SUMM_Main.jobId);
			
			RP_MUR_TREND_SUMM_OracleReader reader = new RP_MUR_TREND_SUMM_OracleReader();
			
			for (RP_MUR_TREND_SUMM_ReadBean bean : reader.getList(true)) {
				System.out.println(bean);
			}
			
			System.out.println("the read ok !!!");
		}
	}
	
	public static void main(String[] args)
	{
		if (Flag.TRUE) test01();
	}
}
