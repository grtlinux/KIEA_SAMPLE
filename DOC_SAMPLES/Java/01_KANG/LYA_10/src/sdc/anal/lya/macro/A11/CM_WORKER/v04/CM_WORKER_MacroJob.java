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

package sdc.anal.lya.macro.A11.CM_WORKER.v04;

import java.util.List;

import kiea.kr.co.tain.base.Flag;
import kiea.proj.sdc.anal.macro.impl.job.AbstractMacroJob;
import kiea.proj.sdc.anal.tools.AnalCsv.v04.AnalCsvBean;
import kiea.proj.sdc.anal.tools.AnalCsv.v04.AnalCsvType;
import kiea.proj.sdc.anal.util.BasePath;
import kiea.proj.sdc.anal.util.FileUtil;
import kiea.proj.sdc.anal.util.StrUtil;
import kiea.proj.sdc.anal.util.log.v02.Logger;

/**
 * @author KangSeok
 * @date 2014. 8. 8.
 * @file_name AIB_CM_WORKER_MacroJob.java
 *
 */
public class CM_WORKER_MacroJob extends AbstractMacroJob
{
	private CM_WORKER_OracleReader reader = null;
	private CM_WORKER_CsvWriter writer = null;
	
	private List<CM_WORKER_ReadBean> inList = null;
	private List<CM_WORKER_WriteBean> outList = null;
	
	private String filePath = null;

	private String jobId = null;

	public CM_WORKER_MacroJob(String jobId)
	{
		this.jobId = jobId;
		
		this.filePath = FileUtil.makeDataDir(BasePath.getInstance().getDataPath(), StrUtil.getDateFromJobId(this.jobId), this.jobId);
	}
	
	/**
	 * generateDataSet
	 */
	public void generateDataSet()
	{
		if (Flag.TRUE) {
		}
	}

	/**
	 * beforeMacroJob
	 */
	public void beforeMacroJob()
	{
		if (Flag.TRUE) Logger.info("beforeMacroJob : " + this.getClass());
		
		if (Flag.TRUE) {
			/*
			 * 시간기록 시작
			 */
			CM_WORKER_Main.runMSec = System.nanoTime();
		}

		if (Flag.TRUE) {
			/*
			 * 기존 자료 삭제
			 */
			AnalCsvBean bean = new AnalCsvBean();

			bean.setType(AnalCsvType.DELETE);
			bean.setTable("ANAL_CSV");
			bean.setWhere(String.format("JOB_ID = %s AND PROG_NM = %s", StrUtil.quote(jobId), StrUtil.quote(CM_WORKER_Main.dsName)));

			bean.sendToOracle();

			if (Flag.TRUE) Logger.info("[%s]", bean.getQuery());
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
					{ "CMD_CODE   ", StrUtil.quote(CM_WORKER_Main.dsName.substring(0, 3)) },
					{ "PROG_NM    ", StrUtil.quote(CM_WORKER_Main.dsName) },
					{ "CSV_NM     ", StrUtil.quote(CM_WORKER_Main.dsName + ".csv") },
					{ "MAIN_CLASS ", StrUtil.quote(this.getClass().getName()) },
					{ "S_TIME     ", "SYSDATE" },
					{ "CSV_STATUS ", StrUtil.quote("START") },
			});
			if (Flag.TRUE) Logger.info("[%s]", bean.getQuery());

