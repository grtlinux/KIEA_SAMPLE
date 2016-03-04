package kiea.proj.sdc.anal.tools.AnalCsv.v01;

public enum AnalCsvType
{
	INSERT  ("INSERT"),  // 0
	UPDATE  ("UPDATE"),  // 1
	DELETE  ("DELETE"),  // 2
	SELECT  ("SELECT"),  // 3
	;
	
	private final String name;
	
	private AnalCsvType(String name)
	{
		this.name = name;
	}
	
	public String getName()
	{
		return this.name;
	}
}
