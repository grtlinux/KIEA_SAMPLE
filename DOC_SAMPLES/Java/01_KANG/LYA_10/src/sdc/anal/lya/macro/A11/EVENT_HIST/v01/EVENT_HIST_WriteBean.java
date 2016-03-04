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

package sdc.anal.lya.macro.A11.EVENT_HIST.v01;

import java.util.ArrayList;
import java.util.List;

import kiea.proj.sdc.anal.macro.impl.bean.AbstractBean;

/**
 * @author KangSeok
 * @date 2014. 8. 8.
 * @file_name AIB_EVENT_HIST_WriteBean.java
 *
 */
public class EVENT_HIST_WriteBean extends AbstractBean
{
	/*
	"USER_GROUP_CODE        :UserGroupCode       ",
	"PROCESS_ID             :ProcessId           ",
	"PRODUCT_ID             :ProductId           ",
	"PRODUCT_TYPE           :ProductType         ",
	"AREA_CODE              :AreaCode            ",
	"SUB_AREA_CODE          :SubAreaCode         ",
	"LINE_CODE              :LineCode            ",
	"GLASS_ID               :GlassId             ",
	"EVENT_OCCUR_CODE       :EventOccurCode      ",
	"EVENT_OCCUR_DETIAL_CODE:EventOccurDetialCode",
	"STEP_ID                :StepId              ",
	"EQP_ID                 :EqpId               ",
	"COMMENTS               :Comments            ",
	"WORK_STATUS            :WorkStatus          ",
	"EVENT_OCCUR_ID         :EventOccurId        ",
	"EVENT_OCCUR_DT         :EventOccurDt        ",
	"UNIT_ID                :UnitId              ",
	"LAYER_CODE             :LayerCode           ",
	"WORK_START_DT          :WorkStartDt         ",
	"WORK_END_DT            :WorkEndDt           ",
	*/

	private String userGroupCode       ;
	private String processId           ;
	private String productId           ;
	private String productType         ;
	private String areaCode            ;
	private String subAreaCode         ;
	private String lineCode            ;
	private String glassId             ;
	private String eventOccurCode      ;
	private String eventOccurDetialCode;
	private String stepId              ;
	private String eqpId               ;
	private String comments            ;
	private String workStatus          ;
	private String eventOccurId        ;
	private String eventOccurDt        ;
	private String unitId              ;
	private String layerCode           ;
	private String workStartDt         ;
	private String workEndDt           ;

	public String toString()
	{
		return String.format("READ : [%s] [%s] [%s] [%s] [%s] [%s] [%s] [%s] [%s] [%s] [%s] [%s] [%s] [%s] [%s] [%s] [%s] [%s] [%s] [%s]"
			, userGroupCode       
			, processId           
			, productId           
			, productType         
			, areaCode            
			, subAreaCode         
			, lineCode            
			, glassId             
			, eventOccurCode      
			, eventOccurDetialCode
			, stepId              
			, eqpId               
			, comments            
			, workStatus          
			, eventOccurId        
			, eventOccurDt        
			, unitId              
			, layerCode           
			, workStartDt         
			, workEndDt           
		);
	}

	public static String[] getHeader()
	{
		List<String> list = new ArrayList<String>();
		list.add("USER_GROUP_CODE"        );
		list.add("PROCESS_ID"             );
		list.add("PRODUCT_ID"             );
		list.add("PRODUCT_TYPE"           );
		list.add("AREA_CODE"              );
		list.add("SUB_AREA_CODE"          );
		list.add("LINE_CODE"              );
		list.add("GLASS_ID"               );
		list.add("EVENT_OCCUR_CODE"       );
		list.add("EVENT_OCCUR_DETIAL_CODE");
		list.add("STEP_ID"                );
		list.add("EQP_ID"                 );
		list.add("COMMENTS"               );
		list.add("WORK_STATUS"            );
		list.add("EVENT_OCCUR_ID"         );
		list.add("EVENT_OCCUR_DT"         );
		list.add("UNIT_ID"                );
		list.add("LAYER_CODE"             );
		list.add("WORK_START_DT"          );
		list.add("WORK_END_DT"            );

		return list.toArray(new String[list.size()]);
	}

	public String[] getStringArray()
	{
		List<String> list = new ArrayList<String>();
		list.add(userGroupCode       );
		list.add(processId           );
		list.add(productId           );
		list.add(productType         );
		list.add(areaCode            );
		list.add(subAreaCode         );
		list.add(lineCode            );
		list.add(glassId             );
		list.add(eventOccurCode      );
		list.add(eventOccurDetialCode);
		list.add(stepId              );
		list.add(eqpId               );
		list.add(comments            );
		list.add(workStatus          );
		list.add(eventOccurId        );
		list.add(eventOccurDt        );
		list.add(unitId              );
		list.add(layerCode           );
		list.add(workStartDt         );
		list.add(workEndDt           );

		return list.toArray(new String[list.size()]);
	}

	/**
	 * @return the userGroupCode
	 */
	public String getUserGroupCode()
	{
		return userGroupCode;
	}

	/**
	 * @return the processId
	 */
	public String getProcessId()
	{
		return processId;
	}

	/**
	 * @return the productId
	 */
	public String getProductId()
	{
		return productId;
	}

