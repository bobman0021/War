import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
/**
 * WarGameGUI that contains the methods necessary to play the game war through a JPanel GUI
 * @author StevenCasey
 *
 */
public class WarGameGUI extends JFrame {
	
	private WarGame game;
	private boolean bGameOver = false;
	
	ImageIcon image = new ImageIcon("src/img/back.jpg");
	private JLabel[] cardLabels = new JLabel[6];
	private JButton quit = new JButton("Quit");
	private JButton playRound = new JButton("Play Round");
	private JLabel plDeck = new JLabel("My Deck: 26");
	private JLabel opDeck = new JLabel("Opponent Deck: 26");
	private JLabel rndResult = new JLabel("This is War!");
	GridLayout gameLayout = new GridLayout(0,6);
	
	/**
	 * Default constructor that creates an empty GUI and creates a new game of war.
	 */
	public WarGameGUI() {
		super();
		setResizable(false);
		game = new WarGame();
	}
	
	/**
	 * Adds all necessary components to the GUI including play round button that uses WarGame's methods to play one round of war,
	 * as well as change all the necessary values and images. 
	 * @param pane Pane to add components to.
	 */
	public void addComponenetsToPane(final Container pane) {
		//create all JLabels that show what is stored in each card column
		final JPanel columnInfo = new JPanel();
		columnInfo.setLayout(gameLayout);
		columnInfo.add(new JLabel("My Deck"));
		columnInfo.add(new JLabel("Your Card"));
		columnInfo.add(new JLabel("Your Pile"));
		columnInfo.add(new JLabel("Opponent Pile"));
		columnInfo.add(new JLabel("Opponent Card"));
		columnInfo.add(new JLabel("Opponent Deck"));
		pane.add(columnInfo, BorderLayout.NORTH);
		//Creates and displays cards for each column and stores them in an array of JLabels for easy access.
		final JPanel cards = new JPanel();
		cards.setLayout(gameLayout);
		for(int i = 0; i < 6; i++) {
			cardLabels[i] = new JLabel();
			cards.add(cardLabels[i]);
			cardLabels[i].setIcon(image);
		}
		cardLabels[2].setIcon(null);
		cardLabels[3].setIcon(null);
		pane.add(cards,BorderLayout.CENTER);
		//Creates the game information row which contains the number of cards in each players deck, play round button, quit button, and round result label.
		final JPanel gameInfo = new JPanel();
		gameInfo.setLayout(gameLayout);
		gameInfo.add(plDeck);
		gameInfo.add(playRound);
		/**
		 * playRound's action listener class that runs through one round of the game. 
		 */
		playRound.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//check if each player's deck has at least 1 card in it and then pull 1 card and display it face up.
				//if one player has 0 cards, determine which one and call the gameOver function with the appropriate winner.
				if(game.getPlayer(true).deckSize() > 0 && game.getPlayer(false).deckSize() > 0) {
					game.roundPullCards();
					cardLabels[1].setIcon(game.getPlayer(true).getTopOfWarPile().getImage());
					cardLabels[4].setIcon(game.getPlayer(false).getTopOfWarPile().getImage());
				} else if(game.getPlayer(true).deckSize() == 0) {
					gameOver(game.getPlayer(false));
				} else {
					gameOver(game.getPlayer(true));
				}
				//Compare each player's cards to eachother and determine a winner. Whoever wins, dump both WarPiles into the winner's main deck
				if(!bGameOver){
					if(game.getPlayer(true).getTopOfWarPile().compareTo(game.getPlayer(false).getTopOfWarPile()) > 0) {
						game.getPlayer(true).dumpWarPiles(game.getPlayer(false));
						plDeck.setText("My Deck: " + game.getPlayer(true).deckSize());
						opDeck.setText("Opponent Deck: " + game.getPlayer(false).deckSize());
						rndResult.setText("You Win!");
						game.setWar(false);
					} else if (game.getPlayer(true).getTopOfWarPile().compareTo(game.getPlayer(false).getTopOfWarPile()) < 0) {
						game.getPlayer(false).dumpWarPiles(game.getPlayer(true));
						plDeck.setText("My Deck: " + game.getPlayer(true).deckSize());
						opDeck.setText("Opponent Deck: " + game.getPlayer(false).deckSize());
						rndResult.setText("You Lose!");
						game.setWar(false);
					} else {
						//if both cards are equal place a card into each player's war pile.
						cardLabels[2].setIcon(image);
						cardLabels[3].setIcon(image);
						//Check if the player has enough cards to war and if they do adjust the roundResult label and pull another set of cards and set bWar to true
						//if either player doesnt have enough cards call gameOver with appropriate winner.
						if(game.getPlayer(true).deckSize() > 0 && game.getPlayer(false).deckSize() > 0) {
							rndResult.setText("War!!");
							game.setWar(true);
							game.roundPullCards();
							cardLabels[2].setIcon(image);
							cardLabels[3].setIcon(image);
						} else if(game.getPlayer(true).deckSize() == 0) {
							gameOver(game.getPlayer(false));
						} else {
							gameOver(game.getPlayer(true));
						}						
					}
					//if there was a war and now it is over, remove the images display war pile.
					if(!game.isWar()) {
						cardLabels[2].setIcon(null);
						cardLabels[3].setIcon(null);
					}
				}
			}
		});
		
		gameInfo.add(rndResult);
		gameInfo.add(new JLabel(""));
		gameInfo.add(quit);	
		plDeck.setHorizontalAlignment(JLabel.CENTER);
		rndResult.setHorizontalAlignment(JLabel.RIGHT);
		opDeck.setHorizontalAlignment(JLabel.CENTER);
		quit.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				System.exit(0);
			}
		});
		gameInfo.add(opDeck);
		pane.add(gameInfo, BorderLayout.SOUTH);
	}
	/**
	 * Removes the image of the losers deck as they now have 0 cards. Adjust rndResult to show the appropriate winner.
	 * finally set bGameOver to true so that additional clicks of playRound don't run through and break the method.
	 * @param winner WarPlayer who won the game.
	 */
	public void gameOver(WarPlayer winner) {
		if(winner.isHuman()) {
			cardLabels[5].setIcon(null);
			rndResult.setText("You Won The Game!");
		} else {
			cardLabels[0].setIcon(null);
			rndResult.setText("You Lost The Game.");
		}
		bGameOver = true;
	}
}
