package kiea.proj.sdc.anal.macro.sample.a11.mod.macro.RP_SYS_MOD_DETAILS_INSPWIP;

import java.util.List;

import kiea.kr.co.tain.base.Flag;
import kiea.kr.co.tain.util.DateTime;
import kiea.proj.sdc.anal.macro.impl.job.AbstractMacroJob;
import kiea.proj.sdc.anal.util.BasePath;
import kiea.proj.sdc.anal.util.FileUtil;
import kiea.proj.sdc.anal.util.log.v02.Logger;

@SuppressWarnings("unused")
public class RP_SYS_MOD_DETAILS_INSPWIP_MacroJob extends AbstractMacroJob
{
	private SI_SYS_MOD_EQP_05_CsvIo reader1 = null;
	private RP_SYS_MOD_DETAILS_INSPWIP_CsvIo writer1 = null;
	
	private List<SI_SYS_MOD_EQP_05_Bean> inList1  = null;
	private List<RP_SYS_MOD_DETAILS_INSPWIP_Bean> outList1 = null;
	
	private String strFromDateTime    = null;
	private String strToDateTime      = null;
	private String strLine            = null;
	private String strAreaCode        = null;
	private String strUserGroupCode   = null;
	private String strDefectGroupCode = null;
	
	private String filePath = null;

	private String jobKeyPath = null;
	private String dsName = null;

	public RP_SYS_MOD_DETAILS_INSPWIP_MacroJob(String jobKeyPath, String dsName)
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
				reader1 = new SI_SYS_MOD_EQP_05_CsvIo(this.filePath);
				writer1 = new RP_SYS_MOD_DETAILS_INSPWIP_CsvIo(this.filePath);
				
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
					for (SI_SYS_MOD_EQP_05_Bean inBean1 : inList1) {
						
						RP_SYS_MOD_DETAILS_INSPWIP_Bean outBean1 = new RP_SYS_MOD_DETAILS_INSPWIP_Bean();
						
						outBean1.setDivCode     ("EQP_ID");
						outBean1.setStepId      (inBean1.getStepId      ());
						outBean1.setEqpId       (inBean1.getEqpId       ());
						outBean1.setTrckInHour  (inBean1.getInspectHour3());
						outBean1.setTotCellCnt  (inBean1.getTotCnt      ());
						outBean1.setBadCellCnt  (inBean1.getBadCnt      ());
						outBean1.setCellBadRatio(inBean1.getBadRatio    ());

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
