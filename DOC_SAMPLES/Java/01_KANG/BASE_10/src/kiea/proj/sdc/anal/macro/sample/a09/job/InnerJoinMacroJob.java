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

package kiea.proj.sdc.anal.macro.sample.a09.job;

import java.util.List;

import kiea.kr.co.tain.base.Flag;
import kiea.kr.co.tain.util.DateTime;
import kiea.kr.co.tain.util.Print;
import kiea.proj.sdc.anal.common.FileValue;
import kiea.proj.sdc.anal.macro.impl.job.AbstractMacroJob;
import kiea.proj.sdc.anal.macro.sample.a09.bean.BUBBLE_IDX_ReadBean;
import kiea.proj.sdc.anal.macro.sample.a09.bean.MURA_DESC_ReadBean;
import kiea.proj.sdc.anal.macro.sample.a09.bean.MURA_RAWDATA_JOINOUT_WriteBean;
import kiea.proj.sdc.anal.macro.sample.a09.bean.MURA_RAWDATA_ReadBean;
import kiea.proj.sdc.anal.macro.sample.a09.io.BUBBLE_IDX_CsvReader;
import kiea.proj.sdc.anal.macro.sample.a09.io.MURA_DESC_CsvReader;
import kiea.proj.sdc.anal.macro.sample.a09.io.MURA_RAWDATA_CsvReader;
import kiea.proj.sdc.anal.macro.sample.a09.io.MURA_RAWDATA_JOINOUT_CsvWriter;
import kiea.proj.sdc.anal.util.BasePath;
import kiea.proj.sdc.anal.util.FileUtil;
import kiea.proj.sdc.anal.util.log.v02.Logger;

/**
 * @author KangSeok
 * @date 2014. 8. 14.
 * @file_name InnerJoinMacroJob.java
 *
 */
public class InnerJoinMacroJob extends AbstractMacroJob
{
	private MURA_RAWDATA_CsvReader muraRawDataReader = null;
	private MURA_DESC_CsvReader    muraDescReader    = null;
	private BUBBLE_IDX_CsvReader   bubbleIdxReader   = null;
	
	private List<MURA_RAWDATA_ReadBean> muraRawDataList = null;
	//private List<MURA_DESC_ReadBean>    muraDescList    = null;
	//private List<BUBBLE_IDX_ReadBean>   bubbleIdxList   = null;
	
	private MURA_RAWDATA_JOINOUT_CsvWriter writer = null;
	private List<MURA_RAWDATA_JOINOUT_WriteBean> outList = null;

	private String filePath = null;
	private String jobKeyPath = null;
	
	private String dsName = null;
	
	private String muraRawDataName = null;
	private String muraDescName    = null;
	private String bubbleIdxName   = null;

