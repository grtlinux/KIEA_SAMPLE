package kiea.z01.ztest.t01.Derby.t01.scores.t01.client.org.apache.derbyDemo.scores.app;

import java.sql.Connection;

import kiea.z01.ztest.t01.Derby.t01.scores.t01.client.org.apache.derbyDemo.scores.data.Data;
import kiea.z01.ztest.t01.Derby.t01.scores.t01.client.org.apache.derbyDemo.scores.data.Database;
import kiea.z01.ztest.t01.Derby.t01.scores.t01.common.org.apache.derbyDemo.scores.util.Logger;
import kiea.z01.ztest.t01.Derby.t01.scores.t01.common.org.apache.derbyDemo.scores.util.Utils;

public class Scores
{
    @SuppressWarnings("unused")
	private Logger  _logger;
    private String  _serverJar;
    private String  _mathJar;
    
    public  Scores()
    {
        _logger = new Logger();
    }

    public  static  void    main( String[] args )
    {
        Scores      application = new Scores();
        int         argIdx = 0;

        try {
            application._serverJar = args[ argIdx++ ];
            application._mathJar = args[ argIdx++ ];

            application.execute();
        }
        catch (Throwable t)
        {
            Logger.getLogger().log( t );
        }
    }

	@SuppressWarnings("static-access")
	private void    execute() throws Exception
    {
        Logger      log = Logger.getLogger();

        log.logBanner( "Starting Scores Application..." );

        Database    db = Database.getDatabase( _serverJar, _mathJar );

        Connection  conn = db.getConnection();
        Data        data = db.getData();

        log.logBanner
            (
             "Now the students take their tests." +
             " Watch for the trigger firing..."
             );
        data.takeTests( db );
        Utils.commit( conn );

        log.logBanner( "Show scores for latest takings..." );
        Database.prettyPrint
            (
             db.getConnection(),
             "select s.lastName, s.firstName, t.testName,\n" +
             "tk.takingID, formatPercent( tk.score ) score\n" +
             "from Student s, Test t, TestTaking tk, LastTaking lt\n" +
             "where t.testID = tk.testID\n" +
             "and s.studentID = tk.studentID\n" +
             "and tk.takingID = lt.takingID\n" +
             "order by s.lastName, s.firstName, t.testName, tk.takingID\n"
             );

        log.logBanner
            ( "Median Score Per Test. Note how we fake " +
              "a user-defined aggregate..." );
        db.prettyPrint
            (
             conn,
             "select testName, " +
             "formatPercent( getMedianTestScore( testID ) ) " +
             "as \"median score\"\n" +
             "from Test\n"
             );

        log.logBanner
            (
             "Who Needs Improvement? Note the filtering done " +
             "at the end of the WHERE clause..."
             );
        data.reportWhoNeedsImprovement
            ( db, Data.LincolnGrammar, "GSM_2_0" );

        log.logBanner( "Hoopla! Everything works!" );
    }
}
