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
 * @date 2014. 8. 7.
 * @file_name AllQafTableInfoMain.java
 *
 */
public class AllQafTableInfoMain
{
	///////////////////////////////////////////////////////////////////////////

	private static void test01()
	{
		if (Flag.TRUE) {
			try {
				String[][] tableNames = {
						{ "D_CLUSTER_SPECIFY                ", "분석기준정보테이블", },
						{ "D_DCOLL                          ", "분석기준정보테이블", },
						{ "D_DELAYTIME_PROCESSTIME          ", "분석기준정보테이블", },
						{ "D_GAMMA_ALERT_ITEM_ID            ", "분석기준정보테이블", },
						{ "D_GAMMA_ALERT_PRODUCT            ", "분석기준정보테이블", },
						{ "D_ITEM_LIST                      ", "분석기준정보테이블", },
						{ "D_MATCHING_WEIGHT                ", "분석기준정보테이블", },
						{ "D_METRO_PROC_EQP                 ", "분석기준정보테이블", },
						{ "D_SAME_DEFECT                    ", "분석기준정보테이블", },
						{ "D_STEP_PROCESS                   ", "분석기준정보테이블", },
						{ "D_STEP_WEIGHT                    ", "분석기준정보테이블", },
						{ "ETL_DCOMP_EVENT                  ", "프로시저 ETL용 GLOBAL TEMPORARY TABLE", },
						{ "ETL_GAMMA_EVENT                  ", "프로시저 ETL용 GLOBAL TEMPORARY TABLE", },
						{ "ETL_JOB_CHECK                    ", "MDWDB -> YMSDB 전환 / ODI 사용 후 미사용", },
						{ "ETL_JOB_LIST                     ", "MDWDB -> YMSDB 전환 / ODI 사용 후 미사용", },
						{ "ETL_JOB_LIST_DEV                 ", "MDWDB -> YMSDB 전환 / ODI 사용 후 미사용", },
						{ "ETL_JOB_LOG                      ", "MDWDB -> YMSDB 전환 / ODI 사용 후 미사용", },
						{ "ETL_JOB_LOG_DEV                  ", "MDWDB -> YMSDB 전환 / ODI 사용 후 미사용", },
						{ "ETL_MURA_EVENT                   ", "프로시저 ETL용 GLOBAL TEMPORARY TABLE", },
						{ "ETL_PREJOB_LIST                  ", "MDWDB -> YMSDB 전환 / ODI 사용 후 미사용", },
						{ "ETL_SPC_EVENT                    ", "MDWDB -> YMSDB 전환 / ODI 사용 후 미사용", },
						{ "LOAD_V                           ", "MDWDB -> YMSDB 전환 / ODI 사용 후 미사용", },
						{ "PART_MGT_JOB_LOG                 ", "MDWDB -> YMSDB 전환 / ODI 사용 후 미사용", },
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
						{ "SYMS_DEFECT_COMP_MASTER          ", "DEFECT PATTERN 분석 MASTER 테이블(현재 해당 분석 미수행)", },
						{ "SYMS_LYA_ANAL_FILE_STATUS        ", "분석 대상 CSV 파일 사용가능 여부 이력 관리 테이블", },
						{ "SYMS_LYA_ANAL_REPORT             ", "분석 JOB_ID 별 리포트 생성 DXP 파일 이력 관리", },
						{ "SYMS_LYA_APP_CODE                ", "프로시져 / UI에서 사용하는 기준정보 테이블", },
						{ "SYMS_LYA_APP_USER_LOG            ", "APP 접근 이력관리테이블", },
						{ "SYMS_LYA_JOB_ANAL_METHOD         ", "JOB_ID별 분석방법 이력관리테이블", },
						{ "SYMS_LYA_JOB_FAIL_CODE           ", "JOB_ID별 불량코드 이력관리테이블", },
						{ "SYMS_LYA_JOB_GLASS_GROUP         ", "", },
						{ "SYMS_LYA_JOB_MASTER              ", "분석 MASTER 테이블", },
						{ "SYMS_LYA_JOB_PROCESS             ", "수동분석시 발생하는 PROCESS_ID 관리 테이블", },
						{ "SYMS_LYA_JOB_PRODUCT             ", "JOB_ID별 PRODUCT 관리테이블(현재 미사용)", },
						{ "SYMS_LYA_JOB_PRODUCT_GRP         ", "JOB_ID별 PRODUCT_GRP 관리테이블", },
						{ "SYMS_LYA_JOB_STEPID              ", "수동분석시 발생하는 STEP_ID 관리테이블", },
						{ "SYMS_LYA_JOB_USER_RULE           ", "수동분석시 발생하는 USER RULE 관리테이블", },
						{ "SYMS_LYA_LOG_INFO                ", "UI DAEMON 특이 로그 생성테이블", },
						{ "SYMS_LYA_MAIL_OPTION             ", "메일 수신인 기준정보", },
						{ "SYMS_LYA_MAP_LAYOUT              ", "기존 기준 정보였으나 현재 미사용", },
						{ "SYMS_LYA_MST_SOURCE_FILE         ", "SPOTFIRE 탬플릿 생성 기준정보 테이블", },
						{ "SYMS_LYA_NOTICE                  ", "공지사항 생성용 테이블(현재 자료 없음)", },
						{ "SYMS_LYA_QAF_RULE_BASE           ", "분석별 파라미터 기준정보 테이블", },
						{ "SYMS_LYA_QAF_WORKFLOW_LIST       ", "UI사용 WORKFLOW LIST 기준정보 테이블", },
						{ "SYMS_LYA_QFOCUS_MASTER           ", "QFOCUS 분석 MASTER 테이블", },
						{ "SYMS_LYA_REPORT_SCRAP            ", "리포트 스크랩 이력 관리 테이블(현재 자료 없음)", },
						{ "SYMS_LYA_RULE_DELAYTIME          ", "DELAYTIME 관리 테이블(현재 자료 없음)", },
						{ "SYMS_LYA_RULE_DELAYTIME_QAF      ", "DELAYTIME 관리 테이블(현재 자료 없음)", },
						{ "SYMS_LYA_RULE_MASTER             ", "UI RULE 기준정보 테이블", },
						{ "SYMS_LYA_RULE_MASTER_QAF         ", "UI RULE 기준정보 테이블", },
						{ "SYMS_LYA_RULE_VALUE              ", "UI RULE VALUE 기준정보 테이블", },
						{ "SYMS_LYA_RULE_VALUE_QAF          ", "UI RULE VALUE 기준정보 테이블", },
						{ "SYMS_LYA_SUMMARY_REPORT          ", "JOB_ID별 UI HTM 이력관리 테이블", },
						{ "SYMS_LYA_SYS_LOG_INFO            ", "", },
						{ "SYMS_LYA_USER_CLUSTER_MAP        ", "USER CLUSTER MAP 저장 테이블(현재 자료 없음)", },
						{ "SYMS_LYA_USER_MASTER             ", "메일 수신인 기준정보 테이블", },
						{ "SYMS_LYA_USER_OPTION             ", "메일 수신인 기준정보 테이블", },
						{ "SYMS_LYA_USER_OPTION_DCOMP       ", "DEFECT PATTERN 분석 수신인 기준정보 테이블(현재 해당 분석 미수행)", },
						{ "SYMS_LYA_USER_OPTION_GAMMA       ", "GAMMA 분석 수신인 기준정보 테이블(현재 해당 분석 미수행)", },
						{ "SYMS_LYA_USER_OPTION_MURA        ", "얼룩 분석 수신인 기준정보 테이블(현재 해당 분석 미수행)", },
						{ "SYMS_LYA_USER_OPTION_PRODUCT     ", "메일 수신인 기준정보 테이블", },
						{ "TMP_DEFECT                       ", "미사용 테이블", },
						{ "TMP_INSPECT_HIST_MAIN            ", "미사용 테이블", },
						{ "TMP_PRODUCT                      ", "미사용 테이블", },
				};
				
				//OracleConnection conn = Connection.getOracleConnection("DEVTRC", "wkehdghk1");
				OracleConnection conn = Connection.getOracleConnection();
				Statement stmt = conn.createStatement();
				
				for (String[] tableName : tableNames) {
					String query;
					query = String.format("SELECT * FROM YMS_LYA.%s WHERE ROWNUM <= 5", tableName[0]);
					
					if (Flag.TRUE) {
						ResultSet resultSet = stmt.executeQuery(query);
						ResultSetMetaData metaData = resultSet.getMetaData();
						
						System.out.println(String.format("\n\n\n---------- table :  %s : %s ------------\n", tableName[0], tableName[1]));
						
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
		if (Flag.TRUE) test01();
	}
}
