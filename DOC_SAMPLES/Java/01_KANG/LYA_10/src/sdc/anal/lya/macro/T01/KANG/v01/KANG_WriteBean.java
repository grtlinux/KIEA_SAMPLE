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

package sdc.anal.lya.macro.T01.KANG.v01;

import java.util.ArrayList;
import java.util.List;

import kiea.proj.sdc.anal.macro.impl.bean.AbstractBean;

/**
 * @author KangSeok
 * @date 2014. 8. 8.
 * @file_name ANAL_INDEX_WriteBean.java
 *
 */
public class KANG_WriteBean extends AbstractBean
{
	/*
	"QAF_JOB_BU              :QafJobBu              ",
	"QAF_JOB_ID              :QafJobId              ",
	"QAF_JOB_WF_SEQ          :QafJobWfSeq           ",
	"QAF_JOB_WF_ID           :QafJobWfId            ",
	"QAF_JOB_SERVICE         :QafJobService         ",
	"QAF_JOB_TBW_PROCESS_ID  :QafJobTbwProcessId    ",
	"QAF_JOB_START_DATETIME  :QafJobStartDatetime   ",
	"QAF_JOB_END_DATETIME    :QafJobEndDatetime     ",
	"QAF_JOB_RESULT          :QafJobResult          ",
	"QAF_JOB_MESSAGE         :QafJobMessage         ",
	"QAF_LAST_UPDATE_USER    :QafLastUpdateUser     ",
	"QAF_LAST_UPDATE_DATETIME:QafLastUpdateDatetime ",
	"QAF_ID_SAS_PROCESS_ID   :QafIdSasProcessId     ",
	"QAF_JOB_CLASS           :QafJobClass           ",
	*/

	private String qafJobBu              ;
	private String qafJobId              ;
	private String qafJobWfSeq           ;
	private String qafJobWfId            ;
	private String qafJobService         ;
	private String qafJobTbwProcessId    ;
	private String qafJobStartDatetime   ;
	private String qafJobEndDatetime     ;
	private String qafJobResult          ;
	private String qafJobMessage         ;
	private String qafLastUpdateUser     ;
	private String qafLastUpdateDatetime ;
	private String qafIdSasProcessId     ;
	private String qafJobClass           ;

	public String toString()
	{
		return String.format("READ : [%s] [%s] [%s] [%s] [%s] [%s] [%s] [%s] [%s] [%s] [%s] [%s] [%s] [%s]"
			, qafJobBu              
			, qafJobId              
			, qafJobWfSeq           
			, qafJobWfId            
			, qafJobService         
			, qafJobTbwProcessId    
			, qafJobStartDatetime   
			, qafJobEndDatetime     
			, qafJobResult          
			, qafJobMessage         
			, qafLastUpdateUser     
			, qafLastUpdateDatetime 
			, qafIdSasProcessId     
			, qafJobClass           
		);
	}

	public static String[] getHeader()
	{
		List<String> list = new ArrayList<String>();
		list.add("QAF_JOB_BU"              );
		list.add("QAF_JOB_ID"              );
		list.add("QAF_JOB_WF_SEQ"          );
		list.add("QAF_JOB_WF_ID"           );
		list.add("QAF_JOB_SERVICE"         );
		list.add("QAF_JOB_TBW_PROCESS_ID"  );
		list.add("QAF_JOB_START_DATETIME"  );
		list.add("QAF_JOB_END_DATETIME"    );
		list.add("QAF_JOB_RESULT"          );
		list.add("QAF_JOB_MESSAGE"         );
		list.add("QAF_LAST_UPDATE_USER"    );
		list.add("QAF_LAST_UPDATE_DATETIME");
		list.add("QAF_ID_SAS_PROCESS_ID"   );
		list.add("QAF_JOB_CLASS"           );

		return list.toArray(new String[list.size()]);
	}

	public String[] getStringArray()
	{
		List<String> list = new ArrayList<String>();
		list.add(qafJobBu              );
		list.add(qafJobId              );
		list.add(qafJobWfSeq           );
		list.add(qafJobWfId            );
		list.add(qafJobService         );
		list.add(qafJobTbwProcessId    );
		list.add(qafJobStartDatetime   );
		list.add(qafJobEndDatetime     );
		list.add(qafJobResult          );
		list.add(qafJobMessage         );
		list.add(qafLastUpdateUser     );
		list.add(qafLastUpdateDatetime );
		list.add(qafIdSasProcessId     );
		list.add(qafJobClass           );

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

	public String getQafJobId()
	{
		return qafJobId;
	}

	public void setQafJobId(String qafJobId)
	{
		this.qafJobId = qafJobId;
	}

	public String getQafJobWfSeq()
	{
		return qafJobWfSeq;
	}

	public void setQafJobWfSeq(String qafJobWfSeq)
	{
		this.qafJobWfSeq = qafJobWfSeq;
	}

	public String getQafJobWfId()
	{
		return qafJobWfId;
	}

	public void setQafJobWfId(String qafJobWfId)
	{
		this.qafJobWfId = qafJobWfId;
	}

	public String getQafJobService()
	{
		return qafJobService;
	}

	public void setQafJobService(String qafJobService)
	{
		this.qafJobService = qafJobService;
	}

	public String getQafJobTbwProcessId()
	{
		return qafJobTbwProcessId;
	}

	public void setQafJobTbwProcessId(String qafJobTbwProcessId)
	{
		this.qafJobTbwProcessId = qafJobTbwProcessId;
	}

	public String getQafJobStartDatetime()
	{
		return qafJobStartDatetime;
	}

	public void setQafJobStartDatetime(String qafJobStartDatetime)
	{
		this.qafJobStartDatetime = qafJobStartDatetime;
	}

	public String getQafJobEndDatetime()
	{
		return qafJobEndDatetime;
	}

	public void setQafJobEndDatetime(String qafJobEndDatetime)
	{
		this.qafJobEndDatetime = qafJobEndDatetime;
	}

	public String getQafJobResult()
	{
		return qafJobResult;
	}

	public void setQafJobResult(String qafJobResult)
	{
		this.qafJobResult = qafJobResult;
	}

	public String getQafJobMessage()
	{
		return qafJobMessage;
	}

	public void setQafJobMessage(String qafJobMessage)
	{
		this.qafJobMessage = qafJobMessage;
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

	public String getQafIdSasProcessId()
	{
		return qafIdSasProcessId;
	}

	public void setQafIdSasProcessId(String qafIdSasProcessId)
	{
		this.qafIdSasProcessId = qafIdSasProcessId;
	}

	public String getQafJobClass()
	{
		return qafJobClass;
	}

	public void setQafJobClass(String qafJobClass)
	{
		this.qafJobClass = qafJobClass;
	}
}
