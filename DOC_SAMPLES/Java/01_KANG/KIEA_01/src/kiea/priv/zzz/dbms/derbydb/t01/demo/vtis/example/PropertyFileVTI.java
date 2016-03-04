package kiea.priv.zzz.dbms.derbydb.t01.demo.vtis.example;

import java.sql.ResultSet;
import java.sql.SQLException;

import kiea.priv.zzz.dbms.derbydb.t01.demo.vtis.core.FlatFileVTI;

public class PropertyFileVTI extends FlatFileVTI
{
	// CONSTANTS
	
	private static final String[] COLUMN_NAMES = { "propKey", "propValue" };
	
	private static final int PROPERTY_KEY = 0;
	private static final int PROPERTY_VALUE = PROPERTY_KEY + 1;
	
	// STATE
	
	// CONSTRUCTORS
	
	public PropertyFileVTI(String propertyFileName)
	{
		super(COLUMN_NAMES, propertyFileName);
	}
	
	// TABLE FUNCTION METHOD
	
	public static ResultSet propertyFileVTI(String propertyFileName) throws SQLException
	{
		return new PropertyFileVTI(propertyFileName);
	}
	
	// FlatFileVTI BEHAVIOR TO BE IMPLEMENTED BY SUBCLASSES
	
	protected String[] parseRow() throws SQLException
	{
		String[] newRow = new String[COLUMN_NAMES.length];
		String nextLine = null;
		String oldLine = "";
		
		while (true) {
			boolean isContinuationLine = (oldLine.length() != 0);
			
			nextLine = readLine();
			if (nextLine == null) {
				if (!isContinuationLine)
					return null;
				else
					nextLine = oldLine;
			}
			
			nextLine = nextLine.trim();
			
			if (nextLine.startsWith("#"))
				continue;
			else if (nextLine.length() == 0) {
				if (!isContinuationLine)
					continue;
			}
			
			nextLine = oldLine + nextLine;
			if (nextLine.endsWith("\\")) {
				oldLine = nextLine.substring(0, nextLine.length() - 1);
				continue;
			}
			
			break;
		}
		
		int equalsIdx = nextLine.indexOf('=');
		
		try {
			if (equalsIdx >= 0) {
				newRow[PROPERTY_KEY] = nextLine.substring(0, equalsIdx);
				newRow[PROPERTY_VALUE] = nextLine.substring(equalsIdx + 1, nextLine.length());
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
	
	// ResultSet METHODS
	
	// MINIONS
}
