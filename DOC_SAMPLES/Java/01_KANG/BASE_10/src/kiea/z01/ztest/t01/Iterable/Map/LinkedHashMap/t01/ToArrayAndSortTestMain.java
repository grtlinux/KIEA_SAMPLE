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

package kiea.z01.ztest.t01.Iterable.Map.LinkedHashMap.t01;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import kiea.kr.co.tain.base.Flag;

/**
 * @author KangSeok
 * @date 2014. 9. 2.
 * @file_name ToArrayAndSortTestMain.java
 *
 */
public class ToArrayAndSortTestMain
{
	@SuppressWarnings("unused")
	private final static class Hello
	{
		private String name;
		private String subject1;
		private String subject2;
		
		public Hello(String name, String subject1, String subject2)
		{
			this.name = name;
			this.subject1 = subject1;
			this.subject2 = subject2;
		}
		
		public String toString()
		{
			return String.format("INFO : [%s] [%s] [%s]", this.name, this.subject1, this.subject2);
		}

		public String getName()
		{
			return name;
		}
		public String getSubject1()
		{
			return subject1;
		}

		public String getSubject2()
		{
			return subject2;
		}

		public void setName(String name)
		{
			this.name = name;
		}

		public void setSubject1(String subject1)
		{
			this.subject1 = subject1;
		}

		public void setSubject2(String subject2)
		{
			this.subject2 = subject2;
		}
	}
	
	///////////////////////////////////////////////////////////////////////////
	
	private static void test01()
	{
		if (Flag.TRUE) {
			
			List<Hello> list = new ArrayList<Hello>();
			if (Flag.TRUE) {
				/*
				 * °´Ã¼»ý¼º
				 */
				list.add(new Hello("È«±æµ¿", "123", "569"));
				list.add(new Hello("ÀÌ¸ù·æ", "568", "879"));
				list.add(new Hello("¼ºÃáÇâ", "789", "584"));
				list.add(new Hello("ÀÌ´Þ·¡", "521", "151"));
				list.add(new Hello("À±´Ù¼Ø", "564", "452"));
				list.add(new Hello("±èÇÏ³ª", "985", "589"));
				list.add(new Hello("ÀÌÀº°æ", "102", "845"));
				list.add(new Hello("ÀÌÀºÁÖ", "235", "458"));
			}
			
			Map<String, Hello> map = new LinkedHashMap<String, Hello>();
			if (Flag.TRUE) {
				/*
				 * Map »ý¼º
				 */
				for (Hello item : list) {
					String key = item.getName() + ":" + item.getSubject1() + ":" + item.getSubject2();
					
					map.put(key, item);
				}
			}
			
			List<Hello> list2 = null;
			if (Flag.TRUE) {
				/*
				 * List°´Ã¼ »ý¼º°ú SORT
				 */
				list2 = new ArrayList<Hello>(map.values());
				
				Collections.sort(list2, new Comparator<Hello>() {
					@Override
					public int compare(Hello h1, Hello h2)
					{
						int ret;
						
						ret = h1.getName().compareTo(h2.getName());
						if (ret != 0) return ret;
						
						ret = h1.getSubject1().compareTo(h2.getSubject1());
						if (ret != 0) return ret;
						
						ret = h1.getSubject2().compareTo(h2.getSubject2());
						
						return ret;
					}
				});
			}
			
			if (Flag.TRUE) {
				/*
				 * Ãâ·Â
				 */
				for (Hello item : list) {
					System.out.println(item);
				}
				
				System.out.println("---------------------------------------------------");
				
				for (Hello item : list2) {
					System.out.println(item);
				}
			}
		}
	}
	
	public static void main(String[] args)
	{
		if (Flag.TRUE) test01();
	}
}
