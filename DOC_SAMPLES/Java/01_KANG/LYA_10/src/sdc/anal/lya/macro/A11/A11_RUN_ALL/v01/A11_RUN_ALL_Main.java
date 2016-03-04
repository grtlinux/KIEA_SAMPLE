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

package sdc.anal.lya.macro.A11.A11_RUN_ALL.v01;

import kiea.kr.co.tain.base.Flag;
import kiea.kr.co.tain.util.Print;
import kiea.kr.co.tain.util.SystemProperties;
import kiea.proj.sdc.anal.common.Parameter;
import kiea.proj.sdc.anal.tools.AnalCsv.v01.AnalCsvBean;
import kiea.proj.sdc.anal.tools.AnalCsv.v01.AnalCsvType;
import kiea.proj.sdc.anal.util.BasePath;
import kiea.proj.sdc.anal.util.ServiceMacroProperties;
import kiea.proj.sdc.anal.util.StrUtil;
import kiea.proj.sdc.anal.util.log.v02.Logger;
import sdc.anal.lya.macro.A01.JOBID_PARAM.v01.JOBID_PARAM_Main;
import sdc.anal.lya.macro.A11.CELLCONVERT.v01.CELLCONVERT_MacroJob;
import sdc.anal.lya.macro.A11.CLUSTER_CONTROL.v01.CLUSTER_CONTROL_MacroJob;
import sdc.anal.lya.macro.A11.CM_WORKER.v01.CM_WORKER_MacroJob;
import sdc.anal.lya.macro.A11.DEFECT_GRP_CODE.v01.DEFECT_GRP_CODE_MacroJob;
import sdc.anal.lya.macro.A11.DEFECT_HIST.v01.DEFECT_HIST_MacroJob;
import sdc.anal.lya.macro.A11.DELAYTIME_PRC.v01.DELAYTIME_PRC_MacroJob;
import sdc.anal.lya.macro.A11.EQPUNIT.v01.EQPUNIT_MacroJob;
import sdc.anal.lya.macro.A11.EVENT_HIST.v01.EVENT_HIST_MacroJob;
import sdc.anal.lya.macro.A11.H_INSPDEFECT.v01.H_INSPDEFECT_MacroJob;
import sdc.anal.lya.macro.A11.INCHCELL_LOC.v01.INCHCELL_LOC_MacroJob;
import sdc.anal.lya.macro.A11.INSPCT_HIST.v01.INSPCT_HIST_MacroJob;
import sdc.anal.lya.macro.A11.INSPSTEPTYPE.v01.INSPSTEPTYPE_MacroJob;
import sdc.anal.lya.macro.A11.INSPSTEP_DEFECTTRACE.v01.INSPSTEP_DEFECTTRACE_MacroJob;
import sdc.anal.lya.macro.A11.MATCHING_WEIGHT.v01.MATCHING_WEIGHT_MacroJob;
import sdc.anal.lya.macro.A11.PRODUCT.v01.PRODUCT_MacroJob;
import sdc.anal.lya.macro.A11.SAME_DEFECT.v01.SAME_DEFECT_MacroJob;
import sdc.anal.lya.macro.A11.STEP_DESC.v01.STEP_DESC_MacroJob;
import sdc.anal.lya.macro.A11.STEP_PROCESS.v01.STEP_PROCESS_MacroJob;
import sdc.anal.lya.macro.A11.STEP_WEIGHT.v01.STEP_WEIGHT_MacroJob;
import sdc.anal.lya.macro.A11.TFT_INSPCT_HIST.v01.TFT_INSPCT_HIST_MacroJob;
import sdc.anal.lya.macro.A11.WIP_CELL_RATIO.v01.WIP_CELL_RATIO_MacroJob;
import sdc.anal.lya.macro.A11.WIP_EQP_SMMRY.v01.WIP_EQP_SMMRY_MacroJob;
import sdc.anal.lya.macro.A11.WIP_MINMAX_DATE.v01.WIP_MINMAX_DATE_MacroJob;

/**
 * @author KangSeok
 * @date 2014. 8. 26.
 * @file_name CELLCONVERT_Main.java
 *
 */
public class A11_RUN_ALL_Main
{
	protected static String jobId = JOBID_PARAM_Main.jobId;
	protected static String dsName = "A11_" + "RUN_ALL";
	protected static ServiceMacroProperties prop = null;
	
	protected static long cntWList = 0;
	protected static long runMSec = 0;

	public A11_RUN_ALL_Main(String[] args)
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

			/*
			 * Parameter
			 * TODO : 2014.09.16 : JOB_ID -> jobId 처리를 위한 수정
			 */
			Parameter.getInstance(jobId);
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
			/*
			 * 기존 자료 삭제
			 */
			AnalCsvBean bean = new AnalCsvBean();

			bean.setType(AnalCsvType.DELETE);
			bean.setTable("ANAL_CSV");
			bean.setWhere(String.format("JOB_ID = %s AND PROG_NM = %s", StrUtil.quote(jobId), StrUtil.quote(dsName)));

