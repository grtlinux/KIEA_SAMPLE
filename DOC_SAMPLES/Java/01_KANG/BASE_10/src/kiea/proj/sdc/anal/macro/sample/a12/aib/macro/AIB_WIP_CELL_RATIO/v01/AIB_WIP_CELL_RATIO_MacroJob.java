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

package kiea.proj.sdc.anal.macro.sample.a12.aib.macro.AIB_WIP_CELL_RATIO.v01;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import kiea.kr.co.tain.base.Flag;
import kiea.kr.co.tain.util.DateTime;
import kiea.proj.sdc.anal.common.Parameter;
import kiea.proj.sdc.anal.macro.impl.job.AbstractMacroJob;
import kiea.proj.sdc.anal.util.BasePath;
import kiea.proj.sdc.anal.util.FileUtil;
import kiea.proj.sdc.anal.util.log.v02.Logger;

/**
 * @author KangSeok
 * @date 2014. 8. 8.
 * @file_name AIB_WIP_CELL_RATIO_MacroJob.java
 *
 */
public class AIB_WIP_CELL_RATIO_MacroJob extends AbstractMacroJob
{
	private AIB_WIP_CELL_RATIO_OracleReader reader = null;
	private AIB_WIP_CELL_RATIO_CsvWriter writer = null;
	
	private List<AIB_WIP_CELL_RATIO_ReadBean> inList = null;
	private List<AIB_WIP_CELL_RATIO_WriteBean> outList = null;
	
	private String filePath = null;

	private String jobKeyPath = null;

	public AIB_WIP_CELL_RATIO_MacroJob(String jobKeyPath)
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
				reader = new AIB_WIP_CELL_RATIO_OracleReader();
				writer = new AIB_WIP_CELL_RATIO_CsvWriter(this.filePath);
				
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
				Map<String, AIB_WIP_P_CELL_CNT_Bean> pMap = new LinkedHashMap<String, AIB_WIP_P_CELL_CNT_Bean>();
				Map<String, AIB_WIP_B_CELL_CNT_Bean> bMap = new LinkedHashMap<String, AIB_WIP_B_CELL_CNT_Bean>();
				String strDecisionCode = Parameter.getInstance().getStrDecisionCode();
				
				if (Flag.TRUE) {
					for (AIB_WIP_CELL_RATIO_ReadBean readBean : inList) {

						String key = readBean.getDivCode() + ":" + readBean.getImptStepGrpId() + ":" + readBean.getImptEqpId() + ":" + readBean.getTrckOutHour();

						if (Flag.TRUE) {
							AIB_WIP_P_CELL_CNT_Bean pBean = pMap.get(key);
							if (pBean == null) {
								pBean = new AIB_WIP_P_CELL_CNT_Bean();
								pBean.setDivCode      (readBean.getDivCode      ());
								pBean.setImptStepGrpId(readBean.getImptStepGrpId());
								pBean.setImptEqpId    (readBean.getImptEqpId    ());
								pBean.setTrckInTime   (readBean.getTrckOutHour  ());
								pBean.setCellCnt      ("" + 0);
								
								pMap.put(key, pBean);
							}
							
							pBean.addQty(readBean.getDecQty());
						}
						
						if (!strDecisionCode.equals(readBean.getDecGradeCd()))
							continue;
						
						if (Flag.TRUE) {
							AIB_WIP_B_CELL_CNT_Bean bBean = bMap.get(key);
							if (bBean == null) {
								bBean = new AIB_WIP_B_CELL_CNT_Bean();
								bBean.setDivCode      (readBean.getDivCode      ());
								bBean.setImptStepGrpId(readBean.getImptStepGrpId());
								bBean.setImptEqpId    (readBean.getImptEqpId    ());
								bBean.setTrckInTime   (readBean.getTrckOutHour  ());
								bBean.setBadCellCnt   ("" + 0);
								
								bMap.put(key, bBean);
							}
							
							bBean.addBadQty(readBean.getDecQty());
						}
					}
					
					if (!Flag.TRUE) {
						/*
						 * 확인출력
						 */
						if (Flag.TRUE) {
							List<AIB_WIP_P_CELL_CNT_Bean> lst = new ArrayList<AIB_WIP_P_CELL_CNT_Bean>(pMap.values());
							for (AIB_WIP_P_CELL_CNT_Bean bean : lst) {
								System.out.println(bean);
							}
							System.out.println("List<AIB_WIP_P_CELL_CNT_Bean>.size = " + lst.size());
						}
						
						if (Flag.TRUE) {
							List<AIB_WIP_B_CELL_CNT_Bean> lst = new ArrayList<AIB_WIP_B_CELL_CNT_Bean>(bMap.values());
							for (AIB_WIP_B_CELL_CNT_Bean bean : lst) {
								System.out.println(bean);
							}
							System.out.println("List<AIB_WIP_B_CELL_CNT_Bean>.size = " + lst.size());
						}
					}
				}
				
				if (Flag.TRUE) {
					for (Map.Entry<String, AIB_WIP_P_CELL_CNT_Bean> entry : pMap.entrySet()) {
						String key = entry.getKey();
						AIB_WIP_P_CELL_CNT_Bean pBean = entry.getValue();
						AIB_WIP_B_CELL_CNT_Bean bBean = bMap.get(key);
						
						int badCellCnt = 0;
						double badRatio = 0.0;
						
						if (bBean != null) {
							badCellCnt = Integer.parseInt(bBean.getBadCellCnt());
							badRatio = badCellCnt / Double.parseDouble(pBean.getCellCnt());
						}
						
						AIB_WIP_CELL_RATIO_WriteBean writeBean = new AIB_WIP_CELL_RATIO_WriteBean();
						
						writeBean.setDivCode     (pBean.getDivCode      ());
						writeBean.setStepId      (pBean.getImptStepGrpId());
						writeBean.setEqpId       (pBean.getImptEqpId    ());
						writeBean.setTrckInHour  (pBean.getTrckInTime   ());
						writeBean.setTotCellCnt  (pBean.getCellCnt      ());
						writeBean.setBadCellCnt  ("" + badCellCnt);
						writeBean.setCellBadRatio("" + badRatio  );
						
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
