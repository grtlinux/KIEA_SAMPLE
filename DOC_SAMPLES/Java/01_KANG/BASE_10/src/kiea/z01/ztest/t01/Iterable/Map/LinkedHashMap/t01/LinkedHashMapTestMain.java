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
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import kiea.kr.co.tain.base.Flag;

/**
 * @author KangSeok
 * @date 2014. 8. 13.
 * @file_name TestMain.java
 *
 */
public class LinkedHashMapTestMain
{
	///////////////////////////////////////////////////////////////////////////
	
	private static void test01()
	{
		if (Flag.TRUE) {
			Map<String, String> linkedMap = new LinkedHashMap<String, String>();
			
			linkedMap.put("Korea"  , "Pusan" );
			linkedMap.put("Germany", "Berlin");
			linkedMap.put("Korea"  , "Seoul" );
			linkedMap.put("Japan"  , "Tokyo" );
			linkedMap.put("France" , "Paris" );
			
			Set<String> keySet = linkedMap.keySet();
			Iterator<String> keyIter = keySet.iterator();
			
			while (keyIter.hasNext()) {
				String key = (String) keyIter.next();
				String value = (String) linkedMap.get(key);
				
				System.out.println("[" + key + ":" + value + "]");
			}
			
			List<String> list = new ArrayList<String>(linkedMap.values());
			System.out.println("size = " + list.size());
			
			for (String item : list) {
				System.out.println("[" + item + "]");
			}
		}
	}

	private static void test02()
	{
		if (Flag.TRUE) {
			Map<String, String> linkedMap = new LinkedHashMap<String, String>();
			
			linkedMap.put("Korea"  , "Pusan" );
			linkedMap.put("Germany", "Berlin");
			linkedMap.put("Korea"  , "Seoul" );
			linkedMap.put("Japan"  , "Tokyo" );
			linkedMap.put("France" , "Paris" );
			
			for (Map.Entry<String, String> entry : linkedMap.entrySet()) {
				System.out.println("[" + entry.getKey() + ":" + entry.getValue() + "]");
			}

			List<String> list = new ArrayList<String>(linkedMap.values());
			
			for (String item : list) {
				System.out.println("[" + item + "]");
			}
		}
	}

	public static void main(String[] args)
	{
		if (Flag.TRUE) test01();
		if (!Flag.TRUE) test02();
	}
}
