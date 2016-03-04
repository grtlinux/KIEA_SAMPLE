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

package kiea.proj.sdc.anal.base.mura.inetd.v03;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;

import kiea.kr.co.tain.base.Flag;
import kiea.proj.sdc.anal.util.log.v02.Logger;

/**
 * @author KangSeok
 * @date 2014. 8. 5.
 * @file_name CommandThread.java
 *
 */
public class CommandThread extends Thread
{
	private Socket socket = null;
	
	private BufferedReader in = null;
	private PrintStream out = null;

	private String cmd = null;
	
	/**
	 * constructor
	 * @param socket
	 */
	public CommandThread(Socket socket)
	{
		this.socket = socket;

		try {
			//this.in = new BufferedReader(new InputStreamReader(this.socket.getInputStream(), DefaultValue.inetdCharset));
			this.in = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
			this.out = new PrintStream(this.socket.getOutputStream());

			if (!Flag.TRUE) {
				/*
				 * cmd macro 에서 stdout, stderr 로 출력되는 모든 내용을 socket을 통해 manager로 보낸다.
				 */
				System.setOut(this.out);
				System.setErr(this.out);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Thread.run() function
	 */
	public void run()
	{
		if (Flag.TRUE) {
			try {
				/*
				 * cmd 수신
				 */
				cmd = in.readLine();
				
				if (!Flag.TRUE) Logger.info("RECV CMD STRING : [%s]", cmd);
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		if (Flag.TRUE) {
			try {
				/*
				 * START
				 * RUNNING
				 * PROCESSING
				 * FINISH
				 * ERROR
				 */
				
				/*
				 * redirection for stdout, stderr to socket output
				 */
				if (Flag.TRUE) {
					/*
					 * command process
					 */
					CommandProcess process = new CommandProcess(cmd);
					process.beforeExec();
					process.exec();
					process.afterExec();
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
}
