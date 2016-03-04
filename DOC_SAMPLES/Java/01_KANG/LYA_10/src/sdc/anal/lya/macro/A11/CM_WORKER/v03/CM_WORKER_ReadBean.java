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

package sdc.anal.lya.macro.A11.CM_WORKER.v03;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;

import kiea.proj.sdc.anal.macro.impl.bean.AbstractBean;

/**
 * @author KangSeok
 * @date 2014. 8. 8.
 * @file_name AIB_CM_WORKER_ReadBean.java
 *
 */
public class CM_WORKER_ReadBean extends AbstractBean
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

	///////////////////////////////////////////////////////////////////////////

	private byte[] bytes = null;
	private String title = null;
	private String str   = null;

	public void setBytes(byte[] bytes)
	{
		ByteBuffer buf = ByteBuffer.allocateDirect(10 * 1024);

		buf.clear();
		if (workerId      == null) buf.put((byte)','); else buf.put((byte)'"').put(workerId     .getBytes()).put((byte)'"').put((byte)',');
		if (workerName    == null) buf.put((byte)','); else buf.put((byte)'"').put(workerName   .getBytes()).put((byte)'"').put((byte)',');
		if (deptCd        == null) buf.put((byte)','); else buf.put((byte)'"').put(deptCd       .getBytes()).put((byte)'"').put((byte)',');
		if (deptName      == null) buf.put((byte)','); else buf.put((byte)'"').put(deptName     .getBytes()).put((byte)'"').put((byte)',');
		if (etlModifyDate == null) buf.put((byte)','); else buf.put((byte)'"').put(etlModifyDate.getBytes()).put((byte)'"').put((byte)',');
		if (positionCd    == null) buf.put((byte)','); else buf.put((byte)'"').put(positionCd   .getBytes()).put((byte)'"').put((byte)',');
		if (positionName  == null) buf.put((byte)','); else buf.put((byte)'"').put(positionName .getBytes()).put((byte)'"').put((byte)',');
		if (email         == null) buf.put((byte)','); else buf.put((byte)'"').put(email        .getBytes()).put((byte)'"').put((byte)',');
		if (status        == null) buf.put((byte)','); else buf.put((byte)'"').put(status       .getBytes()).put((byte)'"').put((byte)',');
		if (sourceSiteId  == null) buf.put((byte)','); else buf.put((byte)'"').put(sourceSiteId .getBytes()).put((byte)'"').put((byte)',');
		if (indept        == null) buf.put((byte)','); else buf.put((byte)'"').put(indept       .getBytes()).put((byte)'"').put((byte)',');
		if (jikchak       == null) buf.put((byte)','); else buf.put((byte)'"').put(jikchak      .getBytes()).put((byte)'"').put((byte)',');
		if (dutyNm        == null) buf.put((byte)','); else buf.put((byte)'"').put(dutyNm       .getBytes()).put((byte)'"').put((byte)',');
		if (locate        == null) buf.put((byte)','); else buf.put((byte)'"').put(locate       .getBytes()).put((byte)'"').put((byte)',');
		if (epId          == null) buf.put((byte)','); else buf.put((byte)'"').put(epId         .getBytes()).put((byte)'"').put((byte)',');
		if (uniqueId      == null) buf.put((byte)','); else buf.put((byte)'"').put(uniqueId     .getBytes()).put((byte)'"').put((byte)',');
		if (ename         == null) buf.put((byte)','); else buf.put((byte)'"').put(ename        .getBytes()).put((byte)'"').put((byte)'\n');

		buf.flip();
		this.bytes = new byte[buf.limit()];
		buf.get(this.bytes);
	}

	public byte[] getBytes()
	{
		if (this.bytes == null)
			setBytes(null);

		return this.bytes;
	}

	public void setTitle(String title)
	{
		ByteBuffer buf = ByteBuffer.allocateDirect(1024);

		buf.clear();
		buf.put((byte)'"').put("WORKERID"     .getBytes()).put((byte)'"').put((byte)',');
		buf.put((byte)'"').put("WORKERNAME"   .getBytes()).put((byte)'"').put((byte)',');
		buf.put((byte)'"').put("DEPTCD"       .getBytes()).put((byte)'"').put((byte)',');
		buf.put((byte)'"').put("DEPTNAME"     .getBytes()).put((byte)'"').put((byte)',');
		buf.put((byte)'"').put("ETLMODIFYDATE".getBytes()).put((byte)'"').put((byte)',');
		buf.put((byte)'"').put("POSITIONCD"   .getBytes()).put((byte)'"').put((byte)',');
		buf.put((byte)'"').put("POSITIONNAME" .getBytes()).put((byte)'"').put((byte)',');
		buf.put((byte)'"').put("EMAIL"        .getBytes()).put((byte)'"').put((byte)',');
		buf.put((byte)'"').put("STATUS"       .getBytes()).put((byte)'"').put((byte)',');
		buf.put((byte)'"').put("SOURCESITEID" .getBytes()).put((byte)'"').put((byte)',');
		buf.put((byte)'"').put("INDEPT"       .getBytes()).put((byte)'"').put((byte)',');
		buf.put((byte)'"').put("JIKCHAK"      .getBytes()).put((byte)'"').put((byte)',');
		buf.put((byte)'"').put("DUTY_NM"      .getBytes()).put((byte)'"').put((byte)',');
		buf.put((byte)'"').put("LOCATE"       .getBytes()).put((byte)'"').put((byte)',');
		buf.put((byte)'"').put("EP_ID"        .getBytes()).put((byte)'"').put((byte)',');
		buf.put((byte)'"').put("UNIQUE_ID"    .getBytes()).put((byte)'"').put((byte)',');
		buf.put((byte)'"').put("ENAME"        .getBytes()).put((byte)'"').put((byte)'\n');

		buf.flip();
		byte[] bytes = new byte[buf.limit()];
		buf.get(bytes);

		this.title = new String(bytes);
	}

	public String getTitle()
	{
		if (this.title == null)
			setTitle(null);

		return this.title;
	}

	public void setStr(String str)
	{
		this.str = new String(getBytes());
	}

	public String getStr()
	{
		if (this.str == null)
			setStr(null);

		return this.str;
	}


	///////////////////////////////////////////////////////////////////////////

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

	public String getWorkerId()
	{
		return workerId;
	}

	public void setWorkerId(String workerId)
	{
		this.workerId = workerId;
	}

	public String getWorkerName()
	{
		return workerName;
	}

	public void setWorkerName(String workerName)
	{
		this.workerName = workerName;
	}

	public String getDeptCd()
	{
		return deptCd;
	}

	public void setDeptCd(String deptCd)
	{
		this.deptCd = deptCd;
	}

	public String getDeptName()
	{
		return deptName;
	}

	public void setDeptName(String deptName)
	{
		this.deptName = deptName;
	}

	public String getEtlModifyDate()
	{
		return etlModifyDate;
	}

	public void setEtlModifyDate(String etlModifyDate)
	{
		this.etlModifyDate = etlModifyDate;
	}

	public String getPositionCd()
	{
		return positionCd;
	}

	public void setPositionCd(String positionCd)
	{
		this.positionCd = positionCd;
	}

	public String getPositionName()
	{
		return positionName;
	}

	public void setPositionName(String positionName)
	{
		this.positionName = positionName;
	}

	public String getEmail()
	{
		return email;
	}

	public void setEmail(String email)
	{
		this.email = email;
	}

	public String getStatus()
	{
		return status;
	}

	public void setStatus(String status)
	{
		this.status = status;
	}

	public String getSourceSiteId()
	{
		return sourceSiteId;
	}

	public void setSourceSiteId(String sourceSiteId)
	{
		this.sourceSiteId = sourceSiteId;
	}

	public String getIndept()
	{
		return indept;
	}

	public void setIndept(String indept)
	{
		this.indept = indept;
	}

	public String getJikchak()
	{
		return jikchak;
	}

	public void setJikchak(String jikchak)
	{
		this.jikchak = jikchak;
	}

	public String getDutyNm()
	{
		return dutyNm;
	}

	public void setDutyNm(String dutyNm)
	{
		this.dutyNm = dutyNm;
	}

	public String getLocate()
	{
		return locate;
	}

	public void setLocate(String locate)
	{
		this.locate = locate;
	}

	public String getEpId()
	{
		return epId;
	}

	public void setEpId(String epId)
	{
		this.epId = epId;
	}

	public String getUniqueId()
	{
		return uniqueId;
	}

	public void setUniqueId(String uniqueId)
	{
		this.uniqueId = uniqueId;
	}

	public String getEname()
	{
		return ename;
	}

	public void setEname(String ename)
	{
		this.ename = ename;
	}
}
