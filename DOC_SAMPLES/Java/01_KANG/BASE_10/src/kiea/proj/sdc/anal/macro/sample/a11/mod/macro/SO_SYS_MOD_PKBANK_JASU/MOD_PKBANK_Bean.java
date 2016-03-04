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

package kiea.proj.sdc.anal.macro.sample.a11.mod.macro.SO_SYS_MOD_PKBANK_JASU;

import java.util.ArrayList;
import java.util.List;

import kiea.proj.sdc.anal.macro.impl.bean.AbstractBean;

/**
 * @author KangSeok
 * @date 2014. 8. 8.
 * @file_name MOD_PKBANK_Bean.java
 *
 */
public class MOD_PKBANK_Bean extends AbstractBean
{
	/*
	"SITEID            :SiteId            ",
	"PRODGRPNAME       :ProdGrpName       ",
	"PRODID            :ProdId            ",
	"GLASSCELLID       :GlassCellId       ",
	"IMPTSTEPGRPID     :ImptStepGrpId     ",
	"IMPTEQPID         :ImptEqpId         ",
	"PROCHOUR          :ProcHour          ",
	"CELLID            :CellId            ",
	"GLASSID           :GlassId           ",
	"INSPSTEPTYPE      :InspStepType      ",
	"DECGRADECD        :DecGradeCd        ",
	"INSPDATE          :InspDate          ",
	"INSPHOUR          :InspHour          ",
	"INSPEQPID         :InspEqpId         ",
	"BATCHID           :BatchId           ",
	"PRODTYPE          :ProdType          ",
	"PROCID            :ProcId            ",
	"INSPSTEPID        :InspStepId        ",
	"BINGRADECD        :BinGradeCd        ",
	"MATCHLOTTYPE      :MatchLotType      ",
	"DEFECTCD          :DefectCd          ",
	"WORKERID          :WorkerId          ",
	"CELLLOCID         :CellLocId         ",
	"DEFECTSYSID       :DefectSysId       ",
	"OUTSITEID         :OutSiteId         ",
	"USERDEF01         :UserDef01         ",
	"DEFECTQTY         :DefectQty         ",
	"ETLMODIFYDATE     :EtlModifyDate     ",
	"ALTERETLMODIFYDATE:AlterEtlModifyDate",
	*/

	private String siteId            ;
	private String prodGrpName       ;
	private String prodId            ;
	private String glassCellId       ;
	private String imptStepGrpId     ;
	private String imptEqpId         ;
	private String procHour          ;
	private String cellId            ;
	private String glassId           ;
	private String inspStepType      ;
	private String decGradeCd        ;
	private String inspDate          ;
	private String inspHour          ;
	private String inspEqpId         ;
	private String batchId           ;
	private String prodType          ;
	private String procId            ;
	private String inspStepId        ;
	private String binGradeCd        ;
	private String matchLotType      ;
	private String defectCd          ;
	private String workerId          ;
	private String cellLocId         ;
	private String defectSysId       ;
	private String outSiteId         ;
	private String userDef01         ;
	private String defectQty         ;
	private String etlModifyDate     ;
	private String alterEtlModifyDate;

	public String toString()
	{
		return String.format("READ : [%s] [%s] [%s] [%s] [%s] [%s] [%s] [%s] [%s] [%s] [%s] [%s] [%s] [%s] [%s] [%s] [%s] [%s] [%s] [%s] [%s] [%s] [%s] [%s] [%s] [%s] [%s] [%s] [%s]"
			, siteId            
			, prodGrpName       
			, prodId            
			, glassCellId       
			, imptStepGrpId     
			, imptEqpId         
			, procHour          
			, cellId            
			, glassId           
			, inspStepType      
			, decGradeCd        
			, inspDate          
			, inspHour          
			, inspEqpId         
			, batchId           
			, prodType          
			, procId            
			, inspStepId        
			, binGradeCd        
			, matchLotType      
			, defectCd          
			, workerId          
			, cellLocId         
			, defectSysId       
			, outSiteId         
			, userDef01         
			, defectQty         
			, etlModifyDate     
			, alterEtlModifyDate
		);
	}

