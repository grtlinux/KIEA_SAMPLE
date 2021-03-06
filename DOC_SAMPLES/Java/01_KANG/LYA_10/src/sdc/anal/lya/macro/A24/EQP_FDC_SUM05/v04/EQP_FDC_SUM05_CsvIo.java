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

package sdc.anal.lya.macro.A24.EQP_FDC_SUM05.v04;

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
 * @file_name CELL_MAP_XY_CsvReader.java
 *
 */
public class EQP_FDC_SUM05_CsvIo extends AbstractCSVReader
{
	private String fileName = null;
	private List<EQP_FDC_SUM05_Bean> list = null;
	
	public EQP_FDC_SUM05_CsvIo(String filePath)
	{
		this.fileName = filePath + FileValue.SEP + EQP_FDC_SUM05_Main.dsName + ".csv";
	}
	
	/**
	 * getList
	 */
	@Override
	public List<EQP_FDC_SUM05_Bean> getList()
	{
		return getList(false);
	}
	
	public List<EQP_FDC_SUM05_Bean> getList(boolean flgRead)
	{
		if (Flag.TRUE) {
			
			if (!flgRead) {
				/*
				 * if flgRead = true, 초기에 읽지않는다.
				 */
				list = new ArrayList<EQP_FDC_SUM05_Bean>();
				return list;
			}

			try {
				/*
				 * Field 명과 Bean 항목명과 맵핑한다.
				 */
				Map<String, String> map = new HashMap<String, String>();
				map.put("CLUSTER_ID" , "clusterId"  );
				map.put("PART"       , "part"       );
				map.put("EQPID"      , "eqpId"      );
				map.put("SENSOR_NAME", "sensorName" );
				map.put("PARAM_VALUE", "paramValue" );
				map.put("GLASSID"    , "glassId"    );
				map.put("GROUP_ID"   , "groupId"    );
				map.put("ODD_FLAG"   , "oddFlag"    );
				map.put("TOT_CNT"    , "totCnt"     );
				map.put("TOT_BCNT"   , "totBCnt"    );
				map.put("TOT_GCNT"   , "totGCnt"    );
				map.put("TOT_IG"     , "totIg"      );
				map.put("L_CNT"      , "LCnt"       );
				map.put("L_BCNT"     , "LBcnt"      );
				map.put("L_GCNT"     , "LGcnt"      );
				map.put("L_IG"       , "LIg"        );
				map.put("R_CNT"      , "RCnt"       );
				map.put("R_BCNT"     , "RBcnt"      );
				map.put("R_GCNT"     , "RGcnt"      );
				map.put("R_IG"       , "RIg"        );
				map.put("INFO_GAIN"  , "infoGain"   );
				
				HeaderColumnNameTranslateMappingStrategy<EQP_FDC_SUM05_Bean> mapping = new HeaderColumnNameTranslateMappingStrategy<EQP_FDC_SUM05_Bean>();
				mapping.setType(EQP_FDC_SUM05_Bean.class);
				mapping.setColumnMapping(map);
				
				/*
				 * CSV 파일을 읽어 Bean 리스트를 생성한다.
				 */
				CsvToBean<EQP_FDC_SUM05_Bean> bean = new CsvToBean<EQP_FDC_SUM05_Bean>();
				//CSVReader reader = new CSVReader(new FileReader(fileName));
				CSVReader reader = new CSVReader(new InputStreamReader(new FileInputStream(fileName), "EUC-KR"));
				list = bean.parse(mapping, reader);
				reader.close();
			
			} catch (FileNotFoundException e) {
				list = new ArrayList<EQP_FDC_SUM05_Bean>();
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
				writer.writeNext(EQP_FDC_SUM05_Bean.getHeader());
				
				if (list != null) {
					for (EQP_FDC_SUM05_Bean line : list) {
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
			BasePath.getInstance();
			try {
				Logger.getInstance(EQP_FDC_SUM05_Main.jobId, EQP_FDC_SUM05_Main.dsName + ".log");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		if (Flag.TRUE) {
			String path = BasePath.getInstance().getDataPath() + "/" + StrUtil.getDateFromJobId(EQP_FDC_SUM05_Main.jobId) + "/" + EQP_FDC_SUM05_Main.jobId;
			EQP_FDC_SUM05_CsvIo ioCsv = new EQP_FDC_SUM05_CsvIo(path);
			
			if (Flag.TRUE) {
				/*
				 * read
				 */
				List<EQP_FDC_SUM05_Bean> list = ioCsv.getList(true);
				
				for (EQP_FDC_SUM05_Bean bean : list) {
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
