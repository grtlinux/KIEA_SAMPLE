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

package sdc.anal.lya.macro.A11.WIP_EQP_SMMRY.v04;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;

import kiea.proj.sdc.anal.macro.impl.bean.AbstractBean;

/**
 * @author KangSeok
 * @date 2014. 8. 8.
 * @file_name AIB_WIP_EQP_SMMRY_WriteBean.java
 *
 */
public class WIP_EQP_SMMRY_WriteBean extends AbstractBean
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

	///////////////////////////////////////////////////////////////////////////

	private byte[] bytes = null;
	private String title = null;
	private String str   = null;

	public void setBytes(byte[] bytes)
	{
		ByteBuffer buf = ByteBuffer.allocateDirect(10 * 1024);

		buf.clear();
		if (lineCode             == null) buf.put((byte)','); else buf.put((byte)'"').put(lineCode            .getBytes()).put((byte)'"').put((byte)',');
		if (userGroupCode        == null) buf.put((byte)','); else buf.put((byte)'"').put(userGroupCode       .getBytes()).put((byte)'"').put((byte)',');
		if (processId            == null) buf.put((byte)','); else buf.put((byte)'"').put(processId           .getBytes()).put((byte)'"').put((byte)',');
		if (productId            == null) buf.put((byte)','); else buf.put((byte)'"').put(productId           .getBytes()).put((byte)'"').put((byte)',');
		if (productType          == null) buf.put((byte)','); else buf.put((byte)'"').put(productType         .getBytes()).put((byte)'"').put((byte)',');
		if (areaCode             == null) buf.put((byte)','); else buf.put((byte)'"').put(areaCode            .getBytes()).put((byte)'"').put((byte)',');
		if (subAreaCode          == null) buf.put((byte)','); else buf.put((byte)'"').put(subAreaCode         .getBytes()).put((byte)'"').put((byte)',');
		if (stepId               == null) buf.put((byte)','); else buf.put((byte)'"').put(stepId              .getBytes()).put((byte)'"').put((byte)',');
		if (eqpId                == null) buf.put((byte)','); else buf.put((byte)'"').put(eqpId               .getBytes()).put((byte)'"').put((byte)',');
		if (glassId              == null) buf.put((byte)','); else buf.put((byte)'"').put(glassId             .getBytes()).put((byte)'"').put((byte)',');
		if (cellId               == null) buf.put((byte)','); else buf.put((byte)'"').put(cellId              .getBytes()).put((byte)'"').put((byte)',');
		if (glassDefectCodeRatio == null) buf.put((byte)','); else buf.put((byte)'"').put(glassDefectCodeRatio.getBytes()).put((byte)'"').put((byte)',');
		if (baseCellNum          == null) buf.put((byte)','); else buf.put((byte)'"').put(baseCellNum         .getBytes()).put((byte)'"').put((byte)',');
		if (defectCellNum        == null) buf.put((byte)','); else buf.put((byte)'"').put(defectCellNum       .getBytes()).put((byte)'"').put((byte)',');
		if (trckInTime           == null) buf.put((byte)','); else buf.put((byte)'"').put(trckInTime          .getBytes()).put((byte)'"').put((byte)',');
		if (trckOutTime          == null) buf.put((byte)','); else buf.put((byte)'"').put(trckOutTime         .getBytes()).put((byte)'"').put((byte)',');
		if (matchLotType         == null) buf.put((byte)','); else buf.put((byte)'"').put(matchLotType        .getBytes()).put((byte)'"').put((byte)',');
		if (binGradeCode         == null) buf.put((byte)','); else buf.put((byte)'"').put(binGradeCode        .getBytes()).put((byte)'"').put((byte)',');
		if (defectGroupCode      == null) buf.put((byte)','); else buf.put((byte)'"').put(defectGroupCode     .getBytes()).put((byte)'"').put((byte)',');
		if (decisionCode         == null) buf.put((byte)'\n'); else buf.put((byte)'"').put(decisionCode        .getBytes()).put((byte)'"').put((byte)'\n');

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
		buf.put((byte)'"').put("LINE_CODE"              .getBytes()).put((byte)'"').put((byte)',');
		buf.put((byte)'"').put("USER_GROUP_CODE"        .getBytes()).put((byte)'"').put((byte)',');
		buf.put((byte)'"').put("PROCESS_ID"             .getBytes()).put((byte)'"').put((byte)',');
		buf.put((byte)'"').put("PRODUCT_ID"             .getBytes()).put((byte)'"').put((byte)',');
		buf.put((byte)'"').put("PRODUCT_TYPE"           .getBytes()).put((byte)'"').put((byte)',');
		buf.put((byte)'"').put("AREA_CODE"              .getBytes()).put((byte)'"').put((byte)',');
		buf.put((byte)'"').put("SUB_AREA_CODE"          .getBytes()).put((byte)'"').put((byte)',');
		buf.put((byte)'"').put("STEP_ID"                .getBytes()).put((byte)'"').put((byte)',');
		buf.put((byte)'"').put("EQP_ID"                 .getBytes()).put((byte)'"').put((byte)',');
		buf.put((byte)'"').put("GLASS_ID"               .getBytes()).put((byte)'"').put((byte)',');
		buf.put((byte)'"').put("CELL_ID"                .getBytes()).put((byte)'"').put((byte)',');
		buf.put((byte)'"').put("GLASS_DEFECT_CODE_RATIO".getBytes()).put((byte)'"').put((byte)',');
		buf.put((byte)'"').put("BASE_CELL_NUM"          .getBytes()).put((byte)'"').put((byte)',');
		buf.put((byte)'"').put("DEFECT_CELL_NUM"        .getBytes()).put((byte)'"').put((byte)',');
		buf.put((byte)'"').put("TRCK_IN_TIME"           .getBytes()).put((byte)'"').put((byte)',');
		buf.put((byte)'"').put("TRCK_OUT_TIME"          .getBytes()).put((byte)'"').put((byte)',');
		buf.put((byte)'"').put("MATCH_LOT_TYPE"         .getBytes()).put((byte)'"').put((byte)',');
		buf.put((byte)'"').put("BIN_GRADE_CODE"         .getBytes()).put((byte)'"').put((byte)',');
		buf.put((byte)'"').put("DEFECT_GROUP_CODE"      .getBytes()).put((byte)'"').put((byte)',');
		buf.put((byte)'"').put("DECISION_CODE"          .getBytes()).put((byte)'"').put((byte)'\n');

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

	public String getGlassDefectCodeRatio()
	{
		return glassDefectCodeRatio;
	}

	public void setGlassDefectCodeRatio(String glassDefectCodeRatio)
	{
		this.glassDefectCodeRatio = glassDefectCodeRatio;
	}

	public String getBaseCellNum()
	{
		return baseCellNum;
	}

	public void setBaseCellNum(String baseCellNum)
	{
		this.baseCellNum = baseCellNum;
	}

	public String getDefectCellNum()
	{
		return defectCellNum;
	}

	public void setDefectCellNum(String defectCellNum)
	{
		this.defectCellNum = defectCellNum;
	}

	public String getTrckInTime()
	{
		return trckInTime;
	}

	public void setTrckInTime(String trckInTime)
	{
		this.trckInTime = trckInTime;
	}

	public String getTrckOutTime()
	{
		return trckOutTime;
	}

	public void setTrckOutTime(String trckOutTime)
	{
		this.trckOutTime = trckOutTime;
	}

	public String getMatchLotType()
	{
		return matchLotType;
	}

	public void setMatchLotType(String matchLotType)
	{
		this.matchLotType = matchLotType;
	}

	public String getBinGradeCode()
	{
		return binGradeCode;
	}

	public void setBinGradeCode(String binGradeCode)
	{
		this.binGradeCode = binGradeCode;
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
}
