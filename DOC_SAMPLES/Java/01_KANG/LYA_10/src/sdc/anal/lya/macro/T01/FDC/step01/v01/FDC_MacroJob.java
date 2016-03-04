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

package sdc.anal.lya.macro.T01.FDC.step01.v01;

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
public class FDC_MacroJob extends AbstractMacroJob
{
	private FDC_OracleReader reader = null;
	private FDC_CsvWriter writer = null;
	
	private List<FDC_ReadBean> inList = null;
	private List<FDC_WriteBean> outList = null;
	
	private String filePath = null;

	private String jobId = null;

	public FDC_MacroJob(String jobId)
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
				reader = new FDC_OracleReader();
				writer = new FDC_CsvWriter(this.filePath);
				
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
					for (FDC_ReadBean readBean : inList) {
						FDC_WriteBean writeBean = new FDC_WriteBean();

						writeBean.setLine       (readBean.getLine       ());
						writeBean.setPart       (readBean.getPart       ());
						writeBean.setEqpModel   (readBean.getEqpModel   ());
						writeBean.setEqpId      (readBean.getEqpId      ());
						writeBean.setModuleName (readBean.getModuleName ());
						writeBean.setProcId     (readBean.getProcId     ());
						writeBean.setProdId     (readBean.getProdId     ());
						writeBean.setPpId       (readBean.getPpId       ());
						writeBean.setGlassId    (readBean.getGlassId    ());
						writeBean.setProcessStep(readBean.getProcessStep());
						writeBean.setBeginStep  (readBean.getBeginStep  ());
						writeBean.setSensorName (readBean.getSensorName ());
						writeBean.setParam      (readBean.getParam      ());
						writeBean.setParamValue (readBean.getParamValue ());
						writeBean.setUsl        (readBean.getUsl        ());
						writeBean.setLsl        (readBean.getLsl        ());
						writeBean.setBeginTime  (readBean.getBeginTime  ());

						outList.add(writeBean);
					}
				}

				if (!Flag.TRUE) {
					for (int i=0; inList != null && i < inList.size(); i++) {
						FDC_WriteBean writeBean = new FDC_WriteBean();

						writeBean.setLine       (inList.get(i).getLine       ());
						writeBean.setPart       (inList.get(i).getPart       ());
						writeBean.setEqpModel   (inList.get(i).getEqpModel   ());
						writeBean.setEqpId      (inList.get(i).getEqpId      ());
						writeBean.setModuleName (inList.get(i).getModuleName ());
						writeBean.setProcId     (inList.get(i).getProcId     ());
						writeBean.setProdId     (inList.get(i).getProdId     ());
						writeBean.setPpId       (inList.get(i).getPpId       ());
						writeBean.setGlassId    (inList.get(i).getGlassId    ());
						writeBean.setProcessStep(inList.get(i).getProcessStep());
						writeBean.setBeginStep  (inList.get(i).getBeginStep  ());
						writeBean.setSensorName (inList.get(i).getSensorName ());
						writeBean.setParam      (inList.get(i).getParam      ());
						writeBean.setParamValue (inList.get(i).getParamValue ());
						writeBean.setUsl        (inList.get(i).getUsl        ());
						writeBean.setLsl        (inList.get(i).getLsl        ());
						writeBean.setBeginTime  (inList.get(i).getBeginTime  ());

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
				
				FDC_Main.cntWList = outList.size();

				writer.writeList();

				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
