package sdc.anal.mura.macro.A21.SO_SYS_GLASS_LIST2.v04;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import kiea.kr.co.tain.base.Flag;
import kiea.kr.co.tain.util.Print;
import kiea.proj.sdc.anal.macro.impl.job.AbstractMacroJob;
import kiea.proj.sdc.anal.tools.AnalCsv.v04.AnalCsvBean;
import kiea.proj.sdc.anal.tools.AnalCsv.v04.AnalCsvType;
import kiea.proj.sdc.anal.util.BasePath;
import kiea.proj.sdc.anal.util.FileUtil;
import kiea.proj.sdc.anal.util.StrUtil;
import kiea.proj.sdc.anal.util.log.v02.Logger;

public class SO_SYS_GLASS_LIST2_MacroJob extends AbstractMacroJob
{
	private CLUSTER_OUTPUT_CsvIo reader1 = null;
	private GOOD_GLASS_CsvIo reader2 = null;
	private SO_SYS_GLASS_LIST2_CsvIo writer1 = null;
	
	private List<CLUSTER_OUTPUT_Bean> inList1  = null;
	private List<GOOD_GLASS_Bean> inList2  = null;
	private List<SO_SYS_GLASS_LIST2_Bean> outList1 = null;
	
	private String filePath = null;

	private String jobId = null;

	public SO_SYS_GLASS_LIST2_MacroJob(String jobId)
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
			SO_SYS_GLASS_LIST2_Main.runMSec = System.nanoTime();
		}

		if (Flag.TRUE) {
			/*
			 * 기존 자료 삭제
			 */
			AnalCsvBean bean = new AnalCsvBean();

			bean.setType(AnalCsvType.DELETE);
			bean.setTable("ANAL_CSV");
			bean.setWhere(String.format("JOB_ID = %s AND PROG_NM = %s", StrUtil.quote(jobId), StrUtil.quote(SO_SYS_GLASS_LIST2_Main.dsName)));

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
					{ "CMD_CODE   ", StrUtil.quote(SO_SYS_GLASS_LIST2_Main.dsName.substring(0, 3)) },
					{ "PROG_NM    ", StrUtil.quote(SO_SYS_GLASS_LIST2_Main.dsName) },
					{ "CSV_NM     ", StrUtil.quote(SO_SYS_GLASS_LIST2_Main.dsName + ".csv") },
					{ "MAIN_CLASS ", StrUtil.quote(this.getClass().getName()) },
					{ "S_TIME     ", "SYSDATE" },
					{ "CSV_STATUS ", StrUtil.quote("START") },
			});
			if (Flag.TRUE) Logger.info("[%s]", bean.getQuery());

			bean.sendToOracle();
		}

		if (Flag.TRUE) {
			try {
				reader1 = new CLUSTER_OUTPUT_CsvIo(this.filePath);
				reader2 = new GOOD_GLASS_CsvIo(this.filePath);
				writer1 = new SO_SYS_GLASS_LIST2_CsvIo(this.filePath);
				
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
				/*
				 * Job
				 * 1. InBean -> OutBean
				 */
				Map<String, Set<String>> map = new HashMap<String, Set<String>>();
				Set<String> setGoodGlass = new HashSet<String>();
				
				if (Flag.TRUE) {
					
					for (CLUSTER_OUTPUT_Bean inBean1 : inList1) {
						Set<String> set = map.get(inBean1.getClusterId());
						
						if (set == null) {
							set = new HashSet<String>();
							map.put(inBean1.getClusterId(), set);
						}
						set.add(inBean1.getGlassId());
					}
					
					if (!Flag.TRUE) {
						/*
						 * 확인출력
						 */
						for (Map.Entry<String, Set<String>> entry : map.entrySet()) {
							String key = entry.getKey();
							Set<String> set = entry.getValue();
							
							Print.println("[%s] => %s", key, set);
						}
					}
				}
				
				if (Flag.TRUE) {
					/*
					 * TODO : 2014.12.29 : 추가
					 * GOOD GLASSID 만 추출
					 */
					for (GOOD_GLASS_Bean inBean2 : inList2) {
						setGoodGlass.add(inBean2.getGlassId());
					}
					
					if (!Flag.TRUE) {
						/*
						 * 확인출력
						 */
						for (String goodGlassId : setGoodGlass) {
							Print.println("GOOD [ %s ]", goodGlassId);
						}
					}
				}
				
				if (Flag.TRUE) {
					
					for (String clusterId : map.keySet()) {
						/*
						 * BAD GLASS
						 */
						for (String glassId : map.get(clusterId)) {

							SO_SYS_GLASS_LIST2_Bean outBean1 = new SO_SYS_GLASS_LIST2_Bean();
							
							outBean1.setGlassId (glassId);
							outBean1.setTarget  ("1");
							outBean1.setGroupNum(clusterId);
							
							outList1.add(outBean1);
						}
						/*
						 * TODO : 2014.12.29 : 추가
						 * GOOD GLASS
						 */
						for (String goodGlassId : setGoodGlass) {
							
							SO_SYS_GLASS_LIST2_Bean outBean1 = new SO_SYS_GLASS_LIST2_Bean();

							outBean1.setGlassId (goodGlassId);
							outBean1.setTarget  ("0");
							outBean1.setGroupNum(clusterId);
							
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
				
				SO_SYS_GLASS_LIST2_Main.cntWList = outList1.size();

				writer1.writeList();
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		if (Flag.TRUE) {
			/*
			 * 시간기록 끝
			 */
			SO_SYS_GLASS_LIST2_Main.runMSec = (System.nanoTime() - SO_SYS_GLASS_LIST2_Main.runMSec) / 1000 / 1000;
			
			if (Flag.TRUE) Logger.info("##### took time : %d ms", SO_SYS_GLASS_LIST2_Main.runMSec);
		}

		if (Flag.TRUE) {
			/*
			 * UPDATE
			 */
			AnalCsvBean bean = new AnalCsvBean();
			
			bean.setType(AnalCsvType.UPDATE);
			bean.setTable("ANAL_CSV");
			bean.setFields(new String[][] {
					{ "LIST_CNT   ", "" + SO_SYS_GLASS_LIST2_Main.cntWList },
					{ "E_TIME     ", "SYSDATE" },
					{ "R_MSEC     ", "" + SO_SYS_GLASS_LIST2_Main.runMSec },
					{ "CSV_STATUS ", StrUtil.quote("OK") },   // OK, ERROR, SKIP, ETC...
					{ "LOG_MESSAGE", StrUtil.quote("COMPLETE") },  // COMPLETE, EXCEPTION
			});
			bean.setWhere(String.format("JOB_ID = %s AND PROG_NM = %s", StrUtil.quote(jobId), StrUtil.quote(SO_SYS_GLASS_LIST2_Main.dsName)));
			
			if (Flag.TRUE) Logger.info("[%s]", bean.getQuery());
			
			bean.sendToOracle();
		}
	}
}
