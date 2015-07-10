package com.paperboyclone;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;

/**
 * The <code>Screen</code> to choose the desired level for the game.
 * 
 * @author Martin Freudenberg
 */
public class DifficultySelectionScreen extends BasicScreen{

	BitmapFont font;
	Menu menu;
	AnimatedBackground background;
	
	public DifficultySelectionScreen(PaperboyClone app){
		super(app);
		
		font = new BitmapFont();
	
		menu = MenuFactory.createDifficultySelectionMenu(this, app);
		background = new AnimatedBackground(Color.GREEN);
	}
	
	public boolean keyDown(int keycode) {
		
		menu.handleInput(keycode);
		
		return false;
	}
	

	public void render(float delta) {
		
		background.update(delta, cam);
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		App.batch.begin();
		background.draw(App.batch);
		menu.draw(App.batch);
		
		App.batch.end();
		
	}
	
	public void dispose(){
		
		
	}

}
