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
	
	private static final class InstanceHolder {
		static final PlayerStats INSTANCE = new PlayerStats(0,  10, 5);
	}
		
	private PlayerStats(int score, int papers, int lives) {
		HUDFont = new BitmapFont();
		this.score = score;
		this.papers = papers;
		this.lives = lives;
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
	}
	
	public void paperDown(int paperCount) {
		this.papers -= paperCount;
	}
	
	public void scoreUp(int scoreCount) {
		this.score += scoreCount;
	}
	
	public void liveDown(int liveCount) {
		this.lives -= liveCount;
	}
	
}
