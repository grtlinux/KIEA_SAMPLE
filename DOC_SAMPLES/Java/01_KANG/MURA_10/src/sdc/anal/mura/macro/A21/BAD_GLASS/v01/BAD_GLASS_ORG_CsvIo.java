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

package sdc.anal.mura.macro.A21.BAD_GLASS.v01;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import kiea.kr.co.tain.base.Flag;
import kiea.kr.co.tain.util.SystemProperties;
import kiea.proj.sdc.anal.common.FileValue;
import kiea.proj.sdc.anal.macro.impl.io.AbstractCSVReader;
import kiea.proj.sdc.anal.util.BasePath;
import kiea.proj.sdc.anal.util.StrUtil;
import kiea.proj.sdc.anal.util.log.v02.Logger;

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
public class BAD_GLASS_ORG_CsvIo extends AbstractCSVReader
{
	private String fileName = null;
	private List<BAD_GLASS_ORG_Bean> list = null;
	
	public BAD_GLASS_ORG_CsvIo(String filePath)
	{
		this.fileName = filePath + FileValue.SEP + "A21_" + "BAD_GLASS_ORG.csv";
	}
	
	/**
	 * getList
	 */
	@Override
	public List<BAD_GLASS_ORG_Bean> getList()
	{
		return getList(false);
	}
	
	public List<BAD_GLASS_ORG_Bean> getList(boolean flgRead)
	{
		if (Flag.TRUE) {
			
			if (!flgRead) {
				/*
				 * if flgRead = true, 초기에 읽지않는다.
				 */
				list = new ArrayList<BAD_GLASS_ORG_Bean>();
				return list;
			}

			try {
				/*
				 * Field 명과 Bean 항목명과 맵핑한다.
				 */
				Map<String, String> map = new HashMap<String, String>();
				map.put("CELLID"       , "cellId"       );
				map.put("GLASSID"      , "glassId"      );
				map.put("SITEID"       , "siteId"       );
				map.put("PRODGRPNAME"  , "prodGrpName"  );
				map.put("PROCID"       , "procId"       );
				map.put("ITEMID"       , "itemId"       );
				map.put("SUBITEMID"    , "subItemId"    );
				map.put("DEFECTNAME"   , "defectName"   );
				map.put("ITEMNAME"     , "itemName"     );
				map.put("MEASEQPUNITID", "measEqpUnitId");
				map.put("MURA_DATA"    , "muraData"     );
				map.put("X_VALUE"      , "XValue"       );
				map.put("Y_VALUE"      , "YValue"       );
				map.put("X2_VALUE"     , "x2Value"      );
				map.put("Y2_VALUE"     , "y2Value"      );
				map.put("AVG_X"        , "avgX"         );
				map.put("AVG_Y"        , "avgY"         );
				map.put("GATELINE_1"   , "gateLine_1"   );
				map.put("GATELINE_2"   , "gateLine_2"   );
				map.put("DATALINE_1"   , "dataLine_1"   );
				map.put("DATALINE_2"   , "dataLine_2"   );
				map.put("TARGET"       , "target"       );
				map.put("CELL_POSITION", "cellPosition" );
				
				HeaderColumnNameTranslateMappingStrategy<BAD_GLASS_ORG_Bean> mapping = new HeaderColumnNameTranslateMappingStrategy<BAD_GLASS_ORG_Bean>();
				mapping.setType(BAD_GLASS_ORG_Bean.class);
				mapping.setColumnMapping(map);
				
				/*
				 * CSV 파일을 읽어 Bean 리스트를 생성한다.
				 */
				CsvToBean<BAD_GLASS_ORG_Bean> bean = new CsvToBean<BAD_GLASS_ORG_Bean>();
				//CSVReader reader = new CSVReader(new FileReader(fileName));
				CSVReader reader = new CSVReader(new InputStreamReader(new FileInputStream(fileName), "EUC-KR"));
				list = bean.parse(mapping, reader);
				reader.close();
			
			} catch (FileNotFoundException e) {
				list = new ArrayList<BAD_GLASS_ORG_Bean>();
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
				writer.writeNext(BAD_GLASS_ORG_Bean.getHeader());
				
				if (list != null) {
					for (BAD_GLASS_ORG_Bean line : list) {
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
			SystemProperties.setTesting("020");
			BasePath.getInstance();
			try {
				Logger.getInstance(BAD_GLASS_Main.jobId, BAD_GLASS_Main.dsName + ".log");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		if (Flag.TRUE) {
			String path = BasePath.getInstance().getDataPath() + "/" + StrUtil.getDateFromJobId(BAD_GLASS_Main.jobId) + "/" + BAD_GLASS_Main.jobId;
			BAD_GLASS_ORG_CsvIo ioCsv = new BAD_GLASS_ORG_CsvIo(path);
			
			if (Flag.TRUE) {
				/*
				 * read
				 */
				List<BAD_GLASS_ORG_Bean> list = ioCsv.getList(true);
				
				for (BAD_GLASS_ORG_Bean bean : list) {
					System.out.println(bean);
				}
				
				System.out.println("the read ok !!!");
			}
		}
		
		Logger.exit();
	}
	
	public static void main(String[] args)
	{
		if (Flag.TRUE) test01();
	}
}
