package kiea.z01.ztest.t01.Derby.t01.vtis.t01.org.apache.derbymo.vtis.example;

import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.derby.vti.XmlVTI;

public class ApacheServerLogVTI extends XmlVTI
{
	private SimpleDateFormat _dateFormatter;
	
	public ApacheServerLogVTI(InputStream is) throws Exception
	{
		super(is, "Visitor", 0, "IP", "accessDate", "request", "statusCode", "fileSize", "referrer", "userAgent");
	}
	
	public static ApacheServerLogVTI apacheNaturalLogFile(String xmlResourceName) throws Exception
	{
		return new ApacheServerLogVTI(new FileInputStream(xmlResourceName));
	}
	
	public String getRawColumn(int columnIndex) throws SQLException
	{
		String columnValue = super.getRawColumn(columnIndex);
		if ("-".equals(columnValue)) {
			return null;
		} else {
			return columnValue;
		}
	}
	
	public Timestamp getTimestamp(int columnIndex) throws SQLException
	{
		String columnValue = getString(columnIndex);
		
		try {
			SimpleDateFormat dateFormatter = getDateFormatter();
			Date rawDate = dateFormatter.parse(columnValue);
			long time = rawDate.getTime();
			
			return new Timestamp(time);
		} catch (Throwable e) {
			throw new SQLException(e.getMessage());
		}
	}
	
	private SimpleDateFormat getDateFormatter()
	{
		if (_dateFormatter == null) {
			_dateFormatter = new SimpleDateFormat("dd/MMM/yyyy:HH:mm:ss Z");
		}
		
		return _dateFormatter;
	}
}
