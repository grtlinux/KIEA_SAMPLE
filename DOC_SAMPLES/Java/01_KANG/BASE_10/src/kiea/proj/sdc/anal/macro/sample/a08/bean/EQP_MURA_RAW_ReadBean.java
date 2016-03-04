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

package kiea.proj.sdc.anal.macro.sample.a08.bean;

import java.util.ArrayList;
import java.util.List;

import kiea.proj.sdc.anal.macro.impl.bean.AbstractBean;

/**
 * @author KangSeok
 * @date 2014. 8. 8.
 * @file_name EQP_MURA_RAW_ReadBean.java
 *
 */
public class EQP_MURA_RAW_ReadBean extends AbstractBean
{
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

	/**
	 * @return the siteId
	 */
	public String getSiteId()
	{
		return siteId;
	}

	/**
	 * @return the siteIdEqpType
	 */
	public String getSiteIdEqpType()
	{
		return siteIdEqpType;
	}

	/**
	 * @return the compMasterKey
	 */
	public String getCompMasterKey()
	{
		return compMasterKey;
	}

	/**
	 * @return the glassId
	 */
	public String getGlassId()
	{
		return glassId;
	}

	/**
	 * @return the prodId
	 */
	public String getProdId()
	{
		return prodId;
	}

	/**
	 * @return the procId
	 */
	public String getProcId()
	{
		return procId;
	}

	/**
	 * @return the orgStepId
	 */
	public String getOrgStepId()
	{
		return orgStepId;
	}

	/**
	 * @return the measStepGrpId
	 */
	public String getMeasStepGrpId()
	{
		return measStepGrpId;
	}

	/**
	 * @return the measEqpUnitId
	 */
	public String getMeasEqpUnitId()
	{
		return measEqpUnitId;
	}

	/**
	 * @return the dcolDate
	 */
	public String getDcolDate()
	{
		return dcolDate;
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
	 * @return the dataValue
	 */
	public String getDataValue()
	{
		return dataValue;
	}

	/**
	 * @return the cellLocId
	 */
	public String getCellLocId()
	{
		return cellLocId;
	}

	/**
	 * @return the usl
	 */
	public String getUsl()
	{
		return usl;
	}

	/**
	 * @return the lsl
	 */
	public String getLsl()
	{
		return lsl;
	}

	/**
	 * @return the target
	 */
	public String getTarget()
	{
		return target;
	}

	/**
	 * @return the codeX
	 */
	public String getCodeX()
	{
		return codeX;
	}

	/**
	 * @return the codeY
	 */
	public String getCodeY()
	{
		return codeY;
	}

	/**
	 * @return the prodType
	 */
	public String getProdType()
	{
		return prodType;
	}

	/**
	 * @return the codeX2
	 */
	public String getCodeX2()
	{
		return codeX2;
	}

	/**
	 * @return the codeY2
	 */
	public String getCodeY2()
	{
		return codeY2;
	}

	/**
	 * @return the etlModifyDate
	 */
	public String getEtlModifyDate()
	{
		return etlModifyDate;
	}

	/**
	 * @return the shotNo
	 */
	public String getShotNo()
	{
		return shotNo;
	}

	/**
	 * @return the imgFile
	 */
	public String getImgFile()
	{
		return imgFile;
	}

	/**
	 * @param siteId the siteId to set
	 */
	public void setSiteId(String siteId)
	{
		this.siteId = siteId;
	}

	/**
	 * @param siteIdEqpType the siteIdEqpType to set
	 */
	public void setSiteIdEqpType(String siteIdEqpType)
	{
		this.siteIdEqpType = siteIdEqpType;
	}

	/**
	 * @param compMasterKey the compMasterKey to set
	 */
	public void setCompMasterKey(String compMasterKey)
	{
		this.compMasterKey = compMasterKey;
	}

	/**
	 * @param glassId the glassId to set
	 */
	public void setGlassId(String glassId)
	{
		this.glassId = glassId;
	}

	/**
	 * @param prodId the prodId to set
	 */
	public void setProdId(String prodId)
	{
		this.prodId = prodId;
	}

	/**
	 * @param procId the procId to set
	 */
	public void setProcId(String procId)
	{
		this.procId = procId;
	}

	/**
	 * @param orgStepId the orgStepId to set
	 */
	public void setOrgStepId(String orgStepId)
	{
		this.orgStepId = orgStepId;
	}

	/**
	 * @param measStepGrpId the measStepGrpId to set
	 */
	public void setMeasStepGrpId(String measStepGrpId)
	{
		this.measStepGrpId = measStepGrpId;
	}

	/**
	 * @param measEqpUnitId the measEqpUnitId to set
	 */
	public void setMeasEqpUnitId(String measEqpUnitId)
	{
		this.measEqpUnitId = measEqpUnitId;
	}

	/**
	 * @param dcolDate the dcolDate to set
	 */
	public void setDcolDate(String dcolDate)
	{
		this.dcolDate = dcolDate;
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
	 * @param dataValue the dataValue to set
	 */
	public void setDataValue(String dataValue)
	{
		this.dataValue = dataValue;
	}

	/**
	 * @param cellLocId the cellLocId to set
	 */
	public void setCellLocId(String cellLocId)
	{
		this.cellLocId = cellLocId;
	}

	/**
	 * @param usl the usl to set
	 */
	public void setUsl(String usl)
	{
		this.usl = usl;
	}

	/**
	 * @param lsl the lsl to set
	 */
	public void setLsl(String lsl)
	{
		this.lsl = lsl;
	}

	/**
	 * @param target the target to set
	 */
	public void setTarget(String target)
	{
		this.target = target;
	}

	/**
	 * @param codeX the codeX to set
	 */
	public void setCodeX(String codeX)
	{
		this.codeX = codeX;
	}

	/**
	 * @param codeY the codeY to set
	 */
	public void setCodeY(String codeY)
	{
		this.codeY = codeY;
	}

	/**
	 * @param prodType the prodType to set
	 */
	public void setProdType(String prodType)
	{
		this.prodType = prodType;
	}

	/**
	 * @param codeX2 the codeX2 to set
	 */
	public void setCodeX2(String codeX2)
	{
		this.codeX2 = codeX2;
	}

	/**
	 * @param codeY2 the codeY2 to set
	 */
	public void setCodeY2(String codeY2)
	{
		this.codeY2 = codeY2;
	}

	/**
	 * @param etlModifyDate the etlModifyDate to set
	 */
	public void setEtlModifyDate(String etlModifyDate)
	{
		this.etlModifyDate = etlModifyDate;
	}

	/**
	 * @param shotNo the shotNo to set
	 */
	public void setShotNo(String shotNo)
	{
		this.shotNo = shotNo;
	}

	/**
	 * @param imgFile the imgFile to set
	 */
	public void setImgFile(String imgFile)
	{
		this.imgFile = imgFile;
	}
}
