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

package kiea.proj.sdc.anal.tools.delarch.v01;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import kiea.kr.co.tain.base.Flag;
import kiea.kr.co.tain.util.DateTime;
import kiea.kr.co.tain.util.Print;

/**
 * @author KangSeok
 * @date 2014. 9. 22.
 * @file_name DeleteArchiveMain.java
 *
 */
public class DeleteArchiveMain
{
	private String motherFolder;
	private int limitHour;
	private long limitTime;
	private SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss.SSS");
	
	public DeleteArchiveMain(String motherFolder, int limitHour)
	{
		this.motherFolder = motherFolder;
		this.limitHour = limitHour;
		this.limitTime = DateTime.addHours(new Date(), this.limitHour);
	}
	
	public void execute()
	{
		if (Flag.TRUE) {
			
			File file = new File(this.motherFolder);
			if (!Flag.TRUE) Print.println("[%s] [%s]", file.getPath(), sdf.format(new Date(file.lastModified())));
			
			deleteArch(file);
		}
	}

	private boolean deleteArch(File folder)
	{
		boolean delFlag = true;
		
		if (Flag.TRUE) {
			try {
				
				File[] files = folder.listFiles();
				
				for (File file : files) {
					if (!Flag.TRUE) Print.println("\t[%s] [%s]", file.getPath(), sdf.format(new Date(file.lastModified())));
					
					if (file.isDirectory()) {
						delFlag = deleteArch(file);
						if (delFlag) {
							// del directory
							if (Flag.TRUE) Print.println("DEL [%s]      [%s]", file.getPath(), sdf.format(new Date(file.lastModified())));
							if (Flag.TRUE) file.delete();
						}
					} else if (file.isFile()) {
						if (this.limitTime > file.lastModified()) {
							// del file
							if (Flag.TRUE) Print.println("DEL  \t[%s]      [%s]", file.getPath(), sdf.format(new Date(file.lastModified())));
							if (Flag.TRUE) file.delete();
						} else {
							delFlag = false;
						}
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		return delFlag;
	}
	
	///////////////////////////////////////////////////////////////////////////
	
	private static void test01()
	{
		if (Flag.TRUE) {
			String motherFolder = "D:/KANG/qaf/prog/java/jdk1.6.0_45";
			int limitHour = 1;
			
			DeleteArchiveMain main = new DeleteArchiveMain(motherFolder, limitHour);
			main.execute();
		}
	}
	
	private static void test02(String[] args)
	{
		if (args.length != 2) {
			System.out.println("USAGE : Main [path] [hour]");
			System.out.println("ex) Main /users/infa/qaf/log  -1");
			System.exit(0);
		}
		
		if (!Flag.TRUE) Print.println("[%s] [%s]", args[0], args[1]);
		
		DeleteArchiveMain main = new DeleteArchiveMain(args[0], Integer.parseInt(args[1]));
		main.execute();
	}
	
	public static void main(String[] args)
	{
		if (!Flag.TRUE) test01();
		if (Flag.TRUE) test02(args);
	}
}
