package kiea.proj.sdc.anal.macro.sample.a11.img.macro.RP_MUR_TOTMURA_MAP_SUMM;

import java.util.List;

import kiea.kr.co.tain.base.Flag;
import kiea.kr.co.tain.util.DateTime;
import kiea.proj.sdc.anal.common.Parameter;
import kiea.proj.sdc.anal.macro.impl.job.AbstractMacroJob;
import kiea.proj.sdc.anal.util.BasePath;
import kiea.proj.sdc.anal.util.FileUtil;
import kiea.proj.sdc.anal.util.log.v02.Logger;

@SuppressWarnings("unused")
public class RP_MUR_TOTMURA_MAP_SUMM_MacroJob extends AbstractMacroJob
{
	private MURA_MAP_XY_4SF_RESULT_CsvIo reader1 = null;
	private RP_MUR_TOTMURA_MAP_SUMM_CsvIo writer1 = null;
	
	private List<MURA_MAP_XY_4SF_RESULT_Bean> inList1  = null;
	private List<RP_MUR_TOTMURA_MAP_SUMM_Bean> outList1 = null;
	
	private String strFromDateTime    = null;
	private String strToDateTime      = null;
	private String strLine            = null;
	private String strAreaCode        = null;
	private String strUserGroupCode   = null;
	private String strDefectGroupCode = null;
	
	private String filePath = null;

	private String jobKeyPath = null;
	private String dsName = null;

	public RP_MUR_TOTMURA_MAP_SUMM_MacroJob(String jobKeyPath, String dsName)
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
				reader1 = new MURA_MAP_XY_4SF_RESULT_CsvIo(this.filePath);
				writer1 = new RP_MUR_TOTMURA_MAP_SUMM_CsvIo(this.filePath);
				
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
				 */
				
				if (Flag.TRUE) {
					/*
					 * 해당자료만 추출한다.
					 */
					for (MURA_MAP_XY_4SF_RESULT_Bean inBean1 : inList1) {
						
						RP_MUR_TOTMURA_MAP_SUMM_Bean outBean1 = new RP_MUR_TOTMURA_MAP_SUMM_Bean();

						outBean1.setDataNum       (inBean1.getDataNum       ());
						outBean1.setSeq           (inBean1.getSeq           ());
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
						String strLine = Parameter.getInstance().getStrLine();
						if ("L5FAB".equals(strLine) || "L6FAB".equals(strLine)) {

							RP_MUR_TOTMURA_MAP_SUMM_Bean outBean1;

							outBean1 = new RP_MUR_TOTMURA_MAP_SUMM_Bean();
							outBean1.setXValue("0");
							outBean1.setYValue("0");
							outList1.add(outBean1);
							
							outBean1 = new RP_MUR_TOTMURA_MAP_SUMM_Bean();
							outBean1.setXValue("1300000");
							outBean1.setYValue("1100000");
							outList1.add(outBean1);

						} else {
							
							RP_MUR_TOTMURA_MAP_SUMM_Bean outBean1;

							outBean1 = new RP_MUR_TOTMURA_MAP_SUMM_Bean();
							outBean1.setXValue("0");
							outBean1.setYValue("0");
							outList1.add(outBean1);
							
							outBean1 = new RP_MUR_TOTMURA_MAP_SUMM_Bean();
							outBean1.setXValue("2500000");
							outBean1.setYValue("2200000");
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
