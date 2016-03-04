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

package kiea.proj.sdc.anal.base.mura.manager.v03;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import kiea.kr.co.tain.base.Flag;
import kiea.kr.co.tain.util.Print;
import kiea.proj.sdc.anal.common.Connection;
import kiea.proj.sdc.anal.util.ServiceManagerProperties;
import kiea.proj.sdc.anal.util.StrUtil;
import oracle.jdbc.OracleConnection;

/**
 * @author KangSeok
 * @date 2014. 9. 16.
 * @file_name HandleAnalJobId.java
 *
 */
public class HandleAnalJobId
{
	/*
	 * 동시에 실행하여 처리할 수 있는 최대 JOB_ID의 갯수
	 */
	private static int nThreadJobMax = 1;
	
	/**
	 * 
	 * getReadyJobId
	 *
	 * @return
	 */
	public static List<String> getReadyJobId(String strThreadJobMax)
	{
		ServiceManagerProperties prop = ServiceManagerProperties.getInstance();
		String svcCode = prop.get("anal.service.code");

		nThreadJobMax = Integer.parseInt(strThreadJobMax);
				
		List<String> list = new ArrayList<String>();
		
		if (Flag.TRUE) {
			/*
			 * JOB_ID의 목록을 얻는다.
			 */
			try {
				
				OracleConnection conn = Connection.getOracleConnection();
				Statement stmt = conn.createStatement();

				String query = String.format(""
						+ "SELECT                          \n"
						+ "    *                           \n"
						+ "FROM                            \n"
						+ "    anal_jobid                  \n"
						+ "WHERE 1=1                       \n"
						+ "    AND job_id like '_R%s%%'    \n"
						+ "    AND status = 'READY'        \n"
						+ "ORDER BY                        \n"
						+ "    job_id                      \n"
						+ ""
						, svcCode
						);
				
				ResultSet resultSet = stmt.executeQuery(query);

				if (Flag.TRUE) {
					/*
					 * row data
					 */
					for (int i=0; i < nThreadJobMax && resultSet.next(); i++) {
						
						list.add(resultSet.getString("JOB_ID"));
					}
				}
				
				conn.close();
				if (!Flag.TRUE) System.out.println("##### OK!");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		if (!Flag.TRUE) {
			/*
			 * JOB_ID의 목록을 확인한다.
			 */
			int idx=0;
			for (String jobId : list) {
				Print.println("(%d) JOB_ID=[%s]", ++idx, jobId);
			}
		}
		
		return list;
	}
	
	/**
	 * 
	 * setRunningStatus
	 *
	 * @param JobId
	 */
	public static void setRunningStatus(String JobId)
	{
		if (Flag.TRUE) {
			/*
			 * STATUS -> RUNNING으로 UPDATE
			 */
			try {
				
				OracleConnection conn = Connection.getOracleConnection();
				Statement stmt = conn.createStatement();

				String query = String.format("UPDATE anal_jobid SET status = 'RUNNING' WHERE job_id=%s ", StrUtil.quote(JobId));
				
				int cnt = stmt.executeUpdate(query);
				if (cnt != 1)
					if (!Flag.TRUE) System.out.println("ERROR : not exist");
				
				conn.close();
				if (!Flag.TRUE) System.out.println("##### OK!");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * 
	 * setFinishStatus
	 *
	 * @param JobId
	 */
	public static void setFinishStatus(String JobId)
	{
		if (Flag.TRUE) {
			/*
			 * STATUS -> FINSISH으로 UPDATE
			 */
			try {
				
				OracleConnection conn = Connection.getOracleConnection();
				Statement stmt = conn.createStatement();

				String query = String.format("UPDATE anal_jobid SET status = 'FINISH' WHERE job_id=%s ", StrUtil.quote(JobId));
				
				int cnt = stmt.executeUpdate(query);
				if (cnt != 1)
					if (!Flag.TRUE) System.out.println("ERROR : not exist");
				
				conn.close();
				if (!Flag.TRUE) System.out.println("##### OK!");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * 
	 * setErrorStatus
	 *
	 * @param JobId
	 */
	public static void setErrorStatus(String JobId)
	{
		if (Flag.TRUE) {
			/*
			 * STATUS -> ERROR으로 UPDATE
			 */
			try {
				
				OracleConnection conn = Connection.getOracleConnection();
				Statement stmt = conn.createStatement();

				String query = String.format("UPDATE anal_jobid SET status = 'ERROR' WHERE job_id=%s ", StrUtil.quote(JobId));
				
				int cnt = stmt.executeUpdate(query);
				if (cnt != 1)
					if (!Flag.TRUE) System.out.println("ERROR : not exist");
				
				conn.close();
				if (!Flag.TRUE) System.out.println("##### OK!");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	///////////////////////////////////////////////////////////////////////////
	
	private static void test01()
	{
		if (Flag.TRUE) {
			List<String> list = getReadyJobId("5");
			
			for (String jobId : list) {
				System.out.println("[" + jobId + "]");
			}
		}
	}
	
	public static void main(String[] args)
	{
		if (Flag.TRUE) test01();
	}
}
