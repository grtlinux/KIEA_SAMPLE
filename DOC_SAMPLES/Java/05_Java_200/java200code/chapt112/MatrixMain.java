public class MatrixMain {

	public static void main(String[] args) {
		Matrix mat=new Matrix();
		double [][] a={{1,2,3},{4,5,6}};   // 2*3 
		double [][] b={{1,2},{3,4},{5,6}}; // 3*2
		double [][] c={{1,2,3},{4,5,6},{7,8,9}};
		double [][] d={{1,0,0},{0,1,0},{0,0,1}};
		double [][] a2={{1,5,3},{7,5,9}};   // 2*3 
		Matrix.prints(a);
		System.out.println("\n --------- X -----------------\n");
		Matrix.prints(b);
		System.out.println("\n --------- = -----------------\n");
		Matrix.prints(mat.matMulti(a,b));
		System.out.println("=================================");
		Matrix.prints(a);
		System.out.println("\n --------- X -----------------\n");
		Matrix.prints(c);
		System.out.println("\n --------- = -----------------\n");
		Matrix.prints(mat.matMulti(a,c));
		System.out.println("=================================");
		Matrix.prints(a);
		System.out.println("\n --------- X -----------------\n");
		Matrix.prints(d);
		System.out.println("\n --------- = -----------------\n");
		Matrix.prints(mat.matMulti(a,d));
	}
}
