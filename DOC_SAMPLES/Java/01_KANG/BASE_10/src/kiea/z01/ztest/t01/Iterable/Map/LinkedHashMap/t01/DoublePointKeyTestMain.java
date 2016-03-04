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
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import kiea.kr.co.tain.base.Flag;

import org.apache.commons.math3.ml.clustering.DoublePoint;

/**
 * @author KangSeok
 * @date 2014. 8. 14.
 * @file_name DoublePointKeyTestMain.java
 *
 */
public class DoublePointKeyTestMain
{
	///////////////////////////////////////////////////////////////////////////
	
	/**
	 * 
	 * test01
	 *
	 */
	private static void test01()
	{
		if (Flag.TRUE) {
			Map<DoublePoint, String> map = new LinkedHashMap<DoublePoint, String>();
			
			map.put(new DoublePoint(new int[] {10, 10}), "10_10");
			map.put(new DoublePoint(new int[] {12,  9}), "12_ 9");
			map.put(new DoublePoint(new int[] {10,  8}), "10_ 8");
			map.put(new DoublePoint(new int[] { 8,  8}), " 8_ 8");
			map.put(new DoublePoint(new int[] { 8,  6}), " 8_ 6");
			map.put(new DoublePoint(new int[] { 7,  7}), " 7_ 7");
			map.put(new DoublePoint(new int[] { 5,  6}), " 5_ 6");
			map.put(new DoublePoint(new int[] {14,  8}), "14_ 8");
			map.put(new DoublePoint(new int[] { 7, 15}), " 7_15");
			map.put(new DoublePoint(new int[] {17,  8}), "17_ 8");

			if (!Flag.TRUE) System.out.println(new DoublePoint(new int[] {17,  8}));
			
			if (Flag.TRUE) {
				List<DoublePoint> listPoint = new ArrayList<DoublePoint>(map.keySet());
				for (DoublePoint point : listPoint) {
					System.out.println("DoublePoint " + point);
				}
				
				System.out.println("------------------------------------");
			}

			if (Flag.TRUE) {
				List<String> listString = new ArrayList<String>(map.values());
				for (String string : listString) {
					System.out.println("String " + string);
				}
				
				System.out.println("------------------------------------");
			}
			

			if (Flag.TRUE) {
				for (Map.Entry<DoublePoint, String> entry : map.entrySet()) {
					System.out.println("(" + entry.getKey() + ") (" + entry.getValue() + ")");
				}

				System.out.println("------------------------------------");
			}
			
			if (Flag.TRUE) {
				String value = map.get(new DoublePoint(new int[] {17,  8}));
				System.out.println("[" + value + "]");
				System.out.println("------------------------------------");
			}
		}
	}

	/**
	 * 
	 * main
	 *
	 * @param args
	 */
	public static void main(String[] args)
	{
		if (Flag.TRUE) test01();
	}
}
