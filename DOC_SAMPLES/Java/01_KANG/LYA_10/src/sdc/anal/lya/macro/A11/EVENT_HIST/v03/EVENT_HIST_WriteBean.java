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

package sdc.anal.lya.macro.A11.EVENT_HIST.v03;

import java.nio.ByteBuffer;
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

	///////////////////////////////////////////////////////////////////////////

	private byte[] bytes = null;
	private String title = null;
	private String str   = null;

	public void setBytes(byte[] bytes)
	{
		ByteBuffer buf = ByteBuffer.allocateDirect(10 * 1024);

		buf.clear();
		if (userGroupCode        == null) buf.put((byte)','); else buf.put((byte)'"').put(userGroupCode       .getBytes()).put((byte)'"').put((byte)',');
		if (processId            == null) buf.put((byte)','); else buf.put((byte)'"').put(processId           .getBytes()).put((byte)'"').put((byte)',');
		if (productId            == null) buf.put((byte)','); else buf.put((byte)'"').put(productId           .getBytes()).put((byte)'"').put((byte)',');
		if (productType          == null) buf.put((byte)','); else buf.put((byte)'"').put(productType         .getBytes()).put((byte)'"').put((byte)',');
		if (areaCode             == null) buf.put((byte)','); else buf.put((byte)'"').put(areaCode            .getBytes()).put((byte)'"').put((byte)',');
		if (subAreaCode          == null) buf.put((byte)','); else buf.put((byte)'"').put(subAreaCode         .getBytes()).put((byte)'"').put((byte)',');
		if (lineCode             == null) buf.put((byte)','); else buf.put((byte)'"').put(lineCode            .getBytes()).put((byte)'"').put((byte)',');
		if (glassId              == null) buf.put((byte)','); else buf.put((byte)'"').put(glassId             .getBytes()).put((byte)'"').put((byte)',');
		if (eventOccurCode       == null) buf.put((byte)','); else buf.put((byte)'"').put(eventOccurCode      .getBytes()).put((byte)'"').put((byte)',');
		if (eventOccurDetialCode == null) buf.put((byte)','); else buf.put((byte)'"').put(eventOccurDetialCode.getBytes()).put((byte)'"').put((byte)',');
		if (stepId               == null) buf.put((byte)','); else buf.put((byte)'"').put(stepId              .getBytes()).put((byte)'"').put((byte)',');
		if (eqpId                == null) buf.put((byte)','); else buf.put((byte)'"').put(eqpId               .getBytes()).put((byte)'"').put((byte)',');
		if (comments             == null) buf.put((byte)','); else buf.put((byte)'"').put(comments            .getBytes()).put((byte)'"').put((byte)',');
		if (workStatus           == null) buf.put((byte)','); else buf.put((byte)'"').put(workStatus          .getBytes()).put((byte)'"').put((byte)',');
		if (eventOccurId         == null) buf.put((byte)','); else buf.put((byte)'"').put(eventOccurId        .getBytes()).put((byte)'"').put((byte)',');
		if (eventOccurDt         == null) buf.put((byte)','); else buf.put((byte)'"').put(eventOccurDt        .getBytes()).put((byte)'"').put((byte)',');
		if (unitId               == null) buf.put((byte)','); else buf.put((byte)'"').put(unitId              .getBytes()).put((byte)'"').put((byte)',');
		if (layerCode            == null) buf.put((byte)','); else buf.put((byte)'"').put(layerCode           .getBytes()).put((byte)'"').put((byte)',');
		if (workStartDt          == null) buf.put((byte)','); else buf.put((byte)'"').put(workStartDt         .getBytes()).put((byte)'"').put((byte)',');
		if (workEndDt            == null) buf.put((byte)'\n'); else buf.put((byte)'"').put(workEndDt           .getBytes()).put((byte)'"').put((byte)'\n');

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
		buf.put((byte)'"').put("USER_GROUP_CODE"        .getBytes()).put((byte)'"').put((byte)',');
		buf.put((byte)'"').put("PROCESS_ID"             .getBytes()).put((byte)'"').put((byte)',');
		buf.put((byte)'"').put("PRODUCT_ID"             .getBytes()).put((byte)'"').put((byte)',');
		buf.put((byte)'"').put("PRODUCT_TYPE"           .getBytes()).put((byte)'"').put((byte)',');
		buf.put((byte)'"').put("AREA_CODE"              .getBytes()).put((byte)'"').put((byte)',');
		buf.put((byte)'"').put("SUB_AREA_CODE"          .getBytes()).put((byte)'"').put((byte)',');
		buf.put((byte)'"').put("LINE_CODE"              .getBytes()).put((byte)'"').put((byte)',');
		buf.put((byte)'"').put("GLASS_ID"               .getBytes()).put((byte)'"').put((byte)',');
		buf.put((byte)'"').put("EVENT_OCCUR_CODE"       .getBytes()).put((byte)'"').put((byte)',');
		buf.put((byte)'"').put("EVENT_OCCUR_DETIAL_CODE".getBytes()).put((byte)'"').put((byte)',');
		buf.put((byte)'"').put("STEP_ID"                .getBytes()).put((byte)'"').put((byte)',');
		buf.put((byte)'"').put("EQP_ID"                 .getBytes()).put((byte)'"').put((byte)',');
		buf.put((byte)'"').put("COMMENTS"               .getBytes()).put((byte)'"').put((byte)',');
		buf.put((byte)'"').put("WORK_STATUS"            .getBytes()).put((byte)'"').put((byte)',');
		buf.put((byte)'"').put("EVENT_OCCUR_ID"         .getBytes()).put((byte)'"').put((byte)',');
		buf.put((byte)'"').put("EVENT_OCCUR_DT"         .getBytes()).put((byte)'"').put((byte)',');
		buf.put((byte)'"').put("UNIT_ID"                .getBytes()).put((byte)'"').put((byte)',');
		buf.put((byte)'"').put("LAYER_CODE"             .getBytes()).put((byte)'"').put((byte)',');
		buf.put((byte)'"').put("WORK_START_DT"          .getBytes()).put((byte)'"').put((byte)',');
		buf.put((byte)'"').put("WORK_END_DT"            .getBytes()).put((byte)'"').put((byte)'\n');

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

	public String getUserGroupCode()
	{
		return userGroupCode;
	}

	public void setUserGroupCode(String userGroupCode)
	{
		this.userGroupCode = userGroupCode;
	}

	public String getProcessId()
	{
		return processId;
	}

	public void setProcessId(String processId)
	{
		this.processId = processId;
	}

	public String getProductId()
	{
		return productId;
	}

	public void setProductId(String productId)
	{
		this.productId = productId;
	}

	public String getProductType()
	{
		return productType;
	}

	public void setProductType(String productType)
	{
		this.productType = productType;
	}

	public String getAreaCode()
	{
		return areaCode;
	}

	public void setAreaCode(String areaCode)
	{
		this.areaCode = areaCode;
	}

	public String getSubAreaCode()
	{
		return subAreaCode;
	}

	public void setSubAreaCode(String subAreaCode)
	{
		this.subAreaCode = subAreaCode;
	}

	public String getLineCode()
	{
		return lineCode;
	}

	public void setLineCode(String lineCode)
	{
		this.lineCode = lineCode;
	}

	public String getGlassId()
	{
		return glassId;
	}

	public void setGlassId(String glassId)
	{
		this.glassId = glassId;
	}

	public String getEventOccurCode()
	{
		return eventOccurCode;
	}

	public void setEventOccurCode(String eventOccurCode)
	{
		this.eventOccurCode = eventOccurCode;
	}

	public String getEventOccurDetialCode()
	{
		return eventOccurDetialCode;
	}

	public void setEventOccurDetialCode(String eventOccurDetialCode)
	{
		this.eventOccurDetialCode = eventOccurDetialCode;
	}

	public String getStepId()
	{
		return stepId;
	}

	public void setStepId(String stepId)
	{
		this.stepId = stepId;
	}

	public String getEqpId()
	{
		return eqpId;
	}

	public void setEqpId(String eqpId)
	{
		this.eqpId = eqpId;
	}

	public String getComments()
	{
		return comments;
	}

	public void setComments(String comments)
	{
		this.comments = comments;
	}

	public String getWorkStatus()
	{
		return workStatus;
	}

	public void setWorkStatus(String workStatus)
	{
		this.workStatus = workStatus;
	}

	public String getEventOccurId()
	{
		return eventOccurId;
	}

	public void setEventOccurId(String eventOccurId)
	{
		this.eventOccurId = eventOccurId;
	}

	public String getEventOccurDt()
	{
		return eventOccurDt;
	}

	public void setEventOccurDt(String eventOccurDt)
	{
		this.eventOccurDt = eventOccurDt;
	}

	public String getUnitId()
	{
		return unitId;
	}

	public void setUnitId(String unitId)
	{
		this.unitId = unitId;
	}

	public String getLayerCode()
	{
		return layerCode;
	}

	public void setLayerCode(String layerCode)
	{
		this.layerCode = layerCode;
	}

	public String getWorkStartDt()
	{
		return workStartDt;
	}

	public void setWorkStartDt(String workStartDt)
	{
		this.workStartDt = workStartDt;
	}

	public String getWorkEndDt()
	{
		return workEndDt;
	}

	public void setWorkEndDt(String workEndDt)
	{
		this.workEndDt = workEndDt;
	}
}
