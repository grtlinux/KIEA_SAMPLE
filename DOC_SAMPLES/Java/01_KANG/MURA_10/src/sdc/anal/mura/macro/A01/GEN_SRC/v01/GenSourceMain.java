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

package sdc.anal.mura.macro.A01.GEN_SRC.v01;

import kiea.kr.co.tain.base.Flag;
import kiea.kr.co.tain.util.Print;

/**
 * @author KangSeok
 * @date 2014. 8. 11.
 * @file_name GenSourceMain.java
 *
 */
public class GenSourceMain
{
	private String[]   lines = null;
	private String[][] words = null;
	
	/**
	 * 
	 * @param lines
	 */
	public GenSourceMain(String[] lines) 
	{
		this.lines = lines;
		this.words = new String[lines.length][5];
		
		makeWords();
	}
	
	/**
	 * 
	 * makeWords
	 *
	 */
	private void makeWords()
	{
		for (int i=0; i < lines.length; i++) {
			String[] items = lines[i].split(":");
			words[i][0] = items[0];
			words[i][1] = items[1];
			words[i][2] = makeQuote(items[0]);
			words[i][3] = makeLowerChar(items[1]);
			words[i][4] = makeQuote(words[i][3]);
		}
	}
	
	/**
	 * 
	 * makeQuote
	 *
	 * @param str
	 * @return
	 */
	private String makeQuote(String str)
	{
		String ret = null;
		int len = str.length() + 2;
		
		str = String.format("\"%s\"", str.trim());
		
		ret = String.format("%-" + len + "." + len + "s", str);
		
		return ret;
	}
	
	/**
	 * 
	 * makeLowerChar
	 *
	 * @param str
	 * @return
	 */
	private String makeLowerChar(String str)
	{
		String ret = null;
		
		String charCheck = str.substring(1, 2);
		if (charCheck.equals(charCheck.toLowerCase())) {
			/*
			 * 바로뒤 문자가 소문자 -> 처리한다.
			 */
			ret = str.substring(0, 1).toLowerCase() + str.substring(1);
		} else {
			/*
			 * 바로뒤 문자가 대문자
			 */
			ret = str;
		}
		
		return ret;
	}
	
	///////////////////////////////////////////////////////////////////////////

	/**
	 * 
	 * print
	 *
	 */
	public void print()
	{
		/*
		 * 원본
		 */
		if (!Flag.TRUE) {
			for (int i=0; i < lines.length; i++) {
				Print.println("\"%s:%s\",", words[i][0], words[i][1]);
			}
		}

		Print.println();
		Print.println("---------------------------------------------- 자료내용");
		
		/*
		 * 정보
		 */
		for (int i=0; i < lines.length; i++) {
			Print.println("[%s] [%s] [%s] [%s] [%s]", words[i][0], words[i][1], words[i][2], words[i][3], words[i][4]);
		}
	}
	
	/**
	 * 
	 * printBean
	 *
	 */
	public void printBean()
	{
		Print.println("\n---------------------------------------------- Bean");
		
		if (Flag.TRUE) {
			/*
			 * 출력형태 : 정보
			 */
			Print.println("/*");
			for (int i=0; i < lines.length; i++) {
				Print.println("\"%s:%s\",", words[i][0], words[i][1]);
			}
			Print.println("*/");
			
			Print.println();
		}

		if (Flag.TRUE) {
			/*
			 * 출력형태 : private String sbcDef   ;
			 */
			for (int i=0; i < lines.length; i++) {
				Print.println("private String %s;", words[i][3]);
			}
			
			Print.println();
		}

		if (Flag.TRUE) {
			/*
			 * 출력형태 : 
			 */
			Print.println("public String toString()");
			Print.println("{");
			Print.print("\treturn String.format(\"READ :");
			for (int i=0; i < lines.length; i++) {
				Print.print(" [%%s]");
			}
			Print.println("\"");
			
			for (int i=0; i < lines.length; i++) {
				Print.println("\t\t, %s", words[i][3]);
			}
			
			Print.println("\t);");
			Print.println("}");
			Print.println();
		}

		if (Flag.TRUE) {
			/*
			 * 출력형태 : 
			 */
			Print.println("public static String[] getHeader()");
			Print.println("{");
			Print.println("\tList<String> list = new ArrayList<String>();");
			
			for (int i=0; i < lines.length; i++) {
				Print.println("\tlist.add(%s);", words[i][2]);
			}
			
			Print.println();
			Print.println("\treturn list.toArray(new String[list.size()]);");
			Print.println("}");
			Print.println();
		}

		if (Flag.TRUE) {
			/*
			 * 출력형태 : 
			 */
			Print.println("public String[] getStringArray()");
			Print.println("{");
			Print.println("\tList<String> list = new ArrayList<String>();");
			
			for (int i=0; i < lines.length; i++) {
				Print.println("\tlist.add(%s);", words[i][3]);
			}
			
			Print.println();
			Print.println("\treturn list.toArray(new String[list.size()]);");
			Print.println("}");
			Print.println();
		}
	}
	
