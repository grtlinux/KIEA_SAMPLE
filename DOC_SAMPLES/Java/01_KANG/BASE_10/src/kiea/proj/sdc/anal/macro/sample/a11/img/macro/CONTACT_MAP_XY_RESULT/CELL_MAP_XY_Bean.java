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

package kiea.proj.sdc.anal.macro.sample.a11.img.macro.CONTACT_MAP_XY_RESULT;

import java.util.ArrayList;
import java.util.List;

import kiea.proj.sdc.anal.macro.impl.bean.AbstractBean;

/**
 * @author KangSeok
 * @date 2014. 8. 8.
 * @file_name CELL_MAP_XY_Bean.java
 *
 */
public class CELL_MAP_XY_Bean extends AbstractBean
{
	/*
	"CELL_NO  :CellNo   ",
	"PROCID   :ProcId   ",
	"CELLLOCID:CellLocId",
	"POINT_X  :PointX   ",
	"POINT_Y  :PointY   ",
	"WIDTH    :Width    ",
	"HEIGHT   :Height   ",
	"POINT_X2 :PointX2  ",
	"POINT_Y2 :PointY2  ",
	*/

	private String cellNo   ;
	private String procId   ;
	private String cellLocId;
	private String pointX   ;
	private String pointY   ;
	private String width    ;
	private String height   ;
	private String pointX2  ;
	private String pointY2  ;

	public String toString()
	{
		return String.format("READ : [%s] [%s] [%s] [%s] [%s] [%s] [%s] [%s] [%s]"
			, cellNo   
			, procId   
			, cellLocId
			, pointX   
			, pointY   
			, width    
			, height   
			, pointX2  
			, pointY2  
		);
	}

	public static String[] getHeader()
	{
		List<String> list = new ArrayList<String>();
		list.add("CELL_NO"  );
		list.add("PROCID"   );
		list.add("CELLLOCID");
		list.add("POINT_X"  );
		list.add("POINT_Y"  );
		list.add("WIDTH"    );
		list.add("HEIGHT"   );
		list.add("POINT_X2" );
		list.add("POINT_Y2" );

		return list.toArray(new String[list.size()]);
	}

	public String[] getStringArray()
	{
		List<String> list = new ArrayList<String>();
		list.add(cellNo   );
		list.add(procId   );
		list.add(cellLocId);
		list.add(pointX   );
		list.add(pointY   );
		list.add(width    );
		list.add(height   );
		list.add(pointX2  );
		list.add(pointY2  );

		return list.toArray(new String[list.size()]);
	}

	/**
	 * @return the cellNo
	 */
	public String getCellNo()
	{
		return cellNo;
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
	 * @return the pointX2
	 */
	public String getPointX2()
	{
		return pointX2;
	}

	/**
	 * @return the pointY2
	 */
	public String getPointY2()
	{
		return pointY2;
	}

	/**
	 * @param cellNo the cellNo to set
	 */
	public void setCellNo(String cellNo)
	{
		this.cellNo = cellNo;
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
	 * @param pointX2 the pointX2 to set
	 */
	public void setPointX2(String pointX2)
	{
		this.pointX2 = pointX2;
	}

	/**
	 * @param pointY2 the pointY2 to set
	 */
	public void setPointY2(String pointY2)
	{
		this.pointY2 = pointY2;
	}
}
