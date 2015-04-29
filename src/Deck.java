import java.util.ArrayList;
import java.util.Random;
/**
 * Deck object that holds an ArrayList of Card objects. 
 * @author StevenCasey
 *
 */
public class Deck {
	private int deckSize;
	private Random rand = new Random();
	private ArrayList<Card> deckOfCards = new ArrayList<>();
	/**
	 * Standard constructor that creates a standard deck of 52 Card objects with appropriate rank and suit.
	 * Also holds the deckSize as an int.
	 */
	public Deck() {
		for(int i = 0; i <= 3; i++) {
			for(int p = 1; p <= 13; p++) {
				Card aCard = new Card(i,p);
				deckOfCards.add(aCard);
			}
		}
		deckSize = deckOfCards.size();
	}
	/**
	 * Alt Constructor that takes a deck and sets new Deck's stack of cards to the input Deck's stack.
	 * @param aDeck Deck object containing a ArrayList of cards
	 */
	public Deck(Deck aDeck) {
		deckOfCards = aDeck.deckOfCards;
		deckSize = deckOfCards.size();
	}
	/**
	 * Alt Constructor that takes an ArrayList of cards directly and sets the new Deck's list to the input list.
	 * @param newDeck ArrayList of Card objects
	 */
	public Deck(ArrayList<Card> newDeck) {
		deckOfCards = newDeck;
		deckSize = deckOfCards.size();
	}
	/**
	 * Shuffles the input ArrayList of Card objects using rand() to go through and swap each index with a random other index.
	 * @param stackCards ArrayList of card objects to be shuffled
	 */
	public void shuffle(ArrayList<Card> stackCards) {
		for(int i = 0; i < stackCards.size(); i++) {
			int swapIndex = rand.nextInt(stackCards.size());
			Card tempCard = stackCards.get(i);
			stackCards.set(i,stackCards.get(swapIndex));
			stackCards.set(swapIndex,tempCard);
		}
	}
	/**
	 * Pulls a card from the deck and returns it. Also deletes the card from the deck and reassigns deckSize approprietly.
	 * @return Card object from the Deck's ArrayList
	 */
	public Card pullCard() {
		Card pullCard = deckOfCards.get(0);
		deckOfCards.remove(0);
		deckSize = deckOfCards.size();
		return pullCard;
	}
	/**
	 * Splits the Deck into two ArrayLists of Card objects. Returns one half and leaves the other for the current Deck's ArrayList.
	 * @return ArrayList of Card Objects that equals half of the original Deck's
	 */
	public Deck splitDeck() {
		ArrayList<Card> tempDeck = new ArrayList<>();
		int splitIndex = this.deckSize;
		for(int i = 0; i < (splitIndex/2); i++) {
			tempDeck.add(this.pullCard());
		}
		Deck splitDeck = new Deck(tempDeck);
		return splitDeck;
	}
	
	public int deckSize() {
		return this.deckSize;
	}
	
	public ArrayList<Card> getCards() {
		return this.deckOfCards;
	}
	/**
	 * Sets Deck's ArrayList of cards to an input ArrayList and also adjusts deckSize var.
	 * @param cardArray ArrayList of Card objects
	 */
	public void setDeck(ArrayList<Card> cardArray) {
		this.deckOfCards = cardArray;
		this.deckSize = this.deckOfCards.size();
	}
	/**
	 * Appends an ArrayList of Card objects to current Deck's cards.
	 * @param cardArray ArrayList of Card Objects
	 */
	public void addToDeck(ArrayList<Card> cardArray) {
		this.deckOfCards.addAll(cardArray);
		this.deckSize = this.deckOfCards.size();
	}
	/**
	 * Alt method that takes a singular Card object and adds it to the current Deck's stack.
	 * @param aCard Card Object
	 */
	public void addToDeck(Card aCard) {
		this.deckOfCards.add(this.deckSize, aCard);
	}
	
	public String toString() {
		return deckOfCards.toString();
	}
	
	public int numCards() {
		return deckOfCards.size();
	}
}


