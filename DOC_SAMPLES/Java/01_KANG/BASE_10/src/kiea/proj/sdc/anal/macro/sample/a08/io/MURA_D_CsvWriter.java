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

import java.util.ArrayList;
import java.util.List;

import kiea.kr.co.tain.base.Flag;
import kiea.proj.sdc.anal.macro.impl.io.AbstractCSVWriter;
import kiea.proj.sdc.anal.macro.sample.a08.bean.MURA_D_WriteBean;

import org.apache.commons.io.output.FileWriterWithEncoding;

import au.com.bytecode.opencsv.CSVWriter;

/**
 * @author KangSeok
 * @date 2014. 8. 8.
 * @file_name MURA_D_CsvWriter.java
 *
 */
public class MURA_D_CsvWriter extends AbstractCSVWriter
{
	private String fileName = null;
	private List<MURA_D_WriteBean> list = null;
	
	public MURA_D_CsvWriter(String fileName)
	{
		this.fileName = fileName;
	}

	/**
	 * getList
	 */
	@Override
	public List<MURA_D_WriteBean> getList()
	{
		list = new ArrayList<MURA_D_WriteBean>();

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
				 * 
				 */

				//CSVWriter writer = new CSVWriter(new FileWriter(fileName));  // default seperator
				CSVWriter writer = new CSVWriter(new FileWriterWithEncoding(fileName, "EUC-KR"));  // default seperator
				//CSVWriter writer = new CSVWriter(new FileWriterWithEncoding(fileName, "US-ASCII"));  // default seperator

				// Header 출력
				writer.writeNext(MURA_D_WriteBean.getHeader());
				
				for (MURA_D_WriteBean line : list) {
					// Data 출력
					writer.writeNext(line.getStringArray());
				}
				
				writer.close();
			
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