	public InnerJoinMacroJob(String jobKeyPath, String dsName)
	{
		this.jobKeyPath = jobKeyPath;
		this.dsName = dsName;
		
		/*
		 * 생성 CSV 파일명
		 * TODO : 2014.08.08 : 나중에 IN/OUT 형태로 바꿈.
		 */
		this.muraRawDataName = "MURA_RAWDATA";
		this.muraDescName    = "MURA_DESC";
		this.bubbleIdxName   = "BUBBLE_IDX";
		
		this.dsName          = "MURA_RAWDATA_JOINOUT";
		
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
				muraRawDataReader = new MURA_RAWDATA_CsvReader(this.filePath + FileValue.SEP + this.muraRawDataName + ".csv");
				muraDescReader    = new MURA_DESC_CsvReader   (this.filePath + FileValue.SEP + this.muraDescName    + ".csv");
				bubbleIdxReader   = new BUBBLE_IDX_CsvReader  (this.filePath + FileValue.SEP + this.bubbleIdxName   + ".csv");
				
				muraRawDataList = muraRawDataReader.getList();
				//muraDescList    = muraDescReader.getList();
				//bubbleIdxList   = bubbleIdxReader.getList();

				writer = new MURA_RAWDATA_JOINOUT_CsvWriter(this.filePath + FileValue.SEP + this.dsName + ".csv");
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
				
				if (!Flag.TRUE) {
					int num = 0;
					for (MURA_RAWDATA_ReadBean readBean : muraRawDataList) {
						Print.println("[%3d]", ++num);
						
						Print.println("\t PROCID        = [%s]", readBean.getProcId        ());
						Print.println("\t SITEID        = [%s]", readBean.getSiteId        ());
						Print.println("\t CELLID        = [%s]", readBean.getCellId        ());
						Print.println("\t GLASSID       = [%s]", readBean.getGlassId       ());
						Print.println("\t PRODGRPNAME   = [%s]", readBean.getProdGrpName   ());
						Print.println("\t PRODID        = [%s]", readBean.getProdId        ());
						Print.println("\t ORGSTEPID     = [%s]", readBean.getOrgStepId     ());
						Print.println("\t MEASEQPUNITID = [%s]", readBean.getMeasEqpUnitId ());
						Print.println("\t DCOLTIME      = [%s]", readBean.getDcolTime      ());
						Print.println("\t ITEMID        = [%s]", readBean.getItemId        ());
						Print.println("\t SUBITEMID     = [%s]", readBean.getSubItemId     ());
						Print.println("\t DEFECTNAME    = [%s]", readBean.getDefectName    ());
						Print.println("\t DATAVALUE     = [%s]", readBean.getDataValue     ());
						Print.println("\t GATELINE      = [%s]", readBean.getGateLine      ());
						Print.println("\t DATALINE      = [%s]", readBean.getDataLine      ());
						Print.println("\t GATELINE2     = [%s]", readBean.getGateLine2     ());
						Print.println("\t DATALINE2     = [%s]", readBean.getDataLine2     ());
						Print.println("\t PRODTYPE      = [%s]", readBean.getProdType      ());
						Print.println("\t CELL_LOC_ID   = [%s]", readBean.getCellLocId     ());
						Print.println("\t WIDTH         = [%s]", readBean.getWidth         ());
						Print.println("\t HEIGHT        = [%s]", readBean.getHeight        ());
						Print.println("\t X_VALUE       = [%s]", readBean.getXValue        ());
						Print.println("\t Y_VALUE       = [%s]", readBean.getYValue        ());
						Print.println("\t X2_VALUE      = [%s]", readBean.getX2Value       ());
						Print.println("\t Y2_VALUE      = [%s]", readBean.getY2Value       ());
						
						MURA_DESC_ReadBean muraDescBean = muraDescReader.get(readBean.getItemId());
						
						Print.println("\t\t ITEMID      = [%s]", muraDescBean.getItemId    ());
						Print.println("\t\t ITEMNAME    = [%s]", muraDescBean.getItemName  ());
						
						BUBBLE_IDX_ReadBean bubbleIdxBean = bubbleIdxReader.get(readBean.getProcId(), readBean.getCellLocId());
						
						Print.println("\t\t PROCID      = [%s]", bubbleIdxBean.getProcId   ());
						Print.println("\t\t CELLLOCID   = [%s]", bubbleIdxBean.getCellLocId());
						Print.println("\t\t COLIDX      = [%s]", bubbleIdxBean.getColIdx   ());
						Print.println("\t\t ROWIDX      = [%s]", bubbleIdxBean.getRowIdx   ());
						Print.println("\t\t POINT_X     = [%s]", bubbleIdxBean.getPointX   ());
						Print.println("\t\t POINT_Y     = [%s]", bubbleIdxBean.getPointY   ());
						
						Print.println("--------------------------------------------------------------");
					}
				}

				if (!Flag.TRUE) {
					/*
					 * Job
					 * 1. InBean -> OutBean
					 */
					for (MURA_RAWDATA_ReadBean readBean : muraRawDataList) {

						MURA_DESC_ReadBean muraDescBean = muraDescReader.get(readBean.getItemId());
						BUBBLE_IDX_ReadBean bubbleIdxBean = bubbleIdxReader.get(readBean.getProcId(), readBean.getCellLocId());

						MURA_RAWDATA_JOINOUT_WriteBean writeBean = new MURA_RAWDATA_JOINOUT_WriteBean();
						
						writeBean.setProcId       (readBean.getProcId       ());
						writeBean.setSiteId       (readBean.getSiteId       ());
						writeBean.setCellId       (readBean.getCellId       ());
						writeBean.setGlassId      (readBean.getGlassId      ());
						writeBean.setProdGrpName  (readBean.getProdGrpName  ());
						writeBean.setProdId       (readBean.getProdId       ());
						writeBean.setOrgStepId    (readBean.getOrgStepId    ());
						writeBean.setMeasEqpUnitId(readBean.getMeasEqpUnitId());
						writeBean.setDcolTime     (readBean.getDcolTime     ());
						writeBean.setItemId       (readBean.getItemId       ());
						writeBean.setSubItemId    (readBean.getSubItemId    ());
						writeBean.setDefectName   (readBean.getDefectName   ());
						writeBean.setDataValue    (readBean.getDataValue    ());
						writeBean.setGateLine     (readBean.getGateLine     ());
						writeBean.setDataLine     (readBean.getDataLine     ());
						writeBean.setGateLine2    (readBean.getGateLine2    ());
						writeBean.setDataLine2    (readBean.getDataLine2    ());
						writeBean.setProdType     (readBean.getProdType     ());
						writeBean.setCellLocId    (readBean.getCellLocId    ());
						writeBean.setWidth        (readBean.getWidth        ());
						writeBean.setHeight       (readBean.getHeight       ());
						writeBean.setXValue       (readBean.getXValue       ());
						writeBean.setYValue       (readBean.getYValue       ());
						writeBean.setX2Value      (readBean.getX2Value      ());
						writeBean.setY2Value      (readBean.getY2Value      ());

						writeBean.setItemName     (muraDescBean.getItemName  ());
						
						writeBean.setColIdx       (bubbleIdxBean.getColIdx   ());
						writeBean.setRowIdx       (bubbleIdxBean.getRowIdx   ());
						writeBean.setPointX       (bubbleIdxBean.getPointX   ());
						writeBean.setPointY       (bubbleIdxBean.getPointY   ());

						outList.add(writeBean);
					}
				}

				if (Flag.TRUE) {
					/*
					 * Job
					 * 1. InBean -> OutBean
					 */
					for (int i=0; muraRawDataList != null && i < muraRawDataList.size(); i++) {
						MURA_DESC_ReadBean muraDescBean = muraDescReader.get(muraRawDataList.get(i).getItemId());
						BUBBLE_IDX_ReadBean bubbleIdxBean = bubbleIdxReader.get(muraRawDataList.get(i).getProcId(), muraRawDataList.get(i).getCellLocId());

						MURA_RAWDATA_JOINOUT_WriteBean writeBean = new MURA_RAWDATA_JOINOUT_WriteBean();

						writeBean.setProcId       (muraRawDataList.get(i).getProcId       ());
						writeBean.setSiteId       (muraRawDataList.get(i).getSiteId       ());
						writeBean.setCellId       (muraRawDataList.get(i).getCellId       ());
						writeBean.setGlassId      (muraRawDataList.get(i).getGlassId      ());
						writeBean.setProdGrpName  (muraRawDataList.get(i).getProdGrpName  ());
						writeBean.setProdId       (muraRawDataList.get(i).getProdId       ());
						writeBean.setOrgStepId    (muraRawDataList.get(i).getOrgStepId    ());
						writeBean.setMeasEqpUnitId(muraRawDataList.get(i).getMeasEqpUnitId());
						writeBean.setDcolTime     (muraRawDataList.get(i).getDcolTime     ());
						writeBean.setItemId       (muraRawDataList.get(i).getItemId       ());
						writeBean.setSubItemId    (muraRawDataList.get(i).getSubItemId    ());
						writeBean.setDefectName   (muraRawDataList.get(i).getDefectName   ());
						writeBean.setDataValue    (muraRawDataList.get(i).getDataValue    ());
						writeBean.setGateLine     (muraRawDataList.get(i).getGateLine     ());
						writeBean.setDataLine     (muraRawDataList.get(i).getDataLine     ());
						writeBean.setGateLine2    (muraRawDataList.get(i).getGateLine2    ());
						writeBean.setDataLine2    (muraRawDataList.get(i).getDataLine2    ());
						writeBean.setProdType     (muraRawDataList.get(i).getProdType     ());
						writeBean.setCellLocId    (muraRawDataList.get(i).getCellLocId    ());
						writeBean.setWidth        (muraRawDataList.get(i).getWidth        ());
						writeBean.setHeight       (muraRawDataList.get(i).getHeight       ());
						writeBean.setXValue       (muraRawDataList.get(i).getXValue       ());
						writeBean.setYValue       (muraRawDataList.get(i).getYValue       ());
						writeBean.setX2Value      (muraRawDataList.get(i).getX2Value      ());
						writeBean.setY2Value      (muraRawDataList.get(i).getY2Value      ());

						writeBean.setItemName     (muraDescBean.getItemName  ());

						writeBean.setColIdx       (bubbleIdxBean.getColIdx   ());
						writeBean.setRowIdx       (bubbleIdxBean.getRowIdx   ());
						writeBean.setPointX       (bubbleIdxBean.getPointX   ());
						writeBean.setPointY       (bubbleIdxBean.getPointY   ());

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
