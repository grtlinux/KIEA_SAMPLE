package kiea.priv.zzz.dbms.derbydb.t01.demo.vtis.example;

import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.derby.vti.XmlVTI;

public class ApacheServerLogVTI extends XmlVTI
{
	// CONSTANTS
	
	// STATE
	
	private SimpleDateFormat _dateFormatter;
	
	// CONSTRUCTORS
	
	public ApacheServerLogVTI(InputStream is) throws Exception
	{
		super(is, "Visitor", 0, "IP", "accessDate", "request", "statusCode", "fileSize", "referrer", "userAgent");
	}
	
	// FUNCTION ENTRY POINT (BOUND BY THE CREATE FUNCTION SATEMENT)
	
	public static ApacheServerLogVTI apacheNaturalLogFile(String xmlResourceName) throws Exception
	{
		return new ApacheServerLogVTI(new FileInputStream(xmlResourceName));
	}
	
	// OVERRIDES
	
	public String getRawColumn(int columnIndex) throws SQLException
	{
		String columnValue = super.getRawColumn(columnIndex);
		if ("-".equals(columnValue))
			return null;
		else 
			return columnValue;
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
	
	// MINIONS
	
	private SimpleDateFormat getDateFormatter()
	{
		if (_dateFormatter == null) {
			_dateFormatter = new SimpleDateFormat("dd/MM/yyyy:HH:mm:ss Z");
		}
		return _dateFormatter;
	}
	
	///////////////////////////////////////////////////////////////////////////
	
	public static void main(String[] args)
	{
		try {
			SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
			Date rawDate = format.parse("2014/12/20 12:13:14");
			long time = rawDate.getTime();
			
			System.out.println(">" + new Timestamp(time));
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		try {
			SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy:HH:mm:ss Z");
			Date rawDate = format.parse("01/07/2002:17:31:17 +0200");
			long time = rawDate.getTime();
			
			System.out.println(">" + new Timestamp(time));
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		try {
			SimpleDateFormat format = new SimpleDateFormat("dd/MMM/yyyy:HH:mm:ss Z");
			Date rawDate = format.parse("01/Jul/2002:17:31:17 +0200");
			long time = rawDate.getTime();
			
			System.out.println(">" + new Timestamp(time));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
