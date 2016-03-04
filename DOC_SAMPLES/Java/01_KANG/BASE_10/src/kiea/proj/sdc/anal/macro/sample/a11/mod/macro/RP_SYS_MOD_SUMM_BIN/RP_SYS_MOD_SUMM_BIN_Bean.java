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

package kiea.proj.sdc.anal.macro.sample.a11.mod.macro.RP_SYS_MOD_SUMM_BIN;

import java.util.ArrayList;
import java.util.List;

import kiea.proj.sdc.anal.macro.impl.bean.AbstractBean;

/**
 * @author KangSeok
 * @date 2014. 8. 8.
 * @file_name RP_SYS_MOD_SUMM_BIN_Bean.java
 *
 */
public class RP_SYS_MOD_SUMM_BIN_Bean extends AbstractBean
{
	/*
	"CODE      :Code      ",
	"CLUSTER_ID:ClusterId ",
	"RANK      :Rank      ",
	"GROUPID   :GroupId   ",
	"BIN_GRADE :BinGrade  ",
	"PERCENTAGE:Percentage",
	*/

	private String code      ;
	private String clusterId ;
	private String rank      ;
	private String groupId   ;
	private String binGrade  ;
	private String percentage;

	public String toString()
	{
		return String.format("READ : [%s] [%s] [%s] [%s] [%s] [%s]"
			, code      
			, clusterId 
			, rank      
			, groupId   
			, binGrade  
			, percentage
		);
	}

	public static String[] getHeader()
	{
		List<String> list = new ArrayList<String>();
		list.add("CODE"      );
		list.add("CLUSTER_ID");
		list.add("RANK"      );
		list.add("GROUPID"   );
		list.add("BIN_GRADE" );
		list.add("PERCENTAGE");

		return list.toArray(new String[list.size()]);
	}

	public String[] getStringArray()
	{
		List<String> list = new ArrayList<String>();
		list.add(code      );
		list.add(clusterId );
		list.add(rank      );
		list.add(groupId   );
		list.add(binGrade  );
		list.add(percentage);

		return list.toArray(new String[list.size()]);
	}

	/**
	 * @return the code
	 */
	public String getCode()
	{
		return code;
	}

	/**
	 * @return the clusterId
	 */
	public String getClusterId()
	{
		return clusterId;
	}

	/**
	 * @return the rank
	 */
	public String getRank()
	{
		return rank;
	}

	/**
	 * @return the groupId
	 */
	public String getGroupId()
	{
		return groupId;
	}

	/**
	 * @return the binGrade
	 */
	public String getBinGrade()
	{
		return binGrade;
	}

	/**
	 * @return the percentage
	 */
	public String getPercentage()
	{
		return percentage;
	}

	/**
	 * @param code the code to set
	 */
	public void setCode(String code)
	{
		this.code = code;
	}

	/**
	 * @param clusterId the clusterId to set
	 */
	public void setClusterId(String clusterId)
	{
		this.clusterId = clusterId;
	}

	/**
	 * @param rank the rank to set
	 */
	public void setRank(String rank)
	{
		this.rank = rank;
	}

	/**
	 * @param groupId the groupId to set
	 */
	public void setGroupId(String groupId)
	{
		this.groupId = groupId;
	}

	/**
	 * @param binGrade the binGrade to set
	 */
	public void setBinGrade(String binGrade)
	{
		this.binGrade = binGrade;
	}

	/**
	 * @param percentage the percentage to set
	 */
	public void setPercentage(String percentage)
	{
		this.percentage = percentage;
	}
}
