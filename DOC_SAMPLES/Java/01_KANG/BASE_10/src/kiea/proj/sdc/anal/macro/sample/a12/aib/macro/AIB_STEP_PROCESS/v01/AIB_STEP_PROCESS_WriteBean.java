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

package kiea.proj.sdc.anal.macro.sample.a12.aib.macro.AIB_STEP_PROCESS.v01;

import java.util.ArrayList;
import java.util.List;

import kiea.proj.sdc.anal.macro.impl.bean.AbstractBean;

/**
 * @author KangSeok
 * @date 2014. 8. 8.
 * @file_name AIB_STEP_PROCESS_WriteBean.java
 *
 */
public class AIB_STEP_PROCESS_WriteBean extends AbstractBean
{
	/*
	"DIV_CODE      :DivCode      ",
	"LINE_CODE     :LineCode     ",
	"STEP_ID       :StepId       ",
	"SUBSTEP_ID    :SubStepId    ",
	"EQPUNITSLOT_ID:EqpUnitSlotId",
	"USE_YN        :UseYn        ",
	"REGISTER_DATE :RegisterDate ",
	*/

	private String divCode      ;
	private String lineCode     ;
	private String stepId       ;
	private String subStepId    ;
	private String eqpUnitSlotId;
	private String useYn        ;
	private String registerDate ;

	public String toString()
	{
		return String.format("READ : [%s] [%s] [%s] [%s] [%s] [%s] [%s]"
			, divCode      
			, lineCode     
			, stepId       
			, subStepId    
			, eqpUnitSlotId
			, useYn        
			, registerDate 
		);
	}

	public static String[] getHeader()
	{
		List<String> list = new ArrayList<String>();
		list.add("DIV_CODE"      );
		list.add("LINE_CODE"     );
		list.add("STEP_ID"       );
		list.add("SUBSTEP_ID"    );
		list.add("EQPUNITSLOT_ID");
		list.add("USE_YN"        );
		list.add("REGISTER_DATE" );

		return list.toArray(new String[list.size()]);
	}

	public String[] getStringArray()
	{
		List<String> list = new ArrayList<String>();
		list.add(divCode      );
		list.add(lineCode     );
		list.add(stepId       );
		list.add(subStepId    );
		list.add(eqpUnitSlotId);
		list.add(useYn        );
		list.add(registerDate );

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
	 * @return the lineCode
	 */
	public String getLineCode()
	{
		return lineCode;
	}

	/**
	 * @return the stepId
	 */
	public String getStepId()
	{
		return stepId;
	}

	/**
	 * @return the subStepId
	 */
	public String getSubStepId()
	{
		return subStepId;
	}

	/**
	 * @return the eqpUnitSlotId
	 */
	public String getEqpUnitSlotId()
	{
		return eqpUnitSlotId;
	}

	/**
	 * @return the useYn
	 */
	public String getUseYn()
	{
		return useYn;
	}

	/**
	 * @return the registerDate
	 */
	public String getRegisterDate()
	{
		return registerDate;
	}

	/**
	 * @param divCode the divCode to set
	 */
	public void setDivCode(String divCode)
	{
		this.divCode = divCode;
	}

	/**
	 * @param lineCode the lineCode to set
	 */
	public void setLineCode(String lineCode)
	{
		this.lineCode = lineCode;
	}

	/**
	 * @param stepId the stepId to set
	 */
	public void setStepId(String stepId)
	{
		this.stepId = stepId;
	}

	/**
	 * @param subStepId the subStepId to set
	 */
	public void setSubStepId(String subStepId)
	{
		this.subStepId = subStepId;
	}

	/**
	 * @param eqpUnitSlotId the eqpUnitSlotId to set
	 */
	public void setEqpUnitSlotId(String eqpUnitSlotId)
	{
		this.eqpUnitSlotId = eqpUnitSlotId;
	}

	/**
	 * @param useYn the useYn to set
	 */
	public void setUseYn(String useYn)
	{
		this.useYn = useYn;
	}

	/**
	 * @param registerDate the registerDate to set
	 */
	public void setRegisterDate(String registerDate)
	{
		this.registerDate = registerDate;
	}
}
