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

package kiea.proj.sdc.anal.macro.sample.a12.aib.macro.AIB_CLUSTER_CONTROL.v01;

import java.util.List;

import kiea.kr.co.tain.base.Flag;
import kiea.kr.co.tain.util.DateTime;
import kiea.proj.sdc.anal.macro.impl.job.AbstractMacroJob;
import kiea.proj.sdc.anal.util.BasePath;
import kiea.proj.sdc.anal.util.FileUtil;
import kiea.proj.sdc.anal.util.log.v02.Logger;

/**
 * @author KangSeok
 * @date 2014. 8. 8.
 * @file_name AIB_CLUSTER_CONTROL_MacroJob.java
 *
 */
public class AIB_CLUSTER_CONTROL_MacroJob extends AbstractMacroJob
{
	private AIB_CLUSTER_CONTROL_OracleReader reader = null;
	private AIB_CLUSTER_CONTROL_CsvWriter writer = null;
	
	private List<AIB_CLUSTER_CONTROL_ReadBean> inList = null;
	private List<AIB_CLUSTER_CONTROL_WriteBean> outList = null;
	
	private String filePath = null;

	private String jobKeyPath = null;

	public AIB_CLUSTER_CONTROL_MacroJob(String jobKeyPath)
	{
		this.jobKeyPath = jobKeyPath;
		
		this.filePath = FileUtil.makeDataDir(BasePath.getInstance().getDataPath(), DateTime.getDate(2), this.jobKeyPath);
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
				reader = new AIB_CLUSTER_CONTROL_OracleReader();
				writer = new AIB_CLUSTER_CONTROL_CsvWriter(this.filePath);
				
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
					for (AIB_CLUSTER_CONTROL_ReadBean readBean : inList) {
						AIB_CLUSTER_CONTROL_WriteBean writeBean = new AIB_CLUSTER_CONTROL_WriteBean();

						writeBean.setQafJobBu             (readBean.getQafJobBu             ());
						writeBean.setQafLineCode          (readBean.getQafLineCode          ());
						writeBean.setQafGroupId           (readBean.getQafGroupId           ());
						writeBean.setQafLogicId           (readBean.getQafLogicId           ());
						writeBean.setQafJudgeStep         (readBean.getQafJudgeStep         ());
						writeBean.setQafDefectGroupCode   (readBean.getQafDefectGroupCode   ());
						writeBean.setQafLogicSeq          (readBean.getQafLogicSeq          ());
						writeBean.setQafJudgeCode         (readBean.getQafJudgeCode         ());
						writeBean.setQafExtractDays       (readBean.getQafExtractDays       ());
						writeBean.setQafWorkflowName      (readBean.getQafWorkflowName      ());
						writeBean.setQafLogicStatus       (readBean.getQafLogicStatus       ());
						writeBean.setQafLogicName         (readBean.getQafLogicName         ());
						writeBean.setQafLogicSourceCode   (readBean.getQafLogicSourceCode   ());
						writeBean.setQafLastUpdateUser    (readBean.getQafLastUpdateUser    ());
						writeBean.setQafLastUpdateDatetime(readBean.getQafLastUpdateDatetime());

						outList.add(writeBean);
					}
				}

				if (!Flag.TRUE) {
					for (int i=0; inList != null && i < inList.size(); i++) {
						AIB_CLUSTER_CONTROL_WriteBean writeBean = new AIB_CLUSTER_CONTROL_WriteBean();

						writeBean.setQafJobBu             (inList.get(i).getQafJobBu             ());
						writeBean.setQafLineCode          (inList.get(i).getQafLineCode          ());
						writeBean.setQafGroupId           (inList.get(i).getQafGroupId           ());
						writeBean.setQafLogicId           (inList.get(i).getQafLogicId           ());
						writeBean.setQafJudgeStep         (inList.get(i).getQafJudgeStep         ());
						writeBean.setQafDefectGroupCode   (inList.get(i).getQafDefectGroupCode   ());
						writeBean.setQafLogicSeq          (inList.get(i).getQafLogicSeq          ());
						writeBean.setQafJudgeCode         (inList.get(i).getQafJudgeCode         ());
						writeBean.setQafExtractDays       (inList.get(i).getQafExtractDays       ());
						writeBean.setQafWorkflowName      (inList.get(i).getQafWorkflowName      ());
						writeBean.setQafLogicStatus       (inList.get(i).getQafLogicStatus       ());
						writeBean.setQafLogicName         (inList.get(i).getQafLogicName         ());
						writeBean.setQafLogicSourceCode   (inList.get(i).getQafLogicSourceCode   ());
						writeBean.setQafLastUpdateUser    (inList.get(i).getQafLastUpdateUser    ());
						writeBean.setQafLastUpdateDatetime(inList.get(i).getQafLastUpdateDatetime());

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
				
				writer.writeList();
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
