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

package sdc.anal.lya.macro.A11.DELAYTIME_PRC.v01;

import java.util.ArrayList;
import java.util.List;

import kiea.proj.sdc.anal.macro.impl.bean.AbstractBean;

/**
 * @author KangSeok
 * @date 2014. 8. 8.
 * @file_name AIB_DELAYTIME_PRC_ReadBean.java
 *
 */
public class DELAYTIME_PRC_ReadBean extends AbstractBean
{
	/*
	"LINE         :Line        ",
	"STARTSTEPID  :StartStepId ",
	"STEPID       :StepId      ",
	"PROCESSTIME  :ProcessTime ",
	"USE_YN       :UseYn       ",
	"REGISTER_DATE:RegisterDate",
	*/

	private String line        ;
	private String startStepId ;
	private String stepId      ;
	private String processTime ;
	private String useYn       ;
	private String registerDate;

	public String toString()
	{
		return String.format("READ : [%s] [%s] [%s] [%s] [%s] [%s]"
			, line        
			, startStepId 
			, stepId      
			, processTime 
			, useYn       
			, registerDate
		);
	}

	public static String[] getHeader()
	{
		List<String> list = new ArrayList<String>();
		list.add("LINE"         );
		list.add("STARTSTEPID"  );
		list.add("STEPID"       );
		list.add("PROCESSTIME"  );
		list.add("USE_YN"       );
		list.add("REGISTER_DATE");

		return list.toArray(new String[list.size()]);
	}

	public String[] getStringArray()
	{
		List<String> list = new ArrayList<String>();
		list.add(line        );
		list.add(startStepId );
		list.add(stepId      );
		list.add(processTime );
		list.add(useYn       );
		list.add(registerDate);

		return list.toArray(new String[list.size()]);
	}

	/**
	 * @return the line
	 */
	public String getLine()
	{
		return line;
	}

	/**
	 * @return the startStepId
	 */
	public String getStartStepId()
	{
		return startStepId;
	}

	/**
	 * @return the stepId
	 */
	public String getStepId()
	{
		return stepId;
	}

	/**
	 * @return the processTime
	 */
	public String getProcessTime()
	{
		return processTime;
	}

	/**
	 * @return the useYn
	 */
	public String getUseYn()
	{
		return useYn;
	}

	/**
	 * @return the registerDate
	 */
	public String getRegisterDate()
	{
		return registerDate;
	}

	/**
	 * @param line the line to set
	 */
	public void setLine(String line)
	{
		this.line = line;
	}

	/**
	 * @param startStepId the startStepId to set
	 */
	public void setStartStepId(String startStepId)
	{
		this.startStepId = startStepId;
	}

	/**
	 * @param stepId the stepId to set
	 */
	public void setStepId(String stepId)
	{
		this.stepId = stepId;
	}

	/**
	 * @param processTime the processTime to set
	 */
	public void setProcessTime(String processTime)
	{
		this.processTime = processTime;
	}

	/**
	 * @param useYn the useYn to set
	 */
	public void setUseYn(String useYn)
	{
		this.useYn = useYn;
	}

	/**
	 * @param registerDate the registerDate to set
	 */
	public void setRegisterDate(String registerDate)
	{
		this.registerDate = registerDate;
	}
}
