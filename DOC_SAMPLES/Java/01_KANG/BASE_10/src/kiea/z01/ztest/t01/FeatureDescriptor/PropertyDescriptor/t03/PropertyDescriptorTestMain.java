package kiea.z01.ztest.t01.FeatureDescriptor.PropertyDescriptor.t03;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.MethodDescriptor;
import java.beans.PropertyDescriptor;

import kiea.kr.co.tain.base.Flag;
import kiea.kr.co.tain.util.Print;

class Info
{
	private String name;
	private String address;
	private String subject;
	
	public String getName()
	{
		return name;
	}
	public void setName(String name)
	{
		this.name = name;
	}
	public String getAddress()
	{
		return address;
	}
	public void setAddress(String address)
	{
		this.address = address;
	}
	public String getSubject()
	{
		return subject;
	}
	public void setSubject(String subject)
	{
		this.subject = subject;
	}
}

public class PropertyDescriptorTestMain
{

	///////////////////////////////////////////////////////////////////////////
	
	private static void test01()
	{
		if (!Flag.TRUE) {
			try {
				String strClassName = "kiea.z01.ztest.t01.FeatureDescriptor.PropertyDescriptor.t03.Info";
				
				Class<?> cls = Class.forName(strClassName);
				BeanInfo beanInfo = Introspector.getBeanInfo(cls);
				PropertyDescriptor[] props = beanInfo.getPropertyDescriptors();
				for (PropertyDescriptor prop : props) {
					String name = prop.getName();
					String type = prop.getPropertyType().getName();
					
					Print.println("[%s] [%s]", name, type);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		if (Flag.TRUE) {
			try {
				String strClassName = "kiea.z01.ztest.t01.FeatureDescriptor.PropertyDescriptor.t03.Info";
				
				Class<?> cls = Class.forName(strClassName);
				BeanInfo beanInfo = Introspector.getBeanInfo(cls);
				
				PropertyDescriptor[] props = beanInfo.getPropertyDescriptors();
				for (PropertyDescriptor prop : props) {
					String name = prop.getName();
					String type = prop.getPropertyType().getName();
					Print.println("[%s] \t[%s]", type, name);
				}
				
				MethodDescriptor[] methods = beanInfo.getMethodDescriptors();
				for (MethodDescriptor method : methods) {
					String name = method.getName();
					String displayName = method.getDisplayName();
					String shortDesc = method.getShortDescription();
					
					Print.println("[%s] \t[%s] \t[%s]", name, displayName, shortDesc);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void main(String[] args)
	{
		if (Flag.TRUE) test01();
	}
}
