public class ArrayTwoInit {

	public static void main(String[] args) {
		System.out.println("//2차원 배열 방법 1");
		int [][] a=new int[4][3];//4X3
		a[0][0]=1;//0부터 시작
		a[0][1]=2;
		a[3][2]=5;
		//...
		println(a);
		
		System.out.println("//2차원 배열 방법 2 (JigJagged도 가능)");
		int [][] b=new int[3][];//3X?
		b[0]=new int[4]; 
		b[1]=new int[5];
		b[2]=new int[6];
		println(b);
		
		System.out.println("//2차원 배열 방법 3");
		int [][]c=new int[][]{{1,2,3,4,5},{2,3,4,5,6},{6,7,8,9,0}};
		println(c);
		
		System.out.println("//2차원 배열 방법 4");
		int [][]g={{1,2,3,4,5},{2,3,4,5,6},{6,7,8,9,0}};
		println(g);
		
		System.out.println("copy 1");
		int [][]d=new int[c.length][c[0].length];//3X5
		for(int i=0;i<c.length;i++){//deep copy
			System.arraycopy(c[i],0,d[i],0,d[i].length);
		}
		c[0][0]=-5;
		println(d);
		
		System.out.println("copy 2");
		int [][]e=new int[c.length][c[0].length];//3X5
		System.arraycopy(c,0,e,0,e.length);//shallow copy
		c[0][0]=-4;
		println(e);
		
		System.out.println("copy 3");
		int [][]f=new int[c.length][c[0].length];//3X5
		f=e; //shallow copy
		e[0][0]=-400;
		println(f);
	}
	
	public static void println(int [][] p){
		for(int i=0;i<p.length;i++){
			for(int j=0;j<p[i].length;j++){
				System.out.print("["+p[i][j]+"] ");
			}
			System.out.println();
		}
	}
}
