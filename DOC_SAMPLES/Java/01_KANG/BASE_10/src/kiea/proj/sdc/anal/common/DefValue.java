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

package kiea.proj.sdc.anal.common;

/**
 * @author KangSeok
 * @date 2014. 8. 5.
 * @file_name DefValue.java
 *
 */
public interface DefValue
{
	/**
	 * the project name
	 */
	public static final String PROJ_NAME = "자동분석 SAS내재화 프로젝트";
	
	///////////////////////////////////////////////////////////////////////////
	
	/**
	 * the project version
	 */
	
	/*
	 * 최초 BASE 버젼
	 */
	//public static final String PROJ_VER = "SDC_BASE0.1_02_20140721";

	/*
	 * 소스정리 및 BASE와 MACROJOB으로 분리
	 */
	//public static final String PROJ_VER = "SDC_BASE0.2_03_20140804";
	
	/*
	 * 서버이관과 운영환경 1차 정리
	 */
	//public static final String PROJ_VER = "SDC_BASE0.2_07_20140916";
	
	/*
	 * 시간단위로 보관파일을 삭제할 수 있도록 프로그램 추가
	 *     DeleteArchiveMain
	 */
	//public static final String PROJ_VER = "SDC_BASE0.2_07_20140916";

	public static final String PROJ_VER = "SDC_BASE10_09_20141001";
	
	///////////////////////////////////////////////////////////////////////////

	/*
	 * inetd 서버 IP주소
	 */
	public static final String inetdIpAddr = "127.0.0.1";
	
	/*
	 * inetd 서버 포트
	 */
	public static final int inetdPort = 4258;
	
	/*
	 * inetd character set
	 */
	public static final String inetdCharset = "EUC-KR";
	
	/*
	 * inetd 가 실행하는 프로그램들의 path
	 */
	//public static final String inetdExePath = "D:/KANG/EXE";
	
	/*
	 * manager properties
	 */
	//public static final String managerProperties = "D:/KANG/WORK/workspace/BASE_02/config/manager.properties";

	/*
	 * manager character set
	 */
	public static final String managerCharset = "EUC-KR";
	
	/*
	 * default base path
	 */
	public static final String basePath = "D:/ANAL/anal";
	
	/*
	 * default manager log file name
	 */
	public static final String managerLogName = "MANAGER.log";
	
	/*
	 * default inetd log file name
	 */
	public static final String inetdLogName = "INETD.log";
}
