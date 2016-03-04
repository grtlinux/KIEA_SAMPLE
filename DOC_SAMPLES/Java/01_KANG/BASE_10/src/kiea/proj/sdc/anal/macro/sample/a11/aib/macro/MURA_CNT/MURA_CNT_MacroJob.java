package kiea.proj.sdc.anal.macro.sample.a11.aib.macro.MURA_CNT;

import java.util.List;
import java.util.Map;
import java.util.SortedMap;
import java.util.SortedSet;
import java.util.TreeMap;
import java.util.TreeSet;

import kiea.kr.co.tain.base.Flag;
import kiea.kr.co.tain.util.DateTime;
import kiea.kr.co.tain.util.Print;
import kiea.proj.sdc.anal.macro.impl.job.AbstractMacroJob;
import kiea.proj.sdc.anal.util.BasePath;
import kiea.proj.sdc.anal.util.FileUtil;
import kiea.proj.sdc.anal.util.log.v02.Logger;

@SuppressWarnings("unused")
public class MURA_CNT_MacroJob extends AbstractMacroJob
{
	private MURA_N_TREND_CsvIo reader1 = null;
	private MURA_CNT_CsvIo    writer1 = null;
	
	private List<MURA_N_TREND_Bean> inList1  = null;
	private List<MURA_CNT_Bean>    outList1 = null;
	
	private String strFromDateTime    = null;
	private String strToDateTime      = null;
	private String strLine            = null;
	private String strAreaCode        = null;
	private String strUserGroupCode   = null;
	private String strDefectGroupCode = null;
	
	private String filePath = null;

	private String jobKeyPath = null;
	private String dsName = null;

	public MURA_CNT_MacroJob(String jobKeyPath, String dsName)
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
				reader1 = new MURA_N_TREND_CsvIo (this.filePath);
				writer1 = new MURA_CNT_CsvIo    (this.filePath);
				
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

					SortedMap<String, SortedSet<String>> map = new TreeMap<String, SortedSet<String>>();

					if (Flag.TRUE) {
						/*
						 * setting
						 */
						int idx = 0;
						for (MURA_N_TREND_Bean inBean1 : inList1) {
							idx ++;
							if (!Flag.TRUE) Print.println("(%d) [D_TIME:%s] [CELLID:%s]", idx, inBean1.getDTime(), inBean1.getCellId());
							
							SortedSet<String> set = map.get(inBean1.getDTime());
							if (set == null) {
								map.put(inBean1.getDTime(), new TreeSet<String>());
								set = map.get(inBean1.getDTime());
							}
							set.add(inBean1.getCellId());
						}

						if (!Flag.TRUE) Print.println("data length = %d", idx);
					}
					
					if (!Flag.TRUE) {
						/*
						 * print
						 */
						for (Map.Entry<String, SortedSet<String>> entry : map.entrySet()) {
							String key = entry.getKey();
							SortedSet<String> set = entry.getValue();
							
							if (Flag.TRUE) Print.println("[%s] [size:%d]", key, set.size());
							
							for (String str : set) {
								if (!Flag.TRUE) Print.println("\t(%s)", str);
							}
						}
					}
					
					if (Flag.TRUE) {
						/*
						 * -> OutBean 
						 */
						for (Map.Entry<String, SortedSet<String>> entry : map.entrySet()) {
							String key = entry.getKey();
							SortedSet<String> set = entry.getValue();
							
							MURA_CNT_Bean outBean1 = new MURA_CNT_Bean();
							
							outBean1.setDTime(key);
							outBean1.setMura ("" + set.size());
							
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
