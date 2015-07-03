package com.paperboyclone;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.GdxRuntimeException;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.SerializationException;

public class GameSettingsHandler{


	private Json json = new Json();
	private FileHandle settingsFile;

	private static final class InstanceHolder {
		static final GameSettingsHandler INSTANCE = new GameSettingsHandler();
	}
		
	public static GameSettingsHandler getInstance() {
		return InstanceHolder.INSTANCE;
	}

	private GameSettingsHandler(){
		settingsFile = Gdx.files.external("settings.json");
	}
	
	public GameSettings loadSettings(){
		System.out.println("Loading Settings...");
		GameSettings returnthat = new GameSettings();
		try {
			String printthat = settingsFile.readString();
			System.out.println(printthat);
		} catch (GdxRuntimeException e) {
			System.out.println("Problem reading settings.json in your home folder: " + e);
		}
		try {
			returnthat = json.fromJson(GameSettings.class, settingsFile);
		} catch (SerializationException e) {
			System.out.println("settings.json has not the needed properties: " + e);
		}
		return returnthat;
	}
	
	public void saveSettings(GameSettings settings){
		json.setUsePrototypes(false);
		json.toJson(settings, settingsFile);
	}
	
	public int getSoundVolume() {
		return loadSettings().SoundVolume;
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