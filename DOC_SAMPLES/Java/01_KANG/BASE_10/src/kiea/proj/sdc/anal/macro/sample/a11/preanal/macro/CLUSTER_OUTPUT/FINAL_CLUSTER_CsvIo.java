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

package kiea.proj.sdc.anal.macro.sample.a11.preanal.macro.CLUSTER_OUTPUT;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
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
 * @file_name FINAL_CLUSTER_CsvIo.java
 *
 */
public class FINAL_CLUSTER_CsvIo extends AbstractCSVReader
{
	private String fileName = null;
	private List<FINAL_CLUSTER_Bean> list = null;
	
	public FINAL_CLUSTER_CsvIo(String filePath)
	{
		this.fileName = filePath + FileValue.SEP + "FINAL_CLUSTER.csv";
	}
	
	public List<FINAL_CLUSTER_Bean> getList()
	{
		if (Flag.TRUE) {
			try {
				/*
				 * Field 명과 Bean 항목명과 맵핑한다.
				 */
				Map<String, String> map = new HashMap<String, String>();
				map.put("DATA_NUM"  , "dataNum"  );
				map.put("AVG_X"     , "avgX"     );
				map.put("AVG_Y"     , "avgY"     );
				map.put("CLUSTER_ID", "clusterId");
				
				HeaderColumnNameTranslateMappingStrategy<FINAL_CLUSTER_Bean> mapping = new HeaderColumnNameTranslateMappingStrategy<FINAL_CLUSTER_Bean>();
				mapping.setType(FINAL_CLUSTER_Bean.class);
				mapping.setColumnMapping(map);
				
				/*
				 * CSV 파일을 읽어 Bean 리스트를 생성한다.
				 */
				CsvToBean<FINAL_CLUSTER_Bean> bean = new CsvToBean<FINAL_CLUSTER_Bean>();
				CSVReader reader = new CSVReader(new FileReader(fileName));
				list = bean.parse(mapping, reader);
				reader.close();
			
			} catch (FileNotFoundException e) {
				list = new ArrayList<FINAL_CLUSTER_Bean>();
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
				writer.writeNext(FINAL_CLUSTER_Bean.getHeader());
				
				if (list != null) {
					for (FINAL_CLUSTER_Bean line : list) {
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

	private Map<String, FINAL_CLUSTER_Bean> map = null;

	/**
	 * 
	 * setMap
	 *
	 */
	private void setMap()
	{
		if (Flag.TRUE) {
			if (map == null) {
				try {
					getList();
					
					map = new LinkedHashMap<String, FINAL_CLUSTER_Bean>();
					
					for (FINAL_CLUSTER_Bean bean : list) {
						String key = bean.getDataNum();
						
						map.put(key, bean);
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	/**
	 * 
	 * get
	 *
	 * @param procId
	 * @param cellLocId
	 * @return
	 */
	public FINAL_CLUSTER_Bean get(String key)
	{
		FINAL_CLUSTER_Bean retBean = null;
		
		if (Flag.TRUE) {
			setMap();
			
			retBean = map.get(key);
		}
		
		return retBean;
	}
	
	///////////////////////////////////////////////////////////////////////////
	
	private static final String TEST_DATE = "20140820";
	
	private static void test01()
	{
		if (Flag.TRUE) {
			String file = "D:/KANG/qaf/data/" + TEST_DATE + "/KEY_HHMM";
			FINAL_CLUSTER_CsvIo ioCsv = new FINAL_CLUSTER_CsvIo(file);
			
			if (Flag.TRUE) {
				/*
				 * read
				 */
				List<FINAL_CLUSTER_Bean> list = ioCsv.getList();
				
				for (FINAL_CLUSTER_Bean bean : list) {
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
			FINAL_CLUSTER_CsvIo ioCsv = new FINAL_CLUSTER_CsvIo(file);
			
			if (Flag.TRUE) {
				/*
				 * read
				 */
				List<FINAL_CLUSTER_Bean> list = ioCsv.getList();
				
				for (FINAL_CLUSTER_Bean bean : list) {
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
			FINAL_CLUSTER_CsvIo ioCsv = new FINAL_CLUSTER_CsvIo(file);
			
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
			FINAL_CLUSTER_CsvIo ioCsv = new FINAL_CLUSTER_CsvIo(file);
			
			if (Flag.TRUE) {
				/*
				 * read
				 */
				List<FINAL_CLUSTER_Bean> list = ioCsv.getList();
				
				for (FINAL_CLUSTER_Bean bean : list) {
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
