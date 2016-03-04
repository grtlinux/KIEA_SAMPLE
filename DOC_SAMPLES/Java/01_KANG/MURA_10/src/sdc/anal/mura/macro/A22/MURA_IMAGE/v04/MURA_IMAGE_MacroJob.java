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

package sdc.anal.mura.macro.A22.MURA_IMAGE.v04;

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
 * @file_name MURA_IMAGE_MacroJob.java
 *
 */
public class MURA_IMAGE_MacroJob extends AbstractMacroJob
{
	private MURA_IMAGE_OracleReader reader = null;
	private MURA_IMAGE_CsvWriter writer = null;
	
	private List<MURA_IMAGE_ReadBean> inList = null;
	private List<MURA_IMAGE_WriteBean> outList = null;
	
	private String filePath = null;

	private String jobId = null;

	public MURA_IMAGE_MacroJob(String jobId)
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
			MURA_IMAGE_Main.runMSec = System.nanoTime();
		}

		if (Flag.TRUE) {
			/*
			 * 기존 자료 삭제
			 */
			AnalCsvBean bean = new AnalCsvBean();

			bean.setType(AnalCsvType.DELETE);
			bean.setTable("ANAL_CSV");
			bean.setWhere(String.format("JOB_ID = %s AND PROG_NM = %s", StrUtil.quote(jobId), StrUtil.quote(MURA_IMAGE_Main.dsName)));

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
					{ "CMD_CODE   ", StrUtil.quote(MURA_IMAGE_Main.dsName.substring(0, 3)) },
					{ "PROG_NM    ", StrUtil.quote(MURA_IMAGE_Main.dsName) },
					{ "CSV_NM     ", StrUtil.quote(MURA_IMAGE_Main.dsName + ".csv") },
					{ "MAIN_CLASS ", StrUtil.quote(this.getClass().getName()) },
					{ "S_TIME     ", "SYSDATE" },
					{ "CSV_STATUS ", StrUtil.quote("START") },
			});
			if (Flag.TRUE) Logger.info("[%s]", bean.getQuery());

			bean.sendToOracle();
		}

		if (Flag.TRUE) {
			try {
				reader = new MURA_IMAGE_OracleReader();
				//writer = new MURA_IMAGE_CsvWriter(this.filePath + FileValue.SEP + this.dsName + "Write.csv");
				writer = new MURA_IMAGE_CsvWriter(this.filePath);
				
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
					for (MURA_IMAGE_ReadBean readBean : inList) {
						MURA_IMAGE_WriteBean writeBean = new MURA_IMAGE_WriteBean();

						writeBean.setSiteId        (readBean.getSiteId        ());
						writeBean.setProcessId     (readBean.getProcessId     ());
						writeBean.setProductId     (readBean.getProductId     ());
						writeBean.setStepId        (readBean.getStepId        ());
						writeBean.setModuleType    (readBean.getModuleType    ());
						writeBean.setEqpId         (readBean.getEqpId         ());
						writeBean.setBatchId       (readBean.getBatchId       ());
						writeBean.setGlassCellId   (readBean.getGlassCellId   ());
						writeBean.setETime         (readBean.getETime         ());
						writeBean.setImagePath     (readBean.getImagePath     ());
						writeBean.setImagePathRed  (readBean.getImagePath().replace("gray", "red"  ).replace("GRAY", "RED"  ));
						writeBean.setImagePathGreen(readBean.getImagePath().replace("gray", "green").replace("GRAY", "GREEN"));
						writeBean.setImagePathBlue (readBean.getImagePath().replace("gray", "blue" ).replace("GRAY", "BLUE" ));

						outList.add(writeBean);
					}
				}

				if (!Flag.TRUE) {
					for (int i=0; inList != null && i < inList.size(); i++) {
						MURA_IMAGE_WriteBean writeBean = new MURA_IMAGE_WriteBean();

						writeBean.setSiteId        (inList.get(i).getSiteId        ());
						writeBean.setProcessId     (inList.get(i).getProcessId     ());
						writeBean.setProductId     (inList.get(i).getProductId     ());
						writeBean.setStepId        (inList.get(i).getStepId        ());
						writeBean.setModuleType    (inList.get(i).getModuleType    ());
						writeBean.setEqpId         (inList.get(i).getEqpId         ());
						writeBean.setBatchId       (inList.get(i).getBatchId       ());
						writeBean.setGlassCellId   (inList.get(i).getGlassCellId   ());
						writeBean.setETime         (inList.get(i).getETime         ());
						writeBean.setImagePath     (inList.get(i).getImagePath     ());
						writeBean.setImagePathRed  (inList.get(i).getImagePath().replace("gray", "red"  ).replace("GRAY", "RED"  ));
						writeBean.setImagePathGreen(inList.get(i).getImagePath().replace("gray", "green").replace("GRAY", "GREEN"));
						writeBean.setImagePathBlue (inList.get(i).getImagePath().replace("gray", "blue" ).replace("GRAY", "BLUE" ));

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
				
				MURA_IMAGE_Main.cntWList = outList.size();

				writer.writeList();

				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		if (Flag.TRUE) {
			/*
			 * 시간기록 끝
			 */
			MURA_IMAGE_Main.runMSec = (System.nanoTime() - MURA_IMAGE_Main.runMSec) / 1000 / 1000;
			
			if (Flag.TRUE) Logger.info("##### took time : %d ms", MURA_IMAGE_Main.runMSec);
		}

		if (Flag.TRUE) {
			/*
			 * UPDATE
			 */
			AnalCsvBean bean = new AnalCsvBean();
			
			bean.setType(AnalCsvType.UPDATE);
			bean.setTable("ANAL_CSV");
			bean.setFields(new String[][] {
					{ "LIST_CNT   ", "" + MURA_IMAGE_Main.cntWList },
					{ "E_TIME     ", "SYSDATE" },
					{ "R_MSEC     ", "" + MURA_IMAGE_Main.runMSec },
					{ "CSV_STATUS ", StrUtil.quote("OK") },   // OK, ERROR, SKIP, ETC...
					{ "LOG_MESSAGE", StrUtil.quote("COMPLETE") },  // COMPLETE, EXCEPTION
			});
			bean.setWhere(String.format("JOB_ID = %s AND PROG_NM = %s", StrUtil.quote(jobId), StrUtil.quote(MURA_IMAGE_Main.dsName)));
			
			if (Flag.TRUE) Logger.info("[%s]", bean.getQuery());
			
			bean.sendToOracle();
		}
	}
}
