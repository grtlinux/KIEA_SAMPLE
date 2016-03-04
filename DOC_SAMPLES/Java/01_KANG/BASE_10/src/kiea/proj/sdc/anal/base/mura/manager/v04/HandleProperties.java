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

package kiea.proj.sdc.anal.base.mura.manager.v04;

import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.Vector;

import kiea.kr.co.tain.base.Flag;
import kiea.kr.co.tain.util.DateTime;
import kiea.kr.co.tain.util.Print;
import kiea.proj.sdc.anal.common.DefValue;
import kiea.proj.sdc.anal.util.BasePath;
import kiea.proj.sdc.anal.util.log.v02.Logger;

/**
 * @author KangSeok
 * @date 2014. 9. 16.
 * @file_name HandleProperties.java
 *
 */
public class HandleProperties
{
	@SuppressWarnings("resource")
	public static List<Vector<String>> getInetdCmdList(String jobId)
	{
		List<Vector<String>> list = new ArrayList<Vector<String>>();

		if (Flag.TRUE) {
			try {
				
				Properties prop = new Properties();
				prop.load(new InputStreamReader(new FileInputStream(BasePath.getInstance().getConfigPath() + "/manager.properties"), DefValue.managerCharset));
				if (!Flag.TRUE) prop.list(System.out);
				
				/*
				 * inetd.cmd 만 얻어서 SortedMap에 넣는다.
				 */
				SortedMap<String, String> map = new TreeMap<String, String>();
				
				for (Map.Entry<Object, Object> entry : prop.entrySet()) {
					
					String grpKey = ((String)entry.getKey()).substring(0, 10);
					if (!"inetd.cmd.".equals(grpKey))
						continue;

					/*
					 * KEY_[HHMM]을 바꾼다.
					 */
					String strCmd = ((String)entry.getValue()).replace("[HHMM]", DateTime.getTime(5));

					/*
					 * [JOB_ID]을 바꾼다.
					 */
					strCmd = strCmd.replace("[JOB_ID]", jobId);

					if (!Flag.TRUE) Logger.info("[%s] [%s] [%s]->[%s]", grpKey, entry.getKey(), entry.getValue(), strCmd);
					
					map.put((String) entry.getKey(), strCmd);
				}

				if (Flag.TRUE) {
					/*
					 * 출력한다.
					 */
					for (Map.Entry<String, String> entry : map.entrySet()) {
						if (Flag.TRUE) Logger.info("[%s] => [%s]", entry.getKey(), entry.getValue());
					}
				}
				
				/*
				 * Vector에 넣는다.
				 */
				String grp = "";
				Vector<String> vec = null;
				
				for (Map.Entry<String, String> entry : map.entrySet()) {
					if (!Flag.TRUE) Logger.info("[" + entry.getKey() + ":" + entry.getValue() + "]");
					
					String[] item = ((String) entry.getKey()).split("\\.");
					if (!Flag.TRUE) Logger.info("[%s]-[%s][%s][%s][%s]", grp, item[0], item[1], item[2], item[3]);
					
					if (!grp.equals(item[2])) {
						vec = new Vector<String>();
						list.add(vec);
						grp = item[2];
					}
					
					vec.add((String) entry.getValue());
				}
				
				if (!Flag.TRUE) {
					/*
					 * 출력한다.
					 */
					for (Vector<String> vecCmd : list) {
						for (String cmd : vecCmd) {
							Logger.info("%s", cmd);
						}
					}
				}
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		return list;
	}
	
	///////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////
	
	@SuppressWarnings("resource")
	private static void test01()
	{
		List<Vector<String>> list = new ArrayList<Vector<String>>();

		if (Flag.TRUE) {
			try {
				Properties prop = new Properties();
				prop.load(new InputStreamReader(new FileInputStream("D:/ANAL/anal/LYA/config" + "/manager.properties"), DefValue.managerCharset));
				if (!Flag.TRUE) prop.list(System.out);
				
				/*
				 * inetd.cmd 만 얻어서 SortedMap에 넣는다.
				 */
				SortedMap<String, String> map = new TreeMap<String, String>();
				
				for (Map.Entry<Object, Object> entry : prop.entrySet()) {
					
					String grpKey = ((String)entry.getKey()).substring(0, 10);
					if (!"inetd.cmd.".equals(grpKey))
						continue;

					/*
					 * KEY_[HHMM]을 바꾼다.
					 */
					String strCmd = ((String)entry.getValue()).replace("[HHMM]", DateTime.getTime(5));

					/*
					 * [JOB_ID]을 바꾼다.
					 */
					strCmd = strCmd.replace("[JOB_ID]", "AU010140915A0001");

					if (!Flag.TRUE) Logger.info("[%s] [%s] [%s]->[%s]", grpKey, entry.getKey(), entry.getValue(), strCmd);
					
					map.put((String) entry.getKey(), strCmd);
				}

				if (Flag.TRUE) {
					/*
					 * 출력한다.
					 */
					for (Map.Entry<String, String> entry : map.entrySet()) {
						if (Flag.TRUE) Print.println("[%s] => [%s]", entry.getKey(), entry.getValue());
					}
				}
				
				/*
				 * Vector에 넣는다.
				 */
				String grp = "";
				Vector<String> vec = null;
				
				for (Map.Entry<String, String> entry : map.entrySet()) {
					if (!Flag.TRUE) Logger.info("[" + entry.getKey() + ":" + entry.getValue() + "]");
					
					String[] item = ((String) entry.getKey()).split("\\.");
					if (!Flag.TRUE) Logger.info("[%s]-[%s][%s][%s][%s]", grp, item[0], item[1], item[2], item[3]);
					
					if (!grp.equals(item[2])) {
						vec = new Vector<String>();
						list.add(vec);
						grp = item[2];
					}
					
					vec.add((String) entry.getValue());
				}
				
				if (Flag.TRUE) {
					/*
					 * 출력한다.
					 */
					for (Vector<String> vecCmd : list) {
						for (String cmd : vecCmd) {
							Print.println("%s", cmd);
						}
					}
				}
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void main(String[] args)
	{
		if (Flag.TRUE) test01();
	}
}
