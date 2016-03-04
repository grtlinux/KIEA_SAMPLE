package kiea.proj.sdc.anal.macro.sample.a12.preanal.macro.SO_SYS_CELLID_LIST2.v01;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import kiea.kr.co.tain.base.Flag;
import kiea.kr.co.tain.util.DateTime;
import kiea.proj.sdc.anal.macro.impl.job.AbstractMacroJob;
import kiea.proj.sdc.anal.util.BasePath;
import kiea.proj.sdc.anal.util.FileUtil;
import kiea.proj.sdc.anal.util.log.v02.Logger;

public class SO_SYS_CELLID_LIST2_MacroJob extends AbstractMacroJob
{
	private AIB_DEFECT_HIST_CsvIo reader1 = null;
	private AIB_INSPCT_HIST_CsvIo reader2 = null;
	private SO_SYS_CELLID_LIST2_CsvIo writer1 = null;
	
	private List<AIB_DEFECT_HIST_Bean> inList1  = null;
	private List<AIB_INSPCT_HIST_Bean> inList2  = null;
	private List<SO_SYS_CELLID_LIST2_Bean> outList1 = null;
	
	private String filePath = null;

	private String jobKeyPath = null;

	public SO_SYS_CELLID_LIST2_MacroJob(String jobKeyPath)
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
				reader1 = new AIB_DEFECT_HIST_CsvIo(this.filePath);
				reader2 = new AIB_INSPCT_HIST_CsvIo(this.filePath);
				writer1 = new SO_SYS_CELLID_LIST2_CsvIo(this.filePath);
				
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
				Map<String, SO_SYS_CELLID_LIST2_Bean> map = new LinkedHashMap<String, SO_SYS_CELLID_LIST2_Bean>();

				if (Flag.TRUE) {
					if (inList1.size() == 0) {
						/*
						 * Defect CNT = 0
						 * AIB_INSPCT_HIST로 처리
						 */
						for (AIB_INSPCT_HIST_Bean inBean2 : inList2) {
							String strGlassId = inBean2.getGlassId();
							
							SO_SYS_CELLID_LIST2_Bean inBean1 = map.get(strGlassId);
							if (inBean1 == null) {
								inBean1 = new SO_SYS_CELLID_LIST2_Bean();
								inBean1.setClusterId("0");
								inBean1.setGlassId(strGlassId);
								
								if ("X".equals(inBean2.getDecisionCode())) {
									inBean1.setGroup("BAD");
								} else {
									inBean1.setGroup("GOOD");
								}
								
								map.put(strGlassId, inBean1);
							}
							
							if ("X".equals(inBean2.getDecisionCode())) {
								inBean1.setGroup("BAD");
							}
						}
					} else {
						/*
						 * DBSCAN 실시
						 * TODO : 2014.09.14 : 자료를 만들어 로직 처리하기로 함.
						 */
						
					}
					
					if (!Flag.TRUE) {
						/*
						 * 확인
						 */
						for (Map.Entry<String, SO_SYS_CELLID_LIST2_Bean> entry : map.entrySet()) {
							System.out.println(entry.getValue());
						}
					}
				}
				
				if (Flag.TRUE) {
					/*
					 * map을 CSV로 저장
					 */
					for (Map.Entry<String, SO_SYS_CELLID_LIST2_Bean> entry : map.entrySet()) {
						outList1.add(entry.getValue());
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
