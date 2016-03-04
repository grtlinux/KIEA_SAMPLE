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

package sdc.anal.lya.macro.A11.CLUSTER_CONTROL.v02;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;

import kiea.proj.sdc.anal.macro.impl.bean.AbstractBean;

/**
 * @author KangSeok
 * @date 2014. 8. 8.
 * @file_name AIB_CLUSTER_CONTROL_ReadBean.java
 *
 */
public class CLUSTER_CONTROL_ReadBean extends AbstractBean
{
	/*
	"QAF_JOB_BU              :QafJobBu             ",
	"QAF_LINE_CODE           :QafLineCode          ",
	"QAF_GROUP_ID            :QafGroupId           ",
	"QAF_LOGIC_ID            :QafLogicId           ",
	"QAF_JUDGE_STEP          :QafJudgeStep         ",
	"QAF_DEFECT_GROUP_CODE   :QafDefectGroupCode   ",
	"QAF_LOGIC_SEQ           :QafLogicSeq          ",
	"QAF_JUDGE_CODE          :QafJudgeCode         ",
	"QAF_EXTRACT_DAYS        :QafExtractDays       ",
	"QAF_WORKFLOW_NAME       :QafWorkflowName      ",
	"QAF_LOGIC_STATUS        :QafLogicStatus       ",
	"QAF_LOGIC_NAME          :QafLogicName         ",
	"QAF_LOGIC_SOURCE_CODE   :QafLogicSourceCode   ",
	"QAF_LAST_UPDATE_USER    :QafLastUpdateUser    ",
	"QAF_LAST_UPDATE_DATETIME:QafLastUpdateDatetime",
	*/

	private String qafJobBu             ;
	private String qafLineCode          ;
	private String qafGroupId           ;
	private String qafLogicId           ;
	private String qafJudgeStep         ;
	private String qafDefectGroupCode   ;
	private String qafLogicSeq          ;
	private String qafJudgeCode         ;
	private String qafExtractDays       ;
	private String qafWorkflowName      ;
	private String qafLogicStatus       ;
	private String qafLogicName         ;
	private String qafLogicSourceCode   ;
	private String qafLastUpdateUser    ;
	private String qafLastUpdateDatetime;

	///////////////////////////////////////////////////////////////////////////

	private byte[] bytes = null;
	private String title = null;
	private String str   = null;

