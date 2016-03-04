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

package sdc.anal.lya.macro.A25.RP_SYS_DEFECT_DETAIL.v01;

import java.util.ArrayList;
import java.util.List;

import kiea.proj.sdc.anal.macro.impl.bean.AbstractBean;

/**
 * @author KangSeok
 * @date 2014. 8. 8.
 * @file_name AIB_WIP_EQP_SMMRY_Bean.java
 *
 */
public class TFT_INSPCT_HIST_Bean extends AbstractBean
{
	/*
	"LINE_CODE         :LineCode       ",
	"USER_GROUP_CODE   :UserGroup_code ",
	"PRODUCT_ID        :ProductId      ",
	"PROCESS_ID        :ProcessId      ",
	"PRODUCT_TYPE      :ProductType    ",
	"AREA_CODE         :AreaCode       ",
	"SUB_AREA_CODE     :SubAreaCode    ",
	"INSP_STEP_GRP_CODE:InspStepGrpCode",
	"INSP_STEP_GRP_NAME:InspStepGrpName",
	"EQPTYPE           :EqpType        ",
	"STEP_ID           :StepId         ",
	"EQP_ID            :EqpId          ",
	"GLASS_ID          :GlassId        ",
	"CELL_ID           :CellId         ",
	"INSPECT_DT        :InspectDt      ",
	"CELL_LOC_ID       :CellLocId      ",
	"DECISION_CODE     :DecisionCode   ",
	"DEFECT_GROUP_CODE :DefectGroupCode",
	"INSPECTOR_ID      :InspectorId    ",
	"DEFECTSYSID       :DefectSysId    ",
	"DEFECT_SEQ        :DefectSeq      ",
	"X_VALUE           :XValue         ",
	"Y_VALUE           :YValue         ",
	"CLUSTER_ID        :ClusterId      ",
	*/

	private String lineCode       ;
	private String userGroup_code ;
	private String productId      ;
	private String processId      ;
	private String productType    ;
	private String areaCode       ;
	private String subAreaCode    ;
	private String inspStepGrpCode;
	private String inspStepGrpName;
	private String eqpType        ;
	private String stepId         ;
	private String eqpId          ;
	private String glassId        ;
	private String cellId         ;
	private String inspectDt      ;
	private String cellLocId      ;
	private String decisionCode   ;
	private String defectGroupCode;
	private String inspectorId    ;
	private String defectSysId    ;
	private String defectSeq      ;
	private String XValue         ;
	private String YValue         ;
	private String clusterId      ;

	public String toString()
	{
		return String.format("READ : [%s] [%s] [%s] [%s] [%s] [%s] [%s] [%s] [%s] [%s] [%s] [%s] [%s] [%s] [%s] [%s] [%s] [%s] [%s] [%s] [%s] [%s] [%s] [%s]"
			, lineCode       
			, userGroup_code 
			, productId      
			, processId      
			, productType    
			, areaCode       
			, subAreaCode    
			, inspStepGrpCode
			, inspStepGrpName
			, eqpType        
			, stepId         
			, eqpId          
			, glassId        
			, cellId         
			, inspectDt      
			, cellLocId      
			, decisionCode   
			, defectGroupCode
			, inspectorId    
			, defectSysId    
			, defectSeq      
			, XValue         
			, YValue         
			, clusterId      
		);
	}

	public static String[] getHeader()
	{
		List<String> list = new ArrayList<String>();
		list.add("LINE_CODE"         );
		list.add("USER_GROUP_CODE"   );
		list.add("PRODUCT_ID"        );
		list.add("PROCESS_ID"        );
		list.add("PRODUCT_TYPE"      );
		list.add("AREA_CODE"         );
		list.add("SUB_AREA_CODE"     );
		list.add("INSP_STEP_GRP_CODE");
		list.add("INSP_STEP_GRP_NAME");
		list.add("EQPTYPE"           );
		list.add("STEP_ID"           );
		list.add("EQP_ID"            );
		list.add("GLASS_ID"          );
		list.add("CELL_ID"           );
		list.add("INSPECT_DT"        );
		list.add("CELL_LOC_ID"       );
		list.add("DECISION_CODE"     );
		list.add("DEFECT_GROUP_CODE" );
		list.add("INSPECTOR_ID"      );
		list.add("DEFECTSYSID"       );
		list.add("DEFECT_SEQ"        );
		list.add("X_VALUE"           );
		list.add("Y_VALUE"           );
		list.add("CLUSTER_ID"        );

		return list.toArray(new String[list.size()]);
	}

