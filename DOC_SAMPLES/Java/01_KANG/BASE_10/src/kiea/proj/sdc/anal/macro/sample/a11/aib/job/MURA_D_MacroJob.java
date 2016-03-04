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

package kiea.proj.sdc.anal.macro.sample.a11.aib.job;

import java.util.List;

import kiea.kr.co.tain.base.Flag;
import kiea.kr.co.tain.util.DateTime;
import kiea.proj.sdc.anal.common.FileValue;
import kiea.proj.sdc.anal.common.Parameter;
import kiea.proj.sdc.anal.macro.impl.job.AbstractMacroJob;
import kiea.proj.sdc.anal.macro.sample.a11.aib.bean.MURA_D_ReadBean;
import kiea.proj.sdc.anal.macro.sample.a11.aib.bean.MURA_D_WriteBean;
import kiea.proj.sdc.anal.macro.sample.a11.aib.io.MURA_D_CsvWriter;
import kiea.proj.sdc.anal.macro.sample.a11.aib.io.MURA_D_OracleReader;
import kiea.proj.sdc.anal.util.BasePath;
import kiea.proj.sdc.anal.util.FileUtil;
import kiea.proj.sdc.anal.util.log.v02.Logger;

/**
 * @author KangSeok
 * @date 2014. 8. 8.
 * @file_name MURA_D_MacroJob.java
 *
 */
public class MURA_D_MacroJob extends AbstractMacroJob
{
	private MURA_D_OracleReader reader = null;
	private MURA_D_CsvWriter writer = null;
	
	private List<MURA_D_ReadBean> inList = null;
	private List<MURA_D_WriteBean> outList = null;
	
	private String strFromDateTime    = null;
	private String strToDateTime      = null;
	private String strLine            = null;
	private String strAreaCode        = null;
	private String strUserGroupCode   = null;
	private String strDefectGroupCode = null;
	
	private String filePath = null;

	private String jobKeyPath = null;
	private String dsName = null;

	public MURA_D_MacroJob(String jobKeyPath, String dsName)
	{
		this.jobKeyPath = jobKeyPath;
		this.dsName = dsName;
		
		/*
		 * 생성 CSV 파일명
		 * TODO : 2014.08.08 : 나중에 IN/OUT 형태로 바꿈.
		 */
		this.dsName = "MURA_D";
		
		this.filePath = FileUtil.makeDataDir(BasePath.getInstance().getDataPath(), DateTime.getDate(2), this.jobKeyPath);
	}
	
