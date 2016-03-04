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

package kiea.proj.sdc.anal.macro.sample.a11.mod.job;

import java.util.List;

import kiea.kr.co.tain.base.Flag;
import kiea.kr.co.tain.util.DateTime;
import kiea.proj.sdc.anal.common.FileValue;
import kiea.proj.sdc.anal.macro.impl.job.AbstractMacroJob;
import kiea.proj.sdc.anal.macro.sample.a11.mod.bean.MOD_WORKID_ReadBean;
import kiea.proj.sdc.anal.macro.sample.a11.mod.bean.MOD_WORKID_WriteBean;
import kiea.proj.sdc.anal.macro.sample.a11.mod.io.MOD_WORKID_CsvWriter;
import kiea.proj.sdc.anal.macro.sample.a11.mod.io.MOD_WORKID_OracleReader;
import kiea.proj.sdc.anal.util.BasePath;
import kiea.proj.sdc.anal.util.FileUtil;
import kiea.proj.sdc.anal.util.log.v02.Logger;

/**
 * @author KangSeok
 * @date 2014. 8. 8.
 * @file_name MOD_WORKID_MacroJob.java
 *
 */
public class MOD_WORKID_MacroJob extends AbstractMacroJob
{
	private MOD_WORKID_OracleReader reader = null;
	private MOD_WORKID_CsvWriter writer = null;
	
	private List<MOD_WORKID_ReadBean> inList = null;
	private List<MOD_WORKID_WriteBean> outList = null;
	
	private String filePath = null;

	private String jobKeyPath = null;
	private String dsName = null;

	public MOD_WORKID_MacroJob(String jobKeyPath, String dsName)
	{
		this.jobKeyPath = jobKeyPath;
		this.dsName = dsName;
		
		/*
		 * 생성 CSV 파일명
		 * TODO : 2014.08.08 : 나중에 IN/OUT 형태로 바꿈.
		 */
		this.dsName = "MOD_WORKID";
		
		this.filePath = FileUtil.makeDataDir(BasePath.getInstance().getDataPath(), DateTime.getDate(2), this.jobKeyPath);
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
				reader = new MOD_WORKID_OracleReader();
				//writer = new MOD_WORKID_CsvWriter(this.filePath + FileValue.SEP + this.dsName + "Write.csv");
				writer = new MOD_WORKID_CsvWriter(this.filePath + FileValue.SEP + this.dsName + ".csv");
				
				inList = reader.getList();
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
					for (MOD_WORKID_ReadBean readBean : inList) {
						MOD_WORKID_WriteBean writeBean = new MOD_WORKID_WriteBean();

						writeBean.setInspStepType(readBean.getInspStepType());
						writeBean.setInspEqpId   (readBean.getInspEqpId   ());
						writeBean.setWorkerId    (readBean.getWorkerId    ());
						writeBean.setTot         (readBean.getTot         ());
						writeBean.setBad         (readBean.getBad         ());

						outList.add(writeBean);
					}
				}

				if (!Flag.TRUE) {
					for (int i=0; inList != null && i < inList.size(); i++) {
						MOD_WORKID_WriteBean writeBean = new MOD_WORKID_WriteBean();

						writeBean.setInspStepType(inList.get(i).getInspStepType());
						writeBean.setInspEqpId   (inList.get(i).getInspEqpId   ());
						writeBean.setWorkerId    (inList.get(i).getWorkerId    ());
						writeBean.setTot         (inList.get(i).getTot         ());
						writeBean.setBad         (inList.get(i).getBad         ());

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
