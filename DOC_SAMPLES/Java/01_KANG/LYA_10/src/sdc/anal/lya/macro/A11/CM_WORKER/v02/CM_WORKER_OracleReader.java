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

package sdc.anal.lya.macro.A11.CM_WORKER.v02;

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
import kiea.proj.sdc.anal.util.log.v02.Logger;
import oracle.jdbc.OracleConnection;

/**
 * @author KangSeok
 * @date 2014. 8. 8.
 * @file_name AIB_CM_WORKER_OracleReader.java
 *
 */
public class CM_WORKER_OracleReader extends AbstractOracleReader
{
	private List<CM_WORKER_ReadBean> list = new ArrayList<CM_WORKER_ReadBean>();
	
	/**
	 * Sample02OracleReader
	 */
	public CM_WORKER_OracleReader()
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
		
		query = String.format("" +
				"SELECT                   \n" +
				"    WORKERID     ,       \n" +
				"    WORKERNAME   ,       \n" +
				"    DEPTCD       ,       \n" +
				"    DEPTNAME     ,       \n" +
				"    ETLMODIFYDATE,       \n" +
				"    POSITIONCD   ,       \n" +
				"    POSITIONNAME ,       \n" +
				"    EMAIL        ,       \n" +
				"    STATUS       ,       \n" +
				"    SOURCESITEID ,       \n" +
				"    INDEPT       ,       \n" +
				"    JIKCHAK      ,       \n" +
				"    DUTY_NM      ,       \n" +
				"    LOCATE       ,       \n" +
				"    EP_ID        ,       \n" +
				"    UNIQUE_ID    ,       \n" +
				"    ENAME                \n" +
				"FROM                     \n" +
				"    YMS_DW.DW_MD_WORKER  \n" +
				""
				);

		return query;
	}

	/**
	 * getList
	 */
	@Override
	public List<CM_WORKER_ReadBean> getList()
	{
		return getList(false);
	}
	
	public List<CM_WORKER_ReadBean> getList(boolean flgRead)
	{
		if (Flag.TRUE) {
			if (!flgRead) {
				/*
				 * if flgRead = true, 초기에 읽지않는다.
				 */
				list = new ArrayList<CM_WORKER_ReadBean>();
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
							System.out.println(String.format("    WORKERID      = [%s]", rs.getString("WORKERID"     )));
							System.out.println(String.format("    WORKERNAME    = [%s]", rs.getString("WORKERNAME"   )));
							System.out.println(String.format("    DEPTCD        = [%s]", rs.getString("DEPTCD"       )));
							System.out.println(String.format("    DEPTNAME      = [%s]", rs.getString("DEPTNAME"     )));
							System.out.println(String.format("    ETLMODIFYDATE = [%s]", rs.getString("ETLMODIFYDATE")));
							System.out.println(String.format("    POSITIONCD    = [%s]", rs.getString("POSITIONCD"   )));
							System.out.println(String.format("    POSITIONNAME  = [%s]", rs.getString("POSITIONNAME" )));
							System.out.println(String.format("    EMAIL         = [%s]", rs.getString("EMAIL"        )));
							System.out.println(String.format("    STATUS        = [%s]", rs.getString("STATUS"       )));
							System.out.println(String.format("    SOURCESITEID  = [%s]", rs.getString("SOURCESITEID" )));
							System.out.println(String.format("    INDEPT        = [%s]", rs.getString("INDEPT"       )));
							System.out.println(String.format("    JIKCHAK       = [%s]", rs.getString("JIKCHAK"      )));
							System.out.println(String.format("    DUTY_NM       = [%s]", rs.getString("DUTY_NM"      )));
							System.out.println(String.format("    LOCATE        = [%s]", rs.getString("LOCATE"       )));
							System.out.println(String.format("    EP_ID         = [%s]", rs.getString("EP_ID"        )));
							System.out.println(String.format("    UNIQUE_ID     = [%s]", rs.getString("UNIQUE_ID"    )));
							System.out.println(String.format("    ENAME         = [%s]", rs.getString("ENAME"        )));
						}
						
						if (Flag.TRUE) {
							CM_WORKER_ReadBean bean = new CM_WORKER_ReadBean();

							bean.setWorkerId     (rs.getString("WORKERID"     ));
							bean.setWorkerName   (rs.getString("WORKERNAME"   ));
							bean.setDeptCd       (rs.getString("DEPTCD"       ));
							bean.setDeptName     (rs.getString("DEPTNAME"     ));
							bean.setEtlModifyDate(rs.getString("ETLMODIFYDATE"));
							bean.setPositionCd   (rs.getString("POSITIONCD"   ));
							bean.setPositionName (rs.getString("POSITIONNAME" ));
							bean.setEmail        (rs.getString("EMAIL"        ));
							bean.setStatus       (rs.getString("STATUS"       ));
							bean.setSourceSiteId (rs.getString("SOURCESITEID" ));
							bean.setIndept       (rs.getString("INDEPT"       ));
							bean.setJikchak      (rs.getString("JIKCHAK"      ));
							bean.setDutyNm       (rs.getString("DUTY_NM"      ));
							bean.setLocate       (rs.getString("LOCATE"       ));
							bean.setEpId         (rs.getString("EP_ID"        ));
							bean.setUniqueId     (rs.getString("UNIQUE_ID"    ));
							bean.setEname        (rs.getString("ENAME"        ));
							
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
				Logger.getInstance(CM_WORKER_Main.jobId, CM_WORKER_Main.dsName + ".log");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		if (Flag.TRUE) {
			Parameter.getInstance(CM_WORKER_Main.jobId);
			CM_WORKER_OracleReader reader = new CM_WORKER_OracleReader();
			
			for (CM_WORKER_ReadBean bean : reader.getList(true)) {
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
