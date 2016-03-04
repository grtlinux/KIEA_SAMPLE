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

package sdc.anal.lya.macro.A23.A23_RUN_ALL.v04;

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
import sdc.anal.lya.macro.A23.MOD_BIN.v04.MOD_BIN_MacroJob;
import sdc.anal.lya.macro.A23.MOD_EQP_SMMRY.v04.MOD_EQP_SMMRY_MacroJob;
import sdc.anal.lya.macro.A23.MOD_PKBANK.v04.MOD_PKBANK_MacroJob;
import sdc.anal.lya.macro.A23.MOD_RJ.v04.MOD_RJ_MacroJob;
import sdc.anal.lya.macro.A23.MOD_WORKID.v04.MOD_WORKID_MacroJob;
import sdc.anal.lya.macro.A23.RP_SYS_MOD_DETAILS_INSPWIP.v04.RP_SYS_MOD_DETAILS_INSPWIP_MacroJob;
import sdc.anal.lya.macro.A23.RP_SYS_MOD_PKBANK_SUMM.v04.RP_SYS_MOD_PKBANK_SUMM_MacroJob;
import sdc.anal.lya.macro.A23.RP_SYS_MOD_SUMM_BIN.v04.RP_SYS_MOD_SUMM_BIN_MacroJob;
import sdc.anal.lya.macro.A23.RP_SYS_MOD_SUMM_INSPWIP.v04.RP_SYS_MOD_SUMM_INSPWIP_MacroJob;
import sdc.anal.lya.macro.A23.RP_SYS_MOD_SUMM_RJ.v04.RP_SYS_MOD_SUMM_RJ_MacroJob;
import sdc.anal.lya.macro.A23.RP_SYS_MOD_SUMM_WORKERID.v04.RP_SYS_MOD_SUMM_WORKERID_MacroJob;
import sdc.anal.lya.macro.A23.SI_SYS_MOD_EQP_01.v04.SI_SYS_MOD_EQP_01_MacroJob;
import sdc.anal.lya.macro.A23.SI_SYS_MOD_EQP_02.v04.SI_SYS_MOD_EQP_02_MacroJob;
import sdc.anal.lya.macro.A23.SI_SYS_MOD_EQP_03.v04.SI_SYS_MOD_EQP_03_MacroJob;
import sdc.anal.lya.macro.A23.SI_SYS_MOD_EQP_04.v04.SI_SYS_MOD_EQP_04_MacroJob;
import sdc.anal.lya.macro.A23.SI_SYS_MOD_EQP_05.v04.SI_SYS_MOD_EQP_05_MacroJob;
import sdc.anal.lya.macro.A23.SI_SYS_MOD_EQP_06.v04.SI_SYS_MOD_EQP_06_MacroJob;
import sdc.anal.lya.macro.A23.SI_SYS_MOD_EQP_07.v04.SI_SYS_MOD_EQP_07_MacroJob;
import sdc.anal.lya.macro.A23.SO_SYS_MOD_BIN_ANL.v04.SO_SYS_MOD_BIN_ANL_MacroJob;
import sdc.anal.lya.macro.A23.SO_SYS_MOD_BIN_CP.v04.SO_SYS_MOD_BIN_CP_MacroJob;
import sdc.anal.lya.macro.A23.SO_SYS_MOD_BIN_CSV.v04.SO_SYS_MOD_BIN_CSV_MacroJob;
import sdc.anal.lya.macro.A23.SO_SYS_MOD_PKBANK.v04.SO_SYS_MOD_PKBANK_MacroJob;
import sdc.anal.lya.macro.A23.SO_SYS_MOD_PKBANK_JASU.v04.SO_SYS_MOD_PKBANK_JASU_MacroJob;
import sdc.anal.lya.macro.A23.SO_SYS_MOD_PKBANK_MOSU.v04.SO_SYS_MOD_PKBANK_MOSU_MacroJob;
import sdc.anal.lya.macro.A23.SO_SYS_MOD_RJ_ANL.v04.SO_SYS_MOD_RJ_ANL_MacroJob;
import sdc.anal.lya.macro.A23.SO_SYS_MOD_RJ_CP.v04.SO_SYS_MOD_RJ_CP_MacroJob;
import sdc.anal.lya.macro.A23.SO_SYS_MOD_RJ_CSV.v04.SO_SYS_MOD_RJ_CSV_MacroJob;
import sdc.anal.lya.macro.A23.SO_SYS_MOD_WORKID_ANL.v04.SO_SYS_MOD_WORKID_ANL_MacroJob;
import sdc.anal.lya.macro.A23.SO_SYS_MOD_WORKID_CP.v04.SO_SYS_MOD_WORKID_CP_MacroJob;
import sdc.anal.lya.macro.A23.SO_SYS_MOD_WORKID_CSV.v04.SO_SYS_MOD_WORKID_CSV_MacroJob;

