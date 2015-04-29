import javax.swing.JFrame;


public class WarGameGUIDriver {

	public static void main(String[] args) {
		WarGameGUI frame = new WarGameGUI();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.addComponenetsToPane(frame.getContentPane());
		frame.pack();
		frame.setVisible(true);
	}

}
