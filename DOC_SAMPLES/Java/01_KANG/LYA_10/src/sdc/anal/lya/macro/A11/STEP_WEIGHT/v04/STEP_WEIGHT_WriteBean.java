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

package sdc.anal.lya.macro.A11.STEP_WEIGHT.v04;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;

import kiea.proj.sdc.anal.macro.impl.bean.AbstractBean;

/**
 * @author KangSeok
 * @date 2014. 8. 8.
 * @file_name AIB_STEP_WEIGHT_WriteBean.java
 *
 */
public class STEP_WEIGHT_WriteBean extends AbstractBean
{
	/*
	"LINE         :Line        ",
	"AREA         :Area        ",
	"DEFECTCD     :DefectCd    ",
	"STEPID       :StepId      ",
	"WEIGHT_VALUE :WeightValue ",
	"DEFECTNM     :DefectNm    ",
	"STEPNM       :StepNm      ",
	"USE_YN       :UseYn       ",
	"REGISTER_DATE:RegisterDate",
	*/

	private String line        ;
	private String area        ;
	private String defectCd    ;
	private String stepId      ;
	private String weightValue ;
	private String defectNm    ;
	private String stepNm      ;
	private String useYn       ;
	private String registerDate;

	///////////////////////////////////////////////////////////////////////////

	private byte[] bytes = null;
	private String title = null;
	private String str   = null;

	public void setBytes(byte[] bytes)
	{
		ByteBuffer buf = ByteBuffer.allocateDirect(10 * 1024);

		buf.clear();
		if (line         == null) buf.put((byte)','); else buf.put((byte)'"').put(line        .getBytes()).put((byte)'"').put((byte)',');
		if (area         == null) buf.put((byte)','); else buf.put((byte)'"').put(area        .getBytes()).put((byte)'"').put((byte)',');
		if (defectCd     == null) buf.put((byte)','); else buf.put((byte)'"').put(defectCd    .getBytes()).put((byte)'"').put((byte)',');
		if (stepId       == null) buf.put((byte)','); else buf.put((byte)'"').put(stepId      .getBytes()).put((byte)'"').put((byte)',');
		if (weightValue  == null) buf.put((byte)','); else buf.put((byte)'"').put(weightValue .getBytes()).put((byte)'"').put((byte)',');
		if (defectNm     == null) buf.put((byte)','); else buf.put((byte)'"').put(defectNm    .getBytes()).put((byte)'"').put((byte)',');
		if (stepNm       == null) buf.put((byte)','); else buf.put((byte)'"').put(stepNm      .getBytes()).put((byte)'"').put((byte)',');
		if (useYn        == null) buf.put((byte)','); else buf.put((byte)'"').put(useYn       .getBytes()).put((byte)'"').put((byte)',');
		if (registerDate == null) buf.put((byte)'\n'); else buf.put((byte)'"').put(registerDate.getBytes()).put((byte)'"').put((byte)'\n');

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
		buf.put((byte)'"').put("LINE"         .getBytes()).put((byte)'"').put((byte)',');
		buf.put((byte)'"').put("AREA"         .getBytes()).put((byte)'"').put((byte)',');
		buf.put((byte)'"').put("DEFECTCD"     .getBytes()).put((byte)'"').put((byte)',');
		buf.put((byte)'"').put("STEPID"       .getBytes()).put((byte)'"').put((byte)',');
		buf.put((byte)'"').put("WEIGHT_VALUE" .getBytes()).put((byte)'"').put((byte)',');
		buf.put((byte)'"').put("DEFECTNM"     .getBytes()).put((byte)'"').put((byte)',');
		buf.put((byte)'"').put("STEPNM"       .getBytes()).put((byte)'"').put((byte)',');
		buf.put((byte)'"').put("USE_YN"       .getBytes()).put((byte)'"').put((byte)',');
		buf.put((byte)'"').put("REGISTER_DATE".getBytes()).put((byte)'"').put((byte)'\n');

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
		return String.format("READ : [%s] [%s] [%s] [%s] [%s] [%s] [%s] [%s] [%s]"
			, line        
			, area        
			, defectCd    
			, stepId      
			, weightValue 
			, defectNm    
			, stepNm      
			, useYn       
			, registerDate
		);
	}

	public static String[] getHeader()
	{
		List<String> list = new ArrayList<String>();
		list.add("LINE"         );
		list.add("AREA"         );
		list.add("DEFECTCD"     );
		list.add("STEPID"       );
		list.add("WEIGHT_VALUE" );
		list.add("DEFECTNM"     );
		list.add("STEPNM"       );
		list.add("USE_YN"       );
		list.add("REGISTER_DATE");

		return list.toArray(new String[list.size()]);
	}

	public String[] getStringArray()
	{
		List<String> list = new ArrayList<String>();
		list.add(line        );
		list.add(area        );
		list.add(defectCd    );
		list.add(stepId      );
		list.add(weightValue );
		list.add(defectNm    );
		list.add(stepNm      );
		list.add(useYn       );
		list.add(registerDate);

		return list.toArray(new String[list.size()]);
	}

	public String getLine()
	{
		return line;
	}

	public void setLine(String line)
	{
		this.line = line;
	}

	public String getArea()
	{
		return area;
	}

	public void setArea(String area)
	{
		this.area = area;
	}

	public String getDefectCd()
	{
		return defectCd;
	}

	public void setDefectCd(String defectCd)
	{
		this.defectCd = defectCd;
	}

	public String getStepId()
	{
		return stepId;
	}

	public void setStepId(String stepId)
	{
		this.stepId = stepId;
	}

	public String getWeightValue()
	{
		return weightValue;
	}

	public void setWeightValue(String weightValue)
	{
		this.weightValue = weightValue;
	}

	public String getDefectNm()
	{
		return defectNm;
	}

	public void setDefectNm(String defectNm)
	{
		this.defectNm = defectNm;
	}

	public String getStepNm()
	{
		return stepNm;
	}

	public void setStepNm(String stepNm)
	{
		this.stepNm = stepNm;
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
