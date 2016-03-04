package kiea.proj.sdc.anal.util;

import kiea.kr.co.tain.base.Flag;
import kiea.kr.co.tain.util.DateTime;
import kiea.kr.co.tain.util.FileProperties;
import kiea.kr.co.tain.util.Print;
import kiea.kr.co.tain.util.SystemProperties;

public class ServiceDetectorProperties
{
	private String basePath = null;
	private String analServiceCode = null;
	
	private FileProperties prop = null;
	
	/**
	 * 
	 */
	private ServiceDetectorProperties()
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
			
			this.prop = new FileProperties(this.basePath + "/config/detector.properties");
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
			System.out.println("# ServiceDetectorProperties -----------------------------");
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
		
		if (Flag.TRUE) {
			ret = ret.replace("{service.path}", this.basePath);
			ret = ret.replace("{YYYYMMDD}", DateTime.getDate(2));
			ret = ret.replace("{YYMMDD}", DateTime.getDate(6));
		}
		
		return ret;
	}
	
	///////////////////////////////////////////////////////////////////////////
	
	/**
	 * Singleton
	 */
	private static ServiceDetectorProperties instance = null;
	
	public static synchronized ServiceDetectorProperties getInstance()
	{
		if (instance == null) {
			instance = new ServiceDetectorProperties();
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
			ServiceDetectorProperties.getInstance().printList();
		}
		
		if (Flag.TRUE) {
			Print.println("[%s]", ServiceDetectorProperties.getInstance().get("anal.service.title"));
			
			ServiceDetectorProperties prop = ServiceDetectorProperties.getInstance();
			Print.println("[%s]", prop.get("anal.service.comment.001"));

		}
		
		if (Flag.TRUE) {
			Print.println("[%s]", ServiceDetectorProperties.getInstance().get("anal.service.log.path"));
		}
	}
	
	public static void main(String[] args)
	{
		if (Flag.TRUE) test01();
	}
}