	/**
	 * 
	 * printReader
	 *
	 */
	public void printReader()
	{
		Print.println("\n---------------------------------------------- Reader");
		
		if (Flag.TRUE) {
			/*
			 * 출력형태 : 
			 */
			for (int i=0; i < lines.length; i++) {
				Print.println("System.out.println(String.format(\"    %s = [%%s]\", rs.getString(%s)));", words[i][0], words[i][2]);
			}
			
			Print.println();
		}

		if (Flag.TRUE) {
			/*
			 * 출력형태 : 
			 */
			for (int i=0; i < lines.length; i++) {
				Print.println("bean.set%s(rs.getString(%s));", words[i][1], words[i][2]);
			}
			
			Print.println();
		}
	}
	
	/**
	 * 
	 * printMacro
	 *
	 */
	public void printMacro()
	{
		Print.println("\n---------------------------------------------- Macro");
		
		if (Flag.TRUE) {
			/*
			 * 출력형태 : 
			 */
			for (int i=0; i < lines.length; i++) {
				Print.println("\twriteBean.set%s(readBean.get%s());", words[i][1], words[i][1]);
			}
			
			Print.println();
		}

		if (Flag.TRUE) {
			/*
			 * 출력형태 : 
			 */
			for (int i=0; i < lines.length; i++) {
				Print.println("\twriteBean.set%s(inList.get(i).get%s());", words[i][1], words[i][1]);
			}
			
			Print.println();
		}

		if (Flag.TRUE) {
			/*
			 * 출력형태 : 
			 */
			for (int i=0; i < lines.length; i++) {
				Print.println("\toutBean1.set%s(inBean1.get%s());", words[i][1], words[i][1]);
			}
			
			Print.println();
		}
	}
	
	/**
	 * 
	 * printCsvIo
	 *
	 */
	public void printCsvIo()
	{
		Print.println("\n---------------------------------------------- CsvIo");
		
		if (Flag.TRUE) {
			/*
			 * 출력형태 : map.put("PROCID"       , "procId"       );
			 */
			Print.println();
			for (int i=0; i < lines.length; i++) {
				Print.println("\tmap.put(%s, %s);", words[i][2], words[i][4]);
			}
			Print.println();
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
			/*
			 *        0               1                2                3
			 * [ULTRAEDITPROG] [UltraEditProg] ["ULTRAEDITPROG"] [ultraEditProg]
			 * [TIMESTAMP    ] [TimeStamp    ] ["TIMESTAMP"    ] [timeStamp    ]
			 * [OURHOUSE     ] [OurHouse     ] ["OURHOUSE"     ] [ourHouse     ]
			 * [THANKYOU     ] [ThankYou     ] ["THANKYOU"     ] [thankYou     ]
			 */
			String[] lines = {
					"DIV_CODE      :DivCode     ",
					"STEP_ID       :StepId      ",
					"EQP_ID        :EqpId       ",
					"MURA_SUM      :MuraSum     ",
					"CELLCNT       :CellCnt     ",
					"AVG_MURA_VALUE:AvgMuraValue",
			};
			
			try {
				GenSourceMain genSrc = new GenSourceMain(lines);
				if (Flag.TRUE) genSrc.print();
				if (Flag.TRUE) genSrc.printBean();
				if (Flag.TRUE) genSrc.printReader();
				if (Flag.TRUE) genSrc.printMacro();
				if (Flag.TRUE) genSrc.printCsvIo();
				
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * 
	 * test02
	 *
	 */
	private static void test02()
	{
		if (Flag.TRUE) {
			Print.println("[%-10.10s]", "Hello");           // [Hello     ]
			Print.println("[%-10.10s]", "Hello1234567890"); // [Hello12345]
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
		if (!Flag.TRUE) test02();
	}
}
