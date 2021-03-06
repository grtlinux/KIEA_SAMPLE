package sdc.anal.mura.macro.A21.FINAL_CLUSTER.v01;

import java.util.List;

import kiea.kr.co.tain.base.Flag;
import kiea.proj.sdc.anal.macro.impl.job.AbstractMacroJob;
import kiea.proj.sdc.anal.util.BasePath;
import kiea.proj.sdc.anal.util.FileUtil;
import kiea.proj.sdc.anal.util.StrUtil;
import kiea.proj.sdc.anal.util.log.v02.Logger;

public class FINAL_CLUSTER_MacroJob extends AbstractMacroJob
{
	private REGROUP_CLUSTER_CsvIo reader1 = null;
	private FINAL_CLUSTER_CsvIo writer1 = null;
	
	private List<REGROUP_CLUSTER_Bean> inList1  = null;
	private List<FINAL_CLUSTER_Bean> outList1 = null;
	
	private String filePath = null;

	private String jobId = null;

	public FINAL_CLUSTER_MacroJob(String jobId)
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
				reader1 = new REGROUP_CLUSTER_CsvIo(this.filePath);
				writer1 = new FINAL_CLUSTER_CsvIo(this.filePath);
				
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
					/*
					 * 해당자료만 추출한다.
					 */
					for (REGROUP_CLUSTER_Bean inBean1 : inList1) {
						
						FINAL_CLUSTER_Bean outBean1 = new FINAL_CLUSTER_Bean();

						outBean1.setDataNum  (inBean1.getDataNum  ());
						outBean1.setAvgX     (inBean1.getAvgX     ());
						outBean1.setAvgY     (inBean1.getAvgY     ());
						outBean1.setClusterId(inBean1.getClusterId());

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
