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

package sdc.anal.lya.macro.A11.EQPUNIT.v02;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;

import kiea.proj.sdc.anal.macro.impl.bean.AbstractBean;

/**
 * @author KangSeok
 * @date 2014. 8. 8.
 * @file_name AIB_EQPUNIT_WriteBean.java
 *
 */
public class EQPUNIT_WriteBean extends AbstractBean
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

	///////////////////////////////////////////////////////////////////////////

	private byte[] bytes = null;
	private String title = null;
	private String str   = null;

	public void setBytes(byte[] bytes)
	{
		ByteBuffer buf = ByteBuffer.allocateDirect(10 * 1024);

		buf.clear();
		if (imptEqpUnitId   == null) buf.put((byte)','); else buf.put((byte)'"').put(imptEqpUnitId  .getBytes()).put((byte)'"').put((byte)',');
		if (siteId          == null) buf.put((byte)','); else buf.put((byte)'"').put(siteId         .getBytes()).put((byte)'"').put((byte)',');
		if (imptEqpId       == null) buf.put((byte)','); else buf.put((byte)'"').put(imptEqpId      .getBytes()).put((byte)'"').put((byte)',');
		if (imptEqpUnitName == null) buf.put((byte)'\n'); else buf.put((byte)'"').put(imptEqpUnitName.getBytes()).put((byte)'"').put((byte)'\n');

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
		buf.put((byte)'"').put("IMPTEQPUNITID"  .getBytes()).put((byte)'"').put((byte)',');
		buf.put((byte)'"').put("SITEID"         .getBytes()).put((byte)'"').put((byte)',');
		buf.put((byte)'"').put("IMPTEQPID"      .getBytes()).put((byte)'"').put((byte)',');
		buf.put((byte)'"').put("IMPTEQPUNITNAME".getBytes()).put((byte)'"').put((byte)'\n');

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

	public String getImptEqpUnitId()
	{
		return imptEqpUnitId;
	}

	public void setImptEqpUnitId(String imptEqpUnitId)
	{
		this.imptEqpUnitId = imptEqpUnitId;
	}

	public String getSiteId()
	{
		return siteId;
	}

	public void setSiteId(String siteId)
	{
		this.siteId = siteId;
	}

	public String getImptEqpId()
	{
		return imptEqpId;
	}

	public void setImptEqpId(String imptEqpId)
	{
		this.imptEqpId = imptEqpId;
	}

	public String getImptEqpUnitName()
	{
		return imptEqpUnitName;
	}

	public void setImptEqpUnitName(String imptEqpUnitName)
	{
		this.imptEqpUnitName = imptEqpUnitName;
	}
}
