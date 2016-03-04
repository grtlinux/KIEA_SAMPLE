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

package kiea.proj.sdc.anal.macro.sample.a12.aib.macro.AIB_WIP_CELL_RATIO.v01;

import java.util.ArrayList;
import java.util.List;

import kiea.proj.sdc.anal.macro.impl.bean.AbstractBean;

/**
 * @author KangSeok
 * @date 2014. 8. 8.
 * @file_name AIB_WIP_CELL_RATIO_ReadBean.java
 *
 */
public class AIB_WIP_CELL_RATIO_ReadBean extends AbstractBean
{
	/*
	"DIV_CODE     :DivCode      ",
	"IMPTSTEPGRPID:ImptStepGrpId",
	"IMPTEQPID    :ImptEqpId    ",
	"TRCK_OUT_HOUR:TrckOutHour  ",
	"DEFECTCD     :DefectCd     ",
	"DECGRADECD   :DecGradeCd   ",
	"DECQTY       :DecQty       ",
	*/

	private String divCode      ;
	private String imptStepGrpId;
	private String imptEqpId    ;
	private String trckOutHour  ;
	private String defectCd     ;
	private String decGradeCd   ;
	private String decQty       ;

	public String toString()
	{
		return String.format("READ : [%s] [%s] [%s] [%s] [%s] [%s] [%s]"
			, divCode      
			, imptStepGrpId
			, imptEqpId    
			, trckOutHour  
			, defectCd     
			, decGradeCd   
			, decQty       
		);
	}

	public static String[] getHeader()
	{
		List<String> list = new ArrayList<String>();
		list.add("DIV_CODE"     );
		list.add("IMPTSTEPGRPID");
		list.add("IMPTEQPID"    );
		list.add("TRCK_OUT_HOUR");
		list.add("DEFECTCD"     );
		list.add("DECGRADECD"   );
		list.add("DECQTY"       );

		return list.toArray(new String[list.size()]);
	}

	public String[] getStringArray()
	{
		List<String> list = new ArrayList<String>();
		list.add(divCode      );
		list.add(imptStepGrpId);
		list.add(imptEqpId    );
		list.add(trckOutHour  );
		list.add(defectCd     );
		list.add(decGradeCd   );
		list.add(decQty       );

		return list.toArray(new String[list.size()]);
	}

	/**
	 * @return the divCode
	 */
	public String getDivCode()
	{
		return divCode;
	}

	/**
	 * @return the imptStepGrpId
	 */
	public String getImptStepGrpId()
	{
		return imptStepGrpId;
	}

	/**
	 * @return the imptEqpId
	 */
	public String getImptEqpId()
	{
		return imptEqpId;
	}

	/**
	 * @return the trckOutHour
	 */
	public String getTrckOutHour()
	{
		return trckOutHour;
	}

	/**
	 * @return the defectCd
	 */
	public String getDefectCd()
	{
		return defectCd;
	}

	/**
	 * @return the decGradeCd
	 */
	public String getDecGradeCd()
	{
		return decGradeCd;
	}

	/**
	 * @return the decQty
	 */
	public String getDecQty()
	{
		return decQty;
	}

	/**
	 * @param divCode the divCode to set
	 */
	public void setDivCode(String divCode)
	{
		this.divCode = divCode;
	}

	/**
	 * @param imptStepGrpId the imptStepGrpId to set
	 */
	public void setImptStepGrpId(String imptStepGrpId)
	{
		this.imptStepGrpId = imptStepGrpId;
	}

	/**
	 * @param imptEqpId the imptEqpId to set
	 */
	public void setImptEqpId(String imptEqpId)
	{
		this.imptEqpId = imptEqpId;
	}

	/**
	 * @param trckOutHour the trckOutHour to set
	 */
	public void setTrckOutHour(String trckOutHour)
	{
		this.trckOutHour = trckOutHour;
	}

	/**
	 * @param defectCd the defectCd to set
	 */
	public void setDefectCd(String defectCd)
	{
		this.defectCd = defectCd;
	}

	/**
	 * @param decGradeCd the decGradeCd to set
	 */
	public void setDecGradeCd(String decGradeCd)
	{
		this.decGradeCd = decGradeCd;
	}

	/**
	 * @param decQty the decQty to set
	 */
	public void setDecQty(String decQty)
	{
		this.decQty = decQty;
	}
}
