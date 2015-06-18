package com.paperboyclone;

import java.util.ArrayList;
import java.util.Collections;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.math.Vector2;

public class HighScoreScreen extends BasicScreen{
	
	BitmapFont font;
	private int highscore;
	private ArrayList<HighScore> highscoreList;
	private OrthographicCamera camera;
	private BackgroundManager background;
	HighScoreHandler hsh;
	
	public HighScoreScreen(PaperboyClone app) {
		
		super(app);
		create();
	}
	
	public void create() {
		camera = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
	    camera.position.set(Gdx.graphics.getWidth()/2, Gdx.graphics.getHeight()/2, 0);
		camera.update();
		
		font = new BitmapFont();
		background = new BackgroundManager(new Vector2(0,0), Assets.getTexture("background.png"));
		
		hsh = HighScoreHandler.getInstance();
		highscore = hsh.getHighScore();
		highscoreList = hsh.getHighscoreList(DifficultySettings.getInstance().getCurrentMode());
		Collections.sort(highscoreList);
				
	    App.batch.setProjectionMatrix(camera.combined);
	}
		
	public void render(float delta) {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		App.batch.begin();
		
	    background.update(camera);		
		draw();

		App.batch.end();
	}
	
	private void draw() {
		
		font.draw(App.batch, "You scored " + String.valueOf(highscore) + "!", Gdx.graphics.getWidth()/2, Gdx.graphics.getHeight()/2 + 200);
		font.draw(App.batch, "HIGHSCORES:", Gdx.graphics.getWidth()/2, Gdx.graphics.getHeight()/2 + 140);
		int y = Gdx.graphics.getHeight()/2 + 100;
		if (highscoreList.size() > 9) {
			for (HighScore highScore : highscoreList.subList(0, 10)) {
				font.draw(App.batch, highScore.toString(), Gdx.graphics.getWidth()/2, y -= 20);
			}
		} else {
			for (int i = 0; i < highscoreList.size(); i++) {
				font.draw(App.batch, highscoreList.get(i).toString(), Gdx.graphics.getWidth()/2, y -= 20);	
			}
		}
	}

	public void dispose(){

	}

	
}
