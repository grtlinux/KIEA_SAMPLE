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

package kiea.proj.sdc.anal.util;

import java.io.File;

import kiea.kr.co.tain.base.Flag;
import kiea.kr.co.tain.util.DateTime;
import kiea.proj.sdc.anal.common.FileValue;

/**
 * @author KangSeok
 * @date 2014. 8. 5.
 * @file_name FileUtil.java
 *
 */
public class FileUtil
{
	/**
	 * 데이터 Path 아래 날짜폴더, 키폴더를 만든다. 만일 없으면 새로 만든다.
	 * @param dataPath
	 * @param datePath
	 * @param jobKeyPath
	 * @return
	 */
	public static String makeDataDir(String dataPath, String datePath, String jobKeyPath)
	{
		String strPath = null;
		
		if (Flag.TRUE) {
			File file = null;
			/*
			 * 날짜 폴더를 확인하여 없으면 생성한다.
			 */
			strPath = dataPath + FileValue.SEP + datePath;
			file = new File(strPath);
			if (!file.exists()) {
				/*
				 * 날짜 폴더가 없다. 그럼 생성한다.
				 */
				file.mkdir();
			}
			
			if (jobKeyPath != null) {
				/*
				 * 키 폴더를 확인하여 없으면 생성한다.
				 */
				strPath += FileValue.SEP + jobKeyPath;
				file = new File(strPath);
				if (!file.exists()) {
					/*
					 * 키 폴더가 없다. 그럼 생성한다.
					 */
					file.mkdir();
				}
			}
			
//			/*
//			 * 로그파일을 확인하여 없으면 생성한다.
//			 */
//			chkName += FileValue.SEP + this.logFile;
//			chkFile = new File(chkName);
//			if (!chkFile.exists()) {
//				/*
//				 * 로그파일이 없다. 그럼 생성한다.
//				 */
//				try {
//					ret = chkFile.createNewFile();
//				} catch (IOException e) {
//					e.printStackTrace();
//				}
//			}
		}
		
		return strPath;
	}

	///////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////
	
	/**
	 * test-01
	 */
	private static void test01()
	{
		if (Flag.TRUE) {
			String strDir = FileUtil.makeDataDir("D:/ANAL/anal/LYA/data", "YYYYMMDD", "KEY_HHMM");
			System.out.println("[" + strDir + "]");
		}
		
		if (Flag.TRUE) {
			String strDir = FileUtil.makeDataDir(BasePath.getInstance().getDataPath(), DateTime.getDate(2), "KEY_[HHMM]".replace("[HHMM]", DateTime.getTime(5)));
			System.out.println("[" + strDir + "]");
		}
	}
	
	/**
	 * main entry
	 * @param args
	 */
	public static void main(String[] args)
	{
		if (Flag.TRUE) test01();
	}
}
