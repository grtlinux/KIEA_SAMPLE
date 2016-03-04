public class MatrixMain2 {

	public static void main(String[] args) {
		Matrix2 mat=new Matrix2();
		double [][] a={{1,2,3},{4,5,6}};   // 2*3 
		double [][] a2={{4,3,3},{6,7,3}};   // 2*3 
		
		Matrix.prints(mat.matSum(a,a2));
		Matrix.prints(mat.matSub(a,a2));
	}
}
