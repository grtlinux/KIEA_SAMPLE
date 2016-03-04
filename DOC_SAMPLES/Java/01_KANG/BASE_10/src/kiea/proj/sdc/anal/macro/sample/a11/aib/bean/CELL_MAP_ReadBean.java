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

package kiea.proj.sdc.anal.macro.sample.a11.aib.bean;

import java.util.ArrayList;
import java.util.List;

import kiea.proj.sdc.anal.macro.impl.bean.AbstractBean;

/**
 * @author KangSeok
 * @date 2014. 8. 8.
 * @file_name CELL_MAP_ReadBean.java
 *
 */
public class CELL_MAP_ReadBean extends AbstractBean
{
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
	private String xLogic1       ;
	private String yLogic1       ;
	private String xLogic2       ;
	private String yLogic2       ;
	private String siteId        ;
	private String glassHeight   ;
	private String mapCell       ;

	public String toString()
	{
		return String.format("READ : [%s] [%s] [%s] [%s] [%s]  [%s] [%s] [%s] [%s] [%s]  [%s] [%s] [%s] [%s] [%s]  [%s] [%s] [%s] [%s]"
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
				, xLogic1
				, yLogic1
				, xLogic2
				, yLogic2
				, siteId
				, glassHeight
				, mapCell
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
		list.add("MAPCELL"       );

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
		list.add(xLogic1       );
		list.add(yLogic1       );
		list.add(xLogic2       );
		list.add(yLogic2       );
		list.add(siteId        );
		list.add(glassHeight   );
		list.add(mapCell       );

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
	 * @param procId the procId to set
	 */
	public void setProcId(String procId)
	{
		this.procId = procId;
	}

	/**
	 * @return the cellLocId
	 */
	public String getCellLocId()
	{
		return cellLocId;
	}

	/**
	 * @param cellLocId the cellLocId to set
	 */
	public void setCellLocId(String cellLocId)
	{
		this.cellLocId = cellLocId;
	}

	/**
	 * @return the pointX
	 */
	public String getPointX()
	{
		return pointX;
	}

	/**
	 * @param pointX the pointX to set
	 */
	public void setPointX(String pointX)
	{
		this.pointX = pointX;
	}

	/**
	 * @return the pointY
	 */
	public String getPointY()
	{
		return pointY;
	}

	/**
	 * @param pointY the pointY to set
	 */
	public void setPointY(String pointY)
	{
		this.pointY = pointY;
	}

	/**
	 * @return the width
	 */
	public String getWidth()
	{
		return width;
	}

	/**
	 * @param width the width to set
	 */
	public void setWidth(String width)
	{
		this.width = width;
	}

	/**
	 * @return the height
	 */
	public String getHeight()
	{
		return height;
	}

	/**
	 * @param height the height to set
	 */
	public void setHeight(String height)
	{
		this.height = height;
	}

	/**
	 * @return the gateResolution
	 */
	public String getGateResolution()
	{
		return gateResolution;
	}

	/**
	 * @param gateResolution the gateResolution to set
	 */
	public void setGateResolution(String gateResolution)
	{
		this.gateResolution = gateResolution;
	}

	/**
	 * @return the dataResolution
	 */
	public String getDataResolution()
	{
		return dataResolution;
	}

	/**
	 * @param dataResolution the dataResolution to set
	 */
	public void setDataResolution(String dataResolution)
	{
		this.dataResolution = dataResolution;
	}

	/**
	 * @return the colIdx
	 */
	public String getColIdx()
	{
		return colIdx;
	}

	/**
	 * @param colIdx the colIdx to set
	 */
	public void setColIdx(String colIdx)
	{
		this.colIdx = colIdx;
	}

	/**
	 * @return the rowIdx
	 */
	public String getRowIdx()
	{
		return rowIdx;
	}

	/**
	 * @param rowIdx the rowIdx to set
	 */
	public void setRowIdx(String rowIdx)
	{
		this.rowIdx = rowIdx;
	}

	/**
	 * @return the pixcelX
	 */
	public String getPixcelX()
	{
		return pixcelX;
	}

	/**
	 * @param pixcelX the pixcelX to set
	 */
	public void setPixcelX(String pixcelX)
	{
		this.pixcelX = pixcelX;
	}

	/**
	 * @return the pixcelY
	 */
	public String getPixcelY()
	{
		return pixcelY;
	}

	/**
	 * @param pixcelY the pixcelY to set
	 */
	public void setPixcelY(String pixcelY)
	{
		this.pixcelY = pixcelY;
	}

	/**
	 * @return the xLogic1
	 */
	public String getxLogic1()
	{
		return xLogic1;
	}

	/**
	 * @param xLogic1 the xLogic1 to set
	 */
	public void setxLogic1(String xLogic1)
	{
		this.xLogic1 = xLogic1;
	}

	/**
	 * @return the yLogic1
	 */
	public String getyLogic1()
	{
		return yLogic1;
	}

	/**
	 * @param yLogic1 the yLogic1 to set
	 */
	public void setyLogic1(String yLogic1)
	{
		this.yLogic1 = yLogic1;
	}

	/**
	 * @return the xLogic2
	 */
	public String getxLogic2()
	{
		return xLogic2;
	}

	/**
	 * @param xLogic2 the xLogic2 to set
	 */
	public void setxLogic2(String xLogic2)
	{
		this.xLogic2 = xLogic2;
	}

	/**
	 * @return the yLogic2
	 */
	public String getyLogic2()
	{
		return yLogic2;
	}

	/**
	 * @param yLogic2 the yLogic2 to set
	 */
	public void setyLogic2(String yLogic2)
	{
		this.yLogic2 = yLogic2;
	}

	/**
	 * @return the siteId
	 */
	public String getSiteId()
	{
		return siteId;
	}

	/**
	 * @param siteId the siteId to set
	 */
	public void setSiteId(String siteId)
	{
		this.siteId = siteId;
	}

	/**
	 * @return the glassHeight
	 */
	public String getGlassHeight()
	{
		return glassHeight;
	}

	/**
	 * @param glassHeight the glassHeight to set
	 */
	public void setGlassHeight(String glassHeight)
	{
		this.glassHeight = glassHeight;
	}

	/**
	 * @return the mapCell
	 */
	public String getMapCell()
	{
		return mapCell;
	}

	/**
	 * @param mapCell the mapCell to set
	 */
	public void setMapCell(String mapCell)
	{
		this.mapCell = mapCell;
	}
}
