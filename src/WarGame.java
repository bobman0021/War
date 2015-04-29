/**
 * WarGame class that contains all necessary methods to play the game of War.
 * @author StevenCasey
 *
 */
public class WarGame {
	
	private Deck mainDeck = new Deck();
	private WarPlayer player1, player2;
	private boolean bWar = false;
	private boolean bGameOver = false;
	/**
	 * Default game constructor that creates a new Deck of cards then splits those cards between two players.
	 */
	public WarGame() {
		mainDeck.shuffle(mainDeck.getCards());
		player1 = new WarPlayer(true, mainDeck);
		player2 = new WarPlayer(false, player1.splitDeck());
	}
	/**
	 * Pulls one card from each player and places them into their respective WarPiles.
	 */
	public void roundPullCards() {
		if(!bGameOver) {
			player1.addToWarPile(player1.pullCard());
			player2.addToWarPile(player2.pullCard());
		}
	}
	/**
	 * Checks if there currently is a war.
	 * @return boolean bWar
	 */
	public boolean isWar(){
		return bWar;
	}
	
	public void setWar(boolean bWar) {
		this.bWar = bWar;
	}
	
	public void gameOver() {
		bGameOver = true;
	}
	
	public boolean isGameOver() {
		return bGameOver;
	}
	/**
	 * Fetches either player1 or player2 depending on if the human or computer player is desired
	 * @param bIsHuman which player is being fetched
	 * @return WarPlayer object
	 */
	public WarPlayer getPlayer(boolean bIsHuman) {
		if (bIsHuman) {
			return player1;
		} else {
			return player2;
		}
	}	
}
