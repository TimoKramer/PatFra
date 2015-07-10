package com.paperboyclone;

import java.util.ArrayList;
import java.util.Collections;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;

/**
 * The <code>Screen</code> to show the <code>HighScore</code>s for
 * the current level at the end of each game.
 * 
 * @author Timo Kramer
 * @author Martin Freudenberg
 */
public class HighScoreScreen extends BasicScreen{
	
	BitmapFont font;
	private int highscore;
	private ArrayList<HighScore> highscoreList;
	private OrthographicCamera camera;
	
	HighScoreHandler hsh;
	private Menu menu;
	private AnimatedBackground background;
	
	public HighScoreScreen(PaperboyClone app) {
		
		super(app);
		create();
	}
	
	public void create() {
		camera = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
	    camera.position.set(Gdx.graphics.getWidth()/2, Gdx.graphics.getHeight()/2, 0);
		camera.update();
		
		font = Assets.getFont();
		background = new AnimatedBackground(Color.GREEN);
		hsh = HighScoreHandler.getInstance();
		highscore = hsh.getHighScore();
		highscoreList = hsh.getHighscoreList(DifficultySettings.getInstance().getCurrentMode());
		Collections.sort(highscoreList);
				
	    App.batch.setProjectionMatrix(camera.combined);
	    
	    menu = MenuFactory.createHighscoreScreenMenu(this, App);
	    menu.setPosition(20, Gdx.graphics.getHeight() - 200);
	    
	    AudioPlayer.getInstance().setBackgroundMusic(new BackgroundMusic(Assets.getMusic("MenuMusic.ogg")));
		AudioPlayer.getInstance().startMusic();
	}
		
	public void render(float delta) {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		background.update(delta,camera);
		
		App.batch.begin();	
	    background.draw(App.batch);
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
		
		menu.draw(App.batch);
		font.setColor(Color.MAGENTA);
		font.draw(App.batch, layout, Gdx.graphics.getWidth()/2 - width/2 + 50, Gdx.graphics.getHeight() * 0.9f);
	
		
	}
	
	public boolean keyDown(int keycode) {
		
		menu.handleInput(keycode);
		
		return false;
	}

	public void dispose(){

	}

	
}
