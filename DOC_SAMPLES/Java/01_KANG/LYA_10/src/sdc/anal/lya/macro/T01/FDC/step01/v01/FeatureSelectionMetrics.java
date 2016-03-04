package sdc.anal.lya.macro.T01.FDC.step01.v01;

import kiea.kr.co.tain.base.Flag;


public class FeatureSelectionMetrics extends FeatureOccurrenceCounter
{
	// Mutual information score
	private Double mi;
	
	// Chi-square score
	private Double chiSquare;
	
	// Information gain score
	private Double ig;
	
	public FeatureSelectionMetrics() {
		super();
	}
	
	public FeatureSelectionMetrics(double tp, double fn, double fp, double tn) {
		super(tp, fn, fp, tn);
	}
	
	private double log2(double value) {
		return (Math.log(value) / Math.log(2));
	}
	
	private void calculateMutualInformation() {
		if (tp == 0 || fn == 0 || fp == 0 || tn == 0) {
			mi = null;
			return;
		}
		
		calculateSum();
		
		double gPos = getGoldStandardPositives();
		double gNeg = getGoldStandardNegatives();
		double fPos = getPredictedPositives();
		double fNeg = getPredictedNegatives();
		
		double imsi = (tp/n) * log2((n*tp) / (gPos*fPos))
				+ (fp/n) * log2((n*fp) / (gNeg*fPos))
				+ (fn/n) * log2((n*fn) / (gPos*fNeg))
				+ (tn/n) * log2((n*tn) / (gNeg*fNeg));
		
		mi = Double.valueOf(imsi);
	}
	
	private void calculateChiSquare() {
		if (tp+fp == 0 || tp+fn == 0 || fn+tn == 0 || fp+tn == 0) {
			chiSquare = null;
			return;
		}
		
		calculateSum();
		
		double chi = (n*Math.pow((tp*tn-fn*fp), 2)) / ((tp+fp)*(tp+fn)*(fn+tn)*(fp+tn));
		
		chiSquare = Double.valueOf(chi);
	}
	
	private void calculateInformationGain() {
		if (tp == 0 || fn == 0 || fp == 0 || tn == 0) {
			ig = null;
			return;
		}
		
		calculateSum();
		
		double gPos = getGoldStandardPositives();
		double gNeg = getGoldStandardNegatives();
		double fPos = getPredictedPositives();
		double fNeg = getPredictedNegatives();

		double igs = -(gPos/n) * log2(gPos/n) - (gNeg/n) * log2(gNeg/n) 
				+ (tp/n) * log2(tp/fPos) + (fp/n) * log2(fp/fPos) 
				+ (fn/n) * log2(fn/fNeg) + (tn/n) * log2(tn/fNeg); 

		ig = Double.valueOf(igs);
	}
	
	public Double getMI() {
		calculateMutualInformation();
		return mi;
	}
	
	public Double getChiSquare() {
		calculateChiSquare();
		return chiSquare;
	}
	
	public Double getIG() {
		calculateInformationGain();
		return ig;
	}
	
	///////////////////////////////////////////////////////////////////////////

	private static double getLog2(double value)
	{
		return (Math.log(value) / Math.log(2));
	}
	
	public static double getInfoGain(double m, double n)
	{
		double ret = 0;
		
		if (Flag.TRUE) {
			if (m == 0 || n == 0)
				return 0;
			
			double t = m + n;
			
			ret = - m / t * getLog2(m / t) - n / t * getLog2(n / t);
		}

		return ret;
	}
	
	public static String getBG(int value)
	{
		if (value == 0)
			return "B";
		else
			return "G";
	}

	///////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////
	
	private static void metrics(double tp, double fn, double fp, double tn)
	{
		FeatureSelectionMetrics fsm1 = new FeatureSelectionMetrics(tp, fn, fp, tn);
		Double mi1        = fsm1.getMI();
		Double chiSquare1 = fsm1.getChiSquare();
		Double ig1        = fsm1.getIG();
		
		System.out.println("( " + tp + ", " + fn + ", " + fp + ", " + tn + " )");
		System.out.println("mi        : " + mi1);
		System.out.println("chiSquare : " + chiSquare1);
		System.out.println("ig        : " + ig1);
		System.out.println("------------------------------------------");
	}
	
	private static void test01()
	{
		metrics(49, 141, 27652, 774106);
		metrics(3, 4, 6, 1);
		metrics(2, 0, 0, 3);
		metrics(3, 0.0001, 0.0001, 2);
		metrics(2, 0.0001, 0.0001, 3);
		metrics(0.0001, 2, 3, 0.0001);
		metrics(0.0001, 2, 30, 0.0001);
		metrics(0.0001, 2, 300, 0.0001);
	}
	
