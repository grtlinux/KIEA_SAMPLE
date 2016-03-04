package sdc.anal.mura.macro.A22.CONTACT_MURA_DIST_RESULT.v04;

import java.util.List;

import kiea.kr.co.tain.base.Flag;
import kiea.proj.sdc.anal.macro.impl.job.AbstractMacroJob;
import kiea.proj.sdc.anal.tools.AnalCsv.v04.AnalCsvBean;
import kiea.proj.sdc.anal.tools.AnalCsv.v04.AnalCsvType;
import kiea.proj.sdc.anal.util.BasePath;
import kiea.proj.sdc.anal.util.FileUtil;
import kiea.proj.sdc.anal.util.StrUtil;
import kiea.proj.sdc.anal.util.log.v02.Logger;

public class CONTACT_MURA_DIST_RESULT_MacroJob extends AbstractMacroJob
{
	private CONTACT_MAP_XY_DIST_RESULT_CsvIo reader1 = null;
	private CONTACT_MURA_XY_RESULT_CsvIo reader2 = null;
	private CONTACT_MURA_DIST_RESULT_CsvIo writer1 = null;
	
	private List<CONTACT_MAP_XY_DIST_RESULT_Bean> inList1  = null;
	private List<CONTACT_MURA_XY_RESULT_Bean> inList2  = null;
	private List<CONTACT_MURA_DIST_RESULT_Bean> outList1 = null;
	
	private String filePath = null;

	private String jobId = null;

