package kiea.proj.sdc.anal.tools.AnalCsv.v01;

import kiea.kr.co.tain.base.Flag;
import kiea.kr.co.tain.util.Print;
import kiea.kr.co.tain.util.SystemProperties;
import kiea.proj.sdc.anal.util.ServiceMacroProperties;
import kiea.proj.sdc.anal.util.StrUtil;

public class AnalCsvClient
{
	private static final String HOST = "127.0.0.1";
	private static final String PORT = "1521";
	
	private ServiceMacroProperties prop = null;
	
	private String strHost = null;
	private String strPort = null;
	
	private AnalCsvClient()
	{
		if (Flag.TRUE) {
			this.prop = ServiceMacroProperties.getInstance();
			this.strHost = this.prop.get("anal.sendquery.host", HOST);
			this.strPort = this.prop.get("anal.sendquery.port", PORT);
			
			if (Flag.TRUE) Print.println("ANAL_CSV_CLIENT : [%s][%s]", this.strHost, this.strPort);
		}
	}
	
	public AnalCsvClient sendQuery(AnalCsvBean bean)
	{
		if (Flag.TRUE) Print.println("[%s]", bean.getQuery());
		
		return this;
	}
	
	///////////////////////////////////////////////////////////////////////////
	
	private static AnalCsvClient instance = null;
	
	public static AnalCsvClient getInstance()
	{
		if (instance == null) {
			instance = new AnalCsvClient();
		}
		
		return instance;
	}
	
	///////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////

	private static void test01()
	{
		if (Flag.TRUE) {
			SystemProperties.setTesting("010");
		}
		
		if (Flag.TRUE) {

			AnalCsvBean bean = new AnalCsvBean();
			
			bean.setType(AnalCsvType.INSERT);
			bean.setTable("ABC");
			bean.setFields(new String[][] {
					{ "FLD1", StrUtil.quote("Hello") },
					{ "FLD2", StrUtil.quote("강석") },
					{ "FLD3", "" + 1234 },
			});
			bean.setWhere("FLD1 = '12345'");
			
			AnalCsvClient.getInstance().sendQuery(bean);
		}
		
		if (!Flag.TRUE) {
			AnalCsvBean bean = new AnalCsvBean();
			
			bean.setType(AnalCsvType.UPDATE);
			bean.setTable("ABC");
			bean.setFields(new String[][] {
					{ "FLD1", StrUtil.quote("Hello") },
					{ "FLD2", StrUtil.quote("강석") },
					{ "FLD3", "" + 1234 },
			});
			bean.setWhere("FLD1 = '12345'");
		}

		if (!Flag.TRUE) {
			AnalCsvBean bean = new AnalCsvBean();
			
			bean.setType(AnalCsvType.DELETE);
			bean.setTable("ABC");
			bean.setFields(new String[][] {
					{ "FLD1", StrUtil.quote("Hello") },
					{ "FLD2", StrUtil.quote("강석") },
					{ "FLD3", "" + 1234 },
			});
			bean.setWhere("FLD1 = '12345'");
			
			Print.println("[%s][%s]", bean, bean.getQuery());
		}
	}
	
	public static void main(String[] args)
	{
		if (Flag.TRUE) test01();
	}
}
