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

package sdc.anal.mura.macro.A22.CONTACT_MURA_DIST_RESULT.v04;

import java.util.ArrayList;
import java.util.List;

import kiea.proj.sdc.anal.macro.impl.bean.AbstractBean;

/**
 * @author KangSeok
 * @date 2014. 8. 8.
 * @file_name CONTACT_MURA_XY_RESULT_Bean.java
 *
 */
public class CONTACT_MURA_DIST_RESULT_Bean extends AbstractBean
{
	/*
	"EQP_NAME        :EqpName        ",
	"LINE            :Line           ",
	"AREAID          :AreaId         ",
	"PART            :Part           ",
	"MAKER           :Maker          ",
	"UNITNAME        :UnitName       ",
	"CONTACTMAP      :ContactMap     ",
	"COORD_X1        :CoordX1        ",
	"COORD_Y1        :CoordY1        ",
	"COORD_X2        :CoordX2        ",
	"COORD_Y2        :CoordY2        ",
	"MATERIAL        :Material       ",
	"TYPE            :Type           ",
	"VERSION         :Version        ",
	"CONTACTMAP_ATTR1:ContactMapAttr1",
	"CONTACTMAP_ATTR2:ContactMapAttr2",
	"USERID          :UserId         ",
	"UPDATETIME      :UpdateTime     ",
	"CELL_NO         :CellNo         ",
	"PROCID          :ProcId         ",
	"CELLLOCID       :CellLocId      ",
	"POINT_X         :PointX         ",
	"POINT_Y         :PointY         ",
	"WIDTH           :Width          ",
	"HEIGHT          :Height         ",
	"POINT_X2        :PointX2        ",
	"POINT_Y2        :PointY2        ",
	"CONTACT_X1      :ContactX1      ",
	"CONTACT_Y1      :ContactY1      ",
	"CONTACT_X2      :ContactX2      ",
	"CONTACT_Y2      :ContactY2      ",
	"SITEID          :SiteId         ",
	"PRODGRPNAME     :ProdGrpName    ",
	"CELL_POSITION   :CellPosition   ",
	"ITEMID          :ItemId         ",
	"ITEMNAME        :ItemName       ",
	"AVG_X1          :AvgX1          ",
	"AVG_Y1          :AvgY1          ",
	"AVG_X2          :AvgX2          ",
	"AVG_Y2          :AvgY2          ",
	"CLUSTERID       :ClusterId      ",
	"CLUSTER_NO      :ClusterNo      ",
	"MURA_X1         :MuraX1         ",
	"MURA_Y1         :MuraY1         ",
	"MURA_X2         :MuraX2         ",
	"MURA_Y2         :MuraY2         ",
	*/

	private String eqpName        ;
	private String line           ;
	private String areaId         ;
	private String part           ;
	private String maker          ;
	private String unitName       ;
	private String contactMap     ;
	private String coordX1        ;
	private String coordY1        ;
	private String coordX2        ;
	private String coordY2        ;
	private String material       ;
	private String type           ;
	private String version        ;
	private String contactMapAttr1;
	private String contactMapAttr2;
	private String userId         ;
	private String updateTime     ;
	private String cellNo         ;
	private String procId         ;
	private String cellLocId      ;
	private String pointX         ;
	private String pointY         ;
	private String width          ;
	private String height         ;
	private String pointX2        ;
	private String pointY2        ;
	private String contactX1      ;
	private String contactY1      ;
	private String contactX2      ;
	private String contactY2      ;
	private String siteId         ;
	private String prodGrpName    ;
	private String cellPosition   ;
	private String itemId         ;
	private String itemName       ;
	private String avgX1          ;
	private String avgY1          ;
	private String avgX2          ;
	private String avgY2          ;
	private String clusterId      ;
	private String clusterNo      ;
	private String muraX1         ;
	private String muraY1         ;
	private String muraX2         ;
	private String muraY2         ;

