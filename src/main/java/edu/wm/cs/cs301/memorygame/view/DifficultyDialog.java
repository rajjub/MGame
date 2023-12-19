package edu.wm.cs.cs301.memorygame.view;

import static javax.swing.JOptionPane.showMessageDialog;

import javax.swing.JDialog;
import javax.swing.JEditorPane;

import edu.wm.cs.cs301.memorygame.model.MemoryGame;

public class DifficultyDialog extends JDialog {

	private static final long serialVersionUID = 1L;

	private JEditorPane editorPane;

	public DifficultyDialog(MemoryGameFrame view, String difficulty) {
		MemoryGame model;
		model = new MemoryGame(difficulty);
		
		showMessageDialog(view.getFrame(), "Restarting Game with Difficult Level: " + difficulty.toUpperCase());
		view.getFrame().dispose();

		new Runnable() {
			@Override
			public void run() {
				new MemoryGameFrame(model);
			}
		}.run();
	}
}