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

package kiea.proj.sdc.anal.macro.sample.a11.img.macro.CONTACT_MAP_XY_RESULT;

import java.util.ArrayList;
import java.util.List;

import kiea.proj.sdc.anal.macro.impl.bean.AbstractBean;

/**
 * @author KangSeok
 * @date 2014. 8. 8.
 * @file_name CONTACT_MAP_Bean.java
 *
 */
public class CONTACT_MAP_Bean extends AbstractBean
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

	public String toString()
	{
		return String.format("READ : [%s] [%s] [%s] [%s] [%s] [%s] [%s] [%s] [%s] [%s] [%s] [%s] [%s] [%s] [%s] [%s] [%s] [%s]"
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
}
