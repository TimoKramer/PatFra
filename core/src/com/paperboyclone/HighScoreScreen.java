package com.paperboyclone;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;

public class HighScoreScreen extends BasicScreen{
	
	String highScore;
	BitmapFont font;
	String string;
	FileHandle handle;
	
	public HighScoreScreen(PaperboyClone app, HighScore highScore) {
		
		super(app);
		
		HighScoreHandler.getInstance().writeScore(highScore);
		
		
		font = new BitmapFont();
	}
		
	public void render(float delta) {
		
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		App.batch.begin();
		
		font.setColor(Color.WHITE);
		font.draw(App.batch, "Game Over!", Gdx.graphics.getWidth()/2, Gdx.graphics.getHeight()/2);
		
		App.batch.end();
		
	}
	
	public void dispose(){
		
		
	}

	
}
