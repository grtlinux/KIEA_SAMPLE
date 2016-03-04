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

package sdc.anal.lya.macro.A11.STEP_PROCESS.v04;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;

import kiea.proj.sdc.anal.macro.impl.bean.AbstractBean;

/**
 * @author KangSeok
 * @date 2014. 8. 8.
 * @file_name AIB_STEP_PROCESS_WriteBean.java
 *
 */
public class STEP_PROCESS_WriteBean extends AbstractBean
{
	/*
	"DIV_CODE      :DivCode      ",
	"LINE_CODE     :LineCode     ",
	"STEP_ID       :StepId       ",
	"SUBSTEP_ID    :SubStepId    ",
	"EQPUNITSLOT_ID:EqpUnitSlotId",
	"USE_YN        :UseYn        ",
	"REGISTER_DATE :RegisterDate ",
	*/

	private String divCode      ;
	private String lineCode     ;
	private String stepId       ;
	private String subStepId    ;
	private String eqpUnitSlotId;
	private String useYn        ;
	private String registerDate ;

	///////////////////////////////////////////////////////////////////////////

	private byte[] bytes = null;
	private String title = null;
	private String str   = null;

	public void setBytes(byte[] bytes)
	{
		ByteBuffer buf = ByteBuffer.allocateDirect(10 * 1024);

		buf.clear();
		if (divCode       == null) buf.put((byte)','); else buf.put((byte)'"').put(divCode      .getBytes()).put((byte)'"').put((byte)',');
		if (lineCode      == null) buf.put((byte)','); else buf.put((byte)'"').put(lineCode     .getBytes()).put((byte)'"').put((byte)',');
		if (stepId        == null) buf.put((byte)','); else buf.put((byte)'"').put(stepId       .getBytes()).put((byte)'"').put((byte)',');
		if (subStepId     == null) buf.put((byte)','); else buf.put((byte)'"').put(subStepId    .getBytes()).put((byte)'"').put((byte)',');
		if (eqpUnitSlotId == null) buf.put((byte)','); else buf.put((byte)'"').put(eqpUnitSlotId.getBytes()).put((byte)'"').put((byte)',');
		if (useYn         == null) buf.put((byte)','); else buf.put((byte)'"').put(useYn        .getBytes()).put((byte)'"').put((byte)',');
		if (registerDate  == null) buf.put((byte)'\n'); else buf.put((byte)'"').put(registerDate .getBytes()).put((byte)'"').put((byte)'\n');

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
		buf.put((byte)'"').put("DIV_CODE"      .getBytes()).put((byte)'"').put((byte)',');
		buf.put((byte)'"').put("LINE_CODE"     .getBytes()).put((byte)'"').put((byte)',');
		buf.put((byte)'"').put("STEP_ID"       .getBytes()).put((byte)'"').put((byte)',');
		buf.put((byte)'"').put("SUBSTEP_ID"    .getBytes()).put((byte)'"').put((byte)',');
		buf.put((byte)'"').put("EQPUNITSLOT_ID".getBytes()).put((byte)'"').put((byte)',');
		buf.put((byte)'"').put("USE_YN"        .getBytes()).put((byte)'"').put((byte)',');
		buf.put((byte)'"').put("REGISTER_DATE" .getBytes()).put((byte)'"').put((byte)'\n');

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
			, lineCode     
			, stepId       
			, subStepId    
			, eqpUnitSlotId
			, useYn        
			, registerDate 
		);
	}

	public static String[] getHeader()
	{
		List<String> list = new ArrayList<String>();
		list.add("DIV_CODE"      );
		list.add("LINE_CODE"     );
		list.add("STEP_ID"       );
		list.add("SUBSTEP_ID"    );
		list.add("EQPUNITSLOT_ID");
		list.add("USE_YN"        );
		list.add("REGISTER_DATE" );

		return list.toArray(new String[list.size()]);
	}

	public String[] getStringArray()
	{
		List<String> list = new ArrayList<String>();
		list.add(divCode      );
		list.add(lineCode     );
		list.add(stepId       );
		list.add(subStepId    );
		list.add(eqpUnitSlotId);
		list.add(useYn        );
		list.add(registerDate );

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

	public String getLineCode()
	{
		return lineCode;
	}

	public void setLineCode(String lineCode)
	{
		this.lineCode = lineCode;
	}

	public String getStepId()
	{
		return stepId;
	}

	public void setStepId(String stepId)
	{
		this.stepId = stepId;
	}

	public String getSubStepId()
	{
		return subStepId;
	}

	public void setSubStepId(String subStepId)
	{
		this.subStepId = subStepId;
	}

	public String getEqpUnitSlotId()
	{
		return eqpUnitSlotId;
	}

	public void setEqpUnitSlotId(String eqpUnitSlotId)
	{
		this.eqpUnitSlotId = eqpUnitSlotId;
	}

	public String getUseYn()
	{
		return useYn;
	}

	public void setUseYn(String useYn)
	{
		this.useYn = useYn;
	}

	public String getRegisterDate()
	{
		return registerDate;
	}

	public void setRegisterDate(String registerDate)
	{
		this.registerDate = registerDate;
	}
}
