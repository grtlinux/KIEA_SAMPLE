package sdc.anal.lya.macro.A24.EQP_FDC_SUM03.v04;

import java.util.List;
import java.util.Random;

import kiea.kr.co.tain.base.Flag;
import kiea.proj.sdc.anal.macro.impl.job.AbstractMacroJob;
import kiea.proj.sdc.anal.tools.AnalCsv.v04.AnalCsvBean;
import kiea.proj.sdc.anal.tools.AnalCsv.v04.AnalCsvType;
import kiea.proj.sdc.anal.util.BasePath;
import kiea.proj.sdc.anal.util.FileUtil;
import kiea.proj.sdc.anal.util.StrUtil;
import kiea.proj.sdc.anal.util.log.v02.Logger;

public class EQP_FDC_SUM03_MacroJob extends AbstractMacroJob
{
	private EQP_FDC_SUM02_CsvIo reader1 = null;
	private EQP_FDC_SUM03_CsvIo writer1 = null;
	
	private List<EQP_FDC_SUM02_Bean> inList1  = null;
	private List<EQP_FDC_SUM03_Bean> outList1 = null;
	
	private String filePath = null;

	private String jobId = null;

	public EQP_FDC_SUM03_MacroJob(String jobId)
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
			EQP_FDC_SUM03_Main.runMSec = System.nanoTime();
		}

		if (Flag.TRUE) {
			/*
			 * 기존 자료 삭제
			 */
			AnalCsvBean bean = new AnalCsvBean();

			bean.setType(AnalCsvType.DELETE);
			bean.setTable("ANAL_CSV");
			bean.setWhere(String.format("JOB_ID = %s AND PROG_NM = %s", StrUtil.quote(jobId), StrUtil.quote(EQP_FDC_SUM03_Main.dsName)));

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
					{ "CMD_CODE   ", StrUtil.quote(EQP_FDC_SUM03_Main.dsName.substring(0, 3)) },
					{ "PROG_NM    ", StrUtil.quote(EQP_FDC_SUM03_Main.dsName) },
					{ "CSV_NM     ", StrUtil.quote(EQP_FDC_SUM03_Main.dsName + ".csv") },
					{ "MAIN_CLASS ", StrUtil.quote(this.getClass().getName()) },
					{ "S_TIME     ", "SYSDATE" },
					{ "CSV_STATUS ", StrUtil.quote("START") },
			});
			if (Flag.TRUE) Logger.info("[%s]", bean.getQuery());

			bean.sendToOracle();
		}

		if (Flag.TRUE) {
			try {
				reader1 = new EQP_FDC_SUM02_CsvIo(this.filePath);
				writer1 = new EQP_FDC_SUM03_CsvIo(this.filePath);
				
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
					/*
					 * 해당자료만 추출한다.
					 */
					for (EQP_FDC_SUM02_Bean inBean1 : inList1) {
						
						EQP_FDC_SUM03_Bean outBean1 = new EQP_FDC_SUM03_Bean();

						outBean1.setClusterId  (inBean1.getClusterId  ());
						outBean1.setPart       (inBean1.getPart       ());
						outBean1.setEqpId      (inBean1.getEqpId      ());
						outBean1.setSensorName (inBean1.getSensorName ());
						outBean1.setParamValue (inBean1.getParamValue ());
						outBean1.setGlassId    (inBean1.getGlassId    ());
						outBean1.setGroupId    (inBean1.getGroupId    ());

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
				
				EQP_FDC_SUM03_Main.cntWList = outList1.size();

				writer1.writeList();
				
			} catch (Exception e) {
				e.printStackTrace();
			}

			if (Flag.TRUE) {
				/*
				 * 시간기록 끝
				 */
				EQP_FDC_SUM03_Main.runMSec = (System.nanoTime() - EQP_FDC_SUM03_Main.runMSec) / 1000 / 1000;
				
				if (Flag.TRUE) Logger.info("##### took time : %d ms", EQP_FDC_SUM03_Main.runMSec);
			}

			if (Flag.TRUE) {
				/*
				 * UPDATE
				 */
				AnalCsvBean bean = new AnalCsvBean();
				
				bean.setType(AnalCsvType.UPDATE);
				bean.setTable("ANAL_CSV");
				bean.setFields(new String[][] {
						{ "LIST_CNT   ", "" + EQP_FDC_SUM03_Main.cntWList },
						{ "E_TIME     ", "SYSDATE" },
						{ "R_MSEC     ", "" + EQP_FDC_SUM03_Main.runMSec },
						{ "CSV_STATUS ", StrUtil.quote("OK") },   // OK, ERROR, SKIP, ETC...
						{ "LOG_MESSAGE", StrUtil.quote("COMPLETE") },  // COMPLETE, EXCEPTION
				});
				bean.setWhere(String.format("JOB_ID = %s AND PROG_NM = %s", StrUtil.quote(jobId), StrUtil.quote(EQP_FDC_SUM03_Main.dsName)));
				
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
	
	public static void main(String[] args)
	{
		if (Flag.TRUE) test01();
	}
}
