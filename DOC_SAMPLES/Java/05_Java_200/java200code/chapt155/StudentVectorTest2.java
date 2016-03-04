import java.util.*;
public class StudentVectorTest2 {

	public static void main(String[] args) {
		Vector v=new Vector(2,5);    
		v.add(new Student("Jee",1,"Seoul"));//0
		v.add(new Student("Gong",2,"Seoul"));//1
		v.add(new Student("Song",3,"Seoul"));//2
		v.add(new Student("Chang",4,"koyang"));//3
		print(v);
		Collections.shuffle(v);//무작위로 섞는다.
		print(v);
	}
	public static void print(List list){
		System.out.println("-----------------------------");
		System.out.println(list.size());
		for(int i=0;i<list.size();i++){
			Student stu=(Student)list.get(i);//index는 0부터
			System.out.println(stu.getId()+" "+stu.getName()+" "+stu.getAddr());
		}
	}
}
