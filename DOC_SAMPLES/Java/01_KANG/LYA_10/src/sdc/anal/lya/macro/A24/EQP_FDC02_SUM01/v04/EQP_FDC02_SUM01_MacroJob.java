package sdc.anal.lya.macro.A24.EQP_FDC02_SUM01.v04;

import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import kiea.kr.co.tain.base.Flag;
import kiea.kr.co.tain.util.Print;
import kiea.proj.sdc.anal.macro.impl.job.AbstractMacroJob;
import kiea.proj.sdc.anal.tools.AnalCsv.v04.AnalCsvBean;
import kiea.proj.sdc.anal.tools.AnalCsv.v04.AnalCsvType;
import kiea.proj.sdc.anal.util.BasePath;
import kiea.proj.sdc.anal.util.FileUtil;
import kiea.proj.sdc.anal.util.StrUtil;
import kiea.proj.sdc.anal.util.log.v02.Logger;

public class EQP_FDC02_SUM01_MacroJob extends AbstractMacroJob
{
	private EQP_FDC02_DETAILS01_CsvIo reader1 = null;
	private EQP_FDC02_SUM01_CsvIo writer1 = null;
	
	private List<EQP_FDC02_DETAILS01_Bean> inList1  = null;
	private List<EQP_FDC02_SUM01_Bean> outList1 = null;
	
	private String filePath = null;

	private String jobId = null;

