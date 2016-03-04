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

package kiea.proj.sdc.anal.tools.yms.check.v01.t01;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;

import kiea.kr.co.tain.base.Flag;
import kiea.proj.sdc.anal.common.Connection;
import oracle.jdbc.OracleConnection;

/**
 * @author KangSeok
 * @date 2014. 8. 6.
 * @file_name QafListMain.java
 *
 */
public class QafListMain
{
	///////////////////////////////////////////////////////////////////////////

	private static void test01()
	{
		if (Flag.TRUE) {
			try {
				String[] tableNames = {
						"QAF_CHECK_DATA              ",
						"QAF_GOODBAD_ID              ",
						"QAF_ID_AWS_LOG_DETAIL       ",
						"QAF_ID_AWS_LOG_HEADER       ",
						"QAF_ID_DIP_HISTORY          ",
						"QAF_ID_JOB_ARP_HISTORY      ",
						"QAF_ID_JOB_ARP_PARAM_VALUE  ",
						"QAF_ID_JOB_BASE             ",
						"QAF_ID_JOB_DIP_HISTORY      ",
						"QAF_ID_JOB_GIP_HISTORY      ",
						"QAF_ID_JOB_SIP_HISTORY      ",
						"QAF_ID_JOB_WORKFLOW_LIST    ",
						"QAF_ID_LOGIC_BRANCH_CONTROL ",
						"QAF_ID_OUTPUT_BASE          ",
						"QAF_ID_OUTPUT_HISTORY       ",
						"QAF_MD_CODE_NAME            ",
						"QAF_MD_CONFIG_BASE          ",
						"QAF_MD_DEFECTCD_MAPPING     ",
						"QAF_MD_ITEM_BASE            ",
						"QAF_MD_OUTPUT_BROKER_STATUS ",
						"QAF_MD_RULE_BASE            ",
						"QAF_MD_RULE_BASE_HISTORY    ",
						"QAF_MD_SRCDATA_MAPPING      ",
						"QAF_PARA_SASDI_JOB          ",
				};
				
				OracleConnection conn = Connection.getOracleConnection();
				Statement stmt = conn.createStatement();
				
				for (String tableName : tableNames) {
					String query;
					query = String.format("SELECT * FROM %s WHERE ROWNUM <= 1", tableName);
					
					if (Flag.TRUE) {
						ResultSet resultSet = stmt.executeQuery(query);
						ResultSetMetaData metaData = resultSet.getMetaData();
						
						System.out.println(String.format("\n\n\n---------- table :  %s  ------------\n", tableName));
						
						/*
						 * row data
						 */
						for (int i=0; i < 10000 && resultSet.next(); i++) {
							
							StringBuffer sb = new StringBuffer();
							sb.append(String.format("(%4d)   ", i));
							
							for (int col=1; col <= metaData.getColumnCount(); col++) {
								sb.append("\t").append(resultSet.getString(col));
							}
							
							System.out.println(sb.toString());
						}

						System.out.println();
						
						/* 
						 * meta data
						 */
						for (int i=1; i <= metaData.getColumnCount(); i++) {
							System.out.println(String.format("\t%2d) %-30s %-30s [%s]", i, metaData.getColumnName(i), metaData.getColumnLabel(i), metaData.getColumnClassName(i)));
						}
					}
				}
				
				conn.close();
				if (Flag.TRUE) System.out.println("##### OK!");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	private static void test02()
	{
		if (Flag.TRUE) {
			try {
				String[][] tableNames = {
						{ "QAF_CHECK_DATA                   ", "TIBCO BW - SAS 간 통신 기록 테이블 (현재 로그 미적재중)", },
						{ "QAF_GOODBAD_ID                   ", "분석시 JOB_ID별 GOOD/BAD 정보 테이블", },
						{ "QAF_ID_AWS_LOG_DETAIL            ", "WORKFLOW / JOB_ID별 SAS분석 결과 수행시간 적재 테이블", },
						{ "QAF_ID_AWS_LOG_HEADER            ", "WORKFLOW / JOB_ID별 SAS분석 결과 HEADER LOG 적재 테이블", },
						{ "QAF_ID_DIP_HISTORY               ", "개발 초기 단계 사용한 테이블로 현재 미사용", },
						{ "QAF_ID_JOB_ARP_HISTORY           ", "JOB_ID별 분석 파라미터 정보 입력", },
						{ "QAF_ID_JOB_ARP_PARAM_VALUE       ", "ARP_HISTORY 에 넣은 파라미터 기준정보 테이블", },
						{ "QAF_ID_JOB_BASE                  ", "QAF 분석의 전체 이력 관리 테이블", },
						{ "QAF_ID_JOB_DIP_HISTORY           ", "JOB_ID별 분석 정보관리 테이블(분석시간, 모델, 라인정보등)", },
						{ "QAF_ID_JOB_GIP_HISTORY           ", "USER CLUSTER 분석시 사용되는 기준정보 테이블", },
						{ "QAF_ID_JOB_SIP_HISTORY           ", "JOB_ID별 어떤 분석 수행할지 결정하는 테이블(WIP/MOD/DEFECT 등)", },
						{ "QAF_ID_JOB_WORKFLOW_LIST         ", "WORKFLOW LIST 기준정보테이블", },
						{ "QAF_ID_LOGIC_BRANCH_CONTROL      ", "(현재 데이터 없음)", },
						{ "QAF_ID_OUTPUT_BASE               ", "JOB_ID별 CSV 파일생성목록 이력관리테이블", },
						{ "QAF_MD_CODE_NAME                 ", "기준정보 테이블(QAF 초기 단계에 설계된 기준정보 테이블) QAF META DATA 코드 기준정보 저장", },
						{ "QAF_MD_CODE_NAME1                ", "미사용 테이블", },
						{ "QAF_MD_CONFIG_BASE               ", "기준정보 테이블(QAF 초기 단계에 설계된 기준정보 테이블) QAF META DATA 코드 기준정보 저장", },
						{ "QAF_MD_DEFECTCD_MAPPING          ", "DEFECTCD MAPPING 기준정보", },
						{ "QAF_MD_ITEM_BASE                 ", "QAF SAS SOURCE 기준정보 테이블", },
						{ "QAF_MD_OUTPUT_BROKER_STATUS      ", "QAF 분석별 OUTPUT 기준정보 저장", },
						{ "QAF_MD_RULE_BASE                 ", "분석별 파라미터 기준정보 테이블", },
						{ "QAF_MD_RULE_BASE_D               ", "(현재 데이터 없음)", },
						{ "QAF_MD_RULE_BASE_HISTORY         ", "(현재 데이터 없음)", },
						{ "QAF_MD_SRCDATA_MAPPING           ", "QAF META DATA 소스 매핑 정보 저장", },
						{ "QAF_PARA_SASDI_JOB               ", "(현재 데이터 없음)", },
				};
				
				OracleConnection conn = Connection.getOracleConnection();
				Statement stmt = conn.createStatement();
				
				for (String[] tableName : tableNames) {
					String query;
					query = String.format("SELECT * FROM YMS_LYA.%s WHERE ROWNUM <= 1", tableName[0]);
					
					if (Flag.TRUE) {
						ResultSet resultSet = stmt.executeQuery(query);
						ResultSetMetaData metaData = resultSet.getMetaData();
						
						System.out.println(String.format("\n\n\n---------- table :  %s  %s ------------\n", tableName[0], tableName[1]));
						
						/*
						 * row data
						 */
						for (int i=0; i < 10000 && resultSet.next(); i++) {
							
							StringBuffer sb = new StringBuffer();
							sb.append(String.format("(%4d)   ", i));
							
							for (int col=1; col <= metaData.getColumnCount(); col++) {
								sb.append("\t").append(resultSet.getString(col));
							}
							
							System.out.println(sb.toString());
						}

						System.out.println();
						
						/* 
						 * meta data
						 */
						for (int i=1; i <= metaData.getColumnCount(); i++) {
							System.out.println(String.format("\t%2d) %-30s %-30s [%s]", i, metaData.getColumnName(i), metaData.getColumnLabel(i), metaData.getColumnClassName(i)));
						}
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
		if (!Flag.TRUE) test01();
		if (Flag.TRUE) test02();
	}
}
