package sdc.anal.lya.macro.A26.DELAY_TIME03.v04;

import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
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

public class DELAY_TIME03_MacroJob extends AbstractMacroJob
{
	private DELAY_TIME01_CsvIo reader1 = null;
	private DELAY_TIME03_CsvIo writer1 = null;
	
	private List<DELAY_TIME01_Bean> inList1  = null;
	private List<DELAY_TIME03_Bean> outList1 = null;
	
	private String filePath = null;

	private String jobId = null;

	public DELAY_TIME03_MacroJob(String jobId)
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
			DELAY_TIME03_Main.runMSec = System.nanoTime();
		}

		if (Flag.TRUE) {
			/*
			 * 기존 자료 삭제
			 */
			AnalCsvBean bean = new AnalCsvBean();

			bean.setType(AnalCsvType.DELETE);
			bean.setTable("ANAL_CSV");
			bean.setWhere(String.format("JOB_ID = %s AND PROG_NM = %s", StrUtil.quote(jobId), StrUtil.quote(DELAY_TIME03_Main.dsName)));

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
					{ "CMD_CODE   ", StrUtil.quote(DELAY_TIME03_Main.dsName.substring(0, 3)) },
					{ "PROG_NM    ", StrUtil.quote(DELAY_TIME03_Main.dsName) },
					{ "CSV_NM     ", StrUtil.quote(DELAY_TIME03_Main.dsName + ".csv") },
					{ "MAIN_CLASS ", StrUtil.quote(this.getClass().getName()) },
					{ "S_TIME     ", "SYSDATE" },
					{ "CSV_STATUS ", StrUtil.quote("START") },
			});
			if (Flag.TRUE) Logger.info("[%s]", bean.getQuery());

			bean.sendToOracle();
		}

		if (Flag.TRUE) {
			try {
				reader1 = new DELAY_TIME01_CsvIo(this.filePath);
				writer1 = new DELAY_TIME03_CsvIo(this.filePath);
				
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
				/*
				 * Job
				 */
				Map<String, DELAY_TIME03_Bean> mapDelay = new LinkedHashMap<String, DELAY_TIME03_Bean>();
				
				if (Flag.TRUE) {
					/*
					 * DELAY_TIME01 자료를 읽어 CLUSTER_ID, STEP_NAME 별로 BAD/GOOD 끼리 누적한다.
					 */
					for (DELAY_TIME01_Bean bean1 : inList1) {
						String key = bean1.getClusterId() + ":" + bean1.getStepName();
						
						DELAY_TIME03_Bean bean3 = mapDelay.get(key);
						if (bean3 == null) {
							bean3 = new DELAY_TIME03_Bean();
							
							bean3.setClusterId(bean1.getClusterId());
							bean3.setStepName(bean1.getStepName());
							bean3.setStepDesc(bean1.getStepDesc());
							bean3.setBadCnt("0");
							bean3.setGoodCnt("0");
							bean3.setBadSum("0");
							bean3.setGoodSum("0");
							
							mapDelay.put(key, bean3);
						}
						
						if ("BAD".equals(bean1.getGroup())) {
							bean3.incBadCnt();
							bean3.addBadSum(bean1.getStepValue());
						} else {
							bean3.incGoodCnt();
							bean3.addGoodSum(bean1.getStepValue());
						}
					}
					
					if (!Flag.TRUE) {
						/*
						 * 확인출력
						 */
						for (Map.Entry<String, DELAY_TIME03_Bean> entry : mapDelay.entrySet()) {
							System.out.println(">" + entry.getValue());
						}
					}
				}
				
				if (Flag.TRUE) {
					/*
					 * 연산작업을 한다. BAD_AVG, GOOD_AVG, BG_AVG 값을 얻는다.
					 */
					for (DELAY_TIME03_Bean bean : mapDelay.values()) {
						
						double badCnt = Double.parseDouble(bean.getBadCnt());
						double badSum = Double.parseDouble(bean.getBadSum());
						double goodCnt = Double.parseDouble(bean.getGoodCnt());
						double goodSum = Double.parseDouble(bean.getGoodSum());
						double badAvg = badCnt != 0 ? badSum / badCnt : 0;
						double goodAvg = goodCnt != 0 ? goodSum / goodCnt : 0;
						double bgAvg = badAvg - goodAvg;
						
						bean.setBadAvg("" + badAvg);
						bean.setGoodAvg("" + goodAvg);
						bean.setBGAvg("" + bgAvg);
						
						outList1.add(bean);
					}
				}
				
				if (Flag.TRUE) {
					/*
					 * SORT : BG_AVG DESC
					 */
					Collections.sort(outList1, new Comparator<DELAY_TIME03_Bean>() {
						@Override
						public int compare(DELAY_TIME03_Bean bean1, DELAY_TIME03_Bean bean2) {
							int ret = 0;
							
							// 1. BG_AVG DESC
							double dbl = Double.parseDouble(bean1.getBGAvg()) - Double.parseDouble(bean2.getBGAvg());
							if (dbl < 0)
								return 1;
							else if (dbl > 0)
								return -1;

							return ret;
						}
					});
				}
				
				if (Flag.TRUE) {
					/*
					 * CLUSTER 별 RANK를 붙인다.
					 */
					Map<String, String> mapRank = new HashMap<String, String>(); // CLUSTER_ID, RANK
					
					for (DELAY_TIME03_Bean bean : outList1) {
						
						String rank = mapRank.get(bean.getClusterId());
						if (rank == null) {
							rank = "0";
						}
						rank = "" + (Integer.parseInt(rank) + 1);
						
						bean.setRank(rank);
						
						mapRank.put(bean.getClusterId(), rank);
					}
					
					if (!Flag.TRUE) {
						/*
						 * 확인출력
						 */
						for (DELAY_TIME03_Bean bean : outList1) {
							System.out.println(bean);
						}
					}
					
				}

				if (!Flag.TRUE) {
					/*
					 * 확인출력
					 */
					for (DELAY_TIME03_Bean bean : outList1) {
						System.out.println(bean);
					}
				}

				if (Flag.TRUE) {
					/*
					 * RANK 11 이상 삭제
					 */
					for (int i=outList1.size()-1; 0 <= i; i--) {
						int rank = Integer.parseInt(outList1.get(i).getRank());
						if (10 < rank) {
							outList1.remove(i);
						}
					}
					
					if (!Flag.TRUE) {
						/*
						 * 확인출력
						 */
						for (DELAY_TIME03_Bean bean : outList1) {
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
				
				DELAY_TIME03_Main.cntWList = outList1.size();

				writer1.writeList();
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		if (Flag.TRUE) {
			/*
			 * 시간기록 끝
			 */
			DELAY_TIME03_Main.runMSec = (System.nanoTime() - DELAY_TIME03_Main.runMSec) / 1000 / 1000;
			
			if (Flag.TRUE) Logger.info("##### took time : %d ms", DELAY_TIME03_Main.runMSec);
		}

		if (Flag.TRUE) {
			/*
			 * UPDATE
			 */
			AnalCsvBean bean = new AnalCsvBean();
			
			bean.setType(AnalCsvType.UPDATE);
			bean.setTable("ANAL_CSV");
			bean.setFields(new String[][] {
					{ "LIST_CNT   ", "" + DELAY_TIME03_Main.cntWList },
					{ "E_TIME     ", "SYSDATE" },
					{ "R_MSEC     ", "" + DELAY_TIME03_Main.runMSec },
					{ "CSV_STATUS ", StrUtil.quote("OK") },   // OK, ERROR, SKIP, ETC...
					{ "LOG_MESSAGE", StrUtil.quote("COMPLETE") },  // COMPLETE, EXCEPTION
			});
			bean.setWhere(String.format("JOB_ID = %s AND PROG_NM = %s", StrUtil.quote(jobId), StrUtil.quote(DELAY_TIME03_Main.dsName)));
			
			if (Flag.TRUE) Logger.info("[%s]", bean.getQuery());
			
			bean.sendToOracle();
		}
	}
}
