package kiea.proj.sdc.anal.macro.sample.a11.preanal.macro.MURA_RAWDATA_ANAL2;

import java.util.List;

import kiea.kr.co.tain.base.Flag;
import kiea.kr.co.tain.util.DateTime;
import kiea.proj.sdc.anal.macro.impl.job.AbstractMacroJob;
import kiea.proj.sdc.anal.util.BasePath;
import kiea.proj.sdc.anal.util.FileUtil;
import kiea.proj.sdc.anal.util.log.v02.Logger;

@SuppressWarnings("unused")
public class MURA_RAWDATA_ANAL2_MacroJob extends AbstractMacroJob
{
	private MURA_RAWDATA_CsvIo reader1 = null;
	private MURA_RAWDATA_ANAL2_CsvIo writer1 = null;
	
	private List<MURA_RAWDATA_Bean> inList1  = null;
	private List<MURA_RAWDATA_ANAL2_Bean> outList1 = null;
	
	private String strFromDateTime    = null;
	private String strToDateTime      = null;
	private String strLine            = null;
	private String strAreaCode        = null;
	private String strUserGroupCode   = null;
	private String strDefectGroupCode = null;
	
	private String filePath = null;

	private String jobKeyPath = null;
	private String dsName = null;

	public MURA_RAWDATA_ANAL2_MacroJob(String jobKeyPath, String dsName)
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
				reader1 = new MURA_RAWDATA_CsvIo(this.filePath);
				writer1 = new MURA_RAWDATA_ANAL2_CsvIo(this.filePath);
				
				inList1  = reader1.getList();
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

					for (MURA_RAWDATA_Bean inBean1 : inList1) {
						
						MURA_RAWDATA_ANAL2_Bean outBean1 = new MURA_RAWDATA_ANAL2_Bean();
						
						int gateLine1 = (int) Math.round(Double.parseDouble(inBean1.getGateLine()));
						int gateLine2 = (int) Math.round(Double.parseDouble(inBean1.getGateLine2()));
						int dataLine1 = (int) Math.round(Double.parseDouble(inBean1.getDataLine()));
						int dataLine2 = (int) Math.round(Double.parseDouble(inBean1.getDataLine2()));
						
						double avgX = (Double.parseDouble(inBean1.getXValue()) + Double.parseDouble(inBean1.getX2Value())) / 2;
						double avgY = (Double.parseDouble(inBean1.getYValue()) + Double.parseDouble(inBean1.getY2Value())) / 2;
								
						outBean1.setProcId       (inBean1.getProcId       ());
						outBean1.setSiteId       (inBean1.getSiteId       ());
						outBean1.setCellId       (inBean1.getCellId       ());
						outBean1.setGlassId      (inBean1.getGlassId      ());
						outBean1.setProdGrpName  (inBean1.getProdGrpName  ());
						outBean1.setProdId       (inBean1.getProdId       ());
						outBean1.setOrgStepId    (inBean1.getOrgStepId    ());
						outBean1.setMeasEqpUnitId(inBean1.getMeasEqpUnitId());
						outBean1.setDcolTime     (inBean1.getDcolTime     ());
						outBean1.setItemId       (inBean1.getItemId       ());
						outBean1.setSubItemId    (inBean1.getSubItemId    ());
						outBean1.setDefectName   (inBean1.getDefectName   ());
						outBean1.setDataValue    (inBean1.getDataValue    ());
						outBean1.setGateLine     (inBean1.getGateLine     ());
						outBean1.setDataLine     (inBean1.getDataLine     ());
						outBean1.setGateLine2    (inBean1.getGateLine2    ());
						outBean1.setDataLine2    (inBean1.getDataLine2    ());
						outBean1.setProdType     (inBean1.getProdType     ());
						outBean1.setCellLocId    (inBean1.getCellLocId    ());
						outBean1.setWidth        (inBean1.getWidth        ());
						outBean1.setHeight       (inBean1.getHeight       ());
						outBean1.setXValue       (inBean1.getXValue       ());
						outBean1.setYValue       (inBean1.getYValue       ());
						outBean1.setX2Value      (inBean1.getX2Value      ());
						outBean1.setY2Value      (inBean1.getY2Value      ());
						outBean1.setDTime        (inBean1.getDcolTime().substring(0, 13));
						outBean1.setMuraData     (inBean1.getDataValue());
						outBean1.setGateLine_1   ("" + gateLine1);
						outBean1.setGateLine_2   ("" + gateLine2);
						outBean1.setDataLine_1   ("" + dataLine1);
						outBean1.setDataLine_2   ("" + dataLine2);
						outBean1.setAvgX         ("" + avgX);
						outBean1.setAvgY         ("" + avgY);
						
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
