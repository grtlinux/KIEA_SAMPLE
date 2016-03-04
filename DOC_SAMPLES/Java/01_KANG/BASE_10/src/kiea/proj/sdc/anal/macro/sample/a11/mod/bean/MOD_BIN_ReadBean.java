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

package kiea.proj.sdc.anal.macro.sample.a11.mod.bean;

import java.util.ArrayList;
import java.util.List;

import kiea.proj.sdc.anal.macro.impl.bean.AbstractBean;

/**
 * @author KangSeok
 * @date 2014. 8. 8.
 * @file_name MOD_BIN_ReadBean.java
 *
 */
public class MOD_BIN_ReadBean extends AbstractBean
{
	/*
	"INSPHOUR  :InspHour  ",
	"BINGRADECD:BinGradeCd",
	"TOT       :Tot       ",
	"BAD       :Bad       ",
	"GOOD      :Good      ",
	*/

	private String inspHour  ;
	private String binGradeCd;
	private String tot       ;
	private String bad       ;
	private String good      ;

	public String toString()
	{
		return String.format("READ : [%s] [%s] [%s] [%s] [%s]"
			, inspHour  
			, binGradeCd
			, tot       
			, bad       
			, good      
		);
	}

	public static String[] getHeader()
	{
		List<String> list = new ArrayList<String>();
		list.add("INSPHOUR"  );
		list.add("BINGRADECD");
		list.add("TOT"       );
		list.add("BAD"       );
		list.add("GOOD"      );

		return list.toArray(new String[list.size()]);
	}

	public String[] getStringArray()
	{
		List<String> list = new ArrayList<String>();
		list.add(inspHour  );
		list.add(binGradeCd);
		list.add(tot       );
		list.add(bad       );
		list.add(good      );

		return list.toArray(new String[list.size()]);
	}

	/**
	 * @return the inspHour
	 */
	public String getInspHour()
	{
		return inspHour;
	}

	/**
	 * @return the binGradeCd
	 */
	public String getBinGradeCd()
	{
		return binGradeCd;
	}

	/**
	 * @return the tot
	 */
	public String getTot()
	{
		return tot;
	}

	/**
	 * @return the bad
	 */
	public String getBad()
	{
		return bad;
	}

	/**
	 * @return the good
	 */
	public String getGood()
	{
		return good;
	}

	/**
	 * @param inspHour the inspHour to set
	 */
	public void setInspHour(String inspHour)
	{
		this.inspHour = inspHour;
	}

	/**
	 * @param binGradeCd the binGradeCd to set
	 */
	public void setBinGradeCd(String binGradeCd)
	{
		this.binGradeCd = binGradeCd;
	}

	/**
	 * @param tot the tot to set
	 */
	public void setTot(String tot)
	{
		this.tot = tot;
	}

	/**
	 * @param bad the bad to set
	 */
	public void setBad(String bad)
	{
		this.bad = bad;
	}

	/**
	 * @param good the good to set
	 */
	public void setGood(String good)
	{
		this.good = good;
	}
}
