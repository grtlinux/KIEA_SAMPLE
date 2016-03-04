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

package sdc.anal.lya.macro.A11.H_INSPDEFECT.v04;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;

import kiea.proj.sdc.anal.macro.impl.bean.AbstractBean;

/**
 * @author KangSeok
 * @date 2014. 8. 8.
 * @file_name AIB_H_INSPDEFECT_ReadBean.java
 *
 */
public class H_INSPDEFECT_ReadBean extends AbstractBean
{
	/*
	"CELLID            :CellId            ",
	"GLASSID           :GlassId           ",
	"SITEID            :SiteId            ",
	"INSPSTEPTYPE      :InspStepType      ",
	"DECGRADECD        :DecGradeCd        ",
	"INSPDATE          :InspDate          ",
	"INSPHOUR          :InspHour          ",
	"INSPEQPID         :InspEqpId         ",
	"BATCHID           :BatchId           ",
	"PRODTYPE          :ProdType          ",
	"PRODID            :ProdId            ",
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

	private String cellId            ;
	private String glassId           ;
	private String siteId            ;
	private String inspStepType      ;
	private String decGradeCd        ;
	private String inspDate          ;
	private String inspHour          ;
	private String inspEqpId         ;
	private String batchId           ;
	private String prodType          ;
	private String prodId            ;
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

	///////////////////////////////////////////////////////////////////////////

	private byte[] bytes = null;
	private String title = null;
	private String str   = null;

	public void setBytes(byte[] bytes)
	{
		ByteBuffer buf = ByteBuffer.allocateDirect(10 * 1024);

		buf.clear();
		if (cellId             == null) buf.put((byte)','); else buf.put((byte)'"').put(cellId            .getBytes()).put((byte)'"').put((byte)',');
		if (glassId            == null) buf.put((byte)','); else buf.put((byte)'"').put(glassId           .getBytes()).put((byte)'"').put((byte)',');
		if (siteId             == null) buf.put((byte)','); else buf.put((byte)'"').put(siteId            .getBytes()).put((byte)'"').put((byte)',');
		if (inspStepType       == null) buf.put((byte)','); else buf.put((byte)'"').put(inspStepType      .getBytes()).put((byte)'"').put((byte)',');
		if (decGradeCd         == null) buf.put((byte)','); else buf.put((byte)'"').put(decGradeCd        .getBytes()).put((byte)'"').put((byte)',');
		if (inspDate           == null) buf.put((byte)','); else buf.put((byte)'"').put(inspDate          .getBytes()).put((byte)'"').put((byte)',');
		if (inspHour           == null) buf.put((byte)','); else buf.put((byte)'"').put(inspHour          .getBytes()).put((byte)'"').put((byte)',');
		if (inspEqpId          == null) buf.put((byte)','); else buf.put((byte)'"').put(inspEqpId         .getBytes()).put((byte)'"').put((byte)',');
		if (batchId            == null) buf.put((byte)','); else buf.put((byte)'"').put(batchId           .getBytes()).put((byte)'"').put((byte)',');
		if (prodType           == null) buf.put((byte)','); else buf.put((byte)'"').put(prodType          .getBytes()).put((byte)'"').put((byte)',');
		if (prodId             == null) buf.put((byte)','); else buf.put((byte)'"').put(prodId            .getBytes()).put((byte)'"').put((byte)',');
		if (procId             == null) buf.put((byte)','); else buf.put((byte)'"').put(procId            .getBytes()).put((byte)'"').put((byte)',');
		if (inspStepId         == null) buf.put((byte)','); else buf.put((byte)'"').put(inspStepId        .getBytes()).put((byte)'"').put((byte)',');
		if (binGradeCd         == null) buf.put((byte)','); else buf.put((byte)'"').put(binGradeCd        .getBytes()).put((byte)'"').put((byte)',');
		if (matchLotType       == null) buf.put((byte)','); else buf.put((byte)'"').put(matchLotType      .getBytes()).put((byte)'"').put((byte)',');
		if (defectCd           == null) buf.put((byte)','); else buf.put((byte)'"').put(defectCd          .getBytes()).put((byte)'"').put((byte)',');
		if (workerId           == null) buf.put((byte)','); else buf.put((byte)'"').put(workerId          .getBytes()).put((byte)'"').put((byte)',');
		if (cellLocId          == null) buf.put((byte)','); else buf.put((byte)'"').put(cellLocId         .getBytes()).put((byte)'"').put((byte)',');
		if (defectSysId        == null) buf.put((byte)','); else buf.put((byte)'"').put(defectSysId       .getBytes()).put((byte)'"').put((byte)',');
		if (outSiteId          == null) buf.put((byte)','); else buf.put((byte)'"').put(outSiteId         .getBytes()).put((byte)'"').put((byte)',');
		if (userDef01          == null) buf.put((byte)','); else buf.put((byte)'"').put(userDef01         .getBytes()).put((byte)'"').put((byte)',');
		if (defectQty          == null) buf.put((byte)','); else buf.put((byte)'"').put(defectQty         .getBytes()).put((byte)'"').put((byte)',');
		if (etlModifyDate      == null) buf.put((byte)','); else buf.put((byte)'"').put(etlModifyDate     .getBytes()).put((byte)'"').put((byte)',');
		if (alterEtlModifyDate == null) buf.put((byte)','); else buf.put((byte)'"').put(alterEtlModifyDate.getBytes()).put((byte)'"').put((byte)'\n');

		buf.flip();
		this.bytes = new byte[buf.limit()];
		buf.get(this.bytes);
	}

	public byte[] getBytes()
	{
		if (this.bytes == null)
			setBytes(null);

		return this.bytes;
	}

	public void setTitle(String title)
	{
		ByteBuffer buf = ByteBuffer.allocateDirect(1024);

		buf.clear();
		buf.put((byte)'"').put("CELLID"            .getBytes()).put((byte)'"').put((byte)',');
		buf.put((byte)'"').put("GLASSID"           .getBytes()).put((byte)'"').put((byte)',');
		buf.put((byte)'"').put("SITEID"            .getBytes()).put((byte)'"').put((byte)',');
		buf.put((byte)'"').put("INSPSTEPTYPE"      .getBytes()).put((byte)'"').put((byte)',');
		buf.put((byte)'"').put("DECGRADECD"        .getBytes()).put((byte)'"').put((byte)',');
		buf.put((byte)'"').put("INSPDATE"          .getBytes()).put((byte)'"').put((byte)',');
		buf.put((byte)'"').put("INSPHOUR"          .getBytes()).put((byte)'"').put((byte)',');
		buf.put((byte)'"').put("INSPEQPID"         .getBytes()).put((byte)'"').put((byte)',');
		buf.put((byte)'"').put("BATCHID"           .getBytes()).put((byte)'"').put((byte)',');
		buf.put((byte)'"').put("PRODTYPE"          .getBytes()).put((byte)'"').put((byte)',');
		buf.put((byte)'"').put("PRODID"            .getBytes()).put((byte)'"').put((byte)',');
		buf.put((byte)'"').put("PROCID"            .getBytes()).put((byte)'"').put((byte)',');
		buf.put((byte)'"').put("INSPSTEPID"        .getBytes()).put((byte)'"').put((byte)',');
		buf.put((byte)'"').put("BINGRADECD"        .getBytes()).put((byte)'"').put((byte)',');
		buf.put((byte)'"').put("MATCHLOTTYPE"      .getBytes()).put((byte)'"').put((byte)',');
		buf.put((byte)'"').put("DEFECTCD"          .getBytes()).put((byte)'"').put((byte)',');
		buf.put((byte)'"').put("WORKERID"          .getBytes()).put((byte)'"').put((byte)',');
		buf.put((byte)'"').put("CELLLOCID"         .getBytes()).put((byte)'"').put((byte)',');
		buf.put((byte)'"').put("DEFECTSYSID"       .getBytes()).put((byte)'"').put((byte)',');
		buf.put((byte)'"').put("OUTSITEID"         .getBytes()).put((byte)'"').put((byte)',');
		buf.put((byte)'"').put("USERDEF01"         .getBytes()).put((byte)'"').put((byte)',');
		buf.put((byte)'"').put("DEFECTQTY"         .getBytes()).put((byte)'"').put((byte)',');
		buf.put((byte)'"').put("ETLMODIFYDATE"     .getBytes()).put((byte)'"').put((byte)',');
		buf.put((byte)'"').put("ALTERETLMODIFYDATE".getBytes()).put((byte)'"').put((byte)'\n');

		buf.flip();
		byte[] bytes = new byte[buf.limit()];
		buf.get(bytes);

		this.title = new String(bytes);
	}

	public String getTitle()
	{
		if (this.title == null)
			setTitle(null);

		return this.title;
	}

	public void setStr(String str)
	{
		this.str = new String(getBytes());
	}

	public String getStr()
	{
		if (this.str == null)
			setStr(null);

		return this.str;
	}


	///////////////////////////////////////////////////////////////////////////

	public String toString()
	{
		return String.format("READ : [%s] [%s] [%s] [%s] [%s] [%s] [%s] [%s] [%s] [%s] [%s] [%s] [%s] [%s] [%s] [%s] [%s] [%s] [%s] [%s] [%s] [%s] [%s] [%s]"
			, cellId            
			, glassId           
			, siteId            
			, inspStepType      
			, decGradeCd        
			, inspDate          
			, inspHour          
			, inspEqpId         
			, batchId           
			, prodType          
			, prodId            
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
		list.add("CELLID"            );
		list.add("GLASSID"           );
		list.add("SITEID"            );
		list.add("INSPSTEPTYPE"      );
		list.add("DECGRADECD"        );
		list.add("INSPDATE"          );
		list.add("INSPHOUR"          );
		list.add("INSPEQPID"         );
		list.add("BATCHID"           );
		list.add("PRODTYPE"          );
		list.add("PRODID"            );
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
		list.add(cellId            );
		list.add(glassId           );
		list.add(siteId            );
		list.add(inspStepType      );
		list.add(decGradeCd        );
		list.add(inspDate          );
		list.add(inspHour          );
		list.add(inspEqpId         );
		list.add(batchId           );
		list.add(prodType          );
		list.add(prodId            );
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

	public String getCellId()
	{
		return cellId;
	}

	public void setCellId(String cellId)
	{
		this.cellId = cellId;
	}

	public String getGlassId()
	{
		return glassId;
	}

	public void setGlassId(String glassId)
	{
		this.glassId = glassId;
	}

	public String getSiteId()
	{
		return siteId;
	}

	public void setSiteId(String siteId)
	{
		this.siteId = siteId;
	}

	public String getInspStepType()
	{
		return inspStepType;
	}

	public void setInspStepType(String inspStepType)
	{
		this.inspStepType = inspStepType;
	}

	public String getDecGradeCd()
	{
		return decGradeCd;
	}

	public void setDecGradeCd(String decGradeCd)
	{
		this.decGradeCd = decGradeCd;
	}

	public String getInspDate()
	{
		return inspDate;
	}

	public void setInspDate(String inspDate)
	{
		this.inspDate = inspDate;
	}

	public String getInspHour()
	{
		return inspHour;
	}

	public void setInspHour(String inspHour)
	{
		this.inspHour = inspHour;
	}

	public String getInspEqpId()
	{
		return inspEqpId;
	}

	public void setInspEqpId(String inspEqpId)
	{
		this.inspEqpId = inspEqpId;
	}

	public String getBatchId()
	{
		return batchId;
	}

	public void setBatchId(String batchId)
	{
		this.batchId = batchId;
	}

	public String getProdType()
	{
		return prodType;
	}

	public void setProdType(String prodType)
	{
		this.prodType = prodType;
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

	public String getInspStepId()
	{
		return inspStepId;
	}

	public void setInspStepId(String inspStepId)
	{
		this.inspStepId = inspStepId;
	}

	public String getBinGradeCd()
	{
		return binGradeCd;
	}

	public void setBinGradeCd(String binGradeCd)
	{
		this.binGradeCd = binGradeCd;
	}

	public String getMatchLotType()
	{
		return matchLotType;
	}

	public void setMatchLotType(String matchLotType)
	{
		this.matchLotType = matchLotType;
	}

	public String getDefectCd()
	{
		return defectCd;
	}

	public void setDefectCd(String defectCd)
	{
		this.defectCd = defectCd;
	}

	public String getWorkerId()
	{
		return workerId;
	}

	public void setWorkerId(String workerId)
	{
		this.workerId = workerId;
	}

	public String getCellLocId()
	{
		return cellLocId;
	}

	public void setCellLocId(String cellLocId)
	{
		this.cellLocId = cellLocId;
	}

	public String getDefectSysId()
	{
		return defectSysId;
	}

	public void setDefectSysId(String defectSysId)
	{
		this.defectSysId = defectSysId;
	}

	public String getOutSiteId()
	{
		return outSiteId;
	}

	public void setOutSiteId(String outSiteId)
	{
		this.outSiteId = outSiteId;
	}

	public String getUserDef01()
	{
		return userDef01;
	}

	public void setUserDef01(String userDef01)
	{
		this.userDef01 = userDef01;
	}

	public String getDefectQty()
	{
		return defectQty;
	}

	public void setDefectQty(String defectQty)
	{
		this.defectQty = defectQty;
	}

	public String getEtlModifyDate()
	{
		return etlModifyDate;
	}

	public void setEtlModifyDate(String etlModifyDate)
	{
		this.etlModifyDate = etlModifyDate;
	}

	public String getAlterEtlModifyDate()
	{
		return alterEtlModifyDate;
	}

	public void setAlterEtlModifyDate(String alterEtlModifyDate)
	{
		this.alterEtlModifyDate = alterEtlModifyDate;
	}
}
