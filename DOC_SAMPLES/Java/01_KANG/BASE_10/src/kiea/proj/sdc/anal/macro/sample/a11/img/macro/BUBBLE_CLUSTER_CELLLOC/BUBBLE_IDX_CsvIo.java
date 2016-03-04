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

package kiea.proj.sdc.anal.macro.sample.a11.img.macro.BUBBLE_CLUSTER_CELLLOC;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
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
 * @file_name BUBBLE_INDEX_CsvIo.java
 *
 */
public class BUBBLE_IDX_CsvIo extends AbstractCSVReader
{
	private String fileName = null;
	private List<BUBBLE_IDX_Bean> list = null;
	
	public BUBBLE_IDX_CsvIo(String filePath)
	{
		this.fileName = filePath + FileValue.SEP + "BUBBLE_IDX.csv";
	}
	
	public List<BUBBLE_IDX_Bean> getList()
	{
		if (Flag.TRUE) {
			try {
				/*
				 * Field 명과 Bean 항목명과 맵핑한다.
				 */
				Map<String, String> map = new HashMap<String, String>();
				map.put("PROCID"   , "procId"   );
				map.put("CELLLOCID", "cellLocId");   // KEY
				map.put("COLIDX"   , "colIdx"   );
				map.put("ROWIDX"   , "rowIdx"   );
				map.put("POINT_X"  , "pointX"   );
				map.put("POINT_Y"  , "pointY"   );
				
				HeaderColumnNameTranslateMappingStrategy<BUBBLE_IDX_Bean> mapping = new HeaderColumnNameTranslateMappingStrategy<BUBBLE_IDX_Bean>();
				mapping.setType(BUBBLE_IDX_Bean.class);
				mapping.setColumnMapping(map);
				
				/*
				 * CSV 파일을 읽어 Bean 리스트를 생성한다.
				 */
				CsvToBean<BUBBLE_IDX_Bean> bean = new CsvToBean<BUBBLE_IDX_Bean>();
				CSVReader reader = new CSVReader(new FileReader(fileName));
				list = bean.parse(mapping, reader);
				reader.close();
			
			} catch (FileNotFoundException e) {
				list = new ArrayList<BUBBLE_IDX_Bean>();
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
				writer.writeNext(BUBBLE_IDX_Bean.getHeader());
				
				if (list != null) {
					for (BUBBLE_IDX_Bean line : list) {
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

	private Map<String, BUBBLE_IDX_Bean> map = null;

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
					
					map = new LinkedHashMap<String, BUBBLE_IDX_Bean>();
					
					for (BUBBLE_IDX_Bean bean : list) {
						String key = bean.getCellLocId();
						
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
	public BUBBLE_IDX_Bean get(String key)
	{
		BUBBLE_IDX_Bean retBean = null;
		
		if (Flag.TRUE) {
			setMap();
			
			retBean = map.get(key);
		}
		
		return retBean;
	}
	
	///////////////////////////////////////////////////////////////////////////
	
	private static final String TEST_DATE = DateTime.getDate(2);

	private static void test01()
	{
		if (Flag.TRUE) {
			String path = "D:/KANG/qaf/data/" + TEST_DATE + "/KEY_HHMM";
			BUBBLE_IDX_CsvIo ioCsv = new BUBBLE_IDX_CsvIo(path);
			
			if (Flag.TRUE) {
				/*
				 * read
				 */
				List<BUBBLE_IDX_Bean> list = ioCsv.getList();
				
				for (BUBBLE_IDX_Bean bean : list) {
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
			String path = "D:/KANG/qaf/data/" + TEST_DATE + "/KEY_HHMM";
			BUBBLE_IDX_CsvIo ioCsv = new BUBBLE_IDX_CsvIo(path);
			
			if (Flag.TRUE) {
				/*
				 * read
				 */
				List<BUBBLE_IDX_Bean> list = ioCsv.getList();
				
				for (BUBBLE_IDX_Bean bean : list) {
					System.out.println(bean);
				}
				
				System.out.println("the read ok !!!");
			}
		}
	}
	
	private static void test03()
	{
		if (Flag.TRUE) {
			String path = "D:/KANG/qaf/data/" + TEST_DATE + "/KEY_HHMM";
			BUBBLE_IDX_CsvIo ioCsv = new BUBBLE_IDX_CsvIo(path);
			
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
			String path = "D:/KANG/qaf/data/" + TEST_DATE + "/KEY_HHMM";
			BUBBLE_IDX_CsvIo ioCsv = new BUBBLE_IDX_CsvIo(path);
			
			if (Flag.TRUE) {
				/*
				 * read
				 */
				List<BUBBLE_IDX_Bean> list = ioCsv.getList();
				
				for (BUBBLE_IDX_Bean bean : list) {
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
