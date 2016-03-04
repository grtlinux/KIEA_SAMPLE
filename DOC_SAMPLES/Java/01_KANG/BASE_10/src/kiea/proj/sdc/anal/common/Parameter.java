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

package kiea.proj.sdc.anal.common;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import kiea.kr.co.tain.base.Flag;
import kiea.kr.co.tain.util.Print;
import kiea.kr.co.tain.util.SystemProperties;
import kiea.proj.sdc.anal.util.BasePath;
import kiea.proj.sdc.anal.util.StrUtil;
import kiea.proj.sdc.anal.util.log.v02.Logger;

/**
 * @author KangSeok
 * @date 2014. 8. 11.
 * @file_name Parameter.java
 *
 */
public class Parameter
{
	private String jobId = null;
	private String filePath = null;
	private ParameterJobIdCsv csv = null;
	private List<ParameterJobIdBean> listBean = null;
	private Map<String, List<ParameterJobIdBean>> mapParam = new LinkedHashMap<String, List<ParameterJobIdBean>>();
	
	private String strJobId           = "";
	private String strAnalMethod      = "";
	private String strAreaCode        = "";
	private String strSubAreaCode     = "";
	
	private String strFromDateTime    = "";
	private String strToDateTime      = "";
	private String strLine            = "";

	private String strDecisionCode    = "";
	private String strUserGroupCode   = "";
	private String strDefectGroupCode = "";
	private String strProductType     = "";
	
	private String strUsl             = "";

	private String strStepId          = "";
	private String strProcessId       = "";
	private String strProductId       = "";
	
	///////////////////////////////////////////////////////////////////////////
	
	public String getStrJobId()
	{
		return strJobId;
	}

	public void setStrJobId(String strJobId)
	{
		this.strJobId = strJobId;
	}

	public String getStrAnalMethod()
	{
		return strAnalMethod;
	}

	public void setStrAnalMethod(String strAnalMethod)
	{
		this.strAnalMethod = strAnalMethod;
	}

	public String getStrAreaCode()
	{
		return strAreaCode;
	}

	public void setStrAreaCode(String strAreaCode)
	{
		this.strAreaCode = strAreaCode;
	}

	public String getStrSubAreaCode()
	{
		return strSubAreaCode;
	}

	public void setStrSubAreaCode(String strSubAreaCode)
	{
		this.strSubAreaCode = strSubAreaCode;
	}

	public String getStrFromDateTime()
	{
		return strFromDateTime;
	}

	public void setStrFromDateTime(String strFromDateTime)
	{
		this.strFromDateTime = strFromDateTime;
	}

	public String getStrToDateTime()
	{
		return strToDateTime;
	}

	public void setStrToDateTime(String strToDateTime)
	{
		this.strToDateTime = strToDateTime;
	}

	public String getStrLine()
	{
		return strLine;
	}

	public void setStrLine(String strLine)
	{
		this.strLine = strLine;
	}

	public String getStrDecisionCode()
	{
		return strDecisionCode;
	}

	public void setStrDecisionCode(String strDecisionCode)
	{
		this.strDecisionCode = strDecisionCode;
	}

	public String getStrUserGroupCode()
	{
		return strUserGroupCode;
	}

	public void setStrUserGroupCode(String strUserGroupCode)
	{
		this.strUserGroupCode = strUserGroupCode;
	}

	public String getStrDefectGroupCode()
	{
		return strDefectGroupCode;
	}

	public void setStrDefectGroupCode(String strDefectGroupCode)
	{
		this.strDefectGroupCode = strDefectGroupCode;
	}

	public String getStrProductType()
	{
		return strProductType;
	}

	public void setStrProductType(String strProductType)
	{
		this.strProductType = strProductType;
	}

	public String getStrUsl()
	{
		return strUsl;
	}

	public void setStrUsl(String strUsl)
	{
		this.strUsl = strUsl;
	}

	public String getStrStepId()
	{
		return strStepId;
	}

	public void setStrStepId(String strStepId)
	{
		this.strStepId = strStepId;
	}

	public String getStrProcessId()
	{
		return strProcessId;
	}

	public void setStrProcessId(String strProcessId)
	{
		this.strProcessId = strProcessId;
	}

	public String getStrProductId()
	{
		return strProductId;
	}

	public void setStrProductId(String strProductId)
	{
		this.strProductId = strProductId;
	}

