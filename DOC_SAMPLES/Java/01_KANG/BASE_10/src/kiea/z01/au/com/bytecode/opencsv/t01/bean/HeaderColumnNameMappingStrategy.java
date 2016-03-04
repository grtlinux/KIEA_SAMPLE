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

package kiea.z01.au.com.bytecode.opencsv.t01.bean;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import kiea.z01.au.com.bytecode.opencsv.t01.CSVReader;

/**
 * @author KangSeok
 * @date 2014. 8. 27.
 * @file_name HeaderColumnNameMappingStrategy.java
 *
 */
public class HeaderColumnNameMappingStrategy<T> implements MappingStrategy<T>
{
	protected String[] header;
	protected Map<String, PropertyDescriptor> descriptorMap = null;
	protected Class<T> type;
	
	public void captureHeader(CSVReader reader) throws IOException
	{
		header = reader.readNext();
	}
	
	public PropertyDescriptor findDescriptor(int col) throws IntrospectionException
	{
		String columnName = getColumnName(col);
		return (columnName != null && columnName.trim().length() > 0) ? findDescriptor(columnName) : null;
	}
	
	protected String getColumnName(int col)
	{
		return (header != null && col < header.length) ? header[col] : null;
	}
	
	protected PropertyDescriptor findDescriptor(String name) throws IntrospectionException
	{
		if (this.descriptorMap == null) {
			this.descriptorMap = loadDescriptorMap(getType());
		}
		return descriptorMap.get(name.toUpperCase().trim());
	}
	
	/**
	 * 
	 * matches
	 * CSV 헤더가 곧바로 Properties의 이름이 된다.  예) HELLO_WORLD -> Hello_World
	 *
	 * @param name
	 * @param desc
	 * @return
	 */
	protected boolean matches(String name, PropertyDescriptor desc)
	{
		return desc.getName().equals(name.trim());
	}
	
	protected Map<String, PropertyDescriptor> loadDescriptorMap(Class<T> cls) throws IntrospectionException
	{
		Map<String, PropertyDescriptor> map = new HashMap<String, PropertyDescriptor>();
		
		PropertyDescriptor[] descriptors = loadDescriptors(cls);
		for (PropertyDescriptor descriptor : descriptors) {
			map.put(descriptor.getName().toUpperCase().trim(), descriptor);
		}
		
		return map;
	}
	
	private PropertyDescriptor[] loadDescriptors(Class<T> cls) throws IntrospectionException
	{
		BeanInfo beanInfo = Introspector.getBeanInfo(cls);
		return beanInfo.getPropertyDescriptors();
	}
	
	public T createBean() throws InstantiationException, IllegalAccessException
	{
		return this.type.newInstance();
	}
	
	public Class<T> getType()
	{
		return this.type;
	}
	
	public void setType(Class<T> type)
	{
		this.type = type;
	}
}
