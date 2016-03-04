package kiea.proj.sdc.anal.macro.sample.a11.aib.macro.RP_MUR_CELL_MAP_SUMM;

import java.util.List;

import kiea.kr.co.tain.base.Flag;
import kiea.kr.co.tain.util.DateTime;
import kiea.proj.sdc.anal.macro.impl.job.AbstractMacroJob;
import kiea.proj.sdc.anal.util.BasePath;
import kiea.proj.sdc.anal.util.FileUtil;
import kiea.proj.sdc.anal.util.log.v02.Logger;

@SuppressWarnings("unused")
public class RP_MUR_CELL_MAP_SUMM_MacroJob extends AbstractMacroJob
{
	private CELL_MAP_XY_CsvIo          reader1 = null;
	private RP_MUR_CELL_MAP_SUMM_CsvIo writer1 = null;
	
	private List<CELL_MAP_XY_Bean>          inList1  = null;
	private List<RP_MUR_CELL_MAP_SUMM_Bean> outList1 = null;
	
	private String strFromDateTime    = null;
	private String strToDateTime      = null;
	private String strLine            = null;
	private String strAreaCode        = null;
	private String strUserGroupCode   = null;
	private String strDefectGroupCode = null;
	
	private String filePath = null;

	private String jobKeyPath = null;
	private String dsName = null;

	public RP_MUR_CELL_MAP_SUMM_MacroJob(String jobKeyPath, String dsName)
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
				reader1 = new CELL_MAP_XY_CsvIo          (this.filePath);
				writer1 = new RP_MUR_CELL_MAP_SUMM_CsvIo (this.filePath);
				
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

					for (CELL_MAP_XY_Bean inBean1 : inList1) {
						
						if (Flag.TRUE) {
							RP_MUR_CELL_MAP_SUMM_Bean outBean1 = new RP_MUR_CELL_MAP_SUMM_Bean();
							
							outBean1.setCellNo   (inBean1.getCellNo   ());
							outBean1.setProcId   (inBean1.getProcId   ());
							outBean1.setCellLocId(inBean1.getCellLocId());
							outBean1.setPointX   (inBean1.getPointX   ());
							outBean1.setPointY   (inBean1.getPointY   ());
							outBean1.setWidth    (inBean1.getWidth    ());
							outBean1.setHeight   (inBean1.getHeight   ());
							outBean1.setPointX2  (inBean1.getPointX2  ());
							outBean1.setPointY2  (inBean1.getPointY2  ());
							outBean1.setSeq      (inBean1.getCellNo   ());

							outBean1.setPointX2  ("" + (Integer.parseInt(inBean1.getPointX()) + Integer.parseInt(inBean1.getWidth ())));
							outBean1.setPointY2  ("" + (Integer.parseInt(inBean1.getPointY()) + Integer.parseInt(inBean1.getHeight())));
							
							outList1.add(outBean1);
						}
						
						if (Flag.TRUE) {
							RP_MUR_CELL_MAP_SUMM_Bean outBean1 = new RP_MUR_CELL_MAP_SUMM_Bean();
							
							outBean1.setCellNo   (inBean1.getCellNo   ());
							outBean1.setProcId   (inBean1.getProcId   ());
							outBean1.setCellLocId(inBean1.getCellLocId());
							outBean1.setPointX   (inBean1.getPointX   ());
							outBean1.setPointY   ("" + (Integer.parseInt(inBean1.getPointY()) + Integer.parseInt(inBean1.getHeight())));
							outBean1.setWidth    (inBean1.getWidth    ());
							outBean1.setHeight   (inBean1.getHeight   ());
							outBean1.setPointX2  (inBean1.getPointX2  ());
							outBean1.setPointY2  (inBean1.getPointY2  ());
							outBean1.setSeq      (inBean1.getCellNo   ());
							
							outList1.add(outBean1);
						}

						if (Flag.TRUE) {
							RP_MUR_CELL_MAP_SUMM_Bean outBean1 = new RP_MUR_CELL_MAP_SUMM_Bean();
							
							outBean1.setCellNo   (inBean1.getCellNo   ());
							outBean1.setProcId   (inBean1.getProcId   ());
							outBean1.setCellLocId(inBean1.getCellLocId());
							outBean1.setPointX   ("" + (Integer.parseInt(inBean1.getPointX()) + Integer.parseInt(inBean1.getWidth ())));
							outBean1.setPointY   ("" + (Integer.parseInt(inBean1.getPointY()) + Integer.parseInt(inBean1.getHeight())));
							outBean1.setWidth    (inBean1.getWidth    ());
							outBean1.setHeight   (inBean1.getHeight   ());
							outBean1.setPointX2  (inBean1.getPointX2  ());
							outBean1.setPointY2  (inBean1.getPointY2  ());
							outBean1.setSeq      (inBean1.getCellNo   ());
							
							outList1.add(outBean1);
						}
						
						if (Flag.TRUE) {
							RP_MUR_CELL_MAP_SUMM_Bean outBean1 = new RP_MUR_CELL_MAP_SUMM_Bean();
							
							outBean1.setCellNo   (inBean1.getCellNo   ());
							outBean1.setProcId   (inBean1.getProcId   ());
							outBean1.setCellLocId(inBean1.getCellLocId());
							outBean1.setPointX   ("" + (Integer.parseInt(inBean1.getPointX()) + Integer.parseInt(inBean1.getWidth ())));
							outBean1.setPointY   (inBean1.getPointY   ());
							outBean1.setWidth    (inBean1.getWidth    ());
							outBean1.setHeight   (inBean1.getHeight   ());
							outBean1.setPointX2  (inBean1.getPointX2  ());
							outBean1.setPointY2  (inBean1.getPointY2  ());
							outBean1.setSeq      (inBean1.getCellNo   ());
							
							outList1.add(outBean1);
						}
						
						if (Flag.TRUE) {
							RP_MUR_CELL_MAP_SUMM_Bean outBean1 = new RP_MUR_CELL_MAP_SUMM_Bean();
							
							outBean1.setCellNo   (inBean1.getCellNo   ());
							outBean1.setProcId   (inBean1.getProcId   ());
							outBean1.setCellLocId(inBean1.getCellLocId());
							outBean1.setPointX   (inBean1.getPointX   ());
							outBean1.setPointY   (inBean1.getPointY   ());
							outBean1.setWidth    (inBean1.getWidth    ());
							outBean1.setHeight   (inBean1.getHeight   ());
							outBean1.setPointX2  (inBean1.getPointX2  ());
							outBean1.setPointY2  (inBean1.getPointY2  ());
							outBean1.setSeq      (inBean1.getCellNo   ());
							
							outList1.add(outBean1);
						}
						
						if (Flag.TRUE) {
							RP_MUR_CELL_MAP_SUMM_Bean outBean1 = new RP_MUR_CELL_MAP_SUMM_Bean();
							
							outBean1.setCellNo   (inBean1.getCellNo   ());
							outBean1.setProcId   (inBean1.getProcId   ());
							outBean1.setCellLocId(inBean1.getCellLocId());
							outBean1.setPointX   ("");
							outBean1.setPointY   ("");
							outBean1.setWidth    ("");
							outBean1.setHeight   ("");
							outBean1.setPointX2  ("");
							outBean1.setPointY2  ("");
							outBean1.setSeq      (inBean1.getCellNo   ());
							
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
