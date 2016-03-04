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

package kiea.proj.sdc.anal.macro.sample.a12.aib.macro.AIB_INCHCELL_LOC.v01;

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
 * @file_name AIB_INCHCELL_LOC_MacroJob.java
 *
 */
public class AIB_INCHCELL_LOC_MacroJob extends AbstractMacroJob
{
	private AIB_INCHCELL_LOC_OracleReader reader = null;
	private AIB_INCHCELL_LOC_CsvWriter writer = null;
	
	private List<AIB_INCHCELL_LOC_ReadBean> inList = null;
	private List<AIB_INCHCELL_LOC_WriteBean> outList = null;
	
	private String filePath = null;

	private String jobKeyPath = null;

	public AIB_INCHCELL_LOC_MacroJob(String jobKeyPath)
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
				reader = new AIB_INCHCELL_LOC_OracleReader();
				writer = new AIB_INCHCELL_LOC_CsvWriter(this.filePath);
				
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
					for (AIB_INCHCELL_LOC_ReadBean readBean : inList) {
						AIB_INCHCELL_LOC_WriteBean writeBean = new AIB_INCHCELL_LOC_WriteBean();

						writeBean.setSiteId     (readBean.getSiteId     ());
						writeBean.setCellLocId  (readBean.getCellLocId  ());
						writeBean.setProdGrpName(readBean.getProdGrpName());
						writeBean.setInch       (readBean.getInch       ());
						writeBean.setAreaId     (readBean.getAreaId     ());
						writeBean.setPointX     (readBean.getPointX     ());
						writeBean.setPointY     (readBean.getPointY     ());
						writeBean.setColIdx     (readBean.getColIdx     ());
						writeBean.setRowIdx     (readBean.getRowIdx     ());
						writeBean.setWidth      (readBean.getWidth      ());
						writeBean.setHeight     (readBean.getHeight     ());

						outList.add(writeBean);
					}
				}

				if (!Flag.TRUE) {
					for (int i=0; inList != null && i < inList.size(); i++) {
						AIB_INCHCELL_LOC_WriteBean writeBean = new AIB_INCHCELL_LOC_WriteBean();

						writeBean.setSiteId     (inList.get(i).getSiteId     ());
						writeBean.setCellLocId  (inList.get(i).getCellLocId  ());
						writeBean.setProdGrpName(inList.get(i).getProdGrpName());
						writeBean.setInch       (inList.get(i).getInch       ());
						writeBean.setAreaId     (inList.get(i).getAreaId     ());
						writeBean.setPointX     (inList.get(i).getPointX     ());
						writeBean.setPointY     (inList.get(i).getPointY     ());
						writeBean.setColIdx     (inList.get(i).getColIdx     ());
						writeBean.setRowIdx     (inList.get(i).getRowIdx     ());
						writeBean.setWidth      (inList.get(i).getWidth      ());
						writeBean.setHeight     (inList.get(i).getHeight     ());

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
