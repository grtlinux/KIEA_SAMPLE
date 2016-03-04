public class DecimalScaleMain2 {

	public static void main(String[] args) {
		int deciNUm=123;
		
		for(int i=0;i<32;i++){
			System.out.print((int)(deciNUm/Math.pow(2,31-i)));
			deciNUm=(int)(deciNUm%Math.pow(2,31-i));
		}
		System.out.println(); 
		System.out.println(0173);//8진법  
		System.out.println(toOctal(123));//8진법  
		System.out.println(0x7b);//0x7B 16진법
		System.out.println(toHex(123));//8진법  

	}
	public static String toOctal(int num){
		int temp=num;
		String sToOct="";//String 초기화
		for(int i=0;i<32/3;i++){
			sToOct=(temp%8)+sToOct;
			temp=temp/8;
		}
		return sToOct;
	}
	public static String toHex(int num){
		int temp=num;
		String sToOct="";//String 초기화
		for(int i=0;i<32/4;i++){
			sToOct=toAlphabet(temp%16)+sToOct;
			//System.out.println(i+"  "+sToOct);
			temp=temp/16;
		}
		return sToOct;
	}
	public static String toAlphabet(int num){
		String sToHex="";
		switch(num){
			case 10 : sToHex="A"; break;
			case 11 : sToHex="B"; break;
			case 12 : sToHex="C"; break;
			case 13 : sToHex="D"; break;
			case 14 : sToHex="E"; break;
			case 15 : sToHex="F"; break;
			default : sToHex+=num; break;
		}
		return sToHex;
	}
}
