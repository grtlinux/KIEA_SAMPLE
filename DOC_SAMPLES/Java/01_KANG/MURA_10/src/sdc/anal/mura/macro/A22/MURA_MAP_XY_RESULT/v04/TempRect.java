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

package sdc.anal.mura.macro.A22.MURA_MAP_XY_RESULT.v04;

/**
 * @author KangSeok
 * @date 2014. 8. 25.
 * @file_name TempRect.java
 *
 */
public class TempRect
{
	private double x1;
	private double y1;
	
	private double x2;
	private double y2;
	
	public TempRect(double x1, double y1, double x2, double y2)
	{
		this.x1 = Math.min(x1, x2);
		this.y1 = Math.min(y1, y2);
		this.x2 = Math.max(x1, x2);
		this.y2 = Math.max(y1, y2);
	}
	
	public TempRect(double[] p)
	{
		this.x1 = Math.min(p[0], p[2]);
		this.y1 = Math.min(p[1], p[3]);
		this.x2 = Math.max(p[0], p[2]);
		this.y2 = Math.max(p[1], p[3]);
	}

	/**
	 * @return the x1
	 */
	public double getX1()
	{
		return x1;
	}

	/**
	 * @return the y1
	 */
	public double getY1()
	{
		return y1;
	}

	/**
	 * @return the x2
	 */
	public double getX2()
	{
		return x2;
	}

	/**
	 * @return the y2
	 */
	public double getY2()
	{
		return y2;
	}

	/**
	 * @param x1 the x1 to set
	 */
	public void setX1(double x1)
	{
		this.x1 = x1;
	}

	/**
	 * @param y1 the y1 to set
	 */
	public void setY1(double y1)
	{
		this.y1 = y1;
	}

	/**
	 * @param x2 the x2 to set
	 */
	public void setX2(double x2)
	{
		this.x2 = x2;
	}

	/**
	 * @param y2 the y2 to set
	 */
	public void setY2(double y2)
	{
		this.y2 = y2;
	}
}
