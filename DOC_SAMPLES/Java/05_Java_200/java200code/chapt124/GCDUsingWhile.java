public class  GCDUsingWhile
{
  //최대 공약수 값
  public  int gcd(int m,int n){
	  int temp=0;
	  while(m!=n){
		  if(m>n)m-=n;
		  else if(n>m)n-=m;
	  }
	  return m;
  }
  //최소 공배수 값
  public  int lcm(int m,int n){
	  return (m*n)/gcd(m,n);
  }
}
