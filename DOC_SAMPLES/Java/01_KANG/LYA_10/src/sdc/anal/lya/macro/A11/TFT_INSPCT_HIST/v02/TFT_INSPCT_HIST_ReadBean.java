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

package sdc.anal.lya.macro.A11.TFT_INSPCT_HIST.v02;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;

import kiea.proj.sdc.anal.macro.impl.bean.AbstractBean;

/**
 * @author KangSeok
 * @date 2014. 8. 8.
 * @file_name AIB_TFT_INSPCT_HIST_ReadBean.java
 *
 */
public class TFT_INSPCT_HIST_ReadBean extends AbstractBean
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

	///////////////////////////////////////////////////////////////////////////

	private byte[] bytes = null;
	private String title = null;
	private String str   = null;

	public void setBytes(byte[] bytes)
	{
		ByteBuffer buf = ByteBuffer.allocateDirect(10 * 1024);

		buf.clear();
		if (lineCode        == null) buf.put((byte)','); else buf.put((byte)'"').put(lineCode       .getBytes()).put((byte)'"').put((byte)',');
		if (userGroup_code  == null) buf.put((byte)','); else buf.put((byte)'"').put(userGroup_code .getBytes()).put((byte)'"').put((byte)',');
		if (productId       == null) buf.put((byte)','); else buf.put((byte)'"').put(productId      .getBytes()).put((byte)'"').put((byte)',');
		if (processId       == null) buf.put((byte)','); else buf.put((byte)'"').put(processId      .getBytes()).put((byte)'"').put((byte)',');
		if (productType     == null) buf.put((byte)','); else buf.put((byte)'"').put(productType    .getBytes()).put((byte)'"').put((byte)',');
		if (areaCode        == null) buf.put((byte)','); else buf.put((byte)'"').put(areaCode       .getBytes()).put((byte)'"').put((byte)',');
		if (subAreaCode     == null) buf.put((byte)','); else buf.put((byte)'"').put(subAreaCode    .getBytes()).put((byte)'"').put((byte)',');
		if (inspStepGrpCode == null) buf.put((byte)','); else buf.put((byte)'"').put(inspStepGrpCode.getBytes()).put((byte)'"').put((byte)',');
		if (inspStepGrpName == null) buf.put((byte)','); else buf.put((byte)'"').put(inspStepGrpName.getBytes()).put((byte)'"').put((byte)',');
		if (eqpType         == null) buf.put((byte)','); else buf.put((byte)'"').put(eqpType        .getBytes()).put((byte)'"').put((byte)',');
		if (stepId          == null) buf.put((byte)','); else buf.put((byte)'"').put(stepId         .getBytes()).put((byte)'"').put((byte)',');
		if (eqpId           == null) buf.put((byte)','); else buf.put((byte)'"').put(eqpId          .getBytes()).put((byte)'"').put((byte)',');
		if (glassId         == null) buf.put((byte)','); else buf.put((byte)'"').put(glassId        .getBytes()).put((byte)'"').put((byte)',');
		if (cellId          == null) buf.put((byte)','); else buf.put((byte)'"').put(cellId         .getBytes()).put((byte)'"').put((byte)',');
		if (inspectDt       == null) buf.put((byte)','); else buf.put((byte)'"').put(inspectDt      .getBytes()).put((byte)'"').put((byte)',');
		if (cellLocId       == null) buf.put((byte)','); else buf.put((byte)'"').put(cellLocId      .getBytes()).put((byte)'"').put((byte)',');
		if (decisionCode    == null) buf.put((byte)','); else buf.put((byte)'"').put(decisionCode   .getBytes()).put((byte)'"').put((byte)',');
		if (defectGroupCode == null) buf.put((byte)','); else buf.put((byte)'"').put(defectGroupCode.getBytes()).put((byte)'"').put((byte)',');
		if (inspectorId     == null) buf.put((byte)','); else buf.put((byte)'"').put(inspectorId    .getBytes()).put((byte)'"').put((byte)',');
		if (defectSysId     == null) buf.put((byte)','); else buf.put((byte)'"').put(defectSysId    .getBytes()).put((byte)'"').put((byte)',');
		if (defectSeq       == null) buf.put((byte)','); else buf.put((byte)'"').put(defectSeq      .getBytes()).put((byte)'"').put((byte)',');
		if (XValue          == null) buf.put((byte)','); else buf.put((byte)'"').put(XValue         .getBytes()).put((byte)'"').put((byte)',');
		if (YValue          == null) buf.put((byte)','); else buf.put((byte)'"').put(YValue         .getBytes()).put((byte)'"').put((byte)'\n');

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
		buf.put((byte)'"').put("LINE_CODE"         .getBytes()).put((byte)'"').put((byte)',');
		buf.put((byte)'"').put("USER_GROUP_CODE"   .getBytes()).put((byte)'"').put((byte)',');
		buf.put((byte)'"').put("PRODUCT_ID"        .getBytes()).put((byte)'"').put((byte)',');
		buf.put((byte)'"').put("PROCESS_ID"        .getBytes()).put((byte)'"').put((byte)',');
		buf.put((byte)'"').put("PRODUCT_TYPE"      .getBytes()).put((byte)'"').put((byte)',');
		buf.put((byte)'"').put("AREA_CODE"         .getBytes()).put((byte)'"').put((byte)',');
		buf.put((byte)'"').put("SUB_AREA_CODE"     .getBytes()).put((byte)'"').put((byte)',');
		buf.put((byte)'"').put("INSP_STEP_GRP_CODE".getBytes()).put((byte)'"').put((byte)',');
		buf.put((byte)'"').put("INSP_STEP_GRP_NAME".getBytes()).put((byte)'"').put((byte)',');
		buf.put((byte)'"').put("EQPTYPE"           .getBytes()).put((byte)'"').put((byte)',');
		buf.put((byte)'"').put("STEP_ID"           .getBytes()).put((byte)'"').put((byte)',');
		buf.put((byte)'"').put("EQP_ID"            .getBytes()).put((byte)'"').put((byte)',');
		buf.put((byte)'"').put("GLASS_ID"          .getBytes()).put((byte)'"').put((byte)',');
		buf.put((byte)'"').put("CELL_ID"           .getBytes()).put((byte)'"').put((byte)',');
		buf.put((byte)'"').put("INSPECT_DT"        .getBytes()).put((byte)'"').put((byte)',');
		buf.put((byte)'"').put("CELL_LOC_ID"       .getBytes()).put((byte)'"').put((byte)',');
		buf.put((byte)'"').put("DECISION_CODE"     .getBytes()).put((byte)'"').put((byte)',');
		buf.put((byte)'"').put("DEFECT_GROUP_CODE" .getBytes()).put((byte)'"').put((byte)',');
		buf.put((byte)'"').put("INSPECTOR_ID"      .getBytes()).put((byte)'"').put((byte)',');
		buf.put((byte)'"').put("DEFECTSYSID"       .getBytes()).put((byte)'"').put((byte)',');
		buf.put((byte)'"').put("DEFECT_SEQ"        .getBytes()).put((byte)'"').put((byte)',');
		buf.put((byte)'"').put("X_VALUE"           .getBytes()).put((byte)'"').put((byte)',');
		buf.put((byte)'"').put("Y_VALUE"           .getBytes()).put((byte)'"').put((byte)'\n');

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
		return String.format("READ : [%s] [%s] [%s] [%s] [%s] [%s] [%s] [%s] [%s] [%s] [%s] [%s] [%s] [%s] [%s] [%s] [%s] [%s] [%s] [%s] [%s] [%s] [%s]"
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
}
