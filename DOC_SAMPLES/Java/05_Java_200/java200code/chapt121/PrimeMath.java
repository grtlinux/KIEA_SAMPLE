public class PrimeMath {
   public PrimeMath(){}
   //두수를 받아서 나누어 떨어지는 가를 확인.
   public boolean isMod(int a,int b){
   	boolean isP=false ; // 기본 나누어 떨어지지 않는다.
   	if( b!=0 && a%b==0){
		isP=true; //나누어 떨어진다.
   	}
   	return isP;
   }
   //솟수인지 확인하는 메서드
   public boolean isPri(int a){
	boolean isP=true ;
	int b=(int)Math.sqrt(a);//50이면 2부터 7까지의 정수로 나눈다. 
	for(int i=2;i<=b;i++){
		if(isMod(a,i)){     //(50,2) (50,3) (50,4) .....
			isP=false;
			break;   //for탈출  //(50,2) |소수가 아니다. 
		}else isP=true;
	}
	return isP;
   }
	//약수의 개수를 구하는 메서드
   public int divisors(int a){
   	int count=1;
	for(int j=2;j<=a;j++){
		if(isMod(a,j)){
			count++;
		}
	}
	return count;
   }
   //약수를 구하는 메서드
   public int[] commonDividors(int a){
   	int [] b=null;
   	int count=1;   //1과 자기 자신 
    int num=1;
   	if(a>1){	
		count=divisors(a);  //약수개수 구하기
		b=new int[count];     //약수 개수만큼 배열 선언
		//System.out.println("count  "+count);
		b[0]=1;
		for(int j=2;j<=a;j++){
				if(isMod(a,j)){   //약수라면 
					b[num++]=j;   //약수를 보관
				}
		}	
   	}else if(a==1){
   		b=new int[1];
   		b[0]=1;
   	}	
   	return b;
   }
   //출력을 편하게 하는 메서드 
   public void printPrime(int [] array){
   	int count=array.length;
	System.out.print(count+"개  : [  ");
   	for(int i=0;i<count-1;i++){
   		System.out.print(array[i]+" , ");
   	}
	System.out.print(array[count-1]);
	System.out.println("  ]");
   }//
}
