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

package sdc.anal.mura.macro.A24.RP_MUR_EQP_SUMM.v04;

import java.util.ArrayList;
import java.util.List;

import kiea.proj.sdc.anal.macro.impl.bean.AbstractBean;

/**
 * @author KangSeok
 * @date 2014. 8. 8.
 * @file_name ANAL_INDEX_ReadBean.java
 *
 */
public class RP_MUR_EQP_SUMM_ReadBean extends AbstractBean
{
	/*
	"MEASEQPUNITID:MeasEqpUnitId",
	"TOTCNT       :TotCnt       ",
	"MURACELL     :MuraCell     ",
	"MURA_SUM     :MuraSum      ",
	"RATIO        :Ratio        ",
	"MURA_CNT     :MuraCnt      ",
	*/

	private String measEqpUnitId;
	private String totCnt       ;
	private String muraCell     ;
	private String muraSum      ;
	private String ratio        ;
	private String muraCnt      ;

	public String toString()
	{
		return String.format("READ : [%s] [%s] [%s] [%s] [%s] [%s]"
			, measEqpUnitId
			, totCnt       
			, muraCell     
			, muraSum      
			, ratio        
			, muraCnt      
		);
	}

	public static String[] getHeader()
	{
		List<String> list = new ArrayList<String>();
		list.add("MEASEQPUNITID");
		list.add("TOTCNT"       );
		list.add("MURACELL"     );
		list.add("MURA_SUM"     );
		list.add("RATIO"        );
		list.add("MURA_CNT"     );

		return list.toArray(new String[list.size()]);
	}

	public String[] getStringArray()
	{
		List<String> list = new ArrayList<String>();
		list.add(measEqpUnitId);
		list.add(totCnt       );
		list.add(muraCell     );
		list.add(muraSum      );
		list.add(ratio        );
		list.add(muraCnt      );

		return list.toArray(new String[list.size()]);
	}

	public String getMeasEqpUnitId()
	{
		return measEqpUnitId;
	}

	public void setMeasEqpUnitId(String measEqpUnitId)
	{
		this.measEqpUnitId = measEqpUnitId;
	}

	public String getTotCnt()
	{
		return totCnt;
	}

	public void setTotCnt(String totCnt)
	{
		this.totCnt = totCnt;
	}

	public String getMuraCell()
	{
		return muraCell;
	}

	public void setMuraCell(String muraCell)
	{
		this.muraCell = muraCell;
	}

	public String getMuraSum()
	{
		return muraSum;
	}

	public void setMuraSum(String muraSum)
	{
		this.muraSum = muraSum;
	}

	public String getRatio()
	{
		return ratio;
	}

	public void setRatio(String ratio)
	{
		this.ratio = ratio;
	}

	public String getMuraCnt()
	{
		return muraCnt;
	}

	public void setMuraCnt(String muraCnt)
	{
		this.muraCnt = muraCnt;
	}
}
