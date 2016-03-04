import java.util.Comparator;
public class StudentIDComparator implements Comparator{
	//싱글톤 디자인 패턴을 이용하자.
	private static StudentIDComparator lc
			=new StudentIDComparator();
	private  StudentIDComparator(){} 
	public static StudentIDComparator getInstance(){
		return lc;
	}
	//Comparator때문에 구현 할 메서드
	public int compare(Object o1, Object o2) {
   		Student sid1  = ((Student)o1);
   		Student sid2  = ((Student)o2);
   		if(sid1.getId()>sid2.getId()){
			return 1;//크다.//증가순
		}else if(sid1.getId()==sid2.getId()){
			return 0;
		}else{
			return -1;
		}
	}
	/*
	public int compare(Object o1, Object o2) {
   		Student sid1  = (Student)o1;
   		Student sid2  = (Student)o2;
   		return sid1.compareTo(sid2);
	}*/
}
