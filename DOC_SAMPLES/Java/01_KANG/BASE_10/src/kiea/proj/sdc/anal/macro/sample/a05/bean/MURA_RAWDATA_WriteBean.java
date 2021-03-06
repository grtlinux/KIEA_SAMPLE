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

package kiea.proj.sdc.anal.macro.sample.a05.bean;

import java.util.ArrayList;
import java.util.List;

import kiea.proj.sdc.anal.macro.impl.bean.AbstractBean;

/**
 * @author KangSeok
 * @date 2014. 8. 8.
 * @file_name MURA_RAWDATA_WriteBean.java
 *
 */
public class MURA_RAWDATA_WriteBean extends AbstractBean
{
	private String procId       ;
	private String siteId       ;
	private String cellId       ;
	private String glassId      ;
	private String prodGrpName  ;
	private String prodId       ;
	private String orgStepId    ;
	private String measEqpUnitId;
	private String dcolTime     ;
	private String itemId       ;
	private String subItemId    ;
	private String defectName   ;
	private String dataValue    ;
	private String gateLine     ;
	private String dataLine     ;
	private String gateLine2    ;
	private String dataLine2    ;
	private String prodType     ;
	private String cellLocId    ;
	private String width        ;
	private String height       ;
	private String XValue       ;
	private String YValue       ;
	private String x2Value      ;
	private String y2Value      ;

	public String toString()
	{
		return String.format("READ : [%s] [%s] [%s] [%s] [%s] [%s] [%s] [%s] [%s] [%s] [%s] [%s] [%s] [%s] [%s] [%s] [%s] [%s] [%s] [%s] [%s] [%s] [%s] [%s] [%s]"
			, procId       
			, siteId       
			, cellId       
			, glassId      
			, prodGrpName  
			, prodId       
			, orgStepId    
			, measEqpUnitId
			, dcolTime     
			, itemId       
			, subItemId    
			, defectName   
			, dataValue    
			, gateLine     
			, dataLine     
			, gateLine2    
			, dataLine2    
			, prodType     
			, cellLocId    
			, width        
			, height       
			, XValue       
			, YValue       
			, x2Value      
			, y2Value      
		);
	}

	public static String[] getHeader()
	{
		List<String> list = new ArrayList<String>();
		list.add("PROCID"       );
		list.add("SITEID"       );
		list.add("CELLID"       );
		list.add("GLASSID"      );
		list.add("PRODGRPNAME"  );
		list.add("PRODID"       );
		list.add("ORGSTEPID"    );
		list.add("MEASEQPUNITID");
		list.add("DCOLTIME"     );
		list.add("ITEMID"       );
		list.add("SUBITEMID"    );
		list.add("DEFECTNAME"   );
		list.add("DATAVALUE"    );
		list.add("GATELINE"     );
		list.add("DATALINE"     );
		list.add("GATELINE2"    );
		list.add("DATALINE2"    );
		list.add("PRODTYPE"     );
		list.add("CELL_LOC_ID"  );
		list.add("WIDTH"        );
		list.add("HEIGHT"       );
		list.add("X_VALUE"      );
		list.add("Y_VALUE"      );
		list.add("X2_VALUE"     );
		list.add("Y2_VALUE"     );

		return list.toArray(new String[list.size()]);
	}

	public String[] getStringArray()
	{
		List<String> list = new ArrayList<String>();
		list.add(procId       );
		list.add(siteId       );
		list.add(cellId       );
		list.add(glassId      );
		list.add(prodGrpName  );
		list.add(prodId       );
		list.add(orgStepId    );
		list.add(measEqpUnitId);
		list.add(dcolTime     );
		list.add(itemId       );
		list.add(subItemId    );
		list.add(defectName   );
		list.add(dataValue    );
		list.add(gateLine     );
		list.add(dataLine     );
		list.add(gateLine2    );
		list.add(dataLine2    );
		list.add(prodType     );
		list.add(cellLocId    );
		list.add(width        );
		list.add(height       );
		list.add(XValue       );
		list.add(YValue       );
		list.add(x2Value      );
		list.add(y2Value      );

		return list.toArray(new String[list.size()]);
	}

	/**
	 * @return the procId
	 */
	public String getProcId()
	{
		return procId;
	}

	/**
	 * @return the siteId
	 */
	public String getSiteId()
	{
		return siteId;
	}

	/**
	 * @return the cellId
	 */
	public String getCellId()
	{
		return cellId;
	}

	/**
	 * @return the glassId
	 */
	public String getGlassId()
	{
		return glassId;
	}

	/**
	 * @return the prodGrpName
	 */
	public String getProdGrpName()
	{
		return prodGrpName;
	}

	/**
	 * @return the prodId
	 */
	public String getProdId()
	{
		return prodId;
	}

	/**
	 * @return the orgStepId
	 */
	public String getOrgStepId()
	{
		return orgStepId;
	}

	/**
	 * @return the measEqpUnitId
	 */
	public String getMeasEqpUnitId()
	{
		return measEqpUnitId;
	}

