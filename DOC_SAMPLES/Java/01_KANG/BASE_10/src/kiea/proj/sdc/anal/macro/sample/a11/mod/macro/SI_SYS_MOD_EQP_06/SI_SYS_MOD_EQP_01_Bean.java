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

package kiea.proj.sdc.anal.macro.sample.a11.mod.macro.SI_SYS_MOD_EQP_06;

import java.util.ArrayList;
import java.util.List;

import kiea.proj.sdc.anal.macro.impl.bean.AbstractBean;

/**
 * @author KangSeok
 * @date 2014. 8. 8.
 * @file_name SI_SYS_MOD_EQP_01_Bean.java
 *
 */
public class SI_SYS_MOD_EQP_01_Bean extends AbstractBean
{
	/*
	"LINE_CODE        :LineCode       ",
	"USER_GROUP_CODE  :UserGroupCode  ",
	"PROCESS_ID       :ProcessId      ",
	"PRODUCT_ID       :ProductId      ",
	"PRODUCT_TYPE     :ProductType    ",
	"AREA_CODE        :AreaAode       ",
	"SUB_AREA_CODE    :SubAreaCode    ",
	"STEP_ID          :StepId         ",
	"EQP_ID           :EqpId          ",
	"GLASS_ID         :GlassId        ",
	"CELL_ID          :CellId         ",
	"CELL_LOC_ID      :CellLocId      ",
	"DEFECT_GROUP_CODE:DefectGroupCode",
	"DECISION_CODE    :DecisionCode   ",
	"INSPECTOR_ID     :InspectorId    ",
	"INSPECT_DT       :InspectDt      ",
	"INSPECT_HOUR     :InspectHour    ",
	"INSPECT_HOUR2    :InspectHour2   ",
	*/

	private String lineCode       ;
	private String userGroupCode  ;
	private String processId      ;
	private String productId      ;
	private String productType    ;
	private String areaAode       ;
	private String subAreaCode    ;
	private String stepId         ;
	private String eqpId          ;
	private String glassId        ;
	private String cellId         ;
	private String cellLocId      ;
	private String defectGroupCode;
	private String decisionCode   ;
	private String inspectorId    ;
	private String inspectDt      ;
	private String inspectHour    ;
	private String inspectHour2   ;

	public String toString()
	{
		return String.format("READ : [%s] [%s] [%s] [%s] [%s] [%s] [%s] [%s] [%s] [%s] [%s] [%s] [%s] [%s] [%s] [%s] [%s] [%s]"
			, lineCode       
			, userGroupCode  
			, processId      
			, productId      
			, productType    
			, areaAode       
			, subAreaCode    
			, stepId         
			, eqpId          
			, glassId        
			, cellId         
			, cellLocId      
			, defectGroupCode
			, decisionCode   
			, inspectorId    
			, inspectDt      
			, inspectHour    
			, inspectHour2   
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
		list.add("STEP_ID"          );
		list.add("EQP_ID"           );
		list.add("GLASS_ID"         );
		list.add("CELL_ID"          );
		list.add("CELL_LOC_ID"      );
		list.add("DEFECT_GROUP_CODE");
		list.add("DECISION_CODE"    );
		list.add("INSPECTOR_ID"     );
		list.add("INSPECT_DT"       );
		list.add("INSPECT_HOUR"     );
		list.add("INSPECT_HOUR2"    );

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
		list.add(areaAode       );
		list.add(subAreaCode    );
		list.add(stepId         );
		list.add(eqpId          );
		list.add(glassId        );
		list.add(cellId         );
		list.add(cellLocId      );
		list.add(defectGroupCode);
		list.add(decisionCode   );
		list.add(inspectorId    );
		list.add(inspectDt      );
		list.add(inspectHour    );
		list.add(inspectHour2   );

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
	 * @return the areaAode
	 */
	public String getAreaAode()
	{
		return areaAode;
	}

	/**
	 * @return the subAreaCode
	 */
	public String getSubAreaCode()
	{
		return subAreaCode;
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
	 * @return the cellLocId
	 */
	public String getCellLocId()
	{
		return cellLocId;
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
	 * @return the inspectDt
	 */
	public String getInspectDt()
	{
		return inspectDt;
	}

	/**
	 * @return the inspectHour
	 */
	public String getInspectHour()
	{
		return inspectHour;
	}

	/**
	 * @return the inspectHour2
	 */
	public String getInspectHour2()
	{
		return inspectHour2;
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
	 * @param areaAode the areaAode to set
	 */
	public void setAreaAode(String areaAode)
	{
		this.areaAode = areaAode;
	}

	/**
	 * @param subAreaCode the subAreaCode to set
	 */
	public void setSubAreaCode(String subAreaCode)
	{
		this.subAreaCode = subAreaCode;
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
	 * @param cellLocId the cellLocId to set
	 */
	public void setCellLocId(String cellLocId)
	{
		this.cellLocId = cellLocId;
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
	 * @param inspectDt the inspectDt to set
	 */
	public void setInspectDt(String inspectDt)
	{
		this.inspectDt = inspectDt;
	}

	/**
	 * @param inspectHour the inspectHour to set
	 */
	public void setInspectHour(String inspectHour)
	{
		this.inspectHour = inspectHour;
	}

	/**
	 * @param inspectHour2 the inspectHour2 to set
	 */
	public void setInspectHour2(String inspectHour2)
	{
		this.inspectHour2 = inspectHour2;
	}
}
