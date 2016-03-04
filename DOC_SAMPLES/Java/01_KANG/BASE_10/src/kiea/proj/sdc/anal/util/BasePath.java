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

import java.util.Properties;

import kiea.kr.co.tain.base.Flag;
import kiea.kr.co.tain.util.SystemProperties;

/**
 * @author KangSeok
 * @date 2014. 8. 5.
 * @file_name BasePath.java
 *
 */
public class BasePath
{
	private static BasePath instance = null;

	private SystemProperties sysProp = null;
	private AnalProperties analProp = null;
	
	private String author = null;
	private String version = null;
	
	private String basePath = null;
	
	private String configPath = null;
	
	private String dataPath = null;
	
	private String exePath = null;
	
	private String libPath = null;
	
	private String logPath = null;
	
	private String progPath = null;
	
	private String shellPath = null;
	
	private String srcPath = null;
	
	private String tempPath = null;
	
	/**
	 * constructor
	 */
	private BasePath()
	{
		if (Flag.TRUE) {
			this.sysProp = SystemProperties.getInstance();
			if (!Flag.TRUE) {
				System.out.println("[" + sysProp.getOsName         () + "]");
				System.out.println("[" + sysProp.getUserName       () + "]");
				System.out.println("[" + sysProp.getFileEncoding   () + "]");
				System.out.println("[" + sysProp.getAnalBasePath   () + "]");
				System.out.println("[" + sysProp.getAnalServiceCode() + "]");
				System.out.println("[" + sysProp.getAnalLoggerFlag () + "]");
			}
			
			this.analProp = AnalProperties.getInstance();
			if (!Flag.TRUE) {
				System.out.println("[" + analProp.getUse            () + "]");
				System.out.println("[" + analProp.getCode           () + "]");
				System.out.println("[" + analProp.getName           () + "]");
				System.out.println("[" + analProp.getTitle          () + "]");
				System.out.println("[" + analProp.getServiceBasePath() + "]");
			}
			
			this.basePath = this.analProp.getServiceBasePath();

			this.configPath = this.basePath + "/config";
			this.dataPath   = this.basePath + "/data";
			this.exePath    = this.basePath + "/exe";
			this.libPath    = this.basePath + "/lib";
			this.logPath    = this.basePath + "/log";
			this.progPath   = this.basePath + "/prog";
			this.shellPath  = this.basePath + "/shell";
			this.srcPath    = this.basePath + "/src";
			this.tempPath   = this.basePath + "/temp";
			
			if (!Flag.TRUE) {
				System.out.println(String.format("[config=%s]", this.configPath));
				System.out.println(String.format("[data  =%s]", this.dataPath));
				System.out.println(String.format("[exe   =%s]", this.exePath));
				System.out.println(String.format("[lib   =%s]", this.libPath));
				System.out.println(String.format("[log   =%s]", this.logPath));
				System.out.println(String.format("[prog  =%s]", this.progPath));
				System.out.println(String.format("[shell =%s]", this.shellPath));
				System.out.println(String.format("[src   =%s]", this.srcPath));
				System.out.println(String.format("[temp  =%s]", this.tempPath));
			}
		}
		
		if (!Flag.TRUE) {
			Properties prop = System.getProperties();
			this.author   = prop.getProperty("dev.author", "NOT DEFINE");
			this.version  = prop.getProperty("dev.version", "NOT DEFINE");
			//this.basePath = prop.getProperty("anal.base.path", DefaultValue.basePath);
			this.basePath = prop.getProperty("anal.base.path", "NOT DEFINE");
			
			if ("NOT DEFINE".equals(this.basePath)) {
				/*
				 * 이렇게 먼저 선언되어 실행해야함.
				 * -Danal.base.path=D:/ANAL/anal  .....
				 */
				System.err.println("ERROR : anal.base.path isn't defined....");
				System.exit(-1);
			}
			
			if (!Flag.TRUE) System.out.println(String.format("[author=%s] [version=%s] [basePath=%s]", this.author, this.version, this.basePath));

			this.configPath = basePath + "/config";
			this.dataPath   = basePath + "/data";
			this.exePath    = basePath + "/exe";
			this.libPath    = basePath + "/lib";
			this.logPath    = basePath + "/log";
			this.progPath   = basePath + "/prog";
			this.shellPath  = basePath + "/shell";
			this.srcPath    = basePath + "/src";
			this.tempPath   = basePath + "/temp";
			
			if (!Flag.TRUE) {
				System.out.println(String.format("[config=%s]", this.configPath));
				System.out.println(String.format("[data  =%s]", this.dataPath));
				System.out.println(String.format("[exe   =%s]", this.exePath));
				System.out.println(String.format("[lib   =%s]", this.libPath));
				System.out.println(String.format("[log   =%s]", this.logPath));
				System.out.println(String.format("[prog  =%s]", this.progPath));
				System.out.println(String.format("[shell =%s]", this.shellPath));
				System.out.println(String.format("[src   =%s]", this.srcPath));
				System.out.println(String.format("[temp  =%s]", this.tempPath));
			}
		}
	}
	
