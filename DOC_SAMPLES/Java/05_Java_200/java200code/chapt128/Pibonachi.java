public class Pibonachi {
	
	public int pibo(int n){
		if(n==1  || n==2){
			return 1;
		}else if(n>2){
			return (this.pibo(n-1)+this.pibo(n-2));
		}
		return 1; //의미없는 
	}//
	public double golden(int n){
		if(n<0){return 0;}
		else if(n==1 || n==2){return 1;}
		else{ 
			return ((double)this.pibo(n))/this.pibo(n+1);
		}
	}//
}//
