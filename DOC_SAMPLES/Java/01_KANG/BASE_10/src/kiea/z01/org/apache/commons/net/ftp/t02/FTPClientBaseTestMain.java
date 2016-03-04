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

package kiea.z01.org.apache.commons.net.ftp.t02;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

import kiea.kr.co.tain.base.Flag;
import kiea.kr.co.tain.util.Print;
import kiea.proj.sdc.anal.util.BasePath;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPCommand;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPListParseEngine;
import org.apache.commons.net.ftp.FTPReply;

/**
 * @author KangSeok
 * @date 2014. 9. 25.
 * @file_name FTPClientBaseTestMain.java
 *
 */
@SuppressWarnings("deprecation")
public class FTPClientBaseTestMain
{
	private static final String HOST = "12.93.11.247";
	private static final String PORT = "2011";
	private static final String USER = "qafuser01";
	private static final String PASS = "qaf890qaf";
	
	/*
	 * 
	 * 1. FTPClient 생성
	 * 
	 *     FTPClient ftpClient = new FTPClient();
	 * 
	 * 2. FTP 서버에 connect
	 * 
	 *     ftpClient.connect(server);
	 * 
	 * 3. 응답이 정상인지 확인
	 * 
	 *     int reply = ftpClient.getReplyCode();
	 *     if (!FTPReply.isPositiveCompletion(reply)) {
	 *         // 비정상
	 *         ftpClient.disconnect();
	 *     } else {
	 *         // 정상
	 *     }
	 * 
	 * 4. FTP Login
	 * 
	 *     ftpClient.login(username, password);
	 * 
	 * 5. Various Jobs
	 * 
	 *     list, set, get...
	 * 
	 * 6. FTP Logout
	 * 
	 *     ftpClient.logout();
	 * 
	 * 7. FTP 서버에 disconnect
	 * 
	 *     ftpClient.disconnect();
	 * 
	 * 
	 */
	private static void test01_list1()
	{
		FTPClient ftpClient = null;
		
		try {
			ftpClient = new FTPClient();
			ftpClient.setControlEncoding("EUC-KR");
			//ftpClient.connect("user.chollian.net", 2011);
			ftpClient.connect("user.chollian.net");
			int reply = ftpClient.getReplyCode();
			if (!FTPReply.isPositiveCompletion(reply)) {
				// 비정상
				ftpClient.disconnect();
				System.out.println("FTP server refused connection.");
			} else {
				// 정상
				System.out.println(ftpClient.getReplyString());
				
				ftpClient.setSoTimeout(10000);
				ftpClient.login("user", "pass");
				
				if (Flag.TRUE) {
					FTPFile[] ftpFiles = ftpClient.listFiles("/public");
					for (FTPFile ftpFile : ftpFiles) {
						System.out.println(ftpFile);
					}
				}
				
				// finally에서 처리해도 됨.
				//ftpClient.disconnect();
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (ftpClient != null && ftpClient.isConnected()) {
				try {
					ftpClient.disconnect();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	/*
	 * 위 소스코드의 다른 방법
	 */
	private static void test02_list2()
	{
		FTPClient ftpClient = null;
		
		try {
			ftpClient = new FTPClient();
			ftpClient.setControlEncoding("EUC-KR");
			//ftpClient.connect("user.chollian.net", 2011);
			ftpClient.connect("user.chollian.net");
			int reply = ftpClient.getReplyCode();
			if (!FTPReply.isPositiveCompletion(reply)) {
				// 비정상
				ftpClient.disconnect();
				System.out.println("FTP server refused connection.");
			} else {
				// 정상
				System.out.println(ftpClient.getReplyString());
				
				ftpClient.setSoTimeout(10000);
				ftpClient.login("user", "pass");
				
				if (Flag.TRUE) {
					int page = 1;
					FTPListParseEngine engine = ftpClient.initiateListParsing("/public");
					while (engine.hasNext()) {
						
						System.out.println("### PAGE " + page++);
						
						FTPFile[] ftpFiles = engine.getNext(10); // 10개 단위 페이지
						for (FTPFile ftpFile : ftpFiles) {
							System.out.println(ftpFile);
						}
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (ftpClient != null && ftpClient.isConnected()) {
				try {
					ftpClient.disconnect();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	/*
	 * DOWN LOAD
	 */
	private static void test03_down()
	{
		FTPClient ftpClient = null;
		
		try {
			ftpClient = new FTPClient();
			ftpClient.setControlEncoding("EUC-KR");
			//ftpClient.connect("user.chollian.net", 2011);
			ftpClient.connect("user.chollian.net");
			int reply = ftpClient.getReplyCode();
			if (!FTPReply.isPositiveCompletion(reply)) {
				// 비정상
				ftpClient.disconnect();
				System.out.println("FTP server refused connection.");
			} else {
				// 정상
				System.out.println(ftpClient.getReplyString());
				
				ftpClient.setSoTimeout(10000);
				ftpClient.login("user", "pass");
				
				if (Flag.TRUE) {
					ftpClient.setFileType(FTP.STREAM_TRANSFER_MODE); // Default
					ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
					ftpClient.setFileType(FTP.ASCII_FILE_TYPE);

					OutputStream os = new FileOutputStream(new File("D:/down.txt"));
					@SuppressWarnings("unused")
					boolean result = ftpClient.retrieveFile("/public/DOWN.txt", os);
					os.close();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (ftpClient != null && ftpClient.isConnected()) {
				try {
					ftpClient.disconnect();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	/*
	 * UP LOAD. 만일 서버에 동일한 파일명이 있으면 덮어쓴다.
	 */
	private static void test04_up1_store()
	{
		FTPClient ftpClient = null;
		
		try {
			ftpClient = new FTPClient();
			ftpClient.setControlEncoding("EUC-KR");
			//ftpClient.connect("user.chollian.net", 2011);
			ftpClient.connect("user.chollian.net");
			int reply = ftpClient.getReplyCode();
			if (!FTPReply.isPositiveCompletion(reply)) {
				// 비정상
				ftpClient.disconnect();
				System.out.println("FTP server refused connection.");
			} else {
				// 정상
				System.out.println(ftpClient.getReplyString());
				
				ftpClient.setSoTimeout(10000);
				ftpClient.login("user", "pass");
				
				if (Flag.TRUE) {
					ftpClient.setFileType(FTP.STREAM_TRANSFER_MODE); // Default
					ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
					ftpClient.setFileType(FTP.ASCII_FILE_TYPE);

					InputStream is = new FileInputStream(new File("D:/UP.txt"));
					@SuppressWarnings("unused")
					boolean result = ftpClient.storeFile("/public/UP.txt", is);
					is.close();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (ftpClient != null && ftpClient.isConnected()) {
				try {
					ftpClient.disconnect();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	/*
	 * UP LOAD. 만일 서버에 동일한 파일명이 있으면 false를 반환하고 실행하지 않는다.
	 */
	private static void test05_up2_append()
	{
		FTPClient ftpClient = null;
		
		try {
			ftpClient = new FTPClient();
			ftpClient.setControlEncoding("EUC-KR");
			//ftpClient.connect("user.chollian.net", 2011);
			ftpClient.connect("user.chollian.net");
			int reply = ftpClient.getReplyCode();
			if (!FTPReply.isPositiveCompletion(reply)) {
				// 비정상
				ftpClient.disconnect();
				System.out.println("FTP server refused connection.");
			} else {
				// 정상
				System.out.println(ftpClient.getReplyString());
				
				ftpClient.setSoTimeout(10000);
				ftpClient.login("user", "pass");
				
				if (Flag.TRUE) {
					ftpClient.setFileType(FTP.STREAM_TRANSFER_MODE); // Default
					ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
					ftpClient.setFileType(FTP.ASCII_FILE_TYPE);
					
					InputStream is = new FileInputStream(new File("D:/UP.txt"));
					@SuppressWarnings("unused")
					boolean result = ftpClient.appendFile("/public/UP.txt", is);
					is.close();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (ftpClient != null && ftpClient.isConnected()) {
				try {
					ftpClient.disconnect();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	/*
	 * RENAME
	 */
	private static void test06_rename()
	{
		FTPClient ftpClient = null;
		
		try {
			ftpClient = new FTPClient();
			ftpClient.setControlEncoding("EUC-KR");
			//ftpClient.connect("user.chollian.net", 2011);
			ftpClient.connect("user.chollian.net");
			int reply = ftpClient.getReplyCode();
			if (!FTPReply.isPositiveCompletion(reply)) {
				// 비정상
				ftpClient.disconnect();
				System.out.println("FTP server refused connection.");
			} else {
				// 정상
				System.out.println(ftpClient.getReplyString());
				
				ftpClient.setSoTimeout(10000);
				ftpClient.login("user", "pass");
				
				if (Flag.TRUE) {
					@SuppressWarnings("unused")
					boolean result = ftpClient.rename("/public/from.txt", "/public/to.txt");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (ftpClient != null && ftpClient.isConnected()) {
				try {
					ftpClient.disconnect();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	/*
	 * DELETE
	 */
	private static void test07_delete()
	{
		FTPClient ftpClient = null;
		
		try {
			ftpClient = new FTPClient();
			ftpClient.setControlEncoding("EUC-KR");
			//ftpClient.connect("user.chollian.net", 2011);
			ftpClient.connect("user.chollian.net");
			int reply = ftpClient.getReplyCode();
			if (!FTPReply.isPositiveCompletion(reply)) {
				// 비정상
				ftpClient.disconnect();
				System.out.println("FTP server refused connection.");
			} else {
				// 정상
				System.out.println(ftpClient.getReplyString());
				
				ftpClient.setSoTimeout(10000);
				ftpClient.login("user", "pass");
				
				if (Flag.TRUE) {
					@SuppressWarnings("unused")
					boolean result = ftpClient.deleteFile("/public/delete.txt");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (ftpClient != null && ftpClient.isConnected()) {
				try {
					ftpClient.disconnect();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	/*
	 * MAKE DIRECTORY
	 */
	private static void test08_mkDir1()
	{
		FTPClient ftpClient = null;
		
		try {
			ftpClient = new FTPClient();
			ftpClient.setControlEncoding("EUC-KR");
			//ftpClient.connect("user.chollian.net", 2011);
			ftpClient.connect("user.chollian.net");
			int reply = ftpClient.getReplyCode();
			if (!FTPReply.isPositiveCompletion(reply)) {
				// 비정상
				ftpClient.disconnect();
				System.out.println("FTP server refused connection.");
			} else {
				// 정상
				System.out.println(ftpClient.getReplyString());
				
				ftpClient.setSoTimeout(10000);
				ftpClient.login("user", "pass");
				
				if (Flag.TRUE) {
					@SuppressWarnings("unused")
					boolean result = ftpClient.makeDirectory("/public/folder01");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (ftpClient != null && ftpClient.isConnected()) {
				try {
					ftpClient.disconnect();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	/*
	 * MAKE DIRECTORY - 2
	 */
	private static void test09_mkDir2()
	{
		FTPClient ftpClient = null;
		
		try {
			ftpClient = new FTPClient();
			ftpClient.setControlEncoding("EUC-KR");
			//ftpClient.connect("user.chollian.net", 2011);
			ftpClient.connect("user.chollian.net");
			int reply = ftpClient.getReplyCode();
			if (!FTPReply.isPositiveCompletion(reply)) {
				// 비정상
				ftpClient.disconnect();
				System.out.println("FTP server refused connection.");
			} else {
				// 정상
				System.out.println(ftpClient.getReplyString());
				
				ftpClient.setSoTimeout(10000);
				ftpClient.login("user", "pass");
				
				if (Flag.TRUE) {
					@SuppressWarnings("unused")
					int result = ftpClient.sendCommand(FTPCommand.MAKE_DIRECTORY, "/public/folder02");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (ftpClient != null && ftpClient.isConnected()) {
				try {
					ftpClient.disconnect();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	/*
	 * WORKING DIRECTORY
	 */
	private static void test10_workDir()
	{
		FTPClient ftpClient = null;
		
		try {
			ftpClient = new FTPClient();
			ftpClient.setControlEncoding("EUC-KR");
			//ftpClient.connect("user.chollian.net", 2011);
			ftpClient.connect("user.chollian.net");
			int reply = ftpClient.getReplyCode();
			if (!FTPReply.isPositiveCompletion(reply)) {
				// 비정상
				ftpClient.disconnect();
				System.out.println("FTP server refused connection.");
			} else {
				// 정상
				System.out.println(ftpClient.getReplyString());
				
				ftpClient.setSoTimeout(10000);
				ftpClient.login("user", "pass");
				
				if (Flag.TRUE) {
					ftpClient.changeWorkingDirectory("/public");
					@SuppressWarnings("unused")
					boolean result = ftpClient.makeDirectory("folder01");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (ftpClient != null && ftpClient.isConnected()) {
				try {
					ftpClient.disconnect();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	/*
	 * ANAL 접속 list 출력
	 * Active Mode
	 */
	private static void test11_listAnal_Active()
	{
		FTPClient ftpClient = null;
		
		try {
			ftpClient = new FTPClient();
			ftpClient.setControlEncoding("EUC-KR");
			ftpClient.connect(HOST, Integer.parseInt(PORT));
			int reply = ftpClient.getReplyCode();
			if (!FTPReply.isPositiveCompletion(reply)) {
				// 비정상
				ftpClient.disconnect();
				System.out.println("FTP server refused connection.");
			} else {
				// 정상
				System.out.println(ftpClient.getReplyString());
				
				ftpClient.setSoTimeout(10000);
				ftpClient.login(USER, PASS);
				ftpClient.getReplyCode();
				System.out.println(ftpClient.getReplyString());
				
				if (Flag.TRUE) {
					FTPFile[] ftpFiles = ftpClient.listFiles();
					System.out.println("ftpFiles.length = " + ftpFiles.length);
					for (FTPFile ftpFile : ftpFiles) {
						System.out.println(ftpFile);
					}
				}
				
				if (Flag.TRUE) {
					String[] strFiles = ftpClient.listNames();
					System.out.println("strFiles.length = " + strFiles.length);
					for (String strFile : strFiles) {
						System.out.println(strFile);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (ftpClient != null && ftpClient.isConnected()) {
				try {
					ftpClient.disconnect();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	/*
	 * ANAL 접속 list 출력
	 * Passive Mode
	 */
	private static void test12_listAnal_Passive()
	{
		FTPClient ftpClient = null;
		
		try {
			ftpClient = new FTPClient();
			ftpClient.setControlEncoding("EUC-KR");
			ftpClient.connect(HOST, Integer.parseInt(PORT));
			int reply = ftpClient.getReplyCode();
			if (!FTPReply.isPositiveCompletion(reply)) {
				// 비정상
				ftpClient.disconnect();
				System.out.println("FTP server refused connection.");
			} else {
				// 정상
				System.out.println(ftpClient.getReplyString());
				
				ftpClient.setSoTimeout(10000);
				ftpClient.login(USER, PASS);
				ftpClient.getReplyCode();
				System.out.println(ftpClient.getReplyString());
				
				// Passive Mode
				ftpClient.enterLocalPassiveMode();
				
				if (Flag.TRUE) {
					FTPFile[] ftpFiles = ftpClient.listFiles();
					System.out.println("ftpFiles.length = " + ftpFiles.length);
					for (FTPFile ftpFile : ftpFiles) {
						System.out.println(ftpFile);
					}
				}
				
				if (Flag.TRUE) {
					ftpClient.changeWorkingDirectory("140925");
					ftpClient.getReplyCode();
					System.out.println(ftpClient.getReplyString());

					String[] strFiles = ftpClient.listNames();
					System.out.println("strFiles.length = " + strFiles.length);
					for (String strFile : strFiles) {
						System.out.println(strFile);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (ftpClient != null && ftpClient.isConnected()) {
				try {
					ftpClient.disconnect();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	/*
	 * 1. makeDirectory
	 * 2. printWorkingDirectory
	 * 3. removeDirectory
	 * 
	 */
	private static void test13_mkdir_pwd_rmdir()
	{
		FTPClient ftpClient = null;
		String strFolder = "KANG";
		
		try {
			ftpClient = new FTPClient();
			ftpClient.setControlEncoding("EUC-KR");
			ftpClient.connect(HOST, Integer.parseInt(PORT));
			int reply = ftpClient.getReplyCode();
			if (!FTPReply.isPositiveCompletion(reply)) {
				// 비정상
				ftpClient.disconnect();
				System.out.println("FTP server refused connection.");
			} else {
				// 정상
				if (Flag.TRUE) System.out.println(ftpClient.getReplyString());  // 220 Microsoft FTP Service
				
				ftpClient.setSoTimeout(10000);
				ftpClient.login(USER, PASS);
				if (Flag.TRUE) System.out.println(ftpClient.getReplyString());   // 230 User logged in.
				
				if (Flag.TRUE) {
					// Passive Mode
					ftpClient.enterLocalPassiveMode();
					if (Flag.TRUE) System.out.println(ftpClient.getReplyString());   // 230 User logged in.
				}
				
				if (Flag.TRUE) {
					// before makeDirectory
					FTPFile[] ftpFiles = ftpClient.listFiles();
					System.out.println("BEFORE MAKE_DIRECTORY : ftpFiles.length = " + ftpFiles.length);
					for (FTPFile ftpFile : ftpFiles) {
						System.out.println(ftpFile);
					}
				}
				
				if (Flag.TRUE) {
					// makeDirectory
					ftpClient.makeDirectory(strFolder);
					if (Flag.TRUE) System.out.println(ftpClient.getReplyString());
				}
				
				if (Flag.TRUE) {
					// after makeDirectory
					FTPFile[] ftpFiles = ftpClient.listFiles();
					System.out.println("AFTER MAKE_DIRECTORY : ftpFiles.length = " + ftpFiles.length);
					for (FTPFile ftpFile : ftpFiles) {
						System.out.println(ftpFile);
					}
				}
				
				if (Flag.TRUE) {
					// removeDirectory
					ftpClient.removeDirectory(strFolder);
					if (Flag.TRUE) System.out.println(ftpClient.getReplyString());
				}
				
				if (Flag.TRUE) {
					// after removeDirectory
					FTPFile[] ftpFiles = ftpClient.listFiles();
					System.out.println("AFTER REMOVE_DIRECTORY : ftpFiles.length = " + ftpFiles.length);
					for (FTPFile ftpFile : ftpFiles) {
						System.out.println(ftpFile);
					}
				}
				
				if (Flag.TRUE) {
					// change Working Directory
					//ftpClient.changeWorkingDirectory("140925");
					ftpClient.changeWorkingDirectory("A140928");
					if (Flag.TRUE) System.out.println(ftpClient.getReplyString());
				}
				
				if (Flag.TRUE) {
					// after changeWorkingDirectory
					String[] ftpFiles = ftpClient.listNames();
					System.out.println("AFTER CHANGE_WORKING_DIRECTORY : 140925 ftpFiles.length = " + ftpFiles.length);
					for (String ftpFile : ftpFiles) {
						System.out.println(ftpFile);
					}
				}

				if (Flag.TRUE) {
					// change Working Directory
					ftpClient.changeWorkingDirectory("AR010140928A0000");
					if (Flag.TRUE) System.out.println(ftpClient.getReplyString());
				}
				
				if (Flag.TRUE) {
					String path = BasePath.getInstance().getConfigPath();
					String fileName = "manager.properties";
					
					InputStream is = new FileInputStream(new File(path + "/" + fileName));
					ftpClient.storeFile(fileName, is);
					is.close();
				}
				
				if (Flag.TRUE) {
					// after changeWorkingDirectory
					FTPFile[] ftpFiles = ftpClient.listFiles();
					System.out.println("AFTER CHANGE_WORKING_DIRECTORY : LYAAU1409253451 ftpFiles.length = " + ftpFiles.length);
					for (FTPFile ftpFile : ftpFiles) {
						System.out.println(ftpFile);
					}
				}

				if (Flag.TRUE) {
					// print Working Directory
					String pwd = ftpClient.printWorkingDirectory();
					if (Flag.TRUE) System.out.println("PWD = [" + pwd + "]");
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (ftpClient != null && ftpClient.isConnected()) {
				try {
					ftpClient.disconnect();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	/**
	 * 
	 * main
	 *
	 * @param args
	 */
	public static void main(String[] args)
	{
		if (!Flag.TRUE) test01_list1();
		if (!Flag.TRUE) test02_list2();
		if (!Flag.TRUE) test03_down();
		if (!Flag.TRUE) test04_up1_store();
		if (!Flag.TRUE) test05_up2_append();
		if (!Flag.TRUE) test06_rename();
		if (!Flag.TRUE) test07_delete();
		if (!Flag.TRUE) test08_mkDir1();
		if (!Flag.TRUE) test09_mkDir2();
		if (!Flag.TRUE) test10_workDir();
		if (!Flag.TRUE) test11_listAnal_Active();
		if (!Flag.TRUE) test12_listAnal_Passive();
		if (Flag.TRUE) test13_mkdir_pwd_rmdir();
		
		if (!Flag.TRUE) {
			int port = 4500;
			int first = 4500 / 256;
			int second = 4500 % 256;
			
			Print.println("%d = %d * 256 + %d", port, first, second);
		}
		
		if (!Flag.TRUE) {
			int first = 16;
			int second = 19;
			int port = 16 * 256 + 19;
			
			Print.println("%d = %d * 256 + %d", port, first, second);
		}
	}
}
