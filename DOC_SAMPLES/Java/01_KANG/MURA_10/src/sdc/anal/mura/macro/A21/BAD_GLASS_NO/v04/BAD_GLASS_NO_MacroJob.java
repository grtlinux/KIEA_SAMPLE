package sdc.anal.mura.macro.A21.BAD_GLASS_NO.v04;

import java.util.List;

import kiea.kr.co.tain.base.Flag;
import kiea.proj.sdc.anal.macro.impl.job.AbstractMacroJob;
import kiea.proj.sdc.anal.tools.AnalCsv.v04.AnalCsvBean;
import kiea.proj.sdc.anal.tools.AnalCsv.v04.AnalCsvType;
import kiea.proj.sdc.anal.util.BasePath;
import kiea.proj.sdc.anal.util.FileUtil;
import kiea.proj.sdc.anal.util.StrUtil;
import kiea.proj.sdc.anal.util.log.v02.Logger;

public class BAD_GLASS_NO_MacroJob extends AbstractMacroJob
{
	private BAD_GLASS_CsvIo reader1 = null;
	private BAD_GLASS_NO_CsvIo writer1 = null;
	
	private List<BAD_GLASS_Bean> inList1  = null;
	private List<BAD_GLASS_NO_Bean> outList1 = null;
	
	private String filePath = null;

	private String jobId = null;

	public BAD_GLASS_NO_MacroJob(String jobId)
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
			BAD_GLASS_NO_Main.runMSec = System.nanoTime();
		}

		if (Flag.TRUE) {
			/*
			 * 기존 자료 삭제
			 */
			AnalCsvBean bean = new AnalCsvBean();

			bean.setType(AnalCsvType.DELETE);
			bean.setTable("ANAL_CSV");
			bean.setWhere(String.format("JOB_ID = %s AND PROG_NM = %s", StrUtil.quote(jobId), StrUtil.quote(BAD_GLASS_NO_Main.dsName)));

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
					{ "CMD_CODE   ", StrUtil.quote(BAD_GLASS_NO_Main.dsName.substring(0, 3)) },
					{ "PROG_NM    ", StrUtil.quote(BAD_GLASS_NO_Main.dsName) },
					{ "CSV_NM     ", StrUtil.quote(BAD_GLASS_NO_Main.dsName + ".csv") },
					{ "MAIN_CLASS ", StrUtil.quote(this.getClass().getName()) },
					{ "S_TIME     ", "SYSDATE" },
					{ "CSV_STATUS ", StrUtil.quote("START") },
			});
			if (Flag.TRUE) Logger.info("[%s]", bean.getQuery());

			bean.sendToOracle();
		}

		if (Flag.TRUE) {
			try {
				reader1 = new BAD_GLASS_CsvIo(this.filePath);
				writer1 = new BAD_GLASS_NO_CsvIo(this.filePath);
				
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
					int dataNum = 0;
					
					for (BAD_GLASS_Bean inBean1 : inList1) {
						
						dataNum ++;
						
						BAD_GLASS_NO_Bean outBean1 = new BAD_GLASS_NO_Bean();

						outBean1.setDataNum      ("" + dataNum);
						outBean1.setCellId       (inBean1.getCellId       ());
						outBean1.setGlassId      (inBean1.getGlassId      ());
						outBean1.setSiteId       (inBean1.getSiteId       ());
						outBean1.setProdGrpName  (inBean1.getProdGrpName  ());
						outBean1.setProcId       (inBean1.getProcId       ());
						outBean1.setItemId       (inBean1.getItemId       ());
						outBean1.setSubItemId    (inBean1.getSubItemId    ());
						outBean1.setDefectName   (inBean1.getDefectName   ());
						outBean1.setItemName     (inBean1.getItemName     ());
						outBean1.setMeasEqpUnitId(inBean1.getMeasEqpUnitId());
						outBean1.setMuraData     (inBean1.getMuraData     ());
						outBean1.setXValue       (inBean1.getXValue       ());
						outBean1.setYValue       (inBean1.getYValue       ());
						outBean1.setX2Value      (inBean1.getX2Value      ());
						outBean1.setY2Value      (inBean1.getY2Value      ());
						outBean1.setAvgX         (inBean1.getAvgX         ());
						outBean1.setAvgY         (inBean1.getAvgY         ());
						outBean1.setGateLine_1   (inBean1.getGateLine_1   ());
						outBean1.setGateLine_2   (inBean1.getGateLine_2   ());
						outBean1.setDataLine_1   (inBean1.getDataLine_1   ());
						outBean1.setDataLine_2   (inBean1.getDataLine_2   ());
						outBean1.setTarget       (inBean1.getTarget       ());
						outBean1.setCellPosition (inBean1.getCellPosition ());

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
				
				BAD_GLASS_NO_Main.cntWList = outList1.size();

				writer1.writeList();
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		if (Flag.TRUE) {
			/*
			 * 시간기록 끝
			 */
			BAD_GLASS_NO_Main.runMSec = (System.nanoTime() - BAD_GLASS_NO_Main.runMSec) / 1000 / 1000;
			
			if (Flag.TRUE) Logger.info("##### took time : %d ms", BAD_GLASS_NO_Main.runMSec);
		}

		if (Flag.TRUE) {
			/*
			 * UPDATE
			 */
			AnalCsvBean bean = new AnalCsvBean();
			
			bean.setType(AnalCsvType.UPDATE);
			bean.setTable("ANAL_CSV");
			bean.setFields(new String[][] {
					{ "LIST_CNT   ", "" + BAD_GLASS_NO_Main.cntWList },
					{ "E_TIME     ", "SYSDATE" },
					{ "R_MSEC     ", "" + BAD_GLASS_NO_Main.runMSec },
					{ "CSV_STATUS ", StrUtil.quote("OK") },   // OK, ERROR, SKIP, ETC...
					{ "LOG_MESSAGE", StrUtil.quote("COMPLETE") },  // COMPLETE, EXCEPTION
			});
			bean.setWhere(String.format("JOB_ID = %s AND PROG_NM = %s", StrUtil.quote(jobId), StrUtil.quote(BAD_GLASS_NO_Main.dsName)));
			
			if (Flag.TRUE) Logger.info("[%s]", bean.getQuery());
			
			bean.sendToOracle();
		}
	}
}
