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

package sdc.anal.mura.macro.A22.MURA_MAP_XY_RESULT.v04;

import java.util.ArrayList;
import java.util.List;

/**
 * @author KangSeok
 * @date 2014. 8. 25.
 * @file_name TempResult.java
 *
 */
public final class TempResult
{
	private String siteId;
	private String prodGrpName;
	private String procId;
	private String cellPosition;
	private String itemId;
	private String itemName;
	private String clusterId;
	private String clusterNo;
	
	private List<TempRect> listRect;
	private String avgX1;
	private String avgY1;
	private String avgX2;
	private String avgY2;
	private String width;
	private String height;
	
	public TempResult()
	{
		listRect = new ArrayList<TempRect>();
	}
	
	public void addList(TempRect rect)
	{
		listRect.add(rect);
	}
	
	public void average()
	{
		int listSize = listRect.size();
		double sumX1 = 0.0;
		double sumY1 = 0.0;
		double sumX2 = 0.0;
		double sumY2 = 0.0;
		
		for (TempRect rect : listRect) {
			sumX1 += rect.getX1();
			sumY1 += rect.getY1();
			sumX2 += rect.getX2();
			sumY2 += rect.getY2();
		}
		
		long X1 = (long) sumX1 / listSize;
		long Y1 = (long) sumY1 / listSize;
		long X2 = (long) sumX2 / listSize;
		long Y2 = (long) sumY2 / listSize;
		
		this.avgX1 = "" + X1;
		this.avgY1 = "" + Y1;
		this.avgX2 = "" + X2;
		this.avgY2 = "" + Y2;
		
		this.width = "" + (X2 - X1);
		this.height = "" + (Y2 - Y1);
	}
	
	public String getKey()
	{
		StringBuffer sb = new StringBuffer();
		
		sb.append(this.siteId      ).append(":");
		sb.append(this.prodGrpName ).append(":");
		sb.append(this.procId      ).append(":");
		sb.append(this.cellPosition).append(":");
		sb.append(this.itemId      ).append(":");
		sb.append(this.clusterId   ).append(":");
		
		return sb.toString();
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
	 * @return the cellPosition
	 */
	public String getCellPosition()
	{
		return cellPosition;
	}

	/**
	 * @return the itemId
	 */
	public String getItemId()
	{
		return itemId;
	}

	/**
	 * @return the itemName
	 */
	public String getItemName()
	{
		return itemName;
	}

	/**
	 * @return the clusterId
	 */
	public String getClusterId()
	{
		return clusterId;
	}

	/**
	 * @return the clusterNo
	 */
	public String getClusterNo()
	{
		return clusterNo;
	}

	/**
	 * @return the avgX1
	 */
	public String getAvgX1()
	{
		return avgX1;
	}

	/**
	 * @return the avgY1
	 */
	public String getAvgY1()
	{
		return avgY1;
	}

	/**
	 * @return the avgX2
	 */
	public String getAvgX2()
	{
		return avgX2;
	}

	/**
	 * @return the avgY2
	 */
	public String getAvgY2()
	{
		return avgY2;
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
	 * @return the listRect
	 */
	public List<TempRect> getListRect()
	{
		return listRect;
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
	 * @param cellPosition the cellPosition to set
	 */
	public void setCellPosition(String cellPosition)
	{
		this.cellPosition = cellPosition;
	}

	/**
	 * @param itemId the itemId to set
	 */
	public void setItemId(String itemId)
	{
		this.itemId = itemId;
	}

	/**
	 * @param itemName the itemName to set
	 */
	public void setItemName(String itemName)
	{
		this.itemName = itemName;
	}

	/**
	 * @param clusterId the clusterId to set
	 */
	public void setClusterId(String clusterId)
	{
		this.clusterId = clusterId;
	}

	/**
	 * @param clusterNo the clusterNo to set
	 */
	public void setClusterNo(String clusterNo)
	{
		this.clusterNo = clusterNo;
	}

	/**
	 * @param avgX1 the avgX1 to set
	 */
	public void setAvgX1(String avgX1)
	{
		this.avgX1 = avgX1;
	}

	/**
	 * @param avgY1 the avgY1 to set
	 */
	public void setAvgY1(String avgY1)
	{
		this.avgY1 = avgY1;
	}

	/**
	 * @param avgX2 the avgX2 to set
	 */
	public void setAvgX2(String avgX2)
	{
		this.avgX2 = avgX2;
	}

	/**
	 * @param avgY2 the avgY2 to set
	 */
	public void setAvgY2(String avgY2)
	{
		this.avgY2 = avgY2;
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
	 * @param listRect the listRect to set
	 */
	public void setListRect(List<TempRect> listRect)
	{
		this.listRect = listRect;
	}
}
