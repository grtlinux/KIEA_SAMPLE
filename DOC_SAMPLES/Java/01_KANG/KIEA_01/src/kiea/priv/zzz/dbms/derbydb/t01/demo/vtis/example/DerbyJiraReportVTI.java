package kiea.priv.zzz.dbms.derbydb.t01.demo.vtis.example;

import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.SQLException;
import java.text.SimpleDateFormat;

import org.apache.derby.vti.XmlVTI;

public class DerbyJiraReportVTI extends XmlVTI
{
	// CONSTANTS
	
	// STATE
	
	@SuppressWarnings("unused")
	private SimpleDateFormat _dateFormatter;
	
	// CONSTRUCTORS
	
	public DerbyJiraReportVTI(InputStream is)
	{
		super(is, "item", 0, "key", "type", "priority", "status", "component", "customfieldvalue", "title");
	}
	
	// FUNCTION ENTRY POINT (BOUND BY THE CREATE FUNCTION STATEMENT)
	
	public static DerbyJiraReportVTI apacheNaturalJiraReport(String xmlResourceName) throws Exception
	{
		return new DerbyJiraReportVTI(new FileInputStream(xmlResourceName));
	}
	
	// ResultSet BEHAVIOR
	
	public String getString(int columnIndex) throws SQLException
	{
		String rawValue = super.getString(columnIndex);
		
		if (!"key".equals(getColumnName(columnIndex)))
			return rawValue;
		else
			return rawValue.substring(6);
	}
	
	// MINIONS
}
