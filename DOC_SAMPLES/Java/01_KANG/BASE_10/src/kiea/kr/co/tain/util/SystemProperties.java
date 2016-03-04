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

package kiea.kr.co.tain.util;

import java.util.Properties;

import kiea.kr.co.tain.base.Flag;

/**
 * @author KangSeok
 * @date 2014. 10. 1.
 * @file_name SystemProperties.java
 *
 */
public class SystemProperties
{
	private Properties prop = null;
	
	/**
	 * 
	 */
	private SystemProperties()
	{
		if (Flag.TRUE) {
			prop = System.getProperties();
		}
	}
	
	/**
	 * 
	 * printList
	 *
	 */
	public void printList()
	{
		if (Flag.TRUE) {
			this.prop.list(System.out);
		}
	}
	
	/**
	 * 
	 * set
	 *
	 * @param paramKey
	 * @param paramVal
	 */
	public void set(String paramKey, String paramVal)
	{
		if (Flag.TRUE) {
			this.prop.setProperty(paramKey, paramVal);
		}
	}
	
	/**
	 * 
	 * get
	 *
	 * @param paramKey
	 * @return
	 */
	public String get(String paramKey)
	{
		String ret = null;
		
		if (Flag.TRUE) {
			ret = this.prop.getProperty(paramKey);
		}
		
		return ret;
	}

	/**
	 * 
	 * isWindows
	 *
	 * @return
	 */
	public boolean isWindows()
	{
		boolean flag = true;
		
		if (Flag.TRUE) {
			String osName = getOsName();
			if (osName.indexOf("Win", 0) >= 0) {
				flag = true;
			} else {
				flag = false;
			}
		}
		return flag;
	}
	
	/**
	 * 
	 * isUnix
	 *
	 * @return
	 */
	public boolean isUnix()
	{
		return isWindows() ? false : true;
	}
	
	/**
	 * 
	 * getOsName
	 *
	 * @return
	 */
	public String getOsName()
	{
		String ret = null;
		
		if (Flag.TRUE) {
			ret = get("os.name");
		}
		
		return ret;
	}
	
	/**
	 * 
	 * getFileEncoding
	 *
	 * @return
	 */
	public String getFileEncoding()
	{
		String ret = null;
		
		if (Flag.TRUE) {
			ret = get("file.encoding");
		}
		
		return ret;
	}
	
	/**
	 * 
	 * getUserName
	 *
	 * @return
	 */
	public String getUserName()
	{
		String ret = null;
		
		if (Flag.TRUE) {
			ret = get("user.name");
		}
		
		return ret;
	}
	
	/**
	 * 
	 * getBasePath
	 *
	 * @return
	 */
	public String getAnalBasePath()
	{
		String ret = null;
		
		if (Flag.TRUE) {
			ret = get("anal.base.path");
		}
		
		return ret;
	}
	
	/**
	 * 
	 * setAnalServiceCode
	 *
	 * @param analServiceCode
	 */
	public void setAnalServiceCode(String analServiceCode)
	{
		if (Flag.TRUE) {
			this.prop.setProperty("anal.service.code", analServiceCode);
		}
	}
	
	/**
	 * 
	 * getAnalServiceCode
	 *
	 * @return
	 */
	public String getAnalServiceCode()
	{
		String ret = null;
		
		if (Flag.TRUE) {
			ret = get("anal.service.code");
		}
		
		return ret;
	}
	
	/**
	 * 
	 * setAnalLoggerFlag
	 *
	 * @param analLoggerFlag
	 */
	public void setAnalLoggerFlag(String analLoggerFlag)
	{
		if (Flag.TRUE) {
			this.prop.setProperty("anal.logger.flag", analLoggerFlag);
		}
	}
	
	/**
	 * 
	 * getAnalLoggerFlag
	 *
	 * @return
	 */
	public String getAnalLoggerFlag()
	{
		String ret = null;
		
		if (Flag.TRUE) {
			ret = get("anal.logger.flag");
		}
		
		return ret;
	}
	
