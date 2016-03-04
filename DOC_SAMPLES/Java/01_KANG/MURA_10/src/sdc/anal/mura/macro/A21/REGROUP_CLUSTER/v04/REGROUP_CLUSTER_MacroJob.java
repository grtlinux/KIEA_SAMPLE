package sdc.anal.mura.macro.A21.REGROUP_CLUSTER.v04;

import java.util.List;

import kiea.kr.co.tain.base.Flag;
import kiea.kr.co.tain.util.Print;
import kiea.proj.sdc.anal.macro.impl.job.AbstractMacroJob;
import kiea.proj.sdc.anal.tools.AnalCsv.v04.AnalCsvBean;
import kiea.proj.sdc.anal.tools.AnalCsv.v04.AnalCsvType;
import kiea.proj.sdc.anal.util.BasePath;
import kiea.proj.sdc.anal.util.FileUtil;
import kiea.proj.sdc.anal.util.StrUtil;
import kiea.proj.sdc.anal.util.log.v02.Logger;

public class REGROUP_CLUSTER_MacroJob extends AbstractMacroJob
{
	private MURA_RAWDATA_ANAL2_CsvIo reader1 = null;
	private BAD_GLASS_ORG_CsvIo reader2 = null;
	private OUT_DBSCAN_CsvIo reader3 = null;
	private REGROUP_CLUSTER_CsvIo writer1 = null;
	
	private List<MURA_RAWDATA_ANAL2_Bean> inList1  = null;
	private List<BAD_GLASS_ORG_Bean> inList2  = null;
	private List<OUT_DBSCAN_Bean> inList3  = null;
	private List<REGROUP_CLUSTER_Bean> outList1 = null;
	
	private String filePath = null;

	private String jobId = null;

