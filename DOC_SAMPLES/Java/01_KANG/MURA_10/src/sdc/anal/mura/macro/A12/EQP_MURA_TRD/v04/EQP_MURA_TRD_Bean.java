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

package sdc.anal.mura.macro.A12.EQP_MURA_TRD.v04;

import java.util.ArrayList;
import java.util.List;

import kiea.proj.sdc.anal.macro.impl.bean.AbstractBean;

/**
 * @author KangSeok
 * @date 2014. 8. 8.
 * @file_name WIP_EQP_SMMRY_WriteBean.java
 *
 */
public class EQP_MURA_TRD_Bean extends AbstractBean
{
	/*
	"USER_GROUP_CODE  :UserGroupCode  ",
	"PROCESS_ID       :ProcessId      ",
	"PRODUCT_ID       :ProductId      ",
	"PRODUCT_TYPE     :ProductType    ",
	"AREA_CODE        :AreaCode       ",
	"SUB_AREA_CODE    :SubAreaCode    ",
	"STEP_ID          :StepId         ",
	"EQP_ID           :EqpId          ",
	"GLASSCELLID      :GlassCellId    ",
	"CELLID           :CellId         ",
	"TKINDATE         :TkInDate       ",
	"TKOUTDATE        :TkOutDate      ",
	"DEFECT_GROUP_CODE:DefectGroupCode",
	"MURA_DATA        :MuraData       ",
	"D_TIME           :DTime          ",
	*/

	private String userGroupCode  ;
	private String processId      ;
	private String productId      ;
	private String productType    ;
	private String areaCode       ;
	private String subAreaCode    ;
	private String stepId         ;
	private String eqpId          ;
	private String glassCellId    ;
	private String cellId         ;
	private String tkInDate       ;
	private String tkOutDate      ;
	private String defectGroupCode;
	private String muraData       ;
	private String DTime          ;

	public String toString()
	{
		return String.format("READ : [%s] [%s] [%s] [%s] [%s] [%s] [%s] [%s] [%s] [%s] [%s] [%s] [%s] [%s] [%s]"
			, userGroupCode  
			, processId      
			, productId      
			, productType    
			, areaCode       
			, subAreaCode    
			, stepId         
			, eqpId          
			, glassCellId    
			, cellId         
			, tkInDate       
			, tkOutDate      
			, defectGroupCode
			, muraData       
			, DTime          
		);
	}

	public static String[] getHeader()
	{
		List<String> list = new ArrayList<String>();
		list.add("USER_GROUP_CODE"  );
		list.add("PROCESS_ID"       );
		list.add("PRODUCT_ID"       );
		list.add("PRODUCT_TYPE"     );
		list.add("AREA_CODE"        );
		list.add("SUB_AREA_CODE"    );
		list.add("STEP_ID"          );
		list.add("EQP_ID"           );
		list.add("GLASSCELLID"      );
		list.add("CELLID"           );
		list.add("TKINDATE"         );
		list.add("TKOUTDATE"        );
		list.add("DEFECT_GROUP_CODE");
		list.add("MURA_DATA"        );
		list.add("D_TIME"           );

		return list.toArray(new String[list.size()]);
	}

	public String[] getStringArray()
	{
		List<String> list = new ArrayList<String>();
		list.add(userGroupCode  );
		list.add(processId      );
		list.add(productId      );
		list.add(productType    );
		list.add(areaCode       );
		list.add(subAreaCode    );
		list.add(stepId         );
		list.add(eqpId          );
		list.add(glassCellId    );
		list.add(cellId         );
		list.add(tkInDate       );
		list.add(tkOutDate      );
		list.add(defectGroupCode);
		list.add(muraData       );
		list.add(DTime          );

		return list.toArray(new String[list.size()]);
	}

	/**
	 * @return the userGroupCode
	 */
	public String getUserGroupCode()
	{
		return userGroupCode;
	}

