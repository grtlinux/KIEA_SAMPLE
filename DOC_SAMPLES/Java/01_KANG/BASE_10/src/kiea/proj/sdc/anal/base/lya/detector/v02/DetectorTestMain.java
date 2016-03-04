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

package kiea.proj.sdc.anal.base.lya.detector.v02;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;

import kiea.kr.co.tain.base.Flag;
import kiea.kr.co.tain.util.DateTime;
import kiea.kr.co.tain.util.Print;
import kiea.proj.sdc.anal.common.Connection;
import kiea.proj.sdc.anal.util.StrUtil;
import oracle.jdbc.OracleConnection;

/**
 * @author KangSeok
 * @date 2014. 9. 17.
 * @file_name DetectorTestMain.java
 *
 */
public class DetectorTestMain
{
	///////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////
	
	private static void test01_qaf()
	{
		if (Flag.TRUE) {
			try {
				
				String[][] arrQuerys = {
						{ "SELECT * FROM yms_lya.qaf_id_job_base WHERE qaf_job_id like 'LYAAU%' AND qaf_job_wf_seq = 0 ORDER BY qaf_job_id DESC", "JOBID 테이블", },
						//{ "SELECT * FROM yms_lya.qaf_id_job_base WHERE qaf_job_wf_seq = 0 ORDER BY qaf_job_id DESC", "JOBID 테이블", },
						{ "SELECT * FROM yms_lya.qaf_id_job_dip_history WHERE qaf_job_id like 'LYAAU%' ORDER BY qaf_job_id DESC", "인자 테이블", },
				};

				OracleConnection conn = Connection.getOracleConnection();
				Statement stmt = conn.createStatement();

				for (String[] querys : arrQuerys) {
					String query;
					query = String.format("%s", querys[0]);
					
					ResultSet resultSet = stmt.executeQuery(query);
					ResultSetMetaData metaData = resultSet.getMetaData();
					
					System.out.println(String.format("\n### TABLE INFO :  < %s > : %s \n", querys[0].trim(), querys[1]));
					
					if (Flag.TRUE) {
						/* 
						 * meta data
						 */
						System.out.println("\t---------- META DATA ----------");

						for (int i=1; i <= metaData.getColumnCount(); i++) {
							//System.out.println(String.format("\t%2d) %-30s %-30s [%s]", i, metaData.getColumnName(i), metaData.getColumnLabel(i), metaData.getColumnClassName(i)));
							System.out.println(String.format("\tF%2d) %-30s [%s]", i, metaData.getColumnName(i), metaData.getColumnClassName(i)));
						}

						System.out.println();
					}

					if (Flag.TRUE) {
						/*
						 * row data
						 */
						System.out.println("\t---------- ROW DATA ----------");
						
						for (int i=0; i < 50 && resultSet.next(); i++) {
							
							StringBuffer sb = new StringBuffer();
							sb.append(String.format("\t(%4d) ", i));
							
							for (int col=1; col <= metaData.getColumnCount(); col++) {
								sb.append("\t").append(resultSet.getString(col));
							}
							
							System.out.println(sb.toString());
						}

						System.out.println();
					}
				}
				
				conn.close();
				if (Flag.TRUE) System.out.println("##### OK!");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	private static void test02_anal()
	{
		if (Flag.TRUE) {
			try {
				
				String[][] tableNames = {
						{ "ANAL_JOBID", "ANAL에 사용되는 JOBID 테이블", },
						{ "ANAL_PARAM", "ANAL에 사용되는 인자 테이블", },
				};

				OracleConnection conn = Connection.getOracleConnection();
				Statement stmt = conn.createStatement();

				for (String[] tableName : tableNames) {
					String query;
					query = String.format("SELECT * FROM %s", tableName[0]);
					//query = String.format("SELECT * FROM YMS_LYA.%s WHERE ROWNUM <= 1", tableName[0]);
					
					ResultSet resultSet = stmt.executeQuery(query);
					ResultSetMetaData metaData = resultSet.getMetaData();
					
					System.out.println(String.format("\n### TABLE INFO :  < %s > : %s \n", tableName[0].trim(), tableName[1]));
					
					if (Flag.TRUE) {
						/* 
						 * meta data
						 */
						System.out.println("\t---------- META DATA ----------");

						for (int i=1; i <= metaData.getColumnCount(); i++) {
							//System.out.println(String.format("\t%2d) %-30s %-30s [%s]", i, metaData.getColumnName(i), metaData.getColumnLabel(i), metaData.getColumnClassName(i)));
							System.out.println(String.format("\tF%2d) %-30s [%s]", i, metaData.getColumnName(i), metaData.getColumnClassName(i)));
						}

						System.out.println();
					}

					if (Flag.TRUE) {
						/*
						 * row data
						 */
						System.out.println("\t---------- ROW DATA ----------");
						
						for (int i=0; i < 100 && resultSet.next(); i++) {
							
							StringBuffer sb = new StringBuffer();
							sb.append(String.format("\t(%4d) ", i));
							
							for (int col=1; col <= metaData.getColumnCount(); col++) {
								sb.append("\t").append(resultSet.getString(col));
							}
							
							System.out.println(sb.toString());
						}

						System.out.println();
					}
				}
				
				conn.close();
				if (Flag.TRUE) System.out.println("##### OK!");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	private static void test02_anal_jobid()
	{
		if (Flag.TRUE) {
			try {
				
				OracleConnection conn = Connection.getOracleConnection();
				Statement stmt = conn.createStatement();

				String query;
				query = String.format("SELECT * FROM anal_jobid ORDER BY job_id ");
					
				ResultSet resultSet = stmt.executeQuery(query);
				ResultSetMetaData metaData = resultSet.getMetaData();
					
				if (Flag.TRUE) {
					/* 
					 * meta data
					 */
					System.out.println("\t---------- META DATA ----------");

					for (int i=1; i <= metaData.getColumnCount(); i++) {
						//System.out.println(String.format("\t%2d) %-30s %-30s [%s]", i, metaData.getColumnName(i), metaData.getColumnLabel(i), metaData.getColumnClassName(i)));
						System.out.println(String.format("\tF%2d) %-30s [%s]", i, metaData.getColumnName(i), metaData.getColumnClassName(i)));
					}

					System.out.println();
				}

				if (Flag.TRUE) {
					/*
					 * row data
					 */
					System.out.println("\t---------- ROW DATA ----------");
					
					for (int i=0; resultSet.next(); i++) {
						
						StringBuffer sb = new StringBuffer();
						sb.append(String.format("\t(%4d) ", i));
						
						for (int col=1; col <= metaData.getColumnCount(); col++) {
							sb.append("\t").append(resultSet.getString(col));
						}
						
						System.out.println(sb.toString());
					}

					System.out.println();
				}
				
				conn.close();
				if (Flag.TRUE) System.out.println("##### OK!");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	private static void test02_anal_param()
	{
		if (Flag.TRUE) {
			try {
				
				OracleConnection conn = Connection.getOracleConnection();
				Statement stmt = conn.createStatement();

				String query;
				query = String.format("SELECT * FROM anal_param ORDER BY job_id, seq ");
					
				ResultSet resultSet = stmt.executeQuery(query);
				ResultSetMetaData metaData = resultSet.getMetaData();
					
				if (Flag.TRUE) {
					/* 
					 * meta data
					 */
					System.out.println("\t---------- META DATA ----------");

					for (int i=1; i <= metaData.getColumnCount(); i++) {
						//System.out.println(String.format("\t%2d) %-30s %-30s [%s]", i, metaData.getColumnName(i), metaData.getColumnLabel(i), metaData.getColumnClassName(i)));
						System.out.println(String.format("\tF%2d) %-30s [%s]", i, metaData.getColumnName(i), metaData.getColumnClassName(i)));
					}

					System.out.println();
				}

				if (Flag.TRUE) {
					/*
					 * row data
					 */
					System.out.println("\t---------- ROW DATA ----------");
					
					for (int i=0; resultSet.next(); i++) {
						
						StringBuffer sb = new StringBuffer();
						sb.append(String.format("\t(%4d) ", i));
						
						for (int col=1; col <= metaData.getColumnCount(); col++) {
							sb.append("\t").append(resultSet.getString(col));
						}
						
						System.out.println(sb.toString());
					}

					System.out.println();
				}
				
				conn.close();
				if (Flag.TRUE) System.out.println("##### OK!");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	private static void test03_DeleteAll()
	{
		if (Flag.TRUE) {

			try {
				/*
				 * DELETE ANAL_PARAM data already exist.
				 */

				OracleConnection conn = Connection.getOracleConnection();
				Statement stmt = conn.createStatement();

				stmt.executeUpdate("DELETE FROM ANAL_PARAM ");
				stmt.executeUpdate("DELETE FROM ANAL_JOBID ");

				conn.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	private static void test04_getMax()
	{
		if (Flag.TRUE) {
			try {
				
				OracleConnection conn = Connection.getOracleConnection();
				Statement stmt = conn.createStatement();

				String query;
				query = String.format("SELECT job_id FROM anal_jobid WHERE job_id like 'AR010%sA%%' AND ROWNUM <= 1 ORDER BY job_id DESC", DateTime.getDate(6));
				if (Flag.TRUE) System.out.println("[" + query + "]");
				
				if (Flag.TRUE) {
					ResultSet rs = stmt.executeQuery(query);
					if (rs.next()) {
						String jobId = rs.getString("JOB_ID");
						String qafJobId = "LYAAU" + jobId.substring(5, 11) + jobId.substring(12);
						
						Print.println("> %s -> %s", jobId, qafJobId);
					} else {
						String jobId = String.format("AR010%sA0000", DateTime.getDate(6));
						String qafJobId = "LYAAU" + jobId.substring(5, 11) + jobId.substring(12);
						
						Print.println("> %s -> %s", jobId, qafJobId);
					}
				}
				
				conn.close();
				if (Flag.TRUE) System.out.println("##### OK!");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	private static void test05_searchQaf()
	{
		if (Flag.TRUE) {
			try {
				
				OracleConnection conn = Connection.getOracleConnection();
				Statement stmt = conn.createStatement();

				String query;
				query = String.format("SELECT qaf_job_id "
						+ "FROM yms_lya.qaf_id_job_base "
						+ "WHERE qaf_job_id > %s AND qaf_job_id like 'LYAAU%%' AND qaf_job_wf_seq = 0 "
						+ "ORDER BY qaf_job_id DESC"
						, StrUtil.quote("LYAAU1409170000"));
				if (Flag.TRUE) System.out.println("[" + query + "]");
				
				if (Flag.TRUE) {

					ResultSet rs = stmt.executeQuery(query);

					for (int i=0; i < 10000 && rs.next(); i++) {
						String qafJobId = rs.getString("QAF_JOB_ID");
						String jobId = "AR010" + qafJobId.substring(5, 11) + "A" + qafJobId.substring(11);
						Print.println("%4d) [%s] -> [%s]", i, qafJobId, jobId);
					}
				}

				conn.close();
				if (Flag.TRUE) System.out.println("##### OK!");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	private static void test06_searchQafParam()
	{
		if (Flag.TRUE) {
			try {
				
				OracleConnection conn = Connection.getOracleConnection();
				Statement stmt = conn.createStatement();

				String query;
				query = String.format("SELECT qaf_job_id, qaf_job_dip_name, qaf_job_dip_value "
						+ "FROM yms_lya.qaf_id_job_dip_history "
						+ "WHERE qaf_job_id > %s AND qaf_job_id like 'LYAAU%%' "
						+ "ORDER BY qaf_job_id DESC"
						, StrUtil.quote("LYAAU1409170000"));
				if (Flag.TRUE) System.out.println("[" + query + "]");
				
				if (Flag.TRUE) {

					ResultSet rs = stmt.executeQuery(query);

					for (int i=0; i < 100 && rs.next(); i++) {
						String qafJobId = rs.getString("QAF_JOB_ID");
						String paramNm = rs.getString("QAF_JOB_DIP_NAME");
						String paramVal = rs.getString("QAF_JOB_DIP_VALUE");
						String jobId = "AR010" + qafJobId.substring(5, 11) + "A" + qafJobId.substring(11);
						Print.println("%4d) [%s] -> [%s] [%s] [%s]", i, qafJobId, jobId, paramNm, paramVal);
					}
				}

				conn.close();
				if (Flag.TRUE) System.out.println("##### OK!");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void main(String[] args)
	{
		if (!Flag.TRUE) test01_qaf();
		if (!Flag.TRUE) test02_anal();
		if (Flag.TRUE) test02_anal_jobid();
		if (!Flag.TRUE) test02_anal_param();
		if (!Flag.TRUE) test03_DeleteAll();
		
		if (!Flag.TRUE) test04_getMax();
		if (!Flag.TRUE) test05_searchQaf();
		if (!Flag.TRUE) test06_searchQafParam();
	}
}
