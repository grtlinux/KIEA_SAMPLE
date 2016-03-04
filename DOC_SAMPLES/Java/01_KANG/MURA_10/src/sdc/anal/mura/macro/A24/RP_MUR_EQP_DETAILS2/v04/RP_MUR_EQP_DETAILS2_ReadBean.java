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

package sdc.anal.mura.macro.A24.RP_MUR_EQP_DETAILS2.v04;

import java.util.ArrayList;
import java.util.List;

import kiea.proj.sdc.anal.macro.impl.bean.AbstractBean;

/**
 * @author KangSeok
 * @date 2014. 8. 8.
 * @file_name ANAL_INDEX_ReadBean.java
 *
 */
public class RP_MUR_EQP_DETAILS2_ReadBean extends AbstractBean
{
	/*
	"SITEID        :SiteId       ",
	"SITEID_EQPTYPE:SiteIdEqpType",
	"COMPMASTERKEY :CompMasterKey",
	"GLASSID       :GlassId      ",
	"PRODID        :ProdId       ",
	"PROCID        :ProcId       ",
	"ORGSTEPID     :OrgStepId    ",
	"MEASSTEPGRPID :MeasStepGrpId",
	"MEASEQPUNITID :MeasEqpUnitId",
	"DCOLDATE      :DcolDate     ",
	"ITEMID        :ItemId       ",
	"SUBITEMID     :SubItemId    ",
	"DATAVALUE     :DataValue    ",
	"CELLLOCID     :CellLocId    ",
	"USL           :Usl          ",
	"LSL           :Lsl          ",
	"TARGET        :Target       ",
	"CODE_X        :CodeX        ",
	"CODE_Y        :CodeY        ",
	"PRODTYPE      :ProdType     ",
	"CODE_X2       :CodeX2       ",
	"CODE_Y2       :CodeY2       ",
	"ETLMODIFYDATE :EtlModifyDate",
	"SHOTNO        :ShotNo       ",
	"IMGFILE       :ImgFile      ",
	*/

	private String siteId       ;
	private String siteIdEqpType;
	private String compMasterKey;
	private String glassId      ;
	private String prodId       ;
	private String procId       ;
	private String orgStepId    ;
	private String measStepGrpId;
	private String measEqpUnitId;
	private String dcolDate     ;
	private String itemId       ;
	private String subItemId    ;
	private String dataValue    ;
	private String cellLocId    ;
	private String usl          ;
	private String lsl          ;
	private String target       ;
	private String codeX        ;
	private String codeY        ;
	private String prodType     ;
	private String codeX2       ;
	private String codeY2       ;
	private String etlModifyDate;
	private String shotNo       ;
	private String imgFile      ;

	public String toString()
	{
		return String.format("READ : [%s] [%s] [%s] [%s] [%s] [%s] [%s] [%s] [%s] [%s] [%s] [%s] [%s] [%s] [%s] [%s] [%s] [%s] [%s] [%s] [%s] [%s] [%s] [%s] [%s]"
			, siteId       
			, siteIdEqpType
			, compMasterKey
			, glassId      
			, prodId       
			, procId       
			, orgStepId    
			, measStepGrpId
			, measEqpUnitId
			, dcolDate     
			, itemId       
			, subItemId    
			, dataValue    
			, cellLocId    
			, usl          
			, lsl          
			, target       
			, codeX        
			, codeY        
			, prodType     
			, codeX2       
			, codeY2       
			, etlModifyDate
			, shotNo       
			, imgFile      
		);
	}

	public static String[] getHeader()
	{
		List<String> list = new ArrayList<String>();
		list.add("SITEID"        );
		list.add("SITEID_EQPTYPE");
		list.add("COMPMASTERKEY" );
		list.add("GLASSID"       );
		list.add("PRODID"        );
		list.add("PROCID"        );
		list.add("ORGSTEPID"     );
		list.add("MEASSTEPGRPID" );
		list.add("MEASEQPUNITID" );
		list.add("DCOLDATE"      );
		list.add("ITEMID"        );
		list.add("SUBITEMID"     );
		list.add("DATAVALUE"     );
		list.add("CELLLOCID"     );
		list.add("USL"           );
		list.add("LSL"           );
		list.add("TARGET"        );
		list.add("CODE_X"        );
		list.add("CODE_Y"        );
		list.add("PRODTYPE"      );
		list.add("CODE_X2"       );
		list.add("CODE_Y2"       );
		list.add("ETLMODIFYDATE" );
		list.add("SHOTNO"        );
		list.add("IMGFILE"       );

		return list.toArray(new String[list.size()]);
	}

