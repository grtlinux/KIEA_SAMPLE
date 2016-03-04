package sdc.anal.mura.macro.A12.MURA_N_TREND.v01;

import java.util.List;

import kiea.kr.co.tain.base.Flag;
import kiea.proj.sdc.anal.macro.impl.job.AbstractMacroJob;
import kiea.proj.sdc.anal.util.BasePath;
import kiea.proj.sdc.anal.util.FileUtil;
import kiea.proj.sdc.anal.util.StrUtil;
import kiea.proj.sdc.anal.util.log.v02.Logger;

public class MURA_N_TREND_MacroJob extends AbstractMacroJob
{
	private static final double DATA_VALUE_CONST = 200;
	
	private MURA_TREND_CsvIo     reader1 = null;
	private MURA_N_TREND_CsvIo  writer1 = null;
	
	private List<MURA_TREND_Bean>     inList1  = null;
	private List<MURA_N_TREND_Bean>  outList1 = null;
	
	private String filePath = null;

	private String jobId = null;

	public MURA_N_TREND_MacroJob(String jobId)
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
				reader1 = new MURA_TREND_CsvIo    (this.filePath);
				writer1 = new MURA_N_TREND_CsvIo (this.filePath);
				
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

					String strDataValue = "";
					
					for (MURA_TREND_Bean inBean1 : inList1) {
						
						MURA_N_TREND_Bean outBean1 = new MURA_N_TREND_Bean();
						
						if (Double.parseDouble(inBean1.getDataValue()) > DATA_VALUE_CONST) {
							strDataValue = "" + DATA_VALUE_CONST;
						} else {
							strDataValue = inBean1.getDataValue();
						}
						
						outBean1.setProcId       (inBean1.getProcId       ());
						outBean1.setSiteId       (inBean1.getSiteId       ());
						outBean1.setCellId       (inBean1.getCellId       ());
						outBean1.setGlassId      (inBean1.getGlassId      ());
						outBean1.setProdRrpName  (inBean1.getProdRrpName  ());
						outBean1.setProdId       (inBean1.getProdId       ());
						outBean1.setOrgStepId    (inBean1.getOrgStepId    ());
						outBean1.setMeasEqpUnitId(inBean1.getMeasEqpUnitId());
						outBean1.setDcolTime     (inBean1.getDcolTime     ());
						outBean1.setItemId       (inBean1.getItemId       ());
						outBean1.setSubItemId    (inBean1.getSubItemId    ());
						outBean1.setDataValue    (inBean1.getDataValue    ());
						outBean1.setGateLine     (inBean1.getGateLine     ());
						outBean1.setDataLine     (inBean1.getDataLine     ());
						outBean1.setGateLine2    (inBean1.getGateLine2    ());
						outBean1.setDataLine2    (inBean1.getDataLine2    ());
						outBean1.setProdType     (inBean1.getProdType     ());
						outBean1.setMeasStepGrpId(inBean1.getMeasStepGrpId());
						outBean1.setCellLocId    (inBean1.getCellLocId    ());
						outBean1.setMuraValue    (strDataValue);                  // TODO : 2014.11.05 : 200이상을 200으로 고정
						outBean1.setDTime        (inBean1.getDcolTime().substring(0, 13));
						
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
