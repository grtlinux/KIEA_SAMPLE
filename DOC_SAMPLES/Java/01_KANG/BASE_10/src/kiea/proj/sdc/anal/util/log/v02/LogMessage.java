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

package kiea.proj.sdc.anal.util.log.v02;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import kiea.kr.co.tain.base.Flag;

/**
 * @author KangSeok
 * @date 2014. 8. 5.
 * @file_name LogMessage.java
 *
 */
public class LogMessage
{
	/**
	 * log message를 얻는다.
	 * 
	 * @param strStat
	 * @param strMessage
	 * @return
	 */
	public static String getLogMessage(String strStat, String strMessage)
	{
		String strLogMessage = null;
		
		if (Flag.TRUE) {
			try {
				
				/*
				 * 처리일시을 구한다.
				 * 앞 8자리 날짜는 실제 로그폴더를 나타낸다.
				 */
				String strDateTime = new SimpleDateFormat("yyyyMMdd HH:mm:ss.SSS", Locale.KOREA).format(new Date());
				
				/*
				 * 위치를 구한다.
				 */
				Exception e = new Exception("KANG SEOK is a good man. (강석)");
				StackTraceElement[] ste = e.getStackTrace();
				
				if (!Flag.TRUE) {
					/*
					 * 확인출력
					 */
					System.out.println("Thread.name = [" + Thread.currentThread().getName() + "]");
					
					for (int i=0; i < ste.length; i++) {
						System.out.println("ste[" + i + "].getClassName()  = [" + ste[i].getClassName()  + "]");
						System.out.println("ste[" + i + "].getFileName()   = [" + ste[i].getFileName()   + "]");
						System.out.println("ste[" + i + "].getLineNumber() = [" + ste[i].getLineNumber() + "]");
						System.out.println("ste[" + i + "].getMethodName() = [" + ste[i].getMethodName() + "]");
						System.out.println("ste[" + i + "].toString()      = [" + ste[i].toString()      + "]");
						System.out.println("--------------------------------------");
					}
				}

				int iPos = 1;
				if (Flag.TRUE) {
					/*
					 * log/debug/info/warn/error 를 찾는다.
					 */
					boolean flagPos = true;
					
					for (iPos = 0; iPos < ste.length; iPos ++) {
						if (flagPos) {
							/*
							 * log/debug/info/warn/error 가 나오는데까지 looping
							 */
							if ("log".equals(ste[iPos].getMethodName())
									|| "debug".equals(ste[iPos].getMethodName())
									|| "info".equals(ste[iPos].getMethodName())
									|| "warn".equals(ste[iPos].getMethodName())
									|| "error".equals(ste[iPos].getMethodName())
									) {
								flagPos = false;
								continue;
							}
						}
						
						if (!flagPos) {
							/*
							 * log/debug/info/warn/error 가 안나오는데까지 looping
							 */
							if (!"log".equals(ste[iPos].getMethodName())
									&& !"debug".equals(ste[iPos].getMethodName())
									&& !"info".equals(ste[iPos].getMethodName())
									&& !"warn".equals(ste[iPos].getMethodName())
									&& !"error".equals(ste[iPos].getMethodName())
									) {
								break;
							}
						}
					}

					/*
					 * log/debug/info/warn/error 를 호출한 위치를 출력할 수 있다.
					 * 아래는 만일 찾지 못했을 경우
					 * 그냥 처음 stacktrace 값으로 처리한다.
					 */
					if (iPos == ste.length) {
						iPos = 0;
					}
				}
				
				String strPosition = String.format("%s(%d).%s()"
						, ste[iPos].getClassName()
						, ste[iPos].getLineNumber()
						, ste[iPos].getMethodName()
						);
				
				/*
				 * 로그파일에 기록할 log information message 를 만든다.
				 */
				strLogMessage = String.format("%s %-5s [%s] %s"
						, strDateTime
						, strStat
						, strPosition
						, strMessage);
				
				if (!Flag.TRUE) System.out.println(strLogMessage);
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		return strLogMessage;
	}
	
	//////////////////////////////////////////////////////////////////
	//////////////////////////////////////////////////////////////////
	//////////////////////////////////////////////////////////////////
	//////////////////////////////////////////////////////////////////
	//////////////////////////////////////////////////////////////////

	/**
	 * test-01
	 */
	private static void test01()
	{
		if (Flag.TRUE) {
			String strLogMessage = LogMessage.getLogMessage("DEBUG", "이것은 LOG 입니다. 상태를 출력하거나 진행상황을 나타냅니다.");
			
			System.out.println("[" + strLogMessage + "]");
		}
	}
	
	/**
	 * main entry
	 * @param args
	 */
	public static void main(String[] args)
	{
		if (Flag.TRUE) test01();
	}
}
