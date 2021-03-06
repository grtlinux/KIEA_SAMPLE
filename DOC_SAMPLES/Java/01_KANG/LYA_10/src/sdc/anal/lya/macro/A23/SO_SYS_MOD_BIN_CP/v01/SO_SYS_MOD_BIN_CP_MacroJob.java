package sdc.anal.lya.macro.A23.SO_SYS_MOD_BIN_CP.v01;

import java.util.List;

import kiea.kr.co.tain.base.Flag;
import kiea.proj.sdc.anal.macro.impl.job.AbstractMacroJob;
import kiea.proj.sdc.anal.util.BasePath;
import kiea.proj.sdc.anal.util.FileUtil;
import kiea.proj.sdc.anal.util.StrUtil;
import kiea.proj.sdc.anal.util.log.v02.Logger;

public class SO_SYS_MOD_BIN_CP_MacroJob extends AbstractMacroJob
{
	private MOD_BIN_CsvIo reader1 = null;
	private SO_SYS_MOD_BIN_CP_CsvIo writer1 = null;
	
	private List<MOD_BIN_Bean> inList1  = null;
	private List<SO_SYS_MOD_BIN_CP_Bean> outList1 = null;
	
	private String filePath = null;

	private String jobId = null;

	public SO_SYS_MOD_BIN_CP_MacroJob(String jobId)
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
				reader1 = new MOD_BIN_CsvIo(this.filePath);
				writer1 = new SO_SYS_MOD_BIN_CP_CsvIo(this.filePath);
				
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
					
					for (MOD_BIN_Bean inBean1 : inList1) {

						SO_SYS_MOD_BIN_CP_Bean outBean1 = new SO_SYS_MOD_BIN_CP_Bean();
						
						outBean1.setInspHour  (inBean1.getInspHour  ());
						outBean1.setBinGradeCd(inBean1.getBinGradeCd());
						outBean1.setTot       (inBean1.getTot       ());
						outBean1.setBad       (inBean1.getBad       ());
						outBean1.setGood      (inBean1.getGood      ());

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
