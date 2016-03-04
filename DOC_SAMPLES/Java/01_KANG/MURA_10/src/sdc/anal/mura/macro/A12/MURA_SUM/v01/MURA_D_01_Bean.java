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

package sdc.anal.mura.macro.A12.MURA_SUM.v01;

import java.util.ArrayList;
import java.util.List;

import kiea.proj.sdc.anal.macro.impl.bean.AbstractBean;

/**
 * @author KangSeok
 * @date 2014. 8. 8.
 * @file_name MURA_SPC_ReadBean.java
 *
 */
public class MURA_D_01_Bean extends AbstractBean
{
	/*
	"PROCID       :ProcId       ",
	"SITEID       :SiteId       ",
	"CELLID       :CellId       ",
	"GLASSID      :GlassId      ",
	"PRODGRPNAME  :ProdRrpName  ",
	"PRODID       :ProdId       ",
	"ORGSTEPID    :OrgStepId    ",
	"MEASEQPUNITID:MeasEqpUnitId",
	"DCOLDATE     :DcolDate     ",
	"ITEMID       :ItemId       ",
	"SUBITEMID    :SubItemId    ",
	"DATAVALUE    :DataValue    ",
	"GATELINE     :GateLine     ",
	"DATALINE     :DataLine     ",
	"GATELINE2    :GateLine2    ",
	"DATALINE2    :DataLine2    ",
	"PRODTYPE     :ProdType     ",
	"CELL_LOC_ID  :CellLocId    ",
	*/

	private String procId       ;
	private String siteId       ;
	private String cellId       ;
	private String glassId      ;
	private String prodRrpName  ;
	private String prodId       ;
	private String orgStepId    ;
	private String measEqpUnitId;
	private String dcolDate     ;
	private String itemId       ;
	private String subItemId    ;
	private String dataValue    ;
	private String gateLine     ;
	private String dataLine     ;
	private String gateLine2    ;
	private String dataLine2    ;
	private String prodType     ;
	private String cellLocId    ;

	public String toString()
	{
		return String.format("READ : [%s] [%s] [%s] [%s] [%s] [%s] [%s] [%s] [%s] [%s] [%s] [%s] [%s] [%s] [%s] [%s] [%s] [%s]"
			, procId       
			, siteId       
			, cellId       
			, glassId      
			, prodRrpName  
			, prodId       
			, orgStepId    
			, measEqpUnitId
			, dcolDate     
			, itemId       
			, subItemId    
			, dataValue    
			, gateLine     
			, dataLine     
			, gateLine2    
			, dataLine2    
			, prodType     
			, cellLocId    
		);
	}

	public static String[] getHeader()
	{
		List<String> list = new ArrayList<String>();
		list.add("PROCID"       );
		list.add("SITEID"       );
		list.add("CELLID"       );
		list.add("GLASSID"      );
		list.add("PRODGRPNAME"  );
		list.add("PRODID"       );
		list.add("ORGSTEPID"    );
		list.add("MEASEQPUNITID");
		list.add("DCOLDATE"     );
		list.add("ITEMID"       );
		list.add("SUBITEMID"    );
		list.add("DATAVALUE"    );
		list.add("GATELINE"     );
		list.add("DATALINE"     );
		list.add("GATELINE2"    );
		list.add("DATALINE2"    );
		list.add("PRODTYPE"     );
		list.add("CELL_LOC_ID"  );

		return list.toArray(new String[list.size()]);
	}

	public String[] getStringArray()
	{
		List<String> list = new ArrayList<String>();
		list.add(procId       );
		list.add(siteId       );
		list.add(cellId       );
		list.add(glassId      );
		list.add(prodRrpName  );
		list.add(prodId       );
		list.add(orgStepId    );
		list.add(measEqpUnitId);
		list.add(dcolDate     );
		list.add(itemId       );
		list.add(subItemId    );
		list.add(dataValue    );
		list.add(gateLine     );
		list.add(dataLine     );
		list.add(gateLine2    );
		list.add(dataLine2    );
		list.add(prodType     );
		list.add(cellLocId    );

		return list.toArray(new String[list.size()]);
	}

	/**
	 * @return the procId
	 */
	public String getProcId()
	{
		return procId;
	}

	/**
	 * @return the siteId
	 */
	public String getSiteId()
	{
		return siteId;
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
	 * @return the prodRrpName
	 */
	public String getProdRrpName()
	{
		return prodRrpName;
	}

	/**
	 * @return the prodId
	 */
	public String getProdId()
	{
		return prodId;
	}

	/**
	 * @return the orgStepId
	 */
	public String getOrgStepId()
	{
		return orgStepId;
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
	 * @return the gateLine
	 */
	public String getGateLine()
	{
		return gateLine;
	}

	/**
	 * @return the dataLine
	 */
	public String getDataLine()
	{
		return dataLine;
	}

	/**
	 * @return the gateLine2
	 */
	public String getGateLine2()
	{
		return gateLine2;
	}

	/**
	 * @return the dataLine2
	 */
	public String getDataLine2()
	{
		return dataLine2;
	}

	/**
	 * @return the prodType
	 */
	public String getProdType()
	{
		return prodType;
	}

	/**
	 * @return the cellLocId
	 */
	public String getCellLocId()
	{
		return cellLocId;
	}

	/**
	 * @param procId the procId to set
	 */
	public void setProcId(String procId)
	{
		this.procId = procId;
	}

	/**
	 * @param siteId the siteId to set
	 */
	public void setSiteId(String siteId)
	{
		this.siteId = siteId;
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
	 * @param prodRrpName the prodRrpName to set
	 */
	public void setProdRrpName(String prodRrpName)
	{
		this.prodRrpName = prodRrpName;
	}

	/**
	 * @param prodId the prodId to set
	 */
	public void setProdId(String prodId)
	{
		this.prodId = prodId;
	}

	/**
	 * @param orgStepId the orgStepId to set
	 */
	public void setOrgStepId(String orgStepId)
	{
		this.orgStepId = orgStepId;
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
	 * @param gateLine the gateLine to set
	 */
	public void setGateLine(String gateLine)
	{
		this.gateLine = gateLine;
	}

	/**
	 * @param dataLine the dataLine to set
	 */
	public void setDataLine(String dataLine)
	{
		this.dataLine = dataLine;
	}

	/**
	 * @param gateLine2 the gateLine2 to set
	 */
	public void setGateLine2(String gateLine2)
	{
		this.gateLine2 = gateLine2;
	}

	/**
	 * @param dataLine2 the dataLine2 to set
	 */
	public void setDataLine2(String dataLine2)
	{
		this.dataLine2 = dataLine2;
	}

	/**
	 * @param prodType the prodType to set
	 */
	public void setProdType(String prodType)
	{
		this.prodType = prodType;
	}

	/**
	 * @param cellLocId the cellLocId to set
	 */
	public void setCellLocId(String cellLocId)
	{
		this.cellLocId = cellLocId;
	}
}
