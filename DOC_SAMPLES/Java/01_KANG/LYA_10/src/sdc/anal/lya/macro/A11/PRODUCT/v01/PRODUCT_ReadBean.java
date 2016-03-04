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

package sdc.anal.lya.macro.A11.PRODUCT.v01;

import java.util.ArrayList;
import java.util.List;

import kiea.proj.sdc.anal.macro.impl.bean.AbstractBean;

/**
 * @author KangSeok
 * @date 2014. 8. 8.
 * @file_name AIB_PRODUCT_ReadBean.java
 *
 */
public class PRODUCT_ReadBean extends AbstractBean
{
	/*
	"PRODID     :ProdId     ",
	"PROCID     :ProcId     ",
	"PRODDESC   :ProdDesc   ",
	"PRODGRPNAME:ProdGrpName",
	"REPPRODID  :RepProdId  ",
	"CELLQTY    :CellQty    ",
	"BINGRADE   :BinGrade   ",
	"CUSTNAME   :CustName   ",
	"SITEID     :SiteId     ",
	"INCH       :Inch       ",
	"PRODTYPE   :ProdType   ",
	"USETYPE    :UseType    ",
	"SUMYN      :SumYn      ",
	"LCCODE     :LcCode     ",
	"PRODMNGCD  :ProdMngCd  ",
	*/

	private String prodId     ;
	private String procId     ;
	private String prodDesc   ;
	private String prodGrpName;
	private String repProdId  ;
	private String cellQty    ;
	private String binGrade   ;
	private String custName   ;
	private String siteId     ;
	private String inch       ;
	private String prodType   ;
	private String useType    ;
	private String sumYn      ;
	private String lcCode     ;
	private String prodMngCd  ;

	public String toString()
	{
		return String.format("READ : [%s] [%s] [%s] [%s] [%s] [%s] [%s] [%s] [%s] [%s] [%s] [%s] [%s] [%s] [%s]"
			, prodId     
			, procId     
			, prodDesc   
			, prodGrpName
			, repProdId  
			, cellQty    
			, binGrade   
			, custName   
			, siteId     
			, inch       
			, prodType   
			, useType    
			, sumYn      
			, lcCode     
			, prodMngCd  
		);
	}

	public static String[] getHeader()
	{
		List<String> list = new ArrayList<String>();
		list.add("PRODID"     );
		list.add("PROCID"     );
		list.add("PRODDESC"   );
		list.add("PRODGRPNAME");
		list.add("REPPRODID"  );
		list.add("CELLQTY"    );
		list.add("BINGRADE"   );
		list.add("CUSTNAME"   );
		list.add("SITEID"     );
		list.add("INCH"       );
		list.add("PRODTYPE"   );
		list.add("USETYPE"    );
		list.add("SUMYN"      );
		list.add("LCCODE"     );
		list.add("PRODMNGCD"  );

		return list.toArray(new String[list.size()]);
	}

	public String[] getStringArray()
	{
		List<String> list = new ArrayList<String>();
		list.add(prodId     );
		list.add(procId     );
		list.add(prodDesc   );
		list.add(prodGrpName);
		list.add(repProdId  );
		list.add(cellQty    );
		list.add(binGrade   );
		list.add(custName   );
		list.add(siteId     );
		list.add(inch       );
		list.add(prodType   );
		list.add(useType    );
		list.add(sumYn      );
		list.add(lcCode     );
		list.add(prodMngCd  );

		return list.toArray(new String[list.size()]);
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
	 * @return the prodDesc
	 */
	public String getProdDesc()
	{
		return prodDesc;
	}

	/**
	 * @return the prodGrpName
	 */
	public String getProdGrpName()
	{
		return prodGrpName;
	}

	/**
	 * @return the repProdId
	 */
	public String getRepProdId()
	{
		return repProdId;
	}

	/**
	 * @return the cellQty
	 */
	public String getCellQty()
	{
		return cellQty;
	}

	/**
	 * @return the binGrade
	 */
	public String getBinGrade()
	{
		return binGrade;
	}

	/**
	 * @return the custName
	 */
	public String getCustName()
	{
		return custName;
	}

	/**
	 * @return the siteId
	 */
	public String getSiteId()
	{
		return siteId;
	}

	/**
	 * @return the inch
	 */
	public String getInch()
	{
		return inch;
	}

	/**
	 * @return the prodType
	 */
	public String getProdType()
	{
		return prodType;
	}

	/**
	 * @return the useType
	 */
	public String getUseType()
	{
		return useType;
	}

	/**
	 * @return the sumYn
	 */
	public String getSumYn()
	{
		return sumYn;
	}

	/**
	 * @return the lcCode
	 */
	public String getLcCode()
	{
		return lcCode;
	}

	/**
	 * @return the prodMngCd
	 */
	public String getProdMngCd()
	{
		return prodMngCd;
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
	 * @param prodDesc the prodDesc to set
	 */
	public void setProdDesc(String prodDesc)
	{
		this.prodDesc = prodDesc;
	}

	/**
	 * @param prodGrpName the prodGrpName to set
	 */
	public void setProdGrpName(String prodGrpName)
	{
		this.prodGrpName = prodGrpName;
	}

	/**
	 * @param repProdId the repProdId to set
	 */
	public void setRepProdId(String repProdId)
	{
		this.repProdId = repProdId;
	}

	/**
	 * @param cellQty the cellQty to set
	 */
	public void setCellQty(String cellQty)
	{
		this.cellQty = cellQty;
	}

	/**
	 * @param binGrade the binGrade to set
	 */
	public void setBinGrade(String binGrade)
	{
		this.binGrade = binGrade;
	}

	/**
	 * @param custName the custName to set
	 */
	public void setCustName(String custName)
	{
		this.custName = custName;
	}

	/**
	 * @param siteId the siteId to set
	 */
	public void setSiteId(String siteId)
	{
		this.siteId = siteId;
	}

	/**
	 * @param inch the inch to set
	 */
	public void setInch(String inch)
	{
		this.inch = inch;
	}

	/**
	 * @param prodType the prodType to set
	 */
	public void setProdType(String prodType)
	{
		this.prodType = prodType;
	}

	/**
	 * @param useType the useType to set
	 */
	public void setUseType(String useType)
	{
		this.useType = useType;
	}

	/**
	 * @param sumYn the sumYn to set
	 */
	public void setSumYn(String sumYn)
	{
		this.sumYn = sumYn;
	}

	/**
	 * @param lcCode the lcCode to set
	 */
	public void setLcCode(String lcCode)
	{
		this.lcCode = lcCode;
	}

	/**
	 * @param prodMngCd the prodMngCd to set
	 */
	public void setProdMngCd(String prodMngCd)
	{
		this.prodMngCd = prodMngCd;
	}
}
