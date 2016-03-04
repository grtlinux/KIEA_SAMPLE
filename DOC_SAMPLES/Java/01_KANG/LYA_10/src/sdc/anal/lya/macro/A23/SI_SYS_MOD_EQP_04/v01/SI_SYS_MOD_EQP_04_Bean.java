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

package sdc.anal.lya.macro.A23.SI_SYS_MOD_EQP_04.v01;

import java.util.ArrayList;
import java.util.List;

import kiea.proj.sdc.anal.macro.impl.bean.AbstractBean;

/**
 * @author KangSeok
 * @date 2014. 8. 8.
 * @file_name SI_SYS_MOD_EQP_04_Bean.java
 *
 */
public class SI_SYS_MOD_EQP_04_Bean extends AbstractBean
{
	/*
	"STEP_ID      :StepId      ",
	"EQP_ID       :EqpId       ",
	"INSPECT_HOUR3:InspectHour3",
	"TOT_CNT      :TotCnt      ",
	"BAD_CNT      :BadCnt      ",
	"GOOD_CNT     :GoodCnt     ",
	*/

	private String stepId      ;
	private String eqpId       ;
	private String inspectHour3;
	private String totCnt      ;
	private String badCnt      ;
	private String goodCnt     ;

	public String toString()
	{
		return String.format("READ : [%s] [%s] [%s] [%s] [%s] [%s]"
			, stepId      
			, eqpId       
			, inspectHour3
			, totCnt      
			, badCnt      
			, goodCnt     
		);
	}

	public static String[] getHeader()
	{
		List<String> list = new ArrayList<String>();
		list.add("STEP_ID"      );
		list.add("EQP_ID"       );
		list.add("INSPECT_HOUR3");
		list.add("TOT_CNT"      );
		list.add("BAD_CNT"      );
		list.add("GOOD_CNT"     );

		return list.toArray(new String[list.size()]);
	}

	public String[] getStringArray()
	{
		List<String> list = new ArrayList<String>();
		list.add(stepId      );
		list.add(eqpId       );
		list.add(inspectHour3);
		list.add(totCnt      );
		list.add(badCnt      );
		list.add(goodCnt     );

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
	 * @return the inspectHour3
	 */
	public String getInspectHour3()
	{
		return inspectHour3;
	}

	/**
	 * @return the totCnt
	 */
	public String getTotCnt()
	{
		return totCnt;
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
	 * @param inspectHour3 the inspectHour3 to set
	 */
	public void setInspectHour3(String inspectHour3)
	{
		this.inspectHour3 = inspectHour3;
	}

	/**
	 * @param totCnt the totCnt to set
	 */
	public void setTotCnt(String totCnt)
	{
		this.totCnt = totCnt;
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
}
