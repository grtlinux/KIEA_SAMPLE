public class BitShift2To10 {
	//2진법 스트링을 10진법으로 바꾸기 위한 메소드 (정해진 위치)
	private int frBiTo10s(String str,int a){
		return Integer.parseInt(str)*(2<<a);
	}
	//2진법 스트링을 10진법으로 바꾸기 위한 메소드 (정해진 위치)
	private int frBiTo10(String str,int a){
		int temp=1;
		if(a>=1){
			temp= Integer.parseInt(str)<<a;
		}else{
			temp= Integer.parseInt(str)>>(-a);
		}
		return temp;
	}
	//2진법으로 되어 있는 수를 10진법수로
	public int binTo10(String str){
		int nums=0;
		int count=str.length();
		for(int i=0;i<count;i++){
			nums+=frBiTo10(str.charAt(count-1-i)+"",i);
		}
		return nums;
	}
}
