public class PointUtil {
	
	public static double length(Point x1, Point y1){
		double x3=x1.getX()-y1.getX();
		double y3=x1.getY()-y1.getY();
		return Math.sqrt((x3)*(x3)+(y3)*(y3));
	}
	public static Point move(Point x,Point y){
		return new Point(x.getX()+y.getX(),x.getY()+y.getY());
	}
	public static Point move(Point x,double x1, double y1){
		return new Point(x.getX()+x1,x.getY()+y1);
	}
	//세점을 줄때 구하는 법
	public static double area(Point x1,Point x2,Point x3){
		double s1=x1.getX()*x2.getY()+x2.getX()*x3.getY()+x3.getX()*x1.getY();
		double s2=x2.getX()*x1.getY()+x3.getX()*x2.getY()+x1.getX()*x3.getY();
		return 1.0/2.0*Math.abs(s1-s2);
	}
}
