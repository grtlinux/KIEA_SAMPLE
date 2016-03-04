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

package kiea.proj.sdc.anal.macro.sample.a03.job;

import java.util.List;

import kiea.kr.co.tain.base.Flag;
import kiea.kr.co.tain.util.DateTime;
import kiea.proj.sdc.anal.common.FileValue;
import kiea.proj.sdc.anal.macro.impl.job.AbstractMacroJob;
import kiea.proj.sdc.anal.macro.sample.a03.bean.Sample03ReadBean;
import kiea.proj.sdc.anal.macro.sample.a03.bean.Sample03WriteBean;
import kiea.proj.sdc.anal.macro.sample.a03.io.Sample03CSVWriter;
import kiea.proj.sdc.anal.macro.sample.a03.io.Sample03OracleReader;
import kiea.proj.sdc.anal.util.BasePath;
import kiea.proj.sdc.anal.util.FileUtil;
import kiea.proj.sdc.anal.util.log.v02.Logger;

/**
 * @author KangSeok
 * @date 2014. 8. 7.
 * @file_name Sample03MacroJob.java
 *
 */
public class Sample03MacroJob extends AbstractMacroJob
{
	private Sample03OracleReader reader = null;
	private Sample03CSVWriter writer = null;
	
	private List<Sample03ReadBean> inList = null;
	private List<Sample03WriteBean> outList = null;
	
	private String filePath = null;

	private String jobKeyPath = null;
	private String dsName = null;

	public Sample03MacroJob(String jobKeyPath, String dsName)
	{
		this.jobKeyPath = jobKeyPath;
		this.dsName = dsName;
		
		this.filePath = FileUtil.makeDataDir(BasePath.getInstance().getDataPath(), DateTime.getDate(2), this.jobKeyPath);
	}
	
	/**
	 * generateDataSet
	 */
	public void generateDataSet() {}

	/**
	 * beforeMacroJob
	 */
	public void beforeMacroJob()
	{
		if (Flag.TRUE) Logger.info("beforeMacroJob : " + this.getClass());
		
		if (Flag.TRUE) {
			try {
				reader = new Sample03OracleReader();
				writer = new Sample03CSVWriter(this.filePath + FileValue.SEP + this.dsName + "Write.csv");
				
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
					for (Sample03ReadBean readBean : inList) {
						Sample03WriteBean writeBean = new Sample03WriteBean();
						
						writeBean.setJobId        (readBean.getJobId        ());
						writeBean.setAnalEventType(readBean.getAnalEventType());
						writeBean.setLineId       (readBean.getLineId       ());
						writeBean.setInspectArea  (readBean.getInspectArea  ());
						writeBean.setInspectStep  (readBean.getInspectStep  ());
						writeBean.setFromDate     (readBean.getFromDate     ());
						writeBean.setToDate       (readBean.getToDate       ());
						writeBean.setProductGrp   (readBean.getProductGrp   ());
						writeBean.setProductType  (readBean.getProductType  ());
						writeBean.setFailCd       (readBean.getFailCd       ());
						writeBean.setDecisionCd   (readBean.getDecisionCd   ());

						outList.add(writeBean);
					}
				}
				
				if (!Flag.TRUE) {
					for (int i=0; inList != null && i < inList.size(); i++) {
						Sample03WriteBean writeBean = new Sample03WriteBean();

						writeBean.setJobId        (inList.get(i).getJobId        ());
						writeBean.setAnalEventType(inList.get(i).getAnalEventType());
						writeBean.setLineId       (inList.get(i).getLineId       ());
						writeBean.setInspectArea  (inList.get(i).getInspectArea  ());
						writeBean.setInspectStep  (inList.get(i).getInspectStep  ());
						writeBean.setFromDate     (inList.get(i).getFromDate     ());
						writeBean.setToDate       (inList.get(i).getToDate       ());
						writeBean.setProductGrp   (inList.get(i).getProductGrp   ());
						writeBean.setProductType  (inList.get(i).getProductType  ());
						writeBean.setFailCd       (inList.get(i).getFailCd       ());
						writeBean.setDecisionCd   (inList.get(i).getDecisionCd   ());
						
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
