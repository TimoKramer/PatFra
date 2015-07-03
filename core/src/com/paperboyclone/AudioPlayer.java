package com.paperboyclone;

import javax.swing.text.html.HTMLDocument.HTMLReader.IsindexAction;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;

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
	
	public void playSound(Sound sound, float volume, float pitch, float pan) {
		sound.play(GameSettingsHandler.getInstance().getVolume() * volume, pitch, pan);
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


class BackgroundMusic extends Thread {

	Music music = Assets.getMusic("8-Bit_Ninjas_-_12_-_Shiny_Spaceship.mp3");

	public void playMusic() {
		music.setVolume(0.5f);
		music.play();
	}
	
	public void startPlayingMusic() {
		music.setVolume(0.3f);
		music.play();
	}
	
	public void stopPlayingMusic() {
		music.stop();
		music.dispose();
	}
}