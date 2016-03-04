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

package sdc.anal.lya.macro.A11.INCHCELL_LOC.v02;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;

import kiea.proj.sdc.anal.macro.impl.bean.AbstractBean;

/**
 * @author KangSeok
 * @date 2014. 8. 8.
 * @file_name AIB_INCHCELL_LOC_ReadBean.java
 *
 */
public class INCHCELL_LOC_ReadBean extends AbstractBean
{
	/*
	"SITEID     :SiteId     ",
	"CELLLOCID  :CellLocId  ",
	"PRODGRPNAME:ProdGrpName",
	"INCH       :Inch       ",
	"AREAID     :AreaId     ",
	"POINTX     :PointX     ",
	"POINTY     :PointY     ",
	"COLIDX     :ColIdx     ",
	"ROWIDX     :RowIdx     ",
	"WIDTH      :Width      ",
	"HEIGHT     :Height     ",
	*/

	private String siteId     ;
	private String cellLocId  ;
	private String prodGrpName;
	private String inch       ;
	private String areaId     ;
	private String pointX     ;
	private String pointY     ;
	private String colIdx     ;
	private String rowIdx     ;
	private String width      ;
	private String height     ;

	///////////////////////////////////////////////////////////////////////////

	private byte[] bytes = null;
	private String title = null;
	private String str   = null;

	public void setBytes(byte[] bytes)
	{
		ByteBuffer buf = ByteBuffer.allocateDirect(10 * 1024);

		buf.clear();
		if (siteId      == null) buf.put((byte)','); else buf.put((byte)'"').put(siteId     .getBytes()).put((byte)'"').put((byte)',');
		if (cellLocId   == null) buf.put((byte)','); else buf.put((byte)'"').put(cellLocId  .getBytes()).put((byte)'"').put((byte)',');
		if (prodGrpName == null) buf.put((byte)','); else buf.put((byte)'"').put(prodGrpName.getBytes()).put((byte)'"').put((byte)',');
		if (inch        == null) buf.put((byte)','); else buf.put((byte)'"').put(inch       .getBytes()).put((byte)'"').put((byte)',');
		if (areaId      == null) buf.put((byte)','); else buf.put((byte)'"').put(areaId     .getBytes()).put((byte)'"').put((byte)',');
		if (pointX      == null) buf.put((byte)','); else buf.put((byte)'"').put(pointX     .getBytes()).put((byte)'"').put((byte)',');
		if (pointY      == null) buf.put((byte)','); else buf.put((byte)'"').put(pointY     .getBytes()).put((byte)'"').put((byte)',');
		if (colIdx      == null) buf.put((byte)','); else buf.put((byte)'"').put(colIdx     .getBytes()).put((byte)'"').put((byte)',');
		if (rowIdx      == null) buf.put((byte)','); else buf.put((byte)'"').put(rowIdx     .getBytes()).put((byte)'"').put((byte)',');
		if (width       == null) buf.put((byte)','); else buf.put((byte)'"').put(width      .getBytes()).put((byte)'"').put((byte)',');
		if (height      == null) buf.put((byte)','); else buf.put((byte)'"').put(height     .getBytes()).put((byte)'"').put((byte)'\n');

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
		buf.put((byte)'"').put("SITEID"     .getBytes()).put((byte)'"').put((byte)',');
		buf.put((byte)'"').put("CELLLOCID"  .getBytes()).put((byte)'"').put((byte)',');
		buf.put((byte)'"').put("PRODGRPNAME".getBytes()).put((byte)'"').put((byte)',');
		buf.put((byte)'"').put("INCH"       .getBytes()).put((byte)'"').put((byte)',');
		buf.put((byte)'"').put("AREAID"     .getBytes()).put((byte)'"').put((byte)',');
		buf.put((byte)'"').put("POINTX"     .getBytes()).put((byte)'"').put((byte)',');
		buf.put((byte)'"').put("POINTY"     .getBytes()).put((byte)'"').put((byte)',');
		buf.put((byte)'"').put("COLIDX"     .getBytes()).put((byte)'"').put((byte)',');
		buf.put((byte)'"').put("ROWIDX"     .getBytes()).put((byte)'"').put((byte)',');
		buf.put((byte)'"').put("WIDTH"      .getBytes()).put((byte)'"').put((byte)',');
		buf.put((byte)'"').put("HEIGHT"     .getBytes()).put((byte)'"').put((byte)'\n');

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
		return String.format("READ : [%s] [%s] [%s] [%s] [%s] [%s] [%s] [%s] [%s] [%s] [%s]"
			, siteId     
			, cellLocId  
			, prodGrpName
			, inch       
			, areaId     
			, pointX     
			, pointY     
			, colIdx     
			, rowIdx     
			, width      
			, height     
		);
	}

	public static String[] getHeader()
	{
		List<String> list = new ArrayList<String>();
		list.add("SITEID"     );
		list.add("CELLLOCID"  );
		list.add("PRODGRPNAME");
		list.add("INCH"       );
		list.add("AREAID"     );
		list.add("POINTX"     );
		list.add("POINTY"     );
		list.add("COLIDX"     );
		list.add("ROWIDX"     );
		list.add("WIDTH"      );
		list.add("HEIGHT"     );

		return list.toArray(new String[list.size()]);
	}

	public String[] getStringArray()
	{
		List<String> list = new ArrayList<String>();
		list.add(siteId     );
		list.add(cellLocId  );
		list.add(prodGrpName);
		list.add(inch       );
		list.add(areaId     );
		list.add(pointX     );
		list.add(pointY     );
		list.add(colIdx     );
		list.add(rowIdx     );
		list.add(width      );
		list.add(height     );

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

	public String getCellLocId()
	{
		return cellLocId;
	}

	public void setCellLocId(String cellLocId)
	{
		this.cellLocId = cellLocId;
	}

	public String getProdGrpName()
	{
		return prodGrpName;
	}

	public void setProdGrpName(String prodGrpName)
	{
		this.prodGrpName = prodGrpName;
	}

	public String getInch()
	{
		return inch;
	}

	public void setInch(String inch)
	{
		this.inch = inch;
	}

	public String getAreaId()
	{
		return areaId;
	}

	public void setAreaId(String areaId)
	{
		this.areaId = areaId;
	}

	public String getPointX()
	{
		return pointX;
	}

	public void setPointX(String pointX)
	{
		this.pointX = pointX;
	}

	public String getPointY()
	{
		return pointY;
	}

	public void setPointY(String pointY)
	{
		this.pointY = pointY;
	}

	public String getColIdx()
	{
		return colIdx;
	}

	public void setColIdx(String colIdx)
	{
		this.colIdx = colIdx;
	}

	public String getRowIdx()
	{
		return rowIdx;
	}

	public void setRowIdx(String rowIdx)
	{
		this.rowIdx = rowIdx;
	}

	public String getWidth()
	{
		return width;
	}

	public void setWidth(String width)
	{
		this.width = width;
	}

	public String getHeight()
	{
		return height;
	}

	public void setHeight(String height)
	{
		this.height = height;
	}
}
