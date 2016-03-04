package kiea.proj.sdc.anal.macro.sample.a11.aib.macro.Y_AXIS_CELL;

import java.util.List;
import java.util.SortedMap;
import java.util.SortedSet;
import java.util.TreeMap;

import kiea.kr.co.tain.base.Flag;
import kiea.kr.co.tain.util.DateTime;
import kiea.proj.sdc.anal.macro.impl.job.AbstractMacroJob;
import kiea.proj.sdc.anal.util.BasePath;
import kiea.proj.sdc.anal.util.FileUtil;
import kiea.proj.sdc.anal.util.log.v02.Logger;

@SuppressWarnings("unused")
public class Y_AXIS_CELL_MacroJob extends AbstractMacroJob
{
	private T_CNT_CELL_CsvIo     reader1 = null;
	private MURA_CNT_CELL_CsvIo  reader2 = null;
	private Y_AXIS_CELL_CsvIo    writer1 = null;
	
	private List<T_CNT_CELL_Bean>     inList1  = null;
	private List<MURA_CNT_CELL_Bean>  inList2  = null;
	private List<Y_AXIS_CELL_Bean>    outList1 = null;
	
	private String strFromDateTime    = null;
	private String strToDateTime      = null;
	private String strLine            = null;
	private String strAreaCode        = null;
	private String strUserGroupCode   = null;
	private String strDefectGroupCode = null;
	
	private String filePath = null;

	private String jobKeyPath = null;
	private String dsName = null;

	public Y_AXIS_CELL_MacroJob(String jobKeyPath, String dsName)
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
				reader1 = new T_CNT_CELL_CsvIo     (this.filePath);
				reader2 = new MURA_CNT_CELL_CsvIo  (this.filePath);
				writer1 = new Y_AXIS_CELL_CsvIo    (this.filePath);
				
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
				if (Flag.TRUE) {

					SortedMap<String, SortedSet<String>> map = new TreeMap<String, SortedSet<String>>();

					if (Flag.TRUE) {
						double sumMura, avgMura;
						
						for (T_CNT_CELL_Bean inBean1 : inList1) {
							MURA_CNT_CELL_Bean inBean2 = reader2.get(inBean1.getCellLocId());

							if (inBean2 == null) {
								sumMura = 0.0;
								avgMura = 0.0;
							} else {
								sumMura = Double.parseDouble(inBean2.getSMura());
								avgMura = sumMura / Integer.parseInt(inBean1.getTotCnt());
							}
							
							Y_AXIS_CELL_Bean outBean1 = new Y_AXIS_CELL_Bean();
							
							outBean1.setCellLocId(inBean1.getCellLocId());
							outBean1.setSMura("" + sumMura);
							outBean1.setTotCnt(inBean1.getTotCnt());
							outBean1.setAvgMura("" + avgMura);

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
