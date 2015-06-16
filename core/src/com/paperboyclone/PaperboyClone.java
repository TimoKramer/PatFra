package com.paperboyclone;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;


public class PaperboyClone extends Game {
	public SpriteBatch batch;
	public GameSettings settings;
	
	public void create () {
		
		batch = new SpriteBatch();
		settings = new GameSettings();
		
		GameSettingsHandler g = new GameSettingsHandler();
		settings = g.loadSettings();
		Gdx.graphics.setDisplayMode((int)settings.resolution.x,(int)settings.resolution.y, settings.fullscreen);
		
		setScreen(new MainScreen(this));
		
	}


	public void render () {
	
		super.render();
			
	}
	
	public void dispose(){
		batch.dispose();
		Assets.dispose();
		System.out.println("Game disposed");
	}
}
