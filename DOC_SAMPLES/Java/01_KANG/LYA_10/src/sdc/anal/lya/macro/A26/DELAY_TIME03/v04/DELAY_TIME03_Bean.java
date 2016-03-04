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

package sdc.anal.lya.macro.A26.DELAY_TIME03.v04;

import java.util.ArrayList;
import java.util.List;

import kiea.proj.sdc.anal.macro.impl.bean.AbstractBean;

/**
 * @author KangSeok
 * @date 2014. 8. 8.
 * @file_name RP_TOTWIP_SUMM_Bean.java
 *
 */
public class DELAY_TIME03_Bean extends AbstractBean
{
	/*
	"RANK      :Rank     ",
	"CLUSTER_ID:ClusterId",
	"STEP_NAME :StepName ",
	"STEP_DESC :StepDesc ",
	"BAD_CNT   :BadCnt   ",
	"GOOD_CNT  :GoodCnt  ",
	"BAD_SUM   :BadSum   ",
	"GOOD_SUM  :GoodSum  ",
	"BAD_AVG   :BadAvg   ",
	"GOOD_AVG  :GoodAvg  ",
	"BG_AVG    :BGAvg    ",
	*/

	public void incBadCnt()
	{
		badCnt = "" + (Integer.parseInt(badCnt) + 1);
	}
	
	public void incGoodCnt()
	{
		goodCnt = "" + (Integer.parseInt(goodCnt) + 1);
	}
	
	public void addBadSum(String val)
	{
		badSum = "" + (Double.parseDouble(badSum) + Double.parseDouble(val));
	}
	
	public void addGoodSum(String val)
	{
		goodSum = "" + (Double.parseDouble(goodSum) + Double.parseDouble(val));
	}
	
	///////////////////////////////////////////////////////////////////////////
	
	private String rank     ;
	private String clusterId;
	private String stepName ;
	private String stepDesc ;
	private String badCnt   ;
	private String goodCnt  ;
	private String badSum   ;
	private String goodSum  ;
	private String badAvg   ;
	private String goodAvg  ;
	private String BGAvg    ;

	public String toString()
	{
		return String.format("READ : [%s] [%s] [%s] [%s] [%s] [%s] [%s] [%s] [%s] [%s] [%s]"
			, rank     
			, clusterId
			, stepName 
			, stepDesc 
			, badCnt   
			, goodCnt  
			, badSum   
			, goodSum  
			, badAvg   
			, goodAvg  
			, BGAvg    
		);
	}

	public static String[] getHeader()
	{
		List<String> list = new ArrayList<String>();
		list.add("RANK"      );
		list.add("CLUSTER_ID");
		list.add("STEP_NAME" );
		list.add("STEP_DESC" );
		list.add("BAD_CNT"   );
		list.add("GOOD_CNT"  );
		list.add("BAD_SUM"   );
		list.add("GOOD_SUM"  );
		list.add("BAD_AVG"   );
		list.add("GOOD_AVG"  );
		list.add("BG_AVG"    );

		return list.toArray(new String[list.size()]);
	}

	public String[] getStringArray()
	{
		List<String> list = new ArrayList<String>();
		list.add(rank     );
		list.add(clusterId);
		list.add(stepName );
		list.add(stepDesc );
		list.add(badCnt   );
		list.add(goodCnt  );
		list.add(badSum   );
		list.add(goodSum  );
		list.add(badAvg   );
		list.add(goodAvg  );
		list.add(BGAvg    );

		return list.toArray(new String[list.size()]);
	}

	public String getRank()
	{
		return rank;
	}

	public void setRank(String rank)
	{
		this.rank = rank;
	}

	public String getClusterId()
	{
		return clusterId;
	}

	public void setClusterId(String clusterId)
	{
		this.clusterId = clusterId;
	}

	public String getStepName()
	{
		return stepName;
	}

	public void setStepName(String stepName)
	{
		this.stepName = stepName;
	}

	public String getStepDesc()
	{
		return stepDesc;
	}

	public void setStepDesc(String stepDesc)
	{
		this.stepDesc = stepDesc;
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

	public String getBadSum()
	{
		return badSum;
	}

	public void setBadSum(String badSum)
	{
		this.badSum = badSum;
	}

	public String getGoodSum()
	{
		return goodSum;
	}

	public void setGoodSum(String goodSum)
	{
		this.goodSum = goodSum;
	}

	public String getBadAvg()
	{
		return badAvg;
	}

	public void setBadAvg(String badAvg)
	{
		this.badAvg = badAvg;
	}

	public String getGoodAvg()
	{
		return goodAvg;
	}

	public void setGoodAvg(String goodAvg)
	{
		this.goodAvg = goodAvg;
	}

	public String getBGAvg()
	{
		return BGAvg;
	}

	public void setBGAvg(String bGAvg)
	{
		BGAvg = bGAvg;
	}
}
