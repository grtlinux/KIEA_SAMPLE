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
import java.net.Socket;

/**
 * @author KangSeok
 * @date 2014. 9. 26.
 * @file_name ClientTestMain.java
 *
 */
public class ClientTestMain
{
	private static boolean flag = true;
	
	private static final String IPADDR = "127.0.0.1";
	private static final int PORT = 7800;
	
	private static void test01(String[] args)
	{
		if (!flag) {
			if (args.length != 3) {
				System.out.println("USAGE : java Main [IP] [PORT] [MSG]");
				System.exit(1);
			}
		}

		if (flag) {
			
			Socket socket = null;
			
			BufferedReader in = null;
			PrintStream out = null;
			
			try {
				socket = new Socket(IPADDR, PORT);
				
				in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
				out = new PrintStream(socket.getOutputStream());
				
				if (flag) {
					/*
					 * send cmd
					 */
					String str = "LCD.LYA.DAEMON:JOB_ID,STATUS,MESSAGE";
					out.println(str);
					
					if (flag) System.out.println("SEND> [" + str + "]");
				}
				
				if (flag) {
					/*
					 * receive the result of the processing about cmd
					 */
					String line = null;
					
					while ((line = in.readLine()) != null) {
						if (flag) System.out.println("RECV> [" + line + "]");
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
	}

	public static void main(String[] args)
	{
		if (flag) test01(args);
	}
}
