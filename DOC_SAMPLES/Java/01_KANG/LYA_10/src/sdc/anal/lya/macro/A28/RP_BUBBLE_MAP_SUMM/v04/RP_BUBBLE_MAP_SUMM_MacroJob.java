package sdc.anal.lya.macro.A28.RP_BUBBLE_MAP_SUMM.v04;

import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import kiea.kr.co.tain.base.Flag;
import kiea.kr.co.tain.util.Print;
import kiea.proj.sdc.anal.common.Parameter;
import kiea.proj.sdc.anal.macro.impl.job.AbstractMacroJob;
import kiea.proj.sdc.anal.tools.AnalCsv.v04.AnalCsvBean;
import kiea.proj.sdc.anal.tools.AnalCsv.v04.AnalCsvType;
import kiea.proj.sdc.anal.util.BasePath;
import kiea.proj.sdc.anal.util.FileUtil;
import kiea.proj.sdc.anal.util.StrUtil;
import kiea.proj.sdc.anal.util.log.v02.Logger;

public class RP_BUBBLE_MAP_SUMM_MacroJob extends AbstractMacroJob
{
	private INSPCT_HIST_CsvIo reader1 = null;
	private CELLCONVERT_CsvIo reader2 = null;
	private RP_BUBBLE_MAP_SUMM_CsvIo writer1 = null;
	
	private List<INSPCT_HIST_Bean> inList1  = null;
	private List<CELLCONVERT_Bean> inList2  = null;
	private List<RP_BUBBLE_MAP_SUMM_Bean> outList1 = null;
	
	private String filePath = null;

	private String jobId = null;

	public RP_BUBBLE_MAP_SUMM_MacroJob(String jobId)
	{
		this.jobId = jobId;
		
		this.filePath = FileUtil.makeDataDir(BasePath.getInstance().getDataPath(), StrUtil.getDateFromJobId(this.jobId), this.jobId);
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
			/*
			 * 시간기록 시작
			 */
			RP_BUBBLE_MAP_SUMM_Main.runMSec = System.nanoTime();
		}

		if (Flag.TRUE) {
			/*
			 * 기존 자료 삭제
			 */
			AnalCsvBean bean = new AnalCsvBean();

			bean.setType(AnalCsvType.DELETE);
			bean.setTable("ANAL_CSV");
			bean.setWhere(String.format("JOB_ID = %s AND PROG_NM = %s", StrUtil.quote(jobId), StrUtil.quote(RP_BUBBLE_MAP_SUMM_Main.dsName)));

			bean.sendToOracle();

			if (Flag.TRUE) Logger.info("[%s]", bean.getQuery());
		}

		if (Flag.TRUE) {
			/*
			 * INSERT
			 */
			AnalCsvBean bean = new AnalCsvBean();

			bean.setType(AnalCsvType.INSERT);
			bean.setTable("ANAL_CSV");
			bean.setFields(new String[][] {
					{ "JOB_ID     ", StrUtil.quote(jobId) },
					{ "CMD_CODE   ", StrUtil.quote(RP_BUBBLE_MAP_SUMM_Main.dsName.substring(0, 3)) },
					{ "PROG_NM    ", StrUtil.quote(RP_BUBBLE_MAP_SUMM_Main.dsName) },
					{ "CSV_NM     ", StrUtil.quote(RP_BUBBLE_MAP_SUMM_Main.dsName + ".csv") },
					{ "MAIN_CLASS ", StrUtil.quote(this.getClass().getName()) },
					{ "S_TIME     ", "SYSDATE" },
					{ "CSV_STATUS ", StrUtil.quote("START") },
			});
			if (Flag.TRUE) Logger.info("[%s]", bean.getQuery());

			bean.sendToOracle();
		}

