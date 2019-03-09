import java.io.IOException;
import java.util.Scanner;

//@SuppressWarnings("IOException")
public class Dealer  {//open the dealer class
	//variable for the number people seated at the table, the name of the players, and the default players if not enough enough are given
	private int seated;
	//An array for 3 cards that will be the burn cards
	private Card[] burns = new Card[] {null, null, null};
	//An array for 3 cards that will be the flop cards
	private Card[] flop = new Card[] {null, null, null};
	//The Turn Card
	private Card turn;
	//The river Card
	private Card river;
	private DeckOfCards deck = new DeckOfCards();
	//The number of players currently playing
	private int playerCount;
	//How many cards are currently viewable for each person
	public int cardCount;
	
	public Stats stats = new Stats(this);
	
//	private DeckOfCards play = new DeckOfCards();
	
//	private String[] players = {"empty", "empty", "empty", "empty", "empty", "empty", "empty", "empty", "empty", "empty"};
	
	//An array that represents seats that are taken and seats that are empty
	private Player[] players = new Player[] {
			new Player("empty"),
			new Player("empty"),
			new Player("empty"),
			new Player("empty"),
			new Player("empty"),
			new Player("empty"),
			new Player("empty"),
			new Player("empty"),
			new Player("empty"),
			new Player("empty")};
//	private Player[] players2 = new Player[10] { new Player("empty")};
	
	//If not given user names then use these default names
	private String[] defaultPlayers = {"Player1", "Player2", "Player3", "Player4", "Player5", "Player6", "Player7", "Player8", "Player9", "Player10"};
	
//	//Creating a deck for the dealer to deal
//	DeckOfCards bicycle = new DeckOfCards(); 
	
	//Define the constructor that assigns the position for each player. A method will be created to switch seats.
	public Dealer(String[] player, int seated) throws IOException {//open
		this.seated = seated;
		//sift through the given names and assign them to a seat
		if(player.length == seated) {//open
			for(int i = 0; i < seated; i++) {//open				
				(this.players[i]).setPlayer(player[i]);
				playerCount += 1;
			}//close
		}//close
		
		//If not given names then use the default names
		else {//open
			for(int i = 0; i < seated; i++) {//open
				this.players[i].setPlayer(defaultPlayers[i]);
			}//close
		}//close
	}//close
	
	
	//returns the name of the player from a given seat number
	public String getPlayer(int seat) {//open
		return players[seat - 1].getPlayer();
	}//close
	
	//Returns the player with all attributes after given a name
	public Player findPlayer(String name) {
		for(int i = 0; i < 10; i++) {
			if((players[i].getPlayer().toLowerCase()).equals(name.toLowerCase())) {
				return players[i];
			}
		}
		return null;
	}
	
	//Returns the card one of a given player
	public Card getCardOne(int seat) {//open
		return players[seat - 1].viewCardOne();
	}//close
	
	//Returns the cardone of a given player
	public Card getCardTwo(int seat) {//open
		return players[seat - 1].viewCardTwo();
	}//close
	
	//Return the seat it which a given player is seated at.
	public int getSeat(String name) {//open
		int x = 0;
		for(int i= 0; i < 10; i++) {//open
			if((players[i].getPlayer().toLowerCase()).equals(name.toLowerCase())) {//open
				x = i + 1;
			}//close
		}//close
		return x;
	}//close
	
	//Returns the number of players seated
	public int getSeatsTaken() {//open
		int count  = 0;
		int i = 0;
		while(i < 10) {//open
			if(players[i].getPlayer() != "empty") {//open
				count++;
			}//close
			i++;
		}//close
		return count;
	}//close
	
	//Returns the number of seats open
	public int  getSeatsOpen() {//open
		return 10 - getSeatsTaken();
	}//close
	
	//swiches a given player from current seat to the seat requested
	public void switchSeats(String name, int seat) {//open
		if(seat != 10) {
			int i = 0;
			while(i < 10) {//open
				if((players[i].getPlayer().toLowerCase()).equals(name.toLowerCase())) {//open
					break;
				}//close
				i++;
			}//close
			players[seat - 1].setPlayer(name);
			players[i].setPlayer("empty");
		}
		
		System.out.println("The table is full. you cannot switch seats");
		
	}//close
	
	//Removes a player from a given seat number
	public void removePlayer(int seat) {//open
		players[seat - 1].setPlayer("empty");
	}//close
	
	//Adds a player from a given name and seat number
	public void addPlayer(String name, int seat) {//open
		if(players[seat - 1].getPlayer() == "empty") {//open
			players[seat - 1].setPlayer(name);
		}//close
		
		else {//open
			System.out.println("This seat is taken. Find a new seat!!" );
			System.out.print("seat? " );
			Scanner again = new Scanner(System.in);
			int n = again.nextInt();
			addPlayer(name, n);
		}//close
	}//close
	
	//adds an unknown player
	public void addPlayer(int seat) {//open
		int j = 0;
		int i = 0;
		
		while(i < 10) {//open
			if(players[i].getPlayer() == defaultPlayers[j]) {//open
				j++;
				i = 0;
			}//close
			if(i == 9) {//open
				break;
			}//close
			else {//open
				i++;
			}//close
		}//close
		
		System.out.println("defaulPlayer[" + i + "]: " + defaultPlayers[i]);
		addPlayer(defaultPlayers[j], seat);
	}//close
	
	//This method calls a method in DeckOfCards that shuffles the cards
	public void shuffleCards() {
		deck.shuffle();
	}
	
	
	//This method deals cards to the players currently at the table
	public void dealCards() {
		
		//Dealing Card 1
		for(int i = 0, j = 0; i < getSeatsTaken(); i++) {
			while(players[j].getPlayer() == "empty") {
				j++;
			}
			players[j].setCardOne(deck.dealCard());
			j++;
		}
		
		//Dealing Card 2
		for(int i = 0, j = 0; i < getSeatsTaken(); i++) {
			while(players[j].getPlayer() == "empty") {
				j++;
			}
			players[j].setCardTwo(deck.dealCard());
			j++;
		}
	}
	
	//deals the burn cards
	public void setBurnCard() {
		for(int i = 0; i < 3; i++) {
			if(burns[i] == null) {
				burns[i] = deck.dealCard();
				break;
			}
		}
	}
	
	//Deals the flop cards
	public void setFlopCards() {
		for(int i = 0; i < 3; i++) {
			if(flop[i] == null) {
				flop[i] = deck.dealCard();
			}
		}
	}
	
	//deals the turn card
	public void setTurnCard() {
		turn = deck.dealCard();
	}
	
	//Deals the river card
	public void setRiverCard() {
		river = deck.dealCard();
	}
	
	//displays the turn cards
	public void getBurnCards() {
		for(int i = 0; i < 3; i++) {
			if(burns[i] != null) {
				System.out.println("burn card " + i + " " + burns[i]);
			}
		}
	}
	
	//Displays the flop cards
	public void getFlopCards() {
		for(int i = 0; i < 3; i++) {
			if(flop[i] != null) {
				System.out.println("flop card " + (i + 1) + "********** " + flop[i]);
				System.out.println("flop card " + (i + 2) + "********** " + flop[i + 1]);
				System.out.println("flop card " + (i + 3) + "********** " + flop[i + 2]);
			}
			i += 3;
		}
	}
	
	//displays the turn card
	public void getTurnCard() {
		if(turn != null) {
			System.out.println("turn card  ********** " + turn);
		}
		
	}
	
	//displays the river card
	public void getRiverCard() {
		if(turn != null) {
			System.out.println("River card ********** " + river);
		}
		
	}

	//Return the number of cards left in the deck
	public int getNumberOfCards() {
		return deck.getNumberOfCards();
	}
	
	//Collects the all the cards that have been dealt and puts them back in the deck
	public void collectTheCards() {
		for(int i = 0; i < 3; i++) {
			if(burns[i] != null) {
				deck.collect(burns[i]);
			}
		}
		
		for(int i = 0; i < 3; i++) {
			if(flop[i] != null) {
				deck.collect(flop[i]);
			}
		}
		
		if(turn != null) {
			deck.collect(turn);
		}
		
		if(river != null) {
			deck.collect(river);
		}
		
		for(int i = 0; i < 10; i++) {
			if(players[i].getPlayer() != "empty") {
				deck.collect(players[i].viewCardOne());
				deck.collect(players[i].viewCardTwo());
			}
		}
	}
	
