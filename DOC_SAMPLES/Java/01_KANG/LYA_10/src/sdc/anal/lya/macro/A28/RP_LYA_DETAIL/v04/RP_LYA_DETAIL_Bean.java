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

package sdc.anal.lya.macro.A28.RP_LYA_DETAIL.v04;

import java.util.ArrayList;
import java.util.List;

import kiea.proj.sdc.anal.macro.impl.bean.AbstractBean;

/**
 * @author KangSeok
 * @date 2014. 8. 8.
 * @file_name SO_SYS_CELLID_LIST2_Bean.java
 *
 */
public class RP_LYA_DETAIL_Bean extends AbstractBean
{
	/*
	"검사 Step     :SubAreaCode ",
	"검사판정시간  :InspectHour ",
	"진행 Cell 수  :CellCount   ",
	"불량 Cell 수  :BadCellCount",
	"Cell 불량률(%):BadCellRatio",
	"USL           :Usl         ",
	*/

	private String subAreaCode ;
	private String inspectHour ;
	private String cellCount    = "0";
	private String badCellCount = "0";
	private String badCellRatio;
	private String usl         ;

	public void addCellCount()
	{
		this.cellCount = "" + (Long.parseLong(this.cellCount) + 1);
	}
	
	public void addBadCellCount()
	{
		this.badCellCount = "" + (Long.parseLong(this.badCellCount) + 1);
	}
	
	public String toString()
	{
		return String.format("READ : [%s] [%s] [%s] [%s] [%s] [%s]"
			, subAreaCode 
			, inspectHour 
			, cellCount   
			, badCellCount
			, badCellRatio
			, usl         
		);
	}

	public static String[] getHeader()
	{
		List<String> list = new ArrayList<String>();
		list.add("검사 Step"     );
		list.add("검사판정시간"  );
		list.add("진행 Cell 수"  );
		list.add("불량 Cell 수"  );
		list.add("Cell 불량률(%)");
		list.add("USL"           );

		return list.toArray(new String[list.size()]);
	}

	public String[] getStringArray()
	{
		List<String> list = new ArrayList<String>();
		list.add(subAreaCode );
		list.add(inspectHour );
		list.add(cellCount   );
		list.add(badCellCount);
		list.add(badCellRatio);
		list.add(usl         );

		return list.toArray(new String[list.size()]);
	}

	public String getSubAreaCode()
	{
		return subAreaCode;
	}

	public void setSubAreaCode(String subAreaCode)
	{
		this.subAreaCode = subAreaCode;
	}

	public String getInspectHour()
	{
		return inspectHour;
	}

	public void setInspectHour(String inspectHour)
	{
		this.inspectHour = inspectHour;
	}

	public String getCellCount()
	{
		return cellCount;
	}

	public void setCellCount(String cellCount)
	{
		this.cellCount = cellCount;
	}

	public String getBadCellCount()
	{
		return badCellCount;
	}

	public void setBadCellCount(String badCellCount)
	{
		this.badCellCount = badCellCount;
	}

	public String getBadCellRatio()
	{
		return badCellRatio;
	}

	public void setBadCellRatio(String badCellRatio)
	{
		this.badCellRatio = badCellRatio;
	}

	public String getUsl()
	{
		return usl;
	}

	public void setUsl(String usl)
	{
		this.usl = usl;
	}
}
