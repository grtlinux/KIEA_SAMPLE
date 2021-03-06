package sdc.anal.lya.macro.A27.RP_SYS_EVENT_HIST_SUMM.v04;

import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import kiea.kr.co.tain.base.Flag;
import kiea.proj.sdc.anal.macro.impl.job.AbstractMacroJob;
import kiea.proj.sdc.anal.tools.AnalCsv.v04.AnalCsvBean;
import kiea.proj.sdc.anal.tools.AnalCsv.v04.AnalCsvType;
import kiea.proj.sdc.anal.util.BasePath;
import kiea.proj.sdc.anal.util.FileUtil;
import kiea.proj.sdc.anal.util.StrUtil;
import kiea.proj.sdc.anal.util.log.v02.Logger;

public class RP_SYS_EVENT_HIST_SUMM_MacroJob extends AbstractMacroJob
{
	private RP_SYS_EVENT_HIST_DETAIL_CsvIo reader1 = null;
	private RP_SYS_EVENT_HIST_SUMM_CsvIo writer1 = null;
	
	private List<RP_SYS_EVENT_HIST_DETAIL_Bean> inList1  = null;
	private List<RP_SYS_EVENT_HIST_SUMM_Bean> outList1 = null;
	
	private String filePath = null;

	private String jobId = null;

