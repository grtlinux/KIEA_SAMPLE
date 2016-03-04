package kiea.priv.zzz.dbms.derbydb.t01.demo.vtis.example;

import java.sql.ResultSet;
import java.sql.SQLException;

import kiea.priv.zzz.dbms.derbydb.t01.demo.vtis.core.FlatFileVTI;

public class LineListVTI extends FlatFileVTI
{
	// CONSTANTS
	
	private static final String[] COLUMN_NAMES = { "line" };
	
	private static final int LINE = 0;
	
	// STATE
	
	// CONSRUCTORS
	
	public LineListVTI(String fileName)
	{
		super(COLUMN_NAMES, fileName);
	}
	
	// TABLE FUNCTION METHOD
	
	public static ResultSet lineListVTI(String fileName) throws SQLException
	{
		return new LineListVTI(fileName);
	}
	
	// FlatFileVTI ENHAVIOR TO BE IMPLEMENTED BY SUBCLASSES
	
	protected String[] parseRow() throws SQLException
	{
		String[] newRow = new String[COLUMN_NAMES.length];
		String nextLine = null;
		
		nextLine = readLine();
		
		if (nextLine == null)
			return null;
		
		nextLine = nextLine.trim();
		
		newRow[LINE] = nextLine;
		
		return newRow;
	}
	
	// ResultSet METHODS
	
	// MINIONS
}
