package edu.wm.cs.cs301.memorygame.model;

import java.util.Scanner;

public class MemoryGame {
	private GameBoard gameBoard;
	private int row, col;
	private LeaderBoard leaderBoard;

	public MemoryGame(int rows, int cols, Alphabet alphabet) {
		gameBoard = new GameBoard(rows, cols, alphabet);
		gameBoard.displayBoard(false);
		row = rows;
		col = cols;
	}

	public MemoryGame() {
		this("medium");
	}
	
	public MemoryGame(String difficulty) {
		leaderBoard = LeaderBoardFileOperation.loadLeaderBoardDetails();
		runGame(difficulty);
	}
	
	public void runGame(String difficulty) {
		
		if ( difficulty.toLowerCase().equalsIgnoreCase("easy")) {
			row = 3;
			col = 4;
		} else if ( difficulty.toLowerCase().equalsIgnoreCase("hard")) {
			row = 7;
			col = 8;
		} else {
			row = 4;
			col = 7;
		}
		
		this.gameBoard = new GameBoard(row, col, new LatinAlphabet());
		this.gameBoard.displayBoard(false);

		//game.playGame();

	}

	public void runGame(GameBoard gameBoard) {

		Scanner scanner = new Scanner(System.in);

		// welcome message and game rules
		System.out.println("Welcome to the Memory Game!\n");
		System.out.println("Rules: ..."); 

		displayLeaderboard();

		// choosing the difficulty mode
		System.out.println("\nDifficulty modes:");
		System.out.println("1. Easy");
		System.out.println("2. Medium");
		System.out.println("3. Hard");
		System.out.print("Choose a difficulty mode:");

		int modeSelect = scanner.nextInt();
		int rows, cols;

		switch (modeSelect) {
		case 1:
			rows = 3;
			cols = 4;
			break;
		case 2:
			rows = 4;
			cols = 7;
			break;
		case 3:
			rows = 7;
			cols = 8;
			break;
		default:
			rows = 3;
			cols = 4;
			break;
		}

		// picking game board pieces
		
		System.out.println("\nAvailable Alphabet for the game: ");
		System.out.println("1. Latin Alphabet");
		System.out.println("2. Cherokee Alphabet");
		System.out.println("3. Unicode Alphabet");
		
		System.out.print("Choose an alphabet for the game: ");

		
		int chosenAlphabet = scanner.nextInt();
		Alphabet alphabet;

		switch (chosenAlphabet) {
		case 1:
			alphabet = new LatinAlphabet();
			break;
		case 2:
			alphabet = new CherokeeAlphabet();
			break;
		case 3:
			alphabet = new UnicodeAlphabet();
			break;
		default:
			alphabet = new LatinAlphabet();
			break;
		}

		MemoryGame game = new MemoryGame(rows, cols, alphabet);

		game.playGame();

		scanner.close();
	}

	private void playGame() {

		Scanner scanner = new Scanner(System.in);

		try {
			while ( gameBoard.getMaxScore() > 0 ) {

				GamePiece[] gpSelected = new GamePiece[2];

				int activeTurn = 0;

				while ( activeTurn < 2) {

					System.out.print("Choose a tile [R C] or type 'quit' to exit:");

					String option = scanner.nextLine();
					option = option.trim();
					
					if ( option.equalsIgnoreCase("quit")) {
						//flipped board
						gameBoard.displayBoard(true);
						return;
						//print score
						//check for completion etc
					} 
					
					//commit 1
					if ( option.length() != 3) {
						continue;
					}

					String[] options = option.split(" ");
					int selRow = Integer.valueOf(options[0]);
					int selCol = Integer.valueOf(options[1]);
					if (! validateInput(selRow, selCol)) {
						System.out.println("Invalid Input. Please enter another value");
					} else {
						gpSelected[activeTurn] = gameBoard.flipCard(selRow, selCol);
						gameBoard.displayBoard(false);
						activeTurn++;
					}
				}

				if ( gpSelected[0].getSymbol().equals(gpSelected[1].getSymbol()) ) {
					System.out.println("Match!");
					gameBoard.increaseScore();
				} else {
					System.out.println("No Match. This turn has ended.");
					gameBoard.nextTurn();
					gpSelected[0].setVisible(false);
					gpSelected[1].setVisible(false);
				}

				System.out.println("Press <RETURN> to continue:");
				scanner.nextLine();

				if ( gameBoard.getScore() < gameBoard.getMaxScore() ) {
					gameBoard.displayBoard(false);
				} else {
					System.out.println("Congrats you won");
					leaderBoard.addToLeaderBoard("Easy", "Amoolya", 3);
					displayLeaderboard();
					return;
				}
			}
		} finally {
			scanner.close();
		}

	}

	private boolean validateInput(int row, int col) {
		boolean isValid = false;
		if (row < 1 | col < 1) {
			isValid = false;
		} else {
			if (row <= this.row && col <= this.col) {
				if(gameBoard.isFlipped(row, col)) {
					isValid = false;
				} else {
					isValid = true;
				}
			}
		}
		return isValid;
	}

	private void displayLeaderboard() {
		leaderBoard.addToLeaderBoard("Easy", "Amoolya", 3);

		leaderBoard.printLeaderBoard();
		
	}

	public GameBoard getGameBoard() {
		return gameBoard;
	}

	public int getRow() {
		return row;
	}

	public int getCol() {
		return col;
	}

	public LeaderBoard getLeaderBoard() {
		return leaderBoard;
	}
	
	
}

