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

package sdc.anal.lya.macro.A11.INSPSTEP_DEFECTTRACE.v01;

import java.util.ArrayList;
import java.util.List;

import kiea.proj.sdc.anal.macro.impl.bean.AbstractBean;

/**
 * @author KangSeok
 * @date 2014. 8. 8.
 * @file_name AIB_INSPSTEP_DEFECTTRACE_ReadBean.java
 *
 */
public class INSPSTEP_DEFECTTRACE_ReadBean extends AbstractBean
{
	/*
	"SITEID         :SiteId         ",
	"EQPUNITID      :EqpUnitId      ",
	"INSPSTEPGRPID  :InspStepGrpId  ",
	"INSPSTEPGRPNAME:InspStepGrpName",
	"AREAID         :AreaId         ",
	"INSPSTEPGRPTYPE:InspStepGrpType",
	"ORD            :Ord            ",
	"COORDCONVERTYN :CoordConvertYn ",
	*/

	private String siteId         ;
	private String eqpUnitId      ;
	private String inspStepGrpId  ;
	private String inspStepGrpName;
	private String areaId         ;
	private String inspStepGrpType;
	private String ord            ;
	private String coordConvertYn ;

	public String toString()
	{
		return String.format("READ : [%s] [%s] [%s] [%s] [%s] [%s] [%s] [%s]"
			, siteId         
			, eqpUnitId      
			, inspStepGrpId  
			, inspStepGrpName
			, areaId         
			, inspStepGrpType
			, ord            
			, coordConvertYn 
		);
	}

	public static String[] getHeader()
	{
		List<String> list = new ArrayList<String>();
		list.add("SITEID"         );
		list.add("EQPUNITID"      );
		list.add("INSPSTEPGRPID"  );
		list.add("INSPSTEPGRPNAME");
		list.add("AREAID"         );
		list.add("INSPSTEPGRPTYPE");
		list.add("ORD"            );
		list.add("COORDCONVERTYN" );

		return list.toArray(new String[list.size()]);
	}

	public String[] getStringArray()
	{
		List<String> list = new ArrayList<String>();
		list.add(siteId         );
		list.add(eqpUnitId      );
		list.add(inspStepGrpId  );
		list.add(inspStepGrpName);
		list.add(areaId         );
		list.add(inspStepGrpType);
		list.add(ord            );
		list.add(coordConvertYn );

		return list.toArray(new String[list.size()]);
	}

	/**
	 * @return the siteId
	 */
	public String getSiteId()
	{
		return siteId;
	}

	/**
	 * @return the eqpUnitId
	 */
	public String getEqpUnitId()
	{
		return eqpUnitId;
	}

	/**
	 * @return the inspStepGrpId
	 */
	public String getInspStepGrpId()
	{
		return inspStepGrpId;
	}

	/**
	 * @return the inspStepGrpName
	 */
	public String getInspStepGrpName()
	{
		return inspStepGrpName;
	}

	/**
	 * @return the areaId
	 */
	public String getAreaId()
	{
		return areaId;
	}

	/**
	 * @return the inspStepGrpType
	 */
	public String getInspStepGrpType()
	{
		return inspStepGrpType;
	}

	/**
	 * @return the ord
	 */
	public String getOrd()
	{
		return ord;
	}

	/**
	 * @return the coordConvertYn
	 */
	public String getCoordConvertYn()
	{
		return coordConvertYn;
	}

	/**
	 * @param siteId the siteId to set
	 */
	public void setSiteId(String siteId)
	{
		this.siteId = siteId;
	}

	/**
	 * @param eqpUnitId the eqpUnitId to set
	 */
	public void setEqpUnitId(String eqpUnitId)
	{
		this.eqpUnitId = eqpUnitId;
	}

	/**
	 * @param inspStepGrpId the inspStepGrpId to set
	 */
	public void setInspStepGrpId(String inspStepGrpId)
	{
		this.inspStepGrpId = inspStepGrpId;
	}

	/**
	 * @param inspStepGrpName the inspStepGrpName to set
	 */
	public void setInspStepGrpName(String inspStepGrpName)
	{
		this.inspStepGrpName = inspStepGrpName;
	}

	/**
	 * @param areaId the areaId to set
	 */
	public void setAreaId(String areaId)
	{
		this.areaId = areaId;
	}

	/**
	 * @param inspStepGrpType the inspStepGrpType to set
	 */
	public void setInspStepGrpType(String inspStepGrpType)
	{
		this.inspStepGrpType = inspStepGrpType;
	}

	/**
	 * @param ord the ord to set
	 */
	public void setOrd(String ord)
	{
		this.ord = ord;
	}

	/**
	 * @param coordConvertYn the coordConvertYn to set
	 */
	public void setCoordConvertYn(String coordConvertYn)
	{
		this.coordConvertYn = coordConvertYn;
	}
}