			bean.sendToOracle();
		}

		if (Flag.TRUE) {
			try {
				reader = new CM_WORKER_OracleReader();
				writer = new CM_WORKER_CsvWriter(this.filePath);
				
				inList = reader.getList(true);
				outList = writer.getList();
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * macroJob
	 */
	public void macroJob()
	{
		if (Flag.TRUE) Logger.info("macroJob : " + this.getClass());

		if (Flag.TRUE) {
			try {
				/*
				 * Job
				 * 1. InBean -> OutBean
				 */
				
				if (Flag.TRUE) {
					for (CM_WORKER_ReadBean readBean : inList) {
						CM_WORKER_WriteBean writeBean = new CM_WORKER_WriteBean();

						writeBean.setWorkerId     (readBean.getWorkerId     ());
						writeBean.setWorkerName   (readBean.getWorkerName   ());
						writeBean.setDeptCd       (readBean.getDeptCd       ());
						writeBean.setDeptName     (readBean.getDeptName     ());
						writeBean.setEtlModifyDate(readBean.getEtlModifyDate());
						writeBean.setPositionCd   (readBean.getPositionCd   ());
						writeBean.setPositionName (readBean.getPositionName ());
						writeBean.setEmail        (readBean.getEmail        ());
						writeBean.setStatus       (readBean.getStatus       ());
						writeBean.setSourceSiteId (readBean.getSourceSiteId ());
						writeBean.setIndept       (readBean.getIndept       ());
						writeBean.setJikchak      (readBean.getJikchak      ());
						writeBean.setDutyNm       (readBean.getDutyNm       ());
						writeBean.setLocate       (readBean.getLocate       ());
						writeBean.setEpId         (readBean.getEpId         ());
						writeBean.setUniqueId     (readBean.getUniqueId     ());
						writeBean.setEname        (readBean.getEname        ());

						outList.add(writeBean);
					}
				}

				if (!Flag.TRUE) {
					for (int i=0; inList != null && i < inList.size(); i++) {
						CM_WORKER_WriteBean writeBean = new CM_WORKER_WriteBean();

						writeBean.setWorkerId     (inList.get(i).getWorkerId     ());
						writeBean.setWorkerName   (inList.get(i).getWorkerName   ());
						writeBean.setDeptCd       (inList.get(i).getDeptCd       ());
						writeBean.setDeptName     (inList.get(i).getDeptName     ());
						writeBean.setEtlModifyDate(inList.get(i).getEtlModifyDate());
						writeBean.setPositionCd   (inList.get(i).getPositionCd   ());
						writeBean.setPositionName (inList.get(i).getPositionName ());
						writeBean.setEmail        (inList.get(i).getEmail        ());
						writeBean.setStatus       (inList.get(i).getStatus       ());
						writeBean.setSourceSiteId (inList.get(i).getSourceSiteId ());
						writeBean.setIndept       (inList.get(i).getIndept       ());
						writeBean.setJikchak      (inList.get(i).getJikchak      ());
						writeBean.setDutyNm       (inList.get(i).getDutyNm       ());
						writeBean.setLocate       (inList.get(i).getLocate       ());
						writeBean.setEpId         (inList.get(i).getEpId         ());
						writeBean.setUniqueId     (inList.get(i).getUniqueId     ());
						writeBean.setEname        (inList.get(i).getEname        ());

						outList.add(writeBean);
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * afterMacroJob
	 */
	public void afterMacroJob()
	{
		if (Flag.TRUE) Logger.info("afterMacroJob : " + this.getClass());
		
		if (Flag.TRUE) {
			try {
				
				CM_WORKER_Main.cntWList = outList.size();

				writer.writeList();

				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		if (Flag.TRUE) {
			/*
			 * 시간기록 끝
			 */
			CM_WORKER_Main.runMSec = (System.nanoTime() - CM_WORKER_Main.runMSec) / 1000 / 1000;
			
			if (Flag.TRUE) Logger.info("##### took time : %d ms", CM_WORKER_Main.runMSec);
		}

		if (Flag.TRUE) {
			/*
			 * UPDATE
			 */
			AnalCsvBean bean = new AnalCsvBean();
			
			bean.setType(AnalCsvType.UPDATE);
			bean.setTable("ANAL_CSV");
			bean.setFields(new String[][] {
					{ "LIST_CNT   ", "" + CM_WORKER_Main.cntWList },
					{ "E_TIME     ", "SYSDATE" },
					{ "R_MSEC     ", "" + CM_WORKER_Main.runMSec },
					{ "CSV_STATUS ", StrUtil.quote("OK") },   // OK, ERROR, SKIP, ETC...
					{ "LOG_MESSAGE", StrUtil.quote("COMPLETE") },  // COMPLETE, EXCEPTION
			});
			bean.setWhere(String.format("JOB_ID = %s AND PROG_NM = %s", StrUtil.quote(jobId), StrUtil.quote(CM_WORKER_Main.dsName)));
			
			if (Flag.TRUE) Logger.info("[%s]", bean.getQuery());
			
			bean.sendToOracle();
		}
	}
}
