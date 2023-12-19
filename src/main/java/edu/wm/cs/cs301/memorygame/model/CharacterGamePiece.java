package edu.wm.cs.cs301.memorygame.model;

public class CharacterGamePiece implements GamePiece {
	private final Character symbol;
	private boolean visible;
	
	public CharacterGamePiece(char s) {
		this.symbol = s;
		this.visible = false;
	}

	public Character getSymbol() {
		return symbol;
	}
	
	public void setVisible(boolean v) {
		visible = v;
	}
	
	public boolean isVisible() {
		return visible;
	}
	
	public boolean equals(GamePiece piece) {
		if(piece instanceof CharacterGamePiece) {
			return this.symbol.equals(((CharacterGamePiece) piece).symbol);
		}
		return false;
	}
	
}
