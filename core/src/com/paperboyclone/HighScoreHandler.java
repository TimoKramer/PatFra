package com.paperboyclone;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.Json;


public class HighScoreHandler {

	private FileHandle handle;
	
	private static final class InstanceHolder {
		static final HighScoreHandler INSTANCE = new HighScoreHandler();
	}
	
	private HighScoreHandler() {
		handle = Gdx.files.external("PaperboyCloneHighScore.json");
		if(!Gdx.files.external("PaperboyCloneHighScore.json").exists()) {
			System.err.println("json existiert nicht in "+ System.getProperty("user.home") +"/ !");
		}		
	}
	
	public static synchronized HighScoreHandler getInstance() {
		return InstanceHolder.INSTANCE;
	}

	public void writeScore(int highScore) {
		HighScore newHighScore = new HighScore("Player", System.currentTimeMillis(), highScore);
		Json json = new Json();
		System.out.println("Name: " + newHighScore.name);
		System.out.println("HighScore: " + newHighScore.score);
		System.out.println("Time: " + newHighScore.time);
		System.out.println(json.prettyPrint(newHighScore));
		this.handle.writeString(json.toJson(newHighScore), true);
	}
	
	public int getHighScore() {
		return PlayerStats.getInstance().getScore();
	}
}

class HighScore {

	String name;
	long time;
	int score;
	
	public HighScore(String name, long time, int score) {
		this.name = name;
		this.time = time;
		this.score = score;
	}
	
	public HighScore() {
		this.name = "Player";
		this.time = 0L;
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
}
