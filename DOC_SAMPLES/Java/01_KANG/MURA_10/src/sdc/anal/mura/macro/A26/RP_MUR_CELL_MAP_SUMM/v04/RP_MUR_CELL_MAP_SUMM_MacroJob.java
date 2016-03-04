package sdc.anal.mura.macro.A26.RP_MUR_CELL_MAP_SUMM.v04;

import java.util.List;

import kiea.kr.co.tain.base.Flag;
import kiea.proj.sdc.anal.macro.impl.job.AbstractMacroJob;
import kiea.proj.sdc.anal.tools.AnalCsv.v04.AnalCsvBean;
import kiea.proj.sdc.anal.tools.AnalCsv.v04.AnalCsvType;
import kiea.proj.sdc.anal.util.BasePath;
import kiea.proj.sdc.anal.util.FileUtil;
import kiea.proj.sdc.anal.util.StrUtil;
import kiea.proj.sdc.anal.util.log.v02.Logger;

public class RP_MUR_CELL_MAP_SUMM_MacroJob extends AbstractMacroJob
{
	private CELL_MAP_XY_CsvIo     reader1 = null;
	private RP_MUR_CELL_MAP_SUMM_CsvIo  writer1 = null;
	
	private List<CELL_MAP_XY_Bean>     inList1  = null;
	private List<RP_MUR_CELL_MAP_SUMM_Bean>  outList1 = null;
	
	private String filePath = null;

	private String jobId = null;

