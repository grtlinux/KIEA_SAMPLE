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

package kiea.z01.ztest.t01.Iterable.Comparable.t01;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import kiea.kr.co.tain.base.Flag;

/**
 * @author KangSeok
 * @date 2014. 8. 19.
 * @file_name ComparableTestMain.java
 *
 */
public class ComparableTestMain
{
	public static class Info implements Comparable<Info>
	{
		private String name;
		private String title;
		
		public Info(String name, String title)
		{
			this.name = name;
			this.title = title;
		}

		public String getName()
		{
			return name;
		}

		public String getTitle()
		{
			return title;
		}

		public int compareTo(Info info)
		{
			return name.compareTo(info.getName());
		}
		
		public String toString()
		{
			return String.format("[%s=>%s]", this.name, this.title);
		}
	}
	
	public static class TitleComparator implements Comparator<Info>
	{
		public int compare(Info info1, Info info2)
		{
			return info1.getTitle().compareTo(info2.getTitle());
		}
	}
	
	///////////////////////////////////////////////////////////////////////////
	
	private static void test01()
	{
		if (Flag.TRUE) {
			List<Info> list = new ArrayList<Info>();

			list.add(new Info("김삿갓", "영어"));
			list.add(new Info("홍길동", "사회"));
			list.add(new Info("춘향이", "국사"));
			list.add(new Info("이도령", "수학"));
			list.add(new Info("향단이", "국어"));
			System.out.println(list);
			
			Collections.sort(list);   // Comparable의 compareTo의 기능 사용
			System.out.println(list);
			
			Collections.sort(list, new TitleComparator());   // Comparator의 compareTo 기능사용
			System.out.println(list);
		}
	}
	
	public static void main(String[] args)
	{
		if (Flag.TRUE) test01();
	}
}
