import java.util.Arrays;
class TetrisUtil {
	public static final int[][] TETRISA={
	{1,1,1,0}, {0,0,1,0}, {0,0,1,0}, {0,0,1,0}};
	public static final int[][] TETRISB={
	{0,0,1,0}, {0,0,1,0}, {0,0,1,0}, {0,0,1,0}};
	public static final int[][] TETRISC={
	{0,0,0,0}, {1,1,1,0}, {0,1,0,0}, {0,0,0,0}};
	public static final int[][] TETRISD={
	{0,0,0,0}, {0,1,1,0}, {0,1,1,0}, {0,0,0,0}};
}
//2차원배열을 비교하기 위해서 
class TetrisArrays{
	public static boolean equals(int [][] a,int [][] b){
		boolean isE=false;
		int m1=a.length;
		int n1=a[0].length;
		int m2=b.length;
		int n2=b[0].length;
		if(m1!=m2 || n1!=n2){
			return false;
		}
		//이차원배열비교
		for(int i=0;i<m1;i++){
			//일차원배열 비교
			if(Arrays.equals(a[i],b[i])){
				isE=true;
			}else{
				isE=false;
				break;
			}//
		}
		return isE;
	}//
}

class Tetris {
	private int[][] tetris=TetrisUtil.TETRISA;
	
	public Tetris() {}
	
	public Tetris(int[][] a) {
		this.tetris = a;
	}
	
	public int[][] getTetris() {
		return tetris;
	}
	
	public void setTetris(int[][] is) {
		tetris = is;
	}
	
	public void turnRight() {
		int[][] temp = (int[][])this.tetris.clone();
		printTetris(temp);//tetris가 계속 변하기 때문에
		//클론값도 변하는 것이 당연하지 않을 까요?
		//clone을 호출할 당시 그때의 값을 리턴 
		//turnRight하면 최종적으로 오른쪽으로 회전한
		//것이 tetris로 저장되기 때문 회전된 clone이 복사됨
		
		//아래것은 이차원 배열을 비교하는 것인데
		//항상 true가 되는데요???
		//배열도 참조타입이기 때문에 배열 ref==배열 ref는 항상 false
		//그래서 equals를 사용해야 하는데 
		//이차원이 없으니 만들어야 합니다.
		boolean isT=TetrisArrays.equals(this.tetris,temp);
		System.out.println(isT);
		int[][] temp1 = new int[tetris.length][tetris[0].length];
		int iCount = 0;
		int jCount = temp.length-1;
		
		for (int i=temp[0].length-1; i>=0; i--) {
			for (int j=0; j<temp.length; j++) {
				temp1[Math.abs(i-(temp.length-1))][j] = temp[jCount][iCount];
				//tetris[Math.abs(i-(temp.length-1))][j] = temp[jCount][iCount];
				if (jCount == 0) 
					jCount = 3;
				else
					jCount--;
			}
			iCount++;
		}
		tetris = temp1;
	}
	
	public void turnLeft() {
		
	}
	public static void printTetris(int [][] tet) {
		System.out.println("\n----------------------");
		int m = tet.length;
		int n = tet[0].length;
		for (int i=0; i<m;i++) {
			for (int j=0;j<n;j++) 
				System.out.print(tet[i][j]+" ");
			System.out.println();
		}
		System.out.println("----------------------");
	}
	public void printTetris() {
		int m = tetris.length;
		int n = tetris[0].length;
		for (int i=0; i<m;i++) {
			for (int j=0;j<n;j++) 
				System.out.print(tetris[i][j]+" ");
			System.out.println();
		}
		System.out.println("========================");
	}
}

public class TetrisMainHwang {
	public static void main(String[] args) {
		Tetris tetris = new Tetris(TetrisUtil.TETRISA);
		tetris.printTetris();
		tetris.turnRight();
		tetris.printTetris();
		tetris.turnRight();
		tetris.printTetris();
		tetris.turnRight();
		tetris.printTetris();
		//tetris = new Tetris(TetrisUtil.TETRISB);
		//tetris.printTetris();
	}
}