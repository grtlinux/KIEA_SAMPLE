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

import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Properties;

import kiea.kr.co.tain.base.Default;
import kiea.kr.co.tain.base.Flag;

/**
 * @author KangSeok
 * @date 2014. 10. 1.
 * @file_name FileProperties.java
 *
 */
public class FileProperties
{
	private Properties prop = null;
	
	/**
	 * 
	 * @param file
	 */
	public FileProperties(String file)
	{
		if (Flag.TRUE) {
			try {
				this.prop = new Properties();
				FileInputStream fis = new FileInputStream(file);
				this.prop.load(new InputStreamReader(fis, Default.CHARACTER_SET));
				fis.close();
				if (!Flag.TRUE) this.prop.list(System.out);
			} catch (Exception e) {
				e.printStackTrace();
				if (Flag.TRUE) System.exit(-1);
			}
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
	 * get
	 * 
	 * @param paramKey
	 * @param defaultValue
	 * @return
	 */
	public String get(String paramKey, String defaultValue)
	{
		String ret = null;
		
		if (Flag.TRUE) {
			ret = this.prop.getProperty(paramKey, defaultValue);
		}
		
		return ret;
	}
	
	/**
	 * 
	 * @return
	 */
	public Properties getProperties()
	{
		return this.prop;
	}
	
	///////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////
	
	private static void test01()
	{
		String analSvcCd = "010";
		
		if (Flag.TRUE) {
			SystemProperties.setTesting(analSvcCd);
			if (!Flag.TRUE) SystemProperties.getInstance().printList();
		}
		
		if (Flag.TRUE) {
			String basePath = SystemProperties.getInstance().getAnalBasePath();
			if (basePath != null) {
				String file = basePath + "/config/anal.properties";
				FileProperties prop = new FileProperties(file);
				prop.printList();
				System.out.println("USE       : " + prop.get("anal.service." + analSvcCd + ".use"));
				System.out.println("NAME      : " + prop.get("anal.service." + analSvcCd + ".name"));
				System.out.println("TITLE     : " + prop.get("anal.service." + analSvcCd + ".title"));
				System.out.println("BASE_PATH : " + prop.get("anal.service." + analSvcCd + ".base.path").replace("{anal.base.path}", basePath));
			}
		}
	}
	
	public static void main(String[] args)
	{
		if (Flag.TRUE) test01();
	}
}
