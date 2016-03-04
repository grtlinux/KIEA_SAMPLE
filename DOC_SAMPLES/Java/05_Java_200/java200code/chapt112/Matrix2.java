public class Matrix2 {
	public double[][] matSum(double[][] a,double[][] b){
		int m=a.length; 
		int n=b[0].length;
		double [][] c=new double[m][n];  //2차원 배열 선언 
		for(int i=0;i<m;i++){
			for(int j=0;j<n;j++){
				c[i][j]=a[i][j]+b[i][j];  
				//Sigma c[1][1]=a[1][1]+b[1][1]			 
			}
		}
		return c;
	}
	public double[][] matSub(double[][] a,double[][] b){
		int m=a.length; 
		int n=b[0].length;
		double [][] c=new double[m][n];  //2차원 배열 선언 
		for(int i=0;i<m;i++){
			for(int j=0;j<n;j++){
				c[i][j]=a[i][j]-b[i][j];  
				//Sigma c[1][1]=a[1][1]-b[1][1]			 
			}
		}
		return c;
	}
}
