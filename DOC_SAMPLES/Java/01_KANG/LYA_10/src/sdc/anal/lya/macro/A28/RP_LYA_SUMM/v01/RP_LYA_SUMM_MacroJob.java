package sdc.anal.lya.macro.A28.RP_LYA_SUMM.v01;

import java.util.List;

import kiea.kr.co.tain.base.Flag;
import kiea.proj.sdc.anal.common.Parameter;
import kiea.proj.sdc.anal.macro.impl.job.AbstractMacroJob;
import kiea.proj.sdc.anal.util.BasePath;
import kiea.proj.sdc.anal.util.FileUtil;
import kiea.proj.sdc.anal.util.StrUtil;
import kiea.proj.sdc.anal.util.log.v02.Logger;

public class RP_LYA_SUMM_MacroJob extends AbstractMacroJob
{
	private INSPCT_HIST_CsvIo reader1 = null;
	private JOBID_PARAM_CsvIo reader2 = null;
	private RP_LYA_SUMM_CsvIo writer1 = null;
	
	private List<INSPCT_HIST_Bean> inList1  = null;
	private List<JOBID_PARAM_Bean> inList2  = null;
	private List<RP_LYA_SUMM_Bean> outList1 = null;
	
	private String filePath = null;

	private String jobId = null;

	public RP_LYA_SUMM_MacroJob(String jobId)
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
				reader1 = new INSPCT_HIST_CsvIo(this.filePath);
				reader2 = new JOBID_PARAM_CsvIo(this.filePath);
				writer1 = new RP_LYA_SUMM_CsvIo(this.filePath);
				
				inList1  = reader1.getList(true);
				inList2  = reader2.getList(true);
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
				String strParameterCount = "";
				long cntTotal = 0;     // 진행 Cell 갯수
				long cntBad = 0;       // 불량 Cell 갯수
				double ratioBad = 0;  // Cell 불량률
				
				if (Flag.TRUE) {
					String paramDecisionCode = StrUtil.quote(Parameter.getInstance().getStrDecisionCode());
					String paramDefectGroupCode = StrUtil.quote(Parameter.getInstance().getStrDefectGroupCode());
					
					for (INSPCT_HIST_Bean inBean1 : inList1) {
						if (paramDecisionCode.indexOf(StrUtil.quote(inBean1.getDecisionCode())) >= 0
						&& paramDefectGroupCode.indexOf(StrUtil.quote(inBean1.getDefectGroupCode())) >= 0) {
							cntBad ++;
						}
						cntTotal ++;
					}
					
					ratioBad = (double) cntBad * 100 / cntTotal;
				}
				
				if (Flag.TRUE) {
					for (JOBID_PARAM_Bean inBean2 : inList2) {
						if ("PARAMETER_COUNT".equals(inBean2.getParamNm())) {
							strParameterCount = inBean2.getParamVal();
							break;
						}
					}
				}
				
				if (Flag.TRUE) {
					/*
					 * 해당자료만 추출한다.
					 */
					RP_LYA_SUMM_Bean outBean1 = new RP_LYA_SUMM_Bean();
					
					outBean1.setQafJobBu       ("LCD");
					outBean1.setQafJobId       (this.jobId);
					outBean1.setAnalMethod     (Parameter.getInstance().getStrAnalMethod     ());
					outBean1.setLine           (Parameter.getInstance().getStrLine           ());
					outBean1.setAreaCode       (Parameter.getInstance().getStrAreaCode       ());
					outBean1.setSubAreaCode    (Parameter.getInstance().getStrSubAreaCode    ());
					outBean1.setUserGroupCode  (Parameter.getInstance().getStrUserGroupCode  ().replace("'", "").replace(",", "/"));
					outBean1.setProductType    (Parameter.getInstance().getStrProductType    ().replace("'", "").replace(",", "/"));
					outBean1.setDefectGroupCode(Parameter.getInstance().getStrDefectGroupCode().replace("'", "").replace(",", "/"));
					outBean1.setDefectGroupDesc("");
					outBean1.setDecisionCode   (Parameter.getInstance().getStrDecisionCode   ().replace("'", "").replace(",", "/"));
					outBean1.setFromDateTime   (Parameter.getInstance().getStrFromDateTime   ());
					outBean1.setToDateTime     (Parameter.getInstance().getStrToDateTime     ());
					outBean1.setParameterCount (strParameterCount);
					outBean1.setUsl            (Parameter.getInstance().getStrUsl            ());
					outBean1.setTotGlassCnt    ("");
					outBean1.setSysGlassCnt    ("");
					outBean1.setRanglassCnt    ("");
					outBean1.setSystematicRatio("");
					outBean1.setRandomRatio    ("");
					outBean1.setTotCellCount   ("" + cntTotal);
					outBean1.setBadCellCount   ("" + cntBad);
					outBean1.setBadCellRatio   ("" + ratioBad);

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
