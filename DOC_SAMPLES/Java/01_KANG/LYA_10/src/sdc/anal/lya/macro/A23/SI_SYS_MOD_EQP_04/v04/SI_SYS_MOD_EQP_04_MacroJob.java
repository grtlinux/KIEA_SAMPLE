package sdc.anal.lya.macro.A23.SI_SYS_MOD_EQP_04.v04;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import kiea.kr.co.tain.base.Flag;
import kiea.proj.sdc.anal.common.Parameter;
import kiea.proj.sdc.anal.macro.impl.job.AbstractMacroJob;
import kiea.proj.sdc.anal.tools.AnalCsv.v04.AnalCsvBean;
import kiea.proj.sdc.anal.tools.AnalCsv.v04.AnalCsvType;
import kiea.proj.sdc.anal.util.BasePath;
import kiea.proj.sdc.anal.util.FileUtil;
import kiea.proj.sdc.anal.util.StrUtil;
import kiea.proj.sdc.anal.util.log.v02.Logger;

public class SI_SYS_MOD_EQP_04_MacroJob extends AbstractMacroJob
{
	private SI_SYS_MOD_EQP_01_CsvIo reader1 = null;
	private SI_SYS_MOD_EQP_04_CsvIo writer1 = null;
	
	private List<SI_SYS_MOD_EQP_01_Bean> inList1  = null;
	private List<SI_SYS_MOD_EQP_04_Bean> outList1 = null;
	
	private String filePath = null;

	private String jobId = null;

	public SI_SYS_MOD_EQP_04_MacroJob(String jobId)
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
			SI_SYS_MOD_EQP_04_Main.runMSec = System.nanoTime();
		}

		if (Flag.TRUE) {
			/*
			 * 기존 자료 삭제
			 */
			AnalCsvBean bean = new AnalCsvBean();

			bean.setType(AnalCsvType.DELETE);
			bean.setTable("ANAL_CSV");
			bean.setWhere(String.format("JOB_ID = %s AND PROG_NM = %s", StrUtil.quote(jobId), StrUtil.quote(SI_SYS_MOD_EQP_04_Main.dsName)));

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
					{ "CMD_CODE   ", StrUtil.quote(SI_SYS_MOD_EQP_04_Main.dsName.substring(0, 3)) },
					{ "PROG_NM    ", StrUtil.quote(SI_SYS_MOD_EQP_04_Main.dsName) },
					{ "CSV_NM     ", StrUtil.quote(SI_SYS_MOD_EQP_04_Main.dsName + ".csv") },
					{ "MAIN_CLASS ", StrUtil.quote(this.getClass().getName()) },
					{ "S_TIME     ", "SYSDATE" },
					{ "CSV_STATUS ", StrUtil.quote("START") },
			});
			if (Flag.TRUE) Logger.info("[%s]", bean.getQuery());

			bean.sendToOracle();
		}

		if (Flag.TRUE) {
			try {
				reader1 = new SI_SYS_MOD_EQP_01_CsvIo(this.filePath);
				writer1 = new SI_SYS_MOD_EQP_04_CsvIo(this.filePath);
				
				inList1  = reader1.getList();
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
				String strDecisionCode = Parameter.getInstance().getStrDecisionCode();

				Map<String, List<SI_SYS_MOD_EQP_01_Bean>> map = new LinkedHashMap<String, List<SI_SYS_MOD_EQP_01_Bean>>();
				List<SI_SYS_MOD_EQP_01_Bean> list = null;
				
				if (Flag.TRUE) {
					for (SI_SYS_MOD_EQP_01_Bean inBean1 : inList1) {
						
						String key = inBean1.getStepId() + ":" + inBean1.getEqpId() + ":" + inBean1.getInspectHour2();
						list = map.get(key);
						if (list == null) {
							list = new ArrayList<SI_SYS_MOD_EQP_01_Bean>();
							map.put(key, list);
						}
						
						list.add(inBean1);
					}
				}
				
				if (Flag.TRUE) {
					for (Map.Entry<String, List<SI_SYS_MOD_EQP_01_Bean>> entry : map.entrySet()) {
						list = entry.getValue();
						
						SI_SYS_MOD_EQP_01_Bean inBean = list.get(0);
						int totCnt = list.size();
						int badCnt = 0;
						int goodCnt = 0;
						for (SI_SYS_MOD_EQP_01_Bean inBean1 : list) {
							if (strDecisionCode.indexOf(StrUtil.quote(inBean1.getDecisionCode())) >= 0) {
								badCnt ++;
							} else {
								goodCnt ++;
							}
						}
						
						SI_SYS_MOD_EQP_04_Bean outBean1 = new SI_SYS_MOD_EQP_04_Bean();
						
						outBean1.setStepId      (inBean.getStepId ());
						outBean1.setEqpId       (inBean.getEqpId  ());
						outBean1.setInspectHour3(inBean.getInspectHour2());
						outBean1.setTotCnt      ("" + totCnt);
						outBean1.setBadCnt      ("" + badCnt);
						outBean1.setGoodCnt     ("" + goodCnt);

						outList1.add(outBean1);
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
				
				SI_SYS_MOD_EQP_04_Main.cntWList = outList1.size();

				writer1.writeList();
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		if (Flag.TRUE) {
			/*
			 * 시간기록 끝
			 */
			SI_SYS_MOD_EQP_04_Main.runMSec = (System.nanoTime() - SI_SYS_MOD_EQP_04_Main.runMSec) / 1000 / 1000;
			
			if (Flag.TRUE) Logger.info("##### took time : %d ms", SI_SYS_MOD_EQP_04_Main.runMSec);
		}

		if (Flag.TRUE) {
			/*
			 * UPDATE
			 */
			AnalCsvBean bean = new AnalCsvBean();
			
			bean.setType(AnalCsvType.UPDATE);
			bean.setTable("ANAL_CSV");
			bean.setFields(new String[][] {
					{ "LIST_CNT   ", "" + SI_SYS_MOD_EQP_04_Main.cntWList },
					{ "E_TIME     ", "SYSDATE" },
					{ "R_MSEC     ", "" + SI_SYS_MOD_EQP_04_Main.runMSec },
					{ "CSV_STATUS ", StrUtil.quote("OK") },   // OK, ERROR, SKIP, ETC...
					{ "LOG_MESSAGE", StrUtil.quote("COMPLETE") },  // COMPLETE, EXCEPTION
			});
			bean.setWhere(String.format("JOB_ID = %s AND PROG_NM = %s", StrUtil.quote(jobId), StrUtil.quote(SI_SYS_MOD_EQP_04_Main.dsName)));
			
			if (Flag.TRUE) Logger.info("[%s]", bean.getQuery());
			
			bean.sendToOracle();
		}
	}
}
