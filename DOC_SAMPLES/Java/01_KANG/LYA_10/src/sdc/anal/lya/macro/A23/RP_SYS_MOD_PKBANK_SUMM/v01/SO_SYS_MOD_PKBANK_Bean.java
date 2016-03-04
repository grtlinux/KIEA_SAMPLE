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

package sdc.anal.lya.macro.A23.RP_SYS_MOD_PKBANK_SUMM.v01;

import java.util.ArrayList;
import java.util.List;

import kiea.proj.sdc.anal.macro.impl.bean.AbstractBean;

/**
 * @author KangSeok
 * @date 2014. 8. 8.
 * @file_name SO_SYS_MOD_PKBANK_Bean.java
 *
 */
public class SO_SYS_MOD_PKBANK_Bean extends AbstractBean
{
	/*
	"IMPTEQPID:ImptEqpId",
	"MO       :Mo       ",
	"JA       :Ja       ",
	"RATIO    :Ratio    ",
	*/

	private String imptEqpId;
	private String mo       ;
	private String ja       ;
	private String ratio    ;

	public String toString()
	{
		return String.format("READ : [%s] [%s] [%s] [%s]"
			, imptEqpId
			, mo       
			, ja       
			, ratio    
		);
	}

	public static String[] getHeader()
	{
		List<String> list = new ArrayList<String>();
		list.add("IMPTEQPID");
		list.add("MO"       );
		list.add("JA"       );
		list.add("RATIO"    );

		return list.toArray(new String[list.size()]);
	}

	public String[] getStringArray()
	{
		List<String> list = new ArrayList<String>();
		list.add(imptEqpId);
		list.add(mo       );
		list.add(ja       );
		list.add(ratio    );

		return list.toArray(new String[list.size()]);
	}

	/**
	 * @return the imptEqpId
	 */
	public String getImptEqpId()
	{
		return imptEqpId;
	}

	/**
	 * @return the mo
	 */
	public String getMo()
	{
		return mo;
	}

	/**
	 * @return the ja
	 */
	public String getJa()
	{
		return ja;
	}

	/**
	 * @return the ratio
	 */
	public String getRatio()
	{
		return ratio;
	}

	/**
	 * @param imptEqpId the imptEqpId to set
	 */
	public void setImptEqpId(String imptEqpId)
	{
		this.imptEqpId = imptEqpId;
	}

	/**
	 * @param mo the mo to set
	 */
	public void setMo(String mo)
	{
		this.mo = mo;
	}

	/**
	 * @param ja the ja to set
	 */
	public void setJa(String ja)
	{
		this.ja = ja;
	}

	/**
	 * @param ratio the ratio to set
	 */
	public void setRatio(String ratio)
	{
		this.ratio = ratio;
	}
}