	//Returns an array of cards after sorting a given array of cards
	private Card[] sortCards(Card[] uCards) {
		//If sorted is false then do a recursive call
		boolean sorted = true;
		
		//if there are only 2 cards display then use this method
		if(cardCount == 2) {
			if(uCards[0].getFaceValue() < uCards[1].getFaceValue()) {
				uCards = switchCards(uCards, 0, 1);
			}
		}
		
		//if there are 5 cards on display for each player then use this method
		else if(cardCount == 5) {
			for(int i = 0; i < 4; i++) {
				if(uCards[i].getFaceValue() < uCards[i + 1].getFaceValue()) {
					uCards = switchCards(uCards, i, i + 1);
					sorted = false;
				}
			}
		}
		
		//if there are 6 cards on display for each player then use this method
		else if(cardCount == 6) {
			for(int i = 0; i < 5; i++) {
				if(uCards[i].getFaceValue() < uCards[i + 1].getFaceValue()) {
					uCards = switchCards(uCards, i, i + 1);
					sorted = false;
				}
			}
		}
		
		//if all the cards, 7, are on display for each player then use this method
		else if (cardCount == 7) {
			for(int i = 0; i < 6; i++) {
				if(uCards[i].getFaceValue() < uCards[i + 1].getFaceValue()) {
					uCards = switchCards(uCards, i, i + 1);
					sorted = false;
				}
			}
		}
		
		//if sorted is false the do a recursive call
//		if(!sorted) {
//			uCards = sortCards(uCards);
//		}
		return !sorted ? sortCards(uCards) : uCards;
	}
	
	//prints the best high 5 cards checking only for high cards
	public void getHighCard(String name) {
		Player onePlayer = findPlayer(name);
		Card[] allCards = new Card[] {onePlayer.viewCardOne(), onePlayer.viewCardTwo(), flop[0], flop[1], flop[2], turn, river};
		allCards = sortCards(allCards);
		
		System.out.println("The high card hand for " + onePlayer.getPlayer() + " is\n " + allCards[0] + "\n" + allCards[1] + "\n" + allCards[2] + "\n" + allCards[3] + "\n" + allCards[4]);
	}
	
	//Returns all the hands within an array in sorted order
	public Card[] getCards(Player onePlayer) {
		Card[] allCards = null;
		//if there are only 2 cards then use this method
		if(flop[0] == null) {
			cardCount = 2;
			allCards = new Card[] {onePlayer.viewCardOne(), onePlayer.viewCardTwo()};
		}
		
		//if there are 5 cards on display for each player then use this method
		else if(turn == null) {
			cardCount = 5;
			allCards = new Card[] {onePlayer.viewCardOne(), onePlayer.viewCardTwo(), flop[0], flop[1], flop[2]};
		}
		
		//if there are 6 cards on display for each player then use this method
		else if (river == null) {
			cardCount = 6;
			allCards = new Card[] {onePlayer.viewCardOne(), onePlayer.viewCardTwo(), flop[0], flop[1], flop[2], turn};
		}
		
		//if all the cards, 7, are on display for each player then use this method
		else {
			cardCount = 7;
			allCards = new Card[] {onePlayer.viewCardOne(), onePlayer.viewCardTwo(), flop[0], flop[1], flop[2], turn, river};
		}
		
		//Sort the cards and then return the cards
		return sortCards(allCards);
		
	}
	
	//Check to see which hand has the best high card hand
	public void getHighHand2() {
		
		//Pick a player to compare the other players to, in this case the first player
		Player player1 = players[0];
		Player winner = player1;
		//get the hand of the player
		Card[] win = getCards(player1);
		//Create an array to store all the players with a winning hand just in case there is a tie
		Player[] winners = new Player[playerCount];
		//use the player one as the initial comparison hand
		winners[0] = players[0];
		
		//call the helper function with the variables just created
		getHighHand2b(winner, win, 1, 0, winners);
	}
	
	//recursive helper function of printHighCard2
	private void getHighHand2b(Player winner, Card[] winCards, int iPlayer, int start,Player[] winners) {
		//if the current player is empty then there is no need to compare
		if (iPlayer != 10 && players[iPlayer].getPlayer() == "empty") {
			getHighHand2b(winner, winCards, ++iPlayer, start, winners);
		}
		
		//If the current player position is not null
		else if(iPlayer != 10 && winners[iPlayer] != null) {
			//Get the next player seated
			Player player2 = winners[iPlayer];
			//get the players hand
			Card[] card2 = getCards(player2);
			//Compare the current winning hand with the the next player seated
			int winning = compareHighTwoHands(winCards, card2);
			
			//if winning equals 1 then there is no change in the winner
			if(winning == 1) {				
				getHighHand2b(winner, winCards, ++iPlayer, start, winners);
			}
			
			//if winning equals 2 then the winner is changed with current player seated
			else if (winning == 2) {
				//if start > 0 then that means there are currently more than 1 winner
				//so we must empty the winners array
				if(start > 0 ) {
					for(int i = 0; i < playerCount; i++) {
						winners[i] = null;
			
					}
				}
				winners[start] = players[iPlayer];
				getHighHand2b(player2, card2, ++iPlayer, start, winners);
			}
			
			//If the winning is 3 then the comparison is tied so add the current player
			//seated to the winners array.
			else if (winning == 3) {
				winners[start + 1] = players[start + 1];
				start++;
				getHighHand2b(winner, winCards, ++iPlayer, start, winners);
			}
		}
		
		//After comparing all the hands, call the method that displays the winners
		//along with their hands
		else {
			getHighHandWinners(winners, " with a HIGH HAND of");
		}		
	}
	
	//Prints the winning player or players
	public void getHighHandWinners(Player[] winners, String hand) {
		int i = 0;
		while(winners[i] != null) {
			//get one one winner at a time and their hand and print it out
			Player winner = winners[i];
			Card[] win = getCards(winners[i]);
			System.out.println("The winning Player is " + winner.getPlayer() + " in seat " + getSeat(winner.getPlayer()) + hand + "\n" 
					+ win[0] + "\n" + win[1] + "\n" + win[2] + "\n" + win[3] + "\n" + win[4]);
			i++;
		}
	}
	
	//compares two sorted hands
	public int compareHighTwoHands(Card[] cards1, Card[] cards2) {
		int cards = cardCount;
		
		//We only need to compare the top 5 cards or less if there aren't 5 cards
		//visible yet
		if(cards > 5) {
			cards = 5;
		}
		
		//Go through each card one by one and find which card is greater
		for(int i = 0; i < cardCount; i++) {
			//if the first hand is greater than the second then return 1
			if(cards1[i].getFaceValue() > cards2[i].getFaceValue()) {
				return 1;
			}
			
			//if the second hand is greater than the first then return 2
			else if(cards1[i].getFaceValue() < cards2[i].getFaceValue()) {
				return 2;
			}
			
	
		}
		
		//if the hands are equal then return 3
		return 3;			
	}
	
	//Checks a players hand for a pair
	public Card[] sortOnePair(Card[] uCards) {
		uCards = sortCards(uCards);
		//Call to the helper function
		return sortOnePairb(uCards, 0);
	}
	
	// a recursive helper method for sortOnePair method
	private Card[] sortOnePairb(Card[] uCards, int index) {
		
		//when the index is equal to the card count then stop recursive calling 
		if(index != cardCount) {

			//There is no need to compare the same index so add to the one to compare
			//to the next one.
			int i = index + 1;
			
			//As long as i is less than the card count since i is used as an index
			while (i < cardCount) {
				//if a pair is found enter the loop
				if((uCards[index].getFaceValue()) == (uCards[i].getFaceValue()) && index != i) {
					//every pair needs to be placed as the first 2 in the hand
					uCards = switchCards(uCards, index, 0);
					uCards = switchCards(uCards, i, 1);
					
					//if the index is equal to 3 when the pair is found then the first 2 cards
					//that were switched need to be right after the pair that was found at
					//indexes 2 and 3
					if(index == 3) {
						uCards = switchCards(uCards, 2, 3);
						uCards = switchCards(uCards, 3, 4);
					}
					
					//if the index is equal to 4 when the pair is found then the first 2 cards
					//that were switched need to be right after the pair that was found at
					//indexes 2 and 3. Also the make sure the original card that was at index 3
					// is now at index 4
					else if(index == 4) {
						uCards = switchCards(uCards, 2, 4);
						uCards = switchCards(uCards, 3, 4);
						uCards = switchCards(uCards, 3, 6);
					}
					
					//if the index is equal to 4 when the pair is found then the first 2 cards
					//that were switched need to be right after the pair that was found at
					//indexes 2 and 3. Also the make sure the original card that was at index 3
					// is now at index 4
					else if(index == 5) {
						uCards = switchCards(uCards, 2, 5);
						uCards = switchCards(uCards, 3, 4);
						uCards = switchCards(uCards, 3, 6);
					}
				break;
				}
				i++;
			}
			
			//continue to recursive call until index is equal to the card count
			uCards = sortOnePairb(uCards, ++index);
		}
		return uCards;
	}
	
//	// Print the hand if it there is one pair
//	public void getOnePair() {
//		boolean onePair = false;
//		
//		//s
//		for(int i = 0; i < 10; i++) {
//			if(players[i].getPlayer() != "empty") {
//
//				Card[] cards = getCards(players[i]);
//				cards = sortOnePair(cards); 
//				if(cards[0].getFaceValue() == cards[1].getFaceValue()) {
//					onePair = true;
//					System.out.println(players[i].getPlayer() + " in seat " + getSeat(players[i].getPlayer()) + " has ONE PAIR with a hand of\n"
//							+ cards[0] + "\n" + cards[1] + "\n" + cards[2] + "\n" + cards[3] + "\n" + cards[4] + "\n");
//				}
//			}
//		}
//		if(!onePair) {
//			getHighHand2();
//		}
//	}
	
