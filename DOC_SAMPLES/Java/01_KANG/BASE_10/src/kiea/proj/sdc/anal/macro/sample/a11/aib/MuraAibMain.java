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

package kiea.proj.sdc.anal.macro.sample.a11.aib;

import kiea.kr.co.tain.base.Flag;
import kiea.proj.sdc.anal.common.Parameter;
import kiea.proj.sdc.anal.macro.sample.a11.aib.job.BUBBLE_INDEX_MacroJob;
import kiea.proj.sdc.anal.macro.sample.a11.aib.job.CELL_MAP_MacroJob;
import kiea.proj.sdc.anal.macro.sample.a11.aib.job.MURA_DESC_MacroJob;
import kiea.proj.sdc.anal.macro.sample.a11.aib.job.MURA_D_MacroJob;
import kiea.proj.sdc.anal.macro.sample.a11.aib.job.MURA_HIST_MacroJob;
import kiea.proj.sdc.anal.macro.sample.a11.aib.job.MURA_SPC_MacroJob;
import kiea.proj.sdc.anal.macro.sample.a11.aib.job.MURA_TREND_MacroJob;
import kiea.proj.sdc.anal.macro.sample.a11.aib.job.STEP_DESC_MacroJob;
import kiea.proj.sdc.anal.macro.sample.a11.aib.job.TOTALCELLFLOW_MacroJob;
import kiea.proj.sdc.anal.macro.sample.a11.aib.job.WIP_EQP_SMMRY_MacroJob;
import kiea.proj.sdc.anal.macro.sample.a11.aib.macro.CELL_MAP_XY.CELL_MAP_XY_MacroJob;
import kiea.proj.sdc.anal.macro.sample.a11.aib.macro.EQP_MURA_TRD.EQP_MURA_TRD_MacroJob;
import kiea.proj.sdc.anal.macro.sample.a11.aib.macro.LYA_MUR_1.LYA_MUR_1_MacroJob;
import kiea.proj.sdc.anal.macro.sample.a11.aib.macro.MURA_CNT.MURA_CNT_MacroJob;
import kiea.proj.sdc.anal.macro.sample.a11.aib.macro.MURA_CNT_CELL.MURA_CNT_CELL_MacroJob;
import kiea.proj.sdc.anal.macro.sample.a11.aib.macro.MURA_D_01.MURA_D_01_MacroJob;
import kiea.proj.sdc.anal.macro.sample.a11.aib.macro.MURA_N_TREND.MURA_N_TREND_MacroJob;
import kiea.proj.sdc.anal.macro.sample.a11.aib.macro.MURA_SUM.MURA_SUM_MacroJob;
import kiea.proj.sdc.anal.macro.sample.a11.aib.macro.RP_MUR_BUBBLE_MAP_SUMM.RP_MUR_BUBBLE_MAP_SUMM_MacroJob;
import kiea.proj.sdc.anal.macro.sample.a11.aib.macro.RP_MUR_CELL_MAP_SUMM.RP_MUR_CELL_MAP_SUMM_MacroJob;
import kiea.proj.sdc.anal.macro.sample.a11.aib.macro.RP_MUR_TREND_SUMM.RP_MUR_TREND_SUMM_MacroJob;
import kiea.proj.sdc.anal.macro.sample.a11.aib.macro.TOTAL_CNT.TOTAL_CNT_MacroJob;
import kiea.proj.sdc.anal.macro.sample.a11.aib.macro.T_CNT_CELL.T_CNT_CELL_MacroJob;
import kiea.proj.sdc.anal.macro.sample.a11.aib.macro.Y_AXIS.Y_AXIS_MacroJob;
import kiea.proj.sdc.anal.macro.sample.a11.aib.macro.Y_AXIS_CELL.Y_AXIS_CELL_MacroJob;
import kiea.proj.sdc.anal.util.BasePath;
import kiea.proj.sdc.anal.util.log.v02.Logger;

/**
 * @author KangSeok
 * @date 2014. 8. 8.
 * @file_name MuraAibMain.java
 *
 */
public class MuraAibMain
{
	private String jobKeyPath = "KEY_HHMM";   // JOBID, JOBKEY
	private String dsName = "SAMPLE03";
	
