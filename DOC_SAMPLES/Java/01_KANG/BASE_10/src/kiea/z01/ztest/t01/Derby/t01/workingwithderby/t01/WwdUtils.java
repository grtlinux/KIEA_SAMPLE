package kiea.z01.ztest.t01.Derby.t01.workingwithderby.t01;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class WwdUtils
{
	public static String getWishItem()
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		String ans = "";
		
		try {
			while (ans.length() == 0) {
				System.out.println("Enter wish-list item (enter exit to end): ");
				ans = br.readLine();
				if (ans.length() == 0)
					System.out.print("Nothing entered: ");
			}
		} catch (Exception e) {
			System.out.println("ld not read response from stdin");
		}
		
		return ans;
	}
	
	@SuppressWarnings("unused")
	public static boolean wwdChk4Table(Connection conn) throws SQLException
	{
		boolean chk = true;
		boolean doCreate = false;
		
		try {
			Statement s = conn.createStatement();
			s.execute("update WISH_LIST set ENTRY_DATE = CURRENT_TIMESTAMP, WISH_ITEM = 'TEST ENTRY' where 1=3");
		} catch (SQLException e) {
			String theError = e.getSQLState();
			if (theError.equals("42X05")) {
				return false;
			} else if (theError.equals("42X14") | theError.equals("42821")) {
				System.out.println("WwdChk4Table: incorrect table definition. Drop table WISH_LIST and return this program");
				throw e;
			} else {
				System.out.println("WwdChk4Table: Unhandled SQLException.");
				throw e;
			}
		}
		
		return true;
	}
	
	///////////////////////////////////////////////////////////////////////////
	
	public static void main(String[] args)
	{
		String answer;
		do {
			answer = getWishItem();
			if (!answer.equals("exit")) {
				System.out.println("You said: " + answer);
			}
		} while (!answer.equals("exit"));
	}
}