	public REGROUP_CLUSTER_MacroJob(String jobId)
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
			REGROUP_CLUSTER_Main.runMSec = System.nanoTime();
		}

		if (Flag.TRUE) {
			/*
			 * 기존 자료 삭제
			 */
			AnalCsvBean bean = new AnalCsvBean();

			bean.setType(AnalCsvType.DELETE);
			bean.setTable("ANAL_CSV");
			bean.setWhere(String.format("JOB_ID = %s AND PROG_NM = %s", StrUtil.quote(jobId), StrUtil.quote(REGROUP_CLUSTER_Main.dsName)));

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
					{ "CMD_CODE   ", StrUtil.quote(REGROUP_CLUSTER_Main.dsName.substring(0, 3)) },
					{ "PROG_NM    ", StrUtil.quote(REGROUP_CLUSTER_Main.dsName) },
					{ "CSV_NM     ", StrUtil.quote(REGROUP_CLUSTER_Main.dsName + ".csv") },
					{ "MAIN_CLASS ", StrUtil.quote(this.getClass().getName()) },
					{ "S_TIME     ", "SYSDATE" },
					{ "CSV_STATUS ", StrUtil.quote("START") },
			});
			if (Flag.TRUE) Logger.info("[%s]", bean.getQuery());

			bean.sendToOracle();
		}

		if (Flag.TRUE) {
			try {
				reader1 = new MURA_RAWDATA_ANAL2_CsvIo(this.filePath);
				reader2 = new BAD_GLASS_ORG_CsvIo(this.filePath);
				reader3 = new OUT_DBSCAN_CsvIo(this.filePath);
				writer1 = new REGROUP_CLUSTER_CsvIo(this.filePath);
				
				inList1  = reader1.getList(true);
				inList2  = reader2.getList(true);
				inList3  = reader3.getList(true);
				outList1 = writer1.getList();
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	private enum COORD
	{
		X_VALUE, Y_VALUE,
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
				COORD coord = COORD.Y_VALUE;
				int maxCntId = 1;
				
				if (inList1.size() <= 0)
					return;
				
				if (Flag.TRUE) {
					
					int nGap = 0;
					String strMCode = null;

					/*
					 * GAP을 구한다.
					 */
					MURA_RAWDATA_ANAL2_Bean inBean1 = inList1.get(0);
					
					nGap = Integer.parseInt(inBean1.getWidth()) - Integer.parseInt(inBean1.getHeight());

					/*
					 * 가로줄 건수, 세로줄 건수를 구한다.
					 * DefectName에 포함된 단어
					 *    가로줄, UHT1
					 *    세로줄, UVT1
					 * M_CODE를 구한다.
					 */
					String defectName;
					String[] word = new String[] { "가로줄", "UHT1", "세로줄", "UVT1" };
					int[] count = new int[] { 0, 0, 0, 0, };
					
					for (BAD_GLASS_ORG_Bean inBean2 : inList2) {
						defectName = inBean2.getDefectName();

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
					
					strMCode = word[idxMax];
					
					if (!Flag.TRUE) {
						/*
						 * 출력
						 */
						for (int i=0; i < word.length; i++) {
							Print.println("%d) [%s]-[%d]", i, word[i], count[i]);
						}
						
						Print.println("strMCode = [%s] idxMax=%d", strMCode, idxMax);
					}
					
					if (nGap >= 0) {
						if ("가로줄".equals(strMCode) || "UHT1".equals(strMCode)) {
							coord = COORD.Y_VALUE;
						} else if ("세로줄".equals(strMCode) || "UVT1".equals(strMCode)){
							coord = COORD.X_VALUE;
						}
					} else {
						if ("가로줄".equals(strMCode) || "UHT1".equals(strMCode)) {
							coord = COORD.X_VALUE;
						} else if ("세로줄".equals(strMCode) || "UVT1".equals(strMCode)){
							coord = COORD.Y_VALUE;
						}
					}
				}
				
				if (!Flag.TRUE) {
					/*
					 * 해당자료만 추출한다.
					 */
					Print.println("inList3.size = %d", inList3.size());
					
					for (int idx = inList3.size()-1; idx >= 10; idx--) {
						inList3.remove(idx);
					}
					
					Print.println("inList3.size = %d", inList3.size());
				}
				
				if (Flag.TRUE) {
					/*
					 * LOOP for clusterID
					 */
					while (inList3.size() > 0) {
						
						if (Flag.TRUE) {
							
							int maxId = 100;
							
							// initialize
							int[] idCount = new int[maxId];
							for (int i=0; i < maxId; i++)
								idCount[i] = 0;
							
							// set the id count
							for (OUT_DBSCAN_Bean inBean3 : inList3) {
								int idx = Integer.parseInt(inBean3.getClusterId());
								if (idx < 0 || maxId <= idx)
									throw new IllegalArgumentException("ERROR : cluster id overflow...");
								
								idCount[idx] ++;
							}
							
							// get the max id count
							maxCntId = 1;
							for (int i=2; i < idCount.length; i++) {
								if (idCount[maxCntId] <= idCount[i]) {
									maxCntId = i;
								}
							}
							
							if (maxCntId >= (maxId - 1))
								break;
						}
						
						if (Flag.TRUE) {
							if (coord == COORD.Y_VALUE) {
								/*
								 * 가로줄 얼룩
								 */
								
								// AVG(Y_VALUE)
								// maxCntId 를 REGROUP_CLUSTER 에 옮긴다.
								long cnt = 0;
								double sum = 0;
								double avg = 0;
								
								for (OUT_DBSCAN_Bean inBean3 : inList3) {
									if (inBean3.getClusterId().equals("" + maxCntId)) {
										cnt ++;
										sum += Double.parseDouble(inBean3.getAvgY());  // <- getAvgY();
										
										REGROUP_CLUSTER_Bean outBean1 = new REGROUP_CLUSTER_Bean();

										outBean1.setDataNum  (inBean3.getDataNum  ());
										outBean1.setAvgX     (inBean3.getAvgX     ());
										outBean1.setAvgY     (inBean3.getAvgY     ());
										outBean1.setClusterId(inBean3.getClusterId());

										outList1.add(outBean1);
									}
								}
								
								avg = sum / cnt;
								
								// -30000 < AVG() < +30000 인 자료를 maxCntId 로 set한다.
								// maxCntId 를 REGROUP_CLUSTER 에 옮긴다.
								for (OUT_DBSCAN_Bean inBean3 : inList3) {
									double yValue = Double.parseDouble(inBean3.getAvgY());         // <- yValue
									if ((avg - 30000.0) < yValue && yValue < (avg + 30000.0)) {
										inBean3.setClusterId("" + maxCntId);
										
										REGROUP_CLUSTER_Bean outBean1 = new REGROUP_CLUSTER_Bean();

										outBean1.setDataNum  (inBean3.getDataNum  ());
										outBean1.setAvgX     (inBean3.getAvgX     ());
										outBean1.setAvgY     (inBean3.getAvgY     ());
										outBean1.setClusterId(inBean3.getClusterId());

										outList1.add(outBean1);
									}
								}
								
								// maxCntId 를 삭제한다.
								for (int idx=inList3.size()-1; idx >= 0; idx--) {
									OUT_DBSCAN_Bean inBean3 = inList3.get(idx);
									if (inBean3.getClusterId().equals("" + maxCntId)) {
										inList3.remove(idx);
									}
								}

							} else if (coord == COORD.X_VALUE) {
								/*
								 * 세로줄 얼룩
								 */
								
								// AVG(X_VALUE)
								// maxCntId 를 REGROUP_CLUSTER 에 옮긴다.
								long cnt = 0;
								double sum = 0;
								double avg = 0;
								
								for (OUT_DBSCAN_Bean inBean3 : inList3) {
									if (inBean3.getClusterId().equals("" + maxCntId)) {
										cnt ++;
										sum += Double.parseDouble(inBean3.getAvgX());  // <- getAvgX();
										
										REGROUP_CLUSTER_Bean outBean1 = new REGROUP_CLUSTER_Bean();

										outBean1.setDataNum  (inBean3.getDataNum  ());
										outBean1.setAvgX     (inBean3.getAvgX     ());
										outBean1.setAvgY     (inBean3.getAvgY     ());
										outBean1.setClusterId(inBean3.getClusterId());

										outList1.add(outBean1);
									}
								}
								
								avg = sum / cnt;
								
								// -30000 < AVG() < +30000 인 자료를 maxCntId 로 set한다.
								// maxCntId 를 REGROUP_CLUSTER 에 옮긴다.
								for (OUT_DBSCAN_Bean inBean3 : inList3) {
									double xValue = Double.parseDouble(inBean3.getAvgX());         // <- xValue
									if ((avg - 30000.0) < xValue && xValue < (avg + 30000.0)) {
										inBean3.setClusterId("" + maxCntId);
										
										REGROUP_CLUSTER_Bean outBean1 = new REGROUP_CLUSTER_Bean();

										outBean1.setDataNum  (inBean3.getDataNum  ());
										outBean1.setAvgX     (inBean3.getAvgX     ());
										outBean1.setAvgY     (inBean3.getAvgY     ());
										outBean1.setClusterId(inBean3.getClusterId());

										outList1.add(outBean1);
									}
								}
								
								// maxCntId 를 삭제한다.
								for (int idx=inList3.size()-1; idx >= 0; idx--) {
									OUT_DBSCAN_Bean inBean3 = inList3.get(idx);
									if (inBean3.getClusterId().equals("" + maxCntId)) {
										inList3.remove(idx);
									}
								}
							}
						}
					}
					
					// 나머지를 옮긴다.
					for (OUT_DBSCAN_Bean inBean3 : inList3) {
							
						REGROUP_CLUSTER_Bean outBean1 = new REGROUP_CLUSTER_Bean();

						outBean1.setDataNum  (inBean3.getDataNum  ());
						outBean1.setAvgX     (inBean3.getAvgX     ());
						outBean1.setAvgY     (inBean3.getAvgY     ());
						outBean1.setClusterId(inBean3.getClusterId());

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
				
				REGROUP_CLUSTER_Main.cntWList = outList1.size();

				writer1.writeList();
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		if (Flag.TRUE) {
			/*
			 * 시간기록 끝
			 */
			REGROUP_CLUSTER_Main.runMSec = (System.nanoTime() - REGROUP_CLUSTER_Main.runMSec) / 1000 / 1000;
			
			if (Flag.TRUE) Logger.info("##### took time : %d ms", REGROUP_CLUSTER_Main.runMSec);
		}

		if (Flag.TRUE) {
			/*
			 * UPDATE
			 */
			AnalCsvBean bean = new AnalCsvBean();
			
			bean.setType(AnalCsvType.UPDATE);
			bean.setTable("ANAL_CSV");
			bean.setFields(new String[][] {
					{ "LIST_CNT   ", "" + REGROUP_CLUSTER_Main.cntWList },
					{ "E_TIME     ", "SYSDATE" },
					{ "R_MSEC     ", "" + REGROUP_CLUSTER_Main.runMSec },
					{ "CSV_STATUS ", StrUtil.quote("OK") },   // OK, ERROR, SKIP, ETC...
					{ "LOG_MESSAGE", StrUtil.quote("COMPLETE") },  // COMPLETE, EXCEPTION
			});
			bean.setWhere(String.format("JOB_ID = %s AND PROG_NM = %s", StrUtil.quote(jobId), StrUtil.quote(REGROUP_CLUSTER_Main.dsName)));
			
			if (Flag.TRUE) Logger.info("[%s]", bean.getQuery());
			
			bean.sendToOracle();
		}
	}
}
