package com.paperboyclone;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;

public class OptionScreen extends BasicScreen{

	
	private Menu menu;
	
	public OptionScreen(PaperboyClone app) {
		super(app);
		menu = MenuFactory.createOptionScreenMenu(this, app);
		
	}
	
	
	public boolean keyDown(int keycode) {
		
		menu.handleInput(keycode);
		
		return false;
	}
	

	public void render(float delta) {
	
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		App.batch.begin();

		menu.draw(App.batch);
		
		App.batch.end();
		
	}
	
	public void dispose(){
		
		
	}
	

}
