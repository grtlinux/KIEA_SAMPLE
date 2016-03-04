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

package sdc.anal.lya.macro.A11.INSPSTEP_DEFECTTRACE.v04;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;

import kiea.proj.sdc.anal.macro.impl.bean.AbstractBean;

/**
 * @author KangSeok
 * @date 2014. 8. 8.
 * @file_name AIB_INSPSTEP_DEFECTTRACE_WriteBean.java
 *
 */
public class INSPSTEP_DEFECTTRACE_WriteBean extends AbstractBean
{
	/*
	"SITEID         :SiteId         ",
	"EQPUNITID      :EqpUnitId      ",
	"INSPSTEPGRPID  :InspStepGrpId  ",
	"INSPSTEPGRPNAME:InspStepGrpName",
	"AREAID         :AreaId         ",
	"INSPSTEPGRPTYPE:InspStepGrpType",
	"ORD            :Ord            ",
	"COORDCONVERTYN :CoordConvertYn ",
	*/

	private String siteId         ;
	private String eqpUnitId      ;
	private String inspStepGrpId  ;
	private String inspStepGrpName;
	private String areaId         ;
	private String inspStepGrpType;
	private String ord            ;
	private String coordConvertYn ;

	///////////////////////////////////////////////////////////////////////////

	private byte[] bytes = null;
	private String title = null;
	private String str   = null;

	public void setBytes(byte[] bytes)
	{
		ByteBuffer buf = ByteBuffer.allocateDirect(10 * 1024);

		buf.clear();
		if (siteId          == null) buf.put((byte)','); else buf.put((byte)'"').put(siteId         .getBytes()).put((byte)'"').put((byte)',');
		if (eqpUnitId       == null) buf.put((byte)','); else buf.put((byte)'"').put(eqpUnitId      .getBytes()).put((byte)'"').put((byte)',');
		if (inspStepGrpId   == null) buf.put((byte)','); else buf.put((byte)'"').put(inspStepGrpId  .getBytes()).put((byte)'"').put((byte)',');
		if (inspStepGrpName == null) buf.put((byte)','); else buf.put((byte)'"').put(inspStepGrpName.getBytes()).put((byte)'"').put((byte)',');
		if (areaId          == null) buf.put((byte)','); else buf.put((byte)'"').put(areaId         .getBytes()).put((byte)'"').put((byte)',');
		if (inspStepGrpType == null) buf.put((byte)','); else buf.put((byte)'"').put(inspStepGrpType.getBytes()).put((byte)'"').put((byte)',');
		if (ord             == null) buf.put((byte)','); else buf.put((byte)'"').put(ord            .getBytes()).put((byte)'"').put((byte)',');
		if (coordConvertYn  == null) buf.put((byte)'\n'); else buf.put((byte)'"').put(coordConvertYn .getBytes()).put((byte)'"').put((byte)'\n');

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
		buf.put((byte)'"').put("SITEID"         .getBytes()).put((byte)'"').put((byte)',');
		buf.put((byte)'"').put("EQPUNITID"      .getBytes()).put((byte)'"').put((byte)',');
		buf.put((byte)'"').put("INSPSTEPGRPID"  .getBytes()).put((byte)'"').put((byte)',');
		buf.put((byte)'"').put("INSPSTEPGRPNAME".getBytes()).put((byte)'"').put((byte)',');
		buf.put((byte)'"').put("AREAID"         .getBytes()).put((byte)'"').put((byte)',');
		buf.put((byte)'"').put("INSPSTEPGRPTYPE".getBytes()).put((byte)'"').put((byte)',');
		buf.put((byte)'"').put("ORD"            .getBytes()).put((byte)'"').put((byte)',');
		buf.put((byte)'"').put("COORDCONVERTYN" .getBytes()).put((byte)'"').put((byte)'\n');

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
		return String.format("READ : [%s] [%s] [%s] [%s] [%s] [%s] [%s] [%s]"
			, siteId         
			, eqpUnitId      
			, inspStepGrpId  
			, inspStepGrpName
			, areaId         
			, inspStepGrpType
			, ord            
			, coordConvertYn 
		);
	}

	public static String[] getHeader()
	{
		List<String> list = new ArrayList<String>();
		list.add("SITEID"         );
		list.add("EQPUNITID"      );
		list.add("INSPSTEPGRPID"  );
		list.add("INSPSTEPGRPNAME");
		list.add("AREAID"         );
		list.add("INSPSTEPGRPTYPE");
		list.add("ORD"            );
		list.add("COORDCONVERTYN" );

		return list.toArray(new String[list.size()]);
	}

	public String[] getStringArray()
	{
		List<String> list = new ArrayList<String>();
		list.add(siteId         );
		list.add(eqpUnitId      );
		list.add(inspStepGrpId  );
		list.add(inspStepGrpName);
		list.add(areaId         );
		list.add(inspStepGrpType);
		list.add(ord            );
		list.add(coordConvertYn );

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

	public String getEqpUnitId()
	{
		return eqpUnitId;
	}

	public void setEqpUnitId(String eqpUnitId)
	{
		this.eqpUnitId = eqpUnitId;
	}

	public String getInspStepGrpId()
	{
		return inspStepGrpId;
	}

	public void setInspStepGrpId(String inspStepGrpId)
	{
		this.inspStepGrpId = inspStepGrpId;
	}

	public String getInspStepGrpName()
	{
		return inspStepGrpName;
	}

	public void setInspStepGrpName(String inspStepGrpName)
	{
		this.inspStepGrpName = inspStepGrpName;
	}

	public String getAreaId()
	{
		return areaId;
	}

	public void setAreaId(String areaId)
	{
		this.areaId = areaId;
	}

	public String getInspStepGrpType()
	{
		return inspStepGrpType;
	}

	public void setInspStepGrpType(String inspStepGrpType)
	{
		this.inspStepGrpType = inspStepGrpType;
	}

	public String getOrd()
	{
		return ord;
	}

	public void setOrd(String ord)
	{
		this.ord = ord;
	}

	public String getCoordConvertYn()
	{
		return coordConvertYn;
	}

	public void setCoordConvertYn(String coordConvertYn)
	{
		this.coordConvertYn = coordConvertYn;
	}
}
