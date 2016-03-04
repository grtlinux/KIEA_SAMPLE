//package baseball;
public class Compare 
{	
	private int strike;
	private int ball;
	private int[] comp;
	private int[] play;
	public Compare(){}

	public int getStrike(){
		strikes();
		return strike;
	}
	public int getBall(){
		balls();
		return ball;
	}
	public void input(int [] a,int [] b){
		comp=a;
		play=b;
	}
	private void strikes(){
		strike=0;
		for(int i=0;i<play.length;i++){
			if(comp[i]==play[i]){strike++;}
		}
	}
	private void balls(){
		ball=0;
		for(int i=0;i<play.length;i++){
			for(int j=0;j<play.length;j++){
				if(comp[i]==play[j]  && i!=j){ball++;}
			}
		}
	}


}
