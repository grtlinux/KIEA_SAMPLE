package kiea.proj.sdc.anal.util;

import java.util.Map.Entry;
import java.util.Properties;

import kiea.kr.co.tain.base.Flag;
import kiea.kr.co.tain.util.FileProperties;
import kiea.kr.co.tain.util.Print;
import kiea.kr.co.tain.util.SystemProperties;

public class ServiceMacroProperties
{
	private String basePath = null;
	private String analServiceCode = null;
	
	private FileProperties prop = null;
	
	/**
	 * 
	 */
	private ServiceMacroProperties()
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
			
			this.prop = new FileProperties(this.basePath + "/config/macro.properties");
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
			System.out.println("# ServiceMacroProperties -----------------------------");
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
			ret = this.prop.get(paramKey);
			if (ret == null) {
				ret = defaultValue;
			}
		}
		
		return ret;
	}
	
	/**
	 * 
	 * @return
	 */
	public Properties getProperties()
	{
		return this.prop.getProperties();
	}
	
	///////////////////////////////////////////////////////////////////////////
	
	/**
	 * Singleton
	 */
	private static ServiceMacroProperties instance = null;
	
	public static synchronized ServiceMacroProperties getInstance()
	{
		if (instance == null) {
			instance = new ServiceMacroProperties();
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
			ServiceMacroProperties.getInstance().printList();
		}
		
		if (!Flag.TRUE) {
			Print.println("[%s]", ServiceMacroProperties.getInstance().get("anal.service.title"));
			
			ServiceMacroProperties prop = ServiceMacroProperties.getInstance();
			Print.println("[%s]", prop.get("anal.service.comment.001"));
		}
		
		if (Flag.TRUE) {
			ServiceMacroProperties prop = ServiceMacroProperties.getInstance();
			
			for (Entry<Object, Object> entry : prop.getProperties().entrySet()) {
				String key = (String) entry.getKey();
				String val = (String) entry.getValue();
				
				if (key.startsWith("anal.ftp.csv.file.")) {
					System.out.println("[" + key + "] [" + val + "]");
				}
			}
		}
	}
	
	public static void main(String[] args)
	{
		if (Flag.TRUE) test01();
	}
}