	public String toString()
	{
		return String.format("READ : [%s] [%s] [%s] [%s] [%s] [%s] [%s] [%s] [%s] [%s] [%s] [%s] [%s] [%s] [%s] [%s] [%s] [%s] [%s] [%s] [%s] [%s] [%s] [%s] [%s] [%s] [%s] [%s] [%s] [%s] [%s] [%s] [%s] [%s] [%s] [%s] [%s] [%s] [%s] [%s] [%s] [%s] [%s] [%s] [%s] [%s]"
			, eqpName        
			, line           
			, areaId         
			, part           
			, maker          
			, unitName       
			, contactMap     
			, coordX1        
			, coordY1        
			, coordX2        
			, coordY2        
			, material       
			, type           
			, version        
			, contactMapAttr1
			, contactMapAttr2
			, userId         
			, updateTime     
			, cellNo         
			, procId         
			, cellLocId      
			, pointX         
			, pointY         
			, width          
			, height         
			, pointX2        
			, pointY2        
			, contactX1      
			, contactY1      
			, contactX2      
			, contactY2      
			, siteId         
			, prodGrpName    
			, cellPosition   
			, itemId         
			, itemName       
			, avgX1          
			, avgY1          
			, avgX2          
			, avgY2          
			, clusterId      
			, clusterNo      
			, muraX1         
			, muraY1         
			, muraX2         
			, muraY2         
		);
	}

	public static String[] getHeader()
	{
		List<String> list = new ArrayList<String>();
		list.add("EQP_NAME"        );
		list.add("LINE"            );
		list.add("AREAID"          );
		list.add("PART"            );
		list.add("MAKER"           );
		list.add("UNITNAME"        );
		list.add("CONTACTMAP"      );
		list.add("COORD_X1"        );
		list.add("COORD_Y1"        );
		list.add("COORD_X2"        );
		list.add("COORD_Y2"        );
		list.add("MATERIAL"        );
		list.add("TYPE"            );
		list.add("VERSION"         );
		list.add("CONTACTMAP_ATTR1");
		list.add("CONTACTMAP_ATTR2");
		list.add("USERID"          );
		list.add("UPDATETIME"      );
		list.add("CELL_NO"         );
		list.add("PROCID"          );
		list.add("CELLLOCID"       );
		list.add("POINT_X"         );
		list.add("POINT_Y"         );
		list.add("WIDTH"           );
		list.add("HEIGHT"          );
		list.add("POINT_X2"        );
		list.add("POINT_Y2"        );
		list.add("CONTACT_X1"      );
		list.add("CONTACT_Y1"      );
		list.add("CONTACT_X2"      );
		list.add("CONTACT_Y2"      );
		list.add("SITEID"          );
		list.add("PRODGRPNAME"     );
		list.add("CELL_POSITION"   );
		list.add("ITEMID"          );
		list.add("ITEMNAME"        );
		list.add("AVG_X1"          );
		list.add("AVG_Y1"          );
		list.add("AVG_X2"          );
		list.add("AVG_Y2"          );
		list.add("CLUSTERID"       );
		list.add("CLUSTER_NO"      );
		list.add("MURA_X1"         );
		list.add("MURA_Y1"         );
		list.add("MURA_X2"         );
		list.add("MURA_Y2"         );

		return list.toArray(new String[list.size()]);
	}

