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
import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import kiea.kr.co.tain.base.Flag;

import org.apache.commons.math3.ml.clustering.Cluster;
import org.apache.commons.math3.ml.clustering.DBSCANClusterer;
import org.apache.commons.math3.ml.clustering.DoublePoint;

/**
 * @author KangSeok
 * @date 2014. 8. 16.
 * @file_name DBSCANObject.java
 *
 */
public class DBSCANObject
{
	private double eps = 0.0;
	private int minPts = 0;
	
	private DBSCANClusterer<DoublePoint> transformer = null;
	private List<Cluster<DoublePoint>> clusters = null;
	
	private int keyNo;
	private Map<Integer, DBSCANItem> map = null;   // (keyNo, DBSCANItem)
	private DoublePoint[] inPoints = null;

	/**
	 * constructor
	 */
	public DBSCANObject()
	{
		this(1.0, 3);  // default values of eps, minPts
	}

	/**
	 * constructor
	 * 
	 * @param eps
	 * @param minPts
	 */
	public DBSCANObject(double eps, int minPts)
	{
		this.eps = eps;
		this.minPts = minPts;
		this.map = new LinkedHashMap<Integer, DBSCANItem>();
		this.keyNo = 0;
	}
	
	public void setEps(double eps)
	{
		this.eps = eps;
	}
	
	public double getEps()
	{
		return this.eps;
	}
	
	public void setMinPts(int minPts)
	{
		this.minPts = minPts;
	}
	
	public int getMinPts()
	{
		return this.minPts;
	}
	
	///////////////////////////////////////////////////////////////////////////
	
	public void addPoint(DoublePoint point)
	{
		DBSCANItem item = new DBSCANItem(point, keyNo);
		map.put(new Integer(keyNo), item);
		
		keyNo ++;
	}
	
	///////////////////////////////////////////////////////////////////////////

	/**
	 * 
	 * setTransformer
	 *
	 * @throws IllegalArgumentException
	 */
	private void setTransformer() throws IllegalArgumentException
	{
		if (Flag.TRUE) {
			if (this.eps <= 0.0 || this.minPts <= 0) {
				throw new IllegalArgumentException("ERROR : wrong arguments [eps:" + this.eps + "][minPts:" + this.minPts + "]");
			}
			
			this.transformer = new DBSCANClusterer<DoublePoint>(this.eps, this.minPts);
		}
	}
	
	/**
	 * 
	 * setPoints
	 *
	 * @throws IllegalArgumentException
	 */
	private void setPoints() throws IllegalArgumentException
	{
		if (Flag.TRUE) {
			/*
			 * map to array
			 */
			inPoints = new DoublePoint[map.size()];
			
			int idx = 0;
			for (DBSCANItem item : map.values()) {
				if (!Flag.TRUE) System.out.println(item);
				
				inPoints[idx] = item.getPoint();
				idx ++;
			}
			
			if (!Flag.TRUE) {
				/*
				 * 출력해본다.
				 */
				for (int i=0; i < inPoints.length; i++) {
					System.out.println(inPoints[i]);
				}
			}
		}
	}
	
	/**
	 * 
	 * setClusterNo
	 *
	 * @throws IllegalArgumentException
	 */
	private void setClusterNo() throws IllegalArgumentException
	{
		if (Flag.TRUE) {

			int clusterId = 1;
			
			for (Cluster<DoublePoint> cluster : clusters) {
				List<DoublePoint> outPoints = cluster.getPoints();
				
				for (DoublePoint point : outPoints) {
					
					for (DBSCANItem item : map.values()) {
						if (item.getClusterId() > 0)
							continue;
						
						if (item.getPoint() != point)
							continue;
						
						// set clusterId
						item.setClusterId(clusterId);

						if (!Flag.TRUE) System.out.println(point + " => " + item);
					}
				}
				
				clusterId ++;
			}
		}

		if (!Flag.TRUE) {

			int clusterId = 1;
			
			for (Cluster<DoublePoint> cluster : clusters) {
				List<DoublePoint> outPoints = cluster.getPoints();
				
				if (Flag.TRUE) System.out.println("\n[clusterId=" + clusterId + "] outPoints.size = " + outPoints.size());
				
				for (DoublePoint point : outPoints) {
					if (Flag.TRUE) System.out.println("[CID:" + clusterId + "] " + point);
				}
				
				clusterId ++;
			}
		}
	}
	
	/**
	 * 
	 * clustering
	 *
	 * @throws IllegalArgumentException
	 */
	public void clustering() throws IllegalArgumentException
	{
		setTransformer();
		
		setPoints();
		
		if (Flag.TRUE) {
			/*
			 * clustering
			 */
			this.clusters = this.transformer.cluster(Arrays.asList(inPoints));   // List <- Array
		}
		
		setClusterNo();
	}
	
	/**
	 * 
	 * getList
	 *
	 * @return
	 */
	public List<DBSCANItem> getList()
	{
		return new ArrayList<DBSCANItem>(map.values());
	}
	
	/**
	 * 
	 * getMap
	 *
	 * @return
	 */
	public Map<Integer, DBSCANItem> getMap()
	{
		return this.map;
	}
	
	/**
	 * 
	 * getClusterSize
	 *
	 * @return
	 */
	public int getClusterSize()
	{
		return this.clusters.size();
	}
	
	/**
	 * 
	 * getClusters
	 *
	 * @return
	 */
	public List<Cluster<DoublePoint>> getClusters()
	{
		return this.clusters;
	}
	
	///////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////
	
	/**
	 * 
	 * getMaxClusterId
	 *
	 * @return
	 */
	public int getMaxClusterId()
	{
		int retClusterId = 0;
		int size = this.clusters.size() + 1;
		int[] cntCluster = new int[size];
		
		if (Flag.TRUE) {
			/*
			 * 초기화
			 */
			for (int i=0; i < size; i++) {
				cntCluster[i] = 0;
			}
		}

		if (Flag.TRUE) {
			/*
			 * counting
			 */
			for (DBSCANItem item : map.values()) {
				cntCluster[item.getClusterId()] ++;
			}
			
			if (!Flag.TRUE) {
				for (int i=0; i < size; i++) {
					System.out.println(String.format("COUNT(%d) = %d", i, cntCluster[i]));
				}
			}
		}
		
		if (Flag.TRUE) {
			int maxCnt = 0;
			
			for (int i=0; i < size; i++) {
				if (maxCnt < cntCluster[i]) {
					maxCnt = cntCluster[i];
					retClusterId = i;
				}
			}
		}
		
		return retClusterId;
	}
}
