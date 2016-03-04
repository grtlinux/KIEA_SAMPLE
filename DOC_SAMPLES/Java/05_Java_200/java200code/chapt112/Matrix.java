public class Matrix {
	//public static void matMulti(double[][] a,double[][] b){
	public double[][] matMulti(double[][] a,double[][] b){
		int m=a.length;    //m*m1  n1*n   ==> m*n
		int m1=a[0].length;//m*m1  n1*n   ==> m*n
		int n1=b.length;   //m*m1  n1*n   ==> m*n
		int n=b[0].length; //m*m1  n1*n   ==> m*n
		double [][] c=new double[m][n];  //2차원 배열 선언 
		
		//2차원 배열 값 할당 
		if(m1==n1){     //m1==n1이 같으면 ==> m by n 행렬 완성 
			for(int i=0;i<m;i++){
				for(int j=0;j<n;j++){
					c[i][j]=0.0;
					for(int k=0;k<n1;k++){
						c[i][j]+=a[i][k]*b[k][j];  
						//Sigma c[1][1]=a[1][0]*b[0][1]+
						//              a[1][1]*b[1][1]+
						//              a[1][2]*b[2][1];
					}
				}
			}
		}
		return c;
	}
	//2차원 배열 출력
	public static void prints(double[][] pp){
		if(pp.length==0){return;}
		for(int i=0;i<pp.length;i++){
			System.out.print("[ ");
			for(int j=0;j<pp[0].length-1;j++){
				System.out.print(pp[i][j]+" , ");
			}
			System.out.println(pp[i][pp[0].length-1]+" ]");
		}
	}
}
/*
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
*/
