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

package sdc.anal.lya.macro.A24.EQP_FDC_SUM03.v04;

import java.util.ArrayList;
import java.util.List;

import kiea.proj.sdc.anal.macro.impl.bean.AbstractBean;

/**
 * @author KangSeok
 * @date 2014. 8. 8.
 * @file_name MURA_SPC_ReadBean.java
 *
 */
public class EQP_FDC_SUM02_Bean extends AbstractBean
{
	/*
	"LINE       :Line       ",
	"PART       :Part       ",
	"EQPMODEL   :EqpModel   ",
	"EQPID      :EqpId      ",
	"MODULE_NAME:ModuleName ",
	"PROCID     :ProcId     ",
	"PRODID     :ProdId     ",
	"PPID       :PpId       ",
	"GLASSID    :GlassId    ",
	"PROCESSSTEP:ProcessStep",
	"BEGINSTEP  :BeginStep  ",
	"SENSOR_NAME:SensorName ",
	"PARAM      :Param      ",
	"PARAM_VALUE:ParamValue ",
	"USL        :Usl        ",
	"LSL        :Lsl        ",
	"BEGINTIME  :BeginTime  ",
	"CLUSTER_ID :ClusterId  ",
	"GROUP_ID   :GroupId    ",
	*/

	private String line       ;
	private String part       ;
	private String eqpModel   ;
	private String eqpId      ;
	private String moduleName ;
	private String procId     ;
	private String prodId     ;
	private String ppId       ;
	private String glassId    ;
	private String processStep;
	private String beginStep  ;
	private String sensorName ;
	private String param      ;
	private String paramValue ;
	private String usl        ;
	private String lsl        ;
	private String beginTime  ;
	private String clusterId  ;
	private String groupId    ;

	public String toString()
	{
		return String.format("READ : [%s] [%s] [%s] [%s] [%s] [%s] [%s] [%s] [%s] [%s] [%s] [%s] [%s] [%s] [%s] [%s] [%s] [%s] [%s]"
			, line       
			, part       
			, eqpModel   
			, eqpId      
			, moduleName 
			, procId     
			, prodId     
			, ppId       
			, glassId    
			, processStep
			, beginStep  
			, sensorName 
			, param      
			, paramValue 
			, usl        
			, lsl        
			, beginTime  
			, clusterId  
			, groupId    
		);
	}

	public static String[] getHeader()
	{
		List<String> list = new ArrayList<String>();
		list.add("LINE"       );
		list.add("PART"       );
		list.add("EQPMODEL"   );
		list.add("EQPID"      );
		list.add("MODULE_NAME");
		list.add("PROCID"     );
		list.add("PRODID"     );
		list.add("PPID"       );
		list.add("GLASSID"    );
		list.add("PROCESSSTEP");
		list.add("BEGINSTEP"  );
		list.add("SENSOR_NAME");
		list.add("PARAM"      );
		list.add("PARAM_VALUE");
		list.add("USL"        );
		list.add("LSL"        );
		list.add("BEGINTIME"  );
		list.add("CLUSTER_ID" );
		list.add("GROUP_ID"   );

		return list.toArray(new String[list.size()]);
	}

	public String[] getStringArray()
	{
		List<String> list = new ArrayList<String>();
		list.add(line       );
		list.add(part       );
		list.add(eqpModel   );
		list.add(eqpId      );
		list.add(moduleName );
		list.add(procId     );
		list.add(prodId     );
		list.add(ppId       );
		list.add(glassId    );
		list.add(processStep);
		list.add(beginStep  );
		list.add(sensorName );
		list.add(param      );
		list.add(paramValue );
		list.add(usl        );
		list.add(lsl        );
		list.add(beginTime  );
		list.add(clusterId  );
		list.add(groupId    );

		return list.toArray(new String[list.size()]);
	}

	public String getLine()
	{
		return line;
	}

	public void setLine(String line)
	{
		this.line = line;
	}

	public String getPart()
	{
		return part;
	}

	public void setPart(String part)
	{
		this.part = part;
	}

	public String getEqpModel()
	{
		return eqpModel;
	}

	public void setEqpModel(String eqpModel)
	{
		this.eqpModel = eqpModel;
	}

	public String getEqpId()
	{
		return eqpId;
	}

	public void setEqpId(String eqpId)
	{
		this.eqpId = eqpId;
	}

	public String getModuleName()
	{
		return moduleName;
	}

	public void setModuleName(String moduleName)
	{
		this.moduleName = moduleName;
	}

	public String getProcId()
	{
		return procId;
	}

	public void setProcId(String procId)
	{
		this.procId = procId;
	}

	public String getProdId()
	{
		return prodId;
	}

	public void setProdId(String prodId)
	{
		this.prodId = prodId;
	}

	public String getPpId()
	{
		return ppId;
	}

	public void setPpId(String ppId)
	{
		this.ppId = ppId;
	}

	public String getGlassId()
	{
		return glassId;
	}

	public void setGlassId(String glassId)
	{
		this.glassId = glassId;
	}

	public String getProcessStep()
	{
		return processStep;
	}

	public void setProcessStep(String processStep)
	{
		this.processStep = processStep;
	}

	public String getBeginStep()
	{
		return beginStep;
	}

	public void setBeginStep(String beginStep)
	{
		this.beginStep = beginStep;
	}

	public String getSensorName()
	{
		return sensorName;
	}

	public void setSensorName(String sensorName)
	{
		this.sensorName = sensorName;
	}

	public String getParam()
	{
		return param;
	}

	public void setParam(String param)
	{
		this.param = param;
	}

	public String getParamValue()
	{
		return paramValue;
	}

	public void setParamValue(String paramValue)
	{
		this.paramValue = paramValue;
	}

	public String getUsl()
	{
		return usl;
	}

	public void setUsl(String usl)
	{
		this.usl = usl;
	}

	public String getLsl()
	{
		return lsl;
	}

	public void setLsl(String lsl)
	{
		this.lsl = lsl;
	}

	public String getBeginTime()
	{
		return beginTime;
	}

	public void setBeginTime(String beginTime)
	{
		this.beginTime = beginTime;
	}

	public String getClusterId()
	{
		return clusterId;
	}

	public void setClusterId(String clusterId)
	{
		this.clusterId = clusterId;
	}

	public String getGroupId()
	{
		return groupId;
	}

	public void setGroupId(String groupId)
	{
		this.groupId = groupId;
	}
}
