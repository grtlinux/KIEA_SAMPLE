public class CardArrayBox {
	private final int numOfCards=
		Card.deck.length*Card.stic.length;//40
	private Card [] card;
	public CardArrayBox(){
		make();
	}
	public void make(){
		init();
		fulling();
	}
	private void init(){
		card=null;
		card=new Card[numOfCards];
	}
	private void fulling(){
		int count=0;
		while(true){
			Card cc=new Card();
			if(!this.hasCard(card,cc)){
				card[count++]=cc;
			}
			if(count==numOfCards){break;}
		}
	}
	public Card[] getAllCards(){
		return card;
	}
	private  boolean hasCard(Card [] cards,Card c){
		boolean hasC=false;
		for(int i=0;i<cards.length;i++){
			if( cards[i]!=null && cards[i].equals(c)){
				hasC=true; break;
			}
		}
		return hasC;
	}
}
