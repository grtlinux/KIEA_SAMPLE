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

package kiea.z01.au.com.bytecode.opencsv.t01.bean;

import java.io.IOException;

import kiea.z01.au.com.bytecode.opencsv.t01.CSVReader;

/**
 * @author KangSeok
 * @date 2014. 8. 27.
 * @file_name ColumnPositonMappingStrategy.java
 *
 */
public class ColumnPositonMappingStrategy<T> extends HeaderColumnNameMappingStrategy<T>
{
	private String[] columnMapping = new String[] {};
	
	public void capturedHeader(CSVReader reader) throws IOException
	{
		// do nothing, first line is not header
	}
	
	protected String getColumnName(int col)
	{
		return (columnMapping != null && col < columnMapping.length) ? columnMapping[col] : null;
	}
	
	public String[] getColumnMapping()
	{
		return this.columnMapping != null ? this.columnMapping.clone() : null;
	}
	
	public void setColumnMapping(String[] columnMapping)
	{
		this.columnMapping = columnMapping != null ? columnMapping.clone() : null;
	}
}
