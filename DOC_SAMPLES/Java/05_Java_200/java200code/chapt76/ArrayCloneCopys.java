public class ArrayCloneCopys {

	public static void main(String[] args) {
		int [] a={8,5,2,4,6,3,7,9};
		int [] b={8,5,2,4,6,3,7,9};
		int [] c=a;    //shallow copy
		int [] d=new int[b.length];
		System.arraycopy(b,0,d,0,b.length);//deep copy
		//clone을 지원하지 않으면 CloneNotSupportedException 발생
		int [] e=(int [])b.clone();
		print(e);
		b[4]=-2;
		print(e);
		
		int [][] fa={{4,5,6,7},{6,7,8,9}};
		int [][] fb=(int[][])fa.clone();
		fa[0][0]=-1;
		print(fb);//1차원 배열 clone-> deep copy
		          //2차원 배열 clone-> shallow copy
		//2차원 배열을 deep copy 시키기 위해서 
		int [][] fc=new int[fa.length][fa[0].length];
		for(int i=0;i<fa.length;i++){
			fc[i]=(int [])fa[i].clone();
		}
		fa[0][0]=-5;
		print(fc);//fc는 fa변화에 영향을 받지 않는다.
	}
	public static void print(int [] p){
		for(int i=0;i<p.length;i++){      
			System.out.print("["+p[i]+"] ");
		}
		System.out.println();
	}
	public static void print(int [][] p){
		for(int i=0;i<p.length;i++){
			for(int j=0;j<p[i].length;j++){
				System.out.print("["+p[i][j]+"] ");
			}
			System.out.println();
		}
	}
}
