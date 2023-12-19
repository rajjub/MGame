package edu.wm.cs.cs301.memorygame.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.AbstractAction;
import javax.swing.ActionMap;
import javax.swing.BorderFactory;
import javax.swing.InputMap;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.KeyStroke;

import edu.wm.cs.cs301.memorygame.model.MemoryGame;

public class MemoryGameFrame {
	
	private final JFrame frame;
	
	private final MemoryGame model;

	private final MemoryGameGridPanel gridPanel;
	
	public MemoryGameFrame(MemoryGame model) {
		this.model = model;
		this.gridPanel = new MemoryGameGridPanel(this, model);
		this.frame = createAndShowGUI();
	}
	
	private JFrame createAndShowGUI() {
		JFrame frame = new JFrame("MemoryGame");
		frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		frame.setJMenuBar(createMenuBar());
		frame.setResizable(false);
		frame.addWindowListener(new WindowAdapter() {
			@Override
			 public void windowClosing(WindowEvent event) {
				shutdown();
			}
		});
		
		frame.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent me) {
                model.getGameBoard().flipCard(1, 1);
            }
        });
		
		frame.add(createTitlePanel(), BorderLayout.NORTH);
		frame.add(gridPanel, BorderLayout.CENTER);
		
		frame.pack();
		frame.setLocationByPlatform(true);
		frame.setVisible(true);
		
		System.out.println("Frame size: " + frame.getSize());
		
		return frame;
	}
	
	private JMenuBar createMenuBar() {
		JMenuBar menuBar = new JMenuBar();
		
		JMenu difficultyLevel = new JMenu("Difficulty");
		JMenu helpMenu = new JMenu("Help");
		
		menuBar.add(difficultyLevel);
		menuBar.add(helpMenu);
		
		JMenuItem instructionsItem = new JMenuItem("Instructions...");
		instructionsItem.addActionListener(event -> new InstructionsDialog(this));
		helpMenu.add(instructionsItem);
		
		JMenuItem aboutItem = new JMenuItem("About...");
		aboutItem.addActionListener(event -> new AboutDialog(this));
		helpMenu.add(aboutItem);
		
		JMenuItem easyDifficulty = new JMenuItem("Easy");
		easyDifficulty.addActionListener(event -> new DifficultyDialog(this, "easy"));
		difficultyLevel.add(easyDifficulty);
		
		JMenuItem medDifficulty = new JMenuItem("Medium");
		medDifficulty.addActionListener(event -> new DifficultyDialog(this, "medium"));
		difficultyLevel.add(medDifficulty);
		
		JMenuItem hardDifficulty = new JMenuItem("Hard");
		hardDifficulty.addActionListener(event -> new DifficultyDialog(this, "hard"));
		difficultyLevel.add(hardDifficulty);
		
		return menuBar;
	}
	
	private JPanel createTitlePanel() {
		JPanel panel = new JPanel(new FlowLayout());
		panel.setBorder(BorderFactory.createEmptyBorder(0, 5, 5, 5));
		
		InputMap inputMap = panel.getInputMap(JPanel.WHEN_IN_FOCUSED_WINDOW);
		inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), "cancelAction");
		ActionMap actionMap = panel.getActionMap();
		actionMap.put("cancelAction", new CancelAction());
		
		JLabel label = new JLabel("MemoryGame");
		label.setFont(AppFonts.getTitleFont());
		panel.add(label);
		
		return panel;
	}
	
	public void shutdown() {
		//model.getStatistics().writeStatistics();
		frame.dispose();
		System.exit(0);
	}
	
	public void repaintWordleGridPanel() {
		gridPanel.repaint();
	}

	public JFrame getFrame() {
		return frame;
	}
	
	private class CancelAction extends AbstractAction {

		private static final long serialVersionUID = 1L;

		@Override
		public void actionPerformed(ActionEvent event) {
			shutdown();
		}
		
	}
	

}
