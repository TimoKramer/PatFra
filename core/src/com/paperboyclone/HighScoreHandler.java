package com.paperboyclone;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.ObjectMap;


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
	
	public void writeScore(int highScore) {
		HighScore newHighScore = new HighScore("Player", DifficultySettings.getInstance().getCurrentMode(), highScore);
		this.highscoreList.add(newHighScore);
		writeFile();
	}
	
	public int getHighScore() {
		return PlayerStats.getInstance().getScore();
	}
	
	public ArrayList<HighScore> getHighscoreList() {
		return highscoreList;
	}
			
	public ArrayList<HighScore> getHighscoreList(String level) {
		ArrayList<HighScore> sortedHighscoreList = new ArrayList<HighScore>();
		for(int i = 0; i < highscoreList.size(); i++) {
			if(highscoreList.get(i).getLevel().equals(level)) {
				sortedHighscoreList.add(highscoreList.get(i));
			}
		}
		return sortedHighscoreList;
	}
	
	public String arrayListToString(ArrayList<HighScore> hsl) {
		String returnString = "";
		for(int i = 0; i < hsl.size(); i++) {
			returnString += hsl.get(i).toString() + "\n";
		}
		returnString.replaceAll("\n", System.getProperty("line.separator"));
		return returnString;
	}
}

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