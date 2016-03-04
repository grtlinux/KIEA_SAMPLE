package sdc.anal.lya.macro.A24.EQP_FDC02_DETAILS01.v04.D20141216;

import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
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

public class EQP_FDC02_DETAILS01_MacroJob extends AbstractMacroJob
{
	private SO_SYS_CELLID_LIST2_CsvIo reader1 = null;
	private RP_SYS_WIP_SUMM1_CsvIo reader2 = null;
	private EQP_FDC02_DETAILS01_OracleReader reader3 = null;
	private EQP_FDC02_DETAILS01_CsvIo writer1 = null;
	
	private List<SO_SYS_CELLID_LIST2_Bean> inList1  = null;
	private List<RP_SYS_WIP_SUMM1_Bean> inList2  = null;
	private List<EQP_FDC02_DETAILS01_Bean> inList3 = null;
	private List<EQP_FDC02_DETAILS01_Bean> outList1 = null;
	
	private String filePath = null;

	private String jobId = null;

	public EQP_FDC02_DETAILS01_MacroJob(String jobId)
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
			EQP_FDC02_DETAILS01_Main.runMSec = System.nanoTime();
		}

		if (Flag.TRUE) {
			/*
			 * 기존 자료 삭제
			 */
			AnalCsvBean bean = new AnalCsvBean();

			bean.setType(AnalCsvType.DELETE);
			bean.setTable("ANAL_CSV");
			bean.setWhere(String.format("JOB_ID = %s AND PROG_NM = %s", StrUtil.quote(jobId), StrUtil.quote(EQP_FDC02_DETAILS01_Main.dsName)));

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
					{ "CMD_CODE   ", StrUtil.quote(EQP_FDC02_DETAILS01_Main.dsName.substring(0, 3)) },
					{ "PROG_NM    ", StrUtil.quote(EQP_FDC02_DETAILS01_Main.dsName) },
					{ "CSV_NM     ", StrUtil.quote(EQP_FDC02_DETAILS01_Main.dsName + ".csv") },
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
				reader2 = new RP_SYS_WIP_SUMM1_CsvIo(this.filePath);
				reader3 = new EQP_FDC02_DETAILS01_OracleReader();
				writer1 = new EQP_FDC02_DETAILS01_CsvIo(this.filePath);
				
				inList1  = reader1.getList(true);
				inList2  = reader2.getList(true);
				reader3.setEqpGlassList(inList1, inList2);
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
				/*
				 * Job
				 * 1. InBean -> OutBean
				 */
				
				Map<String, SO_SYS_CELLID_LIST2_Bean> mapGlass = new HashMap<String, SO_SYS_CELLID_LIST2_Bean>();
				
				if (Flag.TRUE) {
					/*
					 * GLASS의 ClusterId, Group 정보를 얻는다.
					 */
					for (SO_SYS_CELLID_LIST2_Bean bean : inList1) {
						mapGlass.put(bean.getGlassId(), bean);
					}
				}

				if (Flag.TRUE) {
					for (EQP_FDC02_DETAILS01_Bean readBean : inList3) {
						EQP_FDC02_DETAILS01_Bean writeBean = new EQP_FDC02_DETAILS01_Bean();

						writeBean.setLine       (readBean.getLine       ());
						writeBean.setPart       (readBean.getPart       ());
						writeBean.setEqpModel   (readBean.getEqpModel   ());
						writeBean.setEqpId      (readBean.getEqpId      ());
						writeBean.setModuleName (readBean.getModuleName ());
						writeBean.setProcId     (readBean.getProcId     ());
						writeBean.setProdId     (readBean.getProdId     ());
						writeBean.setPpid       (readBean.getPpid       ());
						writeBean.setGlassId    (readBean.getGlassId    ());
						writeBean.setProcessStep(readBean.getProcessStep());
						writeBean.setBeginStep  (readBean.getBeginStep  ());
						writeBean.setSensorName (readBean.getSensorName ());
						writeBean.setParam      (readBean.getParam      ());
						writeBean.setParamValue (readBean.getParamValue ());
						writeBean.setUsl        (readBean.getUsl        ());
						writeBean.setLsl        (readBean.getLsl        ());
						writeBean.setBeginTime  (readBean.getBeginTime  ());
						
						SO_SYS_CELLID_LIST2_Bean bean = mapGlass.get(readBean.getGlassId());
						if (bean != null) {
							writeBean.setClusterId  (bean.getClusterId());
							writeBean.setGroupId    (bean.getGroup    ());
						} else {
							writeBean.setClusterId  ("");
							writeBean.setGroupId    ("");
						}

						outList1.add(writeBean);
					}
				}

				if (!Flag.TRUE) {
					for (int i=0; inList3 != null && i < inList3.size(); i++) {
						EQP_FDC02_DETAILS01_Bean writeBean = new EQP_FDC02_DETAILS01_Bean();

						writeBean.setLine       (inList3.get(i).getLine       ());
						writeBean.setPart       (inList3.get(i).getPart       ());
						writeBean.setEqpModel   (inList3.get(i).getEqpModel   ());
						writeBean.setEqpId      (inList3.get(i).getEqpId      ());
						writeBean.setModuleName (inList3.get(i).getModuleName ());
						writeBean.setProcId     (inList3.get(i).getProcId     ());
						writeBean.setProdId     (inList3.get(i).getProdId     ());
						writeBean.setPpid       (inList3.get(i).getPpid       ());
						writeBean.setGlassId    (inList3.get(i).getGlassId    ());
						writeBean.setProcessStep(inList3.get(i).getProcessStep());
						writeBean.setBeginStep  (inList3.get(i).getBeginStep  ());
						writeBean.setSensorName (inList3.get(i).getSensorName ());
						writeBean.setParam      (inList3.get(i).getParam      ());
						writeBean.setParamValue (inList3.get(i).getParamValue ());
						writeBean.setUsl        (inList3.get(i).getUsl        ());
						writeBean.setLsl        (inList3.get(i).getLsl        ());
						writeBean.setBeginTime  (inList3.get(i).getBeginTime  ());
						
						SO_SYS_CELLID_LIST2_Bean bean = mapGlass.get(inList3.get(i).getGlassId());
						if (bean != null) {
							writeBean.setClusterId  (bean.getClusterId());
							writeBean.setGroupId    (bean.getGroup    ());
						} else {
							writeBean.setClusterId  ("");
							writeBean.setGroupId    ("");
						}

						outList1.add(writeBean);
					}
				}
				
				if (Flag.TRUE) {
					/*
					 * 자료를 SORT 한다.
					 */
					
					Collections.sort(outList1, new Comparator<EQP_FDC02_DETAILS01_Bean>() {
						@Override
						public int compare(EQP_FDC02_DETAILS01_Bean bean1, EQP_FDC02_DETAILS01_Bean bean2) {
							int ret = 0;
							
							// 1. CLUSTER_ID
							ret = bean1.getClusterId().compareTo(bean2.getClusterId());
							if (ret != 0) return ret;

							// 2. PART
							ret = bean1.getPart().compareTo(bean2.getPart());
							if (ret != 0) return ret;
							
							// 3. EQP
							ret = bean1.getEqpId().compareTo(bean2.getEqpId());
							if (ret != 0) return ret;
							
							// 4. SENSOR_NAME
							ret = bean1.getSensorName().compareTo(bean2.getSensorName());
							if (ret != 0) return ret;
							
							// 5. PARAM_VALUE
							double dbl = Double.parseDouble(bean1.getParamValue()) - Double.parseDouble(bean2.getParamValue());
							if (dbl < 0)
								return -1;
							else if (dbl > 0)
								return 1;
							
							// 6. GROUP_ID
							ret = bean1.getGroupId().compareTo(bean2.getGroupId());
									
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
				
				EQP_FDC02_DETAILS01_Main.cntWList = outList1.size();

				writer1.writeList();
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		if (Flag.TRUE) {
			/*
			 * 시간기록 끝
			 */
			EQP_FDC02_DETAILS01_Main.runMSec = (System.nanoTime() - EQP_FDC02_DETAILS01_Main.runMSec) / 1000 / 1000;
			
			if (Flag.TRUE) Logger.info("##### took time : %d ms", EQP_FDC02_DETAILS01_Main.runMSec);
		}

		if (Flag.TRUE) {
			/*
			 * UPDATE
			 */
			AnalCsvBean bean = new AnalCsvBean();
			
			bean.setType(AnalCsvType.UPDATE);
			bean.setTable("ANAL_CSV");
			bean.setFields(new String[][] {
					{ "LIST_CNT   ", "" + EQP_FDC02_DETAILS01_Main.cntWList },
					{ "E_TIME     ", "SYSDATE" },
					{ "R_MSEC     ", "" + EQP_FDC02_DETAILS01_Main.runMSec },
					{ "CSV_STATUS ", StrUtil.quote("OK") },   // OK, ERROR, SKIP, ETC...
					{ "LOG_MESSAGE", StrUtil.quote("COMPLETE") },  // COMPLETE, EXCEPTION
			});
			bean.setWhere(String.format("JOB_ID = %s AND PROG_NM = %s", StrUtil.quote(jobId), StrUtil.quote(EQP_FDC02_DETAILS01_Main.dsName)));
			
			if (Flag.TRUE) Logger.info("[%s]", bean.getQuery());
			
			bean.sendToOracle();
		}
	}
}
