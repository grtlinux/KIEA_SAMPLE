package sdc.anal.mura.macro.A22.AVG_MURA_MAP_XY_4SF_RESULT.v04;

import java.util.List;

import kiea.kr.co.tain.base.Flag;
import kiea.proj.sdc.anal.macro.impl.job.AbstractMacroJob;
import kiea.proj.sdc.anal.tools.AnalCsv.v04.AnalCsvBean;
import kiea.proj.sdc.anal.tools.AnalCsv.v04.AnalCsvType;
import kiea.proj.sdc.anal.util.BasePath;
import kiea.proj.sdc.anal.util.FileUtil;
import kiea.proj.sdc.anal.util.StrUtil;
import kiea.proj.sdc.anal.util.log.v02.Logger;

public class AVG_MURA_MAP_XY_4SF_RESULT_MacroJob extends AbstractMacroJob
{
	private MURA_MAP_XY_RESULT_CsvIo reader1 = null;
	private AVG_MURA_MAP_XY_4SF_RESULT_CsvIo writer1 = null;
	
	private List<MURA_MAP_XY_RESULT_Bean> inList1  = null;
	private List<AVG_MURA_MAP_XY_4SF_RESULT_Bean> outList1 = null;
	
	private String filePath = null;

	private String jobId = null;

