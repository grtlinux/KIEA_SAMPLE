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

package kiea.proj.sdc.anal.macro.sample.a11.aib.macro.MURA_CNT_CELL;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import kiea.kr.co.tain.base.Flag;
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
 * @file_name MURA_N_TREND_CsvReader.java
 *
 */
public class MURA_N_TREND_CsvIo extends AbstractCSVReader
{
	private String fileName = null;
	private List<MURA_N_TREND_Bean> list = null;
	
	public MURA_N_TREND_CsvIo(String filePath)
	{
		this.fileName = filePath + FileValue.SEP + "MURA_N_TREND.csv";
	}
	
	public List<MURA_N_TREND_Bean> getList()
	{
		if (Flag.TRUE) {
			try {
				/*
				 * Field 명과 Bean 항목명과 맵핑한다.
				 */
				Map<String, String> map = new HashMap<String, String>();
				map.put("PROCID"       , "procId"       );
				map.put("SITEID"       , "siteId"       );
				map.put("CELLID"       , "cellId"       );
				map.put("GLASSID"      , "glassId"      );
				map.put("PRODGRPNAME"  , "prodRrpName"  );
				map.put("PRODID"       , "prodId"       );
				map.put("ORGSTEPID"    , "orgStepId"    );
				map.put("MEASEQPUNITID", "measEqpUnitId");
				map.put("DCOLTIME"     , "dcolTime"     );
				map.put("ITEMID"       , "itemId"       );
				map.put("SUBITEMID"    , "subItemId"    );
				map.put("DATAVALUE"    , "dataValue"    );
				map.put("GATELINE"     , "gateLine"     );
				map.put("DATALINE"     , "dataLine"     );
				map.put("GATELINE2"    , "gateLine2"    );
				map.put("DATALINE2"    , "dataLine2"    );
				map.put("PRODTYPE"     , "prodType"     );
				map.put("MEASSTEPGRPID", "measStepGrpId");
				map.put("CELL_LOC_ID"  , "cellLocId"    );
				map.put("MURA_VALUE"   , "muraValue"    );
				map.put("D_TIME"       , "DTime"        );
				
				HeaderColumnNameTranslateMappingStrategy<MURA_N_TREND_Bean> mapping = new HeaderColumnNameTranslateMappingStrategy<MURA_N_TREND_Bean>();
				mapping.setType(MURA_N_TREND_Bean.class);
				mapping.setColumnMapping(map);
				
				/*
				 * CSV 파일을 읽어 Bean 리스트를 생성한다.
				 */
				CsvToBean<MURA_N_TREND_Bean> bean = new CsvToBean<MURA_N_TREND_Bean>();
				CSVReader reader = new CSVReader(new FileReader(fileName));
				list = bean.parse(mapping, reader);
				reader.close();
			
			} catch (FileNotFoundException e) {
				list = new ArrayList<MURA_N_TREND_Bean>();
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
				writer.writeNext(MURA_N_TREND_Bean.getHeader());
				
				if (list != null) {
					for (MURA_N_TREND_Bean line : list) {
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
	
	private static void test01()
	{
		if (Flag.TRUE) {
			String path = "D:/KANG/qaf/data/20140819/KEY_HHMM";
			MURA_N_TREND_CsvIo ioCsv = new MURA_N_TREND_CsvIo(path);
			
			if (Flag.TRUE) {
				/*
				 * read
				 */
				List<MURA_N_TREND_Bean> list = ioCsv.getList();
				
				for (MURA_N_TREND_Bean bean : list) {
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
			String path = "D:/KANG/qaf/data/20140819/KEY_HHMM";
			MURA_N_TREND_CsvIo ioCsv = new MURA_N_TREND_CsvIo(path);
			
			if (Flag.TRUE) {
				/*
				 * read
				 */
				List<MURA_N_TREND_Bean> list = ioCsv.getList();
				
				for (MURA_N_TREND_Bean bean : list) {
					System.out.println(bean);
				}
				
				System.out.println("the read ok !!!");
			}
		}
	}
	
	private static void test03()
	{
		if (Flag.TRUE) {
			String path = "D:/KANG/qaf/data/20140819/KEY_HHMM";
			MURA_N_TREND_CsvIo ioCsv = new MURA_N_TREND_CsvIo(path);
			
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
			String path = "D:/KANG/qaf/data/20140819/KEY_HHMM";
			MURA_N_TREND_CsvIo ioCsv = new MURA_N_TREND_CsvIo(path);
			
			if (Flag.TRUE) {
				/*
				 * read
				 */
				List<MURA_N_TREND_Bean> list = ioCsv.getList();
				
				for (MURA_N_TREND_Bean bean : list) {
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
