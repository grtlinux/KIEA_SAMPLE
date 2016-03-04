package kiea.proj.sdc.anal.macro.sample.a11.wip.macro.RP_MUR_WIP_SUMM.v01;

import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import kiea.kr.co.tain.base.Flag;
import kiea.kr.co.tain.util.DateTime;
import kiea.proj.sdc.anal.macro.impl.job.AbstractMacroJob;
import kiea.proj.sdc.anal.util.BasePath;
import kiea.proj.sdc.anal.util.FileUtil;
import kiea.proj.sdc.anal.util.log.v02.Logger;

@SuppressWarnings("unused")
public class RP_MUR_WIP_SUMM_MacroJob extends AbstractMacroJob
{
	private SO_SYS_GLASS_LIST2_CsvIo reader1 = null;
	private WIP_EQP_SMMRY_CsvIo reader2 = null;
	private GLASS_CELL_CsvIo reader3 = null;
	private GROUP_CNT_CsvIo reader4 = null;
	private EQP_CNT_CsvIo reader5 = null;
	private RP_MUR_WIP_SUMM_CsvIo writer1 = null;
	
	private List<SO_SYS_GLASS_LIST2_Bean> inList1  = null;
	private List<WIP_EQP_SMMRY_Bean> inList2  = null;
	private List<GLASS_CELL_Bean> inList3  = null;
	private List<GROUP_CNT_Bean> inList4  = null;
	private List<EQP_CNT_Bean> inList5  = null;
	private List<RP_MUR_WIP_SUMM_Bean> outList1 = null;
	
	private String strFromDateTime    = null;
	private String strToDateTime      = null;
	private String strLine            = null;
	private String strAreaCode        = null;
	private String strUserGroupCode   = null;
	private String strDefectGroupCode = null;
	
	private String filePath = null;

	private String jobKeyPath = null;
	private String dsName = null;

	public RP_MUR_WIP_SUMM_MacroJob(String jobKeyPath, String dsName)
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
				reader1 = new SO_SYS_GLASS_LIST2_CsvIo(this.filePath);
				reader2 = new WIP_EQP_SMMRY_CsvIo(this.filePath);
				reader3 = new GLASS_CELL_CsvIo(this.filePath);
				reader4 = new GROUP_CNT_CsvIo(this.filePath);
				reader5 = new EQP_CNT_CsvIo(this.filePath);
				writer1 = new RP_MUR_WIP_SUMM_CsvIo(this.filePath);
				
				inList1  = reader1.getList();
				inList2  = reader2.getList();
				inList3  = reader3.getList();
				inList4  = reader4.getList();
				inList5  = reader5.getList();
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
				Map<String, SO_SYS_GLASS_LIST2_Bean> listMap = new LinkedHashMap<String, SO_SYS_GLASS_LIST2_Bean>();
				Map<String, GROUP_CNT_Bean> groupCntMap = new LinkedHashMap<String, GROUP_CNT_Bean>();
				Map<String, EQP_CNT_Bean> eqpCntMap = new LinkedHashMap<String, EQP_CNT_Bean>();
				
				
				if (Flag.TRUE) {
					for (SO_SYS_GLASS_LIST2_Bean inBean1 : inList1) {
						
						String key = inBean1.getGlassId();
						listMap.put(key, inBean1);
					}
					
					if (!Flag.TRUE) {
						/*
						 * 확인
						 */
						for (Map.Entry<String, SO_SYS_GLASS_LIST2_Bean> entry : listMap.entrySet()) {
							System.out.println("[" + entry.getKey() + "] => [" + entry.getValue() + "]");
						}
					}
				}
				
				if (Flag.TRUE) {
					for (WIP_EQP_SMMRY_Bean inBean2 : inList2) {
						
						String key = inBean2.getGlassCellId();
						SO_SYS_GLASS_LIST2_Bean inBean1 = listMap.get(key);
						
						GLASS_CELL_Bean inBean3 = new GLASS_CELL_Bean();
						
						inBean3.setLineCode            (inBean2.getLineCode            ());
						inBean3.setUserGroupCode       (inBean2.getUserGroupCode       ());
						inBean3.setProcessId           (inBean2.getProcessId           ());
						inBean3.setProductId           (inBean2.getProductId           ());
						inBean3.setProductType         (inBean2.getProductType         ());
						inBean3.setAreaCode            (inBean2.getAreaCode            ());
						inBean3.setSubAreaCode         (inBean2.getSubAreaCode         ());
						inBean3.setStepId              (inBean2.getStepId              ());
						inBean3.setEqpId               (inBean2.getEqpId               ());
						inBean3.setGlassCellId         (inBean2.getGlassCellId         ());
						inBean3.setCellId              (inBean2.getCellId              ());
						inBean3.setGlassDefectCodeRatio(inBean2.getGlassDefectCodeRatio());
						inBean3.setBaseCellNum         (inBean2.getBaseCellNum         ());
						inBean3.setDefectCellNum       (inBean2.getDefectCellNum       ());
						inBean3.setTkInDate            (inBean2.getTkInDate            ());
						inBean3.setTkOutDate           (inBean2.getTkOutDate           ());
						inBean3.setMatchLotType        (inBean2.getMatchLotType        ());
						inBean3.setBinGradeCd          (inBean2.getBinGradeCd          ());
						inBean3.setDefectGroupCode     (inBean2.getDefectGroupCode     ());
						inBean3.setDecisionCode        (inBean2.getDecisionCode        ());
						inBean3.setDataValue           (inBean2.getDataValue           ());
						inBean3.setPreTrkOut           (inBean2.getPreTrkOut           ());
						inBean3.setTarget              (inBean1.getTarget              ());
						inBean3.setGroupNum            (inBean1.getGroupNum            ());
						
						inList3.add(inBean3);
					}
					
					if (!Flag.TRUE) {
						/*
						 * 확인
						 */
						for (GLASS_CELL_Bean inBean3 : inList3) {
							System.out.println(inBean3);
						}
					}
				}
				
