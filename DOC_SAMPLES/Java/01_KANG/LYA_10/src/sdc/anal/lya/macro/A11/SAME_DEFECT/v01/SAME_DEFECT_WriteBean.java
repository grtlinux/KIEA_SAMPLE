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

package sdc.anal.lya.macro.A11.SAME_DEFECT.v01;

import java.util.ArrayList;
import java.util.List;

import kiea.proj.sdc.anal.macro.impl.bean.AbstractBean;

/**
 * @author KangSeok
 * @date 2014. 8. 8.
 * @file_name AIB_SAME_DEFECT_WriteBean.java
 *
 */
public class SAME_DEFECT_WriteBean extends AbstractBean
{
	/*
	"DEFECTCD       :DefectCd      ",
	"DEFECTLINEID   :DefectLineId  ",
	"SAME_DEFECTCD  :SameDefectCd  ",
	"SAME_DEFECTNAME:SameDefectName",
	*/

	private String defectCd      ;
	private String defectLineId  ;
	private String sameDefectCd  ;
	private String sameDefectName;

	public String toString()
	{
		return String.format("READ : [%s] [%s] [%s] [%s]"
			, defectCd      
			, defectLineId  
			, sameDefectCd  
			, sameDefectName
		);
	}

	public static String[] getHeader()
	{
		List<String> list = new ArrayList<String>();
		list.add("DEFECTCD"       );
		list.add("DEFECTLINEID"   );
		list.add("SAME_DEFECTCD"  );
		list.add("SAME_DEFECTNAME");

		return list.toArray(new String[list.size()]);
	}

	public String[] getStringArray()
	{
		List<String> list = new ArrayList<String>();
		list.add(defectCd      );
		list.add(defectLineId  );
		list.add(sameDefectCd  );
		list.add(sameDefectName);

		return list.toArray(new String[list.size()]);
	}

	/**
	 * @return the defectCd
	 */
	public String getDefectCd()
	{
		return defectCd;
	}

	/**
	 * @return the defectLineId
	 */
	public String getDefectLineId()
	{
		return defectLineId;
	}

	/**
	 * @return the sameDefectCd
	 */
	public String getSameDefectCd()
	{
		return sameDefectCd;
	}

	/**
	 * @return the sameDefectName
	 */
	public String getSameDefectName()
	{
		return sameDefectName;
	}

	/**
	 * @param defectCd the defectCd to set
	 */
	public void setDefectCd(String defectCd)
	{
		this.defectCd = defectCd;
	}

	/**
	 * @param defectLineId the defectLineId to set
	 */
	public void setDefectLineId(String defectLineId)
	{
		this.defectLineId = defectLineId;
	}

	/**
	 * @param sameDefectCd the sameDefectCd to set
	 */
	public void setSameDefectCd(String sameDefectCd)
	{
		this.sameDefectCd = sameDefectCd;
	}

	/**
	 * @param sameDefectName the sameDefectName to set
	 */
	public void setSameDefectName(String sameDefectName)
	{
		this.sameDefectName = sameDefectName;
	}
}
