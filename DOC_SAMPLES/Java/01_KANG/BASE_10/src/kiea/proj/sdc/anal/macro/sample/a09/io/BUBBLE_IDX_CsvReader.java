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

package kiea.proj.sdc.anal.macro.sample.a09.io;

import java.io.FileReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import kiea.kr.co.tain.base.Flag;
import kiea.proj.sdc.anal.macro.impl.io.AbstractCSVReader;
import kiea.proj.sdc.anal.macro.sample.a09.bean.BUBBLE_IDX_ReadBean;
import au.com.bytecode.opencsv.CSVReader;
import au.com.bytecode.opencsv.bean.CsvToBean;
import au.com.bytecode.opencsv.bean.HeaderColumnNameTranslateMappingStrategy;

/**
 * @author KangSeok
 * @date 2014. 8. 5.
 * @file_name BUBBLE_IDX_CsvReader.java
 *
 */
public class BUBBLE_IDX_CsvReader extends AbstractCSVReader
{
	private String fileName = null;
	private List<BUBBLE_IDX_ReadBean> list = null;
	private Map<String, BUBBLE_IDX_ReadBean> map = null;
	
	public BUBBLE_IDX_CsvReader(String fileName)
	{
		this.fileName = fileName;
	}
	
	/**
	 * 
	 * getList
	 *
	 * @return
	 */
	public List<BUBBLE_IDX_ReadBean> getList()
	{
		if (Flag.TRUE) {
			if (list == null) {
				try {
					/*
					 * Field 명과 Bean 항목명과 맵핑한다.
					 */
					Map<String, String> map = new HashMap<String, String>();
					map.put("PROCID"   , "procId"   );  // key
					map.put("CELLLOCID", "cellLocId");  // key
					map.put("COLIDX"   , "colIdx"   );
					map.put("ROWIDX"   , "rowIdx"   );
					map.put("POINT_X"  , "pointX"   );
					map.put("POINT_Y"  , "pointY"   );
					
					HeaderColumnNameTranslateMappingStrategy<BUBBLE_IDX_ReadBean> mapping = new HeaderColumnNameTranslateMappingStrategy<BUBBLE_IDX_ReadBean>();
					mapping.setType(BUBBLE_IDX_ReadBean.class);
					mapping.setColumnMapping(map);
					
					/*
					 * CSV 파일을 읽어 Bean 리스트를 생성한다.
					 */
					CsvToBean<BUBBLE_IDX_ReadBean> bean = new CsvToBean<BUBBLE_IDX_ReadBean>();
					CSVReader reader = new CSVReader(new FileReader(fileName));
					list = bean.parse(mapping, reader);
					reader.close();
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		return list;
	}
	
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
					
					map = new LinkedHashMap<String, BUBBLE_IDX_ReadBean>();
					
					for (BUBBLE_IDX_ReadBean bean : list) {
						String key = bean.getProcId() + "~" + bean.getCellLocId();
						
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
	public BUBBLE_IDX_ReadBean get(String procId, String cellLocId)
	{
		BUBBLE_IDX_ReadBean retBean = null;
		
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
	public List<BUBBLE_IDX_ReadBean> getListProcId(String procId)
	{
		List<BUBBLE_IDX_ReadBean> list = new ArrayList<BUBBLE_IDX_ReadBean>();
		
		if (Flag.TRUE) {
			setMap();
			
			for (BUBBLE_IDX_ReadBean bean : map.values()) {
				if (procId.equals(bean.getProcId())) {
					list.add(bean);
				}
			}
		}
		
		return list;
	}
	
	/**
	 * 
	 * getListColIdx
	 *
	 * @param colIdx
	 * @return
	 */
	public List<BUBBLE_IDX_ReadBean> getListColIdx(String colIdx)
	{
		List<BUBBLE_IDX_ReadBean> list = new ArrayList<BUBBLE_IDX_ReadBean>();
		
		if (Flag.TRUE) {
			setMap();
			
			for (BUBBLE_IDX_ReadBean bean : map.values()) {
				if (colIdx.equals(bean.getColIdx())) {
					list.add(bean);
				}
			}
		}
		
		return list;
	}
	
	///////////////////////////////////////////////////////////////////////////
	
	/**
	 * 
	 * test01
	 *
	 */
	private static void test01()
	{
		if (Flag.TRUE) {
			String file = "D:/KANG/qaf/data/20140816/KEY_HHMM/BUBBLE_IDX.csv";
			BUBBLE_IDX_CsvReader reader = new BUBBLE_IDX_CsvReader(file);
			
			List<BUBBLE_IDX_ReadBean> list = reader.getListProcId("L6156H11");
			for (BUBBLE_IDX_ReadBean bean : list) {
				System.out.println(bean);
			}
			
			System.out.println("--------------------------------");
			
			list = reader.getListColIdx("3");
			for (BUBBLE_IDX_ReadBean bean : list) {
				System.out.println(bean);
			}
			
			System.out.println("--------------------------------");
		}
	}
	
	/**
	 * 
	 * main
	 *
	 * @param args
	 */
	public static void main(String[] args)
	{
		if (Flag.TRUE) test01();
	}
}
