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
 * @file_name RP_TOTWIP_SUMM_Bean.java
 *
 */
public class RP_SYS_DEFECT_DETAIL_Bean extends AbstractBean
{
	/*
	"CLUSTER_ID   :ClusterId  ",
	"LINE_CODE    :LineCode   ",
	"AREA_CODE    :AreaCode   ",
	"SUB_AREA_CODE:SubAreaCode",
	"EQPTYPE      :EqpType    ",
	"STEP_ID      :StepId     ",
	"GLASS_ID     :GlassId    ",
	"CELL_ID      :CellId     ",
	"DEFECT_SEQ   :DefectSeq  ",
	"X_VALUE      :XValue     ",
	"Y_VALUE      :YValue     ",
	*/

	private String clusterId  ;
	private String lineCode   ;
	private String areaCode   ;
	private String subAreaCode;
	private String eqpType    ;
	private String stepId     ;
	private String glassId    ;
	private String cellId     ;
	private String defectSeq  ;
	private String XValue     ;
	private String YValue     ;

	public String toString()
	{
		return String.format("READ : [%s] [%s] [%s] [%s] [%s] [%s] [%s] [%s] [%s] [%s] [%s]"
			, clusterId  
			, lineCode   
			, areaCode   
			, subAreaCode
			, eqpType    
			, stepId     
			, glassId    
			, cellId     
			, defectSeq  
			, XValue     
			, YValue     
		);
	}

	public static String[] getHeader()
	{
		List<String> list = new ArrayList<String>();
		list.add("CLUSTER_ID"   );
		list.add("LINE_CODE"    );
		list.add("AREA_CODE"    );
		list.add("SUB_AREA_CODE");
		list.add("EQPTYPE"      );
		list.add("STEP_ID"      );
		list.add("GLASS_ID"     );
		list.add("CELL_ID"      );
		list.add("DEFECT_SEQ"   );
		list.add("X_VALUE"      );
		list.add("Y_VALUE"      );

		return list.toArray(new String[list.size()]);
	}

	public String[] getStringArray()
	{
		List<String> list = new ArrayList<String>();
		list.add(clusterId  );
		list.add(lineCode   );
		list.add(areaCode   );
		list.add(subAreaCode);
		list.add(eqpType    );
		list.add(stepId     );
		list.add(glassId    );
		list.add(cellId     );
		list.add(defectSeq  );
		list.add(XValue     );
		list.add(YValue     );

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

	public String getLineCode()
	{
		return lineCode;
	}

	public void setLineCode(String lineCode)
	{
		this.lineCode = lineCode;
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
