package com.paperboyclone;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.audio.Music;


public class MusicPlayer implements Runnable {

	BackgroundMusic bgmusic = new BackgroundMusic();
	public boolean isPlaying = true;

	@Override
	public void run() {
		bgmusic.playMusic();
	}

	public void stop() {
		bgmusic.stopPlayingMusic();
		bgmusic.interrupt();
	}
	
	public void checkMusicButton() {
		if(Gdx.input.isKeyPressed(Keys.S) && isPlaying) {
			stop();
		} else if (Gdx.input.isKeyPressed(Keys.S) && !isPlaying) {
			run();
		}
	}


}

class BackgroundMusic extends Thread {

	Music music = Assets.getMusic("8-Bit_Ninjas_-_12_-_Shiny_Spaceship.mp3");

	public void playMusic() {
		music.setVolume(0.5f);
		music.play();
	}
	
	public void stopPlayingMusic() {
		music.stop();
		music.dispose();
	}
}