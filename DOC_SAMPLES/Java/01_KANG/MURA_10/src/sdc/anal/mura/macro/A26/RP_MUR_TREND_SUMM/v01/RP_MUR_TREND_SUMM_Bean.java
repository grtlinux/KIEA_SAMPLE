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

package sdc.anal.mura.macro.A26.RP_MUR_TREND_SUMM.v01;

import java.util.ArrayList;
import java.util.List;

import kiea.proj.sdc.anal.macro.impl.bean.AbstractBean;

/**
 * @author KangSeok
 * @date 2014. 8. 8.
 * @file_name MURA_SPC_ReadBean.java
 *
 */
public class RP_MUR_TREND_SUMM_Bean extends AbstractBean
{
	/*
	"D_TIME:DTime ",
	"TOTCNT:TotCnt",
	"MURA  :Mura  ",
	"Y_AXIS:YAxis ",
	*/

	private String DTime ;
	private String totCnt;
	private String mura  ;
	private String YAxis ;

	public String toString()
	{
		return String.format("READ : [%s] [%s] [%s] [%s]"
			, DTime 
			, totCnt
			, mura  
			, YAxis 
		);
	}

	public static String[] getHeader()
	{
		List<String> list = new ArrayList<String>();
		list.add("D_TIME");
		list.add("TOTCNT");
		list.add("MURA"  );
		list.add("Y_AXIS");

		return list.toArray(new String[list.size()]);
	}

	public String[] getStringArray()
	{
		List<String> list = new ArrayList<String>();
		list.add(DTime );
		list.add(totCnt);
		list.add(mura  );
		list.add(YAxis );

		return list.toArray(new String[list.size()]);
	}

	/**
	 * @return the dTime
	 */
	public String getDTime()
	{
		return DTime;
	}

	/**
	 * @return the totCnt
	 */
	public String getTotCnt()
	{
		return totCnt;
	}

	/**
	 * @return the mura
	 */
	public String getMura()
	{
		return mura;
	}

	/**
	 * @return the yAxis
	 */
	public String getYAxis()
	{
		return YAxis;
	}

	/**
	 * @param dTime the dTime to set
	 */
	public void setDTime(String dTime)
	{
		DTime = dTime;
	}

	/**
	 * @param totCnt the totCnt to set
	 */
	public void setTotCnt(String totCnt)
	{
		this.totCnt = totCnt;
	}

	/**
	 * @param mura the mura to set
	 */
	public void setMura(String mura)
	{
		this.mura = mura;
	}

	/**
	 * @param yAxis the yAxis to set
	 */
	public void setYAxis(String yAxis)
	{
		YAxis = yAxis;
	}
}
