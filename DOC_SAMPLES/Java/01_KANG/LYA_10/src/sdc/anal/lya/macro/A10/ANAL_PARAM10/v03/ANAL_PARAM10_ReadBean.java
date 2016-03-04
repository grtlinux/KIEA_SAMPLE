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

package sdc.anal.lya.macro.A10.ANAL_PARAM10.v03;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;

import kiea.proj.sdc.anal.macro.impl.bean.AbstractBean;

/**
 * @author KangSeok
 * @date 2014. 8. 8.
 * @file_name ANAL_INDEX_ReadBean.java
 *
 */
public class ANAL_PARAM10_ReadBean extends AbstractBean
{
	/*
	"JOB_ID   :JobId   ",
	"SEQ      :Seq     ",
	"PARAM_NM :ParamNm ",
	"PARAM_VAL:ParamVal",
	"REG_DT   :RegDt   ",
	*/

	private String jobId   ;
	private String seq     ;
	private String paramNm ;
	private String paramVal;
	private String regDt   ;

	///////////////////////////////////////////////////////////////////////////

	private byte[] bytes = null;
	private String title = null;
	private String str   = null;

	public void setBytes(byte[] bytes)
	{
		ByteBuffer buf = ByteBuffer.allocateDirect(10 * 1024);

		buf.clear();
		buf.put((byte)'"').put(jobId   .getBytes()).put((byte)'"').put((byte)',');
		buf.put((byte)'"').put(seq     .getBytes()).put((byte)'"').put((byte)',');
		buf.put((byte)'"').put(paramNm .getBytes()).put((byte)'"').put((byte)',');
		buf.put((byte)'"').put(paramVal.getBytes()).put((byte)'"').put((byte)',');
		buf.put((byte)'"').put(regDt   .getBytes()).put((byte)'"').put((byte)'\n');

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
		buf.put((byte)'"').put("JOB_ID"   .getBytes()).put((byte)'"').put((byte)',');
		buf.put((byte)'"').put("SEQ"      .getBytes()).put((byte)'"').put((byte)',');
		buf.put((byte)'"').put("PARAM_NM" .getBytes()).put((byte)'"').put((byte)',');
		buf.put((byte)'"').put("PARAM_VAL".getBytes()).put((byte)'"').put((byte)',');
		buf.put((byte)'"').put("REG_DT"   .getBytes()).put((byte)'"').put((byte)'\n');

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
			, jobId   
			, seq     
			, paramNm 
			, paramVal
			, regDt   
		);
	}

	public static String[] getHeader()
	{
		List<String> list = new ArrayList<String>();
		list.add("JOB_ID"   );
		list.add("SEQ"      );
		list.add("PARAM_NM" );
		list.add("PARAM_VAL");
		list.add("REG_DT"   );

		return list.toArray(new String[list.size()]);
	}

	public String[] getStringArray()
	{
		List<String> list = new ArrayList<String>();
		list.add(jobId   );
		list.add(seq     );
		list.add(paramNm );
		list.add(paramVal);
		list.add(regDt   );

		return list.toArray(new String[list.size()]);
	}

	public String getJobId()
	{
		return jobId;
	}

	public void setJobId(String jobId)
	{
		this.jobId = jobId;
	}

	public String getSeq()
	{
		return seq;
	}

	public void setSeq(String seq)
	{
		this.seq = seq;
	}

	public String getParamNm()
	{
		return paramNm;
	}

	public void setParamNm(String paramNm)
	{
		this.paramNm = paramNm;
	}

	public String getParamVal()
	{
		return paramVal;
	}

	public void setParamVal(String paramVal)
	{
		this.paramVal = paramVal;
	}

	public String getRegDt()
	{
		return regDt;
	}

	public void setRegDt(String regDt)
	{
		this.regDt = regDt;
	}
}
