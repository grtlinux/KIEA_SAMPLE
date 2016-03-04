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

package sdc.anal.lya.macro.A11.CELLCONVERT.v03;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;

import kiea.proj.sdc.anal.macro.impl.bean.AbstractBean;

/**
 * @author KangSeok
 * @date 2014. 8. 8.
 * @file_name CELLCONVERT_WriteBean.java
 *
 */
public class CELLCONVERT_WriteBean extends AbstractBean
{
	/*
	"PROCID        :ProcId        ",
	"CELLLOCID     :CellLocId     ",
	"POINT_X       :PointX        ",
	"POINT_Y       :PointY        ",
	"WIDTH         :Width         ",
	"HEIGHT        :Height        ",
	"GATERESOLUTION:GateResolution",
	"DATARESOLUTION:DataResolution",
	"COLIDX        :ColIdx        ",
	"ROWIDX        :RowIdx        ",
	"PIXCEL_X      :PixcelX       ",
	"PIXCEL_Y      :PixcelY       ",
	"X_LOGIC1      :XLogic1       ",
	"Y_LOGIC1      :YLogic1       ",
	"X_LOGIC2      :XLogic2       ",
	"Y_LOGIC2      :YLogic2       ",
	"SITEID        :SiteId        ",
	"GLASS_HEIGHT  :GlassHeight   ",
	*/

	private String procId        ;
	private String cellLocId     ;
	private String pointX        ;
	private String pointY        ;
	private String width         ;
	private String height        ;
	private String gateResolution;
	private String dataResolution;
	private String colIdx        ;
	private String rowIdx        ;
	private String pixcelX       ;
	private String pixcelY       ;
	private String XLogic1       ;
	private String YLogic1       ;
	private String XLogic2       ;
	private String YLogic2       ;
	private String siteId        ;
	private String glassHeight   ;

	///////////////////////////////////////////////////////////////////////////

	private byte[] bytes = null;
	private String title = null;
	private String str   = null;

	public void setBytes(byte[] bytes)
	{
		ByteBuffer buf = ByteBuffer.allocateDirect(10 * 1024);

		buf.clear();
		if (procId         == null) buf.put((byte)','); else buf.put((byte)'"').put(procId        .getBytes()).put((byte)'"').put((byte)',');
		if (cellLocId      == null) buf.put((byte)','); else buf.put((byte)'"').put(cellLocId     .getBytes()).put((byte)'"').put((byte)',');
		if (pointX         == null) buf.put((byte)','); else buf.put((byte)'"').put(pointX        .getBytes()).put((byte)'"').put((byte)',');
		if (pointY         == null) buf.put((byte)','); else buf.put((byte)'"').put(pointY        .getBytes()).put((byte)'"').put((byte)',');
		if (width          == null) buf.put((byte)','); else buf.put((byte)'"').put(width         .getBytes()).put((byte)'"').put((byte)',');
		if (height         == null) buf.put((byte)','); else buf.put((byte)'"').put(height        .getBytes()).put((byte)'"').put((byte)',');
		if (gateResolution == null) buf.put((byte)','); else buf.put((byte)'"').put(gateResolution.getBytes()).put((byte)'"').put((byte)',');
		if (dataResolution == null) buf.put((byte)','); else buf.put((byte)'"').put(dataResolution.getBytes()).put((byte)'"').put((byte)',');
		if (colIdx         == null) buf.put((byte)','); else buf.put((byte)'"').put(colIdx        .getBytes()).put((byte)'"').put((byte)',');
		if (rowIdx         == null) buf.put((byte)','); else buf.put((byte)'"').put(rowIdx        .getBytes()).put((byte)'"').put((byte)',');
		if (pixcelX        == null) buf.put((byte)','); else buf.put((byte)'"').put(pixcelX       .getBytes()).put((byte)'"').put((byte)',');
		if (pixcelY        == null) buf.put((byte)','); else buf.put((byte)'"').put(pixcelY       .getBytes()).put((byte)'"').put((byte)',');
		if (XLogic1        == null) buf.put((byte)','); else buf.put((byte)'"').put(XLogic1       .getBytes()).put((byte)'"').put((byte)',');
		if (YLogic1        == null) buf.put((byte)','); else buf.put((byte)'"').put(YLogic1       .getBytes()).put((byte)'"').put((byte)',');
		if (XLogic2        == null) buf.put((byte)','); else buf.put((byte)'"').put(XLogic2       .getBytes()).put((byte)'"').put((byte)',');
		if (YLogic2        == null) buf.put((byte)','); else buf.put((byte)'"').put(YLogic2       .getBytes()).put((byte)'"').put((byte)',');
		if (siteId         == null) buf.put((byte)','); else buf.put((byte)'"').put(siteId        .getBytes()).put((byte)'"').put((byte)',');
		if (glassHeight    == null) buf.put((byte)'\n'); else buf.put((byte)'"').put(glassHeight   .getBytes()).put((byte)'"').put((byte)'\n');

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
		buf.put((byte)'"').put("PROCID"        .getBytes()).put((byte)'"').put((byte)',');
		buf.put((byte)'"').put("CELLLOCID"     .getBytes()).put((byte)'"').put((byte)',');
		buf.put((byte)'"').put("POINT_X"       .getBytes()).put((byte)'"').put((byte)',');
		buf.put((byte)'"').put("POINT_Y"       .getBytes()).put((byte)'"').put((byte)',');
		buf.put((byte)'"').put("WIDTH"         .getBytes()).put((byte)'"').put((byte)',');
		buf.put((byte)'"').put("HEIGHT"        .getBytes()).put((byte)'"').put((byte)',');
		buf.put((byte)'"').put("GATERESOLUTION".getBytes()).put((byte)'"').put((byte)',');
		buf.put((byte)'"').put("DATARESOLUTION".getBytes()).put((byte)'"').put((byte)',');
		buf.put((byte)'"').put("COLIDX"        .getBytes()).put((byte)'"').put((byte)',');
		buf.put((byte)'"').put("ROWIDX"        .getBytes()).put((byte)'"').put((byte)',');
		buf.put((byte)'"').put("PIXCEL_X"      .getBytes()).put((byte)'"').put((byte)',');
		buf.put((byte)'"').put("PIXCEL_Y"      .getBytes()).put((byte)'"').put((byte)',');
		buf.put((byte)'"').put("X_LOGIC1"      .getBytes()).put((byte)'"').put((byte)',');
		buf.put((byte)'"').put("Y_LOGIC1"      .getBytes()).put((byte)'"').put((byte)',');
		buf.put((byte)'"').put("X_LOGIC2"      .getBytes()).put((byte)'"').put((byte)',');
		buf.put((byte)'"').put("Y_LOGIC2"      .getBytes()).put((byte)'"').put((byte)',');
		buf.put((byte)'"').put("SITEID"        .getBytes()).put((byte)'"').put((byte)',');
		buf.put((byte)'"').put("GLASS_HEIGHT"  .getBytes()).put((byte)'"').put((byte)'\n');

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
		return String.format("READ : [%s] [%s] [%s] [%s] [%s] [%s] [%s] [%s] [%s] [%s] [%s] [%s] [%s] [%s] [%s] [%s] [%s] [%s]"
			, procId        
			, cellLocId     
			, pointX        
			, pointY        
			, width         
			, height        
			, gateResolution
			, dataResolution
			, colIdx        
			, rowIdx        
			, pixcelX       
			, pixcelY       
			, XLogic1       
			, YLogic1       
			, XLogic2       
			, YLogic2       
			, siteId        
			, glassHeight   
		);
	}

