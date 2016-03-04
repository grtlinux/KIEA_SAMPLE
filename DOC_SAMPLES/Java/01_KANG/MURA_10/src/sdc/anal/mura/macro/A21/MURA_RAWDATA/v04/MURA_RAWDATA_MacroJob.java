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

package sdc.anal.mura.macro.A21.MURA_RAWDATA.v04;

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
 * @file_name MURA_RAWDATA_MacroJob.java
 *
 */
public class MURA_RAWDATA_MacroJob extends AbstractMacroJob
{
	private MURA_RAWDATA_OracleReader reader = null;
	private MURA_RAWDATA_CsvWriter writer = null;
	
	private List<MURA_RAWDATA_ReadBean> inList = null;
	private List<MURA_RAWDATA_WriteBean> outList = null;
	
	private String filePath = null;

	private String jobId = null;

	public MURA_RAWDATA_MacroJob(String jobId)
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
			MURA_RAWDATA_Main.runMSec = System.nanoTime();
		}

		if (Flag.TRUE) {
			/*
			 * 기존 자료 삭제
			 */
			AnalCsvBean bean = new AnalCsvBean();

			bean.setType(AnalCsvType.DELETE);
			bean.setTable("ANAL_CSV");
			bean.setWhere(String.format("JOB_ID = %s AND PROG_NM = %s", StrUtil.quote(jobId), StrUtil.quote(MURA_RAWDATA_Main.dsName)));

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
					{ "CMD_CODE   ", StrUtil.quote(MURA_RAWDATA_Main.dsName.substring(0, 3)) },
					{ "PROG_NM    ", StrUtil.quote(MURA_RAWDATA_Main.dsName) },
					{ "CSV_NM     ", StrUtil.quote(MURA_RAWDATA_Main.dsName + ".csv") },
					{ "MAIN_CLASS ", StrUtil.quote(this.getClass().getName()) },
					{ "S_TIME     ", "SYSDATE" },
					{ "CSV_STATUS ", StrUtil.quote("START") },
			});
			if (Flag.TRUE) Logger.info("[%s]", bean.getQuery());

			bean.sendToOracle();
		}

		if (Flag.TRUE) {
			try {
				reader = new MURA_RAWDATA_OracleReader();
				//writer = new MURA_RAWDATA_CsvWriter(this.filePath + FileValue.SEP + this.dsName + "Write.csv");
				writer = new MURA_RAWDATA_CsvWriter(this.filePath);
				
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
					for (MURA_RAWDATA_ReadBean readBean : inList) {
						MURA_RAWDATA_WriteBean writeBean = new MURA_RAWDATA_WriteBean();

						writeBean.setProcId       (readBean.getProcId       ());
						writeBean.setSiteId       (readBean.getSiteId       ());
						writeBean.setCellId       (readBean.getCellId       ());
						writeBean.setGlassId      (readBean.getGlassId      ());
						writeBean.setProdGrpName  (readBean.getProdGrpName  ());
						writeBean.setProdId       (readBean.getProdId       ());
						writeBean.setOrgStepId    (readBean.getOrgStepId    ());
						writeBean.setMeasEqpUnitId(readBean.getMeasEqpUnitId());
						writeBean.setDcolTime     (readBean.getDcolTime     ());
						writeBean.setItemId       (readBean.getItemId       ());
						writeBean.setSubItemId    (readBean.getSubItemId    ());
						writeBean.setDefectName   (readBean.getDefectName   ());
						writeBean.setDataValue    (readBean.getDataValue    ());
						writeBean.setGateLine     (readBean.getGateLine     ());
						writeBean.setDataLine     (readBean.getDataLine     ());
						writeBean.setGateLine2    (readBean.getGateLine2    ());
						writeBean.setDataLine2    (readBean.getDataLine2    ());
						writeBean.setProdType     (readBean.getProdType     ());
						writeBean.setCellLocId    (readBean.getCellLocId    ());
						writeBean.setWidth        (readBean.getWidth        ());
						writeBean.setHeight       (readBean.getHeight       ());
						writeBean.setXValue       (readBean.getXValue       ());
						writeBean.setYValue       (readBean.getYValue       ());
						writeBean.setX2Value      (readBean.getX2Value      ());
						writeBean.setY2Value      (readBean.getY2Value      ());

						outList.add(writeBean);
					}
				}

				if (!Flag.TRUE) {
					for (int i=0; inList != null && i < inList.size(); i++) {
						MURA_RAWDATA_WriteBean writeBean = new MURA_RAWDATA_WriteBean();

						writeBean.setProcId       (inList.get(i).getProcId       ());
						writeBean.setSiteId       (inList.get(i).getSiteId       ());
						writeBean.setCellId       (inList.get(i).getCellId       ());
						writeBean.setGlassId      (inList.get(i).getGlassId      ());
						writeBean.setProdGrpName  (inList.get(i).getProdGrpName  ());
						writeBean.setProdId       (inList.get(i).getProdId       ());
						writeBean.setOrgStepId    (inList.get(i).getOrgStepId    ());
						writeBean.setMeasEqpUnitId(inList.get(i).getMeasEqpUnitId());
						writeBean.setDcolTime     (inList.get(i).getDcolTime     ());
						writeBean.setItemId       (inList.get(i).getItemId       ());
						writeBean.setSubItemId    (inList.get(i).getSubItemId    ());
						writeBean.setDefectName   (inList.get(i).getDefectName   ());
						writeBean.setDataValue    (inList.get(i).getDataValue    ());
						writeBean.setGateLine     (inList.get(i).getGateLine     ());
						writeBean.setDataLine     (inList.get(i).getDataLine     ());
						writeBean.setGateLine2    (inList.get(i).getGateLine2    ());
						writeBean.setDataLine2    (inList.get(i).getDataLine2    ());
						writeBean.setProdType     (inList.get(i).getProdType     ());
						writeBean.setCellLocId    (inList.get(i).getCellLocId    ());
						writeBean.setWidth        (inList.get(i).getWidth        ());
						writeBean.setHeight       (inList.get(i).getHeight       ());
						writeBean.setXValue       (inList.get(i).getXValue       ());
						writeBean.setYValue       (inList.get(i).getYValue       ());
						writeBean.setX2Value      (inList.get(i).getX2Value      ());
						writeBean.setY2Value      (inList.get(i).getY2Value      ());

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
				
				MURA_RAWDATA_Main.cntWList = outList.size();

				writer.writeList();

				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		if (Flag.TRUE) {
			/*
			 * 시간기록 끝
			 */
			MURA_RAWDATA_Main.runMSec = (System.nanoTime() - MURA_RAWDATA_Main.runMSec) / 1000 / 1000;
			
			if (Flag.TRUE) Logger.info("##### took time : %d ms", MURA_RAWDATA_Main.runMSec);
		}

		if (Flag.TRUE) {
			/*
			 * UPDATE
			 */
			AnalCsvBean bean = new AnalCsvBean();
			
			bean.setType(AnalCsvType.UPDATE);
			bean.setTable("ANAL_CSV");
			bean.setFields(new String[][] {
					{ "LIST_CNT   ", "" + MURA_RAWDATA_Main.cntWList },
					{ "E_TIME     ", "SYSDATE" },
					{ "R_MSEC     ", "" + MURA_RAWDATA_Main.runMSec },
					{ "CSV_STATUS ", StrUtil.quote("OK") },   // OK, ERROR, SKIP, ETC...
					{ "LOG_MESSAGE", StrUtil.quote("COMPLETE") },  // COMPLETE, EXCEPTION
			});
			bean.setWhere(String.format("JOB_ID = %s AND PROG_NM = %s", StrUtil.quote(jobId), StrUtil.quote(MURA_RAWDATA_Main.dsName)));
			
			if (Flag.TRUE) Logger.info("[%s]", bean.getQuery());
			
			bean.sendToOracle();
		}
	}
}
