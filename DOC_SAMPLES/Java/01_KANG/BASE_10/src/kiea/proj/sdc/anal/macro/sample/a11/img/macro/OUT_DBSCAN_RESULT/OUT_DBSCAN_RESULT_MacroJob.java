package kiea.proj.sdc.anal.macro.sample.a11.img.macro.OUT_DBSCAN_RESULT;

import java.util.List;

import kiea.kr.co.tain.base.Flag;
import kiea.kr.co.tain.util.DateTime;
import kiea.proj.sdc.anal.macro.impl.job.AbstractMacroJob;
import kiea.proj.sdc.anal.util.BasePath;
import kiea.proj.sdc.anal.util.FileUtil;
import kiea.proj.sdc.anal.util.log.v02.Logger;

import org.apache.commons.math3.stat.descriptive.DescriptiveStatistics;

@SuppressWarnings("unused")
public class OUT_DBSCAN_RESULT_MacroJob extends AbstractMacroJob
{
	private BAD_GLASS_NO_CsvIo reader1 = null;
	private FINAL_CLUSTER_CsvIo reader2 = null;
	private OUT_DBSCAN_RESULT_CsvIo writer1 = null;
	
	private List<BAD_GLASS_NO_Bean> inList1  = null;
	private List<FINAL_CLUSTER_Bean> inList2  = null;
	private List<OUT_DBSCAN_RESULT_Bean> outList1 = null;
	
	private String strFromDateTime    = null;
	private String strToDateTime      = null;
	private String strLine            = null;
	private String strAreaCode        = null;
	private String strUserGroupCode   = null;
	private String strDefectGroupCode = null;
	
	private String filePath = null;

	private String jobKeyPath = null;
	private String dsName = null;

	public OUT_DBSCAN_RESULT_MacroJob(String jobKeyPath, String dsName)
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
				reader1 = new BAD_GLASS_NO_CsvIo(this.filePath);
				reader2 = new FINAL_CLUSTER_CsvIo(this.filePath);
				writer1 = new OUT_DBSCAN_RESULT_CsvIo(this.filePath);
				
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
				 */
				
				if (Flag.TRUE) {
					/*
					 * 해당자료만 추출한다.
					 */
					for (BAD_GLASS_NO_Bean inBean1 : inList1) {
						
						FINAL_CLUSTER_Bean inBean2 = reader2.get(inBean1.getDataNum());
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
				
				writer1.writeList();
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
