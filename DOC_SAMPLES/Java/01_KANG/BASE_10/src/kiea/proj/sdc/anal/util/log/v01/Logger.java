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

package kiea.proj.sdc.anal.util.log.v01;

import kiea.kr.co.tain.base.Flag;

/**
 * @author KangSeok
 * @date 2014. 8. 5.
 * @file_name Logger.java
 *
 */
public class Logger
{
	/*
	 * Logger instance
	 */
	private static Logger logger = null;
	
	/*
	 * LogQueue instance
	 */
	private static LogQueue logQueue = null;
	
	/*
	 * keyPath, logFile name
	 */
	private static String keyPath = null;
	private static String logFile = null;
	
	private Logger() throws Exception
	{
		if (!Flag.TRUE) System.out.println("MESSAGE : Logger Constructor..");
	}
	
	public static synchronized Logger getInstance(String keyPath, String logFile) throws Exception
	{
		if (Flag.TRUE) {
			try {
				if (Logger.logger == null) {
					Logger.logger = new Logger();
					
					Logger.keyPath = keyPath;
					Logger.logFile = logFile;
					
					Logger.logQueue = new LogQueue();
					
					/*
					 * 출력을 처리할 thread 를 생성하여 실행한다.
					 */
					new LogWriter(Logger.logQueue, Logger.keyPath, Logger.logFile).start();
				}
			} catch (Exception e) {
				e.printStackTrace();
				throw new IllegalStateException("ERROR : Logger.getInstance()");
			}
		}
		
		return logger;
	}
	
	//////////////////////////////////////////////////////////////////

	private static synchronized void printLog(String strStat, String strMessage)
	{
		if (Flag.TRUE) {
			/*
			 * instance 생성을 확인한다.
			 */
			if (Logger.logger == null) {
				System.err.println("ERROR : Logger.printLog() : anal.base.path isn't defined....");
				System.exit(-1);
			}
		}
		
		if (Flag.TRUE) {
			/*
			 * log 메시지를 생성한다.
			 */
			String strLogMessage = LogMessage.getLogMessage(strStat, strMessage);
			if (!Flag.TRUE) System.out.println("[" + strLogMessage + "]");
			
			/*
			 * 메시지를 LogQueue에 넣는다.
			 */
			Logger.logQueue.put(strLogMessage);
		}
	}
	
	private static void printLog(String strStat, String format, Object... args)
	{
		if (Flag.TRUE) {
			printLog(strStat, String.format(format, args));
		}
	}

	//////////////////////////////////////////////////////////////////
	
	public static void log(String format, Object... args)
	{
		if (Flag.TRUE) {
			printLog("LOG", format, args);
		}
	}

	public static void log(String strMessage)
	{
		if (Flag.TRUE) {
			printLog("LOG", strMessage);
		}
	}
	
	//////////////////////////////////////////////////////////////////

	public static void debug(String format, Object... args)
	{
		if (Flag.TRUE) {
			printLog("DEBUG", format, args);
		}
	}

	public static void debug(String strMessage)
	{
		if (Flag.TRUE) {
			printLog("DEBUG", strMessage);
		}
	}
	
	//////////////////////////////////////////////////////////////////

	public static void info(String format, Object... args)
	{
		if (Flag.TRUE) {
			printLog("INFO", format, args);
		}
	}

	public static void info(String strMessage)
	{
		if (Flag.TRUE) {
			printLog("INFO", strMessage);
		}
	}
	
	//////////////////////////////////////////////////////////////////

	public static void warn(String format, Object... args)
	{
		if (Flag.TRUE) {
			printLog("WARN", format, args);
		}
	}

	public static void warn(String strMessage)
	{
		if (Flag.TRUE) {
			printLog("WARN", strMessage);
		}
	}
	
	//////////////////////////////////////////////////////////////////

	public static void error(String format, Object... args)
	{
		if (Flag.TRUE) {
			printLog("ERROR", format, args);
		}
	}

	public static void error(String strMessage)
	{
		if (Flag.TRUE) {
			printLog("ERROR", strMessage);
		}
	}
	
	//////////////////////////////////////////////////////////////////
	
	public static void exit()
	{
		if (Flag.TRUE) {
			Logger.logQueue.put("EXIT");
		}
	}

	//////////////////////////////////////////////////////////////////
	//////////////////////////////////////////////////////////////////
	//////////////////////////////////////////////////////////////////
	//////////////////////////////////////////////////////////////////
	//////////////////////////////////////////////////////////////////
	
	private static void test01()
	{
		if (!Flag.TRUE) {
			try {
				Logger.getInstance(null, "INETD.log");
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			Logger.log("이것은 LOG 입니다. 상태를 출력하거나 진행상황을 나타냅니다.");
			Logger.debug("이것은 DEBUG 입니다. debug를 위한 정보를 남긴다. 운영시에는 기록하지 않는다.");
			Logger.info("이것은 INFO 입니다. info를 위한 정보를 남긴다. 운영시에는 기록하지 않는다.");
			Logger.warn("이것은 WARN 입니다. warn를 위한 정보를 남긴다. 운영시에는 기록하지 않는다.");
			Logger.error("이것은 ERROR 입니다. 프로세스 진행에 치명적인 영향을 주는 error를 남긴다.");
		}

		if (!Flag.TRUE) {
			try {
				Logger.getInstance(null, "MANAGER.log");
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			Logger.log("이것은 LOG 입니다. 상태를 출력하거나 진행상황을 나타냅니다.");
			Logger.debug("이것은 DEBUG 입니다. debug를 위한 정보를 남긴다. 운영시에는 기록하지 않는다.");
			Logger.info("이것은 INFO 입니다. info를 위한 정보를 남긴다. 운영시에는 기록하지 않는다.");
			Logger.warn("이것은 WARN 입니다. warn를 위한 정보를 남긴다. 운영시에는 기록하지 않는다.");
			Logger.error("이것은 ERROR 입니다. 프로세스 진행에 치명적인 영향을 주는 error를 남긴다.");
		}

		if (Flag.TRUE) {
			try {
				Logger.getInstance("KEY_HHMM", "LOGGER.log");
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			Logger.log("이것은 LOG 입니다. 상태를 출력하거나 진행상황을 나타냅니다.");
			Logger.debug("이것은 DEBUG 입니다. debug를 위한 정보를 남긴다. 운영시에는 기록하지 않는다.");
			Logger.info("이것은 INFO 입니다. info를 위한 정보를 남긴다. 운영시에는 기록하지 않는다.");
			Logger.warn("이것은 WARN 입니다. warn를 위한 정보를 남긴다. 운영시에는 기록하지 않는다.");
			Logger.error("이것은 ERROR 입니다. 프로세스 진행에 치명적인 영향을 주는 error를 남긴다.");
		}
	}
	
	public static void main(String[] args)
	{
		if (Flag.TRUE) test01();
	}
}