	///////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////

	private void arrange()
	{
		if (Flag.TRUE) {
			/*
			 * clear
			 */
			this.mapParam.clear();
		}
		
		if (Flag.TRUE) {
			/*
			 * 정리-1
			 */
			for (ParameterJobIdBean bean : this.listBean) {
				List<ParameterJobIdBean> list = this.mapParam.get(bean.getParamNm());
				if (list == null) {
					list = new ArrayList<ParameterJobIdBean>();
					this.mapParam.put(bean.getParamNm(), list);
				}
				
				list.add(bean);
			}
		}
		
		if (Flag.TRUE) {
			/*
			 * 정리-2
			 * 중복이면 중복, 단건이면 중첩
			 * 추가되는 인자는 기본이 중복처리
			 */
			this.strJobId = this.jobId;
			
			for (Map.Entry<String, List<ParameterJobIdBean>> entry : this.mapParam.entrySet()) {
				String key = entry.getKey();
				List<ParameterJobIdBean> list = entry.getValue();
				if (list == null || list.size() == 0)
					continue;
				
				if ("LINE_CODE".equals(key)) {
					this.strLine = list.get(0).getParamVal();
				} else if ("ANAL_METHOD".equals(key)) {
					this.strAnalMethod = list.get(0).getParamVal();
				} else if ("AREA_CODE".equals(key)) {
					this.strAreaCode = list.get(0).getParamVal();
				} else if ("SUB_AREA_CODE".equals(key)) {
					this.strSubAreaCode = list.get(0).getParamVal();
				} else if ("USL".equals(key)) {
					this.strUsl = list.get(0).getParamVal();
				} else if ("INSPECT_DT".equals(key)) {
					if (Flag.TRUE) {
						if (list.size() == 1) {
							this.strFromDateTime = list.get(0).getParamVal();
							this.strToDateTime = list.get(0).getParamVal();
						} else if (list.size() >= 2) {
							if (list.get(0).getParamVal().compareTo(list.get(1).getParamVal()) < 0) {
								this.strFromDateTime = list.get(0).getParamVal();
								this.strToDateTime = list.get(1).getParamVal();
							} else {
								this.strFromDateTime = list.get(1).getParamVal();
								this.strToDateTime = list.get(0).getParamVal();
							}
						} else {
							this.strFromDateTime = "";
							this.strToDateTime = "";
						}
					}
				} else if ("STEP_ID".equals(key)) {
					StringBuffer sb = new StringBuffer();
					
					for (int i=0; i < list.size(); i++) {
						if (i > 0)
							sb.append(",");
						
						sb.append("'");
						sb.append(list.get(i).getParamVal());
						sb.append("'");
					}
					
					this.strStepId = sb.toString();
				} else if ("PROCESS_ID".equals(key)) {
					StringBuffer sb = new StringBuffer();
					
					for (int i=0; i < list.size(); i++) {
						if (i > 0)
							sb.append(",");
						
						sb.append("'");
						sb.append(list.get(i).getParamVal());
						sb.append("'");
					}
					
					this.strProcessId = sb.toString();
				} else if ("PRODUCT_ID".equals(key)) {
					StringBuffer sb = new StringBuffer();
					
					for (int i=0; i < list.size(); i++) {
						if (i > 0)
							sb.append(",");
						
						sb.append("'");
						sb.append(list.get(i).getParamVal());
						sb.append("'");
					}
					
					this.strProductId = sb.toString();
				} else if ("DECISION_CODE".equals(key)) {
					StringBuffer sb = new StringBuffer();
					
					for (int i=0; i < list.size(); i++) {
						if (i > 0)
							sb.append(",");
						
						sb.append("'");
						sb.append(list.get(i).getParamVal());
						sb.append("'");
					}
					
					this.strDecisionCode = sb.toString();
				} else if ("USER_GROUP_CODE".equals(key)) {
					StringBuffer sb = new StringBuffer();
					
					for (int i=0; i < list.size(); i++) {
						if (i > 0)
							sb.append(",");
						
						sb.append("'");
						sb.append(list.get(i).getParamVal());
						sb.append("'");
					}
					
					this.strUserGroupCode = sb.toString();
				} else if ("DEFECT_GROUP_CODE".equals(key)) {
					StringBuffer sb = new StringBuffer();
					
					for (int i=0; i < list.size(); i++) {
						if (i > 0)
							sb.append(",");
						
						sb.append("'");
						sb.append(list.get(i).getParamVal());
						sb.append("'");
					}
					
					this.strDefectGroupCode = sb.toString();
				} else if ("PRODUCT_TYPE".equals(key)) {
					StringBuffer sb = new StringBuffer();
					
					for (int i=0; i < list.size(); i++) {
						if (i > 0)
							sb.append(",");
						
						sb.append("'");
						sb.append(list.get(i).getParamVal());
						sb.append("'");
					}
					
					this.strProductType = sb.toString();
				}
			}
		}
	}
	
