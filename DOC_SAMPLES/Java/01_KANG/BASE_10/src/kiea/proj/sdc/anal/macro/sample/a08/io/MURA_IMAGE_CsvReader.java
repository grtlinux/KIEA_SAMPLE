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

package kiea.proj.sdc.anal.macro.sample.a08.io;

import java.io.FileReader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import kiea.kr.co.tain.base.Flag;
import kiea.proj.sdc.anal.macro.impl.io.AbstractCSVReader;
import kiea.proj.sdc.anal.macro.sample.a08.bean.MURA_IMAGE_ReadBean;
import au.com.bytecode.opencsv.CSVReader;
import au.com.bytecode.opencsv.bean.CsvToBean;
import au.com.bytecode.opencsv.bean.HeaderColumnNameTranslateMappingStrategy;

/**
 * @author KangSeok
 * @date 2014. 8. 5.
 * @file_name MURA_IMAGE_CsvReader.java
 *
 */
public class MURA_IMAGE_CsvReader extends AbstractCSVReader
{
	private String fileName = null;
	private List<MURA_IMAGE_ReadBean> list = null;
	
	public MURA_IMAGE_CsvReader(String fileName)
	{
		this.fileName = fileName;
	}
	
	public List<MURA_IMAGE_ReadBean> getList()
	{
		if (Flag.TRUE) {
			try {
				/*
				 * Field 명과 Bean 항목명과 맵핑한다.
				 */
				Map<String, String> map = new HashMap<String, String>();
				map.put("SITEID"     , "siteId"     );
				map.put("PROCESSID"  , "processId"  );
				map.put("PRODUCTID"  , "productId"  );
				map.put("STEPID"     , "stepId"     );
				map.put("MODULETYPE" , "moduleType" );
				map.put("EQPID"      , "eqpId"      );
				map.put("BATCHID"    , "batchId"    );
				map.put("GLASSCELLID", "glassCellId");
				map.put("ETIME"      , "ETime"      );
				map.put("IMAGEPATH"  , "imagePath"  );
				
				HeaderColumnNameTranslateMappingStrategy<MURA_IMAGE_ReadBean> mapping = new HeaderColumnNameTranslateMappingStrategy<MURA_IMAGE_ReadBean>();
				mapping.setType(MURA_IMAGE_ReadBean.class);
				mapping.setColumnMapping(map);
				
				/*
				 * CSV 파일을 읽어 Bean 리스트를 생성한다.
				 */
				CsvToBean<MURA_IMAGE_ReadBean> bean = new CsvToBean<MURA_IMAGE_ReadBean>();
				CSVReader reader = new CSVReader(new FileReader(fileName));
				list = bean.parse(mapping, reader);
				reader.close();
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return list;
	}
}
