//package baseball;
public class BaseBallMain {

	public static void main(String[] args) {
		//컴퓨터가 서로다른 3개의 숫자를 생성한다.
		//0~9중 3개의 서로다른 숫자를 생성 
		CompThink com=new CompThink(10,3);
		//컴퓨터의 숫자를 맞추기 위한것.
		InputsInt  player =new InputsInt();
		//비교를 위한것.
		Compare compare=new Compare();
		
		// 컴퓨터가 서로다른 3개의 숫자를 생성한다.
		int [] computor=com.getBall();	
		int count=0;   //10번 안에 끝나기위한것.
		while(true){   //무한 루프를 만든다.
			count++;
			//선수가 입력한다.
			int [] play=player.input(3,0,9,false);
			//컴선수와 선수가 입력한것을 심판에 넣는다.
			compare.input(computor,play);
			//스트라이크 개수
			int strike=compare.getStrike();
			//볼의 개수
			int ball=compare.getBall();
			//선수가 입력한 세수를 보여준다.
			for(int i=0;i<play.length;i++){
				System.out.print(play[i]+"   ");
			}
			//비교한 스트라이크와 볼의 개수를 보여준다.
			System.out.println(count+"  ["+strike+" strike : "+ball+"  ball]");
			//만약 스트라이크가 3개면 경기 끝
			//10번 시행안에 3스트라이크를 마들 수 있는가?
			if(strike==3){
				if(count<10){
					System.out.println("You Win!!");
				}
				break;
			}else{
				if(count>=10){
					System.out.println("You Loose!!");
					break;
				}
			}
		}
		System.out.println("Pit:"+computor[0]+":"+computor[1]+":"+computor[2]);
		System.exit(1);//gui
	}
}
