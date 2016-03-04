/* Copyright (c) 2008-2014, KangSeok
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 * Redistributions of source code must retain the above copyright notice, this
 * list of conditions and the following disclaimer.
 *
 * Redistributions in binary form must reproduce the above copyright notice,
 * this list of conditions and the following disclaimer in the documentation
 * and/or other materials provided with the distribution.
 *
 * Neither the name of the HSQL Development Group nor the names of its
 * contributors may be used to endorse or promote products derived from this
 * software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL HSQL DEVELOPMENT GROUP, HSQLDB.ORG,
 * OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL,
 * EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO,
 * PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
 * LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 * ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

package kiea.proj.sdc.anal.macro.sample.a01.job;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import kiea.kr.co.tain.base.Flag;
import kiea.kr.co.tain.util.DateTime;
import kiea.proj.sdc.anal.common.FileValue;
import kiea.proj.sdc.anal.macro.impl.job.AbstractMacroJob;
import kiea.proj.sdc.anal.macro.sample.a01.bean.Sample01ReadBean;
import kiea.proj.sdc.anal.macro.sample.a01.bean.Sample01WriteBean;
import kiea.proj.sdc.anal.macro.sample.a01.io.Sample01CSVReader;
import kiea.proj.sdc.anal.macro.sample.a01.io.Sample01CSVWriter;
import kiea.proj.sdc.anal.util.BasePath;
import kiea.proj.sdc.anal.util.FileUtil;
import kiea.proj.sdc.anal.util.log.v02.Logger;

import org.apache.commons.io.output.FileWriterWithEncoding;
import org.apache.commons.math3.stat.descriptive.SummaryStatistics;

import au.com.bytecode.opencsv.CSVWriter;

/**
 * @author KangSeok
 * @date 2014. 8. 5.
 * @file_name Sample01MacroJob.java
 *
 */
public class Sample01MacroJob extends AbstractMacroJob
{
	private Sample01CSVReader reader = null;
	private Sample01CSVWriter writer = null;
	
	private List<Sample01ReadBean> inList = null;
	private List<Sample01WriteBean> outList = null;
	
	private String filePath = null;

	private String keyPath = null;
	private String dsName = null;
	private int num = 0;
	
	/**
	 * constructor
	 * @param num
	 * @param dsName
	 */
	public Sample01MacroJob(String keyPath, String dsName, int num)
	{
		this.keyPath = keyPath;
		
		this.num = num;
		this.dsName = dsName;
		
		this.filePath = FileUtil.makeDataDir(BasePath.getInstance().getDataPath(), DateTime.getDate(2), this.keyPath);
	}
	
