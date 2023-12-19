package edu.wm.cs.cs301.memorygame.controller;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.JButton;

import edu.wm.cs.cs301.memorygame.model.MemoryGame;
import edu.wm.cs.cs301.memorygame.view.MemoryGameFrame;

public class KeyboardButtonAction extends AbstractAction {

	private static final long serialVersionUID = 1L;
	
	private final MemoryGameFrame view;
	
	private final MemoryGame model;

	public KeyboardButtonAction(MemoryGameFrame view, MemoryGame model) {
		this.view = view;
		this.model = model;
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		System.out.println("*************" + event.getActionCommand());
		JButton button = (JButton) event.getSource();
		String text = button.getActionCommand();
		
		switch (text) {
		case "Enter":
			/*if (model.getCurrentColumn() >= (model.getColumnCount() - 1)) {
				
				boolean isValid = model.isValid();
				
				if ( !isValid ) {
					showMessageDialog(view.getFrame(),"Word not in list");
					view.repaintWordleGridPanel();
					return;
				}
				
				boolean moreRows = model.setCurrentRow();
				MemoryGameResponse[] currentRow = model.getCurrentRow();
				int greenCount = 0;
				for (WordleResponse wordleResponse : currentRow) {
					view.setColor(Character.toString(wordleResponse.getChar()),
							wordleResponse.getBackgroundColor(), 
							wordleResponse.getForegroundColor());
					if (wordleResponse.getBackgroundColor().equals(AppColors.GREEN)) {
						greenCount++;
					} 
				}
				
				if (greenCount >= model.getColumnCount()) {
					view.repaintWordleGridPanel();
					model.getStatistics().incrementTotalGamesPlayed();
					int currentRowNumber = model.getCurrentRowNumber();
					model.getStatistics().addWordsGuessed(currentRowNumber);
					int currentStreak = model.getStatistics().getCurrentStreak();
					model.getStatistics().setCurrentStreak(++currentStreak);
					//new StatisticsDialog(view, model);
				} else if (!moreRows) {
					view.repaintWordleGridPanel();
					//model.getStatistics().incrementTotalGamesPlayed();
					//model.getStatistics().setCurrentStreak(0);
					//new StatisticsDialog(view, model);
				} else {
					view.repaintWordleGridPanel();
				}
			}*/
			break;
		case "Backspace":
			//model.backspace();
			view.repaintWordleGridPanel();
			break;
		default:
			//model.setCurrentColumn(text.charAt(0));
			view.repaintWordleGridPanel();
			break;
		}
		
	}

}
