
public class WarGame {
	
	private Deck mainDeck = new Deck();
	private WarPlayer player1, player2;
	private boolean bWar = false;
	private boolean bGameOver = false;
	
	public void gameSetup() {
		mainDeck.shuffle(mainDeck.getCards());
		player1 = new WarPlayer(true, mainDeck);
		player2 = new WarPlayer(false, player1.splitDeck());
	}
	
	public void roundPullCards(WarPlayer player1, WarPlayer player2) {
		if(!bGameOver) {
			player1.addToWarPile(player1.pullCard());
			player2.addToWarPile(player2.pullCard());
		}
	}
	
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
	
	public WarPlayer getPlayer(boolean bIsHuman) {
		if (bIsHuman) {
			return player1;
		} else {
			return player2;
		}
	}	
}
