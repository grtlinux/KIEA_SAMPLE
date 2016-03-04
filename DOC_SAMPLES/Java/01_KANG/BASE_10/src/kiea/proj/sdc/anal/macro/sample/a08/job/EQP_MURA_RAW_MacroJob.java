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
import kiea.proj.sdc.anal.macro.sample.a08.bean.EQP_MURA_RAW_ReadBean;
import kiea.proj.sdc.anal.macro.sample.a08.bean.EQP_MURA_RAW_WriteBean;
import kiea.proj.sdc.anal.macro.sample.a08.io.EQP_MURA_RAW_CsvReader;
import kiea.proj.sdc.anal.macro.sample.a08.io.EQP_MURA_RAW_CsvWriter;
import kiea.proj.sdc.anal.util.BasePath;
import kiea.proj.sdc.anal.util.FileUtil;
import kiea.proj.sdc.anal.util.log.v02.Logger;

/**
 * @author KangSeok
 * @date 2014. 8. 8.
 * @file_name EQP_MURA_RAW_MacroJob.java
 *
 */
public class EQP_MURA_RAW_MacroJob extends AbstractMacroJob
{
	private EQP_MURA_RAW_CsvReader reader = null;
	private EQP_MURA_RAW_CsvWriter writer = null;
	
	private List<EQP_MURA_RAW_ReadBean> inList = null;
	private List<EQP_MURA_RAW_WriteBean> outList = null;
	
	private String filePath = null;

	private String jobKeyPath = null;
	private String dsName = null;

	public EQP_MURA_RAW_MacroJob(String jobKeyPath, String dsName)
	{
		this.jobKeyPath = jobKeyPath;
		this.dsName = dsName;
		
		/*
		 * 생성 CSV 파일명
		 * TODO : 2014.08.08 : 나중에 IN/OUT 형태로 바꿈.
		 */
		this.dsName = "EQP_MURA_RAW";
		
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
				reader = new EQP_MURA_RAW_CsvReader(this.filePath + FileValue.SEP + this.dsName + ".csv");
				writer = new EQP_MURA_RAW_CsvWriter(this.filePath + FileValue.SEP + this.dsName + "2.csv");
				
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
					for (EQP_MURA_RAW_ReadBean readBean : inList) {
						EQP_MURA_RAW_WriteBean writeBean = new EQP_MURA_RAW_WriteBean();

						writeBean.setSiteId       (readBean.getSiteId       ());
						writeBean.setSiteIdEqpType(readBean.getSiteIdEqpType());
						writeBean.setCompMasterKey(readBean.getCompMasterKey());
						writeBean.setGlassId      (readBean.getGlassId      ());
						writeBean.setProdId       (readBean.getProdId       ());
						writeBean.setProcId       (readBean.getProcId       ());
						writeBean.setOrgStepId    (readBean.getOrgStepId    ());
						writeBean.setMeasStepGrpId(readBean.getMeasStepGrpId());
						writeBean.setMeasEqpUnitId(readBean.getMeasEqpUnitId());
						writeBean.setDcolDate     (readBean.getDcolDate     ());
						writeBean.setItemId       (readBean.getItemId       ());
						writeBean.setSubItemId    (readBean.getSubItemId    ());
						writeBean.setDataValue    (readBean.getDataValue    ());
						writeBean.setCellLocId    (readBean.getCellLocId    ());
						writeBean.setUsl          (readBean.getUsl          ());
						writeBean.setLsl          (readBean.getLsl          ());
						writeBean.setTarget       (readBean.getTarget       ());
						writeBean.setCodeX        (readBean.getCodeX        ());
						writeBean.setCodeY        (readBean.getCodeY        ());
						writeBean.setProdType     (readBean.getProdType     ());
						writeBean.setCodeX2       (readBean.getCodeX2       ());
						writeBean.setCodeY2       (readBean.getCodeY2       ());
						writeBean.setEtlModifyDate(readBean.getEtlModifyDate());
						writeBean.setShotNo       (readBean.getShotNo       ());
						writeBean.setImgFile      (readBean.getImgFile      ());

						outList.add(writeBean);
					}
				}

				if (!Flag.TRUE) {
					for (int i=0; inList != null && i < inList.size(); i++) {
						EQP_MURA_RAW_WriteBean writeBean = new EQP_MURA_RAW_WriteBean();

						writeBean.setSiteId       (inList.get(i).getSiteId       ());
						writeBean.setSiteIdEqpType(inList.get(i).getSiteIdEqpType());
						writeBean.setCompMasterKey(inList.get(i).getCompMasterKey());
						writeBean.setGlassId      (inList.get(i).getGlassId      ());
						writeBean.setProdId       (inList.get(i).getProdId       ());
						writeBean.setProcId       (inList.get(i).getProcId       ());
						writeBean.setOrgStepId    (inList.get(i).getOrgStepId    ());
						writeBean.setMeasStepGrpId(inList.get(i).getMeasStepGrpId());
						writeBean.setMeasEqpUnitId(inList.get(i).getMeasEqpUnitId());
						writeBean.setDcolDate     (inList.get(i).getDcolDate     ());
						writeBean.setItemId       (inList.get(i).getItemId       ());
						writeBean.setSubItemId    (inList.get(i).getSubItemId    ());
						writeBean.setDataValue    (inList.get(i).getDataValue    ());
						writeBean.setCellLocId    (inList.get(i).getCellLocId    ());
						writeBean.setUsl          (inList.get(i).getUsl          ());
						writeBean.setLsl          (inList.get(i).getLsl          ());
						writeBean.setTarget       (inList.get(i).getTarget       ());
						writeBean.setCodeX        (inList.get(i).getCodeX        ());
						writeBean.setCodeY        (inList.get(i).getCodeY        ());
						writeBean.setProdType     (inList.get(i).getProdType     ());
						writeBean.setCodeX2       (inList.get(i).getCodeX2       ());
						writeBean.setCodeY2       (inList.get(i).getCodeY2       ());
						writeBean.setEtlModifyDate(inList.get(i).getEtlModifyDate());
						writeBean.setShotNo       (inList.get(i).getShotNo       ());
						writeBean.setImgFile      (inList.get(i).getImgFile      ());

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
