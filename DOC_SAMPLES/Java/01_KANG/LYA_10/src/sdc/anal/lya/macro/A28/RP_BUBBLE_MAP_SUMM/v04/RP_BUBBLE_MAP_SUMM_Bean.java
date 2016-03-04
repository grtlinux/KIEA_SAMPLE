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

package sdc.anal.lya.macro.A28.RP_BUBBLE_MAP_SUMM.v04;

import java.util.ArrayList;
import java.util.List;

import kiea.proj.sdc.anal.macro.impl.bean.AbstractBean;

/**
 * @author KangSeok
 * @date 2014. 8. 8.
 * @file_name SO_SYS_CELLID_LIST2_Bean.java
 *
 */
public class RP_BUBBLE_MAP_SUMM_Bean extends AbstractBean
{
	/*
	"QAF_JOB_ID    :QafJobId    ",
	"CELL_LOC_ID   :CellLocId   ",
	"TOT_CELL_CNT  :TotCellCnt  ",
	"BAD_CELL_CNT  :BadCellCnt  ",
	"CELL_BAD_RATIO:CellBadRatio",
	"PRODGRPNAME   :ProdGrpName ",
	"AREAID        :AreaId      ",
	"POINTX        :PointX      ",
	"POINTY        :PointY      ",
	"COLIDX        :ColIdx      ",
	"ROWIDX        :RowIdx      ",
	"WIDTH         :Width       ",
	"HEIGHT        :Height      ",
	*/

	private String qafJobId    ;
	private String cellLocId   ;
	private String totCellCnt  ;
	private String badCellCnt  ;
	private String cellBadRatio;
	private String prodGrpName ;
	private String areaId      ;
	private String pointX      ;
	private String pointY      ;
	private String colIdx      ;
	private String rowIdx      ;
	private String width       ;
	private String height      ;

	public void addTotCellCnt()
	{
		this.totCellCnt = "" + (Long.parseLong(this.totCellCnt) + 1);
	}
	
	public void addBadCellCnt()
	{
		this.badCellCnt = "" + (Long.parseLong(this.badCellCnt) + 1);
	}
	
	public String toString()
	{
		return String.format("READ : [%s] [%s] [%s] [%s] [%s] [%s] [%s] [%s] [%s] [%s] [%s] [%s] [%s]"
			, qafJobId    
			, cellLocId   
			, totCellCnt  
			, badCellCnt  
			, cellBadRatio
			, prodGrpName 
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
		list.add("QAF_JOB_ID"    );
		list.add("CELL_LOC_ID"   );
		list.add("TOT_CELL_CNT"  );
		list.add("BAD_CELL_CNT"  );
		list.add("CELL_BAD_RATIO");
		list.add("PRODGRPNAME"   );
		list.add("AREAID"        );
		list.add("POINTX"        );
		list.add("POINTY"        );
		list.add("COLIDX"        );
		list.add("ROWIDX"        );
		list.add("WIDTH"         );
		list.add("HEIGHT"        );

		return list.toArray(new String[list.size()]);
	}

	public String[] getStringArray()
	{
		List<String> list = new ArrayList<String>();
		list.add(qafJobId    );
		list.add(cellLocId   );
		list.add(totCellCnt  );
		list.add(badCellCnt  );
		list.add(cellBadRatio);
		list.add(prodGrpName );
		list.add(areaId      );
		list.add(pointX      );
		list.add(pointY      );
		list.add(colIdx      );
		list.add(rowIdx      );
		list.add(width       );
		list.add(height      );

		return list.toArray(new String[list.size()]);
	}

	public String getQafJobId()
	{
		return qafJobId;
	}

	public void setQafJobId(String qafJobId)
	{
		this.qafJobId = qafJobId;
	}

	public String getCellLocId()
	{
		return cellLocId;
	}

	public void setCellLocId(String cellLocId)
	{
		this.cellLocId = cellLocId;
	}

	public String getTotCellCnt()
	{
		return totCellCnt;
	}

	public void setTotCellCnt(String totCellCnt)
	{
		this.totCellCnt = totCellCnt;
	}

	public String getBadCellCnt()
	{
		return badCellCnt;
	}

	public void setBadCellCnt(String badCellCnt)
	{
		this.badCellCnt = badCellCnt;
	}

	public String getCellBadRatio()
	{
		return cellBadRatio;
	}

	public void setCellBadRatio(String cellBadRatio)
	{
		this.cellBadRatio = cellBadRatio;
	}

	public String getProdGrpName()
	{
		return prodGrpName;
	}

	public void setProdGrpName(String prodGrpName)
	{
		this.prodGrpName = prodGrpName;
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
