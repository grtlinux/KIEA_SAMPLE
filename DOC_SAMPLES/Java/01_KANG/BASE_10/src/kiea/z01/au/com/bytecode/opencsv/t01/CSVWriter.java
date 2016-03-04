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

package kiea.z01.au.com.bytecode.opencsv.t01;

import java.io.Closeable;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * @author KangSeok
 * @date 2014. 8. 28.
 * @file_name CSVWriter.java
 *
 */
public class CSVWriter implements Closeable
{
	public static final int INITIAL_STRING_SIZE = 128;
	private Writer rawWriter;
	private PrintWriter pw;
	private char separator;
	private char quotechar;
	private char escapechar;
	private String lineEnd;
	
	public static final char DEFAULT_ESCAPE_CHARACTER = '"';
	public static final char DEFAULT_SEPARATOR = ',';
	public static final char DEFAULT_QUOTE_CHARACTER = '"';
	public static final char NO_QUOTE_CHARACTER = '\u0000';
	public static final char NO_ESCAPE_CHARACTER = '\u0000';
	public static final String DEFAULT_LINE_END = "\n";
	
	private ResultSetHelper resultService = new ResultSetHelperService();
	
	public CSVWriter(Writer writer)
	{
		this(writer, DEFAULT_SEPARATOR);
	}
	
	public CSVWriter(Writer writer, char separator)
	{
		this(writer, separator, DEFAULT_QUOTE_CHARACTER);
	}
	
	public CSVWriter(Writer writer, char separator, char quotechar)
	{
		this(writer, separator, quotechar, DEFAULT_ESCAPE_CHARACTER);
	}
	
	public CSVWriter(Writer writer, char separator, char quotechar, char escapechar)
	{
		this(writer, separator, quotechar, escapechar, DEFAULT_LINE_END);
	}
	
	public CSVWriter(Writer writer, char separator, char quotechar, char escapechar, String lineEnd)
	{
		this.rawWriter = writer;
		this.pw = new PrintWriter(writer);
		this.separator = separator;
		this.quotechar = quotechar;
		this.escapechar = escapechar;
		this.lineEnd = lineEnd;
	}
	
	public void writeAll(List<String[]> allLines)
	{
		for (String[] line : allLines) {
			writeNext(line);
		}
	}
	
	protected void writeColumnNames(ResultSet rs) throws SQLException
	{
		writeNext(resultService.getColumnNames(rs));
	}
	
	public void writeAll(ResultSet rs, boolean includeColumnNames) throws SQLException, IOException
	{
		if (includeColumnNames) {
			writeColumnNames(rs);
		}
		
		while (rs.next()) {
			writeNext(resultService.getColumnValues(rs));
		}
	}
	
	public void writeNext(String[] nextLine)
	{
		if (nextLine == null)
			return;
		
		StringBuilder sb = new StringBuilder(INITIAL_STRING_SIZE);
		for (int i=0; i < nextLine.length; i++) {
			if (i != 0) {
				sb.append(separator);
			}
			
			String nextElement = nextLine[i];
			if (nextElement == null)
				continue;
			if (quotechar != NO_QUOTE_CHARACTER)
				sb.append(quotechar);
			
			sb.append(stringContainsSpecialCharacters(nextElement) ? processLine(nextElement) : nextElement);
			
			if (quotechar != NO_QUOTE_CHARACTER)
				sb.append(quotechar);
		}
		
		sb.append(lineEnd);
		pw.write(sb.toString());
	}
	
	private boolean stringContainsSpecialCharacters(String line)
	{
		return line.indexOf(quotechar) != -1 || line.indexOf(escapechar) != -1;
	}
	
	protected StringBuilder processLine(String nextElement)
	{
		StringBuilder sb = new StringBuilder(INITIAL_STRING_SIZE);
		
		for (int j=0; j < nextElement.length(); j++) {
			char nextChar = nextElement.charAt(j);
			if (escapechar != NO_ESCAPE_CHARACTER && nextChar == quotechar) {
				sb.append(escapechar).append(nextChar);
			} else if (escapechar != NO_ESCAPE_CHARACTER && nextChar == escapechar) {
				sb.append(escapechar).append(nextChar);
			} else {
				sb.append(nextChar);
			}
		}
		
		return sb;
	}
	
	public void flush() throws IOException
	{
		pw.flush();
	}
	
	public void close() throws IOException
	{
		flush();
		pw.close();
		rawWriter.close();
	}
	
	public boolean checkError()
	{
		return pw.checkError();
	}
	
	public void setResultService(ResultSetHelper resultService)
	{
		this.resultService = resultService;
	}
}
