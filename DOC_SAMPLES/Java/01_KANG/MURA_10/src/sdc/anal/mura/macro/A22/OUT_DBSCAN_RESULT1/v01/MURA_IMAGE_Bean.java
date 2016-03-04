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

package sdc.anal.mura.macro.A22.OUT_DBSCAN_RESULT1.v01;

import java.util.ArrayList;
import java.util.List;

import kiea.proj.sdc.anal.macro.impl.bean.AbstractBean;

/**
 * @author KangSeok
 * @date 2014. 8. 8.
 * @file_name FINAL_CLUSTER_Bean.java
 *
 */
public class MURA_IMAGE_Bean extends AbstractBean
{
	/*
	"SITEID         :SiteId        ",
	"PROCESSID      :ProcessId     ",
	"PRODUCTID      :ProductId     ",
	"STEPID         :StepId        ",
	"MODULETYPE     :ModuleType    ",
	"EQPID          :EqpId         ",
	"BATCHID        :BatchId       ",
	"GLASSCELLID    :GlassCellId   ",
	"ETIME          :ETime         ",
	"IMAGEPATH      :ImagePath     ",
	"IMAGEPATH_RED  :ImagePathRed  ",
	"IMAGEPATH_GREEN:ImagePathGreen",
	"IMAGEPATH_BLUE :ImagePathBlue ",
	*/

	private String siteId        ;
	private String processId     ;
	private String productId     ;
	private String stepId        ;
	private String moduleType    ;
	private String eqpId         ;
	private String batchId       ;
	private String glassCellId   ;
	private String ETime         ;
	private String imagePath     ;
	private String imagePathRed  ;
	private String imagePathGreen;
	private String imagePathBlue ;

	public String toString()
	{
		return String.format("READ : [%s] [%s] [%s] [%s] [%s] [%s] [%s] [%s] [%s] [%s] [%s] [%s] [%s]"
			, siteId        
			, processId     
			, productId     
			, stepId        
			, moduleType    
			, eqpId         
			, batchId       
			, glassCellId   
			, ETime         
			, imagePath     
			, imagePathRed  
			, imagePathGreen
			, imagePathBlue 
		);
	}

	public static String[] getHeader()
	{
		List<String> list = new ArrayList<String>();
		list.add("SITEID"         );
		list.add("PROCESSID"      );
		list.add("PRODUCTID"      );
		list.add("STEPID"         );
		list.add("MODULETYPE"     );
		list.add("EQPID"          );
		list.add("BATCHID"        );
		list.add("GLASSCELLID"    );
		list.add("ETIME"          );
		list.add("IMAGEPATH"      );
		list.add("IMAGEPATH_RED"  );
		list.add("IMAGEPATH_GREEN");
		list.add("IMAGEPATH_BLUE" );

		return list.toArray(new String[list.size()]);
	}

	public String[] getStringArray()
	{
		List<String> list = new ArrayList<String>();
		list.add(siteId        );
		list.add(processId     );
		list.add(productId     );
		list.add(stepId        );
		list.add(moduleType    );
		list.add(eqpId         );
		list.add(batchId       );
		list.add(glassCellId   );
		list.add(ETime         );
		list.add(imagePath     );
		list.add(imagePathRed  );
		list.add(imagePathGreen);
		list.add(imagePathBlue );

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
	 * @return the moduleType
	 */
	public String getModuleType()
	{
		return moduleType;
	}

	/**
	 * @return the eqpId
	 */
	public String getEqpId()
	{
		return eqpId;
	}

	/**
	 * @return the batchId
	 */
	public String getBatchId()
	{
		return batchId;
	}

	/**
	 * @return the glassCellId
	 */
	public String getGlassCellId()
	{
		return glassCellId;
	}

	/**
	 * @return the eTime
	 */
	public String getETime()
	{
		return ETime;
	}

	/**
	 * @return the imagePath
	 */
	public String getImagePath()
	{
		return imagePath;
	}

	/**
	 * @return the imagePathRed
	 */
	public String getImagePathRed()
	{
		return imagePathRed;
	}

	/**
	 * @return the imagePathGreen
	 */
	public String getImagePathGreen()
	{
		return imagePathGreen;
	}

	/**
	 * @return the imagePathBlue
	 */
	public String getImagePathBlue()
	{
		return imagePathBlue;
	}

	/**
	 * @param siteId the siteId to set
	 */
	public void setSiteId(String siteId)
	{
		this.siteId = siteId;
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
	 * @param moduleType the moduleType to set
	 */
	public void setModuleType(String moduleType)
	{
		this.moduleType = moduleType;
	}

	/**
	 * @param eqpId the eqpId to set
	 */
	public void setEqpId(String eqpId)
	{
		this.eqpId = eqpId;
	}

	/**
	 * @param batchId the batchId to set
	 */
	public void setBatchId(String batchId)
	{
		this.batchId = batchId;
	}

	/**
	 * @param glassCellId the glassCellId to set
	 */
	public void setGlassCellId(String glassCellId)
	{
		this.glassCellId = glassCellId;
	}

	/**
	 * @param eTime the eTime to set
	 */
	public void setETime(String eTime)
	{
		ETime = eTime;
	}

	/**
	 * @param imagePath the imagePath to set
	 */
	public void setImagePath(String imagePath)
	{
		this.imagePath = imagePath;
	}

	/**
	 * @param imagePathRed the imagePathRed to set
	 */
	public void setImagePathRed(String imagePathRed)
	{
		this.imagePathRed = imagePathRed;
	}

	/**
	 * @param imagePathGreen the imagePathGreen to set
	 */
	public void setImagePathGreen(String imagePathGreen)
	{
		this.imagePathGreen = imagePathGreen;
	}

	/**
	 * @param imagePathBlue the imagePathBlue to set
	 */
	public void setImagePathBlue(String imagePathBlue)
	{
		this.imagePathBlue = imagePathBlue;
	}
}
