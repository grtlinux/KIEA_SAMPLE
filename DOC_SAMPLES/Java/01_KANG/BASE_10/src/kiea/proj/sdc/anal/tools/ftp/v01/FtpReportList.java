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

package kiea.proj.sdc.anal.tools.ftp.v01;

import java.io.File;
import java.io.FilenameFilter;

import kiea.kr.co.tain.base.Flag;
import kiea.kr.co.tain.util.Print;
import kiea.proj.sdc.anal.util.BasePath;

/**
 * @author KangSeok
 * @date 2014. 9. 28.
 * @file_name FtpReportList.java
 *
 */
public class FtpReportList
{
	private String date = null;
	private String jobId = null;
	private String path = null;
	
	private FtpInfo info = null;
	
	public FtpReportList(String jobId)
	{
		// TODO : 2014.09.28 : need the validation
		if (Flag.TRUE) {
			this.jobId = jobId;  // AR 010 140928 A 0000
			this.date = "20" + this.jobId.substring(5, 11);
			this.path = BasePath.getInstance().getDataPath() + "/" + this.date + "/" + this.jobId;
			
			this.info = new FtpInfo(this.jobId);
		}
	}
	
	public FtpInfo getInfo()
	{
		if (Flag.TRUE) {
			File dir = new File(this.path);
			String[] files = dir.list(new FilenameFilter() {
				@Override
				public boolean accept(File dir, String name) {
					if (!Flag.TRUE) {
						try {
							Print.println("[%s] [%s] [%s] [%s] [%s]", dir.getAbsolutePath(), dir.getCanonicalPath(), dir.getPath(), dir.getName(), name);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
					
					if (name.startsWith("RP_")) {
						return true;
					} else {
						return false;
					}
				}
			});
			
			for (String file : files) {
				if (!Flag.TRUE) Print.println("\t\t[%s]", file);
				info.addFile(file);
			}
		}
		
		return this.info;
	}
	
	///////////////////////////////////////////////////////////////////////////
	
	private static void test01()
	{
		if (Flag.TRUE) {
			FtpInfo info = new FtpReportList("AR010140928A0000").getInfo();
			if (Flag.TRUE) System.out.println(info);
		}
	}
	
	public static void main(String[] args)
	{
		if (Flag.TRUE) test01();
	}
}