	public static String[] getHeader()
	{
		List<String> list = new ArrayList<String>();
		list.add("PROCID"        );
		list.add("CELLLOCID"     );
		list.add("POINT_X"       );
		list.add("POINT_Y"       );
		list.add("WIDTH"         );
		list.add("HEIGHT"        );
		list.add("GATERESOLUTION");
		list.add("DATARESOLUTION");
		list.add("COLIDX"        );
		list.add("ROWIDX"        );
		list.add("PIXCEL_X"      );
		list.add("PIXCEL_Y"      );
		list.add("X_LOGIC1"      );
		list.add("Y_LOGIC1"      );
		list.add("X_LOGIC2"      );
		list.add("Y_LOGIC2"      );
		list.add("SITEID"        );
		list.add("GLASS_HEIGHT"  );

		return list.toArray(new String[list.size()]);
	}

	public String[] getStringArray()
	{
		List<String> list = new ArrayList<String>();
		list.add(procId        );
		list.add(cellLocId     );
		list.add(pointX        );
		list.add(pointY        );
		list.add(width         );
		list.add(height        );
		list.add(gateResolution);
		list.add(dataResolution);
		list.add(colIdx        );
		list.add(rowIdx        );
		list.add(pixcelX       );
		list.add(pixcelY       );
		list.add(XLogic1       );
		list.add(YLogic1       );
		list.add(XLogic2       );
		list.add(YLogic2       );
		list.add(siteId        );
		list.add(glassHeight   );

		return list.toArray(new String[list.size()]);
	}

	public String getProcId()
	{
		return procId;
	}

	public void setProcId(String procId)
	{
		this.procId = procId;
	}

	public String getCellLocId()
	{
		return cellLocId;
	}

	public void setCellLocId(String cellLocId)
	{
		this.cellLocId = cellLocId;
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

	public String getGateResolution()
	{
		return gateResolution;
	}

	public void setGateResolution(String gateResolution)
	{
		this.gateResolution = gateResolution;
	}

	public String getDataResolution()
	{
		return dataResolution;
	}

	public void setDataResolution(String dataResolution)
	{
		this.dataResolution = dataResolution;
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

	public String getPixcelX()
	{
		return pixcelX;
	}

	public void setPixcelX(String pixcelX)
	{
		this.pixcelX = pixcelX;
	}

	public String getPixcelY()
	{
		return pixcelY;
	}

	public void setPixcelY(String pixcelY)
	{
		this.pixcelY = pixcelY;
	}

	public String getXLogic1()
	{
		return XLogic1;
	}

	public void setXLogic1(String xLogic1)
	{
		XLogic1 = xLogic1;
	}

	public String getYLogic1()
	{
		return YLogic1;
	}

	public void setYLogic1(String yLogic1)
	{
		YLogic1 = yLogic1;
	}

	public String getXLogic2()
	{
		return XLogic2;
	}

	public void setXLogic2(String xLogic2)
	{
		XLogic2 = xLogic2;
	}

	public String getYLogic2()
	{
		return YLogic2;
	}

	public void setYLogic2(String yLogic2)
	{
		YLogic2 = yLogic2;
	}

	public String getSiteId()
	{
		return siteId;
	}

	public void setSiteId(String siteId)
	{
		this.siteId = siteId;
	}

	public String getGlassHeight()
	{
		return glassHeight;
	}

	public void setGlassHeight(String glassHeight)
	{
		this.glassHeight = glassHeight;
	}
}
