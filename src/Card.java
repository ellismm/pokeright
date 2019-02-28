import java.awt.image.BufferedImage;
import java.io.IOException;

public class Card {
	
	private String faceName, suit;
	private int faceValue;
	private BufferedImage cardImage;
	
	/**
	 * The constructor  for the Card class
	 * @param faceName = 2,3,4....10, jack, queen, king, ace
	 * @param suit "spades", "clubs", "diamonds", "hearts"
	 * @param faceValue = 2,3,4....,14
	 * @param card = a bufferdImage that represents the card
	 */
	
	public Card (String suit, String faceName, int faceValue, BufferedImage card) throws IOException {
		
		this.suit = suit;
		this.faceName = faceName;
		this.faceValue = faceValue;
		this.cardImage = card;
		
	}
	
	/**
	 * This return a string representation of a card Object
	 * 
	 */
	
	public String toString() {
		
		return faceName + " of " + suit;
	}
	
	//Returns the name of the face
	public String getFace() {
		return faceName;
	}
	
	//Returns the suit of a card
	public String getSuit() {
		return suit;
	}
	public BufferedImage getCardImage() {
		
		return cardImage;
	}
	
	/**
	 * This return the faceValue of the Card Object
	 */
	
	public int getFaceValue() {
		return faceValue;
	}
	
	//Returns the card image it is attached to
	public BufferedImage getImage() {
		return cardImage;
	}

}