				if (Flag.TRUE) {
					for (GLASS_CELL_Bean inBean3 : inList3) {
						
						String groupCntKey = inBean3.getGroupNum();
						String eqpCntKey = inBean3.getGroupNum() + ":" + inBean3.getStepId() + ":" + inBean3.getEqpId();
						
						if (Flag.TRUE) {
							GROUP_CNT_Bean inBean4 = groupCntMap.get(groupCntKey);
							if (inBean4 == null) {
								inBean4 = new GROUP_CNT_Bean();
								inBean4.setGroupNum(inBean3.getGroupNum());
								inBean4.setGroupCnt("" + 0);
								
								groupCntMap.put(groupCntKey, inBean4);
							}
							inBean4.addCnt();
						}
						
						if (Flag.TRUE) {
							EQP_CNT_Bean inBean5 = eqpCntMap.get(eqpCntKey);
							if (inBean5 == null) {
								inBean5 = new EQP_CNT_Bean();
								inBean5.setGroupNum(inBean3.getGroupNum());
								inBean5.setStepId(inBean3.getStepId());
								inBean5.setEqpId(inBean3.getEqpId());
								inBean5.setGoodCnt("" + 0);
								inBean5.setBadCnt("" + 0);
								
								eqpCntMap.put(eqpCntKey, inBean5);
							}
							
							if ("1".equals(inBean3.getTarget())) {
								inBean5.addBadCnt();
							} else {
								inBean5.addGoodCnt();
							}
						}
					}
					
					if (!Flag.TRUE) {
						/*
						 * 확인
						 */
						for (Map.Entry<String, GROUP_CNT_Bean> entry : groupCntMap.entrySet()) {
							System.out.println(">" + entry.getValue());
						}
						
						System.out.println("--------------------------------------------");
						
						for (Map.Entry<String, EQP_CNT_Bean> entry : eqpCntMap.entrySet()) {
							System.out.println(">" + entry.getValue());
						}
					}
				}
				
				if (Flag.TRUE) {
					/*
					 * 자료처리
					 */
					for (Map.Entry<String, EQP_CNT_Bean> entry : eqpCntMap.entrySet()) {
						
						EQP_CNT_Bean inBean5 = entry.getValue();
						if (!Flag.TRUE) System.out.println(">" + inBean5);
						
						double badCnt = Integer.parseInt(inBean5.getBadCnt());
						double goodCnt = Integer.parseInt(inBean5.getGoodCnt());
						int groupCnt = Integer.parseInt(groupCntMap.get(inBean5.getGroupNum()).getGroupCnt());
						
						double badRatio = badCnt / groupCnt;
						double goodRatio = goodCnt / groupCnt;
						double BGRatio = badRatio - goodRatio;
						
						
						RP_MUR_WIP_SUMM_Bean outBean1 = new RP_MUR_WIP_SUMM_Bean();
						
						outBean1.setGroupNum (inBean5.getGroupNum ());
						outBean1.setStepId   (inBean5.getStepId   ());
						outBean1.setEqpId    (inBean5.getEqpId    ());
						outBean1.setBadCnt   (inBean5.getBadCnt   ());
						outBean1.setGoodCnt  (inBean5.getGoodCnt  ());
						
						outBean1.setGroupCnt ("" + groupCnt );
						outBean1.setBadRaio  ("" + badRatio );
						outBean1.setGoodRatio("" + goodRatio);
						outBean1.setBGRatio  ("" + BGRatio  );
						
						outList1.add(outBean1);
					}
				}
				
				if (Flag.TRUE) {
					/*
					 * SORT
					 */
					Collections.sort(outList1, new Comparator<RP_MUR_WIP_SUMM_Bean>() {
						@Override
						public int compare(RP_MUR_WIP_SUMM_Bean bean1, RP_MUR_WIP_SUMM_Bean bean2) {
							int ret;
							
							ret = bean1.getGroupNum().compareTo(bean2.getGroupNum());
							if (ret != 0) return ret;
							
							ret = bean1.getStepId().compareTo(bean2.getStepId());
							if (ret != 0) return ret;
							
							ret = bean1.getEqpId().compareTo(bean2.getEqpId());
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
