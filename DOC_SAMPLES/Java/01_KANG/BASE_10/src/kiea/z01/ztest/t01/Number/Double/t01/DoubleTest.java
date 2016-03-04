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

package kiea.z01.ztest.t01.Number.Double.t01;

import kiea.kr.co.tain.base.Flag;
import kiea.kr.co.tain.util.Print;

/**
 * @author KangSeok
 * @date 2014. 8. 22.
 * @file_name DoubleTest.java
 *
 */
public class DoubleTest
{
	///////////////////////////////////////////////////////////////////////////
	
	private static void test01()
	{
		if (!Flag.TRUE) {
			String strDouble = "123456.789";
			double dbl = Double.parseDouble(strDouble);
			int num1 = Integer.parseInt(strDouble);   // <- NumberFormatException
			int num2 = (int) dbl;
			
			Print.println("[%s] [%f] [%d] [%d]", strDouble, dbl, num1, num2);
		}
		
		if (Flag.TRUE) {
			String strDouble = "123456.789";
			double dbl = Double.parseDouble(strDouble);
			int num2 = (int) dbl;
			
			Print.println("[%s] [%f] [%d]", strDouble, dbl, num2);
		}
	}
	
	private static void test02()
	{
		if (Flag.TRUE) {
			double dist = Math.sqrt(Math.pow(3.0, 2.0) + Math.pow(4.0, 2.0));
			System.out.println("> " + dist);
		}
		
		if (Flag.TRUE) {
			Double dbl1 = new Double("123456.789");
			Double dbl2 = new Double("123456.789");
			Double dbl3 = dbl1 + dbl2;

			Print.println("[%f] + [%f] = [%f]", dbl1, dbl2, dbl3);
			
			System.out.println(">" + dbl3.toString());
		}
		
		if (Flag.TRUE) {
			double dbl1 = Double.parseDouble("123456.789");
			String strDbl1 = Double.toString(dbl1);
			String strDbl2 = "" + dbl1;
			
			System.out.println(strDbl1);
			System.out.println(strDbl2);
		}
		
		if (Flag.TRUE) {
			Double dbl1 = Double.valueOf(123456.789);
			Double dbl2 = Double.valueOf("123456.789");
			
			if (dbl1.compareTo(dbl2) == 0) {
				System.out.println("Equal");
			} else {
				System.out.println("Diff");
			}
		}
	}
	
	public static void main(String[] args)
	{
		if (!Flag.TRUE) test01();
		if (Flag.TRUE) test02();
	}
}