	public MuraAibMain(String[] args)
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
				if (Flag.TRUE) new WIP_EQP_SMMRY_MacroJob         (jobKeyPath, dsName).executeMacroJob();
				if (Flag.TRUE) new MURA_SPC_MacroJob              (jobKeyPath, dsName).executeMacroJob();
				if (Flag.TRUE) new EQP_MURA_TRD_MacroJob          (jobKeyPath, dsName).executeMacroJob();   // SAS
				if (Flag.TRUE) new TOTALCELLFLOW_MacroJob         (jobKeyPath, dsName).executeMacroJob();
				if (Flag.TRUE) new STEP_DESC_MacroJob             (jobKeyPath, dsName).executeMacroJob();
				if (Flag.TRUE) new CELL_MAP_MacroJob              (jobKeyPath, dsName).executeMacroJob();
				if (Flag.TRUE) new CELL_MAP_XY_MacroJob           (jobKeyPath, dsName).executeMacroJob();   // SAS
				if (Flag.TRUE) new RP_MUR_CELL_MAP_SUMM_MacroJob  (jobKeyPath, dsName).executeMacroJob();   // SAS_RP
				if (Flag.TRUE) new MURA_TREND_MacroJob            (jobKeyPath, dsName).executeMacroJob();
				if (Flag.TRUE) new MURA_N_TREND_MacroJob          (jobKeyPath, dsName).executeMacroJob();   // SAS
				if (Flag.TRUE) new TOTAL_CNT_MacroJob             (jobKeyPath, dsName).executeMacroJob();   // SAS
				if (Flag.TRUE) new MURA_CNT_MacroJob              (jobKeyPath, dsName).executeMacroJob();   // SAS
				if (Flag.TRUE) new Y_AXIS_MacroJob                (jobKeyPath, dsName).executeMacroJob();   // SAS
				if (Flag.TRUE) new RP_MUR_TREND_SUMM_MacroJob     (jobKeyPath, dsName).executeMacroJob();   // SAS_RP
				if (Flag.TRUE) new T_CNT_CELL_MacroJob            (jobKeyPath, dsName).executeMacroJob();   // SAS
				if (Flag.TRUE) new MURA_CNT_CELL_MacroJob         (jobKeyPath, dsName).executeMacroJob();   // SAS
				if (Flag.TRUE) new Y_AXIS_CELL_MacroJob           (jobKeyPath, dsName).executeMacroJob();   // SAS
				if (Flag.TRUE) new BUBBLE_INDEX_MacroJob          (jobKeyPath, dsName).executeMacroJob();
				if (Flag.TRUE) new RP_MUR_BUBBLE_MAP_SUMM_MacroJob(jobKeyPath, dsName).executeMacroJob();   // SAS_RP
				if (Flag.TRUE) new MURA_DESC_MacroJob             (jobKeyPath, dsName).executeMacroJob();
				if (Flag.TRUE) new LYA_MUR_1_MacroJob             (jobKeyPath, dsName).executeMacroJob();   // SAS
				//if (Flag.TRUE) new LYA_MUR_2_MacroJob             (jobKeyPath, dsName).executeMacroJob();   // SAS     TODO : 2014.08.19 : 해석불필요
				//if (Flag.TRUE) new RP_LYA_MUR_SUMM_MacroJob       (jobKeyPath, dsName).executeMacroJob();   // SAS_RP  TODO : 2014.08.19 : 해석불필요
				//if (Flag.TRUE) new RP_LYA_SUMM_MacroJob           (jobKeyPath, dsName).executeMacroJob();   // SAS_RP  TODO : 2014.08.19 : 해석불필요
				if (Flag.TRUE) new MURA_D_MacroJob                (jobKeyPath, dsName).executeMacroJob();
				if (Flag.TRUE) new MURA_D_01_MacroJob             (jobKeyPath, dsName).executeMacroJob();   // SAS
				if (Flag.TRUE) new MURA_SUM_MacroJob              (jobKeyPath, dsName).executeMacroJob();   // SAS
				if (Flag.TRUE) new MURA_HIST_MacroJob             (jobKeyPath, dsName).executeMacroJob();
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
			new MuraAibMain(args).runMacroJob();
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
