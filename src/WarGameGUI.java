import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class WarGameGUI extends JFrame {
	
	private WarGame game = new WarGame();
	private boolean bGameOver = false;
	
	ImageIcon image = new ImageIcon("back.jpg");
	private JLabel[] cardLabels = new JLabel[6];
	private JButton quit = new JButton("Quit");
	private JButton playRound = new JButton("Play Round");
	private JLabel plDeck = new JLabel("My Deck: 26");
	private JLabel opDeck = new JLabel("Opponent Deck: 26");
	private JLabel rndResult = new JLabel("This is War!");
	GridLayout gameLayout = new GridLayout(0,6);
	
	public WarGameGUI() {
		super();
		setResizable(false);
		game.gameSetup();
	}
	
	public void addComponenetsToPane(final Container pane) {
		final JPanel columnInfo = new JPanel();
		columnInfo.setLayout(gameLayout);
		columnInfo.add(new JLabel("My Deck"));
		columnInfo.add(new JLabel("Your Card"));
		columnInfo.add(new JLabel("Your Pile"));
		columnInfo.add(new JLabel("Opponent Pile"));
		columnInfo.add(new JLabel("Opponent Card"));
		columnInfo.add(new JLabel("Opponent Deck"));
		pane.add(columnInfo, BorderLayout.NORTH);
		
		final JPanel gameInfo = new JPanel();
		gameInfo.setLayout(gameLayout);
		gameInfo.add(plDeck);
		gameInfo.add(playRound);
		
		playRound.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(game.getPlayer(true).deckSize() > 0 && game.getPlayer(false).deckSize() > 0) {
					game.roundPullCards(game.getPlayer(true), game.getPlayer(false));
					cardLabels[1].setIcon(game.getPlayer(true).getTopOfWarPile().getImage());
					cardLabels[4].setIcon(game.getPlayer(false).getTopOfWarPile().getImage());
				} else if(game.getPlayer(true).deckSize() == 0) {
					gameOver(game.getPlayer(false));
				} else {
					gameOver(game.getPlayer(true));
				}
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
						cardLabels[2].setIcon(image);
						cardLabels[3].setIcon(image);
						if(game.getPlayer(true).deckSize() > 0 && game.getPlayer(false).deckSize() > 0) {
							rndResult.setText("War!!");
							game.setWar(true);
							game.roundPullCards(game.getPlayer(true), game.getPlayer(false));
							cardLabels[2].setIcon(image);
							cardLabels[3].setIcon(image);
						} else if(game.getPlayer(true).deckSize() == 0) {
							gameOver(game.getPlayer(false));
						} else {
							gameOver(game.getPlayer(true));
						}
//						if(game.canWar() == null) {
//							rndResult.setText("War!!");
//							game.warCardPull();
//							game.setWar(true);
//							plDeck.setText("My Deck: " + game.getPlayer(true).deckSize());
//							opDeck.setText("Opponent Deck: " + game.getPlayer(false).deckSize());
//						} else if(game.canWar() == game.getPlayer(true)) {
//							plDeck.setText("My Deck: " + game.getPlayer(true).deckSize());
//							opDeck.setText("Opponent Deck: " + game.getPlayer(false).deckSize());
//							gameOver(game.getPlayer(false));
//						} else {
//							plDeck.setText("My Deck: " + game.getPlayer(true).deckSize());
//							opDeck.setText("Opponent Deck: " + game.getPlayer(false).deckSize());
//							gameOver(game.getPlayer(true));
//						}
						
					}
					
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
	}
	
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
	
	public static void main(String[] args) {
		WarGameGUI frame = new WarGameGUI();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.addComponenetsToPane(frame.getContentPane());
		frame.pack();
		frame.setVisible(true);
	}

}
