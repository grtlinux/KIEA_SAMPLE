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

package sdc.anal.mura.macro.A21.SO_SYS_GLASS_LIST2.v04.D20141229;

import java.util.ArrayList;
import java.util.List;

import kiea.proj.sdc.anal.macro.impl.bean.AbstractBean;

/**
 * @author KangSeok
 * @date 2014. 8. 8.
 * @file_name CLUSTER_OUTPUT_Bean.java
 *
 */
public class CLUSTER_OUTPUT_Bean extends AbstractBean
{
	/*
	"DATA_NUM  :DataNum  ",
	"GLASS_ID  :GlassId  ",
	"CELL_ID   :CellId   ",
	"X_VALUE   :XValue   ",
	"Y_VALUE   :YValue   ",
	"X2_VALUE  :X2Value  ",
	"Y2_VALUE  :Y2Value  ",
	"AVG_X     :AvgX     ",
	"AVG_Y     :AvgY     ",
	"CLUSTER_ID:ClusterId",
	*/

	private String dataNum  ;
	private String glassId  ;
	private String cellId   ;
	private String XValue   ;
	private String YValue   ;
	private String x2Value  ;
	private String y2Value  ;
	private String avgX     ;
	private String avgY     ;
	private String clusterId;

	public String toString()
	{
		return String.format("READ : [%s] [%s] [%s] [%s] [%s] [%s] [%s] [%s] [%s] [%s]"
			, dataNum  
			, glassId  
			, cellId   
			, XValue   
			, YValue   
			, x2Value  
			, y2Value  
			, avgX     
			, avgY     
			, clusterId
		);
	}

	public static String[] getHeader()
	{
		List<String> list = new ArrayList<String>();
		list.add("DATA_NUM"  );
		list.add("GLASS_ID"  );
		list.add("CELL_ID"   );
		list.add("X_VALUE"   );
		list.add("Y_VALUE"   );
		list.add("X2_VALUE"  );
		list.add("Y2_VALUE"  );
		list.add("AVG_X"     );
		list.add("AVG_Y"     );
		list.add("CLUSTER_ID");

		return list.toArray(new String[list.size()]);
	}

	public String[] getStringArray()
	{
		List<String> list = new ArrayList<String>();
		list.add(dataNum  );
		list.add(glassId  );
		list.add(cellId   );
		list.add(XValue   );
		list.add(YValue   );
		list.add(x2Value  );
		list.add(y2Value  );
		list.add(avgX     );
		list.add(avgY     );
		list.add(clusterId);

		return list.toArray(new String[list.size()]);
	}

	/**
	 * @return the dataNum
	 */
	public String getDataNum()
	{
		return dataNum;
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
	 * @return the clusterId
	 */
	public String getClusterId()
	{
		return clusterId;
	}

	/**
	 * @param dataNum the dataNum to set
	 */
	public void setDataNum(String dataNum)
	{
		this.dataNum = dataNum;
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
	 * @param clusterId the clusterId to set
	 */
	public void setClusterId(String clusterId)
	{
		this.clusterId = clusterId;
	}
}
