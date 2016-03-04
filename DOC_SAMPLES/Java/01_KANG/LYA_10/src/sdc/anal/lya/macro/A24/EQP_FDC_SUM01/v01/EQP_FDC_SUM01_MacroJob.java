package sdc.anal.lya.macro.A24.EQP_FDC_SUM01.v01;

import java.util.List;

import kiea.kr.co.tain.base.Flag;
import kiea.proj.sdc.anal.macro.impl.job.AbstractMacroJob;
import kiea.proj.sdc.anal.util.BasePath;
import kiea.proj.sdc.anal.util.FileUtil;
import kiea.proj.sdc.anal.util.StrUtil;
import kiea.proj.sdc.anal.util.log.v02.Logger;

public class EQP_FDC_SUM01_MacroJob extends AbstractMacroJob
{
	private SO_SYS_CELLID_LIST2_CsvIo reader1 = null;
	private RP_TOTWIP_SUMM_CsvIo reader2 = null;
	private EQP_FDC_SUM01_OracleReader reader3 = null;
	private EQP_FDC_SUM01_CsvIo writer1 = null;
	
	private List<SO_SYS_CELLID_LIST2_Bean> inList1  = null;
	private List<RP_TOTWIP_SUMM_Bean> inList2  = null;
	private List<EQP_FDC_SUM01_Bean> inList3 = null;
	private List<EQP_FDC_SUM01_Bean> outList1 = null;
	
	private String filePath = null;

	private String jobId = null;

	public EQP_FDC_SUM01_MacroJob(String jobId)
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
				reader2 = new RP_TOTWIP_SUMM_CsvIo(this.filePath);
				reader3 = new EQP_FDC_SUM01_OracleReader();
				writer1 = new EQP_FDC_SUM01_CsvIo(this.filePath);
				
				inList1  = reader1.getList(true);
				inList2  = reader2.getList(true);
				reader3.setEqpGlassList(inList1, inList2);
				inList3  = reader3.getList(true);
				
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
					for (EQP_FDC_SUM01_Bean readBean : inList3) {
						EQP_FDC_SUM01_Bean writeBean = new EQP_FDC_SUM01_Bean();

						writeBean.setLine       (readBean.getLine       ());
						writeBean.setPart       (readBean.getPart       ());
						writeBean.setEqpModel   (readBean.getEqpModel   ());
						writeBean.setEqpId      (readBean.getEqpId      ());
						writeBean.setModuleName (readBean.getModuleName ());
						writeBean.setProcId     (readBean.getProcId     ());
						writeBean.setProdId     (readBean.getProdId     ());
						writeBean.setPpid       (readBean.getPpid       ());
						writeBean.setGlassId    (readBean.getGlassId    ());
						writeBean.setProcessStep(readBean.getProcessStep());
						writeBean.setBeginStep  (readBean.getBeginStep  ());
						writeBean.setSensorName (readBean.getSensorName ());
						writeBean.setParam      (readBean.getParam      ());
						writeBean.setParamValue (readBean.getParamValue ());
						writeBean.setUsl        (readBean.getUsl        ());
						writeBean.setLsl        (readBean.getLsl        ());
						writeBean.setBeginTime  (readBean.getBeginTime  ());

						outList1.add(writeBean);
					}
				}

				if (!Flag.TRUE) {
					for (int i=0; inList3 != null && i < inList3.size(); i++) {
						EQP_FDC_SUM01_Bean writeBean = new EQP_FDC_SUM01_Bean();

						writeBean.setLine       (inList3.get(i).getLine       ());
						writeBean.setPart       (inList3.get(i).getPart       ());
						writeBean.setEqpModel   (inList3.get(i).getEqpModel   ());
						writeBean.setEqpId      (inList3.get(i).getEqpId      ());
						writeBean.setModuleName (inList3.get(i).getModuleName ());
						writeBean.setProcId     (inList3.get(i).getProcId     ());
						writeBean.setProdId     (inList3.get(i).getProdId     ());
						writeBean.setPpid       (inList3.get(i).getPpid       ());
						writeBean.setGlassId    (inList3.get(i).getGlassId    ());
						writeBean.setProcessStep(inList3.get(i).getProcessStep());
						writeBean.setBeginStep  (inList3.get(i).getBeginStep  ());
						writeBean.setSensorName (inList3.get(i).getSensorName ());
						writeBean.setParam      (inList3.get(i).getParam      ());
						writeBean.setParamValue (inList3.get(i).getParamValue ());
						writeBean.setUsl        (inList3.get(i).getUsl        ());
						writeBean.setLsl        (inList3.get(i).getLsl        ());
						writeBean.setBeginTime  (inList3.get(i).getBeginTime  ());

						outList1.add(writeBean);
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
