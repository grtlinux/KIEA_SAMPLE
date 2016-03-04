package sdc.anal.mura.macro.T01.FDC.step04.v01;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import kiea.kr.co.tain.base.Flag;
import kiea.proj.sdc.anal.macro.impl.job.AbstractMacroJob;
import kiea.proj.sdc.anal.util.BasePath;
import kiea.proj.sdc.anal.util.FileUtil;
import kiea.proj.sdc.anal.util.StrUtil;
import kiea.proj.sdc.anal.util.log.v02.Logger;

public class FDC_step04_MacroJob extends AbstractMacroJob
{
	private FDC_step03_CsvIo reader1 = null;
	private FDC_step04_CsvIo writer1 = null;
	
	private List<FDC_step03_Bean> inList1  = null;
	private List<FDC_step04_Bean> outList1 = null;
	
	private String filePath = null;

	private String jobId = null;

	public FDC_step04_MacroJob(String jobId)
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
				reader1 = new FDC_step03_CsvIo(this.filePath);
				writer1 = new FDC_step04_CsvIo(this.filePath);
				
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
					String oldClusterId  = "";
					String oldPart       = "";
					String oldEqpId      = "";
					String oldSensorName = "";
					List<FDC_step04_Bean> list = new ArrayList<FDC_step04_Bean>();

					for (FDC_step03_Bean inBean1 : inList1) {
						if (oldClusterId.equals(inBean1.getClusterId())
								&& oldPart.equals(inBean1.getPart())
								&& oldEqpId.equals(inBean1.getEqpId())
								&& oldSensorName.equals(inBean1.getSensorName())
								) {
							// KEY가 같으면 list에 계속 누적한다.
							FDC_step04_Bean bean = new FDC_step04_Bean();

							bean.setClusterId  (inBean1.getClusterId  ());
							bean.setPart       (inBean1.getPart       ());
							bean.setEqpId      (inBean1.getEqpId      ());
							bean.setSensorName (inBean1.getSensorName ());
							bean.setParamValue (inBean1.getParamValue ());
							bean.setGlassId    (inBean1.getGlassId    ());
							bean.setGroupId    (inBean1.getGroupId    ());
							
							list.add(bean);
						} else {
							// InfoGain
							infoGain(list);
							
							// 기록한다.
							for (FDC_step04_Bean bean : list) {
								outList1.add(bean);
							}
							
							// 초기화
							oldClusterId  = inBean1.getClusterId();
							oldPart       = inBean1.getPart();
							oldEqpId      = inBean1.getEqpId();
							oldSensorName = inBean1.getSensorName();
							
							list = new ArrayList<FDC_step04_Bean>();
							
							FDC_step04_Bean bean = new FDC_step04_Bean();

							bean.setClusterId  (inBean1.getClusterId  ());
							bean.setPart       (inBean1.getPart       ());
							bean.setEqpId      (inBean1.getEqpId      ());
							bean.setSensorName (inBean1.getSensorName ());
							bean.setParamValue (inBean1.getParamValue ());
							bean.setGlassId    (inBean1.getGlassId    ());
							bean.setGroupId    (inBean1.getGroupId    ());
							
							list.add(bean);
						}
					}
					
					// 마지막 InfoGain
					infoGain(list);
					
