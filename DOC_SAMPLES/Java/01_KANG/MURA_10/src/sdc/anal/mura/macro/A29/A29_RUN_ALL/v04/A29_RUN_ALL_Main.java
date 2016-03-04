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

package sdc.anal.mura.macro.A29.A29_RUN_ALL.v04;

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
import sdc.anal.mura.macro.A01.JOBID_PARAM.v04.JOBID_PARAM_Main;
import sdc.anal.mura.macro.A11.BUBBLE_INDEX.v04.BUBBLE_INDEX_MacroJob;
import sdc.anal.mura.macro.A11.CELL_MAP.v04.CELL_MAP_MacroJob;
import sdc.anal.mura.macro.A11.MURA_D.v04.MURA_D_MacroJob;
import sdc.anal.mura.macro.A11.MURA_DESC.v04.MURA_DESC_MacroJob;
import sdc.anal.mura.macro.A11.MURA_HIST.v04.MURA_HIST_MacroJob;
import sdc.anal.mura.macro.A11.MURA_SPC.v04.MURA_SPC_MacroJob;
import sdc.anal.mura.macro.A11.MURA_TREND.v04.MURA_TREND_MacroJob;
import sdc.anal.mura.macro.A11.STEP_DESC.v04.STEP_DESC_MacroJob;
import sdc.anal.mura.macro.A11.TOTALCELLFLOW.v04.TOTALCELLFLOW_MacroJob;
import sdc.anal.mura.macro.A11.WIP_EQP_SMMRY.v04.WIP_EQP_SMMRY_MacroJob;
import sdc.anal.mura.macro.A12.CELL_MAP_XY.v04.CELL_MAP_XY_MacroJob;
import sdc.anal.mura.macro.A12.EQP_MURA_TRD.v04.EQP_MURA_TRD_MacroJob;
import sdc.anal.mura.macro.A12.LYA_MUR_1.v04.LYA_MUR_1_MacroJob;
import sdc.anal.mura.macro.A12.MURA_CNT.v04.MURA_CNT_MacroJob;
import sdc.anal.mura.macro.A12.MURA_CNT_CELL.v04.MURA_CNT_CELL_MacroJob;
import sdc.anal.mura.macro.A12.MURA_D_01.v04.MURA_D_01_MacroJob;
import sdc.anal.mura.macro.A12.MURA_N_TREND.v04.MURA_N_TREND_MacroJob;
import sdc.anal.mura.macro.A12.MURA_SUM.v04.MURA_SUM_MacroJob;
import sdc.anal.mura.macro.A12.RP_MUR_TREND_SUMM.v04.RP_MUR_TREND_SUMM_MacroJob;
import sdc.anal.mura.macro.A12.TOTAL_CNT.v04.TOTAL_CNT_MacroJob;
import sdc.anal.mura.macro.A12.T_CNT_CELL.v04.T_CNT_CELL_MacroJob;
import sdc.anal.mura.macro.A12.Y_AXIS.v04.Y_AXIS_MacroJob;
import sdc.anal.mura.macro.A12.Y_AXIS_CELL.v04.Y_AXIS_CELL_MacroJob;
import sdc.anal.mura.macro.A21.BAD_GLASS.v04.BAD_GLASS_MacroJob;
import sdc.anal.mura.macro.A21.BAD_GLASS_NO.v04.BAD_GLASS_NO_MacroJob;
import sdc.anal.mura.macro.A21.BAD_GLASS_ORG.v04.BAD_GLASS_ORG_MacroJob;
import sdc.anal.mura.macro.A21.CLUSTER_OUTPUT.v04.CLUSTER_OUTPUT_MacroJob;
import sdc.anal.mura.macro.A21.FINAL_CLUSTER.v04.FINAL_CLUSTER_MacroJob;
import sdc.anal.mura.macro.A21.GOOD_GLASS.v04.GOOD_GLASS_MacroJob;
import sdc.anal.mura.macro.A21.GOOD_GLASS_ORG.v04.GOOD_GLASS_ORG_MacroJob;
import sdc.anal.mura.macro.A21.IN_DBSCAN.v04.IN_DBSCAN_MacroJob;
import sdc.anal.mura.macro.A21.MURA_RAWDATA.v04.MURA_RAWDATA_MacroJob;
import sdc.anal.mura.macro.A21.MURA_RAWDATA_ANAL2.v04.MURA_RAWDATA_ANAL2_MacroJob;
import sdc.anal.mura.macro.A21.OUT_DBSCAN.v04.OUT_DBSCAN_MacroJob;
import sdc.anal.mura.macro.A21.REGROUP_CLUSTER.v04.REGROUP_CLUSTER_MacroJob;
import sdc.anal.mura.macro.A21.SO_SYS_GLASS_LIST2.v04.SO_SYS_GLASS_LIST2_MacroJob;
import sdc.anal.mura.macro.A22.AVG_MURA_MAP_XY_4SF_RESULT.v04.AVG_MURA_MAP_XY_4SF_RESULT_MacroJob;
import sdc.anal.mura.macro.A22.BUBBLE_CLUSTER_CELLLOC.v04.BUBBLE_CLUSTER_CELLLOC_MacroJob;
import sdc.anal.mura.macro.A22.BUBBLE_IDX.v04.BUBBLE_IDX_MacroJob;
import sdc.anal.mura.macro.A22.CONTACT_MAP.v04.CONTACT_MAP_MacroJob;
import sdc.anal.mura.macro.A22.CONTACT_MAP_XY_DIST_RESULT.v04.CONTACT_MAP_XY_DIST_RESULT_MacroJob;
import sdc.anal.mura.macro.A22.CONTACT_MAP_XY_RESULT.v04.CONTACT_MAP_XY_RESULT_MacroJob;
import sdc.anal.mura.macro.A22.CONTACT_MURA_DIST_RESULT.v04.CONTACT_MURA_DIST_RESULT_MacroJob;
import sdc.anal.mura.macro.A22.CONTACT_MURA_XY_RESULT.v04.CONTACT_MURA_XY_RESULT_MacroJob;
import sdc.anal.mura.macro.A22.CONTACT_MUR_DIST_4SF_RESULT.v04.CONTACT_MUR_DIST_4SF_RESULT_MacroJob;
import sdc.anal.mura.macro.A22.MURA_IMAGE.v04.MURA_IMAGE_MacroJob;
import sdc.anal.mura.macro.A22.MURA_MAP_XY_4SF_RESULT.v04.MURA_MAP_XY_4SF_RESULT_MacroJob;
import sdc.anal.mura.macro.A22.MURA_MAP_XY_RESULT.v04.MURA_MAP_XY_RESULT_MacroJob;
import sdc.anal.mura.macro.A22.OUT_DBSCAN_RESULT.v04.OUT_DBSCAN_RESULT_MacroJob;
import sdc.anal.mura.macro.A22.OUT_DBSCAN_RESULT1.v04.OUT_DBSCAN_RESULT1_MacroJob;
import sdc.anal.mura.macro.A22.RP_MUR_AVGMUR_MAP_SUMM.v04.RP_MUR_AVGMUR_MAP_SUMM_MacroJob;
import sdc.anal.mura.macro.A22.RP_MUR_BUBBLE_MAP_SP_SUMM.v04.RP_MUR_BUBBLE_MAP_SP_SUMM_MacroJob;
import sdc.anal.mura.macro.A22.RP_MUR_CONTACT_MAP_SUMM.v04.RP_MUR_CONTACT_MAP_SUMM_MacroJob;
import sdc.anal.mura.macro.A22.RP_MUR_TOTMURA_MAP_SUMM.v04.RP_MUR_TOTMURA_MAP_SUMM_MacroJob;
import sdc.anal.mura.macro.A23.RP_MUR_WIP_SUMM1.v04.RP_MUR_WIP_SUMM1_MacroJob;
import sdc.anal.mura.macro.A23.RP_MUR_WIP_SUMM2.v04.RP_MUR_WIP_SUMM2_MacroJob;
import sdc.anal.mura.macro.A23.RP_MUR_WIP_SUMM3.v04.RP_MUR_WIP_SUMM3_MacroJob;
import sdc.anal.mura.macro.A23.RP_MUR_WIP_SUMM4.v04.RP_MUR_WIP_SUMM4_MacroJob;
import sdc.anal.mura.macro.A24.RP_MUR_EQP_DETAILS1.v04.RP_MUR_EQP_DETAILS1_MacroJob;
import sdc.anal.mura.macro.A24.RP_MUR_EQP_DETAILS2.v04.RP_MUR_EQP_DETAILS2_MacroJob;
import sdc.anal.mura.macro.A24.RP_MUR_EQP_SUMM.v04.RP_MUR_EQP_SUMM_MacroJob;
import sdc.anal.mura.macro.A25.RP_MUR_ERD_FDC_SUM01.v04.RP_MUR_ERD_FDC_SUM01_MacroJob;
import sdc.anal.mura.macro.A25.RP_MUR_ERD_FDC_SUM02.v04.RP_MUR_ERD_FDC_SUM02_MacroJob;
import sdc.anal.mura.macro.A25.RP_MUR_ERD_FDC_SUM03.v04.RP_MUR_ERD_FDC_SUM03_MacroJob;
import sdc.anal.mura.macro.A25.RP_MUR_ERD_FDC_SUM04.v04.RP_MUR_ERD_FDC_SUM04_MacroJob;
import sdc.anal.mura.macro.A25.RP_MUR_ERD_FDC_SUM05.v04.RP_MUR_ERD_FDC_SUM05_MacroJob;
import sdc.anal.mura.macro.A26.RP_MUR_BUBBLE_MAP_SUMM.v04.RP_MUR_BUBBLE_MAP_SUMM_MacroJob;
import sdc.anal.mura.macro.A26.RP_MUR_CELL_MAP_SUMM.v04.RP_MUR_CELL_MAP_SUMM_MacroJob;
import sdc.anal.mura.macro.A26.RP_MUR_SUMM.v04.RP_LYA_SUMM_MacroJob;

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
				 * A11
				 */
				if (Flag.TRUE) new BUBBLE_INDEX_MacroJob                (jobId).executeMacroJob();
				if (Flag.TRUE) new CELL_MAP_MacroJob                    (jobId).executeMacroJob();
				if (Flag.TRUE) new MURA_D_MacroJob                      (jobId).executeMacroJob();
				if (Flag.TRUE) new MURA_DESC_MacroJob                   (jobId).executeMacroJob();
				if (Flag.TRUE) new MURA_HIST_MacroJob                   (jobId).executeMacroJob();
				if (Flag.TRUE) new MURA_SPC_MacroJob                    (jobId).executeMacroJob();
				if (Flag.TRUE) new MURA_TREND_MacroJob                  (jobId).executeMacroJob();
				if (Flag.TRUE) new STEP_DESC_MacroJob                   (jobId).executeMacroJob();
				if (Flag.TRUE) new TOTALCELLFLOW_MacroJob               (jobId).executeMacroJob();
				if (Flag.TRUE) new WIP_EQP_SMMRY_MacroJob               (jobId).executeMacroJob();

				/*
				 * A12
				 */
				if (Flag.TRUE) new CELL_MAP_XY_MacroJob                 (jobId).executeMacroJob();
				if (Flag.TRUE) new EQP_MURA_TRD_MacroJob                (jobId).executeMacroJob();
				if (Flag.TRUE) new LYA_MUR_1_MacroJob                   (jobId).executeMacroJob();
				if (Flag.TRUE) new MURA_N_TREND_MacroJob                (jobId).executeMacroJob();
				if (Flag.TRUE) new MURA_CNT_MacroJob                    (jobId).executeMacroJob();
				if (Flag.TRUE) new MURA_CNT_CELL_MacroJob               (jobId).executeMacroJob();
				if (Flag.TRUE) new MURA_D_01_MacroJob                   (jobId).executeMacroJob();
				if (Flag.TRUE) new MURA_SUM_MacroJob                    (jobId).executeMacroJob();
				if (Flag.TRUE) new T_CNT_CELL_MacroJob                  (jobId).executeMacroJob();
				if (Flag.TRUE) new TOTAL_CNT_MacroJob                   (jobId).executeMacroJob();
				if (Flag.TRUE) new Y_AXIS_MacroJob                      (jobId).executeMacroJob();
				if (Flag.TRUE) new Y_AXIS_CELL_MacroJob                 (jobId).executeMacroJob();
				if (Flag.TRUE) new RP_MUR_TREND_SUMM_MacroJob           (jobId).executeMacroJob();

				/*
				 * A21
				 */
				if (Flag.TRUE) new MURA_RAWDATA_MacroJob                (jobId).executeMacroJob();
				if (Flag.TRUE) new MURA_RAWDATA_ANAL2_MacroJob          (jobId).executeMacroJob();
				if (Flag.TRUE) new BAD_GLASS_ORG_MacroJob               (jobId).executeMacroJob();
				if (Flag.TRUE) new BAD_GLASS_MacroJob                   (jobId).executeMacroJob();
				if (Flag.TRUE) new GOOD_GLASS_ORG_MacroJob              (jobId).executeMacroJob();
				if (Flag.TRUE) new GOOD_GLASS_MacroJob                  (jobId).executeMacroJob();
				if (Flag.TRUE) new BAD_GLASS_NO_MacroJob                (jobId).executeMacroJob();
				if (Flag.TRUE) new IN_DBSCAN_MacroJob                   (jobId).executeMacroJob();
				if (Flag.TRUE) new OUT_DBSCAN_MacroJob                  (jobId).executeMacroJob();
				if (Flag.TRUE) new REGROUP_CLUSTER_MacroJob             (jobId).executeMacroJob();
				if (Flag.TRUE) new FINAL_CLUSTER_MacroJob               (jobId).executeMacroJob();
				if (Flag.TRUE) new CLUSTER_OUTPUT_MacroJob              (jobId).executeMacroJob();
				if (Flag.TRUE) new SO_SYS_GLASS_LIST2_MacroJob          (jobId).executeMacroJob();

				/*
				 * A22 : IMG
				 */
				if (Flag.TRUE) new OUT_DBSCAN_RESULT_MacroJob           (jobId).executeMacroJob();
				if (Flag.TRUE) new MURA_IMAGE_MacroJob                  (jobId).executeMacroJob();
				if (Flag.TRUE) new OUT_DBSCAN_RESULT1_MacroJob          (jobId).executeMacroJob();
				if (Flag.TRUE) new BUBBLE_IDX_MacroJob                  (jobId).executeMacroJob();
				if (Flag.TRUE) new BUBBLE_CLUSTER_CELLLOC_MacroJob      (jobId).executeMacroJob();
				if (Flag.TRUE) new RP_MUR_BUBBLE_MAP_SP_SUMM_MacroJob   (jobId).executeMacroJob();
				if (Flag.TRUE) new MURA_MAP_XY_4SF_RESULT_MacroJob      (jobId).executeMacroJob();
				if (Flag.TRUE) new RP_MUR_TOTMURA_MAP_SUMM_MacroJob     (jobId).executeMacroJob();
				if (Flag.TRUE) new CONTACT_MAP_MacroJob                 (jobId).executeMacroJob();
				if (Flag.TRUE) new CONTACT_MAP_XY_RESULT_MacroJob       (jobId).executeMacroJob();
				if (Flag.TRUE) new MURA_MAP_XY_RESULT_MacroJob          (jobId).executeMacroJob();
				if (Flag.TRUE) new CONTACT_MURA_XY_RESULT_MacroJob      (jobId).executeMacroJob();
				if (Flag.TRUE) new CONTACT_MAP_XY_DIST_RESULT_MacroJob  (jobId).executeMacroJob();
				if (Flag.TRUE) new CONTACT_MURA_DIST_RESULT_MacroJob    (jobId).executeMacroJob();
				if (Flag.TRUE) new AVG_MURA_MAP_XY_4SF_RESULT_MacroJob  (jobId).executeMacroJob();
				if (Flag.TRUE) new RP_MUR_AVGMUR_MAP_SUMM_MacroJob      (jobId).executeMacroJob();
				if (Flag.TRUE) new CONTACT_MUR_DIST_4SF_RESULT_MacroJob (jobId).executeMacroJob();
				if (Flag.TRUE) new RP_MUR_CONTACT_MAP_SUMM_MacroJob     (jobId).executeMacroJob();

				/*
				 * A23 : WIP
				 */
				if (Flag.TRUE) new RP_MUR_WIP_SUMM1_MacroJob            (jobId).executeMacroJob();
				if (Flag.TRUE) new RP_MUR_WIP_SUMM2_MacroJob            (jobId).executeMacroJob();
				if (Flag.TRUE) new RP_MUR_WIP_SUMM3_MacroJob            (jobId).executeMacroJob();
				if (Flag.TRUE) new RP_MUR_WIP_SUMM4_MacroJob            (jobId).executeMacroJob();

				/*
				 * A24 : EQP
				 */
				if (Flag.TRUE) new RP_MUR_EQP_SUMM_MacroJob             (jobId).executeMacroJob();
				if (Flag.TRUE) new RP_MUR_EQP_DETAILS1_MacroJob         (jobId).executeMacroJob();
				if (Flag.TRUE) new RP_MUR_EQP_DETAILS2_MacroJob         (jobId).executeMacroJob();
				
				/*
				 * A25 : ERD_FDC
				 */
				if (Flag.TRUE) new RP_MUR_ERD_FDC_SUM01_MacroJob        (jobId).executeMacroJob();
				if (Flag.TRUE) new RP_MUR_ERD_FDC_SUM02_MacroJob        (jobId).executeMacroJob();
				if (Flag.TRUE) new RP_MUR_ERD_FDC_SUM03_MacroJob        (jobId).executeMacroJob();
				if (Flag.TRUE) new RP_MUR_ERD_FDC_SUM04_MacroJob        (jobId).executeMacroJob();
				if (Flag.TRUE) new RP_MUR_ERD_FDC_SUM05_MacroJob        (jobId).executeMacroJob();
				
				/*
				 * A26 : Report
				 */
				if (Flag.TRUE) new RP_MUR_BUBBLE_MAP_SUMM_MacroJob      (jobId).executeMacroJob();
				if (Flag.TRUE) new RP_MUR_CELL_MAP_SUMM_MacroJob        (jobId).executeMacroJob();
				if (Flag.TRUE) new RP_LYA_SUMM_MacroJob                 (jobId).executeMacroJob();
				//if (Flag.TRUE) new RP_MUR_TREND_SUMM_MacroJob           (jobId).executeMacroJob();  // A12.RP_MUR_TREND_SUMM_MacroJob
			}
			
			if (!Flag.TRUE) {
				/*
				 * A11
				 */
				if (Flag.TRUE) new BUBBLE_INDEX_MacroJob                (jobId).executeMacroJob();
				if (Flag.TRUE) new CELL_MAP_MacroJob                    (jobId).executeMacroJob();
				if (Flag.TRUE) new MURA_D_MacroJob                      (jobId).executeMacroJob();
				if (Flag.TRUE) new MURA_DESC_MacroJob                   (jobId).executeMacroJob();
				if (Flag.TRUE) new MURA_HIST_MacroJob                   (jobId).executeMacroJob();
				if (Flag.TRUE) new MURA_SPC_MacroJob                    (jobId).executeMacroJob();
				if (Flag.TRUE) new MURA_TREND_MacroJob                  (jobId).executeMacroJob();
				if (Flag.TRUE) new STEP_DESC_MacroJob                   (jobId).executeMacroJob();
				if (Flag.TRUE) new TOTALCELLFLOW_MacroJob               (jobId).executeMacroJob();
				if (Flag.TRUE) new WIP_EQP_SMMRY_MacroJob               (jobId).executeMacroJob();

				/*
				 * A12
				 */
				if (Flag.TRUE) new CELL_MAP_XY_MacroJob                 (jobId).executeMacroJob();
				if (Flag.TRUE) new EQP_MURA_TRD_MacroJob                (jobId).executeMacroJob();
				if (Flag.TRUE) new LYA_MUR_1_MacroJob                   (jobId).executeMacroJob();
				if (Flag.TRUE) new MURA_N_TREND_MacroJob                (jobId).executeMacroJob();
				if (Flag.TRUE) new MURA_CNT_MacroJob                    (jobId).executeMacroJob();
				if (Flag.TRUE) new MURA_CNT_CELL_MacroJob               (jobId).executeMacroJob();
				if (Flag.TRUE) new MURA_D_01_MacroJob                   (jobId).executeMacroJob();
				if (Flag.TRUE) new MURA_SUM_MacroJob                    (jobId).executeMacroJob();
				if (Flag.TRUE) new T_CNT_CELL_MacroJob                  (jobId).executeMacroJob();
				if (Flag.TRUE) new TOTAL_CNT_MacroJob                   (jobId).executeMacroJob();
				if (Flag.TRUE) new Y_AXIS_MacroJob                      (jobId).executeMacroJob();
				if (Flag.TRUE) new Y_AXIS_CELL_MacroJob                 (jobId).executeMacroJob();
				if (Flag.TRUE) new RP_MUR_TREND_SUMM_MacroJob           (jobId).executeMacroJob();

				/*
				 * A21
				 */
				if (Flag.TRUE) new MURA_RAWDATA_MacroJob                (jobId).executeMacroJob();
				if (Flag.TRUE) new MURA_RAWDATA_ANAL2_MacroJob          (jobId).executeMacroJob();
				if (Flag.TRUE) new BAD_GLASS_ORG_MacroJob               (jobId).executeMacroJob();
				if (Flag.TRUE) new BAD_GLASS_MacroJob                   (jobId).executeMacroJob();
				if (Flag.TRUE) new GOOD_GLASS_ORG_MacroJob              (jobId).executeMacroJob();
				if (Flag.TRUE) new GOOD_GLASS_MacroJob                  (jobId).executeMacroJob();
				if (Flag.TRUE) new BAD_GLASS_NO_MacroJob                (jobId).executeMacroJob();
				if (Flag.TRUE) new IN_DBSCAN_MacroJob                   (jobId).executeMacroJob();
				if (Flag.TRUE) new OUT_DBSCAN_MacroJob                  (jobId).executeMacroJob();
				if (Flag.TRUE) new REGROUP_CLUSTER_MacroJob             (jobId).executeMacroJob();
				if (Flag.TRUE) new FINAL_CLUSTER_MacroJob               (jobId).executeMacroJob();
				if (Flag.TRUE) new CLUSTER_OUTPUT_MacroJob              (jobId).executeMacroJob();
				if (Flag.TRUE) new SO_SYS_GLASS_LIST2_MacroJob          (jobId).executeMacroJob();

				/*
				 * A22 : IMG
				 */
				if (Flag.TRUE) new OUT_DBSCAN_RESULT_MacroJob           (jobId).executeMacroJob();
				if (Flag.TRUE) new MURA_IMAGE_MacroJob                  (jobId).executeMacroJob();
				if (Flag.TRUE) new OUT_DBSCAN_RESULT1_MacroJob          (jobId).executeMacroJob();
				if (Flag.TRUE) new BUBBLE_IDX_MacroJob                  (jobId).executeMacroJob();
				if (Flag.TRUE) new BUBBLE_CLUSTER_CELLLOC_MacroJob      (jobId).executeMacroJob();
				if (Flag.TRUE) new RP_MUR_BUBBLE_MAP_SP_SUMM_MacroJob   (jobId).executeMacroJob();
				if (Flag.TRUE) new MURA_MAP_XY_4SF_RESULT_MacroJob      (jobId).executeMacroJob();
				if (Flag.TRUE) new RP_MUR_TOTMURA_MAP_SUMM_MacroJob     (jobId).executeMacroJob();
				if (Flag.TRUE) new CONTACT_MAP_MacroJob                 (jobId).executeMacroJob();
				if (Flag.TRUE) new CONTACT_MAP_XY_RESULT_MacroJob       (jobId).executeMacroJob();
				if (Flag.TRUE) new MURA_MAP_XY_RESULT_MacroJob          (jobId).executeMacroJob();
				if (Flag.TRUE) new CONTACT_MURA_XY_RESULT_MacroJob      (jobId).executeMacroJob();
				if (Flag.TRUE) new CONTACT_MAP_XY_DIST_RESULT_MacroJob  (jobId).executeMacroJob();
				if (Flag.TRUE) new CONTACT_MURA_DIST_RESULT_MacroJob    (jobId).executeMacroJob();
				if (Flag.TRUE) new AVG_MURA_MAP_XY_4SF_RESULT_MacroJob  (jobId).executeMacroJob();
				if (Flag.TRUE) new RP_MUR_AVGMUR_MAP_SUMM_MacroJob      (jobId).executeMacroJob();
				if (Flag.TRUE) new CONTACT_MUR_DIST_4SF_RESULT_MacroJob (jobId).executeMacroJob();
				if (Flag.TRUE) new RP_MUR_CONTACT_MAP_SUMM_MacroJob     (jobId).executeMacroJob();

				/*
				 * A23 : WIP
				 */
				if (Flag.TRUE) new RP_MUR_WIP_SUMM1_MacroJob            (jobId).executeMacroJob();
				if (Flag.TRUE) new RP_MUR_WIP_SUMM2_MacroJob            (jobId).executeMacroJob();
				if (Flag.TRUE) new RP_MUR_WIP_SUMM3_MacroJob            (jobId).executeMacroJob();
				if (Flag.TRUE) new RP_MUR_WIP_SUMM4_MacroJob            (jobId).executeMacroJob();

				/*
				 * A24 : EQP
				 */
				if (Flag.TRUE) new RP_MUR_EQP_SUMM_MacroJob             (jobId).executeMacroJob();
				if (Flag.TRUE) new RP_MUR_EQP_DETAILS1_MacroJob         (jobId).executeMacroJob();
				if (Flag.TRUE) new RP_MUR_EQP_DETAILS2_MacroJob         (jobId).executeMacroJob();
				
				/*
				 * A25 : ERD_FDC
				 */
				if (Flag.TRUE) new RP_MUR_ERD_FDC_SUM01_MacroJob        (jobId).executeMacroJob();
				if (Flag.TRUE) new RP_MUR_ERD_FDC_SUM02_MacroJob        (jobId).executeMacroJob();
				if (Flag.TRUE) new RP_MUR_ERD_FDC_SUM03_MacroJob        (jobId).executeMacroJob();
				if (Flag.TRUE) new RP_MUR_ERD_FDC_SUM04_MacroJob        (jobId).executeMacroJob();
				if (Flag.TRUE) new RP_MUR_ERD_FDC_SUM05_MacroJob        (jobId).executeMacroJob();
				
				/*
				 * A26 : Report
				 */
				if (Flag.TRUE) new RP_MUR_BUBBLE_MAP_SUMM_MacroJob      (jobId).executeMacroJob();
				if (Flag.TRUE) new RP_MUR_CELL_MAP_SUMM_MacroJob        (jobId).executeMacroJob();
				if (Flag.TRUE) new RP_LYA_SUMM_MacroJob                 (jobId).executeMacroJob();
				//if (Flag.TRUE) new RP_MUR_TREND_SUMM_MacroJob           (jobId).executeMacroJob();  // A12.RP_MUR_TREND_SUMM_MacroJob
				
				/*
				 * A27 : DelayTime, History, Metro
				 * TODO : 2014.12.04 : 오늘까지 로직이 정리되지 않음
				 */
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
			SystemProperties.setTesting("020");
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
