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

package sdc.anal.mura.macro.A22.AVG_MURA_MAP_XY_4SF_RESULT.v01;

import java.util.ArrayList;
import java.util.List;

import kiea.proj.sdc.anal.macro.impl.bean.AbstractBean;

/**
 * @author KangSeok
 * @date 2014. 8. 8.
 * @file_name MURA_MAP_XY_RESULT_Bean.java
 *
 */
public class AVG_MURA_MAP_XY_4SF_RESULT_Bean extends AbstractBean
{
	/*
	"CLUSTER_NO   :ClusterNo   ",
	"SEQ          :Seq         ",
	"SITEID       :SiteId      ",
	"PRODGRPNAME  :ProdGrpName ",
	"PROCID       :ProcId      ",
	"CELL_POSITION:CellPosition",
	"ITEMID       :ItemId      ",
	"ITEMNAME     :ItemName    ",
	"AVG_X        :AvgX        ",
	"AVG_Y        :AvgY        ",
	*/

	private String clusterNo   ;
	private String seq         ;
	private String siteId      ;
	private String prodGrpName ;
	private String procId      ;
	private String cellPosition;
	private String itemId      ;
	private String itemName    ;
	private String avgX        ;
	private String avgY        ;

	public String toString()
	{
		return String.format("READ : [%s] [%s] [%s] [%s] [%s] [%s] [%s] [%s] [%s] [%s]"
			, clusterNo   
			, seq         
			, siteId      
			, prodGrpName 
			, procId      
			, cellPosition
			, itemId      
			, itemName    
			, avgX        
			, avgY        
		);
	}

	public static String[] getHeader()
	{
		List<String> list = new ArrayList<String>();
		list.add("CLUSTER_NO"   );
		list.add("SEQ"          );
		list.add("SITEID"       );
		list.add("PRODGRPNAME"  );
		list.add("PROCID"       );
		list.add("CELL_POSITION");
		list.add("ITEMID"       );
		list.add("ITEMNAME"     );
		list.add("AVG_X"        );
		list.add("AVG_Y"        );

		return list.toArray(new String[list.size()]);
	}

	public String[] getStringArray()
	{
		List<String> list = new ArrayList<String>();
		list.add(clusterNo   );
		list.add(seq         );
		list.add(siteId      );
		list.add(prodGrpName );
		list.add(procId      );
		list.add(cellPosition);
		list.add(itemId      );
		list.add(itemName    );
		list.add(avgX        );
		list.add(avgY        );

		return list.toArray(new String[list.size()]);
	}

	/**
	 * @return the clusterNo
	 */
	public String getClusterNo()
	{
		return clusterNo;
	}

	/**
	 * @return the seq
	 */
	public String getSeq()
	{
		return seq;
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
	 * @param clusterNo the clusterNo to set
	 */
	public void setClusterNo(String clusterNo)
	{
		this.clusterNo = clusterNo;
	}

	/**
	 * @param seq the seq to set
	 */
	public void setSeq(String seq)
	{
		this.seq = seq;
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
}
