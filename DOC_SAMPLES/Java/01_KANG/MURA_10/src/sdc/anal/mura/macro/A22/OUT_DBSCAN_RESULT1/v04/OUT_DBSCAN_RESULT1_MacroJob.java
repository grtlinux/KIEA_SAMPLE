package sdc.anal.mura.macro.A22.OUT_DBSCAN_RESULT1.v04;

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

public class OUT_DBSCAN_RESULT1_MacroJob extends AbstractMacroJob
{
	private OUT_DBSCAN_RESULT_CsvIo reader1 = null;
	private MURA_IMAGE_CsvIo reader2 = null;
	private OUT_DBSCAN_RESULT1_CsvIo writer1 = null;
	
	private List<OUT_DBSCAN_RESULT_Bean> inList1  = null;
	private List<MURA_IMAGE_Bean> inList2  = null;
	private List<OUT_DBSCAN_RESULT1_Bean> outList1 = null;
	
	private String filePath = null;

	private String jobId = null;

	public OUT_DBSCAN_RESULT1_MacroJob(String jobId)
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
			OUT_DBSCAN_RESULT1_Main.runMSec = System.nanoTime();
		}

		if (Flag.TRUE) {
			/*
			 * 기존 자료 삭제
			 */
			AnalCsvBean bean = new AnalCsvBean();

			bean.setType(AnalCsvType.DELETE);
			bean.setTable("ANAL_CSV");
			bean.setWhere(String.format("JOB_ID = %s AND PROG_NM = %s", StrUtil.quote(jobId), StrUtil.quote(OUT_DBSCAN_RESULT1_Main.dsName)));

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
					{ "CMD_CODE   ", StrUtil.quote(OUT_DBSCAN_RESULT1_Main.dsName.substring(0, 3)) },
					{ "PROG_NM    ", StrUtil.quote(OUT_DBSCAN_RESULT1_Main.dsName) },
					{ "CSV_NM     ", StrUtil.quote(OUT_DBSCAN_RESULT1_Main.dsName + ".csv") },
					{ "MAIN_CLASS ", StrUtil.quote(this.getClass().getName()) },
					{ "S_TIME     ", "SYSDATE" },
					{ "CSV_STATUS ", StrUtil.quote("START") },
			});
			if (Flag.TRUE) Logger.info("[%s]", bean.getQuery());

			bean.sendToOracle();
		}

		if (Flag.TRUE) {
			try {
				reader1 = new OUT_DBSCAN_RESULT_CsvIo(this.filePath);
				reader2 = new MURA_IMAGE_CsvIo(this.filePath);
				writer1 = new OUT_DBSCAN_RESULT1_CsvIo(this.filePath);
				
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
				Map<String, MURA_IMAGE_Bean> mapMura = new LinkedHashMap<String, MURA_IMAGE_Bean>();
				
				/*
				 * MURA_DESC
				 */
				if (Flag.TRUE) {
					for (MURA_IMAGE_Bean inBean2 : inList2) {
						mapMura.put(inBean2.getGlassCellId(), inBean2);
					}
				}

				/*
				 * Job
				 */
				
				if (Flag.TRUE) {
					/*
					 * 해당자료만 추출한다.
					 */
					String imagePath;
					String imagePathRed;
					String imagePathGreen;
					String imagePathBlue;
					int xValue;
					int yValue;
					int x2Value;
					int y2Value;
					int width;
					int height;

					for (OUT_DBSCAN_RESULT_Bean inBean1 : inList1) {
						
						MURA_IMAGE_Bean inBean2 = mapMura.get(inBean1.getCellId());
						if (inBean2 != null) {
							imagePath      = inBean2.getImagePath     ();
							imagePathRed   = inBean2.getImagePathRed  ();
							imagePathGreen = inBean2.getImagePathGreen();
							imagePathBlue  = inBean2.getImagePathBlue ();
						} else {
							imagePath      = "";
							imagePathRed   = "";
							imagePathGreen = "";
							imagePathBlue  = "";
						}
						
						xValue  = (int) Math.min(Double.parseDouble(inBean1.getXValue()), Double.parseDouble(inBean1.getX2Value()));
						yValue  = (int) Math.min(Double.parseDouble(inBean1.getYValue()), Double.parseDouble(inBean1.getY2Value()));
						x2Value = (int) Math.max(Double.parseDouble(inBean1.getXValue()), Double.parseDouble(inBean1.getX2Value()));
						y2Value = (int) Math.max(Double.parseDouble(inBean1.getYValue()), Double.parseDouble(inBean1.getY2Value()));
						width   = x2Value - xValue;
						height  = y2Value - yValue;
						
						OUT_DBSCAN_RESULT1_Bean outBean1 = new OUT_DBSCAN_RESULT1_Bean();

						outBean1.setCellId        (inBean1.getCellId        ());
						outBean1.setGlassId       (inBean1.getGlassId       ());
						outBean1.setSiteId        (inBean1.getSiteId        ());
						outBean1.setProdGrpName   (inBean1.getProdGrpName   ());
						outBean1.setProcId        (inBean1.getProcId        ());
						outBean1.setItemId        (inBean1.getItemId        ());
						outBean1.setSubItemId     (inBean1.getSubItemId     ());
						outBean1.setDefectName    (inBean1.getDefectName    ());
						outBean1.setItemName      (inBean1.getItemName      ());
						outBean1.setMeasEqpUnitId (inBean1.getMeasEqpUnitId ());
						outBean1.setMuraData      (inBean1.getMuraData      ());
						outBean1.setXValue        ("" + xValue );
						outBean1.setYValue        ("" + yValue );
						outBean1.setX2Value       ("" + x2Value);
						outBean1.setY2Value       ("" + y2Value);
						outBean1.setAvgX          (inBean1.getAvgX          ());
						outBean1.setAvgY          (inBean1.getAvgY          ());
						outBean1.setGateLine_1    (inBean1.getGateLine_1    ());
						outBean1.setGateLine_2    (inBean1.getGateLine_2    ());
						outBean1.setDataLine_1    (inBean1.getDataLine_1    ());
						outBean1.setDataLine_2    (inBean1.getDataLine_2    ());
						outBean1.setTarget        (inBean1.getTarget        ());
						outBean1.setCellPosition  (inBean1.getCellPosition  ());
						outBean1.setDataNum       (inBean1.getDataNum       ());
						outBean1.setClusterId     (inBean1.getClusterId     ());
						outBean1.setImagePath     (imagePath     );
						outBean1.setImagePathRed  (imagePathRed  );
						outBean1.setImagePathGreen(imagePathGreen);
						outBean1.setImagePathBlue (imagePathBlue );
						outBean1.setWidth         ("" + width    );
						outBean1.setHeight        ("" + height   );

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
				
				OUT_DBSCAN_RESULT1_Main.cntWList = outList1.size();

				writer1.writeList();
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		if (Flag.TRUE) {
			/*
			 * 시간기록 끝
			 */
			OUT_DBSCAN_RESULT1_Main.runMSec = (System.nanoTime() - OUT_DBSCAN_RESULT1_Main.runMSec) / 1000 / 1000;
			
			if (Flag.TRUE) Logger.info("##### took time : %d ms", OUT_DBSCAN_RESULT1_Main.runMSec);
		}

		if (Flag.TRUE) {
			/*
			 * UPDATE
			 */
			AnalCsvBean bean = new AnalCsvBean();
			
			bean.setType(AnalCsvType.UPDATE);
			bean.setTable("ANAL_CSV");
			bean.setFields(new String[][] {
					{ "LIST_CNT   ", "" + OUT_DBSCAN_RESULT1_Main.cntWList },
					{ "E_TIME     ", "SYSDATE" },
					{ "R_MSEC     ", "" + OUT_DBSCAN_RESULT1_Main.runMSec },
					{ "CSV_STATUS ", StrUtil.quote("OK") },   // OK, ERROR, SKIP, ETC...
					{ "LOG_MESSAGE", StrUtil.quote("COMPLETE") },  // COMPLETE, EXCEPTION
			});
			bean.setWhere(String.format("JOB_ID = %s AND PROG_NM = %s", StrUtil.quote(jobId), StrUtil.quote(OUT_DBSCAN_RESULT1_Main.dsName)));
			
			if (Flag.TRUE) Logger.info("[%s]", bean.getQuery());
			
			bean.sendToOracle();
		}
	}
}
