package com.paperboyclone;

import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;

public class  Assets {

	public static AssetManager manager = new AssetManager();
	
	
	private static final String TexurePath = "data/textures/";
	private static final String SoundPath = "data/sounds/";
	private static final String MusicPath = "data/music/";
	
	
	static{
		//Error Files laden
		manager.load(TexurePath+"error.jpg", Texture.class);
		manager.load(SoundPath+"testsound1.wav", Sound.class);
		loadAll();
	}
	
	
	public static <T> void addAsset(String path, java.lang.Class<T> type){
		
		manager.load(path, type);
			
	}
	
	
	public static void addTexture(String filename){
		filename = TexurePath+filename;
		manager.load(filename, Texture.class);
	}
	
	
	public static void addSound(String filename){
		filename = SoundPath+filename;
		manager.load(filename, Sound.class);
	}
	
	public static void addMusic(String filename){
		filename = MusicPath+filename;
		manager.load(filename, Music.class);
	}
	
	
	
	private static <T> T getAsset(String path, java.lang.Class<T> type) {
		
		
		if(manager.isLoaded(path, type)){
			
			return manager.get(path, type);
		}
		else{
			
			//Falls File nicht gefunden gib Fehlermeldung aus und ein ErrorFile zurueck
			String errormessage = "AssetManager: %s \"%s\" not loaded!\n";
			
			if(type == Sound.class){
				
				System.out.printf(errormessage, "Sound" ,path);
				return manager.get(SoundPath+"testsound1.wav", type);
			}
			else if(type == Texture.class){
				
				System.out.printf(errormessage, "Texture", path);
				return manager.get(TexurePath+"error.jpg", type);
			}
			else if(type == Music.class){
				
				System.out.printf(errormessage, "Music", path);
				return manager.get(MusicPath+"testsound1.wav", type);
				
			}
			else{
				System.out.println("AssetManager: file type not supported");
				return null;
			}
			
			
		}
		
		
		
	}
	
	public static Texture getTexture(String filename){
		
		filename = TexurePath+filename;
		return getAsset(filename, Texture.class);
		
		
	}
	
	public static Sound getSound(String filename){
		
		filename = SoundPath+filename;
		return getAsset(filename, Sound.class);
	
	}
	
	public static Music getMusic(String filename){
		
		filename = MusicPath+filename;
		return getAsset(filename, Music.class);
	
	}
	
	
	public static void loadAll(){
		manager.finishLoading();		
	}
	
	public static boolean continueLoading(){
		return manager.update();
	}
	
	public static void dispose(){
		manager.clear();
		
	}
	
	
}
