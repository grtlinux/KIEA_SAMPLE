package sdc.anal.mura.macro.A22.BUBBLE_CLUSTER_CELLLOC.v01;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import kiea.kr.co.tain.base.Flag;
import kiea.kr.co.tain.util.Print;
import kiea.proj.sdc.anal.macro.impl.job.AbstractMacroJob;
import kiea.proj.sdc.anal.util.BasePath;
import kiea.proj.sdc.anal.util.FileUtil;
import kiea.proj.sdc.anal.util.StrUtil;
import kiea.proj.sdc.anal.util.log.v02.Logger;

public class BUBBLE_CLUSTER_CELLLOC_MacroJob extends AbstractMacroJob
{
	private OUT_DBSCAN_RESULT_CsvIo reader1 = null;
	private BUBBLE_IDX_CsvIo reader2 = null;
	private BUBBLE_CLUSTER_CELLLOC_CsvIo writer1 = null;
	
	private List<OUT_DBSCAN_RESULT_Bean> inList1  = null;
	private List<BUBBLE_IDX_Bean> inList2  = null;
	private List<BUBBLE_CLUSTER_CELLLOC_Bean> outList1 = null;
	
	private String filePath = null;

	private String jobId = null;

	public BUBBLE_CLUSTER_CELLLOC_MacroJob(String jobId)
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
				reader2 = new BUBBLE_IDX_CsvIo(this.filePath);
				writer1 = new BUBBLE_CLUSTER_CELLLOC_CsvIo(this.filePath);
				
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
				
				if (Flag.TRUE) {
					/*
					 * 해당자료만 추출한다.
					 */
					Set<String> set = new HashSet<String>();
					Map<String, List<Double>> map = new HashMap<String, List<Double>>();
					
					for (OUT_DBSCAN_RESULT_Bean inBean1 : inList1) {
						/*
						 *  clusterid, cellposition, avgmura
						 */
						set.add(inBean1.getClusterId());
						
						String key = inBean1.getClusterId() + ":" + inBean1.getCellPosition();
						List<Double> list = map.get(key);
						if (list == null) {
							list = new ArrayList<Double>();
							map.put(key, list);
						}
						list.add(new Double(Double.parseDouble(inBean1.getMuraData())));
					}
					
					if (!Flag.TRUE) {
						/*
						 * 확인출력
						 */
						for (String clusterId : set) {
							/*
							 * clusterId는 regroup clustering 작업을 하면 사라질 수 있음.
							 */
							Print.println("[%s]", clusterId);
						}
						
						for (Map.Entry<String, List<Double>> entry : map.entrySet()) {
							String key = entry.getKey();
							List<Double> list = entry.getValue();
							
							Print.println("[%s] [%s]", key, list);
						}
					}
					
					/*
					 * 출력구성을 만든다.
					 */
					Map<String, Imsi> mapImsi = new HashMap<String, Imsi>();
					
					for (String clusterId : set) {
						for (BUBBLE_IDX_Bean inBean2 : inList2) {
							Imsi imsi = new Imsi();
							imsi.setClusterId(clusterId);
							imsi.setCellLocId(inBean2.getCellLocId());
							imsi.setPointX(inBean2.getPointX());
							imsi.setPointY(inBean2.getPointY());
							if (!Flag.TRUE) Print.println("%s", inBean2);
							
							String key = clusterId + ":" + inBean2.getCellLocId();
							List<Double> list = map.get(key);
							if (list != null) {
								double sum = 0;
								for (int i=0; i < list.size(); i++) {
									sum += list.get(i);
								}
								imsi.setAvgMura("" + (sum / list.size()));
							} else {
								imsi.setAvgMura("0");
							}
							
							mapImsi.put(key, imsi);
							if (!Flag.TRUE) Print.println("%s", imsi);
						}
					}
					
					if (!Flag.TRUE) {
						for (Map.Entry<String, Imsi> entry : mapImsi.entrySet()) {
							String key = entry.getKey();
							Imsi imsi = entry.getValue();
							Print.println("[%s] => %s", key, imsi);
						}
					}
					
					for (Imsi imsi : mapImsi.values()) {
						
						BUBBLE_CLUSTER_CELLLOC_Bean outBean1 = new BUBBLE_CLUSTER_CELLLOC_Bean();

						outBean1.setClusterId(imsi.getClusterId());
						outBean1.setCellLocId(imsi.getCellLocId());
						outBean1.setPointX   (imsi.getPointX   ());
						outBean1.setPointY   (imsi.getPointY   ());
						outBean1.setAvgMura  (imsi.getAvgMura  ());
						
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
