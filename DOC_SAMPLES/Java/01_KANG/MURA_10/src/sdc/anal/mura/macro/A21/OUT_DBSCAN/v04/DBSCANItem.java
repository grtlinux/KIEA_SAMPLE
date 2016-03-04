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

package sdc.anal.mura.macro.A21.OUT_DBSCAN.v04;

import kiea.kr.co.tain.base.Flag;

import org.apache.commons.math3.ml.clustering.DoublePoint;

/**
 * @author KangSeok
 * @date 2014. 8. 16.
 * @file_name DBSCANItem.java
 *
 */
public class DBSCANItem
{
	private DoublePoint point;
	private double pointX;
	private double pointY;
	private int keyNo;
	private int clusterId;
	
	/**
	 * constructor
	 * 
	 * @param point
	 * @param keyNo
	 */
	public DBSCANItem(DoublePoint point, int keyNo)
	{
		this.point = point;
		this.pointX = point.getPoint()[0];
		this.pointY = point.getPoint()[1];
		this.keyNo = keyNo;
		this.clusterId = 0;
	}
	
	/**
	 * constructor
	 * 
	 * @param pointX
	 * @param pointY
	 * @param keyNo
	 */
	public DBSCANItem(int pointX, int pointY, int keyNo)
	{
		this((double) pointX, (double) pointY, keyNo);
	}
	
	/**
	 * constructor
	 * 
	 * @param pointX
	 * @param pointY
	 * @param keyNo
	 */
	public DBSCANItem(double pointX, double pointY, int keyNo)
	{
		this(new DoublePoint(new double[] { pointX, pointY }), keyNo);
		
		if (!Flag.TRUE) {
			this.pointX = pointX;
			this.pointY = pointY;
			this.point = new DoublePoint(new double[] { this.pointX, this.pointY });
			this.keyNo = keyNo;
			this.clusterId = 0;
		}
	}
	
	/**
	 * toString
	 */
	public String toString()
	{
		return String.format("(%f, %f) [NO:%d] [CID:%d]", this.pointX, this.pointY, this.keyNo, this.clusterId);
	}

	///////////////////////////////////////////////////////////////////////////
	
	/**
	 * @return the point
	 */
	public DoublePoint getPoint()
	{
		return point;
	}

	/**
	 * @return the pointX
	 */
	public double getPointX()
	{
		return pointX;
	}

	/**
	 * @return the pointY
	 */
	public double getPointY()
	{
		return pointY;
	}

	/**
	 * @return the keyNo
	 */
	public int getKeyNo()
	{
		return keyNo;
	}

	/**
	 * @return the clusterNo
	 */
	public int getClusterId()
	{
		return clusterId;
	}

	/**
	 * @param point the point to set
	 */
	public void setPoint(DoublePoint point)
	{
		this.point = point;
	}

	/**
	 * @param pointX the pointX to set
	 */
	public void setPointX(double pointX)
	{
		this.pointX = pointX;
	}

	/**
	 * @param pointY the pointY to set
	 */
	public void setPointY(double pointY)
	{
		this.pointY = pointY;
	}

	/**
	 * @param keyNo the keyNo to set
	 */
	public void setKeyNo(int keyNo)
	{
		this.keyNo = keyNo;
	}

	/**
	 * @param clusterNo the clusterNo to set
	 */
	public void setClusterId(int clusterId)
	{
		this.clusterId = clusterId;
	}
}
