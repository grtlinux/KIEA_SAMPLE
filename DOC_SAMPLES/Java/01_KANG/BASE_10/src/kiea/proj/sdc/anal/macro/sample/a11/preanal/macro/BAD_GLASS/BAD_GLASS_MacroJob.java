package kiea.proj.sdc.anal.macro.sample.a11.preanal.macro.BAD_GLASS;

import java.util.List;

import kiea.kr.co.tain.base.Flag;
import kiea.kr.co.tain.util.DateTime;
import kiea.kr.co.tain.util.Print;
import kiea.proj.sdc.anal.macro.impl.job.AbstractMacroJob;
import kiea.proj.sdc.anal.util.BasePath;
import kiea.proj.sdc.anal.util.FileUtil;
import kiea.proj.sdc.anal.util.log.v02.Logger;

@SuppressWarnings("unused")
public class BAD_GLASS_MacroJob extends AbstractMacroJob
{
	private BAD_GLASS_ORG_CsvIo reader1 = null;
	private BAD_GLASS_CsvIo writer1 = null;
	
	private List<BAD_GLASS_ORG_Bean> inList1  = null;
	private List<BAD_GLASS_Bean> outList1 = null;
	
	private String strFromDateTime    = null;
	private String strToDateTime      = null;
	private String strLine            = null;
	private String strAreaCode        = null;
	private String strUserGroupCode   = null;
	private String strDefectGroupCode = null;
	
	private String filePath = null;

	private String jobKeyPath = null;
	private String dsName = null;

	public BAD_GLASS_MacroJob(String jobKeyPath, String dsName)
	{
		this.jobKeyPath = jobKeyPath;
		this.dsName = dsName;
		
		this.filePath = FileUtil.makeDataDir(BasePath.getInstance().getDataPath(), DateTime.getDate(2), this.jobKeyPath);
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
				reader1 = new BAD_GLASS_ORG_CsvIo(this.filePath);
				writer1 = new BAD_GLASS_CsvIo(this.filePath);
				
				inList1  = reader1.getList();
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
				String defectWord = null;
				
				if (Flag.TRUE) {
					/*
					 * 가로줄 건수, 세로줄 건수를 구한다.
					 * DefectName에 포함된 단어
					 *    가로줄, UHT1
					 *    세로줄, UVT1
					 */
					String defectName;
					String[] word = new String[] { "가로줄", "UHT1", "세로줄", "UVT1" };
					int[] count = new int[] { 0, 0, 0, 0, };
					
					for (BAD_GLASS_ORG_Bean inBean1 : inList1) {
						defectName = inBean1.getDefectName();

						for (int i=0; i < word.length; i++) {
							if (defectName.indexOf(word[i]) >= 0) {
								count[i] ++;
								break;
							}
						}
					}
					
					int idxMax = 0;
					for (int i=1; i < count.length; i++) {
						if (count[idxMax] < count[i]) {
							idxMax = i;
						}
					}
					
					defectWord = word[idxMax];
					
					if (!Flag.TRUE) {
						/*
						 * 출력
						 */
						for (int i=0; i < word.length; i++) {
							Print.println("%d) [%s]-[%d]", i, word[i], count[i]);
						}
						
						Print.println("defectWord = [%s] idxMax=%d", defectWord, idxMax);
					}
				}
				
				if (Flag.TRUE) {
					/*
					 * 해당자료만 추출한다.
					 */
					for (BAD_GLASS_ORG_Bean inBean1 : inList1) {
						
						if (inBean1.getDefectName().indexOf(defectWord) < 0)
							continue;
						
						BAD_GLASS_Bean outBean1 = new BAD_GLASS_Bean();

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
