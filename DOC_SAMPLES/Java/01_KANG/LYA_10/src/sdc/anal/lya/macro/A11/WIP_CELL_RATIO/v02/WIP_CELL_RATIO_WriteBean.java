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

package sdc.anal.lya.macro.A11.WIP_CELL_RATIO.v02;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;

import kiea.proj.sdc.anal.macro.impl.bean.AbstractBean;

/**
 * @author KangSeok
 * @date 2014. 8. 8.
 * @file_name AIB_WIP_CELL_RATIO_WriteBean.java
 *
 */
public class WIP_CELL_RATIO_WriteBean extends AbstractBean
{
	/*
	"DIV_CODE      :DivCode     ",
	"STEP_ID       :StepId      ",
	"EQP_ID        :EqpId       ",
	"TRCK_IN_HOUR  :TrckInHour  ",
	"TOT_CELL_CNT  :TotCellCnt  ",
	"BAD_CELL_CNT  :BadCellCnt  ",
	"CELL_BAD_RATIO:CellBadRatio",
	*/

	private String divCode     ;
	private String stepId      ;
	private String eqpId       ;
	private String trckInHour  ;
	private String totCellCnt  ;
	private String badCellCnt  ;
	private String cellBadRatio;

	///////////////////////////////////////////////////////////////////////////

	private byte[] bytes = null;
	private String title = null;
	private String str   = null;

	public void setBytes(byte[] bytes)
	{
		ByteBuffer buf = ByteBuffer.allocateDirect(10 * 1024);

		buf.clear();
		if (divCode      == null) buf.put((byte)','); else buf.put((byte)'"').put(divCode     .getBytes()).put((byte)'"').put((byte)',');
		if (stepId       == null) buf.put((byte)','); else buf.put((byte)'"').put(stepId      .getBytes()).put((byte)'"').put((byte)',');
		if (eqpId        == null) buf.put((byte)','); else buf.put((byte)'"').put(eqpId       .getBytes()).put((byte)'"').put((byte)',');
		if (trckInHour   == null) buf.put((byte)','); else buf.put((byte)'"').put(trckInHour  .getBytes()).put((byte)'"').put((byte)',');
		if (totCellCnt   == null) buf.put((byte)','); else buf.put((byte)'"').put(totCellCnt  .getBytes()).put((byte)'"').put((byte)',');
		if (badCellCnt   == null) buf.put((byte)','); else buf.put((byte)'"').put(badCellCnt  .getBytes()).put((byte)'"').put((byte)',');
		if (cellBadRatio == null) buf.put((byte)'\n'); else buf.put((byte)'"').put(cellBadRatio.getBytes()).put((byte)'"').put((byte)'\n');

		buf.flip();
		this.bytes = new byte[buf.limit()];
		buf.get(this.bytes);
	}

	public byte[] getBytes()
	{
		if (this.bytes == null)
			setBytes(null);

		return this.bytes;
	}

	public void setTitle(String title)
	{
		ByteBuffer buf = ByteBuffer.allocateDirect(1024);

		buf.clear();
		buf.put((byte)'"').put("DIV_CODE"      .getBytes()).put((byte)'"').put((byte)',');
		buf.put((byte)'"').put("STEP_ID"       .getBytes()).put((byte)'"').put((byte)',');
		buf.put((byte)'"').put("EQP_ID"        .getBytes()).put((byte)'"').put((byte)',');
		buf.put((byte)'"').put("TRCK_IN_HOUR"  .getBytes()).put((byte)'"').put((byte)',');
		buf.put((byte)'"').put("TOT_CELL_CNT"  .getBytes()).put((byte)'"').put((byte)',');
		buf.put((byte)'"').put("BAD_CELL_CNT"  .getBytes()).put((byte)'"').put((byte)',');
		buf.put((byte)'"').put("CELL_BAD_RATIO".getBytes()).put((byte)'"').put((byte)'\n');

		buf.flip();
		byte[] bytes = new byte[buf.limit()];
		buf.get(bytes);

		this.title = new String(bytes);
	}

	public String getTitle()
	{
		if (this.title == null)
			setTitle(null);

		return this.title;
	}

	public void setStr(String str)
	{
		this.str = new String(getBytes());
	}

	public String getStr()
	{
		if (this.str == null)
			setStr(null);

		return this.str;
	}


	///////////////////////////////////////////////////////////////////////////

	public String toString()
	{
		return String.format("READ : [%s] [%s] [%s] [%s] [%s] [%s] [%s]"
			, divCode     
			, stepId      
			, eqpId       
			, trckInHour  
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
		list.add("TRCK_IN_HOUR"  );
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
		list.add(trckInHour  );
		list.add(totCellCnt  );
		list.add(badCellCnt  );
		list.add(cellBadRatio);

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

	public String getTrckInHour()
	{
		return trckInHour;
	}

	public void setTrckInHour(String trckInHour)
	{
		this.trckInHour = trckInHour;
	}

	public String getTotCellCnt()
	{
		return totCellCnt;
	}

	public void setTotCellCnt(String totCellCnt)
	{
		this.totCellCnt = totCellCnt;
	}

	public String getBadCellCnt()
	{
		return badCellCnt;
	}

	public void setBadCellCnt(String badCellCnt)
	{
		this.badCellCnt = badCellCnt;
	}

	public String getCellBadRatio()
	{
		return cellBadRatio;
	}

	public void setCellBadRatio(String cellBadRatio)
	{
		this.cellBadRatio = cellBadRatio;
	}
}
