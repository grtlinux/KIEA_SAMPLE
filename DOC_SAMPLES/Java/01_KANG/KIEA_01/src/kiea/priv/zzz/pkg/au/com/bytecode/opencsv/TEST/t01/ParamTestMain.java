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

package kiea.priv.zzz.pkg.au.com.bytecode.opencsv.TEST.t01;

import java.io.FileReader;

import au.com.bytecode.opencsv.CSVReader;

/**
 * @author KangSeok
 * @date 2014. 10. 1.
 * @file_name ParamTestMain.java
 *
 */
public class ParamTestMain
{
	private static boolean flag = true;

	///////////////////////////////////////////////////////////////////////////
	
	private static void test01()
	{
		if (flag) {
			String jobId = "AR010141001A3268";
			String date = "20" + jobId.substring(5, 11);
			String strFile = "D:/KANG/qaf/data/" + date + "/" + jobId + "/AIB_JOBID_PARAM.csv";
			
			try {
				CSVReader reader = new CSVReader(new FileReader(strFile));
				String[] line;
				
				while ((line = reader.readNext()) != null) {
					System.out.println("JOBID     : [" + line[0] + "]");
					System.out.println("SEQ       : [" + line[1] + "]");
					System.out.println("PARAM_NM  : [" + line[2] + "]");
					System.out.println("PARAM_VAL : [" + line[3] + "]");
					System.out.println();
				}
				
				reader.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void main(String[] args)
	{
		if (flag) test01();
	}
}
