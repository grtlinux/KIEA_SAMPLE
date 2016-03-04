package sdc.anal.lya.macro.A25.RP_SYS_DEFECT_DETAIL.v04;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import kiea.kr.co.tain.base.Flag;
import kiea.proj.sdc.anal.macro.impl.job.AbstractMacroJob;
import kiea.proj.sdc.anal.tools.AnalCsv.v04.AnalCsvBean;
import kiea.proj.sdc.anal.tools.AnalCsv.v04.AnalCsvType;
import kiea.proj.sdc.anal.util.BasePath;
import kiea.proj.sdc.anal.util.FileUtil;
import kiea.proj.sdc.anal.util.StrUtil;
import kiea.proj.sdc.anal.util.log.v02.Logger;

public class RP_SYS_DEFECT_DETAIL_MacroJob extends AbstractMacroJob
{
	private static final double XDELTA = 19000;
	private static final double YDELTA = 19000;
	
	private SO_SYS_CELLID_LIST2_CsvIo reader1 = null;
	private TFT_INSPCT_HIST_CsvIo reader2 = null;
	private DEFECT_HIST_CsvIo reader3 = null;
	private RP_SYS_DEFECT_DETAIL_CsvIo writer1 = null;
	
	private List<SO_SYS_CELLID_LIST2_Bean> inList1  = null;
	private List<TFT_INSPCT_HIST_Bean> inList2  = null;
	private List<DEFECT_HIST_Bean> inList3  = null;
	private List<RP_SYS_DEFECT_DETAIL_Bean> outList1 = null;
	
	private String filePath = null;

	private String jobId = null;