	private Parameter(String jobId)
	{
		if (Flag.TRUE) {
			try {
				if (Flag.TRUE) {
					/*
					 * 기초 자료
					 */
					this.jobId = jobId;
					this.filePath = BasePath.getInstance().getDataPath() + "/" + StrUtil.getDateFromJobId(this.jobId) + "/" + this.jobId;
					this.csv = new ParameterJobIdCsv(this.filePath);
					this.listBean = this.csv.getList(true);
				}

				if (Flag.TRUE) arrange();
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		if (Flag.TRUE) {
			/*
			 * 기본 인자 Loger 출력
			 */
			StringBuffer sb = new StringBuffer();
			sb.append("### PARAMETERS ###\n");
			sb.append(String.format("jobId                   = [%s]\n", jobId                   ));
			sb.append(String.format("this.strLine            = [%s]\n", this.strLine           ));
			sb.append(String.format("this.strAnalMethod      = [%s]\n", this.strAnalMethod     ));
			sb.append(String.format("this.strAreaCode        = [%s]\n", this.strAreaCode       ));
			sb.append(String.format("this.strSubAreaCode     = [%s]\n", this.strSubAreaCode    ));
			sb.append(String.format("this.strUsl             = [%s]\n", this.strUsl            ));
			sb.append(String.format("this.strFromDateTime    = [%s]\n", this.strFromDateTime   ));
			sb.append(String.format("this.strToDateTime      = [%s]\n", this.strToDateTime     ));
			sb.append(String.format("this.strStepId          = [%s]\n", this.strStepId         ));
			sb.append(String.format("this.strProcessId       = [%s]\n", this.strProcessId      ));
			sb.append(String.format("this.strProductId       = [%s]\n", this.strProductId      ));
			sb.append(String.format("this.strDecisionCode    = [%s]\n", this.strDecisionCode   ));
			sb.append(String.format("this.strUserGroupCode   = [%s]\n", this.strUserGroupCode  ));
			sb.append(String.format("this.strDefectGroupCode = [%s]\n", this.strDefectGroupCode));
			sb.append(String.format("this.strProductType     = [%s]\n", this.strProductType    ));
			
			if (Flag.TRUE) Logger.info(sb.toString());
			if (!Flag.TRUE) System.out.println(sb.toString());
		}
	}
	
	///////////////////////////////////////////////////////////////////////////
	
	public void printParams()
	{
		if (Flag.TRUE) {
			for (ParameterJobIdBean bean : listBean) {
				System.out.println(bean);
			}
		}
	}
	
	public void setParam(String key, String val)
	{
		if (Flag.TRUE) {
			//ParameterJobIdBean bean = new ParameterJobIdBean(this.jobId, "", key, val);
			ParameterJobIdBean bean = new ParameterJobIdBean();
			bean.setJobId(this.jobId);
			bean.setSeq("");
			bean.setParamNm(key);
			bean.setParamVal(val);
			
			if ("LINE_CODE".equals(key)
			|| "ANAL_METHOD".equals(key)
			|| "AREA_CODE".equals(key)
			|| "SUB_AREA_CODE".equals(key)
			|| "USL".equals(key)
			|| "INSPECT_DT".equals(key)
			|| "STEP_ID".equals(key)
			|| "PROCESS_ID".equals(key)
			|| "PRODUCT_ID".equals(key)
			|| "DECISION_CODE".equals(key)
			|| "USER_GROUP_CODE".equals(key)
			|| "DEFECT_GROUP_CODE".equals(key)
			|| "PRODUCT_TYPE".equals(key)) {
				/*
				 * 기본인자 중복
				 */
			} else {
				List<ParameterJobIdBean> list = mapParam.get(bean.getParamNm());
				if (list == null) {
					list = new ArrayList<ParameterJobIdBean>();
					mapParam.put(key, list);
				} else {
					//list.clear();
				}
				list.add(bean);
				
				this.listBean.add(bean);
				this.csv.writeList();
			}
		}
	}
	
	public String getParam(String key) 
	{
		String ret = "";
		
		if (Flag.TRUE) {
			List<ParameterJobIdBean> list = mapParam.get(key);
			if (list != null) {
				ret = list.get(list.size()-1).getParamVal();  // 마지막 자료
			}
		}
		
		return ret;
	}
	
	public String getParam(String key, int idx) 
	{
		String ret = "";
		
		if (Flag.TRUE) {
			List<ParameterJobIdBean> list = mapParam.get(key);
			if (list != null && 0 <= idx && idx < list.size()) {
				ret = list.get(idx).getParamVal();     // 특정 위치 자료
			}
		}
		
		return ret;
	}
	
	///////////////////////////////////////////////////////////////////////////

	private static Parameter instance = null;
	
	public static synchronized Parameter getInstance(String jobId)
	{
		if (instance == null) {
			instance = new Parameter(jobId);
		}
		
		return instance;
	}

	public static Parameter getInstance()
	{
		return instance;
	}
	
	public static Parameter getInstance(int no)
	{
		return instance;
	}
	
	///////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////
	
	private static void test01()
	{
		String strJobId = "AR010141110A3144";

		if (Flag.TRUE) {
			SystemProperties.setTesting("010");
			BasePath.getInstance();
			try {
				Logger.getInstance(strJobId, "A01_JOBID_PARAM.log");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		if (Flag.TRUE) {
			/*
			 * 기본 인자
			 */
			Parameter.getInstance(strJobId);
			//Parameter.getInstance().printParams();

			Print.println("strJobId           = [%s]", Parameter.getInstance().getStrJobId          ());
			Print.println("strLine            = [%s]", Parameter.getInstance().getStrLine           ());
			Print.println("strAnalMethod      = [%s]", Parameter.getInstance().getStrAnalMethod     ());
			Print.println("strAreaCode        = [%s]", Parameter.getInstance().getStrAreaCode       ());
			Print.println("strSubAreaCode     = [%s]", Parameter.getInstance().getStrSubAreaCode    ());
			Print.println("strUsl             = [%s]", Parameter.getInstance().getStrUsl            ());
			Print.println("strFromDateTime    = [%s]", Parameter.getInstance().getStrFromDateTime   ());
			Print.println("strToDateTime      = [%s]", Parameter.getInstance().getStrToDateTime     ());
			Print.println("strStepId          = [%s]", Parameter.getInstance().getStrStepId         ());
			Print.println("strProcessId       = [%s]", Parameter.getInstance().getStrProcessId      ());
			Print.println("strProductId       = [%s]", Parameter.getInstance().getStrProductId      ());
			Print.println("strDecisionCode    = [%s]", Parameter.getInstance().getStrDecisionCode   ());
			Print.println("strUserGroupCode   = [%s]", Parameter.getInstance().getStrUserGroupCode  ());
			Print.println("strDefectGroupCode = [%s]", Parameter.getInstance().getStrDefectGroupCode());
			Print.println("strProductType     = [%s]", Parameter.getInstance().getStrProductType    ());
		}
		
		if (Flag.TRUE) {
			/*
			 * 인자 추가
			 */
			System.out.println("Before SET KANG =>" + Parameter.getInstance().getParam("KANG"));

			//Parameter.getInstance().setParam("KANG", "1111,2222,3333;444444,555,  666;;777,,888;;,,,123;123456");  // 곧바로 CSV 파일에 기록한다.
			Parameter.getInstance().setParam("KANG", "1111");  // 곧바로 CSV 파일에 기록한다.
			//Parameter.getInstance().printParams();  // Parameter 객체의 모든 인자를 출력

			System.out.println("After SET KANG =>" + Parameter.getInstance().getParam("KANG"));
		}
		
		if (Flag.TRUE) {
			/*
			 * 2차배열을 얻고 출력한다.
			 */
			
			StrUtil.printArrString(StrUtil.getArrString(Parameter.getInstance().getParam("KANG")));
		}
		
		Logger.exit();
	}
	
	public static void main(String[] args)
	{
		if (Flag.TRUE) test01();
	}
}
