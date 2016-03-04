package sdc.anal.lya.macro.A27.RP_SYS_EVENT_HIST_DETAIL.v04;

import java.util.Collections;
import java.util.Comparator;
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

public class RP_SYS_EVENT_HIST_DETAIL_MacroJob extends AbstractMacroJob
{
	private SO_SYS_CELLID_LIST2_CsvIo reader1 = null;
	private EVENT_HIST_CsvIo reader2 = null;
	private STEP_DESC_CsvIo reader3 = null;
	private RP_SYS_EVENT_HIST_DETAIL_CsvIo writer1 = null;
	
	private List<SO_SYS_CELLID_LIST2_Bean> inList1  = null;
	private List<EVENT_HIST_Bean> inList2  = null;
	private List<STEP_DESC_Bean> inList3  = null;
	private List<RP_SYS_EVENT_HIST_DETAIL_Bean> outList1 = null;
	
	private String filePath = null;

	private String jobId = null;
	
	public RP_SYS_EVENT_HIST_DETAIL_MacroJob(String jobId)
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
			RP_SYS_EVENT_HIST_DETAIL_Main.runMSec = System.nanoTime();
		}

		if (Flag.TRUE) {
			/*
			 * 기존 자료 삭제
			 */
			AnalCsvBean bean = new AnalCsvBean();

			bean.setType(AnalCsvType.DELETE);
			bean.setTable("ANAL_CSV");
			bean.setWhere(String.format("JOB_ID = %s AND PROG_NM = %s", StrUtil.quote(jobId), StrUtil.quote(RP_SYS_EVENT_HIST_DETAIL_Main.dsName)));

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
					{ "CMD_CODE   ", StrUtil.quote(RP_SYS_EVENT_HIST_DETAIL_Main.dsName.substring(0, 3)) },
					{ "PROG_NM    ", StrUtil.quote(RP_SYS_EVENT_HIST_DETAIL_Main.dsName) },
					{ "CSV_NM     ", StrUtil.quote(RP_SYS_EVENT_HIST_DETAIL_Main.dsName + ".csv") },
					{ "MAIN_CLASS ", StrUtil.quote(this.getClass().getName()) },
					{ "S_TIME     ", "SYSDATE" },
					{ "CSV_STATUS ", StrUtil.quote("START") },
			});
			if (Flag.TRUE) Logger.info("[%s]", bean.getQuery());

			bean.sendToOracle();
		}

		if (Flag.TRUE) {
			try {
				reader1 = new SO_SYS_CELLID_LIST2_CsvIo(this.filePath);
				reader2 = new EVENT_HIST_CsvIo(this.filePath);
				reader3 = new STEP_DESC_CsvIo(this.filePath);
				writer1 = new RP_SYS_EVENT_HIST_DETAIL_CsvIo(this.filePath);
				
				inList1  = reader1.getList(true);
				inList2  = reader2.getList(true);
				inList3  = reader3.getList(true);
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
				
				Map<String, SO_SYS_CELLID_LIST2_Bean> mapGlass = new LinkedHashMap<String, SO_SYS_CELLID_LIST2_Bean>();
				Map<String, STEP_DESC_Bean> mapStep = new LinkedHashMap<String, STEP_DESC_Bean>();
				
				if (Flag.TRUE) {
					
					for (SO_SYS_CELLID_LIST2_Bean bean1 : inList1) {
						if ("BAD".equals(bean1.getGroup())) {
							mapGlass.put(bean1.getGlassId(), bean1);
						}
					}
					
					for (STEP_DESC_Bean bean3 : inList3) {
						mapStep.put(bean3.getImptStepGrpId(), bean3);
					}
				}
				
				if (Flag.TRUE) {
					
					for (EVENT_HIST_Bean bean2 : inList2) {
						
						SO_SYS_CELLID_LIST2_Bean bean = mapGlass.get(bean2.getGlassId());
						if (bean == null)
							continue;
						
						RP_SYS_EVENT_HIST_DETAIL_Bean outBean = new RP_SYS_EVENT_HIST_DETAIL_Bean();
						
						outBean.setClusterId     (bean.getClusterId      ());
						outBean.setEventOccurCode(bean2.getEventOccurCode());
						outBean.setStepId        (bean2.getStepId().substring(0, 1) + "__" + bean2.getStepId().substring(3));
						
						STEP_DESC_Bean beanStep = mapStep.get(outBean.getStepId());
						if (beanStep == null) {
							outBean.setStepDesc("");
						} else {
							outBean.setStepDesc(beanStep.getStepDesc());
						}
						
						outBean.setAreaCode      (bean2.getAreaCode      ());
						outBean.setGlassId       (bean2.getGlassId       ());
						outBean.setEventOccurDt  (bean2.getEventOccurDt  ());
						outBean.setEqpId         (bean2.getEqpId         ());
						outBean.setUnitId        (bean2.getUnitId        ());
						outBean.setLayerCode     (bean2.getLayerCode     ());
						
						outList1.add(outBean);
					}
					
					if (!Flag.TRUE) {
						/*
						 * 확인출력
						 */
						for (RP_SYS_EVENT_HIST_DETAIL_Bean bean : outList1) {
							System.out.println(bean);
						}
					}
				}

				if (Flag.TRUE) {
					/*
					 * SORT
					 */
					Collections.sort(outList1, new Comparator<RP_SYS_EVENT_HIST_DETAIL_Bean>() {
						@Override
						public int compare(RP_SYS_EVENT_HIST_DETAIL_Bean bean1, RP_SYS_EVENT_HIST_DETAIL_Bean bean2) {
							int ret = 0;
							
							// 1. CLUSTER_ID
							ret = bean1.getClusterId().compareTo(bean2.getClusterId());
							if (ret != 0) return ret;

							// 2. Step ID
							ret = bean1.getStepId().compareTo(bean2.getStepId());
							if (ret != 0) return ret;
							
							// 3. Layer Code
							ret = bean1.getLayerCode().compareTo(bean2.getLayerCode());
							if (ret != 0) return ret;
							
							// 4. Event Occur Code
							ret = bean1.getEventOccurCode().compareTo(bean2.getEventOccurCode());
							if (ret != 0) return ret;
									
							// 5. GlassId
							ret = bean1.getGlassId().compareTo(bean2.getGlassId());
							if (ret != 0) return ret;

							return ret;
						}
					});
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
				
				RP_SYS_EVENT_HIST_DETAIL_Main.cntWList = outList1.size();

				writer1.writeList();
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		if (Flag.TRUE) {
			/*
			 * 시간기록 끝
			 */
			RP_SYS_EVENT_HIST_DETAIL_Main.runMSec = (System.nanoTime() - RP_SYS_EVENT_HIST_DETAIL_Main.runMSec) / 1000 / 1000;
			
			if (Flag.TRUE) Logger.info("##### took time : %d ms", RP_SYS_EVENT_HIST_DETAIL_Main.runMSec);
		}

		if (Flag.TRUE) {
			/*
			 * UPDATE
			 */
			AnalCsvBean bean = new AnalCsvBean();
			
			bean.setType(AnalCsvType.UPDATE);
			bean.setTable("ANAL_CSV");
			bean.setFields(new String[][] {
					{ "LIST_CNT   ", "" + RP_SYS_EVENT_HIST_DETAIL_Main.cntWList },
					{ "E_TIME     ", "SYSDATE" },
					{ "R_MSEC     ", "" + RP_SYS_EVENT_HIST_DETAIL_Main.runMSec },
					{ "CSV_STATUS ", StrUtil.quote("OK") },   // OK, ERROR, SKIP, ETC...
					{ "LOG_MESSAGE", StrUtil.quote("COMPLETE") },  // COMPLETE, EXCEPTION
			});
			bean.setWhere(String.format("JOB_ID = %s AND PROG_NM = %s", StrUtil.quote(jobId), StrUtil.quote(RP_SYS_EVENT_HIST_DETAIL_Main.dsName)));
			
			if (Flag.TRUE) Logger.info("[%s]", bean.getQuery());
			
			bean.sendToOracle();
		}
	}
}
