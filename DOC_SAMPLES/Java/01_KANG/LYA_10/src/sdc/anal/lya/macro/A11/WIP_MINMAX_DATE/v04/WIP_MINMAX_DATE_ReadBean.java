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

package sdc.anal.lya.macro.A11.WIP_MINMAX_DATE.v04;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;

import kiea.proj.sdc.anal.macro.impl.bean.AbstractBean;

/**
 * @author KangSeok
 * @date 2014. 8. 8.
 * @file_name AIB_WIP_MINMAX_DATE_ReadBean.java
 *
 */
public class WIP_MINMAX_DATE_ReadBean extends AbstractBean
{
	/*
	"LINE_CODE        :LineCode       ",
	"USER_GROUP_CODE  :UserGroupCode  ",
	"AREA             :Area           ",
	"TRACKIN_MIN_TIME :TrackinMinTime ",
	"TRACKOUT_MAX_TIME:TrackoutMaxTime",
	*/

	private String lineCode       ;
	private String userGroupCode  ;
	private String area           ;
	private String trackinMinTime ;
	private String trackoutMaxTime;

	///////////////////////////////////////////////////////////////////////////

	private byte[] bytes = null;
	private String title = null;
	private String str   = null;

	public void setBytes(byte[] bytes)
	{
		ByteBuffer buf = ByteBuffer.allocateDirect(10 * 1024);

		buf.clear();
		if (lineCode        == null) buf.put((byte)','); else buf.put((byte)'"').put(lineCode       .getBytes()).put((byte)'"').put((byte)',');
		if (userGroupCode   == null) buf.put((byte)','); else buf.put((byte)'"').put(userGroupCode  .getBytes()).put((byte)'"').put((byte)',');
		if (area            == null) buf.put((byte)','); else buf.put((byte)'"').put(area           .getBytes()).put((byte)'"').put((byte)',');
		if (trackinMinTime  == null) buf.put((byte)','); else buf.put((byte)'"').put(trackinMinTime .getBytes()).put((byte)'"').put((byte)',');
		if (trackoutMaxTime == null) buf.put((byte)','); else buf.put((byte)'"').put(trackoutMaxTime.getBytes()).put((byte)'"').put((byte)'\n');

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
		buf.put((byte)'"').put("LINE_CODE"        .getBytes()).put((byte)'"').put((byte)',');
		buf.put((byte)'"').put("USER_GROUP_CODE"  .getBytes()).put((byte)'"').put((byte)',');
		buf.put((byte)'"').put("AREA"             .getBytes()).put((byte)'"').put((byte)',');
		buf.put((byte)'"').put("TRACKIN_MIN_TIME" .getBytes()).put((byte)'"').put((byte)',');
		buf.put((byte)'"').put("TRACKOUT_MAX_TIME".getBytes()).put((byte)'"').put((byte)'\n');

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
		return String.format("READ : [%s] [%s] [%s] [%s] [%s]"
			, lineCode       
			, userGroupCode  
			, area           
			, trackinMinTime 
			, trackoutMaxTime
		);
	}

	public static String[] getHeader()
	{
		List<String> list = new ArrayList<String>();
		list.add("LINE_CODE"        );
		list.add("USER_GROUP_CODE"  );
		list.add("AREA"             );
		list.add("TRACKIN_MIN_TIME" );
		list.add("TRACKOUT_MAX_TIME");

		return list.toArray(new String[list.size()]);
	}

	public String[] getStringArray()
	{
		List<String> list = new ArrayList<String>();
		list.add(lineCode       );
		list.add(userGroupCode  );
		list.add(area           );
		list.add(trackinMinTime );
		list.add(trackoutMaxTime);

		return list.toArray(new String[list.size()]);
	}

	public String getLineCode()
	{
		return lineCode;
	}

	public void setLineCode(String lineCode)
	{
		this.lineCode = lineCode;
	}

	public String getUserGroupCode()
	{
		return userGroupCode;
	}

	public void setUserGroupCode(String userGroupCode)
	{
		this.userGroupCode = userGroupCode;
	}

	public String getArea()
	{
		return area;
	}

	public void setArea(String area)
	{
		this.area = area;
	}

	public String getTrackinMinTime()
	{
		return trackinMinTime;
	}

	public void setTrackinMinTime(String trackinMinTime)
	{
		this.trackinMinTime = trackinMinTime;
	}

	public String getTrackoutMaxTime()
	{
		return trackoutMaxTime;
	}

	public void setTrackoutMaxTime(String trackoutMaxTime)
	{
		this.trackoutMaxTime = trackoutMaxTime;
	}
}
