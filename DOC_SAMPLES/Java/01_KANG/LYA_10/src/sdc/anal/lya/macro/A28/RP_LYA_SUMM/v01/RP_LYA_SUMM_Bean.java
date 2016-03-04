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

package sdc.anal.lya.macro.A28.RP_LYA_SUMM.v01;

import java.util.ArrayList;
import java.util.List;

import kiea.proj.sdc.anal.macro.impl.bean.AbstractBean;

/**
 * @author KangSeok
 * @date 2014. 8. 8.
 * @file_name MURA_SPC_ReadBean.java
 *
 */
public class RP_LYA_SUMM_Bean extends AbstractBean
{
	/*
	"QAF_JOB_BU       :QafJobBu       ",
	"QAF_JOB_ID       :QafJobId       ",
	"ANAL_METHOD      :AnalMethod     ",
	"LINE_CODE        :Line           ",
	"AREA_CODE        :AreaCode       ",
	"검사 STEP        :SubAreaCode    ",
	"USER GROUP       :UserGroupCode  ",
	"제품 TYPE        :ProductType    ",
	"불량 그룹 코드   :DefectGroupCode",
	"불량 그룹 Desc   :DefectGroupDesc",
	"판정 코드        :DecisionCode   ",
	"검사판정시간 FROM:FromDateTime   ",
	"검사판정시간 TO  :ToDateTime     ",
	"PARAMETER_COUNT  :ParameterCount ",
	"USL              :Usl            ",
	"TOT_GLASS_CNT    :TotGlassCnt    ",
	"SYS_GLASS_CNT    :SysGlassCnt    ",
	"RAN_GLASS_CNT    :RanglassCnt    ",
	"Systematic Ratio :SystematicRatio",
	"Random Ratio     :RandomRatio    ",
	"진행 Cell 수     :TotCellCount   ",
	"불량 Cell 수     :BadCellCount   ",
	"Cell 불량률(%)   :BadCellRatio   ",
	*/

	private String qafJobBu       ;
	private String qafJobId       ;
	private String analMethod     ;
	private String line           ;
	private String areaCode       ;
	private String subAreaCode    ;
	private String userGroupCode  ;
	private String productType    ;
	private String defectGroupCode;
	private String defectGroupDesc;
	private String decisionCode   ;
	private String fromDateTime   ;
	private String toDateTime     ;
	private String parameterCount ;
	private String usl            ;
	private String totGlassCnt    ;
	private String sysGlassCnt    ;
	private String ranglassCnt    ;
	private String systematicRatio;
	private String randomRatio    ;
	private String totCellCount   ;
	private String badCellCount   ;
	private String badCellRatio   ;

	public String toString()
	{
		return String.format("READ : [%s] [%s] [%s] [%s] [%s] [%s] [%s] [%s] [%s] [%s] [%s] [%s] [%s] [%s] [%s] [%s] [%s] [%s] [%s] [%s] [%s] [%s] [%s]"
			, qafJobBu       
			, qafJobId       
			, analMethod     
			, line           
			, areaCode       
			, subAreaCode    
			, userGroupCode  
			, productType    
			, defectGroupCode
			, defectGroupDesc
			, decisionCode   
			, fromDateTime   
			, toDateTime     
			, parameterCount 
			, usl            
			, totGlassCnt    
			, sysGlassCnt    
			, ranglassCnt    
			, systematicRatio
			, randomRatio    
			, totCellCount   
			, badCellCount   
			, badCellRatio   
		);
	}

	public static String[] getHeader()
	{
		List<String> list = new ArrayList<String>();
		list.add("QAF_JOB_BU"       );
		list.add("QAF_JOB_ID"       );
		list.add("ANAL_METHOD"      );
		list.add("LINE_CODE"        );
		list.add("AREA_CODE"        );
		list.add("검사 STEP"        );
		list.add("USER GROUP"       );
		list.add("제품 TYPE"        );
		list.add("불량 그룹 코드"   );
		list.add("불량 그룹 Desc"   );
		list.add("판정 코드"        );
		list.add("검사판정시간 FROM");
		list.add("검사판정시간 TO"  );
		list.add("PARAMETER_COUNT"  );
		list.add("USL"              );
		list.add("TOT_GLASS_CNT"    );
		list.add("SYS_GLASS_CNT"    );
		list.add("RAN_GLASS_CNT"    );
		list.add("Systematic Ratio" );
		list.add("Random Ratio"     );
		list.add("진행 Cell 수"     );
		list.add("불량 Cell 수"     );
		list.add("Cell 불량률(%)"   );

		return list.toArray(new String[list.size()]);
	}