	public RP_SYS_DEFECT_DETAIL_MacroJob(String jobId)
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
			RP_SYS_DEFECT_DETAIL_Main.runMSec = System.nanoTime();
		}

		if (Flag.TRUE) {
			/*
			 * 기존 자료 삭제
			 */
			AnalCsvBean bean = new AnalCsvBean();

			bean.setType(AnalCsvType.DELETE);
			bean.setTable("ANAL_CSV");
			bean.setWhere(String.format("JOB_ID = %s AND PROG_NM = %s", StrUtil.quote(jobId), StrUtil.quote(RP_SYS_DEFECT_DETAIL_Main.dsName)));

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
					{ "CMD_CODE   ", StrUtil.quote(RP_SYS_DEFECT_DETAIL_Main.dsName.substring(0, 3)) },
					{ "PROG_NM    ", StrUtil.quote(RP_SYS_DEFECT_DETAIL_Main.dsName) },
					{ "CSV_NM     ", StrUtil.quote(RP_SYS_DEFECT_DETAIL_Main.dsName + ".csv") },
					{ "MAIN_CLASS ", StrUtil.quote(this.getClass().getName()) },
					{ "S_TIME     ", "SYSDATE" },
					{ "CSV_STATUS ", StrUtil.quote("START") },
			});
			if (Flag.TRUE) Logger.info("[%s]", bean.getQuery());

			bean.sendToOracle();
		}

		if (Flag.TRUE) {
			try {
				reader1 = new SO_SYS_CELLID_LIST2_CsvIo(this.filePath);
				reader2 = new TFT_INSPCT_HIST_CsvIo(this.filePath);
				reader3 = new DEFECT_HIST_CsvIo(this.filePath);
				writer1 = new RP_SYS_DEFECT_DETAIL_CsvIo(this.filePath);
				
				inList1  = reader1.getList(true);
				inList2  = reader2.getList(true);
				inList3  = reader3.getList(true);
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
				List<SO_SYS_CELLID_LIST2_Bean> lstMatch = new ArrayList<SO_SYS_CELLID_LIST2_Bean>();
				
				if (Flag.TRUE) {
					/*
					 * lstMatch 를 얻는다.
					 */
					for (SO_SYS_CELLID_LIST2_Bean bean1 : inList1) {
						if ("BAD".equals(bean1.getGroup())) {
							lstMatch.add(bean1);
						}
					}
				}
				
				if (Flag.TRUE) {
					/*
					 * TFT_INSPCT_HIST를 STEPID로 SORT한다.
					 */
					Collections.sort(inList2, new Comparator<TFT_INSPCT_HIST_Bean>() {
						@Override
						public int compare(TFT_INSPCT_HIST_Bean bean1, TFT_INSPCT_HIST_Bean bean2) {
							int ret = 0;
							
							// 1. StepId
							ret = bean1.getStepId().compareTo(bean2.getStepId());
							if (ret != 0) return ret;
									
							return ret;
						}
					});
				}
				
				if (Flag.TRUE) {
					
					for (TFT_INSPCT_HIST_Bean bean2 : inList2) {
						
						String defectSeq2 = bean2.getDefectSeq();
						double x2 = Double.parseDouble(bean2.getXValue());
						double y2 = Double.parseDouble(bean2.getYValue());
						
						for (int i=0; i < lstMatch.size(); i++) {
							
							/*
							 * DEFECT_SEQ 가 일치하고
							 * | x2 - x1 | < XDELTA
							 * | y2 - y1 | < YDELTA
							 * 이면 같은 자료라고 생각함.
							 */
							String defectSeq1 = lstMatch.get(i).getDefectSeq();
							double x1 = Double.parseDouble(lstMatch.get(i).getXValue());
							double y1 = Double.parseDouble(lstMatch.get(i).getYValue());
							
							if (defectSeq1.equals(defectSeq2)
									&& x2 - XDELTA < x1 && x1 < x2 + XDELTA 
									&& y2 - YDELTA < y1 && y1 < y2 + YDELTA) {
								
								RP_SYS_DEFECT_DETAIL_Bean bean = new RP_SYS_DEFECT_DETAIL_Bean();
								
								bean.setClusterId  (lstMatch.get(i).getClusterId  ());
								bean.setLineCode   (bean2.getLineCode   ());
								bean.setAreaCode   (bean2.getAreaCode   ());
								bean.setSubAreaCode(bean2.getSubAreaCode());
								bean.setEqpType    (bean2.getEqpType    ());
								bean.setStepId     (bean2.getStepId().substring(0, 1) + "__" + bean2.getStepId().substring(3));
								bean.setGlassId    (bean2.getGlassId    ());
								bean.setCellId     (bean2.getCellId     ());
								bean.setDefectSeq  (bean2.getDefectSeq  ());
								bean.setXValue     (bean2.getXValue     ());
								bean.setYValue     (bean2.getYValue     ());
								
								lstMatch.remove(i);
								
								outList1.add(bean);
								break;
							}
						}
					}
					
					if (!Flag.TRUE) {
						/*
						 * 확인출력
						 */
						for (RP_SYS_DEFECT_DETAIL_Bean bean2 : outList1) {
							System.out.println(bean2);
						}
					}
				}
				
				if (Flag.TRUE) {
					/*
					 * DATE : 2014.12.12 : SAS 소스 분석후 추가
					 */
					for (DEFECT_HIST_Bean bean3 : inList3) {
						
						String defectSeq3 = bean3.getDefectSeq();
						double x3 = Double.parseDouble(bean3.getXValue());
						double y3 = Double.parseDouble(bean3.getYValue());
						
						for (int i=0; i < lstMatch.size(); i++) {
							
							/*
							 * DEFECT_SEQ 가 일치하고
							 * | x2 - x1 | < XDELTA
							 * | y2 - y1 | < YDELTA
							 * 이면 같은 자료라고 생각함.
							 */
							String defectSeq1 = lstMatch.get(i).getDefectSeq();
							double x1 = Double.parseDouble(lstMatch.get(i).getXValue());
							double y1 = Double.parseDouble(lstMatch.get(i).getYValue());
							
							if (defectSeq1.equals(defectSeq3)
									&& x3 - XDELTA < x1 && x1 < x3 + XDELTA 
									&& y3 - YDELTA < y1 && y1 < y3 + YDELTA) {
								
								RP_SYS_DEFECT_DETAIL_Bean bean = new RP_SYS_DEFECT_DETAIL_Bean();
								
								bean.setClusterId  (lstMatch.get(i).getClusterId  ());
								bean.setLineCode   (bean3.getLineCode   ());
								bean.setAreaCode   (bean3.getAreaCode   ());
								bean.setSubAreaCode(bean3.getSubAreaCode());
								bean.setEqpType    ("");
								bean.setStepId     (bean3.getStepId().substring(0, 1) + "__" + bean3.getStepId().substring(3));
								bean.setGlassId    (bean3.getGlassId    ());
								bean.setCellId     (bean3.getCellId     ());
								bean.setDefectSeq  (bean3.getDefectSeq  ());
								bean.setXValue     (bean3.getXValue     ());
								bean.setYValue     (bean3.getYValue     ());
								
								lstMatch.remove(i);
								
								outList1.add(bean);
								break;
							}
						}
					}
					
					if (!Flag.TRUE) {
						/*
						 * 확인출력
						 */
						for (RP_SYS_DEFECT_DETAIL_Bean bean2 : outList1) {
							System.out.println(bean2);
						}
					}
				}
				
				if (Flag.TRUE) {
					/*
					 * SORT
					 */
					Collections.sort(outList1, new Comparator<RP_SYS_DEFECT_DETAIL_Bean>() {
						@Override
						public int compare(RP_SYS_DEFECT_DETAIL_Bean bean1, RP_SYS_DEFECT_DETAIL_Bean bean2) {
							int ret = 0;
							
							// 1. CLUSTER_ID
							ret = bean1.getClusterId().compareTo(bean2.getClusterId());
							if (ret != 0) return ret;

							// 2. SubAreaCode
							ret = bean1.getSubAreaCode().compareTo(bean2.getSubAreaCode());
							if (ret != 0) return ret;
							
							// 3. StepId
							ret = bean1.getStepId().compareTo(bean2.getStepId());
							if (ret != 0) return ret;
							
							// 4. GlassId
							ret = bean1.getGlassId().compareTo(bean2.getGlassId());
							if (ret != 0) return ret;
									
							return ret;
						}
					});
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
				
				RP_SYS_DEFECT_DETAIL_Main.cntWList = outList1.size();

				writer1.writeList();
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		if (Flag.TRUE) {
			/*
			 * 시간기록 끝
			 */
			RP_SYS_DEFECT_DETAIL_Main.runMSec = (System.nanoTime() - RP_SYS_DEFECT_DETAIL_Main.runMSec) / 1000 / 1000;
			
			if (Flag.TRUE) Logger.info("##### took time : %d ms", RP_SYS_DEFECT_DETAIL_Main.runMSec);
		}

		if (Flag.TRUE) {
			/*
			 * UPDATE
			 */
			AnalCsvBean bean = new AnalCsvBean();
			
			bean.setType(AnalCsvType.UPDATE);
			bean.setTable("ANAL_CSV");
			bean.setFields(new String[][] {
					{ "LIST_CNT   ", "" + RP_SYS_DEFECT_DETAIL_Main.cntWList },
					{ "E_TIME     ", "SYSDATE" },
					{ "R_MSEC     ", "" + RP_SYS_DEFECT_DETAIL_Main.runMSec },
					{ "CSV_STATUS ", StrUtil.quote("OK") },   // OK, ERROR, SKIP, ETC...
					{ "LOG_MESSAGE", StrUtil.quote("COMPLETE") },  // COMPLETE, EXCEPTION
			});
			bean.setWhere(String.format("JOB_ID = %s AND PROG_NM = %s", StrUtil.quote(jobId), StrUtil.quote(RP_SYS_DEFECT_DETAIL_Main.dsName)));
			
			if (Flag.TRUE) Logger.info("[%s]", bean.getQuery());
			
			bean.sendToOracle();
		}
	}
}
