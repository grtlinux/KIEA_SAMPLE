public class PointRotateMain {

	public static void main(String[] args) {
		PointRotate ro=new PointRotate();
		
		Point p1=new Point(4,4);
		Point p2=ro.rotate(p1,90);//90도 회전
		System.out.println(p2);
		
		Point p3=ro.rotate(p1,45);//45도 회전
		System.out.println(p3);
	}
}
