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
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import kiea.kr.co.tain.base.Flag;
import kiea.proj.sdc.anal.macro.impl.io.AbstractCSVReader;
import kiea.proj.sdc.anal.macro.sample.a09.bean.MURA_DESC_ReadBean;
import au.com.bytecode.opencsv.CSVReader;
import au.com.bytecode.opencsv.bean.CsvToBean;
import au.com.bytecode.opencsv.bean.HeaderColumnNameTranslateMappingStrategy;

/**
 * @author KangSeok
 * @date 2014. 8. 5.
 * @file_name MURA_DESC_CsvReader.java
 *
 */
public class MURA_DESC_CsvReader extends AbstractCSVReader
{
	private String fileName = null;
	private List<MURA_DESC_ReadBean> list = null;
	private Map<String, MURA_DESC_ReadBean> map = null;
	
	public MURA_DESC_CsvReader(String fileName)
	{
		this.fileName = fileName;
	}
	
	public List<MURA_DESC_ReadBean> getList()
	{
		if (Flag.TRUE) {
			if (list == null) {
				try {
					/*
					 * Field 명과 Bean 항목명과 맵핑한다.
					 */
					Map<String, String> map = new HashMap<String, String>();
					map.put("ITEMID"  , "itemId"  );  // key
					map.put("ITEMNAME", "itemName");
					
					HeaderColumnNameTranslateMappingStrategy<MURA_DESC_ReadBean> mapping = new HeaderColumnNameTranslateMappingStrategy<MURA_DESC_ReadBean>();
					mapping.setType(MURA_DESC_ReadBean.class);
					mapping.setColumnMapping(map);
					
					/*
					 * CSV 파일을 읽어 Bean 리스트를 생성한다.
					 */
					CsvToBean<MURA_DESC_ReadBean> bean = new CsvToBean<MURA_DESC_ReadBean>();
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
	
	public MURA_DESC_ReadBean get(String itemId)
	{
		MURA_DESC_ReadBean retBean = null;
		
		if (Flag.TRUE) {
			if (map == null) {
				try {
					getList();
					
					map = new LinkedHashMap<String, MURA_DESC_ReadBean>();
					
					for (MURA_DESC_ReadBean bean : list) {
						if (!Flag.TRUE) System.out.println("[" + bean.getItemId() + "][" + bean.getItemName() + "]");
						
						map.put(bean.getItemId(), bean);
					}
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			
			retBean = map.get(itemId);
		}
		
		return retBean;
	}
	
	///////////////////////////////////////////////////////////////////////////
	
	private static void test01()
	{
		if (Flag.TRUE) {
			MURA_DESC_CsvReader reader = new MURA_DESC_CsvReader("D:/KANG/qaf/data/20140814/KEY_HHMM/MURA_DESC.csv");
			MURA_DESC_ReadBean bean = reader.get("POR");
			System.out.println("[" + bean.getItemId() + "][" + bean.getItemName() + "]");
		}
	}
	
	public static void main(String[] args)
	{
		if (Flag.TRUE) test01();
	}
}
