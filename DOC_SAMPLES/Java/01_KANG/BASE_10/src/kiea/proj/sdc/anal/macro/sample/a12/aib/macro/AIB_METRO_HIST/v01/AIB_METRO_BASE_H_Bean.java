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

package kiea.proj.sdc.anal.macro.sample.a12.aib.macro.AIB_METRO_HIST.v01;

import java.util.ArrayList;
import java.util.List;

import kiea.proj.sdc.anal.macro.impl.bean.AbstractBean;

/**
 * @author KangSeok
 * @date 2014. 8. 8.
 * @file_name AIB_DCOL_BASE_H_Bean.java
 *
 */
public class AIB_METRO_BASE_H_Bean extends AbstractBean
{
	/*
	"LINE_CODE :LineCode ",
	"PROCESS_ID:ProcessId",
	"PRODUCT_ID:ProductId",
	"AREA_CODE :AreaCode ",
	"STEP_ID   :StepId   ",
	"EQP_ID    :EqpId    ",
	"GLASS_ID  :GlassId  ",
	"ITEM_ID   :ItemId   ",
	"SUBITEM_ID:SubItemId",
	"DATAVALUE :DataValue",
	"DCOLTIME  :DcolTime ",
	"SPEC_UCL  :SpecUcl  ",
	"SPEC_LCL  :SpecLcl  ",
	"SPEC_TGT  :SpecTgt  ",
	*/

	private String lineCode ;
	private String processId;
	private String productId;
	private String areaCode ;
	private String stepId   ;
	private String eqpId    ;
	private String glassId  ;
	private String itemId   ;
	private String subItemId;
	private String dataValue;
	private String dcolTime ;
	private String specUcl  ;
	private String specLcl  ;
	private String specTgt  ;

	public String toString()
	{
		return String.format("READ : [%s] [%s] [%s] [%s] [%s] [%s] [%s] [%s] [%s] [%s] [%s] [%s] [%s] [%s]"
			, lineCode 
			, processId
			, productId
			, areaCode 
			, stepId   
			, eqpId    
			, glassId  
			, itemId   
			, subItemId
			, dataValue
			, dcolTime 
			, specUcl  
			, specLcl  
			, specTgt  
		);
	}

	public static String[] getHeader()
	{
		List<String> list = new ArrayList<String>();
		list.add("LINE_CODE" );
		list.add("PROCESS_ID");
		list.add("PRODUCT_ID");
		list.add("AREA_CODE" );
		list.add("STEP_ID"   );
		list.add("EQP_ID"    );
		list.add("GLASS_ID"  );
		list.add("ITEM_ID"   );
		list.add("SUBITEM_ID");
		list.add("DATAVALUE" );
		list.add("DCOLTIME"  );
		list.add("SPEC_UCL"  );
		list.add("SPEC_LCL"  );
		list.add("SPEC_TGT"  );

		return list.toArray(new String[list.size()]);
	}

	public String[] getStringArray()
	{
		List<String> list = new ArrayList<String>();
		list.add(lineCode );
		list.add(processId);
		list.add(productId);
		list.add(areaCode );
		list.add(stepId   );
		list.add(eqpId    );
		list.add(glassId  );
		list.add(itemId   );
		list.add(subItemId);
		list.add(dataValue);
		list.add(dcolTime );
		list.add(specUcl  );
		list.add(specLcl  );
		list.add(specTgt  );

		return list.toArray(new String[list.size()]);
	}

	/**
	 * @return the lineCode
	 */
	public String getLineCode()
	{
		return lineCode;
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
	 * @return the areaCode
	 */
	public String getAreaCode()
	{
		return areaCode;
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
	 * @return the glassId
	 */
	public String getGlassId()
	{
		return glassId;
	}

	/**
	 * @return the itemId
	 */
	public String getItemId()
	{
		return itemId;
	}

	/**
	 * @return the subItemId
	 */
	public String getSubItemId()
	{
		return subItemId;
	}

	/**
	 * @return the dataValue
	 */
	public String getDataValue()
	{
		return dataValue;
	}

	/**
	 * @return the dcolTime
	 */
	public String getDcolTime()
	{
		return dcolTime;
	}

	/**
	 * @return the specUcl
	 */
	public String getSpecUcl()
	{
		return specUcl;
	}

	/**
	 * @return the specLcl
	 */
	public String getSpecLcl()
	{
		return specLcl;
	}

	/**
	 * @return the specTgt
	 */
	public String getSpecTgt()
	{
		return specTgt;
	}

	/**
	 * @param lineCode the lineCode to set
	 */
	public void setLineCode(String lineCode)
	{
		this.lineCode = lineCode;
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
	 * @param areaCode the areaCode to set
	 */
	public void setAreaCode(String areaCode)
	{
		this.areaCode = areaCode;
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
	 * @param glassId the glassId to set
	 */
	public void setGlassId(String glassId)
	{
		this.glassId = glassId;
	}

	/**
	 * @param itemId the itemId to set
	 */
	public void setItemId(String itemId)
	{
		this.itemId = itemId;
	}

	/**
	 * @param subItemId the subItemId to set
	 */
	public void setSubItemId(String subItemId)
	{
		this.subItemId = subItemId;
	}

	/**
	 * @param dataValue the dataValue to set
	 */
	public void setDataValue(String dataValue)
	{
		this.dataValue = dataValue;
	}

	/**
	 * @param dcolTime the dcolTime to set
	 */
	public void setDcolTime(String dcolTime)
	{
		this.dcolTime = dcolTime;
	}

	/**
	 * @param specUcl the specUcl to set
	 */
	public void setSpecUcl(String specUcl)
	{
		this.specUcl = specUcl;
	}

	/**
	 * @param specLcl the specLcl to set
	 */
	public void setSpecLcl(String specLcl)
	{
		this.specLcl = specLcl;
	}

	/**
	 * @param specTgt the specTgt to set
	 */
	public void setSpecTgt(String specTgt)
	{
		this.specTgt = specTgt;
	}
}
