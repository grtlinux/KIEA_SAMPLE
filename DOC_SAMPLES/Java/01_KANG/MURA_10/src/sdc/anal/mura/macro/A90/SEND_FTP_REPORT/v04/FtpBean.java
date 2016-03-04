package sdc.anal.mura.macro.A90.SEND_FTP_REPORT.v04;

import kiea.kr.co.tain.base.Flag;

public class FtpBean
{
	private String paramKey;
	private String paramValue;
	private String fromFile;
	private String toFile;
	
	public String toString()
	{
		return String.format("READ : [%s] [%s] [%s] [%s]"
			, paramKey   
			, paramValue     
			, fromFile 
			, toFile
		);
	}

	public String getParamKey()
	{
		return paramKey;
	}
	public void setParamKey(String paramKey)
	{
		this.paramKey = paramKey;
	}
	public String getParamValue()
	{
		return paramValue;
	}
	public void setParamValue(String paramValue)
	{
		this.paramValue = paramValue;
		
		String[] item = paramValue.split(":");
		this.fromFile = item[0].trim();
		this.toFile = item[1].trim();
	}
	public String getFromFile()
	{
		return fromFile;
	}
	public void setFromFile(String fromFile)
	{
		this.fromFile = fromFile;
	}
	public String getToFile()
	{
		return toFile;
	}
	public void setToFile(String toFile)
	{
		this.toFile = toFile;
	}
	
	///////////////////////////////////////////////////////////////////////////
	
	private static void test01()
	{
		if (Flag.TRUE) {
			FtpBean bean = new FtpBean();
			
			bean.setParamKey("anal.ftp.csv.file.A01.01");
			bean.setParamValue(" A01_JOBID_PARAM.csv                 :  JOBID_PARAM.csv");
			
			System.out.println(bean);
		}
	}
	
	public static void main(String[] args)
	{
		if (Flag.TRUE) test01();
	}
}
