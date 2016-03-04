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

package sdc.anal.mura.macro.A24.RP_MUR_EQP_SUMM.v01;

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
 * @file_name ANAL_INDEX_MacroJob.java
 *
 */
public class RP_MUR_EQP_SUMM_MacroJob extends AbstractMacroJob
{
	private RP_MUR_EQP_SUMM_OracleReader reader = null;
	private RP_MUR_EQP_SUMM_CsvWriter writer = null;
	
	private List<RP_MUR_EQP_SUMM_ReadBean> inList = null;
	private List<RP_MUR_EQP_SUMM_WriteBean> outList = null;
	
	private String filePath = null;

	private String jobId = null;

	public RP_MUR_EQP_SUMM_MacroJob(String jobId)
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
				reader = new RP_MUR_EQP_SUMM_OracleReader();
				writer = new RP_MUR_EQP_SUMM_CsvWriter(this.filePath);
				
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
					for (RP_MUR_EQP_SUMM_ReadBean readBean : inList) {
						RP_MUR_EQP_SUMM_WriteBean writeBean = new RP_MUR_EQP_SUMM_WriteBean();

						writeBean.setMeasEqpUnitId(readBean.getMeasEqpUnitId());
						writeBean.setTotCnt       (readBean.getTotCnt       ());
						writeBean.setMuraCell     (readBean.getMuraCell     ());
						writeBean.setMuraSum      (readBean.getMuraSum      ());
						writeBean.setRatio        (readBean.getRatio        ());
						writeBean.setMuraCnt      (readBean.getMuraCnt      ());

						outList.add(writeBean);
					}
				}

				if (!Flag.TRUE) {
					for (int i=0; inList != null && i < inList.size(); i++) {
						RP_MUR_EQP_SUMM_WriteBean writeBean = new RP_MUR_EQP_SUMM_WriteBean();

						writeBean.setMeasEqpUnitId(inList.get(i).getMeasEqpUnitId());
						writeBean.setTotCnt       (inList.get(i).getTotCnt       ());
						writeBean.setMuraCell     (inList.get(i).getMuraCell     ());
						writeBean.setMuraSum      (inList.get(i).getMuraSum      ());
						writeBean.setRatio        (inList.get(i).getRatio        ());
						writeBean.setMuraCnt      (inList.get(i).getMuraCnt      ());

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
				
				RP_MUR_EQP_SUMM_Main.cntWList = outList.size();

				writer.writeList();

				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