	/**
	 * get the instance of BasePath class
	 * @return
	 */
	public static synchronized BasePath getInstance()
	{
		if (instance == null) {
			instance = new BasePath();
		}
		
		return instance;
	}
	
	public String getBasePath()
	{
		return this.basePath;
	}
	
	public String getConfigPath()
	{
		return this.configPath;
	}
	
	public String getDataPath()
	{
		return this.dataPath;
	}
	
	public String getExePath()
	{
		return this.exePath;
	}
	
	public String getLibPath()
	{
		return this.libPath;
	}
	
	public String getLogPath()
	{
		return this.logPath;
	}
	
	public String getProgPath()
	{
		return this.progPath;
	}
	
	public String getShellPath()
	{
		return this.shellPath;
	}
	
	public String getSrcPath()
	{
		return this.srcPath;
	}
	
	public String getTempPath()
	{
		return this.tempPath;
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
		if (!Flag.TRUE) {
			try {
				Properties prop = System.getProperties();
				prop.list(System.out);
				System.out.println("-------------------------------------------------------");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		if (Flag.TRUE) {
			try {
				Properties prop = System.getProperties();
				String author = prop.getProperty("dev.author", "NOT DEFINE");
				String version = prop.getProperty("dev.version", "NOT DEFINE");
				String basePath = prop.getProperty("anal.base.path", "NOT DEFINE");
				
				System.out.println(String.format("[author=%s] [version=%s] [basePath=%s]", author, version, basePath));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	private static void test02()
	{
		if (Flag.TRUE) {
			SystemProperties.setTesting("010");
		}
		
		if (Flag.TRUE) {
			SystemProperties prop = SystemProperties.getInstance();
			
			System.out.println("[" + prop.getOsName         () + "]");
			System.out.println("[" + prop.getUserName       () + "]");
			System.out.println("[" + prop.getFileEncoding   () + "]");
			System.out.println("[" + prop.getAnalBasePath   () + "]");
			System.out.println("[" + prop.getAnalServiceCode() + "]");
			System.out.println("[" + prop.getAnalLoggerFlag () + "]");
		}
		
		if (Flag.TRUE) {
			AnalProperties prop = AnalProperties.getInstance();
			
			System.out.println("[" + prop.getUse            () + "]");
			System.out.println("[" + prop.getCode           () + "]");
			System.out.println("[" + prop.getName           () + "]");
			System.out.println("[" + prop.getTitle          () + "]");
			System.out.println("[" + prop.getServiceBasePath() + "]");
		}
	}
	
	/**
	 * test-10
	 */
	private static void test10()
	{
		if (Flag.TRUE) {
			SystemProperties.setTesting("010");
		}
		
		if (Flag.TRUE) {
			System.out.println(String.format("[base  =%s]", BasePath.getInstance().getBasePath()));
			System.out.println(String.format("[config=%s]", BasePath.getInstance().getConfigPath()));
			System.out.println(String.format("[data  =%s]", BasePath.getInstance().getDataPath()));
			System.out.println(String.format("[exe   =%s]", BasePath.getInstance().getExePath()));
			System.out.println(String.format("[lib   =%s]", BasePath.getInstance().getLibPath()));
			System.out.println(String.format("[log   =%s]", BasePath.getInstance().getLogPath()));
			System.out.println(String.format("[prog  =%s]", BasePath.getInstance().getProgPath()));
			System.out.println(String.format("[shell =%s]", BasePath.getInstance().getShellPath()));
			System.out.println(String.format("[src   =%s]", BasePath.getInstance().getSrcPath()));
			System.out.println(String.format("[temp  =%s]", BasePath.getInstance().getTempPath()));
		}
	}
	
	/**
	 * main entry
	 * @param args
	 */
	public static void main(String[] args)
	{
		if (!Flag.TRUE) test01();
		if (!Flag.TRUE) test02();
		
		if (Flag.TRUE) test10();
	}
}