	public String[] getStringArray()
	{
		List<String> list = new ArrayList<String>();
		list.add(siteId       );
		list.add(siteIdEqpType);
		list.add(compMasterKey);
		list.add(glassId      );
		list.add(prodId       );
		list.add(procId       );
		list.add(orgStepId    );
		list.add(measStepGrpId);
		list.add(measEqpUnitId);
		list.add(dcolDate     );
		list.add(itemId       );
		list.add(subItemId    );
		list.add(dataValue    );
		list.add(cellLocId    );
		list.add(usl          );
		list.add(lsl          );
		list.add(target       );
		list.add(codeX        );
		list.add(codeY        );
		list.add(prodType     );
		list.add(codeX2       );
		list.add(codeY2       );
		list.add(etlModifyDate);
		list.add(shotNo       );
		list.add(imgFile      );

		return list.toArray(new String[list.size()]);
	}

	public String getSiteId()
	{
		return siteId;
	}

	public void setSiteId(String siteId)
	{
		this.siteId = siteId;
	}

	public String getSiteIdEqpType()
	{
		return siteIdEqpType;
	}

	public void setSiteIdEqpType(String siteIdEqpType)
	{
		this.siteIdEqpType = siteIdEqpType;
	}

	public String getCompMasterKey()
	{
		return compMasterKey;
	}

	public void setCompMasterKey(String compMasterKey)
	{
		this.compMasterKey = compMasterKey;
	}

	public String getGlassId()
	{
		return glassId;
	}

	public void setGlassId(String glassId)
	{
		this.glassId = glassId;
	}

	public String getProdId()
	{
		return prodId;
	}

	public void setProdId(String prodId)
	{
		this.prodId = prodId;
	}

	public String getProcId()
	{
		return procId;
	}

	public void setProcId(String procId)
	{
		this.procId = procId;
	}

	public String getOrgStepId()
	{
		return orgStepId;
	}

	public void setOrgStepId(String orgStepId)
	{
		this.orgStepId = orgStepId;
	}

	public String getMeasStepGrpId()
	{
		return measStepGrpId;
	}

	public void setMeasStepGrpId(String measStepGrpId)
	{
		this.measStepGrpId = measStepGrpId;
	}

	public String getMeasEqpUnitId()
	{
		return measEqpUnitId;
	}

	public void setMeasEqpUnitId(String measEqpUnitId)
	{
		this.measEqpUnitId = measEqpUnitId;
	}

	public String getDcolDate()
	{
		return dcolDate;
	}

	public void setDcolDate(String dcolDate)
	{
		this.dcolDate = dcolDate;
	}

	public String getItemId()
	{
		return itemId;
	}

	public void setItemId(String itemId)
	{
		this.itemId = itemId;
	}

	public String getSubItemId()
	{
		return subItemId;
	}

	public void setSubItemId(String subItemId)
	{
		this.subItemId = subItemId;
	}

	public String getDataValue()
	{
		return dataValue;
	}

	public void setDataValue(String dataValue)
	{
		this.dataValue = dataValue;
	}

	public String getCellLocId()
	{
		return cellLocId;
	}

	public void setCellLocId(String cellLocId)
	{
		this.cellLocId = cellLocId;
	}

	public String getUsl()
	{
		return usl;
	}

	public void setUsl(String usl)
	{
		this.usl = usl;
	}

	public String getLsl()
	{
		return lsl;
	}

	public void setLsl(String lsl)
	{
		this.lsl = lsl;
	}

	public String getTarget()
	{
		return target;
	}

	public void setTarget(String target)
	{
		this.target = target;
	}

	public String getCodeX()
	{
		return codeX;
	}

	public void setCodeX(String codeX)
	{
		this.codeX = codeX;
	}

	public String getCodeY()
	{
		return codeY;
	}

	public void setCodeY(String codeY)
	{
		this.codeY = codeY;
	}

	public String getProdType()
	{
		return prodType;
	}

	public void setProdType(String prodType)
	{
		this.prodType = prodType;
	}

	public String getCodeX2()
	{
		return codeX2;
	}

	public void setCodeX2(String codeX2)
	{
		this.codeX2 = codeX2;
	}

	public String getCodeY2()
	{
		return codeY2;
	}

	public void setCodeY2(String codeY2)
	{
		this.codeY2 = codeY2;
	}

	public String getEtlModifyDate()
	{
		return etlModifyDate;
	}

	public void setEtlModifyDate(String etlModifyDate)
	{
		this.etlModifyDate = etlModifyDate;
	}

	public String getShotNo()
	{
		return shotNo;
	}

	public void setShotNo(String shotNo)
	{
		this.shotNo = shotNo;
	}

	public String getImgFile()
	{
		return imgFile;
	}

	public void setImgFile(String imgFile)
	{
		this.imgFile = imgFile;
	}
}
