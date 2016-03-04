package kiea.proj.sdc.anal.common;

import java.util.ArrayList;
import java.util.List;

public class ParameterJobIdBean
{
	/*
	"JOB_ID   :JobId   ",
	"SEQ      :Seq     ",
	"PARAM_NM :ParamNm ",
	"PARAM_VAL:ParamVal",
	*/

	private String jobId   ;
	private String seq     ;
	private String paramNm ;
	private String paramVal;

	public String toString()
	{
		return String.format("READ : [%s] [%s] [%s] [%s]"
			, jobId   
			, seq     
			, paramNm 
			, paramVal
		);
	}

	public static String[] getHeader()
	{
		List<String> list = new ArrayList<String>();
		list.add("JOB_ID"   );
		list.add("SEQ"      );
		list.add("PARAM_NM" );
		list.add("PARAM_VAL");

		return list.toArray(new String[list.size()]);
	}

	public String[] getStringArray()
	{
		List<String> list = new ArrayList<String>();
		list.add(jobId   );
		list.add(seq     );
		list.add(paramNm );
		list.add(paramVal);

		return list.toArray(new String[list.size()]);
	}

	/**
	 * @return the jobId
	 */
	public String getJobId()
	{
		return jobId;
	}

	/**
	 * @return the seq
	 */
	public String getSeq()
	{
		return seq;
	}

	/**
	 * @return the paramNm
	 */
	public String getParamNm()
	{
		return paramNm;
	}

	/**
	 * @return the paramVal
	 */
	public String getParamVal()
	{
		return paramVal;
	}

	/**
	 * @param jobId the jobId to set
	 */
	public void setJobId(String jobId)
	{
		this.jobId = jobId;
	}

	/**
	 * @param seq the seq to set
	 */
	public void setSeq(String seq)
	{
		this.seq = seq;
	}

	/**
	 * @param paramNm the paramNm to set
	 */
	public void setParamNm(String paramNm)
	{
		this.paramNm = paramNm;
	}

	/**
	 * @param paramVal the paramVal to set
	 */
	public void setParamVal(String paramVal)
	{
		this.paramVal = paramVal;
	}
}
