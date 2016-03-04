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

package kiea.proj.sdc.anal.macro.sample.a11.mod;

import kiea.kr.co.tain.base.Flag;
import kiea.proj.sdc.anal.macro.sample.a11.mod.job.MOD_BIN_MacroJob;
import kiea.proj.sdc.anal.macro.sample.a11.mod.job.MOD_EQP_SMMRY_MacroJob;
import kiea.proj.sdc.anal.macro.sample.a11.mod.job.MOD_PKBANK_MacroJob;
import kiea.proj.sdc.anal.macro.sample.a11.mod.job.MOD_RJ_MacroJob;
import kiea.proj.sdc.anal.macro.sample.a11.mod.job.MOD_WORKID_MacroJob;
import kiea.proj.sdc.anal.macro.sample.a11.mod.macro.RP_SYS_MOD_DETAILS_INSPWIP.RP_SYS_MOD_DETAILS_INSPWIP_MacroJob;
import kiea.proj.sdc.anal.macro.sample.a11.mod.macro.RP_SYS_MOD_PKBANK_SUMM.RP_SYS_MOD_PKBANK_SUMM_MacroJob;
import kiea.proj.sdc.anal.macro.sample.a11.mod.macro.RP_SYS_MOD_SUMM_BIN.RP_SYS_MOD_SUMM_BIN_MacroJob;
import kiea.proj.sdc.anal.macro.sample.a11.mod.macro.RP_SYS_MOD_SUMM_INSPWIP.RP_SYS_MOD_SUMM_INSPWIP_MacroJob;
import kiea.proj.sdc.anal.macro.sample.a11.mod.macro.RP_SYS_MOD_SUMM_RJ.RP_SYS_MOD_SUMM_RJ_MacroJob;
import kiea.proj.sdc.anal.macro.sample.a11.mod.macro.RP_SYS_MOD_SUMM_WORKERID.RP_SYS_MOD_SUMM_WORKERID_MacroJob;
import kiea.proj.sdc.anal.macro.sample.a11.mod.macro.SI_SYS_MOD_EQP_01.SI_SYS_MOD_EQP_01_MacroJob;
import kiea.proj.sdc.anal.macro.sample.a11.mod.macro.SI_SYS_MOD_EQP_02.SI_SYS_MOD_EQP_02_MacroJob;
import kiea.proj.sdc.anal.macro.sample.a11.mod.macro.SI_SYS_MOD_EQP_03.SI_SYS_MOD_EQP_03_MacroJob;
import kiea.proj.sdc.anal.macro.sample.a11.mod.macro.SI_SYS_MOD_EQP_04.SI_SYS_MOD_EQP_04_MacroJob;
import kiea.proj.sdc.anal.macro.sample.a11.mod.macro.SI_SYS_MOD_EQP_05.SI_SYS_MOD_EQP_05_MacroJob;
import kiea.proj.sdc.anal.macro.sample.a11.mod.macro.SI_SYS_MOD_EQP_06.SI_SYS_MOD_EQP_06_MacroJob;
import kiea.proj.sdc.anal.macro.sample.a11.mod.macro.SI_SYS_MOD_EQP_07.SI_SYS_MOD_EQP_07_MacroJob;
import kiea.proj.sdc.anal.macro.sample.a11.mod.macro.SO_SYS_MOD_BIN_ANL.SO_SYS_MOD_BIN_ANL_MacroJob;
import kiea.proj.sdc.anal.macro.sample.a11.mod.macro.SO_SYS_MOD_BIN_CP.SO_SYS_MOD_BIN_CP_MacroJob;
import kiea.proj.sdc.anal.macro.sample.a11.mod.macro.SO_SYS_MOD_BIN_CSV.SO_SYS_MOD_BIN_CSV_MacroJob;
import kiea.proj.sdc.anal.macro.sample.a11.mod.macro.SO_SYS_MOD_PKBANK.SO_SYS_MOD_PKBANK_MacroJob;
import kiea.proj.sdc.anal.macro.sample.a11.mod.macro.SO_SYS_MOD_PKBANK_JASU.SO_SYS_MOD_PKBANK_JASU_MacroJob;
import kiea.proj.sdc.anal.macro.sample.a11.mod.macro.SO_SYS_MOD_PKBANK_MOSU.SO_SYS_MOD_PKBANK_MOSU_MacroJob;
import kiea.proj.sdc.anal.macro.sample.a11.mod.macro.SO_SYS_MOD_RJ_ANL.SO_SYS_MOD_RJ_ANL_MacroJob;
import kiea.proj.sdc.anal.macro.sample.a11.mod.macro.SO_SYS_MOD_RJ_CP.SO_SYS_MOD_RJ_CP_MacroJob;
import kiea.proj.sdc.anal.macro.sample.a11.mod.macro.SO_SYS_MOD_RJ_CSV.SO_SYS_MOD_RJ_CSV_MacroJob;
import kiea.proj.sdc.anal.macro.sample.a11.mod.macro.SO_SYS_MOD_WORKID_ANL.SO_SYS_MOD_WORKID_ANL_MacroJob;
import kiea.proj.sdc.anal.macro.sample.a11.mod.macro.SO_SYS_MOD_WORKID_CP.SO_SYS_MOD_WORKID_CP_MacroJob;
import kiea.proj.sdc.anal.macro.sample.a11.mod.macro.SO_SYS_MOD_WORKID_CSV.SO_SYS_MOD_WORKID_CSV_MacroJob;
import kiea.proj.sdc.anal.util.BasePath;
import kiea.proj.sdc.anal.util.log.v02.Logger;

