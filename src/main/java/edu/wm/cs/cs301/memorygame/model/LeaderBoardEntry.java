package edu.wm.cs.cs301.memorygame.model;

public class LeaderBoardEntry {

	private String name;
	private int score;
	
	
	public LeaderBoardEntry(String name, int score) {
		this.name = name;
		this.score = score;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
	}
	
	
	
}