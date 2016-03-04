package kiea.z01.ztest.t01.Derby.t01.scores.t01.common.org.apache.derbyDemo.scores.util;

import java.io.PrintStream;

public class Logger
{
	private static final String BANNER_BRACKET = "--------------------------------------------";
	private static final String BANNER_INDENTATION = "-- ";
	
	private static Logger _logger;
	private PrintStream _printStream;
	private boolean _loggingEnabled = true;
	
	public Logger()
	{
		_printStream = System.out;
	}
	
	public static Logger getLogger()
	{
		if (_logger == null) {
			_logger = new Logger();
		}
		
		return _logger;
	}
	
	public boolean isLoggingEnabled()
	{
		return _loggingEnabled;
	}
	
	public void enableLogging(boolean enableLogging)
	{
		_loggingEnabled = enableLogging;
	}
	
	public void log(String text)
	{
		if (_loggingEnabled) {
			_printStream.println(text);
		}
	}
	
	public void log(Throwable t)
	{
		log(t.getMessage());
		t.printStackTrace(_printStream);
	}
	
	public PrintStream getPrintStream()
	{
		return _printStream;
	}
	
	public void logBanner(String text)
	{
		log("\n");
		log(BANNER_BRACKET);
		log(BANNER_INDENTATION);
		log(BANNER_INDENTATION + text);
		log(BANNER_INDENTATION);
		log(BANNER_BRACKET);
		log("\n");
	}
}
