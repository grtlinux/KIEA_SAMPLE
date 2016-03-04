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

package sdc.anal.mura.macro.A12.EQP_MURA_TRD.v04;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
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
 * @file_name MURA_DESC_CsvReader.java
 *
 */
public class MURA_SPC_CsvIo extends AbstractCSVReader
{
	private String fileName = null;
	private List<MURA_SPC_Bean> list = null;
	
	public MURA_SPC_CsvIo(String filePath)
	{
		this.fileName = filePath + FileValue.SEP + "A11_MURA_SPC.csv";
	}

	/**
	 * getList
	 */
	@Override
	public List<MURA_SPC_Bean> getList()
	{
		return getList(false);
	}
	
	public List<MURA_SPC_Bean> getList(boolean flgRead)
	{
		if (Flag.TRUE) {
			
			if (!flgRead) {
				/*
				 * if flgRead = true, 초기에 읽지않는다.
				 */
				list = new ArrayList<MURA_SPC_Bean>();
				return list;
			}
			
			try {
				/*
				 * Field 명과 Bean 항목명과 맵핑한다.
				 */
				Map<String, String> map = new HashMap<String, String>();
				map.put("PROCID"   , "procId"   );
				map.put("CELLLOCID", "cellLocId");
				map.put("COLIDX"   , "colIdx"   );
				map.put("ROWIDX"   , "rowIdx"   );
				
				HeaderColumnNameTranslateMappingStrategy<MURA_SPC_Bean> mapping = new HeaderColumnNameTranslateMappingStrategy<MURA_SPC_Bean>();
				mapping.setType(MURA_SPC_Bean.class);
				mapping.setColumnMapping(map);
				
				/*
				 * CSV 파일을 읽어 Bean 리스트를 생성한다.
				 */
				CsvToBean<MURA_SPC_Bean> bean = new CsvToBean<MURA_SPC_Bean>();
				CSVReader reader = new CSVReader(new FileReader(fileName));
				list = bean.parse(mapping, reader);
				reader.close();
			
			} catch (FileNotFoundException e) {
				list = new ArrayList<MURA_SPC_Bean>();
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
				writer.writeNext(MURA_SPC_Bean.getHeader());
				
				for (MURA_SPC_Bean line : list) {
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

	private Map<String, MURA_SPC_Bean> map = null;

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
					
					map = new LinkedHashMap<String, MURA_SPC_Bean>();
					
					for (MURA_SPC_Bean bean : list) {
						String key = bean.getGlassId();
						
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
	public MURA_SPC_Bean get(String procId, String cellLocId)
	{
		MURA_SPC_Bean retBean = null;
		
		if (Flag.TRUE) {
			setMap();
			
			retBean = map.get(procId + "~" + cellLocId);
		}
		
		return retBean;
	}
	
	/**
	 * 
	 * getListProcId
	 *
	 * @param procId
	 * @return
	 */
	public List<MURA_SPC_Bean> getListGlassId(String glassId)
	{
		List<MURA_SPC_Bean> list = new ArrayList<MURA_SPC_Bean>();
		
		if (Flag.TRUE) {
			if (this.list == null)
				getList();
			
			for (MURA_SPC_Bean bean : this.list) {
				if (glassId.equals(bean.getGlassId())) {
					list.add(bean);
				}
			}
		}
		
		if (!Flag.TRUE) {
			setMap();
			
			for (MURA_SPC_Bean bean : this.map.values()) {
				if (glassId.equals(bean.getGlassId())) {
					list.add(bean);
				}
			}
		}
		
		return list;
	}
	
	///////////////////////////////////////////////////////////////////////////
	
	private static void test01()
	{
		if (Flag.TRUE) {
			SystemProperties.setTesting("020");
		}

		if (Flag.TRUE) {
			String path = BasePath.getInstance().getDataPath() + "/" + StrUtil.getDateFromJobId(EQP_MURA_TRD_Main.jobId) + "/" + EQP_MURA_TRD_Main.jobId;
			MURA_SPC_CsvIo csv = new MURA_SPC_CsvIo(path);
			
			if (Flag.TRUE) {
				/*
				 * read
				 */
				List<MURA_SPC_Bean> list = csv.getList(true);
				
				for (MURA_SPC_Bean bean : list) {
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
