package edu.wm.cs.cs301.memorygame.model;

public class LatinAlphabet implements Alphabet {
	private char[] symbols;
	
	public LatinAlphabet() {
        this.symbols = new char[] { 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 
        		'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z' };
    }
	
	@Override
    public char[] toCharArray() {
        return symbols;
    }
	
}
