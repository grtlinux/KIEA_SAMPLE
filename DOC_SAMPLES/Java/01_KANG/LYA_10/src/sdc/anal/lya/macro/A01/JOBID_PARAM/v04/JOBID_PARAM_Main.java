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

package sdc.anal.lya.macro.A01.JOBID_PARAM.v04;

import kiea.kr.co.tain.base.Flag;
import kiea.kr.co.tain.util.Print;
import kiea.kr.co.tain.util.SystemProperties;
import kiea.proj.sdc.anal.util.BasePath;
import kiea.proj.sdc.anal.util.ServiceMacroProperties;
import kiea.proj.sdc.anal.util.log.v02.Logger;

/**
 * @author KangSeok
 * @date 2014. 8. 26.
 * @file_name AIB_JOBID_PARAM_Main.java
 *
 */
public class JOBID_PARAM_Main
{
	//public static String jobId = "AR010141221A3215";
	public static String jobId = "AR010141229A3154";
	//public static String jobId = "AR010141229A3149";
	protected static String dsName = "A01_" + "JOBID_PARAM";
	protected static ServiceMacroProperties prop = null;
	
	protected static long cntWList = 0;
	protected static long runMSec = 0;
	
	protected static int retValue = 0;
	
	public JOBID_PARAM_Main(String[] args)
	{
		if (Flag.TRUE) {
			if (args.length == 2) {
				/*
				 * setting
				 */
				jobId = args[0];
				dsName = args[1];
			}
		}
		
		if (Flag.TRUE) {
			/*
			 * 반드시 먼저 선언해야 할 것
			 * 1. base path : ..../qaf 까지
			 */
			BasePath.getInstance();

			/*
			 * 사용할 로그정보
			 */
			try {
				Logger.getInstance(jobId, dsName + ".log");
			} catch (Exception e) {
				e.printStackTrace();
			}

			//Parameter.getInstance(this.jobid);
		}
		
		if (Flag.TRUE) {
			prop = ServiceMacroProperties.getInstance();
			if (Flag.TRUE) Logger.info("[anal.service.title = %s]", prop.get("anal.service.title"));
			if (!Flag.TRUE) Print.println("[anal.service.title = %s]", prop.get("anal.service.title"));
		}
	}

	/**
	 * 
	 * runMacroJob
	 *
	 */
	public void runMacroJob()
	{
		if (Flag.TRUE) {

			if (Flag.TRUE) {
				if (Flag.TRUE) new JOBID_PARAM_MacroJob (jobId).executeMacroJob();
			}
		}
		
		Logger.exit();
	}
	
	///////////////////////////////////////////////////////////////////////////
	
	/**
	 * 
	 * test01
	 *
	 */
	private static void test01(String[] args)
	{
		if (Flag.TRUE) {
			SystemProperties.setTesting("010");
		}
		
		if (Flag.TRUE) {
			new JOBID_PARAM_Main(args).runMacroJob();
			
			System.exit(retValue);
		}
	}
	
	/**
	 * 
	 * main
	 *
	 * @param args
	 */
	public static void main(String[] args)
	{
		if (Flag.TRUE) test01(args);
	}
}
