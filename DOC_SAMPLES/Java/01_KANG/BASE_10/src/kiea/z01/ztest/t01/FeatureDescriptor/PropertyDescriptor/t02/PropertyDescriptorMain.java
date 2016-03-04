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

package kiea.z01.ztest.t01.FeatureDescriptor.PropertyDescriptor.t02;

import java.beans.PropertyDescriptor;
import java.util.ArrayList;
import java.util.List;

import kiea.kr.co.tain.base.Flag;

import org.apache.commons.beanutils.PropertyUtils;

/**
 * @author KangSeok
 * @date 2014. 8. 5.
 * @file_name PropertyDescriptorMain.java
 *
 */
public class PropertyDescriptorMain
{
	///////////////////////////////////////////////////////////////////////////

	private Object convertTarget;
	
	private String objectId;           // ResultMap의 ID
	
	private String objectPackageName;  // Package Name
	
	private String objectClassName;    // ClassName
	
	private String[] excludeFields;    // Exclude fields 반드시 배제해야 하는 필드들의 리스트
	
	private String[] idFields;         // ID 선별 Fields
	
	private int depth = 0;
	private List<ClassDepthInfo> cdiInfo = new ArrayList<ClassDepthInfo>();
	
	/*
	 * 고정형 상수 : Template을 만들기 위한 고정형 구조
	 */
	private final String HEADER = "<resultMap\n id=\"#id#\"\n type=\"#type#\">";
	private final String IDFIELD = " <id property=\"#field#\"  column=\"#dbcolumn#\" />";
	private final String RESULTFIELD = " <result property=\"#field#\"  column=\"#dbcolumn#\" />";
	private final String FOOTER = "</resultMap>";
	private final String DEFAULT_DBCOLUMN = "";

	public void setConvertTarget(Object convertTarget)
	{
		this.convertTarget = convertTarget;
		
		// Default로 PackageName과 ClassName을 설정해주자
		Class<?> cls = convertTarget.getClass();
		try {
			this.objectClassName = cls.getName();
		} catch (Exception e) {
			this.objectClassName = null;
		}
		if (Flag.TRUE) System.out.println(String.format("[objectClassName=[%s]", this.objectClassName));
		
		// Package가 아예 없는 경우가 있으므로 분리한다.
		try {
			this.objectPackageName = cls.getPackage().getName();
		} catch (Exception e) {
			this.objectPackageName = null;
		}
		if (Flag.TRUE) System.out.println(String.format("[objectPackageName=[%s]", this.objectPackageName));
	}
	
	public String makeResultMapToString()
	{
		StringBuffer sb = new StringBuffer();
		sb.append(makeHeader(HEADER, objectId, objectClassName));
		sb.append("\n");
		
		ClassDepthInfo cdi = new ClassDepthInfo(depth, convertTarget.getClass().getName());
		cdiInfo.add(cdi);
		List<String> fieldsList = listNestedPropertyName(convertTarget.getClass());
		
		for (String eachFieldName : fieldsList) {
			if (isExcludeField(eachFieldName)) {
				// 배제대상이므로 그냥 넘어간다.
				continue;
			}
			
			if (isIdField(eachFieldName)) {
				// ID Field라면 ID로 추가해준다.
				sb.append(makeFieldInfo(IDFIELD, eachFieldName));
			} else {
				// ID Field가 아니라면 모두 result로 간주하고 result로 추가한다.
				sb.append(makeFieldInfo(RESULTFIELD, eachFieldName));
			}
			sb.append("\n");
		}
		
		sb.append(FOOTER);
		return sb.toString();
	}
	
	private String makeHeader(String headerString, String id, String type)
	{
		headerString = headerString.replaceAll("#id#", id);
		headerString = headerString.replaceAll("#type#", type);
		return headerString;
	}
	
	private String makeFieldInfo(String fieldInfoString, String field, String dbcolumn)
	{
		fieldInfoString = fieldInfoString.replaceAll("#field#", field);
		fieldInfoString = fieldInfoString.replaceAll("#dbcolumn#", dbcolumn);
		return fieldInfoString;
	}
	
	private String makeFieldInfo(String fieldInfoString, String field)
	{
		return makeFieldInfo(fieldInfoString, field, DEFAULT_DBCOLUMN);
	}
	
	private boolean isExcludeField(String fieldName)
	{
		return isInArrayList(excludeFields, fieldName);
	}
	
	private boolean isIdField(String fieldName)
	{
		return isInArrayList(idFields, fieldName);
	}
	
