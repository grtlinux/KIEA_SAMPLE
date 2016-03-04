package kiea.z01.ztest.t01.Derby.t01.scores.t01.server.org.apache.derbyDemo.scores.proc;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.NumberFormat;
import java.util.ArrayList;

import kiea.z01.ztest.t01.Derby.t01.scores.t01.common.org.apache.derbyDemo.scores.util.Logger;
import kiea.z01.ztest.t01.Derby.t01.scores.t01.common.org.apache.derbyDemo.scores.util.Utils;

import org.apache.commons.math3.stat.descriptive.rank.Median;

public class Functions
{
	public static final long MILLISECONDS_IN_YEAR = 1000L * 60L * 60L * 24L * 365L;
	
	public static int vetChoice(int actualChoice, int questionID) throws SQLException
	{
		Connection conn = getDefaultConnection();
		int column = 1;
		
		PreparedStatement ps = Utils.prepare(conn, "select numberOfChoices, questionName from Question where questionID = ?");
		ps.setInt(1, questionID);
		ResultSet rs = ps.executeQuery();
		rs.next();
		
		int numberOfChoices = rs.getInt(column++);
		String questionName = rs.getString(column++);
		
		Utils.close(rs);
		Utils.close(ps);
		
		if ((actualChoice >= -1) && (actualChoice < numberOfChoices)) {
			return 1;
		} else {
			throw new SQLException("Illegal answer " + actualChoice + " given to question " + questionName);
		}
	}
	
	public static int computeAge(Date date)
	{
		long interval = System.currentTimeMillis() - date.getTime();
		return (int)(interval / MILLISECONDS_IN_YEAR);
	}
	
	public static double weighQuestion(int difficulty)
	{
		return Utils.weighQuestion(difficulty);
	}
	
	public static double scoreAnswer(int difficulty, int numberOfChoices, int correctChoice, int actualChoice)
	{
		return Utils.scoreAnswer(difficulty, numberOfChoices, correctChoice, actualChoice);
	}
	
	public static double getMedianTestScore(int testID) throws SQLException
	{
		Logger log = Logger.getLogger();
		boolean loggingEnabled = log.isLoggingEnabled();
		Median median = new Median();
		ArrayList<Double> arraylist = new ArrayList<Double>();
		Connection conn = getDefaultConnection();
		
		try {
			log.enableLogging(false);;
			
			PreparedStatement ps = Utils.prepare(conn, "select tk.score from TestTaking tk, LastTaking lt where tk.takingID = lt.takingID and tk.testID = ?");
			ps.setInt(1, testID);
			
			ResultSet rs = ps.executeQuery();
			
			while (rs.next()) {
				arraylist.add(new Double(rs.getDouble(1)));
			}
			
			Utils.close(rs);
			Utils.close(ps);
		} finally {
			log.enableLogging(loggingEnabled);
		}
		
		int count = arraylist.size();
		double values[] = new double[count];
		
		for (int i=0; i < count; i++) {
			values[i] = ((Double) arraylist.get(i)).doubleValue();
		}
		
		return median.evaluate(values);
	}
	
	public static String formatPercent(double score) throws SQLException
	{
		int rounded = (int) score;
		NumberFormat nf = NumberFormat.getNumberInstance();
		
		return nf.format(rounded);
	}
	
	static Connection getDefaultConnection() throws SQLException
	{
		return DriverManager.getConnection("jdbc:default:connection");
	}
}
