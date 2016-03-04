package sdc.anal.mura.macro.A21.OUT_DBSCAN.v04;

import java.util.List;

import kiea.kr.co.tain.base.Flag;
import kiea.kr.co.tain.util.Print;
import kiea.proj.sdc.anal.macro.impl.job.AbstractMacroJob;
import kiea.proj.sdc.anal.tools.AnalCsv.v04.AnalCsvBean;
import kiea.proj.sdc.anal.tools.AnalCsv.v04.AnalCsvType;
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
			/*
			 * 시간기록 시작
			 */
			OUT_DBSCAN_Main.runMSec = System.nanoTime();
		}

		if (Flag.TRUE) {
			/*
			 * 기존 자료 삭제
			 */
			AnalCsvBean bean = new AnalCsvBean();

			bean.setType(AnalCsvType.DELETE);
			bean.setTable("ANAL_CSV");
			bean.setWhere(String.format("JOB_ID = %s AND PROG_NM = %s", StrUtil.quote(jobId), StrUtil.quote(OUT_DBSCAN_Main.dsName)));

			bean.sendToOracle();

			if (Flag.TRUE) Logger.info("[%s]", bean.getQuery());
		}

		if (Flag.TRUE) {
			/*
			 * INSERT
			 */
			AnalCsvBean bean = new AnalCsvBean();

			bean.setType(AnalCsvType.INSERT);
			bean.setTable("ANAL_CSV");
			bean.setFields(new String[][] {
					{ "JOB_ID     ", StrUtil.quote(jobId) },
					{ "CMD_CODE   ", StrUtil.quote(OUT_DBSCAN_Main.dsName.substring(0, 3)) },
					{ "PROG_NM    ", StrUtil.quote(OUT_DBSCAN_Main.dsName) },
					{ "CSV_NM     ", StrUtil.quote(OUT_DBSCAN_Main.dsName + ".csv") },
					{ "MAIN_CLASS ", StrUtil.quote(this.getClass().getName()) },
					{ "S_TIME     ", "SYSDATE" },
					{ "CSV_STATUS ", StrUtil.quote("START") },
			});
			if (Flag.TRUE) Logger.info("[%s]", bean.getQuery());

			bean.sendToOracle();
		}

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
				
				OUT_DBSCAN_Main.cntWList = outList1.size();

				writer1.writeList();
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		if (Flag.TRUE) {
			/*
			 * 시간기록 끝
			 */
			OUT_DBSCAN_Main.runMSec = (System.nanoTime() - OUT_DBSCAN_Main.runMSec) / 1000 / 1000;
			
			if (Flag.TRUE) Logger.info("##### took time : %d ms", OUT_DBSCAN_Main.runMSec);
		}

		if (Flag.TRUE) {
			/*
			 * UPDATE
			 */
			AnalCsvBean bean = new AnalCsvBean();
			
			bean.setType(AnalCsvType.UPDATE);
			bean.setTable("ANAL_CSV");
			bean.setFields(new String[][] {
					{ "LIST_CNT   ", "" + OUT_DBSCAN_Main.cntWList },
					{ "E_TIME     ", "SYSDATE" },
					{ "R_MSEC     ", "" + OUT_DBSCAN_Main.runMSec },
					{ "CSV_STATUS ", StrUtil.quote("OK") },   // OK, ERROR, SKIP, ETC...
					{ "LOG_MESSAGE", StrUtil.quote("COMPLETE") },  // COMPLETE, EXCEPTION
			});
			bean.setWhere(String.format("JOB_ID = %s AND PROG_NM = %s", StrUtil.quote(jobId), StrUtil.quote(OUT_DBSCAN_Main.dsName)));
			
			if (Flag.TRUE) Logger.info("[%s]", bean.getQuery());
			
			bean.sendToOracle();
		}
	}
}
