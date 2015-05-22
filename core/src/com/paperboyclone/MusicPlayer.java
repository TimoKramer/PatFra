package com.paperboyclone;

import com.badlogic.gdx.audio.Music;


public class MusicPlayer implements Runnable {

	BackgroundMusic bgmusic = new BackgroundMusic();

	@Override
	public void run() {
		bgmusic.playMusic();
	}

	public void stop() {
		bgmusic.stopPlayingMusic();
		bgmusic.interrupt();
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