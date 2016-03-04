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

package kiea.proj.sdc.anal.macro.sample.a11.aib.macro.CELL_MAP_XY;

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
 * @file_name CELL_MAP_CsvReader.java
 *
 */
public class CELL_MAP_CsvIo extends AbstractCSVReader
{
	private String fileName = null;
	private List<CELL_MAP_Bean> list = null;
	
	public CELL_MAP_CsvIo(String filePath)
	{
		this.fileName = filePath + FileValue.SEP + "CELL_MAP.csv";
	}
	
	public List<CELL_MAP_Bean> getList()
	{
		if (Flag.TRUE) {
			try {
				/*
				 * Field 명과 Bean 항목명과 맵핑한다.
				 */
				Map<String, String> map = new HashMap<String, String>();
				map.put("PROCID"        , "procId"        );
				map.put("CELLLOCID"     , "cellLocId"     );
				map.put("POINT_X"       , "pointX"        );
				map.put("POINT_Y"       , "pointY"        );
				map.put("WIDTH"         , "width"         );
				map.put("HEIGHT"        , "height"        );
				map.put("GATERESOLUTION", "gateResolution");
				map.put("DATARESOLUTION", "dataResolution");
				map.put("COLIDX"        , "colIdx"        );
				map.put("ROWIDX"        , "rowIdx"        );
				map.put("PIXCEL_X"      , "pixcelX"       );
				map.put("PIXCEL_Y"      , "pixcelY"       );
				map.put("X_LOGIC1"      , "xLogic1"       );
				map.put("Y_LOGIC1"      , "yLogic1"       );
				map.put("X_LOGIC2"      , "xLogic2"       );
				map.put("Y_LOGIC2"      , "yLogic2"       );
				map.put("SITEID"        , "siteId"        );
				map.put("GLASS_HEIGHT"  , "glassHeight"   );
				map.put("MAPCELL"       , "mapCell"       );
				
				HeaderColumnNameTranslateMappingStrategy<CELL_MAP_Bean> mapping = new HeaderColumnNameTranslateMappingStrategy<CELL_MAP_Bean>();
				mapping.setType(CELL_MAP_Bean.class);
				mapping.setColumnMapping(map);
				
				/*
				 * CSV 파일을 읽어 Bean 리스트를 생성한다.
				 */
				CsvToBean<CELL_MAP_Bean> bean = new CsvToBean<CELL_MAP_Bean>();
				CSVReader reader = new CSVReader(new FileReader(fileName));
				list = bean.parse(mapping, reader);
				reader.close();
			
			} catch (FileNotFoundException e) {
				list = new ArrayList<CELL_MAP_Bean>();
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
				writer.writeNext(CELL_MAP_Bean.getHeader());
				
				if (list != null) {
					for (CELL_MAP_Bean line : list) {
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
			String file = "D:/KANG/qaf/data/20140818/KEY_HHMM";
			CELL_MAP_CsvIo ioCsv = new CELL_MAP_CsvIo(file);
			
			if (Flag.TRUE) {
				/*
				 * read
				 */
				List<CELL_MAP_Bean> list = ioCsv.getList();
				
				for (CELL_MAP_Bean bean : list) {
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
			String file = "D:/KANG/qaf/data/20140818/KEY_HHMM";
			CELL_MAP_CsvIo ioCsv = new CELL_MAP_CsvIo(file);
			
			if (Flag.TRUE) {
				/*
				 * read
				 */
				List<CELL_MAP_Bean> list = ioCsv.getList();
				
				for (CELL_MAP_Bean bean : list) {
					System.out.println(bean);
				}
				
				System.out.println("the read ok !!!");
			}
		}
	}
	
	private static void test03()
	{
		if (Flag.TRUE) {
			String file = "D:/KANG/qaf/data/20140818/KEY_HHMM";
			CELL_MAP_CsvIo ioCsv = new CELL_MAP_CsvIo(file);
			
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
			String file = "D:/KANG/qaf/data/20140818/KEY_HHMM";
			CELL_MAP_CsvIo ioCsv = new CELL_MAP_CsvIo(file);
			
			if (Flag.TRUE) {
				/*
				 * read
				 */
				List<CELL_MAP_Bean> list = ioCsv.getList();
				
				for (CELL_MAP_Bean bean : list) {
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
