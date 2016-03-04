package sdc.anal.lya.macro.A23.SI_SYS_MOD_EQP_01.v01;

import java.util.List;

import kiea.kr.co.tain.base.Flag;
import kiea.proj.sdc.anal.common.Parameter;
import kiea.proj.sdc.anal.macro.impl.job.AbstractMacroJob;
import kiea.proj.sdc.anal.util.BasePath;
import kiea.proj.sdc.anal.util.FileUtil;
import kiea.proj.sdc.anal.util.StrUtil;
import kiea.proj.sdc.anal.util.log.v02.Logger;

public class SI_SYS_MOD_EQP_01_MacroJob extends AbstractMacroJob
{
	private MOD_EQP_SMMRY_CsvIo reader1 = null;
	private SI_SYS_MOD_EQP_01_CsvIo writer1 = null;
	
	private List<MOD_EQP_SMMRY_Bean> inList1  = null;
	private List<SI_SYS_MOD_EQP_01_Bean> outList1 = null;
	
	private String filePath = null;

	private String jobId = null;

	public SI_SYS_MOD_EQP_01_MacroJob(String jobId)
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
				reader1 = new MOD_EQP_SMMRY_CsvIo(this.filePath);
				writer1 = new SI_SYS_MOD_EQP_01_CsvIo(this.filePath);
				
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
				String strDecisionCode = Parameter.getInstance().getStrDecisionCode();
				String strDefectGroupCode = Parameter.getInstance().getStrDefectGroupCode();
				
				if (Flag.TRUE) {
					
					for (MOD_EQP_SMMRY_Bean inBean1 : inList1) {
						
						if (( "OK".equals(inBean1.getDecisionCode()) || "0".equals(inBean1.getDecisionCode()) )
								|| ( strDecisionCode.equals(inBean1.getDecisionCode()) && strDefectGroupCode.equals(inBean1.getDefectGroupCode()))) {

							SI_SYS_MOD_EQP_01_Bean outBean1 = new SI_SYS_MOD_EQP_01_Bean();
							
							outBean1.setLineCode       (inBean1.getLineCode       ());
							outBean1.setUserGroupCode  (inBean1.getUserGroupCode  ());
							outBean1.setProcessId      (inBean1.getProcessId      ());
							outBean1.setProductId      (inBean1.getProductId      ());
							outBean1.setProductType    (inBean1.getProductType    ());
							outBean1.setAreaAode       (inBean1.getAreaAode       ());
							outBean1.setSubAreaCode    (inBean1.getSubAreaCode    ());
							outBean1.setStepId         (inBean1.getStepId         ());
							outBean1.setEqpId          (inBean1.getEqpId          ());
							outBean1.setGlassId        (inBean1.getGlassId        ());
							outBean1.setCellId         (inBean1.getCellId         ());
							outBean1.setCellLocId      (inBean1.getCellLocId      ());
							outBean1.setDefectGroupCode(inBean1.getDefectGroupCode());
							outBean1.setDecisionCode   (inBean1.getDecisionCode   ());
							outBean1.setInspectorId    (inBean1.getInspectorId    ());
							outBean1.setInspectDt      (inBean1.getInspectDt      ());
							outBean1.setInspectHour    (inBean1.getInspectHour    ());
							outBean1.setInspectHour2   (inBean1.getInspectHour2   ());

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
