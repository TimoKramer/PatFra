package com.paperboyclone;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.ObjectMap;

/**
 * HighScoreHandler is the Singleton that handles the HighScores, reading them
 * from the filesystem, writing them to the filesystem, sorting them and 
 * printing them. 
 * 
 * @author Timo Kramer
 * @version 1.0
 */
public class HighScoreHandler {

	private FileHandle handle;
	private Json json = new Json();
	protected ArrayList<HighScore> highscoreList = new ArrayList<HighScore>();
	
	private static final class InstanceHolder {
		static final HighScoreHandler INSTANCE = new HighScoreHandler();
	}
	
	private HighScoreHandler() {
		handle = Gdx.files.external("PaperboyCloneHighScore.json");
		readFile();
	}
	
	public static HighScoreHandler getInstance() {
		return InstanceHolder.INSTANCE;
	}

	@SuppressWarnings("unchecked")
	public void readFile() {
		this.highscoreList = json.fromJson(ArrayList.class, handle);
	}
		
	public void initializeFile() {
		handle.writeString("[]", false);
	}
	
	public void writeFile() {
		// http://badlogicgames.com/forum/viewtopic.php?t=15719&p=67947
		json.setUsePrototypes(false);
		json.toJson(highscoreList, handle);
	}
	
	public void writeScore(int score) {
		HighScore newHighScore = new HighScore("Player", DifficultySettings.getInstance().getCurrentMode(), score);
		this.highscoreList.add(newHighScore);
		writeFile();
	}
	
	public void writeScore(String name, String level, int score) {
		HighScore newHighScore = new HighScore(name, level, score);
		this.highscoreList.add(newHighScore);
		writeFile();
	}
	
	public int getHighScore() {
		return PlayerStats.getInstance().getScore();
	}
	
	/**
	 * returns all highscores independent of the level they were achieved in
	 * 
	 * @return ArrayList<HighScore>
	 */
	public ArrayList<HighScore> getHighscoreList() {
		return highscoreList;
	}

	/**
	 * returns all highscores for certain level
	 * 
	 * @param level	the level the highscores are returned for.
	 * @return ArrayList<HighScore>
	 */
	public ArrayList<HighScore> getHighscoreList(String level) {
		ArrayList<HighScore> sortedHighscoreList = new ArrayList<HighScore>();
		for(int i = 0; i < highscoreList.size(); i++) {
			if(highscoreList.get(i).getLevel().equals(level)) {
				sortedHighscoreList.add(highscoreList.get(i));
			}
		}
		return sortedHighscoreList;
	}
}	

/**
 * A Class that represents a HighScore with a Comparable-Interface to sort highscores
 *  
 * @author Timo Kramer
 */
class HighScore implements Comparable<HighScore>{

	private String name;
	private String level;
	private int score;
	
	public HighScore(String name, String level, int score) {
		this.name = name;
		this.level = level;
		this.score = score;
	}
	
	public HighScore() {
		this.name = "StandardPlayer";
		this.level = DifficultySettings.getInstance().getCurrentMode();
		this.score = 0;
	}
	
	public void setName(String name) {
		this.name = name;
	}

	public void setTime(String level) {
		this.level = level;
	}

	public void setScore(int score) {
		this.score = score;
	}
	
	public String getName() {
		return this.name;
	}
	
	public String getLevel() {
		return this.level;
	}
	
	public int getScore() {
		return this.score;
	}
	
	@Override
	public int compareTo(HighScore o) {
		int compareScore = ((HighScore)o).getScore();
		return compareScore - this.score;
	}
	
	@Override
	public String toString() {
		return this.name + ": " + String.valueOf(this.score);
	}
}