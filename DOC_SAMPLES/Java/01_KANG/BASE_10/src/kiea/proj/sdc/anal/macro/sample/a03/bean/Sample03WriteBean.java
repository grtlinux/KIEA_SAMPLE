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

package kiea.proj.sdc.anal.macro.sample.a03.bean;

import java.util.ArrayList;
import java.util.List;

import kiea.proj.sdc.anal.macro.impl.bean.AbstractBean;

/**
 * @author KangSeok
 * @date 2014. 8. 7.
 * @file_name Sample03WriteBean.java
 *
 */
public class Sample03WriteBean extends AbstractBean
{
	private String jobId        ;
	private String analEventType;
	private String lineId       ;
	private String inspectArea  ;
	private String inspectStep  ;
	private String fromDate     ;
	private String toDate       ;
	private String productGrp   ;
	private String productType  ;
	private String failCd       ;
	private String decisionCd   ;

	/**
	 * 
	 * toString
	 *
	 * @return
	 */
	@Override
	public String toString()
	{
		return String.format("READ : [%s] [%s] [%s] [%s] [%s] [%s] [%s] [%s] [%s] [%s] [%s]"
				, jobId
				, analEventType
				, lineId
				, inspectArea
				, inspectStep
				, fromDate
				, toDate
				, productGrp
				, productType
				, failCd
				, decisionCd
				);
	}

	/**
	 * 
	 * getHeader
	 *
	 * @return
	 */
	public static String[] getHeader()
	{
		List<String> list = new ArrayList<String>();
		list.add("JOB_ID"         );
		list.add("ANAL_EVENT_TYPE");
		list.add("LINE_ID"        );
		list.add("INSPECT_AREA"   );
		list.add("INSPECT_STEP"   );
		list.add("FROM_DATE"      );
		list.add("TO_DATE"        );
		list.add("PRODUCT_GRP"    );
		list.add("PRODUCT_TYPE"   );
		list.add("FAIL_CD"        );
		list.add("DECISION_CD"    );
		
		return list.toArray(new String[list.size()]);
	}
	
	/**
	 * 
	 * getStringArray
	 *
	 * @return
	 */
	@Override
	public String[] getStringArray()
	{
		List<String> list = new ArrayList<String>();
		list.add(jobId        );
		list.add(analEventType);
		list.add(lineId       );
		list.add(inspectArea  );
		list.add(inspectStep  );
		list.add(fromDate     );
		list.add(toDate       );
		list.add(productGrp   );
		list.add(productType  );
		list.add(failCd       );
		list.add(decisionCd   );

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
	 * @param jobId the jobId to set
	 */
	public void setJobId(String jobId)
	{
		this.jobId = jobId;
	}

	/**
	 * @return the analEventType
	 */
	public String getAnalEventType()
	{
		return analEventType;
	}

	/**
	 * @param analEventType the analEventType to set
	 */
	public void setAnalEventType(String analEventType)
	{
		this.analEventType = analEventType;
	}

	/**
	 * @return the lineId
	 */
	public String getLineId()
	{
		return lineId;
	}

	/**
	 * @param lineId the lineId to set
	 */
	public void setLineId(String lineId)
	{
		this.lineId = lineId;
	}

	/**
	 * @return the inspectArea
	 */
	public String getInspectArea()
	{
		return inspectArea;
	}

	/**
	 * @param inspectArea the inspectArea to set
	 */
	public void setInspectArea(String inspectArea)
	{
		this.inspectArea = inspectArea;
	}

	/**
	 * @return the inspectStep
	 */
	public String getInspectStep()
	{
		return inspectStep;
	}

	/**
	 * @param inspectStep the inspectStep to set
	 */
	public void setInspectStep(String inspectStep)
	{
		this.inspectStep = inspectStep;
	}

	/**
	 * @return the fromDate
	 */
	public String getFromDate()
	{
		return fromDate;
	}

	/**
	 * @param fromDate the fromDate to set
	 */
	public void setFromDate(String fromDate)
	{
		this.fromDate = fromDate;
	}

	/**
	 * @return the toDate
	 */
	public String getToDate()
	{
		return toDate;
	}

	/**
	 * @param toDate the toDate to set
	 */
	public void setToDate(String toDate)
	{
		this.toDate = toDate;
	}

	/**
	 * @return the productGrp
	 */
	public String getProductGrp()
	{
		return productGrp;
	}

	/**
	 * @param productGrp the productGrp to set
	 */
	public void setProductGrp(String productGrp)
	{
		this.productGrp = productGrp;
	}

	/**
	 * @return the productType
	 */
	public String getProductType()
	{
		return productType;
	}

	/**
	 * @param productType the productType to set
	 */
	public void setProductType(String productType)
	{
		this.productType = productType;
	}

	/**
	 * @return the failCd
	 */
	public String getFailCd()
	{
		return failCd;
	}

	/**
	 * @param failCd the failCd to set
	 */
	public void setFailCd(String failCd)
	{
		this.failCd = failCd;
	}

	/**
	 * @return the decisionCd
	 */
	public String getDecisionCd()
	{
		return decisionCd;
	}

	/**
	 * @param decisionCd the decisionCd to set
	 */
	public void setDecisionCd(String decisionCd)
	{
		this.decisionCd = decisionCd;
	}
}
