package kiea.proj.sdc.anal.macro.sample.a11.preanal.macro.CLUSTER_OUTPUT;

import java.util.List;

import kiea.kr.co.tain.base.Flag;
import kiea.kr.co.tain.util.DateTime;
import kiea.proj.sdc.anal.macro.impl.job.AbstractMacroJob;
import kiea.proj.sdc.anal.util.BasePath;
import kiea.proj.sdc.anal.util.FileUtil;
import kiea.proj.sdc.anal.util.log.v02.Logger;

@SuppressWarnings("unused")
public class CLUSTER_OUTPUT_MacroJob extends AbstractMacroJob
{
	private BAD_GLASS_NO_CsvIo reader1 = null;
	private FINAL_CLUSTER_CsvIo reader2 = null;
	private CLUSTER_OUTPUT_CsvIo writer1 = null;
	
	private List<BAD_GLASS_NO_Bean> inList1  = null;
	private List<FINAL_CLUSTER_Bean> inList2  = null;
	private List<CLUSTER_OUTPUT_Bean> outList1 = null;
	
	private String strFromDateTime    = null;
	private String strToDateTime      = null;
	private String strLine            = null;
	private String strAreaCode        = null;
	private String strUserGroupCode   = null;
	private String strDefectGroupCode = null;
	
	private String filePath = null;

	private String jobKeyPath = null;
	private String dsName = null;

	public CLUSTER_OUTPUT_MacroJob(String jobKeyPath, String dsName)
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
				reader1 = new BAD_GLASS_NO_CsvIo(this.filePath);
				reader2 = new FINAL_CLUSTER_CsvIo(this.filePath);
				writer1 = new CLUSTER_OUTPUT_CsvIo(this.filePath);
				
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
				 * 1. InBean -> OutBean
				 */
				if (Flag.TRUE) {

					for (BAD_GLASS_NO_Bean inBean1 : inList1) {
						
						CLUSTER_OUTPUT_Bean outBean1 = new CLUSTER_OUTPUT_Bean();
						
						outBean1.setDataNum  (inBean1.getDataNum  ());
						outBean1.setGlassId  (inBean1.getGlassId  ());
						outBean1.setCellId   (inBean1.getCellId   ());
						outBean1.setXValue   (inBean1.getXValue   ());
						outBean1.setYValue   (inBean1.getYValue   ());
						outBean1.setX2Value  (inBean1.getX2Value  ());
						outBean1.setY2Value  (inBean1.getY2Value  ());
						outBean1.setAvgX     (inBean1.getAvgX     ());
						outBean1.setAvgY     (inBean1.getAvgY     ());
						outBean1.setClusterId(reader2.get(inBean1.getDataNum()).getClusterId());
						
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
