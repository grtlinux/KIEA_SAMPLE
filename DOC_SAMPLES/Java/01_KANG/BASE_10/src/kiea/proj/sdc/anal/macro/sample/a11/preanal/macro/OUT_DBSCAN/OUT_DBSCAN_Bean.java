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

package kiea.proj.sdc.anal.macro.sample.a11.preanal.macro.OUT_DBSCAN;

import java.util.ArrayList;
import java.util.List;

import kiea.proj.sdc.anal.macro.impl.bean.AbstractBean;

/**
 * @author KangSeok
 * @date 2014. 8. 8.
 * @file_name OUT_DBSCAN_Bean.java
 *
 */
public class OUT_DBSCAN_Bean extends AbstractBean
{
	private String dataNum  ;
	private String avgX     ;
	private String avgY     ;
	private String clusterId;

	public String toString()
	{
		return String.format("READ : [%s] [%s] [%s] [%s]"
			, dataNum  
			, avgX     
			, avgY     
			, clusterId
		);
	}

	public static String[] getHeader()
	{
		List<String> list = new ArrayList<String>();
		list.add("DATA_NUM"  );
		list.add("AVG_X"     );
		list.add("AVG_Y"     );
		list.add("CLUSTER_ID");

		return list.toArray(new String[list.size()]);
	}

	public String[] getStringArray()
	{
		List<String> list = new ArrayList<String>();
		list.add(dataNum  );
		list.add(avgX     );
		list.add(avgY     );
		list.add(clusterId);

		return list.toArray(new String[list.size()]);
	}

	/**
	 * @return the dataNum
	 */
	public String getDataNum()
	{
		return dataNum;
	}

	/**
	 * @return the avgX
	 */
	public String getAvgX()
	{
		return avgX;
	}

	/**
	 * @return the avgY
	 */
	public String getAvgY()
	{
		return avgY;
	}

	/**
	 * @return the clusterId
	 */
	public String getClusterId()
	{
		return clusterId;
	}

	/**
	 * @param dataNum the dataNum to set
	 */
	public void setDataNum(String dataNum)
	{
		this.dataNum = dataNum;
	}

	/**
	 * @param avgX the avgX to set
	 */
	public void setAvgX(String avgX)
	{
		this.avgX = avgX;
	}

	/**
	 * @param avgY the avgY to set
	 */
	public void setAvgY(String avgY)
	{
		this.avgY = avgY;
	}

	/**
	 * @param clusterId the clusterId to set
	 */
	public void setClusterId(String clusterId)
	{
		this.clusterId = clusterId;
	}
}
