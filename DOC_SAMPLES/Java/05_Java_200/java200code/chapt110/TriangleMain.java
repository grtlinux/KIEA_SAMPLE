public class TriangleMain {

	public static void main(String[] args) {
		Triangle tri=new Triangle();
		
		System.out.println(tri.area(4));
		System.out.println(tri.area(4,4));
		System.out.println(tri.area(3,4,5.0));
		System.out.println(tri.area(3,4,new Radian(90)));
		System.out.println(tri.area(4,new Radian(60),new Radian(60)));
	
		System.out.println("1라디안 "+Radian.RadtoTheta(1)+"도");
		System.out.println("1도는 "+Radian.thetaToRad(1)+"라디안");
	}
}
