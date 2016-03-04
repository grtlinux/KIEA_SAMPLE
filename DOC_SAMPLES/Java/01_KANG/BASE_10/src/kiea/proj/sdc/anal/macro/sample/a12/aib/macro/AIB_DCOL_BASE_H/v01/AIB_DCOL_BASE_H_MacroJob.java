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

package kiea.proj.sdc.anal.macro.sample.a12.aib.macro.AIB_DCOL_BASE_H.v01;

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
 * @file_name AIB_DCOL_BASE_H_MacroJob.java
 *
 */
public class AIB_DCOL_BASE_H_MacroJob extends AbstractMacroJob
{
	private AIB_DCOL_BASE_H_OracleReader reader = null;
	private AIB_DCOL_BASE_H_CsvWriter writer = null;
	
	private List<AIB_DCOL_BASE_H_ReadBean> inList = null;
	private List<AIB_DCOL_BASE_H_WriteBean> outList = null;
	
	private String filePath = null;

	private String jobKeyPath = null;

	public AIB_DCOL_BASE_H_MacroJob(String jobKeyPath)
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
				reader = new AIB_DCOL_BASE_H_OracleReader();
				writer = new AIB_DCOL_BASE_H_CsvWriter(this.filePath);
				
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
					for (AIB_DCOL_BASE_H_ReadBean readBean : inList) {
						AIB_DCOL_BASE_H_WriteBean writeBean = new AIB_DCOL_BASE_H_WriteBean();

						writeBean.setLineCode (readBean.getLineCode ());
						writeBean.setGlassId  (readBean.getGlassId  ());
						writeBean.setProcessId(readBean.getProcessId());
						writeBean.setProductId(readBean.getProductId());
						writeBean.setStepId   (readBean.getStepId   ());
						writeBean.setEqpId    (readBean.getEqpId    ());
						writeBean.setEqpUnitId(readBean.getEqpUnitId());
						writeBean.setItemId   (readBean.getItemId   ());
						writeBean.setSubItemId(readBean.getSubItemId());
						writeBean.setDataValue(readBean.getDataValue());
						writeBean.setDcolTime (readBean.getDcolTime ());

						outList.add(writeBean);
					}
				}

				if (!Flag.TRUE) {
					for (int i=0; inList != null && i < inList.size(); i++) {
						AIB_DCOL_BASE_H_WriteBean writeBean = new AIB_DCOL_BASE_H_WriteBean();

						writeBean.setLineCode (inList.get(i).getLineCode ());
						writeBean.setGlassId  (inList.get(i).getGlassId  ());
						writeBean.setProcessId(inList.get(i).getProcessId());
						writeBean.setProductId(inList.get(i).getProductId());
						writeBean.setStepId   (inList.get(i).getStepId   ());
						writeBean.setEqpId    (inList.get(i).getEqpId    ());
						writeBean.setEqpUnitId(inList.get(i).getEqpUnitId());
						writeBean.setItemId   (inList.get(i).getItemId   ());
						writeBean.setSubItemId(inList.get(i).getSubItemId());
						writeBean.setDataValue(inList.get(i).getDataValue());
						writeBean.setDcolTime (inList.get(i).getDcolTime ());

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
