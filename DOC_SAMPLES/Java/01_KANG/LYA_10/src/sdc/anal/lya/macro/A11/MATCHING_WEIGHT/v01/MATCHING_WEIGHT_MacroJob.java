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

package sdc.anal.lya.macro.A11.MATCHING_WEIGHT.v01;

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
 * @file_name AIB_MATCHING_WEIGHT_MacroJob.java
 *
 */
public class MATCHING_WEIGHT_MacroJob extends AbstractMacroJob
{
	private MATCHING_WEIGHT_OracleReader reader = null;
	private MATCHING_WEIGHT_CsvWriter writer = null;
	
	private List<MATCHING_WEIGHT_ReadBean> inList = null;
	private List<MATCHING_WEIGHT_WriteBean> outList = null;
	
	private String filePath = null;

	private String jobId = null;

	public MATCHING_WEIGHT_MacroJob(String jobId)
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
				reader = new MATCHING_WEIGHT_OracleReader();
				writer = new MATCHING_WEIGHT_CsvWriter(this.filePath);
				
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
					for (MATCHING_WEIGHT_ReadBean readBean : inList) {
						MATCHING_WEIGHT_WriteBean writeBean = new MATCHING_WEIGHT_WriteBean();

						writeBean.setSiteId            (readBean.getSiteId            ());
						writeBean.setDefectCd          (readBean.getDefectCd          ());
						writeBean.setDetectTopStepId   (readBean.getDetectTopStepId   ());
						writeBean.setDetectBottomStepId(readBean.getDetectBottomStepId());
						writeBean.setApplTopStepId     (readBean.getApplTopStepId     ());
						writeBean.setApplBottomStepId  (readBean.getApplBottomStepId  ());
						writeBean.setWeight            (readBean.getWeight            ());
						writeBean.setUseYn             (readBean.getUseYn             ());
						writeBean.setRegisterDate      (readBean.getRegisterDate      ());

						outList.add(writeBean);
					}
				}

				if (!Flag.TRUE) {
					for (int i=0; inList != null && i < inList.size(); i++) {
						MATCHING_WEIGHT_WriteBean writeBean = new MATCHING_WEIGHT_WriteBean();

						writeBean.setSiteId            (inList.get(i).getSiteId            ());
						writeBean.setDefectCd          (inList.get(i).getDefectCd          ());
						writeBean.setDetectTopStepId   (inList.get(i).getDetectTopStepId   ());
						writeBean.setDetectBottomStepId(inList.get(i).getDetectBottomStepId());
						writeBean.setApplTopStepId     (inList.get(i).getApplTopStepId     ());
						writeBean.setApplBottomStepId  (inList.get(i).getApplBottomStepId  ());
						writeBean.setWeight            (inList.get(i).getWeight            ());
						writeBean.setUseYn             (inList.get(i).getUseYn             ());
						writeBean.setRegisterDate      (inList.get(i).getRegisterDate      ());

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
				
				MATCHING_WEIGHT_Main.cntWList = outList.size();

				writer.writeList();

				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
