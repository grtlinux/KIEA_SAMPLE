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

package kiea.proj.sdc.anal.base.lya.inetd.v01;

import java.net.ServerSocket;
import java.net.Socket;

import kiea.kr.co.tain.base.Flag;
import kiea.proj.sdc.anal.common.DefValue;
import kiea.proj.sdc.anal.util.BasePath;
import kiea.proj.sdc.anal.util.log.v01.Logger;

/**
 * @author KangSeok
 * @date 2014. 8. 5.
 * @file_name InetdMain.java
 *
 */
public class InetdMain
{
	private static final int PORT = DefValue.inetdPort;
	
	private static ServerSocket serverSocket = null;
	
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
				Logger.getInstance(null, DefValue.inetdLogName);
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
	
	public static void main(String[] args)
	{
		if (Flag.TRUE) test01();
	}
}
