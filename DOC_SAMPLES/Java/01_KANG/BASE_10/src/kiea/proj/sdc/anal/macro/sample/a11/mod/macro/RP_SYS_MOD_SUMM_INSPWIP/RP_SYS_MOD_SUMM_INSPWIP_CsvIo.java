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

package kiea.proj.sdc.anal.macro.sample.a11.mod.macro.RP_SYS_MOD_SUMM_INSPWIP;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import kiea.kr.co.tain.base.Flag;
import kiea.kr.co.tain.util.DateTime;
import kiea.proj.sdc.anal.common.FileValue;
import kiea.proj.sdc.anal.macro.impl.io.AbstractCSVReader;

import org.apache.commons.io.output.FileWriterWithEncoding;

import au.com.bytecode.opencsv.CSVReader;
import au.com.bytecode.opencsv.CSVWriter;
import au.com.bytecode.opencsv.bean.CsvToBean;
import au.com.bytecode.opencsv.bean.HeaderColumnNameTranslateMappingStrategy;

/**
 * @author KangSeok
 * @date 2014. 8. 5.
 * @file_name RP_SYS_MOD_SUMM_INSPWIP_CsvIo.java
 *
 */
public class RP_SYS_MOD_SUMM_INSPWIP_CsvIo extends AbstractCSVReader
{
	private String fileName = null;
	private List<RP_SYS_MOD_SUMM_INSPWIP_Bean> list = null;
	
	public RP_SYS_MOD_SUMM_INSPWIP_CsvIo(String filePath)
	{
		this.fileName = filePath + FileValue.SEP + "RP_SYS_MOD_SUMM_INSPWIP.csv";
	}
	
