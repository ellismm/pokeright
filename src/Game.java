import java.awt.BorderLayout;
import java.io.*;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Game {
	
	enum playerOptions {
		QUIT, FLOP, TURN, RIVER;
		
	}
	
//	public static void option();
	
	public static void main(String[] args) throws IOException {
		
		//Create a jFrame to display our card
//		JFrame window = new JFrame("Display for a Card");
//		window.setSize(1000, 1500);
//		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		window.setVisible(true);
		
		//Create a JPanel - this is similar to glass in a window
//		JPanel contentPane = new JPanel(new BorderLayout());
		
		
		BufferedReader buff = new BufferedReader(new InputStreamReader(System.in));
		
//		this creates a new deck. The collect method allows us return the cards that are dealt back to the deck.
		DeckOfCards theDeck = new DeckOfCards();
//		theDeck = theDeck.collect();
		
		//testing if my thDeck object is displayed in the window 
//		Card oneCard = theDeck.specifiCard("Ace", "spades", 0);
//		JLabel cardLabel = new JLabel(new ImageIcon(oneCard.getCardImage()));
//		cardLabel.setSize(50, 100);
//		contentPane.add(cardLabel);
//		window.add(contentPane);
//		window.setVisible(true);
//		theDeck.shuffle();
//		
		//Creating players
		String names[] = {"Player 1", "Player 2", "Player 3", "Player 4", "Player 5", "Player 6", "Player 7", "Player 8", "Player 9", "Player 10"};
//		String names[] = {"Anna", "Helina"};
		
		Dealer deal = new Dealer(names, 10);
		
		
//		System.out.println("Player count = " + deal.playerCount + "\n");
		
		//Just testing my methods from Table class
//		System.out.println("getSeat() method: " + deal.getSeat(names[6]));
//		System.out.println("getSeatsTaken() method:" + deal.getSeatsTaken());
//		deal.switchSeats("anna", 10);
//		System.out.println("getSeat() method after switching seats: " + deal.getSeat("anna"));
//		System.out.println("getPlayer() method: " + deal.getPlayer(9));
//		
//		deal.removePlayer(10);
//		System.out.println("getPlayer() method after removePlayer() method " + deal.getPlayer(10));
//		
//		deal.addPlayer("Anna", 4);
//		System.out.println("getPlayer(9) method after addPlayer(anna, 4) method "  + deal.getPlayer(9));
//		
//		deal.addPlayer(2);
//		System.out.println("getPlayer(2) method after addPlayer(2) with unkown player method: " + deal.getPlayer(1));
//		for(String i: names)
		
		//moving a players seat	
//		deal.switchSeats("daniel", 10);
		
		//shuffle cards
		deal.shuffleCards();
				
		//Dealing Cards
		deal.dealCards();
		
		for(int i = 0, j = 0; i < deal.getSeatsTaken(); i++) {
			while(deal.getPlayer(j + 1) == "empty") {
				j++;
			}
			System.out.println(deal.getPlayer(j + 1) + " card one: " + deal.getCardOne(j + 1));
			System.out.println(deal.getPlayer(j + 1) + " card two: " + deal.getCardTwo(j + 1));
			System.out.println();
			j++;
		}
		
//		System.out.println("number of cards: " + deal.getNumberOfCards() + "\n");
//		deal.setBurnCard();
		
		//Deal the community cards
		deal.setBurnCard();
		deal.setFlopCards();
		deal.setBurnCard();
		deal.setTurnCard();
		deal.setBurnCard();
		deal.setRiverCard();
		deal.getBurnCards();
		System.out.println();
//		System.out.println();
		deal.getFlopCards();
		System.out.println();
		deal.getTurnCard();
		System.out.println();
		deal.getRiverCard();
		System.out.println();
//		System.out.println("number of cards: " + deal.getNumberOfCards() + "\n");
		
//		//Checking to see if the high card method works
//		for (int i = 1; i < 11; i++) {
//			if(deal.getPlayer(i).toLowerCase() != "empty") {
//				deal.printHighCard(deal.getPlayer(i));
//				System.out.println();
//			}
//		}
//		
		deal.getStraightFlush2();
//		
//		//Checking to see if the one pair method works
//		deal.getOnePair();
//		
//		//Checking to see if the two pair method works
//		deal.getTwoPair();
//		
//		//Checking to see if the three of a kind method works
//		deal.getThreeOfKind();
//		
//		//Checking to see if the straight method works
//		deal.getStraight();
//		
//		//Checking to see if the flush method and the straight flush method works
//		deal.getFlush();
//		deal.getStraightFlush();
//		
//		//Checking to see if the four of a kind method works
//		deal.getFourKind();
		
		//Checking to see if the winning hand mehod works
//		deal.winningHand();
//		playerOptions choice = buff.readLine();
		Dealer deal2 = new Dealer(names, 10);
		
		deal2.shuffleCards();
		deal2.dealCards();
		
		
		
		options();
		System.out.println("\nMake a Choice");
		String choice = buff.readLine();
		choice.toLowerCase();
		
		while(!choice.equals("quit")) {
			switch(choice) {
				case "flop":
					deal2.setBurnCard();
					deal2.setFlopCards();
					break;
				case "turn":
					System.out.println("here");
					deal2.setBurnCard();
					deal2.setTurnCard();
					break;
				case "river":
					deal2.setBurnCard();
					deal2.setRiverCard();
					break;
				case "quit":
					System.out.println("quit really does equal");
				default:
					System.out.println("Please make a better choice");
					break;
			}
			choice = buff.readLine();
			choice.toLowerCase();
			System.out.println(choice);
//			choice = "quit";
		}
		
		deal2.getFlopCards();
		deal2.getTurnCard();
		deal2.getRiverCard();
		
//		deal2.getStraightFlush2();
		
		
		
		System.out.println("Done");
		
	}
	
	public static void options() {
		System.out.println("quit: to quit the game");
		System.out.println("flop: to see the first 3 cards");
		System.out.println("turn: to see the fourth card");
		System.out.println("river: to see the last card");
	}

}
