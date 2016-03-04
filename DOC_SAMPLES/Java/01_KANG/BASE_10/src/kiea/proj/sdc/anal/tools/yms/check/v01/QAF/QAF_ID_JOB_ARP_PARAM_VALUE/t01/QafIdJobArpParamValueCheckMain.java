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

package kiea.proj.sdc.anal.tools.yms.check.v01.QAF.QAF_ID_JOB_ARP_PARAM_VALUE.t01;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;

import kiea.kr.co.tain.base.Flag;
import kiea.proj.sdc.anal.common.Connection;
import oracle.jdbc.OracleConnection;

/**
 * @author KangSeok
 * @date 2014. 9. 5.
 * @file_name QafIdJobArpParamValueCheckMain.java
 *
 */
public class QafIdJobArpParamValueCheckMain
{
	/*
	 * ARP : Analytic Rule Parameters
	 */
	///////////////////////////////////////////////////////////////////////////
	
	private static void test01()
	{
		if (Flag.TRUE) {
			if (Flag.TRUE) {
				try {
					String[][] tableNames = {
							{ "QAF_ID_JOB_ARP_PARAM_VALUE       ", "ARP_HISTORY 에 넣은 파라미터 기준정보 테이블", },
					};
					
					OracleConnection conn = Connection.getOracleConnection();
					Statement stmt = conn.createStatement();
					
					for (String[] tableName : tableNames) {
						String query;
						query = String.format("SELECT * FROM YMS_LYA.%s order by qaf_rule_wf_id", tableName[0]);
						//query = String.format("SELECT * FROM YMS_LYA.%s WHERE ROWNUM <= 1", tableName[0]);
						
						ResultSet resultSet = stmt.executeQuery(query);
						ResultSetMetaData metaData = resultSet.getMetaData();
						
						System.out.println(String.format("\n### TABLE INFO :  < %s > : %s \n", tableName[0].trim(), tableName[1]));
						
						if (Flag.TRUE) {
							/*
							 * row data
							 */
							System.out.println("---------- ROW DATA ----------");
							
							for (int i=0; i < 1000 && resultSet.next(); i++) {
								
								StringBuffer sb = new StringBuffer();
								sb.append(String.format("(%6d)   ", i));
								
								for (int col=1; col <= metaData.getColumnCount(); col++) {
									sb.append("\t").append(resultSet.getString(col));
								}
								
								System.out.println(sb.toString());
							}

							System.out.println();
						}
						
						if (Flag.TRUE) {
							/* 
							 * meta data
							 */
							System.out.println("---------- META DATA ----------");

							for (int i=1; i <= metaData.getColumnCount(); i++) {
								//System.out.println(String.format("\t%2d) %-30s %-30s [%s]", i, metaData.getColumnName(i), metaData.getColumnLabel(i), metaData.getColumnClassName(i)));
								System.out.println(String.format("\t%2d) %-30s [%s]", i, metaData.getColumnName(i), metaData.getColumnClassName(i)));
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
	}
	
	public static void main(String[] args)
	{
		if (Flag.TRUE) test01();
	}
}