	/**
	 * generateDataSet
	 */
	public void generateDataSet()
	{
		if (!Flag.TRUE) {
			/*
			 * L5FAB, L6FAB - LC                            : 8초
			 */
			this.strFromDateTime    = "20140805090000";
			this.strToDateTime      = "20140806085959";
			this.strLine            = "L6FAB";
			this.strAreaCode        = "LC";
			this.strUserGroupCode   = "L6156H11";
			this.strDefectGroupCode = "POR";
		}

		if (!Flag.TRUE) {
			/*
			 * L5FAB, L6FAB - MOD                          : 80초
			 */
			this.strFromDateTime    = "20140701110000";
			this.strToDateTime      = "20140702105959";
			this.strLine            = "L6FAB";
			this.strAreaCode        = "MOD";
			this.strUserGroupCode   = "LTL097QL01-A01";
			this.strDefectGroupCode = "32PR";
		}

		if (!Flag.TRUE) {
			/*
			 * L7AFAB, L7BFAB, L8AFAB, L8ZFAB - LC         : 6초
			 */
			this.strFromDateTime    = "20140702110000";
			this.strToDateTime      = "20140703105959";
			this.strLine            = "L8AFAB";
			this.strAreaCode        = "LC";
			this.strUserGroupCode   = "L8480F71";
			this.strDefectGroupCode = "32PR";
		}

		if (!Flag.TRUE) {
			/*
			 * L7AFAB, L7BFAB, L8AFAB, L8ZFAB - MOD        : 2초
			 */
			this.strFromDateTime    = "20140702070000";
			this.strToDateTime      = "20140703065959";
			this.strLine            = "L8AFAB";
			this.strAreaCode        = "MOD";
			this.strUserGroupCode   = "LSC480HN02-G01";
			this.strDefectGroupCode = "32BOR";
		}

		if (Flag.TRUE) {
			/*
			 * Parameter
			 */
			this.strFromDateTime    = Parameter.getInstance().getStrFromDateTime   ();
			this.strToDateTime      = Parameter.getInstance().getStrToDateTime     ();
			this.strLine            = Parameter.getInstance().getStrLine           ();
			this.strAreaCode        = Parameter.getInstance().getStrAreaCode       ();
			this.strUserGroupCode   = Parameter.getInstance().getStrUserGroupCode  ();
			this.strDefectGroupCode = Parameter.getInstance().getStrDefectGroupCode();
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
				reader = new MURA_D_OracleReader(strFromDateTime, strToDateTime, strLine, strAreaCode, strUserGroupCode, strDefectGroupCode);
				//writer = new MURA_D_CsvWriter(this.filePath + FileValue.SEP + this.dsName + "Write.csv");
				writer = new MURA_D_CsvWriter(this.filePath + FileValue.SEP + this.dsName + ".csv");
				
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
					for (MURA_D_ReadBean readBean : inList) {
						MURA_D_WriteBean writeBean = new MURA_D_WriteBean();

						writeBean.setProcId       (readBean.getProcId       ());
						writeBean.setSiteId       (readBean.getSiteId       ());
						writeBean.setCellId       (readBean.getCellId       ());
						writeBean.setGlassId      (readBean.getGlassId      ());
						writeBean.setProdRrpName  (readBean.getProdRrpName  ());
						writeBean.setProdId       (readBean.getProdId       ());
						writeBean.setOrgStepId    (readBean.getOrgStepId    ());
						writeBean.setMeasEqpUnitId(readBean.getMeasEqpUnitId());
						writeBean.setDcolDate     (readBean.getDcolDate     ());
						writeBean.setItemId       (readBean.getItemId       ());
						writeBean.setSubItemId    (readBean.getSubItemId    ());
						writeBean.setDataValue    (readBean.getDataValue    ());
						writeBean.setGateLine     (readBean.getGateLine     ());
						writeBean.setDataLine     (readBean.getDataLine     ());
						writeBean.setGateLine2    (readBean.getGateLine2    ());
						writeBean.setDataLine2    (readBean.getDataLine2    ());
						writeBean.setProdType     (readBean.getProdType     ());
						writeBean.setCellLocId    (readBean.getCellLocId    ());

						outList.add(writeBean);
					}
				}

				if (!Flag.TRUE) {
					for (int i=0; inList != null && i < inList.size(); i++) {
						MURA_D_WriteBean writeBean = new MURA_D_WriteBean();

						writeBean.setProcId       (inList.get(i).getProcId       ());
						writeBean.setSiteId       (inList.get(i).getSiteId       ());
						writeBean.setCellId       (inList.get(i).getCellId       ());
						writeBean.setGlassId      (inList.get(i).getGlassId      ());
						writeBean.setProdRrpName  (inList.get(i).getProdRrpName  ());
						writeBean.setProdId       (inList.get(i).getProdId       ());
						writeBean.setOrgStepId    (inList.get(i).getOrgStepId    ());
						writeBean.setMeasEqpUnitId(inList.get(i).getMeasEqpUnitId());
						writeBean.setDcolDate     (inList.get(i).getDcolDate     ());
						writeBean.setItemId       (inList.get(i).getItemId       ());
						writeBean.setSubItemId    (inList.get(i).getSubItemId    ());
						writeBean.setDataValue    (inList.get(i).getDataValue    ());
						writeBean.setGateLine     (inList.get(i).getGateLine     ());
						writeBean.setDataLine     (inList.get(i).getDataLine     ());
						writeBean.setGateLine2    (inList.get(i).getGateLine2    ());
						writeBean.setDataLine2    (inList.get(i).getDataLine2    ());
						writeBean.setProdType     (inList.get(i).getProdType     ());
						writeBean.setCellLocId    (inList.get(i).getCellLocId    ());

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
