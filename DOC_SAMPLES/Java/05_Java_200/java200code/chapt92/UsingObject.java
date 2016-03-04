import java.lang.reflect.Constructor;
public class UsingObject {

	public static void main(String[] args) {
		
		Object obj1=new Object();
		Object obj2=new Object();
		
		System.out.println(obj1.hashCode());//native
		System.out.println(obj1==obj2);//객체는 고유하다.
		System.out.println(obj1.equals(obj2));//객체는 고유하다.
		System.out.println(obj1);
		System.out.println(obj2.toString());
			
		System.out.println(obj1.getClass().getName());//클래스이름
		String str=obj1.getClass().getName() + "@" 
		           + Integer.toHexString(obj1.hashCode());
		System.out.println(str);//클래스이름@16진수해쉬코드
		
		Object objstr=new String("Good");//다형성
		System.out.println(objstr.toString());
		System.out.println(objstr instanceof Object);
		System.out.println(objstr instanceof String);

		Class classes=obj1.getClass();
		System.out.println(classes.getName());//클래스이름
		System.out.println("--------constructor-------");
		Constructor [] constructor = classes.getDeclaredConstructors();
		for(Constructor con: constructor){
			System.out.println(con.getName());
		}
	}
}
