package sdc.anal.lya.macro.A21.SO_SYS_CELLID_LIST2.v04.D20141215;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import kiea.kr.co.tain.base.Flag;
import kiea.kr.co.tain.util.Print;
import kiea.proj.sdc.anal.common.Parameter;
import kiea.proj.sdc.anal.common.dbscan.DBSCANItem;
import kiea.proj.sdc.anal.common.dbscan.DBSCANObject;
import kiea.proj.sdc.anal.macro.impl.job.AbstractMacroJob;
import kiea.proj.sdc.anal.tools.AnalCsv.v04.AnalCsvBean;
import kiea.proj.sdc.anal.tools.AnalCsv.v04.AnalCsvType;
import kiea.proj.sdc.anal.util.BasePath;
import kiea.proj.sdc.anal.util.FileUtil;
import kiea.proj.sdc.anal.util.StrUtil;
import kiea.proj.sdc.anal.util.log.v02.Logger;

import org.apache.commons.math3.ml.clustering.DoublePoint;

public class SO_SYS_CELLID_LIST2_MacroJob extends AbstractMacroJob
{
	private static final int COUNT_SAMPLING = 2000;
	
	private DEFECT_HIST_CsvIo reader1 = null;
	private INSPCT_HIST_CsvIo reader2 = null;
	private SO_SYS_CELLID_LIST2_CsvIo writer1 = null;
	
	private List<DEFECT_HIST_Bean> inList1  = null;
	private List<INSPCT_HIST_Bean> inList2  = null;
	private List<SO_SYS_CELLID_LIST2_Bean> outList1 = null;
	
	private String filePath = null;

	private String jobId = null;

