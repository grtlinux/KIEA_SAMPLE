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

package kiea.proj.sdc.anal.macro.sample.a11.preanal;

import kiea.kr.co.tain.base.Flag;
import kiea.proj.sdc.anal.common.Parameter;
import kiea.proj.sdc.anal.macro.sample.a11.preanal.job.MURA_RAWDATA_MacroJob;
import kiea.proj.sdc.anal.macro.sample.a11.preanal.macro.BAD_GLASS.BAD_GLASS_MacroJob;
import kiea.proj.sdc.anal.macro.sample.a11.preanal.macro.BAD_GLASS_NO.BAD_GLASS_NO_MacroJob;
import kiea.proj.sdc.anal.macro.sample.a11.preanal.macro.BAD_GLASS_ORG.BAD_GLASS_ORG_MacroJob;
import kiea.proj.sdc.anal.macro.sample.a11.preanal.macro.CLUSTER_OUTPUT.CLUSTER_OUTPUT_MacroJob;
import kiea.proj.sdc.anal.macro.sample.a11.preanal.macro.FINAL_CLUSTER.FINAL_CLUSTER_MacroJob;
import kiea.proj.sdc.anal.macro.sample.a11.preanal.macro.GOOD_GLASS.GOOD_GLASS_MacroJob;
import kiea.proj.sdc.anal.macro.sample.a11.preanal.macro.GOOD_GLASS_ORG.GOOD_GLASS_ORG_MacroJob;
import kiea.proj.sdc.anal.macro.sample.a11.preanal.macro.IN_DBSCAN.IN_DBSCAN_MacroJob;
import kiea.proj.sdc.anal.macro.sample.a11.preanal.macro.MURA_RAWDATA_ANAL2.MURA_RAWDATA_ANAL2_MacroJob;
import kiea.proj.sdc.anal.macro.sample.a11.preanal.macro.OUT_DBSCAN.OUT_DBSCAN_MacroJob;
import kiea.proj.sdc.anal.macro.sample.a11.preanal.macro.REGROUP_CLUSTER.REGROUP_CLUSTER_MacroJob;
import kiea.proj.sdc.anal.macro.sample.a11.preanal.macro.SO_SYS_GLASS_LIST2.SO_SYS_GLASS_LIST2_MacroJob;
import kiea.proj.sdc.anal.util.BasePath;
import kiea.proj.sdc.anal.util.log.v02.Logger;

/**
 * @author KangSeok
 * @date 2014. 8. 19.
 * @file_name MuraPreAnalMain.java
 *
 */
public class MuraPreAnalMain
{
	private String jobKeyPath = "KEY_HHMM";   // JOBID, JOBKEY
	private String dsName = "SAMPLE03";
	
	public MuraPreAnalMain(String[] args)
	{
		if (Flag.TRUE) {
			if (args.length == 2) {
				/*
				 * setting
				 */
				this.jobKeyPath = args[0];
				this.dsName = args[1];
			}
		}
		
		if (Flag.TRUE) {
			/*
			 * 반드시 먼저 선언해야 할 것
			 * 1. base path : ..../qaf 까지
			 * 2. 사용할 로그정보
			 */
			BasePath.getInstance();

			try {
				Logger.getInstance(this.jobKeyPath, this.dsName + ".log");
			} catch (Exception e) {
				e.printStackTrace();
			}
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
			long time1, time2;
			time1 = System.nanoTime();

			if (Flag.TRUE) {
				Parameter.getInstance(2);
				//if (Flag.TRUE) new MURA_DESC_MacroJob             (jobKeyPath, dsName).executeMacroJob();
				if (Flag.TRUE) new MURA_RAWDATA_MacroJob          (jobKeyPath, dsName).executeMacroJob();
				if (Flag.TRUE) new MURA_RAWDATA_ANAL2_MacroJob    (jobKeyPath, dsName).executeMacroJob();
				
				if (Flag.TRUE) new BAD_GLASS_ORG_MacroJob         (jobKeyPath, dsName).executeMacroJob();
				if (Flag.TRUE) new BAD_GLASS_MacroJob             (jobKeyPath, dsName).executeMacroJob();
				if (Flag.TRUE) new GOOD_GLASS_ORG_MacroJob        (jobKeyPath, dsName).executeMacroJob();
				if (Flag.TRUE) new GOOD_GLASS_MacroJob            (jobKeyPath, dsName).executeMacroJob();

				if (Flag.TRUE) new BAD_GLASS_NO_MacroJob          (jobKeyPath, dsName).executeMacroJob();
				if (Flag.TRUE) new IN_DBSCAN_MacroJob             (jobKeyPath, dsName).executeMacroJob();
				if (Flag.TRUE) new OUT_DBSCAN_MacroJob            (jobKeyPath, dsName).executeMacroJob();
				if (Flag.TRUE) new REGROUP_CLUSTER_MacroJob       (jobKeyPath, dsName).executeMacroJob();
				if (Flag.TRUE) new FINAL_CLUSTER_MacroJob         (jobKeyPath, dsName).executeMacroJob();
				if (Flag.TRUE) new CLUSTER_OUTPUT_MacroJob        (jobKeyPath, dsName).executeMacroJob();
				if (Flag.TRUE) new SO_SYS_GLASS_LIST2_MacroJob    (jobKeyPath, dsName).executeMacroJob();
			}
			
			if (Flag.TRUE) {
			}
			
			
			

			time2 = System.nanoTime();
			if (Flag.TRUE) Logger.info("##### took time : %d ms", (time2 - time1)/1000/1000);
			
			Logger.exit();
		}
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
			new MuraPreAnalMain(args).runMacroJob();
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
