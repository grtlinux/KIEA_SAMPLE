package sdc.anal.lya.macro.A24.EQP_FDC_SUM02.v01;

import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import kiea.kr.co.tain.base.Flag;
import kiea.kr.co.tain.util.Print;
import kiea.proj.sdc.anal.macro.impl.job.AbstractMacroJob;
import kiea.proj.sdc.anal.util.BasePath;
import kiea.proj.sdc.anal.util.FileUtil;
import kiea.proj.sdc.anal.util.StrUtil;
import kiea.proj.sdc.anal.util.log.v02.Logger;

public class EQP_FDC_SUM02_MacroJob extends AbstractMacroJob
{
	private EQP_FDC_SUM01_CsvIo reader1 = null;
	private SO_SYS_CELLID_LIST2_CsvIo reader2 = null;
	private EQP_FDC_SUM02_CsvIo writer1 = null;
	
	private List<EQP_FDC_SUM01_Bean> inList1  = null;
	private List<SO_SYS_CELLID_LIST2_Bean> inList2  = null;
	private List<EQP_FDC_SUM02_Bean> outList1 = null;
	
	private String filePath = null;

	private String jobId = null;

	public EQP_FDC_SUM02_MacroJob(String jobId)
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
				reader1 = new EQP_FDC_SUM01_CsvIo(this.filePath);
				reader2 = new SO_SYS_CELLID_LIST2_CsvIo(this.filePath);
				writer1 = new EQP_FDC_SUM02_CsvIo(this.filePath);
				
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
				Map<String, SO_SYS_CELLID_LIST2_Bean> mapGlass = new HashMap<String, SO_SYS_CELLID_LIST2_Bean>();
				
				if (Flag.TRUE) {
					/*
					 * GLASS의 ClusterId, Group 정보를 얻는다.
					 */
					for (SO_SYS_CELLID_LIST2_Bean bean : inList2) {
						mapGlass.put(bean.getGlassId(), bean);
					}
				}
				
				if (Flag.TRUE) {
					/*
					 * 해당자료만 추출한다.
					 */
					for (EQP_FDC_SUM01_Bean inBean1 : inList1) {
						
						EQP_FDC_SUM02_Bean outBean1 = new EQP_FDC_SUM02_Bean();

						outBean1.setLine       (inBean1.getLine       ());
						outBean1.setPart       (inBean1.getPart       ());
						outBean1.setEqpModel   (inBean1.getEqpModel   ());
						outBean1.setEqpId      (inBean1.getEqpId      ());
						outBean1.setModuleName (inBean1.getModuleName ());
						outBean1.setProcId     (inBean1.getProcId     ());
						outBean1.setProdId     (inBean1.getProdId     ());
						outBean1.setPpId       (inBean1.getPpId       ());
						outBean1.setGlassId    (inBean1.getGlassId    ());
						outBean1.setProcessStep(inBean1.getProcessStep());
						outBean1.setBeginStep  (inBean1.getBeginStep  ());
						outBean1.setSensorName (inBean1.getSensorName ());
						outBean1.setParam      (inBean1.getParam      ());
						outBean1.setParamValue (inBean1.getParamValue ());
						outBean1.setUsl        (inBean1.getUsl        ());
						outBean1.setLsl        (inBean1.getLsl        ());
						outBean1.setBeginTime  (inBean1.getBeginTime  ());

						SO_SYS_CELLID_LIST2_Bean bean = mapGlass.get(inBean1.getGlassId());
						if (bean != null) {
							outBean1.setClusterId(bean.getClusterId());
							outBean1.setGroupId  (bean.getGroup    ());
						} else {
							outBean1.setClusterId("");
							outBean1.setGroupId  ("");
						}
						
						if (!Flag.TRUE) Print.println("[%s] [%s] [%s] [%s] [%s]", inBean1.getGlassId(), bean.getClusterId(), bean.getGroup(), outBean1.getClusterId(), outBean1.getGroupId());

						outList1.add(outBean1);
					}
				}
				
				if (Flag.TRUE) {
					/*
					 * 자료를 SORT 한다.
					 */
					
					Collections.sort(outList1, new Comparator<EQP_FDC_SUM02_Bean>() {
						@Override
						public int compare(EQP_FDC_SUM02_Bean bean1, EQP_FDC_SUM02_Bean bean2) {
							int ret = 0;
							
							// 1. CLUSTER_ID
							ret = bean1.getClusterId().compareTo(bean2.getClusterId());
							if (ret != 0) return ret;

							// 2. PART
							ret = bean1.getPart().compareTo(bean2.getPart());
							if (ret != 0) return ret;
							
							// 3. EQP
							ret = bean1.getEqpId().compareTo(bean2.getEqpId());
							if (ret != 0) return ret;
							
							// 4. SENSOR_NAME
							ret = bean1.getSensorName().compareTo(bean2.getSensorName());
							if (ret != 0) return ret;
							
							// 5. PARAM_VALUE
							double dbl = Double.parseDouble(bean1.getParamValue()) - Double.parseDouble(bean2.getParamValue());
							if (dbl < 0)
								return -1;
							else if (dbl > 0)
								return 1;
							
							// 6. GROUP_ID
							ret = bean1.getGroupId().compareTo(bean2.getGroupId());
									
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
	
	///////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////
	
	private static void test01()
	{
		if (Flag.TRUE) {
			Random random;
			// random = new Random(new Date().getTime());  // random seed
			random = new Random();
			
			for (int i=0; i < 10; i++) {
				if (Flag.TRUE) System.out.println("[" + random.nextInt(2) + "]");
			}
		}
	}
	
	public static void main(String[] args)
	{
		if (Flag.TRUE) test01();
	}
}
