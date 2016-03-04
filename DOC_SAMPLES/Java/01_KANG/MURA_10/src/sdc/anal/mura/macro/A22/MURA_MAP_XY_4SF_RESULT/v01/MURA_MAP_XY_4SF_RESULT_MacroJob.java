package sdc.anal.mura.macro.A22.MURA_MAP_XY_4SF_RESULT.v01;

import java.util.List;

import kiea.kr.co.tain.base.Flag;
import kiea.proj.sdc.anal.macro.impl.job.AbstractMacroJob;
import kiea.proj.sdc.anal.util.BasePath;
import kiea.proj.sdc.anal.util.FileUtil;
import kiea.proj.sdc.anal.util.StrUtil;
import kiea.proj.sdc.anal.util.log.v02.Logger;

public class MURA_MAP_XY_4SF_RESULT_MacroJob extends AbstractMacroJob
{
	private OUT_DBSCAN_RESULT1_CsvIo reader1 = null;
	private MURA_MAP_XY_4SF_RESULT_CsvIo writer1 = null;
	
	private List<OUT_DBSCAN_RESULT1_Bean> inList1  = null;
	private List<MURA_MAP_XY_4SF_RESULT_Bean> outList1 = null;
	
	private String filePath = null;

	private String jobId = null;

	public MURA_MAP_XY_4SF_RESULT_MacroJob(String jobId)
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
				reader1 = new OUT_DBSCAN_RESULT1_CsvIo(this.filePath);
				writer1 = new MURA_MAP_XY_4SF_RESULT_CsvIo(this.filePath);
				
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
					/*
					 * 해당자료만 추출한다.
					 */
					for (OUT_DBSCAN_RESULT1_Bean inBean1 : inList1) {
						
						if (Flag.TRUE) {
							MURA_MAP_XY_4SF_RESULT_Bean outBean1 = new MURA_MAP_XY_4SF_RESULT_Bean();

							outBean1.setDataNum       (inBean1.getDataNum       ());
							outBean1.setSeq           ("1");
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
							outBean1.setGateLine_1    (inBean1.getGateLine_1    ());
							outBean1.setGateLine_2    (inBean1.getGateLine_2    ());
							outBean1.setDataLine_1    (inBean1.getDataLine_1    ());
							outBean1.setDataLine_2    (inBean1.getDataLine_2    ());
							outBean1.setXValue        (inBean1.getXValue        ());
							outBean1.setYValue        (inBean1.getYValue        ());
							outBean1.setCellPosition  (inBean1.getCellPosition  ());
							outBean1.setClusterId     (inBean1.getClusterId     ());
							outBean1.setImagePath     (inBean1.getImagePath     ());
							outBean1.setImagePathRed  (inBean1.getImagePathRed  ());
							outBean1.setImagePathBlue (inBean1.getImagePathBlue ());
							outBean1.setImagePathGreen(inBean1.getImagePathGreen());

							outList1.add(outBean1);
						}

						if (Flag.TRUE) {
							MURA_MAP_XY_4SF_RESULT_Bean outBean1 = new MURA_MAP_XY_4SF_RESULT_Bean();

							outBean1.setDataNum       (inBean1.getDataNum       ());
							outBean1.setSeq           ("2");
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
							outBean1.setGateLine_1    (inBean1.getGateLine_1    ());
							outBean1.setGateLine_2    (inBean1.getGateLine_2    ());
							outBean1.setDataLine_1    (inBean1.getDataLine_1    ());
							outBean1.setDataLine_2    (inBean1.getDataLine_2    ());
							outBean1.setXValue        ("" + (Double.parseDouble(inBean1.getXValue()) + Double.parseDouble(inBean1.getWidth ())));
							outBean1.setYValue        (inBean1.getYValue        ());
							outBean1.setCellPosition  (inBean1.getCellPosition  ());
							outBean1.setClusterId     (inBean1.getClusterId     ());
							outBean1.setImagePath     ("");
							outBean1.setImagePathRed  ("");
							outBean1.setImagePathBlue ("");
							outBean1.setImagePathGreen("");

							outList1.add(outBean1);
						}

						if (Flag.TRUE) {
							MURA_MAP_XY_4SF_RESULT_Bean outBean1 = new MURA_MAP_XY_4SF_RESULT_Bean();

							outBean1.setDataNum       (inBean1.getDataNum       ());
							outBean1.setSeq           ("3");
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
							outBean1.setGateLine_1    (inBean1.getGateLine_1    ());
							outBean1.setGateLine_2    (inBean1.getGateLine_2    ());
							outBean1.setDataLine_1    (inBean1.getDataLine_1    ());
							outBean1.setDataLine_2    (inBean1.getDataLine_2    ());
							outBean1.setXValue        ("" + (Double.parseDouble(inBean1.getXValue()) + Double.parseDouble(inBean1.getWidth ())));
							outBean1.setYValue        ("" + (Double.parseDouble(inBean1.getYValue()) + Double.parseDouble(inBean1.getHeight())));
							outBean1.setCellPosition  (inBean1.getCellPosition  ());
							outBean1.setClusterId     (inBean1.getClusterId     ());
							outBean1.setImagePath     ("");
							outBean1.setImagePathRed  ("");
							outBean1.setImagePathBlue ("");
							outBean1.setImagePathGreen("");

							outList1.add(outBean1);
						}

						if (Flag.TRUE) {
							MURA_MAP_XY_4SF_RESULT_Bean outBean1 = new MURA_MAP_XY_4SF_RESULT_Bean();

							outBean1.setDataNum       (inBean1.getDataNum       ());
							outBean1.setSeq           ("4");
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
							outBean1.setGateLine_1    (inBean1.getGateLine_1    ());
							outBean1.setGateLine_2    (inBean1.getGateLine_2    ());
							outBean1.setDataLine_1    (inBean1.getDataLine_1    ());
							outBean1.setDataLine_2    (inBean1.getDataLine_2    ());
							outBean1.setXValue        (inBean1.getXValue        ());
							outBean1.setYValue        ("" + (Double.parseDouble(inBean1.getYValue()) + Double.parseDouble(inBean1.getHeight())));
							outBean1.setCellPosition  (inBean1.getCellPosition  ());
							outBean1.setClusterId     (inBean1.getClusterId     ());
							outBean1.setImagePath     ("");
							outBean1.setImagePathRed  ("");
							outBean1.setImagePathBlue ("");
							outBean1.setImagePathGreen("");

							outList1.add(outBean1);
						}

						if (Flag.TRUE) {
							MURA_MAP_XY_4SF_RESULT_Bean outBean1 = new MURA_MAP_XY_4SF_RESULT_Bean();

							outBean1.setDataNum       (inBean1.getDataNum       ());
							outBean1.setSeq           ("5");
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
							outBean1.setGateLine_1    (inBean1.getGateLine_1    ());
							outBean1.setGateLine_2    (inBean1.getGateLine_2    ());
							outBean1.setDataLine_1    (inBean1.getDataLine_1    ());
							outBean1.setDataLine_2    (inBean1.getDataLine_2    ());
							outBean1.setXValue        (inBean1.getXValue        ());
							outBean1.setYValue        (inBean1.getYValue        ());
							outBean1.setCellPosition  (inBean1.getCellPosition  ());
							outBean1.setClusterId     (inBean1.getClusterId     ());
							outBean1.setImagePath     ("");
							outBean1.setImagePathRed  ("");
							outBean1.setImagePathBlue ("");
							outBean1.setImagePathGreen("");

							outList1.add(outBean1);
						}

						if (Flag.TRUE) {
							MURA_MAP_XY_4SF_RESULT_Bean outBean1 = new MURA_MAP_XY_4SF_RESULT_Bean();

							outBean1.setDataNum       (inBean1.getDataNum       ());
							outBean1.setSeq           ("6");
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
							outBean1.setGateLine_1    (inBean1.getGateLine_1    ());
							outBean1.setGateLine_2    (inBean1.getGateLine_2    ());
							outBean1.setDataLine_1    (inBean1.getDataLine_1    ());
							outBean1.setDataLine_2    (inBean1.getDataLine_2    ());
							outBean1.setXValue        ("");
							outBean1.setYValue        ("");
							outBean1.setCellPosition  (inBean1.getCellPosition  ());
							outBean1.setClusterId     (inBean1.getClusterId     ());
							outBean1.setImagePath     ("");
							outBean1.setImagePathRed  ("");
							outBean1.setImagePathBlue ("");
							outBean1.setImagePathGreen("");

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
