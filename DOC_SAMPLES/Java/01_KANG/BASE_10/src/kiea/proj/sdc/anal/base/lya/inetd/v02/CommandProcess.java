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

package kiea.proj.sdc.anal.base.lya.inetd.v02;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import kiea.kr.co.tain.base.Flag;
import kiea.proj.sdc.anal.common.DefValue;
import kiea.proj.sdc.anal.util.BasePath;
import kiea.proj.sdc.anal.util.log.v01.Logger;

/**
 * @author KangSeok
 * @date 2014. 8. 5.
 * @file_name CommandProcess.java
 *
 */
public class CommandProcess
{
	private String cmd = null;
	
	/**
	 * constructor
	 * 
	 * @param cmd
	 */
	public CommandProcess(String cmd)
	{
		if (Flag.TRUE) {
			/*
			 * command를 저장한다.
			 */
			this.cmd = cmd;
			
			if (Flag.TRUE) Logger.info("<%s>  [CMD:%s]", DefValue.PROJ_NAME, this.cmd);
		}
	}
	
	/**
	 * before-process job before the exec job
	 */
	public void beforeExec()
	{
		if (Flag.TRUE) {
		}
	}
	
	/**
	 * exec job for command processing
	 */
	public void exec()
	{
		if (Flag.TRUE) {

			try {
				List<String> cmdList = new ArrayList<String>();
				
				/*
				 * 명령어를 실행할 windows command를 실행한다.
				 * TODO : 2014.07.28 : 명령어 실행 command를 windows 와 unix로 분리
				 */
				
				
				ProcessBuilder processBuilder = new ProcessBuilder();
				
				if (Flag.TRUE) {
					Properties prop = System.getProperties();
					String osName = prop.getProperty("os.name");
					if (osName.indexOf("Win", 0) >= 0) {
						// Window OS
						if (!Flag.TRUE) {
							cmdList.add("cmd");
							cmdList.add("/c");
						} else {
							for (String param : "cmd /c".split("\\s")) {
								cmdList.add(param);
							}
						}

						/*
						 * 명령어를 분리한다. split
						 */
						if (!Flag.TRUE) {
							String[] items = this.cmd.split("\\s");
							for (int i=0; i < items.length; i++) {
								cmdList.add(items[i]);
							}
						} else {
							for (String param : this.cmd.split("\\s")) {
								cmdList.add(param);
							}
						}

						processBuilder.directory(new File(BasePath.getInstance().getExePath()));
					} else {
						// Other OS. we suppose the UNIX system
						cmdList.add("ksh");

						/*
						 * 명령어를 분리한다. split
						 */
						if (!Flag.TRUE) {
							String[] items = this.cmd.split("\\s");
							for (int i=0; i < items.length; i++) {
								cmdList.add(items[i]);
							}
						} else {
							for (String param : this.cmd.split("\\s")) {
								cmdList.add(param);
							}
						}
						
						processBuilder.directory(new File(BasePath.getInstance().getShellPath()));
					}
				}
				
				processBuilder.command(cmdList);
				Process p = processBuilder.start();

				// PrintWriter in = new PrintWriter(p.getOutputStream());
				BufferedReader out = new BufferedReader(new InputStreamReader(p.getInputStream(), DefValue.inetdCharset));
				BufferedReader err = new BufferedReader(new InputStreamReader(p.getErrorStream(), DefValue.inetdCharset));

				if (Flag.TRUE) {
					String line = null;
					
					while ((line = out.readLine()) != null) {
						Logger.info("INETD-OUT> " + line);
					}
					
					while ((line = err.readLine()) != null) {
						Logger.info("INETD-ERR> " + line);
					}
				}

				p.waitFor();
				p.destroy();
				
				if (!Flag.TRUE) Logger.info("Exit Code : " + p.exitValue());
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * after-process job after the exec job
	 */
	public void afterExec()
	{
		if (Flag.TRUE) {
			
		}
	}
	
	///////////////////////////////////////////////////////////////////////////
	
	/**
	 * main function for testing
	 * 
	 * @param args
	 */
	public static void main(String[] args)
	{
		if (Flag.TRUE) {

			BasePath.getInstance();

			try {
				Logger.getInstance(null, "CommandProcess");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		if (!Flag.TRUE) {
			CommandProcess process = new CommandProcess("imsi  12345  ABCD  강석");
			process.beforeExec();
			process.exec();
			process.afterExec();
		}
		
		if (Flag.TRUE) {
			CommandProcess process = new CommandProcess("sample01 KEY_0909    200000  SAMPLE0024 ");
			process.beforeExec();
			process.exec();
			process.afterExec();
		}
	}
}
