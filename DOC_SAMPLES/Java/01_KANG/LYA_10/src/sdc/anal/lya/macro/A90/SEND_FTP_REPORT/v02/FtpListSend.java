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

package sdc.anal.lya.macro.A90.SEND_FTP_REPORT.v02;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;

import kiea.kr.co.tain.base.Flag;
import kiea.proj.sdc.anal.util.BasePath;
import kiea.proj.sdc.anal.util.ServiceMacroProperties;
import kiea.proj.sdc.anal.util.log.v02.Logger;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;

/**
 * @author KangSeok
 * @date 2014. 9. 28.
 * @file_name FtpListSend.java
 *
 */
public class FtpListSend
{
	private static final String HOST = "12.93.11.247";
	private static final String PORT = "2011";
	private static final String USER = "qafuser01";
	private static final String PASS = "qaf890qaf";

	private String strHost;
	private String strPort;
	private String strUser;
	private String strPass;
	
	private FtpInfo ftpInfo = null;
	
	private FTPClient ftpClient = null;

	private String path = null;
	
	public FtpListSend(FtpInfo ftpInfo)
	{
		if (Flag.TRUE) {
			ServiceMacroProperties propMacro = ServiceMacroProperties.getInstance();

			this.strHost = propMacro.get("anal.ftp.server.ip"  , HOST);
			this.strPort = propMacro.get("anal.ftp.server.port", PORT);
			this.strUser = propMacro.get("anal.ftp.server.user", USER);
			this.strPass = propMacro.get("anal.ftp.server.pass", PASS);
		}
		
		if (Flag.TRUE) {
			this.ftpInfo = ftpInfo;
			this.path = BasePath.getInstance().getDataPath() + "/" + this.ftpInfo.getDate() + "/" + this.ftpInfo.getJobId();
		}
	}
	
	public void send()
	{
		if (Flag.TRUE) {
			if (ftpConnection()) {
				List<String> list = null;
				boolean flg = true;
				
				// FTP ROOT 폴더
				setWorkingFolder("/");
				list = getRemoteList();
				flg = true;
				for (String item : list) {
					if (item.equals(this.ftpInfo.getRDate())) {
						flg = false;
						break;
					}
				}
				
				if (flg) {
					// rdate폴더를 확인하여 없으면 만든다.
					makeWorkingFolder(this.ftpInfo.getRDate());
				}
				
				// rdate폴더로 이동한다.
				setWorkingFolder(this.ftpInfo.getRDate());
				list = getRemoteList();
				flg = true;
				for (String item : list) {
					if (item.equals(this.ftpInfo.getJobId())) {
						flg = false;
						break;
					}
				}

				if (flg) {
					// jobId폴더를 확인하여 없으면 만든다.
					makeWorkingFolder(this.ftpInfo.getJobId());
				}
				
				// jobId폴더로 이동한다.
				setWorkingFolder(this.ftpInfo.getJobId());
				
				// csv 파일들을 전송한다.
				for (FtpBean bean : this.ftpInfo.getList()) {
					
					storeFiles(bean.getFromFile(), bean.getToFile());
				}
				
			} else {
				// 접속 실패
				if (Flag.TRUE) Logger.info("FTP server refused connection.");
			}
		}
		
		if (Flag.TRUE) {
			ftpDisconnection();
		}
	}
	
	private boolean ftpConnection()
	{
		if (Flag.TRUE) {
			try {
				
				ftpClient = new FTPClient();
				ftpClient.setControlEncoding("EUC-KR");
				ftpClient.connect(this.strHost, Integer.parseInt(this.strPort));
				int reply = ftpClient.getReplyCode();
				if (!FTPReply.isPositiveCompletion(reply)) {
					// 비정상 접속
					if (Flag.TRUE) Logger.error(ftpClient.getReplyString());
					return false;
				}
				// 정상 접속
				if (Flag.TRUE) Logger.info(ftpClient.getReplyString());
				
				// TIMEOUT 세팅
				ftpClient.setSoTimeout(10000);
				
				// 사용자 인증
				if (!ftpClient.login(this.strUser, this.strPass)) {
					// 인증실패
					if (Flag.TRUE) Logger.error(ftpClient.getReplyString());
					return false;
				}
				if (Flag.TRUE) Logger.info(ftpClient.getReplyString());
				
				// Passive Mode
				ftpClient.enterLocalPassiveMode();
				
			} catch (Exception e) {
				e.printStackTrace();
				return false;
			}
		}
		
		return true;
	}
	
	private boolean ftpDisconnection()
	{
		if (Flag.TRUE) {
			if (ftpClient != null && ftpClient.isConnected()) {
				try {
					ftpClient.disconnect();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		
		return true;
	}
	
	private void makeWorkingFolder(String path)
	{
		if (Flag.TRUE) {
			try {
				ftpClient.makeDirectory(path);
				if (!Flag.TRUE) Logger.info(ftpClient.getReplyString());
				if (Flag.TRUE) Logger.info("[%s] 폴더를 만든다..", path);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	private void setWorkingFolder(String path)
	{
		if (Flag.TRUE) {
			try {
				ftpClient.changeWorkingDirectory(path);
				if (!Flag.TRUE) Logger.info(ftpClient.getReplyString());
				if (Flag.TRUE) Logger.info("[%s] 폴더로 이동한다.", path);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	private List<String> getRemoteList()
	{
		List<String> list = null;
		
		if (Flag.TRUE) {
			try {
				list = Arrays.asList(ftpClient.listNames());
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		return list;
	}
	
	private void storeFiles(String fromFile, String toFile)
	{
		if (Flag.TRUE) {
			try {
				ftpClient.setFileType(FTP.STREAM_TRANSFER_MODE); // Default
				ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
				ftpClient.setFileType(FTP.ASCII_FILE_TYPE);

				InputStream is = new FileInputStream(new File(this.path + "/" + fromFile));
				ftpClient.storeFile(toFile, is);
				is.close();
				if (Flag.TRUE) Logger.info("ANAL서버[%s] -> FTP서버[%s] 파일을 upload 완료 !!!", fromFile, toFile);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
