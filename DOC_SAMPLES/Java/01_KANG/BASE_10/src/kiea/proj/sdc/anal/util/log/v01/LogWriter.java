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
 * @file_name LogWriter.java
 *
 */
public class LogWriter extends Thread
{
	// waiting time to get message from the queue 
	private static final int WAIT_TIME = 60 * 1000;
	
	private LogQueue logQueue = null;
	
	private String keyPath = null;
	private String logFile = null;
	
	/**
	 * constructor
	 * 
	 * @param logQueue
	 * @param keyPath
	 * @param logFile
	 */
	public LogWriter(LogQueue logQueue, String keyPath, String logFile)
	{
		if (Flag.TRUE) {
			this.logQueue = logQueue;
			this.keyPath = keyPath;
			this.logFile = logFile;
			
			/*
			if (this.keyPath == null) {
				keyLogFile = FileValue.SEP + this.logFile;
			} else {
				keyLogFile = FileValue.SEP + this.keyPath + FileValue.SEP + this.logFile;
			}
			*/
		}
	}

	/**
	 * thread run function
	 */
	public void run()
	{
		if (Flag.TRUE) {
			while (true) {
				
				String strLogMessage = (String) logQueue.get(WAIT_TIME);
				if (strLogMessage == null)
					continue;
				
				if ("EXIT".equals(strLogMessage))
					break;
				
				if (!Flag.TRUE) System.out.println(String.format("[%s] [%s] [%s]", this.keyPath, this.logFile, strLogMessage));
				
				LogFile.getInstance().write(keyPath, logFile, strLogMessage);
			}
		}
	}
	
	///////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////

	private static void test01()
	{
		if (!Flag.TRUE) {
			
			LogQueue queue = new LogQueue();
			new LogWriter(queue, null, "INETD.log").start();
			
			queue.put("Hello, world!!!");
		}

		if (!Flag.TRUE) {
			
			LogQueue queue = new LogQueue();
			new LogWriter(queue, null, "MANAGER.log").start();
			
			queue.put("이것은 LOG 입니다. 상태를 출력하거나 진행상황을 나타냅니다.");
		}

		if (Flag.TRUE) {
			
			LogQueue queue = new LogQueue();
			new LogWriter(queue, "KEY_HHMM", "LOGGER.log").start();
			
			queue.put("이것은 ERROR 입니다. 프로세스 진행에 치명적인 영향을 주는 error를 남긴다.");
		}
	}
	
	public static void main(String[] args)
	{
		if (Flag.TRUE) test01();
	}
}
