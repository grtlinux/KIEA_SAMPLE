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

package kiea.proj.sdc.anal.macro.sample.a02.bean;

import java.util.ArrayList;
import java.util.List;

import kiea.proj.sdc.anal.macro.impl.bean.AbstractBean;

/**
 * @author KangSeok
 * @date 2014. 8. 5.
 * @file_name Sample02WriteBean.java
 *
 */
public class Sample02WriteBean extends AbstractBean
{
	private String inspHour;
	private String cellId;
	private String inspStepType;
	private String decGradeCd;
	private String defectCd;
	private String binGradeCd;
	private int   rn;

	/**
	 * 
	 * toString
	 *
	 * @return
	 */
	@Override
	public String toString()
	{
		return String.format("WRITE : [%s] [%s] [%s] [%s] [%s] [%s] [%d]", inspHour, cellId, inspStepType, decGradeCd, defectCd, binGradeCd, rn);
	}

	/**
	 * 
	 * getHeader
	 *
	 * @return
	 */
	public static String[] getHeader()
	{
		List<String> list = new ArrayList<String>();
		list.add("INSPHOUR");
		list.add("CELLID");
		list.add("INSPSTEPTYPE");
		list.add("DECGRADECD");
		list.add("DEFECTCD");
		list.add("BINGRADECD");
		list.add("RN");
		
		return list.toArray(new String[list.size()]);
	}
	
	/**
	 * 
	 * getStringArray
	 *
	 * @return
	 */
	@Override
	public String[] getStringArray()
	{
		List<String> list = new ArrayList<String>();
		list.add(inspHour);
		list.add(cellId);
		list.add(inspStepType);
		list.add(decGradeCd);
		list.add(defectCd);
		list.add(binGradeCd);
		list.add(Integer.toString(rn));

		return list.toArray(new String[list.size()]);
	}

	/**
	 * @return the inspHour
	 */
	public String getInspHour()
	{
		return inspHour;
	}

	/**
	 * @param inspHour the inspHour to set
	 */
	public void setInspHour(String inspHour)
	{
		this.inspHour = inspHour;
	}

	/**
	 * @return the cellId
	 */
	public String getCellId()
	{
		return cellId;
	}

	/**
	 * @param cellId the cellId to set
	 */
	public void setCellId(String cellId)
	{
		this.cellId = cellId;
	}

	/**
	 * @return the inspStepType
	 */
	public String getInspStepType()
	{
		return inspStepType;
	}

	/**
	 * @param inspStepType the inspStepType to set
	 */
	public void setInspStepType(String inspStepType)
	{
		this.inspStepType = inspStepType;
	}

	/**
	 * @return the decGradeCd
	 */
	public String getDecGradeCd()
	{
		return decGradeCd;
	}

	/**
	 * @param decGradeCd the decGradeCd to set
	 */
	public void setDecGradeCd(String decGradeCd)
	{
		this.decGradeCd = decGradeCd;
	}

	/**
	 * @return the defectCd
	 */
	public String getDefectCd()
	{
		return defectCd;
	}

	/**
	 * @param defectCd the defectCd to set
	 */
	public void setDefectCd(String defectCd)
	{
		this.defectCd = defectCd;
	}

	/**
	 * @return the binGradeCd
	 */
	public String getBinGradeCd()
	{
		return binGradeCd;
	}

	/**
	 * @param binGradeCd the binGradeCd to set
	 */
	public void setBinGradeCd(String binGradeCd)
	{
		this.binGradeCd = binGradeCd;
	}

	/**
	 * @return the rn
	 */
	public int getRn()
	{
		return rn;
	}

	/**
	 * @param rn the rn to set
	 */
	public void setRn(int rn)
	{
		this.rn = rn;
	}
}
