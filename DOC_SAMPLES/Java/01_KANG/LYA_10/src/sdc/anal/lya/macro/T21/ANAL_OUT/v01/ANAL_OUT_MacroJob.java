package sdc.anal.lya.macro.T21.ANAL_OUT.v01;

import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import kiea.kr.co.tain.base.Flag;
import kiea.proj.sdc.anal.macro.impl.job.AbstractMacroJob;
import kiea.proj.sdc.anal.util.BasePath;
import kiea.proj.sdc.anal.util.FileUtil;
import kiea.proj.sdc.anal.util.StrUtil;
import kiea.proj.sdc.anal.util.log.v02.Logger;

public class ANAL_OUT_MacroJob extends AbstractMacroJob
{
	private ANAL_JOBID_CsvIo reader1 = null;
	private ANAL_PARAM_CsvIo reader2 = null;
	private ANAL_OUT_CsvIo writer1 = null;
	
	private List<ANAL_JOBID_Bean> inList1  = null;
	private List<ANAL_PARAM_Bean> inList2  = null;
	private List<ANAL_OUT_Bean> outList1 = null;
	
	private String filePath = null;

	private String jobId = null;

	public ANAL_OUT_MacroJob(String jobId)
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
				reader1 = new ANAL_JOBID_CsvIo(this.filePath);
				reader2 = new ANAL_PARAM_CsvIo(this.filePath);
				writer1 = new ANAL_OUT_CsvIo(this.filePath);
				
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
				 */
				Map<String, ANAL_OUT_Bean> mapOut = new LinkedHashMap<String, ANAL_OUT_Bean>();
				
				if (Flag.TRUE) {
					/*
					 * ANAL_OUT Hash Map
					 */
					for (ANAL_JOBID_Bean inBean1 : inList1) {
						
						ANAL_OUT_Bean outBean1 = new ANAL_OUT_Bean();

						outBean1.settDate   (inBean1.getJobId().substring(5, 11));
						outBean1.setJobId   (inBean1.getJobId   ());
						outBean1.setCntParam("0");
						
						mapOut.put(inBean1.getJobId(), outBean1);
					}
					
					if (!Flag.TRUE) {
						/*
						 * 확인출력
						 */
						for (Map.Entry<String, ANAL_OUT_Bean> entry : mapOut.entrySet()) {
							String key = entry.getKey();
							ANAL_OUT_Bean bean = entry.getValue();
							
							System.out.println("[" + key + "] -" + bean);
						}
					}
				}
				
				if (Flag.TRUE) {
					/*
					 * ANAL_OUT 과 ANAL_PARAM 과 Join 및 count
					 */
					
					for (ANAL_PARAM_Bean inBean2 : inList2) {
						String key = inBean2.getJobId();
						
						ANAL_OUT_Bean bean = mapOut.get(key);
						if (bean == null) {
							;
						} else {
							bean.addCntParam();
						}
					}
					
					if (!Flag.TRUE) {
						/*
						 * 확인출력
						 */
						for (Map.Entry<String, ANAL_OUT_Bean> entry : mapOut.entrySet()) {
							String key = entry.getKey();
							ANAL_OUT_Bean bean = entry.getValue();
							
							System.out.println("[" + key + "] -" + bean);
						}
					}
				}
				
				if (Flag.TRUE) {
					
					for (Map.Entry<String, ANAL_OUT_Bean> entry : mapOut.entrySet()) {
						ANAL_OUT_Bean bean = entry.getValue();
						
						outList1.add(bean);
					}
				}
				
				if (Flag.TRUE) {
					/*
					 * JOBID sort
					 */
					Collections.sort(outList1, new Comparator<ANAL_OUT_Bean>() {
						@Override
						public int compare(ANAL_OUT_Bean bean1, ANAL_OUT_Bean bean2) {
							int ret = 0;
							
							// 1. JOBID
							ret = bean2.getJobId().compareTo(bean1.getJobId());
							if (ret != 0) return ret;

							return ret;
						}
					});
					
					if (!Flag.TRUE) {
						/*
						 * 확인출력
						 */
						for (ANAL_OUT_Bean bean : outList1) {
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
