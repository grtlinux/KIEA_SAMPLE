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

package sdc.anal.lya.macro.A11.PRODUCT.v03;

import java.nio.ByteBuffer;
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

	///////////////////////////////////////////////////////////////////////////

	private byte[] bytes = null;
	private String title = null;
	private String str   = null;

	public void setBytes(byte[] bytes)
	{
		ByteBuffer buf = ByteBuffer.allocateDirect(10 * 1024);

		buf.clear();
		if (prodId      == null) buf.put((byte)','); else buf.put((byte)'"').put(prodId     .getBytes()).put((byte)'"').put((byte)',');
		if (procId      == null) buf.put((byte)','); else buf.put((byte)'"').put(procId     .getBytes()).put((byte)'"').put((byte)',');
		if (prodDesc    == null) buf.put((byte)','); else buf.put((byte)'"').put(prodDesc   .getBytes()).put((byte)'"').put((byte)',');
		if (prodGrpName == null) buf.put((byte)','); else buf.put((byte)'"').put(prodGrpName.getBytes()).put((byte)'"').put((byte)',');
		if (repProdId   == null) buf.put((byte)','); else buf.put((byte)'"').put(repProdId  .getBytes()).put((byte)'"').put((byte)',');
		if (cellQty     == null) buf.put((byte)','); else buf.put((byte)'"').put(cellQty    .getBytes()).put((byte)'"').put((byte)',');
		if (binGrade    == null) buf.put((byte)','); else buf.put((byte)'"').put(binGrade   .getBytes()).put((byte)'"').put((byte)',');
		if (custName    == null) buf.put((byte)','); else buf.put((byte)'"').put(custName   .getBytes()).put((byte)'"').put((byte)',');
		if (siteId      == null) buf.put((byte)','); else buf.put((byte)'"').put(siteId     .getBytes()).put((byte)'"').put((byte)',');
		if (inch        == null) buf.put((byte)','); else buf.put((byte)'"').put(inch       .getBytes()).put((byte)'"').put((byte)',');
		if (prodType    == null) buf.put((byte)','); else buf.put((byte)'"').put(prodType   .getBytes()).put((byte)'"').put((byte)',');
		if (useType     == null) buf.put((byte)','); else buf.put((byte)'"').put(useType    .getBytes()).put((byte)'"').put((byte)',');
		if (sumYn       == null) buf.put((byte)','); else buf.put((byte)'"').put(sumYn      .getBytes()).put((byte)'"').put((byte)',');
		if (lcCode      == null) buf.put((byte)','); else buf.put((byte)'"').put(lcCode     .getBytes()).put((byte)'"').put((byte)',');
		if (prodMngCd   == null) buf.put((byte)','); else buf.put((byte)'"').put(prodMngCd  .getBytes()).put((byte)'"').put((byte)'\n');

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
		buf.put((byte)'"').put("PRODID"     .getBytes()).put((byte)'"').put((byte)',');
		buf.put((byte)'"').put("PROCID"     .getBytes()).put((byte)'"').put((byte)',');
		buf.put((byte)'"').put("PRODDESC"   .getBytes()).put((byte)'"').put((byte)',');
		buf.put((byte)'"').put("PRODGRPNAME".getBytes()).put((byte)'"').put((byte)',');
		buf.put((byte)'"').put("REPPRODID"  .getBytes()).put((byte)'"').put((byte)',');
		buf.put((byte)'"').put("CELLQTY"    .getBytes()).put((byte)'"').put((byte)',');
		buf.put((byte)'"').put("BINGRADE"   .getBytes()).put((byte)'"').put((byte)',');
		buf.put((byte)'"').put("CUSTNAME"   .getBytes()).put((byte)'"').put((byte)',');
		buf.put((byte)'"').put("SITEID"     .getBytes()).put((byte)'"').put((byte)',');
		buf.put((byte)'"').put("INCH"       .getBytes()).put((byte)'"').put((byte)',');
		buf.put((byte)'"').put("PRODTYPE"   .getBytes()).put((byte)'"').put((byte)',');
		buf.put((byte)'"').put("USETYPE"    .getBytes()).put((byte)'"').put((byte)',');
		buf.put((byte)'"').put("SUMYN"      .getBytes()).put((byte)'"').put((byte)',');
		buf.put((byte)'"').put("LCCODE"     .getBytes()).put((byte)'"').put((byte)',');
		buf.put((byte)'"').put("PRODMNGCD"  .getBytes()).put((byte)'"').put((byte)'\n');

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

	public String getProdDesc()
	{
		return prodDesc;
	}

	public void setProdDesc(String prodDesc)
	{
		this.prodDesc = prodDesc;
	}

	public String getProdGrpName()
	{
		return prodGrpName;
	}

	public void setProdGrpName(String prodGrpName)
	{
		this.prodGrpName = prodGrpName;
	}

	public String getRepProdId()
	{
		return repProdId;
	}

	public void setRepProdId(String repProdId)
	{
		this.repProdId = repProdId;
	}

	public String getCellQty()
	{
		return cellQty;
	}

	public void setCellQty(String cellQty)
	{
		this.cellQty = cellQty;
	}

	public String getBinGrade()
	{
		return binGrade;
	}

	public void setBinGrade(String binGrade)
	{
		this.binGrade = binGrade;
	}

	public String getCustName()
	{
		return custName;
	}

	public void setCustName(String custName)
	{
		this.custName = custName;
	}

	public String getSiteId()
	{
		return siteId;
	}

	public void setSiteId(String siteId)
	{
		this.siteId = siteId;
	}

	public String getInch()
	{
		return inch;
	}

	public void setInch(String inch)
	{
		this.inch = inch;
	}

	public String getProdType()
	{
		return prodType;
	}

	public void setProdType(String prodType)
	{
		this.prodType = prodType;
	}

	public String getUseType()
	{
		return useType;
	}

	public void setUseType(String useType)
	{
		this.useType = useType;
	}

	public String getSumYn()
	{
		return sumYn;
	}

	public void setSumYn(String sumYn)
	{
		this.sumYn = sumYn;
	}

	public String getLcCode()
	{
		return lcCode;
	}

	public void setLcCode(String lcCode)
	{
		this.lcCode = lcCode;
	}

	public String getProdMngCd()
	{
		return prodMngCd;
	}

	public void setProdMngCd(String prodMngCd)
	{
		this.prodMngCd = prodMngCd;
	}
}
