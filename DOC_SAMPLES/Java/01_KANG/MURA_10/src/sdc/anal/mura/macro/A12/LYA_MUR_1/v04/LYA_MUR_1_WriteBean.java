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

package sdc.anal.mura.macro.A12.LYA_MUR_1.v04;

import java.util.ArrayList;
import java.util.List;

import kiea.proj.sdc.anal.macro.impl.bean.AbstractBean;

/**
 * @author KangSeok
 * @date 2014. 8. 8.
 * @file_name ANAL_INDEX_WriteBean.java
 *
 */
public class LYA_MUR_1_WriteBean extends AbstractBean
{
	/*
	"QAF_JOB_BU           :QafjobBu          ",
	"QAF_JOB_ID           :QafjobId          ",
	"QAF_JOB_DIP_NAME     :QafjobDipName     ",
	"QAF_JOB_DIP_VALUE    :QafjobDipValue    ",
	"QAF_JOB_DIP_CONDITION:QafjobDipCondition",
	*/

	private String qafjobBu          ;
	private String qafjobId          ;
	private String qafjobDipName     ;
	private String qafjobDipValue    ;
	private String qafjobDipCondition;

	public String toString()
	{
		return String.format("READ : [%s] [%s] [%s] [%s] [%s]"
			, qafjobBu          
			, qafjobId          
			, qafjobDipName     
			, qafjobDipValue    
			, qafjobDipCondition
		);
	}

	public static String[] getHeader()
	{
		List<String> list = new ArrayList<String>();
		list.add("QAF_JOB_BU"           );
		list.add("QAF_JOB_ID"           );
		list.add("QAF_JOB_DIP_NAME"     );
		list.add("QAF_JOB_DIP_VALUE"    );
		list.add("QAF_JOB_DIP_CONDITION");

		return list.toArray(new String[list.size()]);
	}

	public String[] getStringArray()
	{
		List<String> list = new ArrayList<String>();
		list.add(qafjobBu          );
		list.add(qafjobId          );
		list.add(qafjobDipName     );
		list.add(qafjobDipValue    );
		list.add(qafjobDipCondition);

		return list.toArray(new String[list.size()]);
	}

	/**
	 * @return the qafjobBu
	 */
	public String getQafjobBu()
	{
		return qafjobBu;
	}

	/**
	 * @return the qafjobId
	 */
	public String getQafjobId()
	{
		return qafjobId;
	}

	/**
	 * @return the qafjobDipName
	 */
	public String getQafjobDipName()
	{
		return qafjobDipName;
	}

	/**
	 * @return the qafjobDipValue
	 */
	public String getQafjobDipValue()
	{
		return qafjobDipValue;
	}

	/**
	 * @return the qafjobDipCondition
	 */
	public String getQafjobDipCondition()
	{
		return qafjobDipCondition;
	}

	/**
	 * @param qafjobBu the qafjobBu to set
	 */
	public void setQafjobBu(String qafjobBu)
	{
		this.qafjobBu = qafjobBu;
	}

	/**
	 * @param qafjobId the qafjobId to set
	 */
	public void setQafjobId(String qafjobId)
	{
		this.qafjobId = qafjobId;
	}

	/**
	 * @param qafjobDipName the qafjobDipName to set
	 */
	public void setQafjobDipName(String qafjobDipName)
	{
		this.qafjobDipName = qafjobDipName;
	}

	/**
	 * @param qafjobDipValue the qafjobDipValue to set
	 */
	public void setQafjobDipValue(String qafjobDipValue)
	{
		this.qafjobDipValue = qafjobDipValue;
	}

	/**
	 * @param qafjobDipCondition the qafjobDipCondition to set
	 */
	public void setQafjobDipCondition(String qafjobDipCondition)
	{
		this.qafjobDipCondition = qafjobDipCondition;
	}
}
