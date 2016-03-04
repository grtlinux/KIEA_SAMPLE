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

package sdc.anal.mura.macro.A90.SEND_FTP_REPORT.v04;

import java.sql.Statement;

import kiea.kr.co.tain.base.Flag;
import kiea.kr.co.tain.util.Print;
import kiea.kr.co.tain.util.SystemProperties;
import kiea.proj.sdc.anal.common.Connection04;
import kiea.proj.sdc.anal.tools.AnalCsv.v01.AnalCsvBean;
import kiea.proj.sdc.anal.tools.AnalCsv.v01.AnalCsvType;
import kiea.proj.sdc.anal.util.AnalProperties;
import kiea.proj.sdc.anal.util.BasePath;
import kiea.proj.sdc.anal.util.StrUtil;
import kiea.proj.sdc.anal.util.log.v02.Logger;
import oracle.jdbc.OracleConnection;

/**
 * @author KangSeok
 * @date 2014. 9. 28.
 * @file_name FtpSendReportMain.java
 *
 */
public class FtpSendReportMain
{
	private String jobId = null;
	private String dsName = "A90_" + "SEND_FTP_REPORT";
	
	protected static long cntWList = 0;
	protected static long runMSec = 0;

	public FtpSendReportMain(String[] args)
	{
		if (Flag.TRUE) {
			if (args.length == 2) {
				/*
				 * setting
				 */
				this.jobId = args[0];
				this.dsName = args[1];
			} else {
				// ERROR 처리
				Print.println("ERROR : parameter error.");
			}
		}
		
		if (Flag.TRUE) {
			/*
			 * 반드시 먼저 선언해야 할 것
			 * 1. base path : ..../qaf 까지
			 */
			BasePath.getInstance();

			/*
			 * 사용할 로그정보
			 */
			try {
				Logger.getInstance(AnalProperties.getInstance().getServiceBasePath(), this.jobId, this.dsName + ".log");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	/**
	 * 
	 * setFtpSendStatus
	 *
	 * @param JobId
	 */
	private void setFtpSendStatus(String JobId)
	{
		if (!Flag.TRUE) {
			/*
			 * STATUS -> RUNNING으로 UPDATE
			 */
			try {
				
				OracleConnection conn = Connection04.getInstance().getConn();
				Statement stmt = conn.createStatement();

				String query = String.format("UPDATE anal_jobid SET status = 'FTP_SEND' WHERE job_id=%s ", StrUtil.quote(JobId));
				
				int cnt = stmt.executeUpdate(query);
				if (cnt != 1)
					if (!Flag.TRUE) System.out.println("ERROR : not exist");
				
				Connection04.getInstance().close();;
				if (!Flag.TRUE) System.out.println("##### OK!");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		if (Flag.TRUE) {
			/*
			 * UPDATE
			 */
			AnalCsvBean bean = new AnalCsvBean();
			
			bean.setType(AnalCsvType.UPDATE);
			bean.setTable("ANAL_JOBID");
			bean.setFields(new String[][] {
					{ "STATUS ", StrUtil.quote("FTP_SEND") },
			});
			bean.setWhere(String.format("JOB_ID = %s", StrUtil.quote(jobId)));
			
			if (Flag.TRUE) Logger.info("[%s][%s]", bean, bean.getQuery());
			
			bean.sendToOracle();
		}
	}
	
	/**
	 * 
	 */
	public void execute()
	{
		if (Flag.TRUE) {
			/*
			 * 기존 자료 삭제
			 */
			AnalCsvBean bean = new AnalCsvBean();

			bean.setType(AnalCsvType.DELETE);
			bean.setTable("ANAL_CSV");
			bean.setWhere(String.format("JOB_ID = %s AND PROG_NM = %s", StrUtil.quote(jobId), StrUtil.quote(dsName)));
			
			bean.sendToOracle();
			
			if (Flag.TRUE) Logger.info("[%s][%s]", bean, bean.getQuery());
		}
		
		if (Flag.TRUE) {
			/*
			 * INSERT
			 */
			AnalCsvBean bean = new AnalCsvBean();
			
			bean.setType(AnalCsvType.INSERT);
			bean.setTable("ANAL_CSV");
			bean.setFields(new String[][] {
					{ "JOB_ID     ", StrUtil.quote(jobId) },
					{ "CMD_CODE   ", StrUtil.quote(dsName.substring(0, 3)) },
					{ "PROG_NM    ", StrUtil.quote(dsName) },
					{ "CSV_NM     ", StrUtil.quote(dsName + ".csv") },
					{ "MAIN_CLASS ", StrUtil.quote(this.getClass().getName()) },
					{ "S_TIME     ", "SYSDATE" },
					{ "CSV_STATUS ", StrUtil.quote("START") },
			});
			if (Flag.TRUE) Logger.info("[%s][%s]", bean, bean.getQuery());

			bean.sendToOracle();
		}

		if (Flag.TRUE) {
			
			long time1, time2;
			time1 = System.nanoTime();

			setFtpSendStatus(jobId);
			
			FtpInfo ftpInfo = new FtpReportList(jobId).getInfo();
			if (Flag.TRUE) System.out.println(ftpInfo);
			
			new FtpListSend(ftpInfo).send();
			
			time2 = System.nanoTime();
			
			runMSec = (time2 - time1)/1000/1000;
			if (Flag.TRUE) Logger.info("##### took time : %d ms", runMSec);
		}
		
		if (Flag.TRUE) {
			/*
			 * UPDATE
			 */
			AnalCsvBean bean = new AnalCsvBean();
			
			bean.setType(AnalCsvType.UPDATE);
			bean.setTable("ANAL_CSV");
			bean.setFields(new String[][] {
					{ "LIST_CNT   ", "" + cntWList },
					{ "E_TIME     ", "SYSDATE" },
					{ "R_MSEC     ", "" + runMSec },
					{ "CSV_STATUS ", StrUtil.quote("OK") },   // OK, ERROR, SKIP, ETC...
					{ "LOG_MESSAGE", StrUtil.quote("COMPLETE") },  // COMPLETE, EXCEPTION
			});
			bean.setWhere(String.format("JOB_ID = %s AND PROG_NM = %s", StrUtil.quote(jobId), StrUtil.quote(dsName)));
			
			if (Flag.TRUE) Logger.info("[%s][%s]", bean, bean.getQuery());
			
			bean.sendToOracle();
		}

		Logger.exit();
		System.exit(0);
	}
	
	///////////////////////////////////////////////////////////////////////////
	
	private static void test01(String[] args)
	{
		if (Flag.TRUE) {
			SystemProperties.setTesting("020");
		}

		new FtpSendReportMain(args).execute();
	}
	
	public static void main(String[] args)
	{
		if (Flag.TRUE) test01(args);
		if (!Flag.TRUE) test01(new String[] { "AR020140928A0000", "FTP_SEND_REPORT" });  // JOBID, LOGFILE
	}
}
