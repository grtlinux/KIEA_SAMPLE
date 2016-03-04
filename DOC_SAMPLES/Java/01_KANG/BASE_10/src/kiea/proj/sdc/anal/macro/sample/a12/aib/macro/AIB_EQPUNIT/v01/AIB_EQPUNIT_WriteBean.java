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

package kiea.proj.sdc.anal.macro.sample.a12.aib.macro.AIB_EQPUNIT.v01;

import java.util.ArrayList;
import java.util.List;

import kiea.proj.sdc.anal.macro.impl.bean.AbstractBean;

/**
 * @author KangSeok
 * @date 2014. 8. 8.
 * @file_name AIB_EQPUNIT_WriteBean.java
 *
 */
public class AIB_EQPUNIT_WriteBean extends AbstractBean
{
	/*
	"IMPTEQPUNITID  :ImptEqpUnitId  ",
	"SITEID         :SiteId         ",
	"IMPTEQPID      :ImptEqpId      ",
	"IMPTEQPUNITNAME:ImptEqpUnitName",
	*/

	private String imptEqpUnitId  ;
	private String siteId         ;
	private String imptEqpId      ;
	private String imptEqpUnitName;

	public String toString()
	{
		return String.format("READ : [%s] [%s] [%s] [%s]"
			, imptEqpUnitId  
			, siteId         
			, imptEqpId      
			, imptEqpUnitName
		);
	}

	public static String[] getHeader()
	{
		List<String> list = new ArrayList<String>();
		list.add("IMPTEQPUNITID"  );
		list.add("SITEID"         );
		list.add("IMPTEQPID"      );
		list.add("IMPTEQPUNITNAME");

		return list.toArray(new String[list.size()]);
	}

	public String[] getStringArray()
	{
		List<String> list = new ArrayList<String>();
		list.add(imptEqpUnitId  );
		list.add(siteId         );
		list.add(imptEqpId      );
		list.add(imptEqpUnitName);

		return list.toArray(new String[list.size()]);
	}

	/**
	 * @return the imptEqpUnitId
	 */
	public String getImptEqpUnitId()
	{
		return imptEqpUnitId;
	}

	/**
	 * @return the siteId
	 */
	public String getSiteId()
	{
		return siteId;
	}

	/**
	 * @return the imptEqpId
	 */
	public String getImptEqpId()
	{
		return imptEqpId;
	}

	/**
	 * @return the imptEqpUnitName
	 */
	public String getImptEqpUnitName()
	{
		return imptEqpUnitName;
	}

	/**
	 * @param imptEqpUnitId the imptEqpUnitId to set
	 */
	public void setImptEqpUnitId(String imptEqpUnitId)
	{
		this.imptEqpUnitId = imptEqpUnitId;
	}

	/**
	 * @param siteId the siteId to set
	 */
	public void setSiteId(String siteId)
	{
		this.siteId = siteId;
	}

	/**
	 * @param imptEqpId the imptEqpId to set
	 */
	public void setImptEqpId(String imptEqpId)
	{
		this.imptEqpId = imptEqpId;
	}

	/**
	 * @param imptEqpUnitName the imptEqpUnitName to set
	 */
	public void setImptEqpUnitName(String imptEqpUnitName)
	{
		this.imptEqpUnitName = imptEqpUnitName;
	}
}
