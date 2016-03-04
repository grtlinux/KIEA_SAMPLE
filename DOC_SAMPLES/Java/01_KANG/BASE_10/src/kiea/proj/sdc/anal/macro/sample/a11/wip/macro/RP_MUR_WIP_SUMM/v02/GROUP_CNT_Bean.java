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

package kiea.proj.sdc.anal.macro.sample.a11.wip.macro.RP_MUR_WIP_SUMM.v02;

import java.util.ArrayList;
import java.util.List;

import kiea.proj.sdc.anal.macro.impl.bean.AbstractBean;

/**
 * @author KangSeok
 * @date 2014. 8. 8.
 * @file_name GROUP_CNT_Bean.java
 *
 */
public class GROUP_CNT_Bean extends AbstractBean
{
	/*
	"GROUP_NUM   :GroupNum  ",
	"GROUP_CNT   :GroupCnt  ",
	"GRP_BAD_CNT :GrpBadCnt ",
	"GRP_GOOD_CNT:GrpGoodCnt",
	*/

	private String groupNum  ;
	private String groupCnt  ;
	private String grpBadCnt ;
	private String grpGoodCnt;

	public String toString()
	{
		return String.format("READ : [%s] [%s] [%s] [%s]"
			, groupNum  
			, groupCnt  
			, grpBadCnt 
			, grpGoodCnt
		);
	}

	public static String[] getHeader()
	{
		List<String> list = new ArrayList<String>();
		list.add("GROUP_NUM"   );
		list.add("GROUP_CNT"   );
		list.add("GRP_BAD_CNT" );
		list.add("GRP_GOOD_CNT");

		return list.toArray(new String[list.size()]);
	}

	public String[] getStringArray()
	{
		List<String> list = new ArrayList<String>();
		list.add(groupNum  );
		list.add(groupCnt  );
		list.add(grpBadCnt );
		list.add(grpGoodCnt);

		return list.toArray(new String[list.size()]);
	}

	public void addGroupCnt()
	{
		groupCnt = "" + (Integer.parseInt(groupCnt) + 1);
	}
	
	public void addGrpBadCnt()
	{
		grpBadCnt = "" + (Integer.parseInt(grpBadCnt) + 1);
	}
	
	public void addGrpGoodCnt()
	{
		grpGoodCnt = "" + (Integer.parseInt(grpGoodCnt) + 1);
	}
	
	/**
	 * @return the groupNum
	 */
	public String getGroupNum()
	{
		return groupNum;
	}

	/**
	 * @return the groupCnt
	 */
	public String getGroupCnt()
	{
		return groupCnt;
	}

	/**
	 * @return the grpBadCnt
	 */
	public String getGrpBadCnt()
	{
		return grpBadCnt;
	}

	/**
	 * @return the grpGoodCnt
	 */
	public String getGrpGoodCnt()
	{
		return grpGoodCnt;
	}

	/**
	 * @param groupNum the groupNum to set
	 */
	public void setGroupNum(String groupNum)
	{
		this.groupNum = groupNum;
	}

	/**
	 * @param groupCnt the groupCnt to set
	 */
	public void setGroupCnt(String groupCnt)
	{
		this.groupCnt = groupCnt;
	}

	/**
	 * @param grpBadCnt the grpBadCnt to set
	 */
	public void setGrpBadCnt(String grpBadCnt)
	{
		this.grpBadCnt = grpBadCnt;
	}

	/**
	 * @param grpGoodCnt the grpGoodCnt to set
	 */
	public void setGrpGoodCnt(String grpGoodCnt)
	{
		this.grpGoodCnt = grpGoodCnt;
	}
}
