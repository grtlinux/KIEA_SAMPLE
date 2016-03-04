public class IntToChar
{
	public static void main(String[] args) 
	{
		for(int i=21;i<=60;i++){
			//System.out.printf("[%c] ",i);
			System.out.print("["+(char)i+"] ");
			// (char)i --> int를 char로 casting
			if(i%10==0){
				System.out.println();//한칸을 내려라
			}
		}
		System.out.println('a');
		//System.out.println('\u0041');
		System.out.println('A');
		System.out.println("알파벳 : "+('z'-'a'+1)+"자");
		char cc='한';
		System.out.println(cc);//한
		System.out.println((int)cc);//int
		char zChar='0';//Unicode 48
		int count=(int)zChar;
		System.out.println(count);
		for(int i=count;i<=count+9;i++){
			System.out.print("["+(char)i+"] ");
		}
		System.out.println();//한칸을 내려라

		System.out.println('\u0001'); //
		System.out.println('\1'); //
		System.out.println('\u0015'); //16진법
		System.out.println('\25'); //8진법
		for(int i=0;i<=39;i++){
			System.out.print("["+(char)('\25'+i)+"] ");
			if((i+1)%10==0){
				System.out.println();//한칸을 내려라
			}
		}
		System.out.println();
		/*
		for(int i=0;i<=39;i++){
			System.out.printf("[%c] ",('\25'+i));
			if((i+1)%10==0){
				System.out.printf("%n");//한칸을 내려라
			}
		}
		*/
	}
}
