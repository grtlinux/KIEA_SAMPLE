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

package kiea.proj.sdc.anal.macro.sample.a08.job;

import java.util.List;

import kiea.kr.co.tain.base.Flag;
import kiea.kr.co.tain.util.DateTime;
import kiea.proj.sdc.anal.common.FileValue;
import kiea.proj.sdc.anal.macro.impl.job.AbstractMacroJob;
import kiea.proj.sdc.anal.macro.sample.a08.bean.BUBBLE_IDX_ReadBean;
import kiea.proj.sdc.anal.macro.sample.a08.bean.BUBBLE_IDX_WriteBean;
import kiea.proj.sdc.anal.macro.sample.a08.io.BUBBLE_IDX_CsvReader;
import kiea.proj.sdc.anal.macro.sample.a08.io.BUBBLE_IDX_CsvWriter;
import kiea.proj.sdc.anal.util.BasePath;
import kiea.proj.sdc.anal.util.FileUtil;
import kiea.proj.sdc.anal.util.log.v02.Logger;

/**
 * @author KangSeok
 * @date 2014. 8. 8.
 * @file_name BUBBLE_IDX_MacroJob.java
 *
 */
public class BUBBLE_IDX_MacroJob extends AbstractMacroJob
{
	private BUBBLE_IDX_CsvReader reader = null;
	private BUBBLE_IDX_CsvWriter writer = null;
	
	private List<BUBBLE_IDX_ReadBean> inList = null;
	private List<BUBBLE_IDX_WriteBean> outList = null;
	
	private String filePath = null;

	private String jobKeyPath = null;
	private String dsName = null;

	public BUBBLE_IDX_MacroJob(String jobKeyPath, String dsName)
	{
		this.jobKeyPath = jobKeyPath;
		this.dsName = dsName;
		
		/*
		 * 생성 CSV 파일명
		 * TODO : 2014.08.08 : 나중에 IN/OUT 형태로 바꿈.
		 */
		this.dsName = "BUBBLE_IDX";
		
		this.filePath = FileUtil.makeDataDir(BasePath.getInstance().getDataPath(), DateTime.getDate(2), this.jobKeyPath);
	}
	
	/**
	 * generateDataSet
	 */
	public void generateDataSet()
	{
	}

	/**
	 * beforeMacroJob
	 */
	public void beforeMacroJob()
	{
		if (Flag.TRUE) Logger.info("beforeMacroJob : " + this.getClass());
		
		if (Flag.TRUE) {
			try {
				reader = new BUBBLE_IDX_CsvReader(this.filePath + FileValue.SEP + this.dsName + ".csv");
				writer = new BUBBLE_IDX_CsvWriter(this.filePath + FileValue.SEP + this.dsName + "2.csv");
				
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
					for (BUBBLE_IDX_ReadBean readBean : inList) {
						BUBBLE_IDX_WriteBean writeBean = new BUBBLE_IDX_WriteBean();

						writeBean.setProcId   (readBean.getProcId   ());
						writeBean.setCellLocId(readBean.getCellLocId());
						writeBean.setColIdx   (readBean.getColIdx   ());
						writeBean.setRowIdx   (readBean.getRowIdx   ());
						writeBean.setPointX   (readBean.getPointX   ());
						writeBean.setPointY   (readBean.getPointY   ());

						outList.add(writeBean);
					}
				}

				if (!Flag.TRUE) {
					for (int i=0; inList != null && i < inList.size(); i++) {
						BUBBLE_IDX_WriteBean writeBean = new BUBBLE_IDX_WriteBean();

						writeBean.setProcId   (inList.get(i).getProcId   ());
						writeBean.setCellLocId(inList.get(i).getCellLocId());
						writeBean.setColIdx   (inList.get(i).getColIdx   ());
						writeBean.setRowIdx   (inList.get(i).getRowIdx   ());
						writeBean.setPointX   (inList.get(i).getPointX   ());
						writeBean.setPointY   (inList.get(i).getPointY   ());

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
