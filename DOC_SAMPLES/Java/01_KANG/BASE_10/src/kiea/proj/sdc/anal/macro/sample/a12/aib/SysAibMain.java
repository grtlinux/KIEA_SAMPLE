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

package kiea.proj.sdc.anal.macro.sample.a12.aib;

import kiea.kr.co.tain.base.Flag;
import kiea.proj.sdc.anal.common.Parameter;
import kiea.proj.sdc.anal.macro.sample.a12.aib.macro.AIB_CELLCONVERT.v01.AIB_CELLCONVERT_MacroJob;
import kiea.proj.sdc.anal.macro.sample.a12.aib.macro.AIB_CLUSTER_CONTROL.v01.AIB_CLUSTER_CONTROL_MacroJob;
import kiea.proj.sdc.anal.macro.sample.a12.aib.macro.AIB_CM_WORKER.v01.AIB_CM_WORKER_MacroJob;
import kiea.proj.sdc.anal.macro.sample.a12.aib.macro.AIB_DCOL_BASE_H.v01.AIB_DCOL_BASE_H_MacroJob;
import kiea.proj.sdc.anal.macro.sample.a12.aib.macro.AIB_DCOL_HIST.v01.AIB_DCOL_HIST_MacroJob;
import kiea.proj.sdc.anal.macro.sample.a12.aib.macro.AIB_DCOL_WITH_INSP.v01.AIB_DCOL_WITH_INSP_MacroJob;
import kiea.proj.sdc.anal.macro.sample.a12.aib.macro.AIB_DEFECT_GRP_CODE.v01.AIB_DEFECT_GRP_CODE_MacroJob;
import kiea.proj.sdc.anal.macro.sample.a12.aib.macro.AIB_DEFECT_HIST.v01.AIB_DEFECT_HIST_MacroJob;
import kiea.proj.sdc.anal.macro.sample.a12.aib.macro.AIB_DELAYTIME_PRC.v01.AIB_DELAYTIME_PRC_MacroJob;
import kiea.proj.sdc.anal.macro.sample.a12.aib.macro.AIB_EQPUNIT.v01.AIB_EQPUNIT_MacroJob;
import kiea.proj.sdc.anal.macro.sample.a12.aib.macro.AIB_EVENT_HIST.v01.AIB_EVENT_HIST_MacroJob;
import kiea.proj.sdc.anal.macro.sample.a12.aib.macro.AIB_H_INSPDEFECT.v01.AIB_H_INSPDEFECT_MacroJob;
import kiea.proj.sdc.anal.macro.sample.a12.aib.macro.AIB_INCHCELL_LOC.v01.AIB_INCHCELL_LOC_MacroJob;
import kiea.proj.sdc.anal.macro.sample.a12.aib.macro.AIB_INSPCT_HIST.v01.AIB_INSPCT_HIST_MacroJob;
import kiea.proj.sdc.anal.macro.sample.a12.aib.macro.AIB_INSPSTEPTYPE.v01.AIB_INSPSTEPTYPE_MacroJob;
import kiea.proj.sdc.anal.macro.sample.a12.aib.macro.AIB_INSPSTEP_DEFECTTRACE.v01.AIB_INSPSTEP_DEFECTTRACE_MacroJob;
import kiea.proj.sdc.anal.macro.sample.a12.aib.macro.AIB_ITEM_LIST.v01.AIB_ITEM_LIST_MacroJob;
import kiea.proj.sdc.anal.macro.sample.a12.aib.macro.AIB_MATCHING_WEIGHT.v01.AIB_MATCHING_WEIGHT_MacroJob;
import kiea.proj.sdc.anal.macro.sample.a12.aib.macro.AIB_METRO_BASE_H.v01.AIB_METRO_BASE_H_MacroJob;
import kiea.proj.sdc.anal.macro.sample.a12.aib.macro.AIB_METRO_HIST.v01.AIB_METRO_HIST_MacroJob;
import kiea.proj.sdc.anal.macro.sample.a12.aib.macro.AIB_METRO_S_H.v01.AIB_METRO_S_H_MacroJob;
import kiea.proj.sdc.anal.macro.sample.a12.aib.macro.AIB_METRO_WITH_INSP.v01.AIB_METRO_WITH_INSP_MacroJob;
import kiea.proj.sdc.anal.macro.sample.a12.aib.macro.AIB_MOD_BIN.v01.AIB_MOD_BIN_MacroJob;
import kiea.proj.sdc.anal.macro.sample.a12.aib.macro.AIB_MOD_RJ.v01.AIB_MOD_RJ_MacroJob;
import kiea.proj.sdc.anal.macro.sample.a12.aib.macro.AIB_MOD_WORKID.v01.AIB_MOD_WORKID_MacroJob;
import kiea.proj.sdc.anal.macro.sample.a12.aib.macro.AIB_PRODUCT.v01.AIB_PRODUCT_MacroJob;
import kiea.proj.sdc.anal.macro.sample.a12.aib.macro.AIB_SAME_DEFECT.v01.AIB_SAME_DEFECT_MacroJob;
import kiea.proj.sdc.anal.macro.sample.a12.aib.macro.AIB_STEP_DESC.v01.AIB_STEP_DESC_MacroJob;
import kiea.proj.sdc.anal.macro.sample.a12.aib.macro.AIB_STEP_PROCESS.v01.AIB_STEP_PROCESS_MacroJob;
import kiea.proj.sdc.anal.macro.sample.a12.aib.macro.AIB_STEP_WEIGHT.v01.AIB_STEP_WEIGHT_MacroJob;
import kiea.proj.sdc.anal.macro.sample.a12.aib.macro.AIB_TFT_INSPCT_HIST.v01.AIB_TFT_INSPCT_HIST_MacroJob;
import kiea.proj.sdc.anal.macro.sample.a12.aib.macro.AIB_WIP_CELL_RATIO.v01.AIB_WIP_CELL_RATIO_MacroJob;
import kiea.proj.sdc.anal.macro.sample.a12.aib.macro.AIB_WIP_EQP_SMMRY.v01.AIB_WIP_EQP_SMMRY_MacroJob;
import kiea.proj.sdc.anal.macro.sample.a12.aib.macro.AIB_WIP_MINMAX_DATE.v01.AIB_WIP_MINMAX_DATE_MacroJob;
import kiea.proj.sdc.anal.macro.sample.a12.aib.macro.AIB_WIP_PROC_RATIO.v01.AIB_WIP_PROC_RATIO_MacroJob;
import kiea.proj.sdc.anal.macro.sample.a12.aib.macro.AIB_WIP_SLOT_SMMRY.v01.AIB_WIP_SLOT_SMMRY_MacroJob;
import kiea.proj.sdc.anal.macro.sample.a12.aib.macro.AIB_WIP_UNIT_SMMRY.v01.AIB_WIP_UNIT_SMMRY_MacroJob;
import kiea.proj.sdc.anal.util.BasePath;
import kiea.proj.sdc.anal.util.log.v02.Logger;

