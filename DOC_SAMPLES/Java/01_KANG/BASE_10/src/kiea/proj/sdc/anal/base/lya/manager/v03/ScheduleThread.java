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

package kiea.proj.sdc.anal.base.lya.manager.v03;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;

import kiea.kr.co.tain.base.Flag;
import kiea.proj.sdc.anal.util.ServiceManagerProperties;
import kiea.proj.sdc.anal.util.log.v02.Logger;

/**
 * @author KangSeok
 * @date 2014. 9. 16.
 * @file_name ScheduleThread.java
 *
 */
public class ScheduleThread extends Thread
{
	private String cmd = null;
	
	//private static final String IPADDR = DefValue.inetdIpAddr;
	//private static final int PORT = DefValue.inetdPort;
	
	private ServiceManagerProperties prop = null;
	private String strInetIp = null;
	private String strInetPort = null;

	private Socket socket = null;
	
	private BufferedReader in = null;
	private PrintStream out = null;

	/**
	 * constructor
	 * @param cmd
	 */
	public ScheduleThread(String cmd)
	{
		if (Flag.TRUE) {
			this.prop = ServiceManagerProperties.getInstance();
			this.strInetIp = this.prop.get("inetd.host.ip");
			this.strInetPort = this.prop.get("inetd.host.port");
		}
		
		this.cmd = cmd;
	}
	
	/**
	 * Thread.run() function
	 */
	public void run()
	{
		if (!Flag.TRUE) {
			/*
			 * for testing
			 */
			if (Flag.TRUE) Logger.info("DEMO THREAD ....[%s]", this.cmd);
			
			try { Thread.sleep(10 * 1000); } catch (InterruptedException e) {}
		}
		
		if (Flag.TRUE) {
			/*
			 * for socket processing
			 */

			try {
				socket = new Socket(strInetIp, Integer.parseInt(this.strInetPort));
				
				//this.in = new BufferedReader(new InputStreamReader(this.socket.getInputStream(), DefaultValue.inetdCharset));
				this.in = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
				this.out = new PrintStream(this.socket.getOutputStream());
				
				if (Flag.TRUE) {
					/*
					 * send cmd
					 */
					this.out.println(this.cmd);
					
					if (!Flag.TRUE) Logger.info("SEND CMD STRING : [%s]", cmd);
				}
				
				if (Flag.TRUE) {
					/*
					 * receive the result of the processing about cmd
					 */
					
					String line = null;
					
					while ((line = this.in.readLine()) != null) {
						System.out.println("MANAGER> " + line);
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
	}

	///////////////////////////////////////////////////////////////////////////
	
	private static void test01()
	{
		if (Flag.TRUE) {
			
		}
	}
	
	public static void main(String[] args)
	{
		if (Flag.TRUE) test01();
	}
}