	public CONTACT_MURA_DIST_RESULT_MacroJob(String jobId)
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
			CONTACT_MURA_DIST_RESULT_Main.runMSec = System.nanoTime();
		}

		if (Flag.TRUE) {
			/*
			 * 기존 자료 삭제
			 */
			AnalCsvBean bean = new AnalCsvBean();

			bean.setType(AnalCsvType.DELETE);
			bean.setTable("ANAL_CSV");
			bean.setWhere(String.format("JOB_ID = %s AND PROG_NM = %s", StrUtil.quote(jobId), StrUtil.quote(CONTACT_MURA_DIST_RESULT_Main.dsName)));

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
					{ "CMD_CODE   ", StrUtil.quote(CONTACT_MURA_DIST_RESULT_Main.dsName.substring(0, 3)) },
					{ "PROG_NM    ", StrUtil.quote(CONTACT_MURA_DIST_RESULT_Main.dsName) },
					{ "CSV_NM     ", StrUtil.quote(CONTACT_MURA_DIST_RESULT_Main.dsName + ".csv") },
					{ "MAIN_CLASS ", StrUtil.quote(this.getClass().getName()) },
					{ "S_TIME     ", "SYSDATE" },
					{ "CSV_STATUS ", StrUtil.quote("START") },
			});
			if (Flag.TRUE) Logger.info("[%s]", bean.getQuery());

			bean.sendToOracle();
		}

		if (Flag.TRUE) {
			try {
				reader1 = new CONTACT_MAP_XY_DIST_RESULT_CsvIo(this.filePath);
				reader2 = new CONTACT_MURA_XY_RESULT_CsvIo(this.filePath);
				writer1 = new CONTACT_MURA_DIST_RESULT_CsvIo(this.filePath);
				
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
				
				if (Flag.TRUE) {
					/*
					 * 해당자료만 추출한다.
					 */
					for (CONTACT_MAP_XY_DIST_RESULT_Bean inBean1 : inList1) {

						CONTACT_MURA_DIST_RESULT_Bean outBean1 = new CONTACT_MURA_DIST_RESULT_Bean();

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
						outBean1.setSiteId         (inBean1.getSiteId         ());
						outBean1.setProdGrpName    (inBean1.getProdGrpName    ());
						outBean1.setCellPosition   (inBean1.getCellPosition   ());
						outBean1.setItemId         (inBean1.getItemId         ());
						outBean1.setItemName       (inBean1.getItemName       ());
						outBean1.setAvgX1          (inBean1.getAvgX1          ());
						outBean1.setAvgY1          (inBean1.getAvgY1          ());
						outBean1.setAvgX2          (inBean1.getAvgX2          ());
						outBean1.setAvgY2          (inBean1.getAvgY2          ());
						outBean1.setClusterId      (inBean1.getClusterId      ());
						outBean1.setClusterNo      (inBean1.getClusterNo      ());

						outList1.add(outBean1);
					}

					for (CONTACT_MURA_XY_RESULT_Bean inBean2 : inList2) {

						CONTACT_MURA_DIST_RESULT_Bean outBean1 = new CONTACT_MURA_DIST_RESULT_Bean();

						outBean1.setEqpName        (inBean2.getEqpName        ());
						outBean1.setLine           (inBean2.getLine           ());
						outBean1.setAreaId         (inBean2.getAreaId         ());
						outBean1.setPart           (inBean2.getPart           ());
						outBean1.setMaker          (inBean2.getMaker          ());
						outBean1.setUnitName       (inBean2.getUnitName       ());
						outBean1.setContactMap     (inBean2.getContactMap     ());
						outBean1.setCoordX1        (inBean2.getCoordX1        ());
						outBean1.setCoordY1        (inBean2.getCoordY1        ());
						outBean1.setCoordX2        (inBean2.getCoordX2        ());
						outBean1.setCoordY2        (inBean2.getCoordY2        ());
						outBean1.setMaterial       (inBean2.getMaterial       ());
						outBean1.setType           (inBean2.getType           ());
						outBean1.setVersion        (inBean2.getVersion        ());
						outBean1.setContactMapAttr1(inBean2.getContactMapAttr1());
						outBean1.setContactMapAttr2(inBean2.getContactMapAttr2());
						outBean1.setUserId         (inBean2.getUserId         ());
						outBean1.setUpdateTime     (inBean2.getUpdateTime     ());
						outBean1.setCellNo         (inBean2.getCellNo         ());
						outBean1.setProcId         (inBean2.getProcId         ());
						outBean1.setCellLocId      (inBean2.getCellLocId      ());
						outBean1.setPointX         (inBean2.getPointX         ());
						outBean1.setPointY         (inBean2.getPointY         ());
						outBean1.setWidth          (inBean2.getWidth          ());
						outBean1.setHeight         (inBean2.getHeight         ());
						outBean1.setPointX2        (inBean2.getPointX2        ());
						outBean1.setPointY2        (inBean2.getPointY2        ());
						outBean1.setContactX1      (inBean2.getContactX1      ());
						outBean1.setContactY1      (inBean2.getContactY1      ());
						outBean1.setContactX2      (inBean2.getContactX2      ());
						outBean1.setContactY2      (inBean2.getContactY2      ());
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
				
				CONTACT_MURA_DIST_RESULT_Main.cntWList = outList1.size();

				writer1.writeList();
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		if (Flag.TRUE) {
			/*
			 * 시간기록 끝
			 */
			CONTACT_MURA_DIST_RESULT_Main.runMSec = (System.nanoTime() - CONTACT_MURA_DIST_RESULT_Main.runMSec) / 1000 / 1000;
			
			if (Flag.TRUE) Logger.info("##### took time : %d ms", CONTACT_MURA_DIST_RESULT_Main.runMSec);
		}

		if (Flag.TRUE) {
			/*
			 * UPDATE
			 */
			AnalCsvBean bean = new AnalCsvBean();
			
			bean.setType(AnalCsvType.UPDATE);
			bean.setTable("ANAL_CSV");
			bean.setFields(new String[][] {
					{ "LIST_CNT   ", "" + CONTACT_MURA_DIST_RESULT_Main.cntWList },
					{ "E_TIME     ", "SYSDATE" },
					{ "R_MSEC     ", "" + CONTACT_MURA_DIST_RESULT_Main.runMSec },
					{ "CSV_STATUS ", StrUtil.quote("OK") },   // OK, ERROR, SKIP, ETC...
					{ "LOG_MESSAGE", StrUtil.quote("COMPLETE") },  // COMPLETE, EXCEPTION
			});
			bean.setWhere(String.format("JOB_ID = %s AND PROG_NM = %s", StrUtil.quote(jobId), StrUtil.quote(CONTACT_MURA_DIST_RESULT_Main.dsName)));
			
			if (Flag.TRUE) Logger.info("[%s]", bean.getQuery());
			
			bean.sendToOracle();
		}
	}
}
