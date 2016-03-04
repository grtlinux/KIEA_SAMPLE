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

package sdc.anal.lya.macro.A90.SEND_RESULT_MSG.v04;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;

import kiea.kr.co.tain.base.Flag;
import kiea.kr.co.tain.util.Print;
import kiea.kr.co.tain.util.SystemProperties;
import kiea.proj.sdc.anal.util.ServiceMacroProperties;
import kiea.proj.sdc.anal.util.log.v02.Logger;

/**
 * @author KangSeok
 * @date 2014. 9. 28.
 * @file_name FtpReportList.java
 *
 */
public class SendResultMessage
{
	private static final String HOST = "127.0.0.1";
	private static final String PORT = "7600, 7700";
	
	private String jobId = null;
	
	private String strHost = null;
	private String strPort = null;
	
	private String[] arrPort = null;
	
	public SendResultMessage(String jobId)
	{
		if (Flag.TRUE) {
			this.jobId = jobId;  // AR 010 140928 A 0000
		}
		
		if (Flag.TRUE) {
			ServiceMacroProperties propMacro = ServiceMacroProperties.getInstance();
			
			this.strHost = propMacro.get("anal.netqueue.ip"  , HOST);
			//this.strHost = HOST;
			this.strPort = propMacro.get("anal.netqueue.port", PORT);
			this.arrPort = this.strPort.replace(" ", "").split(",");
					
			if (Flag.TRUE) Print.println("SEND_RESULT_MSG => [%s] [%s] [%s]", this.strHost, this.strPort, this.jobId);
			if (Flag.TRUE) Logger.info("SEND_RESULT_MSG => [%s] [%s] [%s]", this.strHost, this.strPort, this.jobId);
		}
	}
	
	public void sendMessage()
	{
		if (!Flag.TRUE) {
			/*
			 * LCD.LYA.DAEMON:JOB_ID,STATUS,MESSAGE"
			 * AR010141110A3143,OK,Complete
			 */
			
			String ipAddr = strHost;
			int port = Integer.parseInt(strPort);
			String msg = "LCD.LYA.DAEMON:" + jobId + ",OK,Complete";
			
			Socket socket = null;
			
			BufferedReader in = null;
			PrintStream out = null;
			
			try {
				socket = new Socket(ipAddr, port);
				
				in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
				out = new PrintStream(socket.getOutputStream());
				
				if (Flag.TRUE) {
					/*
					 * send cmd
					 */
					String str = "LCD.LYA.DAEMON:<JOB_ID>,STATUS,MESSAGE";
					str = msg;
					out.println(str);
					
					if (Flag.TRUE) Print.println("SEND> [%s]", str);
					if (Flag.TRUE) Logger.debug("SEND> [%s]", str);
				}
				
				if (Flag.TRUE) {
					/*
					 * receive the result of the processing about cmd
					 */
					
					String line = null;
					
					while ((line = in.readLine()) != null) {
						if (Flag.TRUE) Print.println("RECV> [%s]", line);
						if (Flag.TRUE) Logger.debug("RECV> [%s]", line);
						break;
					}
				}

			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				try {
					if (socket != null) {
						socket.close();
						in.close();
						out.close();
					}
				} catch (Exception e) {}
			}
		}

		if (Flag.TRUE) {
			/*
			 * DATE : 2014.12.05 : 7600, 7700 듀얼포트 사용
			 */
			for (int i=0; i < arrPort.length; i++) {
				/*
				 * LCD.LYA.DAEMON:JOB_ID,STATUS,MESSAGE"
				 * AR010141110A3143,OK,Complete
				 */
				
				String ipAddr = strHost;
				int port = Integer.parseInt(arrPort[i]);
				String msg = "LCD.LYA.DAEMON:" + jobId + ",OK,Complete-" + (i+1);
				
				Socket socket = null;
				
				BufferedReader in = null;
				PrintStream out = null;
				
				try {
					socket = new Socket(ipAddr, port);
					
					in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
					out = new PrintStream(socket.getOutputStream());
					
					if (Flag.TRUE) {
						/*
						 * send cmd
						 */
						String str = "LCD.LYA.DAEMON:<JOB_ID>,STATUS,MESSAGE";
						str = msg;
						out.println(str);
						
						if (Flag.TRUE) Print.println("SEND-%d> [%s]", i+1, str);
						if (Flag.TRUE) Logger.debug("SEND-%d> [%s]", i+1, str);
					}
					
					if (Flag.TRUE) {
						/*
						 * receive the result of the processing about cmd
						 */
						
						String line = null;
						
						while ((line = in.readLine()) != null) {
							if (Flag.TRUE) Print.println("RECV-%d> [%s]", i+1, line);
							if (Flag.TRUE) Logger.debug("RECV-%d> [%s]", i+1, line);
							break;
						}
					}

				} catch (Exception e) {
					e.printStackTrace();
				} finally {
					try {
						if (socket != null) {
							socket.close();
							in.close();
							out.close();
						}
					} catch (Exception e) {}
				}
			}
			
			// 메시지 전송 종료
		}
	}
	
	///////////////////////////////////////////////////////////////////////////
	
	private static void test01()
	{
		if (Flag.TRUE) {
			SystemProperties.setTesting("010");
		}

		if (Flag.TRUE) {
			new SendResultMessage("AR010141110A3143").sendMessage();
		}
	}
	
	private static void test02()
	{
		if (Flag.TRUE) {
			String strPorts = "   7600   , 7700  ";
			String[] arrPort = strPorts.replace(" ", "").split(",");
			
			for (String port : arrPort) {
				System.out.println("[" + port + "]");
			}
		}
	}
	
	public static void main(String[] args)
	{
		if (!Flag.TRUE) test01();
		if (Flag.TRUE) test02();
	}
}
