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

package kiea.proj.sdc.anal.common.dbscan;

import java.util.List;
import java.util.Map;

import kiea.kr.co.tain.base.Flag;

import org.apache.commons.math3.ml.clustering.Cluster;
import org.apache.commons.math3.ml.clustering.DoublePoint;

/**
 * @author KangSeok
 * @date 2014. 8. 18.
 * @file_name DBSCANTest.java
 *
 */
public class DBSCANTestMain
{
	///////////////////////////////////////////////////////////////////////////
	
	/**
	 * 
	 * test01
	 *
	 */
	private static void test01()
	{
		if (Flag.TRUE) {
			DoublePoint[] inPoints = {
					new DoublePoint(new int[] {10, 10}), // A
					new DoublePoint(new int[] {12,  9}),
					new DoublePoint(new int[] {10,  8}),
					new DoublePoint(new int[] { 8,  8}),
					new DoublePoint(new int[] { 8,  6}),
					new DoublePoint(new int[] { 7,  7}),
					new DoublePoint(new int[] { 5,  6}), // B
					new DoublePoint(new int[] {14,  8}), // C
					new DoublePoint(new int[] { 7, 15}), // N - Noise, should not be present
					new DoublePoint(new int[] {17,  8}), // D - single-link connected to C should not be present
			};
			
			DBSCANObject dbscan = new DBSCANObject(2, 3);   // eps, minPts
			
			for (DoublePoint point : inPoints) {
				dbscan.addPoint(point);
			}
			
			dbscan.clustering();
			
			List<DBSCANItem> list = dbscan.getList();
			
			for (DBSCANItem item : list) {
				System.out.println(item);
			}
		}
	}
	
