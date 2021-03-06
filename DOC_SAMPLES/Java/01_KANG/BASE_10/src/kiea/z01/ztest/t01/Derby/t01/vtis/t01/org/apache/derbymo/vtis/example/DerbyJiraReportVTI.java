package kiea.z01.ztest.t01.Derby.t01.vtis.t01.org.apache.derbymo.vtis.example;

import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.SQLException;
import java.text.SimpleDateFormat;

import org.apache.derby.vti.XmlVTI;

public class DerbyJiraReportVTI extends XmlVTI
{
	@SuppressWarnings("unused")
	private SimpleDateFormat _dateFormatter;
	
	public DerbyJiraReportVTI(InputStream is)
	{
		super(is, "item", 0, "key", "type", "priority", "status", "component", "customfieldvalue", "title");
	}
	
	public static DerbyJiraReportVTI apacheNaturalJiraReport(String xmlResourceName) throws Exception
	{
		return new DerbyJiraReportVTI(new FileInputStream(xmlResourceName));
	}
	
	public String getString(int columnIndex) throws SQLException
	{
		String rawValue = super.getString(columnIndex);
		
		if (!"key".equals(getColumnName(columnIndex))) {
			return rawValue;
		} else {
			return rawValue.substring(6);
		}
	}
}
