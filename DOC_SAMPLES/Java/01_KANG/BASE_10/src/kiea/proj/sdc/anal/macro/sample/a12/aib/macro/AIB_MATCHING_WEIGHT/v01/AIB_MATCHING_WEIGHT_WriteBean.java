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

package kiea.proj.sdc.anal.macro.sample.a12.aib.macro.AIB_MATCHING_WEIGHT.v01;

import java.util.ArrayList;
import java.util.List;

import kiea.proj.sdc.anal.macro.impl.bean.AbstractBean;

/**
 * @author KangSeok
 * @date 2014. 8. 8.
 * @file_name AIB_MATCHING_WEIGHT_WriteBean.java
 *
 */
public class AIB_MATCHING_WEIGHT_WriteBean extends AbstractBean
{
	/*
	"SITEID               :SiteId            ",
	"DEFECTCD             :DefectCd          ",
	"DETECT_TOP_STEP_ID   :DetectTopStepId   ",
	"DETECT_BOTTOM_STEP_ID:DetectBottomStepId",
	"APPL_TOP_STEP_ID     :ApplTopStepId     ",
	"APPL_BOTTOM_STEP_ID  :ApplBottomStepId  ",
	"WEIGHT               :Weight            ",
	"USE_YN               :UseYn             ",
	"REGISTER_DATE        :RegisterDate      ",
	*/

	private String siteId            ;
	private String defectCd          ;
	private String detectTopStepId   ;
	private String detectBottomStepId;
	private String applTopStepId     ;
	private String applBottomStepId  ;
	private String weight            ;
	private String useYn             ;
	private String registerDate      ;

	public String toString()
	{
		return String.format("READ : [%s] [%s] [%s] [%s] [%s] [%s] [%s] [%s] [%s]"
			, siteId            
			, defectCd          
			, detectTopStepId   
			, detectBottomStepId
			, applTopStepId     
			, applBottomStepId  
			, weight            
			, useYn             
			, registerDate      
		);
	}

	public static String[] getHeader()
	{
		List<String> list = new ArrayList<String>();
		list.add("SITEID"               );
		list.add("DEFECTCD"             );
		list.add("DETECT_TOP_STEP_ID"   );
		list.add("DETECT_BOTTOM_STEP_ID");
		list.add("APPL_TOP_STEP_ID"     );
		list.add("APPL_BOTTOM_STEP_ID"  );
		list.add("WEIGHT"               );
		list.add("USE_YN"               );
		list.add("REGISTER_DATE"        );

		return list.toArray(new String[list.size()]);
	}

	public String[] getStringArray()
	{
		List<String> list = new ArrayList<String>();
		list.add(siteId            );
		list.add(defectCd          );
		list.add(detectTopStepId   );
		list.add(detectBottomStepId);
		list.add(applTopStepId     );
		list.add(applBottomStepId  );
		list.add(weight            );
		list.add(useYn             );
		list.add(registerDate      );

		return list.toArray(new String[list.size()]);
	}

	/**
	 * @return the siteId
	 */
	public String getSiteId()
	{
		return siteId;
	}

	/**
	 * @return the defectCd
	 */
	public String getDefectCd()
	{
		return defectCd;
	}

	/**
	 * @return the detectTopStepId
	 */
	public String getDetectTopStepId()
	{
		return detectTopStepId;
	}

	/**
	 * @return the detectBottomStepId
	 */
	public String getDetectBottomStepId()
	{
		return detectBottomStepId;
	}

	/**
	 * @return the applTopStepId
	 */
	public String getApplTopStepId()
	{
		return applTopStepId;
	}

	/**
	 * @return the applBottomStepId
	 */
	public String getApplBottomStepId()
	{
		return applBottomStepId;
	}

	/**
	 * @return the weight
	 */
	public String getWeight()
	{
		return weight;
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
	 * @param siteId the siteId to set
	 */
	public void setSiteId(String siteId)
	{
		this.siteId = siteId;
	}

	/**
	 * @param defectCd the defectCd to set
	 */
	public void setDefectCd(String defectCd)
	{
		this.defectCd = defectCd;
	}

	/**
	 * @param detectTopStepId the detectTopStepId to set
	 */
	public void setDetectTopStepId(String detectTopStepId)
	{
		this.detectTopStepId = detectTopStepId;
	}

	/**
	 * @param detectBottomStepId the detectBottomStepId to set
	 */
	public void setDetectBottomStepId(String detectBottomStepId)
	{
		this.detectBottomStepId = detectBottomStepId;
	}

	/**
	 * @param applTopStepId the applTopStepId to set
	 */
	public void setApplTopStepId(String applTopStepId)
	{
		this.applTopStepId = applTopStepId;
	}

	/**
	 * @param applBottomStepId the applBottomStepId to set
	 */
	public void setApplBottomStepId(String applBottomStepId)
	{
		this.applBottomStepId = applBottomStepId;
	}

	/**
	 * @param weight the weight to set
	 */
	public void setWeight(String weight)
	{
		this.weight = weight;
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
