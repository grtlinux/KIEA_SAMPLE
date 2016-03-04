public class CloneMain {

	public static void main(String[] args) {
		Student st=new Student("새새",6,"서울 동작구 방앗간리");
		System.out.println("1 "+st);
		Student st1=st;//shallow copy
		st1.setAddr("경기 고양시");//
		st1.setId(3);//
		System.out.println("2 "+st);
		Student sts=(Student)st.clone();//deepcopy
		System.out.println("3 "+sts);
		sts.setAddr("서울 운동장");
		sts.setId(5);
		System.out.println("4 "+st);
		System.out.println("5 "+sts);
	}
}
