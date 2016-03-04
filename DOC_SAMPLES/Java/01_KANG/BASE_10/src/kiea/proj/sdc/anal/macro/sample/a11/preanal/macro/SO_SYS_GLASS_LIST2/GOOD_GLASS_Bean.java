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

package kiea.proj.sdc.anal.macro.sample.a11.preanal.macro.SO_SYS_GLASS_LIST2;

import java.util.ArrayList;
import java.util.List;

import kiea.proj.sdc.anal.macro.impl.bean.AbstractBean;

/**
 * @author KangSeok
 * @date 2014. 8. 8.
 * @file_name GOOD_GLASS_Bean.java
 *
 */
public class GOOD_GLASS_Bean extends AbstractBean
{
	private String cellId       ;
	private String glassId      ;
	private String siteId       ;
	private String prodGrpName  ;
	private String procId       ;
	private String itemId       ;
	private String subItemId    ;
	private String defectName   ;
	private String itemName     ;
	private String measEqpUnitId;
	private String muraData     ;
	private String XValue       ;
	private String YValue       ;
	private String x2Value      ;
	private String y2Value      ;
	private String avgX         ;
	private String avgY         ;
	private String gateLine_1   ;
	private String gateLine_2   ;
	private String dataLine_1   ;
	private String dataLine_2   ;
	private String target       ;
	private String cellPosition ;

	public String toString()
	{
		return String.format("READ : [%s] [%s] [%s] [%s] [%s] [%s] [%s] [%s] [%s] [%s] [%s] [%s] [%s] [%s] [%s] [%s] [%s] [%s] [%s] [%s] [%s] [%s] [%s]"
			, cellId       
			, glassId      
			, siteId       
			, prodGrpName  
			, procId       
			, itemId       
			, subItemId    
			, defectName   
			, itemName     
			, measEqpUnitId
			, muraData     
			, XValue       
			, YValue       
			, x2Value      
			, y2Value      
			, avgX         
			, avgY         
			, gateLine_1   
			, gateLine_2   
			, dataLine_1   
			, dataLine_2   
			, target       
			, cellPosition 
		);
	}

	public static String[] getHeader()
	{
		List<String> list = new ArrayList<String>();
		list.add("CELLID"       );
		list.add("GLASSID"      );
		list.add("SITEID"       );
		list.add("PRODGRPNAME"  );
		list.add("PROCID"       );
		list.add("ITEMID"       );
		list.add("SUBITEMID"    );
		list.add("DEFECTNAME"   );
		list.add("ITEMNAME"     );
		list.add("MEASEQPUNITID");
		list.add("MURA_DATA"    );
		list.add("X_VALUE"      );
		list.add("Y_VALUE"      );
		list.add("X2_VALUE"     );
		list.add("Y2_VALUE"     );
		list.add("AVG_X"        );
		list.add("AVG_Y"        );
		list.add("GATELINE_1"   );
		list.add("GATELINE_2"   );
		list.add("DATALINE_1"   );
		list.add("DATALINE_2"   );
		list.add("TARGET"       );
		list.add("CELL_POSITION");

		return list.toArray(new String[list.size()]);
	}

