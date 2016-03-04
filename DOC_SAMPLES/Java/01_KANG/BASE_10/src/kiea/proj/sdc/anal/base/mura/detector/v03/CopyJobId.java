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

package kiea.proj.sdc.anal.base.mura.detector.v03;

import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import kiea.kr.co.tain.base.Flag;
import kiea.kr.co.tain.util.DateTime;
import kiea.kr.co.tain.util.Print;
import kiea.proj.sdc.anal.common.Connection;
import kiea.proj.sdc.anal.util.StrUtil;
import oracle.jdbc.OracleConnection;


/**
 * @author KangSeok
 * @date 2014. 9. 16.
 * @file_name CopyJobid.java
 *
 */
public class CopyJobId implements JobIdImpl
{
	private final static String ROWNUM = "10";

	private static final int DEFAULT_COPY_COUNT = 10;
	
	@SuppressWarnings("unused")
	private int copyCount;
	
	private OracleConnection conn = null;
	private Statement stmt = null;

	///////////////////////////////////////////////////////////////////////////
	
	public JobIdInfo get()
	{
		return get(DEFAULT_COPY_COUNT);
	}

	public JobIdInfo get(int no)
	{
		return null;
	}
	
	///////////////////////////////////////////////////////////////////////////

	public CopyJobId(int copyCount)
	{
		this.copyCount = copyCount;
		
		try {
			
			conn = Connection.getOracleConnection();
			stmt = conn.createStatement();

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public CopyJobId()
	{
		this(DEFAULT_COPY_COUNT);
	}
	
	///////////////////////////////////////////////////////////////////////////

	private String getMaxQafJobId()
	{
		String maxQafJobId = null;
		
		if (Flag.TRUE) {
			try {
				
				String query;
				query = String.format("\n"
						+ "SELECT                            \n"
						+ "    job_id                        \n"
						+ "FROM                              \n"
						+ "    anal_jobid                    \n"
						+ "WHERE                             \n"
						+ "    ROWNUM <= 1                   \n"
						+ "    AND job_id like 'AR010%sA%%'  \n"
						+ "ORDER BY                          \n"
						+ "    job_id DESC                   \n"
						, DateTime.getDate(6)
						);
				if (!Flag.TRUE) System.out.println("[" + query + "]");
				
				if (Flag.TRUE) {
					String jobId, qafJobId;
					
					ResultSet rs = stmt.executeQuery(query);
					if (rs.next()) {
						jobId = rs.getString("JOB_ID");
						qafJobId = "LYAAU" + jobId.substring(5, 11) + jobId.substring(12);
					} else {
						jobId = String.format("AR010%sA0000", DateTime.getDate(6));
						qafJobId = "LYAAU" + jobId.substring(5, 11) + jobId.substring(12);
					}
					
					if (!Flag.TRUE) Print.println("> %s -> %s", jobId, qafJobId);
					
					maxQafJobId = qafJobId;
				}
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		return maxQafJobId;
	}
	
	private List<String[]> getJobIdParams(String maxQJobId)
	{
		List<String[]> list = new ArrayList<String[]>();
		
		if (Flag.TRUE) {
			try {

				String query;
				query = String.format("\n"
						+ "SELECT                                           \n"
						+ "    qaf_job_id,                                  \n"
						+ "    qaf_job_dip_name,                            \n"
						+ "    qaf_job_dip_value                            \n"
						+ "FROM                                             \n"
						+ "    yms_lya.qaf_id_job_dip_history               \n"
						+ "WHERE                                            \n"
						+ "    qaf_job_id in (                              \n"
						+ "        SELECT                                   \n"
						+ "            qaf_job_id                           \n"
						+ "        FROM                                     \n"
						+ "        (                                        \n"
						+ "            SELECT                               \n"
						+ "                DISTINCT qaf_job_id              \n"
						+ "            FROM                                 \n"
						+ "                yms_lya.qaf_id_job_dip_history   \n"
						+ "            WHERE 1=1                            \n"
						+ "                AND qaf_job_id > %s              \n"
						+ "                AND qaf_job_id LIKE 'LYAAU%%'    \n"
						+ "            ORDER BY                             \n"
						+ "                qaf_job_id DESC                  \n"
						+ "                                                 \n"
						+ "        )                                        \n"
						+ "        WHERE 1=1                                \n"
						+ "            AND ROWNUM <= %s                     \n"
						+ "    )                                            \n"
						+ "ORDER BY                                         \n"
						+ "    qaf_job_id                                   \n"
						, StrUtil.quote(maxQJobId)
						, ROWNUM
						);
				if (!Flag.TRUE) System.out.println("[" + query + "]");
				
				if (Flag.TRUE) {

					ResultSet rs = stmt.executeQuery(query);

					for (int i=0; rs.next(); i++) {
						String qafJobId = rs.getString("QAF_JOB_ID");
						String jobId = "AR010" + qafJobId.substring(5, 11) + "A" + qafJobId.substring(11);
						String paramNm = rs.getString("QAF_JOB_DIP_NAME");
						String paramVal = rs.getString("QAF_JOB_DIP_VALUE");
						
						if (!Flag.TRUE) Print.println("%4d) [%s] -> [%s] [%s] [%s]", i, qafJobId, jobId, paramNm, paramVal);
						
						list.add(new String[] { jobId, paramNm, paramVal } );
					}
				}

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		return list;
	}
	
	private void insertAnal(List<String[]> list)
	{
		if (Flag.TRUE) {
			try {
				
				String jobId = "";
				int seq = 0;
				String query;
				
				for (String[] items : list) {
					if (Flag.TRUE) {
						if (!jobId.equals(items[0])) {
							query = String.format(""
									+ "INSERT INTO anal_jobid (job_id, status, reg_dt) VALUES ( %s, 'WAIT', SYSDATE) "
									, StrUtil.quote(items[0])
									);
							stmt.executeUpdate(query);

							jobId = items[0];
							seq = 0;
						}
					}
					
					if (Flag.TRUE) {
						query = String.format(""
								+ "INSERT INTO anal_param (job_id, seq, param_nm, param_val, reg_dt) "
								+ "VALUES ( %s, %d, %s, %s, SYSDATE ) "
								, StrUtil.quote(items[0])
								, ++ seq
								, StrUtil.quote(items[1])
								, StrUtil.quote(items[2])
								);
						stmt.executeUpdate(query);
					}
				}
				
				if (Flag.TRUE) {
					query = String.format("UPDATE anal_jobid SET status='READY' WHERE status='WAIT' ");
					stmt.executeUpdate(query);
				}
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	public void execute()
	{
		if (Flag.TRUE) {
			
			String maxQafJobId = getMaxQafJobId();
			
			List<String[]> list = getJobIdParams(maxQafJobId);
			if (!Flag.TRUE) {
				/*
				 * list 확인
				 */
				for (String[] items : list) {
					Print.println("[%s] [%-20s] [%-50s]", items[0], items[1], items[2]);
				}
			}
			
			insertAnal(list);
		}
		
		/*
		 * connection close
		 */
		if (Flag.TRUE) {
			try {
				conn.close();
				if (!Flag.TRUE) System.out.println("##### OK!");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
