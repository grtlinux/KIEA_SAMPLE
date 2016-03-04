package kiea.proj.sdc.anal.util;

import kiea.kr.co.tain.base.Flag;
import kiea.kr.co.tain.util.FileProperties;
import kiea.kr.co.tain.util.Print;
import kiea.kr.co.tain.util.SystemProperties;

public class ServiceManagerProperties
{
	private String basePath = null;
	private String analServiceCode = null;
	
	private FileProperties prop = null;
	
	/**
	 * 
	 */
	private ServiceManagerProperties()
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
			
			this.prop = new FileProperties(this.basePath + "/config/manager.properties");
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
			System.out.println("# ServiceManagerProperties -----------------------------");
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
	
	///////////////////////////////////////////////////////////////////////////
	
	/**
	 * Singleton
	 */
	private static ServiceManagerProperties instance = null;
	
	public static synchronized ServiceManagerProperties getInstance()
	{
		if (instance == null) {
			instance = new ServiceManagerProperties();
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
			ServiceManagerProperties.getInstance().printList();
		}
		
		if (Flag.TRUE) {
			Print.println("[%s]", ServiceManagerProperties.getInstance().get("anal.service.title"));
			
			ServiceManagerProperties prop = ServiceManagerProperties.getInstance();
			Print.println("[%s]", prop.get("anal.service.comment.001"));

		}
	}
	
	public static void main(String[] args)
	{
		if (Flag.TRUE) test01();
	}
}
