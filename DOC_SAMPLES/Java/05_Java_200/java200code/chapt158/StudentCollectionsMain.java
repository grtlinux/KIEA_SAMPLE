import java.util.*;
public class StudentCollectionsMain {

	public static void main(String[] args) {
		GradeComparator gc=GradeComparator.getInstance();
		//선언 
		Vector v=new Vector(5,5);
		//Student [] sg=new Student[7];
		//초기화
		Student stu1=new Student("김말뚝",101001,"서울");
		Student stu2=new Student("홍길동",101002,"경기");
		Student stu3=new Student("최순동",101003,"인천");
		Student stu4=new Student("이연림",101004,"부산");
		Student stu5=new Student("하하호",101005,"창원");
		Student stu6=new Student("김경민",101006,"목포");
		Student stu7=new Student("김소연",101007,"제주");

		v.add(stu1);
		v.add(stu2);
		v.add(stu3);
		v.add(stu4);
		v.add(stu5);
		v.add(stu6);
		v.add(stu7);

		stu1.setGrade(new Grade(60,60,70));
		stu2.setGrade(new Grade(89,65,67));
		stu3.setGrade(new Grade(60,80,100));
		stu4.setGrade(new Grade(70,99,80));
		stu5.setGrade(new Grade(60,70,80));
		stu6.setGrade(new Grade(60,80,80));
		stu7.setGrade(new Grade(73,70,80));
		System.out.println("입력순 출력==============");
		prints(v);
		System.out.println("입력 역순 출력==============");
		Collections.reverse(v);
		prints(v);
		System.out.println("임의로 섞은 후 출력==============");
		Collections.shuffle(v);
		prints(v);
		System.out.println("성적순 출력==============");
		Collections.sort(v,gc);
		prints(v);
	}
	public static void prints(Vector v){
		int num=v.size();
		for(int i=0;i<num;i++){
			Student stu=(Student)v.get(i);
		    System.out.println(stu);
	   }//for      
	   System.out.println();
	}//prints    
}
