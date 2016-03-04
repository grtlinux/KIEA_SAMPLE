package sdc.anal.lya.macro.A25.RP_SYS_DEFECT_DETAIL.v01;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import kiea.kr.co.tain.base.Flag;
import kiea.proj.sdc.anal.macro.impl.job.AbstractMacroJob;
import kiea.proj.sdc.anal.util.BasePath;
import kiea.proj.sdc.anal.util.FileUtil;
import kiea.proj.sdc.anal.util.StrUtil;
import kiea.proj.sdc.anal.util.log.v02.Logger;

public class RP_SYS_DEFECT_DETAIL_MacroJob extends AbstractMacroJob
{
	private static final double XDELTA = 10000;
	private static final double YDELTA = 10000;
	
	private SO_SYS_CELLID_LIST2_CsvIo reader1 = null;
	private TFT_INSPCT_HIST_CsvIo reader2 = null;
	private RP_SYS_DEFECT_DETAIL_CsvIo writer1 = null;
	
	private List<SO_SYS_CELLID_LIST2_Bean> inList1  = null;
	private List<TFT_INSPCT_HIST_Bean> inList2  = null;
	private List<RP_SYS_DEFECT_DETAIL_Bean> outList1 = null;
	
	private String filePath = null;

	private String jobId = null;

	public RP_SYS_DEFECT_DETAIL_MacroJob(String jobId)
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
				reader2 = new TFT_INSPCT_HIST_CsvIo(this.filePath);
				writer1 = new RP_SYS_DEFECT_DETAIL_CsvIo(this.filePath);
				
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
				List<SO_SYS_CELLID_LIST2_Bean> lstMatch = new ArrayList<SO_SYS_CELLID_LIST2_Bean>();
				
				if (Flag.TRUE) {
					
					for (SO_SYS_CELLID_LIST2_Bean bean1 : inList1) {
						if ("BAD".equals(bean1.getGroup())) {
							lstMatch.add(bean1);
						}
					}
				}
				
				if (Flag.TRUE) {
					
					for (TFT_INSPCT_HIST_Bean bean2 : inList2) {
						
						String defectSeq2 = bean2.getDefectSeq();
						double x2 = Double.parseDouble(bean2.getXValue());
						double y2 = Double.parseDouble(bean2.getYValue());
						
						for (SO_SYS_CELLID_LIST2_Bean bean1 : lstMatch) {
							
							String defectSeq1 = bean1.getDefectSeq();
							double x1 = Double.parseDouble(bean1.getXValue());
							double y1 = Double.parseDouble(bean1.getYValue());
							
							if (defectSeq1.equals(defectSeq2)
									&& x2 - XDELTA < x1 && x1 < x2 + XDELTA 
									&& y2 - YDELTA < y1 && y1 < y2 + YDELTA) {
								
								RP_SYS_DEFECT_DETAIL_Bean bean = new RP_SYS_DEFECT_DETAIL_Bean();
								
								bean.setClusterId  (bean1.getClusterId  ());
								bean.setLineCode   (bean2.getLineCode   ());
								bean.setAreaCode   (bean2.getAreaCode   ());
								bean.setSubAreaCode(bean2.getSubAreaCode());
								bean.setEqpType    (bean2.getEqpType    ());
								bean.setStepId     (bean2.getStepId().substring(0, 1) + "__" + bean2.getStepId().substring(3));
								bean.setGlassId    (bean2.getGlassId    ());
								bean.setCellId     (bean2.getCellId     ());
								bean.setDefectSeq  (bean2.getDefectSeq  ());
								bean.setXValue     (bean2.getXValue     ());
								bean.setYValue     (bean2.getYValue     ());
								
								outList1.add(bean);
								break;
							}
						}
					}
					
					if (!Flag.TRUE) {
						/*
						 * 확인출력
						 */
						for (RP_SYS_DEFECT_DETAIL_Bean bean2 : outList1) {
							System.out.println(bean2);
						}
					}
				}
				
				if (Flag.TRUE) {
					/*
					 * SORT
					 */
					Collections.sort(outList1, new Comparator<RP_SYS_DEFECT_DETAIL_Bean>() {
						@Override
						public int compare(RP_SYS_DEFECT_DETAIL_Bean bean1, RP_SYS_DEFECT_DETAIL_Bean bean2) {
							int ret = 0;
							
							// 1. CLUSTER_ID
							ret = bean1.getClusterId().compareTo(bean2.getClusterId());
							if (ret != 0) return ret;

							// 2. SubAreaCode
							ret = bean1.getSubAreaCode().compareTo(bean2.getSubAreaCode());
							if (ret != 0) return ret;
							
							// 3. StepId
							ret = bean1.getStepId().compareTo(bean2.getStepId());
							if (ret != 0) return ret;
							
							// 4. GlassId
							ret = bean1.getGlassId().compareTo(bean2.getGlassId());
							if (ret != 0) return ret;
									
							return ret;
						}
					});
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