	public String[] getStringArray()
	{
		List<String> list = new ArrayList<String>();
		list.add(cellId       );
		list.add(glassId      );
		list.add(siteId       );
		list.add(prodGrpName  );
		list.add(procId       );
		list.add(itemId       );
		list.add(subItemId    );
		list.add(defectName   );
		list.add(itemName     );
		list.add(measEqpUnitId);
		list.add(muraData     );
		list.add(XValue       );
		list.add(YValue       );
		list.add(x2Value      );
		list.add(y2Value      );
		list.add(avgX         );
		list.add(avgY         );
		list.add(gateLine_1   );
		list.add(gateLine_2   );
		list.add(dataLine_1   );
		list.add(dataLine_2   );
		list.add(target       );
		list.add(cellPosition );

		return list.toArray(new String[list.size()]);
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
	 * @return the siteId
	 */
	public String getSiteId()
	{
		return siteId;
	}

	/**
	 * @return the prodGrpName
	 */
	public String getProdGrpName()
	{
		return prodGrpName;
	}

	/**
	 * @return the procId
	 */
	public String getProcId()
	{
		return procId;
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
	 * @return the itemName
	 */
	public String getItemName()
	{
		return itemName;
	}

	/**
	 * @return the measEqpUnitId
	 */
	public String getMeasEqpUnitId()
	{
		return measEqpUnitId;
	}

	/**
	 * @return the muraData
	 */
	public String getMuraData()
	{
		return muraData;
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
	 * @return the avgX
	 */
	public String getAvgX()
	{
		return avgX;
	}

	/**
	 * @return the avgY
	 */
	public String getAvgY()
	{
		return avgY;
	}

	/**
	 * @return the gateLine_1
	 */
	public String getGateLine_1()
	{
		return gateLine_1;
	}

	/**
	 * @return the gateLine_2
	 */
	public String getGateLine_2()
	{
		return gateLine_2;
	}

	/**
	 * @return the dataLine_1
	 */
	public String getDataLine_1()
	{
		return dataLine_1;
	}

	/**
	 * @return the dataLine_2
	 */
	public String getDataLine_2()
	{
		return dataLine_2;
	}

	/**
	 * @return the target
	 */
	public String getTarget()
	{
		return target;
	}

	/**
	 * @return the cellPosition
	 */
	public String getCellPosition()
	{
		return cellPosition;
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
	 * @param siteId the siteId to set
	 */
	public void setSiteId(String siteId)
	{
		this.siteId = siteId;
	}

	/**
	 * @param prodGrpName the prodGrpName to set
	 */
	public void setProdGrpName(String prodGrpName)
	{
		this.prodGrpName = prodGrpName;
	}

	/**
	 * @param procId the procId to set
	 */
	public void setProcId(String procId)
	{
		this.procId = procId;
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
	 * @param itemName the itemName to set
	 */
	public void setItemName(String itemName)
	{
		this.itemName = itemName;
	}

	/**
	 * @param measEqpUnitId the measEqpUnitId to set
	 */
	public void setMeasEqpUnitId(String measEqpUnitId)
	{
		this.measEqpUnitId = measEqpUnitId;
	}

	/**
	 * @param muraData the muraData to set
	 */
	public void setMuraData(String muraData)
	{
		this.muraData = muraData;
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

	/**
	 * @param avgX the avgX to set
	 */
	public void setAvgX(String avgX)
	{
		this.avgX = avgX;
	}

	/**
	 * @param avgY the avgY to set
	 */
	public void setAvgY(String avgY)
	{
		this.avgY = avgY;
	}

	/**
	 * @param gateLine_1 the gateLine_1 to set
	 */
	public void setGateLine_1(String gateLine_1)
	{
		this.gateLine_1 = gateLine_1;
	}

	/**
	 * @param gateLine_2 the gateLine_2 to set
	 */
	public void setGateLine_2(String gateLine_2)
	{
		this.gateLine_2 = gateLine_2;
	}

	/**
	 * @param dataLine_1 the dataLine_1 to set
	 */
	public void setDataLine_1(String dataLine_1)
	{
		this.dataLine_1 = dataLine_1;
	}

	/**
	 * @param dataLine_2 the dataLine_2 to set
	 */
	public void setDataLine_2(String dataLine_2)
	{
		this.dataLine_2 = dataLine_2;
	}

	/**
	 * @param target the target to set
	 */
	public void setTarget(String target)
	{
		this.target = target;
	}

	/**
	 * @param cellPosition the cellPosition to set
	 */
	public void setCellPosition(String cellPosition)
	{
		this.cellPosition = cellPosition;
	}
}
