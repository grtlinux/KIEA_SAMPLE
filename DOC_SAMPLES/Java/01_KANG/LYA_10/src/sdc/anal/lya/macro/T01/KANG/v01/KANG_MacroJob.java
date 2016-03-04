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

package sdc.anal.lya.macro.T01.KANG.v01;

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
public class KANG_MacroJob extends AbstractMacroJob
{
	private KANG_OracleReader reader = null;
	private KANG_CsvWriter writer = null;
	
	private List<KANG_ReadBean> inList = null;
	private List<KANG_WriteBean> outList = null;
	
	private String filePath = null;

	private String jobId = null;

	public KANG_MacroJob(String jobId)
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
				reader = new KANG_OracleReader();
				writer = new KANG_CsvWriter(this.filePath);
				
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
					for (KANG_ReadBean readBean : inList) {
						KANG_WriteBean writeBean = new KANG_WriteBean();

						writeBean.setQafJobBu              (readBean.getQafJobBu              ());
						writeBean.setQafJobId              (readBean.getQafJobId              ());
						writeBean.setQafJobWfSeq           (readBean.getQafJobWfSeq           ());
						writeBean.setQafJobWfId            (readBean.getQafJobWfId            ());
						writeBean.setQafJobService         (readBean.getQafJobService         ());
						writeBean.setQafJobTbwProcessId    (readBean.getQafJobTbwProcessId    ());
						writeBean.setQafJobStartDatetime   (readBean.getQafJobStartDatetime   ());
						writeBean.setQafJobEndDatetime     (readBean.getQafJobEndDatetime     ());
						writeBean.setQafJobResult          (readBean.getQafJobResult          ());
						writeBean.setQafJobMessage         (readBean.getQafJobMessage         ());
						writeBean.setQafLastUpdateUser     (readBean.getQafLastUpdateUser     ());
						writeBean.setQafLastUpdateDatetime (readBean.getQafLastUpdateDatetime ());
						writeBean.setQafIdSasProcessId     (readBean.getQafIdSasProcessId     ());
						writeBean.setQafJobClass           (readBean.getQafJobClass           ());

						outList.add(writeBean);
					}
				}

				if (!Flag.TRUE) {
					for (int i=0; inList != null && i < inList.size(); i++) {
						KANG_WriteBean writeBean = new KANG_WriteBean();

						writeBean.setQafJobBu              (inList.get(i).getQafJobBu              ());
						writeBean.setQafJobId              (inList.get(i).getQafJobId              ());
						writeBean.setQafJobWfSeq           (inList.get(i).getQafJobWfSeq           ());
						writeBean.setQafJobWfId            (inList.get(i).getQafJobWfId            ());
						writeBean.setQafJobService         (inList.get(i).getQafJobService         ());
						writeBean.setQafJobTbwProcessId    (inList.get(i).getQafJobTbwProcessId    ());
						writeBean.setQafJobStartDatetime   (inList.get(i).getQafJobStartDatetime   ());
						writeBean.setQafJobEndDatetime     (inList.get(i).getQafJobEndDatetime     ());
						writeBean.setQafJobResult          (inList.get(i).getQafJobResult          ());
						writeBean.setQafJobMessage         (inList.get(i).getQafJobMessage         ());
						writeBean.setQafLastUpdateUser     (inList.get(i).getQafLastUpdateUser     ());
						writeBean.setQafLastUpdateDatetime (inList.get(i).getQafLastUpdateDatetime ());
						writeBean.setQafIdSasProcessId     (inList.get(i).getQafIdSasProcessId     ());
						writeBean.setQafJobClass           (inList.get(i).getQafJobClass           ());

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
				
				KANG_Main.cntWList = outList.size();

				writer.writeList();

				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