	/**
	 * generate the read CSV file
	 */
	public void generateDataSet()
	{
		//int size = 1000 * 5000;  // 6분
		//int size = 1000 * 100;
		int size = this.num;
		
		if (Flag.TRUE) Logger.info("generateDataSet : %s (%d 건) ", this.getClass(), size);

		List<Sample01ReadBean> list = new ArrayList<Sample01ReadBean>();
		
		if (Flag.TRUE) {
			try {
				/*
				 * 자료를 생성한다.
				 */
				Random randGroup  = new Random();
				Random randValueX = new Random();
				Random randValueY = new Random();
				Random randFlag   = new Random();
				
				for (int i=0; i < size; i++) {
					Sample01ReadBean bean = new Sample01ReadBean();
					bean.setGroupNo(randGroup.nextInt(10));
					bean.setValueX(randValueX.nextInt(1000));
					bean.setValueY(randValueY.nextInt(1000));
					bean.setFlag(randFlag.nextBoolean());
					
					list.add(bean);
				}

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		if (Flag.TRUE) {
			/*
			 * 자료를 출력한다.
			 * TODO : 2014.07.23 : 소켓통신의 송신에 대한 문제 고려필요.
			 */
			for (int i=0; i < list.size(); i++) {
				if (i >= 10) {
					Logger.info(".....(생략).....");
					break;
				}
				
				Logger.info(list.get(i).toString());
			}
		}
		
		if (Flag.TRUE) {
			try {
				
				/*
				 * 자료를 저장한다.
				 */
				// CSVWriter writer = new CSVWriter(new FileWriter(ConfigTestMain.DATA_FOLDER + "Sample01Read.csv"));
				CSVWriter writer = new CSVWriter(new FileWriterWithEncoding(this.filePath + FileValue.SEP + this.dsName + "Read.csv", "EUC-KR"));

				// Header 출력
				writer.writeNext(Sample01ReadBean.getHeader());
				
				for (Sample01ReadBean line : list) {
					// Data 출력
					writer.writeNext(line.getStringArray());
				}
				
				writer.close();

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * before the macro job
	 */
	public void beforeMacroJob()
	{
		if (Flag.TRUE) Logger.info("beforeMacroJob : " + this.getClass());
		
		if (Flag.TRUE) {
			try {
				/*
				 * before Job
				 * 1. InBean
				 * 2. OutBean
				 */
				reader = new Sample01CSVReader(this.filePath + FileValue.SEP + this.dsName + "Read.csv");
				writer = new Sample01CSVWriter(this.filePath + FileValue.SEP + this.dsName + "Write.csv");
				
				inList = reader.getList();
				outList = writer.getList();
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * macro job
	 */
	@SuppressWarnings("unused")
	public void macroJob()
	{
		if (Flag.TRUE) Logger.info("macroJob       : " + this.getClass());
		
		if (Flag.TRUE) {
			try {
				/*
				 * Job
				 * 1. InBean -> OutBean
				 */
				
				// X
				SummaryStatistics xStat = new SummaryStatistics();
				for (int i=0; i < inList.size(); i++) {
					xStat.addValue(inList.get(i).getValueX());
				}

				double sumX  = xStat.getSum();
				double minX  = xStat.getMin();
				double maxX  = xStat.getMax();
				double meanX = xStat.getMean();
				double varX  = xStat.getVariance();
				double stdX  = xStat.getStandardDeviation();

				// Y
				SummaryStatistics yStat = new SummaryStatistics();
				for (int i=0; i < inList.size(); i++) {
					yStat.addValue(inList.get(i).getValueY());
				}

				double sumY  = yStat.getSum();
				double minY  = yStat.getMin();
				double maxY  = yStat.getMax();
				double meanY = yStat.getMean();
				double varY  = yStat.getVariance();
				double stdY  = yStat.getStandardDeviation();

				// OUT
				for (int i=0; i < inList.size(); i++) {
					Sample01WriteBean bean = new Sample01WriteBean();

					bean.setGroupNo(inList.get(i).getGroupNo());
					bean.setValueX (inList.get(i).getValueX());
					bean.setValueY (inList.get(i).getValueY());
					bean.setFlag   (inList.get(i).isFlag());
					bean.setEtc    (inList.get(i).getEtc());
					
					bean.setSumX  (sumX);
					bean.setAvgX  (meanX);
					bean.setRatioX(inList.get(i).getValueX() * 100.0 / sumX);
					
					bean.setSumY  (sumY);
					bean.setAvgY  (meanY);
					bean.setRatioY(inList.get(i).getValueY() * 100.0 / sumY);
					
					outList.add(bean);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * after the macro job
	 */
	public void afterMacroJob()
	{
		if (Flag.TRUE) Logger.info("afterMacroJob  : " + this.getClass());

		if (Flag.TRUE) {
			try {
				/*
				 * after Job
				 * 1. OutBean
				 */
				writer.writeList();

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * run the macro job
	 */
	@Override
	public void executeMacroJob()
	{
		if (Flag.TRUE) {
			/*
			 * 자료생성 -> 실처리
			 */
			if (Flag.TRUE) generateDataSet();
			beforeMacroJob();
			macroJob();
			afterMacroJob();
			Logger.info("------------------------------------------------");
		}

		if (!Flag.TRUE) {
			/*
			 * 실처리-선행처리만
			 */
			beforeMacroJob();
			macroJob();
			Logger.info("------------------------------------------------");
		}


		if (!Flag.TRUE) {
			/*
			 * 실처리-후행처리만
			 */
			macroJob();
			afterMacroJob();
			Logger.info("------------------------------------------------");
		}

		if (!Flag.TRUE) {
			/*
			 * 그냥 실처리만.
			 */
			macroJob();
			Logger.info("------------------------------------------------");
		}

		if (!Flag.TRUE) {
			/*
			 * 작업시간에 대한 테스트용도
			 */
			Logger.info("START : Hello, world!!!");
			
			try { Thread.sleep(2 * 1000); } catch (InterruptedException e) {}
			
			Logger.info("END : Hello, world!!!");
		}

		if (!Flag.TRUE) {
			/*
			 * read 용 CSV 파일을 만든다.
			 */
			Logger.info("START : generateDataSet");

			generateDataSet();

			Logger.info("END : generateDataSet");
		}
	}
}
