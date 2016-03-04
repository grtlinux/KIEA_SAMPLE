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

package kiea.z01.ztest.t01.Iterable.Map.SortedMap.TreeMap.t01;

import java.util.Map;
import java.util.SortedMap;
import java.util.SortedSet;
import java.util.TreeMap;
import java.util.TreeSet;

import kiea.kr.co.tain.base.Flag;
import kiea.kr.co.tain.util.Print;

/**
 * @author KangSeok
 * @date 2014. 8. 19.
 * @file_name TreeMapTestMain.java
 *
 */
public class TreeMapTestMain
{
	///////////////////////////////////////////////////////////////////////////
	
	private static void test01()
	{
		if (Flag.TRUE) {

			String[][] lists = {
					{ "872521  ", "385722", },
					{ "1083071 ", "204622", },
					{ "1083071 ", "204622", },
					{ "872521  ", "566822", },
					{ "661971  ", "23522 ", },
					{ "1083071 ", "566822", },
					{ "872521  ", "23522 ", },
					{ "20321   ", "23522 ", },
					{ "441421  ", "747922", },
					{ "230871  ", "204622", },
					{ "661971  ", "385722", },
					{ "230871  ", "566822", },
					{ "230871  ", "929022", },
					{ "20321   ", "929022", },
					{ "1083071 ", "929022", },
					{ "441421  ", "385722", },
					{ "661971  ", "204622", },
					{ "872521  ", "204622", },
					{ "661971  ", "747922", },
					{ "661971  ", "929022", },
					{ "441421  ", "929022", },
					{ "20321   ", "385722", },
					{ "661971  ", "566822", },
					{ "441421  ", "566822", },
					{ "230871  ", "385722", },
					{ "1083071 ", "747922", },
					{ "872521  ", "747922", },
					{ "20321   ", "566822", },
					{ "230871  ", "747922", },
					{ "441421  ", "204622", },
					{ "230871  ", "23522 ", },
					{ "872521  ", "929022", },
					{ "20321   ", "747922", },
					{ "1083071 ", "385722", },
					{ "20321   ", "204622", },
					{ "441421  ", "23522 ", },
			};
			
			Print.println("list.length = %d", lists.length);
			
			SortedMap<String, SortedSet<String>> map = new TreeMap<String, SortedSet<String>>();
			
			/*
			 * setting
			 */
			for (String[] list : lists) {
				
				if (!Flag.TRUE) Print.println("%s %s", list[0], list[1]);
				
				SortedSet<String> set = map.get(list[0]);
				if (set == null) {
					map.put(list[0], new TreeSet<String>());
					set = map.get(list[0]);
				}
				set.add(list[1]);
			}
			
			/*
			 * print
			 */
			for (Map.Entry<String, SortedSet<String>> entry : map.entrySet()) {
				String key = entry.getKey();
				SortedSet<String> set = entry.getValue();
				
				Print.println("[%s] [size:%d]", key, set.size());
				
				for (String str : set) {
					Print.println("\t(%s)", str);
				}
			}
		}
	}
	
	public static void main(String[] args)
	{
		if (Flag.TRUE) test01();
	}
}
