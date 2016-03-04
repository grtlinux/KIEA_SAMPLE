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
import java.io.Reader;
import java.math.BigDecimal;
import java.sql.Clob;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Time;
import java.sql.Timestamp;
import java.sql.Types;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * @author KangSeok
 * @date 2014. 8. 28.
 * @file_name ResultSetHelperService.java
 *
 */
public class ResultSetHelperService implements ResultSetHelper
{
	public static final int CLOBBUFFERSIZE = 2048;
	
	private static final int NVARCHAR = -9;
	private static final int NCHAR = -15;
	private static final int LONGNVARCHAR = -16;
	private static final int NCLOB = 2011;
	
	public String[] getColumnNames(ResultSet rs) throws SQLException
	{
		List<String> names = new ArrayList<String>();
		ResultSetMetaData metaData = rs.getMetaData();
		
		for (int i=0; i < metaData.getColumnCount(); i++) {
			names.add(metaData.getColumnName(i + 1));
		}
		
		return names.toArray(new String[names.size()]);
	}
	
	public String[] getColumnValues(ResultSet rs) throws SQLException, IOException
	{
		List<String> values = new ArrayList<String>();
		ResultSetMetaData metaData = rs.getMetaData();
		
		for (int i=0; i < metaData.getColumnCount(); i++) {
			values.add(getColumnValue(rs, metaData.getColumnType(i+1), i+1));
		}
		
		return values.toArray(new String[values.size()]);
	}
	
	private String handleObject(Object obj)
	{
		return obj == null ? "" : String.valueOf(obj);
	}
	
	private String handleBigDecimal(BigDecimal decimal)
	{
		return decimal == null ? "" : decimal.toString();
	}
	
	@SuppressWarnings("unused")
	private String handleLong(ResultSet rs, int columnIndex) throws SQLException
	{
		long lv = rs.getLong(columnIndex);
		return rs.wasNull() ? "" : Long.toString(lv);
	}
	
	private String handleInteger(ResultSet rs, int columnIndex) throws SQLException
	{
		int i = rs.getInt(columnIndex);
		return rs.wasNull() ? "" : Integer.toString(i);
	}
	
	private String handleDate(ResultSet rs, int columnIndex) throws SQLException
	{
		Date date = rs.getDate(columnIndex);
		String value = null;
		if (date != null) {
			SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MMM-yyyy");
			value = dateFormat.format(date);
		}
		return value;
	}
	
	private String handleTime(Time time)
	{
		return time == null ? null : time.toString();
	}
	
	private String handleTimestamp(Timestamp timestamp)
	{
		SimpleDateFormat timeFormat = new SimpleDateFormat("dd-MMM-yyyy HH:mm:ss");
		return timestamp == null ? null : timeFormat.format(timestamp);
	}
	
	private String getColumnValue(ResultSet rs, int colType, int colIndex) throws SQLException, IOException
	{
		String value = "";
		
		switch (colType) {
			case Types.BIT:
			case Types.JAVA_OBJECT:
				value = handleObject(rs.getObject(colIndex));
				break;
			case Types.BOOLEAN:
				boolean b = rs.getBoolean(colIndex);
				value = Boolean.valueOf(b).toString();
				break;
			case NCLOB:
			case Types.CLOB:
				Clob c = rs.getClob(colIndex);
				if (c != null) {
					value = read(c);
				}
				break;
			case Types.BIGINT:
			case Types.DECIMAL:
			case Types.DOUBLE:
			case Types.FLOAT:
			case Types.REAL:
			case Types.NUMERIC:
				value = handleBigDecimal(rs.getBigDecimal(colIndex));
				break;
			case Types.INTEGER:
			case Types.TINYINT:
			case Types.SMALLINT:
				value = handleInteger(rs, colIndex);
				break;
			case Types.DATE:
				value = handleDate(rs, colIndex);
				break;
			case Types.TIME:
				value = handleTime(rs.getTime(colIndex));
				break;
			case Types.TIMESTAMP:
				value = handleTimestamp(rs.getTimestamp(colIndex));
			case NVARCHAR:
			case NCHAR:
			case LONGNVARCHAR:
			case Types.LONGVARCHAR:
			case Types.VARCHAR:
			case Types.CHAR:
				value = rs.getString(colIndex);
				break;
			default:
				value = "";
		}
		
		if (value == null) {
			value = "";
		}
		
		return value;
	}
	
	private static String read(Clob c) throws SQLException, IOException
	{
		StringBuilder sb = new StringBuilder((int) c.length());
		Reader r = c.getCharacterStream();
		char[] cbuf = new char[CLOBBUFFERSIZE];
		int n;
		while ((n = r.read(cbuf, 0, cbuf.length)) != -1) {
			sb.append(cbuf, 0, n);
		}
		return sb.toString();
	}
}
