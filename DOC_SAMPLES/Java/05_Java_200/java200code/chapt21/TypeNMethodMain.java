public class TypeNMethodMain {

	public static void main(String[] args) {
		//primitive type
		int myId=1001;
		int yourID=1002;
		//Reference type -- Predefined
		String myName="hyonny";
		String yourName=new String("Gagamel");
		//Reference type -- Userdefined
		Student stu=new Student();
		stu.setId(yourID);
		stu.setName(yourName);
		//method calling
		printInfor(myId,myName);
		printInfor(stu);
		
	}
	public static void printInfor(int id, String name){
		System.out.println("ID는 "+id+", 이름은 "+name+"이다.");
		//System.out.printf("ID는 %d, 이름은 %s이다.%n",id,name);
	}
	public static void printInfor(Student st){
		System.out.println("ID는 "+st.getId()+
                               ", 이름은 "+st.getName()+"이다.");
		//System.out.printf("ID는 %d, 이름은 %s이다.%n",
		//                              st.getId(),st.getName());
	}
}
