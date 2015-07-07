package com.paperboyclone;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;

/**
 * @author Martin Freudenberg
 *
 */
public class OptionScreen extends BasicScreen{

	
	private Menu menu;
	private AnimatedBackground background;
	
	public OptionScreen(PaperboyClone app) {
		super(app);
		menu = MenuFactory.createOptionScreenMenu(this, app);
		background = new AnimatedBackground(Color.GREEN);
	}
	
	
	public boolean keyDown(int keycode) {
		
		menu.handleInput(keycode);
		
		return false;
	}
	

	public void render(float delta) {
	
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		background.update(delta, cam);
		
		App.batch.begin();
		background.draw(App.batch);
		menu.draw(App.batch);
		
		App.batch.end();
		
	}
	
	public void dispose(){
		
		
	}
	

}
