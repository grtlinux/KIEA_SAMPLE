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

package sdc.anal.lya.macro.A27.RP_SYS_EVENT_HIST_DETAIL.v01;

import java.util.ArrayList;
import java.util.List;

import kiea.proj.sdc.anal.macro.impl.bean.AbstractBean;

/**
 * @author KangSeok
 * @date 2014. 8. 8.
 * @file_name AIB_STEP_DESC_WriteBean.java
 *
 */
public class STEP_DESC_Bean extends AbstractBean
{
	/*
	"IMPTSTEPGRPID:ImptStepGrpId",
	"SITEID       :SiteId       ",
	"AREAID       :AreaId       ",
	"LAYERID      :LayerId      ",
	"STEPDESC     :StepDesc     ",
	"STEPDEPT     :StepDept     ",
	*/

	private String imptStepGrpId;
	private String siteId       ;
	private String areaId       ;
	private String layerId      ;
	private String stepDesc     ;
	private String stepDept     ;

	public String toString()
	{
		return String.format("READ : [%s] [%s] [%s] [%s] [%s] [%s]"
			, imptStepGrpId
			, siteId       
			, areaId       
			, layerId      
			, stepDesc     
			, stepDept     
		);
	}

	public static String[] getHeader()
	{
		List<String> list = new ArrayList<String>();
		list.add("IMPTSTEPGRPID");
		list.add("SITEID"       );
		list.add("AREAID"       );
		list.add("LAYERID"      );
		list.add("STEPDESC"     );
		list.add("STEPDEPT"     );

		return list.toArray(new String[list.size()]);
	}

	public String[] getStringArray()
	{
		List<String> list = new ArrayList<String>();
		list.add(imptStepGrpId);
		list.add(siteId       );
		list.add(areaId       );
		list.add(layerId      );
		list.add(stepDesc     );
		list.add(stepDept     );

		return list.toArray(new String[list.size()]);
	}

	/**
	 * @return the imptStepGrpId
	 */
	public String getImptStepGrpId()
	{
		return imptStepGrpId;
	}

	/**
	 * @return the siteId
	 */
	public String getSiteId()
	{
		return siteId;
	}

	/**
	 * @return the areaId
	 */
	public String getAreaId()
	{
		return areaId;
	}

	/**
	 * @return the layerId
	 */
	public String getLayerId()
	{
		return layerId;
	}

	/**
	 * @return the stepDesc
	 */
	public String getStepDesc()
	{
		return stepDesc;
	}

	/**
	 * @return the stepDept
	 */
	public String getStepDept()
	{
		return stepDept;
	}

	/**
	 * @param imptStepGrpId the imptStepGrpId to set
	 */
	public void setImptStepGrpId(String imptStepGrpId)
	{
		this.imptStepGrpId = imptStepGrpId;
	}

	/**
	 * @param siteId the siteId to set
	 */
	public void setSiteId(String siteId)
	{
		this.siteId = siteId;
	}

	/**
	 * @param areaId the areaId to set
	 */
	public void setAreaId(String areaId)
	{
		this.areaId = areaId;
	}

	/**
	 * @param layerId the layerId to set
	 */
	public void setLayerId(String layerId)
	{
		this.layerId = layerId;
	}

	/**
	 * @param stepDesc the stepDesc to set
	 */
	public void setStepDesc(String stepDesc)
	{
		this.stepDesc = stepDesc;
	}

	/**
	 * @param stepDept the stepDept to set
	 */
	public void setStepDept(String stepDept)
	{
		this.stepDept = stepDept;
	}
}
