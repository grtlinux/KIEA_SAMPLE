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

package kiea.proj.sdc.anal.tools.yms.check.v01.ANAL.QAF_DIP;

import java.sql.ResultSet;
import java.sql.Statement;

import kiea.kr.co.tain.base.Flag;
import kiea.kr.co.tain.util.DateTime;
import kiea.kr.co.tain.util.Print;
import kiea.proj.sdc.anal.common.Connection;
import kiea.proj.sdc.anal.util.StrUtil;
import oracle.jdbc.OracleConnection;

/**
 * @author KangSeok
 * @date 2014. 9. 29.
 * @file_name QafDipCheckMain.java
 *
 */
public class QafDipCheckMain
{
	private final static String ROWNUM = "10";
	
	///////////////////////////////////////////////////////////////////////////
	
	private static void test01()
	{
		OracleConnection conn = null;
		Statement stmt = null;
		String query = null;
		String maxJobId = null;
		String maxQafJobId = null;

		if (Flag.TRUE) {
			try {
				conn = Connection.getOracleConnection();
				stmt = conn.createStatement();
			} catch (Exception e) {
				e.printStackTrace();
				return;
			}
		}
		
		if (Flag.TRUE) {
			try {
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
				if (Flag.TRUE) System.out.println("[" + query + "]");

				ResultSet resultSet = stmt.executeQuery(query);
				if (resultSet.next()) {
					maxJobId = resultSet.getString("JOB_ID");
					maxQafJobId = "LYAAU" + maxJobId.substring(5, 11) + maxJobId.substring(12);
				} else {
					maxJobId = String.format("AR010%sA0000", DateTime.getDate(6));
					maxQafJobId = "LYAAU" + maxJobId.substring(5, 11) + maxJobId.substring(12);
				}
				
				if (Flag.TRUE) Print.println("> %s -> %s", maxJobId, maxQafJobId);
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		if (!Flag.TRUE) {
			try {
				query = String.format("\n"
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
						, StrUtil.quote(maxQafJobId)
						, ROWNUM
						);
				if (Flag.TRUE) System.out.println("[" + query + "]");

				ResultSet resultSet = stmt.executeQuery(query);
				for (int i=0; resultSet.next(); i++) {
					String qafJobId = resultSet.getString("QAF_JOB_ID");
					
					if (Flag.TRUE) Print.println("%4d) [%s]", i, qafJobId);
				}
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		if (Flag.TRUE) {
			try {
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
						, StrUtil.quote(maxQafJobId)
						, ROWNUM
						);
				if (Flag.TRUE) System.out.println("[" + query + "]");

				ResultSet resultSet = stmt.executeQuery(query);
				for (int i=0; resultSet.next(); i++) {
					String qafJobId = resultSet.getString("QAF_JOB_ID");
					String jobId = "AR010" + qafJobId.substring(5, 11) + "A" + qafJobId.substring(11);
					String paramNm = resultSet.getString("QAF_JOB_DIP_NAME");
					String paramVal = resultSet.getString("QAF_JOB_DIP_VALUE");
					
					if (Flag.TRUE) Print.println("%4d) [%s] -> [%s] [%s] [%s]", i, qafJobId, jobId, paramNm, paramVal);
				}
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		if (Flag.TRUE) {
			try {
				conn.close();
				if (Flag.TRUE) System.out.println("##### OK!");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void main(String[] args)
	{
		if (Flag.TRUE) test01();
	}
}
