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

package kiea.proj.sdc.anal.util;

import java.util.ArrayList;
import java.util.List;

import kiea.kr.co.tain.base.Flag;
import kiea.kr.co.tain.util.Print;

/**
 * @author KangSeok
 * @date 2014. 8. 8.
 * @file_name StrUtil.java
 *
 */
public class StrUtil
{
	/**
	 * 
	 * quote
	 *
	 * @param str
	 * @return
	 */
	public static String quote(String str)
	{
		if (str.indexOf("'", 0) < 0)
			return String.format("'%s'", str);
		
		return str;
	}
	
	/**
	 * 
	 * doubleQuote
	 *
	 * @param str
	 * @return
	 */
	public static String doubleQuote(String str)
	{
		return String.format("\"%s\"", str);
	}
	
	/**
	 * 
	 * @param jobId
	 * @return
	 */
	public static String getDateFromJobId(String jobId)
	{
		return "20" + jobId.substring(5, 11);
	}
	
	/**
	 * 
	 * @param str
	 * @return
	 */
	public static String[][] getArrString(String strFirst)
	{
		String[][] ret = null;
		
		if (Flag.TRUE) {
			int maxSizeSecond = 0;
			
			List<List<String>> listFirst = new ArrayList<List<String>>();
			String[] arrFirst = strFirst.split(";");
			for (String strSecond : arrFirst) {
				
				List<String> listSecond = new ArrayList<String>();
				String[] arrSecond = strSecond.split(",");
				for (String str : arrSecond) {
					listSecond.add(str);
				}
				
				listFirst.add(listSecond);
				
				if (maxSizeSecond < listSecond.size())
					maxSizeSecond = listSecond.size();
			}
			
			ret = new String[listFirst.size()][maxSizeSecond];
			
			for (int i=0; i < listFirst.size(); i++) {
				List<String> listSecond = listFirst.get(i);
				
				if (!Flag.TRUE) {
					for (int j=0; j < listSecond.size(); j++) {
						ret[i][j] = listSecond.get(j);
					}
				}
				
				if (Flag.TRUE) {
					for (int j=0; j < maxSizeSecond; j++) {
						if (j < listSecond.size()) {
							ret[i][j] = listSecond.get(j).trim();
						} else {
							ret[i][j] = "";
						}
					}
				}
			}
		}
		
		return ret;
	}
	
	/**
	 * 
	 * @param arrStr
	 */
	public static void printArrString(String[][] arrStr)
	{
		if (Flag.TRUE) {
			for (int i=0; i < arrStr.length; i++) {
				for (int j=0; j < arrStr[i].length; j++) {
					System.out.print("[" + arrStr[i][j] + "]   ");
				}
				System.out.println();
			}
		}
	}
	
	///////////////////////////////////////////////////////////////////////////
	
	/**
	 * 
	 * test01
	 *
	 */
	private static void test01()
	{
		if (Flag.TRUE) {
			System.out.println(StrUtil.quote("Hello, world!!!!"));
			System.out.println(StrUtil.doubleQuote("Hello, world!!!!"));
		}
	}
	
	/**
	 * 
	 */
	private static void test02()
	{
		if (Flag.TRUE) {
			Print.println("[%s]", StrUtil.getDateFromJobId("AR010141022A0000"));
		}
	}
	
	/**
	 * 
	 */
	private static void test03()
	{
		if (!Flag.TRUE) {
			String strSrc = "1111,2222,3333;444444,555,  666;;777,,888;;";
			
			String[] first = strSrc.split(";");
			for (String str : first) {
				/*
				 *   [1111,2222,3333]
				 *   [444444,555,  666]
				 *   []
				 *   [777,,888]
				 */
				System.out.println("[" + str + "]");
			}
		}

		if (!Flag.TRUE) {
			
			String strSrc = "1111,2222,3333;444444,555,  666;;777,,888;;,,123;";
			
			int maxSizeSecond = 0;
			List<List<String>> listFirst = new ArrayList<List<String>>();
			String[] arrFirst = strSrc.split(";");
			for (String strSecond : arrFirst) {
				
				List<String> listSecond = new ArrayList<String>();
				String[] arrSecond = strSecond.split(",");
				for (String str : arrSecond) {
					listSecond.add(str);
				}
				
				listFirst.add(listSecond);
				
				if (maxSizeSecond < listSecond.size())
					maxSizeSecond = listSecond.size();
			}
			
			String[][] ret = new String[listFirst.size()][maxSizeSecond];
			
			for (int i=0; i < listFirst.size(); i++) {
				List<String> listSecond = listFirst.get(i);
				
				if (!Flag.TRUE) {
					for (int j=0; j < listSecond.size(); j++) {
						ret[i][j] = listSecond.get(j);
					}
				}
				
				if (Flag.TRUE) {
					for (int j=0; j < maxSizeSecond; j++) {
						if (j < listSecond.size()) {
							ret[i][j] = listSecond.get(j).trim();
						} else {
							ret[i][j] = "";
						}
					}
				}
			}
			
			if (Flag.TRUE) {
				/*
				 * 확인출력
				 */
				for (int i=0; i < ret.length; i++) {
					for (int j=0; j < ret[i].length; j++) {
						System.out.print("[" + ret[i][j] + "]   ");
					}
					System.out.println();
				}
			}
		}
		
		if (Flag.TRUE) {
			
			String strSrc = "1111,2222,3333;444444,555,  666;;777,,888;;,,,123;";

			String[][] strTgt = StrUtil.getArrString(strSrc);
			
			if (Flag.TRUE) {
				/*
				 * 확인출력
				 */
				for (int i=0; i < strTgt.length; i++) {
					for (int j=0; j < strTgt[i].length; j++) {
						System.out.print("[" + strTgt[i][j] + "]   ");
					}
					System.out.println();
				}
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
		if (!Flag.TRUE) test01();
		if (!Flag.TRUE) test02();
		if (Flag.TRUE) test03();
	}
}
