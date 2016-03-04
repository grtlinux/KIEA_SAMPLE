package kiea.proj.sdc.anal.util;

import kiea.kr.co.tain.base.Flag;
import kiea.kr.co.tain.util.FileProperties;
import kiea.kr.co.tain.util.SystemProperties;

public class AnalProperties
{
	private String basePath = null;
	private FileProperties prop = null;
	
	/**
	 * 
	 */
	private AnalProperties()
	{
		if (Flag.TRUE) {
			this.basePath = SystemProperties.getInstance().getAnalBasePath();
			if (this.basePath == null) {
				System.err.println("ERROR : don't define anal.base.path");
				if (Flag.TRUE) System.exit(-1);
			}
		}

		if (Flag.TRUE) {
			String file = basePath + "/config/anal.properties";
			this.prop = new FileProperties(file);
			if (!Flag.TRUE) this.prop.printList();
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
			System.out.println("# AnalProperties -----------------------------");
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
	 * get with default value
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
			if (ret == null)
				ret = defaultValue;
		}
		
		return ret;
	}
	
	/**
	 * 
	 * getServiceCode
	 *
	 * @return
	 */
	private String getServiceCode()
	{
		String serviceCode = null;
		
		if (Flag.TRUE) {
			serviceCode = SystemProperties.getInstance().getAnalServiceCode();
			if (serviceCode == null) {
				System.out.println("ERROR : don't define anal.service.code");
				System.exit(-1);
			}
		}
		
		return serviceCode;
	}
	
	/**
	 * 
	 * getCode
	 *
	 * @return
	 */
	public String getCode()
	{
		return getServiceCode();
	}
	
	/**
	 * 
	 * getUse
	 *
	 * @return
	 */
	public String getUse()
	{
		String ret = null;
		
		if (Flag.TRUE) {
			ret = get("anal.service." + getServiceCode() + ".use");
		}
		
		return ret;
	}
	
	/**
	 * 
	 * getName
	 *
	 * @return
	 */
	public String getName()
	{
		String ret = null;
		
		if (Flag.TRUE) {
			ret = get("anal.service." + getServiceCode() + ".name");
		}
		
		return ret;
	}
	
	/**
	 * 
	 * getTitle
	 *
	 * @return
	 */
	public String getTitle()
	{
		String ret = null;
		
		if (Flag.TRUE) {
			ret = get("anal.service." + getServiceCode() + ".title");
		}
		
		return ret;
	}
	
	/**
	 * 
	 * getServiceBasePath
	 *
	 * @return
	 */
	public String getServiceBasePath()
	{
		String ret = null;
		
		if (Flag.TRUE) {
			ret = get("anal.service." + getServiceCode() + ".base.path").replace("{anal.base.path}", SystemProperties.getInstance().getAnalBasePath());
		}
		
		return ret;
	}
	
	/**
	 * getSystemType
	 * 
	 * (D:Development개발서버, T:Test테스트서버, R:Real운영서버)
	 * 
	 * @return
	 */
	public String getSystemType()
	{
		String ret = null;
		
		if (Flag.TRUE) {
			ret = get("anal.system.type", "D").substring(0, 1).toUpperCase();
		}
		
		return ret;
	}
	
	///////////////////////////////////////////////////////////////////////////
	
	/**
	 * Singleton
	 */
	private static AnalProperties instance = null;
	
	public static synchronized AnalProperties getInstance()
	{
		if (instance == null) {
			instance = new AnalProperties();
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
			AnalProperties.getInstance().printList();
			if (!Flag.TRUE) System.out.println(">" + AnalProperties.getInstance().get("anal.service.010.base.path"));
		}
	}
	
	private static void test02()
	{
		if (Flag.TRUE) {
			SystemProperties.setTesting("010");
		}
		
		if (Flag.TRUE) {
			AnalProperties prop = AnalProperties.getInstance();
			
			if (Flag.TRUE) System.out.println(">" + prop.get("anal.service.040.base.path"));
			if (Flag.TRUE) System.out.println(">" + prop.getServiceCode());  // private
			if (Flag.TRUE) System.out.println(">" + prop.getCode ());
			if (Flag.TRUE) System.out.println(">" + prop.getUse  ());
			if (Flag.TRUE) System.out.println(">" + prop.getName ());
			if (Flag.TRUE) System.out.println(">" + prop.getTitle());
			if (Flag.TRUE) System.out.println(">" + prop.getServiceBasePath());
		}
	}
	
	private static void test03()
	{
		if (Flag.TRUE) {
			SystemProperties.setTesting("010");
		}
		
		if (Flag.TRUE) {
			AnalProperties prop = AnalProperties.getInstance();
			if (Flag.TRUE) System.out.println(">" + prop.getSystemType());
		}
	}
	
	public static void main(String[] args)
	{
		if (!Flag.TRUE) test01();
		if (!Flag.TRUE) test02();
		if (Flag.TRUE) test03();
	}
}
