package sdc.anal.lya.macro.A23.SO_SYS_MOD_PKBANK_JASU.v04;

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

public class SO_SYS_MOD_PKBANK_JASU_MacroJob extends AbstractMacroJob
{
	private MOD_PKBANK_CsvIo reader1 = null;
	private SO_SYS_MOD_PKBANK_JASU_CsvIo writer1 = null;
	
	private List<MOD_PKBANK_Bean> inList1  = null;
	private List<SO_SYS_MOD_PKBANK_JASU_Bean> outList1 = null;
	
	private String filePath = null;

	private String jobId = null;

	public SO_SYS_MOD_PKBANK_JASU_MacroJob(String jobId)
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
			SO_SYS_MOD_PKBANK_JASU_Main.runMSec = System.nanoTime();
		}

		if (Flag.TRUE) {
			/*
			 * 기존 자료 삭제
			 */
			AnalCsvBean bean = new AnalCsvBean();

			bean.setType(AnalCsvType.DELETE);
			bean.setTable("ANAL_CSV");
			bean.setWhere(String.format("JOB_ID = %s AND PROG_NM = %s", StrUtil.quote(jobId), StrUtil.quote(SO_SYS_MOD_PKBANK_JASU_Main.dsName)));

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
					{ "CMD_CODE   ", StrUtil.quote(SO_SYS_MOD_PKBANK_JASU_Main.dsName.substring(0, 3)) },
					{ "PROG_NM    ", StrUtil.quote(SO_SYS_MOD_PKBANK_JASU_Main.dsName) },
					{ "CSV_NM     ", StrUtil.quote(SO_SYS_MOD_PKBANK_JASU_Main.dsName + ".csv") },
					{ "MAIN_CLASS ", StrUtil.quote(this.getClass().getName()) },
					{ "S_TIME     ", "SYSDATE" },
					{ "CSV_STATUS ", StrUtil.quote("START") },
			});
			if (Flag.TRUE) Logger.info("[%s]", bean.getQuery());

			bean.sendToOracle();
		}

		if (Flag.TRUE) {
			try {
				reader1 = new MOD_PKBANK_CsvIo(this.filePath);
				writer1 = new SO_SYS_MOD_PKBANK_JASU_CsvIo(this.filePath);
				
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
				String strDefectGroupCode = Parameter.getInstance().getStrDefectGroupCode();
				
				Map<String, List<MOD_PKBANK_Bean>> map = new LinkedHashMap<String, List<MOD_PKBANK_Bean>>();
				List<MOD_PKBANK_Bean> list = null;
				
				if (Flag.TRUE) {
					for (MOD_PKBANK_Bean inBean1 : inList1) {
						if (strDecisionCode.indexOf(StrUtil.quote(inBean1.getDecGradeCd())) >= 0
								&& strDefectGroupCode.indexOf(StrUtil.quote(inBean1.getDefectCd())) >= 0) {
							
							list = map.get(inBean1.getImptEqpId());
							if (list == null) {
								list = new ArrayList<MOD_PKBANK_Bean>();
								map.put(inBean1.getImptEqpId(), list);
							}
							list.add(inBean1);
						}
					}
				}
				
				if (Flag.TRUE) {
					for (Map.Entry<String, List<MOD_PKBANK_Bean>> entry : map.entrySet()) {

						SO_SYS_MOD_PKBANK_JASU_Bean outBean1 = new SO_SYS_MOD_PKBANK_JASU_Bean();
						
						outBean1.setImptEqpId(entry.getKey());
						outBean1.setCount("" + entry.getValue().size());

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
				
				SO_SYS_MOD_PKBANK_JASU_Main.cntWList = outList1.size();

				writer1.writeList();
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		if (Flag.TRUE) {
			/*
			 * 시간기록 끝
			 */
			SO_SYS_MOD_PKBANK_JASU_Main.runMSec = (System.nanoTime() - SO_SYS_MOD_PKBANK_JASU_Main.runMSec) / 1000 / 1000;
			
			if (Flag.TRUE) Logger.info("##### took time : %d ms", SO_SYS_MOD_PKBANK_JASU_Main.runMSec);
		}

		if (Flag.TRUE) {
			/*
			 * UPDATE
			 */
			AnalCsvBean bean = new AnalCsvBean();
			
			bean.setType(AnalCsvType.UPDATE);
			bean.setTable("ANAL_CSV");
			bean.setFields(new String[][] {
					{ "LIST_CNT   ", "" + SO_SYS_MOD_PKBANK_JASU_Main.cntWList },
					{ "E_TIME     ", "SYSDATE" },
					{ "R_MSEC     ", "" + SO_SYS_MOD_PKBANK_JASU_Main.runMSec },
					{ "CSV_STATUS ", StrUtil.quote("OK") },   // OK, ERROR, SKIP, ETC...
					{ "LOG_MESSAGE", StrUtil.quote("COMPLETE") },  // COMPLETE, EXCEPTION
			});
			bean.setWhere(String.format("JOB_ID = %s AND PROG_NM = %s", StrUtil.quote(jobId), StrUtil.quote(SO_SYS_MOD_PKBANK_JASU_Main.dsName)));
			
			if (Flag.TRUE) Logger.info("[%s]", bean.getQuery());
			
			bean.sendToOracle();
		}
	}
}
