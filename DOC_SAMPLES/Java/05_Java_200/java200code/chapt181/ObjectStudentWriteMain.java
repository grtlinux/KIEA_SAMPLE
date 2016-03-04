import java.io.IOException;
import java.util.Vector;
public class ObjectStudentWriteMain {
	public static void main(String[] args) {
		ObjectStudentWrite osw=new ObjectStudentWrite();
		Vector v=new Vector(5, 5);
		//Vector<Student> v=new Vector<Student>(5, 5);//JAVA5
		v.add(new Student("È«±æµ¿",17,"ÇÑ¾ç"));
		v.add(new Student("È«±æ¼ø",15,"¼øÃµ"));
		v.add(new Student("¸ù·æ",20,"È­Ãµ"));
		v.add(new Student("ÃáÇâ",18,"»ïÃ´"));
		try {
			osw.write("stu.txt",v);
			osw.read("stu.txt");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