/**
 * @author KangSeok
 * @date 2014. 8. 26.
 * @file_name LyaModMain.java
 *
 */
public class LyaModMain
{
	private String jobKeyPath = "KEY_HHMM";   // JOBID, JOBKEY
	private String dsName = "SAMPLE03";
	
	public LyaModMain(String[] args)
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
				if (Flag.TRUE) new MOD_BIN_MacroJob                   (jobKeyPath, dsName).executeMacroJob();
				if (Flag.TRUE) new MOD_RJ_MacroJob                    (jobKeyPath, dsName).executeMacroJob();
				if (Flag.TRUE) new MOD_WORKID_MacroJob                (jobKeyPath, dsName).executeMacroJob();
				if (Flag.TRUE) new SO_SYS_MOD_BIN_CP_MacroJob         (jobKeyPath, dsName).executeMacroJob();
				if (Flag.TRUE) new SO_SYS_MOD_WORKID_CP_MacroJob      (jobKeyPath, dsName).executeMacroJob();
				if (Flag.TRUE) new SO_SYS_MOD_RJ_CP_MacroJob          (jobKeyPath, dsName).executeMacroJob();
				if (Flag.TRUE) new MOD_EQP_SMMRY_MacroJob             (jobKeyPath, dsName).executeMacroJob();
				if (Flag.TRUE) new MOD_PKBANK_MacroJob                (jobKeyPath, dsName).executeMacroJob();
				if (Flag.TRUE) new SO_SYS_MOD_BIN_ANL_MacroJob        (jobKeyPath, dsName).executeMacroJob();
				if (Flag.TRUE) new SO_SYS_MOD_BIN_CSV_MacroJob        (jobKeyPath, dsName).executeMacroJob();
				if (Flag.TRUE) new SO_SYS_MOD_WORKID_ANL_MacroJob     (jobKeyPath, dsName).executeMacroJob();
				if (Flag.TRUE) new SO_SYS_MOD_WORKID_CSV_MacroJob     (jobKeyPath, dsName).executeMacroJob();
				if (Flag.TRUE) new SO_SYS_MOD_RJ_ANL_MacroJob         (jobKeyPath, dsName).executeMacroJob();
				if (Flag.TRUE) new SO_SYS_MOD_RJ_CSV_MacroJob         (jobKeyPath, dsName).executeMacroJob();
				if (Flag.TRUE) new SI_SYS_MOD_EQP_01_MacroJob         (jobKeyPath, dsName).executeMacroJob();
				if (Flag.TRUE) new SI_SYS_MOD_EQP_02_MacroJob         (jobKeyPath, dsName).executeMacroJob();
				if (Flag.TRUE) new SI_SYS_MOD_EQP_03_MacroJob         (jobKeyPath, dsName).executeMacroJob();
				if (Flag.TRUE) new SI_SYS_MOD_EQP_04_MacroJob         (jobKeyPath, dsName).executeMacroJob();
				if (Flag.TRUE) new SI_SYS_MOD_EQP_05_MacroJob         (jobKeyPath, dsName).executeMacroJob();
				if (Flag.TRUE) new SI_SYS_MOD_EQP_06_MacroJob         (jobKeyPath, dsName).executeMacroJob();
				if (Flag.TRUE) new SI_SYS_MOD_EQP_07_MacroJob         (jobKeyPath, dsName).executeMacroJob();
				if (Flag.TRUE) new SO_SYS_MOD_PKBANK_MOSU_MacroJob    (jobKeyPath, dsName).executeMacroJob();
				if (Flag.TRUE) new SO_SYS_MOD_PKBANK_JASU_MacroJob    (jobKeyPath, dsName).executeMacroJob();
				if (Flag.TRUE) new SO_SYS_MOD_PKBANK_MacroJob         (jobKeyPath, dsName).executeMacroJob();
				if (Flag.TRUE) new RP_SYS_MOD_SUMM_BIN_MacroJob       (jobKeyPath, dsName).executeMacroJob();
				if (Flag.TRUE) new RP_SYS_MOD_SUMM_WORKERID_MacroJob  (jobKeyPath, dsName).executeMacroJob();
				if (Flag.TRUE) new RP_SYS_MOD_SUMM_RJ_MacroJob        (jobKeyPath, dsName).executeMacroJob();
				if (Flag.TRUE) new RP_SYS_MOD_SUMM_INSPWIP_MacroJob   (jobKeyPath, dsName).executeMacroJob();
				if (Flag.TRUE) new RP_SYS_MOD_DETAILS_INSPWIP_MacroJob(jobKeyPath, dsName).executeMacroJob();
				if (Flag.TRUE) new RP_SYS_MOD_PKBANK_SUMM_MacroJob    (jobKeyPath, dsName).executeMacroJob();
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
			new LyaModMain(args).runMacroJob();
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
