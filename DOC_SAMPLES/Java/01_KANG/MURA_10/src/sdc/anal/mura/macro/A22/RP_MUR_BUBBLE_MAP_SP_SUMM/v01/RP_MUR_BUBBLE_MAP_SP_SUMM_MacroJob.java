package sdc.anal.mura.macro.A22.RP_MUR_BUBBLE_MAP_SP_SUMM.v01;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import kiea.kr.co.tain.base.Flag;
import kiea.proj.sdc.anal.macro.impl.job.AbstractMacroJob;
import kiea.proj.sdc.anal.util.BasePath;
import kiea.proj.sdc.anal.util.FileUtil;
import kiea.proj.sdc.anal.util.StrUtil;
import kiea.proj.sdc.anal.util.log.v02.Logger;

public class RP_MUR_BUBBLE_MAP_SP_SUMM_MacroJob extends AbstractMacroJob
{
	private BUBBLE_CLUSTER_CELLLOC_CsvIo reader1 = null;
	private RP_MUR_BUBBLE_MAP_SP_SUMM_CsvIo writer1 = null;
	
	private List<BUBBLE_CLUSTER_CELLLOC_Bean> inList1 = null;
	private List<RP_MUR_BUBBLE_MAP_SP_SUMM_Bean> outList1  = null;
	
	private String filePath = null;

	private String jobId = null;

	public RP_MUR_BUBBLE_MAP_SP_SUMM_MacroJob(String jobId)
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
				reader1 = new BUBBLE_CLUSTER_CELLLOC_CsvIo(this.filePath);
				writer1 = new RP_MUR_BUBBLE_MAP_SP_SUMM_CsvIo(this.filePath);
				
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
					 * SORT
					 */
					Collections.sort(inList1, new Comparator<BUBBLE_CLUSTER_CELLLOC_Bean>() {
						@Override
						public int compare(BUBBLE_CLUSTER_CELLLOC_Bean bean1, BUBBLE_CLUSTER_CELLLOC_Bean bean2) {
							int ret = Integer.parseInt(bean1.getClusterId()) - Integer.parseInt(bean2.getClusterId());
							if (ret == 0) {
								ret = bean1.getCellLocId().compareTo(bean2.getCellLocId());
							}
							return ret;
						}
					});
				}
				
				if (Flag.TRUE) {
					/*
					 * 해당자료만 추출한다.
					 */
					for (BUBBLE_CLUSTER_CELLLOC_Bean inBean1 : inList1) {
						
						RP_MUR_BUBBLE_MAP_SP_SUMM_Bean outBean1 = new RP_MUR_BUBBLE_MAP_SP_SUMM_Bean();

						outBean1.setClusterId(inBean1.getClusterId());
						outBean1.setCellLocId(inBean1.getCellLocId());
						outBean1.setPointX   (inBean1.getPointX   ());
						outBean1.setPointY   (inBean1.getPointY   ());
						outBean1.setAvgMura  (inBean1.getAvgMura  ());

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
