package sdc.anal.mura.macro.A21.OUT_DBSCAN.v01;

import java.util.List;

import kiea.kr.co.tain.base.Flag;
import kiea.kr.co.tain.util.Print;
import kiea.proj.sdc.anal.macro.impl.job.AbstractMacroJob;
import kiea.proj.sdc.anal.util.BasePath;
import kiea.proj.sdc.anal.util.FileUtil;
import kiea.proj.sdc.anal.util.StrUtil;
import kiea.proj.sdc.anal.util.log.v02.Logger;

import org.apache.commons.math3.ml.clustering.DoublePoint;

public class OUT_DBSCAN_MacroJob extends AbstractMacroJob
{
	private IN_DBSCAN_CsvIo reader1 = null;
	private OUT_DBSCAN_CsvIo writer1 = null;
	
	private List<IN_DBSCAN_Bean> inList1  = null;
	private List<OUT_DBSCAN_Bean> outList1 = null;
	
	private String filePath = null;

	private String jobId = null;

	public OUT_DBSCAN_MacroJob(String jobId)
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
				reader1 = new IN_DBSCAN_CsvIo(this.filePath);
				writer1 = new OUT_DBSCAN_CsvIo(this.filePath);
				
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
				
				//DBSCANObject dbscan = new DBSCANObject(20000, 15);   // eps, minPts 천안 
				DBSCANObject dbscan = new DBSCANObject(50000, 15);   // eps, minPts 아산

				if (Flag.TRUE) {
					/*
					 * DBSCAN clustering
					 */
					
					for (IN_DBSCAN_Bean inBean1 : inList1) {
						dbscan.addPoint(new DoublePoint( new double[] { Double.parseDouble(inBean1.getAvgX()), Double.parseDouble(inBean1.getAvgY()) }));
					}
					
					dbscan.clustering();
					
					if (!Flag.TRUE) {
						List<DBSCANItem> list = dbscan.getList();
						
						if (Flag.TRUE) {
							for (DBSCANItem item : list) {
								System.out.println(item);
							}
						}
						
						if (!Flag.TRUE) {
							for (DBSCANItem item : list) {
								Print.println("(%s, %s) %d", new Double(item.getPointX()).toString(), new Double(item.getPointY()).toString(), item.getClusterId());
							}
						}
					}
				}
				
				if (Flag.TRUE) {
					/*
					 * 해당자료만 추출한다.
					 */
					List<DBSCANItem> list = dbscan.getList();
					int clusterId = -1;

					for (IN_DBSCAN_Bean inBean1 : inList1) {
						
						for (DBSCANItem item : list) {
							if (item.getClusterId() < 0)
								continue;
							
							if (item.getPointX() == Double.parseDouble(inBean1.getAvgX()) 
									&& item.getPointY() == Double.parseDouble(inBean1.getAvgY())) {
								clusterId = item.getClusterId();
								item.setClusterId(-1);
								break;
							}
						}
						
						OUT_DBSCAN_Bean outBean1 = new OUT_DBSCAN_Bean();

						outBean1.setDataNum  (inBean1.getDataNum  ());
						outBean1.setAvgX     (inBean1.getAvgX     ());
						outBean1.setAvgY     (inBean1.getAvgY     ());
						outBean1.setClusterId("" + clusterId);

						outList1.add(outBean1);
					}
					
					if (!Flag.TRUE) {
						for (DBSCANItem item : list) {
							System.out.println(item);
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
