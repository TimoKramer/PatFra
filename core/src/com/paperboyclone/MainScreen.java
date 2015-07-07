package com.paperboyclone;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;

/**
 * @author Martin Freudenberg
 *
 */
public class MainScreen extends BasicScreen {

	BitmapFont font;
	Menu menu;
	AnimatedBackground background;
	
	public MainScreen(PaperboyClone app){
		super(app);
		
		font = new BitmapFont();
	
		menu = MenuFactory.createStartMenu(this, app);
		background = new AnimatedBackground(Color.GREEN);
		AudioPlayer.getInstance().setBackgroundMusic(new BackgroundMusic(Assets.getMusic("MenuMusic.ogg")));
		AudioPlayer.getInstance().run();
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
