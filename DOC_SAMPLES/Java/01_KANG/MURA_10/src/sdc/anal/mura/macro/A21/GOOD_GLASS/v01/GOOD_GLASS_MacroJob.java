package sdc.anal.mura.macro.A21.GOOD_GLASS.v01;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import kiea.kr.co.tain.base.Flag;
import kiea.proj.sdc.anal.macro.impl.job.AbstractMacroJob;
import kiea.proj.sdc.anal.util.BasePath;
import kiea.proj.sdc.anal.util.FileUtil;
import kiea.proj.sdc.anal.util.StrUtil;
import kiea.proj.sdc.anal.util.log.v02.Logger;

public class GOOD_GLASS_MacroJob extends AbstractMacroJob
{
	private GOOD_GLASS_ORG_CsvIo reader1 = null;
	private BAD_GLASS_CsvIo reader2 = null;
	private GOOD_GLASS_CsvIo writer1 = null;
	
	private List<GOOD_GLASS_ORG_Bean> inList1  = null;
	private List<BAD_GLASS_Bean> inList2  = null;
	private List<GOOD_GLASS_Bean> outList1 = null;
	
	private String filePath = null;

	private String jobId = null;

	public GOOD_GLASS_MacroJob(String jobId)
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
				reader1 = new GOOD_GLASS_ORG_CsvIo(this.filePath);
				reader2 = new BAD_GLASS_CsvIo(this.filePath);
				writer1 = new GOOD_GLASS_CsvIo(this.filePath);
				
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
				Map<String, BAD_GLASS_Bean> mapBad = new LinkedHashMap<String, BAD_GLASS_Bean>();
				
				/*
				 * MURA_DESC
				 */
				if (Flag.TRUE) {
					for (BAD_GLASS_Bean inBean2 : inList2) {
						mapBad.put(inBean2.getGlassId(), inBean2);
					}
				}
				
				/*
				 * Job
				 */
				if (Flag.TRUE) {
					/*
					 * 해당자료만 추출한다.
					 */
					for (GOOD_GLASS_ORG_Bean inBean1 : inList1) {
						
						if (mapBad.get(inBean1.getGlassId()) != null)
							continue;
						
						GOOD_GLASS_Bean outBean1 = new GOOD_GLASS_Bean();

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
