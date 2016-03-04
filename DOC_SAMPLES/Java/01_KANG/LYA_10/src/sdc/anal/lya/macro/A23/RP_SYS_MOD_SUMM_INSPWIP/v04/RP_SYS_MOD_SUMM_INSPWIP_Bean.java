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

package sdc.anal.lya.macro.A23.RP_SYS_MOD_SUMM_INSPWIP.v04;

import java.util.ArrayList;
import java.util.List;

import kiea.proj.sdc.anal.macro.impl.bean.AbstractBean;

/**
 * @author KangSeok
 * @date 2014. 8. 8.
 * @file_name RP_SYS_MOD_SUMM_INSPWIP_Bean.java
 *
 */
public class RP_SYS_MOD_SUMM_INSPWIP_Bean extends AbstractBean
{
	/*
	"ROLE                   :Role                  ",
	"TYPE_GUBUN             :TypeGubun             ",
	"MISSCLASSIFICATION_RATE:MissclassificationRate",
	"WEIGHTD_B_G            :WeightDBG             ",
	"STEP_WIGHT             :StepWight             ",
	"BAD_GOOD_RATIO         :BadGoodRatio          ",
	"BAD_RATIO              :BadRatio              ",
	"GOOD_RATIO             :GoodRatio             ",
	"BAD_CNT                :BadCnt                ",
	"GOOD_CNT               :GoodCnt               ",
	"TOT_BAD_CNT            :TotBadCnt             ",
	"TOT_GOOD_CNT           :TotGoodCnt            ",
	"GROUP_NUM              :GroupNum              ",
	"NAME                   :Name                  ",
	"DIV_CODE               :DivCode               ",
	"STEP_ID                :StepId                ",
	"EQP_ID                 :EqpId                 ",
	"STEP_RATE              :StepRate              ",
	*/

	private String role                  ;
	private String typeGubun             ;
	private String missclassificationRate;
	private String weightDBG             ;
	private String stepWight             ;
	private String badGoodRatio          ;
	private String badRatio              ;
	private String goodRatio             ;
	private String badCnt                ;
	private String goodCnt               ;
	private String totBadCnt             ;
	private String totGoodCnt            ;
	private String groupNum              ;
	private String name                  ;
	private String divCode               ;
	private String stepId                ;
	private String eqpId                 ;
	private String stepRate              ;

	public String toString()
	{
		return String.format("READ : [%s] [%s] [%s] [%s] [%s] [%s] [%s] [%s] [%s] [%s] [%s] [%s] [%s] [%s] [%s] [%s] [%s] [%s]"
			, role                  
			, typeGubun             
			, missclassificationRate
			, weightDBG             
			, stepWight             
			, badGoodRatio          
			, badRatio              
			, goodRatio             
			, badCnt                
			, goodCnt               
			, totBadCnt             
			, totGoodCnt            
			, groupNum              
			, name                  
			, divCode               
			, stepId                
			, eqpId                 
			, stepRate              
		);
	}

	public static String[] getHeader()
	{
		List<String> list = new ArrayList<String>();
		list.add("ROLE"                   );
		list.add("TYPE_GUBUN"             );
		list.add("MISSCLASSIFICATION_RATE");
		list.add("WEIGHTD_B_G"            );
		list.add("STEP_WIGHT"             );
		list.add("BAD_GOOD_RATIO"         );
		list.add("BAD_RATIO"              );
		list.add("GOOD_RATIO"             );
		list.add("BAD_CNT"                );
		list.add("GOOD_CNT"               );
		list.add("TOT_BAD_CNT"            );
		list.add("TOT_GOOD_CNT"           );
		list.add("GROUP_NUM"              );
		list.add("NAME"                   );
		list.add("DIV_CODE"               );
		list.add("STEP_ID"                );
		list.add("EQP_ID"                 );
		list.add("STEP_RATE"              );

		return list.toArray(new String[list.size()]);
	}

	public String[] getStringArray()
	{
		List<String> list = new ArrayList<String>();
		list.add(role                  );
		list.add(typeGubun             );
		list.add(missclassificationRate);
		list.add(weightDBG             );
		list.add(stepWight             );
		list.add(badGoodRatio          );
		list.add(badRatio              );
		list.add(goodRatio             );
		list.add(badCnt                );
		list.add(goodCnt               );
		list.add(totBadCnt             );
		list.add(totGoodCnt            );
		list.add(groupNum              );
		list.add(name                  );
		list.add(divCode               );
		list.add(stepId                );
		list.add(eqpId                 );
		list.add(stepRate              );

		return list.toArray(new String[list.size()]);
	}

	/**
	 * @return the role
	 */
	public String getRole()
	{
		return role;
	}

