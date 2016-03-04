public class Radian {
	
	private double rad;
	public  Radian (double theta){
		rad=Math.PI/180.0*theta;
	}
	public double getRad() {
		return rad;
	}
	public void setRad(double theta) {
		rad=Math.PI/180.0*theta;
	}
	public static double  RadtoTheta(double rad) {
		return 180.0/Math.PI*rad;
	}
	public static double  thetaToRad(double theta) {
		return Math.PI/180.0*theta;
	}
}