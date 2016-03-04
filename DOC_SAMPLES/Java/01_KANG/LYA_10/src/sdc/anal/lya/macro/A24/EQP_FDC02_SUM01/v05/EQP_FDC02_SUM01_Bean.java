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

package sdc.anal.lya.macro.A24.EQP_FDC02_SUM01.v05;

import java.util.ArrayList;
import java.util.List;

import kiea.proj.sdc.anal.macro.impl.bean.AbstractBean;

/**
 * @author KangSeok
 * @date 2014. 8. 8.
 * @file_name MURA_SPC_ReadBean.java
 *
 */
public class EQP_FDC02_SUM01_Bean extends AbstractBean
{
	/*
	"CLUSTER_ID :ClusterId  ",
	"PART       :Part       ",
	"EQPID      :EqpId      ",
	"SENSOR_NAME:SensorName ",
	"VMIN       :VMin       ",
	"VMAX       :VMax       ",
	"BAD_SUM    :BadSum     ",
	"BAD_CNT    :BadCnt     ",
	"GOOD_SUM   :GoodSum    ",
	"GOOD_CNT   :GoodCnt    ",
	"BAD_AVG    :BadAvg     ",
	"GOOD_AVG   :GoodAvg    ",
	"FDC_BG     :FdcBG      ",
	"RANK       :Rank       ",
	*/

	private String clusterId  ;
	private String part       ;
	private String eqpId      ;
	private String sensorName ;
	private String VMin       ;
	private String VMax       ;
	private String badSum     ;
	private String badCnt     ;
	private String goodSum    ;
	private String goodCnt    ;
	private String badAvg     ;
	private String goodAvg    ;
	private String fdcBG      ;
	private String rank       ;

	///////////////////////////////////////////////////////////////////////////
	
	private double dVMin = 999999999;
	private double dVMax = 0;
	private double dBadSum = 0;
	private double dBadCnt = 0;
	private double dGoodSum = 0;
	private double dGoodCnt = 0;
	private double dBadAvg = 0;
	private double dGoodAvg = 0;
	private double dFdcBG = 0;
	
	public void setDVMin(String val)
	{
		double d = Double.parseDouble(val);
		
		if (d < dVMin)
			dVMin = d;
	}
	
	public void setDVMax(String val)
	{
		double d = Double.parseDouble(val);
		
		if (d > dVMax)
			dVMax = d;
	}
	
	public void setDBadSum(String val)
	{
		dBadSum += Double.parseDouble(val);
	}
	
	public void setDBadCnt()
	{
		dBadCnt += 1;
	}
	
	public void setDGoodSum(String val)
	{
		dGoodSum += Double.parseDouble(val);
	}
	
	public void setDGoodCnt()
	{
		dGoodCnt += 1;
	}
	
	public void setDBadAvg(double dbl)
	{
		this.dBadAvg = dbl;
	}
	
	public void setDGoodAvg(double dbl)
	{
		this.dGoodAvg = dbl;
	}
	
	public void setDFdcBG(double dbl)
	{
		this.dFdcBG = dbl;
	}
	
	public double getDVMin()
	{
		return this.dVMin;
	}
	
	public double getDVMax()
	{
		return this.dVMax;
	}
	
	public double getDBadSum()
	{
		return this.dBadSum;
	}
	
	public double getDBadCnt()
	{
		return this.dBadCnt;
	}
	
	public double getDGoodSum()
	{
		return this.dGoodSum;
	}
	
	public double getDGoodCnt()
	{
		return this.dGoodCnt;
	}
	
	public double getDBadAvg()
	{
		return this.dBadAvg;
	}
	
	public double getDGoodAvg()
	{
		return this.dGoodAvg;
	}
	
	public double getDFdcBG()
	{
		return this.dFdcBG;
	}
	
	///////////////////////////////////////////////////////////////////////////

	public String toString()
	{
		return String.format("READ : [%s] [%s] [%s] [%s] [%s] [%s] [%s] [%s] [%s] [%s] [%s] [%s] [%s] [%s]"
			, clusterId  
			, part       
			, eqpId      
			, sensorName 
			, VMin       
			, VMax       
			, badSum     
			, badCnt     
			, goodSum    
			, goodCnt    
			, badAvg     
			, goodAvg    
			, fdcBG      
			, rank       
		);
	}

	public static String[] getHeader()
	{
		List<String> list = new ArrayList<String>();
		list.add("CLUSTER_ID" );
		list.add("PART"       );
		list.add("EQPID"      );
		list.add("SENSOR_NAME");
		list.add("VMIN"       );
		list.add("VMAX"       );
		list.add("BAD_SUM"    );
		list.add("BAD_CNT"    );
		list.add("GOOD_SUM"   );
		list.add("GOOD_CNT"   );
		list.add("BAD_AVG"    );
		list.add("GOOD_AVG"   );
		list.add("FDC_BG"     );
		list.add("RANK"       );

		return list.toArray(new String[list.size()]);
	}

	public String[] getStringArray()
	{
		List<String> list = new ArrayList<String>();
		list.add(clusterId  );
		list.add(part       );
		list.add(eqpId      );
		list.add(sensorName );
		list.add(VMin       );
		list.add(VMax       );
		list.add(badSum     );
		list.add(badCnt     );
		list.add(goodSum    );
		list.add(goodCnt    );
		list.add(badAvg     );
		list.add(goodAvg    );
		list.add(fdcBG      );
		list.add(rank       );

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

	public String getPart()
	{
		return part;
	}

	public void setPart(String part)
	{
		this.part = part;
	}

	public String getEqpId()
	{
		return eqpId;
	}

	public void setEqpId(String eqpId)
	{
		this.eqpId = eqpId;
	}

	public String getSensorName()
	{
		return sensorName;
	}

	public void setSensorName(String sensorName)
	{
		this.sensorName = sensorName;
	}

	public String getVMin()
	{
		return VMin;
	}

	public void setVMin(String vMin)
	{
		VMin = vMin;
	}

	public String getVMax()
	{
		return VMax;
	}

	public void setVMax(String vMax)
	{
		VMax = vMax;
	}

	public String getBadSum()
	{
		return badSum;
	}

	public void setBadSum(String badSum)
	{
		this.badSum = badSum;
	}

	public String getBadCnt()
	{
		return badCnt;
	}

	public void setBadCnt(String badCnt)
	{
		this.badCnt = badCnt;
	}

	public String getGoodSum()
	{
		return goodSum;
	}

	public void setGoodSum(String goodSum)
	{
		this.goodSum = goodSum;
	}

	public String getGoodCnt()
	{
		return goodCnt;
	}

	public void setGoodCnt(String goodCnt)
	{
		this.goodCnt = goodCnt;
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

	public String getFdcBG()
	{
		return fdcBG;
	}

	public void setFdcBG(String fdcBG)
	{
		this.fdcBG = fdcBG;
	}

	public String getRank()
	{
		return rank;
	}

	public void setRank(String rank)
	{
		this.rank = rank;
	}
}