	public void setBytes(byte[] bytes)
	{
		ByteBuffer buf = ByteBuffer.allocateDirect(10 * 1024);

		buf.clear();
		if (qafJobBu              == null) buf.put((byte)','); else buf.put((byte)'"').put(qafJobBu             .getBytes()).put((byte)'"').put((byte)',');
		if (qafLineCode           == null) buf.put((byte)','); else buf.put((byte)'"').put(qafLineCode          .getBytes()).put((byte)'"').put((byte)',');
		if (qafGroupId            == null) buf.put((byte)','); else buf.put((byte)'"').put(qafGroupId           .getBytes()).put((byte)'"').put((byte)',');
		if (qafLogicId            == null) buf.put((byte)','); else buf.put((byte)'"').put(qafLogicId           .getBytes()).put((byte)'"').put((byte)',');
		if (qafJudgeStep          == null) buf.put((byte)','); else buf.put((byte)'"').put(qafJudgeStep         .getBytes()).put((byte)'"').put((byte)',');
		if (qafDefectGroupCode    == null) buf.put((byte)','); else buf.put((byte)'"').put(qafDefectGroupCode   .getBytes()).put((byte)'"').put((byte)',');
		if (qafLogicSeq           == null) buf.put((byte)','); else buf.put((byte)'"').put(qafLogicSeq          .getBytes()).put((byte)'"').put((byte)',');
		if (qafJudgeCode          == null) buf.put((byte)','); else buf.put((byte)'"').put(qafJudgeCode         .getBytes()).put((byte)'"').put((byte)',');
		if (qafExtractDays        == null) buf.put((byte)','); else buf.put((byte)'"').put(qafExtractDays       .getBytes()).put((byte)'"').put((byte)',');
		if (qafWorkflowName       == null) buf.put((byte)','); else buf.put((byte)'"').put(qafWorkflowName      .getBytes()).put((byte)'"').put((byte)',');
		if (qafLogicStatus        == null) buf.put((byte)','); else buf.put((byte)'"').put(qafLogicStatus       .getBytes()).put((byte)'"').put((byte)',');
		if (qafLogicName          == null) buf.put((byte)','); else buf.put((byte)'"').put(qafLogicName         .getBytes()).put((byte)'"').put((byte)',');
		if (qafLogicSourceCode    == null) buf.put((byte)','); else buf.put((byte)'"').put(qafLogicSourceCode   .getBytes()).put((byte)'"').put((byte)',');
		if (qafLastUpdateUser     == null) buf.put((byte)','); else buf.put((byte)'"').put(qafLastUpdateUser    .getBytes()).put((byte)'"').put((byte)',');
		if (qafLastUpdateDatetime == null) buf.put((byte)','); else buf.put((byte)'"').put(qafLastUpdateDatetime.getBytes()).put((byte)'"').put((byte)'\n');

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
		buf.put((byte)'"').put("QAF_JOB_BU"              .getBytes()).put((byte)'"').put((byte)',');
		buf.put((byte)'"').put("QAF_LINE_CODE"           .getBytes()).put((byte)'"').put((byte)',');
		buf.put((byte)'"').put("QAF_GROUP_ID"            .getBytes()).put((byte)'"').put((byte)',');
		buf.put((byte)'"').put("QAF_LOGIC_ID"            .getBytes()).put((byte)'"').put((byte)',');
		buf.put((byte)'"').put("QAF_JUDGE_STEP"          .getBytes()).put((byte)'"').put((byte)',');
		buf.put((byte)'"').put("QAF_DEFECT_GROUP_CODE"   .getBytes()).put((byte)'"').put((byte)',');
		buf.put((byte)'"').put("QAF_LOGIC_SEQ"           .getBytes()).put((byte)'"').put((byte)',');
		buf.put((byte)'"').put("QAF_JUDGE_CODE"          .getBytes()).put((byte)'"').put((byte)',');
		buf.put((byte)'"').put("QAF_EXTRACT_DAYS"        .getBytes()).put((byte)'"').put((byte)',');
		buf.put((byte)'"').put("QAF_WORKFLOW_NAME"       .getBytes()).put((byte)'"').put((byte)',');
		buf.put((byte)'"').put("QAF_LOGIC_STATUS"        .getBytes()).put((byte)'"').put((byte)',');
		buf.put((byte)'"').put("QAF_LOGIC_NAME"          .getBytes()).put((byte)'"').put((byte)',');
		buf.put((byte)'"').put("QAF_LOGIC_SOURCE_CODE"   .getBytes()).put((byte)'"').put((byte)',');
		buf.put((byte)'"').put("QAF_LAST_UPDATE_USER"    .getBytes()).put((byte)'"').put((byte)',');
		buf.put((byte)'"').put("QAF_LAST_UPDATE_DATETIME".getBytes()).put((byte)'"').put((byte)'\n');

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
		return String.format("READ : [%s] [%s] [%s] [%s] [%s] [%s] [%s] [%s] [%s] [%s] [%s] [%s] [%s] [%s] [%s]"
			, qafJobBu             
			, qafLineCode          
			, qafGroupId           
			, qafLogicId           
			, qafJudgeStep         
			, qafDefectGroupCode   
			, qafLogicSeq          
			, qafJudgeCode         
			, qafExtractDays       
			, qafWorkflowName      
			, qafLogicStatus       
			, qafLogicName         
			, qafLogicSourceCode   
			, qafLastUpdateUser    
			, qafLastUpdateDatetime
		);
	}

	public static String[] getHeader()
	{
		List<String> list = new ArrayList<String>();
		list.add("QAF_JOB_BU"              );
		list.add("QAF_LINE_CODE"           );
		list.add("QAF_GROUP_ID"            );
		list.add("QAF_LOGIC_ID"            );
		list.add("QAF_JUDGE_STEP"          );
		list.add("QAF_DEFECT_GROUP_CODE"   );
		list.add("QAF_LOGIC_SEQ"           );
		list.add("QAF_JUDGE_CODE"          );
		list.add("QAF_EXTRACT_DAYS"        );
		list.add("QAF_WORKFLOW_NAME"       );
		list.add("QAF_LOGIC_STATUS"        );
		list.add("QAF_LOGIC_NAME"          );
		list.add("QAF_LOGIC_SOURCE_CODE"   );
		list.add("QAF_LAST_UPDATE_USER"    );
		list.add("QAF_LAST_UPDATE_DATETIME");

		return list.toArray(new String[list.size()]);
	}