	public String[] getStringArray()
	{
		List<String> list = new ArrayList<String>();
		list.add(qafJobBu       );
		list.add(qafJobId       );
		list.add(analMethod     );
		list.add(line           );
		list.add(areaCode       );
		list.add(subAreaCode    );
		list.add(userGroupCode  );
		list.add(productType    );
		list.add(defectGroupCode);
		list.add(defectGroupDesc);
		list.add(decisionCode   );
		list.add(fromDateTime   );
		list.add(toDateTime     );
		list.add(parameterCount );
		list.add(usl            );
		list.add(totGlassCnt    );
		list.add(sysGlassCnt    );
		list.add(ranglassCnt    );
		list.add(systematicRatio);
		list.add(randomRatio    );
		list.add(totCellCount   );
		list.add(badCellCount   );
		list.add(badCellRatio   );

		return list.toArray(new String[list.size()]);
	}

	public String getQafJobBu()
	{
		return qafJobBu;
	}

	public void setQafJobBu(String qafJobBu)
	{
		this.qafJobBu = qafJobBu;
	}

	public String getQafJobId()
	{
		return qafJobId;
	}

	public void setQafJobId(String qafJobId)
	{
		this.qafJobId = qafJobId;
	}

	public String getAnalMethod()
	{
		return analMethod;
	}

	public void setAnalMethod(String analMethod)
	{
		this.analMethod = analMethod;
	}

	public String getLine()
	{
		return line;
	}

	public void setLine(String line)
	{
		this.line = line;
	}

	public String getAreaCode()
	{
		return areaCode;
	}

	public void setAreaCode(String areaCode)
	{
		this.areaCode = areaCode;
	}

	public String getSubAreaCode()
	{
		return subAreaCode;
	}

	public void setSubAreaCode(String subAreaCode)
	{
		this.subAreaCode = subAreaCode;
	}

	public String getUserGroupCode()
	{
		return userGroupCode;
	}

	public void setUserGroupCode(String userGroupCode)
	{
		this.userGroupCode = userGroupCode;
	}

	public String getProductType()
	{
		return productType;
	}

	public void setProductType(String productType)
	{
		this.productType = productType;
	}

	public String getDefectGroupCode()
	{
		return defectGroupCode;
	}

	public void setDefectGroupCode(String defectGroupCode)
	{
		this.defectGroupCode = defectGroupCode;
	}

	public String getDefectGroupDesc()
	{
		return defectGroupDesc;
	}

	public void setDefectGroupDesc(String defectGroupDesc)
	{
		this.defectGroupDesc = defectGroupDesc;
	}

	public String getDecisionCode()
	{
		return decisionCode;
	}

	public void setDecisionCode(String decisionCode)
	{
		this.decisionCode = decisionCode;
	}

	public String getFromDateTime()
	{
		return fromDateTime;
	}

	public void setFromDateTime(String fromDateTime)
	{
		this.fromDateTime = fromDateTime;
	}

	public String getToDateTime()
	{
		return toDateTime;
	}

	public void setToDateTime(String toDateTime)
	{
		this.toDateTime = toDateTime;
	}

	public String getParameterCount()
	{
		return parameterCount;
	}

	public void setParameterCount(String parameterCount)
	{
		this.parameterCount = parameterCount;
	}

	public String getUsl()
	{
		return usl;
	}

	public void setUsl(String usl)
	{
		this.usl = usl;
	}

	public String getTotGlassCnt()
	{
		return totGlassCnt;
	}

	public void setTotGlassCnt(String totGlassCnt)
	{
		this.totGlassCnt = totGlassCnt;
	}

	public String getSysGlassCnt()
	{
		return sysGlassCnt;
	}

	public void setSysGlassCnt(String sysGlassCnt)
	{
		this.sysGlassCnt = sysGlassCnt;
	}

	public String getRanglassCnt()
	{
		return ranglassCnt;
	}

	public void setRanglassCnt(String ranglassCnt)
	{
		this.ranglassCnt = ranglassCnt;
	}

	public String getSystematicRatio()
	{
		return systematicRatio;
	}

	public void setSystematicRatio(String systematicRatio)
	{
		this.systematicRatio = systematicRatio;
	}

	public String getRandomRatio()
	{
		return randomRatio;
	}

	public void setRandomRatio(String randomRatio)
	{
		this.randomRatio = randomRatio;
	}

	public String getTotCellCount()
	{
		return totCellCount;
	}

	public void setTotCellCount(String totCellCount)
	{
		this.totCellCount = totCellCount;
	}

	public String getBadCellCount()
	{
		return badCellCount;
	}

	public void setBadCellCount(String badCellCount)
	{
		this.badCellCount = badCellCount;
	}

	public String getBadCellRatio()
	{
		return badCellRatio;
	}

	public void setBadCellRatio(String badCellRatio)
	{
		this.badCellRatio = badCellRatio;
	}
}