	public SO_SYS_CELLID_LIST2_MacroJob(String jobId)
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
			/*
			 * 시간기록 시작
			 */
			SO_SYS_CELLID_LIST2_Main.runMSec = System.nanoTime();
		}

		if (Flag.TRUE) {
			/*
			 * 기존 자료 삭제
			 */
			AnalCsvBean bean = new AnalCsvBean();

			bean.setType(AnalCsvType.DELETE);
			bean.setTable("ANAL_CSV");
			bean.setWhere(String.format("JOB_ID = %s AND PROG_NM = %s", StrUtil.quote(jobId), StrUtil.quote(SO_SYS_CELLID_LIST2_Main.dsName)));

			bean.sendToOracle();

			if (Flag.TRUE) Logger.info("[%s]", bean.getQuery());
		}

		if (Flag.TRUE) {
			/*
			 * INSERT
			 */
			AnalCsvBean bean = new AnalCsvBean();

			bean.setType(AnalCsvType.INSERT);
			bean.setTable("ANAL_CSV");
			bean.setFields(new String[][] {
					{ "JOB_ID     ", StrUtil.quote(jobId) },
					{ "CMD_CODE   ", StrUtil.quote(SO_SYS_CELLID_LIST2_Main.dsName.substring(0, 3)) },
					{ "PROG_NM    ", StrUtil.quote(SO_SYS_CELLID_LIST2_Main.dsName) },
					{ "CSV_NM     ", StrUtil.quote(SO_SYS_CELLID_LIST2_Main.dsName + ".csv") },
					{ "MAIN_CLASS ", StrUtil.quote(this.getClass().getName()) },
					{ "S_TIME     ", "SYSDATE" },
					{ "CSV_STATUS ", StrUtil.quote("START") },
			});
			if (Flag.TRUE) Logger.info("[%s]", bean.getQuery());

			bean.sendToOracle();
		}

		if (Flag.TRUE) {
			try {
				reader1 = new DEFECT_HIST_CsvIo(this.filePath);
				reader2 = new INSPCT_HIST_CsvIo(this.filePath);
				writer1 = new SO_SYS_CELLID_LIST2_CsvIo(this.filePath);
				
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
				String paramDecisionCode = StrUtil.quote(Parameter.getInstance().getStrDecisionCode());
				String paramDefectGroupCode = StrUtil.quote(Parameter.getInstance().getStrDefectGroupCode());
				if (Flag.TRUE) Print.println("[Decision:%s] [Defect:%s]", paramDecisionCode, paramDefectGroupCode);

				Map<String, INSPCT_HIST_Bean> mapGInspct = new LinkedHashMap<String, INSPCT_HIST_Bean>();
				Map<String, INSPCT_HIST_Bean> mapCInspct = new LinkedHashMap<String, INSPCT_HIST_Bean>();
				
				if (Flag.TRUE) {
					String group;
					String glassId;
					String cellId;
					INSPCT_HIST_Bean bean;

					/*
					 * GLASS INSPCT
					 * 적용 : 한개 CELL 이 BAD이면 그 해당 GLASS는 BAD
					 */
					for (INSPCT_HIST_Bean inBean2 : inList2) {
						
						glassId = inBean2.getGlassId();
						
						/*
						 * BAD/GOOD 구분 group
						 */
						if (paramDecisionCode.indexOf(StrUtil.quote(inBean2.getDecisionCode())) >= 0
						&& paramDefectGroupCode.indexOf(StrUtil.quote(inBean2.getDefectGroupCode())) >= 0) {
							group = "BAD";
						} else {
							group = "GOOD";
						}
					
						/*
						 * GLASSID
						 */
						bean = mapGInspct.get(glassId);
						if (bean == null) {
							inBean2.setGroup(group);
							mapGInspct.put(glassId, inBean2);
						} else if ("BAD".equals(group)) {
							/*
							 * 한개 CELL 이 BAD이면 그 해당 GLASS는 BAD
							 */
							bean.setDecisionCode(inBean2.getDecisionCode());
							bean.setDefectGroupCode(inBean2.getDefectGroupCode());
							bean.setGroup(group);
						}
					}
					
					if (!Flag.TRUE) {
						for (Map.Entry<String, INSPCT_HIST_Bean> entry : mapGInspct.entrySet()) {
							System.out.println(entry.getValue());
						}
					}

					/*
					 * CELL INSPCT
					 */
					for (INSPCT_HIST_Bean inBean2 : inList2) {
						
						glassId = inBean2.getGlassId();
						INSPCT_HIST_Bean gBean = mapGInspct.get(glassId);
						group = gBean.getGroup();

						inBean2.setGroup(group);

						cellId = inBean2.getCellId();
						mapCInspct.put(cellId, inBean2);
					}
					
					if (!Flag.TRUE) {
						for (Map.Entry<String, INSPCT_HIST_Bean> entry : mapCInspct.entrySet()) {
							System.out.println(entry.getValue());
						}
					}
				}
				
				
				// Map<String, SO_SYS_CELLID_LIST2_Bean> mapList = new LinkedHashMap<String, SO_SYS_CELLID_LIST2_Bean>();

				if (Flag.TRUE) {
					if (!Flag.TRUE || inList1.size() == 0) {
						/*
						 * Defect CNT = 0
						 * AIB_INSPCT_HIST로 처리
						 */
						for (Map.Entry<String, INSPCT_HIST_Bean> entry : mapCInspct.entrySet()) {
							
							INSPCT_HIST_Bean bean = entry.getValue();

							SO_SYS_CELLID_LIST2_Bean outBean1 = new SO_SYS_CELLID_LIST2_Bean();
							
							outBean1.setClusterId("0");
							outBean1.setGlassId  (bean.getGlassId());
							outBean1.setCellId   (bean.getCellId ());
							outBean1.setDefectSeq("");
							outBean1.setXValue   ("");
							outBean1.setYValue   ("");
							outBean1.setGroup    (bean.getGroup  ());
							
							outList1.add(outBean1);
						}
					} else {
						
						if (Flag.TRUE) {
							/*
							 * TODO : 2014.10.27 : DEFECT_HIST 2000건 이상이면 임의로 2000건 Sampling 한다.
							 */
							Random rand = new Random();
							int idx;
							while (inList1.size() > COUNT_SAMPLING) {
								//idx = rand.nextInt(COUNT_SAMPLING);
								idx = rand.nextInt(inList1.size());
								inList1.remove(idx);
								
								if (!Flag.TRUE) Print.println("[%d] [%d] [%d]", inList1.size(), COUNT_SAMPLING, idx);
							}
						}
						
						/*
						 * DBSCAN 실시
						 */
						DBSCANObject dbscan = new DBSCANObject(50000, 15);   // eps, minPts 아산
						//DBSCANObject dbscan = new DBSCANObject(50000, 10);   // eps, minPts 아산
						
						if (Flag.TRUE) {
							/*
							 * DBSCAN clustering
							 */
							for (DEFECT_HIST_Bean inBean1 : inList1) {
								dbscan.addPoint(new DoublePoint( new double[] { Double.parseDouble(inBean1.getXValue()), Double.parseDouble(inBean1.getYValue()) }));
							}
							
							dbscan.clustering();
							
							if (!Flag.TRUE) {
								/*
								 * DBSCAN 확인출력
								 */
								List<DBSCANItem> list = dbscan.getList();
								
								if (Flag.TRUE) {
									for (DBSCANItem item : list) {
										System.out.println(item);
									}
								}
								
								if (!Flag.TRUE) {
									for (DBSCANItem item : list) {
										Print.println("(%s, %s) %d", new Double(item.getPointX()).toString(), new Double(item.getPointY()).toString(), item.getClusterId());
									}
								}
							}
							
							if (Flag.TRUE) {
								/*
								 * 해당자료만 추출한다.
								 */
								List<DBSCANItem> list = dbscan.getList();
								int clusterId = -1;

								for (DEFECT_HIST_Bean inBean1 : inList1) {
									
									double valueX = Double.parseDouble(inBean1.getXValue());
									double valueY = Double.parseDouble(inBean1.getYValue());
									
									for (DBSCANItem item : list) {
										if (item.getClusterId() < 0)
											continue;
										/*
										 * DEFECT 좌표와 비교하여 같으면 해당 좌표로 판단.
										 */
										if (item.getPointX() == valueX && item.getPointY() == valueY) {
											clusterId = item.getClusterId();
											item.setClusterId(-1);
											break;
										}
									}
									
									inBean1.setClusterId("" + clusterId);
									
									INSPCT_HIST_Bean inBean2 = mapGInspct.get(inBean1.getGlassId());
									if (inBean2 != null) {
										inBean1.setGroup(inBean2.getGroup());
									} else {
										inBean1.setGroup("GOOD");
									}
								}
								
								if (!Flag.TRUE) {
									System.out.println();
									for (DBSCANItem item : list) {
										System.out.println(item);
									}
								}
								
								if (Flag.TRUE) {
									/*
									 * DEFECT_HIST를 재기록한다.
									 * -> REWRITE
									 */
									try {
										reader1.writeList();
									} catch (Exception e) {
										e.printStackTrace();
									}
								}
							}
						}
						
						if (Flag.TRUE) {
							/*
							 * SO_SYS_CELLID_LIST2
							 */
							for (DEFECT_HIST_Bean inBean1 : inList1) {
								SO_SYS_CELLID_LIST2_Bean outBean = new SO_SYS_CELLID_LIST2_Bean();
								
								outBean.setClusterId(inBean1.getClusterId());
								outBean.setGlassId  (inBean1.getGlassId  ());
								outBean.setCellId   (inBean1.getCellId   ());
								outBean.setDefectSeq(inBean1.getDefectSeq());
								outBean.setXValue   (inBean1.getXValue   ());
								outBean.setYValue   (inBean1.getYValue   ());
								outBean.setGroup    (inBean1.getGroup    ());
								
								outList1.add(outBean);
							}
							
							if (!Flag.TRUE) Print.println("[ MAX_CLUSTER_ID : %d ]", dbscan.getMaxClusterNo());
							
							/*
							 * 각 CLUSTER_ID 별로 INSPCT_HIST에서 GOOD GLASS를 추가한다.
							 */
							for (int i=0; i <= dbscan.getMaxClusterNo(); i++) {
								
								for (Map.Entry<String, INSPCT_HIST_Bean> entry : mapGInspct.entrySet()) {
									
									INSPCT_HIST_Bean bean = entry.getValue();
									if ("BAD".equals(bean.getGroup()))
										continue;

									SO_SYS_CELLID_LIST2_Bean outBean1 = new SO_SYS_CELLID_LIST2_Bean();
									
									outBean1.setClusterId("" + i);
									outBean1.setGlassId  (bean.getGlassId());
									outBean1.setCellId   (bean.getCellId ());
									outBean1.setDefectSeq("");
									outBean1.setXValue   ("");
									outBean1.setYValue   ("");
									outBean1.setGroup    (bean.getGroup  ());
									
									outList1.add(outBean1);
								}
							}
						}
					}
					
					if (!Flag.TRUE) {
						/*
						 * 확인
						 */
						for (SO_SYS_CELLID_LIST2_Bean bean : outList1) {
							System.out.println(bean);
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
				
				SO_SYS_CELLID_LIST2_Main.cntWList = outList1.size();

				writer1.writeList();
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		if (Flag.TRUE) {
			/*
			 * 시간기록 끝
			 */
			SO_SYS_CELLID_LIST2_Main.runMSec = (System.nanoTime() - SO_SYS_CELLID_LIST2_Main.runMSec) / 1000 / 1000;
			
			if (Flag.TRUE) Logger.info("##### took time : %d ms", SO_SYS_CELLID_LIST2_Main.runMSec);
		}

		if (Flag.TRUE) {
			/*
			 * UPDATE
			 */
			AnalCsvBean bean = new AnalCsvBean();
			
			bean.setType(AnalCsvType.UPDATE);
			bean.setTable("ANAL_CSV");
			bean.setFields(new String[][] {
					{ "LIST_CNT   ", "" + SO_SYS_CELLID_LIST2_Main.cntWList },
					{ "E_TIME     ", "SYSDATE" },
					{ "R_MSEC     ", "" + SO_SYS_CELLID_LIST2_Main.runMSec },
					{ "CSV_STATUS ", StrUtil.quote("OK") },   // OK, ERROR, SKIP, ETC...
					{ "LOG_MESSAGE", StrUtil.quote("COMPLETE") },  // COMPLETE, EXCEPTION
			});
			bean.setWhere(String.format("JOB_ID = %s AND PROG_NM = %s", StrUtil.quote(jobId), StrUtil.quote(SO_SYS_CELLID_LIST2_Main.dsName)));
			
			if (Flag.TRUE) Logger.info("[%s]", bean.getQuery());
			
			bean.sendToOracle();
		}
	}
}
