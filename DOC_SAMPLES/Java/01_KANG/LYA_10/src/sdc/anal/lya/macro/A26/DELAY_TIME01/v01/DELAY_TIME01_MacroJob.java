package sdc.anal.lya.macro.A26.DELAY_TIME01.v01;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import kiea.kr.co.tain.base.Flag;
import kiea.kr.co.tain.util.DateTime;
import kiea.proj.sdc.anal.macro.impl.job.AbstractMacroJob;
import kiea.proj.sdc.anal.util.BasePath;
import kiea.proj.sdc.anal.util.FileUtil;
import kiea.proj.sdc.anal.util.StrUtil;
import kiea.proj.sdc.anal.util.log.v02.Logger;

public class DELAY_TIME01_MacroJob extends AbstractMacroJob
{
	private SO_SYS_CELLID_LIST2_CsvIo reader1 = null;
	private WIP_EQP_SMMRY_CsvIo reader2 = null;
	private DELAY_TIME01_CsvIo writer1 = null;
	
	private List<SO_SYS_CELLID_LIST2_Bean> inList1  = null;
	private List<WIP_EQP_SMMRY_Bean> inList2  = null;
	private List<DELAY_TIME01_Bean> outList1 = null;
	
	private String filePath = null;

	private String jobId = null;

	public DELAY_TIME01_MacroJob(String jobId)
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
			try {
				reader1 = new SO_SYS_CELLID_LIST2_CsvIo(this.filePath);
				reader2 = new WIP_EQP_SMMRY_CsvIo(this.filePath);
				writer1 = new DELAY_TIME01_CsvIo(this.filePath);
				
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
			/*
			 * TODO : 2014.10.31 : 건수 많은 것에 대한 처리
			 */
			if (inList2.size() > 100000)
				return;
		}
		
		if (Flag.TRUE) {
			try {
				Map<String, List<SO_SYS_CELLID_LIST2_Bean>> mapGlassList = new LinkedHashMap<String, List<SO_SYS_CELLID_LIST2_Bean>>();
				
				if (Flag.TRUE) {
					/*
					 * GLASS_ID에 대한 list 정리
					 */
					for (SO_SYS_CELLID_LIST2_Bean bean : inList1) {
						
						List<SO_SYS_CELLID_LIST2_Bean> list = mapGlassList.get(bean.getGlassId());
						
						if (list == null) {
							list = new ArrayList<SO_SYS_CELLID_LIST2_Bean>();
							
							mapGlassList.put(bean.getGlassId(), list);
							
							list.add(bean);
						} else {
							if (Flag.TRUE) {
								/*
								 * TODO : 2014.10.31
								 * DELAY_TIME의 FDC 에서 사용하는 SO_SYS_CELLID_LIST2의 단위는 GLASS_ID입니다.
								 * CELL_ID를 기본단위로 사용할 시에는 list.add(bean)을 실행해야 합니다.
								 */
								list.add(bean);
							}
						}
					}
					
					if (!Flag.TRUE) {
						/*
						 * 확인출력
						 */
						for (Map.Entry<String, List<SO_SYS_CELLID_LIST2_Bean>> entry : mapGlassList.entrySet()) {
							String glassId = entry.getKey();
							List<SO_SYS_CELLID_LIST2_Bean> list = entry.getValue();
							
							System.out.println("<" + glassId + ">");
							for (SO_SYS_CELLID_LIST2_Bean bean : list) {
								System.out.println("\t" + bean);
							}
						}
					}
				}
				
				if (Flag.TRUE) {
					/*
					 * WIP_EQP_SMMRY SORT
					 */
					Collections.sort(inList2, new Comparator<WIP_EQP_SMMRY_Bean>() {
						@Override
						public int compare(WIP_EQP_SMMRY_Bean bean1, WIP_EQP_SMMRY_Bean bean2) {
							int ret = 0;
							
							// 1. GLASS_ID
							ret = bean1.getGlassId().compareTo(bean2.getGlassId());
							if (ret != 0) return ret;

							// 2. TRCK_IN_TIME
							ret = bean1.getTrckInTime().compareTo(bean2.getTrckInTime());
							if (ret != 0) return ret;

							return ret;
						}
					});
					
					if (!Flag.TRUE) {
						/*
						 * 확인출력
						 */
						for (WIP_EQP_SMMRY_Bean bean : inList2) {
							System.out.println(bean);
						}
					}
				}
				
				if (Flag.TRUE) {
					/*
					 * TODO : 2014.10.30 : 나중에 필드항목과 논리을 재정의 할 것
					 * 처리
					 */
					for (int i=0; i < inList2.size() - 1; i++) {
						if (inList2.get(i).getGlassId().equals(inList2.get(i+1).getGlassId())) {
							
							if ("".equals(inList2.get(i).getTrckInTime()) || "".equals(inList2.get(i+1).getTrckInTime()))
								continue;
							
							//String stepName = inList2.get(i+1).getStepId() + "_" + inList2.get(i).getStepId();
							String stepName = inList2.get(i).getStepId() + "_" + inList2.get(i+1).getStepId();
							long stepValue = DateTime.getMilliSeconds(inList2.get(i+1).getTrckInTime()) - DateTime.getMilliSeconds(inList2.get(i).getTrckOutTime());
							String glassId = inList2.get(i).getGlassId();
							
							if (stepValue < 0)
								continue;
							
							List<SO_SYS_CELLID_LIST2_Bean> list = mapGlassList.get(glassId);
							if (list != null) {
								for (SO_SYS_CELLID_LIST2_Bean bean : list) {
									DELAY_TIME01_Bean outBean = new DELAY_TIME01_Bean();
									
									outBean.setClusterId(bean.getClusterId());
									outBean.setStepName (stepName);
									outBean.setStepValue("" + stepValue);
									outBean.setGlassId  (glassId);
									outBean.setGroup    (bean.getGroup());
									
									outList1.add(outBean);
								}
							}
						}
					}
					
					if (!Flag.TRUE) {
						/*
						 * 확인출력
						 */
						for (DELAY_TIME01_Bean bean : outList1) {
							System.out.println(bean);
						}
					}
				}
				
				if (Flag.TRUE) {
					/*
					 * SORT
					 */
					Collections.sort(outList1, new Comparator<DELAY_TIME01_Bean>() {
						@Override
						public int compare(DELAY_TIME01_Bean bean1, DELAY_TIME01_Bean bean2) {
							int ret = 0;
							
							double dbl = 0;
							
							// 1. CLUSTER_ID
							dbl = Double.parseDouble(bean1.getClusterId()) - Double.parseDouble(bean2.getClusterId());
							if (dbl < 0)
								return -1;
							else if (dbl > 0)
								return 1;
							
							// 2. STEP_NAME
							ret = bean1.getStepName().compareTo(bean2.getStepName());
							if (ret != 0) return ret;

							// 3. STEP_VALUE
							dbl = Double.parseDouble(bean1.getStepValue()) - Double.parseDouble(bean2.getStepValue());
							if (dbl < 0)
								return -1;
							else if (dbl > 0)
								return 1;

							// 4. GROUP
							ret = bean1.getGroup().compareTo(bean2.getGroup());
							if (ret != 0) return ret;

							return ret;
						}
					});
					
					if (!Flag.TRUE) {
						/*
						 * 확인출력
						 */
						for (DELAY_TIME01_Bean bean : outList1) {
							System.out.println(bean);
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
				
				writer1.writeList();
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