	//prints the winner(s) with a one pair and their hand(s)
	public void getPairHandWinners(Player[] winners, String hand) {
		int i = 0;
		//At most, there will be as many winners as players seated
		while(i != playerCount && winners[i] != null) {
			//get the cards of the current winner
			Player winner = winners[i];
			Card[] win = getCards(winners[i]);
			//sort the cards for a one pair hand
			win = sortOnePair(win);
			
			// print the hand
			System.out.println("The winning Player is " + winner.getPlayer() + " in seat " + getSeat(winner.getPlayer()) + hand + "\n" 
					+ win[0] + "\n" + win[1] + "\n" + win[2] + "\n" + win[3] + "\n" + win[4] + "\n");
			
			//increment the index to determine if there is another winner
			i++;
		}
	}
	
	public void getOnePair2() {
		Player[] winners = new Player[playerCount];
		int n = 0;
		boolean onePair = false;
		
		//Sift through every player and determine if the player has a pair.
		for(int i = 0; i < 10; i++) {
			
			//if the seat is empty then skip it
			if(players[i].getPlayer() != "empty") {
				
				//get the cards of the current player
				Card[] cards = getCards(players[i]);
				//sort the cards for the one pair
				cards = sortOnePair(cards); 
				
				//if the the current player does have a one pair then add them to the
				//array
				if(cards[0].getFaceValue() == cards[1].getFaceValue()) {
					winners[n] = players[i];
					n++;
					onePair = true;

				}
			}
		}
		
		//one then enter this condition
		if(onePair) {
			//find the ultimate winner(s)
			winners = compareOnePairHands(winners);
			
			//Make a call to the method that will print the winners and their hands
			getPairHandWinners(winners, " with a ONE PAIR hand of");
		}
		
		//If there is no player with a pair then make a call to the method that
		//determines the high hand winner(s)
		else {
			getHighHand2();
		}
	}
	
	public Player[] compareOnePairHands(Player[] winners) {
		//make an array to store all the real winner(s)
		Player[] officialWinners = new Player[playerCount];
		
		//there has to be at least one real winner so set the first player from the
		//given array as the current real winner
		officialWinners[0] = winners[0];
		
		//set the player first compare to as the first player from the given array 
		Player winner = winners[0];		
		//get the cards of the player that will be compared to
		Card[] win = getCards(winner);
		//sort the cards for a one pair hand
		win = sortOnePair(win);
		
		//make a call to the helper method with the variables created
		winners = compareOnePairHandsb(winner, win, 1, 0, winners, officialWinners);
		return winners;
	}
	
	
	public Player[] compareOnePairHandsb(Player winner, Card[] win, int iPlayer, int start,Player[] winners, Player[] officialWinners) {
		
		//if there are no more players with a one pair the stop making recursive calls
		if (iPlayer != playerCount && winners[iPlayer] == null) {
			return officialWinners;
		}
		
		// as long as the all the winners have not been compared then enter this condition
		else if(iPlayer != playerCount) {
			//Get the next player to compare to the current real winner
			Player player2 = winners[iPlayer];
			//get the cards of this winner
			Card[] card2 = getCards(player2);
			//sort the cards for a one pair hand
			card2 = sortOnePair(card2);
			
			//make a call to the method to compare the current real winner and the
			//the next player to compare to
			int winning = compareHighTwoHands(win, card2);
			
			//If winning is equal 1 then there is no change in the real winner
			if(winning == 1) {				
				compareOnePairHandsb(winner, win, ++iPlayer, start, winners, officialWinners);
			}
			
			//If winning is equal to 2 then the next player that was compared to is
			// now the real winner
			else if (winning == 2) {
				
				//if there are more than one real winners then set that to 0
				if(start > 0 ) {
					for(int i = 0; i < playerCount; i++) {
						officialWinners[i] = null;
			
					}
				}
				start = 0;
				officialWinners[start] = winners[iPlayer];
				compareOnePairHandsb(player2, card2, ++iPlayer, ++start, winners, officialWinners);
			}
			
			//if winning is equal to 3 then that means the two hands were equal and
			//need to add the current player to part of the real winners
			else if (winning == 3) {
				officialWinners[start] = winners[iPlayer];
				compareOnePairHandsb(winner, win, ++iPlayer, ++start, winners, officialWinners);
			}
		}
		return officialWinners;
	}
	
	//Prints the hand if there is two pairs
	public void getTwoPair() {
		
		boolean twoPair = false;
		for(int i = 0; i < 10; i++) {
			if(players[i].getPlayer() != "empty") {
				Card[] cards = new Card[] {players[i].viewCardOne(), players[i].viewCardTwo(), flop[0], flop[1], flop[2], turn, river};
				cards = sortOnePair(cards);
				if(cards[0].getFaceValue() == cards[1].getFaceValue() && cards[2].getFaceValue() == cards[3].getFaceValue()) {
					twoPair = true;
					System.out.println(players[i].getPlayer() + " in seat " + getSeat(players[i].getPlayer()) + " has two pair with a hand of\n"
							+ cards[0] + "\n" + cards[1] + "\n" + cards[2] + "\n" + cards[3] + "\n" + cards[4] + "\n");
				}
			}
		}
		if(!twoPair) {
			getOnePair2();
		}
	}
	
	//A second method to find a two hand pair
	public void getTwoPair2() {
		Player[] winners = new Player[playerCount];
		int n = 0;
		boolean twoPair = false;
		for(int i = 0; i < 10; i++) {
			if(players[i].getPlayer() != "empty") {
				Card[] cards = getCards(players[i]);
				cards = sortTwoPair(cards);
				if(cards[0].getFaceValue() == cards[1].getFaceValue() && cards[2].getFaceValue() == cards[3].getFaceValue()) {
					twoPair = true;
					winners[n] = players[i];
					n += 1;
				}
			}
		}
		if(!twoPair) {
			getOnePair2();
		}
		else {
			winners = compareOnePairHands(winners);
			getTwoPairHandWinners(winners, " with a TWO PAIR hand of");
		}
	}
	
	public void getTwoPairHandWinners(Player[] winners, String hand) {
		int i = 0;
		while(i != playerCount && winners[i] != null) {
			Player winner = winners[i];
			Card[] win = getCards(winners[i]);
			win = sortTwoPair(win);
			System.out.println("The winning Player is " + winner.getPlayer() + " in seat " + getSeat(winner.getPlayer()) + hand + "\n" 
					+ win[0] + "\n" + win[1] + "\n" + win[2] + "\n" + win[3] + "\n" + win[4] + "\n");
			i++;
			if((win[4].getFaceValue() < win[5].getFaceValue()) 
					|| (win[4].getFaceValue() < win[6].getFaceValue())) {
				System.out.println("\n*********************\n***********This hand is incorrect!!!!*******\n******\n");
			}
		}
	}
	
	
	// A method to sort a hand to determine if there is two pair
	public Card[] sortTwoPair(Card[] uCards) { 
		return sortTwoPairb(uCards, 0);
	}
	
	// a helper function for sortTwoPair
	private Card[] sortTwoPairb(Card[] uCards, int index) {
		boolean paired = false;
		if(index < 6) {

			
			int i = index;
			while (i < 7) {
				if((uCards[index].getFaceValue()) == (uCards[i].getFaceValue()) && index != i) {
					uCards = switchCards(uCards, index, 0);
					uCards = switchCards(uCards, i, 1);
					paired = true;
					if (index > 2) {
						uCards = switchCards(uCards, index, 2);
						uCards = switchCards(uCards, i, 3);
					}
					if (index > 3) {
						uCards = switchCards(uCards, index, 3);
						uCards = switchCards(uCards, i, 4);
					}
					if (index > 4) {
						uCards = switchCards(uCards, index, 4);
						uCards = switchCards(uCards, i, 5);
					}
					break;
				}

				
				i++;
			}
			if(uCards[0].getFaceValue() == (uCards[1].getFaceValue())) {
				paired = true;
				index = 6;
			}
			uCards = sortTwoPairb(uCards, ++index);
			
		}
		if(paired) {
			return sortTwoPairc(uCards, 2);
		}
		else
			return uCards;
		
	}
	
