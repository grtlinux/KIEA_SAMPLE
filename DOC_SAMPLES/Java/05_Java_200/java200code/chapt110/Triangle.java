public class Triangle {
	//정삼각형 
	public double area(double x){
		return Math.sqrt(3)/4.0*x*x;
	}
	//이등변 삼각형  1/2 *밑변*높이  
	public double area(double x,double y){
		return 1.0/2.0*x*y;
	}
	//	두변과 두변 사이에 낀각  
	public double area(double x,double y, Radian rad){
		double radian=rad.getRad();
		return 1.0/2.0*x*y*Math.sin(radian);
	}
	//	한 변과 두 양각 
	public double area(double x,Radian rad1, Radian rad2){
		double radian1=rad1.getRad();
		double radian2=rad1.getRad();
		return 1.0/2.0*x*x*Math.sin(radian1)*Math.sin(radian2)/Math.sin(radian1+radian2);
	}
	//헤론의 공식 -- 세변의 길이를 줄때 
	public double area(double x,double y,double z){
		double s=(x+y+z)/2.0;
		return Math.sqrt(s*(s-x)*(s-y)*(s-z));
	}
}
