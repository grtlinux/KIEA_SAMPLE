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

package sdc.anal.lya.macro.A29.A29_RUN_ALL.v04;

import kiea.kr.co.tain.base.Flag;
import kiea.kr.co.tain.util.Print;
import kiea.kr.co.tain.util.SystemProperties;
import kiea.proj.sdc.anal.common.Parameter;
import kiea.proj.sdc.anal.tools.AnalCsv.v04.AnalCsvBean;
import kiea.proj.sdc.anal.tools.AnalCsv.v04.AnalCsvType;
import kiea.proj.sdc.anal.util.BasePath;
import kiea.proj.sdc.anal.util.ServiceMacroProperties;
import kiea.proj.sdc.anal.util.StrUtil;
import kiea.proj.sdc.anal.util.log.v02.Logger;
import sdc.anal.lya.macro.A01.JOBID_PARAM.v04.JOBID_PARAM_Main;
import sdc.anal.lya.macro.A21.SO_SYS_CELLID_LIST2.v04.SO_SYS_CELLID_LIST2_MacroJob;
import sdc.anal.lya.macro.A22.RP_SYS_WIP_SUMM1.v04.RP_SYS_WIP_SUMM1_MacroJob;
import sdc.anal.lya.macro.A22.RP_SYS_WIP_SUMM2.v04.RP_SYS_WIP_SUMM2_MacroJob;
import sdc.anal.lya.macro.A22.RP_SYS_WIP_SUMM3.v04.RP_SYS_WIP_SUMM3_MacroJob;
import sdc.anal.lya.macro.A22.RP_SYS_WIP_SUMM4.v04.RP_SYS_WIP_SUMM4_MacroJob;
import sdc.anal.lya.macro.A22.RP_TOTWIP_SUMM.v04.RP_TOTWIP_SUMM_MacroJob;
import sdc.anal.lya.macro.A24.EQP_FDC02_DETAILS01.v05.EQP_FDC02_DETAILS01_MacroJob;
import sdc.anal.lya.macro.A24.EQP_FDC02_SUM01.v05.EQP_FDC02_SUM01_MacroJob;
import sdc.anal.lya.macro.A25.RP_SYS_DEFECT_DETAIL.v04.RP_SYS_DEFECT_DETAIL_MacroJob;
import sdc.anal.lya.macro.A25.RP_SYS_DEFECT_SUMM.v04.RP_SYS_DEFECT_SUMM_MacroJob;
import sdc.anal.lya.macro.A26.DELAY_TIME01.v04.DELAY_TIME01_MacroJob;
import sdc.anal.lya.macro.A26.DELAY_TIME02.v04.DELAY_TIME02_MacroJob;
import sdc.anal.lya.macro.A26.DELAY_TIME03.v04.DELAY_TIME03_MacroJob;
import sdc.anal.lya.macro.A27.RP_SYS_EVENT_HIST_DETAIL.v04.RP_SYS_EVENT_HIST_DETAIL_MacroJob;
import sdc.anal.lya.macro.A27.RP_SYS_EVENT_HIST_SUMM.v04.RP_SYS_EVENT_HIST_SUMM_MacroJob;
import sdc.anal.lya.macro.A28.RP_BUBBLE_MAP_SUMM.v04.RP_BUBBLE_MAP_SUMM_MacroJob;
import sdc.anal.lya.macro.A28.RP_LYA_DETAIL.v04.RP_LYA_DETAIL_MacroJob;
import sdc.anal.lya.macro.A28.RP_LYA_SUMM.v04.RP_LYA_SUMM_MacroJob;
import sdc.anal.lya.macro.A28.RP_MAP_CHART_DETAILS.v04.RP_MAP_CHART_DETAILS_MacroJob;

/**
 * @author KangSeok
 * @date 2014. 8. 26.
 * @file_name CELLCONVERT_Main.java
 *
 */
public class A29_RUN_ALL_Main
{
	protected static String jobId = JOBID_PARAM_Main.jobId;
	protected static String dsName = "A29_" + "RUN_ALL";
	protected static ServiceMacroProperties prop = null;
	
	protected static long cntWList = 0;
	protected static long runMSec = 0;

	public A29_RUN_ALL_Main(String[] args)
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
				/*
				 * A21 PreAnal
				 */
				if (Flag.TRUE) new SO_SYS_CELLID_LIST2_MacroJob      (jobId).executeMacroJob();

				/*
				 * A28 Single
				 */
				if (Flag.TRUE) new RP_BUBBLE_MAP_SUMM_MacroJob       (jobId).executeMacroJob();
				if (Flag.TRUE) new RP_LYA_DETAIL_MacroJob            (jobId).executeMacroJob();
				if (Flag.TRUE) new RP_LYA_SUMM_MacroJob              (jobId).executeMacroJob();
				if (Flag.TRUE) new RP_MAP_CHART_DETAILS_MacroJob     (jobId).executeMacroJob();

				/*
				 * A22 WIP
				 */
				if (Flag.TRUE) new RP_TOTWIP_SUMM_MacroJob           (jobId).executeMacroJob();
				if (Flag.TRUE) new RP_SYS_WIP_SUMM1_MacroJob         (jobId).executeMacroJob();
				if (Flag.TRUE) new RP_SYS_WIP_SUMM2_MacroJob         (jobId).executeMacroJob();
				if (Flag.TRUE) new RP_SYS_WIP_SUMM3_MacroJob         (jobId).executeMacroJob();
				if (Flag.TRUE) new RP_SYS_WIP_SUMM4_MacroJob         (jobId).executeMacroJob();
				
