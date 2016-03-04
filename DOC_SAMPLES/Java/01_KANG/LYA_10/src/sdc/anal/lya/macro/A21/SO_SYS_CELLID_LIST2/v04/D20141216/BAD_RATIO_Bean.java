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

package sdc.anal.lya.macro.A21.SO_SYS_CELLID_LIST2.v04.D20141216;

import java.util.ArrayList;
import java.util.List;

import kiea.proj.sdc.anal.macro.impl.bean.AbstractBean;

/**
 * @author KangSeok
 * @date 2014. 8. 8.
 * @file_name AIB_INSPCT_HIST_Bean.java
 *
 */
public class BAD_RATIO_Bean extends AbstractBean
{
	/*
	// DATE : 2014.12.16 추가
	"PROC_ID   :ProcId   ",
	"CELL_CNT  :CellCnt  ",
	"GLASS_ID  :GlassId  ",
	"BAD_CNT   :BadCnt   ",
	"BAD_RATIO :BadRatio ",
	*/

	private String procId   ;
	private String cellCnt  ;
	private String glassId  ;
	private String badCnt   ;
	private String badRatio ;

	public void addBadCnt()
	{
		badCnt = "" + (Integer.parseInt(badCnt) + 1);
	}
	
	public String toString()
	{
		return String.format("READ : [%s] [%s] [%s] [%s] [%s]"
			, procId   
			, cellCnt  
			, glassId  
			, badCnt   
			, badRatio 
		);
	}

	public static String[] getHeader()
	{
		List<String> list = new ArrayList<String>();
		list.add("PROC_ID"   );
		list.add("CELL_CNT"  );
		list.add("GLASS_ID"  );
		list.add("BAD_CNT"   );
		list.add("BAD_RATIO" );

		return list.toArray(new String[list.size()]);
	}

	public String[] getStringArray()
	{
		List<String> list = new ArrayList<String>();
		list.add(procId   );
		list.add(cellCnt  );
		list.add(glassId  );
		list.add(badCnt   );
		list.add(badRatio );

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

	public String getCellCnt()
	{
		return cellCnt;
	}

	public void setCellCnt(String cellCnt)
	{
		this.cellCnt = cellCnt;
	}

	public String getGlassId()
	{
		return glassId;
	}

	public void setGlassId(String glassId)
	{
		this.glassId = glassId;
	}

	public String getBadCnt()
	{
		return badCnt;
	}

	public void setBadCnt(String badCnt)
	{
		this.badCnt = badCnt;
	}

	public String getBadRatio()
	{
		return badRatio;
	}

	public void setBadRatio(String badRatio)
	{
		this.badRatio = badRatio;
	}
}
