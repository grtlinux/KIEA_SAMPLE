package sdc.anal.mura.macro.A23.RP_MUR_WIP_SUMM4.v04.D20141229;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import kiea.kr.co.tain.base.Flag;
import kiea.kr.co.tain.util.Print;
import kiea.proj.sdc.anal.macro.impl.job.AbstractMacroJob;
import kiea.proj.sdc.anal.tools.AnalCsv.v04.AnalCsvBean;
import kiea.proj.sdc.anal.tools.AnalCsv.v04.AnalCsvType;
import kiea.proj.sdc.anal.util.BasePath;
import kiea.proj.sdc.anal.util.FileUtil;
import kiea.proj.sdc.anal.util.StrUtil;
import kiea.proj.sdc.anal.util.log.v02.Logger;

public class RP_MUR_WIP_SUMM4_MacroJob extends AbstractMacroJob
{
	private SO_SYS_GLASS_LIST2_CsvIo reader1 = null;
	private WIP_EQP_SMMRY_CsvIo reader2 = null;
	private RP_MUR_WIP_SUMM4_CsvIo writer1 = null;
	
	private List<SO_SYS_GLASS_LIST2_Bean> inList1  = null;
	private List<WIP_EQP_SMMRY_Bean> inList2  = null;
	private List<RP_MUR_WIP_SUMM4_Bean> outList1 = null;
	
	private String filePath = null;

	private String jobId = null;

