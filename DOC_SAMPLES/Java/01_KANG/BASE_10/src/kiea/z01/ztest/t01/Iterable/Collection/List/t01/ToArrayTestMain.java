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

package kiea.z01.ztest.t01.Iterable.Collection.List.t01;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import kiea.kr.co.tain.base.Flag;

/**
 * @author KangSeok
 * @date 2014. 8. 28.
 * @file_name ToArrayTestMain.java
 *
 */
public class ToArrayTestMain
{
	private static void test01()
	{
		if (Flag.TRUE) {
			String[] strs = { "»´±Êµø", "¿Ã∏˘∑Ê", "º∫√·«‚", "¿Ã¥ﬁ∑°", "¿±¥Ÿºÿ", "±Ë«œ≥™", "¿Ã¿∫∞Ê", "¿Ã¿∫¡÷" };
			for (String str : strs) {
				System.out.println(str);
			}
			
			System.out.println("------------------");
			List<String> list = new ArrayList<String>();
			for (String str : strs) {
				list.add(str);
			}
			System.out.println(list);
			
			Collections.sort(list);
			System.out.println(list);
			
			System.out.println("------------------");
			String[] strs2 = list.toArray(new String[list.size()]);
			for (String str : strs2) {
				System.out.println(str);
			}
			
			System.out.println("------------------");
			Object[] objs = list.toArray();
			for (Object obj : objs) {
				String str = (String) obj;
				System.out.println(str);
			}
		}
	}

	public static void main(String[] args)
	{
		if (Flag.TRUE) test01();
	}
}