	public String[] getStringArray()
	{
		List<String> list = new ArrayList<String>();
		list.add(lineCode       );
		list.add(userGroup_code );
		list.add(productId      );
		list.add(processId      );
		list.add(productType    );
		list.add(areaCode       );
		list.add(subAreaCode    );
		list.add(inspStepGrpCode);
		list.add(inspStepGrpName);
		list.add(eqpType        );
		list.add(stepId         );
		list.add(eqpId          );
		list.add(glassId        );
		list.add(cellId         );
		list.add(inspectDt      );
		list.add(cellLocId      );
		list.add(decisionCode   );
		list.add(defectGroupCode);
		list.add(inspectorId    );
		list.add(defectSysId    );
		list.add(defectSeq      );
		list.add(XValue         );
		list.add(YValue         );
		list.add(clusterId      );

		return list.toArray(new String[list.size()]);
	}

	public String getLineCode()
	{
		return lineCode;
	}

	public void setLineCode(String lineCode)
	{
		this.lineCode = lineCode;
	}

	public String getUserGroup_code()
	{
		return userGroup_code;
	}

	public void setUserGroup_code(String userGroup_code)
	{
		this.userGroup_code = userGroup_code;
	}

	public String getProductId()
	{
		return productId;
	}

	public void setProductId(String productId)
	{
		this.productId = productId;
	}

	public String getProcessId()
	{
		return processId;
	}

	public void setProcessId(String processId)
	{
		this.processId = processId;
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

	public String getInspStepGrpCode()
	{
		return inspStepGrpCode;
	}

	public void setInspStepGrpCode(String inspStepGrpCode)
	{
		this.inspStepGrpCode = inspStepGrpCode;
	}

	public String getInspStepGrpName()
	{
		return inspStepGrpName;
	}

	public void setInspStepGrpName(String inspStepGrpName)
	{
		this.inspStepGrpName = inspStepGrpName;
	}

	public String getEqpType()
	{
		return eqpType;
	}

	public void setEqpType(String eqpType)
	{
		this.eqpType = eqpType;
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

	public String getGlassId()
	{
		return glassId;
	}

	public void setGlassId(String glassId)
	{
		this.glassId = glassId;
	}

	public String getCellId()
	{
		return cellId;
	}

	public void setCellId(String cellId)
	{
		this.cellId = cellId;
	}

	public String getInspectDt()
	{
		return inspectDt;
	}

	public void setInspectDt(String inspectDt)
	{
		this.inspectDt = inspectDt;
	}

	public String getCellLocId()
	{
		return cellLocId;
	}

	public void setCellLocId(String cellLocId)
	{
		this.cellLocId = cellLocId;
	}

	public String getDecisionCode()
	{
		return decisionCode;
	}

	public void setDecisionCode(String decisionCode)
	{
		this.decisionCode = decisionCode;
	}

	public String getDefectGroupCode()
	{
		return defectGroupCode;
	}

	public void setDefectGroupCode(String defectGroupCode)
	{
		this.defectGroupCode = defectGroupCode;
	}

	public String getInspectorId()
	{
		return inspectorId;
	}

	public void setInspectorId(String inspectorId)
	{
		this.inspectorId = inspectorId;
	}

	public String getDefectSysId()
	{
		return defectSysId;
	}

	public void setDefectSysId(String defectSysId)
	{
		this.defectSysId = defectSysId;
	}

	public String getDefectSeq()
	{
		return defectSeq;
	}

	public void setDefectSeq(String defectSeq)
	{
		this.defectSeq = defectSeq;
	}

	public String getXValue()
	{
		return XValue;
	}

	public void setXValue(String xValue)
	{
		XValue = xValue;
	}

	public String getYValue()
	{
		return YValue;
	}

	public void setYValue(String yValue)
	{
		YValue = yValue;
	}

	public String getClusterId()
	{
		return clusterId;
	}

	public void setClusterId(String clusterId)
	{
		this.clusterId = clusterId;
	}
}