				/*
				 * A24 ERD_FDC
				 * -> FDC02로 변경됨
				 */
//				if (Flag.TRUE) new EQP_FDC_SUM01_MacroJob            (jobId).executeMacroJob();
//				if (Flag.TRUE) new EQP_FDC_SUM02_MacroJob            (jobId).executeMacroJob();
//				if (Flag.TRUE) new EQP_FDC_SUM03_MacroJob            (jobId).executeMacroJob();
//				if (Flag.TRUE) new EQP_FDC_SUM04_MacroJob            (jobId).executeMacroJob();
//				if (Flag.TRUE) new EQP_FDC_SUM05_MacroJob            (jobId).executeMacroJob();
//				if (Flag.TRUE) new EQP_FDC_DETAILS02_MacroJob        (jobId).executeMacroJob();

				if (Flag.TRUE) new EQP_FDC02_DETAILS01_MacroJob      (jobId).executeMacroJob();
				if (Flag.TRUE) new EQP_FDC02_SUM01_MacroJob          (jobId).executeMacroJob();
				/*
				 * A25 Defect
				 */
				if (Flag.TRUE) new RP_SYS_DEFECT_DETAIL_MacroJob     (jobId).executeMacroJob();
				if (Flag.TRUE) new RP_SYS_DEFECT_SUMM_MacroJob       (jobId).executeMacroJob();
				
				/*
				 * A26 Delay Time
				 */
				if (Flag.TRUE) new DELAY_TIME01_MacroJob             (jobId).executeMacroJob();
				if (Flag.TRUE) new DELAY_TIME02_MacroJob             (jobId).executeMacroJob();
				if (Flag.TRUE) new DELAY_TIME03_MacroJob             (jobId).executeMacroJob();
			
				/*
				 * A27 Event History
				 */
				if (Flag.TRUE) new RP_SYS_EVENT_HIST_DETAIL_MacroJob (jobId).executeMacroJob();
				if (Flag.TRUE) new RP_SYS_EVENT_HIST_SUMM_MacroJob   (jobId).executeMacroJob();
			}

			if (!Flag.TRUE) {
				/*
				 * A21 PreAnal
				 */
				if (Flag.TRUE) new SO_SYS_CELLID_LIST2_MacroJob      (jobId).executeMacroJob();

				/*
				 * A28 Single
				 */
				if (Flag.TRUE) new RP_BUBBLE_MAP_SUMM_MacroJob       (jobId).executeMacroJob();
				if (Flag.TRUE) new RP_LYA_DETAIL_MacroJob            (jobId).executeMacroJob();
				if (Flag.TRUE) new RP_LYA_SUMM_MacroJob              (jobId).executeMacroJob();
				if (Flag.TRUE) new RP_MAP_CHART_DETAILS_MacroJob     (jobId).executeMacroJob();

				/*
				 * A22 WIP
				 */
				if (Flag.TRUE) new RP_TOTWIP_SUMM_MacroJob           (jobId).executeMacroJob();
				if (Flag.TRUE) new RP_SYS_WIP_SUMM1_MacroJob         (jobId).executeMacroJob();
				if (Flag.TRUE) new RP_SYS_WIP_SUMM2_MacroJob         (jobId).executeMacroJob();
				if (Flag.TRUE) new RP_SYS_WIP_SUMM3_MacroJob         (jobId).executeMacroJob();
				if (Flag.TRUE) new RP_SYS_WIP_SUMM4_MacroJob         (jobId).executeMacroJob();
				
				/*
				 * A24 ERD_FDC
				 * -> FDC02로 변경됨
				 */
//				if (Flag.TRUE) new EQP_FDC_SUM01_MacroJob            (jobId).executeMacroJob();
//				if (Flag.TRUE) new EQP_FDC_SUM02_MacroJob            (jobId).executeMacroJob();
//				if (Flag.TRUE) new EQP_FDC_SUM03_MacroJob            (jobId).executeMacroJob();
//				if (Flag.TRUE) new EQP_FDC_SUM04_MacroJob            (jobId).executeMacroJob();
//				if (Flag.TRUE) new EQP_FDC_SUM05_MacroJob            (jobId).executeMacroJob();
//				if (Flag.TRUE) new EQP_FDC_DETAILS02_MacroJob        (jobId).executeMacroJob();

				if (Flag.TRUE) new EQP_FDC02_DETAILS01_MacroJob      (jobId).executeMacroJob();
				if (Flag.TRUE) new EQP_FDC02_SUM01_MacroJob          (jobId).executeMacroJob();

				/*
				 * A25 Defect
				 */
				if (Flag.TRUE) new RP_SYS_DEFECT_DETAIL_MacroJob     (jobId).executeMacroJob();
				if (Flag.TRUE) new RP_SYS_DEFECT_SUMM_MacroJob       (jobId).executeMacroJob();
				
				/*
				 * A26 Delay Time
				 */
				if (Flag.TRUE) new DELAY_TIME01_MacroJob             (jobId).executeMacroJob();
				if (Flag.TRUE) new DELAY_TIME02_MacroJob             (jobId).executeMacroJob();
				if (Flag.TRUE) new DELAY_TIME03_MacroJob             (jobId).executeMacroJob();
			
				/*
				 * A27 Event History
				 */
				if (Flag.TRUE) new RP_SYS_EVENT_HIST_DETAIL_MacroJob (jobId).executeMacroJob();
				if (Flag.TRUE) new RP_SYS_EVENT_HIST_SUMM_MacroJob   (jobId).executeMacroJob();
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
			new A29_RUN_ALL_Main(args).runMacroJob();
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
