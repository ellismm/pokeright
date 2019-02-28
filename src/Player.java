
public class Player {
	private String player = "empty";
	private Card card1, card2;
	public Player(String m) {
		player = m;
//		card1 = c1;
//		card2 = c2;
	}
	
	public String getPlayer() {
		return player;
	}
	
	public void setPlayer(String name) {
		this.player = name;
	}
	
	//Return the first card
	public Card viewCardOne() {
		return card1;
	}
	
	//Return the second card
	public Card viewCardTwo() {
		return card2;
	}
	
	//Set card one
	public void setCardOne(Card c1) {
		card1 = c1;
		
	}
	
	//Set card two
	public void setCardTwo(Card c2) {
		card2 = c2;
		
	}
	
}
