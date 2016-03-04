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

package sdc.anal.lya.macro.A11.METRO_S_H.v00;

import java.util.ArrayList;
import java.util.List;

import kiea.proj.sdc.anal.macro.impl.bean.AbstractBean;

/**
 * @author KangSeok
 * @date 2014. 8. 8.
 * @file_name AIB_METRO_S_H_WriteBean.java
 *
 */
public class METRO_S_H_WriteBean extends AbstractBean
{
	/*
	"LINE_CODE        :LineCode       ",
	"USER_GROUP_CODE  :UserGroupCode  ",
	"PROCESS_ID       :ProcessId      ",
	"PRODUCT_ID       :ProductId      ",
	"EQP_ID           :EqpId          ",
	"PRODUCT_TYPE     :ProductType    ",
	"STEP_ID          :StepId         ",
	"AREA_CODE        :AreaCode       ",
	"SUB_AREA_CODE    :SubAreaCode    ",
	"GLASS_ID         :GlassId        ",
	"CELL_ID          :CellId         ",
	"ITEM_ID          :ItemId         ",
	"SUBITEM_ID       :SubItemI       ",
	"MEASURE_VALUE    :MeasureValue   ",
	"SPEC_UCL         :SpecUcl        ",
	"SPEC_TGT         :SpecTgt        ",
	"SPEC_LCL         :SpecLcl        ",
	"METRO_END_DT     :MetroEndDt     ",
	"DEFECT_GROUP_CODE:DefectGroupCode",
	"DECISION_CODE    :DecisionCode   ",
	*/

	private String lineCode       ;
	private String userGroupCode  ;
	private String processId      ;
	private String productId      ;
	private String eqpId          ;
	private String productType    ;
	private String stepId         ;
	private String areaCode       ;
	private String subAreaCode    ;
	private String glassId        ;
	private String cellId         ;
	private String itemId         ;
	private String subItemI       ;
	private String measureValue   ;
	private String specUcl        ;
	private String specTgt        ;
	private String specLcl        ;
	private String metroEndDt     ;
	private String defectGroupCode;
	private String decisionCode   ;

	public String toString()
	{
		return String.format("READ : [%s] [%s] [%s] [%s] [%s] [%s] [%s] [%s] [%s] [%s] [%s] [%s] [%s] [%s] [%s] [%s] [%s] [%s] [%s] [%s]"
			, lineCode       
			, userGroupCode  
			, processId      
			, productId      
			, eqpId          
			, productType    
			, stepId         
			, areaCode       
			, subAreaCode    
			, glassId        
			, cellId         
			, itemId         
			, subItemI       
			, measureValue   
			, specUcl        
			, specTgt        
			, specLcl        
			, metroEndDt     
			, defectGroupCode
			, decisionCode   
		);
	}

	public static String[] getHeader()
	{
		List<String> list = new ArrayList<String>();
		list.add("LINE_CODE"        );
		list.add("USER_GROUP_CODE"  );
		list.add("PROCESS_ID"       );
		list.add("PRODUCT_ID"       );
		list.add("EQP_ID"           );
		list.add("PRODUCT_TYPE"     );
		list.add("STEP_ID"          );
		list.add("AREA_CODE"        );
		list.add("SUB_AREA_CODE"    );
		list.add("GLASS_ID"         );
		list.add("CELL_ID"          );
		list.add("ITEM_ID"          );
		list.add("SUBITEM_ID"       );
		list.add("MEASURE_VALUE"    );
		list.add("SPEC_UCL"         );
		list.add("SPEC_TGT"         );
		list.add("SPEC_LCL"         );
		list.add("METRO_END_DT"     );
		list.add("DEFECT_GROUP_CODE");
		list.add("DECISION_CODE"    );

		return list.toArray(new String[list.size()]);
	}

	public String[] getStringArray()
	{
		List<String> list = new ArrayList<String>();
		list.add(lineCode       );
		list.add(userGroupCode  );
		list.add(processId      );
		list.add(productId      );
		list.add(eqpId          );
		list.add(productType    );
		list.add(stepId         );
		list.add(areaCode       );
		list.add(subAreaCode    );
		list.add(glassId        );
		list.add(cellId         );
		list.add(itemId         );
		list.add(subItemI       );
		list.add(measureValue   );
		list.add(specUcl        );
		list.add(specTgt        );
		list.add(specLcl        );
		list.add(metroEndDt     );
		list.add(defectGroupCode);
		list.add(decisionCode   );

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
	 * @return the eqpId
	 */
	public String getEqpId()
	{
		return eqpId;
	}

	/**
	 * @return the productType
	 */
	public String getProductType()
	{
		return productType;
	}

	/**
	 * @return the stepId
	 */
	public String getStepId()
	{
		return stepId;
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
	 * @return the itemId
	 */
	public String getItemId()
	{
		return itemId;
	}

	/**
	 * @return the subItemI
	 */
	public String getSubItemI()
	{
		return subItemI;
	}

	/**
	 * @return the measureValue
	 */
	public String getMeasureValue()
	{
		return measureValue;
	}

	/**
	 * @return the specUcl
	 */
	public String getSpecUcl()
	{
		return specUcl;
	}

	/**
	 * @return the specTgt
	 */
	public String getSpecTgt()
	{
		return specTgt;
	}

	/**
	 * @return the specLcl
	 */
	public String getSpecLcl()
	{
		return specLcl;
	}

	/**
	 * @return the metroEndDt
	 */
	public String getMetroEndDt()
	{
		return metroEndDt;
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
	 * @param eqpId the eqpId to set
	 */
	public void setEqpId(String eqpId)
	{
		this.eqpId = eqpId;
	}

	/**
	 * @param productType the productType to set
	 */
	public void setProductType(String productType)
	{
		this.productType = productType;
	}

	/**
	 * @param stepId the stepId to set
	 */
	public void setStepId(String stepId)
	{
		this.stepId = stepId;
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
	 * @param itemId the itemId to set
	 */
	public void setItemId(String itemId)
	{
		this.itemId = itemId;
	}

	/**
	 * @param subItemI the subItemI to set
	 */
	public void setSubItemI(String subItemI)
	{
		this.subItemI = subItemI;
	}

	/**
	 * @param measureValue the measureValue to set
	 */
	public void setMeasureValue(String measureValue)
	{
		this.measureValue = measureValue;
	}

	/**
	 * @param specUcl the specUcl to set
	 */
	public void setSpecUcl(String specUcl)
	{
		this.specUcl = specUcl;
	}

	/**
	 * @param specTgt the specTgt to set
	 */
	public void setSpecTgt(String specTgt)
	{
		this.specTgt = specTgt;
	}

	/**
	 * @param specLcl the specLcl to set
	 */
	public void setSpecLcl(String specLcl)
	{
		this.specLcl = specLcl;
	}

	/**
	 * @param metroEndDt the metroEndDt to set
	 */
	public void setMetroEndDt(String metroEndDt)
	{
		this.metroEndDt = metroEndDt;
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
}
