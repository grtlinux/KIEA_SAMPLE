package sdc.anal.lya.macro.A21.SO_SYS_CELLID_LIST2.v04.D20141128;

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
				String paramDecisionCode = StrUtil.quote(Parameter.getInstance().getStrDecisionCode());
				String paramDefectGroupCode = StrUtil.quote(Parameter.getInstance().getStrDefectGroupCode());
				if (Flag.TRUE) Print.println("[Decision:%s] [Defect:%s]", paramDecisionCode, paramDefectGroupCode);
				
				Map<String, DEFECT_HIST_Bean> mapDefectGlass = new LinkedHashMap<String, DEFECT_HIST_Bean>();

				if (Flag.TRUE) {
					/*
					 * DEFECT_HIST의 GLASS map을 만든다.
					 */
					for (DEFECT_HIST_Bean inBean1 : inList1) {
						mapDefectGlass.put(inBean1.getGlassId(), inBean1);
					}
					
					if (!Flag.TRUE) {
						/*
						 * 확인출력
						 */
						for (Map.Entry<String, DEFECT_HIST_Bean> entry : mapDefectGlass.entrySet()) {
							Print.println(">" + entry.getKey());
						}
					}
				}
				
				if (Flag.TRUE) {
					/*
					 * INSPECT_HIST에서 DEFECT_HIST의 GLASS_ID와 같은 자료를 삭제한다.
					 */
					for (int i = inList2.size() - 1; i >= 0; i--) {
						String strGlassId = inList2.get(i).getGlassId();
						
						DEFECT_HIST_Bean bean = mapDefectGlass.get(strGlassId);
						if (bean != null) {
							inList2.remove(i);
						}
					}
					
					if (!Flag.TRUE) {
						/*
						 * 확인출력
						 */
						for (int i=0; i < inList2.size(); i++) {
							Print.println("(%3d) %s", i, inList2.get(i));
						}
					}
				}
				
				if (Flag.TRUE) {
					/*
					 * 샘플링 작업 선택
					 */
					
					if (inList1.size() > COUNT_SAMPLING) {
						/*
						 * DEFECT_HIST 샘플링 
						 * TODO : 2014.10.27 : DEFECT_HIST 2000건 이상이면 임의로 2000건 Sampling 한다.
						 * 즉 DEFECT_HIST는 2000건 이상 초과될 수 없다.
						 */
						Random rand = new Random();
						int idx;
						while (inList1.size() > COUNT_SAMPLING) {
							//idx = rand.nextInt(COUNT_SAMPLING);
							idx = rand.nextInt(inList1.size());
							inList1.remove(idx);
							
							if (!Flag.TRUE) Print.println("[%d] [%d] [%d]", inList1.size(), COUNT_SAMPLING, idx);
						}
						
						if (Flag.TRUE) {
							/*
							 * DEFECT_HIST를 샘플링 되었으면 재기록한다.
							 */
							reader1.writeList();
						}
						
						if (!Flag.TRUE) {
							/*
							 * 확인출력
							 */
							for (DEFECT_HIST_Bean bean : inList1) {
								Print.println("샘플링 후 DEFECT_HIST> %s", bean);
							}
						}
					}
				}
				
				DBSCANObject dbscan = null;

				if (Flag.TRUE) {
					/*
					 * DBSCAN 실시
					 */
					dbscan = new DBSCANObject(50000, 15);   // eps, minPts 아산
					//dbscan = new DBSCANObject(50000, 10);   // eps, minPts 아산
					
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
							 * 확인출력
							 */
							for (DBSCANItem item : dbscan.getList()) {
								Print.println("DBSCAN 결과 (%s, %s) %d", new Double(item.getPointX()).toString(), new Double(item.getPointY()).toString(), item.getClusterId());
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
								
								if (item.getPointX() == valueX && item.getPointY() == valueY) {
									clusterId = item.getClusterId();
									item.setClusterId(-1);
									break;
								}
							}
							
							inBean1.setClusterId("" + clusterId);
							inBean1.setGroup("BAD");
						}
						
						if (!Flag.TRUE) {
							/*
							 * 확인출력
							 */
							for (DEFECT_HIST_Bean bean : inList1) {
								Print.println("DBSCAN 후 DEFECT_HIST > %s", bean);
							}
						}
					}
				}
				
				if (Flag.TRUE) {
					/*
					 * SO_SYS_CELLID_LIST2를 만든다.
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
					
					for (int i=0; i <= dbscan.getMaxClusterNo(); i++) {
						
						for (INSPCT_HIST_Bean bean : inList2) {
							
							SO_SYS_CELLID_LIST2_Bean outBean1 = new SO_SYS_CELLID_LIST2_Bean();
							
							outBean1.setClusterId("" + i);
							outBean1.setGlassId  (bean.getGlassId());
							outBean1.setCellId   (bean.getCellId ());
							outBean1.setDefectSeq("");
							outBean1.setXValue   ("");
							outBean1.setYValue   ("");
							outBean1.setGroup    ("GOOD");
							
							outList1.add(outBean1);
						}
					}
					
					if (!Flag.TRUE) {
						/*
						 * 확인출력
						 */
						for (SO_SYS_CELLID_LIST2_Bean bean : outList1) {
							Print.println("결과 SO_SYS_CELLID_LIST2 > %s", bean);
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
