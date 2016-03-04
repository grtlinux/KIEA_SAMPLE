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
 * @file_name SO_SYS_CELLID_LIST2_Bean.java
 *
 */
public class RP_MAP_CHART_DETAILS_Bean extends AbstractBean
{
	/*
	"DIV_CODE         :DivCode        ",
	"GROUP_NUM        :GroupNum       ",
	"LOT_ID           :LotId          ",
	"GLASS_ID         :GlassId        ",
	"CELL_ID          :CellId         ",
	"DEFECT_SEQ       :DefectSeq      ",
	"DEFECT_GROUP_CODE:DefectGroupCode",
	"DECISION_CODE    :DecisionCode   ",
	"INSPECT_DT       :InspectDt      ",
	"IMAGE_PATH       :ImagePath      ",
	"X_VALUE          :XValue         ",
	"Y_VALUE          :YValue         ",
	*/

	private String divCode        ;
	private String groupNum       ;
	private String lotId          ;
	private String glassId        ;
	private String cellId         ;
	private String defectSeq      ;
	private String defectGroupCode;
	private String decisionCode   ;
	private String inspectDt      ;
	private String imagePath      ;
	private String XValue         ;
	private String YValue         ;

	public String toString()
	{
		return String.format("READ : [%s] [%s] [%s] [%s] [%s] [%s] [%s] [%s] [%s] [%s] [%s] [%s]"
			, divCode        
			, groupNum       
			, lotId          
			, glassId        
			, cellId         
			, defectSeq      
			, defectGroupCode
			, decisionCode   
			, inspectDt      
			, imagePath      
			, XValue         
			, YValue         
		);
	}

	public static String[] getHeader()
	{
		List<String> list = new ArrayList<String>();
		list.add("DIV_CODE"         );
		list.add("GROUP_NUM"        );
		list.add("LOT_ID"           );
		list.add("GLASS_ID"         );
		list.add("CELL_ID"          );
		list.add("DEFECT_SEQ"       );
		list.add("DEFECT_GROUP_CODE");
		list.add("DECISION_CODE"    );
		list.add("INSPECT_DT"       );
		list.add("IMAGE_PATH"       );
		list.add("X_VALUE"          );
		list.add("Y_VALUE"          );

		return list.toArray(new String[list.size()]);
	}

	public String[] getStringArray()
	{
		List<String> list = new ArrayList<String>();
		list.add(divCode        );
		list.add(groupNum       );
		list.add(lotId          );
		list.add(glassId        );
		list.add(cellId         );
		list.add(defectSeq      );
		list.add(defectGroupCode);
		list.add(decisionCode   );
		list.add(inspectDt      );
		list.add(imagePath      );
		list.add(XValue         );
		list.add(YValue         );

		return list.toArray(new String[list.size()]);
	}

	public String getDivCode()
	{
		return divCode;
	}

	public void setDivCode(String divCode)
	{
		this.divCode = divCode;
	}

	public String getGroupNum()
	{
		return groupNum;
	}

	public void setGroupNum(String groupNum)
	{
		this.groupNum = groupNum;
	}

	public String getLotId()
	{
		return lotId;
	}

	public void setLotId(String lotId)
	{
		this.lotId = lotId;
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

	public String getInspectDt()
	{
		return inspectDt;
	}

	public void setInspectDt(String inspectDt)
	{
		this.inspectDt = inspectDt;
	}

	public String getImagePath()
	{
		return imagePath;
	}

	public void setImagePath(String imagePath)
	{
		this.imagePath = imagePath;
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
