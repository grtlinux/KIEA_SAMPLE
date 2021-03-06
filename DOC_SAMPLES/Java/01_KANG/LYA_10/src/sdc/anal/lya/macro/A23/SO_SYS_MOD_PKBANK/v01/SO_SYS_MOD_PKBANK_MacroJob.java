package sdc.anal.lya.macro.A23.SO_SYS_MOD_PKBANK.v01;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import kiea.kr.co.tain.base.Flag;
import kiea.proj.sdc.anal.macro.impl.job.AbstractMacroJob;
import kiea.proj.sdc.anal.util.BasePath;
import kiea.proj.sdc.anal.util.FileUtil;
import kiea.proj.sdc.anal.util.StrUtil;
import kiea.proj.sdc.anal.util.log.v02.Logger;

public class SO_SYS_MOD_PKBANK_MacroJob extends AbstractMacroJob
{
	private SO_SYS_MOD_PKBANK_MOSU_CsvIo reader1 = null;
	private SO_SYS_MOD_PKBANK_JASU_CsvIo reader2 = null;
	private SO_SYS_MOD_PKBANK_CsvIo writer1 = null;
	
	private List<SO_SYS_MOD_PKBANK_MOSU_Bean> inList1  = null;
	private List<SO_SYS_MOD_PKBANK_JASU_Bean> inList2  = null;
	private List<SO_SYS_MOD_PKBANK_Bean> outList1 = null;
	
	private String filePath = null;

	private String jobId = null;

	public SO_SYS_MOD_PKBANK_MacroJob(String jobId)
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
				reader1 = new SO_SYS_MOD_PKBANK_MOSU_CsvIo(this.filePath);
				reader2 = new SO_SYS_MOD_PKBANK_JASU_CsvIo(this.filePath);
				writer1 = new SO_SYS_MOD_PKBANK_CsvIo(this.filePath);
				
				inList1  = reader1.getList();
				inList2  = reader2.getList();
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
					Map<String, SO_SYS_MOD_PKBANK_JASU_Bean> map = new LinkedHashMap<String, SO_SYS_MOD_PKBANK_JASU_Bean>();
					for (SO_SYS_MOD_PKBANK_JASU_Bean inBean2 : inList2) {
						map.put(inBean2.getImptEqpId(), inBean2);
					}
					
					for (SO_SYS_MOD_PKBANK_MOSU_Bean inBean1 : inList1) {
						
						SO_SYS_MOD_PKBANK_JASU_Bean inBean2 = map.get(inBean1.getImptEqpId());
						if (inBean2 != null) {
							
							SO_SYS_MOD_PKBANK_Bean outBean1 = new SO_SYS_MOD_PKBANK_Bean();
							
							outBean1.setImptEqpId(inBean1.getImptEqpId());
							outBean1.setMo       (inBean1.getCount    ());
							outBean1.setJa       (inBean2.getCount    ());
							outBean1.setRatio("" + Double.parseDouble(inBean2.getCount()) / Double.parseDouble(inBean1.getCount()));
							
							outList1.add(outBean1);
						}
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
