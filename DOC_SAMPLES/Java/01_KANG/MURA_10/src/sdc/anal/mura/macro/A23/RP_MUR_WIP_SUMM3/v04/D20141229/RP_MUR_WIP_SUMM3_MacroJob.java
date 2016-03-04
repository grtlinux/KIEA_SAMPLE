package sdc.anal.mura.macro.A23.RP_MUR_WIP_SUMM3.v04.D20141229;

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

public class RP_MUR_WIP_SUMM3_MacroJob extends AbstractMacroJob
{
	//private SO_SYS_CELLID_LIST2_CsvIo reader1 = null;
	private WIP_EQP_SMMRY_CsvIo reader2 = null;
	private MURA_RAWDATA_ANAL2_CsvIo reader3 = null;
	private RP_MUR_WIP_SUMM3_CsvIo writer1 = null;
	
	//private List<SO_SYS_CELLID_LIST2_Bean> inList1  = null;
	private List<WIP_EQP_SMMRY_Bean> inList2  = null;
	private List<MURA_RAWDATA_ANAL2_Bean> inList3  = null;
	private List<RP_MUR_WIP_SUMM3_Bean> outList1 = null;
	
	private String filePath = null;

	private String jobId = null;

	public RP_MUR_WIP_SUMM3_MacroJob(String jobId)
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
			RP_MUR_WIP_SUMM3_Main.runMSec = System.nanoTime();
		}

		if (Flag.TRUE) {
			/*
			 * 기존 자료 삭제
			 */
			AnalCsvBean bean = new AnalCsvBean();

			bean.setType(AnalCsvType.DELETE);
			bean.setTable("ANAL_CSV");
			bean.setWhere(String.format("JOB_ID = %s AND PROG_NM = %s", StrUtil.quote(jobId), StrUtil.quote(RP_MUR_WIP_SUMM3_Main.dsName)));

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
					{ "CMD_CODE   ", StrUtil.quote(RP_MUR_WIP_SUMM3_Main.dsName.substring(0, 3)) },
					{ "PROG_NM    ", StrUtil.quote(RP_MUR_WIP_SUMM3_Main.dsName) },
					{ "CSV_NM     ", StrUtil.quote(RP_MUR_WIP_SUMM3_Main.dsName + ".csv") },
					{ "MAIN_CLASS ", StrUtil.quote(this.getClass().getName()) },
					{ "S_TIME     ", "SYSDATE" },
					{ "CSV_STATUS ", StrUtil.quote("START") },
			});
			if (Flag.TRUE) Logger.info("[%s]", bean.getQuery());

			bean.sendToOracle();
		}

		if (Flag.TRUE) {
			try {
				//reader1 = new SO_SYS_CELLID_LIST2_CsvIo(this.filePath);
				reader2 = new WIP_EQP_SMMRY_CsvIo(this.filePath);
				reader3 = new MURA_RAWDATA_ANAL2_CsvIo(this.filePath);
				writer1 = new RP_MUR_WIP_SUMM3_CsvIo(this.filePath);
				
				//inList1  = reader1.getList(true);
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
				
				Map<String, MURA_RAWDATA_ANAL2_Bean> mapMura = new LinkedHashMap<String, MURA_RAWDATA_ANAL2_Bean>();
				Map<String, RP_MUR_WIP_SUMM3_Bean> mapWip = new LinkedHashMap<String, RP_MUR_WIP_SUMM3_Bean>();
				
				if (Flag.TRUE) {
					/*
					 * MURA_RAWDATA_ANAL2_Bean
					 */
					for (MURA_RAWDATA_ANAL2_Bean inBean3 : inList3) {
						if (mapMura.get(inBean3.getGlassId()) == null) {
							mapMura.put(inBean3.getGlassId(), inBean3);
						}
					}
					
					if (!Flag.TRUE) {
						/*
						 * 확인출력
						 */
						for (Map.Entry<String, MURA_RAWDATA_ANAL2_Bean> entry : mapMura.entrySet()) {
							System.out.println(entry.getValue());
						}
					}
				}
				
				if (Flag.TRUE) {
					/*
					 * TODO : 2014.11.06
					 * WIP_EQP_SMMRY stepid + eqpid + glassid 중복제거 모듈 추가
					 */
					Map<String, WIP_EQP_SMMRY_Bean> map = new LinkedHashMap<String, WIP_EQP_SMMRY_Bean>();
					
					for (WIP_EQP_SMMRY_Bean inBean2 : inList2) {
						String key = inBean2.getStepId() + ":" + inBean2.getEqpId() + ":" + inBean2.getGlassCellId();
						
						if (map.get(key) == null) {
							map.put(key, inBean2);
						}
					}
					
					inList2.clear();
					
					for (Map.Entry<String, WIP_EQP_SMMRY_Bean> entry : map.entrySet()) {
						inList2.add(entry.getValue());
					}
				}

				if (Flag.TRUE) {
					/*
					 * WIP_EQP_SMMRY 누적처리
					 */
					for (WIP_EQP_SMMRY_Bean inBean2 : inList2) {
						
						String key = inBean2.getStepId() + ":" + inBean2.getEqpId();
						
						if (Flag.TRUE) {
							/*
							 * CNT
							 */
							RP_MUR_WIP_SUMM3_Bean bean = mapWip.get(key);
							if (bean == null) {
								bean = new RP_MUR_WIP_SUMM3_Bean();
								
								bean.setDivCode     ("EQP_ID");
								bean.setStepId      (inBean2.getStepId());
								bean.setEqpId       (inBean2.getEqpId());
								bean.setTotCellCnt  ("0");
								bean.setBadCellCnt  ("0");
								bean.setCellBadRatio("0");

								mapWip.put(key, bean);
							}
							
							if (mapMura.get(inBean2.getGlassCellId()) != null) {
								bean.addBadCellCnt();
							}
							
							bean.addTotCellCnt();
						}
					}
					
					/*
					 * RP_SYS_WIP_SUMM3 CELL RATIO 값 처리
					 */
					for (Map.Entry<String, RP_MUR_WIP_SUMM3_Bean> entry : mapWip.entrySet()) {
						RP_MUR_WIP_SUMM3_Bean bean = entry.getValue();
						
						double cellBadRatio = Double.parseDouble(bean.getBadCellCnt()) * 100 / Double.parseDouble(bean.getTotCellCnt());
						bean.setCellBadRatio("" + cellBadRatio);
						
						outList1.add(bean);
					}

					if (!Flag.TRUE) {
						/*
						 * 확인출력
						 */
						for (RP_MUR_WIP_SUMM3_Bean bean : outList1) {
							System.out.println(bean);
						}
					}
				}
				
				if (Flag.TRUE) {
					/*
					 * SORT
					 *     STEP_ID
					 *     EQP_ID
					 */
					Collections.sort(outList1, new Comparator<RP_MUR_WIP_SUMM3_Bean>() {
						@Override
						public int compare(RP_MUR_WIP_SUMM3_Bean bean1, RP_MUR_WIP_SUMM3_Bean bean2) {
							int ret = 0;
							
							// 1. STEP_ID
							ret = bean1.getStepId().compareTo(bean2.getStepId());
							if (ret != 0) return ret;

							// 2. EQP_ID
							ret = bean1.getEqpId().compareTo(bean2.getEqpId());
							if (ret != 0) return ret;

							return ret;
						}
					});
					
					if (!Flag.TRUE) {
						/*
						 * 확인출력
						 */
						for (RP_MUR_WIP_SUMM3_Bean bean : outList1) {
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
				
				RP_MUR_WIP_SUMM3_Main.cntWList = outList1.size();

				writer1.writeList();
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		if (Flag.TRUE) {
			/*
			 * 시간기록 끝
			 */
			RP_MUR_WIP_SUMM3_Main.runMSec = (System.nanoTime() - RP_MUR_WIP_SUMM3_Main.runMSec) / 1000 / 1000;
			
			if (Flag.TRUE) Logger.info("##### took time : %d ms", RP_MUR_WIP_SUMM3_Main.runMSec);
		}

		if (Flag.TRUE) {
			/*
			 * UPDATE
			 */
			AnalCsvBean bean = new AnalCsvBean();
			
			bean.setType(AnalCsvType.UPDATE);
			bean.setTable("ANAL_CSV");
			bean.setFields(new String[][] {
					{ "LIST_CNT   ", "" + RP_MUR_WIP_SUMM3_Main.cntWList },
					{ "E_TIME     ", "SYSDATE" },
					{ "R_MSEC     ", "" + RP_MUR_WIP_SUMM3_Main.runMSec },
					{ "CSV_STATUS ", StrUtil.quote("OK") },   // OK, ERROR, SKIP, ETC...
					{ "LOG_MESSAGE", StrUtil.quote("COMPLETE") },  // COMPLETE, EXCEPTION
			});
			bean.setWhere(String.format("JOB_ID = %s AND PROG_NM = %s", StrUtil.quote(jobId), StrUtil.quote(RP_MUR_WIP_SUMM3_Main.dsName)));
			
			if (Flag.TRUE) Logger.info("[%s]", bean.getQuery());
			
			bean.sendToOracle();
		}
	}
}
