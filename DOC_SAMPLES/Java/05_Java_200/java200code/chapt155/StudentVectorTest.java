import java.util.*;
public class StudentVectorTest {

	public static void main(String[] args) {
		Vector v=new Vector();     //capacity 10 increasing *2
		//Vector v=new Vector(5);  //capacity 5 increasing *2
		//Vector v=new Vector(5,5);//capacity 5 increasing 5
		v.add(new Student("Jee",1,"Seoul"));//0
		v.clear();//모두 제거
		v.add(new Student("Gong",2,"Seoul"));//1
		v.add(new Student("Song",3,"Seoul"));//2
		v.add(new Student("Lee",4,"Koyang"));//3
		v.add(new Student("Lee",4,"Koyang"));//3과 중복허용
		System.out.println(v.size()+"  "+v.capacity());
		System.out.println(v.contains(new Student("Lee",25,"Koyang")));
		
		Student stu=(Student)v.elementAt(2);//index는 0부터
		System.out.println(stu.getId()+"  "+stu.getName()+"  "+stu.getAddr());
		//모든 elements 출력하기
		Enumeration enums=v.elements();
		while(enums.hasMoreElements()){
			Student stus=(Student)enums.nextElement() ;
			System.out.println(stus.getId()+"  "+stus.getName()+"  "+stus.getAddr());
		}
	}
}
