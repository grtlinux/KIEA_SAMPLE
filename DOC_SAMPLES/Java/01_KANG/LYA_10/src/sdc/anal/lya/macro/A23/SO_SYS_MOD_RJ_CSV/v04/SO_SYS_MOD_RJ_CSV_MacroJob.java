package sdc.anal.lya.macro.A23.SO_SYS_MOD_RJ_CSV.v04;

import java.util.List;

import kiea.kr.co.tain.base.Flag;
import kiea.proj.sdc.anal.macro.impl.job.AbstractMacroJob;
import kiea.proj.sdc.anal.tools.AnalCsv.v04.AnalCsvBean;
import kiea.proj.sdc.anal.tools.AnalCsv.v04.AnalCsvType;
import kiea.proj.sdc.anal.util.BasePath;
import kiea.proj.sdc.anal.util.FileUtil;
import kiea.proj.sdc.anal.util.StrUtil;
import kiea.proj.sdc.anal.util.log.v02.Logger;

public class SO_SYS_MOD_RJ_CSV_MacroJob extends AbstractMacroJob
{
	private SO_SYS_MOD_RJ_ANL_CsvIo reader1 = null;
	private SO_SYS_MOD_RJ_CSV_CsvIo writer1 = null;
	
	private List<SO_SYS_MOD_RJ_ANL_Bean> inList1  = null;
	private List<SO_SYS_MOD_RJ_CSV_Bean> outList1 = null;
	
	private String filePath = null;

	private String jobId = null;

	public SO_SYS_MOD_RJ_CSV_MacroJob(String jobId)
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
			SO_SYS_MOD_RJ_CSV_Main.runMSec = System.nanoTime();
		}

		if (Flag.TRUE) {
			/*
			 * 기존 자료 삭제
			 */
			AnalCsvBean bean = new AnalCsvBean();

			bean.setType(AnalCsvType.DELETE);
			bean.setTable("ANAL_CSV");
			bean.setWhere(String.format("JOB_ID = %s AND PROG_NM = %s", StrUtil.quote(jobId), StrUtil.quote(SO_SYS_MOD_RJ_CSV_Main.dsName)));

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
					{ "CMD_CODE   ", StrUtil.quote(SO_SYS_MOD_RJ_CSV_Main.dsName.substring(0, 3)) },
					{ "PROG_NM    ", StrUtil.quote(SO_SYS_MOD_RJ_CSV_Main.dsName) },
					{ "CSV_NM     ", StrUtil.quote(SO_SYS_MOD_RJ_CSV_Main.dsName + ".csv") },
					{ "MAIN_CLASS ", StrUtil.quote(this.getClass().getName()) },
					{ "S_TIME     ", "SYSDATE" },
					{ "CSV_STATUS ", StrUtil.quote("START") },
			});
			if (Flag.TRUE) Logger.info("[%s]", bean.getQuery());

			bean.sendToOracle();
		}

		if (Flag.TRUE) {
			try {
				reader1 = new SO_SYS_MOD_RJ_ANL_CsvIo(this.filePath);
				writer1 = new SO_SYS_MOD_RJ_CSV_CsvIo(this.filePath);
				
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
				if (Flag.TRUE) {
					
					for (SO_SYS_MOD_RJ_ANL_Bean inBean1 : inList1) {

						SO_SYS_MOD_RJ_CSV_Bean outBean1 = new SO_SYS_MOD_RJ_CSV_Bean();
						
						outBean1.setCellId      (inBean1.getCellId      ());
						outBean1.setInspStepType(inBean1.getInspStepType());
						outBean1.setDefectName  (inBean1.getDefectName  ());
						outBean1.setDefectCode  (inBean1.getDefectCode  ());
						outBean1.setInspStepId  (inBean1.getInspStepId  ());
						outBean1.setInspTime    (inBean1.getInspTime    ());
						outBean1.setWorkerName  (inBean1.getWorkerName  ());
						outBean1.setWorkerId    (inBean1.getWorkerId    ());
						outBean1.setInspEqpId   (inBean1.getInspEqpId   ());

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
				
				SO_SYS_MOD_RJ_CSV_Main.cntWList = outList1.size();

				writer1.writeList();
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		if (Flag.TRUE) {
			/*
			 * 시간기록 끝
			 */
			SO_SYS_MOD_RJ_CSV_Main.runMSec = (System.nanoTime() - SO_SYS_MOD_RJ_CSV_Main.runMSec) / 1000 / 1000;
			
			if (Flag.TRUE) Logger.info("##### took time : %d ms", SO_SYS_MOD_RJ_CSV_Main.runMSec);
		}

		if (Flag.TRUE) {
			/*
			 * UPDATE
			 */
			AnalCsvBean bean = new AnalCsvBean();
			
			bean.setType(AnalCsvType.UPDATE);
			bean.setTable("ANAL_CSV");
			bean.setFields(new String[][] {
					{ "LIST_CNT   ", "" + SO_SYS_MOD_RJ_CSV_Main.cntWList },
					{ "E_TIME     ", "SYSDATE" },
					{ "R_MSEC     ", "" + SO_SYS_MOD_RJ_CSV_Main.runMSec },
					{ "CSV_STATUS ", StrUtil.quote("OK") },   // OK, ERROR, SKIP, ETC...
					{ "LOG_MESSAGE", StrUtil.quote("COMPLETE") },  // COMPLETE, EXCEPTION
			});
			bean.setWhere(String.format("JOB_ID = %s AND PROG_NM = %s", StrUtil.quote(jobId), StrUtil.quote(SO_SYS_MOD_RJ_CSV_Main.dsName)));
			
			if (Flag.TRUE) Logger.info("[%s]", bean.getQuery());
			
			bean.sendToOracle();
		}
	}
}
