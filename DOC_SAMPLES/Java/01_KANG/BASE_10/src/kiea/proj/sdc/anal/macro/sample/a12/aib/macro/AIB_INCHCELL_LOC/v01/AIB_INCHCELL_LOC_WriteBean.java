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

package kiea.proj.sdc.anal.macro.sample.a12.aib.macro.AIB_INCHCELL_LOC.v01;

import java.util.ArrayList;
import java.util.List;

import kiea.proj.sdc.anal.macro.impl.bean.AbstractBean;

/**
 * @author KangSeok
 * @date 2014. 8. 8.
 * @file_name AIB_INCHCELL_LOC_WriteBean.java
 *
 */
public class AIB_INCHCELL_LOC_WriteBean extends AbstractBean
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

	/**
	 * @return the siteId
	 */
	public String getSiteId()
	{
		return siteId;
	}

	/**
	 * @return the cellLocId
	 */
	public String getCellLocId()
	{
		return cellLocId;
	}

	/**
	 * @return the prodGrpName
	 */
	public String getProdGrpName()
	{
		return prodGrpName;
	}

	/**
	 * @return the inch
	 */
	public String getInch()
	{
		return inch;
	}

	/**
	 * @return the areaId
	 */
	public String getAreaId()
	{
		return areaId;
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
	 * @param siteId the siteId to set
	 */
	public void setSiteId(String siteId)
	{
		this.siteId = siteId;
	}

	/**
	 * @param cellLocId the cellLocId to set
	 */
	public void setCellLocId(String cellLocId)
	{
		this.cellLocId = cellLocId;
	}

	/**
	 * @param prodGrpName the prodGrpName to set
	 */
	public void setProdGrpName(String prodGrpName)
	{
		this.prodGrpName = prodGrpName;
	}

	/**
	 * @param inch the inch to set
	 */
	public void setInch(String inch)
	{
		this.inch = inch;
	}

	/**
	 * @param areaId the areaId to set
	 */
	public void setAreaId(String areaId)
	{
		this.areaId = areaId;
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
}
