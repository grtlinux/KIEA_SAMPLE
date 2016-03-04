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

package kiea.proj.sdc.anal.macro.sample.a12.wip.macro.RP_TOTWIP_SUMM.v01;

import java.util.ArrayList;
import java.util.List;

import kiea.proj.sdc.anal.macro.impl.bean.AbstractBean;

/**
 * @author KangSeok
 * @date 2014. 8. 8.
 * @file_name AIB_WIP_EQP_SMMRY_Bean.java
 *
 */
public class AIB_WIP_EQP_SMMRY_Bean extends AbstractBean
{
	/*
	"LINE_CODE              :LineCode            ",
	"USER_GROUP_CODE        :UserGroupCode       ",
	"PROCESS_ID             :ProcessId           ",
	"PRODUCT_ID             :ProductId           ",
	"PRODUCT_TYPE           :ProductType         ",
	"AREA_CODE              :AreaCode            ",
	"SUB_AREA_CODE          :SubAreaCode         ",
	"STEP_ID                :StepId              ",
	"EQP_ID                 :EqpId               ",
	"GLASS_ID               :GlassId             ",
	"CELL_ID                :CellId              ",
	"GLASS_DEFECT_CODE_RATIO:GlassDefectCodeRatio",
	"BASE_CELL_NUM          :BaseCellNum         ",
	"DEFECT_CELL_NUM        :DefectCellNum       ",
	"TRCK_IN_TIME           :TrckInTime          ",
	"TRCK_OUT_TIME          :TrckOutTime         ",
	"MATCH_LOT_TYPE         :MatchLotType        ",
	"BIN_GRADE_CODE         :BinGradeCode        ",
	"DEFECT_GROUP_CODE      :DefectGroupCode     ",
	"DECISION_CODE          :DecisionCode        ",
	*/

	private String lineCode            ;
	private String userGroupCode       ;
	private String processId           ;
	private String productId           ;
	private String productType         ;
	private String areaCode            ;
	private String subAreaCode         ;
	private String stepId              ;
	private String eqpId               ;
	private String glassId             ;
	private String cellId              ;
	private String glassDefectCodeRatio;
	private String baseCellNum         ;
	private String defectCellNum       ;
	private String trckInTime          ;
	private String trckOutTime         ;
	private String matchLotType        ;
	private String binGradeCode        ;
	private String defectGroupCode     ;
	private String decisionCode        ;

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
			, stepId              
			, eqpId               
			, glassId             
			, cellId              
			, glassDefectCodeRatio
			, baseCellNum         
			, defectCellNum       
			, trckInTime          
			, trckOutTime         
			, matchLotType        
			, binGradeCode        
			, defectGroupCode     
			, decisionCode        
		);
	}

	public static String[] getHeader()
	{
		List<String> list = new ArrayList<String>();
		list.add("LINE_CODE"              );
		list.add("USER_GROUP_CODE"        );
		list.add("PROCESS_ID"             );
		list.add("PRODUCT_ID"             );
		list.add("PRODUCT_TYPE"           );
		list.add("AREA_CODE"              );
		list.add("SUB_AREA_CODE"          );
		list.add("STEP_ID"                );
		list.add("EQP_ID"                 );
		list.add("GLASS_ID"               );
		list.add("CELL_ID"                );
		list.add("GLASS_DEFECT_CODE_RATIO");
		list.add("BASE_CELL_NUM"          );
		list.add("DEFECT_CELL_NUM"        );
		list.add("TRCK_IN_TIME"           );
		list.add("TRCK_OUT_TIME"          );
		list.add("MATCH_LOT_TYPE"         );
		list.add("BIN_GRADE_CODE"         );
		list.add("DEFECT_GROUP_CODE"      );
		list.add("DECISION_CODE"          );

		return list.toArray(new String[list.size()]);
	}

	public String[] getStringArray()
	{
		List<String> list = new ArrayList<String>();
		list.add(lineCode            );
		list.add(userGroupCode       );
		list.add(processId           );
		list.add(productId           );
		list.add(productType         );
		list.add(areaCode            );
		list.add(subAreaCode         );
		list.add(stepId              );
		list.add(eqpId               );
		list.add(glassId             );
		list.add(cellId              );
		list.add(glassDefectCodeRatio);
		list.add(baseCellNum         );
		list.add(defectCellNum       );
		list.add(trckInTime          );
		list.add(trckOutTime         );
		list.add(matchLotType        );
		list.add(binGradeCode        );
		list.add(defectGroupCode     );
		list.add(decisionCode        );

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
	 * @return the glassDefectCodeRatio
	 */
	public String getGlassDefectCodeRatio()
	{
		return glassDefectCodeRatio;
	}

	/**
	 * @return the baseCellNum
	 */
	public String getBaseCellNum()
	{
		return baseCellNum;
	}

	/**
	 * @return the defectCellNum
	 */
	public String getDefectCellNum()
	{
		return defectCellNum;
	}

	/**
	 * @return the trckInTime
	 */
	public String getTrckInTime()
	{
		return trckInTime;
	}

	/**
	 * @return the trckOutTime
	 */
	public String getTrckOutTime()
	{
		return trckOutTime;
	}

	/**
	 * @return the matchLotType
	 */
	public String getMatchLotType()
	{
		return matchLotType;
	}

	/**
	 * @return the binGradeCode
	 */
	public String getBinGradeCode()
	{
		return binGradeCode;
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
	 * @param glassDefectCodeRatio the glassDefectCodeRatio to set
	 */
	public void setGlassDefectCodeRatio(String glassDefectCodeRatio)
	{
		this.glassDefectCodeRatio = glassDefectCodeRatio;
	}

	/**
	 * @param baseCellNum the baseCellNum to set
	 */
	public void setBaseCellNum(String baseCellNum)
	{
		this.baseCellNum = baseCellNum;
	}

	/**
	 * @param defectCellNum the defectCellNum to set
	 */
	public void setDefectCellNum(String defectCellNum)
	{
		this.defectCellNum = defectCellNum;
	}

	/**
	 * @param trckInTime the trckInTime to set
	 */
	public void setTrckInTime(String trckInTime)
	{
		this.trckInTime = trckInTime;
	}

	/**
	 * @param trckOutTime the trckOutTime to set
	 */
	public void setTrckOutTime(String trckOutTime)
	{
		this.trckOutTime = trckOutTime;
	}

	/**
	 * @param matchLotType the matchLotType to set
	 */
	public void setMatchLotType(String matchLotType)
	{
		this.matchLotType = matchLotType;
	}

	/**
	 * @param binGradeCode the binGradeCode to set
	 */
	public void setBinGradeCode(String binGradeCode)
	{
		this.binGradeCode = binGradeCode;
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
