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

package sdc.anal.lya.macro.A27.RP_SYS_EVENT_HIST_SUMM.v01;

import java.util.ArrayList;
import java.util.List;

import kiea.proj.sdc.anal.macro.impl.bean.AbstractBean;

/**
 * @author KangSeok
 * @date 2014. 8. 8.
 * @file_name RP_TOTWIP_SUMM_Bean.java
 *
 */
public class RP_SYS_EVENT_HIST_SUMM_Bean extends AbstractBean
{
	/*
	"CLUSTER_ID:ClusterId",
	"STEP_ID   :StepId   ",
	"STEP_DESC :StepDesc ",
	"LAYER_CODE:LayerCode",
	"SUM       :Sum      ",
	"REWORK    :Rework   ",
	"REPROCESS :Reprocess",
	"SPC       :Spc      ",
	"RMS       :Rms      ",
	"FDC       :Fdc      ",
	"RANK      :Rank     ",
	*/

	private String clusterId;
	private String stepId   ;
	private String stepDesc ;
	private String layerCode;
	private String sum      ;
	private String rework   ;
	private String reprocess;
	private String spc      ;
	private String rms      ;
	private String fdc      ;
	private String rank     ;

	public void addSum()
	{
		this.sum = "" + (Integer.parseInt(this.sum) + 1);
	}
	
	public void addRework()
	{
		this.rework = "" + (Integer.parseInt(this.rework) + 1);
	}
	
	public void addReprocess()
	{
		this.reprocess = "" + (Integer.parseInt(this.reprocess) + 1);
	}
	
	public void addSpc()
	{
		this.spc = "" + (Integer.parseInt(this.spc) + 1);
	}
	
	public void addRms()
	{
		this.rms = "" + (Integer.parseInt(this.rms) + 1);
	}
	
	public void addFdc()
	{
		this.fdc = "" + (Integer.parseInt(this.fdc) + 1);
	}
	
	public String toString()
	{
		return String.format("READ : [%s] [%s] [%s] [%s] [%s] [%s] [%s] [%s] [%s] [%s] [%s]"
			, clusterId
			, stepId   
			, stepDesc 
			, layerCode
			, sum      
			, rework   
			, reprocess
			, spc      
			, rms      
			, fdc      
			, rank     
		);
	}

	public static String[] getHeader()
	{
		List<String> list = new ArrayList<String>();
		list.add("CLUSTER_ID");
		list.add("STEP_ID"   );
		list.add("STEP_DESC" );
		list.add("LAYER_CODE");
		list.add("SUM"       );
		list.add("REWORK"    );
		list.add("REPROCESS" );
		list.add("SPC"       );
		list.add("RMS"       );
		list.add("FDC"       );
		list.add("RANK"      );

		return list.toArray(new String[list.size()]);
	}

	public String[] getStringArray()
	{
		List<String> list = new ArrayList<String>();
		list.add(clusterId);
		list.add(stepId   );
		list.add(stepDesc );
		list.add(layerCode);
		list.add(sum      );
		list.add(rework   );
		list.add(reprocess);
		list.add(spc      );
		list.add(rms      );
		list.add(fdc      );
		list.add(rank     );

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

	public String getLayerCode()
	{
		return layerCode;
	}

	public void setLayerCode(String layerCode)
	{
		this.layerCode = layerCode;
	}

	public String getSum()
	{
		return sum;
	}

	public void setSum(String sum)
	{
		this.sum = sum;
	}

	public String getRework()
	{
		return rework;
	}

	public void setRework(String rework)
	{
		this.rework = rework;
	}

	public String getReprocess()
	{
		return reprocess;
	}

	public void setReprocess(String reprocess)
	{
		this.reprocess = reprocess;
	}

	public String getSpc()
	{
		return spc;
	}

	public void setSpc(String spc)
	{
		this.spc = spc;
	}

	public String getRms()
	{
		return rms;
	}

	public void setRms(String rms)
	{
		this.rms = rms;
	}

	public String getFdc()
	{
		return fdc;
	}

	public void setFdc(String fdc)
	{
		this.fdc = fdc;
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
