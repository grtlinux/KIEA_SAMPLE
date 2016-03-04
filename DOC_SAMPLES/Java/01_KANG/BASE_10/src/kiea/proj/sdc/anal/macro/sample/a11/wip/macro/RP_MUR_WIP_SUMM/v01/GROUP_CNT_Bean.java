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

package kiea.proj.sdc.anal.macro.sample.a11.wip.macro.RP_MUR_WIP_SUMM.v01;

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
	"GROUP_NUM:GroupNum",
	"GROUP_CNT:GroupCnt",
	*/

	private String groupNum;
	private String groupCnt;

	public String toString()
	{
		return String.format("READ : [%s] [%s]"
			, groupNum
			, groupCnt
		);
	}

	public static String[] getHeader()
	{
		List<String> list = new ArrayList<String>();
		list.add("GROUP_NUM");
		list.add("GROUP_CNT");

		return list.toArray(new String[list.size()]);
	}

	public String[] getStringArray()
	{
		List<String> list = new ArrayList<String>();
		list.add(groupNum);
		list.add(groupCnt);

		return list.toArray(new String[list.size()]);
	}

	public void addCnt()
	{
		int cnt = Integer.parseInt(groupCnt) + 1;
		groupCnt = "" + cnt;
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
}