	public String[] getStringArray()
	{
		List<String> list = new ArrayList<String>();
		list.add(eqpName        );
		list.add(line           );
		list.add(areaId         );
		list.add(part           );
		list.add(maker          );
		list.add(unitName       );
		list.add(contactMap     );
		list.add(coordX1        );
		list.add(coordY1        );
		list.add(coordX2        );
		list.add(coordY2        );
		list.add(material       );
		list.add(type           );
		list.add(version        );
		list.add(contactMapAttr1);
		list.add(contactMapAttr2);
		list.add(userId         );
		list.add(updateTime     );
		list.add(cellNo         );
		list.add(procId         );
		list.add(cellLocId      );
		list.add(pointX         );
		list.add(pointY         );
		list.add(width          );
		list.add(height         );
		list.add(pointX2        );
		list.add(pointY2        );
		list.add(contactX1      );
		list.add(contactY1      );
		list.add(contactX2      );
		list.add(contactY2      );
		list.add(siteId         );
		list.add(prodGrpName    );
		list.add(cellPosition   );
		list.add(itemId         );
		list.add(itemName       );
		list.add(avgX1          );
		list.add(avgY1          );
		list.add(avgX2          );
		list.add(avgY2          );
		list.add(clusterId      );
		list.add(clusterNo      );
		list.add(muraX1         );
		list.add(muraY1         );
		list.add(muraX2         );
		list.add(muraY2         );

		return list.toArray(new String[list.size()]);
	}

	/**
	 * @return the eqpName
	 */
	public String getEqpName()
	{
		return eqpName;
	}

	/**
	 * @return the line
	 */
	public String getLine()
	{
		return line;
	}

