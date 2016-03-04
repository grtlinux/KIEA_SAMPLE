package kiea.proj.sdc.anal.macro.sample.a11.img.macro.RP_MUR_AVGMUR_MAP_SUMM;

import java.util.List;

import kiea.kr.co.tain.base.Flag;
import kiea.kr.co.tain.util.DateTime;
import kiea.proj.sdc.anal.common.Parameter;
import kiea.proj.sdc.anal.macro.impl.job.AbstractMacroJob;
import kiea.proj.sdc.anal.util.BasePath;
import kiea.proj.sdc.anal.util.FileUtil;
import kiea.proj.sdc.anal.util.log.v02.Logger;

@SuppressWarnings("unused")
public class RP_MUR_AVGMUR_MAP_SUMM_MacroJob extends AbstractMacroJob
{
	private AVG_MURA_MAP_XY_4SF_RESULT_CsvIo reader1 = null;
	private RP_MUR_AVGMUR_MAP_SUMM_CsvIo writer1 = null;
	
	private List<AVG_MURA_MAP_XY_4SF_RESULT_Bean> inList1  = null;
	private List<RP_MUR_AVGMUR_MAP_SUMM_Bean> outList1 = null;
	
	private String strFromDateTime    = null;
	private String strToDateTime      = null;
	private String strLine            = null;
	private String strAreaCode        = null;
	private String strUserGroupCode   = null;
	private String strDefectGroupCode = null;
	
	private String filePath = null;

	private String jobKeyPath = null;
	private String dsName = null;

	public RP_MUR_AVGMUR_MAP_SUMM_MacroJob(String jobKeyPath, String dsName)
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
				reader1 = new AVG_MURA_MAP_XY_4SF_RESULT_CsvIo(this.filePath);
				writer1 = new RP_MUR_AVGMUR_MAP_SUMM_CsvIo(this.filePath);
				
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
					
					for (AVG_MURA_MAP_XY_4SF_RESULT_Bean inBean1 : inList1) {

						if (Flag.TRUE) {
							RP_MUR_AVGMUR_MAP_SUMM_Bean outBean1 = new RP_MUR_AVGMUR_MAP_SUMM_Bean();
							
							outBean1.setClusterNo   (inBean1.getClusterNo   ());
							outBean1.setSeq         (inBean1.getSeq         ());
							outBean1.setSiteId      (inBean1.getSiteId      ());
							outBean1.setProdGrpName (inBean1.getProdGrpName ());
							outBean1.setProcId      (inBean1.getProcId      ());
							outBean1.setCellPosition(inBean1.getCellPosition());
							outBean1.setItemId      (inBean1.getItemId      ());
							outBean1.setItemName    (inBean1.getItemName    ());
							outBean1.setAvgX        (inBean1.getAvgX        ());
							outBean1.setAvgY        (inBean1.getAvgY        ());

							outList1.add(outBean1);
						}
					}
					
					if (Flag.TRUE) {
						String strLine = Parameter.getInstance().getStrLine();
						if ("L5FAB".equals(strLine) || "L6FAB".equals(strLine)) {

							RP_MUR_AVGMUR_MAP_SUMM_Bean outBean1;

							outBean1 = new RP_MUR_AVGMUR_MAP_SUMM_Bean();
							outBean1.setAvgX("0");
							outBean1.setAvgY("0");
							outList1.add(outBean1);
							
							outBean1 = new RP_MUR_AVGMUR_MAP_SUMM_Bean();
							outBean1.setAvgX("1300000");
							outBean1.setAvgY("1100000");
							outList1.add(outBean1);

						} else {
							
							RP_MUR_AVGMUR_MAP_SUMM_Bean outBean1;

							outBean1 = new RP_MUR_AVGMUR_MAP_SUMM_Bean();
							outBean1.setAvgX("0");
							outBean1.setAvgY("0");
							outList1.add(outBean1);
							
							outBean1 = new RP_MUR_AVGMUR_MAP_SUMM_Bean();
							outBean1.setAvgX("2500000");
							outBean1.setAvgY("2200000");
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
