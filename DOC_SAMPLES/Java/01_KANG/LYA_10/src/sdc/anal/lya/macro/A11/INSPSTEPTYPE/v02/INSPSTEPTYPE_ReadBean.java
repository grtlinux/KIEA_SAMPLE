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

package sdc.anal.lya.macro.A11.INSPSTEPTYPE.v02;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;

import kiea.proj.sdc.anal.macro.impl.bean.AbstractBean;

/**
 * @author KangSeok
 * @date 2014. 8. 8.
 * @file_name AIB_INSPSTEPTYPE_ReadBean.java
 *
 */
public class INSPSTEPTYPE_ReadBean extends AbstractBean
{
	/*
	"SITEID      :SiteId      ",
	"INSPSTEPTYPE:InspStepType",
	"AREAID      :AreaId      ",
	"LAYERID     :LayerId     ",
	"STEPDESC    :StepDesc    ",
	"ORD         :Ord         ",
	*/

	private String siteId      ;
	private String inspStepType;
	private String areaId      ;
	private String layerId     ;
	private String stepDesc    ;
	private String ord         ;

	///////////////////////////////////////////////////////////////////////////

	private byte[] bytes = null;
	private String title = null;
	private String str   = null;

	public void setBytes(byte[] bytes)
	{
		ByteBuffer buf = ByteBuffer.allocateDirect(10 * 1024);

		buf.clear();
		if (siteId       == null) buf.put((byte)','); else buf.put((byte)'"').put(siteId      .getBytes()).put((byte)'"').put((byte)',');
		if (inspStepType == null) buf.put((byte)','); else buf.put((byte)'"').put(inspStepType.getBytes()).put((byte)'"').put((byte)',');
		if (areaId       == null) buf.put((byte)','); else buf.put((byte)'"').put(areaId      .getBytes()).put((byte)'"').put((byte)',');
		if (layerId      == null) buf.put((byte)','); else buf.put((byte)'"').put(layerId     .getBytes()).put((byte)'"').put((byte)',');
		if (stepDesc     == null) buf.put((byte)','); else buf.put((byte)'"').put(stepDesc    .getBytes()).put((byte)'"').put((byte)',');
		if (ord          == null) buf.put((byte)','); else buf.put((byte)'"').put(ord         .getBytes()).put((byte)'"').put((byte)'\n');

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
		buf.put((byte)'"').put("SITEID"      .getBytes()).put((byte)'"').put((byte)',');
		buf.put((byte)'"').put("INSPSTEPTYPE".getBytes()).put((byte)'"').put((byte)',');
		buf.put((byte)'"').put("AREAID"      .getBytes()).put((byte)'"').put((byte)',');
		buf.put((byte)'"').put("LAYERID"     .getBytes()).put((byte)'"').put((byte)',');
		buf.put((byte)'"').put("STEPDESC"    .getBytes()).put((byte)'"').put((byte)',');
		buf.put((byte)'"').put("ORD"         .getBytes()).put((byte)'"').put((byte)'\n');

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
		return String.format("READ : [%s] [%s] [%s] [%s] [%s] [%s]"
			, siteId      
			, inspStepType
			, areaId      
			, layerId     
			, stepDesc    
			, ord         
		);
	}

	public static String[] getHeader()
	{
		List<String> list = new ArrayList<String>();
		list.add("SITEID"      );
		list.add("INSPSTEPTYPE");
		list.add("AREAID"      );
		list.add("LAYERID"     );
		list.add("STEPDESC"    );
		list.add("ORD"         );

		return list.toArray(new String[list.size()]);
	}

	public String[] getStringArray()
	{
		List<String> list = new ArrayList<String>();
		list.add(siteId      );
		list.add(inspStepType);
		list.add(areaId      );
		list.add(layerId     );
		list.add(stepDesc    );
		list.add(ord         );

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

	public String getInspStepType()
	{
		return inspStepType;
	}

	public void setInspStepType(String inspStepType)
	{
		this.inspStepType = inspStepType;
	}

	public String getAreaId()
	{
		return areaId;
	}

	public void setAreaId(String areaId)
	{
		this.areaId = areaId;
	}

	public String getLayerId()
	{
		return layerId;
	}

	public void setLayerId(String layerId)
	{
		this.layerId = layerId;
	}

	public String getStepDesc()
	{
		return stepDesc;
	}

	public void setStepDesc(String stepDesc)
	{
		this.stepDesc = stepDesc;
	}

	public String getOrd()
	{
		return ord;
	}

	public void setOrd(String ord)
	{
		this.ord = ord;
	}
}
