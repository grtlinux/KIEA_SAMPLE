package kiea.proj.sdc.anal.macro.sample.a11.img.macro.CONTACT_MAP_XY_DIST_RESULT;

import java.util.List;

import kiea.kr.co.tain.base.Flag;
import kiea.kr.co.tain.util.DateTime;
import kiea.proj.sdc.anal.macro.impl.job.AbstractMacroJob;
import kiea.proj.sdc.anal.util.BasePath;
import kiea.proj.sdc.anal.util.FileUtil;
import kiea.proj.sdc.anal.util.log.v02.Logger;

@SuppressWarnings("unused")
public class CONTACT_MAP_XY_DIST_RESULT_MacroJob extends AbstractMacroJob
{
	private CONTACT_MAP_XY_RESULT_CsvIo reader1 = null;
	private MURA_MAP_XY_RESULT_CsvIo reader2 = null;
	private CONTACT_MAP_XY_DIST_RESULT_CsvIo writer1 = null;
	
	private List<CONTACT_MAP_XY_RESULT_Bean> inList1  = null;
	private List<MURA_MAP_XY_RESULT_Bean> inList2  = null;
	private List<CONTACT_MAP_XY_DIST_RESULT_Bean> outList1 = null;
	
	private String strFromDateTime    = null;
	private String strToDateTime      = null;
	private String strLine            = null;
	private String strAreaCode        = null;
	private String strUserGroupCode   = null;
	private String strDefectGroupCode = null;
	
	private String filePath = null;

	private String jobKeyPath = null;
	private String dsName = null;

	public CONTACT_MAP_XY_DIST_RESULT_MacroJob(String jobKeyPath, String dsName)
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
				reader1 = new CONTACT_MAP_XY_RESULT_CsvIo(this.filePath);
				reader2 = new MURA_MAP_XY_RESULT_CsvIo(this.filePath);
				writer1 = new CONTACT_MAP_XY_DIST_RESULT_CsvIo(this.filePath);
				
				inList1  = reader1.getList();
				inList2  = reader2.getList();
				outList1 = writer1.getList();
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	private boolean getBoolDistance(double[] p, double[] q)
	{
		boolean ret = false;
		
		if (Flag.TRUE) {
			double px1 = Math.min(p[0], p[2]);
			double py1 = Math.min(p[1], p[3]);
			double px2 = Math.max(p[0], p[2]);
			double py2 = Math.max(p[1], p[3]);
			
			double qx1 = Math.min(q[0], q[2]);
			double qy1 = Math.min(q[1], q[3]);
			double qx2 = Math.max(q[0], q[2]);
			double qy2 = Math.max(q[1], q[3]);
			
			double px = (px1 + px2) / 2;
			double py = (py1 + py2) / 2;
			double qx = (qx1 + qx2) / 2;
			double qy = (qy1 + qy2) / 2;
			
			double dist = Math.sqrt(Math.pow(px - qx, 2.0) + Math.pow(py - qy, 2.0));
			
			if (dist <= 30000) {
				ret = true;
			}
		}
		
		return ret;
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
					for (CONTACT_MAP_XY_RESULT_Bean inBean1 : inList1) {

						for (MURA_MAP_XY_RESULT_Bean inBean2 : inList2) {
							
							double[] coordRect = new double[] {
									Double.parseDouble(inBean1.getContactX1()),
									Double.parseDouble(inBean1.getContactY1()),
									Double.parseDouble(inBean1.getContactX2()),
									Double.parseDouble(inBean1.getContactY2()),
							};

							double[] cellRect = new double[] {
									Double.parseDouble(inBean2.getAvgX1()),
									Double.parseDouble(inBean2.getAvgY1()),
									Double.parseDouble(inBean2.getAvgX2()),
									Double.parseDouble(inBean2.getAvgY2()),
							};
							
							if (!getBoolDistance(coordRect, cellRect))
								continue;
							
							CONTACT_MAP_XY_DIST_RESULT_Bean outBean1 = new CONTACT_MAP_XY_DIST_RESULT_Bean();

							outBean1.setEqpName        (inBean1.getEqpName        ());
							outBean1.setLine           (inBean1.getLine           ());
							outBean1.setAreaId         (inBean1.getAreaId         ());
							outBean1.setPart           (inBean1.getPart           ());
							outBean1.setMaker          (inBean1.getMaker          ());
							outBean1.setUnitName       (inBean1.getUnitName       ());
							outBean1.setContactMap     (inBean1.getContactMap     ());
							outBean1.setCoordX1        (inBean1.getCoordX1        ());
							outBean1.setCoordY1        (inBean1.getCoordY1        ());
							outBean1.setCoordX2        (inBean1.getCoordX2        ());
							outBean1.setCoordY2        (inBean1.getCoordY2        ());
							outBean1.setMaterial       (inBean1.getMaterial       ());
							outBean1.setType           (inBean1.getType           ());
							outBean1.setVersion        (inBean1.getVersion        ());
							outBean1.setContactMapAttr1(inBean1.getContactMapAttr1());
							outBean1.setContactMapAttr2(inBean1.getContactMapAttr2());
							outBean1.setUserId         (inBean1.getUserId         ());
							outBean1.setUpdateTime     (inBean1.getUpdateTime     ());
							outBean1.setCellNo         (inBean1.getCellNo         ());
							outBean1.setProcId         (inBean1.getProcId         ());
							outBean1.setCellLocId      (inBean1.getCellLocId      ());
							outBean1.setPointX         (inBean1.getPointX         ());
							outBean1.setPointY         (inBean1.getPointY         ());
							outBean1.setWidth          (inBean1.getWidth          ());
							outBean1.setHeight         (inBean1.getHeight         ());
							outBean1.setPointX2        (inBean1.getPointX2        ());
							outBean1.setPointY2        (inBean1.getPointY2        ());
							outBean1.setContactX1      (inBean1.getContactX1      ());
							outBean1.setContactY1      (inBean1.getContactY1      ());
							outBean1.setContactX2      (inBean1.getContactX2      ());
							outBean1.setContactY2      (inBean1.getContactY2      ());
							outBean1.setSiteId         (inBean2.getSiteId         ());
							outBean1.setProdGrpName    (inBean2.getProdGrpName    ());
							outBean1.setCellPosition   (inBean2.getCellPosition   ());
							outBean1.setItemId         (inBean2.getItemId         ());
							outBean1.setItemName       (inBean2.getItemName       ());
							outBean1.setAvgX1          (inBean2.getAvgX1          ());
							outBean1.setAvgY1          (inBean2.getAvgY1          ());
							outBean1.setAvgX2          (inBean2.getAvgX2          ());
							outBean1.setAvgY2          (inBean2.getAvgY2          ());
							outBean1.setClusterId      (inBean2.getClusterId      ());
							outBean1.setClusterNo      (inBean2.getClusterNo      ());

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
