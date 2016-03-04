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

package kiea.proj.sdc.anal.macro.sample.a11.mod.macro.RP_SYS_MOD_SUMM_WORKERID;

import java.util.ArrayList;
import java.util.List;

import kiea.proj.sdc.anal.macro.impl.bean.AbstractBean;

/**
 * @author KangSeok
 * @date 2014. 8. 8.
 * @file_name SO_SYS_MOD_WORKID_CSV_Bean.java
 *
 */
public class SO_SYS_MOD_WORKID_CSV_Bean extends AbstractBean
{
	/*
	"INSPSTEPTYPE:InspStepType",
	"INSPEQPID   :InspEqpId   ",
	"WORKERID    :WorkerId    ",
	"TOT         :Tot         ",
	"BAD         :Bad         ",
	"BAD_RATE    :BadRate     ",
	*/

	private String inspStepType;
	private String inspEqpId   ;
	private String workerId    ;
	private String tot         ;
	private String bad         ;
	private String badRate     ;

	public String toString()
	{
		return String.format("READ : [%s] [%s] [%s] [%s] [%s] [%s]"
			, inspStepType
			, inspEqpId   
			, workerId    
			, tot         
			, bad         
			, badRate     
		);
	}

	public static String[] getHeader()
	{
		List<String> list = new ArrayList<String>();
		list.add("INSPSTEPTYPE");
		list.add("INSPEQPID"   );
		list.add("WORKERID"    );
		list.add("TOT"         );
		list.add("BAD"         );
		list.add("BAD_RATE"    );

		return list.toArray(new String[list.size()]);
	}

	public String[] getStringArray()
	{
		List<String> list = new ArrayList<String>();
		list.add(inspStepType);
		list.add(inspEqpId   );
		list.add(workerId    );
		list.add(tot         );
		list.add(bad         );
		list.add(badRate     );

		return list.toArray(new String[list.size()]);
	}

	/**
	 * @return the inspStepType
	 */
	public String getInspStepType()
	{
		return inspStepType;
	}

	/**
	 * @return the inspEqpId
	 */
	public String getInspEqpId()
	{
		return inspEqpId;
	}

	/**
	 * @return the workerId
	 */
	public String getWorkerId()
	{
		return workerId;
	}

	/**
	 * @return the tot
	 */
	public String getTot()
	{
		return tot;
	}

	/**
	 * @return the bad
	 */
	public String getBad()
	{
		return bad;
	}

	/**
	 * @return the badRate
	 */
	public String getBadRate()
	{
		return badRate;
	}

	/**
	 * @param inspStepType the inspStepType to set
	 */
	public void setInspStepType(String inspStepType)
	{
		this.inspStepType = inspStepType;
	}

	/**
	 * @param inspEqpId the inspEqpId to set
	 */
	public void setInspEqpId(String inspEqpId)
	{
		this.inspEqpId = inspEqpId;
	}

	/**
	 * @param workerId the workerId to set
	 */
	public void setWorkerId(String workerId)
	{
		this.workerId = workerId;
	}

	/**
	 * @param tot the tot to set
	 */
	public void setTot(String tot)
	{
		this.tot = tot;
	}

	/**
	 * @param bad the bad to set
	 */
	public void setBad(String bad)
	{
		this.bad = bad;
	}

	/**
	 * @param badRate the badRate to set
	 */
	public void setBadRate(String badRate)
	{
		this.badRate = badRate;
	}
}
