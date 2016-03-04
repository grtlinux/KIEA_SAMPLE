package sdc.anal.mura.macro.A12.Y_AXIS_CELL.v04;

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

public class Y_AXIS_CELL_MacroJob extends AbstractMacroJob
{
	private T_CNT_CELL_CsvIo     reader1 = null;
	private MURA_CNT_CELL_CsvIo  reader2 = null;
	private Y_AXIS_CELL_CsvIo    writer1 = null;
	
	private List<T_CNT_CELL_Bean>     inList1  = null;
	private List<MURA_CNT_CELL_Bean>  inList2  = null;
	private List<Y_AXIS_CELL_Bean>    outList1 = null;
	
	private String filePath = null;

	private String jobId = null;

	public Y_AXIS_CELL_MacroJob(String jobId)
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
			Y_AXIS_CELL_Main.runMSec = System.nanoTime();
		}

		if (Flag.TRUE) {
			/*
			 * 기존 자료 삭제
			 */
			AnalCsvBean bean = new AnalCsvBean();

			bean.setType(AnalCsvType.DELETE);
			bean.setTable("ANAL_CSV");
			bean.setWhere(String.format("JOB_ID = %s AND PROG_NM = %s", StrUtil.quote(jobId), StrUtil.quote(Y_AXIS_CELL_Main.dsName)));

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
					{ "CMD_CODE   ", StrUtil.quote(Y_AXIS_CELL_Main.dsName.substring(0, 3)) },
					{ "PROG_NM    ", StrUtil.quote(Y_AXIS_CELL_Main.dsName) },
					{ "CSV_NM     ", StrUtil.quote(Y_AXIS_CELL_Main.dsName + ".csv") },
					{ "MAIN_CLASS ", StrUtil.quote(this.getClass().getName()) },
					{ "S_TIME     ", "SYSDATE" },
					{ "CSV_STATUS ", StrUtil.quote("START") },
			});
			if (Flag.TRUE) Logger.info("[%s]", bean.getQuery());

			bean.sendToOracle();
		}

		if (Flag.TRUE) {
			try {
				reader1 = new T_CNT_CELL_CsvIo     (this.filePath);
				reader2 = new MURA_CNT_CELL_CsvIo  (this.filePath);
				writer1 = new Y_AXIS_CELL_CsvIo    (this.filePath);
				
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
				Map<String, MURA_CNT_CELL_Bean> mapDesc = new LinkedHashMap<String, MURA_CNT_CELL_Bean>();
				
				/*
				 * MURA_DESC
				 */
				if (Flag.TRUE) {
					for (MURA_CNT_CELL_Bean inBean2 : inList2) {
						mapDesc.put(inBean2.getCellLocId(), inBean2);
					}
				}

				/*
				 * Job
				 * 1. InBean -> OutBean
				 */
				if (Flag.TRUE) {

					if (Flag.TRUE) {
						double sumMura, avgMura;
						
						for (T_CNT_CELL_Bean inBean1 : inList1) {
							MURA_CNT_CELL_Bean inBean2 = mapDesc.get(inBean1.getCellLocId());

							if (inBean2 == null) {
								sumMura = 0.0;
								avgMura = 0.0;
							} else {
								sumMura = Double.parseDouble(inBean2.getSMura());
								avgMura = sumMura / Integer.parseInt(inBean1.getTotCnt());
							}
							
							Y_AXIS_CELL_Bean outBean1 = new Y_AXIS_CELL_Bean();
							
							outBean1.setCellLocId(inBean1.getCellLocId());
							outBean1.setSMura("" + sumMura);
							outBean1.setTotCnt(inBean1.getTotCnt());
							outBean1.setAvgMura("" + avgMura);

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
				
				Y_AXIS_CELL_Main.cntWList = outList1.size();

				writer1.writeList();
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		if (Flag.TRUE) {
			/*
			 * 시간기록 끝
			 */
			Y_AXIS_CELL_Main.runMSec = (System.nanoTime() - Y_AXIS_CELL_Main.runMSec) / 1000 / 1000;
			
			if (Flag.TRUE) Logger.info("##### took time : %d ms", Y_AXIS_CELL_Main.runMSec);
		}

		if (Flag.TRUE) {
			/*
			 * UPDATE
			 */
			AnalCsvBean bean = new AnalCsvBean();
			
			bean.setType(AnalCsvType.UPDATE);
			bean.setTable("ANAL_CSV");
			bean.setFields(new String[][] {
					{ "LIST_CNT   ", "" + Y_AXIS_CELL_Main.cntWList },
					{ "E_TIME     ", "SYSDATE" },
					{ "R_MSEC     ", "" + Y_AXIS_CELL_Main.runMSec },
					{ "CSV_STATUS ", StrUtil.quote("OK") },   // OK, ERROR, SKIP, ETC...
					{ "LOG_MESSAGE", StrUtil.quote("COMPLETE") },  // COMPLETE, EXCEPTION
			});
			bean.setWhere(String.format("JOB_ID = %s AND PROG_NM = %s", StrUtil.quote(jobId), StrUtil.quote(Y_AXIS_CELL_Main.dsName)));
			
			if (Flag.TRUE) Logger.info("[%s]", bean.getQuery());
			
			bean.sendToOracle();
		}
	}
}