	// A helper function for sortTwoPairb
	private Card[] sortTwoPairc(Card[] uCards, int index) {
		boolean paired = false;
		if(index < 6) {

			
			int i = index;
			while (i < 7) {
				if((uCards[index].getFaceValue()) == (uCards[i].getFaceValue()) && index != i) {
					uCards = switchCards(uCards, index, 2);
					uCards = switchCards(uCards, i, 3);
					paired = true;
					if (index > 4) {
						uCards = switchCards(uCards, index, 4);
						uCards = switchCards(uCards, i, 5);
						
					}
					break;
				}

				i++;
			}
			if(paired) {
				index = 6;
			}
			uCards = sortTwoPairc(uCards, ++index);
			
		}
		return uCards;
	}
	
	//A sorting method for three of a kind
	private Card[] sortThreeKind(Card[] uCards) {
		uCards = sortOnePair(uCards);
		if (uCards[0].getFaceValue() == uCards[1].getFaceValue()) {
			int i = 3;
			while(i < 7) {
				if(uCards[0].getFaceValue() == uCards[i].getFaceValue()) {
					uCards = switchCards(uCards, i, 2);
					
					if(i == 4) {
						uCards = switchCards(uCards, 3, 4);
					}
					
					else if (i == 5) {
						uCards = switchCards(uCards, 3, 5);
						uCards = switchCards(uCards, 4, 5);
					}
					
					else if (i == 6) {
						uCards = switchCards(uCards, 3, 6);
						uCards = switchCards(uCards, 4, 6);
					}
					break;
				}
				
				
				i++;
			}
		}
		
		else if (uCards[2].getFaceValue() == uCards[3].getFaceValue()) {
			int i = 5;
			while(i < 7) {
				if(uCards[2].getFaceValue() == uCards[i].getFaceValue()) {
					uCards = switchCards(uCards, i, 4);
					
					uCards = switchCards(uCards, 0, 3);
					
					uCards = switchCards(uCards, 1, 4);
					break;
				}
				
				
				i++;
			}
		}
		return uCards;
	}
	
	//Another method to sorting three of a kind
	public Card[] sortThreeKind2(Card[] uCards) {
		int found = 7;
		int row = 1;
		for(int i = 0; i < 5; i++ ) {
			row = 1;
			for(int j = i + 1; j < 7; j++) {
				if(uCards[i].getFaceValue() == uCards[j].getFaceValue()) {
					row += 1;
				}
				if(row  > 2 && found > 6) {
					found = i;
					break;
				}
				else if(row > 2 && uCards[i].getFaceValue() > uCards[found].getFaceValue()) {
					found = i;
					break;
				}
			}
			
		}
		if(found < 7) {
			return sortThreeKind2b(found, uCards);
		}
		
		return uCards;
		
	}
	
	// A helper function for sorthreeKind2
	public Card[] sortThreeKind2b(int found, Card[] uCards) {
		Card temp = uCards[found];
		uCards[found] = uCards[0];
		uCards[0] = temp;
		boolean set = false;
		if(found > 1) {
			uCards = switchCards(uCards, found, 1);
			if(found > 2) {
				uCards = switchCards(uCards, found, 2);
			}
			if(found > 3) {
				temp = uCards[found];
				uCards[found] = uCards[3];
				uCards[3] = temp;
				uCards = switchCards(uCards, found, 3);
			}
			if(found > 4) {
				uCards = switchCards(uCards, found, 4);
			}
			if(found > 5) {
				uCards = switchCards(uCards, found, 5);
			}
		}
		
		for(int i = 1; i < 7; i++) {
			if((uCards[0].getFaceValue() == uCards[i].getFaceValue())) {
				if(!set) {
					uCards = switchCards(uCards, i, 1);
				}
				else {
					uCards = switchCards(uCards, i, 2);
				}
				
				if(i > 2 && !set) {
					uCards = switchCards(uCards, i, 2);
				}
				if(i > 3) {
					uCards = switchCards(uCards, i, 3);
				}
				if(i > 4) {
					uCards = switchCards(uCards, i, 4);
				}
				if(i > 5) {
					uCards = switchCards(uCards, i, 5);
				}
				if(set) {
					break;
				}
			}
		}
		return uCards;
	}
	
	// Prints the hand if it is a three of a kind
	public void getThreeOfKind() {
		boolean three = false;
		
		for(int i = 0; i < 10; i++) {
			
			
			if(players[i].getPlayer() != "empty") {
				
				Card[] cards = new Card[] {players[i].viewCardOne(), players[i].viewCardTwo(), flop[0], flop[1], flop[2], turn, river};
				cards = sortThreeKind(cards); 
				
				if((cards[1].getFaceValue() == cards[0].getFaceValue()) && (cards[0].getFaceValue() == cards[2].getFaceValue())) {
					
					three = true;
					System.out.println(players[i].getPlayer() + " in seat " + getSeat(players[i].getPlayer()) + " has three of a kind with a hand of\n"
							+ cards[0] + "\n" + cards[1] + "\n" + cards[2] + "\n" + cards[3] + "\n" + cards[4] + "\n");
				}
			}
		}
		if(!three) {
			getTwoPair();
		}
	}
	
	// Determine the highest three of a kind hand
	public void getThreeKindWinners(Player[] winners, String hand) {
		int i = 0;
		while(i != playerCount && winners[i] != null) {
			Player winner = winners[i];
			Card[] win = getCards(winners[i]);
			win = sortThreeKind2(win);
			System.out.println("The winning Player is " + winner.getPlayer() + " in seat " + getSeat(winner.getPlayer()) + hand + "\n" 
					+ win[0] + "\n" + win[1] + "\n" + win[2] + "\n" + win[3] + "\n" + win[4] + "\n");
			i++;
			if((win[4].getFaceValue() < win[5].getFaceValue()) 
					|| (win[4].getFaceValue() < win[6].getFaceValue())) {
				System.out.println("\n*********************\n***********This hand is incorrect!!!!*******\n******\n");
			}
			if((win[4].getFaceValue() < win[5].getFaceValue()) 
					|| (win[4].getFaceValue() < win[6].getFaceValue()) || (win[3].getFaceValue() < win[4].getFaceValue())) {
				System.out.println("\n*********************\n***********This hand is incorrect!!!!*******\n******\n");
			}
		}
	}
	
	
	//A second version of getting a 3 of a kind
	public void getThreeOfKind2() {
		Player[] winners = new Player[playerCount];
		int n = 0;
		boolean three = false;
		
		for(int i = 0; i < 10; i++) {
			
			
			if(players[i].getPlayer() != "empty") {
				
				Card[] cards = new Card[] {players[i].viewCardOne(), players[i].viewCardTwo(), flop[0], flop[1], flop[2], turn, river};
				cards = sortThreeKind(cards); 
				
				if((cards[1].getFaceValue() == cards[0].getFaceValue()) && (cards[0].getFaceValue() == cards[2].getFaceValue())) {
					winners[n] = players[i];
					n++;
					three = true;
				}
			}
		}
		if(!three) {
			getTwoPair2();
		}
		
		else {
			winners = compareThreeKind(winners);
			getThreeKindWinners(winners, " has THREE OF A KIND with a hand of");
		}
	}
	
	// A method to compare three of a kind hands
	public Player[] compareThreeKind(Player[] winners) {
		Player[] officialWinners = new Player[playerCount];
		Player player1 = winners[0];
		officialWinners[0] = winners[0];
//		Player player2 = winners[1];
		Player winner = player1;
		Card[] win = getCards(player1);
		win = sortThreeKind2(win);

		
		winners = compareThreeKindb(winner, win, 1, 0, winners, officialWinners);
		return winners;
	}
	
	// helper function for comareThreeKind method
	public Player[] compareThreeKindb(Player winner, Card[] win, int iPlayer, int start,Player[] winners, Player[] officialWinners) {

		
		if (iPlayer != playerCount && winners[iPlayer] == null) {
			return officialWinners;
		}

		
		else if(iPlayer != playerCount) {
			Player player2 = winners[iPlayer];
			Card[] card2 = getCards(player2);
			card2 = sortThreeKind2(card2);
			int winning = compareHighTwoHands(win, card2);
			if(winning == 1) {				
				compareThreeKindb(winner, win, ++iPlayer, start, winners, officialWinners);
			}
			
			else if (winning == 2) {
				if(start > 0 ) {
					for(int i = 0; i < playerCount; i++) {
						officialWinners[i] = null;
			
					}
				}
				start = 0;
				officialWinners[start] = winners[iPlayer];
				compareThreeKindb(player2, card2, ++iPlayer, ++start, winners, officialWinners);
			}
			else if (winning == 3) {
				officialWinners[start] = winners[iPlayer];
				compareThreeKindb(winner, win, ++iPlayer, ++start, winners, officialWinners);
			}
		}
		
		return officialWinners;
	}
	
