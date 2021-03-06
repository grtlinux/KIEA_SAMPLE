package kiea.proj.sdc.anal.macro.sample.a11.mod.macro.RP_SYS_MOD_SUMM_WORKERID;

import java.util.List;

import kiea.kr.co.tain.base.Flag;
import kiea.kr.co.tain.util.DateTime;
import kiea.proj.sdc.anal.macro.impl.job.AbstractMacroJob;
import kiea.proj.sdc.anal.util.BasePath;
import kiea.proj.sdc.anal.util.FileUtil;
import kiea.proj.sdc.anal.util.log.v02.Logger;

@SuppressWarnings("unused")
public class RP_SYS_MOD_SUMM_WORKERID_MacroJob extends AbstractMacroJob
{
	private SO_SYS_MOD_WORKID_CSV_CsvIo reader1 = null;
	private RP_SYS_MOD_SUMM_WORKERID_CsvIo writer1 = null;
	
	private List<SO_SYS_MOD_WORKID_CSV_Bean> inList1  = null;
	private List<RP_SYS_MOD_SUMM_WORKERID_Bean> outList1 = null;
	
	private String strFromDateTime    = null;
	private String strToDateTime      = null;
	private String strLine            = null;
	private String strAreaCode        = null;
	private String strUserGroupCode   = null;
	private String strDefectGroupCode = null;
	
	private String filePath = null;

	private String jobKeyPath = null;
	private String dsName = null;

	public RP_SYS_MOD_SUMM_WORKERID_MacroJob(String jobKeyPath, String dsName)
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
				reader1 = new SO_SYS_MOD_WORKID_CSV_CsvIo(this.filePath);
				writer1 = new RP_SYS_MOD_SUMM_WORKERID_CsvIo(this.filePath);
				
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
					
					for (SO_SYS_MOD_WORKID_CSV_Bean inBean1 : inList1) {

						if ("0".equals(inBean1.getBadRate()))
							continue;
						
						RP_SYS_MOD_SUMM_WORKERID_Bean outBean1 = new RP_SYS_MOD_SUMM_WORKERID_Bean();
						
						outBean1.setCode     ("no data"             );
						outBean1.setClusterId("0"                   );
						outBean1.setRank     ("-99999"              );
						outBean1.setInspEqpId(inBean1.getInspEqpId());
						outBean1.setWorkerId (inBean1.getWorkerId ());
						outBean1.setTot      (inBean1.getTot      ());
						outBean1.setBad      (inBean1.getBad      ());
						outBean1.setBadRate  (inBean1.getBadRate  ());

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
