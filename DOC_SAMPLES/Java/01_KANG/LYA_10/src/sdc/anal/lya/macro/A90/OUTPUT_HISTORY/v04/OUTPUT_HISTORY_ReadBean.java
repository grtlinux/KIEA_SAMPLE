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

package sdc.anal.lya.macro.A90.OUTPUT_HISTORY.v04;

import java.util.ArrayList;
import java.util.List;

import kiea.proj.sdc.anal.macro.impl.bean.AbstractBean;

/**
 * @author KangSeok
 * @date 2014. 8. 8.
 * @file_name ANAL_INDEX_ReadBean.java
 *
 */
public class OUTPUT_HISTORY_ReadBean extends AbstractBean
{
	/*
	"JOB_ID     :JobId     ",
	"CMD_CODE   :CmdCode   ",
	"PROG_NM    :ProgNm    ",
	"CSV_NM     :CsvNm     ",
	"MAIN_CLASS :MainClass ",
	"LIST_CNT   :ListCnt   ",
	"S_TIME     :STime     ",
	"E_TIME     :ETime     ",
	"R_MSEC     :RMsec     ",
	"CSV_STATUS :CsvStatus ",
	"LOG_MESSAGE:LogMessage",
	*/

	private String jobId     ;
	private String cmdCode   ;
	private String progNm    ;
	private String csvNm     ;
	private String mainClass ;
	private String listCnt   ;
	private String STime     ;
	private String ETime     ;
	private String RMsec     ;
	private String csvStatus ;
	private String logMessage;

	public String toString()
	{
		return String.format("READ : [%s] [%s] [%s] [%s] [%s] [%s] [%s] [%s] [%s] [%s] [%s]"
			, jobId     
			, cmdCode   
			, progNm    
			, csvNm     
			, mainClass 
			, listCnt   
			, STime     
			, ETime     
			, RMsec     
			, csvStatus 
			, logMessage
		);
	}

	public static String[] getHeader()
	{
		List<String> list = new ArrayList<String>();
		list.add("JOB_ID"     );
		list.add("CMD_CODE"   );
		list.add("PROG_NM"    );
		list.add("CSV_NM"     );
		list.add("MAIN_CLASS" );
		list.add("LIST_CNT"   );
		list.add("S_TIME"     );
		list.add("E_TIME"     );
		list.add("R_MSEC"     );
		list.add("CSV_STATUS" );
		list.add("LOG_MESSAGE");

		return list.toArray(new String[list.size()]);
	}

	public String[] getStringArray()
	{
		List<String> list = new ArrayList<String>();
		list.add(jobId     );
		list.add(cmdCode   );
		list.add(progNm    );
		list.add(csvNm     );
		list.add(mainClass );
		list.add(listCnt   );
		list.add(STime     );
		list.add(ETime     );
		list.add(RMsec     );
		list.add(csvStatus );
		list.add(logMessage);

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

	public String getCmdCode()
	{
		return cmdCode;
	}

	public void setCmdCode(String cmdCode)
	{
		this.cmdCode = cmdCode;
	}

	public String getProgNm()
	{
		return progNm;
	}

	public void setProgNm(String progNm)
	{
		this.progNm = progNm;
	}

	public String getCsvNm()
	{
		return csvNm;
	}

	public void setCsvNm(String csvNm)
	{
		this.csvNm = csvNm;
	}

	public String getMainClass()
	{
		return mainClass;
	}

	public void setMainClass(String mainClass)
	{
		this.mainClass = mainClass;
	}

	public String getListCnt()
	{
		return listCnt;
	}

	public void setListCnt(String listCnt)
	{
		this.listCnt = listCnt;
	}

	public String getSTime()
	{
		return STime;
	}

	public void setSTime(String sTime)
	{
		STime = sTime;
	}

	public String getETime()
	{
		return ETime;
	}

	public void setETime(String eTime)
	{
		ETime = eTime;
	}

	public String getRMsec()
	{
		return RMsec;
	}

	public void setRMsec(String rMsec)
	{
		RMsec = rMsec;
	}

	public String getCsvStatus()
	{
		return csvStatus;
	}

	public void setCsvStatus(String csvStatus)
	{
		this.csvStatus = csvStatus;
	}

	public String getLogMessage()
	{
		return logMessage;
	}

	public void setLogMessage(String logMessage)
	{
		this.logMessage = logMessage;
	}
}
