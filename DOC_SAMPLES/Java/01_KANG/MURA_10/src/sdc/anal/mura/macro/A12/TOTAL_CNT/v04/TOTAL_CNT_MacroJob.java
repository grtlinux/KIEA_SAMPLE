package sdc.anal.mura.macro.A12.TOTAL_CNT.v04;

import java.util.List;
import java.util.Map;
import java.util.SortedMap;
import java.util.SortedSet;
import java.util.TreeMap;
import java.util.TreeSet;

import kiea.kr.co.tain.base.Flag;
import kiea.kr.co.tain.util.Print;
import kiea.proj.sdc.anal.macro.impl.job.AbstractMacroJob;
import kiea.proj.sdc.anal.tools.AnalCsv.v04.AnalCsvBean;
import kiea.proj.sdc.anal.tools.AnalCsv.v04.AnalCsvType;
import kiea.proj.sdc.anal.util.BasePath;
import kiea.proj.sdc.anal.util.FileUtil;
import kiea.proj.sdc.anal.util.StrUtil;
import kiea.proj.sdc.anal.util.log.v02.Logger;

public class TOTAL_CNT_MacroJob extends AbstractMacroJob
{
	private MURA_N_TREND_CsvIo     reader1 = null;
	private TOTAL_CNT_CsvIo  writer1 = null;
	
	private List<MURA_N_TREND_Bean>     inList1  = null;
	private List<TOTAL_CNT_Bean>  outList1 = null;
	
	private String filePath = null;

	private String jobId = null;

	public TOTAL_CNT_MacroJob(String jobId)
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
			TOTAL_CNT_Main.runMSec = System.nanoTime();
		}

		if (Flag.TRUE) {
			/*
			 * 기존 자료 삭제
			 */
			AnalCsvBean bean = new AnalCsvBean();

			bean.setType(AnalCsvType.DELETE);
			bean.setTable("ANAL_CSV");
			bean.setWhere(String.format("JOB_ID = %s AND PROG_NM = %s", StrUtil.quote(jobId), StrUtil.quote(TOTAL_CNT_Main.dsName)));

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
					{ "CMD_CODE   ", StrUtil.quote(TOTAL_CNT_Main.dsName.substring(0, 3)) },
					{ "PROG_NM    ", StrUtil.quote(TOTAL_CNT_Main.dsName) },
					{ "CSV_NM     ", StrUtil.quote(TOTAL_CNT_Main.dsName + ".csv") },
					{ "MAIN_CLASS ", StrUtil.quote(this.getClass().getName()) },
					{ "S_TIME     ", "SYSDATE" },
					{ "CSV_STATUS ", StrUtil.quote("START") },
			});
			if (Flag.TRUE) Logger.info("[%s]", bean.getQuery());

			bean.sendToOracle();
		}

		if (Flag.TRUE) {
			try {
				reader1 = new MURA_N_TREND_CsvIo    (this.filePath);
				writer1 = new TOTAL_CNT_CsvIo (this.filePath);
				
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

					SortedMap<String, SortedSet<String>> map = new TreeMap<String, SortedSet<String>>();

					if (Flag.TRUE) {
						/*
						 * setting
						 */
						@SuppressWarnings("unused")
						int idx = 0;
						for (MURA_N_TREND_Bean inBean1 : inList1) {
							idx ++;
							if (!Flag.TRUE) Print.println("(%d) [D_TIME:%s] [CELLID:%s]", idx, inBean1.getDTime(), inBean1.getCellId());
							
							SortedSet<String> set = map.get(inBean1.getDTime());
							if (set == null) {
								map.put(inBean1.getDTime(), new TreeSet<String>());
								set = map.get(inBean1.getDTime());
							}
							set.add(inBean1.getCellId());
						}

						if (!Flag.TRUE) Print.println("data length = %d", idx);
					}
					
					if (!Flag.TRUE) {
						/*
						 * print
						 */
						for (Map.Entry<String, SortedSet<String>> entry : map.entrySet()) {
							String key = entry.getKey();
							SortedSet<String> set = entry.getValue();
							
							if (Flag.TRUE) Print.println("[%s] [size:%d]", key, set.size());
							
							for (String str : set) {
								if (!Flag.TRUE) Print.println("\t(%s)", str);
							}
						}
					}
					
					if (Flag.TRUE) {
						/*
						 * -> OutBean 
						 */
						for (Map.Entry<String, SortedSet<String>> entry : map.entrySet()) {
							String key = entry.getKey();
							SortedSet<String> set = entry.getValue();
							
							TOTAL_CNT_Bean outBean1 = new TOTAL_CNT_Bean();
							
							outBean1.setDTime  (key);
							outBean1.setTotCnt ("" + set.size());
							
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
				
				TOTAL_CNT_Main.cntWList = outList1.size();

				writer1.writeList();
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		if (Flag.TRUE) {
			/*
			 * 시간기록 끝
			 */
			TOTAL_CNT_Main.runMSec = (System.nanoTime() - TOTAL_CNT_Main.runMSec) / 1000 / 1000;
			
			if (Flag.TRUE) Logger.info("##### took time : %d ms", TOTAL_CNT_Main.runMSec);
		}

		if (Flag.TRUE) {
			/*
			 * UPDATE
			 */
			AnalCsvBean bean = new AnalCsvBean();
			
			bean.setType(AnalCsvType.UPDATE);
			bean.setTable("ANAL_CSV");
			bean.setFields(new String[][] {
					{ "LIST_CNT   ", "" + TOTAL_CNT_Main.cntWList },
					{ "E_TIME     ", "SYSDATE" },
					{ "R_MSEC     ", "" + TOTAL_CNT_Main.runMSec },
					{ "CSV_STATUS ", StrUtil.quote("OK") },   // OK, ERROR, SKIP, ETC...
					{ "LOG_MESSAGE", StrUtil.quote("COMPLETE") },  // COMPLETE, EXCEPTION
			});
			bean.setWhere(String.format("JOB_ID = %s AND PROG_NM = %s", StrUtil.quote(jobId), StrUtil.quote(TOTAL_CNT_Main.dsName)));
			
			if (Flag.TRUE) Logger.info("[%s]", bean.getQuery());
			
			bean.sendToOracle();
		}
	}
}
