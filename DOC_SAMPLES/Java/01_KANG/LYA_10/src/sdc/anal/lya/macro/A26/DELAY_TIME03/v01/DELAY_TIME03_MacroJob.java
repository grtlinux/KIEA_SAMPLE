package sdc.anal.lya.macro.A26.DELAY_TIME03.v01;

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

public class DELAY_TIME03_MacroJob extends AbstractMacroJob
{
	private DELAY_TIME02_CsvIo reader1 = null;
	private DELAY_TIME03_CsvIo writer1 = null;
	
	private List<DELAY_TIME02_Bean> inList1  = null;
	private List<DELAY_TIME03_Bean> outList1 = null;
	
	private String filePath = null;

	private String jobId = null;

	public DELAY_TIME03_MacroJob(String jobId)
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
				reader1 = new DELAY_TIME02_CsvIo(this.filePath);
				writer1 = new DELAY_TIME03_CsvIo(this.filePath);
				
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
					String oldClusterId = "";
					String oldStepName  = "";
					List<DELAY_TIME03_Bean> list = new ArrayList<DELAY_TIME03_Bean>();

					for (DELAY_TIME02_Bean inBean1 : inList1) {
						if (oldClusterId.equals(inBean1.getClusterId())
								&& oldStepName.equals(inBean1.getStepName())
								) {
							// KEY가 같으면 list에 계속 누적한다.
							DELAY_TIME03_Bean bean = new DELAY_TIME03_Bean();

							bean.setClusterId  (inBean1.getClusterId  ());
							bean.setStepName   (inBean1.getStepName   ());
							bean.setStepValue  (inBean1.getStepValue  ());
							bean.setGlassId    (inBean1.getGlassId    ());
							bean.setGroup      (inBean1.getGroup      ());
							bean.setOddFlag    (inBean1.getOddFlag    ());
							bean.setTotCnt     (inBean1.getTotCnt     ());
							bean.setTotBCnt    (inBean1.getTotBCnt    ());
							bean.setTotGCnt    (inBean1.getTotGCnt    ());
							bean.setTotIg      (inBean1.getTotIg      ());
							bean.setLCnt       (inBean1.getLCnt       ());
							bean.setLBcnt      (inBean1.getLBcnt      ());
							bean.setLGcnt      (inBean1.getLGcnt      ());
							bean.setLIg        (inBean1.getLIg        ());
							bean.setRCnt       (inBean1.getRCnt       ());
							bean.setRBcnt      (inBean1.getRBcnt      ());
							bean.setRGcnt      (inBean1.getRGcnt      ());
							bean.setRIg        (inBean1.getRIg        ());
							bean.setInfoGain   (inBean1.getInfoGain   ());
							
							list.add(bean);
						} else {
							
							// Print the article of max InfoGain
							printDataMaxIG(list);
							
							// 초기화
							oldClusterId = inBean1.getClusterId();
							oldStepName  = inBean1.getStepName();
							
							list = new ArrayList<DELAY_TIME03_Bean>();
							
							DELAY_TIME03_Bean bean = new DELAY_TIME03_Bean();

							bean.setClusterId  (inBean1.getClusterId  ());
							bean.setStepName   (inBean1.getStepName   ());
							bean.setStepValue  (inBean1.getStepValue  ());
							bean.setGlassId    (inBean1.getGlassId    ());
							bean.setGroup      (inBean1.getGroup      ());
							bean.setOddFlag    (inBean1.getOddFlag    ());
							bean.setTotCnt     (inBean1.getTotCnt     ());
							bean.setTotBCnt    (inBean1.getTotBCnt    ());
							bean.setTotGCnt    (inBean1.getTotGCnt    ());
							bean.setTotIg      (inBean1.getTotIg      ());
							bean.setLCnt       (inBean1.getLCnt       ());
							bean.setLBcnt      (inBean1.getLBcnt      ());
							bean.setLGcnt      (inBean1.getLGcnt      ());
							bean.setLIg        (inBean1.getLIg        ());
							bean.setRCnt       (inBean1.getRCnt       ());
							bean.setRBcnt      (inBean1.getRBcnt      ());
							bean.setRGcnt      (inBean1.getRGcnt      ());
							bean.setRIg        (inBean1.getRIg        ());
							bean.setInfoGain   (inBean1.getInfoGain   ());
							
							list.add(bean);
						}
					}
					
					// 마지막 InfoGain
					printDataMaxIG(list);
				}
				
				if (Flag.TRUE) {
					/*
					 * InfoGain으로 SORT(DESC) 한다.
					 */
					
					Collections.sort(outList1, new Comparator<DELAY_TIME03_Bean>() {
						@Override
						public int compare(DELAY_TIME03_Bean bean1, DELAY_TIME03_Bean bean2) {
							int ret = 0;
							
							double dbl1, dbl2;
							
							if ("".equals(bean1.getInfoGain())) {
								dbl1 = 0.0;
							} else {
								dbl1 = Double.parseDouble(bean1.getInfoGain());
							}
							
							if ("".equals(bean2.getInfoGain())) {
								dbl2 = 0.0;
							} else {
								dbl2 = Double.parseDouble(bean2.getInfoGain());
							}
							
							// InfoGain  DESC
							double dbl = dbl2 - dbl1;
							if (dbl < 0)
								return -1;
							else if (dbl > 0)
								return 1;

							return ret;
						}
					});
				}
				
				if (!Flag.TRUE) {
					/*
					 * 확인출력
					 */
					for (DELAY_TIME03_Bean bean : outList1) {
						System.out.println(bean);
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

	///////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////
	
	private void printDataMaxIG(List<DELAY_TIME03_Bean> list)
	{
		if (Flag.TRUE) {
			if (list.size() < 2)
				return;
		}
		
		if (Flag.TRUE) {
			/*
			 * 자료를 SORT 한다.
			 */
			
			Collections.sort(list, new Comparator<DELAY_TIME03_Bean>() {
				@Override
				public int compare(DELAY_TIME03_Bean bean1, DELAY_TIME03_Bean bean2) {
					int ret = 0;
					
					double dbl1, dbl2;
					
					if ("".equals(bean1.getInfoGain())) {
						dbl1 = 0.0;
					} else {
						dbl1 = Double.parseDouble(bean1.getInfoGain());
					}
					
					if ("".equals(bean2.getInfoGain())) {
						dbl2 = 0.0;
					} else {
						dbl2 = Double.parseDouble(bean2.getInfoGain());
					}
					
					// InfoGain  DESC
					double dbl = dbl2 - dbl1;
					if (dbl < 0)
						return -1;
					else if (dbl > 0)
						return 1;

					return ret;
				}
			});
		}
		
		if (Flag.TRUE) {
			/*
			 * 자료를 출력한다.
			 */
			if (!"".equals(list.get(0).getInfoGain())) {
				outList1.add(list.get(0));
			}
		}
	}

}