	public String[] getStringArray()
	{
		List<String> list = new ArrayList<String>();
		list.add(qafJobBu             );
		list.add(qafLineCode          );
		list.add(qafGroupId           );
		list.add(qafLogicId           );
		list.add(qafJudgeStep         );
		list.add(qafDefectGroupCode   );
		list.add(qafLogicSeq          );
		list.add(qafJudgeCode         );
		list.add(qafExtractDays       );
		list.add(qafWorkflowName      );
		list.add(qafLogicStatus       );
		list.add(qafLogicName         );
		list.add(qafLogicSourceCode   );
		list.add(qafLastUpdateUser    );
		list.add(qafLastUpdateDatetime);

		return list.toArray(new String[list.size()]);
	}

	public String getQafJobBu()
	{
		return qafJobBu;
	}

	public void setQafJobBu(String qafJobBu)
	{
		this.qafJobBu = qafJobBu;
	}

	public String getQafLineCode()
	{
		return qafLineCode;
	}

	public void setQafLineCode(String qafLineCode)
	{
		this.qafLineCode = qafLineCode;
	}

	public String getQafGroupId()
	{
		return qafGroupId;
	}

	public void setQafGroupId(String qafGroupId)
	{
		this.qafGroupId = qafGroupId;
	}

	public String getQafLogicId()
	{
		return qafLogicId;
	}

	public void setQafLogicId(String qafLogicId)
	{
		this.qafLogicId = qafLogicId;
	}

	public String getQafJudgeStep()
	{
		return qafJudgeStep;
	}

	public void setQafJudgeStep(String qafJudgeStep)
	{
		this.qafJudgeStep = qafJudgeStep;
	}

	public String getQafDefectGroupCode()
	{
		return qafDefectGroupCode;
	}

	public void setQafDefectGroupCode(String qafDefectGroupCode)
	{
		this.qafDefectGroupCode = qafDefectGroupCode;
	}

	public String getQafLogicSeq()
	{
		return qafLogicSeq;
	}

	public void setQafLogicSeq(String qafLogicSeq)
	{
		this.qafLogicSeq = qafLogicSeq;
	}

	public String getQafJudgeCode()
	{
		return qafJudgeCode;
	}

	public void setQafJudgeCode(String qafJudgeCode)
	{
		this.qafJudgeCode = qafJudgeCode;
	}

	public String getQafExtractDays()
	{
		return qafExtractDays;
	}

	public void setQafExtractDays(String qafExtractDays)
	{
		this.qafExtractDays = qafExtractDays;
	}

	public String getQafWorkflowName()
	{
		return qafWorkflowName;
	}

	public void setQafWorkflowName(String qafWorkflowName)
	{
		this.qafWorkflowName = qafWorkflowName;
	}

	public String getQafLogicStatus()
	{
		return qafLogicStatus;
	}

	public void setQafLogicStatus(String qafLogicStatus)
	{
		this.qafLogicStatus = qafLogicStatus;
	}

	public String getQafLogicName()
	{
		return qafLogicName;
	}

	public void setQafLogicName(String qafLogicName)
	{
		this.qafLogicName = qafLogicName;
	}

	public String getQafLogicSourceCode()
	{
		return qafLogicSourceCode;
	}

	public void setQafLogicSourceCode(String qafLogicSourceCode)
	{
		this.qafLogicSourceCode = qafLogicSourceCode;
	}

	public String getQafLastUpdateUser()
	{
		return qafLastUpdateUser;
	}

	public void setQafLastUpdateUser(String qafLastUpdateUser)
	{
		this.qafLastUpdateUser = qafLastUpdateUser;
	}

	public String getQafLastUpdateDatetime()
	{
		return qafLastUpdateDatetime;
	}

	public void setQafLastUpdateDatetime(String qafLastUpdateDatetime)
	{
		this.qafLastUpdateDatetime = qafLastUpdateDatetime;
	}
}
