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

package sdc.anal.lya.macro.A28.RP_MAP_CHART_DETAILS.v04;

import java.util.ArrayList;
import java.util.List;

import kiea.proj.sdc.anal.macro.impl.bean.AbstractBean;

/**
 * @author KangSeok
 * @date 2014. 8. 8.
 * @file_name AIB_DEFECT_HIST_Bean.java
 *
 */
public class DEFECT_HIST_Bean extends AbstractBean
{
	/*
	"LINE_CODE        :LineCode       ",
	"USER_GROUP_CODE  :UserGroupCode  ",
	"PROCESS_ID       :ProcessId      ",
	"PRODUCT_ID       :ProductId      ",
	"PRODUCT_TYPE     :ProductType    ",
	"AREA_CODE        :AreaCode       ",
	"SUB_AREA_CODE    :SubAreaCode    ",
	"GLASS_ID         :GlassId        ",
	"CELL_ID          :CellId         ",
	"STEP_ID          :StepId         ",
	"EQP_ID           :EqpId          ",
	"DEFECT_SEQ       :DefectSeq      ",
	"INSPECT_DT       :InspectDt      ",
	"X_VALUE          :XValue         ",
	"Y_VALUE          :YValue         ",
	"DEFECT_GROUP_CODE:DefectGroupCode",
	"DECISION_CODE    :DecisionCode   ",
	"INSPECTOR_ID     :InspectorId    ",
	"CELL_LOC_ID      :CellLocId      ",
	"IMAGEPATH        :Imagepath      ",
	"CLUSTER_ID       :ClusterId      ",
	"GROUP            :Group          ",
	*/

	private String lineCode       ;
	private String userGroupCode  ;
	private String processId      ;
	private String productId      ;
	private String productType    ;
	private String areaCode       ;
	private String subAreaCode    ;
	private String glassId        ;
	private String cellId         ;
	private String stepId         ;
	private String eqpId          ;
	private String defectSeq      ;
	private String inspectDt      ;
	private String XValue         ;
	private String YValue         ;
	private String defectGroupCode;
	private String decisionCode   ;
	private String inspectorId    ;
	private String cellLocId      ;
	private String imagepath      ;
	private String clusterId      ;
	private String group          ;

	public String toString()
	{
		return String.format("READ : [%s] [%s] [%s] [%s] [%s] [%s] [%s] [%s] [%s] [%s] [%s] [%s] [%s] [%s] [%s] [%s] [%s] [%s] [%s] [%s] [%s] [%s]"
			, lineCode       
			, userGroupCode  
			, processId      
			, productId      
			, productType    
			, areaCode       
			, subAreaCode    
			, glassId        
			, cellId         
			, stepId         
			, eqpId          
			, defectSeq      
			, inspectDt      
			, XValue         
			, YValue         
			, defectGroupCode
			, decisionCode   
			, inspectorId    
			, cellLocId      
			, imagepath      
			, clusterId      
			, group          
		);
	}

	public static String[] getHeader()
	{
		List<String> list = new ArrayList<String>();
		list.add("LINE_CODE"        );
		list.add("USER_GROUP_CODE"  );
		list.add("PROCESS_ID"       );
		list.add("PRODUCT_ID"       );
		list.add("PRODUCT_TYPE"     );
		list.add("AREA_CODE"        );
		list.add("SUB_AREA_CODE"    );
		list.add("GLASS_ID"         );
		list.add("CELL_ID"          );
		list.add("STEP_ID"          );
		list.add("EQP_ID"           );
		list.add("DEFECT_SEQ"       );
		list.add("INSPECT_DT"       );
		list.add("X_VALUE"          );
		list.add("Y_VALUE"          );
		list.add("DEFECT_GROUP_CODE");
		list.add("DECISION_CODE"    );
		list.add("INSPECTOR_ID"     );
		list.add("CELL_LOC_ID"      );
		list.add("IMAGEPATH"        );
		list.add("CLUSTER_ID"       );
		list.add("GROUP"            );

		return list.toArray(new String[list.size()]);
	}

	public String[] getStringArray()
	{
		List<String> list = new ArrayList<String>();
		list.add(lineCode       );
		list.add(userGroupCode  );
		list.add(processId      );
		list.add(productId      );
		list.add(productType    );
		list.add(areaCode       );
		list.add(subAreaCode    );
		list.add(glassId        );
		list.add(cellId         );
		list.add(stepId         );
		list.add(eqpId          );
		list.add(defectSeq      );
		list.add(inspectDt      );
		list.add(XValue         );
		list.add(YValue         );
		list.add(defectGroupCode);
		list.add(decisionCode   );
		list.add(inspectorId    );
		list.add(cellLocId      );
		list.add(imagepath      );
		list.add(clusterId      );
		list.add(group          );

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

	public String getDefectSeq()
	{
		return defectSeq;
	}

	public void setDefectSeq(String defectSeq)
	{
		this.defectSeq = defectSeq;
	}

	public String getInspectDt()
	{
		return inspectDt;
	}

	public void setInspectDt(String inspectDt)
	{
		this.inspectDt = inspectDt;
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

	public String getDefectGroupCode()
	{
		return defectGroupCode;
	}

	public void setDefectGroupCode(String defectGroupCode)
	{
		this.defectGroupCode = defectGroupCode;
	}

	public String getDecisionCode()
	{
		return decisionCode;
	}

	public void setDecisionCode(String decisionCode)
	{
		this.decisionCode = decisionCode;
	}

	public String getInspectorId()
	{
		return inspectorId;
	}

	public void setInspectorId(String inspectorId)
	{
		this.inspectorId = inspectorId;
	}

	public String getCellLocId()
	{
		return cellLocId;
	}

	public void setCellLocId(String cellLocId)
	{
		this.cellLocId = cellLocId;
	}

	public String getImagepath()
	{
		return imagepath;
	}

	public void setImagepath(String imagepath)
	{
		this.imagepath = imagepath;
	}

	public String getClusterId()
	{
		return clusterId;
	}

	public void setClusterId(String clusterId)
	{
		this.clusterId = clusterId;
	}

	public String getGroup()
	{
		return group;
	}

	public void setGroup(String group)
	{
		this.group = group;
	}
}
