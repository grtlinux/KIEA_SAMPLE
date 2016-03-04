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

package kiea.proj.sdc.anal.macro.sample.a11.img;

import kiea.kr.co.tain.base.Flag;
import kiea.proj.sdc.anal.common.Parameter;
import kiea.proj.sdc.anal.macro.sample.a11.img.job.BUBBLE_IDX_MacroJob;
import kiea.proj.sdc.anal.macro.sample.a11.img.job.CONTACT_MAP_MacroJob;
import kiea.proj.sdc.anal.macro.sample.a11.img.job.MURA_IMAGE_MacroJob;
import kiea.proj.sdc.anal.macro.sample.a11.img.macro.AVG_MURA_MAP_XY_4SF_RESULT.AVG_MURA_MAP_XY_4SF_RESULT_MacroJob;
import kiea.proj.sdc.anal.macro.sample.a11.img.macro.BUBBLE_CLUSTER_CELLLOC.BUBBLE_CLUSTER_CELLLOC_MacroJob;
import kiea.proj.sdc.anal.macro.sample.a11.img.macro.CONTACT_MAP_XY_DIST_RESULT.CONTACT_MAP_XY_DIST_RESULT_MacroJob;
import kiea.proj.sdc.anal.macro.sample.a11.img.macro.CONTACT_MAP_XY_RESULT.CONTACT_MAP_XY_RESULT_MacroJob;
import kiea.proj.sdc.anal.macro.sample.a11.img.macro.CONTACT_MURA_DIST_RESULT.CONTACT_MURA_DIST_RESULT_MacroJob;
import kiea.proj.sdc.anal.macro.sample.a11.img.macro.CONTACT_MURA_XY_RESULT.CONTACT_MURA_XY_RESULT_MacroJob;
import kiea.proj.sdc.anal.macro.sample.a11.img.macro.CONTACT_MUR_DIST_4SF_RESULT.CONTACT_MUR_DIST_4SF_RESULT_MacroJob;
import kiea.proj.sdc.anal.macro.sample.a11.img.macro.MURA_MAP_XY_4SF_RESULT.MURA_MAP_XY_4SF_RESULT_MacroJob;
import kiea.proj.sdc.anal.macro.sample.a11.img.macro.MURA_MAP_XY_RESULT.MURA_MAP_XY_RESULT_MacroJob;
import kiea.proj.sdc.anal.macro.sample.a11.img.macro.OUT_DBSCAN_RESULT.OUT_DBSCAN_RESULT_MacroJob;
import kiea.proj.sdc.anal.macro.sample.a11.img.macro.OUT_DBSCAN_RESULT1.OUT_DBSCAN_RESULT1_MacroJob;
import kiea.proj.sdc.anal.macro.sample.a11.img.macro.RP_MUR_AVGMUR_MAP_SUMM.RP_MUR_AVGMUR_MAP_SUMM_MacroJob;
import kiea.proj.sdc.anal.macro.sample.a11.img.macro.RP_MUR_BUBBLE_MAP_SP_SUMM.RP_MUR_BUBBLE_MAP_SP_SUMM_MacroJob;
import kiea.proj.sdc.anal.macro.sample.a11.img.macro.RP_MUR_CONTACT_MAP_SUMM.RP_MUR_CONTACT_MAP_SUMM_MacroJob;
import kiea.proj.sdc.anal.macro.sample.a11.img.macro.RP_MUR_TOTMURA_MAP_SUMM.RP_MUR_TOTMURA_MAP_SUMM_MacroJob;
import kiea.proj.sdc.anal.util.BasePath;
import kiea.proj.sdc.anal.util.log.v02.Logger;

/**
 * @author KangSeok
 * @date 2014. 8. 20.
 * @file_name MuraImgMain.java
 *
 */
public class MuraImgMain
{
	private String jobKeyPath = "KEY_HHMM";   // JOBID, JOBKEY
	private String dsName = "SAMPLE03";
	
	public MuraImgMain(String[] args)
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

			if (!Flag.TRUE) {
				Parameter.getInstance(7);
				if (Flag.TRUE) new OUT_DBSCAN_RESULT_MacroJob           (jobKeyPath, dsName).executeMacroJob();
				if (Flag.TRUE) new MURA_IMAGE_MacroJob                  (jobKeyPath, dsName).executeMacroJob();
				if (Flag.TRUE) new OUT_DBSCAN_RESULT1_MacroJob          (jobKeyPath, dsName).executeMacroJob();
				if (Flag.TRUE) new BUBBLE_IDX_MacroJob                  (jobKeyPath, dsName).executeMacroJob();
				if (Flag.TRUE) new BUBBLE_CLUSTER_CELLLOC_MacroJob      (jobKeyPath, dsName).executeMacroJob();
				if (Flag.TRUE) new RP_MUR_BUBBLE_MAP_SP_SUMM_MacroJob   (jobKeyPath, dsName).executeMacroJob();
				if (Flag.TRUE) new MURA_MAP_XY_4SF_RESULT_MacroJob      (jobKeyPath, dsName).executeMacroJob();
				if (Flag.TRUE) new RP_MUR_TOTMURA_MAP_SUMM_MacroJob     (jobKeyPath, dsName).executeMacroJob();
				if (Flag.TRUE) new CONTACT_MAP_MacroJob                 (jobKeyPath, dsName).executeMacroJob();

				if (Flag.TRUE) new CONTACT_MAP_XY_RESULT_MacroJob       (jobKeyPath, dsName).executeMacroJob();
				if (Flag.TRUE) new MURA_MAP_XY_RESULT_MacroJob          (jobKeyPath, dsName).executeMacroJob();
				if (Flag.TRUE) new CONTACT_MURA_XY_RESULT_MacroJob      (jobKeyPath, dsName).executeMacroJob();
				if (Flag.TRUE) new CONTACT_MAP_XY_DIST_RESULT_MacroJob  (jobKeyPath, dsName).executeMacroJob();
				if (Flag.TRUE) new CONTACT_MURA_DIST_RESULT_MacroJob    (jobKeyPath, dsName).executeMacroJob();
				if (Flag.TRUE) new AVG_MURA_MAP_XY_4SF_RESULT_MacroJob  (jobKeyPath, dsName).executeMacroJob();
				if (Flag.TRUE) new RP_MUR_AVGMUR_MAP_SUMM_MacroJob      (jobKeyPath, dsName).executeMacroJob();
				if (Flag.TRUE) new CONTACT_MUR_DIST_4SF_RESULT_MacroJob (jobKeyPath, dsName).executeMacroJob();
				if (Flag.TRUE) new RP_MUR_CONTACT_MAP_SUMM_MacroJob     (jobKeyPath, dsName).executeMacroJob();
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
			new MuraImgMain(args).runMacroJob();
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
