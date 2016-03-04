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

/**
 * @author KangSeok
 * @date 2014. 8. 5.
 * @file_name TestMultipleBean.java
 *
 */
public class TestMultipleBean
{
	private String testId = null;
	private String kkk = "123";
	private Test2Bean kk2;
	private Test2Bean kk_sub2;
	public String getTestId()
	{
		return testId;
	}
	public String getKkk()
	{
		return kkk;
	}
	public Test2Bean getKk2()
	{
		return kk2;
	}
	public Test2Bean getKk_sub2()
	{
		return kk_sub2;
	}
	public Test2Bean getKk_sub2_2()
	{
		return kk_sub2_2;
	}
	public void setTestId(String testId)
	{
		this.testId = testId;
	}
	public void setKkk(String kkk)
	{
		this.kkk = kkk;
	}
	public void setKk2(Test2Bean kk2)
	{
		this.kk2 = kk2;
	}
	public void setKk_sub2(Test2Bean kk_sub2)
	{
		this.kk_sub2 = kk_sub2;
	}
	public void setKk_sub2_2(Test2Bean kk_sub2_2)
	{
		this.kk_sub2_2 = kk_sub2_2;
	}
	private Test2Bean kk_sub2_2;
}
