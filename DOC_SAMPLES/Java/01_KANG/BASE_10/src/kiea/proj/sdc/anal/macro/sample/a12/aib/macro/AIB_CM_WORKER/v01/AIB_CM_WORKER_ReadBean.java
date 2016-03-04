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

package kiea.proj.sdc.anal.macro.sample.a12.aib.macro.AIB_CM_WORKER.v01;

import java.util.ArrayList;
import java.util.List;

import kiea.proj.sdc.anal.macro.impl.bean.AbstractBean;

/**
 * @author KangSeok
 * @date 2014. 8. 8.
 * @file_name AIB_CM_WORKER_ReadBean.java
 *
 */
public class AIB_CM_WORKER_ReadBean extends AbstractBean
{
	/*
	"WORKERID     :WorkerId     ",
	"WORKERNAME   :WorkerName   ",
	"DEPTCD       :DeptCd       ",
	"DEPTNAME     :DeptName     ",
	"ETLMODIFYDATE:EtlModifyDate",
	"POSITIONCD   :PositionCd   ",
	"POSITIONNAME :PositionName ",
	"EMAIL        :Email        ",
	"STATUS       :Status       ",
	"SOURCESITEID :SourceSiteId ",
	"INDEPT       :Indept       ",
	"JIKCHAK      :Jikchak      ",
	"DUTY_NM      :DutyNm       ",
	"LOCATE       :Locate       ",
	"EP_ID        :EpId         ",
	"UNIQUE_ID    :UniqueId     ",
	"ENAME        :Ename        ",
	*/

	private String workerId     ;
	private String workerName   ;
	private String deptCd       ;
	private String deptName     ;
	private String etlModifyDate;
	private String positionCd   ;
	private String positionName ;
	private String email        ;
	private String status       ;
	private String sourceSiteId ;
	private String indept       ;
	private String jikchak      ;
	private String dutyNm       ;
	private String locate       ;
	private String epId         ;
	private String uniqueId     ;
	private String ename        ;

	public String toString()
	{
		return String.format("READ : [%s] [%s] [%s] [%s] [%s] [%s] [%s] [%s] [%s] [%s] [%s] [%s] [%s] [%s] [%s] [%s] [%s]"
			, workerId     
			, workerName   
			, deptCd       
			, deptName     
			, etlModifyDate
			, positionCd   
			, positionName 
			, email        
			, status       
			, sourceSiteId 
			, indept       
			, jikchak      
			, dutyNm       
			, locate       
			, epId         
			, uniqueId     
			, ename        
		);
	}

	public static String[] getHeader()
	{
		List<String> list = new ArrayList<String>();
		list.add("WORKERID"     );
		list.add("WORKERNAME"   );
		list.add("DEPTCD"       );
		list.add("DEPTNAME"     );
		list.add("ETLMODIFYDATE");
		list.add("POSITIONCD"   );
		list.add("POSITIONNAME" );
		list.add("EMAIL"        );
		list.add("STATUS"       );
		list.add("SOURCESITEID" );
		list.add("INDEPT"       );
		list.add("JIKCHAK"      );
		list.add("DUTY_NM"      );
		list.add("LOCATE"       );
		list.add("EP_ID"        );
		list.add("UNIQUE_ID"    );
		list.add("ENAME"        );

		return list.toArray(new String[list.size()]);
	}

	public String[] getStringArray()
	{
		List<String> list = new ArrayList<String>();
		list.add(workerId     );
		list.add(workerName   );
		list.add(deptCd       );
		list.add(deptName     );
		list.add(etlModifyDate);
		list.add(positionCd   );
		list.add(positionName );
		list.add(email        );
		list.add(status       );
		list.add(sourceSiteId );
		list.add(indept       );
		list.add(jikchak      );
		list.add(dutyNm       );
		list.add(locate       );
		list.add(epId         );
		list.add(uniqueId     );
		list.add(ename        );

		return list.toArray(new String[list.size()]);
	}

	/**
	 * @return the workerId
	 */
	public String getWorkerId()
	{
		return workerId;
	}

