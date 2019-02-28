//package cardgame;
import javax.imageio.ImageIO;
import java.util.Arrays;
//import java.org.apache.commons.lang.ArrayUtils;

//import javax.swing.ImageIcon;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.security.SecureRandom;
import java.util.Scanner;

public class DeckOfCards {
	private Card[] deck;
	private int currentCard;
	
	
	@SuppressWarnings("null")
	public DeckOfCards() throws IOException {
		//declare arrays with, image files, suits, and names
//		String[] value = {"2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14"}
		String[] faces = {"two", "three", "four", "five", "six", "seven", "eight", "nine", "ten", "jack", "queen", "king", "ace"};
		String[] suits = {"diamonds", "clubs", "hearts", "spades"};
//		String[] fileNames = null;
		BufferedImage[] images = new BufferedImage[52];
		
		//opens the txt file that has the names of the images
		File inputFile = new File("card_files.txt");
		Scanner input = new Scanner(inputFile);
			
		//assigns the images array of BufferedImage type to each image of the deck of cards
		int count = 0;
		while(input.hasNext()) {
			String line = input.nextLine();
			images[count++] = ImageIO.read(new File("Images/" + line + ".png"));
		}
		input.close();
	
		
		deck = new Card[52];
		currentCard = 0;
		
		//Information used to extract individual cards from the 1 image holding all the cards
//		final int width = 123;
//		final int height = 172;
//		final int rows = 4;
//		final int cols = 13;
		

		
		for (int suit = 0; suit < 4; suit++) {
			for (int faceNum = 0; faceNum < 13; faceNum++) {
				
				deck[(faceNum + (suit*13))] = new Card(
						suits[suit], // calls the suits array to get the name of the suit
						faces[faceNum], //calls the faces array to get the face name
						faceNum+2,		//value of the card as an int
						images[assign(suits[suit], faces[faceNum], 0)]);	//image of the card
			
			
			}
		}
		
		
	}	//end of the constructor
	
	//takes input of name of the card and it's suit and matches it to the image file and returns the index position
	public static int assign(String str1, String str2, int cnt) throws FileNotFoundException {
		File File = new File("card_files.txt");
		Scanner put = new Scanner(File);
		while(put.hasNext()) {
			String line = put.nextLine();
			if(line.contains(str1) && line.contains(str2)) {
				return cnt;
			}
			else
				cnt++;
		}
		put.close();
		return cnt-1;
		
	
	
	}
	
	/**
	 * this method will print the deck of cards to the screen
	 */
	
	public void displayDeck() {
		for (Card card: deck) {
			System.out.println(card);
		}
	}
	
	
	/**
	 * this method will shuffle the card objects in the deck
	 * @param args
	 * @throws IOException
	 */
	public void shuffle() {
		
		currentCard = 0;
		
		
		SecureRandom randomNumber = new SecureRandom();
		//for each card in the deck, pick another random card and swap them
		for (int first = 0; first < deck.length; first++) {
			
			//select a random card
			int second = randomNumber.nextInt(52);
			
			//Swap the cards
			Card temp = deck[first];
			deck[first] = deck[second];
			deck[second] = temp;
		}
		
		for (int first = 0; first < deck.length; first++) {
					
			//select a random card
			int second = randomNumber.nextInt(52);
			
			//Swap the cards
			Card temp = deck[first];
			deck[first] = deck[second];
			deck[second] = temp;
		}
	} //end of the method shuffle
	
	/**
	 * This will deal a card off the deck and advane the currentCard instance
	 * variable
	 * @return the top Card on the deck
	 */
	
	public Card dealCard() {
		Card tempCard;
		if (currentCard < deck.length) {
			tempCard = deck[currentCard++];
			removeACard(tempCard);
			return tempCard;
		}
		else
			return null;
	}
	
	//Returns a card that is asked for
	public Card specifiCard(String cardFace, String suit, int index) {
		cardFace = cardFace.toLowerCase();
		suit = suit.toLowerCase();
		if (((deck[index].getFace()).equals(cardFace)) && ((deck[index].getSuit()).equals(suit))) {
//			System.out.println(deck[6].getFace());
			return deck[index];
		}
		else
			return specifiCard(cardFace, suit, ++index);
		
	}
	
	//Returns the index of a given face of the card and it's suit
	public int getIndexCard(String CardFace, String suit) {
		int i = 0;
		while(deck[i] == null || deck[i].getFace() != CardFace || deck[i].getSuit() != suit) {
			i++;
		}
		return i;
	}
	
	//collect the cards after playing a hand or a misdeal
	public void collect(Card oneCard) {
		int i = 0;
		while(deck[i] != null) {
			i++;
		}
		addACard(i, oneCard);
//		deck = new DeckOfCards();
//		return deck;
	}
	
	//remove a card that has been dealt
	public void removeACard(Card oneCard) {
		deck[getIndexCard(oneCard.getFace(), oneCard.getSuit())] = null;
	}
	
//	//Pick up one card to put back in the deck
	public void addACard(int index, Card oneCard) {
		deck[index] = oneCard;
	}
	
	//Return the number of cards left in the deck
	public int getNumberOfCards() {
		int i = 0;
		int j = 0;
		while(j < 52) {
			if(deck[j] != null) {
				i++;
			}
			j++;
		}
		return i;
	}
}	//end of the class
