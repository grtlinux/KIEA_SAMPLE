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

package sdc.anal.lya.macro.A11.WIP_CELL_RATIO.v03;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;

import kiea.proj.sdc.anal.macro.impl.bean.AbstractBean;

/**
 * @author KangSeok
 * @date 2014. 8. 8.
 * @file_name AIB_WIP_CELL_RATIO_ReadBean.java
 *
 */
public class WIP_CELL_RATIO_ReadBean extends AbstractBean
{
	/*
	"DIV_CODE     :DivCode      ",
	"IMPTSTEPGRPID:ImptStepGrpId",
	"IMPTEQPID    :ImptEqpId    ",
	"TRCK_OUT_HOUR:TrckOutHour  ",
	"DEFECTCD     :DefectCd     ",
	"DECGRADECD   :DecGradeCd   ",
	"DECQTY       :DecQty       ",
	*/

	private String divCode      ;
	private String imptStepGrpId;
	private String imptEqpId    ;
	private String trckOutHour  ;
	private String defectCd     ;
	private String decGradeCd   ;
	private String decQty       ;

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
		if (trckOutHour   == null) buf.put((byte)','); else buf.put((byte)'"').put(trckOutHour  .getBytes()).put((byte)'"').put((byte)',');
		if (defectCd      == null) buf.put((byte)','); else buf.put((byte)'"').put(defectCd     .getBytes()).put((byte)'"').put((byte)',');
		if (decGradeCd    == null) buf.put((byte)','); else buf.put((byte)'"').put(decGradeCd   .getBytes()).put((byte)'"').put((byte)',');
		if (decQty        == null) buf.put((byte)','); else buf.put((byte)'"').put(decQty       .getBytes()).put((byte)'"').put((byte)'\n');

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
		buf.put((byte)'"').put("TRCK_OUT_HOUR".getBytes()).put((byte)'"').put((byte)',');
		buf.put((byte)'"').put("DEFECTCD"     .getBytes()).put((byte)'"').put((byte)',');
		buf.put((byte)'"').put("DECGRADECD"   .getBytes()).put((byte)'"').put((byte)',');
		buf.put((byte)'"').put("DECQTY"       .getBytes()).put((byte)'"').put((byte)'\n');

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
		return String.format("READ : [%s] [%s] [%s] [%s] [%s] [%s] [%s]"
			, divCode      
			, imptStepGrpId
			, imptEqpId    
			, trckOutHour  
			, defectCd     
			, decGradeCd   
			, decQty       
		);
	}

	public static String[] getHeader()
	{
		List<String> list = new ArrayList<String>();
		list.add("DIV_CODE"     );
		list.add("IMPTSTEPGRPID");
		list.add("IMPTEQPID"    );
		list.add("TRCK_OUT_HOUR");
		list.add("DEFECTCD"     );
		list.add("DECGRADECD"   );
		list.add("DECQTY"       );

		return list.toArray(new String[list.size()]);
	}

	public String[] getStringArray()
	{
		List<String> list = new ArrayList<String>();
		list.add(divCode      );
		list.add(imptStepGrpId);
		list.add(imptEqpId    );
		list.add(trckOutHour  );
		list.add(defectCd     );
		list.add(decGradeCd   );
		list.add(decQty       );

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

	public String getTrckOutHour()
	{
		return trckOutHour;
	}

	public void setTrckOutHour(String trckOutHour)
	{
		this.trckOutHour = trckOutHour;
	}

	public String getDefectCd()
	{
		return defectCd;
	}

	public void setDefectCd(String defectCd)
	{
		this.defectCd = defectCd;
	}

	public String getDecGradeCd()
	{
		return decGradeCd;
	}

	public void setDecGradeCd(String decGradeCd)
	{
		this.decGradeCd = decGradeCd;
	}

	public String getDecQty()
	{
		return decQty;
	}

	public void setDecQty(String decQty)
	{
		this.decQty = decQty;
	}
}
