package com.paperboyclone;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Json;

public class GameSettingsHandler{


	private Json json = new Json();
	private FileHandle settingsFile;

	
	public GameSettingsHandler(){
		settingsFile = Gdx.files.external("settings.json");
	}
	
	public GameSettings loadSettings(){
		System.out.println("Loading Settings...");
		System.out.println(settingsFile.readString());
		return json.fromJson(GameSettings.class, settingsFile);
		
	}
	
	public void saveSettings(GameSettings settings){
		json.setUsePrototypes(false);
		json.toJson(settings, settingsFile);
	}
	
	
}


class GameSettings{
	
	public Vector2 resolution;
	public int SoundVolume;
	public boolean fullscreen;
	
	public GameSettings(){
		
		resolution = new Vector2(1280,1024);
		SoundVolume = 100;
		fullscreen = false;
	}
	
}