	/**
	 * @return the workerName
	 */
	public String getWorkerName()
	{
		return workerName;
	}

	/**
	 * @return the deptCd
	 */
	public String getDeptCd()
	{
		return deptCd;
	}

	/**
	 * @return the deptName
	 */
	public String getDeptName()
	{
		return deptName;
	}

	/**
	 * @return the etlModifyDate
	 */
	public String getEtlModifyDate()
	{
		return etlModifyDate;
	}

	/**
	 * @return the positionCd
	 */
	public String getPositionCd()
	{
		return positionCd;
	}

	/**
	 * @return the positionName
	 */
	public String getPositionName()
	{
		return positionName;
	}

	/**
	 * @return the email
	 */
	public String getEmail()
	{
		return email;
	}

	/**
	 * @return the status
	 */
	public String getStatus()
	{
		return status;
	}

	/**
	 * @return the sourceSiteId
	 */
	public String getSourceSiteId()
	{
		return sourceSiteId;
	}

	/**
	 * @return the indept
	 */
	public String getIndept()
	{
		return indept;
	}

	/**
	 * @return the jikchak
	 */
	public String getJikchak()
	{
		return jikchak;
	}

	/**
	 * @return the dutyNm
	 */
	public String getDutyNm()
	{
		return dutyNm;
	}

	/**
	 * @return the locate
	 */
	public String getLocate()
	{
		return locate;
	}

	/**
	 * @return the epId
	 */
	public String getEpId()
	{
		return epId;
	}

	/**
	 * @return the uniqueId
	 */
	public String getUniqueId()
	{
		return uniqueId;
	}

	/**
	 * @return the ename
	 */
	public String getEname()
	{
		return ename;
	}

	/**
	 * @param workerId the workerId to set
	 */
	public void setWorkerId(String workerId)
	{
		this.workerId = workerId;
	}

	/**
	 * @param workerName the workerName to set
	 */
	public void setWorkerName(String workerName)
	{
		this.workerName = workerName;
	}

	/**
	 * @param deptCd the deptCd to set
	 */
	public void setDeptCd(String deptCd)
	{
		this.deptCd = deptCd;
	}

	/**
	 * @param deptName the deptName to set
	 */
	public void setDeptName(String deptName)
	{
		this.deptName = deptName;
	}

	/**
	 * @param etlModifyDate the etlModifyDate to set
	 */
	public void setEtlModifyDate(String etlModifyDate)
	{
		this.etlModifyDate = etlModifyDate;
	}

	/**
	 * @param positionCd the positionCd to set
	 */
	public void setPositionCd(String positionCd)
	{
		this.positionCd = positionCd;
	}

	/**
	 * @param positionName the positionName to set
	 */
	public void setPositionName(String positionName)
	{
		this.positionName = positionName;
	}

	/**
	 * @param email the email to set
	 */
	public void setEmail(String email)
	{
		this.email = email;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(String status)
	{
		this.status = status;
	}

	/**
	 * @param sourceSiteId the sourceSiteId to set
	 */
	public void setSourceSiteId(String sourceSiteId)
	{
		this.sourceSiteId = sourceSiteId;
	}

	/**
	 * @param indept the indept to set
	 */
	public void setIndept(String indept)
	{
		this.indept = indept;
	}

	/**
	 * @param jikchak the jikchak to set
	 */
	public void setJikchak(String jikchak)
	{
		this.jikchak = jikchak;
	}

	/**
	 * @param dutyNm the dutyNm to set
	 */
	public void setDutyNm(String dutyNm)
	{
		this.dutyNm = dutyNm;
	}

	/**
	 * @param locate the locate to set
	 */
	public void setLocate(String locate)
	{
		this.locate = locate;
	}

	/**
	 * @param epId the epId to set
	 */
	public void setEpId(String epId)
	{
		this.epId = epId;
	}

	/**
	 * @param uniqueId the uniqueId to set
	 */
	public void setUniqueId(String uniqueId)
	{
		this.uniqueId = uniqueId;
	}

	/**
	 * @param ename the ename to set
	 */
	public void setEname(String ename)
	{
		this.ename = ename;
	}
}
