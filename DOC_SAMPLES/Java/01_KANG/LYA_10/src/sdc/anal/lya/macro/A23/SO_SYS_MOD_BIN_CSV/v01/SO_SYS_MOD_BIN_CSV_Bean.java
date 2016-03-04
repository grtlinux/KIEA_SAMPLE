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

package sdc.anal.lya.macro.A23.SO_SYS_MOD_BIN_CSV.v01;

import java.util.ArrayList;
import java.util.List;

import kiea.proj.sdc.anal.macro.impl.bean.AbstractBean;

/**
 * @author KangSeok
 * @date 2014. 8. 8.
 * @file_name SO_SYS_MOD_BIN_CSV_Bean.java
 *
 */
public class SO_SYS_MOD_BIN_CSV_Bean extends AbstractBean
{
	/*
	"BINGRADECD:BinGradeCd",
	"BAD_RATE  :BadRate   ",
	"GOOD_RATE :GoodRate  ",
	*/

	private String binGradeCd;
	private String badRate   ;
	private String goodRate  ;

	public String toString()
	{
		return String.format("READ : [%s] [%s] [%s]"
			, binGradeCd
			, badRate   
			, goodRate  
		);
	}

	public static String[] getHeader()
	{
		List<String> list = new ArrayList<String>();
		list.add("BINGRADECD");
		list.add("BAD_RATE"  );
		list.add("GOOD_RATE" );

		return list.toArray(new String[list.size()]);
	}

	public String[] getStringArray()
	{
		List<String> list = new ArrayList<String>();
		list.add(binGradeCd);
		list.add(badRate   );
		list.add(goodRate  );

		return list.toArray(new String[list.size()]);
	}

	/**
	 * @return the binGradeCd
	 */
	public String getBinGradeCd()
	{
		return binGradeCd;
	}

	/**
	 * @return the badRate
	 */
	public String getBadRate()
	{
		return badRate;
	}

	/**
	 * @return the goodRate
	 */
	public String getGoodRate()
	{
		return goodRate;
	}

	/**
	 * @param binGradeCd the binGradeCd to set
	 */
	public void setBinGradeCd(String binGradeCd)
	{
		this.binGradeCd = binGradeCd;
	}

	/**
	 * @param badRate the badRate to set
	 */
	public void setBadRate(String badRate)
	{
		this.badRate = badRate;
	}

	/**
	 * @param goodRate the goodRate to set
	 */
	public void setGoodRate(String goodRate)
	{
		this.goodRate = goodRate;
	}
}
