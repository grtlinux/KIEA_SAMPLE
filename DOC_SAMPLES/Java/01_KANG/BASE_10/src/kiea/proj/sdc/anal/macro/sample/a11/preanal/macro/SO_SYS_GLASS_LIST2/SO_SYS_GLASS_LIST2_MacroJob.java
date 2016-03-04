package kiea.proj.sdc.anal.macro.sample.a11.preanal.macro.SO_SYS_GLASS_LIST2;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import kiea.kr.co.tain.base.Flag;
import kiea.kr.co.tain.util.DateTime;
import kiea.kr.co.tain.util.Print;
import kiea.proj.sdc.anal.macro.impl.job.AbstractMacroJob;
import kiea.proj.sdc.anal.util.BasePath;
import kiea.proj.sdc.anal.util.FileUtil;
import kiea.proj.sdc.anal.util.log.v02.Logger;

@SuppressWarnings("unused")
public class SO_SYS_GLASS_LIST2_MacroJob extends AbstractMacroJob
{
	private CLUSTER_OUTPUT_CsvIo reader1 = null;
	private GOOD_GLASS_CsvIo reader2 = null;
	private SO_SYS_GLASS_LIST2_CsvIo writer1 = null;
	
	private List<CLUSTER_OUTPUT_Bean> inList1  = null;
	private List<GOOD_GLASS_Bean> inList2  = null;
	private List<SO_SYS_GLASS_LIST2_Bean> outList1 = null;
	
	private String strFromDateTime    = null;
	private String strToDateTime      = null;
	private String strLine            = null;
	private String strAreaCode        = null;
	private String strUserGroupCode   = null;
	private String strDefectGroupCode = null;
	
	private String filePath = null;

	private String jobKeyPath = null;
	private String dsName = null;

	public SO_SYS_GLASS_LIST2_MacroJob(String jobKeyPath, String dsName)
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
				reader1 = new CLUSTER_OUTPUT_CsvIo(this.filePath);
				reader2 = new GOOD_GLASS_CsvIo(this.filePath);
				writer1 = new SO_SYS_GLASS_LIST2_CsvIo(this.filePath);
				
				inList1  = reader1.getList();
				inList2  = reader2.getList();
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
				Map<String, Set<String>> map = new HashMap<String, Set<String>>();
				
				if (Flag.TRUE) {
					
					for (CLUSTER_OUTPUT_Bean inBean1 : inList1) {
						Set<String> set = map.get(inBean1.getClusterId());
						
						if (set == null) {
							set = new HashSet<String>();
							map.put(inBean1.getClusterId(), set);
						}
						set.add(inBean1.getGlassId());
					}
					
					if (!Flag.TRUE) {
						for (Map.Entry<String, Set<String>> entry : map.entrySet()) {
							String key = entry.getKey();
							Set<String> set = entry.getValue();
							
							Print.println("[%s] => %s", key, set);
						}
					}
				}
				
				if (Flag.TRUE) {
					
					for (String clusterId : map.keySet()) {
						/*
						 * BAD GLASS
						 */
						for (String glassId : map.get(clusterId)) {

							SO_SYS_GLASS_LIST2_Bean outBean1 = new SO_SYS_GLASS_LIST2_Bean();
							
							outBean1.setGlassId (glassId);
							outBean1.setTarget  ("1");
							outBean1.setGroupNum(clusterId);
							
							outList1.add(outBean1);
						}
						/*
						 * GOOD GLASS
						 */
						for (GOOD_GLASS_Bean inBean2 : inList2) {
							
							SO_SYS_GLASS_LIST2_Bean outBean1 = new SO_SYS_GLASS_LIST2_Bean();
							
							outBean1.setGlassId (inBean2.getGlassId());
							outBean1.setTarget  ("0");
							outBean1.setGroupNum(clusterId);
							
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