	/**
	 * @return the dcolTime
	 */
	public String getDcolTime()
	{
		return dcolTime;
	}

	/**
	 * @return the itemId
	 */
	public String getItemId()
	{
		return itemId;
	}

	/**
	 * @return the subItemId
	 */
	public String getSubItemId()
	{
		return subItemId;
	}

	/**
	 * @return the defectName
	 */
	public String getDefectName()
	{
		return defectName;
	}

	/**
	 * @return the dataValue
	 */
	public String getDataValue()
	{
		return dataValue;
	}

	/**
	 * @return the gateLine
	 */
	public String getGateLine()
	{
		return gateLine;
	}

	/**
	 * @return the dataLine
	 */
	public String getDataLine()
	{
		return dataLine;
	}

	/**
	 * @return the gateLine2
	 */
	public String getGateLine2()
	{
		return gateLine2;
	}

	/**
	 * @return the dataLine2
	 */
	public String getDataLine2()
	{
		return dataLine2;
	}

	/**
	 * @return the prodType
	 */
	public String getProdType()
	{
		return prodType;
	}

	/**
	 * @return the cellLocId
	 */
	public String getCellLocId()
	{
		return cellLocId;
	}

	/**
	 * @return the width
	 */
	public String getWidth()
	{
		return width;
	}

	/**
	 * @return the height
	 */
	public String getHeight()
	{
		return height;
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
	 * @return the x2Value
	 */
	public String getX2Value()
	{
		return x2Value;
	}

	/**
	 * @return the y2Value
	 */
	public String getY2Value()
	{
		return y2Value;
	}

	/**
	 * @param procId the procId to set
	 */
	public void setProcId(String procId)
	{
		this.procId = procId;
	}

	/**
	 * @param siteId the siteId to set
	 */
	public void setSiteId(String siteId)
	{
		this.siteId = siteId;
	}

	/**
	 * @param cellId the cellId to set
	 */
	public void setCellId(String cellId)
	{
		this.cellId = cellId;
	}

	/**
	 * @param glassId the glassId to set
	 */
	public void setGlassId(String glassId)
	{
		this.glassId = glassId;
	}

	/**
	 * @param prodGrpName the prodGrpName to set
	 */
	public void setProdGrpName(String prodGrpName)
	{
		this.prodGrpName = prodGrpName;
	}

	/**
	 * @param prodId the prodId to set
	 */
	public void setProdId(String prodId)
	{
		this.prodId = prodId;
	}

	/**
	 * @param orgStepId the orgStepId to set
	 */
	public void setOrgStepId(String orgStepId)
	{
		this.orgStepId = orgStepId;
	}

	/**
	 * @param measEqpUnitId the measEqpUnitId to set
	 */
	public void setMeasEqpUnitId(String measEqpUnitId)
	{
		this.measEqpUnitId = measEqpUnitId;
	}

	/**
	 * @param dcolTime the dcolTime to set
	 */
	public void setDcolTime(String dcolTime)
	{
		this.dcolTime = dcolTime;
	}

	/**
	 * @param itemId the itemId to set
	 */
	public void setItemId(String itemId)
	{
		this.itemId = itemId;
	}

	/**
	 * @param subItemId the subItemId to set
	 */
	public void setSubItemId(String subItemId)
	{
		this.subItemId = subItemId;
	}

	/**
	 * @param defectName the defectName to set
	 */
	public void setDefectName(String defectName)
	{
		this.defectName = defectName;
	}

	/**
	 * @param dataValue the dataValue to set
	 */
	public void setDataValue(String dataValue)
	{
		this.dataValue = dataValue;
	}

	/**
	 * @param gateLine the gateLine to set
	 */
	public void setGateLine(String gateLine)
	{
		this.gateLine = gateLine;
	}

	/**
	 * @param dataLine the dataLine to set
	 */
	public void setDataLine(String dataLine)
	{
		this.dataLine = dataLine;
	}

	/**
	 * @param gateLine2 the gateLine2 to set
	 */
	public void setGateLine2(String gateLine2)
	{
		this.gateLine2 = gateLine2;
	}

	/**
	 * @param dataLine2 the dataLine2 to set
	 */
	public void setDataLine2(String dataLine2)
	{
		this.dataLine2 = dataLine2;
	}

	/**
	 * @param prodType the prodType to set
	 */
	public void setProdType(String prodType)
	{
		this.prodType = prodType;
	}

	/**
	 * @param cellLocId the cellLocId to set
	 */
	public void setCellLocId(String cellLocId)
	{
		this.cellLocId = cellLocId;
	}

	/**
	 * @param width the width to set
	 */
	public void setWidth(String width)
	{
		this.width = width;
	}

	/**
	 * @param height the height to set
	 */
	public void setHeight(String height)
	{
		this.height = height;
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
	 * @param x2Value the x2Value to set
	 */
	public void setX2Value(String x2Value)
	{
		this.x2Value = x2Value;
	}

	/**
	 * @param y2Value the y2Value to set
	 */
	public void setY2Value(String y2Value)
	{
		this.y2Value = y2Value;
	}
}
