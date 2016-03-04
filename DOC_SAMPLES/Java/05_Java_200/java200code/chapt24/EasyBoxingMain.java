public class  EasyBoxingMain
{
	public static void main(String[] args) 
	{
		//Boxing/Unboxing은 JAVA5부터 
		int intNm1=123;
		long longNum1=345L;
		double doubleNum=123.123;
		
		Integer intWrap1=new Integer(187);
		Long    longWrap1=new Long(876);
		Double  doubleWrap1=new Double(365);

		Object  obj=new Object();          //predefined
		
		Integer intWrap2=intNm1;  //Boxing
		intNm1=(int)intWrap2;     //UnBoxing

		obj=longWrap1;        //상속관계(계층) promotion
		longWrap1=(Long)obj;  //상속관계(계층) casting
		obj=intNm1;       //Boxing-->obj=new new Integer(intNm1);
		intNm1=(Integer)obj;  //UnBoxing-->((Integer)obj).intValue();
		System.out.println(intNm1);
	}
}
