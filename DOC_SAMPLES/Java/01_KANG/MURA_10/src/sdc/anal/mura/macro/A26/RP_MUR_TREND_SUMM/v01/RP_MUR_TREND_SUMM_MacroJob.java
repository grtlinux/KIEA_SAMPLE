package sdc.anal.mura.macro.A26.RP_MUR_TREND_SUMM.v01;

import java.util.List;
import java.util.SortedMap;
import java.util.SortedSet;
import java.util.TreeMap;

import kiea.kr.co.tain.base.Flag;
import kiea.proj.sdc.anal.macro.impl.job.AbstractMacroJob;
import kiea.proj.sdc.anal.util.BasePath;
import kiea.proj.sdc.anal.util.FileUtil;
import kiea.proj.sdc.anal.util.StrUtil;
import kiea.proj.sdc.anal.util.log.v02.Logger;

public class RP_MUR_TREND_SUMM_MacroJob extends AbstractMacroJob
{
	private Y_AXIS_CsvIo     reader1 = null;
	private RP_MUR_TREND_SUMM_CsvIo  writer1 = null;
	
	private List<Y_AXIS_Bean>     inList1  = null;
	private List<RP_MUR_TREND_SUMM_Bean>  outList1 = null;
	
	private String filePath = null;

	private String jobId = null;

	public RP_MUR_TREND_SUMM_MacroJob(String jobId)
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
				reader1 = new Y_AXIS_CsvIo    (this.filePath);
				writer1 = new RP_MUR_TREND_SUMM_CsvIo (this.filePath);
				
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
				 * 1. InBean -> OutBean
				 */
				if (Flag.TRUE) {

					@SuppressWarnings("unused")
					SortedMap<String, SortedSet<String>> map = new TreeMap<String, SortedSet<String>>();

					if (Flag.TRUE) {
						@SuppressWarnings("unused")
						double mura, yAxis;
						
						for (Y_AXIS_Bean inBean1 : inList1) {
							
							RP_MUR_TREND_SUMM_Bean outBean1 = new RP_MUR_TREND_SUMM_Bean();
							
							outBean1.setDTime  (inBean1.getDTime ());
							outBean1.setTotCnt (inBean1.getTotCnt());
							outBean1.setMura   (inBean1.getMura  ());
							outBean1.setYAxis  (inBean1.getYAxis ());

							outList1.add(outBean1);
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
