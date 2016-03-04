import java.util.*;
public class  CardPointArrayMain{
	public static void main(String[] args) 
	{
		CardArrayBox box = new CardArrayBox();
		Card [] cards=box.getAllCards();
		CardPoint cp  = new CardPoint(cards[0], cards[2]);
		CardPoint cp2 = new CardPoint(cards[1], cards[3]);
		System.out.println(cp);
		System.out.println(cp.getPoint());//점수산출
		System.out.println(cp2);
		System.out.println(cp2.getPoint());//점수산출
		if(cp.getPoint() > cp2.getPoint()){
			System.out.println(cp+" Win!!!  "+cp2+" Lose!!");
		}else if(cp.getPoint()<cp2.getPoint()){
			System.out.println(cp2+" Win!!!  "+cp+" Lose!!");
		}else{
			System.out.println(cp+" = "+cp2);
		}
	}
}
