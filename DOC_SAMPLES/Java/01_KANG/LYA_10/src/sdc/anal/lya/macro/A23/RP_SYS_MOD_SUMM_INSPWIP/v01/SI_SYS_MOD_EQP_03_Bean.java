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

package sdc.anal.lya.macro.A23.RP_SYS_MOD_SUMM_INSPWIP.v01;

import java.util.ArrayList;
import java.util.List;

import kiea.proj.sdc.anal.macro.impl.bean.AbstractBean;

/**
 * @author KangSeok
 * @date 2014. 8. 8.
 * @file_name SI_SYS_MOD_EQP_03_Bean.java
 *
 */
public class SI_SYS_MOD_EQP_03_Bean extends AbstractBean
{
	/*
	"STEP_ID       :StepId      ",
	"EQP_ID        :EqpId       ",
	"BAD_CNT       :BadCnt      ",
	"GOOD_CNT      :GoodCnt     ",
	"BAD_RATIO     :BadRatio    ",
	"GOOD_RATIO    :GoodRatio   ",
	"BAD_GOOD_RATIO:BadGoodRatio",
	*/

	private String stepId      ;
	private String eqpId       ;
	private String badCnt      ;
	private String goodCnt     ;
	private String badRatio    ;
	private String goodRatio   ;
	private String badGoodRatio;

	public String toString()
	{
		return String.format("READ : [%s] [%s] [%s] [%s] [%s] [%s] [%s]"
			, stepId      
			, eqpId       
			, badCnt      
			, goodCnt     
			, badRatio    
			, goodRatio   
			, badGoodRatio
		);
	}

	public static String[] getHeader()
	{
		List<String> list = new ArrayList<String>();
		list.add("STEP_ID"       );
		list.add("EQP_ID"        );
		list.add("BAD_CNT"       );
		list.add("GOOD_CNT"      );
		list.add("BAD_RATIO"     );
		list.add("GOOD_RATIO"    );
		list.add("BAD_GOOD_RATIO");

		return list.toArray(new String[list.size()]);
	}

	public String[] getStringArray()
	{
		List<String> list = new ArrayList<String>();
		list.add(stepId      );
		list.add(eqpId       );
		list.add(badCnt      );
		list.add(goodCnt     );
		list.add(badRatio    );
		list.add(goodRatio   );
		list.add(badGoodRatio);

		return list.toArray(new String[list.size()]);
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
	 * @return the badGoodRatio
	 */
	public String getBadGoodRatio()
	{
		return badGoodRatio;
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
	 * @param badGoodRatio the badGoodRatio to set
	 */
	public void setBadGoodRatio(String badGoodRatio)
	{
		this.badGoodRatio = badGoodRatio;
	}
}
