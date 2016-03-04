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

package sdc.anal.lya.macro.A11.CLUSTER_CONTROL.v01;

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

	/**
	 * @return the qafJobBu
	 */
	public String getQafJobBu()
	{
		return qafJobBu;
	}

	/**
	 * @return the qafLineCode
	 */
	public String getQafLineCode()
	{
		return qafLineCode;
	}

	/**
	 * @return the qafGroupId
	 */
	public String getQafGroupId()
	{
		return qafGroupId;
	}

	/**
	 * @return the qafLogicId
	 */
	public String getQafLogicId()
	{
		return qafLogicId;
	}

	/**
	 * @return the qafJudgeStep
	 */
	public String getQafJudgeStep()
	{
		return qafJudgeStep;
	}

	/**
	 * @return the qafDefectGroupCode
	 */
	public String getQafDefectGroupCode()
	{
		return qafDefectGroupCode;
	}

	/**
	 * @return the qafLogicSeq
	 */
	public String getQafLogicSeq()
	{
		return qafLogicSeq;
	}

	/**
	 * @return the qafJudgeCode
	 */
	public String getQafJudgeCode()
	{
		return qafJudgeCode;
	}

	/**
	 * @return the qafExtractDays
	 */
	public String getQafExtractDays()
	{
		return qafExtractDays;
	}

	/**
	 * @return the qafWorkflowName
	 */
	public String getQafWorkflowName()
	{
		return qafWorkflowName;
	}

	/**
	 * @return the qafLogicStatus
	 */
	public String getQafLogicStatus()
	{
		return qafLogicStatus;
	}

	/**
	 * @return the qafLogicName
	 */
	public String getQafLogicName()
	{
		return qafLogicName;
	}

	/**
	 * @return the qafLogicSourceCode
	 */
	public String getQafLogicSourceCode()
	{
		return qafLogicSourceCode;
	}

	/**
	 * @return the qafLastUpdateUser
	 */
	public String getQafLastUpdateUser()
	{
		return qafLastUpdateUser;
	}

	/**
	 * @return the qafLastUpdateDatetime
	 */
	public String getQafLastUpdateDatetime()
	{
		return qafLastUpdateDatetime;
	}

	/**
	 * @param qafJobBu the qafJobBu to set
	 */
	public void setQafJobBu(String qafJobBu)
	{
		this.qafJobBu = qafJobBu;
	}

	/**
	 * @param qafLineCode the qafLineCode to set
	 */
	public void setQafLineCode(String qafLineCode)
	{
		this.qafLineCode = qafLineCode;
	}

	/**
	 * @param qafGroupId the qafGroupId to set
	 */
	public void setQafGroupId(String qafGroupId)
	{
		this.qafGroupId = qafGroupId;
	}

	/**
	 * @param qafLogicId the qafLogicId to set
	 */
	public void setQafLogicId(String qafLogicId)
	{
		this.qafLogicId = qafLogicId;
	}

	/**
	 * @param qafJudgeStep the qafJudgeStep to set
	 */
	public void setQafJudgeStep(String qafJudgeStep)
	{
		this.qafJudgeStep = qafJudgeStep;
	}

	/**
	 * @param qafDefectGroupCode the qafDefectGroupCode to set
	 */
	public void setQafDefectGroupCode(String qafDefectGroupCode)
	{
		this.qafDefectGroupCode = qafDefectGroupCode;
	}

	/**
	 * @param qafLogicSeq the qafLogicSeq to set
	 */
	public void setQafLogicSeq(String qafLogicSeq)
	{
		this.qafLogicSeq = qafLogicSeq;
	}

	/**
	 * @param qafJudgeCode the qafJudgeCode to set
	 */
	public void setQafJudgeCode(String qafJudgeCode)
	{
		this.qafJudgeCode = qafJudgeCode;
	}

	/**
	 * @param qafExtractDays the qafExtractDays to set
	 */
	public void setQafExtractDays(String qafExtractDays)
	{
		this.qafExtractDays = qafExtractDays;
	}

	/**
	 * @param qafWorkflowName the qafWorkflowName to set
	 */
	public void setQafWorkflowName(String qafWorkflowName)
	{
		this.qafWorkflowName = qafWorkflowName;
	}

	/**
	 * @param qafLogicStatus the qafLogicStatus to set
	 */
	public void setQafLogicStatus(String qafLogicStatus)
	{
		this.qafLogicStatus = qafLogicStatus;
	}

	/**
	 * @param qafLogicName the qafLogicName to set
	 */
	public void setQafLogicName(String qafLogicName)
	{
		this.qafLogicName = qafLogicName;
	}

	/**
	 * @param qafLogicSourceCode the qafLogicSourceCode to set
	 */
	public void setQafLogicSourceCode(String qafLogicSourceCode)
	{
		this.qafLogicSourceCode = qafLogicSourceCode;
	}

	/**
	 * @param qafLastUpdateUser the qafLastUpdateUser to set
	 */
	public void setQafLastUpdateUser(String qafLastUpdateUser)
	{
		this.qafLastUpdateUser = qafLastUpdateUser;
	}

	/**
	 * @param qafLastUpdateDatetime the qafLastUpdateDatetime to set
	 */
	public void setQafLastUpdateDatetime(String qafLastUpdateDatetime)
	{
		this.qafLastUpdateDatetime = qafLastUpdateDatetime;
	}
}
