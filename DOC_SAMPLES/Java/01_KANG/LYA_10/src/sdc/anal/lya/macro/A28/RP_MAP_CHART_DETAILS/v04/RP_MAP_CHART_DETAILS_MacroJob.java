package sdc.anal.lya.macro.A28.RP_MAP_CHART_DETAILS.v04;

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

public class RP_MAP_CHART_DETAILS_MacroJob extends AbstractMacroJob
{
	private SO_SYS_CELLID_LIST2_CsvIo reader1 = null;
	private DEFECT_HIST_CsvIo reader2 = null;
	private INSPCT_HIST_CsvIo reader3 = null;
	private RP_MAP_CHART_DETAILS_CsvIo writer1 = null;
	
	private List<SO_SYS_CELLID_LIST2_Bean> inList1  = null;
	private List<DEFECT_HIST_Bean> inList2  = null;
	private List<INSPCT_HIST_Bean> inList3  = null;
	private List<RP_MAP_CHART_DETAILS_Bean> outList1 = null;
	
	private String filePath = null;

	private String jobId = null;

	public RP_MAP_CHART_DETAILS_MacroJob(String jobId)
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
			RP_MAP_CHART_DETAILS_Main.runMSec = System.nanoTime();
		}

		if (Flag.TRUE) {
			/*
			 * 기존 자료 삭제
			 */
			AnalCsvBean bean = new AnalCsvBean();

			bean.setType(AnalCsvType.DELETE);
			bean.setTable("ANAL_CSV");
			bean.setWhere(String.format("JOB_ID = %s AND PROG_NM = %s", StrUtil.quote(jobId), StrUtil.quote(RP_MAP_CHART_DETAILS_Main.dsName)));

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
					{ "CMD_CODE   ", StrUtil.quote(RP_MAP_CHART_DETAILS_Main.dsName.substring(0, 3)) },
					{ "PROG_NM    ", StrUtil.quote(RP_MAP_CHART_DETAILS_Main.dsName) },
					{ "CSV_NM     ", StrUtil.quote(RP_MAP_CHART_DETAILS_Main.dsName + ".csv") },
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
				reader2 = new DEFECT_HIST_CsvIo(this.filePath);
				reader3 = new INSPCT_HIST_CsvIo(this.filePath);
				writer1 = new RP_MAP_CHART_DETAILS_CsvIo(this.filePath);
				
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
				/*
				 * Job
				 */
				Map<String, SO_SYS_CELLID_LIST2_Bean> mapCell = new LinkedHashMap<String, SO_SYS_CELLID_LIST2_Bean>();
				Map<String, DEFECT_HIST_Bean> mapDefect = new LinkedHashMap<String, DEFECT_HIST_Bean>();
				
				if (Flag.TRUE) {
					/*
					 * 기초 SO_SYS_CELLID_LIST2_Bean
					 */
					for (SO_SYS_CELLID_LIST2_Bean inBean1 : inList1) {
						if (!"BAD".equalsIgnoreCase(inBean1.getGroup()))
							continue;
						
						String key = inBean1.getCellId();
						mapCell.put(key, inBean1);
					}
					
					if (!Flag.TRUE) {
						/*
						 * 확인출력
						 */
						for (Map.Entry<String, SO_SYS_CELLID_LIST2_Bean> entry : mapCell.entrySet()) {
							System.out.println(entry.getValue());
						}
					}
				}
				
				if (Flag.TRUE) {
					/*
					 * 기초 DEFECT_HIST_Bean
					 */
					for (DEFECT_HIST_Bean inBean2 : inList2) {
						String key = inBean2.getCellId();
						mapDefect.put(key, inBean2);
					}
					
					if (!Flag.TRUE) {
						/*
						 * 확인출력
						 */
						for (Map.Entry<String, DEFECT_HIST_Bean> entry : mapDefect.entrySet()) {
							System.out.println(entry.getValue());
						}
					}
				}
				
				if (Flag.TRUE) {
					/*
					 * 처리
					 */
					
					for (INSPCT_HIST_Bean inBean3 : inList3) {
						
						String key = inBean3.getCellId();
						if (!Flag.TRUE) Print.println("[%s]", key);
						
						SO_SYS_CELLID_LIST2_Bean inBean1 = mapCell.get(key);
						if (!Flag.TRUE) System.out.println(">" + inBean1);
						
						
						DEFECT_HIST_Bean inBean2 = mapDefect.get(key);
						
						RP_MAP_CHART_DETAILS_Bean outBean1 = new RP_MAP_CHART_DETAILS_Bean();
						
						outBean1.setLotId          ("*");
						outBean1.setDefectGroupCode(inBean3.getDefectGroupCode());  // INSPCT_HIST_Bean
						outBean1.setDecisionCode   (inBean3.getDecisionCode   ());  // INSPCT_HIST_Bean
						outBean1.setInspectDt      (inBean3.getInspectDt      ());  // INSPCT_HIST_Bean
						outBean1.setGlassId        (inBean3.getGlassId        ());  // INSPCT_HIST_Bean
						outBean1.setCellId         (inBean3.getCellId         ());  // INSPCT_HIST_Bean
						
						if (!Flag.TRUE) System.out.println(">" + inBean1);

						if (inBean1 != null) {
							if ("0".equals(inBean1.getClusterId())) {
								outBean1.setGroupNum("X");
							} else {
								outBean1.setGroupNum(inBean1.getClusterId());
							}
							outBean1.setDefectSeq      (inBean1.getDefectSeq      ());  // SO_SYS_CELLID_LIST2_Bean
							outBean1.setXValue         (inBean1.getXValue         ());  // SO_SYS_CELLID_LIST2_Bean
							outBean1.setYValue         (inBean1.getYValue         ());  // SO_SYS_CELLID_LIST2_Bean
							outBean1.setDivCode        ("SYSTEMATIC");
						} else {
							outBean1.setGroupNum       ("X"                         );
							outBean1.setDefectSeq      (""                          );
							outBean1.setXValue         (""                          );
							outBean1.setYValue         (""                          );
							outBean1.setDivCode        ("RANDOM");
						}

						if (inBean2 != null) {
							outBean1.setImagePath      (inBean2.getImagepath      ());  // DEFECT_HIST_Bean
						} else {
							outBean1.setImagePath      (""                          );
						}

						outList1.add(outBean1);
					}
					
					if (!Flag.TRUE) {
						/*
						 * 확인출력
						 */
						for (RP_MAP_CHART_DETAILS_Bean bean : outList1) {
							System.out.println(bean);
						}
					}
				}
				
				if (Flag.TRUE) {
					/*
					 * SORT
					 *     DIV_CODE   DESC
					 *     GROUP_NUM  ASC
					 *     CELL_ID    ASC
					 */
					Collections.sort(outList1, new Comparator<RP_MAP_CHART_DETAILS_Bean>() {
						@Override
						public int compare(RP_MAP_CHART_DETAILS_Bean bean1, RP_MAP_CHART_DETAILS_Bean bean2) {
							int ret = 0;
							
							// 1. DIV_CODE  DESC
							ret = bean2.getDivCode().compareTo(bean1.getDivCode());
							if (ret != 0) return ret;

							// 2. GROUP_NUM
							int val1 = 99999;
							if (!"X".equals(bean1.getGroupNum()))
								val1 = Integer.parseInt(bean1.getGroupNum());
							
							int val2 = 99999;
							if (!"X".equals(bean2.getGroupNum()))
								val2 = Integer.parseInt(bean2.getGroupNum());
							
							int val = val1 - val2;
							if (val < 0)
								return -1;
							else if (val > 0)
								return 1;

							// 3. CELL_ID
							ret = bean1.getCellId().compareTo(bean2.getCellId());
							if (ret != 0) return ret;

							return ret;
						}
					});
					
					if (!Flag.TRUE) {
						/*
						 * 확인출력
						 */
						for (RP_MAP_CHART_DETAILS_Bean bean : outList1) {
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
				
				RP_MAP_CHART_DETAILS_Main.cntWList = outList1.size();

				writer1.writeList();
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		if (Flag.TRUE) {
			/*
			 * 시간기록 끝
			 */
			RP_MAP_CHART_DETAILS_Main.runMSec = (System.nanoTime() - RP_MAP_CHART_DETAILS_Main.runMSec) / 1000 / 1000;
			
			if (Flag.TRUE) Logger.info("##### took time : %d ms", RP_MAP_CHART_DETAILS_Main.runMSec);
		}

		if (Flag.TRUE) {
			/*
			 * UPDATE
			 */
			AnalCsvBean bean = new AnalCsvBean();
			
			bean.setType(AnalCsvType.UPDATE);
			bean.setTable("ANAL_CSV");
			bean.setFields(new String[][] {
					{ "LIST_CNT   ", "" + RP_MAP_CHART_DETAILS_Main.cntWList },
					{ "E_TIME     ", "SYSDATE" },
					{ "R_MSEC     ", "" + RP_MAP_CHART_DETAILS_Main.runMSec },
					{ "CSV_STATUS ", StrUtil.quote("OK") },   // OK, ERROR, SKIP, ETC...
					{ "LOG_MESSAGE", StrUtil.quote("COMPLETE") },  // COMPLETE, EXCEPTION
			});
			bean.setWhere(String.format("JOB_ID = %s AND PROG_NM = %s", StrUtil.quote(jobId), StrUtil.quote(RP_MAP_CHART_DETAILS_Main.dsName)));
			
			if (Flag.TRUE) Logger.info("[%s]", bean.getQuery());
			
			bean.sendToOracle();
		}
	}
}
