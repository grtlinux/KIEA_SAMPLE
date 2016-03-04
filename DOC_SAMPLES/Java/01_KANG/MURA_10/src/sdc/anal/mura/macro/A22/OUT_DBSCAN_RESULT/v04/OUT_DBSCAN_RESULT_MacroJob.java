package sdc.anal.mura.macro.A22.OUT_DBSCAN_RESULT.v04;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import kiea.kr.co.tain.base.Flag;
import kiea.proj.sdc.anal.macro.impl.job.AbstractMacroJob;
import kiea.proj.sdc.anal.tools.AnalCsv.v04.AnalCsvBean;
import kiea.proj.sdc.anal.tools.AnalCsv.v04.AnalCsvType;
import kiea.proj.sdc.anal.util.BasePath;
import kiea.proj.sdc.anal.util.FileUtil;
import kiea.proj.sdc.anal.util.StrUtil;
import kiea.proj.sdc.anal.util.log.v02.Logger;

import org.apache.commons.math3.stat.descriptive.DescriptiveStatistics;

public class OUT_DBSCAN_RESULT_MacroJob extends AbstractMacroJob
{
	private BAD_GLASS_NO_CsvIo reader1 = null;
	private FINAL_CLUSTER_CsvIo reader2 = null;
	private OUT_DBSCAN_RESULT_CsvIo writer1 = null;
	
	private List<BAD_GLASS_NO_Bean> inList1  = null;
	private List<FINAL_CLUSTER_Bean> inList2  = null;
	private List<OUT_DBSCAN_RESULT_Bean> outList1 = null;
	
	private String filePath = null;

	private String jobId = null;

