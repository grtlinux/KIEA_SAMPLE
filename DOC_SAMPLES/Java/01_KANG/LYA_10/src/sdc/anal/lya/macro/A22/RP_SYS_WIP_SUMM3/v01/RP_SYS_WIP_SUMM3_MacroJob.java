package sdc.anal.lya.macro.A22.RP_SYS_WIP_SUMM3.v01;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import kiea.kr.co.tain.base.Flag;
import kiea.kr.co.tain.util.Print;
import kiea.proj.sdc.anal.common.Parameter;
import kiea.proj.sdc.anal.macro.impl.job.AbstractMacroJob;
import kiea.proj.sdc.anal.util.BasePath;
import kiea.proj.sdc.anal.util.FileUtil;
import kiea.proj.sdc.anal.util.StrUtil;
import kiea.proj.sdc.anal.util.log.v02.Logger;

public class RP_SYS_WIP_SUMM3_MacroJob extends AbstractMacroJob
{
	//private SO_SYS_CELLID_LIST2_CsvIo reader1 = null;
	private WIP_EQP_SMMRY_CsvIo reader2 = null;
	private INSPCT_HIST_CsvIo reader3 = null;
	private RP_SYS_WIP_SUMM3_CsvIo writer1 = null;
	
	//private List<SO_SYS_CELLID_LIST2_Bean> inList1  = null;
	private List<WIP_EQP_SMMRY_Bean> inList2  = null;
	private List<INSPCT_HIST_Bean> inList3  = null;
	private List<RP_SYS_WIP_SUMM3_Bean> outList1 = null;
	
	private String filePath = null;

	private String jobId = null;

	public RP_SYS_WIP_SUMM3_MacroJob(String jobId)
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
				//reader1 = new SO_SYS_CELLID_LIST2_CsvIo(this.filePath);
				reader2 = new WIP_EQP_SMMRY_CsvIo(this.filePath);
				reader3 = new INSPCT_HIST_CsvIo(this.filePath);
				writer1 = new RP_SYS_WIP_SUMM3_CsvIo(this.filePath);
				
				//inList1  = reader1.getList(true);
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
				
				String paramDecisionCode = StrUtil.quote(Parameter.getInstance().getStrDecisionCode());
				String paramDefectGroupCode = StrUtil.quote(Parameter.getInstance().getStrDefectGroupCode());
				if (!Flag.TRUE) Print.println("[Decision:%s] [Defect:%s]", paramDecisionCode, paramDefectGroupCode);

				Map<String, List<INSPCT_HIST_Bean>> mapListInspct = new LinkedHashMap<String, List<INSPCT_HIST_Bean>>();
				Map<String, RP_SYS_WIP_SUMM3_Bean> mapWip = new LinkedHashMap<String, RP_SYS_WIP_SUMM3_Bean>();
				
				if (Flag.TRUE) {
					/*
					 * INSPCT_HIST_Bean
					 */
					for (INSPCT_HIST_Bean inBean3 : inList3) {
						String key = inBean3.getGlassId();
						
						List<INSPCT_HIST_Bean> listInspct = mapListInspct.get(key);
						if (listInspct == null) {
							listInspct = new ArrayList<INSPCT_HIST_Bean>();
							mapListInspct.put(key, listInspct);
						}
						
						listInspct.add(inBean3);
					}
					
					if (!Flag.TRUE) {
						/*
						 * 확인출력
						 */
						for (Map.Entry<String, List<INSPCT_HIST_Bean>> listEntry : mapListInspct.entrySet()) {
							System.out.println(listEntry.getKey());
							List<INSPCT_HIST_Bean> listBean = listEntry.getValue();
							for (INSPCT_HIST_Bean bean : listBean) {
								System.out.println("\t" + bean);
							}
						}
					}
				}
				
				if (Flag.TRUE) {
					/*
					 * WIP_EQP_SMMRY 중복제거
					 */
				}

				if (Flag.TRUE) {
					/*
					 * WIP_EQP_SMMRY 누적처리
					 */
					for (WIP_EQP_SMMRY_Bean inBean2 : inList2) {
						
						List<INSPCT_HIST_Bean> listInspct = mapListInspct.get(inBean2.getGlassId());
						if (listInspct == null)
							continue;

						for (INSPCT_HIST_Bean inBean3 : listInspct) {
							
							if (Flag.TRUE) {
								/*
								 * CNT
								 */
								String key = inBean2.getStepId() + ":" + inBean2.getEqpId();
								
								RP_SYS_WIP_SUMM3_Bean bean = mapWip.get(key);
								if (bean == null) {
									bean = new RP_SYS_WIP_SUMM3_Bean();
									
									bean.setDivCode   ("EQP_ID");
									bean.setStepId    (inBean2.getStepId());
									bean.setEqpId     (inBean2.getEqpId());
									bean.setTotCellCnt("0");
									bean.setBadCellCnt("0");

									mapWip.put(key, bean);
								}
								
								if (paramDecisionCode.indexOf(StrUtil.quote(inBean3.getDecisionCode())) >= 0
								&& paramDefectGroupCode.indexOf(StrUtil.quote(inBean3.getDefectGroupCode())) >= 0) {
									bean.addBadCellCnt();
								}
								
								bean.addTotCellCnt();
							}
						}
					}
					
					if (!Flag.TRUE) {
						/*
						 * 확인출력
						 */
						for (Map.Entry<String, RP_SYS_WIP_SUMM3_Bean> entry : mapWip.entrySet()) {
							System.out.println(entry.getValue());
						}
					}
				}
				
				if (Flag.TRUE) {
					/*
					 * RP_SYS_WIP_SUMM3_Bean
					 */
					for (Map.Entry<String, RP_SYS_WIP_SUMM3_Bean> entry : mapWip.entrySet()) {
						outList1.add(entry.getValue());
					}
				}
					
				if (Flag.TRUE) {
					/*
					 * RP_SYS_WIP_SUMM3 CELL RATIO 값 처리
					 */
					for (RP_SYS_WIP_SUMM3_Bean outBean1 : outList1) {
						
						double ratioBadCell = Double.parseDouble(outBean1.getBadCellCnt()) * 100 / Double.parseDouble(outBean1.getTotCellCnt());
						
						outBean1.setCellBadRatio  ("" + ratioBadCell);
					}
					
					if (!Flag.TRUE) {
						/*
						 * 확인출력
						 */
						for (RP_SYS_WIP_SUMM3_Bean bean : outList1) {
							System.out.println(bean);
						}
					}
				}
				
				if (Flag.TRUE) {
					/*
					 * SORT
					 *     STEP_ID
					 *     EQP_ID
					 */
					Collections.sort(outList1, new Comparator<RP_SYS_WIP_SUMM3_Bean>() {
						@Override
						public int compare(RP_SYS_WIP_SUMM3_Bean bean1, RP_SYS_WIP_SUMM3_Bean bean2) {
							int ret = 0;
							
							// 1. STEP_ID
							ret = bean1.getStepId().compareTo(bean2.getStepId());
							if (ret != 0) return ret;

							// 2. EQP_ID
							ret = bean1.getEqpId().compareTo(bean2.getEqpId());
							if (ret != 0) return ret;

							return ret;
						}
					});
					
					if (!Flag.TRUE) {
						/*
						 * 확인출력
						 */
						for (RP_SYS_WIP_SUMM3_Bean bean : outList1) {
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
