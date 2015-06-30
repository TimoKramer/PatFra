package com.paperboyclone;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

public class  Assets {

	public static AssetManager manager = new AssetManager();
	
	
	private static final String TexurePath = "data/textures/";
	private static final String SoundPath = "data/sounds/";
	private static final String MusicPath = "data/music/";
	private static final String SkinPath = "data/skins/";
	private static final BitmapFont mainFont;
	static{
		//Error Files laden
		manager.load(TexurePath+"error.jpg", Texture.class);
		manager.load(SoundPath+"testsound1.wav", Sound.class);
		manager.load(TexurePath+"backgroundPattern.png", Texture.class);
		loadAll();
			
		mainFont = new BitmapFont(Gdx.files.internal("data/arcade.fnt"),Gdx.files.internal("data/arcade.png"), false );
		
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
	
	public static void addSkin(String filename) {
		filename = SkinPath+filename;
		manager.load(filename, Skin.class);
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
			else if(type == Skin.class){
				
				System.out.printf(errormessage, "Skin", path);
				return manager.get(SkinPath+"uiskin.json", type);
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
	
	public static Skin getSkin(String filename){
		
		filename = SkinPath+filename;
		return getAsset(filename, Skin.class);
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
	
	public static BitmapFont getFont(){
		return mainFont;
	}
	
}
