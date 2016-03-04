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

package kiea.proj.sdc.anal.base.lya.manager.v01;

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
import kiea.proj.sdc.anal.common.DefValue;
import kiea.proj.sdc.anal.util.BasePath;
import kiea.proj.sdc.anal.util.log.v01.Logger;

/**
 * @author KangSeok
 * @date 2014. 8. 5.
 * @file_name ManagerMain.java
 *
 */
public class ManagerMain
{
	private Properties prop = null;
	
	private List<Vector<String>> lstCmd = null;
	
	/**
	 * constructor
	 */
	public ManagerMain()
	{
		if (Flag.TRUE) {
			/*
			 * 반드시 먼저 선언해야 할 것
			 * 1. base path : ..../qaf 까지
			 * 2. 사용할 로그정보
			 */
			BasePath.getInstance();

			try {
				Logger.getInstance(null, DefValue.managerLogName);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * get the manager properties
	 */
	@SuppressWarnings("resource")
	private void getProperties()
	{
		if (Flag.TRUE) {
			/*
			 * inetd.cmd 의 구조를 유연하게 작성함.
			 *   1. inetd.cmd 의 키는 무조건 적으로 sort 된다.
			 *   2. sort 된 상태에서 순서적으로 실행한다.
			 *   3. entry 중에 같은 group 이면 동시에 실행된다.
			 *   4. 한 group 의 모든 entry가 종료되면 다음 group 이 실행된다.
			 *   5. group 키나 item 키는 영문 대/소 숫자로 이루어 지는 것을 원칙으로 한다.
			 *   6. TODO : 2014.07.29 나중에 manager properties 를 읽어 sort된 cmd entry 를 보여주는 프로그램을 작성한다.
			 */
			try {
				prop = new Properties();
				prop.load(new InputStreamReader(new FileInputStream(BasePath.getInstance().getConfigPath() + "/manager.properties"), DefValue.managerCharset));
				if (!Flag.TRUE) prop.list(System.out);
				
				/*
				 * inetd.cmd 만 얻어서 SortedMap에 넣는다.
				 */
				SortedMap<String,String> map = new TreeMap<String, String>();
				
				for (Map.Entry<Object, Object> entry : prop.entrySet()) {
					
					String grpKey = ((String)entry.getKey()).substring(0, 10);
					if (!"inetd.cmd.".equals(grpKey))
						continue;

					/*
					 * KEY_[HHMM]을 바꾼다.
					 */
					String strCmd = ((String)entry.getValue()).replace("[HHMM]", DateTime.getTime(5));

					if (!Flag.TRUE) Logger.info("[%s] [%s] [%s]->[%s]", grpKey, entry.getKey(), entry.getValue(), strCmd);
					
					map.put((String) entry.getKey(), strCmd);
				}

				if (Flag.TRUE) {
					/*
					 * sorting 결과를 출력한다.
					 */
					for (Map.Entry<String, String> entry : map.entrySet()) {
						Logger.info("[%s]=>[%s]", entry.getKey(), entry.getValue());
					}
					
					Logger.info("=========================================================");
				}
				
				/*
				 * Vector에 넣는다.
				 */
				lstCmd = new ArrayList<Vector<String>>();
				
				String grp = "";
				Vector<String> vecCmd = null;
				
				for (Map.Entry<String, String> entry : map.entrySet()) {
					if (!Flag.TRUE) Logger.info("[" + entry.getKey() + ":" + entry.getValue() + "]");
					
					String[] item = ((String) entry.getKey()).split("\\.");
					if (!Flag.TRUE) Logger.info("[%s]-[%s][%s][%s][%s]", grp, item[0], item[1], item[2], item[3]);
					
					if (!grp.equals(item[2])) {
						vecCmd = new Vector<String>();
						lstCmd.add(vecCmd);
						grp = item[2];
					}
					
					vecCmd.add((String) entry.getValue());
				}
				
				if (Flag.TRUE) {
					/*
					 * List Vector를 출력한다.
					 */
					for (Vector<String> vec : lstCmd) {
						for (String cmd : vec) {
							Logger.info("%s", cmd);
						}
						Logger.info("-------------------------------------------------------");
					}
				}
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		if (!Flag.TRUE) {
			try {
				
				prop = new Properties();
				prop.load(new InputStreamReader(new FileInputStream(BasePath.getInstance().getConfigPath() + "/manager.properties"), DefValue.managerCharset));
				if (!Flag.TRUE) prop.list(System.out);

				lstCmd = new ArrayList<Vector<String>>();
				
				for (int i=1; i < 1000; i++) {
					
					Vector<String> vecCmd = null;
					
					for (int j=1; j < 100; j++) {
						String propKey = String.format("%s.%s.%03d.%02d", "inetd", "cmd", i, j);

						String strCmd = prop.getProperty(propKey);
						if (strCmd == null)
							continue;
						
						//////////
						
						if (!Flag.TRUE) System.out.println(String.format("[%s]=[%s]", propKey, strCmd));

						if (vecCmd == null) {
							vecCmd = new Vector<String>();
						}
						
						vecCmd.add(strCmd);
					}
					
					if (vecCmd != null) {
						lstCmd.add(vecCmd);
					}
				}
				
				if (!Flag.TRUE) {
					/*
					 *  목록 출력해 본다.
					 */
					for (Vector<String> vecCmd : lstCmd) {
						for (String strCmd : vecCmd) {
							System.out.println(String.format("strCmd = [%s].", strCmd));
						}
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * start threads for running the macros
	 */
	private void startThreads()
	{
		if (Flag.TRUE) {
			try {
				
				for (Vector<String> vecCmd : lstCmd) {
					
					Thread[] lstThread = new ScheduleThread[vecCmd.size()];
					
					/*
					 * cmd-schedule thread를 실행한다.
					 */
					for (int i=0; i < vecCmd.size(); i++) {

						if (Flag.TRUE) Logger.info("Create ScheduleThread : (%d) [%s]", i, vecCmd.get(i));
						
						lstThread[i] = new ScheduleThread(vecCmd.get(i));
						lstThread[i].start();
					}

					/*
					 * cmd-schedule thread의 join을 실행한다.
					 * 모든 thread가 마칠 때까지 기다린다.
					 */
					for (int i=0; i < vecCmd.size(); i++) {
						// join the threads

						if (Flag.TRUE) Logger.info("Join ScheduleThread : (%d) [%s]", i, vecCmd.get(i));
						
						lstThread[i].join(5 * 60 * 1000);   // wait for thread finish
					}
				}
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * execution function
	 */
	public void execute()
	{
		long time1, time2;
		time1 = System.nanoTime();
		if (Flag.TRUE) Logger.info("=========================================================");
		if (Flag.TRUE) Logger.info("########## MANAGER START");
		
		
		if (Flag.TRUE) {
			/*
			 * manager.properties 값을 읽어들인다.
			 */
			if (Flag.TRUE) getProperties();
			
			/*
			 * cmd 에 해당하는 명령어를 실행한다.
			 */
			if (Flag.TRUE) startThreads();
		}
		
		time2 = System.nanoTime();
		if (Flag.TRUE) Logger.info("########## MANAGER FINISH : took time : %d ms", (time2 - time1)/1000/1000);
		if (Flag.TRUE) Logger.info("=========================================================");

		Logger.exit();
	}
	
	///////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////
	
	private static void test01()
	{
		if (Flag.TRUE) {
			/*
			 * 병렬처리를 위한 로직 테스트
			 */
			String[] cmds = {
					"sample01  KEY_[HHMM] 200000  SAMPLE0024 ",
			};
			
			for (String cmd : cmds) {
				Thread schThread = new ScheduleThread(cmd);
				schThread.start();                                   // 동시실행
			}
		}
	}
	
	public static void main(String[] args)
	{
		if (!Flag.TRUE) test01();
		
		if (Flag.TRUE) new ManagerMain().execute();
	}
}
