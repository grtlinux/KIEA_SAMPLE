public class ReadFromSystem1 {

	public static void main(String[] args) {
		byte[] b=new byte[1024];
		int len=0;
        try{
			len=System.in.read(b);  //섹션 102
        }
		catch(Exception e){ 
			System.out.println("입력실패"); 
		}
		String str=new String(b,0,len-2);//'\n'과 '\r'이 붙어 있어서  2를 뺀다.
		System.out.println(str);
		try{
			len=System.in.read(b);
		    System.out.write(b, 0, len);
        }
		catch(Exception e){ 
			System.out.println("입력실패"); 
		}
	}
}
