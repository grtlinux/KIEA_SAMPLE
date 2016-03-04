package kiea.proj.sdc.anal.macro.sample.a11.mod.macro.SO_SYS_MOD_RJ_ANL;

import java.util.List;

import kiea.kr.co.tain.base.Flag;
import kiea.kr.co.tain.util.DateTime;
import kiea.proj.sdc.anal.macro.impl.job.AbstractMacroJob;
import kiea.proj.sdc.anal.util.BasePath;
import kiea.proj.sdc.anal.util.FileUtil;
import kiea.proj.sdc.anal.util.log.v02.Logger;

@SuppressWarnings("unused")
public class SO_SYS_MOD_RJ_ANL_MacroJob extends AbstractMacroJob
{
	private SO_SYS_MOD_RJ_CP_CsvIo reader1 = null;
	private SO_SYS_MOD_RJ_ANL_CsvIo writer1 = null;
	
	private List<SO_SYS_MOD_RJ_CP_Bean> inList1  = null;
	private List<SO_SYS_MOD_RJ_ANL_Bean> outList1 = null;
	
	private String strFromDateTime    = null;
	private String strToDateTime      = null;
	private String strLine            = null;
	private String strAreaCode        = null;
	private String strUserGroupCode   = null;
	private String strDefectGroupCode = null;
	
	private String filePath = null;

	private String jobKeyPath = null;
	private String dsName = null;

	public SO_SYS_MOD_RJ_ANL_MacroJob(String jobKeyPath, String dsName)
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
				reader1 = new SO_SYS_MOD_RJ_CP_CsvIo(this.filePath);
				writer1 = new SO_SYS_MOD_RJ_ANL_CsvIo(this.filePath);
				
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
					
					for (SO_SYS_MOD_RJ_CP_Bean inBean1 : inList1) {

						SO_SYS_MOD_RJ_ANL_Bean outBean1 = new SO_SYS_MOD_RJ_ANL_Bean();
						
						outBean1.setCellId      (inBean1.getCellId      ());
						outBean1.setInspStepType(inBean1.getInspStepType());
						outBean1.setDefectName  (inBean1.getDefectName  ());
						outBean1.setDefectCode  (inBean1.getDefectCode  ());
						outBean1.setInspStepId  (inBean1.getInspStepId  ());
						outBean1.setInspTime    (inBean1.getInspTime    ());
						outBean1.setWorkerName  (inBean1.getWorkerName  ());
						outBean1.setWorkerId    (inBean1.getWorkerId    ());
						outBean1.setInspEqpId   (inBean1.getInspEqpId   ());

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
