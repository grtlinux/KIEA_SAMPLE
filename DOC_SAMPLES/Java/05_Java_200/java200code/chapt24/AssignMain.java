public class  AssignMain
{
	public static void main(String[] args) 
	{
		int intNm1=123;
		long longNum1=345L;
		double doubleNum=123.123;
		
		Integer intWrap1=new Integer(187);
		Long    longWrap1=new Long(876);
		Double  doubleWrap1=new Double(365);
		
		String str="Hello JAVA5";          //predefined
		String newStr=new String("Hello"); //predefined
		Object  obj=new Object();          //predefined
		
		Student stu=new Student();       //user-defined

		doubleNum=longNum1;       //primitive promotion
		intNm1=(int)doubleNum;      //primitive casting
		longNum1=longWrap1.longValue();  //pri<-->wrap
		
		//longWrap1=intWrap1; //reference type mismatch 
		obj=longWrap1;        //상속관계(계층) promotion
		longWrap1=(Long)obj;  //상속관계(계층) casting
		System.out.println(longWrap1.longValue());
		obj=stu;             //상속관계(계층) promotion
		stu=(Student)obj;    //상속관계(계층) casting
		System.out.println(stu.getName());
		//str=stu;           //reference type mismatch 
	}
}
