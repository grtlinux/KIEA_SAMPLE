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

package sdc.anal.mura.macro.A22.CONTACT_MURA_XY_RESULT.v04;

import java.util.ArrayList;
import java.util.List;

import kiea.proj.sdc.anal.macro.impl.bean.AbstractBean;

/**
 * @author KangSeok
 * @date 2014. 8. 8.
 * @file_name MURA_MAP_XY_RESULT_Bean.java
 *
 */
public class MURA_MAP_XY_RESULT_Bean extends AbstractBean
{
	/*
	"SITEID       :SiteId      ",
	"PRODGRPNAME  :ProdGrpName ",
	"PROCID       :ProcId      ",
	"CELL_POSITION:CellPosition",
	"ITEMID       :ItemId      ",
	"ITEMNAME     :ItemName    ",
	"AVG_X1       :AvgX1       ",
	"AVG_Y1       :AvgY1       ",
	"AVG_X2       :AvgX2       ",
	"AVG_Y2       :AvgY2       ",
	"CLUSTERID    :ClusterId   ",
	"CLUSTER_NO   :ClusterNo   ",
	"WIDTH        :Width       ",
	"HEIGHT       :Height      ",
	*/

	private String siteId      ;
	private String prodGrpName ;
	private String procId      ;
	private String cellPosition;
	private String itemId      ;
	private String itemName    ;
	private String avgX1       ;
	private String avgY1       ;
	private String avgX2       ;
	private String avgY2       ;
	private String clusterId   ;
	private String clusterNo   ;
	private String width       ;
	private String height      ;

	public String toString()
	{
		return String.format("READ : [%s] [%s] [%s] [%s] [%s] [%s] [%s] [%s] [%s] [%s] [%s] [%s] [%s] [%s]"
			, siteId      
			, prodGrpName 
			, procId      
			, cellPosition
			, itemId      
			, itemName    
			, avgX1       
			, avgY1       
			, avgX2       
			, avgY2       
			, clusterId   
			, clusterNo   
			, width       
			, height      
		);
	}

	public static String[] getHeader()
	{
		List<String> list = new ArrayList<String>();
		list.add("SITEID"       );
		list.add("PRODGRPNAME"  );
		list.add("PROCID"       );
		list.add("CELL_POSITION");
		list.add("ITEMID"       );
		list.add("ITEMNAME"     );
		list.add("AVG_X1"       );
		list.add("AVG_Y1"       );
		list.add("AVG_X2"       );
		list.add("AVG_Y2"       );
		list.add("CLUSTERID"    );
		list.add("CLUSTER_NO"   );
		list.add("WIDTH"        );
		list.add("HEIGHT"       );

		return list.toArray(new String[list.size()]);
	}

	public String[] getStringArray()
	{
		List<String> list = new ArrayList<String>();
		list.add(siteId      );
		list.add(prodGrpName );
		list.add(procId      );
		list.add(cellPosition);
		list.add(itemId      );
		list.add(itemName    );
		list.add(avgX1       );
		list.add(avgY1       );
		list.add(avgX2       );
		list.add(avgY2       );
		list.add(clusterId   );
		list.add(clusterNo   );
		list.add(width       );
		list.add(height      );

		return list.toArray(new String[list.size()]);
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
}
