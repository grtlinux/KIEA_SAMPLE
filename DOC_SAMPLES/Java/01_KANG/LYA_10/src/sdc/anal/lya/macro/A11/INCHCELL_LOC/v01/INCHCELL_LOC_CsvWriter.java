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

package sdc.anal.lya.macro.A11.INCHCELL_LOC.v01;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import kiea.kr.co.tain.base.Flag;
import kiea.kr.co.tain.util.SystemProperties;
import kiea.proj.sdc.anal.common.FileValue;
import kiea.proj.sdc.anal.macro.impl.io.AbstractCSVWriter;
import kiea.proj.sdc.anal.util.BasePath;
import kiea.proj.sdc.anal.util.StrUtil;

import org.apache.commons.io.output.FileWriterWithEncoding;

import au.com.bytecode.opencsv.CSVReader;
import au.com.bytecode.opencsv.CSVWriter;
import au.com.bytecode.opencsv.bean.CsvToBean;
import au.com.bytecode.opencsv.bean.HeaderColumnNameTranslateMappingStrategy;

/**
 * @author KangSeok
 * @date 2014. 8. 8.
 * @file_name AIB_INCHCELL_LOC_CsvWriter.java
 *
 */
public class INCHCELL_LOC_CsvWriter extends AbstractCSVWriter
{
	private String fileName = null;
	private List<INCHCELL_LOC_WriteBean> list = null;
	
	public INCHCELL_LOC_CsvWriter(String filePath)
	{
		this.fileName = filePath + FileValue.SEP + INCHCELL_LOC_Main.dsName + ".csv";
	}

	/**
	 * getList
	 */
	@Override
	public List<INCHCELL_LOC_WriteBean> getList()
	{
		return getList(false);
	}
	
	public List<INCHCELL_LOC_WriteBean> getList(boolean flgRead)
	{
		if (Flag.TRUE) {
			
			if (!flgRead) {
				/*
				 * if flgRead = true, 초기에 읽지않는다.
				 */
				list = new ArrayList<INCHCELL_LOC_WriteBean>();
				return list;
			}
			
			try {
				/*
				 * Field 명과 Bean 항목명과 맵핑한다.
				 */
				Map<String, String> map = new HashMap<String, String>();
				map.put("SITEID"     , "siteId"     );
				map.put("CELLLOCID"  , "cellLocId"  );
				map.put("PRODGRPNAME", "prodGrpName");
				map.put("INCH"       , "inch"       );
				map.put("AREAID"     , "areaId"     );
				map.put("POINTX"     , "pointX"     );
				map.put("POINTY"     , "pointY"     );
				map.put("COLIDX"     , "colIdx"     );
				map.put("ROWIDX"     , "rowIdx"     );
				map.put("WIDTH"      , "width"      );
				map.put("HEIGHT"     , "height"     );
				
				HeaderColumnNameTranslateMappingStrategy<INCHCELL_LOC_WriteBean> mapping = new HeaderColumnNameTranslateMappingStrategy<INCHCELL_LOC_WriteBean>();
				mapping.setType(INCHCELL_LOC_WriteBean.class);
				mapping.setColumnMapping(map);
				
				/*
				 * CSV 파일을 읽어 Bean 리스트를 생성한다.
				 */
				CsvToBean<INCHCELL_LOC_WriteBean> bean = new CsvToBean<INCHCELL_LOC_WriteBean>();
				CSVReader reader = new CSVReader(new FileReader(fileName));
				list = bean.parse(mapping, reader);
				reader.close();
			
			} catch (FileNotFoundException e) {
				list = new ArrayList<INCHCELL_LOC_WriteBean>();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return list;
	}

	/**
	 * writeList
	 */
	@Override
	public void writeList()
	{
		if (Flag.TRUE) {
			try {
				/*
				 * 기존 CSV 파일을 삭제하고 신규로 CSV를 생성하여 list를 기록한다.
				 */

				if (Flag.TRUE) new File(fileName).delete();
				
				//CSVWriter writer = new CSVWriter(new FileWriter(fileName));  // default seperator
				//CSVWriter writer = new CSVWriter(new FileWriterWithEncoding(fileName, "US-ASCII"));  // default seperator
				CSVWriter writer = new CSVWriter(new FileWriterWithEncoding(fileName, "EUC-KR"));  // default seperator

				// Header 출력
				writer.writeNext(INCHCELL_LOC_WriteBean.getHeader());
				
				for (INCHCELL_LOC_WriteBean line : list) {
					// Data 출력
					writer.writeNext(line.getStringArray());
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
			SystemProperties.setTesting("010");
		}

		if (Flag.TRUE) {
			String path = BasePath.getInstance().getDataPath() + "/" + StrUtil.getDateFromJobId(INCHCELL_LOC_Main.jobId) + "/" + INCHCELL_LOC_Main.jobId;
			INCHCELL_LOC_CsvWriter csv = new INCHCELL_LOC_CsvWriter(path);
			
			if (Flag.TRUE) {
				/*
				 * read
				 */
				List<INCHCELL_LOC_WriteBean> list = csv.getList(true);
				
				for (INCHCELL_LOC_WriteBean bean : list) {
					System.out.println(bean);
				}
				
				System.out.println("the read ok !!!");
			}
		}
	}

	public static void main(String[] args)
	{
		if (Flag.TRUE) test01();
	}
}
