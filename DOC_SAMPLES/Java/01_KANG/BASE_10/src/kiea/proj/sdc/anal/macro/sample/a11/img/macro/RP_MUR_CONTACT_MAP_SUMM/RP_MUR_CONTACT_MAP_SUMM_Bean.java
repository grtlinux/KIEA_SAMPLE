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

package kiea.proj.sdc.anal.macro.sample.a11.img.macro.RP_MUR_CONTACT_MAP_SUMM;

import java.util.ArrayList;
import java.util.List;

import kiea.proj.sdc.anal.macro.impl.bean.AbstractBean;

/**
 * @author KangSeok
 * @date 2014. 8. 8.
 * @file_name RP_MUR_AVGMUR_MAP_SUMM_Bean.java
 *
 */
public class RP_MUR_CONTACT_MAP_SUMM_Bean extends AbstractBean
{
	/*
	"DATA_NUM   :DataNum    ",
	"SEQ        :Seq        ",
	"CLUSTER_NO :ClusterNo  ",
	"SITEID     :SiteId     ",
	"PRODGRPNAME:ProdGrpName",
	"PROCID     :ProcId     ",
	"ITEMID     :ItemId     ",
	"ITEMNAME   :ItemName   ",
	"CELL_NO    :CellNo     ",
	"CELLLOCID  :CellLocId  ",
	"EQP_NAME   :EqpName    ",
	"PART       :Part       ",
	"MAKER      :Maker      ",
	"UNITNAME   :UnitName   ",
	"CONTACTMAP :ContactMap ",
	"CONTACT_X1 :ContactX1  ",
	"CONTACT_Y1 :ContactY1  ",
	"WIDTH      :Width      ",
	"HEIGHT     :Height     ",
	*/

	private String dataNum    ;
	private String seq        ;
	private String clusterNo  ;
	private String siteId     ;
	private String prodGrpName;
	private String procId     ;
	private String itemId     ;
	private String itemName   ;
	private String cellNo     ;
	private String cellLocId  ;
	private String eqpName    ;
	private String part       ;
	private String maker      ;
	private String unitName   ;
	private String contactMap ;
	private String contactX1  ;
	private String contactY1  ;
	private String width      ;
	private String height     ;

	public String toString()
	{
		return String.format("READ : [%s] [%s] [%s] [%s] [%s] [%s] [%s] [%s] [%s] [%s] [%s] [%s] [%s] [%s] [%s] [%s] [%s] [%s] [%s]"
			, dataNum    
			, seq        
			, clusterNo  
			, siteId     
			, prodGrpName
			, procId     
			, itemId     
			, itemName   
			, cellNo     
			, cellLocId  
			, eqpName    
			, part       
			, maker      
			, unitName   
			, contactMap 
			, contactX1  
			, contactY1  
			, width      
			, height     
		);
	}

	public static String[] getHeader()
	{
		List<String> list = new ArrayList<String>();
		list.add("DATA_NUM"   );
		list.add("SEQ"        );
		list.add("CLUSTER_NO" );
		list.add("SITEID"     );
		list.add("PRODGRPNAME");
		list.add("PROCID"     );
		list.add("ITEMID"     );
		list.add("ITEMNAME"   );
		list.add("CELL_NO"    );
		list.add("CELLLOCID"  );
		list.add("EQP_NAME"   );
		list.add("PART"       );
		list.add("MAKER"      );
		list.add("UNITNAME"   );
		list.add("CONTACTMAP" );
		list.add("CONTACT_X1" );
		list.add("CONTACT_Y1" );
		list.add("WIDTH"      );
		list.add("HEIGHT"     );

		return list.toArray(new String[list.size()]);
	}

	public String[] getStringArray()
	{
		List<String> list = new ArrayList<String>();
		list.add(dataNum    );
		list.add(seq        );
		list.add(clusterNo  );
		list.add(siteId     );
		list.add(prodGrpName);
		list.add(procId     );
		list.add(itemId     );
		list.add(itemName   );
		list.add(cellNo     );
		list.add(cellLocId  );
		list.add(eqpName    );
		list.add(part       );
		list.add(maker      );
		list.add(unitName   );
		list.add(contactMap );
		list.add(contactX1  );
		list.add(contactY1  );
		list.add(width      );
		list.add(height     );

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
	 * @return the seq
	 */
	public String getSeq()
	{
		return seq;
	}

	/**
	 * @return the clusterNo
	 */
	public String getClusterNo()
	{
		return clusterNo;
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
	 * @return the itemName
	 */
	public String getItemName()
	{
		return itemName;
	}

	/**
	 * @return the cellNo
	 */
	public String getCellNo()
	{
		return cellNo;
	}

	/**
	 * @return the cellLocId
	 */
	public String getCellLocId()
	{
		return cellLocId;
	}

	/**
	 * @return the eqpName
	 */
	public String getEqpName()
	{
		return eqpName;
	}

	/**
	 * @return the part
	 */
	public String getPart()
	{
		return part;
	}

	/**
	 * @return the maker
	 */
	public String getMaker()
	{
		return maker;
	}

	/**
	 * @return the unitName
	 */
	public String getUnitName()
	{
		return unitName;
	}

	/**
	 * @return the contactMap
	 */
	public String getContactMap()
	{
		return contactMap;
	}

	/**
	 * @return the contactX1
	 */
	public String getContactX1()
	{
		return contactX1;
	}

	/**
	 * @return the contactY1
	 */
	public String getContactY1()
	{
		return contactY1;
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
	 * @param dataNum the dataNum to set
	 */
	public void setDataNum(String dataNum)
	{
		this.dataNum = dataNum;
	}

	/**
	 * @param seq the seq to set
	 */
	public void setSeq(String seq)
	{
		this.seq = seq;
	}

	/**
	 * @param clusterNo the clusterNo to set
	 */
	public void setClusterNo(String clusterNo)
	{
		this.clusterNo = clusterNo;
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
	 * @param itemName the itemName to set
	 */
	public void setItemName(String itemName)
	{
		this.itemName = itemName;
	}

	/**
	 * @param cellNo the cellNo to set
	 */
	public void setCellNo(String cellNo)
	{
		this.cellNo = cellNo;
	}

	/**
	 * @param cellLocId the cellLocId to set
	 */
	public void setCellLocId(String cellLocId)
	{
		this.cellLocId = cellLocId;
	}

	/**
	 * @param eqpName the eqpName to set
	 */
	public void setEqpName(String eqpName)
	{
		this.eqpName = eqpName;
	}

	/**
	 * @param part the part to set
	 */
	public void setPart(String part)
	{
		this.part = part;
	}

	/**
	 * @param maker the maker to set
	 */
	public void setMaker(String maker)
	{
		this.maker = maker;
	}

	/**
	 * @param unitName the unitName to set
	 */
	public void setUnitName(String unitName)
	{
		this.unitName = unitName;
	}

	/**
	 * @param contactMap the contactMap to set
	 */
	public void setContactMap(String contactMap)
	{
		this.contactMap = contactMap;
	}

	/**
	 * @param contactX1 the contactX1 to set
	 */
	public void setContactX1(String contactX1)
	{
		this.contactX1 = contactX1;
	}

	/**
	 * @param contactY1 the contactY1 to set
	 */
	public void setContactY1(String contactY1)
	{
		this.contactY1 = contactY1;
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
