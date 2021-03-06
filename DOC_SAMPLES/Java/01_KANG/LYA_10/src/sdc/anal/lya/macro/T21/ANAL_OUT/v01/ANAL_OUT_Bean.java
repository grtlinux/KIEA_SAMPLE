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

package sdc.anal.lya.macro.T21.ANAL_OUT.v01;

import java.util.ArrayList;
import java.util.List;

import kiea.proj.sdc.anal.macro.impl.bean.AbstractBean;

/**
 * @author KangSeok
 * @date 2014. 8. 8.
 * @file_name MURA_SPC_ReadBean.java
 *
 */
public class ANAL_OUT_Bean extends AbstractBean
{
	/*
	"T_DATE:tDate",
	"JOBID:jobId",
	"CNT_PARAM:cntParam",
	*/

	private String tDate;
	private String jobId;
	private String cntParam;

	public String toString()
	{
		return String.format("READ : [%s] [%s] [%s]"
			, tDate
			, jobId
			, cntParam
		);
	}

	public void addCntParam()
	{
		this.cntParam = "" + (Integer.parseInt(this.cntParam) + 1);
	}
	
	public static String[] getHeader()
	{
		List<String> list = new ArrayList<String>();
		list.add("T_DATE");
		list.add("JOBID");
		list.add("CNT_PARAM");

		return list.toArray(new String[list.size()]);
	}

	public String gettDate()
	{
		return tDate;
	}

	public void settDate(String tDate)
	{
		this.tDate = tDate;
	}

	public String getJobId()
	{
		return jobId;
	}

	public void setJobId(String jobId)
	{
		this.jobId = jobId;
	}

	public String getCntParam()
	{
		return cntParam;
	}

	public void setCntParam(String cntParam)
	{
		this.cntParam = cntParam;
	}

	public String[] getStringArray()
	{
		List<String> list = new ArrayList<String>();
		list.add(tDate);
		list.add(jobId);
		list.add(cntParam);

		return list.toArray(new String[list.size()]);
	}
}
