public class Magic
{
	private int width;
	private int [][]magic; 
	public static final int TOP=0;
	public static final int LEFT=0;
	private int right=width-1;
	private int bottom=width-1;
	
	public int[][] getMagic(){
		return magic;
	}
	public Magic(int width){
		reSet(width);
	}//
	public void reSet(int width){
		this.width=width;
		magic=new int[width][width];
	}//
	public int getWidth(){
		return width;
	}//
	private boolean isEmpty(int a){
		boolean isE=false;
		if(a==0){
			isE=true;
		}
		return isE;
	}//
	private void fill(int [][] m,int x, int y,int i,int j){
		m[x][y]=i*width+j+1;
	}//
	private boolean isBoundTop(int x){
		boolean isB=false;
		if(x-1<Magic.TOP){
			isB=true;
		}
		return isB;
	}//
	private boolean isBoundLeft(int y){
		boolean isB=false;
		if(y-1<Magic.LEFT){
			isB=true;
		}
		return isB;
	}//
	public void make(){
		
		int y=width/2;
		int x=Magic.TOP;
		
		for(int i=0;i<width;i++){
			for(int j=0;j<width;j++){
				if(isEmpty(magic[x][y])){
					fill(magic,x,y,i,j);
				}else {
					int tempX=x;
					int tempY=y;
					if( isBoundTop(x) ){
						x=width-1;
					}else{x=x-1;}
						
					if( isBoundLeft(y) ){
						y=width-1;
					}else{y=y-1;}

					if(!isEmpty(magic[x][y])){
						x=tempX+1;
						y=tempY;
					}
					fill(magic,x,y,i,j);
				}
			}
		}
	}//

}
