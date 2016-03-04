import java.util.Comparator;
public class GradeComparator implements Comparator{
	private static GradeComparator gc
			=new GradeComparator();
	private  GradeComparator(){} 
	public static GradeComparator getInstance(){
		return gc;
	}
	//Comparator때문에 구현 할 메서드
	/*
	public int compare(Object o1, Object o2) {
		Student sg1=(Student)o1;
		Student sg2=(Student)o2;
		double mean1=sg1.getGrade().getMean();
		double mean2=sg2.getGrade().getMean();
		if(mean1<mean2){
			return -1;  //increasing
		}else if(mean1==mean2){
			return 0;
		}else {
			return 1;  //decreasing
		}
	}*/
	public int compare(Object o1, Object o2) {
		Student sg1=(Student)o1;//학생
		Student sg2=(Student)o2;
		Grade g1=sg1.getGrade();//성적
		Grade g2=sg2.getGrade();
		return g1.compareTo(g2);//성적비교
	}
}
