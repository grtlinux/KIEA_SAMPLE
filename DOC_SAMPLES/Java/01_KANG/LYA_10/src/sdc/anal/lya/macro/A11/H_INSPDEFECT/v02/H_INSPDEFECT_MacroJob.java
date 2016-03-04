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

package sdc.anal.lya.macro.A11.H_INSPDEFECT.v02;

import java.util.List;

import kiea.kr.co.tain.base.Flag;
import kiea.proj.sdc.anal.macro.impl.job.AbstractMacroJob;
import kiea.proj.sdc.anal.util.BasePath;
import kiea.proj.sdc.anal.util.FileUtil;
import kiea.proj.sdc.anal.util.StrUtil;
import kiea.proj.sdc.anal.util.log.v02.Logger;

/**
 * @author KangSeok
 * @date 2014. 8. 8.
 * @file_name AIB_H_INSPDEFECT_MacroJob.java
 *
 */
public class H_INSPDEFECT_MacroJob extends AbstractMacroJob
{
	private H_INSPDEFECT_OracleReader reader = null;
	private H_INSPDEFECT_CsvWriter writer = null;
	
	private List<H_INSPDEFECT_ReadBean> inList = null;
	private List<H_INSPDEFECT_WriteBean> outList = null;
	
	private String filePath = null;

	private String jobId = null;

	public H_INSPDEFECT_MacroJob(String jobId)
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
			try {
				reader = new H_INSPDEFECT_OracleReader();
				writer = new H_INSPDEFECT_CsvWriter(this.filePath);
				
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
					for (H_INSPDEFECT_ReadBean readBean : inList) {
						H_INSPDEFECT_WriteBean writeBean = new H_INSPDEFECT_WriteBean();

						writeBean.setCellId            (readBean.getCellId            ());
						writeBean.setGlassId           (readBean.getGlassId           ());
						writeBean.setSiteId            (readBean.getSiteId            ());
						writeBean.setInspStepType      (readBean.getInspStepType      ());
						writeBean.setDecGradeCd        (readBean.getDecGradeCd        ());
						writeBean.setInspDate          (readBean.getInspDate          ());
						writeBean.setInspHour          (readBean.getInspHour          ());
						writeBean.setInspEqpId         (readBean.getInspEqpId         ());
						writeBean.setBatchId           (readBean.getBatchId           ());
						writeBean.setProdType          (readBean.getProdType          ());
						writeBean.setProdId            (readBean.getProdId            ());
						writeBean.setProcId            (readBean.getProcId            ());
						writeBean.setInspStepId        (readBean.getInspStepId        ());
						writeBean.setBinGradeCd        (readBean.getBinGradeCd        ());
						writeBean.setMatchLotType      (readBean.getMatchLotType      ());
						writeBean.setDefectCd          (readBean.getDefectCd          ());
						writeBean.setWorkerId          (readBean.getWorkerId          ());
						writeBean.setCellLocId         (readBean.getCellLocId         ());
						writeBean.setDefectSysId       (readBean.getDefectSysId       ());
						writeBean.setOutSiteId         (readBean.getOutSiteId         ());
						writeBean.setUserDef01         (readBean.getUserDef01         ());
						writeBean.setDefectQty         (readBean.getDefectQty         ());
						writeBean.setEtlModifyDate     (readBean.getEtlModifyDate     ());
						writeBean.setAlterEtlModifyDate(readBean.getAlterEtlModifyDate());

						outList.add(writeBean);
					}
				}

				if (!Flag.TRUE) {
					for (int i=0; inList != null && i < inList.size(); i++) {
						H_INSPDEFECT_WriteBean writeBean = new H_INSPDEFECT_WriteBean();

						writeBean.setCellId            (inList.get(i).getCellId            ());
						writeBean.setGlassId           (inList.get(i).getGlassId           ());
						writeBean.setSiteId            (inList.get(i).getSiteId            ());
						writeBean.setInspStepType      (inList.get(i).getInspStepType      ());
						writeBean.setDecGradeCd        (inList.get(i).getDecGradeCd        ());
						writeBean.setInspDate          (inList.get(i).getInspDate          ());
						writeBean.setInspHour          (inList.get(i).getInspHour          ());
						writeBean.setInspEqpId         (inList.get(i).getInspEqpId         ());
						writeBean.setBatchId           (inList.get(i).getBatchId           ());
						writeBean.setProdType          (inList.get(i).getProdType          ());
						writeBean.setProdId            (inList.get(i).getProdId            ());
						writeBean.setProcId            (inList.get(i).getProcId            ());
						writeBean.setInspStepId        (inList.get(i).getInspStepId        ());
						writeBean.setBinGradeCd        (inList.get(i).getBinGradeCd        ());
						writeBean.setMatchLotType      (inList.get(i).getMatchLotType      ());
						writeBean.setDefectCd          (inList.get(i).getDefectCd          ());
						writeBean.setWorkerId          (inList.get(i).getWorkerId          ());
						writeBean.setCellLocId         (inList.get(i).getCellLocId         ());
						writeBean.setDefectSysId       (inList.get(i).getDefectSysId       ());
						writeBean.setOutSiteId         (inList.get(i).getOutSiteId         ());
						writeBean.setUserDef01         (inList.get(i).getUserDef01         ());
						writeBean.setDefectQty         (inList.get(i).getDefectQty         ());
						writeBean.setEtlModifyDate     (inList.get(i).getEtlModifyDate     ());
						writeBean.setAlterEtlModifyDate(inList.get(i).getAlterEtlModifyDate());

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
				
				H_INSPDEFECT_Main.cntWList = outList.size();

				writer.writeList();

				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
