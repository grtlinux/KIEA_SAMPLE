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

package kiea.z01.ztest.t01.FeatureDescriptor.BeanInfo.t01;

import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;

import kiea.kr.co.tain.base.Flag;

/**
 * @author KangSeok
 * @date 2014. 8. 5.
 * @file_name BeanInfoTestMain.java
 *
 */
public class BeanInfoTestMain
{
	///////////////////////////////////////////////////////////////////////////
	
	/**
	 * test-01
	 */
	private static void test01()
	{
		if (Flag.TRUE) {
			try {
				// BeanInfo beanInfo = Introspector.getBeanInfo(TestMultipleBean.class);
				BeanInfo beanInfo = Introspector.getBeanInfo(String.class);
				PropertyDescriptor[] descs = beanInfo.getPropertyDescriptors();
				
				for (PropertyDescriptor prop : descs) {
					if (Flag.TRUE) System.out.println(String.format("[%s] [%s] [%s]", prop.getName(), prop.getClass().getName(), prop.getDisplayName()));
					if (Flag.TRUE) System.out.println(String.format("[%s]", prop.getValue("kkk")));  // -> null
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
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