	public AVG_MURA_MAP_XY_4SF_RESULT_MacroJob(String jobId)
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
			AVG_MURA_MAP_XY_4SF_RESULT_Main.runMSec = System.nanoTime();
		}

		if (Flag.TRUE) {
			/*
			 * 기존 자료 삭제
			 */
			AnalCsvBean bean = new AnalCsvBean();

			bean.setType(AnalCsvType.DELETE);
			bean.setTable("ANAL_CSV");
			bean.setWhere(String.format("JOB_ID = %s AND PROG_NM = %s", StrUtil.quote(jobId), StrUtil.quote(AVG_MURA_MAP_XY_4SF_RESULT_Main.dsName)));

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
					{ "CMD_CODE   ", StrUtil.quote(AVG_MURA_MAP_XY_4SF_RESULT_Main.dsName.substring(0, 3)) },
					{ "PROG_NM    ", StrUtil.quote(AVG_MURA_MAP_XY_4SF_RESULT_Main.dsName) },
					{ "CSV_NM     ", StrUtil.quote(AVG_MURA_MAP_XY_4SF_RESULT_Main.dsName + ".csv") },
					{ "MAIN_CLASS ", StrUtil.quote(this.getClass().getName()) },
					{ "S_TIME     ", "SYSDATE" },
					{ "CSV_STATUS ", StrUtil.quote("START") },
			});
			if (Flag.TRUE) Logger.info("[%s]", bean.getQuery());

			bean.sendToOracle();
		}

		if (Flag.TRUE) {
			try {
				reader1 = new MURA_MAP_XY_RESULT_CsvIo(this.filePath);
				writer1 = new AVG_MURA_MAP_XY_4SF_RESULT_CsvIo(this.filePath);
				
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
					
					for (MURA_MAP_XY_RESULT_Bean inBean1 : inList1) {

						if (Flag.TRUE) {
							AVG_MURA_MAP_XY_4SF_RESULT_Bean outBean1 = new AVG_MURA_MAP_XY_4SF_RESULT_Bean();
							
							outBean1.setClusterNo   (inBean1.getClusterNo   ());
							outBean1.setSeq         ("1");
							outBean1.setSiteId      (inBean1.getSiteId      ());
							outBean1.setProdGrpName (inBean1.getProdGrpName ());
							outBean1.setProcId      (inBean1.getProcId      ());
							outBean1.setCellPosition(inBean1.getCellPosition());
							outBean1.setItemId      (inBean1.getItemId      ());
							outBean1.setItemName    (inBean1.getItemName    ());
							outBean1.setAvgX        (inBean1.getAvgX1       ());
							outBean1.setAvgY        (inBean1.getAvgY1       ());

							outList1.add(outBean1);
						}

						if (Flag.TRUE) {
							AVG_MURA_MAP_XY_4SF_RESULT_Bean outBean1 = new AVG_MURA_MAP_XY_4SF_RESULT_Bean();
							
							outBean1.setClusterNo   (inBean1.getClusterNo   ());
							outBean1.setSeq         ("2");
							outBean1.setSiteId      (inBean1.getSiteId      ());
							outBean1.setProdGrpName (inBean1.getProdGrpName ());
							outBean1.setProcId      (inBean1.getProcId      ());
							outBean1.setCellPosition(inBean1.getCellPosition());
							outBean1.setItemId      (inBean1.getItemId      ());
							outBean1.setItemName    (inBean1.getItemName    ());
							outBean1.setAvgX        ("" + (Double.parseDouble(inBean1.getAvgX1()) + Double.parseDouble(inBean1.getWidth ())));
							outBean1.setAvgY        (inBean1.getAvgY1       ());

							outList1.add(outBean1);
						}

						if (Flag.TRUE) {
							AVG_MURA_MAP_XY_4SF_RESULT_Bean outBean1 = new AVG_MURA_MAP_XY_4SF_RESULT_Bean();
							
							outBean1.setClusterNo   (inBean1.getClusterNo   ());
							outBean1.setSeq         ("3");
							outBean1.setSiteId      (inBean1.getSiteId      ());
							outBean1.setProdGrpName (inBean1.getProdGrpName ());
							outBean1.setProcId      (inBean1.getProcId      ());
							outBean1.setCellPosition(inBean1.getCellPosition());
							outBean1.setItemId      (inBean1.getItemId      ());
							outBean1.setItemName    (inBean1.getItemName    ());
							outBean1.setAvgX        ("" + (Double.parseDouble(inBean1.getAvgX1()) + Double.parseDouble(inBean1.getWidth ())));
							outBean1.setAvgY        ("" + (Double.parseDouble(inBean1.getAvgY1()) + Double.parseDouble(inBean1.getHeight())));

							outList1.add(outBean1);
						}

						if (Flag.TRUE) {
							AVG_MURA_MAP_XY_4SF_RESULT_Bean outBean1 = new AVG_MURA_MAP_XY_4SF_RESULT_Bean();
							
							outBean1.setClusterNo   (inBean1.getClusterNo   ());
							outBean1.setSeq         ("4");
							outBean1.setSiteId      (inBean1.getSiteId      ());
							outBean1.setProdGrpName (inBean1.getProdGrpName ());
							outBean1.setProcId      (inBean1.getProcId      ());
							outBean1.setCellPosition(inBean1.getCellPosition());
							outBean1.setItemId      (inBean1.getItemId      ());
							outBean1.setItemName    (inBean1.getItemName    ());
							outBean1.setAvgX        (inBean1.getAvgX1       ());
							outBean1.setAvgY        ("" + (Double.parseDouble(inBean1.getAvgY1()) + Double.parseDouble(inBean1.getHeight())));

							outList1.add(outBean1);
						}

						if (Flag.TRUE) {
							AVG_MURA_MAP_XY_4SF_RESULT_Bean outBean1 = new AVG_MURA_MAP_XY_4SF_RESULT_Bean();
							
							outBean1.setClusterNo   (inBean1.getClusterNo   ());
							outBean1.setSeq         ("5");
							outBean1.setSiteId      (inBean1.getSiteId      ());
							outBean1.setProdGrpName (inBean1.getProdGrpName ());
							outBean1.setProcId      (inBean1.getProcId      ());
							outBean1.setCellPosition(inBean1.getCellPosition());
							outBean1.setItemId      (inBean1.getItemId      ());
							outBean1.setItemName    (inBean1.getItemName    ());
							outBean1.setAvgX        (inBean1.getAvgX1       ());
							outBean1.setAvgY        (inBean1.getAvgY1       ());

							outList1.add(outBean1);
						}

						if (Flag.TRUE) {
							AVG_MURA_MAP_XY_4SF_RESULT_Bean outBean1 = new AVG_MURA_MAP_XY_4SF_RESULT_Bean();
							
							outBean1.setClusterNo   (inBean1.getClusterNo   ());
							outBean1.setSeq         ("6");
							outBean1.setSiteId      (inBean1.getSiteId      ());
							outBean1.setProdGrpName (inBean1.getProdGrpName ());
							outBean1.setProcId      (inBean1.getProcId      ());
							outBean1.setCellPosition(inBean1.getCellPosition());
							outBean1.setItemId      (inBean1.getItemId      ());
							outBean1.setItemName    (inBean1.getItemName    ());
							outBean1.setAvgX        ("");
							outBean1.setAvgY        ("");

							outList1.add(outBean1);
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
				
				AVG_MURA_MAP_XY_4SF_RESULT_Main.cntWList = outList1.size();

				writer1.writeList();
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		if (Flag.TRUE) {
			/*
			 * 시간기록 끝
			 */
			AVG_MURA_MAP_XY_4SF_RESULT_Main.runMSec = (System.nanoTime() - AVG_MURA_MAP_XY_4SF_RESULT_Main.runMSec) / 1000 / 1000;
			
			if (Flag.TRUE) Logger.info("##### took time : %d ms", AVG_MURA_MAP_XY_4SF_RESULT_Main.runMSec);
		}

		if (Flag.TRUE) {
			/*
			 * UPDATE
			 */
			AnalCsvBean bean = new AnalCsvBean();
			
			bean.setType(AnalCsvType.UPDATE);
			bean.setTable("ANAL_CSV");
			bean.setFields(new String[][] {
					{ "LIST_CNT   ", "" + AVG_MURA_MAP_XY_4SF_RESULT_Main.cntWList },
					{ "E_TIME     ", "SYSDATE" },
					{ "R_MSEC     ", "" + AVG_MURA_MAP_XY_4SF_RESULT_Main.runMSec },
					{ "CSV_STATUS ", StrUtil.quote("OK") },   // OK, ERROR, SKIP, ETC...
					{ "LOG_MESSAGE", StrUtil.quote("COMPLETE") },  // COMPLETE, EXCEPTION
			});
			bean.setWhere(String.format("JOB_ID = %s AND PROG_NM = %s", StrUtil.quote(jobId), StrUtil.quote(AVG_MURA_MAP_XY_4SF_RESULT_Main.dsName)));
			
			if (Flag.TRUE) Logger.info("[%s]", bean.getQuery());
			
			bean.sendToOracle();
		}
	}
}
