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

package sdc.anal.mura.macro.A25.RP_MUR_ERD_FDC_SUM01.v04;

import java.util.ArrayList;
import java.util.List;

import kiea.proj.sdc.anal.macro.impl.bean.AbstractBean;

/**
 * @author KangSeok
 * @date 2014. 8. 8.
 * @file_name RP_TOTWIP_SUMM_Bean.java
 *
 */
public class RP_MUR_WIP_SUMM1_Bean extends AbstractBean
{
	/*
	"DIV_CODE               :DivCode         ",
	"CLUSTER_ID             :ClusterId       ",
	"RANK                   :Rank            ",
	"STEP_ID                :StepId          ",
	"STEP_DESC              :StepDesc        ",
	"SUSPICIOUS_EQP         :SuspiciousEqp   ",
	"Weighted B-G           :WeightedBG      ",
	"B Ratio - G Ratio      :BGRatio         ",
	"Bad Ratio              :BadRatio        ",
	"Good Ratio             :GoodRatio       ",
	"Bad 수                 :BadCnt          ",
	"Good 수                :GoodCnt         ",
	"TOT_BAD_CNT            :TotBadCnt       ",
	"TOT_GOOD_CNT           :TotGoodCnt      ",
	*/

	private String divCode         ;
	private String clusterId       ;
	private String rank            ;
	private String stepId          ;
	private String stepDesc        ;
	private String suspiciousEqp   ;
	private String weightedBG      ;
	private String BGRatio         ;
	private String badRatio        ;
	private String goodRatio       ;
	private String badCnt          ;
	private String goodCnt         ;
	private String totBadCnt       ;
	private String totGoodCnt      ;

	public void addBadCnt()
	{
		this.badCnt = "" + (Long.parseLong(this.badCnt) + 1);
	}
	
	public void addGoodCnt()
	{
		this.goodCnt = "" + (Long.parseLong(this.goodCnt) + 1);
	}
	
	public void addTotBadCnt()
	{
		this.totBadCnt = "" + (Long.parseLong(this.totBadCnt) + 1);
	}
	
	public void addTotGoodCnt()
	{
		this.totGoodCnt = "" + (Long.parseLong(this.totGoodCnt) + 1);
	}
	
	public void addTotBadCnt(String num)
	{
		this.totBadCnt = "" + (Long.parseLong(this.totBadCnt) + Long.parseLong(num));
	}
	
	public void addTotGoodCnt(String num)
	{
		this.totGoodCnt = "" + (Long.parseLong(this.totGoodCnt) + Long.parseLong(num));
	}
	
	public String toString()
	{
		return String.format("READ : [%s] [%s] [%s] [%s] [%s] [%s] [%s] [%s] [%s] [%s] [%s] [%s] [%s] [%s]"
			, divCode         
			, clusterId       
			, rank            
			, stepId          
			, stepDesc        
			, suspiciousEqp   
			, weightedBG      
			, BGRatio         
			, badRatio        
			, goodRatio       
			, badCnt          
			, goodCnt         
			, totBadCnt       
			, totGoodCnt      
		);
	}

	public static String[] getHeader()
	{
		List<String> list = new ArrayList<String>();
		list.add("DIV_CODE"               );
		list.add("CLUSTER_ID"             );
		list.add("RANK"                   );
		list.add("STEP_ID"                );
		list.add("STEP_DESC"              );
		list.add("SUSPICIOUS_EQP"         );
		list.add("Weighted B-G"           );
		list.add("B Ratio - G Ratio"      );
		list.add("Bad Ratio"              );
		list.add("Good Ratio"             );
		list.add("Bad 수"                 );
		list.add("Good 수"                );
		list.add("TOT_BAD_CNT"            );
		list.add("TOT_GOOD_CNT"           );

		return list.toArray(new String[list.size()]);
	}

	public String[] getStringArray()
	{
		List<String> list = new ArrayList<String>();
		list.add(divCode         );
		list.add(clusterId       );
		list.add(rank            );
		list.add(stepId          );
		list.add(stepDesc        );
		list.add(suspiciousEqp   );
		list.add(weightedBG      );
		list.add(BGRatio         );
		list.add(badRatio        );
		list.add(goodRatio       );
		list.add(badCnt          );
		list.add(goodCnt         );
		list.add(totBadCnt       );
		list.add(totGoodCnt      );

		return list.toArray(new String[list.size()]);
	}

	public String getDivCode()
	{
		return divCode;
	}

	public void setDivCode(String divCode)
	{
		this.divCode = divCode;
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

	public String getStepId()
	{
		return stepId;
	}

	public void setStepId(String stepId)
	{
		this.stepId = stepId;
	}

	public String getStepDesc()
	{
		return stepDesc;
	}

	public void setStepDesc(String stepDesc)
	{
		this.stepDesc = stepDesc;
	}

	public String getSuspiciousEqp()
	{
		return suspiciousEqp;
	}

	public void setSuspiciousEqp(String suspiciousEqp)
	{
		this.suspiciousEqp = suspiciousEqp;
	}

	public String getWeightedBG()
	{
		return weightedBG;
	}

	public void setWeightedBG(String weightedBG)
	{
		this.weightedBG = weightedBG;
	}

	public String getBGRatio()
	{
		return BGRatio;
	}

	public void setBGRatio(String bGRatio)
	{
		BGRatio = bGRatio;
	}

	public String getBadRatio()
	{
		return badRatio;
	}

	public void setBadRatio(String badRatio)
	{
		this.badRatio = badRatio;
	}

	public String getGoodRatio()
	{
		return goodRatio;
	}

	public void setGoodRatio(String goodRatio)
	{
		this.goodRatio = goodRatio;
	}

	public String getBadCnt()
	{
		return badCnt;
	}

	public void setBadCnt(String badCnt)
	{
		this.badCnt = badCnt;
	}

	public String getGoodCnt()
	{
		return goodCnt;
	}

	public void setGoodCnt(String goodCnt)
	{
		this.goodCnt = goodCnt;
	}

	public String getTotBadCnt()
	{
		return totBadCnt;
	}

	public void setTotBadCnt(String totBadCnt)
	{
		this.totBadCnt = totBadCnt;
	}

	public String getTotGoodCnt()
	{
		return totGoodCnt;
	}

	public void setTotGoodCnt(String totGoodCnt)
	{
		this.totGoodCnt = totGoodCnt;
	}
}