	public EQP_FDC02_SUM01_MacroJob(String jobId)
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
			EQP_FDC02_SUM01_Main.runMSec = System.nanoTime();
		}

		if (Flag.TRUE) {
			/*
			 * 기존 자료 삭제
			 */
			AnalCsvBean bean = new AnalCsvBean();

			bean.setType(AnalCsvType.DELETE);
			bean.setTable("ANAL_CSV");
			bean.setWhere(String.format("JOB_ID = %s AND PROG_NM = %s", StrUtil.quote(jobId), StrUtil.quote(EQP_FDC02_SUM01_Main.dsName)));

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
					{ "CMD_CODE   ", StrUtil.quote(EQP_FDC02_SUM01_Main.dsName.substring(0, 3)) },
					{ "PROG_NM    ", StrUtil.quote(EQP_FDC02_SUM01_Main.dsName) },
					{ "CSV_NM     ", StrUtil.quote(EQP_FDC02_SUM01_Main.dsName + ".csv") },
					{ "MAIN_CLASS ", StrUtil.quote(this.getClass().getName()) },
					{ "S_TIME     ", "SYSDATE" },
					{ "CSV_STATUS ", StrUtil.quote("START") },
			});
			if (Flag.TRUE) Logger.info("[%s]", bean.getQuery());

			bean.sendToOracle();
		}

		if (Flag.TRUE) {
			try {
				reader1 = new EQP_FDC02_DETAILS01_CsvIo(this.filePath);
				writer1 = new EQP_FDC02_SUM01_CsvIo(this.filePath);
				
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
				Map<String, EQP_FDC02_SUM01_Bean> mapSum = new LinkedHashMap<String, EQP_FDC02_SUM01_Bean>();
				
				if (Flag.TRUE) {
					/*
					 * 해당자료만 추출한다.
					 */
					for (EQP_FDC02_DETAILS01_Bean inBean1 : inList1) {
						String key = inBean1.getClusterId() + ":" + inBean1.getPart() + ":" + inBean1.getEqpId() + ":" + inBean1.getSensorName();
						
						EQP_FDC02_SUM01_Bean bean = mapSum.get(key);
						if (bean == null) {
							bean = new EQP_FDC02_SUM01_Bean();
							
							bean.setClusterId (inBean1.getClusterId ());
							bean.setPart      (inBean1.getPart      ());
							bean.setEqpId     (inBean1.getEqpId     ());
							bean.setSensorName(inBean1.getSensorName());
							
							mapSum.put(key, bean);
						}
						
						bean.setDVMin(inBean1.getParamValue());
						bean.setDVMax(inBean1.getParamValue());
						
						if ("BAD".equals(inBean1.getGroupId())) {
							/*
							 * BAD
							 */
							bean.setDBadCnt();
							bean.setDBadSum(inBean1.getParamValue());
						} else {
							/*
							 * GOOD
							 */
							bean.setDGoodCnt();
							bean.setDGoodSum(inBean1.getParamValue());
						}
					}
				}
				
				if (Flag.TRUE) {
					/*
					 * 자료를 읽어 EQP_FDC02_SUM01_Bean 에 값을 세팅한다.
					 * outList1 세팅
					 */
					for (Map.Entry<String, EQP_FDC02_SUM01_Bean> entry : mapSum.entrySet()) {

						EQP_FDC02_SUM01_Bean bean = entry.getValue();
						
						double dVMin    = bean.getDVMin();
						double dVMax    = bean.getDVMax();
						/*
						 * Vmin = Vmax 이면 분모가 0 이라 의미 없음
						 */
						if (dVMax - dVMin < 0.0000000001)
							continue;
						
						double dBadSum  = bean.getDBadSum();
						double dBadCnt  = bean.getDBadCnt();
						double dGoodSum = bean.getDGoodSum();
						double dGoodCnt = bean.getDGoodCnt();
						
						/*
						 * BAD_CNT = 0 or GOOD_CNT = 0 이면 분모가 0 이라 의미 없음
						 */
						if (dBadCnt == 0 || dGoodCnt == 0)
							continue;
						
						double dBadAvg  = ( dBadSum  - dVMin * dBadCnt  ) / ((dVMax - dVMin) * dBadCnt  * dBadCnt );
						double dGoodAvg = ( dGoodSum - dVMin * dGoodCnt ) / ((dVMax - dVMin) * dGoodCnt * dGoodCnt);
						double dFdcBG   = Math.abs(dBadAvg - dGoodAvg);
						
						bean.setDBadAvg (dBadAvg);
						bean.setDGoodAvg(dGoodAvg);
						bean.setDFdcBG  (dFdcBG);
						
						bean.setVMin   ("" + dVMin);
						bean.setVMax   ("" + dVMax);
						bean.setBadSum ("" + dBadSum);
						bean.setBadCnt ("" + dBadCnt);
						bean.setGoodSum("" + dGoodSum);
						bean.setGoodCnt("" + dGoodCnt);
						bean.setBadAvg ("" + dBadAvg);
						bean.setGoodAvg("" + dGoodAvg);
						bean.setFdcBG  ("" + dFdcBG);
						
						outList1.add(bean);
					}
				}

				if (Flag.TRUE) {
					/*
					 * SORT for FDC_BG
					 */
					
					Collections.sort(outList1, new Comparator<EQP_FDC02_SUM01_Bean>() {
						@Override
						public int compare(EQP_FDC02_SUM01_Bean bean1, EQP_FDC02_SUM01_Bean bean2) {
							int ret = 0;
							
							if (!Flag.TRUE) {
								// 1. FDC_BG DESC
								double dbl = Double.parseDouble(bean1.getFdcBG()) - Double.parseDouble(bean2.getFdcBG());
								if (dbl < 0)
									return -1;
								else if (dbl > 0)
									return 1;
							}
									
							if (Flag.TRUE) {
								// 1. FDC_BG DESC
								double dbl = bean1.getDFdcBG() - bean2.getDFdcBG();
								if (dbl < 0)
									return 1;
								else if (dbl > 0)
									return -1;
							}

							return ret;
						}
					});
				}
				
				if (!Flag.TRUE) {
					/*
					 * SET RANK BY FDC_BG IN ALL CLUSTER
					 */
					for (int i=0; i < outList1.size(); i++) {
						outList1.get(i).setRank("" + (i+1));
					}
				}
				
				if (Flag.TRUE) {
					/*
					 * SET RANK BY FDC_BG
					 */
					Map<String, String> mapRank = new HashMap<String, String>(); // CLUSTER_ID, RANK
					
					for (EQP_FDC02_SUM01_Bean bean : outList1) {
						
						String rank = mapRank.get(bean.getClusterId());
						if (rank == null) {
							rank = "0";
						}
						rank = "" + (Integer.parseInt(rank) + 1);
						
						bean.setRank(rank);
						
						mapRank.put(bean.getClusterId(), rank);
					}
				}

				if (Flag.TRUE) {
					if (outList1.size() > 0) {
						/*
						 * 자료가 있으면 RANK 20 이상 삭제
						 */
						for (int i=outList1.size()-1; i >= 0; i--) {
							int rank = Integer.parseInt(outList1.get(i).getRank());
							if (rank > 20)
								outList1.remove(i);
						}
					}
				}
				
				if (!Flag.TRUE) {
					/*
					 * 확인출력
					 */
					
					for (Map.Entry<String, EQP_FDC02_SUM01_Bean> entry : mapSum.entrySet()) {
						String key = entry.getKey();
						EQP_FDC02_SUM01_Bean bean = entry.getValue();
						
						double dVMin    = bean.getDVMin();
						double dVMax    = bean.getDVMax();
						double dBadSum  = bean.getDBadSum();
						double dBadCnt  = bean.getDBadCnt();
						double dGoodSum = bean.getDGoodSum();
						double dGoodCnt = bean.getDGoodCnt();
						
						Print.println("[%s] %f %f %f %f %f %f ", key, dVMin, dVMax, dBadSum, dBadCnt, dGoodSum, dGoodCnt);
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
				
				EQP_FDC02_SUM01_Main.cntWList = outList1.size();

				writer1.writeList();
				
			} catch (Exception e) {
				e.printStackTrace();
			}

			if (Flag.TRUE) {
				/*
				 * 시간기록 끝
				 */
				EQP_FDC02_SUM01_Main.runMSec = (System.nanoTime() - EQP_FDC02_SUM01_Main.runMSec) / 1000 / 1000;
				
				if (Flag.TRUE) Logger.info("##### took time : %d ms", EQP_FDC02_SUM01_Main.runMSec);
			}

			if (Flag.TRUE) {
				/*
				 * UPDATE
				 */
				AnalCsvBean bean = new AnalCsvBean();
				
				bean.setType(AnalCsvType.UPDATE);
				bean.setTable("ANAL_CSV");
				bean.setFields(new String[][] {
						{ "LIST_CNT   ", "" + EQP_FDC02_SUM01_Main.cntWList },
						{ "E_TIME     ", "SYSDATE" },
						{ "R_MSEC     ", "" + EQP_FDC02_SUM01_Main.runMSec },
						{ "CSV_STATUS ", StrUtil.quote("OK") },   // OK, ERROR, SKIP, ETC...
						{ "LOG_MESSAGE", StrUtil.quote("COMPLETE") },  // COMPLETE, EXCEPTION
				});
				bean.setWhere(String.format("JOB_ID = %s AND PROG_NM = %s", StrUtil.quote(jobId), StrUtil.quote(EQP_FDC02_SUM01_Main.dsName)));
				
				if (Flag.TRUE) Logger.info("[%s]", bean.getQuery());
				
				bean.sendToOracle();
			}
		}
	}
	
	///////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////
	
	private static void test01()
	{
		if (Flag.TRUE) {
			Random random;
			// random = new Random(new Date().getTime());  // random seed
			random = new Random();
			
			for (int i=0; i < 10; i++) {
				if (Flag.TRUE) System.out.println("[" + random.nextInt(2) + "]");
			}
		}
	}
	
	private static void test02()
	{
		if (Flag.TRUE) {
			String str = "1.5938827212914512E-4";
			
			double dbl = Double.parseDouble(str);
			System.out.println(">" + dbl);
		}
	}
	
	public static void _main(String[] args)
	{
		if (!Flag.TRUE) test01();
		if (Flag.TRUE) test02();
	}
}
