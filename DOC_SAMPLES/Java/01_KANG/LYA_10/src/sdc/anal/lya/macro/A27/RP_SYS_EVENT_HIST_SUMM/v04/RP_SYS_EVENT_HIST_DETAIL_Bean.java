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

package sdc.anal.lya.macro.A27.RP_SYS_EVENT_HIST_SUMM.v04;

import java.util.ArrayList;
import java.util.List;

import kiea.proj.sdc.anal.macro.impl.bean.AbstractBean;

/**
 * @author KangSeok
 * @date 2014. 8. 8.
 * @file_name SO_SYS_CELLID_LIST2_Bean.java
 *
 */
public class RP_SYS_EVENT_HIST_DETAIL_Bean extends AbstractBean
{
	/*
	"CLUSTER_ID      :ClusterId     ",
	"EVENT_OCCUR_CODE:EventOccurCode",
	"STEP_ID         :StepId        ",
	"STEP_DESC       :StepDesc      ",
	"AREA_CODE       :AreaCode      ",
	"GLASS_ID        :GlassId       ",
	"EVENT_OCCUR_DT  :EventOccurDt  ",
	"EQP_ID          :EqpId         ",
	"UNIT_ID         :UnitId        ",
	"LAYER_CODE      :LayerCode     ",
	*/

	private String clusterId     ;
	private String eventOccurCode;
	private String stepId        ;
	private String stepDesc      ;
	private String areaCode      ;
	private String glassId       ;
	private String eventOccurDt  ;
	private String eqpId         ;
	private String unitId        ;
	private String layerCode     ;

	public String toString()
	{
		return String.format("READ : [%s] [%s] [%s] [%s] [%s] [%s] [%s] [%s] [%s] [%s]"
			, clusterId     
			, eventOccurCode
			, stepId        
			, stepDesc      
			, areaCode      
			, glassId       
			, eventOccurDt  
			, eqpId         
			, unitId        
			, layerCode     
		);
	}

	public static String[] getHeader()
	{
		List<String> list = new ArrayList<String>();
		list.add("CLUSTER_ID"      );
		list.add("EVENT_OCCUR_CODE");
		list.add("STEP_ID"         );
		list.add("STEP_DESC"       );
		list.add("AREA_CODE"       );
		list.add("GLASS_ID"        );
		list.add("EVENT_OCCUR_DT"  );
		list.add("EQP_ID"          );
		list.add("UNIT_ID"         );
		list.add("LAYER_CODE"      );

		return list.toArray(new String[list.size()]);
	}

	public String[] getStringArray()
	{
		List<String> list = new ArrayList<String>();
		list.add(clusterId     );
		list.add(eventOccurCode);
		list.add(stepId        );
		list.add(stepDesc      );
		list.add(areaCode      );
		list.add(glassId       );
		list.add(eventOccurDt  );
		list.add(eqpId         );
		list.add(unitId        );
		list.add(layerCode     );

		return list.toArray(new String[list.size()]);
	}

	public String getClusterId()
	{
		return clusterId;
	}

	public void setClusterId(String clusterId)
	{
		this.clusterId = clusterId;
	}

	public String getEventOccurCode()
	{
		return eventOccurCode;
	}

	public void setEventOccurCode(String eventOccurCode)
	{
		this.eventOccurCode = eventOccurCode;
	}

	public String getStepId()
	{
		return stepId;
	}

	public void setStepId(String stepId)
	{
		this.stepId = stepId;
	}

	public String getStepDesc()
	{
		return stepDesc;
	}

	public void setStepDesc(String stepDesc)
	{
		this.stepDesc = stepDesc;
	}

	public String getAreaCode()
	{
		return areaCode;
	}

	public void setAreaCode(String areaCode)
	{
		this.areaCode = areaCode;
	}

	public String getGlassId()
	{
		return glassId;
	}

	public void setGlassId(String glassId)
	{
		this.glassId = glassId;
	}

	public String getEventOccurDt()
	{
		return eventOccurDt;
	}

	public void setEventOccurDt(String eventOccurDt)
	{
		this.eventOccurDt = eventOccurDt;
	}

	public String getEqpId()
	{
		return eqpId;
	}

	public void setEqpId(String eqpId)
	{
		this.eqpId = eqpId;
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
}