	/**
	 * @return the typeGubun
	 */
	public String getTypeGubun()
	{
		return typeGubun;
	}

	/**
	 * @return the missclassificationRate
	 */
	public String getMissclassificationRate()
	{
		return missclassificationRate;
	}

	/**
	 * @return the weightDBG
	 */
	public String getWeightDBG()
	{
		return weightDBG;
	}

	/**
	 * @return the stepWight
	 */
	public String getStepWight()
	{
		return stepWight;
	}

	/**
	 * @return the badGoodRatio
	 */
	public String getBadGoodRatio()
	{
		return badGoodRatio;
	}

	/**
	 * @return the badRatio
	 */
	public String getBadRatio()
	{
		return badRatio;
	}

	/**
	 * @return the goodRatio
	 */
	public String getGoodRatio()
	{
		return goodRatio;
	}

	/**
	 * @return the badCnt
	 */
	public String getBadCnt()
	{
		return badCnt;
	}

	/**
	 * @return the goodCnt
	 */
	public String getGoodCnt()
	{
		return goodCnt;
	}

	/**
	 * @return the totBadCnt
	 */
	public String getTotBadCnt()
	{
		return totBadCnt;
	}

	/**
	 * @return the totGoodCnt
	 */
	public String getTotGoodCnt()
	{
		return totGoodCnt;
	}

	/**
	 * @return the groupNum
	 */
	public String getGroupNum()
	{
		return groupNum;
	}

	/**
	 * @return the name
	 */
	public String getName()
	{
		return name;
	}

	/**
	 * @return the divCode
	 */
	public String getDivCode()
	{
		return divCode;
	}

	/**
	 * @return the stepId
	 */
	public String getStepId()
	{
		return stepId;
	}

	/**
	 * @return the eqpId
	 */
	public String getEqpId()
	{
		return eqpId;
	}

	/**
	 * @return the stepRate
	 */
	public String getStepRate()
	{
		return stepRate;
	}

	/**
	 * @param role the role to set
	 */
	public void setRole(String role)
	{
		this.role = role;
	}

	/**
	 * @param typeGubun the typeGubun to set
	 */
	public void setTypeGubun(String typeGubun)
	{
		this.typeGubun = typeGubun;
	}

	/**
	 * @param missclassificationRate the missclassificationRate to set
	 */
	public void setMissclassificationRate(String missclassificationRate)
	{
		this.missclassificationRate = missclassificationRate;
	}

	/**
	 * @param weightDBG the weightDBG to set
	 */
	public void setWeightDBG(String weightDBG)
	{
		this.weightDBG = weightDBG;
	}

	/**
	 * @param stepWight the stepWight to set
	 */
	public void setStepWight(String stepWight)
	{
		this.stepWight = stepWight;
	}

	/**
	 * @param badGoodRatio the badGoodRatio to set
	 */
	public void setBadGoodRatio(String badGoodRatio)
	{
		this.badGoodRatio = badGoodRatio;
	}

	/**
	 * @param badRatio the badRatio to set
	 */
	public void setBadRatio(String badRatio)
	{
		this.badRatio = badRatio;
	}

	/**
	 * @param goodRatio the goodRatio to set
	 */
	public void setGoodRatio(String goodRatio)
	{
		this.goodRatio = goodRatio;
	}

	/**
	 * @param badCnt the badCnt to set
	 */
	public void setBadCnt(String badCnt)
	{
		this.badCnt = badCnt;
	}

	/**
	 * @param goodCnt the goodCnt to set
	 */
	public void setGoodCnt(String goodCnt)
	{
		this.goodCnt = goodCnt;
	}

	/**
	 * @param totBadCnt the totBadCnt to set
	 */
	public void setTotBadCnt(String totBadCnt)
	{
		this.totBadCnt = totBadCnt;
	}

	/**
	 * @param totGoodCnt the totGoodCnt to set
	 */
	public void setTotGoodCnt(String totGoodCnt)
	{
		this.totGoodCnt = totGoodCnt;
	}

	/**
	 * @param groupNum the groupNum to set
	 */
	public void setGroupNum(String groupNum)
	{
		this.groupNum = groupNum;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name)
	{
		this.name = name;
	}

	/**
	 * @param divCode the divCode to set
	 */
	public void setDivCode(String divCode)
	{
		this.divCode = divCode;
	}

	/**
	 * @param stepId the stepId to set
	 */
	public void setStepId(String stepId)
	{
		this.stepId = stepId;
	}

	/**
	 * @param eqpId the eqpId to set
	 */
	public void setEqpId(String eqpId)
	{
		this.eqpId = eqpId;
	}

	/**
	 * @param stepRate the stepRate to set
	 */
	public void setStepRate(String stepRate)
	{
		this.stepRate = stepRate;
	}
}