	/**
	 * @return the processId
	 */
	public String getProcessId()
	{
		return processId;
	}

	/**
	 * @return the productId
	 */
	public String getProductId()
	{
		return productId;
	}

	/**
	 * @return the productType
	 */
	public String getProductType()
	{
		return productType;
	}

	/**
	 * @return the areaCode
	 */
	public String getAreaCode()
	{
		return areaCode;
	}

	/**
	 * @return the subAreaCode
	 */
	public String getSubAreaCode()
	{
		return subAreaCode;
	}

	/**
	 * @return the stepId
	 */
	public String getStepId()
	{
		return stepId;
	}

	/**
	 * @return the eqpId
	 */
	public String getEqpId()
	{
		return eqpId;
	}

	/**
	 * @return the glassCellId
	 */
	public String getGlassCellId()
	{
		return glassCellId;
	}

	/**
	 * @return the cellId
	 */
	public String getCellId()
	{
		return cellId;
	}

	/**
	 * @return the tkInDate
	 */
	public String getTkInDate()
	{
		return tkInDate;
	}

	/**
	 * @return the tkOutDate
	 */
	public String getTkOutDate()
	{
		return tkOutDate;
	}

	/**
	 * @return the defectGroupCode
	 */
	public String getDefectGroupCode()
	{
		return defectGroupCode;
	}

	/**
	 * @return the muraData
	 */
	public String getMuraData()
	{
		return muraData;
	}

	/**
	 * @return the dTime
	 */
	public String getDTime()
	{
		return DTime;
	}

	/**
	 * @param userGroupCode the userGroupCode to set
	 */
	public void setUserGroupCode(String userGroupCode)
	{
		this.userGroupCode = userGroupCode;
	}

	/**
	 * @param processId the processId to set
	 */
	public void setProcessId(String processId)
	{
		this.processId = processId;
	}

	/**
	 * @param productId the productId to set
	 */
	public void setProductId(String productId)
	{
		this.productId = productId;
	}

	/**
	 * @param productType the productType to set
	 */
	public void setProductType(String productType)
	{
		this.productType = productType;
	}

	/**
	 * @param areaCode the areaCode to set
	 */
	public void setAreaCode(String areaCode)
	{
		this.areaCode = areaCode;
	}

	/**
	 * @param subAreaCode the subAreaCode to set
	 */
	public void setSubAreaCode(String subAreaCode)
	{
		this.subAreaCode = subAreaCode;
	}

	/**
	 * @param stepId the stepId to set
	 */
	public void setStepId(String stepId)
	{
		this.stepId = stepId;
	}

	/**
	 * @param eqpId the eqpId to set
	 */
	public void setEqpId(String eqpId)
	{
		this.eqpId = eqpId;
	}

	/**
	 * @param glassCellId the glassCellId to set
	 */
	public void setGlassCellId(String glassCellId)
	{
		this.glassCellId = glassCellId;
	}

	/**
	 * @param cellId the cellId to set
	 */
	public void setCellId(String cellId)
	{
		this.cellId = cellId;
	}

	/**
	 * @param tkInDate the tkInDate to set
	 */
	public void setTkInDate(String tkInDate)
	{
		this.tkInDate = tkInDate;
	}

	/**
	 * @param tkOutDate the tkOutDate to set
	 */
	public void setTkOutDate(String tkOutDate)
	{
		this.tkOutDate = tkOutDate;
	}

	/**
	 * @param defectGroupCode the defectGroupCode to set
	 */
	public void setDefectGroupCode(String defectGroupCode)
	{
		this.defectGroupCode = defectGroupCode;
	}

	/**
	 * @param muraData the muraData to set
	 */
	public void setMuraData(String muraData)
	{
		this.muraData = muraData;
	}

	/**
	 * @param dTime the dTime to set
	 */
	public void setDTime(String dTime)
	{
		DTime = dTime;
	}
}
