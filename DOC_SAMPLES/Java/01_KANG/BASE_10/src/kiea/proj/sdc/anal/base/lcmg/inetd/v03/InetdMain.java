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

package kiea.proj.sdc.anal.base.lcmg.inetd.v03;

import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

import kiea.kr.co.tain.base.Flag;
import kiea.kr.co.tain.util.Print;
import kiea.kr.co.tain.util.SystemProperties;
import kiea.proj.sdc.anal.common.DefValue;
import kiea.proj.sdc.anal.util.AnalProperties;
import kiea.proj.sdc.anal.util.BasePath;
import kiea.proj.sdc.anal.util.ServiceInetdProperties;
import kiea.proj.sdc.anal.util.log.v02.Logger;

/**
 * @author KangSeok
 * @date 2014. 8. 5.
 * @file_name InetdMain.java
 *
 */
public class InetdMain
{
	private static final int PORT = DefValue.inetdPort;
	
	private ServiceInetdProperties prop = null;
	private String logFile = null;
	private String strPort = null;
	private String[] allowIp = null;

	private static ServerSocket serverSocket = null;
	
	public InetdMain()
	{
		if (Flag.TRUE) {
			this.prop = ServiceInetdProperties.getInstance();
			this.logFile = this.prop.get("anal.service.log.file");
			this.strPort = this.prop.get("anal.service.listen.port");
			
			this.allowIp = this.prop.get("anal.service.allow.ip").split(",");
			for (int i=0; i < this.allowIp.length; i++)
				this.allowIp[i] = this.allowIp[i].trim();
		}
		
		if (Flag.TRUE) {
			/*
			 * 반드시 먼저 선언해야 할 것
			 * 1. base path : ..../qaf 까지
			 * 2. 사용할 로그정보
			 */
			BasePath.getInstance();

			try {
				Logger.getInstance(AnalProperties.getInstance().getServiceBasePath(), null, this.logFile);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	public void execute()
	{
		if (Flag.TRUE) {

			try {
				/*
				 * Create ServerSocket
				 */
				serverSocket = new ServerSocket(Integer.parseInt(this.strPort));
				Logger.info("STATUS : ServerSocket Listening, port is %s", this.strPort);
				
				while (true) {
					/*
					 * listen and wait for a connection
					 */
					Socket socket = serverSocket.accept();
					String address = socket.getInetAddress().getHostAddress();
					
					if (Flag.TRUE) {
						boolean flgDeny = true;
						
						for (String ip : allowIp) {
							if (address.equals(ip)) {
								flgDeny = false;
								break;
							}
						}
						
						if (flgDeny) {
							socket.close();

							Logger.info("STATUS : deny the address [%s], and close the socket...", address);
							if (!Flag.TRUE) Print.println("STATUS : deny the address [%s], and close the socket...", address);
							continue;
						}
					}
					
					Logger.info("STATUS : event of connection from manager...[%s]", address);
					if (!Flag.TRUE) Print.println("STATUS : event of connection from manager...[%s]", address);

					if (Flag.TRUE) {
						/*
						 * if a connection, create CommandThread and do the job...
						 */
						CommandThread cmdThread = new CommandThread(socket);
						cmdThread.start();
					} else {
						if (Flag.TRUE) {
							/*
							 * TODO : 2014.10.07 : FOR TESTING
							 * 
							 * InetAddress inet = InetAddress.getByName("naver.com");
							 * InetAddress inet = InetAddress.getByName("125.209.222.141");
							 * System.out.println(inet.getHostAddress());  // 125.209.222.141
							 * 
							 * InetAddress[] list = InetAddress.getAllByName("naver.com");
							 * for (InetAddress inet : list)
							 *     System.out.println(inet.getHostAddress());
							 * 
							 */
							InetAddress inet = socket.getInetAddress();
							Print.println("inet.getCanonicalHostName() = %s", inet.getCanonicalHostName()); // KangSeok-PC
							Print.println("inet.getHostAddress      () = %s", inet.getHostAddress      ()); // 11.94.177.34
							Print.println("inet.getHostName         () = %s", inet.getHostName         ()); // KangSeok-PC

							byte[] num = inet.getAddress();
							Print.println("%d.%d.%d.%d", num[0] & 0xFF, num[1] & 0xFF, num[2] & 0xFF, num[3] & 0xFF);
						}
						
						socket.close();
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {

				try {
					if (serverSocket != null)
						serverSocket.close();
					
				} catch (Exception e2) {}
			}
			
			Logger.exit();
		}
	}
	
	///////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////

	/**
	 * 서버소켓을 생성하여 manager로부터의 실행요청을 받아
	 * cmdThread를 생성하여 처리하는 단일 프로세스를 구성한다.
	 * 
	 * TODO : 2014.07.29 : 접근 IP를 통제한다.
	 */
	private static void test01()
	{
		if (Flag.TRUE) {
			/*
			 * 반드시 먼저 선언해야 할 것
			 * 1. base path : ..../qaf 까지
			 * 2. 사용할 로그정보
			 */
			BasePath.getInstance();

			try {
				Logger.getInstance(AnalProperties.getInstance().getServiceBasePath(), null, DefValue.inetdLogName);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		if (Flag.TRUE) {

			try {
				/*
				 * Create ServerSocket
				 */
				serverSocket = new ServerSocket(PORT);
				Logger.info("STATUS : ServerSocket Listening, port is %d", PORT);
				
				while (true) {
					/*
					 * listen and wait for a connection
					 */
					Socket socket = serverSocket.accept();
					Logger.info("STATUS : event of connection from manager...");
					
					/*
					 * if a connection, create CommandThread and do the job...
					 */
					CommandThread cmdThread = new CommandThread(socket);
					cmdThread.start();
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {

				try {
					if (serverSocket != null)
						serverSocket.close();
					
				} catch (Exception e2) {}
			}
			
			Logger.exit();
		}
	}
	
	private static void test02()
	{
		if (!Flag.TRUE) {
			SystemProperties.setTesting("010");
		}
		
		if (Flag.TRUE) {
			new InetdMain().execute();
		}
	}
	
	public static void main(String[] args)
	{
		if (!Flag.TRUE) test01();
		if (Flag.TRUE) test02();
	}
}