	public RP_SYS_EVENT_HIST_SUMM_MacroJob(String jobId)
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
			RP_SYS_EVENT_HIST_SUMM_Main.runMSec = System.nanoTime();
		}

		if (Flag.TRUE) {
			/*
			 * 기존 자료 삭제
			 */
			AnalCsvBean bean = new AnalCsvBean();

			bean.setType(AnalCsvType.DELETE);
			bean.setTable("ANAL_CSV");
			bean.setWhere(String.format("JOB_ID = %s AND PROG_NM = %s", StrUtil.quote(jobId), StrUtil.quote(RP_SYS_EVENT_HIST_SUMM_Main.dsName)));

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
					{ "CMD_CODE   ", StrUtil.quote(RP_SYS_EVENT_HIST_SUMM_Main.dsName.substring(0, 3)) },
					{ "PROG_NM    ", StrUtil.quote(RP_SYS_EVENT_HIST_SUMM_Main.dsName) },
					{ "CSV_NM     ", StrUtil.quote(RP_SYS_EVENT_HIST_SUMM_Main.dsName + ".csv") },
					{ "MAIN_CLASS ", StrUtil.quote(this.getClass().getName()) },
					{ "S_TIME     ", "SYSDATE" },
					{ "CSV_STATUS ", StrUtil.quote("START") },
			});
			if (Flag.TRUE) Logger.info("[%s]", bean.getQuery());

			bean.sendToOracle();
		}

		if (Flag.TRUE) {
			try {
				reader1 = new RP_SYS_EVENT_HIST_DETAIL_CsvIo(this.filePath);
				writer1 = new RP_SYS_EVENT_HIST_SUMM_CsvIo(this.filePath);
				
				inList1  = reader1.getList(true);
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
				
				Map<String, RP_SYS_EVENT_HIST_SUMM_Bean> map = new LinkedHashMap<String, RP_SYS_EVENT_HIST_SUMM_Bean>();
				Map<String, String> mapSum = new LinkedHashMap<String, String>();
				Map<String, String> mapRank = new LinkedHashMap<String, String>();
				
				if (Flag.TRUE) {
					/*
					 * 자료
					 */
					for (RP_SYS_EVENT_HIST_DETAIL_Bean bean1 : inList1) {
						
						String key = bean1.getClusterId() + ":" + bean1.getStepId() + ":" + bean1.getLayerCode();
						
						RP_SYS_EVENT_HIST_SUMM_Bean bean = map.get(key);
						if (bean == null) {
							bean = new RP_SYS_EVENT_HIST_SUMM_Bean();
							
							bean.setClusterId  (bean1.getClusterId  ());
							bean.setStepId     (bean1.getStepId     ());
							bean.setStepDesc   (bean1.getStepDesc   ());
							bean.setLayerCode  (bean1.getLayerCode  ());
							bean.setRank       ("");
							
							bean.setSum      ("0");
							bean.setRework   ("0");
							bean.setReprocess("0");
							bean.setSpc      ("0");
							bean.setRms      ("0");
							bean.setFdc      ("0");
							
							map.put(key, bean);
						}
						
						bean.addSum();
						
						String code = bean1.getEventOccurCode();
						if ("REWORK".equalsIgnoreCase(code)) {
							bean.addRework();
						} else if ("REPROCESS".equalsIgnoreCase(code)) {
							bean.addReprocess();
						} else if ("SPC".equalsIgnoreCase(code)) {
							bean.addSpc();
						} else if ("RMS".equalsIgnoreCase(code)) {
							bean.addRms();
						} else if ("FDC".equalsIgnoreCase(code)) {
							bean.addFdc();
						}
					}
					
					if (!Flag.TRUE) {
						/*
						 * 확인출력
						 */
						for (Map.Entry<String, RP_SYS_EVENT_HIST_SUMM_Bean> entry : map.entrySet()) {
							System.out.println(entry.getValue());
						}
					}
				}
				
				if (Flag.TRUE) {
					/*
					 * get SUM
					 */
					for (Map.Entry<String, RP_SYS_EVENT_HIST_SUMM_Bean> entry : map.entrySet()) {
						RP_SYS_EVENT_HIST_SUMM_Bean bean = entry.getValue();
						
						outList1.add(bean);
					}
					
					if (!Flag.TRUE) {
						/*
						 * 확인출력
						 */
						for (Map.Entry<String, String> entry : mapSum.entrySet()) {
							System.out.println(entry.getValue());
						}
					}
				}
				
				if (Flag.TRUE) {
					/*
					 * SORT
					 */
					Collections.sort(outList1, new Comparator<RP_SYS_EVENT_HIST_SUMM_Bean>() {
						@Override
						public int compare(RP_SYS_EVENT_HIST_SUMM_Bean bean1, RP_SYS_EVENT_HIST_SUMM_Bean bean2) {
							int ret = 0;
							
							// 1. SUM
							int val = Integer.parseInt(bean2.getSum()) - Integer.parseInt(bean1.getSum());
							if (val < 0)
								return -1;
							else if (val > 0)
								return 1;
									
							return ret;
						}
					});
					
					if (!Flag.TRUE) {
						/*
						 * 확인출력
						 */
						for (RP_SYS_EVENT_HIST_SUMM_Bean bean : outList1) {
							System.out.println(bean);
						}
					}
				}
				
				if (Flag.TRUE) {
					/*
					 * RANK
					 */
					for (int i=0; i < outList1.size(); i++) {
						RP_SYS_EVENT_HIST_SUMM_Bean bean = outList1.get(i);
						
						String rank = mapRank.get(bean.getClusterId());
						if (rank == null) {
							rank = "1";
						} else {
							rank = "" + (Integer.parseInt(rank) + 1);
						}
						mapRank.put(bean.getClusterId(), rank);
						
						bean.setRank(rank);
					}
					
					if (!Flag.TRUE) {
						/*
						 * 확인출력
						 */
						for (RP_SYS_EVENT_HIST_SUMM_Bean bean : outList1) {
							System.out.println(bean);
						}
					}
				}
				
				if (Flag.TRUE) {
					/*
					 * SORT
					 */
					Collections.sort(outList1, new Comparator<RP_SYS_EVENT_HIST_SUMM_Bean>() {
						@Override
						public int compare(RP_SYS_EVENT_HIST_SUMM_Bean bean1, RP_SYS_EVENT_HIST_SUMM_Bean bean2) {
							int ret = 0;
							
							int val;
							
							// 1. CLUSTER_ID
							val = Integer.parseInt(bean1.getClusterId()) - Integer.parseInt(bean2.getClusterId());
							if (val < 0)
								return -1;
							else if (val > 0)
								return 1;
									
							// 2. RANK
							val = Integer.parseInt(bean1.getRank()) - Integer.parseInt(bean2.getRank());
							if (val < 0)
								return -1;
							else if (val > 0)
								return 1;

							return ret;
						}
					});
					
					if (!Flag.TRUE) {
						/*
						 * 확인출력
						 */
						for (RP_SYS_EVENT_HIST_SUMM_Bean bean : outList1) {
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
				
				RP_SYS_EVENT_HIST_SUMM_Main.cntWList = outList1.size();

				writer1.writeList();
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		if (Flag.TRUE) {
			/*
			 * 시간기록 끝
			 */
			RP_SYS_EVENT_HIST_SUMM_Main.runMSec = (System.nanoTime() - RP_SYS_EVENT_HIST_SUMM_Main.runMSec) / 1000 / 1000;
			
			if (Flag.TRUE) Logger.info("##### took time : %d ms", RP_SYS_EVENT_HIST_SUMM_Main.runMSec);
		}

		if (Flag.TRUE) {
			/*
			 * UPDATE
			 */
			AnalCsvBean bean = new AnalCsvBean();
			
			bean.setType(AnalCsvType.UPDATE);
			bean.setTable("ANAL_CSV");
			bean.setFields(new String[][] {
					{ "LIST_CNT   ", "" + RP_SYS_EVENT_HIST_SUMM_Main.cntWList },
					{ "E_TIME     ", "SYSDATE" },
					{ "R_MSEC     ", "" + RP_SYS_EVENT_HIST_SUMM_Main.runMSec },
					{ "CSV_STATUS ", StrUtil.quote("OK") },   // OK, ERROR, SKIP, ETC...
					{ "LOG_MESSAGE", StrUtil.quote("COMPLETE") },  // COMPLETE, EXCEPTION
			});
			bean.setWhere(String.format("JOB_ID = %s AND PROG_NM = %s", StrUtil.quote(jobId), StrUtil.quote(RP_SYS_EVENT_HIST_SUMM_Main.dsName)));
			
			if (Flag.TRUE) Logger.info("[%s]", bean.getQuery());
			
			bean.sendToOracle();
		}
	}
}
