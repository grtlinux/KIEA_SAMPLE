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

package sdc.anal.lya.macro.A23.MOD_PKBANK.v01;

import java.util.List;

import kiea.kr.co.tain.base.Flag;
import kiea.proj.sdc.anal.common.Parameter;
import kiea.proj.sdc.anal.macro.impl.job.AbstractMacroJob;
import kiea.proj.sdc.anal.util.BasePath;
import kiea.proj.sdc.anal.util.FileUtil;
import kiea.proj.sdc.anal.util.StrUtil;
import kiea.proj.sdc.anal.util.log.v02.Logger;

/**
 * @author KangSeok
 * @date 2014. 8. 8.
 * @file_name MOD_PKBANK_MacroJob.java
 *
 */
public class MOD_PKBANK_MacroJob extends AbstractMacroJob
{
	private MOD_PKBANK_OracleReader reader = null;
	private MOD_PKBANK_CsvWriter writer = null;
	
	private List<MOD_PKBANK_ReadBean> inList = null;
	private List<MOD_PKBANK_WriteBean> outList = null;
	
	private String filePath = null;

	private String jobId = null;

	public MOD_PKBANK_MacroJob(String jobId)
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
			/*
			 * Parameter
			 */
			Parameter.getInstance();  // default
		}
	}

	/**
	 * beforeMacroJob
	 */
	public void beforeMacroJob()
	{
		if (Flag.TRUE) Logger.info("1. beforeMacroJob : " + this.getClass());
		
		if (Flag.TRUE) {
			try {
				reader = new MOD_PKBANK_OracleReader();
				writer = new MOD_PKBANK_CsvWriter(this.filePath);
				
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
		if (Flag.TRUE) Logger.info("2. macroJob : " + this.getClass());

		if (Flag.TRUE) {
			try {
				/*
				 * Job
				 * 1. InBean -> OutBean
				 */
				
				if (Flag.TRUE) {
					for (MOD_PKBANK_ReadBean readBean : inList) {
						MOD_PKBANK_WriteBean writeBean = new MOD_PKBANK_WriteBean();

						writeBean.setSiteId            (readBean.getSiteId            ());
						writeBean.setProdGrpName       (readBean.getProdGrpName       ());
						writeBean.setProdId            (readBean.getProdId            ());
						writeBean.setGlassCellId       (readBean.getGlassCellId       ());
						writeBean.setImptStepGrpId     (readBean.getImptStepGrpId     ());
						writeBean.setImptEqpId         (readBean.getImptEqpId         ());
						writeBean.setProcHour          (readBean.getProcHour          ());
						writeBean.setCellId            (readBean.getCellId            ());
						writeBean.setGlassId           (readBean.getGlassId           ());
						writeBean.setInspStepType      (readBean.getInspStepType      ());
						writeBean.setDecGradeCd        (readBean.getDecGradeCd        ());
						writeBean.setInspDate          (readBean.getInspDate          ());
						writeBean.setInspHour          (readBean.getInspHour          ());
						writeBean.setInspEqpId         (readBean.getInspEqpId         ());
						writeBean.setBatchId           (readBean.getBatchId           ());
						writeBean.setProdType          (readBean.getProdType          ());
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
						MOD_PKBANK_WriteBean writeBean = new MOD_PKBANK_WriteBean();

						writeBean.setSiteId            (inList.get(i).getSiteId            ());
						writeBean.setProdGrpName       (inList.get(i).getProdGrpName       ());
						writeBean.setProdId            (inList.get(i).getProdId            ());
						writeBean.setGlassCellId       (inList.get(i).getGlassCellId       ());
						writeBean.setImptStepGrpId     (inList.get(i).getImptStepGrpId     ());
						writeBean.setImptEqpId         (inList.get(i).getImptEqpId         ());
						writeBean.setProcHour          (inList.get(i).getProcHour          ());
						writeBean.setCellId            (inList.get(i).getCellId            ());
						writeBean.setGlassId           (inList.get(i).getGlassId           ());
						writeBean.setInspStepType      (inList.get(i).getInspStepType      ());
						writeBean.setDecGradeCd        (inList.get(i).getDecGradeCd        ());
						writeBean.setInspDate          (inList.get(i).getInspDate          ());
						writeBean.setInspHour          (inList.get(i).getInspHour          ());
						writeBean.setInspEqpId         (inList.get(i).getInspEqpId         ());
						writeBean.setBatchId           (inList.get(i).getBatchId           ());
						writeBean.setProdType          (inList.get(i).getProdType          ());
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
		if (Flag.TRUE) Logger.info("3. afterMacroJob : " + this.getClass());
		
		if (Flag.TRUE) {
			try {
				
				MOD_PKBANK_Main.cntWList = outList.size();

				writer.writeList();

				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
