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

package kiea.proj.sdc.anal.macro.sample.a01;

import kiea.kr.co.tain.base.Flag;
import kiea.proj.sdc.anal.macro.sample.a01.job.Sample01MacroJob;
import kiea.proj.sdc.anal.util.BasePath;
import kiea.proj.sdc.anal.util.log.v02.Logger;

/**
 * @author KangSeok
 * @date 2014. 8. 5.
 * @file_name Sample01Main.java
 *
 */
public class Sample01Main
{
	@SuppressWarnings("unused")
	private static final String LOG_FILE = "SAMPLE_123";
	
	private String keyPath = "KEY_HHMM";
	private String dsName  = "Sample01";
	private int   num      = 123 * 1000;
	
	/**
	 * constructor
	 */
	public Sample01Main(String[] args)
	{
		if (Flag.TRUE) {

			if (args.length == 3) {
				/*
				 *  setting
				 */
				keyPath = args[0];
				dsName = args[1];
				num = Integer.parseInt(args[2]);
			}
		}
		
		if (Flag.TRUE) {
			/*
			 * 반드시 먼저 선언해야 할 것
			 * 1. base path : ..../qaf 까지
			 * 2. 사용할 로그정보
			 */
			BasePath.getInstance();

			try {
				Logger.getInstance(this.keyPath, this.dsName + ".log");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * run the macro job
	 */
	public void runMacroJob()
	{
		if (Flag.TRUE) {
			long time1, time2;
			time1 = System.nanoTime();

			new Sample01MacroJob(keyPath, dsName, num).executeMacroJob();

			time2 = System.nanoTime();
			if (Flag.TRUE) Logger.info("##### took time : %d ms", (time2 - time1)/1000/1000);
			
			Logger.exit();
		}
	}
	
	///////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////
	
	/**
	 * check the argument parameters
	 * @param args
	 */
	private static void test01(String[] args)
	{
		if (Flag.TRUE) {

			if (Flag.TRUE) System.out.println("Hello, 강석 !!!");
			
			for (int i=0; i < args.length; i++) {
				System.out.println("ARGS(" + i + ") [" + args[i] + "]");
			}
		}
	}

	/**
	 * run the macro job
	 * @param args
	 */
	private static void test02(String[] args)
	{
		if (Flag.TRUE) {
			/*
			 *  Default
			 */
			String keyPath = "KEY_HHMM";
			int num = 123 * 1000;
			String dsName = "Sample01";
			
			if (args.length == 3) {
				/*
				 *  setting
				 */
				keyPath = args[0];
				num = Integer.parseInt(args[1]);
				dsName = args[2];
			}
			
			if (Flag.TRUE) new Sample01MacroJob(keyPath, dsName, num).executeMacroJob();
		}
	}

	/**
	 * main entry
	 * @param args
	 */
	public static void main(String[] args)
	{
		if (!Flag.TRUE) test01(args);

		if (!Flag.TRUE) test02(args);
		
		if (Flag.TRUE) new Sample01Main(args).runMacroJob();
	}
}
