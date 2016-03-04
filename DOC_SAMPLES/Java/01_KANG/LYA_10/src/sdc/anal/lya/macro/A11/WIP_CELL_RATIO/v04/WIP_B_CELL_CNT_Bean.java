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

package sdc.anal.lya.macro.A11.WIP_CELL_RATIO.v04;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;

/**
 * @author KangSeok
 * @date 2014. 9. 4.
 * @file_name AIB_WIP_B_CELL_CNT.java
 *
 */
public class WIP_B_CELL_CNT_Bean
{
	/*
	"DIV_CODE     :DivCode      ",
	"IMPTSTEPGRPID:ImptStepGrpId",
	"IMPTEQPID    :ImptEqpId    ",
	"TRCK_IN_TIME :TrckInTime   ",
	"BAD_CELL_CNT :BadCellCnt   ",
	*/

	private String divCode      ;
	private String imptStepGrpId;
	private String imptEqpId    ;
	private String trckInTime   ;
	private String badCellCnt   ;

	///////////////////////////////////////////////////////////////////////////

	private byte[] bytes = null;
	private String title = null;
	private String str   = null;

	public void setBytes(byte[] bytes)
	{
		ByteBuffer buf = ByteBuffer.allocateDirect(10 * 1024);

		buf.clear();
		if (divCode       == null) buf.put((byte)','); else buf.put((byte)'"').put(divCode      .getBytes()).put((byte)'"').put((byte)',');
		if (imptStepGrpId == null) buf.put((byte)','); else buf.put((byte)'"').put(imptStepGrpId.getBytes()).put((byte)'"').put((byte)',');
		if (imptEqpId     == null) buf.put((byte)','); else buf.put((byte)'"').put(imptEqpId    .getBytes()).put((byte)'"').put((byte)',');
		if (trckInTime    == null) buf.put((byte)','); else buf.put((byte)'"').put(trckInTime   .getBytes()).put((byte)'"').put((byte)',');
		if (badCellCnt    == null) buf.put((byte)','); else buf.put((byte)'"').put(badCellCnt   .getBytes()).put((byte)'"').put((byte)'\n');

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
		buf.put((byte)'"').put("DIV_CODE"     .getBytes()).put((byte)'"').put((byte)',');
		buf.put((byte)'"').put("IMPTSTEPGRPID".getBytes()).put((byte)'"').put((byte)',');
		buf.put((byte)'"').put("IMPTEQPID"    .getBytes()).put((byte)'"').put((byte)',');
		buf.put((byte)'"').put("TRCK_IN_TIME" .getBytes()).put((byte)'"').put((byte)',');
		buf.put((byte)'"').put("BAD_CELL_CNT" .getBytes()).put((byte)'"').put((byte)'\n');

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

	public void addBadQty(String qty)
	{
		badCellCnt = "" + (Integer.parseInt(badCellCnt) + Integer.parseInt(qty));
	}

	public String toString()
	{
		return String.format("READ : [%s] [%s] [%s] [%s] [%s]"
			, divCode      
			, imptStepGrpId
			, imptEqpId    
			, trckInTime   
			, badCellCnt   
		);
	}

	public static String[] getHeader()
	{
		List<String> list = new ArrayList<String>();
		list.add("DIV_CODE"     );
		list.add("IMPTSTEPGRPID");
		list.add("IMPTEQPID"    );
		list.add("TRCK_IN_TIME" );
		list.add("BAD_CELL_CNT" );

		return list.toArray(new String[list.size()]);
	}

	public String[] getStringArray()
	{
		List<String> list = new ArrayList<String>();
		list.add(divCode      );
		list.add(imptStepGrpId);
		list.add(imptEqpId    );
		list.add(trckInTime   );
		list.add(badCellCnt   );

		return list.toArray(new String[list.size()]);
	}

	public String getDivCode()
	{
		return divCode;
	}

	public void setDivCode(String divCode)
	{
		this.divCode = divCode;
	}

	public String getImptStepGrpId()
	{
		return imptStepGrpId;
	}

	public void setImptStepGrpId(String imptStepGrpId)
	{
		this.imptStepGrpId = imptStepGrpId;
	}

	public String getImptEqpId()
	{
		return imptEqpId;
	}

	public void setImptEqpId(String imptEqpId)
	{
		this.imptEqpId = imptEqpId;
	}

	public String getTrckInTime()
	{
		return trckInTime;
	}

	public void setTrckInTime(String trckInTime)
	{
		this.trckInTime = trckInTime;
	}

	public String getBadCellCnt()
	{
		return badCellCnt;
	}

	public void setBadCellCnt(String badCellCnt)
	{
		this.badCellCnt = badCellCnt;
	}
}
