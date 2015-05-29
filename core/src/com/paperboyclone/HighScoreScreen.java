package com.paperboyclone;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;

public class HighScoreScreen extends BasicScreen{
	
	BitmapFont font;
	int highscore;
	ArrayList<HighScore> highscoreList;
	
	public HighScoreScreen(PaperboyClone app) {
		
		super(app);
		int highscore = HighScoreHandler.getInstance().getHighScore();
		HighScoreHandler.getInstance().writeScore(highscore);
		highscoreList = HighScoreHandler.getInstance().getHighscoreList();
		font = new BitmapFont();

		System.out.println(String.valueOf("Your Highscore: " + highscore));		
		System.out.println(HighScoreHandler.getInstance().getHighScoreList());
		System.out.println("Height: " + String.valueOf(Gdx.graphics.getHeight()) + " Width: " + String.valueOf(Gdx.graphics.getWidth()));
	}
		
	public void render(float delta) {
		
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		App.batch.begin();
		
		font.draw(App.batch, "Game Over!", 100f, Gdx.graphics.getHeight());
/*		font.draw(App.batch, String.valueOf(highscore), Gdx.graphics.getWidth()/2, Gdx.graphics.getHeight()/2);
		int y =  (int) (Gdx.graphics.get3
		Height()*0.65);
		for (HighScore hs : this.highscoreList) {
			font.draw(App.batch, String.valueOf(hs.score), Gdx.graphics.getWidth()/2, y);
			y -= 30;
		}
*/		App.batch.end();
		
	}
	
	public void dispose(){
		
		
	}

	
}
