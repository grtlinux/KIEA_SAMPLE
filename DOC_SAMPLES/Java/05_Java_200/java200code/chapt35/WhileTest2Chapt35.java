public class WhileTest2Chapt35 
{
	public static void main(String[] args) 
	{
		int m=150;
		int n=330;
		while(m!=n){
			System.out.print("["+m+","+n+"] ");
			if(m>n)m-=n;
			else if(n>m)n-=m;
		}
		System.out.println();
		System.out.println("최대공약수 :"+m);
	}
}