/**
 * @author KangSeok
 * @date 2014. 8. 26.
 * @file_name MuraWipMain.java
 *
 */
public class SysAibMain
{
	private String jobKeyPath = "KEY_HHMM";   // JOBID, JOBKEY
	private String dsName = "SAMPLE03";
	
	public SysAibMain(String[] args)
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

			Parameter.getInstance(5);
			if (Flag.TRUE) {
				if (Flag.TRUE) new AIB_CELLCONVERT_MacroJob           (jobKeyPath).executeMacroJob();
				if (Flag.TRUE) new AIB_CLUSTER_CONTROL_MacroJob       (jobKeyPath).executeMacroJob();
				if (Flag.TRUE) new AIB_CM_WORKER_MacroJob             (jobKeyPath).executeMacroJob();
				if (Flag.TRUE) new AIB_DCOL_BASE_H_MacroJob           (jobKeyPath).executeMacroJob();
				if (Flag.TRUE) new AIB_DCOL_WITH_INSP_MacroJob        (jobKeyPath).executeMacroJob();
				if (Flag.TRUE) new AIB_DCOL_HIST_MacroJob             (jobKeyPath).executeMacroJob();
				if (Flag.TRUE) new AIB_DEFECT_GRP_CODE_MacroJob       (jobKeyPath).executeMacroJob();
				if (Flag.TRUE) new AIB_DEFECT_HIST_MacroJob           (jobKeyPath).executeMacroJob();
				if (Flag.TRUE) new AIB_DELAYTIME_PRC_MacroJob         (jobKeyPath).executeMacroJob();
				if (Flag.TRUE) new AIB_EQPUNIT_MacroJob               (jobKeyPath).executeMacroJob();
				if (Flag.TRUE) new AIB_EVENT_HIST_MacroJob            (jobKeyPath).executeMacroJob();
				if (Flag.TRUE) new AIB_H_INSPDEFECT_MacroJob          (jobKeyPath).executeMacroJob();
				if (Flag.TRUE) new AIB_INCHCELL_LOC_MacroJob          (jobKeyPath).executeMacroJob();
				if (Flag.TRUE) new AIB_INSPCT_HIST_MacroJob           (jobKeyPath).executeMacroJob();
				if (Flag.TRUE) new AIB_INSPSTEP_DEFECTTRACE_MacroJob  (jobKeyPath).executeMacroJob();
				if (Flag.TRUE) new AIB_INSPSTEPTYPE_MacroJob          (jobKeyPath).executeMacroJob();
				if (Flag.TRUE) new AIB_ITEM_LIST_MacroJob             (jobKeyPath).executeMacroJob();
				if (Flag.TRUE) new AIB_MATCHING_WEIGHT_MacroJob       (jobKeyPath).executeMacroJob();
				if (Flag.TRUE) new AIB_METRO_BASE_H_MacroJob          (jobKeyPath).executeMacroJob();
				if (Flag.TRUE) new AIB_METRO_WITH_INSP_MacroJob       (jobKeyPath).executeMacroJob();
				if (Flag.TRUE) new AIB_METRO_HIST_MacroJob            (jobKeyPath).executeMacroJob();
				if (Flag.TRUE) new AIB_METRO_S_H_MacroJob             (jobKeyPath).executeMacroJob();
				if (Flag.TRUE) new AIB_MOD_BIN_MacroJob               (jobKeyPath).executeMacroJob();
				if (Flag.TRUE) new AIB_MOD_RJ_MacroJob                (jobKeyPath).executeMacroJob();
				if (Flag.TRUE) new AIB_MOD_WORKID_MacroJob            (jobKeyPath).executeMacroJob();
				if (Flag.TRUE) new AIB_PRODUCT_MacroJob               (jobKeyPath).executeMacroJob();
				if (Flag.TRUE) new AIB_SAME_DEFECT_MacroJob           (jobKeyPath).executeMacroJob();
				if (Flag.TRUE) new AIB_STEP_DESC_MacroJob             (jobKeyPath).executeMacroJob();
				if (Flag.TRUE) new AIB_STEP_PROCESS_MacroJob          (jobKeyPath).executeMacroJob();
				if (Flag.TRUE) new AIB_STEP_WEIGHT_MacroJob           (jobKeyPath).executeMacroJob();
				if (Flag.TRUE) new AIB_TFT_INSPCT_HIST_MacroJob       (jobKeyPath).executeMacroJob();
				if (Flag.TRUE) new AIB_WIP_CELL_RATIO_MacroJob        (jobKeyPath).executeMacroJob();
				if (Flag.TRUE) new AIB_WIP_EQP_SMMRY_MacroJob         (jobKeyPath).executeMacroJob();
				if (Flag.TRUE) new AIB_WIP_MINMAX_DATE_MacroJob       (jobKeyPath).executeMacroJob();
				if (Flag.TRUE) new AIB_WIP_PROC_RATIO_MacroJob        (jobKeyPath).executeMacroJob();
				if (Flag.TRUE) new AIB_WIP_SLOT_SMMRY_MacroJob        (jobKeyPath).executeMacroJob();
				if (Flag.TRUE) new AIB_WIP_UNIT_SMMRY_MacroJob        (jobKeyPath).executeMacroJob();
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
			new SysAibMain(args).runMacroJob();
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
