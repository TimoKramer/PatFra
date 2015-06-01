package com.paperboyclone;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.TimeZone;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.TimeUtils;


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
		HighScore newHighScore = new HighScore("Player", TimeUtils.millis(), highScore);
		this.highscoreList.add(newHighScore);
		writeFile();
	}
	
	public int getHighScore() {
		return PlayerStats.getInstance().getScore();
	}
	
	public ArrayList<HighScore> getHighscoreList() {
		return highscoreList;
	}
	
	public String getHighScoreList() {
		return json.prettyPrint(handle.readString());
	}
}

class HighScore implements Comparable<HighScore>{

	private String name;
	private long time;
	private int score;
	
	public HighScore(String name, long time, int score) {
		this.name = name;
		this.time = time;
		this.score = score;
	}
	
	public HighScore() {
		this.name = "StandardPlayer";
		this.time = System.currentTimeMillis();
		this.score = 0;
	}
	
	public void setName(String name) {
		this.name = name;
	}

	public void setTime(long time) {
		this.time = time;
	}

	public void setScore(int score) {
		this.score = score;
	}
	
	public String getName() {
		return this.name;
	}
	
	public long getTime() {
		return this.time;
	}
	
	public String getDate() {
		Date date = new Date(this.getTime());
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		sdf.setTimeZone(TimeZone.getTimeZone("Europe/Berlin"));
		String formattedDate = sdf.format(date);
		return formattedDate;
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
		return this.name + "    |    " + this.getDate() + "    |    " + String.valueOf(this.score);
	}
}
