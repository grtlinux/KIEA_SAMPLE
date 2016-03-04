public class Make369 {
	private int number369(int number){
		int a=0;
		int num=0;
		a=number;
		for(int i=0;i<length(number);i++){
			if((a%10)!=0 && (a%10)%3==0){//0이 아니면서 3,6,9
				num++;
			}
			a=a/10;
		}
		return num;
	}//
	private int length(int nums){
		return (nums+"").length();
	}//
	public void play369(int max){
		for(int i=1;i<=max;i++){
			if(number369(i)>0){
				for(int j=1;j<=number369(i);j++){
					System.out.print("*");
				}
				System.out.print("\t");
			}else{
				System.out.print(i+"\t");
			}
			if(i%10==0){
				System.out.println();
			}
		}
	}//
}

/**
 * 3의 배수를 구하는 것이 아니라 </br>
 * 3, 6, 9가 몇개 포함되어 있는 지를 구하는 프로그램 </br>
 */