	//Sorts the cards to check for a straight
	private Card[] sortStraight(Card[] uCards) {
		uCards = sortCards(uCards);
		int start = 0;
		int i = 1;
		int j = 0;
		int spree = 0;
		while (i == 1) {
			i = uCards[j].getFaceValue() - uCards[j + 1].getFaceValue();
			if((i != 1 && i != 0) && spree <= 2 && ( j == 1 || j == 2 || j == 3)) {
				start = 0;
				spree = 0;
			}
			
			while(i == 0 && j < 5) {
				j++;
				i =  uCards[j].getFaceValue() - uCards[j + 1].getFaceValue();
			}
			
			if(i == 1) {
				spree += 1;
				if(spree == 1) {
					start = j;
				}
			}
			
			if((j == 0 || j == 1) && i != 1) {
				i = 1;
			}
			
			if(spree + (6 - j) < 4) {
				break;
			}
			
			else {
				i = 1;
			}
			
			if(j == 5) {
				break;
			}
			j++;
			
		}

		
		if(spree == 4) {
			i = start;
			start = 0;
			j = 4;
			while(j > 0) {
				if(uCards[i].getFaceValue() - uCards[i + 1].getFaceValue() == 1) {
					j--;
					uCards = switchCards(uCards, i, start);
					start++;
					if(j == 0) {
						uCards = switchCards(uCards, i + 1, start);
						start++;
					}
				}
				i++;
			}
		}
		
		if (spree == 3 && uCards[start].getFaceValue() == 5 
				&& (uCards[0].getFaceValue() == 14 || uCards[1].getFaceValue() == 14 || uCards[2].getFaceValue() == 14 )) {
			i = start;
			start = 0;
			j = 3;
			while(j > 0) {
				if(uCards[i].getFaceValue() - uCards[i + 1].getFaceValue() == 1) {
					j--;
					uCards = switchCards(uCards, start, i);
					start++;
					if(j == 0) {
						uCards = switchCards(uCards, start, i + 1);
						start++;
						
						if(uCards[5].getFaceValue() == 14) {
							uCards = switchCards(uCards, 4, 5);
						}
						else if (uCards[6].getFaceValue() == 14) {
							uCards = switchCards(uCards, 4, 6);
						}
					}
				}
				i++;
			}
		}
		
		return uCards;
	}
	
	//prints the hand for the players that have a straight
	public void getStraight() {
		boolean straight = false;
		for(int i = 0; i < 10; i++) {
					
			if(players[i].getPlayer() != "empty") {
				
				Card[] cards = new Card[] {players[i].viewCardOne(), players[i].viewCardTwo(), flop[0], flop[1], flop[2], turn, river};
				cards = sortStraight(cards); 
				
				if((cards[0].getFaceValue() - cards[1].getFaceValue() == 1) 
						&& (cards[1].getFaceValue() - cards[2].getFaceValue() == 1) 
						&& (cards[2].getFaceValue() - cards[3].getFaceValue() == 1) 
						&& (cards[4].getFaceValue() == 14)) {
					
					straight = true;
					System.out.println(players[i].getPlayer() + " in seat " + getSeat(players[i].getPlayer()) + " has a low straight with a hand of\n"
							+ cards[0] + "\n" + cards[1] + "\n" + cards[2] + "\n" + cards[3] + "\n" + cards[4] + "\n");
				}
				
				else if((cards[0].getFaceValue() - cards[1].getFaceValue() == 1) 
						&& (cards[1].getFaceValue() - cards[2].getFaceValue() == 1) 
						&& (cards[2].getFaceValue() - cards[3].getFaceValue() == 1) 
						&& (cards[3].getFaceValue() - cards[4].getFaceValue() == 1)) {
					
					straight = true;
					if (cards[0].getFaceValue() == 14) {
						System.out.println(players[i].getPlayer() + " in seat " + getSeat(players[i].getPlayer()) + " has a high straight with a hand of\n"
								+ cards[0] + "\n" + cards[1] + "\n" + cards[2] + "\n" + cards[3] + "\n" + cards[4] + "\n");
					}
					else {
						System.out.println(players[i].getPlayer() + " in seat " + getSeat(players[i].getPlayer()) + " has a straight with a hand of\n"
								+ cards[0] + "\n" + cards[1] + "\n" + cards[2] + "\n" + cards[3] + "\n" + cards[4] + "\n");
					}
					
				}
			}
		}
		if(!straight) {
			getThreeOfKind();
		}
	}
	
	// Prints all the winners with same highest straight
	public void getStraightWinners(Player[] winners, String hand) {
		int i = 0;
		while(i != playerCount && winners[i] != null) {
			Player winner = winners[i];
			Card[] win = getCards(winners[i]);
			win = sortStraight(win);
			System.out.println("The winning Player is " + winner.getPlayer() + " in seat " + getSeat(winner.getPlayer()) + hand + "\n" 
					+ win[0] + "\n" + win[1] + "\n" + win[2] + "\n" + win[3] + "\n" + win[4] + "\n");
			i++;
			if((win[5].getFaceValue() - win[0].getFaceValue()) == 1) {
				System.out.println("\n*********************\n***********This hand is incorrect!!!!*******\n******\n");
			}
			if((win[6].getFaceValue() - win[0].getFaceValue()) == 1 ) {
				System.out.println("\n*********************\n***********This hand is incorrect!!!!*******\n******\n");
			}
		}
	}
	
	//prints the hand for the players that have a straight
	public void getStraight2() {
		Player[] winners = new Player[playerCount];
		boolean straight = false;
		int n = 0;
		for(int i = 0; i < 10; i++) {
					
			if(players[i].getPlayer() != "empty") {
				
				Card[] cards = new Card[] {players[i].viewCardOne(), players[i].viewCardTwo(), flop[0], flop[1], flop[2], turn, river};
				cards = sortStraight(cards); 
				
				if((cards[0].getFaceValue() - cards[1].getFaceValue() == 1) 
						&& (cards[1].getFaceValue() - cards[2].getFaceValue() == 1) 
						&& (cards[2].getFaceValue() - cards[3].getFaceValue() == 1) 
						&& (cards[4].getFaceValue() == 14)) {
					winners[n] = players[i];
					n++;
					straight = true;
				}
				
				else if((cards[0].getFaceValue() - cards[1].getFaceValue() == 1) 
						&& (cards[1].getFaceValue() - cards[2].getFaceValue() == 1) 
						&& (cards[2].getFaceValue() - cards[3].getFaceValue() == 1) 
						&& (cards[3].getFaceValue() - cards[4].getFaceValue() == 1)) {
					winners[n] = players[i];
					n++;
					straight = true;
					
				}
			}
		}
		if(!straight) {
			getThreeOfKind2();
		}
		else {
			winners = compareThreeKind(winners);
			getStraightWinners(winners, " has a STRAIGHT with a hand of");
		}
	}
	
	
	
	//Sorts the hand to check for a flush
	private Card[] sortFlush(Card[] uCards) {
		uCards = sortCards(uCards);
		int diamonds = 0;
		int clubs = 0;
		int hearts = 0;
		int spades = 0;
		
		for(int i = 0; i < cardCount; i++) {
			if (uCards[i] == null) {
				continue;
			}
			else if (uCards[i].getSuit() == "diamonds") {
				diamonds += 1;
			}
			
			else if(uCards[i].getSuit() == "clubs") {
				clubs += 1;
			}
			
			else if(uCards[i].getSuit() == "hearts") {
				hearts += 1;
			}
			
			else if(uCards[i].getSuit() == "spades") {
				spades += 1;
			}		
			
		}
		
		if (diamonds > 4) {
			for(int i = 0, start = 0; i < cardCount; i++) {
				if (uCards[i].getSuit() == "diamonds") {
					uCards = switchCards(uCards, start, i);
					start++;
				}
				if(start == 5) {
					break;
				}
			}
		}
		
		else if (clubs > 4) {
			for(int i = 0, start = 0; i < cardCount; i++) {
				if (uCards[i].getSuit() == "clubs") {
					uCards = switchCards(uCards, start, i);
					start++;
				}
				if(start == 5) {
					break;
				}
			}
		}
		
		else if (hearts > 4) {
			for(int i = 0, start = 0; i < cardCount; i++) {
				if (uCards[i].getSuit() == "hearts") {
					uCards = switchCards(uCards, start, i);
					start++;
				}
				if(start == 5) {
					break;
				}
			}
		}
		
		else if (spades > 4) {
			for(int i = 0, start = 0; i < cardCount; i++) {
				if (uCards[i].getSuit() == "spades") {
					uCards = switchCards(uCards, start, i);
					start++;
				}
				if(start == 5) {
					break;
				}
			}
		}		
		return uCards;
	}
	
