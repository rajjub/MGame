package edu.wm.cs.cs301.memorygame.model;

import java.util.HashMap;
import java.util.Map;

public class LeaderBoard {
	
	Map<String, LeaderBoardEntry> entries;
	
	public LeaderBoard() {
		entries = new HashMap<>();
	}
	
	public void addToLeaderBoard(String level, String name, int score) {
		LeaderBoardEntry entry = new LeaderBoardEntry(name, score);
		
		entries.put(level.toUpperCase(), entry);
		
	}
	
	public void updateLeaderBoard(String level, String name, int score) {
		LeaderBoardEntry entry = entries.get(level);
		
		if ( entry.getScore() > score) {
			entry.setName(name);
			entry.setScore(score);
		}
		
	}
	
	public void printLeaderBoard(){
		
		System.out.println("LeaderBoard");
		System.out.println("============");
		
		print("Easy");
		print("Medium");
		print("Hard");
		
	}
	
	public void print(String level) {
		LeaderBoardEntry entry = entries.get(level);
		
		if ( entry == null ) {
			System.out.println(level + ": " + "Name: , Score: ");
		} else {
			System.out.println(level + ":" + entry.getName() + ":" + entry.getScore());
		}
	}
}
