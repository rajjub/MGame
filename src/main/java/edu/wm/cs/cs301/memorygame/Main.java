package edu.wm.cs.cs301.memorygame;

import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import edu.wm.cs.cs301.memorygame.model.MemoryGame;
import edu.wm.cs.cs301.memorygame.view.MemoryGameFrame;

public class Main implements Runnable{

	/*public static void main(String[] args) {
		new MemoryGame();
	}*/
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Main());
		
		//use cross-platform look and feel so button backgrounds work on Mac
		try {
		    UIManager.setLookAndFeel( UIManager.getCrossPlatformLookAndFeelClassName() );
		 } catch (Exception e) {
			 e.printStackTrace();
		 }
	}

	@Override
	public void run() {
		new MemoryGameFrame(new MemoryGame());
	}
}

