package sdc.anal.lya.macro.A22.RP_SYS_WIP_DETAILS3.v00;

import java.util.List;

import kiea.kr.co.tain.base.Flag;
import kiea.proj.sdc.anal.macro.impl.job.AbstractMacroJob;
import kiea.proj.sdc.anal.util.BasePath;
import kiea.proj.sdc.anal.util.FileUtil;
import kiea.proj.sdc.anal.util.StrUtil;
import kiea.proj.sdc.anal.util.log.v02.Logger;

@SuppressWarnings("unused")
public class RP_SYS_WIP_DETAILS3_MacroJob extends AbstractMacroJob
{
	private SO_SYS_CELLID_LIST2_CsvIo reader1 = null;
	private WIP_EQP_SMMRY_CsvIo reader2 = null;
	private STEP_DESC_CsvIo reader3 = null;
	private RP_SYS_WIP_DETAILS3_CsvIo writer1 = null;
	
	private List<SO_SYS_CELLID_LIST2_Bean> inList1  = null;
	private List<WIP_EQP_SMMRY_Bean> inList2  = null;
	private List<STEP_DESC_Bean> inList3  = null;
	private List<RP_SYS_WIP_DETAILS3_Bean> outList1 = null;
	
	private String filePath = null;

	private String jobId = null;

	public RP_SYS_WIP_DETAILS3_MacroJob(String jobId)
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
				reader1 = new SO_SYS_CELLID_LIST2_CsvIo(this.filePath);
				reader2 = new WIP_EQP_SMMRY_CsvIo(this.filePath);
				reader3 = new STEP_DESC_CsvIo(this.filePath);
				writer1 = new RP_SYS_WIP_DETAILS3_CsvIo(this.filePath);
				
				//inList1  = reader1.getList(true);
				inList2  = reader2.getList(true);
				//inList3  = reader3.getList(true);
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
				for (WIP_EQP_SMMRY_Bean inBean2 : inList2) {
					
					RP_SYS_WIP_DETAILS3_Bean outBean1 = new RP_SYS_WIP_DETAILS3_Bean();
					
					outBean1.setDivCode        ("EQP_ID");
					outBean1.setLineCode       (inBean2.getLineCode       ());
					outBean1.setUserGroupCode  (inBean2.getUserGroupCode  ());
					outBean1.setProcessId      (inBean2.getProcessId      ());
					outBean1.setProductId      (inBean2.getProductId      ());
					outBean1.setProductType    (inBean2.getProductType    ());
					outBean1.setAreaCode       (inBean2.getAreaCode       ());
					outBean1.setSubAreaCode    (inBean2.getSubAreaCode    ());
					outBean1.setGlassId        (inBean2.getGlassId        ());
					outBean1.setStepId         (inBean2.getStepId         ());
					outBean1.setEqpId          (inBean2.getEqpId          ());
					outBean1.setGlassBadRatio  ("");
					outBean1.setTrckInTime     (inBean2.getTrckInTime     ());
					outBean1.setDefectGroupCode(inBean2.getDefectGroupCode());
					outBean1.setDecisionCode   (inBean2.getDecisionCode   ());
					
					outList1.add(outBean1);
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
