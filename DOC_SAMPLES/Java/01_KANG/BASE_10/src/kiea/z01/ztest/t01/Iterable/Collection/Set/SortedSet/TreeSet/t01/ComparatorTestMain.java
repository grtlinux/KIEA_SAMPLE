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

package kiea.z01.ztest.t01.Iterable.Collection.Set.SortedSet.TreeSet.t01;

import java.util.Arrays;
import java.util.Comparator;
import java.util.SortedSet;
import java.util.TreeSet;

import kiea.kr.co.tain.base.Flag;

/**
 * @author KangSeok
 * @date 2014. 8. 19.
 * @file_name ComparatorTestMain.java
 *
 */
public class ComparatorTestMain
{
	///////////////////////////////////////////////////////////////////////////
	
	private static void test01()
	{
		if (Flag.TRUE) {
			SortedSet<String> set = new TreeSet<String>(new Comparator<String>() {
				private static final boolean ASCEND = false;
				@Override
				public int compare(String obj1, String obj2) {
					if (ASCEND)
						return obj1.compareTo(obj2);
					else
						return obj2.compareTo(obj1);
				}
			});
			
			set.add("872521 ");
			set.add("1083071");
			set.add("1083071");
			set.add("872521 ");
			set.add("661971 ");
			set.add("1083071");
			set.add("872521 ");
			set.add("20321  ");
			set.add("441421 ");
			set.add("230871 ");
			set.add("661971 ");
			set.add("230871 ");
			set.add("230871 ");
			set.add("20321  ");
			set.add("1083071");
			set.add("441421 ");
			set.add("661971 ");
			set.add("872521 ");
			set.add("661971 ");
			set.add("661971 ");
			set.add("441421 ");
			set.add("20321  ");
			set.add("661971 ");
			set.add("441421 ");
			set.add("230871 ");
			set.add("1083071");
			set.add("872521 ");
			set.add("20321  ");
			set.add("230871 ");
			set.add("441421 ");
			set.add("230871 ");
			set.add("872521 ");
			set.add("20321  ");
			set.add("1083071");
			set.add("20321  ");
			set.add("441421 ");
			
			for (String item : set) {
				System.out.println(item);
			}
		}
	}
	
	///////////////////////////////////////////////////////////////////////////
	
	private static class Person
	{
		private String name;
		
		public String getName()
		{
			return name;
		}
	}
	
	public static class NameCompare implements Comparator<Person>
	{
		public int compare(Person p1, Person p2)
		{
			return (p1.getName()).compareTo(p2.getName());
		}
	}
	
	/*
	// 위로 수정함.
	public static class NameCompare implements Comparator
	{
		public int compare(Object o1, Object o2)
		{
			if (o1 instanceof Person && o2 instanceof Person) {
				Person p1 = (Person) o1;
				Person p2 = (Person) o2;
				
				return (p1.getName()).compareTo(p2.getName());
			}
			
			return 0;
		}
	}
	*/

	private static class Descending implements Comparator<String>
	{
		private static final boolean ASCEND = false;
		
		public int compare(String s1, String s2)
		{
			if (ASCEND)
				return s1.compareTo(s2);
			else
				return s2.compareTo(s1);
		}
	}
	
	/*
	// 위로 수정함.
	private static class Descending implements Comparator
	{
		public int compare(Object o1, Object o2)
		{
			if (o1 instanceof Comparable && o2 instanceof Comparable) {
				Comparable c1 = (Comparable) o1;
				Comparable c2 = (Comparable) o2;
				
				return c1.compareTo(c2) * -1;
			}
			return -1;
		}
	}
	*/
	
	private static void test02()
	{
		if (Flag.TRUE) {
			SortedSet<String> set = new TreeSet<String>(new Descending());
			
			set.add("872521 ");
			set.add("1083071");
			set.add("1083071");
			set.add("872521 ");
			set.add("661971 ");
			set.add("1083071");
			set.add("872521 ");
			set.add("20321  ");
			set.add("441421 ");
			set.add("230871 ");
			set.add("661971 ");
			set.add("230871 ");
			set.add("230871 ");
			set.add("20321  ");
			set.add("1083071");
			set.add("441421 ");
			set.add("661971 ");
			set.add("872521 ");
			set.add("661971 ");
			set.add("661971 ");
			set.add("441421 ");
			set.add("20321  ");
			set.add("661971 ");
			set.add("441421 ");
			set.add("230871 ");
			set.add("1083071");
			set.add("872521 ");
			set.add("20321  ");
			set.add("230871 ");
			set.add("441421 ");
			set.add("230871 ");
			set.add("872521 ");
			set.add("20321  ");
			set.add("1083071");
			set.add("20321  ");
			set.add("441421 ");
			
			for (String item : set) {
				System.out.println(item);
			}
		}
	}
	
	private static void test03()
	{
		String[] words = { "일", "다섯자", "두자", "열다섯이상", "사백오자" };
		
		Arrays.sort(words, new Comparator<String>() {
			@Override
			public int compare(String s1, String s2) {
				return s1.length() - s2.length();
			}
		});
		
		for (String word : words) {
			System.out.println(word);
		}
	}
	
	public static void main(String[] args)
	{
		if (!Flag.TRUE) test01();
		if (!Flag.TRUE) test02();
		if (Flag.TRUE) test03();
	}
}
