package com.paperboyclone;

import java.util.ArrayList;
import java.util.Collections;

//import javafx.scene.text.TextBoundsType;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.math.Vector2;

public class HighScoreScreen extends BasicScreen{
	
	BitmapFont font;
	private int highscore;
	private ArrayList<HighScore> highscoreList;
	private OrthographicCamera camera;
	private BackgroundManager background;
	HighScoreHandler hsh;
	Menu menu;
	
	public HighScoreScreen(PaperboyClone app) {
		
		super(app);
		create();
	}
	
	public void create() {
		camera = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
	    camera.position.set(Gdx.graphics.getWidth()/2, Gdx.graphics.getHeight()/2, 0);
		camera.update();
		
		font = Assets.getFont();
		background = new BackgroundManager(new Vector2(0,0), Assets.getTexture("background.png"));
		
		hsh = HighScoreHandler.getInstance();
		highscore = hsh.getHighScore();
		highscoreList = hsh.getHighscoreList(DifficultySettings.getInstance().getCurrentMode());
		Collections.sort(highscoreList);
				
	    App.batch.setProjectionMatrix(camera.combined);
	    
	    menu = MenuFactory.createHighscoreScreenMenu(this, App);
	    menu.setPosition(20, Gdx.graphics.getHeight() - 200);
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
		String highscorelistString = "";
		if (highscoreList.size() > 9) {
			for (HighScore highScore : highscoreList.subList(0, 10)) {
				highscorelistString += "    ";
				highscorelistString += highScore.toString();
				highscorelistString += "\n";
			}
		} else {
			for (int i = 0; i < highscoreList.size(); i++) {
				highscorelistString += "    ";
				highscorelistString += highscoreList.get(i).toString();
				highscorelistString += "\n";
			}
		}
		String completeString = "You scored " + String.valueOf(highscore) + " in level " + 
				DifficultySettings.getInstance().getCurrentMode() + "!\n\n" +
				"HIGHSCORES:\n\n" +
				highscorelistString;
		GlyphLayout layout = new GlyphLayout(font, completeString);
		float width = layout.width;
		float height = layout.height;
		
		//font.draw(App.batch, layout, Gdx.graphics.getWidth()/2 - width/2, Gdx.graphics.getHeight()/2 + height/2);
		font.draw(App.batch, layout, Gdx.graphics.getWidth()/2 - width/2, Gdx.graphics.getHeight() * 0.9f);
		/*		
		/*		font.draw(App.batch,
				"You scored " + String.valueOf(highscore) + " in level " + DifficultySettings.getInstance().getCurrentMode() + "!\n\n" +
						"HIGHSCORES:\n\n" +
						highscorelistString,
				Gdx.graphics.getWidth()/2, 
				Gdx.graphics.getHeight()/2 + 300);
				
		
*/	
		menu.draw(App.batch);
		font.setColor(Color.MAGENTA);
	}
	
	public boolean keyDown(int keycode) {
		
		menu.handleInput(keycode);
		
		return false;
	}

	public void dispose(){

	}

	
}
