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

package sdc.anal.lya.macro.A90.SEND_FTP_REPORT.v04;

import java.io.File;
import java.io.FilenameFilter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import kiea.kr.co.tain.base.Flag;
import kiea.kr.co.tain.util.Print;
import kiea.kr.co.tain.util.SystemProperties;
import kiea.proj.sdc.anal.util.BasePath;
import kiea.proj.sdc.anal.util.ServiceMacroProperties;

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
	
	private Map<String, FtpBean> mapFtpFile = new LinkedHashMap<String, FtpBean>();
	private List<FtpBean> listFtpFile = new ArrayList<FtpBean>();
	
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
			/*
			 * macro.properties 를 읽어 Map<String, FtpBean> 객체를 생성한다.
			 */
			ServiceMacroProperties propMacro = ServiceMacroProperties.getInstance();
			
			for (Entry<Object, Object> entry : propMacro.getProperties().entrySet()) {
				String key = (String) entry.getKey();
				String val = (String) entry.getValue();
				
				if (key.startsWith("anal.ftp.csv.file.")) {
					if (!Flag.TRUE) System.out.println("[" + key + "] [" + val + "]");
					
					FtpBean bean = new FtpBean();
					
					bean.setParamKey(key);
					bean.setParamValue(val);
					
					mapFtpFile.put(bean.getFromFile(), bean);
				}
			}
			
			if (!Flag.TRUE) {
				/*
				 * 확인출력
				 */
				for (Map.Entry<String, FtpBean> entry : mapFtpFile.entrySet()) {
					Print.println("[%s] => %s", entry.getKey(), entry.getValue());
				}
			}
		}
		
		if (Flag.TRUE) {
			/*
			 * JOBID 폴더의 파일을 확인한다. 신 버젼
			 */
			File dir = new File(this.path);
			@SuppressWarnings("unused")
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
					
					FtpBean bean = mapFtpFile.get(name);
					if (bean != null) {
						listFtpFile.add(bean);
						return true;
					} else {
						return false;
					}
				}
			});
			
			if (!Flag.TRUE) {
				/*
				 * 확인출력
				 */
				for (FtpBean bean : listFtpFile) {
					System.out.println(bean);
				}
			}
		}
		
		if (Flag.TRUE) {
			/*
			 * 정렬한다.
			 */
			Collections.sort(listFtpFile, new Comparator<FtpBean>() {
				@Override
				public int compare(FtpBean bean1, FtpBean bean2) {
					int ret = 0;
					
					// 1. JOBID
					ret = bean1.getParamKey().compareTo(bean2.getParamKey());
					if (ret != 0) return ret;

					return ret;
				}
			});
			
			if (!Flag.TRUE) {
				/*
				 * 확인출력
				 */
				for (FtpBean bean : listFtpFile) {
					System.out.println(bean);
				}
			}
		}
		
		if (Flag.TRUE) {
			/*
			 * info에 넣는다.
			 */
			for (FtpBean bean : listFtpFile) {
				info.addBean(bean);
			}
		}

		return this.info;
	}
	
	///////////////////////////////////////////////////////////////////////////
	
	private static void test01()
	{
		if (Flag.TRUE) {
			SystemProperties.setTesting("010");
		}
		
		if (!Flag.TRUE) {
			ServiceMacroProperties.getInstance().printList();
		}

		if (Flag.TRUE) {
			FtpInfo info = new FtpReportList("AR010141110A3143").getInfo();
			if (Flag.TRUE) System.out.println(info);
		}
	}
	
	public static void main(String[] args)
	{
		if (Flag.TRUE) test01();
	}
}
