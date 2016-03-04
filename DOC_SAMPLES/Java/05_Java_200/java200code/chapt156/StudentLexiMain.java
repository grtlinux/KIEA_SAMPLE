import java.util.Arrays;
public class StudentLexiMain {

	public static void main(String[] args) {

		StudentLexiComparator lc=
			   StudentLexiComparator.getInstance();
		StudentIDComparator idc=StudentIDComparator.getInstance();
		//선언
		Student [] sg=new Student[7];
		//초기화
		sg[0]=new Student("김말뚝",101001,"서울");
		sg[1]=new Student("홍길동",101002,"경기");
		sg[2]=new Student("최순동",101003,"인천");
		sg[3]=new Student("이연림",101004,"부산");
		sg[4]=new Student("하하호",101005,"창원");
		sg[5]=new Student("김경민",101006,"목포");
		sg[6]=new Student("김소연",101007,"제주");
		
		System.out.println("이름순 출력==============");
		Arrays.sort(sg,lc);
		prints(sg);

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
