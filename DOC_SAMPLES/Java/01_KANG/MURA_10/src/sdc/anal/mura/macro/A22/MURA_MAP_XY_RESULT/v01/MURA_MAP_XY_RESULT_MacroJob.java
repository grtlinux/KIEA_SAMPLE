package sdc.anal.mura.macro.A22.MURA_MAP_XY_RESULT.v01;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import kiea.kr.co.tain.base.Flag;
import kiea.proj.sdc.anal.macro.impl.job.AbstractMacroJob;
import kiea.proj.sdc.anal.util.BasePath;
import kiea.proj.sdc.anal.util.FileUtil;
import kiea.proj.sdc.anal.util.StrUtil;
import kiea.proj.sdc.anal.util.log.v02.Logger;

public class MURA_MAP_XY_RESULT_MacroJob extends AbstractMacroJob
{
	private OUT_DBSCAN_RESULT_CsvIo reader1 = null;
	private MURA_MAP_XY_RESULT_CsvIo writer1 = null;
	
	private List<OUT_DBSCAN_RESULT_Bean> inList1  = null;
	private List<MURA_MAP_XY_RESULT_Bean> outList1 = null;
	
	private String filePath = null;

	private String jobId = null;

	public MURA_MAP_XY_RESULT_MacroJob(String jobId)
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
				reader1 = new OUT_DBSCAN_RESULT_CsvIo(this.filePath);
				writer1 = new MURA_MAP_XY_RESULT_CsvIo(this.filePath);
				
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
				 */
				if (Flag.TRUE) {
					Map<String, TempResult> map = new LinkedHashMap<String, TempResult>();
					
					for (OUT_DBSCAN_RESULT_Bean inBean1 : inList1) {
						TempResult temp = new TempResult();
						temp.setSiteId       (inBean1.getSiteId      ());
						temp.setProdGrpName  (inBean1.getProdGrpName ());
						temp.setProcId       (inBean1.getProcId      ());
						temp.setCellPosition (inBean1.getCellPosition());
						temp.setItemId       (inBean1.getItemId      ());
						temp.setItemName     (inBean1.getItemName    ());
						temp.setClusterId    (inBean1.getClusterId   ());
						
						TempResult ret = map.get(temp.getKey());
						if (ret == null) {
							map.put(temp.getKey(), temp);
							ret = map.get(temp.getKey());
						}
						
						double[] rect = {
							Double.parseDouble(inBean1.getXValue ()),	
							Double.parseDouble(inBean1.getYValue ()),	
							Double.parseDouble(inBean1.getX2Value()),	
							Double.parseDouble(inBean1.getY2Value()),	
						};
						
						ret.addList(new TempRect(rect));
					}
					
					int no = 0;
					for (Map.Entry<String, TempResult> entry : map.entrySet()) {
						@SuppressWarnings("unused")
						String key = entry.getKey();
						TempResult temp = entry.getValue();
						if ("0".equals(temp.getClusterId()))
							continue;

						no ++;
						temp.setClusterNo("" + no);
						temp.average();
						
						MURA_MAP_XY_RESULT_Bean outBean1 = new MURA_MAP_XY_RESULT_Bean();

						outBean1.setSiteId      (temp.getSiteId      ());
						outBean1.setProdGrpName (temp.getProdGrpName ());
						outBean1.setProcId      (temp.getProcId      ());
						outBean1.setCellPosition(temp.getCellPosition());
						outBean1.setItemId      (temp.getItemId      ());
						outBean1.setItemName    (temp.getItemName    ());
						outBean1.setAvgX1       (temp.getAvgX1       ());
						outBean1.setAvgY1       (temp.getAvgY1       ());
						outBean1.setAvgX2       (temp.getAvgX2       ());
						outBean1.setAvgY2       (temp.getAvgY2       ());
						outBean1.setClusterId   (temp.getClusterId   ());
						outBean1.setClusterNo   (temp.getClusterNo   ());
						outBean1.setWidth       (temp.getWidth       ());
						outBean1.setHeight      (temp.getHeight      ());

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
