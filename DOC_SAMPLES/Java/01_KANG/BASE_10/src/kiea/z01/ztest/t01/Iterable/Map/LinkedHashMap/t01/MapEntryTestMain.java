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
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import kiea.kr.co.tain.base.Flag;

/**
 * @author KangSeok
 * @date 2014. 8. 13.
 * @file_name MapEntry.java
 *
 */
public class MapEntryTestMain
{
	private static void test01()
	{
		if (Flag.TRUE) {
			Map<String, Object> map = new HashMap<String,Object>();
			map.put("A",  new Integer(3));
			map.put("B", "bb1");
			map.put("B", "BB1");
			System.out.println(map);
			
			for (Map.Entry<String, Object> entry : map.entrySet()) {
				System.out.println("\t[" + entry.getKey() + ":" + entry.getValue() + "]");
			}
			
			List<Object> list = new ArrayList<Object>(map.values());
			for (Object obj : list) {
				if (obj instanceof String) {
					System.out.println("String[" + (String)obj + "]");
				} else if (obj instanceof Integer) {
					System.out.println("Integer[" + (Integer) obj + "]");
				}
			}
		}
	}

	private static void test02()
	{
		if (Flag.TRUE) {
			Properties prop = System.getProperties();
			
			for (Map.Entry<Object, Object> entry : prop.entrySet()) {
				System.out.println("\t[" + entry.getKey() + ":" + entry.getValue() + "]");
			}
		}
	}
	
	public static void main(String[] args)
	{
		if (Flag.TRUE) test01();
		if (!Flag.TRUE) test02();
	}
}
