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

package kiea.proj.sdc.anal.base.lya.detector.v02;

import kiea.kr.co.tain.base.Flag;
import kiea.kr.co.tain.util.Print;
import kiea.proj.sdc.anal.util.StrUtil;

/**
 * @author KangSeok
 * @date 2014. 9. 16.
 * @file_name DetectorMain.java
 *
 */
public class DetectorMain
{
	///////////////////////////////////////////////////////////////////////////
	
	private static void detect01_sample()
	{
		if (Flag.TRUE) {

			JobIdImpl impl = new SampleJobId();
			JobIdInfo info = null;
			
			if (!Flag.TRUE) {
				info = impl.get(1);
				new InsertJobId(info).execute();

				info = impl.get(2);
				new InsertJobId(info).execute();
				
				info = impl.get(3);
				new InsertJobId(info).execute();

				info = impl.get(4);
				new InsertJobId(info).execute();

				info = impl.get(5);
				new InsertJobId(info).execute();

				info = impl.get(6);
				new InsertJobId(info).execute();
			}
			
			if (Flag.TRUE) {
				info = impl.get(1);
				new InsertJobId(info).execute();

				info = impl.get(2);
				new InsertJobId(info).execute();
				
				info = impl.get(3);
				new InsertJobId(info).execute();

				info = impl.get(4);
				new InsertJobId(info).execute();
			}
		}
	}
	
	private static void detect02_copy()
	{
		if (Flag.TRUE) {
			new CopyJobId().execute();
		}
	}
	
	private static void detect03_detect()
	{
		if (Flag.TRUE) {

		}
	}
	
	///////////////////////////////////////////////////////////////////////////
	
	private static void test01()
	{
		JobIdImpl impl = new SampleJobId();
		
		JobIdInfo info = impl.get();
		
		Print.println("info.getLineCode            = [%s]", StrUtil.quote(info.getLineCode           ()));  // LINE_CODE
		Print.println("info.getAnalMethod          = [%s]", StrUtil.quote(info.getAnalMethod         ()));  // ANAL_METHOD
		Print.println("info.getAreaCode            = [%s]", StrUtil.quote(info.getAreaCode           ()));  // AREA_CODE
		Print.println("info.getSubAreaCode         = [%s]", StrUtil.quote(info.getSubAreaCode        ()));  // SUB_AREA_CODE
		Print.println("info.getUsl                 = [%s]", StrUtil.quote(info.getUsl                ()));  // USL

		Print.println("info.getInspectDtList       = [%s]", StrUtil.quote(info.getInspectDtList      ()));  // INSPECT_DT
		Print.println("info.getStepIdList          = [%s]", StrUtil.quote(info.getStepIdList         ()));  // STEP_ID
		Print.println("info.getProcessIdList       = [%s]", StrUtil.quote(info.getProcessIdList      ()));  // PROCESS_ID
		Print.println("info.getProductIdList       = [%s]", StrUtil.quote(info.getProductIdList      ()));  // PRODUCT_ID
		Print.println("info.getDecisionCodeList    = [%s]", StrUtil.quote(info.getDecisionCodeList   ()));  // DECISION_CODE
		Print.println("info.getUserGroupCodeList   = [%s]", StrUtil.quote(info.getUserGroupCodeList  ()));  // USER_GROUP_CODE
		Print.println("info.getDefectGroupCodeList = [%s]", StrUtil.quote(info.getDefectGroupCodeList()));  // DEFECT_GROUP_CODE
		Print.println("info.getProductTypeList     = [%s]", StrUtil.quote(info.getProductTypeList    ()));  // PRODUCT_TYPE
		
		for (String str : info.getDecisionCode()) {
			System.out.println(">" + str);
		}
	}

	private static void test02()
	{
		String str = "abc'def' sy";
		
		Print.println(" %d ", str.indexOf("'", 0)); 
		Print.println(" %d ", str.indexOf("z", 0)); 
	}
	
	public static void main(String[] args)
	{
		if (!Flag.TRUE) test01();
		if (!Flag.TRUE) test02();
		
		if (!Flag.TRUE) detect01_sample();
		if (Flag.TRUE) detect02_copy();
		if (!Flag.TRUE) detect03_detect();
	}
}
