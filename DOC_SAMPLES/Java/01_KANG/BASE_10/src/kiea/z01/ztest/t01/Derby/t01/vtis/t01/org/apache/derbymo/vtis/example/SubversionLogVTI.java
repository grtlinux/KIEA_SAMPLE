package kiea.z01.ztest.t01.Derby.t01.vtis.t01.org.apache.derbymo.vtis.example;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

import kiea.z01.ztest.t01.Derby.t01.vtis.t01.org.apache.derbymo.vtis.core.FlatFileVTI;

public class SubversionLogVTI extends FlatFileVTI
{
	private static final String[] COLUMN_NAMES = {
		"XID", "committer", "commit_time", "line_count", "description"
	};
	
	private static final int XID = 0;
	private static final int COMMITTER = XID + 1;
	private static final int COMMIT_TIME = COMMITTER + 1;
	private static final int LINE_COUNT = COMMIT_TIME + 1;
	private static final int DESCRIPTION = LINE_COUNT + 1;
	
	private static final String RECORD_HEADER="------------------------------------------------------------";
	
	private SimpleDateFormat _dateFormatter;
	
	public SubversionLogVTI(String logFileName)
	{
		super(COLUMN_NAMES, logFileName);
	}
	
	public static ResultSet subversionLogVTI(String logFileName) throws SQLException
	{
		return new SubversionLogVTI(logFileName);
	}
	
	protected String[] parseRow() throws SQLException
	{
		String[] newRow = new String[COLUMN_NAMES.length];
		String headerLine = readNextLine();
		
		if (headerLine == null) {
			return null;
		}
		
		if (!isRecordHeader(headerLine)) {
			throw new SQLException("Badly formatted record header: " + headerLine);
		}
		
		String mainline = readNextLine();
		
		if (mainline == null) {
			return null;
		}
		
		int oldIdx[] = new int[1];
		
		oldIdx[0] = 0;
		
		for (int i=0; i < DESCRIPTION; i++) {
			newRow[i] = readField(mainline, oldIdx);
		}
		
		int descriptionLineCount = 0;
		
		try {
			String lineCountField = newRow[LINE_COUNT];
			if (lineCountField != null) {
				lineCountField = lineCountField.trim();
				String numberString = lineCountField.substring(0, lineCountField.indexOf(' '));
				
				descriptionLineCount = Integer.parseInt(numberString);
			}
		} catch (Throwable e) {
			throw wrap("Error parsing description line count at line " + getLineNumber() + ": " + mainline, e);
		}
		
		descriptionLineCount ++;
		
		StringBuffer buffer = new StringBuffer();
		
		for (int i=0; i < descriptionLineCount; i++) {
			String nextLine = readNextLine();
			buffer.append(nextLine);
		}
		
		newRow[DESCRIPTION] = buffer.toString();
		
		return newRow;
	}
	
	public Timestamp getTimestamp(int columnIndex) throws SQLException
	{
		String columnValue = getString(columnIndex).trim();
		
		try {
			SimpleDateFormat dateFormatter = getDateFormatter();
			Date rawDate = dateFormatter.parse(columnValue);
			long time = rawDate.getTime();
			
			return new Timestamp(time);
		} catch (Throwable e) {
			throw new SQLException(e.getMessage());
		}
	}
	
	private String readField(String mainline, int[] oldIdx) throws SQLException
	{
		String result = null;
		int fieldStart = oldIdx[0];
		int fieldEnd = mainline.indexOf( "|", fieldStart);
		
		if (fieldEnd < 0) {
			fieldEnd = mainline.length();
		}
		
		if (fieldStart > fieldEnd) {
			return null;
		}
		
		try {
			result = mainline.substring(fieldStart, fieldEnd);
			if (result != null) {
				result = result.trim();
			}
		} catch (Throwable e) {
			throw wrap("Bad record at line " + getLineNumber() + ". Field start = " + fieldStart + ", fieldEnd = " + fieldEnd + ": " + mainline, e);
		}
		
		oldIdx[0] = fieldEnd + 1;
		
		return result;
	}
	
	private boolean isRecordHeader(String line)
	{
		if (line.startsWith(RECORD_HEADER)) {
			return true;
		} else {
			return false;
		}
	}
	
	private String readNextLine() throws SQLException
	{
		String retval;
		retval = readLine();
		return retval;
	}
	
	private SimpleDateFormat getDateFormatter()
	{
		if (_dateFormatter == null) {
			_dateFormatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss Z (EEE, dd MMM yyy)");
		}
		
		return _dateFormatter;
	}
}
