package sdc.anal.lya.macro.A23.SI_SYS_MOD_EQP_03.v01;

import java.util.List;

import kiea.kr.co.tain.base.Flag;
import kiea.proj.sdc.anal.macro.impl.job.AbstractMacroJob;
import kiea.proj.sdc.anal.util.BasePath;
import kiea.proj.sdc.anal.util.FileUtil;
import kiea.proj.sdc.anal.util.StrUtil;
import kiea.proj.sdc.anal.util.log.v02.Logger;

public class SI_SYS_MOD_EQP_03_MacroJob extends AbstractMacroJob
{
	private SI_SYS_MOD_EQP_02_CsvIo reader1 = null;
	private SI_SYS_MOD_EQP_03_CsvIo writer1 = null;
	
	private List<SI_SYS_MOD_EQP_02_Bean> inList1  = null;
	private List<SI_SYS_MOD_EQP_03_Bean> outList1 = null;
	
	private String filePath = null;

	private String jobId = null;

	public SI_SYS_MOD_EQP_03_MacroJob(String jobId)
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
				reader1 = new SI_SYS_MOD_EQP_02_CsvIo(this.filePath);
				writer1 = new SI_SYS_MOD_EQP_03_CsvIo(this.filePath);
				
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
				int totBadCnt = 0;
				int totGoodCnt = 0;
				
				if (Flag.TRUE) {
					for (SI_SYS_MOD_EQP_02_Bean inBean1 : inList1) {
						totBadCnt += Integer.parseInt(inBean1.getBadCnt());
						totGoodCnt += Integer.parseInt(inBean1.getGoodCnt());
					}
				}
				
				if (Flag.TRUE) {
					for (SI_SYS_MOD_EQP_02_Bean inBean1 : inList1) {
						
						double badRatio  = Double.parseDouble(inBean1.getBadCnt ()) / totBadCnt ;
						double goodRatio = Double.parseDouble(inBean1.getGoodCnt()) / totGoodCnt;
						double badGoodRatio = badRatio - goodRatio;
						
						SI_SYS_MOD_EQP_03_Bean outBean1 = new SI_SYS_MOD_EQP_03_Bean();
						
						outBean1.setStepId (inBean1.getStepId ());
						outBean1.setEqpId  (inBean1.getEqpId  ());
						outBean1.setBadCnt (inBean1.getBadCnt ());
						outBean1.setGoodCnt(inBean1.getGoodCnt());
						outBean1.setBadRatio("" + badRatio);
						outBean1.setGoodRatio("" + goodRatio);
						outBean1.setBadGoodRatio("" + badGoodRatio);

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