		if (Flag.TRUE) {
			try {
				reader1 = new INSPCT_HIST_CsvIo(this.filePath);
				reader2 = new CELLCONVERT_CsvIo(this.filePath);
				writer1 = new RP_BUBBLE_MAP_SUMM_CsvIo(this.filePath);
				
				inList1  = reader1.getList(true);
				inList2  = reader2.getList(true);
				outList1 = writer1.getList();
				
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
				 */
				Map<String, CELLCONVERT_Bean> mapCell = new LinkedHashMap<String, CELLCONVERT_Bean>();
				Map<String, RP_BUBBLE_MAP_SUMM_Bean> mapProcId = new LinkedHashMap<String, RP_BUBBLE_MAP_SUMM_Bean>();
				
				if (Flag.TRUE) {
					/*
					 * 기초자료
					 */
					for (CELLCONVERT_Bean inBean2 : inList2) {
						mapCell.put(inBean2.getProcId() + ":" + inBean2.getCellLocId(), inBean2);
					}
					
					if (!Flag.TRUE) {
						/*
						 * 확인출력
						 */
						for (Map.Entry<String, CELLCONVERT_Bean> entry : mapCell.entrySet()) {
							System.out.println(entry.getValue());
						}
					}
				}
				
				if (Flag.TRUE) {
					/*
					 * 누적
					 */
					String paramDecisionCode = StrUtil.quote(Parameter.getInstance().getStrDecisionCode());
					String paramDefectGroupCode = StrUtil.quote(Parameter.getInstance().getStrDefectGroupCode());
					if (!Flag.TRUE) Print.println("[Decision:%s] [Defect:%s]", paramDecisionCode, paramDefectGroupCode);
					
					for (INSPCT_HIST_Bean inBean1 : inList1) {
						
						String procId = inBean1.getProcessId();
						procId = procId.substring(0, procId.length() - 1);
						String cellLocId = inBean1.getCellLocId();
						RP_BUBBLE_MAP_SUMM_Bean bean = mapProcId.get(cellLocId);
						if (bean == null) {
							bean = new RP_BUBBLE_MAP_SUMM_Bean();
							
							bean.setQafJobId    (RP_BUBBLE_MAP_SUMM_Main.jobId);
							bean.setCellLocId   (cellLocId);
							bean.setTotCellCnt  ("0");
							bean.setBadCellCnt  ("0");
							bean.setCellBadRatio("0");
							
							bean.setProdGrpName(inBean1.getUserGroupCode());
							bean.setAreaId     (inBean1.getAreaCode     ());
							
							CELLCONVERT_Bean cellBean = mapCell.get(procId + ":" + cellLocId);
							if (cellBean != null) {
								bean.setPointX(cellBean.getPointX());
								bean.setPointY(cellBean.getPointY());
								bean.setColIdx(cellBean.getColIdx());
								bean.setRowIdx(cellBean.getRowIdx());
								bean.setWidth (cellBean.getWidth ());
								bean.setHeight(cellBean.getHeight());
							} else {
								bean.setPointX("");
								bean.setPointY("");
								bean.setColIdx("");
								bean.setRowIdx("");
								bean.setWidth ("");
								bean.setHeight("");
							}

							mapProcId.put(cellLocId, bean);
						}
						
						if (paramDecisionCode.indexOf(StrUtil.quote(inBean1.getDecisionCode())) >= 0
						&& paramDefectGroupCode.indexOf(StrUtil.quote(inBean1.getDefectGroupCode())) >= 0) {
							bean.addBadCellCnt();
						}
						bean.addTotCellCnt();
					}
					
					if (!Flag.TRUE) {
						/*
						 * 확인출력
						 */
						for (Map.Entry<String, RP_BUBBLE_MAP_SUMM_Bean> entry : mapProcId.entrySet()) {
							System.out.println(entry.getValue());
						}
					}
				}
				
				if (Flag.TRUE) {
					/*
					 * BadCellRatio 계산
					 * outList1
					 */
					for (Map.Entry<String, RP_BUBBLE_MAP_SUMM_Bean> entry : mapProcId.entrySet()) {
						RP_BUBBLE_MAP_SUMM_Bean bean = entry.getValue();
						double ratio = Double.parseDouble(bean.getBadCellCnt()) * 100 / Double.parseDouble(bean.getTotCellCnt());
						bean.setCellBadRatio("" + ratio);
						
						outList1.add(bean);
					}
					
					if (!Flag.TRUE) {
						for (RP_BUBBLE_MAP_SUMM_Bean bean : outList1) {
							System.out.println(bean);
						}
					}
				}
				
				if (Flag.TRUE) {
					/*
					 * SORT
					 */
					Collections.sort(outList1, new Comparator<RP_BUBBLE_MAP_SUMM_Bean>() {
						@Override
						public int compare(RP_BUBBLE_MAP_SUMM_Bean bean1, RP_BUBBLE_MAP_SUMM_Bean bean2) {
							int ret = 0;
							
							// 1. CLUSTER_ID
							ret = bean1.getCellLocId().compareTo(bean2.getCellLocId());
							if (ret != 0) return ret;

							return ret;
						}
					});
					
					if (!Flag.TRUE) {
						for (RP_BUBBLE_MAP_SUMM_Bean bean : outList1) {
							System.out.println(bean);
						}
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
				
				RP_BUBBLE_MAP_SUMM_Main.cntWList = outList1.size();

				writer1.writeList();
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		if (Flag.TRUE) {
			/*
			 * 시간기록 끝
			 */
			RP_BUBBLE_MAP_SUMM_Main.runMSec = (System.nanoTime() - RP_BUBBLE_MAP_SUMM_Main.runMSec) / 1000 / 1000;
			
			if (Flag.TRUE) Logger.info("##### took time : %d ms", RP_BUBBLE_MAP_SUMM_Main.runMSec);
		}

		if (Flag.TRUE) {
			/*
			 * UPDATE
			 */
			AnalCsvBean bean = new AnalCsvBean();
			
			bean.setType(AnalCsvType.UPDATE);
			bean.setTable("ANAL_CSV");
			bean.setFields(new String[][] {
					{ "LIST_CNT   ", "" + RP_BUBBLE_MAP_SUMM_Main.cntWList },
					{ "E_TIME     ", "SYSDATE" },
					{ "R_MSEC     ", "" + RP_BUBBLE_MAP_SUMM_Main.runMSec },
					{ "CSV_STATUS ", StrUtil.quote("OK") },   // OK, ERROR, SKIP, ETC...
					{ "LOG_MESSAGE", StrUtil.quote("COMPLETE") },  // COMPLETE, EXCEPTION
			});
			bean.setWhere(String.format("JOB_ID = %s AND PROG_NM = %s", StrUtil.quote(jobId), StrUtil.quote(RP_BUBBLE_MAP_SUMM_Main.dsName)));
			
			if (Flag.TRUE) Logger.info("[%s]", bean.getQuery());
			
			bean.sendToOracle();
		}
	}
}
