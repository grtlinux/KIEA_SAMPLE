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

package kiea.proj.sdc.anal.macro.sample.a12.aib.macro.AIB_CELLCONVERT.v01;

import java.util.ArrayList;
import java.util.List;

import kiea.proj.sdc.anal.macro.impl.bean.AbstractBean;

/**
 * @author KangSeok
 * @date 2014. 8. 8.
 * @file_name AIB_CELLCONVERT_WriteBean.java
 *
 */
public class AIB_CELLCONVERT_WriteBean extends AbstractBean
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

	/**
	 * @return the procId
	 */
	public String getProcId()
	{
		return procId;
	}

	/**
	 * @return the cellLocId
	 */
	public String getCellLocId()
	{
		return cellLocId;
	}

	/**
	 * @return the pointX
	 */
	public String getPointX()
	{
		return pointX;
	}

	/**
	 * @return the pointY
	 */
	public String getPointY()
	{
		return pointY;
	}

	/**
	 * @return the width
	 */
	public String getWidth()
	{
		return width;
	}

	/**
	 * @return the height
	 */
	public String getHeight()
	{
		return height;
	}

	/**
	 * @return the gateResolution
	 */
	public String getGateResolution()
	{
		return gateResolution;
	}

	/**
	 * @return the dataResolution
	 */
	public String getDataResolution()
	{
		return dataResolution;
	}

	/**
	 * @return the colIdx
	 */
	public String getColIdx()
	{
		return colIdx;
	}

	/**
	 * @return the rowIdx
	 */
	public String getRowIdx()
	{
		return rowIdx;
	}

	/**
	 * @return the pixcelX
	 */
	public String getPixcelX()
	{
		return pixcelX;
	}

	/**
	 * @return the pixcelY
	 */
	public String getPixcelY()
	{
		return pixcelY;
	}

	/**
	 * @return the xLogic1
	 */
	public String getXLogic1()
	{
		return XLogic1;
	}

	/**
	 * @return the yLogic1
	 */
	public String getYLogic1()
	{
		return YLogic1;
	}

	/**
	 * @return the xLogic2
	 */
	public String getXLogic2()
	{
		return XLogic2;
	}

	/**
	 * @return the yLogic2
	 */
	public String getYLogic2()
	{
		return YLogic2;
	}

	/**
	 * @return the siteId
	 */
	public String getSiteId()
	{
		return siteId;
	}

	/**
	 * @return the glassHeight
	 */
	public String getGlassHeight()
	{
		return glassHeight;
	}

	/**
	 * @param procId the procId to set
	 */
	public void setProcId(String procId)
	{
		this.procId = procId;
	}

	/**
	 * @param cellLocId the cellLocId to set
	 */
	public void setCellLocId(String cellLocId)
	{
		this.cellLocId = cellLocId;
	}

	/**
	 * @param pointX the pointX to set
	 */
	public void setPointX(String pointX)
	{
		this.pointX = pointX;
	}

	/**
	 * @param pointY the pointY to set
	 */
	public void setPointY(String pointY)
	{
		this.pointY = pointY;
	}

	/**
	 * @param width the width to set
	 */
	public void setWidth(String width)
	{
		this.width = width;
	}

	/**
	 * @param height the height to set
	 */
	public void setHeight(String height)
	{
		this.height = height;
	}

	/**
	 * @param gateResolution the gateResolution to set
	 */
	public void setGateResolution(String gateResolution)
	{
		this.gateResolution = gateResolution;
	}

	/**
	 * @param dataResolution the dataResolution to set
	 */
	public void setDataResolution(String dataResolution)
	{
		this.dataResolution = dataResolution;
	}

	/**
	 * @param colIdx the colIdx to set
	 */
	public void setColIdx(String colIdx)
	{
		this.colIdx = colIdx;
	}

	/**
	 * @param rowIdx the rowIdx to set
	 */
	public void setRowIdx(String rowIdx)
	{
		this.rowIdx = rowIdx;
	}

	/**
	 * @param pixcelX the pixcelX to set
	 */
	public void setPixcelX(String pixcelX)
	{
		this.pixcelX = pixcelX;
	}

	/**
	 * @param pixcelY the pixcelY to set
	 */
	public void setPixcelY(String pixcelY)
	{
		this.pixcelY = pixcelY;
	}

	/**
	 * @param xLogic1 the xLogic1 to set
	 */
	public void setXLogic1(String xLogic1)
	{
		XLogic1 = xLogic1;
	}

	/**
	 * @param yLogic1 the yLogic1 to set
	 */
	public void setYLogic1(String yLogic1)
	{
		YLogic1 = yLogic1;
	}

	/**
	 * @param xLogic2 the xLogic2 to set
	 */
	public void setXLogic2(String xLogic2)
	{
		XLogic2 = xLogic2;
	}

	/**
	 * @param yLogic2 the yLogic2 to set
	 */
	public void setYLogic2(String yLogic2)
	{
		YLogic2 = yLogic2;
	}

	/**
	 * @param siteId the siteId to set
	 */
	public void setSiteId(String siteId)
	{
		this.siteId = siteId;
	}

	/**
	 * @param glassHeight the glassHeight to set
	 */
	public void setGlassHeight(String glassHeight)
	{
		this.glassHeight = glassHeight;
	}
}
