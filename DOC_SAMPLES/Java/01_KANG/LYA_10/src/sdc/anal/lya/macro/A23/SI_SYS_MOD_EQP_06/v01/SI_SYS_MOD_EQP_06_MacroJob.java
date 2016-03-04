package sdc.anal.lya.macro.A23.SI_SYS_MOD_EQP_06.v01;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import kiea.kr.co.tain.base.Flag;
import kiea.proj.sdc.anal.common.Parameter;
import kiea.proj.sdc.anal.macro.impl.job.AbstractMacroJob;
import kiea.proj.sdc.anal.util.BasePath;
import kiea.proj.sdc.anal.util.FileUtil;
import kiea.proj.sdc.anal.util.StrUtil;
import kiea.proj.sdc.anal.util.log.v02.Logger;

public class SI_SYS_MOD_EQP_06_MacroJob extends AbstractMacroJob
{
	private SI_SYS_MOD_EQP_01_CsvIo reader1 = null;
	private SI_SYS_MOD_EQP_06_CsvIo writer1 = null;
	
	private List<SI_SYS_MOD_EQP_01_Bean> inList1  = null;
	private List<SI_SYS_MOD_EQP_06_Bean> outList1 = null;
	
	private String filePath = null;

	private String jobId = null;

	public SI_SYS_MOD_EQP_06_MacroJob(String jobId)
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
				reader1 = new SI_SYS_MOD_EQP_01_CsvIo(this.filePath);
				writer1 = new SI_SYS_MOD_EQP_06_CsvIo(this.filePath);
				
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

				Map<String, List<SI_SYS_MOD_EQP_01_Bean>> map = new LinkedHashMap<String, List<SI_SYS_MOD_EQP_01_Bean>>();
				List<SI_SYS_MOD_EQP_01_Bean> list = null;
				
				if (Flag.TRUE) {
					for (SI_SYS_MOD_EQP_01_Bean inBean1 : inList1) {
						
						String key = inBean1.getStepId() + ":" + inBean1.getEqpId();
						list = map.get(key);
						if (list == null) {
							list = new ArrayList<SI_SYS_MOD_EQP_01_Bean>();
							map.put(key, list);
						}
						
						list.add(inBean1);
					}
				}
				
				if (Flag.TRUE) {
					for (Map.Entry<String, List<SI_SYS_MOD_EQP_01_Bean>> entry : map.entrySet()) {
						list = entry.getValue();
						
						SI_SYS_MOD_EQP_01_Bean inBean = list.get(0);
						int totCnt = list.size();
						int badCnt = 0;
						int goodCnt = 0;
						for (SI_SYS_MOD_EQP_01_Bean inBean1 : list) {
							if (strDecisionCode.equals(inBean1.getDecisionCode())) {
								badCnt ++;
							} else {
								goodCnt ++;
							}
						}
						
						SI_SYS_MOD_EQP_06_Bean outBean1 = new SI_SYS_MOD_EQP_06_Bean();
						
						outBean1.setStepId (inBean.getStepId ());
						outBean1.setEqpId  (inBean.getEqpId  ());
						outBean1.setTotCnt ("" + totCnt);
						outBean1.setBadCnt ("" + badCnt);
						outBean1.setGoodCnt("" + goodCnt);

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
				
				writer1.writeList();
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
