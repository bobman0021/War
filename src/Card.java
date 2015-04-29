import javax.swing.ImageIcon;

/**
 * Card class for a standard card in a deck. Contains the card's rank, suit, and ImageIcon with appropriate getters, setters,
 * toString, and comparison methods.
 * @author StevenCasey
 *
 */
public class Card {
	//init variables to contain rank and suit
	private int rank;
	private int suit;
	private ImageIcon frontImageName;
	//init special card values and suit values
	public final static int SPADES = 0,
							HEARTS = 1,
							DIAMONDS = 2,
							CLUBS = 3;
	public final static int ACE = 1,
							JACK = 11,
							QUEEN = 12,
							KING = 13;
	
	/**
	 * Default Card constructor. sets the cards rank and suit from the input variables. Also 
	 * derives the name of the display image to be used during games
	 * @param suit int from 0 to 3 defining the cards suit
	 * @param rank int from 0 to 13 defining the cards rank
	 */
	public Card(int suit, int rank) {
		
		this.rank = rank;
		this.suit = suit;
		this.frontImageName = new ImageIcon(this.rankToString() + this.suitToString() + ".jpg");
	}
	//Accessor Methods
	public int getSuit() {
		return suit;
	}
	
	public int getRank() {
		return rank;
	}
	
	public ImageIcon getImage() {
		return frontImageName;
	}
	/**
	 * 
	 * @return returns the card's rank as a string including face cards
	 */
	public String rankToString() {
		if(getRank() == 11) {
			return "jack";
		} else if (getRank() == 12) {
			return "queen";
		} else if (getRank() == 13) {
			return "king";
		} else if (getRank() == 1) {
			return "ace";
		} else {
			return Integer.toString(getRank());
		}
	}
	/**
	 * @return Card's suit as letter
	 */
	public String suitToString() {
		if (getSuit() == 0) {
			return "s";
		} else if (getSuit() == 1) {
			return "h";
		} else if (getSuit() == 2) {
			return "d";
		} else {
			return "c";
		}
	}
	//Returns the rank and suit of the card in a readable string format
	public String toString() {
		
		String strRank;
		String strSuit;
		//Convert special cards to appropriate string values
		if(getRank() == 11) {
			strRank = "Jack";
		} else if (getRank() == 12) {
			strRank = "Queen";
		} else if (getRank() == 13) {
			strRank = "King";
		} else if (getRank() == 1) {
			strRank = "Ace";
		} else {
			strRank = Integer.toString(getRank());
		}
		//Converts card suit to appropriate string value
		if (getSuit() == 0) {
			strSuit = "Spades";
		} else if (getSuit() == 1) {
			strSuit = "Hearts";
		} else if (getSuit() == 2) {
			strSuit = "Diamonds";
		} else {
			strSuit = "Clubs";
		}
		
		return (strRank + " of " + strSuit);
		
	}
	/**
	 * Compares two cards to eachother and returns an int value of if the comparison card is greater than, less than, or equal to.
	 * @param aCard card to be compared
	 * @return 1 for greater than, -1 for less than, and 0 for equal to
	 */
	public int compareTo(Card aCard) {
		if(this.getRank() > aCard.getRank()) {
			return 1;
		} else if(this.getRank() < aCard.getRank()) {
			return -1;
		} else {
			return 0;
		}
	}
	
}
