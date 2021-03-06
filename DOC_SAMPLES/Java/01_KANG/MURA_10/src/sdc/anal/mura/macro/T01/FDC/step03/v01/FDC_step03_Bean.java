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

package sdc.anal.mura.macro.T01.FDC.step03.v01;

import java.util.ArrayList;
import java.util.List;

import kiea.proj.sdc.anal.macro.impl.bean.AbstractBean;

/**
 * @author KangSeok
 * @date 2014. 8. 8.
 * @file_name MURA_SPC_ReadBean.java
 *
 */
public class FDC_step03_Bean extends AbstractBean
{
	/*
	"CLUSTER_ID :ClusterId  ",
	"PART       :Part       ",
	"EQPID      :EqpId      ",
	"SENSOR_NAME:SensorName ",
	"PARAM_VALUE:ParamValue ",
	"GLASSID    :GlassId    ",
	"GROUP_ID   :GroupId    ",
	*/

	private String clusterId  ;
	private String part       ;
	private String eqpId      ;
	private String sensorName ;
	private String paramValue ;
	private String glassId    ;
	private String groupId    ;

	public String toString()
	{
		return String.format("READ : [%s] [%s] [%s] [%s] [%s] [%s] [%s]"
			, clusterId  
			, part       
			, eqpId      
			, sensorName 
			, paramValue 
			, glassId    
			, groupId    
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
}
