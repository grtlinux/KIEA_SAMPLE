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

package kiea.proj.sdc.anal.macro.sample.a11.img.macro.BUBBLE_CLUSTER_CELLLOC;

/**
 * @author KangSeok
 * @date 2014. 8. 25.
 * @file_name aaa.java
 *
 */
public final class Imsi
{
	private String clusterId;
	private String cellLocId;
	private String pointX;
	private String pointY;
	private String avgMura;
	
	public String toString()
	{
		return String.format("[%s] [%s] [%s] [%s] [%s]", clusterId, cellLocId, pointX, pointY, avgMura);
	}
	
	/**
	 * @return the clusterId
	 */
	public String getClusterId()
	{
		return clusterId;
	}
	/**
	 * @return the cellLocId
	 */
	public String getCellLocId()
	{
		return cellLocId;
	}
	/**
	 * @return the pointX
	 */
	public String getPointX()
	{
		return pointX;
	}
	/**
	 * @return the pointY
	 */
	public String getPointY()
	{
		return pointY;
	}
	/**
	 * @return the avgMura
	 */
	public String getAvgMura()
	{
		return avgMura;
	}
	/**
	 * @param clusterId the clusterId to set
	 */
	public void setClusterId(String clusterId)
	{
		this.clusterId = clusterId;
	}
	/**
	 * @param cellLocId the cellLocId to set
	 */
	public void setCellLocId(String cellLocId)
	{
		this.cellLocId = cellLocId;
	}
	/**
	 * @param pointX the pointX to set
	 */
	public void setPointX(String pointX)
	{
		this.pointX = pointX;
	}
	/**
	 * @param pointY the pointY to set
	 */
	public void setPointY(String pointY)
	{
		this.pointY = pointY;
	}
	/**
	 * @param avgMura the avgMura to set
	 */
	public void setAvgMura(String avgMura)
	{
		this.avgMura = avgMura;
	}
}