			bean.sendToOracle();

			if (Flag.TRUE) Logger.info("[%s][%s]", bean, bean.getQuery());
		}

		if (Flag.TRUE) {
			/*
			 * INSERT
			 */
			AnalCsvBean bean = new AnalCsvBean();

			bean.setType(AnalCsvType.INSERT);
			bean.setTable("ANAL_CSV");
			bean.setFields(new String[][] {
					{ "JOB_ID     ", StrUtil.quote(jobId) },
					{ "CMD_CODE   ", StrUtil.quote(dsName.substring(0, 3)) },
					{ "PROG_NM    ", StrUtil.quote(dsName) },
					{ "CSV_NM     ", StrUtil.quote(dsName + ".csv") },
					{ "MAIN_CLASS ", StrUtil.quote(this.getClass().getName()) },
					{ "S_TIME     ", "SYSDATE" },
					{ "CSV_STATUS ", StrUtil.quote("START") },
			});
			if (Flag.TRUE) Logger.info("[%s][%s]", bean, bean.getQuery());

			bean.sendToOracle();
		}

		if (Flag.TRUE) {
			long time1, time2;
			time1 = System.nanoTime();

			if (Flag.TRUE) {
				if (Flag.TRUE) new CELLCONVERT_MacroJob          (jobId).executeMacroJob();
				if (Flag.TRUE) new CLUSTER_CONTROL_MacroJob      (jobId).executeMacroJob();
				if (Flag.TRUE) new CM_WORKER_MacroJob            (jobId).executeMacroJob();
				if (Flag.TRUE) new DEFECT_GRP_CODE_MacroJob      (jobId).executeMacroJob();
				if (Flag.TRUE) new DEFECT_HIST_MacroJob          (jobId).executeMacroJob();
				if (Flag.TRUE) new DELAYTIME_PRC_MacroJob        (jobId).executeMacroJob();
				if (Flag.TRUE) new EQPUNIT_MacroJob              (jobId).executeMacroJob();
				if (Flag.TRUE) new EVENT_HIST_MacroJob           (jobId).executeMacroJob();
				if (Flag.TRUE) new H_INSPDEFECT_MacroJob         (jobId).executeMacroJob();
				if (Flag.TRUE) new INCHCELL_LOC_MacroJob         (jobId).executeMacroJob();
				if (Flag.TRUE) new INSPCT_HIST_MacroJob          (jobId).executeMacroJob();
				if (Flag.TRUE) new INSPSTEP_DEFECTTRACE_MacroJob (jobId).executeMacroJob();
				if (Flag.TRUE) new INSPSTEPTYPE_MacroJob         (jobId).executeMacroJob();
				if (Flag.TRUE) new MATCHING_WEIGHT_MacroJob      (jobId).executeMacroJob();
				if (Flag.TRUE) new PRODUCT_MacroJob              (jobId).executeMacroJob();
				if (Flag.TRUE) new SAME_DEFECT_MacroJob          (jobId).executeMacroJob();
				if (Flag.TRUE) new STEP_DESC_MacroJob            (jobId).executeMacroJob();
				if (Flag.TRUE) new STEP_PROCESS_MacroJob         (jobId).executeMacroJob();
				if (Flag.TRUE) new STEP_WEIGHT_MacroJob          (jobId).executeMacroJob();
				if (Flag.TRUE) new TFT_INSPCT_HIST_MacroJob      (jobId).executeMacroJob();
				if (Flag.TRUE) new WIP_CELL_RATIO_MacroJob       (jobId).executeMacroJob();
				if (Flag.TRUE) new WIP_EQP_SMMRY_MacroJob        (jobId).executeMacroJob();
				if (Flag.TRUE) new WIP_MINMAX_DATE_MacroJob      (jobId).executeMacroJob();
			}

			time2 = System.nanoTime();
			
			runMSec = (time2 - time1)/1000/1000;
			if (Flag.TRUE) Logger.info("##### took time : %d ms", runMSec);
		}
		
		if (Flag.TRUE) {
			/*
			 * UPDATE
			 */
			AnalCsvBean bean = new AnalCsvBean();
			
			bean.setType(AnalCsvType.UPDATE);
			bean.setTable("ANAL_CSV");
			bean.setFields(new String[][] {
					{ "LIST_CNT   ", "" + cntWList },
					{ "E_TIME     ", "SYSDATE" },
					{ "R_MSEC     ", "" + runMSec },
					{ "CSV_STATUS ", StrUtil.quote("OK") },   // OK, ERROR, SKIP, ETC...
					{ "LOG_MESSAGE", StrUtil.quote("COMPLETE") },  // COMPLETE, EXCEPTION
			});
			bean.setWhere(String.format("JOB_ID = %s AND PROG_NM = %s", StrUtil.quote(jobId), StrUtil.quote(dsName)));
			
			if (Flag.TRUE) Logger.info("[%s][%s]", bean, bean.getQuery());
			
			bean.sendToOracle();
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
			new A11_RUN_ALL_Main(args).runMacroJob();
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
