public class TypeConversion1Chapt40 {

	public static void main(String[] args) {
		
		String strNum1="12345";
		int    tempNum1=2345;
		//String--> int
		int intNum1=Integer.parseInt(strNum1);
		double douNum1=Double.parseDouble(strNum1);
		
		//int--> String
		String strNum2=Integer.toString(tempNum1);
		String strNum3=tempNum1+"";
		
		//String <--> char
		char [] charStr={'H','e','l','l','o'};
		String strChar=new String(charStr);
		char [] charStr2=strChar.toCharArray();
		char charChar=strChar.charAt(4);//'0'
		String sv=String.valueOf(charStr2);
		System.out.println(sv);
		
		//String Concatenation
		String s="Get ";
		s+="java ";
		s+="this time.";
		String st="";
		st.concat("java ").concat("this time.");
		System.out.println("??=>"+st);//immutable
		st=st.concat("java ").concat("this time.");
		System.out.println("??<="+st);
		String stss="¾È³çÇÏ¼¼¿ä.";
		
		//byte<-->String
		byte[] byteStr=stss.getBytes();//S-->b
		for(int i=0;i<byteStr.length;i++){
			System.out.print(byteStr[i]+" ");
		}
		System.out.println();
		String strByte=new String(byteStr);//b-->s
		System.out.println(strByte);
	}
}
