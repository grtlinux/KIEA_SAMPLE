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

package kiea.proj.sdc.anal.macro.sample.a12.preanal.macro.SO_SYS_CELLID_LIST2.v01;

import java.util.ArrayList;
import java.util.List;

import kiea.proj.sdc.anal.macro.impl.bean.AbstractBean;

/**
 * @author KangSeok
 * @date 2014. 8. 8.
 * @file_name AIB_DEFECT_HIST_Bean.java
 *
 */
public class AIB_DEFECT_HIST_Bean extends AbstractBean
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

	public String toString()
	{
		return String.format("READ : [%s] [%s] [%s] [%s] [%s] [%s] [%s] [%s] [%s] [%s] [%s] [%s] [%s] [%s] [%s] [%s] [%s] [%s] [%s] [%s]"
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
	 * @return the glassId
	 */
	public String getGlassId()
	{
		return glassId;
	}

	/**
	 * @return the cellId
	 */
	public String getCellId()
	{
		return cellId;
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
	 * @return the defectSeq
	 */
	public String getDefectSeq()
	{
		return defectSeq;
	}

	/**
	 * @return the inspectDt
	 */
	public String getInspectDt()
	{
		return inspectDt;
	}

	/**
	 * @return the xValue
	 */
	public String getXValue()
	{
		return XValue;
	}

	/**
	 * @return the yValue
	 */
	public String getYValue()
	{
		return YValue;
	}

	/**
	 * @return the defectGroupCode
	 */
	public String getDefectGroupCode()
	{
		return defectGroupCode;
	}

	/**
	 * @return the decisionCode
	 */
	public String getDecisionCode()
	{
		return decisionCode;
	}

	/**
	 * @return the inspectorId
	 */
	public String getInspectorId()
	{
		return inspectorId;
	}

	/**
	 * @return the cellLocId
	 */
	public String getCellLocId()
	{
		return cellLocId;
	}

	/**
	 * @return the imagepath
	 */
	public String getImagepath()
	{
		return imagepath;
	}

	/**
	 * @param lineCode the lineCode to set
	 */
	public void setLineCode(String lineCode)
	{
		this.lineCode = lineCode;
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
	 * @param glassId the glassId to set
	 */
	public void setGlassId(String glassId)
	{
		this.glassId = glassId;
	}

	/**
	 * @param cellId the cellId to set
	 */
	public void setCellId(String cellId)
	{
		this.cellId = cellId;
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
	 * @param defectSeq the defectSeq to set
	 */
	public void setDefectSeq(String defectSeq)
	{
		this.defectSeq = defectSeq;
	}

	/**
	 * @param inspectDt the inspectDt to set
	 */
	public void setInspectDt(String inspectDt)
	{
		this.inspectDt = inspectDt;
	}

	/**
	 * @param xValue the xValue to set
	 */
	public void setXValue(String xValue)
	{
		XValue = xValue;
	}

	/**
	 * @param yValue the yValue to set
	 */
	public void setYValue(String yValue)
	{
		YValue = yValue;
	}

	/**
	 * @param defectGroupCode the defectGroupCode to set
	 */
	public void setDefectGroupCode(String defectGroupCode)
	{
		this.defectGroupCode = defectGroupCode;
	}

	/**
	 * @param decisionCode the decisionCode to set
	 */
	public void setDecisionCode(String decisionCode)
	{
		this.decisionCode = decisionCode;
	}

	/**
	 * @param inspectorId the inspectorId to set
	 */
	public void setInspectorId(String inspectorId)
	{
		this.inspectorId = inspectorId;
	}

	/**
	 * @param cellLocId the cellLocId to set
	 */
	public void setCellLocId(String cellLocId)
	{
		this.cellLocId = cellLocId;
	}

	/**
	 * @param imagepath the imagepath to set
	 */
	public void setImagepath(String imagepath)
	{
		this.imagepath = imagepath;
	}
}
