/**
 * Hanoi tower를 구하는 클래스
 * @author hyonny
 * Created on 2002. 10. 6.
 */

public class Hanois {
	int  tray=3;
	int bar=3;
	int [][] rings;
	public Hanois(int tray){
		this.tray=tray;
		rings=new int[tray][bar];
		for(int i=0;i<rings.length;i++){
			int x=2*i+1;
			rings[i][0]=x;
		}
	}
	public int[][] getRings(){
		return rings;
	}      	
	public  static void moveHanoi(int [][] a,int num, char ringA,char ringB,char ringC){
		if(num==1){
			System.out.println(ringA+" bar쪽에 있던 링이 "+ringB+" bar쪽으로 이동");
			//showHanoi(a,ringA,ringB);
		}else {
			moveHanoi(a,num-1,ringA,ringC,ringB);
			System.out.println(ringA+" bar쪽에 있던 링이 "+ringB+" bar쪽으로 이동");
			//showHanoi(a,ringA,ringB);
			moveHanoi(a,num-1,ringC,ringB,ringA);
		}
		
	}
	
	public static void  showHanoi(int [][] a, char ringA,char ringB){
		int aa=0;
		int ab=1;
		switch(ringA){
			case 'a': aa=0;break;
			case 'b': aa=1;break;
			case 'c': aa=2;break;
		}
		switch(ringB){
			case 'a': ab=0;break;
			case 'b': ab=1;break;
			case 'c': ab=2;break;
		}
		
		System.out.println(ringA+" bar쪽에 있던 링이 "+ringB+" bar쪽으로 이동");
		puts(a,aa,ab);
		
		for(int i=0;i<a.length;i++){
			System.out.print("[\t");
			for(int j=0;j<a[0].length;j++){
				System.out.print(a[i][j]+"\t");
			}
			System.out.println("]");
		}
		System.out.println("=====================================");
	}
	public static void  showHanoi(int [][] a){
		
		for(int i=0;i<a.length;i++){
			System.out.print("[\t");
			for(int j=0;j<a[0].length;j++){
				System.out.print(a[i][j]+"\t");
			}
			System.out.println("]");
		}
		System.out.println("=====================================");
	}
	public  static int   hasTray(int [][] a,int bar){
		boolean isS=false;
		int no=-1;
		for(int i=0;i<a.length;i++){
			if(a[i][bar]!=0){
				no=i;
				break;
			}
		}
		return no;
	}
	// change(a,2,2,0,0);==> 2,2의 값을 0,0으로 이동 
	//2,2의 값을 0으로 
	private  static void change(int [][] a,int aa,int ab,int ba,int bb){
		
		
		System.out.println("("+aa+","+ab+")==>"+"("+ba+","+bb+")  :");
		
		a[ba][bb]=a[aa][ab];
		a[aa][ab]=0;
	}
	
	public static void puts(int [][]a,int barA, int barB){
		if(hasTray(a,barA)!=-1){
			if(hasTray(a,barB)!=-1){
				change(a,hasTray(a,barA),barA,hasTray(a,barB)-1,barB);
			}else{
				change(a,hasTray(a,barA),barA,a.length-1,barB);
			}
		}

	}
	
	
}
