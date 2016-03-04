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

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


/**
 * @author KangSeok
 * @date 2014. 8. 27.
 * @file_name CSVParser.java
 *
 */
public class CSVParser
{
	private final char separator;
	private final char quotechar;
	private final char escape;
	private final boolean strictQuotes;
	
	private String pending;
	private boolean inField = false;
	
	private final boolean ignoreLeadingWhiteSpace;
	
	public static final char DEFAULT_SEPARATOR = ',';
	public static final int INITIAL_READ_SIZE = 128;
	public static final char DEFAULT_QUOTE_CHARACTER = '"';
	public static final char DEFAULT_ESCAPE_CHARACTER = '\\';
	public static final boolean DEFAULT_STRICT_QUOTES = false;
	public static final boolean DEFAULT_IGNORE_LEADING_WHITESPACE = true;
	public static final char NULL_CHARACTER = '\0';
	
	public CSVParser()
	{
		this(DEFAULT_SEPARATOR, DEFAULT_QUOTE_CHARACTER, DEFAULT_ESCAPE_CHARACTER);
	}
	
	public CSVParser(char separator)
	{
		this(separator, DEFAULT_QUOTE_CHARACTER, DEFAULT_ESCAPE_CHARACTER);
	}
	
	public CSVParser(char separator, char quotechar)
	{
		this(separator, quotechar, DEFAULT_ESCAPE_CHARACTER);
	}
	
	public CSVParser(char separator, char quotechar, char escape)
	{
		this(separator, quotechar, escape, DEFAULT_STRICT_QUOTES);
	}
	
	public CSVParser(char separator, char quotechar, char escape, boolean strictQuotes)
	{
		this(separator, quotechar, escape, strictQuotes, DEFAULT_IGNORE_LEADING_WHITESPACE);
	}
	
	
	public CSVParser(char separator, char quotechar, char escape, boolean strictQuotes, boolean ignoreLeadingWhiteSpace)
	{
		if (anyCharactersAreTheSame(separator, quotechar, escape)) {
			throw new UnsupportedOperationException("The separator, quote, and escape characters must be different");
		}
		if (separator == NULL_CHARACTER) {
			throw new UnsupportedOperationException("the separator character must be defined");
		}
		this.separator = separator;
		this.quotechar = quotechar;
		this.escape = escape;
		this.strictQuotes = strictQuotes;
		this.ignoreLeadingWhiteSpace = ignoreLeadingWhiteSpace;
	}
	
	private boolean anyCharactersAreTheSame(char separator, char quotechar, char escape)
	{
		return isSameCharacter(separator, quotechar) || isSameCharacter(separator,  escape) || isSameCharacter(quotechar, escape);
	}
	
	private boolean isSameCharacter(char c1, char c2)
	{
		return c1 != NULL_CHARACTER && c1 == c2;
	}
	
	public boolean isPending()
	{
		return pending != null;
	}
	
	public String[] parseLineMulti(String nextLine) throws IOException
	{
		return parseLine(nextLine, true);
	}
	
	public String[] parseLine(String nextLine) throws IOException
	{
		return parseLine(nextLine, false);
	}
	
	private String[] parseLine(String nextLine, boolean multi) throws IOException
	{
		if (!multi && pending != null) {
			pending = null;
		}
		
		if (nextLine == null) {
			if (pending != null) {
				String s = pending;
				pending = null;
				return new String[] {s};
			} else {
				return null;
			}
		}
		
		List<String> tokensOnThisLine = new ArrayList<String>();
		StringBuilder sb = new StringBuilder(INITIAL_READ_SIZE);
		boolean inQuotes = false;
		if (pending != null) {
			sb.append(pending);
			pending = null;
			inQuotes = true;
		}
		
		for (int i=0; i < nextLine.length(); i++) {
			char c = nextLine.charAt(i);
			if (c == this.escape) {
				if (isNextCharacterEscapable(nextLine, inQuotes || inField, i)) {
					sb.append(nextLine.charAt(i + 1));
					i++;
				}
			} else if (c == quotechar) {
				if (isNextCharacterEscapeQuote(nextLine, inQuotes || inField, i)) {
					sb.append(nextLine.charAt(i + 1));
					i++;
				} else {
					if (!strictQuotes) {
						if (i > 2
								&& nextLine.charAt(i - 1) != this.separator
								&& nextLine.length() > (i + 1)
								&& nextLine.charAt(i + 1) != this.separator
								) {
							if (ignoreLeadingWhiteSpace && sb.length() > 0 && isAllWhiteSpace(sb)) {
								sb.setLength(0);
							} else {
								sb.append(c);
							}
						}
					}
					inQuotes = !inQuotes;
				}
				inField = !inField;
			} else if (c == separator && !inQuotes) {
				tokensOnThisLine.add(sb.toString());
				sb.setLength(0);
				inField = false;
			} else {
				if (!strictQuotes || inQuotes) {
					sb.append(c);
					inField = true;
				}
			}
		}
		
		if (inQuotes) {
			if (multi) {
				sb.append("\n");
				pending = sb.toString();
				sb = null;
			} else {
				throw new IOException("Un-terminated quoted field at end of CSV line");
			}
		}
		
		if (sb != null) {
			tokensOnThisLine.add(sb.toString());
		}
		
		return tokensOnThisLine.toArray(new String[tokensOnThisLine.size()]);
	}
	
	private boolean isNextCharacterEscapeQuote(String nextLine, boolean inQuotes, int i)
	{
		return inQuotes
				&& nextLine.length() > (i + 1)
				&& nextLine.charAt(i + 1) == quotechar;
	}
	
	protected boolean isNextCharacterEscapable(String nextLine, boolean inQuotes, int i)
	{
		return inQuotes
				&& nextLine.length() > (i + 1)
				&& (nextLine.charAt(i + 1) == quotechar || nextLine.charAt(i + 1) == this.escape);
	}
	
	protected boolean isAllWhiteSpace(CharSequence sb)
	{
		boolean result = true;
		for (int i=0; i < sb.length(); i++) {
			char c = sb.charAt(i);
			if (!Character.isWhitespace(c)) {
				return false;
			}
		}
		return result;
	}
}
