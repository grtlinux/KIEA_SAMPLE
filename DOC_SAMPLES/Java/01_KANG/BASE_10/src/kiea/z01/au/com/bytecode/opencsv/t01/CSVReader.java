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

import java.io.BufferedReader;
import java.io.Closeable;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;

/**
 * @author KangSeok
 * @date 2014. 8. 27.
 * @file_name CSVReader.java
 *
 */
public class CSVReader implements Closeable
{
	private BufferedReader br;
	private boolean hasNext = true;
	private CSVParser parser;
	private int skipLines;
	private boolean linesSkiped;
	
	public static final int DEFAULT_SKIP_LINES = 0;
	
	public CSVReader(Reader reader)
	{
		this(reader, CSVParser.DEFAULT_SEPARATOR, CSVParser.DEFAULT_QUOTE_CHARACTER, CSVParser.DEFAULT_ESCAPE_CHARACTER);
	}
	
	public CSVReader(Reader reader, char separator)
	{
		this(reader, separator, CSVParser.DEFAULT_QUOTE_CHARACTER, CSVParser.DEFAULT_ESCAPE_CHARACTER);
	}
	
	public CSVReader(Reader reader, char separator, char quotechar)
	{
		this(reader, separator, quotechar, CSVParser.DEFAULT_ESCAPE_CHARACTER, DEFAULT_SKIP_LINES, CSVParser.DEFAULT_STRICT_QUOTES);
	}
	
	public CSVReader(Reader reader, char separator, char quotechar, boolean strictQuotes)
	{
		this(reader, separator, quotechar, CSVParser.DEFAULT_ESCAPE_CHARACTER, DEFAULT_SKIP_LINES, strictQuotes);
	}
	
	public CSVReader(Reader reader, char separator, char quotechar, char escape)
	{
		this(reader, separator, quotechar, escape, DEFAULT_SKIP_LINES, CSVParser.DEFAULT_STRICT_QUOTES);
	}
	
	public CSVReader(Reader reader, char separator, char quotechar, int line)
	{
		this(reader, separator, quotechar, CSVParser.DEFAULT_ESCAPE_CHARACTER, line, CSVParser.DEFAULT_STRICT_QUOTES);
	}
	
	public CSVReader(Reader reader, char separator, char quotechar, char escape, int line)
	{
		this(reader, separator, quotechar, escape, line, CSVParser.DEFAULT_STRICT_QUOTES);
	}
	
	public CSVReader(Reader reader, char separator, char quotechar, char escape, int line, boolean strictQuotes)
	{
		this(reader, separator, quotechar, escape, line, strictQuotes, CSVParser.DEFAULT_IGNORE_LEADING_WHITESPACE);
	}
	
	public CSVReader(Reader reader, char separator, char quotechar, char escape, int line, boolean strictQuotes, boolean ignoreLeadingWhiteSpace)
	{
		this.br = new BufferedReader(reader);
		this.parser = new CSVParser(separator, quotechar, escape, strictQuotes, ignoreLeadingWhiteSpace);
		this.skipLines = line;
	}
	
	public List<String[]> readAll() throws IOException
	{
		List<String[]> allElements = new ArrayList<String[]>();
		while (hasNext) {
			String[] nextLineAsTokens = readNext();
			if (nextLineAsTokens != null)
				allElements.add(nextLineAsTokens);
		}
		return allElements;
	}
	
	public String[] readNext() throws IOException
	{
		String[] result = null;
		
		do {
			String nextLine = getNextLine();
			if (!hasNext) {
				return result;
			}
			
			String[] r = parser.parseLineMulti(nextLine);
			if (r.length > 0) {
				if (result == null) {
					return r;
				} else {
					String[] t = new String[result.length + r.length];
					System.arraycopy(result, 0, t, 0, result.length);
					System.arraycopy(r, 0, t, result.length, r.length);
					result = t;
				}
			}
		} while (parser.isPending());
		
		return result;
	}
	
	private String getNextLine() throws IOException
	{
		if (!this.linesSkiped) {
			for (int i=0; i < skipLines; i++) {
				br.readLine();
			}
			this.linesSkiped = true;
		}
		String nextLine = br.readLine();
		if (nextLine == null) {
			hasNext = false;
		}
		return hasNext ? nextLine : null;
	}
	
	public void close() throws IOException
	{
		br.close();
	}
}
