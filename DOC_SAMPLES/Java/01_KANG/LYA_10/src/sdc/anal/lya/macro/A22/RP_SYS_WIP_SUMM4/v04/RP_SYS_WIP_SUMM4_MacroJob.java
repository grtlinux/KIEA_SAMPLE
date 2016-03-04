package sdc.anal.lya.macro.A22.RP_SYS_WIP_SUMM4.v04;

import java.util.ArrayList;
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

public class RP_SYS_WIP_SUMM4_MacroJob extends AbstractMacroJob
{
	//private SO_SYS_CELLID_LIST2_CsvIo reader1 = null;
	private WIP_EQP_SMMRY_CsvIo reader2 = null;
	private INSPCT_HIST_CsvIo reader3 = null;
	private RP_SYS_WIP_SUMM4_CsvIo writer1 = null;
	
	//private List<SO_SYS_CELLID_LIST2_Bean> inList1  = null;
	private List<WIP_EQP_SMMRY_Bean> inList2  = null;
	private List<INSPCT_HIST_Bean> inList3  = null;
	private List<RP_SYS_WIP_SUMM4_Bean> outList1 = null;
	
	private String filePath = null;

	private String jobId = null;

	public RP_SYS_WIP_SUMM4_MacroJob(String jobId)
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
			RP_SYS_WIP_SUMM4_Main.runMSec = System.nanoTime();
		}

		if (Flag.TRUE) {
			/*
			 * 기존 자료 삭제
			 */
			AnalCsvBean bean = new AnalCsvBean();

			bean.setType(AnalCsvType.DELETE);
			bean.setTable("ANAL_CSV");
			bean.setWhere(String.format("JOB_ID = %s AND PROG_NM = %s", StrUtil.quote(jobId), StrUtil.quote(RP_SYS_WIP_SUMM4_Main.dsName)));

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
					{ "CMD_CODE   ", StrUtil.quote(RP_SYS_WIP_SUMM4_Main.dsName.substring(0, 3)) },
					{ "PROG_NM    ", StrUtil.quote(RP_SYS_WIP_SUMM4_Main.dsName) },
					{ "CSV_NM     ", StrUtil.quote(RP_SYS_WIP_SUMM4_Main.dsName + ".csv") },
					{ "MAIN_CLASS ", StrUtil.quote(this.getClass().getName()) },
					{ "S_TIME     ", "SYSDATE" },
					{ "CSV_STATUS ", StrUtil.quote("START") },
			});
			if (Flag.TRUE) Logger.info("[%s]", bean.getQuery());

			bean.sendToOracle();
		}

		if (Flag.TRUE) {
			try {
				// reader1 = new SO_SYS_CELLID_LIST2_CsvIo(this.filePath);
				reader2 = new WIP_EQP_SMMRY_CsvIo(this.filePath);
				reader3 = new INSPCT_HIST_CsvIo(this.filePath);
				writer1 = new RP_SYS_WIP_SUMM4_CsvIo(this.filePath);
				
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
				
				String paramDecisionCode = StrUtil.quote(Parameter.getInstance().getStrDecisionCode());
				String paramDefectGroupCode = StrUtil.quote(Parameter.getInstance().getStrDefectGroupCode());
				if (!Flag.TRUE) Print.println("[Decision:%s] [Defect:%s]", paramDecisionCode, paramDefectGroupCode);

				Map<String, WIP_EQP_SMMRY_Bean> mapGlass = new LinkedHashMap<String, WIP_EQP_SMMRY_Bean>();
				
				Map<String, List<INSPCT_HIST_Bean>> mapListInspct = new LinkedHashMap<String, List<INSPCT_HIST_Bean>>();
				Map<String, RP_SYS_WIP_SUMM4_Bean> mapWip = new LinkedHashMap<String, RP_SYS_WIP_SUMM4_Bean>();
				
				if (Flag.TRUE) {
					/*
					 * INSPCT_HIST_Bean
					 */
					for (INSPCT_HIST_Bean inBean3 : inList3) {
						String key = inBean3.getGlassId();
						
						List<INSPCT_HIST_Bean> listInspct = mapListInspct.get(key);
						if (listInspct == null) {
							listInspct = new ArrayList<INSPCT_HIST_Bean>();
							mapListInspct.put(key, listInspct);
						}
						
						listInspct.add(inBean3);
					}
					
					if (!Flag.TRUE) {
						/*
						 * 확인출력
						 */
						for (Map.Entry<String, List<INSPCT_HIST_Bean>> listEntry : mapListInspct.entrySet()) {
							System.out.println(listEntry.getKey());
							List<INSPCT_HIST_Bean> listBean = listEntry.getValue();
							for (INSPCT_HIST_Bean bean : listBean) {
								System.out.println("\t" + bean);
							}
						}
					}
				}
				
				if (Flag.TRUE) {
					/*
					 * WIP_EQP_SMMRY 중복제거
					 */
					for (WIP_EQP_SMMRY_Bean inBean2 : inList2) {
						String key = inBean2.getGlassId() + ":" + inBean2.getStepId() + ":" + inBean2.getEqpId() + ":" + inBean2.getTrckInTime().substring(0, 13);
						mapGlass.put(key, inBean2);
					}
				}

				if (Flag.TRUE) {
					/*
					 * WIP_EQP_SMMRY 누적처리
					 */
					for (WIP_EQP_SMMRY_Bean inBean2 : mapGlass.values()) {
						
						List<INSPCT_HIST_Bean> listInspct = mapListInspct.get(inBean2.getGlassId());
						if (listInspct == null)
							continue;

						for (INSPCT_HIST_Bean inBean3 : listInspct) {
							
							if (Flag.TRUE) {
								/*
								 * CNT
								 */
								String key = inBean2.getStepId() + ":" + inBean2.getEqpId() + ":" + inBean2.getTrckInTime().substring(0, 13);
								
								RP_SYS_WIP_SUMM4_Bean bean = mapWip.get(key);
								if (bean == null) {
									bean = new RP_SYS_WIP_SUMM4_Bean();
									
									bean.setDivCode   ("EQP_ID");
									bean.setClusterId ("0");
									bean.setStepId    (inBean2.getStepId());
									bean.setEqpId     (inBean2.getEqpId());
									bean.setTrckInHour(inBean2.getTrckInTime().substring(0, 13));
									bean.setTotCellCnt("0");
									bean.setBadCellCnt("0");

									mapWip.put(key, bean);
								}
								
								if (paramDecisionCode.indexOf(StrUtil.quote(inBean3.getDecisionCode())) >= 0
								&& paramDefectGroupCode.indexOf(StrUtil.quote(inBean3.getDefectGroupCode())) >= 0) {
									bean.addBadCellCnt();
								}
								
								bean.addTotCellCnt();
							}
						}
					}
					
					if (!Flag.TRUE) {
						/*
						 * 확인출력
						 */
						for (Map.Entry<String, RP_SYS_WIP_SUMM4_Bean> entry : mapWip.entrySet()) {
							System.out.println(entry.getValue());
						}
					}
				}
				
				if (Flag.TRUE) {
					/*
					 * RP_SYS_WIP_SUMM3_Bean
					 */
					for (Map.Entry<String, RP_SYS_WIP_SUMM4_Bean> entry : mapWip.entrySet()) {
						outList1.add(entry.getValue());
					}
				}
					
				if (Flag.TRUE) {
					/*
					 * RP_SYS_WIP_SUMM3 CELL RATIO 값 처리
					 */
					for (RP_SYS_WIP_SUMM4_Bean outBean1 : outList1) {
						
						double ratioBadCell = Double.parseDouble(outBean1.getBadCellCnt()) * 100 / Double.parseDouble(outBean1.getTotCellCnt());
						
						outBean1.setCellBadRatio  ("" + ratioBadCell);
					}
					
					if (!Flag.TRUE) {
						/*
						 * 확인출력
						 */
						for (RP_SYS_WIP_SUMM4_Bean bean : outList1) {
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
					Collections.sort(outList1, new Comparator<RP_SYS_WIP_SUMM4_Bean>() {
						@Override
						public int compare(RP_SYS_WIP_SUMM4_Bean bean1, RP_SYS_WIP_SUMM4_Bean bean2) {
							int ret = 0;
							
							// 1. STEP_ID
							ret = bean1.getStepId().compareTo(bean2.getStepId());
							if (ret != 0) return ret;

							// 2. EQP_ID
							//ret = bean1.getEqpId().compareTo(bean2.getEqpId());
							//if (ret != 0) return ret;

							// 3. TRCK_IN_HOUR
							ret = bean1.getTrckInHour().compareTo(bean2.getTrckInHour());
							if (ret != 0) return ret;

							return ret;
						}
					});
					
					if (!Flag.TRUE) {
						/*
						 * 확인출력
						 */
						for (RP_SYS_WIP_SUMM4_Bean bean : outList1) {
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
				
				RP_SYS_WIP_SUMM4_Main.cntWList = outList1.size();

				writer1.writeList();
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		if (Flag.TRUE) {
			/*
			 * 시간기록 끝
			 */
			RP_SYS_WIP_SUMM4_Main.runMSec = (System.nanoTime() - RP_SYS_WIP_SUMM4_Main.runMSec) / 1000 / 1000;
			
			if (Flag.TRUE) Logger.info("##### took time : %d ms", RP_SYS_WIP_SUMM4_Main.runMSec);
		}

		if (Flag.TRUE) {
			/*
			 * UPDATE
			 */
			AnalCsvBean bean = new AnalCsvBean();
			
			bean.setType(AnalCsvType.UPDATE);
			bean.setTable("ANAL_CSV");
			bean.setFields(new String[][] {
					{ "LIST_CNT   ", "" + RP_SYS_WIP_SUMM4_Main.cntWList },
					{ "E_TIME     ", "SYSDATE" },
					{ "R_MSEC     ", "" + RP_SYS_WIP_SUMM4_Main.runMSec },
					{ "CSV_STATUS ", StrUtil.quote("OK") },   // OK, ERROR, SKIP, ETC...
					{ "LOG_MESSAGE", StrUtil.quote("COMPLETE") },  // COMPLETE, EXCEPTION
			});
			bean.setWhere(String.format("JOB_ID = %s AND PROG_NM = %s", StrUtil.quote(jobId), StrUtil.quote(RP_SYS_WIP_SUMM4_Main.dsName)));
			
			if (Flag.TRUE) Logger.info("[%s]", bean.getQuery());
			
			bean.sendToOracle();
		}
	}
}
