package com.paperboyclone;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.utils.Json;
import com.badlogic.gdx.utils.TimeUtils;

public class PlayerStats {

	private int score;
	private int papers;
	private int lives;
	BitmapFont HUDFont;
	private boolean isPaperAvailable;
	
	private static final class InstanceHolder {
		static final PlayerStats INSTANCE = new PlayerStats(0,  10, 5);
	}
		
	private PlayerStats(int score, int papers, int lives) {
		HUDFont = new BitmapFont();
		this.score = score;
		this.papers = papers;
		this.lives = lives;
		this.isPaperAvailable = true;
	}

	public static synchronized PlayerStats getInstance() {
		return InstanceHolder.INSTANCE;
	}
	
	public void draw(Batch batch, Camera camera) {
		HUDFont.setColor(new Color(Color.WHITE));
		HUDFont.draw(batch, "Score: " + this.score + " Papers: " + this.papers + " Lives: " + this.lives, 
				camera.position.x - camera.viewportWidth/2, 
				camera.position.y + camera.viewportHeight/2);
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
	
	public HighScore getHighScore() {
		int newScore = this.score; 
		HighScore highScore = new HighScore();
		highScore.setName("Player");
		highScore.setTime(TimeUtils.millis()/1000L);
		highScore.setScore(this.score);
		
		HighScore hs = new HighScore("Player2", TimeUtils.millis()/1000L, 666666666);
		
		Json json = new Json();
		System.out.println(json.toJson(highScore));
		System.out.println(json.toJson(hs));
		return highScore;
	}
}
