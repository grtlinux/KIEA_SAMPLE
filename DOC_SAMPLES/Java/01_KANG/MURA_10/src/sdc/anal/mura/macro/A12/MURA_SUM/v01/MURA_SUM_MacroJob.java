package sdc.anal.mura.macro.A12.MURA_SUM.v01;

import java.util.List;
import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;

import kiea.kr.co.tain.base.Flag;
import kiea.kr.co.tain.util.Print;
import kiea.proj.sdc.anal.macro.impl.job.AbstractMacroJob;
import kiea.proj.sdc.anal.util.BasePath;
import kiea.proj.sdc.anal.util.FileUtil;
import kiea.proj.sdc.anal.util.StrUtil;
import kiea.proj.sdc.anal.util.log.v02.Logger;

public class MURA_SUM_MacroJob extends AbstractMacroJob
{
	private MURA_D_01_CsvIo     reader1 = null;
	private MURA_SUM_CsvIo  writer1 = null;
	
	private List<MURA_D_01_Bean>     inList1  = null;
	private List<MURA_SUM_Bean>  outList1 = null;
	
	private String filePath = null;

	private String jobId = null;

	public MURA_SUM_MacroJob(String jobId)
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
				reader1 = new MURA_D_01_CsvIo    (this.filePath);
				writer1 = new MURA_SUM_CsvIo (this.filePath);
				
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

					SortedMap<String, Double> map = new TreeMap<String, Double>();

					if (Flag.TRUE) {
						/*
						 * setting
						 */
						@SuppressWarnings("unused")
						int idx = 0;
						for (MURA_D_01_Bean inBean1 : inList1) {
							idx ++;
							if (!Flag.TRUE) Print.println("(%d) [GLASSID:%s] [DATAVALUE:%s]", idx, inBean1.getGlassId(), inBean1.getDataValue());
							
							Double sumMura = map.get(inBean1.getGlassId());
							if (sumMura == null) {
								map.put(inBean1.getGlassId(), new Double(0.0));
								sumMura = map.get(inBean1.getGlassId());
							}
							double sum = sumMura.doubleValue() + Double.parseDouble(inBean1.getDataValue());
							
							map.put(inBean1.getGlassId(), new Double(sum));
						}

						if (!Flag.TRUE) Print.println("data length = %d", idx);
					}
					
					if (!Flag.TRUE) {
						/*
						 * print
						 */
						for (Map.Entry<String, Double> entry : map.entrySet()) {
							String key = entry.getKey();
							Double sumMura = entry.getValue();
							
							if (Flag.TRUE) Print.println("[%s] [sumMura:%f]", key, sumMura);
						}
					}
					
					if (Flag.TRUE) {
						/*
						 * -> OutBean 
						 */
						for (Map.Entry<String, Double> entry : map.entrySet()) {
							String key = entry.getKey();
							Double sumMura = entry.getValue();
							
							MURA_SUM_Bean outBean1 = new MURA_SUM_Bean();
							
							outBean1.setGlassId(key);
							outBean1.setMuraSum("" + sumMura);
							
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
