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

package sdc.anal.mura.macro.A23.RP_MUR_WIP_SUMM3.v04;

import java.util.ArrayList;
import java.util.List;

import kiea.proj.sdc.anal.macro.impl.bean.AbstractBean;

/**
 * @author KangSeok
 * @date 2014. 8. 8.
 * @file_name RP_TOTWIP_SUMM_Bean.java
 *
 */
public class RP_MUR_WIP_SUMM3_Bean extends AbstractBean
{
	/*
	"DIV_CODE      :DivCode     ",
	"STEP_ID       :StepId      ",
	"EQP_ID        :EqpId       ",
	"MURA_SUM      :MuraSum     ",
	"CELLCNT       :CellCnt     ",
	"AVG_MURA_VALUE:AvgMuraValue",
	*/

	public void addMuraSum(String val)
	{
		muraSum = "" + (Double.parseDouble(muraSum) + Double.parseDouble(val));
	}
	
	public void incCellCnt()
	{
		cellCnt = "" + (Integer.parseInt(cellCnt) + 1);
	}
	
	private String divCode     ;
	private String stepId      ;
	private String eqpId       ;
	private String muraSum     ;
	private String cellCnt     ;
	private String avgMuraValue;

	public String toString()
	{
		return String.format("READ : [%s] [%s] [%s] [%s] [%s] [%s]"
			, divCode     
			, stepId      
			, eqpId       
			, muraSum     
			, cellCnt     
			, avgMuraValue
		);
	}

	public static String[] getHeader()
	{
		List<String> list = new ArrayList<String>();
		list.add("DIV_CODE"      );
		list.add("STEP_ID"       );
		list.add("EQP_ID"        );
		list.add("MURA_SUM"      );
		list.add("CELLCNT"       );
		list.add("AVG_MURA_VALUE");

		return list.toArray(new String[list.size()]);
	}

	public String[] getStringArray()
	{
		List<String> list = new ArrayList<String>();
		list.add(divCode     );
		list.add(stepId      );
		list.add(eqpId       );
		list.add(muraSum     );
		list.add(cellCnt     );
		list.add(avgMuraValue);

		return list.toArray(new String[list.size()]);
	}

	public String getDivCode()
	{
		return divCode;
	}

	public void setDivCode(String divCode)
	{
		this.divCode = divCode;
	}

	public String getStepId()
	{
		return stepId;
	}

	public void setStepId(String stepId)
	{
		this.stepId = stepId;
	}

	public String getEqpId()
	{
		return eqpId;
	}

	public void setEqpId(String eqpId)
	{
		this.eqpId = eqpId;
	}

	public String getMuraSum()
	{
		return muraSum;
	}

	public void setMuraSum(String muraSum)
	{
		this.muraSum = muraSum;
	}

	public String getCellCnt()
	{
		return cellCnt;
	}

	public void setCellCnt(String cellCnt)
	{
		this.cellCnt = cellCnt;
	}

	public String getAvgMuraValue()
	{
		return avgMuraValue;
	}

	public void setAvgMuraValue(String avgMuraValue)
	{
		this.avgMuraValue = avgMuraValue;
	}
}
