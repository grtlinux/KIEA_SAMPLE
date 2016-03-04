package kiea.z01.ztest.t01.Infogain.v01;

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
	///////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////
	///////////////////////////////////////////////////////////////////////////
	
	public static void main(String[] args)
	{
		FeatureSelectionMetrics fsm1 = new FeatureSelectionMetrics(49, 141, 27652, 774106);
		Double mi1        = fsm1.getMI();
		Double chiSquare1 = fsm1.getChiSquare();
		Double ig1        = fsm1.getIG();

		FeatureSelectionMetrics fsm2 = new FeatureSelectionMetrics(3, 4, 6, 1);
		Double mi2        = fsm2.getMI();
		Double chiSquare2 = fsm2.getChiSquare();
		Double ig2        = fsm2.getIG();

		System.out.println("mi1        : " + mi1); // Should be approximately 0.0001105
		System.out.println("chiSquare1 : " + chiSquare1); // Should be approximately 284
		System.out.println("ig1        : " + ig1);
		
		// The scores below should be undefined (null) due to boundary cases.
		System.out.println("mi2        : " + mi2);
		System.out.println("chiSquare2 : " + chiSquare2);
		System.out.println("ig2        : " + ig2);
	}
}
