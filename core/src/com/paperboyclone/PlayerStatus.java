package com.paperboyclone;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;

/**
 * <code>PlayerStats</code> is a Singleton that handles the status of the <code>Player</code> 
 * and its changes and it handles the drawing on the <code>Screen</code>.
 *  
 * 
 * @author Timo Kramer
 * @author Martin Freudenberg
 */
public class PlayerStatus {

	private int score;
	private int papers;
	private int lives;
	BitmapFont HUDFont;
	private boolean isPaperAvailable;
	
	private static final class InstanceHolder {
		static final PlayerStatus INSTANCE = new PlayerStatus(0,  30, 5);
	}
		
	private PlayerStatus(int score, int papers, int lives) {
		HUDFont = Assets.getFont();
		set(score,papers,lives);
	}

	public static synchronized PlayerStatus getInstance() {
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
