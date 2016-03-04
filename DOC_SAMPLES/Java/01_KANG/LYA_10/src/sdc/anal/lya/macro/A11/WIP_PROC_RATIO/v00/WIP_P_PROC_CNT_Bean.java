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

/**
 * @author KangSeok
 * @date 2014. 9. 4.
 * @file_name AIB_WIP_P_PROC_CNT_Bean.java
 *
 */
public class WIP_P_PROC_CNT_Bean
{
	/*
	"DIV_CODE     :DivCode      ",
	"IMPTSTEPGRPID:ImptStepGrpId",
	"IMPTEQPID    :ImptEqpId    ",
	"PROC_TIME    :ProcTime     ",
	"CELL_CNT     :CellCnt      ",
	*/

	private String divCode      ;
	private String imptStepGrpId;
	private String imptEqpId    ;
	private String procTime     ;
	private String cellCnt      ;

	public String toString()
	{
		return String.format("READ : [%s] [%s] [%s] [%s] [%s]"
			, divCode      
			, imptStepGrpId
			, imptEqpId    
			, procTime     
			, cellCnt      
		);
	}

	public static String[] getHeader()
	{
		List<String> list = new ArrayList<String>();
		list.add("DIV_CODE"     );
		list.add("IMPTSTEPGRPID");
		list.add("IMPTEQPID"    );
		list.add("PROC_TIME"    );
		list.add("CELL_CNT"     );

		return list.toArray(new String[list.size()]);
	}

	public String[] getStringArray()
	{
		List<String> list = new ArrayList<String>();
		list.add(divCode      );
		list.add(imptStepGrpId);
		list.add(imptEqpId    );
		list.add(procTime     );
		list.add(cellCnt      );

		return list.toArray(new String[list.size()]);
	}

	public void addQty(String qty)
	{
		cellCnt = "" + (Integer.parseInt(cellCnt) + Integer.parseInt(qty));
	}

	/**
	 * @return the divCode
	 */
	public String getDivCode()
	{
		return divCode;
	}

	/**
	 * @return the imptStepGrpId
	 */
	public String getImptStepGrpId()
	{
		return imptStepGrpId;
	}

	/**
	 * @return the imptEqpId
	 */
	public String getImptEqpId()
	{
		return imptEqpId;
	}

	/**
	 * @return the procTime
	 */
	public String getProcTime()
	{
		return procTime;
	}

	/**
	 * @return the cellCnt
	 */
	public String getCellCnt()
	{
		return cellCnt;
	}

	/**
	 * @param divCode the divCode to set
	 */
	public void setDivCode(String divCode)
	{
		this.divCode = divCode;
	}

	/**
	 * @param imptStepGrpId the imptStepGrpId to set
	 */
	public void setImptStepGrpId(String imptStepGrpId)
	{
		this.imptStepGrpId = imptStepGrpId;
	}

	/**
	 * @param imptEqpId the imptEqpId to set
	 */
	public void setImptEqpId(String imptEqpId)
	{
		this.imptEqpId = imptEqpId;
	}

	/**
	 * @param procTime the procTime to set
	 */
	public void setProcTime(String procTime)
	{
		this.procTime = procTime;
	}

	/**
	 * @param cellCnt the cellCnt to set
	 */
	public void setCellCnt(String cellCnt)
	{
		this.cellCnt = cellCnt;
	}
}