	private static void test02()
	{
		if (Flag.TRUE) {
			System.out.println("InfoGain(555, 555)>" + FeatureSelectionMetrics.getInfoGain(555, 555));
			System.out.println("InfoGain(5, 5)>" + FeatureSelectionMetrics.getInfoGain(5, 5));
			System.out.println("InfoGain(4, 6)>" + FeatureSelectionMetrics.getInfoGain(4, 6));
			System.out.println("InfoGain(6, 4)>" + FeatureSelectionMetrics.getInfoGain(6, 4));
			System.out.println("InfoGain(1, 9)>" + FeatureSelectionMetrics.getInfoGain(1, 9));
			System.out.println("InfoGain(1, 9999999)>" + FeatureSelectionMetrics.getInfoGain(1, 9999999));
			System.out.println("InfoGain(0,10)>" + FeatureSelectionMetrics.getInfoGain(0,10));
		}
	}
	
	private static void test03()
	{
		if (Flag.TRUE) {
			int cntTotal = 0;
			int cntTotalBad = 0;
			int cntTotalGood = 0;
			double infoGainTotal = 0;
			
			int[][] arr = {   // 0:BAD 1:GOOD, 이상값(ODD), Left-B-cnt, Left-G-cnt, Right-B-cnt, Right-G-cnt
					{ 1, 0, 1, 0, 0, 0, 0, },  // ODD
					{ 2, 1, 0, 0, 0, 0, 0, },
					{ 3, 1, 0, 0, 0, 0, 0, },
					{ 4, 0, 0, 0, 0, 0, 0, },
					{ 5, 0, 0, 0, 0, 0, 0, },
					{ 6, 1, 1, 0, 0, 0, 0, },  // ODD
					{ 7, 0, 0, 0, 0, 0, 0, },
					{ 8, 1, 0, 0, 0, 0, 0, },
					{ 9, 0, 0, 0, 0, 0, 0, },
			};
			
			if (Flag.TRUE) {
				for (int i=0; i < arr.length; i++) {
					if (arr[i][2] == 1) {
						// 이상값(ODD) 미처리
						continue;
					}
					
					cntTotal ++;
					
					if (arr[i][1] == 0)
						cntTotalBad ++;
					else
						cntTotalGood ++;
				}
				
				infoGainTotal = getInfoGain(cntTotalBad, cntTotalGood);
				if (Flag.TRUE) System.out.println("InfoGain(" + cntTotalBad + "," + cntTotalGood + ") = " + infoGainTotal);
			}
			
			if (Flag.TRUE) {
				// 값이 바뀐 지점을 BreakPoint로 한다.
				int cntLB = 0;
				int cntLG = 0;
				int cntRB = cntTotalBad;
				int cntRG = cntTotalGood;
				
				Double val = null;
				for (int i=0; i < arr.length; i++) {
					//if (!Flag.TRUE) System.out.println("(" + arr[i][0] + ":" + getBG(arr[i][1]) + ")");
					
					if (arr[i][2] == 1) {
						// 이상값(ODD) 미처리
						continue;
					}
					
					if (arr[i][1] == 0) {
						// BAD
						cntLB ++;
						cntRB --;
					} else {
						// GOOD
						cntLG ++;
						cntRG --;
					}
					
					arr[i][3] = cntLB;
					arr[i][4] = cntLG;
					arr[i][5] = cntRB;
					arr[i][6] = cntRG;
					
					
					if (Flag.TRUE) System.out.print("(" + arr[i][0] + "," + getBG(arr[i][1]) + ") (" + arr[i][3] + "," + arr[i][4] + ") (" + arr[i][5] + "," + arr[i][6] + ")");
					if (val == null) {
						// 첫번째 값
						val = (double) arr[i][1];
					} else {
						// 두번째 이후 값
						if (val != arr[i][1]) {
							// BreakPoint 위치
							double infoGainL = getInfoGain(cntLB, cntLG) * (cntLB + cntLG) / cntTotal;
							double infoGainR = getInfoGain(cntRB, cntRG) * (cntRB + cntRG) / cntTotal;
							double infoGain = infoGainTotal - ( infoGainL + infoGainR); 
							
							
							if (Flag.TRUE) System.out.print("--> BreakPoint InfoGain = " + infoGain + "   (" + infoGainL + ", " + infoGainR + ")");
							val = (double) arr[i][1];
						}
					}
					
					if (Flag.TRUE) System.out.println();
				}
			}
		}
	}
	
	private static void test04()
	{
		if (Flag.TRUE) {
			@SuppressWarnings("unused")
			double[][] arrSource = {  // Value, BG Flag(0:B,1:G), Odd-Value
					{ 1, 0, 1, },  // ODD
					{ 2, 1, 0, },
					{ 3, 1, 0, },
					{ 4, 0, 0, },
					{ 5, 0, 0, },
					{ 6, 1, 1, },  // ODD
					{ 7, 0, 0, },
					{ 8, 1, 0, },
					{ 9, 0, 0, },
			};
			
			// double[][] arrTarget = getArrTarget(arrSource);
		}
	}
	
	public static void main(String[] args)
	{
		if (!Flag.TRUE) test01();
		if (!Flag.TRUE) test02();
		if (!Flag.TRUE) test03();
		if (Flag.TRUE) test04();
	}
}