	//Prints the hand if it is a flush
	public void getFlush() {
		boolean flush = false;
		for(int i = 0; i < 10; i++) {
			Card[] cards = new Card[] {players[i].viewCardOne(), players[i].viewCardTwo(), flop[0], flop[1], flop[2], turn, river};
			cards = sortFlush(cards);
			if(cards[0].getSuit() == cards[1].getSuit() && cards[0].getSuit() == cards[2].getSuit() && cards[0].getSuit() == cards[3].getSuit() 
					&& cards[0].getSuit() == cards[4].getSuit()) {
				flush = true;
				System.out.println(players[i].getPlayer() + " in seat " + getSeat(players[i].getPlayer()) + " has a flush with a hand of\n"
						+ cards[0] + "\n" + cards[1] + "\n" + cards[2] + "\n" + cards[3] + "\n" + cards[4] + "\n");
			}
		}
		if(!flush) {
			getStraight();
		}
	}
	
	// prints all the highest flush hands
	public void getFlushWinners(Player[] winners, String hand) {
		int i = 0;
		while(i != playerCount && winners[i] != null) {
			Player winner = winners[i];
			Card[] win = getCards(winners[i]);
			win = sortFlush(win);
			System.out.println("The winning Player is " + winner.getPlayer() + " in seat " + getSeat(winner.getPlayer()) + hand + "\n" 
					+ win[0] + "\n" + win[1] + "\n" + win[2] + "\n" + win[3] + "\n" + win[4] + "\n");
			i++;
			if((win[4].getFaceValue() < win[5].getFaceValue()) 
					|| (win[4].getFaceValue() < win[6].getFaceValue())) {
				System.out.println("\n*********************\n***********This hand is incorrect!!!!*******\n******\n");
			}
			if((win[4].getFaceValue() < win[5].getFaceValue()) 
					|| (win[4].getFaceValue() < win[6].getFaceValue()) || (win[3].getFaceValue() < win[4].getFaceValue())) {
				System.out.println("\n*********************\n***********This hand is incorrect!!!!*******\n******\n");
			}
		}
	}
	
	//Prints the hand if it is a flush
	public void getFlush2() {
		Player[] winners = new Player[playerCount];
		int n = 0;
		boolean flush = false;
		for(int i = 0; i < 10; i++) {
			if(players[i].getPlayer() != "empty") {
				Card[] cards = getCards(players[i]);
				cards = sortFlush(cards);
				if(cards[0].getSuit() == cards[1].getSuit() && cards[0].getSuit() == cards[2].getSuit() && cards[0].getSuit() == cards[3].getSuit() 
						&& cards[0].getSuit() == cards[4].getSuit()) {
					winners[n] = players[i];
					n++;
					flush = true;
				}
			}
		}
		if(!flush) {
			getStraight2();
		}
		else {
			winners = compareFlushWinners(winners);
			getFlushWinners(winners, " has a FLUSH with hand of\n");
		}
		
	}
	
	// compares all flushes to determine the highest hand
	public Player[] compareFlushWinners(Player[] winners) {
		Player[] officialWinners = new Player[playerCount];
		Player player1 = winners[0];
		officialWinners[0] = winners[0];
		Player winner = player1;
		Card[] win = getCards(player1);
		win = sortFlush(win);
		
		winners = compareFlushWinnersb(winner, win, 1, 0, winners, officialWinners);
		return winners;
	}
	
	// A helper function for the compareFlushWinners method
	public Player[] compareFlushWinnersb(Player winner, Card[] win, int iPlayer, int start,Player[] winners, Player[] officialWinners) {
		
		if (iPlayer != playerCount && winners[iPlayer] == null) {
			return officialWinners;
		}
		
		else if(iPlayer != playerCount) {
			Player player2 = winners[iPlayer];
			Card[] card2 = getCards(player2);
			card2 = sortFlush(card2);
			int winning = compareHighTwoHands(win, card2);
			if(winning == 1) {				
				compareFlushWinnersb(winner, win, ++iPlayer, start, winners, officialWinners);
			}
			
			else if (winning == 2) {
				if(start > 0 ) {
					for(int i = 0; i < playerCount; i++) {
						officialWinners[i] = null;
			
					}
				}
				start = 0;
				officialWinners[start] = winners[iPlayer];
				compareFlushWinnersb(player2, card2, ++iPlayer, ++start, winners, officialWinners);
			}
			else if (winning == 3) {
				officialWinners[start] = winners[iPlayer];
				compareFlushWinnersb(winner, win, ++iPlayer, ++start, winners, officialWinners);
			}
		}
		return officialWinners;
	}
	
	//this method takes two cards and switches there position
	private Card[] switchCards(Card[] cards, int first, int second) {
		Card temp = cards[first];
		cards[first] = cards[second];
		cards[second] = temp;
		return cards;
	}
	
	//this method sorts the cards for a full house hand
	public Card[] sortFullHouse(Card[] uCards) {
		uCards = sortTwoPair(uCards);
		for(int i = 4; i < 7; i++) {
			if(uCards[0].getFaceValue() == uCards[i].getFaceValue()) {
				uCards = switchCards(uCards, 2, i);
				if(i > 4) {
					uCards = switchCards(uCards, 4, i);
				}
			break;
			}
			
		}
		
		if(uCards[0].getFaceValue() == uCards[1].getFaceValue() && uCards[0].getFaceValue() == uCards[2].getFaceValue()) {
			return uCards;
		}
		
		for(int i = 4; i < 7; i++) {
			if(uCards[2].getFaceValue() == uCards[i].getFaceValue()) {
				uCards = switchCards(uCards, 0, i);
				uCards = switchCards(uCards, 1, 3);
				if(i > 4) {
					uCards = switchCards(uCards, 4, i);
				}
				break;
			}
		}
		
		if(uCards[0].getFaceValue() == uCards[1].getFaceValue() && uCards[0].getFaceValue() == uCards[2].getFaceValue()) {
			return uCards;
		}
		
		if(uCards[4].getFaceValue() == uCards[5].getFaceValue()
			&& uCards[2].getFaceValue() == uCards[6].getFaceValue()) {
			uCards = switchCards(uCards, 1, 3);
			uCards = switchCards(uCards, 0, 4);
			uCards = switchCards(uCards, 1, 5);
			uCards = switchCards(uCards, 2, 6);
		}
		return uCards;
	}
	
	
	//a second method that sorts the cards for a full house hand
	public Card[] sortFullHouse2(Card[] uCards) {
		uCards = sortThreeKind(uCards);
		boolean paired = false;
		if( uCards[0].getFaceValue() == uCards[1].getFaceValue() && uCards[0].getFaceValue() == uCards[2].getFaceValue()) {
			for(int i = 3; i < 6; i++ ) {
				for(int j = i + 1; j < 7; j++) {
					if(uCards[i].getFaceValue() == uCards[j].getFaceValue()) {
						uCards = switchCards(uCards, i, 3);
						uCards = switchCards(uCards, j, 4);
						paired = true;
					}
				}
				if(paired) {
					break;
				}
			}
		}
		
		return uCards;
	}

	
	//Prints the winner(s) if  there is a full house hand
	public void getFullHouseWinners(Player[] winners, String hand) {
		int i = 0;
		while(i != playerCount && winners[i] != null) {
			Player winner = winners[i];
			Card[] win = getCards(winners[i]);
			win = sortFullHouse2(win);
			System.out.println("The winning Player is " + winner.getPlayer() + " in seat " + getSeat(winner.getPlayer()) + hand + "\n" 
					+ win[0] + "\n" + win[1] + "\n" + win[2] + "\n" + win[3] + "\n" + win[4] + "\n");
			i++;
			if((win[4].getFaceValue() < win[5].getFaceValue()) 
					|| (win[4].getFaceValue() < win[6].getFaceValue())) {
				System.out.println("\n*********************\n***********This hand is incorrect!!!!*******\n******\n");
			}
			if((win[4].getFaceValue() < win[5].getFaceValue()) 
					|| (win[4].getFaceValue() < win[6].getFaceValue()) || (win[3].getFaceValue() < win[4].getFaceValue())) {
				System.out.println("\n*********************\n***********This hand is incorrect!!!!*******\n******\n");
			}
		}
	}
	
	//This method finds any hands with a full house
	public void getFullHouse() {
		Player[] winners = new Player[playerCount];
		int n = 0;
		boolean four = false;
		for(int i = 0; i < 10; i++) {
			if(players[i].getPlayer() != "empty") {
				Card[] cards = getCards(players[i]);
				cards = sortFullHouse2(cards);
				if(cards[0].getFaceValue() == cards[1].getFaceValue() && cards[0].getFaceValue() == cards[2].getFaceValue() 
						&& cards[3].getFaceValue() == cards[4].getFaceValue()) {
					four = true;
					winners[n] = players[i];
					n++;
				}
			}
			
		}
		if(!four) {
			getFlush2();
		}
		else {
			winners = compareFullHouse(winners);
			getFullHouseWinners(winners, " has a FULL HOUSE with a hand of\n");
		}
	}
	
	//Compare all full house hands and store the winner(s) in an array
	public Player[] compareFullHouse(Player[] winners) {
		Player[] officialWinners = new Player[playerCount];
		Player player1 = winners[0];
		officialWinners[0] = winners[0];
		Player winner = player1;
		Card[] win = getCards(player1);
		win = sortFullHouse2(win);
		
		winners = compareFullHouseb(winner, win, 1, 0, winners, officialWinners);
		return winners;
	}
		
