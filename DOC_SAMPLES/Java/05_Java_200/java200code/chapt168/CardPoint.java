import java.util.*;
public class  CardPoint{	
	
	private Card card1;
	private Card card2;
	private int points=0;
	public CardPoint(Card card1,Card card2) {	
		setCards(card1,card2);
	}//
	public void setCards(Card card1,Card card2){
		this.card1=card1;
		this.card2=card2;
		cardValue();
	}
	private int toInt(Card cc){
		int num=0;
		char c=cc.getCard().charAt(1);//H8¿¡¼­ 8
		switch(c){
			case '1' : num=11;break;
			case '2' : num=2;break;
			case '3' : num=3;break;
			case '4' : num=4;break;
			case '5' : num=5;break;
			case '6' : num=6;break;
			case '7' : num=7;break;
			case '8' : num=8;break;
			case '9' : num=9;break;
			case 'T' : num=10;break;
		}
		return num;
	}
	private void cardValue(){
		points=0;
		if(toInt(card1)==toInt(card2)){
			points=toInt(card1)*100;
		}else{
			points=((toInt(card1)+toInt(card2))%10)*10;
		}
	}
	public int getPoint(){
		//cardValue();
		return points;
	}
	public String toString(){
		return "<card1 "+card1+", card2 "+card2+">";
	}
}
