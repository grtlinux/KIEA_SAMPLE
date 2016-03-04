public class LogicCalculTest
{
	public static void main(String[] args) 
	{
		boolean boa1=true;
		boolean boa2=false;

		System.out.println("                P|Q  P&Q  P^Q   !P");
		System.out.print("1: ["+boa1+","+boa1+"]="+(boa1|boa1)+
			  " "+(boa1&boa1)+" "+(boa1^boa1)+" "+(!boa1)+"\n");
		System.out.print("2: ["+boa1+","+boa2+"]="+(boa1|boa2)+
			  " "+(boa1&boa2)+" "+(boa1^boa2)+" "+"\n");
		System.out.print("3: ["+boa2+","+boa1+"]="+(boa2|boa1)+
			  " "+(boa2&boa1)+" "+(boa2^boa1)+" "+"\n");
		System.out.print("4: ["+boa2+","+boa2+"]="+(boa2|boa2)+
			  " "+(boa2&boa2)+" "+(boa2^boa2)+" "+(!boa2)+"\n");
		//System.out.printf("1: [%b,%b]= %b %b %b %b %n",
		//	 boa1,boa1,(boa1|boa1),(boa1&boa1),(boa1^boa1),(!boa1));
		//System.out.printf("2: [%b,%b]= %b %b %b %n",
		//	 boa1,boa2,(boa1|boa2),(boa1&boa2),(boa1^boa2));
	}
}
