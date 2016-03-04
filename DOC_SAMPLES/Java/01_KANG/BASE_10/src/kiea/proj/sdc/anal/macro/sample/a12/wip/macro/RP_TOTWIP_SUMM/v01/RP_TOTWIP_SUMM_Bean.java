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

package kiea.proj.sdc.anal.macro.sample.a12.wip.macro.RP_TOTWIP_SUMM.v01;

import java.util.ArrayList;
import java.util.List;

import kiea.proj.sdc.anal.macro.impl.bean.AbstractBean;

/**
 * @author KangSeok
 * @date 2014. 8. 8.
 * @file_name RP_TOTWIP_SUMM_Bean.java
 *
 */
public class RP_TOTWIP_SUMM_Bean extends AbstractBean
{
	/*
	"RANK        :Rank      ",
	"STEP_ID     :StepId    ",
	"EQP_ID      :EqpId     ",
	"B_G_RATIO   :BGRatio   ",
	"BAD_RATIO   :BadRatio  ",
	"GOOD_RATIO  :GoodRatio ",
	"BAD_CNT     :BadCnt    ",
	"GOOD_CNT    :GoodCnt   ",
	"TOT_BAD_CNT :TotBadCnt ",
	"TOT_GOOD_CNT:TotGoodCnt",
	"CLUSTER_ID  :ClusterId ",
	*/

	private String rank      ;
	private String stepId    ;
	private String eqpId     ;
	private String BGRatio   ;
	private String badRatio  ;
	private String goodRatio ;
	private String badCnt    ;
	private String goodCnt   ;
	private String totBadCnt ;
	private String totGoodCnt;
	private String clusterId ;

	public String toString()
	{
		return String.format("READ : [%s] [%s] [%s] [%s] [%s] [%s] [%s] [%s] [%s] [%s] [%s]"
			, rank      
			, stepId    
			, eqpId     
			, BGRatio   
			, badRatio  
			, goodRatio 
			, badCnt    
			, goodCnt   
			, totBadCnt 
			, totGoodCnt
			, clusterId 
		);
	}

	public static String[] getHeader()
	{
		List<String> list = new ArrayList<String>();
		list.add("RANK"        );
		list.add("STEP_ID"     );
		list.add("EQP_ID"      );
		list.add("B_G_RATIO"   );
		list.add("BAD_RATIO"   );
		list.add("GOOD_RATIO"  );
		list.add("BAD_CNT"     );
		list.add("GOOD_CNT"    );
		list.add("TOT_BAD_CNT" );
		list.add("TOT_GOOD_CNT");
		list.add("CLUSTER_ID"  );

		return list.toArray(new String[list.size()]);
	}

	public String[] getStringArray()
	{
		List<String> list = new ArrayList<String>();
		list.add(rank      );
		list.add(stepId    );
		list.add(eqpId     );
		list.add(BGRatio   );
		list.add(badRatio  );
		list.add(goodRatio );
		list.add(badCnt    );
		list.add(goodCnt   );
		list.add(totBadCnt );
		list.add(totGoodCnt);
		list.add(clusterId );

		return list.toArray(new String[list.size()]);
	}

	public void addBadCnt()
	{
		setBadCnt("" + (Integer.parseInt(getBadCnt()) + 1));
	}
	
	public void addGoodCnt()
	{
		setGoodCnt("" + (Integer.parseInt(getGoodCnt()) + 1));
	}
	
	/**
	 * @return the rank
	 */
	public String getRank()
	{
		return rank;
	}

	/**
	 * @return the stepId
	 */
	public String getStepId()
	{
		return stepId;
	}

	/**
	 * @return the eqpId
	 */
	public String getEqpId()
	{
		return eqpId;
	}

	/**
	 * @return the bGRatio
	 */
	public String getBGRatio()
	{
		return BGRatio;
	}

	/**
	 * @return the badRatio
	 */
	public String getBadRatio()
	{
		return badRatio;
	}

	/**
	 * @return the goodRatio
	 */
	public String getGoodRatio()
	{
		return goodRatio;
	}

	/**
	 * @return the badCnt
	 */
	public String getBadCnt()
	{
		return badCnt;
	}

	/**
	 * @return the goodCnt
	 */
	public String getGoodCnt()
	{
		return goodCnt;
	}

	/**
	 * @return the totBadCnt
	 */
	public String getTotBadCnt()
	{
		return totBadCnt;
	}

	/**
	 * @return the totGoodCnt
	 */
	public String getTotGoodCnt()
	{
		return totGoodCnt;
	}

	/**
	 * @return the clusterId
	 */
	public String getClusterId()
	{
		return clusterId;
	}

	/**
	 * @param rank the rank to set
	 */
	public void setRank(String rank)
	{
		this.rank = rank;
	}

	/**
	 * @param stepId the stepId to set
	 */
	public void setStepId(String stepId)
	{
		this.stepId = stepId;
	}

	/**
	 * @param eqpId the eqpId to set
	 */
	public void setEqpId(String eqpId)
	{
		this.eqpId = eqpId;
	}

	/**
	 * @param bGRatio the bGRatio to set
	 */
	public void setBGRatio(String bGRatio)
	{
		BGRatio = bGRatio;
	}

	/**
	 * @param badRatio the badRatio to set
	 */
	public void setBadRatio(String badRatio)
	{
		this.badRatio = badRatio;
	}

	/**
	 * @param goodRatio the goodRatio to set
	 */
	public void setGoodRatio(String goodRatio)
	{
		this.goodRatio = goodRatio;
	}

	/**
	 * @param badCnt the badCnt to set
	 */
	public void setBadCnt(String badCnt)
	{
		this.badCnt = badCnt;
	}

	/**
	 * @param goodCnt the goodCnt to set
	 */
	public void setGoodCnt(String goodCnt)
	{
		this.goodCnt = goodCnt;
	}

	/**
	 * @param totBadCnt the totBadCnt to set
	 */
	public void setTotBadCnt(String totBadCnt)
	{
		this.totBadCnt = totBadCnt;
	}

	/**
	 * @param totGoodCnt the totGoodCnt to set
	 */
	public void setTotGoodCnt(String totGoodCnt)
	{
		this.totGoodCnt = totGoodCnt;
	}

	/**
	 * @param clusterId the clusterId to set
	 */
	public void setClusterId(String clusterId)
	{
		this.clusterId = clusterId;
	}
}