/**
 * @author KangSeok
 * @date 2014. 8. 26.
 * @file_name CELLCONVERT_Main.java
 *
 */
public class A23_RUN_ALL_Main
{
	protected static String jobId = JOBID_PARAM_Main.jobId;
	protected static String dsName = "A23_" + "RUN_ALL";
	protected static ServiceMacroProperties prop = null;
	
	protected static long cntWList = 0;
	protected static long runMSec = 0;

	public A23_RUN_ALL_Main(String[] args)
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
				if (Flag.TRUE) new MOD_BIN_MacroJob                    (jobId).executeMacroJob();
				if (Flag.TRUE) new MOD_RJ_MacroJob                     (jobId).executeMacroJob();
				if (Flag.TRUE) new MOD_WORKID_MacroJob                 (jobId).executeMacroJob();
				if (Flag.TRUE) new SO_SYS_MOD_BIN_CP_MacroJob          (jobId).executeMacroJob();
				if (Flag.TRUE) new SO_SYS_MOD_WORKID_CP_MacroJob       (jobId).executeMacroJob();
				if (Flag.TRUE) new SO_SYS_MOD_RJ_CP_MacroJob           (jobId).executeMacroJob();
				if (Flag.TRUE) new MOD_EQP_SMMRY_MacroJob              (jobId).executeMacroJob();
				if (Flag.TRUE) new MOD_PKBANK_MacroJob                 (jobId).executeMacroJob();
				if (Flag.TRUE) new SO_SYS_MOD_BIN_ANL_MacroJob         (jobId).executeMacroJob();
				if (Flag.TRUE) new SO_SYS_MOD_BIN_CSV_MacroJob         (jobId).executeMacroJob();
				if (Flag.TRUE) new SO_SYS_MOD_WORKID_ANL_MacroJob      (jobId).executeMacroJob();
				if (Flag.TRUE) new SO_SYS_MOD_WORKID_CSV_MacroJob      (jobId).executeMacroJob();
				if (Flag.TRUE) new SO_SYS_MOD_RJ_ANL_MacroJob          (jobId).executeMacroJob();
				if (Flag.TRUE) new SO_SYS_MOD_RJ_CSV_MacroJob          (jobId).executeMacroJob();
				if (Flag.TRUE) new SI_SYS_MOD_EQP_01_MacroJob          (jobId).executeMacroJob();
				if (Flag.TRUE) new SI_SYS_MOD_EQP_02_MacroJob          (jobId).executeMacroJob();
				if (Flag.TRUE) new SI_SYS_MOD_EQP_03_MacroJob          (jobId).executeMacroJob();
				if (Flag.TRUE) new SI_SYS_MOD_EQP_04_MacroJob          (jobId).executeMacroJob();
				if (Flag.TRUE) new SI_SYS_MOD_EQP_05_MacroJob          (jobId).executeMacroJob();
				if (Flag.TRUE) new SI_SYS_MOD_EQP_06_MacroJob          (jobId).executeMacroJob();
				if (Flag.TRUE) new SI_SYS_MOD_EQP_07_MacroJob          (jobId).executeMacroJob();
				if (Flag.TRUE) new SO_SYS_MOD_PKBANK_MOSU_MacroJob     (jobId).executeMacroJob();
				if (Flag.TRUE) new SO_SYS_MOD_PKBANK_JASU_MacroJob     (jobId).executeMacroJob();
				if (Flag.TRUE) new SO_SYS_MOD_PKBANK_MacroJob          (jobId).executeMacroJob();
				if (Flag.TRUE) new RP_SYS_MOD_SUMM_BIN_MacroJob        (jobId).executeMacroJob();
				if (Flag.TRUE) new RP_SYS_MOD_SUMM_WORKERID_MacroJob   (jobId).executeMacroJob();
				if (Flag.TRUE) new RP_SYS_MOD_SUMM_RJ_MacroJob         (jobId).executeMacroJob();
				if (Flag.TRUE) new RP_SYS_MOD_SUMM_INSPWIP_MacroJob    (jobId).executeMacroJob();
				if (Flag.TRUE) new RP_SYS_MOD_DETAILS_INSPWIP_MacroJob (jobId).executeMacroJob();
				if (Flag.TRUE) new RP_SYS_MOD_PKBANK_SUMM_MacroJob     (jobId).executeMacroJob();
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
			new A23_RUN_ALL_Main(args).runMacroJob();
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
