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

package kiea.proj.sdc.anal.base.lcmg.manager.v03;

import java.util.List;
import java.util.Vector;

import kiea.kr.co.tain.base.Flag;
import kiea.proj.sdc.anal.util.log.v02.Logger;

/**
 * @author KangSeok
 * @date 2014. 9. 16.
 * @file_name JobIdThread.java
 *
 */
public class JobIdThread extends Thread
{
	private String jobId = null;
	private List<Vector<String>> list = null;
	
	public JobIdThread(String jobId)
	{
		if (Flag.TRUE) {
			this.jobId = jobId;
		}
	}
	
	public void run()
	{
		if (Flag.TRUE) {
			try {
				
				list = HandleProperties.getInetdCmdList(jobId);
				
				for (Vector<String> vector : list) {
					
					Thread[] thr = new ScheduleThread[vector.size()];
					
					for (int idx=0; idx < vector.size(); idx++) {

						if (Flag.TRUE) Logger.info("(%d) [%s] : Create ScheduleThread", idx, vector.get(idx));
						
						thr[idx] = new ScheduleThread(vector.get(idx));
						thr[idx].start();
					}

					for (int idx=0; idx < vector.size(); idx++) {
						// join the threads

						if (Flag.TRUE) Logger.info("(%d) [%s] : Join ScheduleThread", idx, vector.get(idx));
						
						thr[idx].join(5 * 60 * 1000);   // wait for thread finish
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}
	}

	///////////////////////////////////////////////////////////////////////////
	
	private static void test01()
	{
		if (Flag.TRUE) {
			
			String jobId = "AU010140915A0001";
			
			Thread thr = new JobIdThread(jobId);
			thr.start();
		}
	}
	
	public static void main(String[] args)
	{
		if (Flag.TRUE) test01();
	}
}
