package edu.wm.cs.cs301.memorygame.model;

import java.io.*;
import java.util.Map;

public class LeaderBoardFileOperation {
	
	private static final String leaderBoardFileName = "src/main/resources/LeaderBoard.txt";

	
	public static LeaderBoard loadLeaderBoardDetails() {
		
		LeaderBoard leaderBoard = new LeaderBoard();
		
		 // Step 1: Read Data from the File
        try (BufferedReader reader = new BufferedReader(new FileReader(leaderBoardFileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
            	line = line.trim();
            	String[] strs = line.split(",");
            	String name = strs[1];
            	String score = strs[2];
            	
            	if ( name.trim().length() > 0 ) {
            		leaderBoard.addToLeaderBoard(strs[0], name, Integer.valueOf(score));
            	}
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        return leaderBoard;
	}

	
	public static void persistLeaderBoardData( LeaderBoard leaderBoard) {

		Map<String, LeaderBoardEntry> entries = leaderBoard.entries;
		
		// Write the Updated Data Back to the File
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(leaderBoardFileName))) {
        	
        	for ( String level: entries.keySet() ) {
        		LeaderBoardEntry entry = entries.get(level);
        		String updatedData = level+ "," + entry.getName() + "," + entry.getScore();
        		writer.write(updatedData);
        	}
            
        	writer.flush();
            System.out.println("Data has been updated and written back to the file.");
        } catch (IOException e) {
            e.printStackTrace();
        } 
	}
	
}
