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

package sdc.anal.mura.macro.A22.CONTACT_MAP.v01;

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
 * @file_name CONTACT_MAP_MacroJob.java
 *
 */
public class CONTACT_MAP_MacroJob extends AbstractMacroJob
{
	private CONTACT_MAP_OracleReader reader = null;
	private CONTACT_MAP_CsvWriter writer = null;
	
	private List<CONTACT_MAP_ReadBean> inList = null;
	private List<CONTACT_MAP_WriteBean> outList = null;
	
	private String filePath = null;

	private String jobId = null;

	public CONTACT_MAP_MacroJob(String jobId)
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
				reader = new CONTACT_MAP_OracleReader();
				//writer = new CONTACT_MAP_CsvWriter(this.filePath + FileValue.SEP + this.dsName + "Write.csv");
				writer = new CONTACT_MAP_CsvWriter(this.filePath);
				
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
					for (CONTACT_MAP_ReadBean readBean : inList) {
						
						String part = readBean.getPart();
						String contactMap = readBean.getContactMap();
						
						if ("SPT".equals(part) && ("ANODE".equals(contactMap) || "TARGET".equals(contactMap)))
							continue;
						
						CONTACT_MAP_WriteBean writeBean = new CONTACT_MAP_WriteBean();

						writeBean.setEqpName        (readBean.getEqpName        ());
						writeBean.setLine           (readBean.getLine           ());
						writeBean.setAreaId         (readBean.getAreaId         ());
						writeBean.setPart           (readBean.getPart           ());
						writeBean.setMaker          (readBean.getMaker          ());
						writeBean.setUnitName       (readBean.getUnitName       ());
						writeBean.setContactMap     (readBean.getContactMap     ());
						writeBean.setCoordX1        ("" + (Double.parseDouble(readBean.getCoordX1()) * 1000));
						writeBean.setCoordY1        ("" + (Double.parseDouble(readBean.getCoordY1()) * 1000));
						writeBean.setCoordX2        ("" + (Double.parseDouble(readBean.getCoordX2()) * 1000));
						writeBean.setCoordY2        ("" + (Double.parseDouble(readBean.getCoordY2()) * 1000));
						writeBean.setMaterial       (readBean.getMaterial       ());
						writeBean.setType           (readBean.getType           ());
						writeBean.setVersion        (readBean.getVersion        ());
						writeBean.setContactMapAttr1(readBean.getContactMapAttr1());
						writeBean.setContactMapAttr2(readBean.getContactMapAttr2());
						writeBean.setUserId         (readBean.getUserId         ());
						writeBean.setUpdateTime     (readBean.getUpdateTime     ());

						outList.add(writeBean);
					}
				}

				if (!Flag.TRUE) {
					for (int i=0; inList != null && i < inList.size(); i++) {
						
						String part = inList.get(i).getPart();
						String contactMap = inList.get(i).getContactMap();
						
						if ("SPT".equals(part) && ("ANODE".equals(contactMap) || "TARGET".equals(contactMap)))
							continue;
						
						CONTACT_MAP_WriteBean writeBean = new CONTACT_MAP_WriteBean();

						writeBean.setEqpName        (inList.get(i).getEqpName        ());
						writeBean.setLine           (inList.get(i).getLine           ());
						writeBean.setAreaId         (inList.get(i).getAreaId         ());
						writeBean.setPart           (inList.get(i).getPart           ());
						writeBean.setMaker          (inList.get(i).getMaker          ());
						writeBean.setUnitName       (inList.get(i).getUnitName       ());
						writeBean.setContactMap     (inList.get(i).getContactMap     ());
						writeBean.setCoordX1        ("" + (Double.parseDouble(inList.get(i).getCoordX1()) * 1000));
						writeBean.setCoordY1        ("" + (Double.parseDouble(inList.get(i).getCoordY1()) * 1000));
						writeBean.setCoordX2        ("" + (Double.parseDouble(inList.get(i).getCoordX2()) * 1000));
						writeBean.setCoordY2        ("" + (Double.parseDouble(inList.get(i).getCoordY2()) * 1000));
						writeBean.setMaterial       (inList.get(i).getMaterial       ());
						writeBean.setType           (inList.get(i).getType           ());
						writeBean.setVersion        (inList.get(i).getVersion        ());
						writeBean.setContactMapAttr1(inList.get(i).getContactMapAttr1());
						writeBean.setContactMapAttr2(inList.get(i).getContactMapAttr2());
						writeBean.setUserId         (inList.get(i).getUserId         ());
						writeBean.setUpdateTime     (inList.get(i).getUpdateTime     ());

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
				
				CONTACT_MAP_Main.cntWList = outList.size();

				writer.writeList();

				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
