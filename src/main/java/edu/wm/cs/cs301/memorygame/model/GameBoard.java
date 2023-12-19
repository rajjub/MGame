package edu.wm.cs.cs301.memorygame.model;


import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class GameBoard {

    private final GamePiece[][] board;
    private int currentTurn;
    private int maxScore;
    private int score;


    public GameBoard(int rows, int cols, Alphabet alphabet) {

        this.board = new GamePiece[rows][cols];
        this.currentTurn = 1;
        this.score = 0;
        this.maxScore = (board.length * board[0].length) / 2;
        
        createBoard(alphabet);

    }
    
    public GamePiece[][] getBoard() {
    	return board;
    }

    public GamePiece flipCard(int row, int col) {
    	board[row-1][col-1].setVisible(true);
    	return board[row-1][col-1];
    }
    
    public void unflipCard(int row, int col) {
    	board[row-1][col-1].setVisible(false);
    }
    
    public int getMaxScore() {
    	return maxScore;
    }
    
    public void nextTurn() {
    	currentTurn++;
    }
    
    public void increaseScore() {
    	score++;
    }
    
    public int getScore() {
    	return score;
    }
    
    public boolean isFlipped(int row, int col) {
    	return board[row-1][col-1].isVisible();
    }

    private void createBoard(Alphabet alphabet) {

        char[] symbols = alphabet.toCharArray();
        
        List<Character> allSymbols = new ArrayList<>();
        
        //Convert char[] to ArrayList<Character>
        for (char c : symbols) {
        	allSymbols.add(c);
        }
        
        Collections.shuffle(allSymbols);
        
        List<Character> gameSymbols = new ArrayList<>(maxScore);

        for (int i=0; i< maxScore; i++ ) {
        	gameSymbols.add(allSymbols.get(i));
        	gameSymbols.add(allSymbols.get(i));
        } 

        System.out.println("**************" + gameSymbols);
        
        Collections.shuffle(gameSymbols);

        int i = 0;
        for (int row = 0; row < board.length; row++) {
            for (int col = 0; col < board[0].length; col++) {
                board[row][col] = new CharacterGamePiece(gameSymbols.get(i));
                i++;
            }
        }
    }


    public void displayBoard(boolean flipAll) {

        int rows = board.length;
        int cols = board[0].length;
        
        System.out.println(" ");
        System.out.println("Turn: " + currentTurn);
        System.out.print("     ");

        for (int col = 0; col < cols; col++) {
            System.out.print((col+1) + "   ");
        }

        System.out.println();
        System.out.print("   ");

        for (int col = 0; col <= (cols*4); col++) {
            System.out.print("=");
        }

        System.out.println();


        for (int row = 0; row < rows; row++) {

            System.out.print((row+1) + " ||");
           
            for (int col = 0; col < cols; col++) {

                GamePiece piece = board[row][col];

                if (piece.isVisible() || flipAll ) {
                    System.out.print(" " + piece.getSymbol() + " |");
                } else {
                    System.out.print(" ? |");
                }

            } 
            System.out.print("|");

            System.out.println();

            System.out.print("   ");

            for (int col = 0; col <= (cols*4); col++) {
                System.out.print("=");
            }

            System.out.println();

        }
    }

}