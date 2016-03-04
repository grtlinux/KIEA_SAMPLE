package sdc.anal.lya.macro.A23.RP_SYS_MOD_PKBANK_SUMM.v01;

import java.util.List;

import kiea.kr.co.tain.base.Flag;
import kiea.proj.sdc.anal.macro.impl.job.AbstractMacroJob;
import kiea.proj.sdc.anal.util.BasePath;
import kiea.proj.sdc.anal.util.FileUtil;
import kiea.proj.sdc.anal.util.StrUtil;
import kiea.proj.sdc.anal.util.log.v02.Logger;

public class RP_SYS_MOD_PKBANK_SUMM_MacroJob extends AbstractMacroJob
{
	private SO_SYS_MOD_PKBANK_CsvIo reader1 = null;
	private RP_SYS_MOD_PKBANK_SUMM_CsvIo writer1 = null;
	
	private List<SO_SYS_MOD_PKBANK_Bean> inList1  = null;
	private List<RP_SYS_MOD_PKBANK_SUMM_Bean> outList1 = null;
	
	private String filePath = null;

	private String jobId = null;

	public RP_SYS_MOD_PKBANK_SUMM_MacroJob(String jobId)
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
				reader1 = new SO_SYS_MOD_PKBANK_CsvIo(this.filePath);
				writer1 = new RP_SYS_MOD_PKBANK_SUMM_CsvIo(this.filePath);
				
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
					for (SO_SYS_MOD_PKBANK_Bean inBean1 : inList1) {
						
						RP_SYS_MOD_PKBANK_SUMM_Bean outBean1 = new RP_SYS_MOD_PKBANK_SUMM_Bean();
						
						outBean1.setImptEqpId(inBean1.getImptEqpId());
						outBean1.setMosu     (inBean1.getMo       ());
						outBean1.setJasu     (inBean1.getJa       ());
						outBean1.setRatio    (inBean1.getRatio    ());
						
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
