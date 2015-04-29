import java.util.ArrayList;
/**
 * WarPlayer class that extends the Deck object. WarPlayer adds a boolean holding if the object is human and a warPile that holds a temporary stack of cards
 * used during the card game War.
 * @author StevenCasey
 *
 */
public class WarPlayer extends Deck {
	private ArrayList<Card> playerWarPile = new ArrayList<>();
	private boolean bIsHuman;
	/**
	 * Default constructor that only takes bIsHuman and then uses super() to initalize a standard 52 card deck.
	 * @param bIsHuman is WarPlayer a human or computer
	 */
	public WarPlayer(boolean bIsHuman) {
		super();
		this.bIsHuman = bIsHuman;
	}
	/**
	 * Alt Constructor that accepts bIsHuman and a Deck object. calls super(aDeck) to assign aDeck's cards to the new WarPlayer and sets bIsHuman value.
	 * @param bIsHuman is WarPlayer a human or computer
	 * @param aDeck Deck object
	 */
	public WarPlayer(boolean bIsHuman, Deck aDeck) {
		super(aDeck);
		this.bIsHuman = bIsHuman;
	}
	/**
	 * Alt Constructor that accepts bIsHuman and an ArrayList of Card Objects. Calls super(newDeck) to assign input ArrayList of Cards to new deck's list of cards.
	 * Also sets bIsHuman value.
	 * @param bIsHuman is WarPlayer a human or computer
	 * @param newDeck ArrayList of Card objects
	 */
	public WarPlayer(boolean bIsHuman, ArrayList<Card> newDeck) {
		super(newDeck);
		this.bIsHuman = bIsHuman;
	}
	/**
	 * Adds a singular Card object to the beginning of the player's WarPile.
	 * @param aCard Card Object
	 */
	public void addToWarPile(Card aCard) {
		playerWarPile.add(0, aCard);
	}
	
	public ArrayList<Card> getWarPile() {
		return this.playerWarPile;
	}
	/**
	 * Takes a second Player's warPile, combines it with current player's warPile, shuffles the new stack, and appends it to the current player's
	 * main deck. Clear's both player's warPile decks to ensure no duplicate cards. 
	 * @param aPlayer WarPlayer Object
	 */
	public void dumpWarPiles(WarPlayer aPlayer) {
		this.playerWarPile.addAll(aPlayer.getWarPile());
		this.addToDeck(playerWarPile);
		this.shuffle(this.playerWarPile);
		this.playerWarPile.clear();
		aPlayer.playerWarPile.clear();
	}
	
	public boolean isHuman() {
		return this.bIsHuman;
	}
	/**
	 * Returns the first Card object in the player's warPile.
	 * @return Card Object
	 */
	public Card getTopOfWarPile() {
		return this.getWarPile().get(0);
	}
}
