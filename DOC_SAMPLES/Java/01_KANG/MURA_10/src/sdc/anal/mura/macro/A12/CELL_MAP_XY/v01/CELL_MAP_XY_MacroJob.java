package sdc.anal.mura.macro.A12.CELL_MAP_XY.v01;

import java.util.List;

import kiea.kr.co.tain.base.Flag;
import kiea.proj.sdc.anal.macro.impl.job.AbstractMacroJob;
import kiea.proj.sdc.anal.util.BasePath;
import kiea.proj.sdc.anal.util.FileUtil;
import kiea.proj.sdc.anal.util.StrUtil;
import kiea.proj.sdc.anal.util.log.v02.Logger;

public class CELL_MAP_XY_MacroJob extends AbstractMacroJob
{
	private CELL_MAP_CsvIo     reader1 = null;
	private CELL_MAP_XY_CsvIo  writer1 = null;
	
	private List<CELL_MAP_Bean>     inList1  = null;
	private List<CELL_MAP_XY_Bean>  outList1 = null;
	
	private String filePath = null;

	private String jobId = null;

	public CELL_MAP_XY_MacroJob(String jobId)
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
				reader1 = new CELL_MAP_CsvIo    (this.filePath);
				writer1 = new CELL_MAP_XY_CsvIo (this.filePath);
				
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
					int cellNo = 0;
					for (CELL_MAP_Bean inBean1 : inList1) {
						
						CELL_MAP_XY_Bean outBean1 = new CELL_MAP_XY_Bean();
						
						cellNo ++;
						
						outBean1.setCellNo   ("" + cellNo);
						outBean1.setProcId   (inBean1.getProcId   ());
						outBean1.setCellLocId(inBean1.getCellLocId());
						outBean1.setPointX   (inBean1.getPointX   ());
						outBean1.setPointY   (inBean1.getPointY   ());
						outBean1.setWidth    (inBean1.getWidth    ());
						outBean1.setHeight   (inBean1.getHeight   ());
						outBean1.setPointX2  ("" + (Integer.parseInt(inBean1.getPointX()) + Integer.parseInt(inBean1.getWidth ())));
						outBean1.setPointY2  ("" + (Integer.parseInt(inBean1.getPointY()) + Integer.parseInt(inBean1.getHeight())));
						
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
