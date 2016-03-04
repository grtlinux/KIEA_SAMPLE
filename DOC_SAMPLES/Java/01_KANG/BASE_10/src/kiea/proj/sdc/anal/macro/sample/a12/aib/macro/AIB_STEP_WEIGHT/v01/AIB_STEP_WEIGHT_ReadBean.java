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

package kiea.proj.sdc.anal.macro.sample.a12.aib.macro.AIB_STEP_WEIGHT.v01;

import java.util.ArrayList;
import java.util.List;

import kiea.proj.sdc.anal.macro.impl.bean.AbstractBean;

/**
 * @author KangSeok
 * @date 2014. 8. 8.
 * @file_name AIB_STEP_WEIGHT_ReadBean.java
 *
 */
public class AIB_STEP_WEIGHT_ReadBean extends AbstractBean
{
	/*
	"LINE         :Line        ",
	"AREA         :Area        ",
	"DEFECTCD     :DefectCd    ",
	"STEPID       :StepId      ",
	"WEIGHT_VALUE :WeightValue ",
	"DEFECTNM     :DefectNm    ",
	"STEPNM       :StepNm      ",
	"USE_YN       :UseYn       ",
	"REGISTER_DATE:RegisterDate",
	*/

	private String line        ;
	private String area        ;
	private String defectCd    ;
	private String stepId      ;
	private String weightValue ;
	private String defectNm    ;
	private String stepNm      ;
	private String useYn       ;
	private String registerDate;

	public String toString()
	{
		return String.format("READ : [%s] [%s] [%s] [%s] [%s] [%s] [%s] [%s] [%s]"
			, line        
			, area        
			, defectCd    
			, stepId      
			, weightValue 
			, defectNm    
			, stepNm      
			, useYn       
			, registerDate
		);
	}

	public static String[] getHeader()
	{
		List<String> list = new ArrayList<String>();
		list.add("LINE"         );
		list.add("AREA"         );
		list.add("DEFECTCD"     );
		list.add("STEPID"       );
		list.add("WEIGHT_VALUE" );
		list.add("DEFECTNM"     );
		list.add("STEPNM"       );
		list.add("USE_YN"       );
		list.add("REGISTER_DATE");

		return list.toArray(new String[list.size()]);
	}

	public String[] getStringArray()
	{
		List<String> list = new ArrayList<String>();
		list.add(line        );
		list.add(area        );
		list.add(defectCd    );
		list.add(stepId      );
		list.add(weightValue );
		list.add(defectNm    );
		list.add(stepNm      );
		list.add(useYn       );
		list.add(registerDate);

		return list.toArray(new String[list.size()]);
	}

	/**
	 * @return the line
	 */
	public String getLine()
	{
		return line;
	}

	/**
	 * @return the area
	 */
	public String getArea()
	{
		return area;
	}

	/**
	 * @return the defectCd
	 */
	public String getDefectCd()
	{
		return defectCd;
	}

	/**
	 * @return the stepId
	 */
	public String getStepId()
	{
		return stepId;
	}

	/**
	 * @return the weightValue
	 */
	public String getWeightValue()
	{
		return weightValue;
	}

	/**
	 * @return the defectNm
	 */
	public String getDefectNm()
	{
		return defectNm;
	}

	/**
	 * @return the stepNm
	 */
	public String getStepNm()
	{
		return stepNm;
	}

	/**
	 * @return the useYn
	 */
	public String getUseYn()
	{
		return useYn;
	}

	/**
	 * @return the registerDate
	 */
	public String getRegisterDate()
	{
		return registerDate;
	}

	/**
	 * @param line the line to set
	 */
	public void setLine(String line)
	{
		this.line = line;
	}

	/**
	 * @param area the area to set
	 */
	public void setArea(String area)
	{
		this.area = area;
	}

	/**
	 * @param defectCd the defectCd to set
	 */
	public void setDefectCd(String defectCd)
	{
		this.defectCd = defectCd;
	}

	/**
	 * @param stepId the stepId to set
	 */
	public void setStepId(String stepId)
	{
		this.stepId = stepId;
	}

	/**
	 * @param weightValue the weightValue to set
	 */
	public void setWeightValue(String weightValue)
	{
		this.weightValue = weightValue;
	}

	/**
	 * @param defectNm the defectNm to set
	 */
	public void setDefectNm(String defectNm)
	{
		this.defectNm = defectNm;
	}

	/**
	 * @param stepNm the stepNm to set
	 */
	public void setStepNm(String stepNm)
	{
		this.stepNm = stepNm;
	}

	/**
	 * @param useYn the useYn to set
	 */
	public void setUseYn(String useYn)
	{
		this.useYn = useYn;
	}

	/**
	 * @param registerDate the registerDate to set
	 */
	public void setRegisterDate(String registerDate)
	{
		this.registerDate = registerDate;
	}
}
