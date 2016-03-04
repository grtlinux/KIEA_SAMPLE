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

package kiea.proj.sdc.anal.base.lya.detector.v01;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.Vector;

import kiea.kr.co.tain.base.Flag;
import kiea.kr.co.tain.util.Print;
import kiea.proj.sdc.anal.common.Connection;
import oracle.jdbc.OracleConnection;

/**
 * @author KangSeok
 * @date 2014. 9. 16.
 * @file_name JobidInfo.java
 *
 */
public class JobIdInfo
{
	///////////////////////////////////////////////////////////////////////////

	private String jobId;
	
	public void setJobId(String jobId)
	{
		this.jobId = jobId;
	}
	
	public String getJobId()
	{
		return this.jobId;
	}
	
	///////////////////////////////////////////////////////////////////////////
	
	private Vector<String[]> param = new Vector<String[]>();
	
	public void addParam(String[] arrStr)
	{
		param.add(arrStr);
	}
	
	public Vector<String[]> getParam()
	{
		return this.param;
	}
	
	///////////////////////////////////////////////////////////////////////////

	private String lineCode        = null;
	private String analMethod      = null;
	private String areaCode        = null;
	private String subAreaCode     = null;
	private String usl             = null;
	
	public void setLineCode(String lineCode)
	{
		this.lineCode = lineCode;
		addParam(new String[] { "LINE_CODE", lineCode } );
	}
	public void setAnalMethod(String analMethod)
	{
		this.analMethod = analMethod;
		addParam(new String[] { "ANAL_METHOD", analMethod } );
	}

	public void setAreaCode(String areaCode)
	{
		this.areaCode = areaCode;
		addParam(new String[] { "AREA_CODE", areaCode } );
	}

	public void setSubAreaCode(String subAreaCode)
	{
		this.subAreaCode = subAreaCode;
		addParam(new String[] { "SUB_AREA_CODE", subAreaCode } );
	}

	public void setUsl(String usl)
	{
		this.usl = usl;
	}

	public String getLineCode()
	{
		return this.lineCode;
	}

	public String getAnalMethod()
	{
		return this.analMethod;
	}
	
	public String getAreaCode()
	{
		return this.areaCode;
	}
	
	public String getSubAreaCode()
	{
		return this.subAreaCode;
	}
	
	public String getUsl()
	{
		return this.usl;
	}
	///////////////////////////////////////////////////////////////////////////
	
	private Vector<String> inspectDt       = new Vector<String>();
	private Vector<String> stepId          = new Vector<String>();
	private Vector<String> processId       = new Vector<String>();
	private Vector<String> productId       = new Vector<String>();
	private Vector<String> decisionCode    = new Vector<String>();
	private Vector<String> userGroupCode   = new Vector<String>();
	private Vector<String> defectGroupCode = new Vector<String>();
	private Vector<String> productType     = new Vector<String>();
	
	public void addInspectDt(String inspectDt)
	{
		this.inspectDt.add(inspectDt);
		addParam(new String[] { "INSPECT_DT", inspectDt } );
	}
	
	public void addStepId(String stepId)
	{
		this.stepId.add(stepId);
		addParam(new String[] { "STEP_ID", stepId } );
	}
	
	public void addProcessId(String processId)
	{
		this.processId.add(processId);
		addParam(new String[] { "PROCESS_ID", processId } );
	}
	
	public void addProductId(String productId)
	{
		this.productId.add(productId);
		addParam(new String[] { "PRODUCT_ID", productId } );
	}
	
	public void addDecisionCode(String decisionCode)
	{
		this.decisionCode.add(decisionCode);
		addParam(new String[] { "DECISION_CODE", decisionCode } );
	}
	
	public void addUserGroupCode(String userGroupCode)
	{
		this.userGroupCode.add(userGroupCode);
		addParam(new String[] { "USER_GROUP_CODE", userGroupCode } );
	}
	
	public void addDefectGroupCode(String defectGroupCode)
	{
		this.defectGroupCode.add(defectGroupCode);
		addParam(new String[] { "DEFECT_GROUP_CODE", defectGroupCode } );
	}
	
	public void addProductType(String productType)
	{
		this.productType.add(productType);
		addParam(new String[] { "PRODUCT_TYPE", productType } );
	}
	
	public Vector<String> getInspectDt()
	{
		return this.inspectDt;
	}
	public Vector<String> getStepId()
	{
		return this.stepId;
	}
	public Vector<String> getProcessId()
	{
		return this.processId;
	}
	public Vector<String> getProductId()
	{
		return this.productId;
	}
	
	public Vector<String> getDecisionCode()
	{
		return this.decisionCode;
	}
	
