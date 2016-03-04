package kiea.z01.ztest.t01.Derby.t01.scores.t01.server.org.apache.derbyDemo.scores.proc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import kiea.z01.ztest.t01.Derby.t01.scores.t01.common.org.apache.derbyDemo.scores.util.Logger;
import kiea.z01.ztest.t01.Derby.t01.scores.t01.common.org.apache.derbyDemo.scores.util.Utils;

public class Procedures
{
	private static int _scoringCount = 0;
	
	public static void ScoreTestTaking(int takingID) throws SQLException
	{
		Connection conn = Functions.getDefaultConnection();
		Logger log = Logger.getLogger();
		boolean loggingEnabled = log.isLoggingEnabled();
		
		try {
			if (_scoringCount > 0) {
				log.enableLogging(false);
			}
			
			log.log("Trigger has just fired and started the ScoreTestTaking procedure.");
			
			PreparedStatement ps = Utils.prepare(conn, "select sum(weighquestion(q.difficulty)), sum(scoreAnswer(q.difficulty, q.numberOfChoices, q.correctChoice, qt.actualChoice)) "
					+ "from Question q, QuestionTaking qt "
					+ "where q.questionId = qt.questionID and qt.takingID = ?");
			ps.setInt(1, takingID);
			
			ResultSet rs = ps.executeQuery();
			
			rs.next();
			
			int column = 1;
			double allCorrect = rs.getDouble(column++);
			double actual = rs.getDouble(column++);
			double score = Utils.finishScore(allCorrect, actual);
			
			Utils.close(rs);
			Utils.close(ps);
			
			int param = 1;
			ps = Utils.prepare(conn, "update TestTaking set score = ? where takingID = ?");
			ps.setDouble(param++, score);
			ps.setInt(param++, takingID);
			ps.executeUpdate();
			
			Utils.close(ps);
		} finally {
			log.enableLogging(loggingEnabled);
		}
		
		_scoringCount ++;
	}
}
