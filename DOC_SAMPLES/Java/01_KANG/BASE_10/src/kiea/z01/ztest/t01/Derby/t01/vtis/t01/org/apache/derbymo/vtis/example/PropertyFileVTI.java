package kiea.z01.ztest.t01.Derby.t01.vtis.t01.org.apache.derbymo.vtis.example;

import java.sql.ResultSet;
import java.sql.SQLException;

import kiea.z01.ztest.t01.Derby.t01.vtis.t01.org.apache.derbymo.vtis.core.FlatFileVTI;

public class PropertyFileVTI extends FlatFileVTI
{
	private static final String[] COLUMN_NAMES = {
		"propKey", "propValue"
	};
	
	private static final int PROPERTY_KEY = 0;
	private static final int PROPERTY_VALUE = PROPERTY_KEY + 1;
	
	public PropertyFileVTI(String propertyFileName)
	{
		super(COLUMN_NAMES, propertyFileName);
	}
	
	public static ResultSet propertyFileVTI(String propertyFileName) throws SQLException
	{
		return new PropertyFileVTI(propertyFileName);
	}
	
	protected String[] parseRow() throws SQLException
	{
		String[] newRow = new String[COLUMN_NAMES.length];
		String nextLine = null;
		String oldLine = "";
		
		while (true) {
			boolean isContinuationLine = (oldLine.length() != 0);
			
			nextLine = readLine();
			if (nextLine == null) {
				if (!isContinuationLine) {
					return null;
				} else {
					nextLine = oldLine;
				}
			}
			
			nextLine = nextLine.trim();
			
			if (nextLine.startsWith("#")) {
				continue;
			} else if (nextLine.length() == 0) {
				if (!isContinuationLine) {
					continue;
				}
			}
			
			break;
		}
		
		int equalsIdx = nextLine.indexOf("=");
		
		try {
			if (equalsIdx >= 0) {
				newRow[PROPERTY_KEY] = nextLine.substring(0, equalsIdx);
				newRow[PROPERTY_VALUE] = nextLine.substring(equalsIdx+1, nextLine.length());
			} else {
				newRow[PROPERTY_KEY] = nextLine;
				newRow[PROPERTY_VALUE] = "";
			}
		} catch (Throwable e) {
			SQLException se = new SQLException("Unparseable line number " + getLineNumber() + " in file " + getTextFileName() + ": " + nextLine);
			se.initCause(e);
			throw se;
		}
		
		return newRow;
	}
}
