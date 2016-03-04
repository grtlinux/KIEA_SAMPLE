package kiea.proj.sdc.anal.macro.sample.a11.preanal.macro.FINAL_CLUSTER;

import java.util.List;

import kiea.kr.co.tain.base.Flag;
import kiea.kr.co.tain.util.DateTime;
import kiea.proj.sdc.anal.macro.impl.job.AbstractMacroJob;
import kiea.proj.sdc.anal.util.BasePath;
import kiea.proj.sdc.anal.util.FileUtil;
import kiea.proj.sdc.anal.util.log.v02.Logger;

@SuppressWarnings("unused")
public class FINAL_CLUSTER_MacroJob extends AbstractMacroJob
{
	private REGROUP_CLUSTER_CsvIo reader1 = null;
	private FINAL_CLUSTER_CsvIo writer1 = null;
	
	private List<REGROUP_CLUSTER_Bean> inList1  = null;
	private List<FINAL_CLUSTER_Bean> outList1 = null;
	
	private String strFromDateTime    = null;
	private String strToDateTime      = null;
	private String strLine            = null;
	private String strAreaCode        = null;
	private String strUserGroupCode   = null;
	private String strDefectGroupCode = null;
	
	private String filePath = null;

	private String jobKeyPath = null;
	private String dsName = null;

	public FINAL_CLUSTER_MacroJob(String jobKeyPath, String dsName)
	{
		this.jobKeyPath = jobKeyPath;
		this.dsName = dsName;
		
		this.filePath = FileUtil.makeDataDir(BasePath.getInstance().getDataPath(), DateTime.getDate(2), this.jobKeyPath);
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
				if (Flag.TRUE) {
					/*
					 * 해당자료만 추출한다.
					 */
					int dataNum = 0;
					
					for (REGROUP_CLUSTER_Bean inBean1 : inList1) {
						
						dataNum ++;
						
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
