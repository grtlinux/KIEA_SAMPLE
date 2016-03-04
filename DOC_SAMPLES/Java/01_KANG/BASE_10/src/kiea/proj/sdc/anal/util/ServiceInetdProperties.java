package kiea.proj.sdc.anal.util;

import kiea.kr.co.tain.base.Flag;
import kiea.kr.co.tain.util.FileProperties;
import kiea.kr.co.tain.util.Print;
import kiea.kr.co.tain.util.SystemProperties;

public class ServiceInetdProperties
{
	private String basePath = null;
	private String analServiceCode = null;
	
	private FileProperties prop = null;
	
	/**
	 * 
	 */
	private ServiceInetdProperties()
	{
		if (Flag.TRUE) {
			this.analServiceCode = SystemProperties.getInstance().getAnalServiceCode();
			if (this.analServiceCode == null) {
				System.err.println("ERROR : don't define anal.service.code");
				if (Flag.TRUE) System.exit(-1);
			}
		}

		if (Flag.TRUE) {
			String str = AnalProperties.getInstance().get("anal.service." + this.analServiceCode + ".base.path");
			this.basePath = str.replace("{anal.base.path}", SystemProperties.getInstance().getAnalBasePath());
			if (!Flag.TRUE) System.out.println(">" + this.basePath);
			
			this.prop = new FileProperties(this.basePath + "/config/inetd.properties");
		}
	}
	
	/**
	 * 
	 * printList
	 *
	 */
	public void printList()
	{
		if (Flag.TRUE) {
			System.out.println("# ServiceInetdProperties -----------------------------");
			this.prop.printList();
		}
	}
	
	/**
	 * 
	 * get
	 *
	 * @param paramKey
	 * @return
	 */
	public String get(String paramKey)
	{
		String ret = null;
		
		if (Flag.TRUE) {
			ret = this.prop.get(paramKey);
		}
		
		return ret;
	}
	
	/**
	 * get
	 * 
	 * @param paramKey
	 * @param defaultValue
	 * @return
	 */
	public String get(String paramKey, String defaultValue)
	{
		String ret = null;
		
		if (Flag.TRUE) {
			ret = this.prop.get(paramKey, defaultValue);
		}
		
		return ret;
	}
	
	///////////////////////////////////////////////////////////////////////////
	
	/**
	 * Singleton
	 */
	private static ServiceInetdProperties instance = null;
	
	public static synchronized ServiceInetdProperties getInstance()
	{
		if (instance == null) {
			instance = new ServiceInetdProperties();
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
		
		if (!Flag.TRUE) {
			ServiceInetdProperties.getInstance().printList();
		}
		
		if (Flag.TRUE) {
			Print.println("[%s]", ServiceInetdProperties.getInstance().get("anal.service.title"));
			
			ServiceInetdProperties prop = ServiceInetdProperties.getInstance();
			Print.println("[%s]", prop.get("anal.service.comment.001"));

		}
	}
	
	public static void main(String[] args)
	{
		if (Flag.TRUE) test01();
	}
}