	public OUT_DBSCAN_RESULT_MacroJob(String jobId)
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
			OUT_DBSCAN_RESULT_Main.runMSec = System.nanoTime();
		}

		if (Flag.TRUE) {
			/*
			 * 기존 자료 삭제
			 */
			AnalCsvBean bean = new AnalCsvBean();

			bean.setType(AnalCsvType.DELETE);
			bean.setTable("ANAL_CSV");
			bean.setWhere(String.format("JOB_ID = %s AND PROG_NM = %s", StrUtil.quote(jobId), StrUtil.quote(OUT_DBSCAN_RESULT_Main.dsName)));

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
					{ "CMD_CODE   ", StrUtil.quote(OUT_DBSCAN_RESULT_Main.dsName.substring(0, 3)) },
					{ "PROG_NM    ", StrUtil.quote(OUT_DBSCAN_RESULT_Main.dsName) },
					{ "CSV_NM     ", StrUtil.quote(OUT_DBSCAN_RESULT_Main.dsName + ".csv") },
					{ "MAIN_CLASS ", StrUtil.quote(this.getClass().getName()) },
					{ "S_TIME     ", "SYSDATE" },
					{ "CSV_STATUS ", StrUtil.quote("START") },
			});
			if (Flag.TRUE) Logger.info("[%s]", bean.getQuery());

			bean.sendToOracle();
		}

		if (Flag.TRUE) {
			try {
				reader1 = new BAD_GLASS_NO_CsvIo(this.filePath);
				reader2 = new FINAL_CLUSTER_CsvIo(this.filePath);
				writer1 = new OUT_DBSCAN_RESULT_CsvIo(this.filePath);
				
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
				Map<String, FINAL_CLUSTER_Bean> mapCluster = new LinkedHashMap<String, FINAL_CLUSTER_Bean>();
				
				/*
				 * MURA_DESC
				 */
				if (Flag.TRUE) {
					for (FINAL_CLUSTER_Bean inBean2 : inList2) {
						mapCluster.put(inBean2.getDataNum(), inBean2);
					}
				}

				/*
				 * Job
				 */
				
				if (Flag.TRUE) {
					/*
					 * 해당자료만 추출한다.
					 */
					for (BAD_GLASS_NO_Bean inBean1 : inList1) {
						
						FINAL_CLUSTER_Bean inBean2 = mapCluster.get(inBean1.getDataNum());
						if (inBean2 == null)
							continue;
						
						OUT_DBSCAN_RESULT_Bean outBean1 = new OUT_DBSCAN_RESULT_Bean();

						outBean1.setDataNum      (inBean1.getDataNum      ());
						outBean1.setCellId       (inBean1.getCellId       ());
						outBean1.setGlassId      (inBean1.getGlassId      ());
						outBean1.setSiteId       (inBean1.getSiteId       ());
						outBean1.setProdGrpName  (inBean1.getProdGrpName  ());
						outBean1.setProcId       (inBean1.getProcId       ());
						outBean1.setItemId       (inBean1.getItemId       ());
						outBean1.setSubItemId    (inBean1.getSubItemId    ());
						outBean1.setDefectName   (inBean1.getDefectName   ());
						outBean1.setItemName     (inBean1.getItemName     ());
						outBean1.setMeasEqpUnitId(inBean1.getMeasEqpUnitId());
						outBean1.setMuraData     (inBean1.getMuraData     ());
						outBean1.setXValue       (inBean1.getXValue       ());
						outBean1.setYValue       (inBean1.getYValue       ());
						outBean1.setX2Value      (inBean1.getX2Value      ());
						outBean1.setY2Value      (inBean1.getY2Value      ());
						outBean1.setAvgX         (inBean1.getAvgX         ());
						outBean1.setAvgY         (inBean1.getAvgY         ());
						outBean1.setGateLine_1   (inBean1.getGateLine_1   ());
						outBean1.setGateLine_2   (inBean1.getGateLine_2   ());
						outBean1.setDataLine_1   (inBean1.getDataLine_1   ());
						outBean1.setDataLine_2   (inBean1.getDataLine_2   ());
						outBean1.setTarget       (inBean1.getTarget       ());
						outBean1.setCellPosition (inBean1.getCellPosition ());
						outBean1.setClusterId    (inBean2.getClusterId    ());
						outBean1.setMuraGrade    ("");
						outBean1.setToOrder      ("");

						outList1.add(outBean1);
					}
				}
				
				if (Flag.TRUE) {
					/*
					 * MURA_DATA의 min, max 4분위 값을 구한다.
					 * 그리고 outBean1.setMuraGrade, setToOrder에 적용한다.
					 */
					double[] inMuraData = new double[outList1.size()];
					for (int i=0; i < inMuraData.length; i++) {
						inMuraData[i] = Double.parseDouble(outList1.get(i).getMuraData());
					}
					
					DescriptiveStatistics stats = new DescriptiveStatistics(inMuraData);
					
					double min   = stats.getMin();
					double q1    = stats.getPercentile(25);
					double mean  = stats.getMean();
					double q3    = stats.getPercentile(75);
					double max   = stats.getMax();
					
					for (OUT_DBSCAN_RESULT_Bean outBean1 : outList1) {
						double muraData = Double.parseDouble(outBean1.getMuraData());
						
						if (min <= muraData && muraData < q1) {
							outBean1.setMuraGrade(String.format("%f ~ %f", min, q1));
							outBean1.setToOrder("1");
						} else if (q1 <= muraData && muraData < mean) {
							outBean1.setMuraGrade(String.format("%f ~ %f", q1, mean));
							outBean1.setToOrder("2");
						} else if (mean <= muraData && muraData < q3) {
							outBean1.setMuraGrade(String.format("%f ~ %f", mean, q3));
							outBean1.setToOrder("3");
						} else if (q3 <= muraData && muraData <= max) {
							outBean1.setMuraGrade(String.format("%f 이상", q3));
							outBean1.setToOrder("4");
						} else {
							throw new IllegalArgumentException("ERROR : out of bound excepcion");
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
				
				OUT_DBSCAN_RESULT_Main.cntWList = outList1.size();

				writer1.writeList();
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		if (Flag.TRUE) {
			/*
			 * 시간기록 끝
			 */
			OUT_DBSCAN_RESULT_Main.runMSec = (System.nanoTime() - OUT_DBSCAN_RESULT_Main.runMSec) / 1000 / 1000;
			
			if (Flag.TRUE) Logger.info("##### took time : %d ms", OUT_DBSCAN_RESULT_Main.runMSec);
		}

		if (Flag.TRUE) {
			/*
			 * UPDATE
			 */
			AnalCsvBean bean = new AnalCsvBean();
			
			bean.setType(AnalCsvType.UPDATE);
			bean.setTable("ANAL_CSV");
			bean.setFields(new String[][] {
					{ "LIST_CNT   ", "" + OUT_DBSCAN_RESULT_Main.cntWList },
					{ "E_TIME     ", "SYSDATE" },
					{ "R_MSEC     ", "" + OUT_DBSCAN_RESULT_Main.runMSec },
					{ "CSV_STATUS ", StrUtil.quote("OK") },   // OK, ERROR, SKIP, ETC...
					{ "LOG_MESSAGE", StrUtil.quote("COMPLETE") },  // COMPLETE, EXCEPTION
			});
			bean.setWhere(String.format("JOB_ID = %s AND PROG_NM = %s", StrUtil.quote(jobId), StrUtil.quote(OUT_DBSCAN_RESULT_Main.dsName)));
			
			if (Flag.TRUE) Logger.info("[%s]", bean.getQuery());
			
			bean.sendToOracle();
		}
	}
}
