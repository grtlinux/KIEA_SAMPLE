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

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import kiea.kr.co.tain.base.Flag;
import kiea.kr.co.tain.util.DateTime;
import kiea.kr.co.tain.util.Print;
import kiea.kr.co.tain.util.SystemProperties;
import kiea.proj.sdc.anal.common.FileValue;
import kiea.proj.sdc.anal.util.AnalProperties;
import kiea.proj.sdc.anal.util.StrUtil;

/**
 * @author KangSeok
 * @date 2014. 8. 5.
 * @file_name LogFile.java
 *
 */
public class LogFile
{
	private static LogFile instance = null;
	
	private String logPath = null;
	
	private String svcPath = null;
	private String datePath = null;
	private String jobId = null;
	private String logFile = null;
	private String strLogMessage = null;
	
	/**
	 * constructor
	 */
	private LogFile()
	{
		if (!Flag.TRUE) System.out.println("STATUS : LogFile constructor...");
		
		if (Flag.TRUE) {
			/*
			 * TODO : 2014.10.08 : BasePath 클래스 수정 필요
			 */
			//logPath = BasePath.getInstance().getLogPath();
		}
	}

	/**
	 * get the instance of LogFile class
	 * @return
	 */
	public static synchronized LogFile getInstance()
	{
		if (Flag.TRUE) {
			if (instance == null) {
				instance = new LogFile();
			}
		}
		
		return instance;
	}
	
	/**
	 * 
	 * @param jobId
	 * @param logFile
	 * @param strLogMessage
	 * @return
	 */
	public boolean write(String svcPath, String jobId, String logFile, String strLogMessage)
	{
		boolean ret = false;
		
		String chkName = null;
		File chkFile = null;

		if (Flag.TRUE) {
			/*
			 * 기본정보를 저장한다.
			 */
			this.svcPath = svcPath;
			this.jobId = jobId;
			this.logFile = logFile;
			this.strLogMessage = strLogMessage;
			
			if (this.jobId == null) {
				/*
				 * TODO : 2014.07.29
				 *     strLogMessage 의 앞부분이 날짜인지를 확인하는 로직을 추가한다.
				 *     날짜가 아닌 문자열이면 쓰레기통에 버린다. -> trash
				 * TODO : 2014.10.23
				 *     메시지에서 날짜를 얻는 방식과
				 *     JOBID에서 날짜를 얻는 방식을 추가함.
				 */
				if (this.strLogMessage == null || this.strLogMessage.length() < 8) {
					this.datePath = DateTime.getDate(2);  // YYYYMMDD
				} else if (!DateTime.isDateStr(this.strLogMessage.substring(0, 8))) {
					this.datePath = DateTime.getDate(2);  // YYYYMMDD
				} else {
					this.datePath = this.strLogMessage.substring(0, 8);
				}
			} else {
				/*
				 * TODO : 2014.10.22
				 * jobId에서 날짜를 구한다.
				 */
				this.datePath = StrUtil.getDateFromJobId(this.jobId);
			}
			this.logPath = this.svcPath + "/log";
			
			if (!Flag.TRUE) System.out.println(String.format("## [%s] [%s] [%s] [%s] [%s] [%s]"
					, this.logPath, this.svcPath, this.datePath, this.jobId, this.logFile, this.strLogMessage));
		}
		
		if (Flag.TRUE) {
			/*
			 * 날짜 폴더를 확인하여 없으면 생성한다.
			 */
			chkName = this.logPath + FileValue.SEP + this.datePath;
			chkFile = new File(chkName);
			if (!chkFile.exists()) {
				/*
				 * 날짜 폴더가 없다. 그럼 생성한다.
				 */
				chkFile.mkdir();
			}
			
			if (this.jobId != null) {
				/*
				 * 키 폴더를 확인하여 없으면 생성한다.
				 */
				chkName += FileValue.SEP + this.jobId;
				chkFile = new File(chkName);
				if (!chkFile.exists()) {
					/*
					 * 키 폴더가 없다. 그럼 생성한다.
					 */
					chkFile.mkdir();
				}
			}
			
			/*
			 * 로그파일을 확인하여 없으면 생성한다.
			 */
			chkName += FileValue.SEP + this.logFile;
			chkFile = new File(chkName);
			if (!chkFile.exists()) {
				/*
				 * 로그파일이 없다. 그럼 생성한다.
				 */
				try {
					ret = chkFile.createNewFile();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		
		if (Flag.TRUE) {
			/*
			 * 로그파일에 기록한다.
			 * UTF-8로 기록된다.
			 */
			try {
				FileWriter fw = new FileWriter(chkName, true);
				fw.append(this.strLogMessage);
				fw.append("\n");
				fw.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			if (!Flag.TRUE) System.out.println(String.format("## [%s] [%s] [%s] [%s] [%s] [%s]"
					, this.logPath, this.datePath, this.jobId, this.logFile, chkName, this.strLogMessage));
		}
		
		return ret;
	}
	
	///////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////
	
	/**
	 * test-01
	 */
	private static void test01()
	{
		if (Flag.TRUE) {
			SystemProperties.setTesting("010");
		}

		String svcPath = AnalProperties.getInstance().getServiceBasePath();
		if (Flag.TRUE)  LogFile.getInstance().write(svcPath, null, "DETECTOR.log", "20140729 이것은 INFO 입니다. info를 위한 정보를 남긴다. 운영시에는 기록하지 않는다.");
		if (Flag.TRUE)  LogFile.getInstance().write(svcPath, null, "MANAGER.log", "20140729 이것은 INFO 입니다. info를 위한 정보를 남긴다. 운영시에는 기록하지 않는다.");
		if (Flag.TRUE)  LogFile.getInstance().write(svcPath, null, "INETD.log", "20140729 이것은 LOG 입니다. 상태를 출력하거나 진행상황을 나타냅니다.");
		if (Flag.TRUE)  LogFile.getInstance().write(svcPath, "KEY_HHMM", "MACRO.log", "20140729 이것은 ERROR 입니다. 프로세스 진행에 치명적인 영향을 주는 error를 남긴다.");
	}
	
	private static void test02()
	{
		if (Flag.TRUE) {
			Print.println("[%s]", "20" + "AR010141022A0000".substring(5, 11));
		}
	}
	
	private static void test03()
	{
		if (Flag.TRUE) {
			String str1 = "20141022 1234567890 ABCDEFG";
			String str2 = "1234567890 ABCDEFG";
			String str3 = "ABCDEFG";
			//String str4 = null;
			
			Print.println("[%s]", str1.substring(0, Math.min(8, str1.length())));
			Print.println("[%s]", str2.substring(0, Math.min(8, str2.length())));
			Print.println("[%s]", str3.substring(0, Math.min(8, str3.length())));
			//Print.println("[%s]", str4.substring(0, Math.min(8, str4.length())));  // Null pointer access : The variable can only be null at this line
			
			Print.println("[%s]", DateTime.isDateStr(str1.substring(0, Math.min(8, str1.length()))));
			Print.println("[%s]", DateTime.isDateStr(str2.substring(0, Math.min(8, str2.length()))));
			Print.println("[%s]", DateTime.isDateStr(str3.substring(0, Math.min(8, str3.length()))));
			//Print.println("[%s]", DateTime.isDateStr(str4.substring(0, Math.min(8, str4.length()))));  // Null pointer access : The variable can only be null at this line
		}
	}
	
	/**
	 * main entry
	 * @param args
	 */
	public static void main(String[] args)
	{
		if (!Flag.TRUE) test01();
		if (!Flag.TRUE) test02();
		if (Flag.TRUE) test03();
	}
}
