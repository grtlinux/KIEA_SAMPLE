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

package kiea.proj.sdc.anal.macro.sample.a12.aib.macro.AIB_DCOL_BASE_H.v01;

import java.util.ArrayList;
import java.util.List;

import kiea.proj.sdc.anal.macro.impl.bean.AbstractBean;

/**
 * @author KangSeok
 * @date 2014. 8. 8.
 * @file_name AIB_DCOL_BASE_H_ReadBean.java
 *
 */
public class AIB_DCOL_BASE_H_ReadBean extends AbstractBean
{
	/*
	"LINE_CODE :LineCode ",
	"GLASS_ID  :GlassId  ",
	"PROCESS_ID:ProcessId",
	"PRODUCT_ID:ProductId",
	"STEP_ID   :StepId   ",
	"EQP_ID    :EqpId    ",
	"EQPUNIT_ID:EqpUnitId",
	"ITEM_ID   :ItemId   ",
	"SUBITEM_ID:SubItemId",
	"DATAVALUE :DataValue",
	"DCOLTIME  :DcolTime ",
	*/

	private String lineCode ;
	private String glassId  ;
	private String processId;
	private String productId;
	private String stepId   ;
	private String eqpId    ;
	private String eqpUnitId;
	private String itemId   ;
	private String subItemId;
	private String dataValue;
	private String dcolTime ;

	public String toString()
	{
		return String.format("READ : [%s] [%s] [%s] [%s] [%s] [%s] [%s] [%s] [%s] [%s] [%s]"
			, lineCode 
			, glassId  
			, processId
			, productId
			, stepId   
			, eqpId    
			, eqpUnitId
			, itemId   
			, subItemId
			, dataValue
			, dcolTime 
		);
	}

	public static String[] getHeader()
	{
		List<String> list = new ArrayList<String>();
		list.add("LINE_CODE" );
		list.add("GLASS_ID"  );
		list.add("PROCESS_ID");
		list.add("PRODUCT_ID");
		list.add("STEP_ID"   );
		list.add("EQP_ID"    );
		list.add("EQPUNIT_ID");
		list.add("ITEM_ID"   );
		list.add("SUBITEM_ID");
		list.add("DATAVALUE" );
		list.add("DCOLTIME"  );

		return list.toArray(new String[list.size()]);
	}

	public String[] getStringArray()
	{
		List<String> list = new ArrayList<String>();
		list.add(lineCode );
		list.add(glassId  );
		list.add(processId);
		list.add(productId);
		list.add(stepId   );
		list.add(eqpId    );
		list.add(eqpUnitId);
		list.add(itemId   );
		list.add(subItemId);
		list.add(dataValue);
		list.add(dcolTime );

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
	 * @return the glassId
	 */
	public String getGlassId()
	{
		return glassId;
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
	 * @return the eqpUnitId
	 */
	public String getEqpUnitId()
	{
		return eqpUnitId;
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
	 * @param lineCode the lineCode to set
	 */
	public void setLineCode(String lineCode)
	{
		this.lineCode = lineCode;
	}

	/**
	 * @param glassId the glassId to set
	 */
	public void setGlassId(String glassId)
	{
		this.glassId = glassId;
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
	 * @param eqpUnitId the eqpUnitId to set
	 */
	public void setEqpUnitId(String eqpUnitId)
	{
		this.eqpUnitId = eqpUnitId;
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
}
