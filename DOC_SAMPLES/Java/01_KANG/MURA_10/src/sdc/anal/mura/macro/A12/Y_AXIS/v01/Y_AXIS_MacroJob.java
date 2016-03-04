package sdc.anal.mura.macro.A12.Y_AXIS.v01;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.SortedMap;
import java.util.SortedSet;
import java.util.TreeMap;

import kiea.kr.co.tain.base.Flag;
import kiea.proj.sdc.anal.macro.impl.job.AbstractMacroJob;
import kiea.proj.sdc.anal.util.BasePath;
import kiea.proj.sdc.anal.util.FileUtil;
import kiea.proj.sdc.anal.util.StrUtil;
import kiea.proj.sdc.anal.util.log.v02.Logger;

public class Y_AXIS_MacroJob extends AbstractMacroJob
{
	private TOTAL_CNT_CsvIo reader1 = null;
	private MURA_CNT_CsvIo  reader2 = null;
	private Y_AXIS_CsvIo    writer1 = null;
	
	private List<TOTAL_CNT_Bean> inList1  = null;
	private List<MURA_CNT_Bean>  inList2  = null;
	private List<Y_AXIS_Bean>    outList1 = null;
	
	private String filePath = null;

	private String jobId = null;

	public Y_AXIS_MacroJob(String jobId)
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
				reader1 = new TOTAL_CNT_CsvIo (this.filePath);
				reader2 = new MURA_CNT_CsvIo  (this.filePath);
				writer1 = new Y_AXIS_CsvIo    (this.filePath);
				
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
				Map<String, MURA_CNT_Bean> mapMura = new LinkedHashMap<String, MURA_CNT_Bean>();
				
				/*
				 * MURA_DESC
				 */
				if (Flag.TRUE) {
					for (MURA_CNT_Bean inBean2 : inList2) {
						mapMura.put(inBean2.getDTime(), inBean2);
					}
				}
				/*
				 * Job
				 * 1. InBean -> OutBean
				 */
				if (Flag.TRUE) {

					@SuppressWarnings("unused")
					SortedMap<String, SortedSet<String>> map = new TreeMap<String, SortedSet<String>>();

					if (Flag.TRUE) {
						double mura, yAxis;
						
						for (TOTAL_CNT_Bean inBean1 : inList1) {
							MURA_CNT_Bean inBean2 = mapMura.get(inBean1.getDTime());

							if (inBean2 == null) {
								mura = 0.0;
								yAxis = 0.0;
							} else {
								mura = Integer.parseInt(inBean2.getMura());
								yAxis = mura / Integer.parseInt(inBean1.getTotCnt());
							}
							
							Y_AXIS_Bean outBean1 = new Y_AXIS_Bean();
							
							outBean1.setDTime(inBean1.getDTime());
							outBean1.setTotCnt(inBean1.getTotCnt());
							outBean1.setMura("" + mura);
							outBean1.setYAxis("" + yAxis);

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
