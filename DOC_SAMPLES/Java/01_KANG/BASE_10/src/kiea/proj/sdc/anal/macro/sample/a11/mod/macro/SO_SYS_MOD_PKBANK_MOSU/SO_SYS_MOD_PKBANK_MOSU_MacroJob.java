package kiea.proj.sdc.anal.macro.sample.a11.mod.macro.SO_SYS_MOD_PKBANK_MOSU;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import kiea.kr.co.tain.base.Flag;
import kiea.kr.co.tain.util.DateTime;
import kiea.proj.sdc.anal.macro.impl.job.AbstractMacroJob;
import kiea.proj.sdc.anal.util.BasePath;
import kiea.proj.sdc.anal.util.FileUtil;
import kiea.proj.sdc.anal.util.log.v02.Logger;

@SuppressWarnings("unused")
public class SO_SYS_MOD_PKBANK_MOSU_MacroJob extends AbstractMacroJob
{
	private MOD_PKBANK_CsvIo reader1 = null;
	private SO_SYS_MOD_PKBANK_MOSU_CsvIo writer1 = null;
	
	private List<MOD_PKBANK_Bean> inList1  = null;
	private List<SO_SYS_MOD_PKBANK_MOSU_Bean> outList1 = null;
	
	private String strFromDateTime    = null;
	private String strToDateTime      = null;
	private String strLine            = null;
	private String strAreaCode        = null;
	private String strUserGroupCode   = null;
	private String strDefectGroupCode = null;
	
	private String filePath = null;

	private String jobKeyPath = null;
	private String dsName = null;

	public SO_SYS_MOD_PKBANK_MOSU_MacroJob(String jobKeyPath, String dsName)
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
				reader1 = new MOD_PKBANK_CsvIo(this.filePath);
				writer1 = new SO_SYS_MOD_PKBANK_MOSU_CsvIo(this.filePath);
				
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
				Map<String, List<MOD_PKBANK_Bean>> map = new LinkedHashMap<String, List<MOD_PKBANK_Bean>>();
				List<MOD_PKBANK_Bean> list = null;
				
				if (Flag.TRUE) {
					for (MOD_PKBANK_Bean inBean1 : inList1) {
						list = map.get(inBean1.getImptEqpId());
						if (list == null) {
							list = new ArrayList<MOD_PKBANK_Bean>();
							map.put(inBean1.getImptEqpId(), list);
						}
						list.add(inBean1);
					}
				}
				
				if (Flag.TRUE) {
					for (Map.Entry<String, List<MOD_PKBANK_Bean>> entry : map.entrySet()) {

						SO_SYS_MOD_PKBANK_MOSU_Bean outBean1 = new SO_SYS_MOD_PKBANK_MOSU_Bean();
						
						outBean1.setImptEqpId(entry.getKey());
						outBean1.setCount("" + entry.getValue().size());

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
