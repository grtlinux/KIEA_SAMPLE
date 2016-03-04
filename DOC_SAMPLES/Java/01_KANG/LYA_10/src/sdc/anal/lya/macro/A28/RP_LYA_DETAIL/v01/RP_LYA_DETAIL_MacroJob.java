package sdc.anal.lya.macro.A28.RP_LYA_DETAIL.v01;

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

public class RP_LYA_DETAIL_MacroJob extends AbstractMacroJob
{
	private INSPCT_HIST_CsvIo reader1 = null;
	private RP_LYA_DETAIL_CsvIo writer1 = null;
	
	private List<INSPCT_HIST_Bean> inList1  = null;
	private List<RP_LYA_DETAIL_Bean> outList1 = null;
	
	private String filePath = null;

	private String jobId = null;

	public RP_LYA_DETAIL_MacroJob(String jobId)
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
				reader1 = new INSPCT_HIST_CsvIo(this.filePath);
				writer1 = new RP_LYA_DETAIL_CsvIo(this.filePath);
				
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
				Map<String, RP_LYA_DETAIL_Bean> mapHour = new LinkedHashMap<String, RP_LYA_DETAIL_Bean>();
				
				if (Flag.TRUE) {
					/*
					 * 누적
					 */
					String paramDecisionCode = StrUtil.quote(Parameter.getInstance().getStrDecisionCode());
					String paramDefectGroupCode = StrUtil.quote(Parameter.getInstance().getStrDefectGroupCode());
					String paramUsl = Parameter.getInstance().getStrUsl();
					if (!Flag.TRUE) Print.println("[Decision:%s] [Defect:%s] [USL:%s]", paramDecisionCode, paramDefectGroupCode, paramUsl);
					
					for (INSPCT_HIST_Bean inBean1 : inList1) {
						
						String hour = inBean1.getInspectDt().substring(0, 13);
						RP_LYA_DETAIL_Bean bean = mapHour.get(hour);
						if (bean == null) {
							bean = new RP_LYA_DETAIL_Bean();
							
							bean.setSubAreaCode (inBean1.getSubAreaCode());
							bean.setInspectHour (hour);
							bean.setCellCount   ("0");
							bean.setBadCellCount("0");
							bean.setBadCellRatio("0");
							bean.setUsl         (paramUsl);
							
							mapHour.put(hour, bean);
						}
						
						if (paramDecisionCode.indexOf(StrUtil.quote(inBean1.getDecisionCode())) >= 0
						&& paramDefectGroupCode.indexOf(StrUtil.quote(inBean1.getDefectGroupCode())) >= 0) {
							bean.addBadCellCount();
						}
						bean.addCellCount();
					}
					
					if (!Flag.TRUE) {
						/*
						 * 확인출력
						 */
						for (Map.Entry<String, RP_LYA_DETAIL_Bean> entry : mapHour.entrySet()) {
							System.out.println(entry.getValue());
						}
					}
				}
				
				if (Flag.TRUE) {
					/*
					 * BadCellRatio 계산
					 * outList1
					 */
					for (Map.Entry<String, RP_LYA_DETAIL_Bean> entry : mapHour.entrySet()) {
						RP_LYA_DETAIL_Bean bean = entry.getValue();
						double ratio = Double.parseDouble(bean.getBadCellCount()) * 100 / Double.parseDouble(bean.getCellCount());
						bean.setBadCellRatio("" + ratio);
						
						outList1.add(bean);
					}
					
					if (!Flag.TRUE) {
						for (RP_LYA_DETAIL_Bean bean : outList1) {
							System.out.println(bean);
						}
					}
				}
				
				if (Flag.TRUE) {
					/*
					 * SORT
					 */
					Collections.sort(outList1, new Comparator<RP_LYA_DETAIL_Bean>() {
						@Override
						public int compare(RP_LYA_DETAIL_Bean bean1, RP_LYA_DETAIL_Bean bean2) {
							int ret = 0;
							
							// 1. CLUSTER_ID
							ret = bean1.getInspectHour().compareTo(bean2.getInspectHour());
							if (ret != 0) return ret;

							return ret;
						}
					});
					
					if (!Flag.TRUE) {
						for (RP_LYA_DETAIL_Bean bean : outList1) {
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
