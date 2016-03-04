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

package sdc.anal.lya.macro.A25.RP_SYS_DEFECT_SUMM.v01;

import java.util.ArrayList;
import java.util.List;

import kiea.proj.sdc.anal.macro.impl.bean.AbstractBean;

/**
 * @author KangSeok
 * @date 2014. 8. 8.
 * @file_name RP_TOTWIP_SUMM_Bean.java
 *
 */
public class RP_SYS_DEFECT_SUMM_Bean extends AbstractBean
{
	/*
	"CLUSTER_ID   :ClusterId  ",
	"RANK         :Rank       ",
	"SUB_AREA_CODE:SubAreaCode",
	"STEP_ID      :StepId     ",
	"MATCH_CNT    :MatchCnt   ",
	"DEFECT_CNT   :DefectCnt  ",
	"MATCH_RATIO  :MatchRatio ",
	*/

	private String clusterId  ;
	private String rank       ;
	private String subAreaCode;
	private String stepId     ;
	private String matchCnt   ;
	private String defectCnt  ;
	private String matchRatio ;

	public void addMatchCnt()
	{
		this.matchCnt = "" + (Integer.parseInt(this.matchCnt) + 1);
	}
	
	public String toString()
	{
		return String.format("READ : [%s] [%s] [%s] [%s] [%s] [%s] [%s]"
			, clusterId  
			, rank       
			, subAreaCode
			, stepId     
			, matchCnt   
			, defectCnt  
			, matchRatio 
		);
	}

	public static String[] getHeader()
	{
		List<String> list = new ArrayList<String>();
		list.add("CLUSTER_ID"   );
		list.add("RANK"         );
		list.add("SUB_AREA_CODE");
		list.add("STEP_ID"      );
		list.add("MATCH_CNT"    );
		list.add("DEFECT_CNT"   );
		list.add("MATCH_RATIO"  );

		return list.toArray(new String[list.size()]);
	}

	public String[] getStringArray()
	{
		List<String> list = new ArrayList<String>();
		list.add(clusterId  );
		list.add(rank       );
		list.add(subAreaCode);
		list.add(stepId     );
		list.add(matchCnt   );
		list.add(defectCnt  );
		list.add(matchRatio );

		return list.toArray(new String[list.size()]);
	}

	public String getClusterId()
	{
		return clusterId;
	}

	public void setClusterId(String clusterId)
	{
		this.clusterId = clusterId;
	}

	public String getRank()
	{
		return rank;
	}

	public void setRank(String rank)
	{
		this.rank = rank;
	}

	public String getSubAreaCode()
	{
		return subAreaCode;
	}

	public void setSubAreaCode(String subAreaCode)
	{
		this.subAreaCode = subAreaCode;
	}

	public String getStepId()
	{
		return stepId;
	}

	public void setStepId(String stepId)
	{
		this.stepId = stepId;
	}

	public String getMatchCnt()
	{
		return matchCnt;
	}

	public void setMatchCnt(String matchCnt)
	{
		this.matchCnt = matchCnt;
	}

	public String getDefectCnt()
	{
		return defectCnt;
	}

	public void setDefectCnt(String defectCnt)
	{
		this.defectCnt = defectCnt;
	}

	public String getMatchRatio()
	{
		return matchRatio;
	}

	public void setMatchRatio(String matchRatio)
	{
		this.matchRatio = matchRatio;
	}
}
