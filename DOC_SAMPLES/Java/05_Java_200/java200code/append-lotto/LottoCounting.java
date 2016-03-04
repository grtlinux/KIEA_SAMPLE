public class LottoCounting {
	private int [] lotto;
	private int [] count;
	public LottoCounting(int[] lotto){
		setLotto(lotto);
	}
	public void setLotto(int[] lotto){
		this.lotto=lotto;
	}
	private boolean isSame(int [] a, int b){
		boolean isS=false;
		for(int i=0;i<a.length;i++){
			if(a[i]==b){
				isS=true;
				break;
			}
		}
		return isS;
	}
	public void printLotto(){
		System.out.println("-----------------------");
		prints(lotto);
		for(int i=0;i<count.length;i++){
			System.out.print(count[i]+"  ");
		}
		System.out.println();
	}
	public void make(int [][] outlotto){
		int width=outlotto.length;
		int depth=outlotto[0].length;
		count=new int[width];
		for(int i=0;i<width;i++){
			prints(outlotto[i]);
			counts(outlotto[i],i);
		}
	}
	private void counts(int [] a, int b){
		int nums=a.length;
		
		for(int i=0;i<nums;i++){
			if(isSame(a,lotto[i])){
				count[b]++;
			}
		}
	}
	//배열을 출력하는 메서드 
	public  void prints(int [] nums){
		System.out.print("[");
		for(int i=0;i<nums.length-1;i++){
			System.out.print(nums[i]+" , ");
		}
		System.out.println(nums[nums.length-1]+"]");
	}
}
