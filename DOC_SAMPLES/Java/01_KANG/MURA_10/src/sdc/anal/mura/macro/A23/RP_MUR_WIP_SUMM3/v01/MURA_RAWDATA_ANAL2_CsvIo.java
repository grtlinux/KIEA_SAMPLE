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

package sdc.anal.mura.macro.A23.RP_MUR_WIP_SUMM3.v01;

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
 * @date 2014. 8. 8.
 * @file_name AIB_INSPCT_HIST_CsvIo.java
 *
 */
public class MURA_RAWDATA_ANAL2_CsvIo extends AbstractCSVReader
{
	private String fileName = null;
	private List<MURA_RAWDATA_ANAL2_Bean> list = null;
	
	public MURA_RAWDATA_ANAL2_CsvIo(String filePath)
	{
		this.fileName = filePath + FileValue.SEP + "A21_" + "MURA_RAWDATA_ANAL2.csv";
	}

	/**
	 * getList
	 */
	@Override
	public List<MURA_RAWDATA_ANAL2_Bean> getList()
	{
		return getList(false);
	}
	
	public List<MURA_RAWDATA_ANAL2_Bean> getList(boolean flgRead)
	{
		if (Flag.TRUE) {
			
			if (!flgRead) {
				/*
				 * if flgRead = true, 초기에 읽지않는다.
				 */
				list = new ArrayList<MURA_RAWDATA_ANAL2_Bean>();
				return list;
			}
			
			try {
				/*
				 * Field 명과 Bean 항목명과 맵핑한다.
				 */
				Map<String, String> map = new HashMap<String, String>();
				map.put("PROCID"       , "procId"       );
				map.put("SITEID"       , "siteId"       );
				map.put("CELLID"       , "cellId"       );
				map.put("GLASSID"      , "glassId"      );
				map.put("PRODGRPNAME"  , "prodGrpName"  );
				map.put("PRODID"       , "prodId"       );
				map.put("ORGSTEPID"    , "orgStepId"    );
				map.put("MEASEQPUNITID", "measEqpUnitId");
				map.put("DCOLTIME"     , "dcolTime"     );
				map.put("ITEMID"       , "itemId"       );
				map.put("SUBITEMID"    , "subItemId"    );
				map.put("DEFECTNAME"   , "defectName"   );
				map.put("DATAVALUE"    , "dataValue"    );
				map.put("GATELINE"     , "gateLine"     );
				map.put("DATALINE"     , "dataLine"     );
				map.put("GATELINE2"    , "gateLine2"    );
				map.put("DATALINE2"    , "dataLine2"    );
				map.put("PRODTYPE"     , "prodType"     );
				map.put("CELL_LOC_ID"  , "cellLocId"    );
				map.put("WIDTH"        , "width"        );
				map.put("HEIGHT"       , "height"       );
				map.put("X_VALUE"      , "XValue"       );
				map.put("Y_VALUE"      , "YValue"       );
				map.put("X2_VALUE"     , "x2Value"      );
				map.put("Y2_VALUE"     , "y2Value"      );
				map.put("D_TIME"       , "DTime"        );
				map.put("MURA_DATA"    , "muraData"     );
				map.put("GATELINE_1"   , "gateLine_1"   );
				map.put("GATELINE_2"   , "gateLine_2"   );
				map.put("DATALINE_1"   , "dataLine_1"   );
				map.put("DATALINE_2"   , "dataLine_2"   );
				map.put("AVG_X"        , "avgX"         );
				map.put("AVG_Y"        , "avgY"         );
				
				HeaderColumnNameTranslateMappingStrategy<MURA_RAWDATA_ANAL2_Bean> mapping = new HeaderColumnNameTranslateMappingStrategy<MURA_RAWDATA_ANAL2_Bean>();
				mapping.setType(MURA_RAWDATA_ANAL2_Bean.class);
				mapping.setColumnMapping(map);
				
				/*
				 * CSV 파일을 읽어 Bean 리스트를 생성한다.
				 */
				CsvToBean<MURA_RAWDATA_ANAL2_Bean> bean = new CsvToBean<MURA_RAWDATA_ANAL2_Bean>();
				//CSVReader reader = new CSVReader(new FileReader(fileName));
				CSVReader reader = new CSVReader(new InputStreamReader(new FileInputStream(fileName), "EUC-KR"));
				list = bean.parse(mapping, reader);
				reader.close();
			
			} catch (FileNotFoundException e) {
				list = new ArrayList<MURA_RAWDATA_ANAL2_Bean>();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		return list;
	}

	/**
	 * writeList
	 */
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
				writer.writeNext(MURA_RAWDATA_ANAL2_Bean.getHeader());
				
				for (MURA_RAWDATA_ANAL2_Bean line : list) {
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
			SystemProperties.setTesting("020");
			BasePath.getInstance();
			try {
				Logger.getInstance(RP_MUR_WIP_SUMM3_Main.jobId, RP_MUR_WIP_SUMM3_Main.dsName + ".log");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		if (Flag.TRUE) {
			String path = BasePath.getInstance().getDataPath() + "/" + StrUtil.getDateFromJobId(RP_MUR_WIP_SUMM3_Main.jobId) + "/" + RP_MUR_WIP_SUMM3_Main.jobId;
			MURA_RAWDATA_ANAL2_CsvIo ioCsv = new MURA_RAWDATA_ANAL2_CsvIo(path);
			
			if (Flag.TRUE) {
				/*
				 * read
				 */
				List<MURA_RAWDATA_ANAL2_Bean> list = ioCsv.getList(true);
				
				for (MURA_RAWDATA_ANAL2_Bean bean : list) {
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
