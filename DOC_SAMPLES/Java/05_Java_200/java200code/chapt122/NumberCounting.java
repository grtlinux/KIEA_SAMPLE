public class NumberCounting {
	public int numberSum(int number){
		int a=0;
		int num=0;
		a=number;
		for(int i=0;i<length(number);i++){
			num+=a%10;
			a/=10;
		}
		return num;
	}
	private int length(int nums){
		return (nums+"").length();
	}
}
