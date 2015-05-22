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

	public void writeScore(HighScore highScore) {
		Json json = new Json();
		//System.out.println(highScore);
		System.out.println(json.toJson(highScore));
		this.handle.writeString(json.toJson(highScore), true);
	}
}
