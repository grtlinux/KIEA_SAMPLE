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

package sdc.anal.lya.macro.A24.EQP_FDC_SUM04.v04;

import java.util.ArrayList;
import java.util.List;

import kiea.proj.sdc.anal.macro.impl.bean.AbstractBean;

/**
 * @author KangSeok
 * @date 2014. 8. 8.
 * @file_name MURA_SPC_ReadBean.java
 *
 */
public class EQP_FDC_SUM04_Bean extends AbstractBean
{
	/*
	"CLUSTER_ID :ClusterId  ",
	"PART       :Part       ",
	"EQPID      :EqpId      ",
	"SENSOR_NAME:SensorName ",
	"PARAM_VALUE:ParamValue ",
	"GLASSID    :GlassId    ",
	"GROUP_ID   :GroupId    ",
	
	"ODD_FLAG   :OddFlag    ",
	
	"TOT_CNT    :TotCnt     ",
	"TOT_BCNT   :TotBCnt    ",
	"TOT_GCNT   :TotGCnt    ",
	"TOT_IG     :TotIg      ",
	"L_CNT      :LCnt       ",
	"L_BCNT     :LBcnt      ",
	"L_GCNT     :LGcnt      ",
	"L_IG       :LIg        ",
	"R_CNT      :RCnt       ",
	"R_BCNT     :RBcnt      ",
	"R_GCNT     :RGcnt      ",
	"R_IG       :RIg        ",
	"INFO_GAIN  :InfoGain   ",
	*/

	private String clusterId  ;
	private String part       ;
	private String eqpId      ;
	private String sensorName ;
	private String paramValue ;
	private String glassId    ;
	private String groupId    ;
	private String oddFlag    ;
	private String totCnt     ;
	private String totBCnt    ;
	private String totGCnt    ;
	private String totIg      ;
	private String LCnt       ;
	private String LBcnt      ;
	private String LGcnt      ;
	private String LIg        ;
	private String RCnt       ;
	private String RBcnt      ;
	private String RGcnt      ;
	private String RIg        ;
	private String infoGain   ;

	public String toString()
	{
		return String.format("READ : [%s] [%s] [%s] [%s] [%s] [%s] [%s] [%s] [%s] [%s] [%s] [%s] [%s] [%s] [%s] [%s] [%s] [%s] [%s] [%s] [%s]"
			, clusterId  
			, part       
			, eqpId      
			, sensorName 
			, paramValue 
			, glassId    
			, groupId    
			, oddFlag    
			, totCnt     
			, totBCnt    
			, totGCnt    
			, totIg      
			, LCnt       
			, LBcnt      
			, LGcnt      
			, LIg        
			, RCnt       
			, RBcnt      
			, RGcnt      
			, RIg        
			, infoGain   
		);
	}

	public static String[] getHeader()
	{
		List<String> list = new ArrayList<String>();
		list.add("CLUSTER_ID" );
		list.add("PART"       );
		list.add("EQPID"      );
		list.add("SENSOR_NAME");
		list.add("PARAM_VALUE");
		list.add("GLASSID"    );
		list.add("GROUP_ID"   );
		list.add("ODD_FLAG"   );
		list.add("TOT_CNT"    );
		list.add("TOT_BCNT"   );
		list.add("TOT_GCNT"   );
		list.add("TOT_IG"     );
		list.add("L_CNT"      );
		list.add("L_BCNT"     );
		list.add("L_GCNT"     );
		list.add("L_IG"       );
		list.add("R_CNT"      );
		list.add("R_BCNT"     );
		list.add("R_GCNT"     );
		list.add("R_IG"       );
		list.add("INFO_GAIN"  );

		return list.toArray(new String[list.size()]);
	}

	public String[] getStringArray()
	{
		List<String> list = new ArrayList<String>();
		list.add(clusterId  );
		list.add(part       );
		list.add(eqpId      );
		list.add(sensorName );
		list.add(paramValue );
		list.add(glassId    );
		list.add(groupId    );
		list.add(oddFlag    );
		list.add(totCnt     );
		list.add(totBCnt    );
		list.add(totGCnt    );
		list.add(totIg      );
		list.add(LCnt       );
		list.add(LBcnt      );
		list.add(LGcnt      );
		list.add(LIg        );
		list.add(RCnt       );
		list.add(RBcnt      );
		list.add(RGcnt      );
		list.add(RIg        );
		list.add(infoGain   );

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

	public String getParamValue()
	{
		return paramValue;
	}

	public void setParamValue(String paramValue)
	{
		this.paramValue = paramValue;
	}

	public String getGlassId()
	{
		return glassId;
	}

	public void setGlassId(String glassId)
	{
		this.glassId = glassId;
	}

	public String getGroupId()
	{
		return groupId;
	}

	public void setGroupId(String groupId)
	{
		this.groupId = groupId;
	}

	public String getOddFlag()
	{
		return oddFlag;
	}

	public void setOddFlag(String oddFlag)
	{
		this.oddFlag = oddFlag;
	}

	public String getTotCnt()
	{
		return totCnt;
	}

	public void setTotCnt(String totCnt)
	{
		this.totCnt = totCnt;
	}

	public String getTotBCnt()
	{
		return totBCnt;
	}

	public void setTotBCnt(String totBCnt)
	{
		this.totBCnt = totBCnt;
	}

	public String getTotGCnt()
	{
		return totGCnt;
	}

	public void setTotGCnt(String totGCnt)
	{
		this.totGCnt = totGCnt;
	}

	public String getTotIg()
	{
		return totIg;
	}

	public void setTotIg(String totIg)
	{
		this.totIg = totIg;
	}

	public String getLCnt()
	{
		return LCnt;
	}

	public void setLCnt(String lCnt)
	{
		LCnt = lCnt;
	}

	public String getLBcnt()
	{
		return LBcnt;
	}

	public void setLBcnt(String lBcnt)
	{
		LBcnt = lBcnt;
	}

	public String getLGcnt()
	{
		return LGcnt;
	}

	public void setLGcnt(String lGcnt)
	{
		LGcnt = lGcnt;
	}

	public String getLIg()
	{
		return LIg;
	}

	public void setLIg(String lIg)
	{
		LIg = lIg;
	}

	public String getRCnt()
	{
		return RCnt;
	}

	public void setRCnt(String rCnt)
	{
		RCnt = rCnt;
	}

	public String getRBcnt()
	{
		return RBcnt;
	}

	public void setRBcnt(String rBcnt)
	{
		RBcnt = rBcnt;
	}

	public String getRGcnt()
	{
		return RGcnt;
	}

	public void setRGcnt(String rGcnt)
	{
		RGcnt = rGcnt;
	}

	public String getRIg()
	{
		return RIg;
	}

	public void setRIg(String rIg)
	{
		RIg = rIg;
	}

	public String getInfoGain()
	{
		return infoGain;
	}

	public void setInfoGain(String infoGain)
	{
		this.infoGain = infoGain;
	}
}
