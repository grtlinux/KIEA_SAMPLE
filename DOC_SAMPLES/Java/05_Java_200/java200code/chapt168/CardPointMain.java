import java.util.*;
public class  CardPointMain{
	public static void main(String[] args) 
	{
		CardVectorBox box = new CardVectorBox();
		Vector cards=box.getAllCards();
		CardPoint cp=new CardPoint((Card)cards.get(0),
			                      (Card)cards.get(2));
		CardPoint cp2=new CardPoint((Card)cards.get(1),
			                      (Card)cards.get(3));
		System.out.println(cp);
		System.out.println(cp.getPoint());//점수산출
		System.out.println(cp2);
		System.out.println(cp2.getPoint());//점수산출
		if(cp.getPoint()>cp2.getPoint()){
			System.out.println(cp+" Win!!!  "+cp2+" Lose!!");
		}else if(cp.getPoint()<cp2.getPoint()){
			System.out.println(cp2+" Win!!!  "+cp+" Lose!!");
		}else{
			System.out.println(cp+" = "+cp2);
		}
	}
}
