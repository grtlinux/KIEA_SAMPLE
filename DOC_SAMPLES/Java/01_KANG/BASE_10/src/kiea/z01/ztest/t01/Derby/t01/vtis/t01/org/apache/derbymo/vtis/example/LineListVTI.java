package kiea.z01.ztest.t01.Derby.t01.vtis.t01.org.apache.derbymo.vtis.example;

import java.sql.ResultSet;
import java.sql.SQLException;

import kiea.z01.ztest.t01.Derby.t01.vtis.t01.org.apache.derbymo.vtis.core.FlatFileVTI;

public class LineListVTI extends FlatFileVTI
{
	private static final String[] COLUMN_NAMES = {
		"line"
	};
	
	private static final int LINE = 0;
	
	public LineListVTI(String fileName)
	{
		super(COLUMN_NAMES, fileName);
	}
	
	public static ResultSet lineListVTI(String fileName) throws SQLException
	{
		return new LineListVTI(fileName);
	}
	
	protected String[] parseRow() throws SQLException
	{
		String[] newRow = new String[COLUMN_NAMES.length];
		String nextLine = null;
		
		nextLine = readLine();
		
		if (nextLine == null) {
			return null;
		}
		
		nextLine = nextLine.trim();
		
		newRow[ LINE ] = nextLine;
		
		return newRow;
	}
}
