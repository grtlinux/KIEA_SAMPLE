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

package sdc.anal.mura.macro.A24.RP_MUR_EQP_DETAILS1.v04;

import java.util.ArrayList;
import java.util.List;

import kiea.proj.sdc.anal.macro.impl.bean.AbstractBean;

/**
 * @author KangSeok
 * @date 2014. 8. 8.
 * @file_name ANAL_INDEX_ReadBean.java
 *
 */
public class RP_MUR_EQP_DETAILS1_ReadBean extends AbstractBean
{
	/*
	"SITEID       :SiteId       ",
	"MEASEQPUNITID:MeasEqpUnitId",
	"CELLCNT      :CellCnt      ",
	"RATIO        :Ratio        ",
	"D_TIME       :DTime        ",
	*/

	private String siteId       ;
	private String measEqpUnitId;
	private String cellCnt      ;
	private String ratio        ;
	private String DTime        ;

	public String toString()
	{
		return String.format("READ : [%s] [%s] [%s] [%s] [%s]"
			, siteId       
			, measEqpUnitId
			, cellCnt      
			, ratio        
			, DTime        
		);
	}

	public static String[] getHeader()
	{
		List<String> list = new ArrayList<String>();
		list.add("SITEID"       );
		list.add("MEASEQPUNITID");
		list.add("CELLCNT"      );
		list.add("RATIO"        );
		list.add("D_TIME"       );

		return list.toArray(new String[list.size()]);
	}

	public String[] getStringArray()
	{
		List<String> list = new ArrayList<String>();
		list.add(siteId       );
		list.add(measEqpUnitId);
		list.add(cellCnt      );
		list.add(ratio        );
		list.add(DTime        );

		return list.toArray(new String[list.size()]);
	}

	public String getSiteId()
	{
		return siteId;
	}

	public void setSiteId(String siteId)
	{
		this.siteId = siteId;
	}

	public String getMeasEqpUnitId()
	{
		return measEqpUnitId;
	}

	public void setMeasEqpUnitId(String measEqpUnitId)
	{
		this.measEqpUnitId = measEqpUnitId;
	}

	public String getCellCnt()
	{
		return cellCnt;
	}

	public void setCellCnt(String cellCnt)
	{
		this.cellCnt = cellCnt;
	}

	public String getRatio()
	{
		return ratio;
	}

	public void setRatio(String ratio)
	{
		this.ratio = ratio;
	}

	public String getDTime()
	{
		return DTime;
	}

	public void setDTime(String dTime)
	{
		DTime = dTime;
	}
}
