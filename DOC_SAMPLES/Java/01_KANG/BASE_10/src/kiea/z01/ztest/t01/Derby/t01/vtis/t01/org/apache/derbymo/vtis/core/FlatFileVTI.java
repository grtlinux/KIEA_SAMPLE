package kiea.z01.ztest.t01.Derby.t01.vtis.t01.org.apache.derbymo.vtis.core;

import java.io.File;
import java.io.FileReader;
import java.io.LineNumberReader;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

import org.apache.derby.vti.StringColumnVTI;

public abstract class FlatFileVTI extends StringColumnVTI
{
	private String _textFileName;
	private File _file;
	private FileReader _fileReader;
	private LineNumberReader _lineReader;
	
	@SuppressWarnings("unused")
	private boolean _closed = false;
	private String[] _row;
	private int _lineNumber = -1;
	
	protected abstract String[] parseRow() throws SQLException;
	
	protected String getRawColumn(int columnNumber) throws SQLException
	{
		return _row[columnNumber - 1];
	}
	
	public FlatFileVTI(String[] columnNames, String textFileName)
	{
		super(columnNames);
		_textFileName = textFileName;
	}
	
	public boolean next() throws SQLException
	{
		if (_file == null) {
			try {
				_file = new File(_textFileName);
				_fileReader = new FileReader(_file);
				_lineReader = new LineNumberReader(_fileReader);
			} catch (Throwable e) {
				throw wrap(e);
			}
		}
		
		_row = parseRow();
		
		if (_row == null) {
			return false;
		} else {
			return true;
		}
	}
	
	public void close() throws SQLException
	{
		try {
			if (_lineReader != null)
				_lineReader.close();
			if (_fileReader != null)
				_fileReader.close();
		} catch (Throwable e) {
			throw wrap(e);
		} finally {
			_lineReader = null;
			_fileReader = null;
			_file = null;
		}
	}
	
	public ResultSetMetaData getMetaData() throws SQLException
	{
		throw new SQLException("Not implemented.");
	}
	
	protected int getLineNumber()
	{
		return _lineNumber;
	}
	
	protected String getTextFileName()
	{
		return _textFileName;
	}
	
	protected String readLine() throws SQLException
	{
		try {
			String retval = _lineReader.readLine();
			_lineNumber++;
			return retval;
		} catch (Throwable e) {
			throw wrap(e);
		}
	}
	
	protected SQLException wrap(Throwable e)
	{
		SQLException se = new SQLException(e.getMessage());
		se.initCause(e);
		return se;
	}
	
	protected SQLException wrap(String message, Throwable e)
	{
		SQLException se = new SQLException(message);
		se.initCause(e);
		return se;
	}
}
