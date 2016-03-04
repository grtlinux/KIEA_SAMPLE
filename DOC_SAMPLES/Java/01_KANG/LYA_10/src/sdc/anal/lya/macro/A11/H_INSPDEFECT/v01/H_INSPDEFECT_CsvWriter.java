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

package sdc.anal.lya.macro.A11.H_INSPDEFECT.v01;

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
 * @file_name AIB_H_INSPDEFECT_CsvWriter.java
 *
 */
public class H_INSPDEFECT_CsvWriter extends AbstractCSVWriter
{
	private String fileName = null;
	private List<H_INSPDEFECT_WriteBean> list = null;
	
	public H_INSPDEFECT_CsvWriter(String filePath)
	{
		this.fileName = filePath + FileValue.SEP + H_INSPDEFECT_Main.dsName + ".csv";
	}

	/**
	 * getList
	 */
	@Override
	public List<H_INSPDEFECT_WriteBean> getList()
	{
		return getList(false);
	}
	
	public List<H_INSPDEFECT_WriteBean> getList(boolean flgRead)
	{
		if (Flag.TRUE) {
			
			if (!flgRead) {
				/*
				 * if flgRead = true, 초기에 읽지않는다.
				 */
				list = new ArrayList<H_INSPDEFECT_WriteBean>();
				return list;
			}
			
			try {
				/*
				 * Field 명과 Bean 항목명과 맵핑한다.
				 */
				Map<String, String> map = new HashMap<String, String>();
				map.put("CELLID"            , "cellId"            );
				map.put("GLASSID"           , "glassId"           );
				map.put("SITEID"            , "siteId"            );
				map.put("INSPSTEPTYPE"      , "inspStepType"      );
				map.put("DECGRADECD"        , "decGradeCd"        );
				map.put("INSPDATE"          , "inspDate"          );
				map.put("INSPHOUR"          , "inspHour"          );
				map.put("INSPEQPID"         , "inspEqpId"         );
				map.put("BATCHID"           , "batchId"           );
				map.put("PRODTYPE"          , "prodType"          );
				map.put("PRODID"            , "prodId"            );
				map.put("PROCID"            , "procId"            );
				map.put("INSPSTEPID"        , "inspStepId"        );
				map.put("BINGRADECD"        , "binGradeCd"        );
				map.put("MATCHLOTTYPE"      , "matchLotType"      );
				map.put("DEFECTCD"          , "defectCd"          );
				map.put("WORKERID"          , "workerId"          );
				map.put("CELLLOCID"         , "cellLocId"         );
				map.put("DEFECTSYSID"       , "defectSysId"       );
				map.put("OUTSITEID"         , "outSiteId"         );
				map.put("USERDEF01"         , "userDef01"         );
				map.put("DEFECTQTY"         , "defectQty"         );
				map.put("ETLMODIFYDATE"     , "etlModifyDate"     );
				map.put("ALTERETLMODIFYDATE", "alterEtlModifyDate");
				
				HeaderColumnNameTranslateMappingStrategy<H_INSPDEFECT_WriteBean> mapping = new HeaderColumnNameTranslateMappingStrategy<H_INSPDEFECT_WriteBean>();
				mapping.setType(H_INSPDEFECT_WriteBean.class);
				mapping.setColumnMapping(map);
				
				/*
				 * CSV 파일을 읽어 Bean 리스트를 생성한다.
				 */
				CsvToBean<H_INSPDEFECT_WriteBean> bean = new CsvToBean<H_INSPDEFECT_WriteBean>();
				CSVReader reader = new CSVReader(new FileReader(fileName));
				list = bean.parse(mapping, reader);
				reader.close();
			
			} catch (FileNotFoundException e) {
				list = new ArrayList<H_INSPDEFECT_WriteBean>();
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
				writer.writeNext(H_INSPDEFECT_WriteBean.getHeader());
				
				for (H_INSPDEFECT_WriteBean line : list) {
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
			String path = BasePath.getInstance().getDataPath() + "/" + StrUtil.getDateFromJobId(H_INSPDEFECT_Main.jobId) + "/" + H_INSPDEFECT_Main.jobId;
			H_INSPDEFECT_CsvWriter csv = new H_INSPDEFECT_CsvWriter(path);
			
			if (Flag.TRUE) {
				/*
				 * read
				 */
				List<H_INSPDEFECT_WriteBean> list = csv.getList(true);
				
				for (H_INSPDEFECT_WriteBean bean : list) {
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
