public class BernardQuizFrance {

	public int toInt(String a){
		return Integer.parseInt(a.trim());
	}
	public String toStrings(int a){
		return (a+"");
	}
	public String toStrings(int m,int n){
		return (""+m+n);
	}
	public int toInt(String a,int n){
        //return Integer.parseInt(a.charAt(n)+"");
		return a.charAt(n)-'0';
	}
	public int length(int a){
		return (a+"").length();
	}
	public int length(String s){
		return s.length();
	}
	public int [] toArray(String s){
		int count=length(s);
		int [] qui=new int [count];
		for(int i=0;i<count;i++){
			qui[i]=s.charAt(i)-'0';//'2'-'0'=숫자 2
			//qui[i]=this.toInt(s.charAt(i)+"");
		}
		return qui;
	}
	public String makef(String s){
		int count=length(s);
		String ss="";
		int tcount=0;
		while(count>tcount){  
			String tes=makef(s,tcount);
			ss+=tes;
			tcount+=toInt(tes,0);//"12"==>1이 2개 ==>2칸 뛰자
		}
		return ss;
	}//
    //n번째에 있는 숫자 + 몇개인가 를 String으로 출력
	public String makef(String s,int n){
	  int count=length(s);
	  int tempCount=0;
	  String ss="";
	  int [] qui=toArray(s);  //
	  int num=toInt(s,n);
	  for(int i=n;i<count;i++){
		  if(num==qui[i]){
			  tempCount++;
		  }else{
			  ss+=this.toStrings(tempCount,num);
			  break;
		  }
		  if(i==count-1){  //마지막 수를 처리하자. 
			  ss+=this.toStrings(tempCount,num);
			  break;
		  }
	  }
	  return ss;
	}//
    //n 번째  Bernard 결과
	public void makef(int n){
		String s="1";
		for(int i=0;i<n;i++){
	 		s=makef(s);
		}
		print(this.toArray(s));
	}//
	//일차원 배열 출력
	public static void print(int [] a){
		int m=a.length;
		for(int i=0;i<m;i++){
			System.out.print(a[i]);
		}
		System.out.print("\t\tcount:   "+m);
		System.out.println();
	}//
}
