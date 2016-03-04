package sdc.anal.mura.macro.T01.FDC.step03.v01;

import java.util.List;
import java.util.Random;

import kiea.kr.co.tain.base.Flag;
import kiea.proj.sdc.anal.macro.impl.job.AbstractMacroJob;
import kiea.proj.sdc.anal.util.BasePath;
import kiea.proj.sdc.anal.util.FileUtil;
import kiea.proj.sdc.anal.util.StrUtil;
import kiea.proj.sdc.anal.util.log.v02.Logger;

public class FDC_step03_MacroJob extends AbstractMacroJob
{
	private FDC_step02_CsvIo reader1 = null;
	private FDC_step03_CsvIo writer1 = null;
	
	private List<FDC_step02_Bean> inList1  = null;
	private List<FDC_step03_Bean> outList1 = null;
	
	private String filePath = null;

	private String jobId = null;

	public FDC_step03_MacroJob(String jobId)
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
				reader1 = new FDC_step02_CsvIo(this.filePath);
				writer1 = new FDC_step03_CsvIo(this.filePath);
				
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
					for (FDC_step02_Bean inBean1 : inList1) {
						
						FDC_step03_Bean outBean1 = new FDC_step03_Bean();

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