	public static String[] getHeader()
	{
		List<String> list = new ArrayList<String>();
		list.add("SITEID"            );
		list.add("PRODGRPNAME"       );
		list.add("PRODID"            );
		list.add("GLASSCELLID"       );
		list.add("IMPTSTEPGRPID"     );
		list.add("IMPTEQPID"         );
		list.add("PROCHOUR"          );
		list.add("CELLID"            );
		list.add("GLASSID"           );
		list.add("INSPSTEPTYPE"      );
		list.add("DECGRADECD"        );
		list.add("INSPDATE"          );
		list.add("INSPHOUR"          );
		list.add("INSPEQPID"         );
		list.add("BATCHID"           );
		list.add("PRODTYPE"          );
		list.add("PROCID"            );
		list.add("INSPSTEPID"        );
		list.add("BINGRADECD"        );
		list.add("MATCHLOTTYPE"      );
		list.add("DEFECTCD"          );
		list.add("WORKERID"          );
		list.add("CELLLOCID"         );
		list.add("DEFECTSYSID"       );
		list.add("OUTSITEID"         );
		list.add("USERDEF01"         );
		list.add("DEFECTQTY"         );
		list.add("ETLMODIFYDATE"     );
		list.add("ALTERETLMODIFYDATE");

		return list.toArray(new String[list.size()]);
	}