	public RP_MUR_WIP_SUMM4_MacroJob(String jobId)
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
			RP_MUR_WIP_SUMM4_Main.runMSec = System.nanoTime();
		}

		if (Flag.TRUE) {
			/*
			 * 기존 자료 삭제
			 */
			AnalCsvBean bean = new AnalCsvBean();

			bean.setType(AnalCsvType.DELETE);
			bean.setTable("ANAL_CSV");
			bean.setWhere(String.format("JOB_ID = %s AND PROG_NM = %s", StrUtil.quote(jobId), StrUtil.quote(RP_MUR_WIP_SUMM4_Main.dsName)));

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
					{ "CMD_CODE   ", StrUtil.quote(RP_MUR_WIP_SUMM4_Main.dsName.substring(0, 3)) },
					{ "PROG_NM    ", StrUtil.quote(RP_MUR_WIP_SUMM4_Main.dsName) },
					{ "CSV_NM     ", StrUtil.quote(RP_MUR_WIP_SUMM4_Main.dsName + ".csv") },
					{ "MAIN_CLASS ", StrUtil.quote(this.getClass().getName()) },
					{ "S_TIME     ", "SYSDATE" },
					{ "CSV_STATUS ", StrUtil.quote("START") },
			});
			if (Flag.TRUE) Logger.info("[%s]", bean.getQuery());

			bean.sendToOracle();
		}

		if (Flag.TRUE) {
			try {
				reader1 = new SO_SYS_GLASS_LIST2_CsvIo(this.filePath);
				reader2 = new WIP_EQP_SMMRY_CsvIo(this.filePath);
				writer1 = new RP_MUR_WIP_SUMM4_CsvIo(this.filePath);
				
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
				Map<String, List<SO_SYS_GLASS_LIST2_Bean>> mapListGlass = new LinkedHashMap<String, List<SO_SYS_GLASS_LIST2_Bean>>();
				Map<String, RP_MUR_WIP_SUMM4_Bean> mapWip = new LinkedHashMap<String, RP_MUR_WIP_SUMM4_Bean>();
				
				if (Flag.TRUE) {

					for (SO_SYS_GLASS_LIST2_Bean bean : inList1) {
						
						List<SO_SYS_GLASS_LIST2_Bean> list = mapListGlass.get(bean.getGlassId());
						if (list == null) {
							list = new ArrayList<SO_SYS_GLASS_LIST2_Bean>();
							mapListGlass.put(bean.getGlassId(), list);
						}
						
						list.add(bean);
					}
					
					if (!Flag.TRUE) {
						/*
						 * 확인출력
						 */
						for (Map.Entry<String, List<SO_SYS_GLASS_LIST2_Bean>> entry : mapListGlass.entrySet()) {
							String glassId = entry.getKey();
							List<SO_SYS_GLASS_LIST2_Bean> list = entry.getValue();
							
							Print.println("[%s]", glassId);
							for (SO_SYS_GLASS_LIST2_Bean bean : list) {
								Print.println("\t%s", bean);
							}
						}
					}
				}
				
				if (Flag.TRUE) {
					/*
					 * 
					 */
					for (WIP_EQP_SMMRY_Bean inBean2 : inList2) {
						
						List<SO_SYS_GLASS_LIST2_Bean> list = mapListGlass.get(inBean2.getGlassCellId());
						if (list == null)
							continue;
						
						for (SO_SYS_GLASS_LIST2_Bean bean : list) {
							String trckInHour = inBean2.getTkInDate().substring(0, 13);
							String key = bean.getGroupNum() + ":" + inBean2.getStepId() + ":" + inBean2.getEqpId() + ":" + trckInHour;
							
							RP_MUR_WIP_SUMM4_Bean outBean1 = mapWip.get(key);
							if (outBean1 == null) {
								outBean1 = new RP_MUR_WIP_SUMM4_Bean();
								
								outBean1.setDivCode   ("DIV_CODE");
								outBean1.setClusterId (bean.getGroupNum());
								outBean1.setStepId    (inBean2.getStepId());
								outBean1.setEqpId     (inBean2.getEqpId ());
								outBean1.setTrckInHour(trckInHour);
								outBean1.setTotCellCnt("0");
								outBean1.setBadCellCnt("0");
								
								mapWip.put(key, outBean1);
							}
							
							if ("1".equals(bean.getTarget())) {
								outBean1.addBadCellCnt();
							}
							outBean1.addTotCellCnt();
						}
					}
					
					if (!Flag.TRUE) {
						/*
						 * 확인출력
						 */
						for (Map.Entry<String, RP_MUR_WIP_SUMM4_Bean> entry : mapWip.entrySet()) {
							System.out.println(entry.getValue());
						}
					}
				}
				
				if (Flag.TRUE) {
					/*
					 * UPDATE
					 */
					for (Map.Entry<String, RP_MUR_WIP_SUMM4_Bean> entry : mapWip.entrySet()) {
						RP_MUR_WIP_SUMM4_Bean outBean1 = entry.getValue();
						
						double cellBadRatio = Double.parseDouble(outBean1.getBadCellCnt()) * 100 / Double.parseDouble(outBean1.getTotCellCnt());
						outBean1.setCellBadRatio("" + cellBadRatio);

						outList1.add(outBean1);
					}
					
					if (!Flag.TRUE) {
						/*
						 * 확인
						 */
						for (Map.Entry<String, RP_MUR_WIP_SUMM4_Bean> entry : mapWip.entrySet()) {
							System.out.println(entry.getValue());
						}
					}
				}
				
				if (Flag.TRUE) {
					/*
					 * BGRATIO 내림차순 RANK를 구한다.
					 */
					Collections.sort(outList1, new Comparator<RP_MUR_WIP_SUMM4_Bean>() {
						@Override
						public int compare(RP_MUR_WIP_SUMM4_Bean bean1, RP_MUR_WIP_SUMM4_Bean bean2) {
							int ret = 0;
							
							// 1. CLUSTER_ID
							int val = Integer.parseInt(bean1.getClusterId()) - Integer.parseInt(bean2.getClusterId());
							if (val < 0)
								return -1;
							else if (val > 0)
								return 1;

							// 2. STEP_ID
							ret = bean1.getStepId().compareTo(bean2.getStepId());
							if (ret != 0) return ret;

							// 3. TRCK_IN_HOUR
							ret = bean1.getTrckInHour().compareTo(bean2.getTrckInHour());
							if (ret != 0) return ret;
							
							// 4. EQP_ID
							ret = bean1.getEqpId().compareTo(bean2.getEqpId());
							if (ret != 0) return ret;

							return 0;
						}
					});
					
					if (!Flag.TRUE) {
						/*
						 * 확인
						 */
						for (RP_MUR_WIP_SUMM4_Bean outBean1 : outList1) {
							System.out.println(outBean1);
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
				
				RP_MUR_WIP_SUMM4_Main.cntWList = outList1.size();

				writer1.writeList();
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		if (Flag.TRUE) {
			/*
			 * 시간기록 끝
			 */
			RP_MUR_WIP_SUMM4_Main.runMSec = (System.nanoTime() - RP_MUR_WIP_SUMM4_Main.runMSec) / 1000 / 1000;
			
			if (Flag.TRUE) Logger.info("##### took time : %d ms", RP_MUR_WIP_SUMM4_Main.runMSec);
		}

		if (Flag.TRUE) {
			/*
			 * UPDATE
			 */
			AnalCsvBean bean = new AnalCsvBean();
			
			bean.setType(AnalCsvType.UPDATE);
			bean.setTable("ANAL_CSV");
			bean.setFields(new String[][] {
					{ "LIST_CNT   ", "" + RP_MUR_WIP_SUMM4_Main.cntWList },
					{ "E_TIME     ", "SYSDATE" },
					{ "R_MSEC     ", "" + RP_MUR_WIP_SUMM4_Main.runMSec },
					{ "CSV_STATUS ", StrUtil.quote("OK") },   // OK, ERROR, SKIP, ETC...
					{ "LOG_MESSAGE", StrUtil.quote("COMPLETE") },  // COMPLETE, EXCEPTION
			});
			bean.setWhere(String.format("JOB_ID = %s AND PROG_NM = %s", StrUtil.quote(jobId), StrUtil.quote(RP_MUR_WIP_SUMM4_Main.dsName)));
			
			if (Flag.TRUE) Logger.info("[%s]", bean.getQuery());
			
			bean.sendToOracle();
		}
	}
}
