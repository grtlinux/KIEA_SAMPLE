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

package kiea.priv.zzz.etc.socket.t02_thread;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author KangSeok
 * @date 2014. 9. 26.
 * @file_name ServerTestMain.java
 *
 */
class JobThread extends Thread
{
	private static boolean flag = true;
	
	private Socket socket;
	private BufferedReader in = null;
	private PrintStream out = null;
	
	public JobThread(Socket socket) throws Exception
	{
		if (flag) {
			try {
				if (flag) System.out.println("STATUS : connection...");

				this.socket = socket;
				this.in = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
				this.out = new PrintStream(this.socket.getOutputStream());
			} catch (Exception e) {
				throw e;
			}
		}
	}
	
	public void run()
	{
		if (flag) {
			try {
				String reqMsg = null;
				String resMsg = "UI.REPLY.RESULT:JOB_ID";
				
				while ((reqMsg = this.in.readLine()) != null) {
					if (flag) System.out.println("RECV> [" + reqMsg + "]");
					
					if (flag) {
						System.out.println("SEND> [" + resMsg + "]");
						this.out.println(resMsg);
					}
					
					break;
				}
				
				if (flag) System.out.println("STATUS : disconnection .....");
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				try {
					if (this.socket != null) {
						this.socket.close();
						this.in.close();
						this.out.close();
					}
				} catch (Exception e) {}
			}
		}
	}
}

public class ServerTestMain
{
	private static boolean flag = true;
	
	private static final int PORT = 7800;
	
	private static void test01(String[] args)
	{
		if (!flag) {
			if (args.length != 1) {
				System.out.println("USAGE : java Main [PORT]");
				System.exit(1);
			}
		}

		if (flag) {

			ServerSocket serverSocket = null;
			
			try {
				/*
				 * Create ServerSocket
				 */
				serverSocket = new ServerSocket(PORT);
				if (flag) System.out.println("STATUS : ServerSocket Listening, port is " + PORT);
				
				while (true) {
					/*
					 * listen and wait for a connection
					 */
					Socket socket = serverSocket.accept();

					/*
					 * Job thread
					 */
					new JobThread(socket).start();
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				try {
					if (serverSocket != null) {
						serverSocket.close();
					}
				} catch (Exception e) {}
			}
		}
	}
	
	public static void main(String[] args)
	{
		if (flag) test01(args);
	}
}