	private boolean isInArrayList(String[] anyList, String determiner)
	{
		boolean result = false;
		
		// Argument 가 없으면 무저건 false;
		if (anyList == null || determiner == null)
			return result;
		
		for (String oneStringFromList : anyList) {
			if (oneStringFromList.equals(determiner)) {
				result = true;
				return result;
			}
		}
		return result;
	}
	
	public List<String> listNestedPropertyName(Class<?> objectSource)
	{
		List<String> nodeNameList = new ArrayList<String>();
		
		PropertyDescriptor[] propertyDescriptors = PropertyUtils.getPropertyDescriptors(objectSource);
		depth++;
		
		for (PropertyDescriptor propertyDescriptor : propertyDescriptors) {
			String name = propertyDescriptor.getName();
			Class<?> valueClass = propertyDescriptor.getPropertyType();
			
			if ("class".equals(name)) {
				continue;
			}
			
			if ("java.util.List".equals(valueClass.getName())) {
				name = name + "[]";
			}
			
			if ("java.util.Map".equals(valueClass.getName())) {
				name = name + "()";
			}
			
			if (valueClass.isPrimitive() 
					|| valueClass.getName().indexOf("java.lang") >= 0
					|| valueClass.getName().indexOf("java.util") >= 0
					|| valueClass.getName().indexOf("java.math") >= 0
					) {
				// simple value
				nodeNameList.add(name);
			} else {
				// 무한루프가 빠질 수 있으므로 방지루틴이 필요함.
				ClassDepthInfo cdi = new ClassDepthInfo(depth, valueClass.getName());
				cdiInfo.add(cdi);
				
				if (isAlreadyUsedClass(cdiInfo, valueClass.getName(), depth)) {
					// 해당클래스가 다른 depth에서 이미 쓰였다면 해당 클래스에
					// 대해서 주의내용을 표시하고 nodeName에 추가 안하면 된다.
					System.out.println("클래스 " + valueClass.getName() + " (빈 ID:" + name + ")는 이미 상위클래스에서 사용중입니다.");
					System.out.println("따라서 더 이상 분석하지 않습니다.");
				} else {
					// nested value
					List<String> nestedNames = listNestedPropertyName(valueClass);
					for (String nestedName : nestedNames) {
						nodeNameList.add(name + "." + nestedName);
					}
					
					// recursive가 끝나면 복귀한다.
					cdiInfo.remove(cdi);
				}
			}
		}
		
		depth--;
		return nodeNameList;
	}
	
	private boolean isAlreadyUsedClass(List<ClassDepthInfo> cdiInfo2, String name, int depth2)
	{
		for (ClassDepthInfo cdi : cdiInfo2) {
			if (name.equals(cdi.getClassName()) && depth2 != cdi.getDepth()) {
				return true;
			}
		}
		
		return false;
	}
	
	/*
	 * General getter and setter
	 */
	
	public Object getConvertTarget()
	{
		return convertTarget;
	}

	public String getObjectId()
	{
		return objectId;
	}

	public String getObjectPackageName()
	{
		return objectPackageName;
	}

	public String getObjectClassName()
	{
		return objectClassName;
	}

	public String[] getExcludeFields()
	{
		return excludeFields;
	}

	public String[] getIdFields()
	{
		return idFields;
	}

	public void setObjectId(String objectId)
	{
		this.objectId = objectId;
	}

	public void setObjectPackageName(String objectPackageName)
	{
		this.objectPackageName = objectPackageName;
	}

	public void setObjectClassName(String objectClassName)
	{
		this.objectClassName = objectClassName;
	}

	public void setExcludeFields(String[] excludeFields)
	{
		this.excludeFields = excludeFields;
	}

	public void setIdFields(String[] idFields)
	{
		this.idFields = idFields;
	}

	///////////////////////////////////////////////////////////////////////////
	
	/**
	 * test-01
	 */
	private static void test01()
	{
		if (Flag.TRUE) {
			PropertyDescriptorMain main = new PropertyDescriptorMain();
			
			TestMultipleBean bean = new TestMultipleBean();
			
			main.setConvertTarget(bean);
			main.setObjectId("getTestMultipleBeanResultMap");
			main.setIdFields(new String[] {"testId"});
			System.out.println(main.makeResultMapToString());
		}
	}
	
	/**
	 * main entry point
	 * @param args
	 */
	public static void main(String[] args)
	{
		if (Flag.TRUE) test01();
	}
}
