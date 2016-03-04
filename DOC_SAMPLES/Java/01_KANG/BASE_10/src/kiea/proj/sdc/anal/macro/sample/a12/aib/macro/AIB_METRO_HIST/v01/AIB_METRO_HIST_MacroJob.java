package kiea.proj.sdc.anal.macro.sample.a12.aib.macro.AIB_METRO_HIST.v01;

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
public class AIB_METRO_HIST_MacroJob extends AbstractMacroJob
{
	private AIB_METRO_BASE_H_CsvIo reader1 = null;
	private AIB_METRO_WITH_INSP_CsvIo reader2 = null;
	private AIB_METRO_HIST_CsvIo writer1 = null;
	
	private List<AIB_METRO_BASE_H_Bean> inList1  = null;
	private List<AIB_METRO_WITH_INSP_Bean> inList2  = null;
	private List<AIB_METRO_HIST_Bean> outList1 = null;
	
	private String filePath = null;

	private String jobKeyPath = null;

	public AIB_METRO_HIST_MacroJob(String jobKeyPath)
	{
		this.jobKeyPath = jobKeyPath;
		
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
				reader1 = new AIB_METRO_BASE_H_CsvIo(this.filePath);
				reader2 = new AIB_METRO_WITH_INSP_CsvIo(this.filePath);
				writer1 = new AIB_METRO_HIST_CsvIo(this.filePath);
				
				inList1  = reader1.getList(true);
				inList2  = reader2.getList(true);
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
				Map<String, AIB_METRO_WITH_INSP_Bean> mapWith = new LinkedHashMap<String, AIB_METRO_WITH_INSP_Bean>();
				
				if (Flag.TRUE) {
					for (AIB_METRO_WITH_INSP_Bean inBean2 : inList2) {
						String key = inBean2.getLineCode() + ":" + inBean2.getGlassId();
						
						mapWith.put(key, inBean2);
					}
				}
				
				if (Flag.TRUE) {
					/*
					 * 해당자료만 추출한다.
					 */
					for (AIB_METRO_BASE_H_Bean inBean1 : inList1) {

						String key = inBean1.getLineCode() + ":" + inBean1.getGlassId();
						
						AIB_METRO_WITH_INSP_Bean inBean2 = mapWith.get(key);
						if (key == null)
							continue;
						
						AIB_METRO_HIST_Bean outBean1 = new AIB_METRO_HIST_Bean();

						outBean1.setLineCode (inBean1.getLineCode ());
						outBean1.setProcessId(inBean1.getProcessId());
						outBean1.setProductId(inBean1.getProductId());
						outBean1.setAreaCode (inBean1.getAreaCode ());
						outBean1.setStepId   (inBean1.getStepId   ());
						outBean1.setEqpId    (inBean1.getEqpId    ());
						outBean1.setGlassId  (inBean1.getGlassId  ());
						outBean1.setItemId   (inBean1.getItemId   ());
						outBean1.setSubItemId(inBean1.getSubItemId());
						outBean1.setDataValue(inBean1.getDataValue());
						outBean1.setDcolTime (inBean1.getDcolTime ());
						outBean1.setSpecUcl  (inBean1.getSpecUcl  ());
						outBean1.setSpecLcl  (inBean1.getSpecLcl  ());
						outBean1.setSpecTgt  (inBean1.getSpecTgt  ());

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
