import java.util.Arrays;
public class StudentIDMain {

	public static void main(String[] args) {

		StudentIDComparator idc=StudentIDComparator.getInstance();
		//선언
		Student [] sg=new Student[7];
		//초기화
		sg[0]=new Student("김말뚝",101007,"서울");
		sg[1]=new Student("홍길동",101006,"경기");
		sg[2]=new Student("최순동",101003,"인천");
		sg[3]=new Student("이연림",101004,"부산");
		sg[4]=new Student("하하호",101005,"창원");
		sg[5]=new Student("김경민",101001,"목포");
		sg[6]=new Student("김소연",101002,"제주");
		
		System.out.println("id순 출력==============");
		Arrays.sort(sg,idc);
		prints(sg);
	}
	public static void prints(Student[] a){
		int num=a.length;
		for(int j=0;j<num;j++){
		   System.out.println(a[j]);
	   }//for
	   System.out.println();
	}//prints
}
