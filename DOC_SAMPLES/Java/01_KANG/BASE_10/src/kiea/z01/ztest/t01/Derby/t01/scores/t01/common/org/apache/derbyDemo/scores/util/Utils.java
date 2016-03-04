package kiea.z01.ztest.t01.Derby.t01.scores.t01.common.org.apache.derbyDemo.scores.util;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;

public class Utils
{
	private static final String PREPARE_PREAMBLE = "";
	
	public static Logger getLogger()
	{
		return Logger.getLogger();
	}
	
	public static double weighQuestion(int difficulty)
	{
		return (double) difficulty;
	}
	
	public static double scoreAnswer(int difficulty, int numberOfChoices, int correctChoice, int actualChoice)
	{
		if (actualChoice < 0) {
			return 0.0;
		}
		
		double weight = weighQuestion(difficulty);
		
		if (correctChoice == actualChoice) {
			return weight;
		} else {
			double penaltyRatio = 1.0 / ((double) numberOfChoices);
			return -(weight * penaltyRatio);
		}
	}
	
	public static double finishScore(double allCorrect, double actual)
	{
		if (actual < 0.0d) {
			return 0.0d;
		} else {
			return (100d * actual) / allCorrect;
		}
	}
	
	public static void commit(Connection conn) throws SQLException
	{
		getLogger().log("Committing.....");
		conn.commit();
	}
	
	public static void executeDDL(Connection conn, String text) throws SQLException
	{
		PreparedStatement ps = prepare(conn, text);
		ps.execute();
		close(ps);
	}
	
	public static void executeCall(Connection conn, String text) throws SQLException
	{
		CallableStatement cs = prepareCall(conn, text);
		cs.execute();
		close(cs);
	}
	
	public static PreparedStatement prepare(Connection conn, String text) throws SQLException
	{
		getLogger().log(PREPARE_PREAMBLE + text);
		return conn.prepareStatement(text);
	}
	
	public static CallableStatement prepareCall(Connection conn, String text) throws SQLException
	{
		getLogger().log(PREPARE_PREAMBLE + text);
		return conn.prepareCall(text);
	}
	
	public static void close(PreparedStatement ps) throws SQLException
	{
		if (ps != null) {
			ps.close();
		}
	}
	
	public static void close(ResultSet rs) throws SQLException
	{
		if (rs != null) {
			rs.close();
		}
	}
	
	public static void close(Connection conn) throws SQLException
	{
		if (conn != null) {
			conn.close();
		}
	}
	
	public static int getScalarValue(PreparedStatement ps) throws SQLException
	{
		ResultSet rs = ps.executeQuery();
		rs.next();
		try {
			return rs.getInt(1);
		} finally {
			close(rs);
			close(ps);
		}
	}
}
