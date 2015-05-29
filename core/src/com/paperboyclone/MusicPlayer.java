package com.paperboyclone;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.audio.Music;


public class MusicPlayer implements Runnable {

	BackgroundMusic bgmusic = new BackgroundMusic();
	private boolean isPlaying = true;
	private boolean isPressed;

	@Override
	public void run() {
		bgmusic.startPlayingMusic();
	}
	
	public void start() {
		isPlaying = true;
		bgmusic.startPlayingMusic();
	}

	public void stop() {
		isPlaying = false;
		bgmusic.stopPlayingMusic();
		bgmusic.interrupt();
	}
	
	public void checkMusicButton() {
		
		if(Gdx.input.isKeyPressed(Keys.S) && isPlaying) {
			if(!isPressed) stop();
			isPressed = true;
		} else if (Gdx.input.isKeyPressed(Keys.S) && !isPlaying) {
			if(!isPressed) start();
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