	/**
	 * @return the areaId
	 */
	public String getAreaId()
	{
		return areaId;
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
	 * @return the coordX1
	 */
	public String getCoordX1()
	{
		return coordX1;
	}

	/**
	 * @return the coordY1
	 */
	public String getCoordY1()
	{
		return coordY1;
	}

	/**
	 * @return the coordX2
	 */
	public String getCoordX2()
	{
		return coordX2;
	}

	/**
	 * @return the coordY2
	 */
	public String getCoordY2()
	{
		return coordY2;
	}

	/**
	 * @return the material
	 */
	public String getMaterial()
	{
		return material;
	}

	/**
	 * @return the type
	 */
	public String getType()
	{
		return type;
	}

	/**
	 * @return the version
	 */
	public String getVersion()
	{
		return version;
	}

	/**
	 * @return the contactMapAttr1
	 */
	public String getContactMapAttr1()
	{
		return contactMapAttr1;
	}

	/**
	 * @return the contactMapAttr2
	 */
	public String getContactMapAttr2()
	{
		return contactMapAttr2;
	}

	/**
	 * @return the userId
	 */
	public String getUserId()
	{
		return userId;
	}

	/**
	 * @return the updateTime
	 */
	public String getUpdateTime()
	{
		return updateTime;
	}

	/**
	 * @return the cellNo
	 */
	public String getCellNo()
	{
		return cellNo;
	}

	/**
	 * @return the procId
	 */
	public String getProcId()
	{
		return procId;
	}

	/**
	 * @return the cellLocId
	 */
	public String getCellLocId()
	{
		return cellLocId;
	}

	/**
	 * @return the pointX
	 */
	public String getPointX()
	{
		return pointX;
	}

	/**
	 * @return the pointY
	 */
	public String getPointY()
	{
		return pointY;
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
	 * @return the pointX2
	 */
	public String getPointX2()
	{
		return pointX2;
	}

	/**
	 * @return the pointY2
	 */
	public String getPointY2()
	{
		return pointY2;
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
	 * @return the contactX2
	 */
	public String getContactX2()
	{
		return contactX2;
	}

	/**
	 * @return the contactY2
	 */
	public String getContactY2()
	{
		return contactY2;
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
	 * @return the muraX1
	 */
	public String getMuraX1()
	{
		return muraX1;
	}

	/**
	 * @return the muraY1
	 */
	public String getMuraY1()
	{
		return muraY1;
	}

	/**
	 * @return the muraX2
	 */
	public String getMuraX2()
	{
		return muraX2;
	}

	/**
	 * @return the muraY2
	 */
	public String getMuraY2()
	{
		return muraY2;
	}

	/**
	 * @param eqpName the eqpName to set
	 */
	public void setEqpName(String eqpName)
	{
		this.eqpName = eqpName;
	}

	/**
	 * @param line the line to set
	 */
	public void setLine(String line)
	{
		this.line = line;
	}

	/**
	 * @param areaId the areaId to set
	 */
	public void setAreaId(String areaId)
	{
		this.areaId = areaId;
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
	 * @param coordX1 the coordX1 to set
	 */
	public void setCoordX1(String coordX1)
	{
		this.coordX1 = coordX1;
	}

	/**
	 * @param coordY1 the coordY1 to set
	 */
	public void setCoordY1(String coordY1)
	{
		this.coordY1 = coordY1;
	}

	/**
	 * @param coordX2 the coordX2 to set
	 */
	public void setCoordX2(String coordX2)
	{
		this.coordX2 = coordX2;
	}

	/**
	 * @param coordY2 the coordY2 to set
	 */
	public void setCoordY2(String coordY2)
	{
		this.coordY2 = coordY2;
	}

	/**
	 * @param material the material to set
	 */
	public void setMaterial(String material)
	{
		this.material = material;
	}

	/**
	 * @param type the type to set
	 */
	public void setType(String type)
	{
		this.type = type;
	}

	/**
	 * @param version the version to set
	 */
	public void setVersion(String version)
	{
		this.version = version;
	}

	/**
	 * @param contactMapAttr1 the contactMapAttr1 to set
	 */
	public void setContactMapAttr1(String contactMapAttr1)
	{
		this.contactMapAttr1 = contactMapAttr1;
	}

	/**
	 * @param contactMapAttr2 the contactMapAttr2 to set
	 */
	public void setContactMapAttr2(String contactMapAttr2)
	{
		this.contactMapAttr2 = contactMapAttr2;
	}

	/**
	 * @param userId the userId to set
	 */
	public void setUserId(String userId)
	{
		this.userId = userId;
	}

	/**
	 * @param updateTime the updateTime to set
	 */
	public void setUpdateTime(String updateTime)
	{
		this.updateTime = updateTime;
	}

	/**
	 * @param cellNo the cellNo to set
	 */
	public void setCellNo(String cellNo)
	{
		this.cellNo = cellNo;
	}

	/**
	 * @param procId the procId to set
	 */
	public void setProcId(String procId)
	{
		this.procId = procId;
	}

	/**
	 * @param cellLocId the cellLocId to set
	 */
	public void setCellLocId(String cellLocId)
	{
		this.cellLocId = cellLocId;
	}

	/**
	 * @param pointX the pointX to set
	 */
	public void setPointX(String pointX)
	{
		this.pointX = pointX;
	}

	/**
	 * @param pointY the pointY to set
	 */
	public void setPointY(String pointY)
	{
		this.pointY = pointY;
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
	 * @param pointX2 the pointX2 to set
	 */
	public void setPointX2(String pointX2)
	{
		this.pointX2 = pointX2;
	}

	/**
	 * @param pointY2 the pointY2 to set
	 */
	public void setPointY2(String pointY2)
	{
		this.pointY2 = pointY2;
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
	 * @param contactX2 the contactX2 to set
	 */
	public void setContactX2(String contactX2)
	{
		this.contactX2 = contactX2;
	}

	/**
	 * @param contactY2 the contactY2 to set
	 */
	public void setContactY2(String contactY2)
	{
		this.contactY2 = contactY2;
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
	 * @param muraX1 the muraX1 to set
	 */
	public void setMuraX1(String muraX1)
	{
		this.muraX1 = muraX1;
	}

	/**
	 * @param muraY1 the muraY1 to set
	 */
	public void setMuraY1(String muraY1)
	{
		this.muraY1 = muraY1;
	}

	/**
	 * @param muraX2 the muraX2 to set
	 */
	public void setMuraX2(String muraX2)
	{
		this.muraX2 = muraX2;
	}

	/**
	 * @param muraY2 the muraY2 to set
	 */
	public void setMuraY2(String muraY2)
	{
		this.muraY2 = muraY2;
	}
}