	public Vector<String> getUserGroupCode()
	{
		return this.userGroupCode;
	}
	
	public Vector<String> getDefectGroupCode()
	{
		return this.defectGroupCode;
	}
	
	public Vector<String> getProductType()
	{
		return this.productType;
	}
	
	public String getInspectDtList()
	{
		StringBuffer sb = new StringBuffer();
		
		for (int i=0; i < this.inspectDt.size(); i++) {
			if (i != 0)
				sb.append(", ");
			
			sb.append("'");
			sb.append(this.inspectDt.elementAt(i));
			sb.append("'");
		}
		
		return sb.toString();
	}
	
	public String getStepIdList()
	{
		StringBuffer sb = new StringBuffer();
		
		for (int i=0; i < this.stepId.size(); i++) {
			if (i != 0)
				sb.append(", ");
			
			sb.append("'");
			sb.append(this.stepId.elementAt(i));
			sb.append("'");
		}
		
		return sb.toString();
	}
	
	public String getProcessIdList()
	{
		StringBuffer sb = new StringBuffer();
		
		for (int i=0; i < this.processId.size(); i++) {
			if (i != 0)
				sb.append(", ");
			
			sb.append("'");
			sb.append(this.processId.elementAt(i));
			sb.append("'");
		}
		
		return sb.toString();
	}
	
	public String getProductIdList()
	{
		StringBuffer sb = new StringBuffer();
		
		for (int i=0; i < this.productId.size(); i++) {
			if (i != 0)
				sb.append(", ");
			
			sb.append("'");
			sb.append(this.productId.elementAt(i));
			sb.append("'");
		}
		
		return sb.toString();
	}
	
	public String getDecisionCodeList()
	{
		StringBuffer sb = new StringBuffer();
		
		for (int i=0; i < this.decisionCode.size(); i++) {
			if (i != 0)
				sb.append(", ");
			
			sb.append("'");
			sb.append(this.decisionCode.elementAt(i));
			sb.append("'");
		}
		
		return sb.toString();
	}
	
	public String getUserGroupCodeList()
	{
		StringBuffer sb = new StringBuffer();
		
		for (int i=0; i < this.userGroupCode.size(); i++) {
			if (i != 0)
				sb.append(", ");
			
			sb.append("'");
			sb.append(this.userGroupCode.elementAt(i));
			sb.append("'");
		}
		
		return sb.toString();
	}
	
	public String getDefectGroupCodeList()
	{
		StringBuffer sb = new StringBuffer();
		
		for (int i=0; i < this.defectGroupCode.size(); i++) {
			if (i != 0)
				sb.append(", ");
			
			sb.append("'");
			sb.append(this.defectGroupCode.elementAt(i));
			sb.append("'");
		}
		
		return sb.toString();
	}
	
	public String getProductTypeList()
	{
		StringBuffer sb = new StringBuffer();
		
		for (int i=0; i < this.productType.size(); i++) {
			if (i != 0)
				sb.append(", ");
			
			sb.append("'");
			sb.append(this.productType.elementAt(i));
			sb.append("'");
		}
		
		return sb.toString();
	}
	
	///////////////////////////////////////////////////////////////////////////
	
	private static void test01()
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
	
	private static void test02()
	{
		if (Flag.TRUE) {
			
			JobIdInfo info = new JobIdInfo();
			
			info.setJobId          ("AU010140915A0001"            );
	        
			info.setLineCode       ("L7AFAB"                      );
			info.setAnalMethod     ("FILTER"                      );
			info.setAreaCode       ("MOD"                         );
			info.setSubAreaCode    ("MMT"                         );
			info.setUsl            ("0.8"                         );
			                                                        
			info.addInspectDt      ("20140825050000"              );
			info.addInspectDt      ("20140826045959"              );
			info.addStepId         ("STEP"                        );
			info.addProcessId      ("PROCESS"                     );
			info.addProductId      ("PRODUCT"                     );
			info.addDecisionCode   ("RJ"                          );
			info.addDecisionCode   ("JR"                          );
			info.addDecisionCode   ("JJ"                          );
			info.addUserGroupCode  ("40-FHD-60P-MB4-PVA3-A6P-VNB5");
			info.addDefectGroupCode("UWSP"                        );
			info.addProductType    ("PP"                          );
			info.addProductType    ("TT"                          );
			info.addProductType    ("SS"                          );
			
			for (String[] items : info.getParam()) {
				Print.println("[%s] = [%s]", items[0], items[1]);
			}
		}
	}
	
	public static void main(String[] args)
	{
		if (!Flag.TRUE) test01();
		if (Flag.TRUE) test02();
	}
}
