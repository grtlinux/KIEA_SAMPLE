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

package sdc.anal.lya.macro.A11.MATCHING_WEIGHT.v04;

import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;

import kiea.proj.sdc.anal.macro.impl.bean.AbstractBean;

/**
 * @author KangSeok
 * @date 2014. 8. 8.
 * @file_name AIB_MATCHING_WEIGHT_WriteBean.java
 *
 */
public class MATCHING_WEIGHT_WriteBean extends AbstractBean
{
	/*
	"SITEID               :SiteId            ",
	"DEFECTCD             :DefectCd          ",
	"DETECT_TOP_STEP_ID   :DetectTopStepId   ",
	"DETECT_BOTTOM_STEP_ID:DetectBottomStepId",
	"APPL_TOP_STEP_ID     :ApplTopStepId     ",
	"APPL_BOTTOM_STEP_ID  :ApplBottomStepId  ",
	"WEIGHT               :Weight            ",
	"USE_YN               :UseYn             ",
	"REGISTER_DATE        :RegisterDate      ",
	*/

	private String siteId            ;
	private String defectCd          ;
	private String detectTopStepId   ;
	private String detectBottomStepId;
	private String applTopStepId     ;
	private String applBottomStepId  ;
	private String weight            ;
	private String useYn             ;
	private String registerDate      ;

	///////////////////////////////////////////////////////////////////////////

	private byte[] bytes = null;
	private String title = null;
	private String str   = null;

	public void setBytes(byte[] bytes)
	{
		ByteBuffer buf = ByteBuffer.allocateDirect(10 * 1024);

		buf.clear();
		if (siteId             == null) buf.put((byte)','); else buf.put((byte)'"').put(siteId            .getBytes()).put((byte)'"').put((byte)',');
		if (defectCd           == null) buf.put((byte)','); else buf.put((byte)'"').put(defectCd          .getBytes()).put((byte)'"').put((byte)',');
		if (detectTopStepId    == null) buf.put((byte)','); else buf.put((byte)'"').put(detectTopStepId   .getBytes()).put((byte)'"').put((byte)',');
		if (detectBottomStepId == null) buf.put((byte)','); else buf.put((byte)'"').put(detectBottomStepId.getBytes()).put((byte)'"').put((byte)',');
		if (applTopStepId      == null) buf.put((byte)','); else buf.put((byte)'"').put(applTopStepId     .getBytes()).put((byte)'"').put((byte)',');
		if (applBottomStepId   == null) buf.put((byte)','); else buf.put((byte)'"').put(applBottomStepId  .getBytes()).put((byte)'"').put((byte)',');
		if (weight             == null) buf.put((byte)','); else buf.put((byte)'"').put(weight            .getBytes()).put((byte)'"').put((byte)',');
		if (useYn              == null) buf.put((byte)','); else buf.put((byte)'"').put(useYn             .getBytes()).put((byte)'"').put((byte)',');
		if (registerDate       == null) buf.put((byte)'\n'); else buf.put((byte)'"').put(registerDate      .getBytes()).put((byte)'"').put((byte)'\n');

		buf.flip();
		this.bytes = new byte[buf.limit()];
		buf.get(this.bytes);
	}

	public byte[] getBytes()
	{
		if (this.bytes == null)
			setBytes(null);

		return this.bytes;
	}

	public void setTitle(String title)
	{
		ByteBuffer buf = ByteBuffer.allocateDirect(1024);

		buf.clear();
		buf.put((byte)'"').put("SITEID"               .getBytes()).put((byte)'"').put((byte)',');
		buf.put((byte)'"').put("DEFECTCD"             .getBytes()).put((byte)'"').put((byte)',');
		buf.put((byte)'"').put("DETECT_TOP_STEP_ID"   .getBytes()).put((byte)'"').put((byte)',');
		buf.put((byte)'"').put("DETECT_BOTTOM_STEP_ID".getBytes()).put((byte)'"').put((byte)',');
		buf.put((byte)'"').put("APPL_TOP_STEP_ID"     .getBytes()).put((byte)'"').put((byte)',');
		buf.put((byte)'"').put("APPL_BOTTOM_STEP_ID"  .getBytes()).put((byte)'"').put((byte)',');
		buf.put((byte)'"').put("WEIGHT"               .getBytes()).put((byte)'"').put((byte)',');
		buf.put((byte)'"').put("USE_YN"               .getBytes()).put((byte)'"').put((byte)',');
		buf.put((byte)'"').put("REGISTER_DATE"        .getBytes()).put((byte)'"').put((byte)'\n');

		buf.flip();
		byte[] bytes = new byte[buf.limit()];
		buf.get(bytes);

		this.title = new String(bytes);
	}

	public String getTitle()
	{
		if (this.title == null)
			setTitle(null);

		return this.title;
	}

	public void setStr(String str)
	{
		this.str = new String(getBytes());
	}

	public String getStr()
	{
		if (this.str == null)
			setStr(null);

		return this.str;
	}


	///////////////////////////////////////////////////////////////////////////

	public String toString()
	{
		return String.format("READ : [%s] [%s] [%s] [%s] [%s] [%s] [%s] [%s] [%s]"
			, siteId            
			, defectCd          
			, detectTopStepId   
			, detectBottomStepId
			, applTopStepId     
			, applBottomStepId  
			, weight            
			, useYn             
			, registerDate      
		);
	}

	public static String[] getHeader()
	{
		List<String> list = new ArrayList<String>();
		list.add("SITEID"               );
		list.add("DEFECTCD"             );
		list.add("DETECT_TOP_STEP_ID"   );
		list.add("DETECT_BOTTOM_STEP_ID");
		list.add("APPL_TOP_STEP_ID"     );
		list.add("APPL_BOTTOM_STEP_ID"  );
		list.add("WEIGHT"               );
		list.add("USE_YN"               );
		list.add("REGISTER_DATE"        );

		return list.toArray(new String[list.size()]);
	}

	public String[] getStringArray()
	{
		List<String> list = new ArrayList<String>();
		list.add(siteId            );
		list.add(defectCd          );
		list.add(detectTopStepId   );
		list.add(detectBottomStepId);
		list.add(applTopStepId     );
		list.add(applBottomStepId  );
		list.add(weight            );
		list.add(useYn             );
		list.add(registerDate      );

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

	public String getDetectTopStepId()
	{
		return detectTopStepId;
	}

	public void setDetectTopStepId(String detectTopStepId)
	{
		this.detectTopStepId = detectTopStepId;
	}

	public String getDetectBottomStepId()
	{
		return detectBottomStepId;
	}

	public void setDetectBottomStepId(String detectBottomStepId)
	{
		this.detectBottomStepId = detectBottomStepId;
	}

	public String getApplTopStepId()
	{
		return applTopStepId;
	}

	public void setApplTopStepId(String applTopStepId)
	{
		this.applTopStepId = applTopStepId;
	}

	public String getApplBottomStepId()
	{
		return applBottomStepId;
	}

	public void setApplBottomStepId(String applBottomStepId)
	{
		this.applBottomStepId = applBottomStepId;
	}

	public String getWeight()
	{
		return weight;
	}

	public void setWeight(String weight)
	{
		this.weight = weight;
	}

	public String getUseYn()
	{
		return useYn;
	}

	public void setUseYn(String useYn)
	{
		this.useYn = useYn;
	}

	public String getRegisterDate()
	{
		return registerDate;
	}

	public void setRegisterDate(String registerDate)
	{
		this.registerDate = registerDate;
	}
}
