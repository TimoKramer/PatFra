package com.paperboyclone;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;


//spaeteres Hauptmenu
//todo: Maus unterstuetzung impl
//todo: background grafik etc.
public class MainScreen extends BasicScreen {

	BitmapFont font;
	Menu menu;
	
	public MainScreen(PaperboyClone app){
		super(app);
		
		font = new BitmapFont();
	
		menu = MenuFactory.createStartMenu(this, app);
		
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
