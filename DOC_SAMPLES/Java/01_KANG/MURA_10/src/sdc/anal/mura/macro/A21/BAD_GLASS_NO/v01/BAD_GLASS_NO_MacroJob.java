package sdc.anal.mura.macro.A21.BAD_GLASS_NO.v01;

import java.util.List;

import kiea.kr.co.tain.base.Flag;
import kiea.proj.sdc.anal.macro.impl.job.AbstractMacroJob;
import kiea.proj.sdc.anal.util.BasePath;
import kiea.proj.sdc.anal.util.FileUtil;
import kiea.proj.sdc.anal.util.StrUtil;
import kiea.proj.sdc.anal.util.log.v02.Logger;

public class BAD_GLASS_NO_MacroJob extends AbstractMacroJob
{
	private BAD_GLASS_CsvIo reader1 = null;
	private BAD_GLASS_NO_CsvIo writer1 = null;
	
	private List<BAD_GLASS_Bean> inList1  = null;
	private List<BAD_GLASS_NO_Bean> outList1 = null;
	
	private String filePath = null;

	private String jobId = null;

	public BAD_GLASS_NO_MacroJob(String jobId)
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
				reader1 = new BAD_GLASS_CsvIo(this.filePath);
				writer1 = new BAD_GLASS_NO_CsvIo(this.filePath);
				
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
				 */
				if (Flag.TRUE) {
					/*
					 * 해당자료만 추출한다.
					 */
					int dataNum = 0;
					
					for (BAD_GLASS_Bean inBean1 : inList1) {
						
						dataNum ++;
						
						BAD_GLASS_NO_Bean outBean1 = new BAD_GLASS_NO_Bean();

						outBean1.setDataNum      ("" + dataNum);
						outBean1.setCellId       (inBean1.getCellId       ());
						outBean1.setGlassId      (inBean1.getGlassId      ());
						outBean1.setSiteId       (inBean1.getSiteId       ());
						outBean1.setProdGrpName  (inBean1.getProdGrpName  ());
						outBean1.setProcId       (inBean1.getProcId       ());
						outBean1.setItemId       (inBean1.getItemId       ());
						outBean1.setSubItemId    (inBean1.getSubItemId    ());
						outBean1.setDefectName   (inBean1.getDefectName   ());
						outBean1.setItemName     (inBean1.getItemName     ());
						outBean1.setMeasEqpUnitId(inBean1.getMeasEqpUnitId());
						outBean1.setMuraData     (inBean1.getMuraData     ());
						outBean1.setXValue       (inBean1.getXValue       ());
						outBean1.setYValue       (inBean1.getYValue       ());
						outBean1.setX2Value      (inBean1.getX2Value      ());
						outBean1.setY2Value      (inBean1.getY2Value      ());
						outBean1.setAvgX         (inBean1.getAvgX         ());
						outBean1.setAvgY         (inBean1.getAvgY         ());
						outBean1.setGateLine_1   (inBean1.getGateLine_1   ());
						outBean1.setGateLine_2   (inBean1.getGateLine_2   ());
						outBean1.setDataLine_1   (inBean1.getDataLine_1   ());
						outBean1.setDataLine_2   (inBean1.getDataLine_2   ());
						outBean1.setTarget       (inBean1.getTarget       ());
						outBean1.setCellPosition (inBean1.getCellPosition ());

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