	/**
	 * @return the productType
	 */
	public String getProductType()
	{
		return productType;
	}

	/**
	 * @return the areaCode
	 */
	public String getAreaCode()
	{
		return areaCode;
	}

	/**
	 * @return the subAreaCode
	 */
	public String getSubAreaCode()
	{
		return subAreaCode;
	}

	/**
	 * @return the lineCode
	 */
	public String getLineCode()
	{
		return lineCode;
	}

	/**
	 * @return the glassId
	 */
	public String getGlassId()
	{
		return glassId;
	}

	/**
	 * @return the eventOccurCode
	 */
	public String getEventOccurCode()
	{
		return eventOccurCode;
	}

	/**
	 * @return the eventOccurDetialCode
	 */
	public String getEventOccurDetialCode()
	{
		return eventOccurDetialCode;
	}

	/**
	 * @return the stepId
	 */
	public String getStepId()
	{
		return stepId;
	}

	/**
	 * @return the eqpId
	 */
	public String getEqpId()
	{
		return eqpId;
	}

	/**
	 * @return the comments
	 */
	public String getComments()
	{
		return comments;
	}

	/**
	 * @return the workStatus
	 */
	public String getWorkStatus()
	{
		return workStatus;
	}

	/**
	 * @return the eventOccurId
	 */
	public String getEventOccurId()
	{
		return eventOccurId;
	}

	/**
	 * @return the eventOccurDt
	 */
	public String getEventOccurDt()
	{
		return eventOccurDt;
	}

	/**
	 * @return the unitId
	 */
	public String getUnitId()
	{
		return unitId;
	}

	/**
	 * @return the layerCode
	 */
	public String getLayerCode()
	{
		return layerCode;
	}

	/**
	 * @return the workStartDt
	 */
	public String getWorkStartDt()
	{
		return workStartDt;
	}

	/**
	 * @return the workEndDt
	 */
	public String getWorkEndDt()
	{
		return workEndDt;
	}

	/**
	 * @param userGroupCode the userGroupCode to set
	 */
	public void setUserGroupCode(String userGroupCode)
	{
		this.userGroupCode = userGroupCode;
	}

	/**
	 * @param processId the processId to set
	 */
	public void setProcessId(String processId)
	{
		this.processId = processId;
	}

	/**
	 * @param productId the productId to set
	 */
	public void setProductId(String productId)
	{
		this.productId = productId;
	}

	/**
	 * @param productType the productType to set
	 */
	public void setProductType(String productType)
	{
		this.productType = productType;
	}

	/**
	 * @param areaCode the areaCode to set
	 */
	public void setAreaCode(String areaCode)
	{
		this.areaCode = areaCode;
	}

	/**
	 * @param subAreaCode the subAreaCode to set
	 */
	public void setSubAreaCode(String subAreaCode)
	{
		this.subAreaCode = subAreaCode;
	}

	/**
	 * @param lineCode the lineCode to set
	 */
	public void setLineCode(String lineCode)
	{
		this.lineCode = lineCode;
	}

	/**
	 * @param glassId the glassId to set
	 */
	public void setGlassId(String glassId)
	{
		this.glassId = glassId;
	}

	/**
	 * @param eventOccurCode the eventOccurCode to set
	 */
	public void setEventOccurCode(String eventOccurCode)
	{
		this.eventOccurCode = eventOccurCode;
	}

	/**
	 * @param eventOccurDetialCode the eventOccurDetialCode to set
	 */
	public void setEventOccurDetialCode(String eventOccurDetialCode)
	{
		this.eventOccurDetialCode = eventOccurDetialCode;
	}

	/**
	 * @param stepId the stepId to set
	 */
	public void setStepId(String stepId)
	{
		this.stepId = stepId;
	}

	/**
	 * @param eqpId the eqpId to set
	 */
	public void setEqpId(String eqpId)
	{
		this.eqpId = eqpId;
	}

	/**
	 * @param comments the comments to set
	 */
	public void setComments(String comments)
	{
		this.comments = comments;
	}

	/**
	 * @param workStatus the workStatus to set
	 */
	public void setWorkStatus(String workStatus)
	{
		this.workStatus = workStatus;
	}

	/**
	 * @param eventOccurId the eventOccurId to set
	 */
	public void setEventOccurId(String eventOccurId)
	{
		this.eventOccurId = eventOccurId;
	}

	/**
	 * @param eventOccurDt the eventOccurDt to set
	 */
	public void setEventOccurDt(String eventOccurDt)
	{
		this.eventOccurDt = eventOccurDt;
	}

	/**
	 * @param unitId the unitId to set
	 */
	public void setUnitId(String unitId)
	{
		this.unitId = unitId;
	}

	/**
	 * @param layerCode the layerCode to set
	 */
	public void setLayerCode(String layerCode)
	{
		this.layerCode = layerCode;
	}

	/**
	 * @param workStartDt the workStartDt to set
	 */
	public void setWorkStartDt(String workStartDt)
	{
		this.workStartDt = workStartDt;
	}

	/**
	 * @param workEndDt the workEndDt to set
	 */
	public void setWorkEndDt(String workEndDt)
	{
		this.workEndDt = workEndDt;
	}
}
