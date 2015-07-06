package com.paperboyclone;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;

public class PlayerStats {

	private int score;
	private int papers;
	private int lives;
	BitmapFont HUDFont;
	private boolean isPaperAvailable;
	
	private static final class InstanceHolder {
		static final PlayerStats INSTANCE = new PlayerStats(0,  30, 5);
	}
		
	private PlayerStats(int score, int papers, int lives) {
		HUDFont = Assets.getFont();
		set(score,papers,lives);
	}

	public static synchronized PlayerStats getInstance() {
		return InstanceHolder.INSTANCE;
	}
	
	public void set(int score, int papers, int lives){
		this.score = score;
		this.papers = papers;
		this.lives = lives;
		this.isPaperAvailable = true;
	}
	
	public void draw(Batch batch, Camera camera) {
		HUDFont.setColor(Color.BLACK);
		HUDFont.draw(batch, "Score: " + this.score + " Papers: " + this.papers + " Lives: " + this.lives, 
				camera.position.x - camera.viewportWidth/2 + 23, 
				camera.position.y + camera.viewportHeight/2 - 23);
		HUDFont.setColor(Color.MAGENTA);
		HUDFont.draw(batch, "Score: " + this.score + " Papers: " + this.papers + " Lives: " + this.lives, 
				camera.position.x - camera.viewportWidth/2 + 20, 
				camera.position.y + camera.viewportHeight/2 - 20);
	}
	
	public void paperUp(int paperCount) {
		this.papers += paperCount;
		this.isPaperAvailable = true;
	}
	
	public void paperDown(int paperCount) {
		if(this.isPaperAvailable) {
			this.papers -= paperCount;
		} 
		if(this.papers == 0) {
			this.isPaperAvailable = false;
		}
	}
	
	public void scoreUp(int scoreCount) {
		this.score += scoreCount;
		AudioPlayer.getInstance().playScoreUpSound();
	}
	
	public void liveDown(int liveCount) {
		this.lives -= liveCount;
	}
	
	public boolean isPaperAvailable() {
		return isPaperAvailable;
	}
	
	public int getScore() {
		return score;
	}
	
	public int getHighScore() {
		return score;
	}
	
	public int getLives(){
		return lives;
	}
}
