package com.paperboyclone;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;

/**
 * Assets is the class for handling all the assets used in the game. It
 * knows <code>Texture</code>, <code>Sound</code>, <code>Music</code> 
 * and a <code>Font</code>. 
 * 
 * @author Martin Freudenberg
 * @see AssetManager
 */
public class  Assets {

	public static AssetManager manager = new AssetManager();
	
	private static final String TexurePath = "data/textures/";
	private static final String SoundPath = "data/sounds/";
	private static final String MusicPath = "data/music/";
	private static final BitmapFont mainFont;
	static{
		//Error Files + Menu Sounds laden
		manager.load(TexurePath+"error.jpg", Texture.class);
		manager.load(SoundPath+"testsound1.wav", Sound.class);
		manager.load(MusicPath+"testsound1.wav", Sound.class);
		manager.load(TexurePath+"backgroundPattern.png", Texture.class);
		manager.load(SoundPath+"pop.ogg", Sound.class);
		manager.load(MusicPath+"8-Bit_Ninjas_-_12_-_Shiny_Spaceship.mp3", Music.class);
		manager.load(MusicPath+"MenuMusic.ogg", Music.class);
		loadAll();
			
		mainFont = new BitmapFont(Gdx.files.internal("data/BBrick.fnt"),Gdx.files.internal("data/BBrick.png"), false );
		
	}
	
	public static <T> void addAsset(String path, java.lang.Class<T> type){
		
		manager.load(path, type);
			
	}
	
	/**
	 * Loads a <code>Texture</code> from the core/assets/data/textures folder.
	 * 
	 * @param filename		filename as a string
	 */
	public static void addTexture(String filename){
		filename = TexurePath+filename;
		manager.load(filename, Texture.class);
	}
	
	/**
	 * Loads a <code>Sound</code> from the core/assets/data/sounds folder.
	 * 
	 * @param filename		filename as a string
	 */
	public static void addSound(String filename){
		filename = SoundPath+filename;
		manager.load(filename, Sound.class);
	}
	
	/**
	 * Loads a <code>Music</code> from the core/assets/data/music folder.
	 * 
	 * @param 	filename filename as a string
	 */
	public static void addMusic(String filename){
		filename = MusicPath+filename;
		manager.load(filename, Music.class);
	}	
	
	/**
	 * @return	a loaded asset from the <code>AssetManager</code>, either 
	 * 			<code>Texture</code>, <code>Sound</code> or <code>Music</code>
	 * @param String	path as a string
	 * @param Class		type as a class
	 */
	private static <T> T getAsset(String path, java.lang.Class<T> type) {
		
		if(manager.isLoaded(path, type)){
			return manager.get(path, type);
		} else {
			
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
	
	public static BitmapFont getFont(){
		return mainFont;
	}	
}