					// 마지막 기록한다.
					for (FDC_step04_Bean bean : list) {
						outList1.add(bean);
					}
				}
				
				if (!Flag.TRUE) {
					/*
					 * 해당자료만 추출한다.
					 */
					for (FDC_step03_Bean inBean1 : inList1) {
						
						FDC_step04_Bean outBean1 = new FDC_step04_Bean();

						outBean1.setClusterId  (inBean1.getClusterId  ());
						outBean1.setPart       (inBean1.getPart       ());
						outBean1.setEqpId      (inBean1.getEqpId      ());
						outBean1.setSensorName (inBean1.getSensorName ());
						outBean1.setParamValue (inBean1.getParamValue ());
						outBean1.setGlassId    (inBean1.getGlassId    ());
						outBean1.setGroupId    (inBean1.getGroupId    ());

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
	
	///////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////
	
	private double getLog2(double value)
	{
		return (Math.log(value) / Math.log(2));
	}
	
	private double getInfoGain(double m, double n)
	{
		double ret = 0;
		
		if (Flag.TRUE) {
			if (m == 0 || n == 0)
				return 0;
			
			double t = m + n;
			
			ret = - m / t * getLog2(m / t) - n / t * getLog2(n / t);
		}

		return ret;
	}
	
	private void infoGain(List<FDC_step04_Bean> list)
	{
		if (Flag.TRUE) {
			
			double cntTot = 0;
			double cntTotB = 0;
			double cntTotG = 0;
			double igTot = 0;
			
			if (Flag.TRUE) {
				/*
				 * 이상값을 골라낸다.
				 * 알고리즘이 필요
				 */
				for (int i=0; i < list.size(); i++) {
					// 이상치면 SKIP
					list.get(i).setOddFlag("");
					
					list.get(i).setTotCnt ("");
					list.get(i).setTotBCnt("");
					list.get(i).setTotGCnt("");
					list.get(i).setTotIg  ("");
					
					list.get(i).setLCnt   ("");
					list.get(i).setLBcnt  ("");
					list.get(i).setLGcnt  ("");
					list.get(i).setLIg    ("");

					list.get(i).setRCnt   ("");
					list.get(i).setRBcnt  ("");
					list.get(i).setRGcnt  ("");
					list.get(i).setRIg    ("");
					
					list.get(i).setInfoGain("");
				}
			}
			
			if (Flag.TRUE) {
				/*
				 * cntTot, cntTotB, cntTotG, TotIG 값을 구한다.
				 */
				for (int i=0; i < list.size(); i++) {
					// 이상치면 SKIP
					
					cntTot ++;
					
					if ("B".equals(list.get(i).getGroupId())) {
						cntTotB ++;
					} else {
						cntTotG ++;
					}
				}
				
				igTot = getInfoGain(cntTotB, cntTotG);
			}
			
			if (Flag.TRUE) {
				/*
				 *  값이 바뀐 지점을 BreakPoint로 한다.
				 */
				double cntLB = 0;
				double cntLG = 0;
				double igL = 0;
				double cntRB = cntTotB;
				double cntRG = cntTotG;
				double igR = 0;
				double infoGain = 0;

				String oldGroup = "";
				
				for (int i=0; i < list.size(); i++) {
					// 이상치면 SKIP
					
					if ("B".equals(list.get(i).getGroupId())) {
						cntLB ++;
						cntRB --;
					} else {
						cntLG ++;
						cntRG --;
					}
					
					if ("".equals(oldGroup)) {
						// 첫번째 값
						oldGroup = list.get(i).getGroupId();
					} else {
						// 두번째 이후 값
						if (!oldGroup.equals(list.get(i).getGroupId())) {
							// BreakPoint
							igL = getInfoGain(cntLB, cntLG) * (cntLB + cntLG) / cntTot;
							igR = getInfoGain(cntRB, cntRG) * (cntRB + cntRG) / cntTot;
							infoGain = igTot - (igL + igR);
							if (infoGain < 0)
								infoGain = 0;
							
							list.get(i).setTotCnt  ("" + cntTot);
							list.get(i).setTotBCnt ("" + cntTotB);
							list.get(i).setTotGCnt ("" + cntTotG);
							list.get(i).setTotIg   ("" + igTot);
							list.get(i).setLCnt    ("" + (cntLB + cntLG));
							list.get(i).setLBcnt   ("" + cntLB);
							list.get(i).setLGcnt   ("" + cntLG);
							list.get(i).setLIg     ("" + igL);
							list.get(i).setRCnt    ("" + (cntRB + cntRG));
							list.get(i).setRBcnt   ("" + cntRB);
							list.get(i).setRGcnt   ("" + cntRG);
							list.get(i).setRIg     ("" + igR);
							list.get(i).setInfoGain("" + infoGain);
							
							oldGroup = list.get(i).getGroupId();
						}
					}
				}
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