	/**
	 * 
	 * isAnalLogger
	 *
	 * @return
	 */
	public boolean isAnalLogger()
	{
		boolean ret = false;
		
		if (Flag.TRUE) {
			if ("YES".equalsIgnoreCase(getAnalLoggerFlag()))
				ret = true;
			/*
			 * TODO : 2014.10.12 : 나중에 properties에 anal.logger.flag 키를 추가한다.
			 */
			ret = true;
		}
		
		return ret;
	}
	
	///////////////////////////////////////////////////////////////////////////
	
	/**
	 * Singleton
	 */
	private static SystemProperties instance = null;
	
	public static synchronized SystemProperties getInstance()
	{
		if (instance == null) {
			instance = new SystemProperties();
		}
		
		return instance;
	}

	///////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////
	
	public static void setTesting(String analServiceCode)
	{
		if (Flag.TRUE) {
			/*
			 * -Danal.base.path=D:/ANAL/anal
			 * -Danal.service.code=010
			 * -Danal.logger.flag=yes
			 * -Dfile.encoding=euc-kr
			 * 위 properties가 선언되었으면 아래는 skip한다.
			 */
			SystemProperties prop = SystemProperties.getInstance();
			if (prop.getAnalBasePath() == null && prop.isWindows()) {
				prop.set("anal.base.path"   , "D:/ANAL/anal");
				prop.set("anal.service.code", analServiceCode);
				prop.set("anal.logger.flag" , "yes");
				prop.set("file.encoding"    , "euc-kr");
				
				if (Flag.TRUE) {
					Print.println("Default (anal.base.path)    = %s", prop.get("anal.base.path"   ));
					Print.println("Default (anal.service.code) = %s", prop.get("anal.service.code"));
					Print.println("Default (anal.logger.flag)  = %s", prop.get("anal.logger.flag" ));
					Print.println("Default (file.encoding)     = %s", prop.get("file.encoding"    ));
				}
			}
		}
	}

	public static void setTesting()
	{
		if (Flag.TRUE) {
			setTesting("010");
		}
	}

	private static void test01()
	{
		if (Flag.TRUE) {
			SystemProperties.getInstance().printList();
		}

		if (Flag.TRUE) {
			SystemProperties prop = SystemProperties.getInstance();
			prop.setAnalServiceCode("010");
			
			System.out.println("OS NAME       : " + prop.getOsName());
			System.out.println("FILE ENCODING : " + prop.getFileEncoding());
			System.out.println("USER NAME     : " + prop.getUserName());
			System.out.println("SERVICE CODE  : " + prop.getAnalServiceCode());
			System.out.println("BASE PATH     : " + prop.getAnalBasePath());
			
			if (SystemProperties.getInstance().isWindows()) {
				System.out.println("Windows.....");
			}
			
			if (SystemProperties.getInstance().isUnix()) {
				System.out.println("UNIX.....");
			}
		}
	}
	
	private static void test02()
	{
		if (Flag.TRUE) {
			System.out.println("[" + SystemProperties.getInstance().getAnalLoggerFlag() + "]");
			System.out.println("[" + SystemProperties.getInstance().isAnalLogger() + "]");
		}
		
		if (Flag.TRUE) {
			SystemProperties.setTesting("010");
			System.out.println("[" + SystemProperties.getInstance().getAnalLoggerFlag() + "]");
			System.out.println("[" + SystemProperties.getInstance().isAnalLogger() + "]");
		}
		
		if (Flag.TRUE) {
			SystemProperties.setTesting("010");
			SystemProperties.getInstance().setAnalLoggerFlag("");
			System.out.println("[" + SystemProperties.getInstance().getAnalLoggerFlag() + "]");
			System.out.println("[" + SystemProperties.getInstance().isAnalLogger() + "]");
		}
	}
	
	public static void main(String[] args)
	{
		if (!Flag.TRUE) test01();
		if (Flag.TRUE) test02();
	}
}
