package com.paperboyclone;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;

/**
 * The <code>AudioPlayer</code> is a Singleton and responsible for 
 * playing music and sound effects while the game is running. For
 * the music not affecting the game the Music is implemented as
 * a <code>Thread</code>, therefore <code>AudioPlayer</code> implements <code>Runnable</code>. 
 * 
 * @author Timo Kramer
 * @author Martin Freudenberg
 * @version 1.0
 */
public class AudioPlayer implements Runnable {

	private BackgroundMusic bgmusic = new BackgroundMusic();
	private boolean isPlaying = true;
	private boolean isPressed;
	
	private static final class InstanceHolder {
		static final AudioPlayer INSTANCE = new AudioPlayer();
	}
	
	private AudioPlayer(){
		
	};
	
	public static AudioPlayer getInstance() {
		return InstanceHolder.INSTANCE;
	}

	@Override
	public void run() {
		bgmusic.startPlayingMusic();
	}
	
	public void startMusic() {
		isPlaying = true;
		bgmusic.startPlayingMusic();
	}

	public void stopMusic() {
		isPlaying = false;
		bgmusic.stopPlayingMusic();
		bgmusic.interrupt();
	}
	
	public void setBackgroundMusic(BackgroundMusic m){
		bgmusic.stopPlayingMusic();
		bgmusic = m;
	}
	
	public void playCrashSound(){
		playSound(Assets.getSound("crash.wav"), 1, 1, 0);
	}
	
	public void playHitSound() {
		playSound(Assets.getSound("hit.wav"), 1, 1, 0);
	}
	
	public void playThrowLeftSound() {
		playSound(Assets.getSound("throwleft.mp3"), 1, 1, 0);
	}
	
	public void playThrowRightSound() {
		playSound(Assets.getSound("throwright.mp3"), 1, 1, 0);
	}
	
	public void playCollectPaperSound() {
		playSound(Assets.getSound("pop.ogg"), 1, 1, 0);
	}
	
	public void playScoreUpSound() {
		playSound(Assets.getSound("scoreUP.ogg"), 1, 1, 0);
	}
	
	public void playSound(Sound sound, float volume, float pitch, float pan) {
		sound.play(volume * GameSettingsHandler.getInstance().getVolume()/100, pitch, pan);
	}
		
	public void checkMusicButton() {
		if(Gdx.input.isKeyPressed(Keys.S) && isPlaying) {
			if(!isPressed) stopMusic();
			isPressed = true;
		} else if (Gdx.input.isKeyPressed(Keys.S) && !isPlaying) {
			if(!isPressed) startMusic();
			isPressed = true;
		} else {
			isPressed = false;
		}
	}
}

/**
 * Is the class for playing the background music as a thread
 * 
 * @author Timo Kramer
 * @author Martin Freudenberg
 */
class BackgroundMusic extends Thread {

	Music music;
	
	public BackgroundMusic(){
		music = Assets.getMusic("8-Bit_Ninjas_-_12_-_Shiny_Spaceship.mp3");
	}
	
	public BackgroundMusic(Music m){
		music = m;
	}
	
	public void playMusic() {
		music.setVolume(0.5f * GameSettingsHandler.getInstance().getVolume()/100);
		music.setLooping(true);
		music.play();
	}
	
	public void startPlayingMusic() {
		music.setVolume(0.5f * GameSettingsHandler.getInstance().getVolume()/100);
		music.setLooping(true);
		music.play();
	}
	
	public void stopPlayingMusic() {
		music.stop();
		music.dispose();
	}
}