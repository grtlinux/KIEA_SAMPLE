package sdc.anal.mura.macro.A12.MURA_D_01.v01;

import java.util.List;

import kiea.kr.co.tain.base.Flag;
import kiea.proj.sdc.anal.macro.impl.job.AbstractMacroJob;
import kiea.proj.sdc.anal.util.BasePath;
import kiea.proj.sdc.anal.util.FileUtil;
import kiea.proj.sdc.anal.util.StrUtil;
import kiea.proj.sdc.anal.util.log.v02.Logger;

public class MURA_D_01_MacroJob extends AbstractMacroJob
{
	private MURA_D_CsvIo     reader1 = null;
	private MURA_D_01_CsvIo  writer1 = null;
	
	private List<MURA_D_Bean>     inList1  = null;
	private List<MURA_D_01_Bean>  outList1 = null;
	
	private String filePath = null;

	private String jobId = null;

	public MURA_D_01_MacroJob(String jobId)
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
				reader1 = new MURA_D_CsvIo    (this.filePath);
				writer1 = new MURA_D_01_CsvIo (this.filePath);
				
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
				 * 1. InBean -> OutBean
				 */
				if (Flag.TRUE) {
					
					for (MURA_D_Bean inBean1 : inList1) {
						MURA_D_01_Bean outBean1 = new MURA_D_01_Bean();
						
						outBean1.setProcId       (inBean1.getProcId       ());
						outBean1.setSiteId       (inBean1.getSiteId       ());
						outBean1.setCellId       (inBean1.getCellId       ());
						outBean1.setGlassId      (inBean1.getGlassId      ());
						outBean1.setProdRrpName  (inBean1.getProdRrpName  ());
						outBean1.setProdId       (inBean1.getProdId       ());
						outBean1.setOrgStepId    (inBean1.getOrgStepId    ());
						outBean1.setMeasEqpUnitId(inBean1.getMeasEqpUnitId());
						outBean1.setDcolDate     (inBean1.getDcolDate     ());
						outBean1.setItemId       (inBean1.getItemId       ());
						outBean1.setSubItemId    (inBean1.getSubItemId    ());
						outBean1.setDataValue    (inBean1.getDataValue    ());
						outBean1.setGateLine     (inBean1.getGateLine     ());
						outBean1.setDataLine     (inBean1.getDataLine     ());
						outBean1.setGateLine2    (inBean1.getGateLine2    ());
						outBean1.setDataLine2    (inBean1.getDataLine2    ());
						outBean1.setProdType     (inBean1.getProdType     ());
						outBean1.setCellLocId    (inBean1.getCellLocId    ());
						
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
