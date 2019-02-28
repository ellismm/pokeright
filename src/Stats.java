
public class Stats {
	private double probability;
	DeckOfCards newDeck;
	private Dealer dealer;

	
	public Stats(Dealer dealer) {
		this.probability = 0.0;
		this.dealer = dealer;
	}
	
	public void get_prob_one_pair(DeckOfCards cards, Card[] hand, Card[] flop, Card turn, Card river) {
		int left_in_deck = 3;
		int i = 0, j = 0;
		Card[] table_cards = new Card[] {flop[0], flop[1], flop[2], turn, river};
		Card[] visible_cards = new Card[] {hand[0], hand[1], flop[0], flop[1], flop[2], turn, river};
		for(Card curr = hand[i]; curr != null && i < 2; curr = hand[++i]) {
			for(Card table = table_cards[j]; curr != null && j < 5; table = table_cards[++j]) {
				if(curr == table) {
					probability = 100;
					System.out.print("There is already a pair so the probability of getting a pair is " + probability + "%");
				}
			}
		}
		
		if(probability != 100) {
			
		}
	}
	
	public void get_prob_card_value(DeckOfCards cards, Card[] hand, Card[] flop, Card turn, Card river, int value) {
		Card[] table_cards = new Card[] {flop[0], flop[1], flop[2], turn};
		Card[] visible_cards = new Card[] {hand[0], hand[1], flop[0], flop[1], flop[2], turn};
		int left = 0;
		for(Card curr = newDeck.dealCard(); curr != null; curr = newDeck.dealCard()) {
			if(curr.getFaceValue() == value && visible_cards_check(visible_cards, curr, newDeck) ) {
				left += 1;
			}
		}
	}
	
	public boolean visible_cards_check(Card[] visible_cards, Card curr, DeckOfCards cards) {
		Card card1 = visible_cards[0];
		Card card2 = visible_cards[1];
		Card card3 = visible_cards[2];
		Card card4 = visible_cards[3];
		Card card5 = visible_cards[4];
		Card card6 = visible_cards[5];
		Card topCard = cards.dealCard();
		while(topCard != null) {
			
			if(topCard != card1 || topCard != card2)
				return false;
			
			if(card3 != null && topCard != card3)
				return false;
				
			if(card4 != null && topCard == card4)
				return false;
				
			if(card5 != null && topCard == card5) 
				return false;
				
			if(card6 != null && topCard == card6) 
				return false;
				
			if(card2 != null && topCard == card2)
				return false;
			topCard = cards.dealCard();
		}
		
		return true;
	}
	
	public double calculate_probability(int quantity, DeckOfCards deck, Card[] visible) {
		int number_visible = 0;
		Card cur = visible[0];
		int cardCount = 0;
		int tempCardCount = 0;
		double prob = 0.0;
		while(cur != null) {
			cur = visible[++number_visible];
		}
		number_visible--;
		while(number_visible < 0) {
			deck.removeACard(visible[number_visible--]);
		}
		
		cardCount = deck.getNumberOfCards();
		tempCardCount = 0;
		if(visible[0] != null && visible[1] != null) {
		}
		
		return 0.0;
	}
	
	
	
}