	//Recursive method to compareFourKind()
	public Player[] compareFullHouseb(Player winner, Card[] win, int iPlayer, int start,Player[] winners, Player[] officialWinners) {
		
		if (iPlayer != playerCount && winners[iPlayer] == null) {
			return officialWinners;
		}
		
		else if(iPlayer != playerCount) {
			Player player2 = winners[iPlayer];
			Card[] card2 = getCards(player2);
			card2 = sortFullHouse(card2);
			int winning = compareHighTwoHands(win, card2);
			if(winning == 1) {				
				compareFullHouseb(winner, win, ++iPlayer, start, winners, officialWinners);
			}
			
			else if (winning == 2) {
				if(start > 0 ) {
					for(int i = 0; i < playerCount; i++) {
						officialWinners[i] = null;
			
					}
				}
				start = 0;
				officialWinners[start] = winners[iPlayer];
				compareFullHouseb(player2, card2, ++iPlayer, ++start, winners, officialWinners);
			}
			else if (winning == 3) {
				officialWinners[start] = winners[iPlayer];
				compareFullHouseb(winner, win, ++iPlayer, ++start, winners, officialWinners);
			}
		}
		return officialWinners;
	}
	
	//Sorts for a four of a kind hand
	private Card[] sortFourKind(Card[] uCards) {
		uCards = sortCards(uCards);
		for(int i = 1; i < 4; i++) {
			int start = 0;
			if(uCards[i].getFaceValue() == uCards[i+1].getFaceValue() && uCards[i].getFaceValue() == uCards[i + 2].getFaceValue()
					&& uCards[i].getFaceValue() == uCards[i + 3].getFaceValue()) {
				uCards = switchCards(uCards, start, start + 4);
				start++;
				if(i > 1) {
					uCards = switchCards(uCards, start, start + 4);
					start++;
				}
				
				if(i == 3) {
					uCards = switchCards(uCards, start, start + 4);
				}
			}
		}
		return uCards;
	}
	
	//Prints the hand if it is a four of a kind
	public void getFourKind() {
		boolean four = false;
		for(int i = 0; i < 10; i++) {
			Card[] cards = new Card[] {players[i].viewCardOne(), players[i].viewCardTwo(), flop[0], flop[1], flop[2], turn, river};
			cards = sortFourKind(cards);
			if(cards[0].getFaceValue() == cards[1].getFaceValue() && cards[0].getFaceValue() == cards[2].getFaceValue() 
					&&cards[0].getFaceValue() == cards[3].getFaceValue()) {
				four = true;
				System.out.println(players[i].getPlayer() + " in seat " + getSeat(players[i].getPlayer()) + " has a four of a kind with a hand of\n"
						+ cards[0] + "\n" + cards[1] + "\n" + cards[2] + "\n" + cards[3] + "\n" + cards[4] + "\n");
			}
		}
		if(!four) {
			getFlush();
		}
	}
	
	//Prints the winner(s) if four of a kind hand
	public void getFourKindWinners(Player[] winners, String hand) {
		int i = 0;
		while(i != playerCount && winners[i] != null) {
			Player winner = winners[i];
			Card[] win = getCards(winners[i]);
			win = sortFourKind(win);
			System.out.println("The winning Player is " + winner.getPlayer() + " in seat " + getSeat(winner.getPlayer()) + hand + "\n" 
					+ win[0] + "\n" + win[1] + "\n" + win[2] + "\n" + win[3] + "\n" + win[4] + "\n");
			i++;
			if((win[4].getFaceValue() < win[5].getFaceValue()) 
					|| (win[4].getFaceValue() < win[6].getFaceValue())) {
				System.out.println("\n*********************\n***********This hand is incorrect!!!!*******\n******\n");
			}
			if((win[4].getFaceValue() < win[5].getFaceValue()) 
					|| (win[4].getFaceValue() < win[6].getFaceValue()) || (win[3].getFaceValue() < win[4].getFaceValue())) {
				System.out.println("\n*********************\n***********This hand is incorrect!!!!*******\n******\n");
			}
		}
	}
	
	//A second method to finding hands with four of a kind
	public void getFourKind2() {
		Player[] winners = new Player[playerCount];
		int n = 0;
		boolean four = false;
		for(int i = 0; i < 10; i++) {
			Card[] cards = new Card[] {players[i].viewCardOne(), players[i].viewCardTwo(), flop[0], flop[1], flop[2], turn, river};
			cards = sortFourKind(cards);
			if(cards[0].getFaceValue() == cards[1].getFaceValue() && cards[0].getFaceValue() == cards[2].getFaceValue() 
					&&cards[0].getFaceValue() == cards[3].getFaceValue()) {
				four = true;
				winners[n] = players[i];
				n++;
			}
		}
		if(!four) {
			getFullHouse();
		}
		else {
			winners = compareFourKind(winners);
			getFourKindWinners(winners, " has a FOUR OF A KIND with a hand of\n");
		}
	}
	
	//Compare all four of a kind winners and store the winner(s) in an array
	public Player[] compareFourKind(Player[] winners) {
		Player[] officialWinners = new Player[playerCount];
		Player player1 = winners[0];
		officialWinners[0] = winners[0];
//		Player player2 = winners[1];
		Player winner = player1;
		Card[] win = getCards(player1);
		win = sortFourKind(win);
		
		winners = compareFourKindb(winner, win, 1, 0, winners, officialWinners);
		return winners;
	}
	
	//Recursive method to compareFourKind()
	public Player[] compareFourKindb(Player winner, Card[] win, int iPlayer, int start,Player[] winners, Player[] officialWinners) {
		
		if (iPlayer != playerCount && winners[iPlayer] == null) {
			return officialWinners;
		}
		
		else if(iPlayer != playerCount) {
			Player player2 = winners[iPlayer];
			Card[] card2 = getCards(player2);
			card2 = sortFourKind(card2);
			int winning = compareHighTwoHands(win, card2);
			if(winning == 1) {				
				compareFourKindb(winner, win, ++iPlayer, start, winners, officialWinners);
			}
			
			else if (winning == 2) {
				if(start > 0 ) {
					for(int i = 0; i < playerCount; i++) {
						officialWinners[i] = null;
			
					}
				}
				start = 0;
				officialWinners[start] = winners[iPlayer];
				compareFourKindb(player2, card2, ++iPlayer, ++start, winners, officialWinners);
			}
			else if (winning == 3) {
				officialWinners[start] = winners[iPlayer];
				compareFourKindb(winner, win, ++iPlayer, ++start, winners, officialWinners);
			}
		}
		return officialWinners;
	}
	
	//sorts the cards to detect a straight flush
	private Card[] sortStraightFlush(Card[] uCards) {
		uCards = sortFlush(uCards);
		
		for(int i = 1; i < 3; i++) {
			if(uCards[i].getFaceValue() - uCards[i + 1].getFaceValue() == 1 
					&& uCards[i + 1].getFaceValue() - uCards[i + 2].getFaceValue() == 1
					&& uCards[i + 2].getFaceValue() - uCards[i + 3].getFaceValue() == 1
					&& uCards[i + 3].getFaceValue() - uCards[i + 4].getFaceValue() == 1) {
				if(i == 1) {
					Card temp = uCards[0];
					uCards[0] = uCards[1];
					uCards[1] = uCards[2];
					uCards[2] = uCards[3];
					uCards[3] = uCards[4];
					uCards[4] = uCards[5];
					uCards[5] = temp;
					break;
				}
				
				if (i == 2) {
					Card temp = uCards[0];
					uCards[0] = uCards[2];
					uCards[2] = uCards[3];
					uCards[3] = uCards[4];
					uCards[4] = uCards[5];
					uCards[5] = uCards[6];
					uCards[6] = temp;
					temp = uCards[1];
					uCards[1] = uCards[2];
					uCards[2] = uCards[3];
					uCards[3] = uCards[4];
					uCards[4] = uCards[5];
					uCards[5] = uCards[6];
					uCards[6] = temp;
				}
				
			}
		}
		
		for (int i = 1; i < 4; i++) {
			if(uCards[i].getFaceValue() - uCards[i + 1].getFaceValue() == 1 
					&& uCards[i + 1].getFaceValue() - uCards[i + 2].getFaceValue() == 1
					&& uCards[i + 2].getFaceValue() - uCards[i + 3].getFaceValue() == 1
					&& uCards[i].getFaceValue() == 5
					&& (uCards[0].getFaceValue() == 14 || uCards[1].getFaceValue() == 14 || uCards[2].getFaceValue() == 14)) {
				if (i == 1) {
					Card temp = uCards[0];
					uCards[0] = uCards[1];
					uCards[1] = uCards[2];
					uCards[2] = uCards[3];
					uCards[3] = uCards[4];
					uCards[4] = temp;
					break;
				}
				
				if (i == 2) {
					Card temp = uCards[0];
					uCards[0] = uCards[2];
					uCards[2] = uCards[3];
					uCards[3] = uCards[4];
					uCards[4] = uCards[5];
					uCards[5] = temp;
					temp = uCards[1];
					uCards[1] = uCards[2];
					uCards[2] = uCards[3];
					uCards[3] = uCards[4];
					uCards[4] = uCards[5];
					uCards[5] = temp;
					break;
				}
				if (i == 3) {
					Card temp = uCards[0];
					uCards[0] = uCards[3];
					uCards[3] = uCards[4];
					uCards[4] = uCards[5];
					uCards[5] = uCards[6];
					uCards[6] = temp;
					temp = uCards[1];
					uCards[1] = uCards[3];
					uCards[3] = uCards[4];
					uCards[4] = uCards[5];
					uCards[5] = uCards[6];
					uCards[6] = temp;
					temp = uCards[2];
					uCards[2] = uCards[3];
					uCards[3] = uCards[4];
					uCards[4] = uCards[5];
					uCards[5] = uCards[6];
					uCards[6] = temp;
				}
			}
		}
		return uCards;
	}
	
