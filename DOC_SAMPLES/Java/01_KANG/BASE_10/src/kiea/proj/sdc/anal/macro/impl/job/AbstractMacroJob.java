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

package kiea.proj.sdc.anal.macro.impl.job;

import kiea.kr.co.tain.base.Flag;

/**
 * @author KangSeok
 * @date 2014. 8. 5.
 * @file_name AbstractMacroJob.java
 *
 */
public abstract class AbstractMacroJob
{
	/*
	 * 테스트 DataSet을 생성한다.
	 */
	public abstract void generateDataSet();
	
	/*
	 * 1. InBean, OutBean 준비
	 * 1. DataSetReader로 InBean의 자료를 읽는다.
	 */
	public abstract void beforeMacroJob();
	
	/*
	 * 1. InBean 자료를 읽어 OutBean 자료를 만든다.
	 * 2. 연산을 이용하여 OutBean 자료를 만든다.
	 */
	public abstract void macroJob();
	
	/*
	 * 1. DataSetWriter로 OutBean 자료를 쓴다.
	 */
	public abstract void afterMacroJob();
	
	
	public void executeMacroJob()
	{
		if (Flag.TRUE) generateDataSet();
		
		if (Flag.TRUE) beforeMacroJob();
		
		if (Flag.TRUE) macroJob();
		
		if (Flag.TRUE) afterMacroJob();
	}
}