	public String[] getStringArray()
	{
		List<String> list = new ArrayList<String>();
		list.add(siteId            );
		list.add(prodGrpName       );
		list.add(prodId            );
		list.add(glassCellId       );
		list.add(imptStepGrpId     );
		list.add(imptEqpId         );
		list.add(procHour          );
		list.add(cellId            );
		list.add(glassId           );
		list.add(inspStepType      );
		list.add(decGradeCd        );
		list.add(inspDate          );
		list.add(inspHour          );
		list.add(inspEqpId         );
		list.add(batchId           );
		list.add(prodType          );
		list.add(procId            );
		list.add(inspStepId        );
		list.add(binGradeCd        );
		list.add(matchLotType      );
		list.add(defectCd          );
		list.add(workerId          );
		list.add(cellLocId         );
		list.add(defectSysId       );
		list.add(outSiteId         );
		list.add(userDef01         );
		list.add(defectQty         );
		list.add(etlModifyDate     );
		list.add(alterEtlModifyDate);

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
	 * @return the prodId
	 */
	public String getProdId()
	{
		return prodId;
	}

	/**
	 * @return the glassCellId
	 */
	public String getGlassCellId()
	{
		return glassCellId;
	}

	/**
	 * @return the imptStepGrpId
	 */
	public String getImptStepGrpId()
	{
		return imptStepGrpId;
	}

	/**
	 * @return the imptEqpId
	 */
	public String getImptEqpId()
	{
		return imptEqpId;
	}

	/**
	 * @return the procHour
	 */
	public String getProcHour()
	{
		return procHour;
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
	 * @return the inspStepType
	 */
	public String getInspStepType()
	{
		return inspStepType;
	}

	/**
	 * @return the decGradeCd
	 */
	public String getDecGradeCd()
	{
		return decGradeCd;
	}

	/**
	 * @return the inspDate
	 */
	public String getInspDate()
	{
		return inspDate;
	}

	/**
	 * @return the inspHour
	 */
	public String getInspHour()
	{
		return inspHour;
	}

	/**
	 * @return the inspEqpId
	 */
	public String getInspEqpId()
	{
		return inspEqpId;
	}

	/**
	 * @return the batchId
	 */
	public String getBatchId()
	{
		return batchId;
	}

	/**
	 * @return the prodType
	 */
	public String getProdType()
	{
		return prodType;
	}

	/**
	 * @return the procId
	 */
	public String getProcId()
	{
		return procId;
	}

	/**
	 * @return the inspStepId
	 */
	public String getInspStepId()
	{
		return inspStepId;
	}

	/**
	 * @return the binGradeCd
	 */
	public String getBinGradeCd()
	{
		return binGradeCd;
	}

	/**
	 * @return the matchLotType
	 */
	public String getMatchLotType()
	{
		return matchLotType;
	}

	/**
	 * @return the defectCd
	 */
	public String getDefectCd()
	{
		return defectCd;
	}

	/**
	 * @return the workerId
	 */
	public String getWorkerId()
	{
		return workerId;
	}

	/**
	 * @return the cellLocId
	 */
	public String getCellLocId()
	{
		return cellLocId;
	}

	/**
	 * @return the defectSysId
	 */
	public String getDefectSysId()
	{
		return defectSysId;
	}

	/**
	 * @return the outSiteId
	 */
	public String getOutSiteId()
	{
		return outSiteId;
	}

	/**
	 * @return the userDef01
	 */
	public String getUserDef01()
	{
		return userDef01;
	}

	/**
	 * @return the defectQty
	 */
	public String getDefectQty()
	{
		return defectQty;
	}

	/**
	 * @return the etlModifyDate
	 */
	public String getEtlModifyDate()
	{
		return etlModifyDate;
	}

	/**
	 * @return the alterEtlModifyDate
	 */
	public String getAlterEtlModifyDate()
	{
		return alterEtlModifyDate;
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
	 * @param prodId the prodId to set
	 */
	public void setProdId(String prodId)
	{
		this.prodId = prodId;
	}

	/**
	 * @param glassCellId the glassCellId to set
	 */
	public void setGlassCellId(String glassCellId)
	{
		this.glassCellId = glassCellId;
	}

	/**
	 * @param imptStepGrpId the imptStepGrpId to set
	 */
	public void setImptStepGrpId(String imptStepGrpId)
	{
		this.imptStepGrpId = imptStepGrpId;
	}

	/**
	 * @param imptEqpId the imptEqpId to set
	 */
	public void setImptEqpId(String imptEqpId)
	{
		this.imptEqpId = imptEqpId;
	}

	/**
	 * @param procHour the procHour to set
	 */
	public void setProcHour(String procHour)
	{
		this.procHour = procHour;
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
	 * @param inspStepType the inspStepType to set
	 */
	public void setInspStepType(String inspStepType)
	{
		this.inspStepType = inspStepType;
	}

	/**
	 * @param decGradeCd the decGradeCd to set
	 */
	public void setDecGradeCd(String decGradeCd)
	{
		this.decGradeCd = decGradeCd;
	}

	/**
	 * @param inspDate the inspDate to set
	 */
	public void setInspDate(String inspDate)
	{
		this.inspDate = inspDate;
	}

	/**
	 * @param inspHour the inspHour to set
	 */
	public void setInspHour(String inspHour)
	{
		this.inspHour = inspHour;
	}

	/**
	 * @param inspEqpId the inspEqpId to set
	 */
	public void setInspEqpId(String inspEqpId)
	{
		this.inspEqpId = inspEqpId;
	}

	/**
	 * @param batchId the batchId to set
	 */
	public void setBatchId(String batchId)
	{
		this.batchId = batchId;
	}

	/**
	 * @param prodType the prodType to set
	 */
	public void setProdType(String prodType)
	{
		this.prodType = prodType;
	}

	/**
	 * @param procId the procId to set
	 */
	public void setProcId(String procId)
	{
		this.procId = procId;
	}

	/**
	 * @param inspStepId the inspStepId to set
	 */
	public void setInspStepId(String inspStepId)
	{
		this.inspStepId = inspStepId;
	}

	/**
	 * @param binGradeCd the binGradeCd to set
	 */
	public void setBinGradeCd(String binGradeCd)
	{
		this.binGradeCd = binGradeCd;
	}

	/**
	 * @param matchLotType the matchLotType to set
	 */
	public void setMatchLotType(String matchLotType)
	{
		this.matchLotType = matchLotType;
	}

	/**
	 * @param defectCd the defectCd to set
	 */
	public void setDefectCd(String defectCd)
	{
		this.defectCd = defectCd;
	}

	/**
	 * @param workerId the workerId to set
	 */
	public void setWorkerId(String workerId)
	{
		this.workerId = workerId;
	}

	/**
	 * @param cellLocId the cellLocId to set
	 */
	public void setCellLocId(String cellLocId)
	{
		this.cellLocId = cellLocId;
	}

	/**
	 * @param defectSysId the defectSysId to set
	 */
	public void setDefectSysId(String defectSysId)
	{
		this.defectSysId = defectSysId;
	}

	/**
	 * @param outSiteId the outSiteId to set
	 */
	public void setOutSiteId(String outSiteId)
	{
		this.outSiteId = outSiteId;
	}

	/**
	 * @param userDef01 the userDef01 to set
	 */
	public void setUserDef01(String userDef01)
	{
		this.userDef01 = userDef01;
	}

	/**
	 * @param defectQty the defectQty to set
	 */
	public void setDefectQty(String defectQty)
	{
		this.defectQty = defectQty;
	}

	/**
	 * @param etlModifyDate the etlModifyDate to set
	 */
	public void setEtlModifyDate(String etlModifyDate)
	{
		this.etlModifyDate = etlModifyDate;
	}

	/**
	 * @param alterEtlModifyDate the alterEtlModifyDate to set
	 */
	public void setAlterEtlModifyDate(String alterEtlModifyDate)
	{
		this.alterEtlModifyDate = alterEtlModifyDate;
	}
}