	// Prints all the highest straight flush hands
	public void getStraightFlushWinners(Player[] winners, String hand) {
		int i = 0;
		while(i != playerCount && winners[i] != null) {
			Player winner = winners[i];
			Card[] win = getCards(winners[i]);
			win = sortStraightFlush(win);
			System.out.println("The winning Player is " + winner.getPlayer() + " in seat " + getSeat(winner.getPlayer()) + hand + "\n" 
					+ win[0] + "\n" + win[1] + "\n" + win[2] + "\n" + win[3] + "\n" + win[4] + "\n");
			i++;
			if((win[4].getFaceValue() < win[5].getFaceValue()) 
					|| (win[4].getFaceValue() < win[6].getFaceValue())) {
				System.out.println("\n*********************\n***********This hand is incorrect!!!!*******\n******\n");
			}
			if((win[4].getFaceValue() < win[5].getFaceValue()) 
					|| (win[4].getFaceValue() < win[6].getFaceValue()) || (win[3].getFaceValue() < win[4].getFaceValue())) {
				System.out.println("\n*********************\n***********This hand is incorrect!!!!*******\n******\n");
			}
		}
	}
	
	//Prints the  hand if it is a straight flush
	public void getStraightFlush() {
		boolean sFlush = false;
		for(int i = 0; i < 10; i++) {
			Card[] cards = new Card[] {players[i].viewCardOne(), players[i].viewCardTwo(), flop[0], flop[1], flop[2], turn, river};
			cards = sortStraightFlush(cards);
			
			if (cards[0].getSuit() == cards[1].getSuit() && cards[0].getSuit() == cards[2].getSuit() && cards[0].getSuit() == cards[3].getSuit() 
					&& cards[0].getSuit() == cards[4].getSuit()
					&& (cards[0].getFaceValue() - cards[1].getFaceValue() == 1) 
					&& (cards[1].getFaceValue() - cards[2].getFaceValue() == 1) 
					&& (cards[2].getFaceValue() - cards[3].getFaceValue() == 1) 
					&& (cards[0].getFaceValue() == 5)
					&& (cards[4].getFaceValue() == 14)) {
				sFlush = true;
				System.out.println(players[i].getPlayer() + " in seat " + getSeat(players[i].getPlayer()) + " has the low straight flush with a hand of\n"
						+ cards[0] + "\n" + cards[1] + "\n" + cards[2] + "\n" + cards[3] + "\n" + cards[4] + "\n");
			}
			
			else if(cards[0].getSuit() == cards[1].getSuit() && cards[0].getSuit() == cards[2].getSuit() && cards[0].getSuit() == cards[3].getSuit() 
					&& cards[0].getSuit() == cards[4].getSuit()
					&& (cards[0].getFaceValue() - cards[1].getFaceValue() == 1) 
					&& (cards[1].getFaceValue() - cards[2].getFaceValue() == 1) 
					&& (cards[2].getFaceValue() - cards[3].getFaceValue() == 1) 
					&& (cards[3].getFaceValue() - cards[4].getFaceValue() == 1)) {
				sFlush = true;
				if (cards[0].getFaceValue() == 14) {
					System.out.println(players[i].getPlayer() + " in seat " + getSeat(players[i].getPlayer()) + " has a " + cards[0].getSuit() + " Royal flush with a hand of\n"
							+ cards[0] + "\n" + cards[1] + "\n" + cards[2] + "\n" + cards[3] + "\n" + cards[4] + "\n");
				}
				
				else {
					System.out.println(players[i].getPlayer() + " in seat " + getSeat(players[i].getPlayer()) + " has a straight flush with a hand of\n"
							+ cards[0] + "\n" + cards[1] + "\n" + cards[2] + "\n" + cards[3] + "\n" + cards[4] + "\n");
				}
			}
		}
		if(!sFlush) {
			getFourKind2();
		}
	}
	
	
	
	//Prints the  hand if it is a straight flush
	public void getStraightFlush2() {
		Player[] winners = new Player[playerCount];
		int n = 0;
		boolean sFlush = false;
		for(int i = 0; i < 10; i++) {
			Card[] cards = new Card[] {players[i].viewCardOne(), players[i].viewCardTwo(), flop[0], flop[1], flop[2], turn, river};
			cards = sortStraightFlush(cards);
			
			if (cards[0].getSuit() == cards[1].getSuit() && cards[0].getSuit() == cards[2].getSuit() && cards[0].getSuit() == cards[3].getSuit() 
					&& cards[0].getSuit() == cards[4].getSuit()
					&& (cards[0].getFaceValue() - cards[1].getFaceValue() == 1) 
					&& (cards[1].getFaceValue() - cards[2].getFaceValue() == 1) 
					&& (cards[2].getFaceValue() - cards[3].getFaceValue() == 1) 
					&& (cards[0].getFaceValue() == 5)
					&& (cards[4].getFaceValue() == 14)) {
				sFlush = true;
				winners[n] = players[i];
				n++;
			}
			
			else if(cards[0].getSuit() == cards[1].getSuit() && cards[0].getSuit() == cards[2].getSuit() && cards[0].getSuit() == cards[3].getSuit() 
					&& cards[0].getSuit() == cards[4].getSuit()
					&& (cards[0].getFaceValue() - cards[1].getFaceValue() == 1) 
					&& (cards[1].getFaceValue() - cards[2].getFaceValue() == 1) 
					&& (cards[2].getFaceValue() - cards[3].getFaceValue() == 1) 
					&& (cards[3].getFaceValue() - cards[4].getFaceValue() == 1)) {
				sFlush = true;
				winners[n] = players[i];
				n++;
			}
		}
		
		if(!sFlush) {
			getFourKind2();
		}
		else {
			winners = compareStraightFlush(winners);
			getStraightFlushWinners(winners, " has a STRAIGHT FLUSH with a hand of \n");
		}
	}
	
	
	//Compare all straight flush winners and store the winner(s) in an array
	public Player[] compareStraightFlush(Player[] winners) {
		Player[] officialWinners = new Player[playerCount];
		Player player1 = winners[0];
		officialWinners[0] = winners[0];
		Player winner = player1;
		Card[] win = getCards(player1);
		win = sortStraightFlush(win);
		
		winners = compareStraightFlushb(winner, win, 1, 0, winners, officialWinners);
		return winners;
	}
	
	//Recursive method to compareStraightFlush()
	public Player[] compareStraightFlushb(Player winner, Card[] win, int iPlayer, int start,Player[] winners, Player[] officialWinners) {
		
		if (iPlayer != playerCount && winners[iPlayer] == null) {
			return officialWinners;
		}
		
		else if(iPlayer != playerCount) {
			Player player2 = winners[iPlayer];
			Card[] card2 = getCards(player2);
			card2 = sortStraightFlush(card2);
			int winning = compareHighTwoHands(win, card2);
			if(winning == 1) {				
				compareStraightFlushb(winner, win, ++iPlayer, start, winners, officialWinners);
			}
			
			else if (winning == 2) {
				if(start > 0 ) {
					for(int i = 0; i < playerCount; i++) {
						officialWinners[i] = null;
			
					}
				}
				start = 0;
				officialWinners[start] = winners[iPlayer];
				compareStraightFlushb(player2, card2, ++iPlayer, ++start, winners, officialWinners);
			}
			else if (winning == 3) {
				officialWinners[start] = winners[iPlayer];
				compareStraightFlushb(winner, win, ++iPlayer, ++start, winners, officialWinners);
			}
		}
		return officialWinners;
	}
	
	//Goes through all the possible hands and finds the winning hand
	public void winningHand() {
		getStraightFlush();
	}

}//close the dealer class
