	private static void test02()
	{
		if (Flag.TRUE) {
			//DoublePoint[] inPoints = new DoublePoint[] {
			DoublePoint[] inPoints = {
					new DoublePoint(new double[] { 83.083032449241730, 58.833877541823310 }),
					new DoublePoint(new double[] { 45.054455109406260, 23.469642649637535 }),
					new DoublePoint(new double[] { 14.964179214322940, 69.026409639045600 }),
					new DoublePoint(new double[] { 73.531896043336020, 34.896145021310076 }),
					new DoublePoint(new double[] { 73.284981735516340, 33.968608069932090 }),
					new DoublePoint(new double[] { 73.458280988736080, 33.925844230921940 }),
					new DoublePoint(new double[] { 73.965788918314500, 35.731910069240260 }),
					new DoublePoint(new double[] { 74.007409718353300, 36.817355961771680 }),
					new DoublePoint(new double[] { 73.412475414108480, 34.273148566950110 }),
					new DoublePoint(new double[] { 73.915625635301700, 36.832067915471270 }),
					new DoublePoint(new double[] { 74.814992058090870, 37.156827498460190 }),
					new DoublePoint(new double[] { 74.031448800815270, 37.573991785524410 }),
					new DoublePoint(new double[] { 74.518709412077440, 38.674258946906775 }),
					new DoublePoint(new double[] { 74.507545951055360, 35.589039784157650 }),
					new DoublePoint(new double[] { 74.513227527495470, 36.030572259100154 }),
					new DoublePoint(new double[] { 59.279009966179730, 46.410917202942070 }),
					new DoublePoint(new double[] { 59.737447938416150, 46.200155583675950 }),
					new DoublePoint(new double[] { 58.811340766726060, 45.711501263314860 }),
					new DoublePoint(new double[] { 58.522255394374950, 47.416083617601544 }),
					new DoublePoint(new double[] { 58.218626647023484, 47.362289021722970 }),
					new DoublePoint(new double[] { 60.271396694472060, 46.606106348801404 }),
					new DoublePoint(new double[] { 60.894962462363765, 46.976924697402865 }),
					new DoublePoint(new double[] { 62.290486738784240, 47.669705635635180 }),
					new DoublePoint(new double[] { 61.038576089777050, 46.212924720020965 }),
					new DoublePoint(new double[] { 60.169162141392010, 45.181936613516880 }),
					new DoublePoint(new double[] { 59.900369059760120, 47.555364347063005 }),
					new DoublePoint(new double[] { 62.330036341445520, 47.839414898771790 }),
					new DoublePoint(new double[] { 57.860355367185550, 47.311179301934320 }),
					new DoublePoint(new double[] { 58.137154796859250, 48.985960494028404 }),
					new DoublePoint(new double[] { 56.131923963548616, 46.850890425266700 }),
					new DoublePoint(new double[] { 55.976329887053000, 47.463840376585720 }),
					new DoublePoint(new double[] { 56.232459752354770, 47.940035191131756 }),
					new DoublePoint(new double[] { 58.516870482126250, 46.622885352699086 }),
					new DoublePoint(new double[] { 57.854110819054770, 45.953943615779280 }),
					new DoublePoint(new double[] { 56.445776311447844, 45.162093662656844 }),
					new DoublePoint(new double[] { 57.366919496562330, 47.500971943372860 }),
					new DoublePoint(new double[] { 58.243626387557015, 46.114052729681134 }),
					new DoublePoint(new double[] { 56.272245956351980, 44.799080066150054 }),
					new DoublePoint(new double[] { 57.606924816500396, 46.942910577636210 }),
					new DoublePoint(new double[] { 30.187142300419510, 13.877149710431695 }),
					new DoublePoint(new double[] { 30.449448810657486, 13.490778346545994 }),
					new DoublePoint(new double[] { 30.295018390286714, 13.264889000216499 }),
					new DoublePoint(new double[] { 30.160201832884923, 11.892782623413950 }),
					new DoublePoint(new double[] { 31.341509791789576, 15.282655921997502 }),
					new DoublePoint(new double[] { 31.686016303254290, 14.756873246748000 }),
					new DoublePoint(new double[] { 29.325963742565364, 12.097849250072613 }),
					new DoublePoint(new double[] { 29.548207423882560, 13.613295356975868 }),
					new DoublePoint(new double[] { 28.793596088886260, 10.363520640879870 }),
					new DoublePoint(new double[] { 31.012845970923080, 12.788479208014905 }),
					new DoublePoint(new double[] { 27.585092167370020, 11.475701106013730 }),
					new DoublePoint(new double[] { 28.593799561727792, 10.780998203903437 }),
					new DoublePoint(new double[] { 31.356105766724795, 15.080316198524088 }),
					new DoublePoint(new double[] { 31.259485036367550, 13.674329151166603 }),
					new DoublePoint(new double[] { 32.315900763729590, 14.952617586590350 }),
					new DoublePoint(new double[] { 30.460413702763617, 15.884028092026710 }),
					new DoublePoint(new double[] { 32.561782030621540, 14.586076852632686 }),
					new DoublePoint(new double[] { 32.761386485304680, 16.239837325178087 }),
					new DoublePoint(new double[] { 30.182945333188400, 14.709592407103628 }),
					new DoublePoint(new double[] { 29.550881735282020, 15.065124718006700 }),
					new DoublePoint(new double[] { 29.004155302187428, 14.089665298582986 }),
					new DoublePoint(new double[] { 29.339624439831823, 13.290960655780510 }),
					new DoublePoint(new double[] { 30.997460327576846, 14.551914158277214 }),
					new DoublePoint(new double[] { 30.667841261252760, 16.269703107886016 }),
			};
			
			DBSCANObject dbscan = new DBSCANObject(2, 5);   // eps, minPts

			if (Flag.TRUE) {
				for (DoublePoint point : inPoints) {
					dbscan.addPoint(point);
				}
				
				dbscan.clustering();
			}

			if (Flag.TRUE) {
				List<DBSCANItem> list = dbscan.getList();
				
				for (DBSCANItem item : list) {
					System.out.println(item);
				}
			}

			if (Flag.TRUE) {
				List<Cluster<DoublePoint>> clusters = dbscan.getClusters();
				for (int i=0; i < dbscan.getClusterSize(); i++) {
					Cluster<DoublePoint> cluster = clusters.get(i);
					List<DoublePoint> points = cluster.getPoints();
					
					System.out.println(String.format("CNO=%d, size=%d", (i+1), points.size()));
				}
			}
			
			if (Flag.TRUE) {
				Map<Integer, DBSCANItem> map = dbscan.getMap();
				for (int i=0; i < map.size(); i++) {
					DBSCANItem item = map.get(new Integer(i));
					System.out.println(String.format("(%2d) %s", i, item));
				}
			}
			
			if (Flag.TRUE) {
				System.out.println("Max count of clusterId : " + dbscan.getMaxClusterId());
			}
		}
	}
	
	/**
	 * 
	 * main
	 *
	 * @param args
	 */
	public static void main(String[] args)
	{
		if (!Flag.TRUE) test01();
		if (Flag.TRUE) test02();
	}
}
