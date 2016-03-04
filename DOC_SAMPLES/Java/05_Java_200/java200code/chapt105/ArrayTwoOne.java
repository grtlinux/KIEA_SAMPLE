public class ArrayTwoOne {

	public int [] twoToOne(int a[][]){
		int [] aa=null;
		//3*4==> 12
		int dimen1=a.length;
		int dimen2=a[0].length;
		if(dimen1<0 || dimen2 <0){
			return null;
		}else{
			aa=new int[dimen1*dimen2];
		}
		for(int i=0;i<dimen1;i++){
			for(int j=0;j<dimen2;j++){
				aa[i*dimen2+j]=a[i][j];
			}
		}
		return aa;
	}//twoToOne
}
