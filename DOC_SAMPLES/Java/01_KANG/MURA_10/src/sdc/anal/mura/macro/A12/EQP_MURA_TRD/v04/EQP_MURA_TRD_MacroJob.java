package sdc.anal.mura.macro.A12.EQP_MURA_TRD.v04;

import java.util.List;

import kiea.kr.co.tain.base.Flag;
import kiea.proj.sdc.anal.macro.impl.job.AbstractMacroJob;
import kiea.proj.sdc.anal.tools.AnalCsv.v04.AnalCsvBean;
import kiea.proj.sdc.anal.tools.AnalCsv.v04.AnalCsvType;
import kiea.proj.sdc.anal.util.BasePath;
import kiea.proj.sdc.anal.util.FileUtil;
import kiea.proj.sdc.anal.util.StrUtil;
import kiea.proj.sdc.anal.util.log.v02.Logger;

public class EQP_MURA_TRD_MacroJob extends AbstractMacroJob
{
	private WIP_EQP_SMMRY_CsvIo reader1 = null;
	private MURA_SPC_CsvIo      reader2 = null;
	private EQP_MURA_TRD_CsvIo  writer1 = null;
	
	private List<WIP_EQP_SMMRY_Bean> inList1  = null;
	private List<MURA_SPC_Bean>      inList2  = null;
	private List<EQP_MURA_TRD_Bean>  outList1 = null;
	
	private String filePath = null;

	private String jobId = null;

	public EQP_MURA_TRD_MacroJob(String jobId)
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
			/*
			 * 시간기록 시작
			 */
			EQP_MURA_TRD_Main.runMSec = System.nanoTime();
		}

		if (Flag.TRUE) {
			/*
			 * 기존 자료 삭제
			 */
			AnalCsvBean bean = new AnalCsvBean();

			bean.setType(AnalCsvType.DELETE);
			bean.setTable("ANAL_CSV");
			bean.setWhere(String.format("JOB_ID = %s AND PROG_NM = %s", StrUtil.quote(jobId), StrUtil.quote(EQP_MURA_TRD_Main.dsName)));

			bean.sendToOracle();

			if (Flag.TRUE) Logger.info("[%s]", bean.getQuery());
		}

		if (Flag.TRUE) {
			/*
			 * INSERT
			 */
			AnalCsvBean bean = new AnalCsvBean();

			bean.setType(AnalCsvType.INSERT);
			bean.setTable("ANAL_CSV");
			bean.setFields(new String[][] {
					{ "JOB_ID     ", StrUtil.quote(jobId) },
					{ "CMD_CODE   ", StrUtil.quote(EQP_MURA_TRD_Main.dsName.substring(0, 3)) },
					{ "PROG_NM    ", StrUtil.quote(EQP_MURA_TRD_Main.dsName) },
					{ "CSV_NM     ", StrUtil.quote(EQP_MURA_TRD_Main.dsName + ".csv") },
					{ "MAIN_CLASS ", StrUtil.quote(this.getClass().getName()) },
					{ "S_TIME     ", "SYSDATE" },
					{ "CSV_STATUS ", StrUtil.quote("START") },
			});
			if (Flag.TRUE) Logger.info("[%s]", bean.getQuery());

			bean.sendToOracle();
		}

		if (Flag.TRUE) {
			try {
				reader1 = new WIP_EQP_SMMRY_CsvIo(this.filePath);
				reader2 = new MURA_SPC_CsvIo     (this.filePath);
				writer1 = new EQP_MURA_TRD_CsvIo (this.filePath);
				
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
				
				EQP_MURA_TRD_Main.cntWList = outList1.size();

				writer1.writeList();
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		if (Flag.TRUE) {
			/*
			 * 시간기록 끝
			 */
			EQP_MURA_TRD_Main.runMSec = (System.nanoTime() - EQP_MURA_TRD_Main.runMSec) / 1000 / 1000;
			
			if (Flag.TRUE) Logger.info("##### took time : %d ms", EQP_MURA_TRD_Main.runMSec);
		}

		if (Flag.TRUE) {
			/*
			 * UPDATE
			 */
			AnalCsvBean bean = new AnalCsvBean();
			
			bean.setType(AnalCsvType.UPDATE);
			bean.setTable("ANAL_CSV");
			bean.setFields(new String[][] {
					{ "LIST_CNT   ", "" + EQP_MURA_TRD_Main.cntWList },
					{ "E_TIME     ", "SYSDATE" },
					{ "R_MSEC     ", "" + EQP_MURA_TRD_Main.runMSec },
					{ "CSV_STATUS ", StrUtil.quote("OK") },   // OK, ERROR, SKIP, ETC...
					{ "LOG_MESSAGE", StrUtil.quote("COMPLETE") },  // COMPLETE, EXCEPTION
			});
			bean.setWhere(String.format("JOB_ID = %s AND PROG_NM = %s", StrUtil.quote(jobId), StrUtil.quote(EQP_MURA_TRD_Main.dsName)));
			
			if (Flag.TRUE) Logger.info("[%s]", bean.getQuery());
			
			bean.sendToOracle();
		}
	}
}
