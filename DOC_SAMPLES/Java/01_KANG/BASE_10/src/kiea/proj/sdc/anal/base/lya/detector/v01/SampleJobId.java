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

package kiea.proj.sdc.anal.base.lya.detector.v01;

import kiea.kr.co.tain.base.Flag;

/**
 * @author KangSeok
 * @date 2014. 9. 16.
 * @file_name SampleJobid.java
 *
 */
public class SampleJobId implements JobIdImpl
{
	private JobIdInfo sample00()
	{
		JobIdInfo info = new JobIdInfo();
		
		info.setJobId          ("AU010140915A0001"            );
        
		info.setLineCode       ("L7AFAB"                      );
		info.setAnalMethod     ("FILTER"                      );
		info.setAreaCode       ("MOD"                         );
		info.setSubAreaCode    ("MMT"                         );
		info.setUsl            ("0.8"                         );
		                                                        
		info.addInspectDt      ("20140825050000"              );
		info.addInspectDt      ("20140826045959"              );
		info.addStepId         ("STEP"                        );
		info.addProcessId      ("PROCESS"                     );
		info.addProductId      ("PRODUCT"                     );
		info.addDecisionCode   ("RJ"                          );
		info.addDecisionCode   ("JR"                          );
		info.addDecisionCode   ("JJ"                          );
		info.addUserGroupCode  ("40-FHD-60P-MB4-PVA3-A6P-VNB5");
		info.addDefectGroupCode("UWSP"                        );
		info.addProductType    ("PP"                          );
		info.addProductType    ("TT"                          );
		info.addProductType    ("SS"                          );
		
		return info;
	}
	
	private JobIdInfo sample01()
	{
		JobIdInfo info = new JobIdInfo();
		
		info.setJobId          ("AU010140915A0001"            );
        
		info.setLineCode       ("L7AFAB"                      );
		info.setAnalMethod     ("FILTER"                      );
		info.setAreaCode       ("MOD"                         );
		info.setSubAreaCode    ("MMT"                         );
		info.setUsl            ("0.8"                         );
		                                                        
		info.addInspectDt      ("20140825050000"              );
		info.addInspectDt      ("20140826045959"              );
		info.addDecisionCode   ("RJ"                          );
		info.addUserGroupCode  ("40-FHD-60P-MB4-PVA3-A6P-VNB5");
		info.addDefectGroupCode("UWSP"                        );
		info.addProductType    ("PP"                          );
		
		return info;
	}
	
	public JobIdInfo get()
	{
		if (Flag.TRUE) return sample00();
		if (Flag.TRUE) return sample01();
		
		return null;
	}
}
