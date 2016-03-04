public class  FactorialUsingFor
{
  public  int fact(int m){
	int temp=1;
	for(int i=m;i>0;i--){
		temp*=i;   
	}
	return temp;
  }
  public static void main(String[] args) 
	{
		FactorialUsingFor fa=new FactorialUsingFor();
		for(int i=1; i<14;i++){
			System.out.println(fa.fact(i));
		}
	}
}
