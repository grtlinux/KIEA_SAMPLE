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

package kiea.proj.sdc.anal.macro.sample.a04.bean;

import java.util.ArrayList;
import java.util.List;

import kiea.proj.sdc.anal.macro.impl.bean.AbstractBean;

/**
 * @author KangSeok
 * @date 2014. 8. 8.
 * @file_name MURA_HIST_WriteBean.java
 *
 */
public class MURA_HIST_WriteBean extends AbstractBean
{
	private String lineCode            ;
	private String glassId             ;
	private String eventOccurCode      ;
	private String eventOccurDetialCode;
	private String stepId              ;
	private String mainStepId          ;
	private String eqpId               ;
	private String comments            ;
	private String workStatus          ;
	private String eventOccurId        ;
	private String eventOccurDt        ;

	public String toString()
	{
		return String.format("READ : [%s] [%s] [%s] [%s] [%s]  [%s] [%s] [%s] [%s] [%s]  [%s]"
				, lineCode            
				, glassId             
				, eventOccurCode      
				, eventOccurDetialCode
				, stepId              
				, mainStepId          
				, eqpId               
				, comments            
				, workStatus          
				, eventOccurId        
				, eventOccurDt        
				);
	}

	public static String[] getHeader()
	{
		List<String> list = new ArrayList<String>();
		list.add("LINE_CODE"              );
		list.add("GLASS_ID"               );
		list.add("EVENT_OCCUR_CODE"       );
		list.add("EVENT_OCCUR_DETIAL_CODE");
		list.add("STEP_ID"                );
		list.add("MAINSTEPID"             );
		list.add("EQP_ID"                 );
		list.add("COMMENTS"               );
		list.add("WORK_STATUS"            );
		list.add("EVENT_OCCUR_ID"         );
		list.add("EVENT_OCCUR_DT"         );

		return list.toArray(new String[list.size()]);
	}

	public String[] getStringArray()
	{
		List<String> list = new ArrayList<String>();
		list.add(lineCode            );
		list.add(glassId             );
		list.add(eventOccurCode      );
		list.add(eventOccurDetialCode);
		list.add(stepId              );
		list.add(mainStepId          );
		list.add(eqpId               );
		list.add(comments            );
		list.add(workStatus          );
		list.add(eventOccurId        );
		list.add(eventOccurDt        );

		return list.toArray(new String[list.size()]);
	}

	/**
	 * @return the lineCode
	 */
	public String getLineCode()
	{
		return lineCode;
	}

	/**
	 * @param lineCode the lineCode to set
	 */
	public void setLineCode(String lineCode)
	{
		this.lineCode = lineCode;
	}

	/**
	 * @return the glassId
	 */
	public String getGlassId()
	{
		return glassId;
	}

	/**
	 * @param glassId the glassId to set
	 */
	public void setGlassId(String glassId)
	{
		this.glassId = glassId;
	}

	/**
	 * @return the eventOccurCode
	 */
	public String getEventOccurCode()
	{
		return eventOccurCode;
	}

	/**
	 * @param eventOccurCode the eventOccurCode to set
	 */
	public void setEventOccurCode(String eventOccurCode)
	{
		this.eventOccurCode = eventOccurCode;
	}

	/**
	 * @return the eventOccurDetialCode
	 */
	public String getEventOccurDetialCode()
	{
		return eventOccurDetialCode;
	}

	/**
	 * @param eventOccurDetialCode the eventOccurDetialCode to set
	 */
	public void setEventOccurDetialCode(String eventOccurDetialCode)
	{
		this.eventOccurDetialCode = eventOccurDetialCode;
	}

	/**
	 * @return the stepId
	 */
	public String getStepId()
	{
		return stepId;
	}

	/**
	 * @param stepId the stepId to set
	 */
	public void setStepId(String stepId)
	{
		this.stepId = stepId;
	}

	/**
	 * @return the mainStepId
	 */
	public String getMainStepId()
	{
		return mainStepId;
	}

	/**
	 * @param mainStepId the mainStepId to set
	 */
	public void setMainStepId(String mainStepId)
	{
		this.mainStepId = mainStepId;
	}

	/**
	 * @return the eqpId
	 */
	public String getEqpId()
	{
		return eqpId;
	}

	/**
	 * @param eqpId the eqpId to set
	 */
	public void setEqpId(String eqpId)
	{
		this.eqpId = eqpId;
	}

	/**
	 * @return the comments
	 */
	public String getComments()
	{
		return comments;
	}

	/**
	 * @param comments the comments to set
	 */
	public void setComments(String comments)
	{
		this.comments = comments;
	}

	/**
	 * @return the workStatus
	 */
	public String getWorkStatus()
	{
		return workStatus;
	}

	/**
	 * @param workStatus the workStatus to set
	 */
	public void setWorkStatus(String workStatus)
	{
		this.workStatus = workStatus;
	}

	/**
	 * @return the eventOccurId
	 */
	public String getEventOccurId()
	{
		return eventOccurId;
	}

	/**
	 * @param eventOccurId the eventOccurId to set
	 */
	public void setEventOccurId(String eventOccurId)
	{
		this.eventOccurId = eventOccurId;
	}

	/**
	 * @return the eventOccurDt
	 */
	public String getEventOccurDt()
	{
		return eventOccurDt;
	}

	/**
	 * @param eventOccurDt the eventOccurDt to set
	 */
	public void setEventOccurDt(String eventOccurDt)
	{
		this.eventOccurDt = eventOccurDt;
	}
}
