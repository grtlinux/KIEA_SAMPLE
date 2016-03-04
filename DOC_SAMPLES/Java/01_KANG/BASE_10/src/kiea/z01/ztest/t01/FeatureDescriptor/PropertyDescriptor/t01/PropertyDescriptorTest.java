/* Copyright (c) 2008-2014, KangSeok
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 * Redistributions of source code must retain the above copyright notice, this
 * list of conditions and the following disclaimer.
 *
 * Redistributions in binary form must reproduce the above copyright notice,
 * this list of conditions and the following disclaimer in the documentation
 * and/or other materials provided with the distribution.
 *
 * Neither the name of the HSQL Development Group nor the names of its
 * contributors may be used to endorse or promote products derived from this
 * software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL HSQL DEVELOPMENT GROUP, HSQLDB.ORG,
 * OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL,
 * EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO,
 * PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
 * LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 * ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

package kiea.z01.ztest.t01.FeatureDescriptor.PropertyDescriptor.t01;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.MethodDescriptor;
import java.beans.PropertyDescriptor;

import kiea.kr.co.tain.base.Flag;
import kiea.kr.co.tain.util.Print;

/**
 * @author KangSeok
 * @date 2014. 8. 27.
 * @file_name PropertyDescriptorTest.java
 *
 */
class Info
{
	private String name;
	private String address;
	private String subject;
	/**
	 * @return the name
	 */
	public String getName()
	{
		return name;
	}
	/**
	 * @return the address
	 */
	public String getAddress()
	{
		return address;
	}
	/**
	 * @return the subject
	 */
	public String getSubject()
	{
		return subject;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name)
	{
		this.name = name;
	}
	/**
	 * @param address the address to set
	 */
	public void setAddress(String address)
	{
		this.address = address;
	}
	/**
	 * @param subject the subject to set
	 */
	public void setSubject(String subject)
	{
		this.subject = subject;
	}
}

public class PropertyDescriptorTest
{
	private static void test01()
	{
		if (!Flag.TRUE) {
			try {
				
				BeanInfo beanInfo = Introspector.getBeanInfo(Info.class);
				
				PropertyDescriptor[] props = beanInfo.getPropertyDescriptors();
				for (PropertyDescriptor prop : props) {
					
					String name = prop.getName();
					Class<?> type = prop.getPropertyType();
					String typeName = type.getName();
					
					if (Flag.TRUE) Print.println("[%s] [%s]", name, typeName);
				}
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		if (!Flag.TRUE) {
			try {
				
				BeanInfo beanInfo = Introspector.getBeanInfo(Class.forName("kiea.z01.ztest.t01.FeatureDescriptor.PropertyDescriptor.t01.Info"));
				
				PropertyDescriptor[] props = beanInfo.getPropertyDescriptors();
				for (PropertyDescriptor prop : props) {
					
					String name = prop.getName();
					Class<?> type = prop.getPropertyType();
					String typeName = type.getName();
					
					if (Flag.TRUE) Print.println("[%s] [%s]", name, typeName);
				}
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		if (Flag.TRUE) {
			try {
				Class<?> cls = Class.forName("kiea.z01.ztest.t01.FeatureDescriptor.PropertyDescriptor.t01.Info");
				BeanInfo beanInfo = Introspector.getBeanInfo(cls);
				
				PropertyDescriptor[] props = beanInfo.getPropertyDescriptors();
				for (PropertyDescriptor prop : props) {
					
					String name = prop.getName();
					Class<?> type = prop.getPropertyType();
					String typeName = type.getName();
					
					if (Flag.TRUE) Print.println("[%s] [%s]", name, typeName);
				}
				
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		if (!Flag.TRUE) {
			try {
				
				BeanInfo beanInfo = Introspector.getBeanInfo(Info.class);
				
				PropertyDescriptor[] props = beanInfo.getPropertyDescriptors();
				for (PropertyDescriptor prop : props) {
					if (Flag.TRUE) Print.println("Property : [%s] [%s] [%s]", prop.getName(), prop.getPropertyType().getName(), prop.getDisplayName());
				}
				
				Print.println();
				
				MethodDescriptor[] methods = beanInfo.getMethodDescriptors();
				for (MethodDescriptor method : methods) {
					if (Flag.TRUE) Print.println("Method : [%s] [%s] [%s]", method.getName(), method.getShortDescription(), method.getDisplayName());
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
