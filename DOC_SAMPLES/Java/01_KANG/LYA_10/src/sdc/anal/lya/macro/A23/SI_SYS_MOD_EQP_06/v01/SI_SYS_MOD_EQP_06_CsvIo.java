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

package sdc.anal.lya.macro.A23.SI_SYS_MOD_EQP_06.v01;

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
import kiea.proj.sdc.anal.macro.impl.io.AbstractCSVReader;
import kiea.proj.sdc.anal.util.BasePath;
import kiea.proj.sdc.anal.util.StrUtil;

import org.apache.commons.io.output.FileWriterWithEncoding;

import au.com.bytecode.opencsv.CSVReader;
import au.com.bytecode.opencsv.CSVWriter;
import au.com.bytecode.opencsv.bean.CsvToBean;
import au.com.bytecode.opencsv.bean.HeaderColumnNameTranslateMappingStrategy;

/**
 * @author KangSeok
 * @date 2014. 8. 5.
 * @file_name SI_SYS_MOD_EQP_06_CsvIo.java
 *
 */
public class SI_SYS_MOD_EQP_06_CsvIo extends AbstractCSVReader
{
	private String fileName = null;
	private List<SI_SYS_MOD_EQP_06_Bean> list = null;
	
	public SI_SYS_MOD_EQP_06_CsvIo(String filePath)
	{
		this.fileName = filePath + FileValue.SEP + SI_SYS_MOD_EQP_06_Main.dsName + ".csv";
	}
	
	public List<SI_SYS_MOD_EQP_06_Bean> getList()
	{
		if (Flag.TRUE) {
			try {
				/*
				 * Field 명과 Bean 항목명과 맵핑한다.
				 */
				Map<String, String> map = new HashMap<String, String>();
				map.put("STEP_ID" , "stepId" );
				map.put("EQP_ID"  , "eqpId"  );
				map.put("TOT_CNT" , "totCnt" );
				map.put("BAD_CNT" , "badCnt" );
				map.put("GOOD_CNT", "goodCnt");
				
				HeaderColumnNameTranslateMappingStrategy<SI_SYS_MOD_EQP_06_Bean> mapping = new HeaderColumnNameTranslateMappingStrategy<SI_SYS_MOD_EQP_06_Bean>();
				mapping.setType(SI_SYS_MOD_EQP_06_Bean.class);
				mapping.setColumnMapping(map);
				
				/*
				 * CSV 파일을 읽어 Bean 리스트를 생성한다.
				 */
				CsvToBean<SI_SYS_MOD_EQP_06_Bean> bean = new CsvToBean<SI_SYS_MOD_EQP_06_Bean>();
				CSVReader reader = new CSVReader(new FileReader(fileName));
				list = bean.parse(mapping, reader);
				reader.close();
			
			} catch (FileNotFoundException e) {
				list = new ArrayList<SI_SYS_MOD_EQP_06_Bean>();
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
				writer.writeNext(SI_SYS_MOD_EQP_06_Bean.getHeader());
				
				if (list != null) {
					for (SI_SYS_MOD_EQP_06_Bean line : list) {
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
			SystemProperties.setTesting("010");
		}

		if (Flag.TRUE) {
			String path = BasePath.getInstance().getDataPath() + "/" + StrUtil.getDateFromJobId(SI_SYS_MOD_EQP_06_Main.jobId) + "/" + SI_SYS_MOD_EQP_06_Main.jobId;
			SI_SYS_MOD_EQP_06_CsvIo ioCsv = new SI_SYS_MOD_EQP_06_CsvIo(path);
			
			if (Flag.TRUE) {
				/*
				 * read
				 */
				List<SI_SYS_MOD_EQP_06_Bean> list = ioCsv.getList();
				
				for (SI_SYS_MOD_EQP_06_Bean bean : list) {
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
