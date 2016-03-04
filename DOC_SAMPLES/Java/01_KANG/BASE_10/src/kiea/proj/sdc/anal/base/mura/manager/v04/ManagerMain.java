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

import java.util.List;

import kiea.kr.co.tain.base.Flag;
import kiea.kr.co.tain.util.SystemProperties;
import kiea.proj.sdc.anal.util.AnalProperties;
import kiea.proj.sdc.anal.util.BasePath;
import kiea.proj.sdc.anal.util.ServiceInetdProperties;
import kiea.proj.sdc.anal.util.ServiceManagerProperties;
import kiea.proj.sdc.anal.util.log.v02.Logger;

/**
 * @author KangSeok
 * @date 2014. 9. 16.
 * @file_name ManagerMain.java
 *
 */
public class ManagerMain
{
	private ServiceManagerProperties prop = null;
	private ServiceInetdProperties propInetd = null;
	private String logFile = null;
	private String strThreadJobMax = null;
	private String strThreadWaitSec = null;
	
	protected static String strServerType = "P";   // P(Primary) S(Secondary)

	/**
	 * constructor
	 */
	public ManagerMain()
	{
		if (Flag.TRUE) {
			/*
			 * DATE 2014.11.17 : 추가
			 */
			this.propInetd = ServiceInetdProperties.getInstance();
			strServerType = propInetd.get("anal.server.type").substring(0, 1).toUpperCase();
		}
		
		if (Flag.TRUE) {
			this.prop = ServiceManagerProperties.getInstance();
			this.logFile = this.prop.get("anal.service.log.file");
			this.strThreadJobMax = prop.get("anal.service.thread.job.max");
			this.strThreadWaitSec = prop.get("anal.service.thread.wait.sec");
		}

		if (Flag.TRUE) {
			/*
			 * 반드시 먼저 선언해야 할 것
			 * 1. base path : ..../qaf 까지
			 * 2. 사용할 로그정보
			 */
			BasePath.getInstance();

			try {
				Logger.getInstance(AnalProperties.getInstance().getServiceBasePath(), null, this.logFile);
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
			
			int nThreadWaitSec = Integer.parseInt(this.strThreadWaitSec);
			
			try {
				/*
				 * JOB_ID 목록을 구한다.
				 */
				List<String> list = HandleAnalJobId.getReadyJobId(this.strThreadJobMax);
				
				if (list.size() > 0) {
					/*
					 * Thread를 생성한다.
					 * 
					 * TODO : 2014.10.07 : Thread Block 형태로 실행
					 */
					Thread[] thr = new Thread[list.size()];
					
					for (int idx=0; idx < list.size(); idx++) {
						thr[idx] = new JobIdThread(list.get(idx));
						HandleAnalJobId.setRunningStatus(list.get(idx));
						
						thr[idx].start();
					}
					
					/*
					 * wait for thr job finish
					 */
					for (int idx=0; idx < list.size(); idx++) {
						thr[idx].join(nThreadWaitSec * 1000);   // wait for thread finish
						//HandleAnalJobId.setFinishStatus(list.get(idx));
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		time2 = System.nanoTime();
		if (Flag.TRUE) Logger.info("########## MANAGER FINISH : took time : %d ms", (time2 - time1)/1000/1000);
		if (Flag.TRUE) Logger.info("=========================================================");

		Logger.exit();
	}

	///////////////////////////////////////////////////////////////////////////
	
	private static void test01()
	{
		if (Flag.TRUE) {
			new ManagerMain().execute();
		}
	}
	
	private static void test02()
	{
		if (Flag.TRUE) {
			SystemProperties.setTesting("020");
		}
		
		if (Flag.TRUE) {
			new ManagerMain().execute();
		}
	}
	
	public static void main(String[] args)
	{
		if (!Flag.TRUE) test01();
		if (Flag.TRUE) test02();
	}
}
