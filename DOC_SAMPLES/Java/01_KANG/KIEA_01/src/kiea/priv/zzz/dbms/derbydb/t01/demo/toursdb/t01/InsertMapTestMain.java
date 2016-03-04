package kiea.priv.zzz.dbms.derbydb.t01.demo.toursdb.t01;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Map;
import java.util.Properties;

public class InsertMapTestMain
{
	// °­¼®
	///////////////////////////////////////////////////////////////////////////
	
	private static final String CSdriver = new String("org.apache.derby.jdbc.EmbeddedDriver");
	//private static final String dbURLS = new String("jdbc:derby:D:/KANG/WORK/DerbyDB/toursdb");
	private static final String dbURLS = new String("jdbc:derby:toursdb");
	
	private static int insertRows(String path, Connection conn)
	throws SQLException, FileNotFoundException, IOException
	{
		String fileName = null;
		File file = null;
		InputStream fis = null;
		int numRows = 0;
		
		PreparedStatement ps = null;
		
		ps = conn.prepareStatement("insert into maps (map_name, region, area, photo_format, picture) values (?,?,?,?,?)");
		
		// 1st
		ps.setString(1, "North Ocean");
		ps.setString(2, "Cup Island");
		ps.setBigDecimal(3, new BigDecimal("1776.11"));
		ps.setString(4, "gif");
		if (path == null)
			fileName = "cupisle.gif";
		else
			fileName = path + File.separator + "cupisle.gif";
		file = new File(fileName);
		fis = new FileInputStream(file);
		ps.setBinaryStream(5,  fis, (int) file.length());
		numRows = ps.executeUpdate();
		fis.close();
		
		// 2nd
		ps.setString(1, "Middle Ocean");
		ps.setString(2, "Small Island");
		ps.setBigDecimal(3, new BigDecimal("1166.77"));
		ps.setString(4, "gif");
		if (path == null)
			fileName = "smallisle.gif";
		else
			fileName = path + File.separator + "smallisle.gif";
		file = new File(fileName);
		fis = new FileInputStream(file);
		ps.setBinaryStream(5,  fis, (int) file.length());
		numRows += ps.executeUpdate();
		fis.close();
		
		// 3rd
		ps.setString(1, "South Ocean");
		ps.setString(2, "Witch Island");
		ps.setBigDecimal(3, new BigDecimal("9117.90"));
		ps.setString(4, "gif");
		if (path == null)
			fileName = "witchisle.gif";
		else
			fileName = path + File.separator + "witchisle.gif";
		file = new File(fileName);
		fis = new FileInputStream(file);
		ps.setBinaryStream(5,  fis, (int) file.length());
		numRows += ps.executeUpdate();
		fis.close();
		
		ps.close();
		
		return numRows;
	}
	
	private static void test01(String[] args)
	{
		try {
			System.setProperty("derby.system.home", "D:/KANG/WORK/DerbyDB");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		try {
			System.out.println("Loading the Derby jdbc driver....");
			Class.forName(CSdriver).newInstance();
			
			System.out.println("Getting Derby database connection....");
			Connection connCS = DriverManager.getConnection(dbURLS);
			System.out.println("Successfully got the Derby database connection....");
			
			System.out.println("Insert " + insertRows("D:/KANG/PROG/derby/demo/programs/toursdb", connCS) + " rows into the ToursDB");
			
			try {
				DriverManager.getConnection(dbURLS + ";shutdown=true");
			} catch (SQLException e) {
				// Database shutdown is expected to raise SQLState 08006.
				// Report any other exception.
				if (!"08006".equals(e.getSQLState()))
					throw e;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@SuppressWarnings("unused")
	private static void test02(String[] args)
	{
		try {
			Properties prop = System.getProperties();
			prop.list(System.out);
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		try {
			Map<String, String> map = System.getenv();
			
			for (Map.Entry<String, String> entry : map.entrySet()) {
				System.out.println("[" + entry.getKey() + "=" + entry.getValue() + "]");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args)
	{
		test01(args);
		//test02(args);
	}
}
