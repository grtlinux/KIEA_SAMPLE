public class StringInput{
	
	public static int readInt(){
		int temp=Integer.parseInt(readString());
		return temp;
	}
	public static String readString(){
		
		byte[] b=new byte[40];
		int len=0;
        try{
			len=System.in.read(b);
        }
		catch(Exception e){ 
			System.out.println("입력실패"); 
		}
		String str=new String(b,0,len-2);//'\n'과 '\r'이 붙어 있어서  2를 뺀다.

		return str;
	}

}