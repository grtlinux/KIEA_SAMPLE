package kiea.z01.ztest.t01.Derby.t01.workingwithderby.t01;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import kiea.kr.co.tain.base.Flag;

public class WwdEmbeddedTestMain
{
	///////////////////////////////////////////////////////////////////////////
	
	public static void test01(String[] args)
	{
		String driver = "org.apache.derby.jdbc.EmbeddedDriver";
		String dbName = "jdbcDemoDB";
		String connectionURL = "jdbc:derby:" + dbName + ";create=true";
		
		Connection conn = null;
		Statement s;
		PreparedStatement psInsert;
		ResultSet myWishes;
		String printLine = "  ------------------------------------------------";
		String createString = ""
				+ "CREATE TABLE WISH_LIST                                    \n"
				+ "(                                                         \n"
				+ "    WISH_ID INT NOT NULL GENERATED ALWAYS AS IDENTITY     \n"
				+ "        CONSTRAINT WISH_PK PRIMARY KEY                    \n"
				+ "    , ENTRY_DATE TIMESTAMP DEFAULT CURRENT_TIMESTAMP      \n"
				+ "    , WISH_ITEM VARCHAR(32)  NOT NULL                     \n"
				+ ")                                                         \n"
				;
		String answer;
		
		try {
			conn = DriverManager.getConnection(connectionURL);
			System.out.println("Connected to database " + dbName);
			
			s = conn.createStatement();
			if (!WwdUtils.wwdChk4Table(conn)) {
				System.out.println(" . . .  creating table WISH_LIST");
				s.execute(createString);
			}
			
			psInsert = conn.prepareStatement("insert into WISH_LIST(WISH_ITEM) values (?)");
			
			do {
				answer = WwdUtils.getWishItem();
				if (!answer.equals("exit")) {
					
					// INSERT
					psInsert.setString(1, answer);
					psInsert.executeUpdate();
					
					// SELECT
					myWishes = s.executeQuery("select ENTRY_DATE, WISH_ITEM from WISH_LIST order by ENTRY_DATE");
					
					System.out.println(printLine);
					while (myWishes.next()) {
						System.out.println("On " + myWishes.getTimestamp(1) + " I wished for " + myWishes.getString(2));
					}
					System.out.println(printLine);
					
					myWishes.close();
				}
			} while (!answer.equals("exit"));
			
			psInsert.close();
			s.close();
			conn.close();
			
			System.out.println("Closed connection");
			
			if (driver.equals("org.apache.derby.jdbc.EmbeddedDriver")) {
				
				boolean gotSQLExc = false;
				
				try {
					DriverManager.getConnection("jdbc:derby:;shutdown=true");
				} catch (SQLException e) {
					if (e.getSQLState().equals("XJ015")) {
						gotSQLExc = true;
					}
				}
				
				if (!gotSQLExc) {
					System.out.println("Database did not shut down normally");
				} else {
					System.out.println("Database shut down normally");
				}
			}
			
		} catch (Throwable e) {
			System.out.println(" . . . exception thrown:");
			e.printStackTrace();
		}
		
		System.out.println("Getting Started With Derby JDBC program ending.");
	}
	
	public static void main(String[] args)
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
		
		if (Flag.TRUE) test01(args);
	}
}
