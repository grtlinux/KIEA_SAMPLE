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

package sdc.anal.lya.macro.A23.MOD_RJ.v04;

import java.util.ArrayList;
import java.util.List;

import kiea.proj.sdc.anal.macro.impl.bean.AbstractBean;

/**
 * @author KangSeok
 * @date 2014. 8. 8.
 * @file_name MOD_RJ_ReadBean.java
 *
 */
public class MOD_RJ_ReadBean extends AbstractBean
{
	/*
	"CELL_ID     :CellId      ",
	"INSPSTEPTYPE:InspStepType",
	"DEFECTNAME  :DefectName  ",
	"DEFECTCODE  :DefectCode  ",
	"INSPSTEPID  :InspStepId  ",
	"INSPTIME    :InspTime    ",
	"WORKERNAME  :WorkerName  ",
	"WORKERID    :WorkerId    ",
	"INSPEQPID   :InspEqpId   ",
	*/

	private String cellId      ;
	private String inspStepType;
	private String defectName  ;
	private String defectCode  ;
	private String inspStepId  ;
	private String inspTime    ;
	private String workerName  ;
	private String workerId    ;
	private String inspEqpId   ;

	public String toString()
	{
		return String.format("READ : [%s] [%s] [%s] [%s] [%s] [%s] [%s] [%s] [%s]"
			, cellId      
			, inspStepType
			, defectName  
			, defectCode  
			, inspStepId  
			, inspTime    
			, workerName  
			, workerId    
			, inspEqpId   
		);
	}

	public static String[] getHeader()
	{
		List<String> list = new ArrayList<String>();
		list.add("CELL_ID"     );
		list.add("INSPSTEPTYPE");
		list.add("DEFECTNAME"  );
		list.add("DEFECTCODE"  );
		list.add("INSPSTEPID"  );
		list.add("INSPTIME"    );
		list.add("WORKERNAME"  );
		list.add("WORKERID"    );
		list.add("INSPEQPID"   );

		return list.toArray(new String[list.size()]);
	}

	public String[] getStringArray()
	{
		List<String> list = new ArrayList<String>();
		list.add(cellId      );
		list.add(inspStepType);
		list.add(defectName  );
		list.add(defectCode  );
		list.add(inspStepId  );
		list.add(inspTime    );
		list.add(workerName  );
		list.add(workerId    );
		list.add(inspEqpId   );

		return list.toArray(new String[list.size()]);
	}

	/**
	 * @return the cellId
	 */
	public String getCellId()
	{
		return cellId;
	}

	/**
	 * @return the inspStepType
	 */
	public String getInspStepType()
	{
		return inspStepType;
	}

	/**
	 * @return the defectName
	 */
	public String getDefectName()
	{
		return defectName;
	}

	/**
	 * @return the defectCode
	 */
	public String getDefectCode()
	{
		return defectCode;
	}

	/**
	 * @return the inspStepId
	 */
	public String getInspStepId()
	{
		return inspStepId;
	}

	/**
	 * @return the inspTime
	 */
	public String getInspTime()
	{
		return inspTime;
	}

	/**
	 * @return the workerName
	 */
	public String getWorkerName()
	{
		return workerName;
	}

	/**
	 * @return the workerId
	 */
	public String getWorkerId()
	{
		return workerId;
	}

	/**
	 * @return the inspEqpId
	 */
	public String getInspEqpId()
	{
		return inspEqpId;
	}

	/**
	 * @param cellId the cellId to set
	 */
	public void setCellId(String cellId)
	{
		this.cellId = cellId;
	}

	/**
	 * @param inspStepType the inspStepType to set
	 */
	public void setInspStepType(String inspStepType)
	{
		this.inspStepType = inspStepType;
	}

	/**
	 * @param defectName the defectName to set
	 */
	public void setDefectName(String defectName)
	{
		this.defectName = defectName;
	}

	/**
	 * @param defectCode the defectCode to set
	 */
	public void setDefectCode(String defectCode)
	{
		this.defectCode = defectCode;
	}

	/**
	 * @param inspStepId the inspStepId to set
	 */
	public void setInspStepId(String inspStepId)
	{
		this.inspStepId = inspStepId;
	}

	/**
	 * @param inspTime the inspTime to set
	 */
	public void setInspTime(String inspTime)
	{
		this.inspTime = inspTime;
	}

	/**
	 * @param workerName the workerName to set
	 */
	public void setWorkerName(String workerName)
	{
		this.workerName = workerName;
	}

	/**
	 * @param workerId the workerId to set
	 */
	public void setWorkerId(String workerId)
	{
		this.workerId = workerId;
	}

	/**
	 * @param inspEqpId the inspEqpId to set
	 */
	public void setInspEqpId(String inspEqpId)
	{
		this.inspEqpId = inspEqpId;
	}
}
