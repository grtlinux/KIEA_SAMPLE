package kiea.z01.ztest.t01.Derby.t01.toursdb.t01;

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

import kiea.kr.co.tain.base.Flag;

public class InsertMapsTestMain
{
	///////////////////////////////////////////////////////////////////////////
	
	private static final String strDriver = "org.apache.derby.jdbc.EmbeddedDriver";
	private static final String strConnURL = "jdbc:derby:toursdb";
	
	private static int insertRows(String path, Connection conn)
	throws SQLException, FileNotFoundException, IOException
	{
		String fileName = null;
		File file = null;
		InputStream fis = null;
		int numRows = 0;
		
		PreparedStatement ps = conn.prepareStatement("INSERT INTO MAPS (MAP_NAME, REGION, AREA, PHOTO_FORMAT, PICTURE) VALUES (?,?,?,?,?)");
		
		if (Flag.TRUE) {
			/*
			 * row - 1
			 */
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
			ps.setBinaryStream(5, fis, (int)file.length());

			numRows += ps.executeUpdate();
			
			fis.close();
		}
		
		if (Flag.TRUE) {
			/*
			 * row - 2
			 */
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
			ps.setBinaryStream(5, fis, (int)file.length());

			numRows += ps.executeUpdate();
			
			fis.close();
		}
		
		if (Flag.TRUE) {
			/*
			 * row - 3
			 */
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
			ps.setBinaryStream(5, fis, (int)file.length());

			numRows += ps.executeUpdate();
			
			fis.close();
		}
		
		ps.close();
		
		return numRows;
	}
	
	private static void test01(String[] args)
	{
		if (Flag.TRUE) {
			try {
				// DB SYSTEM HOME
				System.setProperty("derby.system.home", "D:/KANG/WORK/DERBY_DB_HOME");
			} catch (Exception e) {
				e.printStackTrace();
				return;
			}
		}
		
		if (Flag.TRUE) {
			try {
				
				if (Flag.TRUE) System.out.println("");
				if (Flag.TRUE) System.out.println("Loading the Derby jdbc driver .....");
				Class.forName(strDriver).newInstance();
				
				if (Flag.TRUE) System.out.println("Getting Derby database connection .....");
				Connection conn = DriverManager.getConnection(strConnURL);
				if (Flag.TRUE) System.out.println("Successfully got the Derby database connection .....");

				if (Flag.TRUE) System.out.println("Inserted " + insertRows("D:/KANG/WORK/DERBY_TEST/programs/toursdb", conn) + " rows into the ToursDB");
				
				conn.close();
				
				// shutdown the database cleanly before exiting
				try {
					DriverManager.getConnection(strConnURL + ";shutdown=true");
				} catch (SQLException e) {
					// Database shutdown is expected to raise SQLStatus 08006
					// Report any other exception
					if (!"08006".equals(e.getSQLState())) {
						throw e;
					}
				}

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void main(String[] args)
	{
		if (Flag.TRUE) test01(args);
	}
}
