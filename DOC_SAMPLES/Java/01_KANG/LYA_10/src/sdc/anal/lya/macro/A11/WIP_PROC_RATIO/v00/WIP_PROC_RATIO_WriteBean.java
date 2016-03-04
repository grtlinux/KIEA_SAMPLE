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

package sdc.anal.lya.macro.A11.WIP_PROC_RATIO.v00;

import java.util.ArrayList;
import java.util.List;

import kiea.proj.sdc.anal.macro.impl.bean.AbstractBean;

/**
 * @author KangSeok
 * @date 2014. 8. 8.
 * @file_name AIB_WIP_PROC_RATIO_WriteBean.java
 *
 */
public class WIP_PROC_RATIO_WriteBean extends AbstractBean
{
	/*
	"DIV_CODE      :DivCode     ",
	"STEP_ID       :StepId      ",
	"EQP_ID        :EqpId       ",
	"PROC_TIME     :ProcTime    ",
	"TOT_CELL_CNT  :TotCellCnt  ",
	"BAD_CELL_CNT  :BadCellCnt  ",
	"CELL_BAD_RATIO:CellBadRatio",
	*/

	private String divCode     ;
	private String stepId      ;
	private String eqpId       ;
	private String procTime    ;
	private String totCellCnt  ;
	private String badCellCnt  ;
	private String cellBadRatio;

	public String toString()
	{
		return String.format("READ : [%s] [%s] [%s] [%s] [%s] [%s] [%s]"
			, divCode     
			, stepId      
			, eqpId       
			, procTime    
			, totCellCnt  
			, badCellCnt  
			, cellBadRatio
		);
	}

	public static String[] getHeader()
	{
		List<String> list = new ArrayList<String>();
		list.add("DIV_CODE"      );
		list.add("STEP_ID"       );
		list.add("EQP_ID"        );
		list.add("PROC_TIME"     );
		list.add("TOT_CELL_CNT"  );
		list.add("BAD_CELL_CNT"  );
		list.add("CELL_BAD_RATIO");

		return list.toArray(new String[list.size()]);
	}

	public String[] getStringArray()
	{
		List<String> list = new ArrayList<String>();
		list.add(divCode     );
		list.add(stepId      );
		list.add(eqpId       );
		list.add(procTime    );
		list.add(totCellCnt  );
		list.add(badCellCnt  );
		list.add(cellBadRatio);

		return list.toArray(new String[list.size()]);
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
	 * @return the procTime
	 */
	public String getProcTime()
	{
		return procTime;
	}

	/**
	 * @return the totCellCnt
	 */
	public String getTotCellCnt()
	{
		return totCellCnt;
	}

	/**
	 * @return the badCellCnt
	 */
	public String getBadCellCnt()
	{
		return badCellCnt;
	}

	/**
	 * @return the cellBadRatio
	 */
	public String getCellBadRatio()
	{
		return cellBadRatio;
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
	 * @param procTime the procTime to set
	 */
	public void setProcTime(String procTime)
	{
		this.procTime = procTime;
	}

	/**
	 * @param totCellCnt the totCellCnt to set
	 */
	public void setTotCellCnt(String totCellCnt)
	{
		this.totCellCnt = totCellCnt;
	}

	/**
	 * @param badCellCnt the badCellCnt to set
	 */
	public void setBadCellCnt(String badCellCnt)
	{
		this.badCellCnt = badCellCnt;
	}

	/**
	 * @param cellBadRatio the cellBadRatio to set
	 */
	public void setCellBadRatio(String cellBadRatio)
	{
		this.cellBadRatio = cellBadRatio;
	}
}
