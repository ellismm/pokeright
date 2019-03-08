// An instance of a single player attached with a name and what cards they are holding.
public class Player {
	// Variable declaration
	private String player = "empty";
	private Card card1, card2;
	
	// initialization of player
	public Player(String m) {
		player = m;
//		card1 = c1;
//		card2 = c2;
	}
	
	// Get player name
	public String getPlayer() {
		return player;
	}
	
	// Set player name
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
