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

package sdc.anal.lya.macro.A11.DEFECT_GRP_CODE.v01;

import java.util.ArrayList;
import java.util.List;

import kiea.proj.sdc.anal.macro.impl.bean.AbstractBean;

/**
 * @author KangSeok
 * @date 2014. 8. 8.
 * @file_name AIB_DEFECT_GRP_CODE_WriteBean.java
 *
 */
public class DEFECT_GRP_CODE_WriteBean extends AbstractBean
{
	/*
	"SITEID       :SiteId       ",
	"DEFECTCD     :DefectCd     ",
	"DEFECTNAME   :DefectName   ",
	"DEFECTGRPNAME:DefectGrpName",
	"AREAID       :AreaId       ",
	*/

	private String siteId       ;
	private String defectCd     ;
	private String defectName   ;
	private String defectGrpName;
	private String areaId       ;

	public String toString()
	{
		return String.format("READ : [%s] [%s] [%s] [%s] [%s]"
			, siteId       
			, defectCd     
			, defectName   
			, defectGrpName
			, areaId       
		);
	}

	public static String[] getHeader()
	{
		List<String> list = new ArrayList<String>();
		list.add("SITEID"       );
		list.add("DEFECTCD"     );
		list.add("DEFECTNAME"   );
		list.add("DEFECTGRPNAME");
		list.add("AREAID"       );

		return list.toArray(new String[list.size()]);
	}

	public String[] getStringArray()
	{
		List<String> list = new ArrayList<String>();
		list.add(siteId       );
		list.add(defectCd     );
		list.add(defectName   );
		list.add(defectGrpName);
		list.add(areaId       );

		return list.toArray(new String[list.size()]);
	}

	public String getSiteId()
	{
		return siteId;
	}

	public void setSiteId(String siteId)
	{
		this.siteId = siteId;
	}

	public String getDefectCd()
	{
		return defectCd;
	}

	public void setDefectCd(String defectCd)
	{
		this.defectCd = defectCd;
	}

	public String getDefectName()
	{
		return defectName;
	}

	public void setDefectName(String defectName)
	{
		this.defectName = defectName;
	}

	public String getDefectGrpName()
	{
		return defectGrpName;
	}

	public void setDefectGrpName(String defectGrpName)
	{
		this.defectGrpName = defectGrpName;
	}

	public String getAreaId()
	{
		return areaId;
	}

	public void setAreaId(String areaId)
	{
		this.areaId = areaId;
	}
}
