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

package kiea.proj.sdc.anal.macro.sample.a01.bean;

import java.util.ArrayList;
import java.util.List;

import kiea.proj.sdc.anal.macro.impl.bean.AbstractBean;

/**
 * @author KangSeok
 * @date 2014. 8. 5.
 * @file_name Sample01ReadBean.java
 *
 */
public class Sample01ReadBean extends AbstractBean
{
	private int groupNo;
	private int valueX;
	private int valueY;
	private boolean flag;
	private String etc;

	/**
	 * 자료의 화면 출력용
	 */
	public String toString()
	{
		return String.format("[%d] [%d] [%d] [%s] [%s].", groupNo, valueX, valueY, flag, etc);
	}
	
	/**
	 * 자료의 Header
	 * @return
	 */
	public static String[] getHeader()
	{
		List<String> arrayList = new ArrayList<String>();
		arrayList.add(" GROUP_NO ");
		arrayList.add(" VALUE_X");
		arrayList.add("VALUE_Y ");
		arrayList.add("F L A G");
		arrayList.add("E T C");
		
		return arrayList.toArray(new String[arrayList.size()]);
	}
	
	/**
	 * 자료의 내용
	 */
	public String[] getStringArray()
	{
		List<String> arrayList = new ArrayList<String>();
		arrayList.add(Integer.toString(groupNo));
		arrayList.add(Integer.toString(valueX));
		arrayList.add(Integer.toString(valueY));
		arrayList.add(Boolean.toString(flag));
		arrayList.add(etc);
		
		return arrayList.toArray(new String[arrayList.size()]);
	}

	public int getGroupNo()
	{
		return groupNo;
	}

	public int getValueX()
	{
		return valueX;
	}

	public int getValueY()
	{
		return valueY;
	}

	public boolean isFlag()
	{
		return flag;
	}

	public String getEtc()
	{
		return etc;
	}

	public void setGroupNo(int groupNo)
	{
		this.groupNo = groupNo;
	}

	public void setValueX(int valueX)
	{
		this.valueX = valueX;
	}

	public void setValueY(int valueY)
	{
		this.valueY = valueY;
	}

	public void setFlag(boolean flag)
	{
		this.flag = flag;
	}

	public void setEtc(String etc)
	{
		this.etc = etc;
	}
}