	public List<RP_SYS_MOD_SUMM_INSPWIP_Bean> getList()
	{
		if (Flag.TRUE) {
			try {
				/*
				 * Field 명과 Bean 항목명과 맵핑한다.
				 */
				Map<String, String> map = new HashMap<String, String>();
				map.put("ROLE"                   , "role"                  );
				map.put("TYPE_GUBUN"             , "typeGubun"             );
				map.put("MISSCLASSIFICATION_RATE", "missclassificationRate");
				map.put("WEIGHTD_B_G"            , "weightDBG"             );
				map.put("STEP_WIGHT"             , "stepWight"             );
				map.put("BAD_GOOD_RATIO"         , "badGoodRatio"          );
				map.put("BAD_RATIO"              , "badRatio"              );
				map.put("GOOD_RATIO"             , "goodRatio"             );
				map.put("BAD_CNT"                , "badCnt"                );
				map.put("GOOD_CNT"               , "goodCnt"               );
				map.put("TOT_BAD_CNT"            , "totBadCnt"             );
				map.put("TOT_GOOD_CNT"           , "totGoodCnt"            );
				map.put("GROUP_NUM"              , "groupNum"              );
				map.put("NAME"                   , "name"                  );
				map.put("DIV_CODE"               , "divCode"               );
				map.put("STEP_ID"                , "stepId"                );
				map.put("EQP_ID"                 , "eqpId"                 );
				map.put("STEP_RATE"              , "stepRate"              );
				
				HeaderColumnNameTranslateMappingStrategy<RP_SYS_MOD_SUMM_INSPWIP_Bean> mapping = new HeaderColumnNameTranslateMappingStrategy<RP_SYS_MOD_SUMM_INSPWIP_Bean>();
				mapping.setType(RP_SYS_MOD_SUMM_INSPWIP_Bean.class);
				mapping.setColumnMapping(map);
				
				/*
				 * CSV 파일을 읽어 Bean 리스트를 생성한다.
				 */
				CsvToBean<RP_SYS_MOD_SUMM_INSPWIP_Bean> bean = new CsvToBean<RP_SYS_MOD_SUMM_INSPWIP_Bean>();
				CSVReader reader = new CSVReader(new FileReader(fileName));
				list = bean.parse(mapping, reader);
				reader.close();
			
			} catch (FileNotFoundException e) {
				list = new ArrayList<RP_SYS_MOD_SUMM_INSPWIP_Bean>();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		return list;
	}
	
	public void writeList()
	{
		if (Flag.TRUE) {
			try {
				if (Flag.TRUE) new File(fileName).delete();
				
				//CSVWriter writer = new CSVWriter(new FileWriter(fileName));  // default seperator
				//CSVWriter writer = new CSVWriter(new FileWriterWithEncoding(fileName, "US-ASCII"));  // default seperator
				CSVWriter writer = new CSVWriter(new FileWriterWithEncoding(fileName, "EUC-KR"));  // default seperator

				// Header 출력
				writer.writeNext(RP_SYS_MOD_SUMM_INSPWIP_Bean.getHeader());
				
				if (list != null) {
					for (RP_SYS_MOD_SUMM_INSPWIP_Bean line : list) {
						// Data 출력
						writer.writeNext(line.getStringArray());
					}
				}
				
				writer.close();
			
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	///////////////////////////////////////////////////////////////////////////
	
	private static final String TEST_DATE = DateTime.getDate(2);
	
	private static void test01()
	{
		if (Flag.TRUE) {
			String file = "D:/KANG/qaf/data/" + TEST_DATE + "/KEY_HHMM";
			RP_SYS_MOD_SUMM_INSPWIP_CsvIo ioCsv = new RP_SYS_MOD_SUMM_INSPWIP_CsvIo(file);
			
			if (Flag.TRUE) {
				/*
				 * read
				 */
				List<RP_SYS_MOD_SUMM_INSPWIP_Bean> list = ioCsv.getList();
				
				for (RP_SYS_MOD_SUMM_INSPWIP_Bean bean : list) {
					System.out.println(bean);
				}
				System.out.println("the read ok !!!");
			}
			
			if (Flag.TRUE) try { Thread.sleep(10*1000); } catch (InterruptedException e) {}
			
			if (Flag.TRUE) {
				/*
				 * write
				 */
				ioCsv.writeList();
				System.out.println("the write ok !!!");
			}
		}
	}
	
	private static void test02()
	{
		if (Flag.TRUE) {
			String file = "D:/KANG/qaf/data/" + TEST_DATE + "/KEY_HHMM";
			RP_SYS_MOD_SUMM_INSPWIP_CsvIo ioCsv = new RP_SYS_MOD_SUMM_INSPWIP_CsvIo(file);
			
			if (Flag.TRUE) {
				/*
				 * read
				 */
				List<RP_SYS_MOD_SUMM_INSPWIP_Bean> list = ioCsv.getList();
				
				for (RP_SYS_MOD_SUMM_INSPWIP_Bean bean : list) {
					System.out.println(bean);
				}
				
				System.out.println("the read ok !!!");
			}
		}
	}
	
	private static void test03()
	{
		if (Flag.TRUE) {
			String file = "D:/KANG/qaf/data/" + TEST_DATE + "/KEY_HHMM";
			RP_SYS_MOD_SUMM_INSPWIP_CsvIo ioCsv = new RP_SYS_MOD_SUMM_INSPWIP_CsvIo(file);
			
			if (Flag.TRUE) {
				/*
				 * write
				 */
				ioCsv.writeList();
				System.out.println("the write ok !!!");
			}
		}
	}
	
	private static void test04()
	{
		if (Flag.TRUE) {
			String file = "D:/KANG/qaf/data/" + TEST_DATE + "/KEY_HHMM";
			RP_SYS_MOD_SUMM_INSPWIP_CsvIo ioCsv = new RP_SYS_MOD_SUMM_INSPWIP_CsvIo(file);
			
			if (Flag.TRUE) {
				/*
				 * read
				 */
				List<RP_SYS_MOD_SUMM_INSPWIP_Bean> list = ioCsv.getList();
				
				for (RP_SYS_MOD_SUMM_INSPWIP_Bean bean : list) {
					System.out.println(bean);
				}
				
				System.out.println("the read ok !!!");
			}
			
			if (Flag.TRUE) try { Thread.sleep(10*1000); } catch (InterruptedException e) {}
			
			if (Flag.TRUE) {
				/*
				 * write
				 */
				ioCsv.writeList();
				
				System.out.println("the write ok !!!");
			}
		}
	}
	
	public static void main(String[] args)
	{
		if (!Flag.TRUE) test01();
		if (Flag.TRUE) test02();
		if (!Flag.TRUE) test03();
		if (!Flag.TRUE) test04();
	}
}
