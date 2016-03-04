package kiea.proj.sdc.anal.macro.sample.a11.aib.macro.EQP_MURA_TRD;

import java.util.List;

import kiea.kr.co.tain.base.Flag;
import kiea.kr.co.tain.util.DateTime;
import kiea.proj.sdc.anal.macro.impl.job.AbstractMacroJob;
import kiea.proj.sdc.anal.util.BasePath;
import kiea.proj.sdc.anal.util.FileUtil;
import kiea.proj.sdc.anal.util.log.v02.Logger;

@SuppressWarnings("unused")
public class EQP_MURA_TRD_MacroJob extends AbstractMacroJob
{
	private WIP_EQP_SMMRY_CsvIo reader1 = null;
	private MURA_SPC_CsvIo      reader2 = null;
	private EQP_MURA_TRD_CsvIo  writer1 = null;
	
	private List<WIP_EQP_SMMRY_Bean> inList1  = null;
	private List<MURA_SPC_Bean>      inList2  = null;
	private List<EQP_MURA_TRD_Bean>  outList1 = null;
	
	private String strFromDateTime    = null;
	private String strToDateTime      = null;
	private String strLine            = null;
	private String strAreaCode        = null;
	private String strUserGroupCode   = null;
	private String strDefectGroupCode = null;
	
	private String filePath = null;

	private String jobKeyPath = null;
	private String dsName = null;

	public EQP_MURA_TRD_MacroJob(String jobKeyPath, String dsName)
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
				reader1 = new WIP_EQP_SMMRY_CsvIo(this.filePath);
				reader2 = new MURA_SPC_CsvIo     (this.filePath);
				writer1 = new EQP_MURA_TRD_CsvIo (this.filePath);
				
				inList1  = reader1.getList();
				inList2  = reader2.getList();
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
				if (inList2.size() > 0) {
					if (Flag.TRUE) {
						for (WIP_EQP_SMMRY_Bean inBean1 : inList1) {
							
							List<MURA_SPC_Bean> list2 = reader2.getListGlassId(inBean1.getGlassCellId());
							
							for (MURA_SPC_Bean inBean2 : list2) {
								EQP_MURA_TRD_Bean outBean1 = new EQP_MURA_TRD_Bean();
								
								outBean1.setUserGroupCode  (inBean1.getUserGroupCode  ());
								outBean1.setProcessId      (inBean1.getProcessId      ());
								outBean1.setProductId      (inBean1.getProductId      ());
								outBean1.setProductType    (inBean1.getProductType    ());
								outBean1.setAreaCode       (inBean1.getAreaCode       ());
								outBean1.setSubAreaCode    (inBean1.getSubAreaCode    ());
								outBean1.setStepId         (inBean1.getStepId         ());
								outBean1.setEqpId          (inBean1.getEqpId          ());
								outBean1.setGlassCellId    (inBean1.getGlassCellId    ());
								outBean1.setCellId         (inBean2.getCellId         ());
								outBean1.setTkInDate       (inBean1.getTkInDate       ());
								outBean1.setTkOutDate      (inBean1.getTkOutDate      ());
								outBean1.setDefectGroupCode(inBean1.getDefectGroupCode());
								
								outBean1.setMuraData       (inBean2.getItemId         ());
								outBean1.setDTime          (inBean1.getTkOutDate().substring(0, 13));
								
								outList1.add(outBean1);
							}
						}
					}
				} else {
					if (Flag.TRUE) {
						for (WIP_EQP_SMMRY_Bean inBean1 : inList1) {
							
							EQP_MURA_TRD_Bean outBean1 = new EQP_MURA_TRD_Bean();
							
							outBean1.setUserGroupCode  (inBean1.getUserGroupCode  ());
							outBean1.setProcessId      (inBean1.getProcessId      ());
							outBean1.setProductId      (inBean1.getProductId      ());
							outBean1.setProductType    (inBean1.getProductType    ());
							outBean1.setAreaCode       (inBean1.getAreaCode       ());
							outBean1.setSubAreaCode    (inBean1.getSubAreaCode    ());
							outBean1.setStepId         (inBean1.getStepId         ());
							outBean1.setEqpId          (inBean1.getEqpId          ());
							outBean1.setGlassCellId    (inBean1.getGlassCellId    ());
							outBean1.setCellId         (inBean1.getCellId         ());
							outBean1.setTkInDate       (inBean1.getTkInDate       ());
							outBean1.setTkOutDate      (inBean1.getTkOutDate      ());
							outBean1.setDefectGroupCode(inBean1.getDefectGroupCode());
							
							outBean1.setMuraData       (inBean1.getDataValue      ());
							outBean1.setDTime          (inBean1.getTkOutDate().substring(0, 13));
							
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