	public RP_MUR_CELL_MAP_SUMM_MacroJob(String jobId)
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
			RP_MUR_CELL_MAP_SUMM_Main.runMSec = System.nanoTime();
		}

		if (Flag.TRUE) {
			/*
			 * 기존 자료 삭제
			 */
			AnalCsvBean bean = new AnalCsvBean();

			bean.setType(AnalCsvType.DELETE);
			bean.setTable("ANAL_CSV");
			bean.setWhere(String.format("JOB_ID = %s AND PROG_NM = %s", StrUtil.quote(jobId), StrUtil.quote(RP_MUR_CELL_MAP_SUMM_Main.dsName)));

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
					{ "CMD_CODE   ", StrUtil.quote(RP_MUR_CELL_MAP_SUMM_Main.dsName.substring(0, 3)) },
					{ "PROG_NM    ", StrUtil.quote(RP_MUR_CELL_MAP_SUMM_Main.dsName) },
					{ "CSV_NM     ", StrUtil.quote(RP_MUR_CELL_MAP_SUMM_Main.dsName + ".csv") },
					{ "MAIN_CLASS ", StrUtil.quote(this.getClass().getName()) },
					{ "S_TIME     ", "SYSDATE" },
					{ "CSV_STATUS ", StrUtil.quote("START") },
			});
			if (Flag.TRUE) Logger.info("[%s]", bean.getQuery());

			bean.sendToOracle();
		}

		if (Flag.TRUE) {
			try {
				reader1 = new CELL_MAP_XY_CsvIo    (this.filePath);
				writer1 = new RP_MUR_CELL_MAP_SUMM_CsvIo (this.filePath);
				
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
				 * 1. InBean -> OutBean
				 */
				if (Flag.TRUE) {

					for (CELL_MAP_XY_Bean inBean1 : inList1) {
						
						if (Flag.TRUE) {
							RP_MUR_CELL_MAP_SUMM_Bean outBean1 = new RP_MUR_CELL_MAP_SUMM_Bean();
							
							outBean1.setCellNo   (inBean1.getCellNo   ());
							outBean1.setProcId   (inBean1.getProcId   ());
							outBean1.setCellLocId(inBean1.getCellLocId());
							outBean1.setPointX   (inBean1.getPointX   ());
							outBean1.setPointY   (inBean1.getPointY   ());
							outBean1.setWidth    (inBean1.getWidth    ());
							outBean1.setHeight   (inBean1.getHeight   ());
							outBean1.setPointX2  (inBean1.getPointX2  ());
							outBean1.setPointY2  (inBean1.getPointY2  ());
							outBean1.setSeq      (inBean1.getCellNo   ());

							outBean1.setPointX2  ("" + (Integer.parseInt(inBean1.getPointX()) + Integer.parseInt(inBean1.getWidth ())));
							outBean1.setPointY2  ("" + (Integer.parseInt(inBean1.getPointY()) + Integer.parseInt(inBean1.getHeight())));
							
							outList1.add(outBean1);
						}
						
						if (Flag.TRUE) {
							RP_MUR_CELL_MAP_SUMM_Bean outBean1 = new RP_MUR_CELL_MAP_SUMM_Bean();
							
							outBean1.setCellNo   (inBean1.getCellNo   ());
							outBean1.setProcId   (inBean1.getProcId   ());
							outBean1.setCellLocId(inBean1.getCellLocId());
							outBean1.setPointX   (inBean1.getPointX   ());
							outBean1.setPointY   ("" + (Integer.parseInt(inBean1.getPointY()) + Integer.parseInt(inBean1.getHeight())));
							outBean1.setWidth    (inBean1.getWidth    ());
							outBean1.setHeight   (inBean1.getHeight   ());
							outBean1.setPointX2  (inBean1.getPointX2  ());
							outBean1.setPointY2  (inBean1.getPointY2  ());
							outBean1.setSeq      (inBean1.getCellNo   ());
							
							outList1.add(outBean1);
						}

						if (Flag.TRUE) {
							RP_MUR_CELL_MAP_SUMM_Bean outBean1 = new RP_MUR_CELL_MAP_SUMM_Bean();
							
							outBean1.setCellNo   (inBean1.getCellNo   ());
							outBean1.setProcId   (inBean1.getProcId   ());
							outBean1.setCellLocId(inBean1.getCellLocId());
							outBean1.setPointX   ("" + (Integer.parseInt(inBean1.getPointX()) + Integer.parseInt(inBean1.getWidth ())));
							outBean1.setPointY   ("" + (Integer.parseInt(inBean1.getPointY()) + Integer.parseInt(inBean1.getHeight())));
							outBean1.setWidth    (inBean1.getWidth    ());
							outBean1.setHeight   (inBean1.getHeight   ());
							outBean1.setPointX2  (inBean1.getPointX2  ());
							outBean1.setPointY2  (inBean1.getPointY2  ());
							outBean1.setSeq      (inBean1.getCellNo   ());
							
							outList1.add(outBean1);
						}
						
						if (Flag.TRUE) {
							RP_MUR_CELL_MAP_SUMM_Bean outBean1 = new RP_MUR_CELL_MAP_SUMM_Bean();
							
							outBean1.setCellNo   (inBean1.getCellNo   ());
							outBean1.setProcId   (inBean1.getProcId   ());
							outBean1.setCellLocId(inBean1.getCellLocId());
							outBean1.setPointX   ("" + (Integer.parseInt(inBean1.getPointX()) + Integer.parseInt(inBean1.getWidth ())));
							outBean1.setPointY   (inBean1.getPointY   ());
							outBean1.setWidth    (inBean1.getWidth    ());
							outBean1.setHeight   (inBean1.getHeight   ());
							outBean1.setPointX2  (inBean1.getPointX2  ());
							outBean1.setPointY2  (inBean1.getPointY2  ());
							outBean1.setSeq      (inBean1.getCellNo   ());
							
							outList1.add(outBean1);
						}
						
						if (Flag.TRUE) {
							RP_MUR_CELL_MAP_SUMM_Bean outBean1 = new RP_MUR_CELL_MAP_SUMM_Bean();
							
							outBean1.setCellNo   (inBean1.getCellNo   ());
							outBean1.setProcId   (inBean1.getProcId   ());
							outBean1.setCellLocId(inBean1.getCellLocId());
							outBean1.setPointX   (inBean1.getPointX   ());
							outBean1.setPointY   (inBean1.getPointY   ());
							outBean1.setWidth    (inBean1.getWidth    ());
							outBean1.setHeight   (inBean1.getHeight   ());
							outBean1.setPointX2  (inBean1.getPointX2  ());
							outBean1.setPointY2  (inBean1.getPointY2  ());
							outBean1.setSeq      (inBean1.getCellNo   ());
							
							outList1.add(outBean1);
						}
						
						if (Flag.TRUE) {
							RP_MUR_CELL_MAP_SUMM_Bean outBean1 = new RP_MUR_CELL_MAP_SUMM_Bean();
							
							outBean1.setCellNo   (inBean1.getCellNo   ());
							outBean1.setProcId   (inBean1.getProcId   ());
							outBean1.setCellLocId(inBean1.getCellLocId());
							outBean1.setPointX   ("");
							outBean1.setPointY   ("");
							outBean1.setWidth    ("");
							outBean1.setHeight   ("");
							outBean1.setPointX2  ("");
							outBean1.setPointY2  ("");
							outBean1.setSeq      (inBean1.getCellNo   ());
							
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
				
				RP_MUR_CELL_MAP_SUMM_Main.cntWList = outList1.size();

				writer1.writeList();
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		if (Flag.TRUE) {
			/*
			 * 시간기록 끝
			 */
			RP_MUR_CELL_MAP_SUMM_Main.runMSec = (System.nanoTime() - RP_MUR_CELL_MAP_SUMM_Main.runMSec) / 1000 / 1000;
			
			if (Flag.TRUE) Logger.info("##### took time : %d ms", RP_MUR_CELL_MAP_SUMM_Main.runMSec);
		}

		if (Flag.TRUE) {
			/*
			 * UPDATE
			 */
			AnalCsvBean bean = new AnalCsvBean();
			
			bean.setType(AnalCsvType.UPDATE);
			bean.setTable("ANAL_CSV");
			bean.setFields(new String[][] {
					{ "LIST_CNT   ", "" + RP_MUR_CELL_MAP_SUMM_Main.cntWList },
					{ "E_TIME     ", "SYSDATE" },
					{ "R_MSEC     ", "" + RP_MUR_CELL_MAP_SUMM_Main.runMSec },
					{ "CSV_STATUS ", StrUtil.quote("OK") },   // OK, ERROR, SKIP, ETC...
					{ "LOG_MESSAGE", StrUtil.quote("COMPLETE") },  // COMPLETE, EXCEPTION
			});
			bean.setWhere(String.format("JOB_ID = %s AND PROG_NM = %s", StrUtil.quote(jobId), StrUtil.quote(RP_MUR_CELL_MAP_SUMM_Main.dsName)));
			
			if (Flag.TRUE) Logger.info("[%s]", bean.getQuery());
			
			bean.sendToOracle();
		}
	}
}
