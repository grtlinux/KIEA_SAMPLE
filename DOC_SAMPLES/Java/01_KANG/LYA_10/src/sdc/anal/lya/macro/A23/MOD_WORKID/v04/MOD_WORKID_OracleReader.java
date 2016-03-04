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

package sdc.anal.lya.macro.A23.MOD_WORKID.v04;

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
 * @file_name MOD_WORKID_OracleReader.java
 *
 */
public class MOD_WORKID_OracleReader extends AbstractOracleReader
{
	private List<MOD_WORKID_ReadBean> list = new ArrayList<MOD_WORKID_ReadBean>();
	
	/**
	 * Sample02OracleReader
	 */
	public MOD_WORKID_OracleReader()
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
	            //"    AND ROWNUM <= 100                                            \n" +
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

		return query;
	}

	/**
	 * getList
	 */
	@Override
	public List<MOD_WORKID_ReadBean> getList()
	{
		return getList(false);
	}
	
	public List<MOD_WORKID_ReadBean> getList(boolean flgRead)
	{
		if (Flag.TRUE) {
			if (!flgRead) {
				/*
				 * if flgRead = true, 초기에 읽지않는다.
				 */
				list = new ArrayList<MOD_WORKID_ReadBean>();
				return list;
			}
			
			try {
				String query = null;
				
				if ("MOD".equals(Parameter.getInstance().getStrAreaCode())) {
					query = getLineMOD();
				} else {
					return list;
				}
				
				if (!Flag.TRUE) System.out.println("[" + query + "]");
				
				OracleConnection conn = Connection04.getInstance().getConn();

				if (Flag.TRUE) {
					Statement stmt = conn.createStatement();
					ResultSet rs = stmt.executeQuery(query);
					for (int i=0; i < 100000 && rs.next(); i++) {                     // TODO : 2014.08.26 : 최대건수 처리
						if (!Flag.TRUE) {
							System.out.println(String.format("%3d) --------------------------------------", i));
							System.out.println(String.format("    INSPSTEPTYPE = [%s]", rs.getString("INSPSTEPTYPE")));
							System.out.println(String.format("    INSPEQPID    = [%s]", rs.getString("INSPEQPID"   )));
							System.out.println(String.format("    WORKERID     = [%s]", rs.getString("WORKERID"    )));
							System.out.println(String.format("    TOT          = [%s]", rs.getString("TOT"         )));
							System.out.println(String.format("    BAD          = [%s]", rs.getString("BAD"         )));
						}
						
						if (Flag.TRUE) {
							MOD_WORKID_ReadBean bean = new MOD_WORKID_ReadBean();

							bean.setInspStepType(rs.getString("INSPSTEPTYPE"));
							bean.setInspEqpId   (rs.getString("INSPEQPID"   ));
							bean.setWorkerId    (rs.getString("WORKERID"    ));
							bean.setTot         (rs.getString("TOT"         ));
							bean.setBad         (rs.getString("BAD"         ));
							
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
				Logger.getInstance(MOD_WORKID_Main.jobId, MOD_WORKID_Main.dsName + ".log");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		if (Flag.TRUE) {
			Parameter.getInstance(MOD_WORKID_Main.jobId);
			
			MOD_WORKID_OracleReader reader = new MOD_WORKID_OracleReader();
			
			for (MOD_WORKID_ReadBean bean : reader.getList(true)) {
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
