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

package kiea.proj.sdc.anal.macro.sample.a11.wip.macro.RP_MUR_WIP_SUMM.v01;

import java.util.ArrayList;
import java.util.List;

import kiea.proj.sdc.anal.macro.impl.bean.AbstractBean;

/**
 * @author KangSeok
 * @date 2014. 8. 8.
 * @file_name RP_MUR_WIP_SUMM_Bean.java
 *
 */
public class RP_MUR_WIP_SUMM_Bean extends AbstractBean
{
	/*
	"GROUP_NUM :GroupNum ",
	"STEP_ID   :StepId   ",
	"EQP_ID    :EqpId    ",
	"BAD_CNT   :BadCnt   ",
	"GOOD_CNT  :GoodCnt  ",
	"GROUP_CNT :GroupCnt ",
	"BAD_RATIO :BadRaio  ",
	"GOOD_RATIO:GoodRatio",
	"B_G_RATIO :BGRatio  ",
	*/

	private String groupNum ;
	private String stepId   ;
	private String eqpId    ;
	private String badCnt   ;
	private String goodCnt  ;
	private String groupCnt ;
	private String badRaio  ;
	private String goodRatio;
	private String BGRatio  ;

	public String toString()
	{
		return String.format("READ : [%s] [%s] [%s] [%s] [%s] [%s] [%s] [%s] [%s]"
			, groupNum 
			, stepId   
			, eqpId    
			, badCnt   
			, goodCnt  
			, groupCnt 
			, badRaio  
			, goodRatio
			, BGRatio  
		);
	}

	public static String[] getHeader()
	{
		List<String> list = new ArrayList<String>();
		list.add("GROUP_NUM" );
		list.add("STEP_ID"   );
		list.add("EQP_ID"    );
		list.add("BAD_CNT"   );
		list.add("GOOD_CNT"  );
		list.add("GROUP_CNT" );
		list.add("BAD_RATIO" );
		list.add("GOOD_RATIO");
		list.add("B_G_RATIO" );

		return list.toArray(new String[list.size()]);
	}

	public String[] getStringArray()
	{
		List<String> list = new ArrayList<String>();
		list.add(groupNum );
		list.add(stepId   );
		list.add(eqpId    );
		list.add(badCnt   );
		list.add(goodCnt  );
		list.add(groupCnt );
		list.add(badRaio  );
		list.add(goodRatio);
		list.add(BGRatio  );

		return list.toArray(new String[list.size()]);
	}

	/**
	 * @return the groupNum
	 */
	public String getGroupNum()
	{
		return groupNum;
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
	 * @return the groupCnt
	 */
	public String getGroupCnt()
	{
		return groupCnt;
	}

	/**
	 * @return the badRaio
	 */
	public String getBadRaio()
	{
		return badRaio;
	}

	/**
	 * @return the goodRatio
	 */
	public String getGoodRatio()
	{
		return goodRatio;
	}

	/**
	 * @return the bGRatio
	 */
	public String getBGRatio()
	{
		return BGRatio;
	}

	/**
	 * @param groupNum the groupNum to set
	 */
	public void setGroupNum(String groupNum)
	{
		this.groupNum = groupNum;
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
	 * @param groupCnt the groupCnt to set
	 */
	public void setGroupCnt(String groupCnt)
	{
		this.groupCnt = groupCnt;
	}

	/**
	 * @param badRaio the badRaio to set
	 */
	public void setBadRaio(String badRaio)
	{
		this.badRaio = badRaio;
	}

	/**
	 * @param goodRatio the goodRatio to set
	 */
	public void setGoodRatio(String goodRatio)
	{
		this.goodRatio = goodRatio;
	}

	/**
	 * @param bGRatio the bGRatio to set
	 */
	public void setBGRatio(String bGRatio)
	{
		BGRatio = bGRatio;
	}
}
