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

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.beans.PropertyEditor;
import java.beans.PropertyEditorManager;
import java.io.Reader;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import kiea.z01.au.com.bytecode.opencsv.t01.CSVReader;

/**
 * @author KangSeok
 * @date 2014. 8. 27.
 * @file_name CsvToBean.java
 *
 */
public class CsvToBean<T>
{
	private Map<Class<?>, PropertyEditor> editorMap = null;
	
	public CsvToBean() {}
	
	public List<T> parse(MappingStrategy<T> mapper, Reader reader)
	{
		return parse(mapper, new CSVReader(reader));
	}
	
	public List<T> parse(MappingStrategy<T> mapper, CSVReader csv)
	{
		try {
			mapper.captureHeader(csv);
			String[] line;
			List<T> list = new ArrayList<T>();
			while ((line = csv.readNext()) != null) {
				T obj = processLine(mapper, line);
				list.add(obj);
			}
			return list;
		} catch (Exception e) {
			//e.printStackTrace();
			throw new RuntimeException("Error parsing CSV!", e);
		}
	}
	
	protected T processLine(MappingStrategy<T> mapper, String[] line) throws
	IllegalAccessException,
	InvocationTargetException,
	InstantiationException,
	IntrospectionException {
		T bean = mapper.createBean();
		for (int col=0; col < line.length; col++) {
			PropertyDescriptor prop = mapper.findDescriptor(col);
			if (prop != null) {
				String value = checkForTrim(line[col], prop);
				Object obj = convertValue(value, prop);
				prop.getWriteMethod().invoke(bean, obj);
			}
		}
		return bean;
	}
	
	private String checkForTrim(String s, PropertyDescriptor prop)
	{
		return trimmableProperty(prop) ? s.trim() : s;
	}
	
	private boolean trimmableProperty(PropertyDescriptor prop)
	{
		return !prop.getPropertyType().getName().contains("String");
	}
	
	protected Object convertValue(String value, PropertyDescriptor prop) throws
	InstantiationException,
	IllegalAccessException {
		PropertyEditor editor = getPropertyEditor(prop);
		Object obj = value;
		if (editor != null) {
			editor.setAsText(value);
			obj = editor.getValue();
		}
		return obj;
	}
	
	private PropertyEditor getPropertyEditorValue(Class<?> cls)
	{
		if (editorMap == null) {
			editorMap = new HashMap<Class<?>, PropertyEditor>();
		}
		
		PropertyEditor editor = editorMap.get(cls);
		
		if (editor == null) {
			editor = PropertyEditorManager.findEditor(cls);
			addEditorToMap(cls, editor);
		}
		
		return editor;
	}
	
	private void addEditorToMap(Class<?> cls, PropertyEditor editor)
	{
		if (editor != null) {
			editorMap.put(cls, editor);
		}
	}
	
	/*
	 * attempt to find custom property editor on descriptor first, else try the proeperty editor manager.
	 */
	protected PropertyEditor getPropertyEditor(PropertyDescriptor desc) throws
	InstantiationException,
	IllegalAccessException {
		Class<?> cls = desc.getPropertyEditorClass();
		if (null != cls) return (PropertyEditor) cls.newInstance();
		return getPropertyEditorValue(desc.getPropertyType());
	}
}
