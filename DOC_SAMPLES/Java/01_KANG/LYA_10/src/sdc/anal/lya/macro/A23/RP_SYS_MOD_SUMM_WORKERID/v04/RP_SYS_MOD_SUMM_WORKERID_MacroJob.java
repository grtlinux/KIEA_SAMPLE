package sdc.anal.lya.macro.A23.RP_SYS_MOD_SUMM_WORKERID.v04;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import kiea.kr.co.tain.base.Flag;
import kiea.proj.sdc.anal.macro.impl.job.AbstractMacroJob;
import kiea.proj.sdc.anal.tools.AnalCsv.v04.AnalCsvBean;
import kiea.proj.sdc.anal.tools.AnalCsv.v04.AnalCsvType;
import kiea.proj.sdc.anal.util.BasePath;
import kiea.proj.sdc.anal.util.FileUtil;
import kiea.proj.sdc.anal.util.StrUtil;
import kiea.proj.sdc.anal.util.log.v02.Logger;

public class RP_SYS_MOD_SUMM_WORKERID_MacroJob extends AbstractMacroJob
{
	private SO_SYS_MOD_WORKID_CSV_CsvIo reader1 = null;
	private RP_SYS_MOD_SUMM_WORKERID_CsvIo writer1 = null;
	
	private List<SO_SYS_MOD_WORKID_CSV_Bean> inList1  = null;
	private List<RP_SYS_MOD_SUMM_WORKERID_Bean> outList1 = null;
	
	private String filePath = null;

	private String jobId = null;

	public RP_SYS_MOD_SUMM_WORKERID_MacroJob(String jobId)
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
			RP_SYS_MOD_SUMM_WORKERID_Main.runMSec = System.nanoTime();
		}

		if (Flag.TRUE) {
			/*
			 * 기존 자료 삭제
			 */
			AnalCsvBean bean = new AnalCsvBean();

			bean.setType(AnalCsvType.DELETE);
			bean.setTable("ANAL_CSV");
			bean.setWhere(String.format("JOB_ID = %s AND PROG_NM = %s", StrUtil.quote(jobId), StrUtil.quote(RP_SYS_MOD_SUMM_WORKERID_Main.dsName)));

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
					{ "CMD_CODE   ", StrUtil.quote(RP_SYS_MOD_SUMM_WORKERID_Main.dsName.substring(0, 3)) },
					{ "PROG_NM    ", StrUtil.quote(RP_SYS_MOD_SUMM_WORKERID_Main.dsName) },
					{ "CSV_NM     ", StrUtil.quote(RP_SYS_MOD_SUMM_WORKERID_Main.dsName + ".csv") },
					{ "MAIN_CLASS ", StrUtil.quote(this.getClass().getName()) },
					{ "S_TIME     ", "SYSDATE" },
					{ "CSV_STATUS ", StrUtil.quote("START") },
			});
			if (Flag.TRUE) Logger.info("[%s]", bean.getQuery());

			bean.sendToOracle();
		}

		if (Flag.TRUE) {
			try {
				reader1 = new SO_SYS_MOD_WORKID_CSV_CsvIo(this.filePath);
				writer1 = new RP_SYS_MOD_SUMM_WORKERID_CsvIo(this.filePath);
				
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
					
					for (SO_SYS_MOD_WORKID_CSV_Bean inBean1 : inList1) {

						if (Double.parseDouble(inBean1.getBadRate()) == 0.0)
							continue;
						
						RP_SYS_MOD_SUMM_WORKERID_Bean outBean1 = new RP_SYS_MOD_SUMM_WORKERID_Bean();
						
						outBean1.setCode     ("no data"             );
						outBean1.setClusterId("0"                   );
						outBean1.setRank     ("-99999"              );
						outBean1.setInspEqpId(inBean1.getInspEqpId());
						outBean1.setWorkerId (inBean1.getWorkerId ());
						outBean1.setTot      (inBean1.getTot      ());
						outBean1.setBad      (inBean1.getBad      ());
						outBean1.setBadRate  (inBean1.getBadRate  ());

						outList1.add(outBean1);
					}
				}
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		if (Flag.TRUE) {
			/*
			 * SORT INSPEQPID
			 */
			Collections.sort(outList1, new Comparator<RP_SYS_MOD_SUMM_WORKERID_Bean>() {
				@Override
				public int compare(RP_SYS_MOD_SUMM_WORKERID_Bean bean1, RP_SYS_MOD_SUMM_WORKERID_Bean bean2) {
					int ret = 0;
					
					// 1. INSPEQPID
					ret = bean1.getInspEqpId().compareTo(bean2.getInspEqpId());
					if (ret != 0) return ret;

					return ret;
				}
			});
			
			if (!Flag.TRUE) {
				for (RP_SYS_MOD_SUMM_WORKERID_Bean bean : outList1) {
					System.out.println(bean);
				}
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
				
				RP_SYS_MOD_SUMM_WORKERID_Main.cntWList = outList1.size();

				writer1.writeList();
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		if (Flag.TRUE) {
			/*
			 * 시간기록 끝
			 */
			RP_SYS_MOD_SUMM_WORKERID_Main.runMSec = (System.nanoTime() - RP_SYS_MOD_SUMM_WORKERID_Main.runMSec) / 1000 / 1000;
			
			if (Flag.TRUE) Logger.info("##### took time : %d ms", RP_SYS_MOD_SUMM_WORKERID_Main.runMSec);
		}

		if (Flag.TRUE) {
			/*
			 * UPDATE
			 */
			AnalCsvBean bean = new AnalCsvBean();
			
			bean.setType(AnalCsvType.UPDATE);
			bean.setTable("ANAL_CSV");
			bean.setFields(new String[][] {
					{ "LIST_CNT   ", "" + RP_SYS_MOD_SUMM_WORKERID_Main.cntWList },
					{ "E_TIME     ", "SYSDATE" },
					{ "R_MSEC     ", "" + RP_SYS_MOD_SUMM_WORKERID_Main.runMSec },
					{ "CSV_STATUS ", StrUtil.quote("OK") },   // OK, ERROR, SKIP, ETC...
					{ "LOG_MESSAGE", StrUtil.quote("COMPLETE") },  // COMPLETE, EXCEPTION
			});
			bean.setWhere(String.format("JOB_ID = %s AND PROG_NM = %s", StrUtil.quote(jobId), StrUtil.quote(RP_SYS_MOD_SUMM_WORKERID_Main.dsName)));
			
			if (Flag.TRUE) Logger.info("[%s]", bean.getQuery());
			
			bean.sendToOracle();
		}
	}
}
