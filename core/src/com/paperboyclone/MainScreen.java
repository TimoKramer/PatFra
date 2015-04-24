package com.paperboyclone;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;


//spaeteres Hauptmenue
public class MainScreen extends BasicScreen {

	BitmapFont font;
	
	public MainScreen(PaperboyClone app){
		super(app);
		
		font = new BitmapFont();
	}
	//bei Tastendruck uebergang zum loading screen
	public boolean keyDown(int keycode) {
		App.setScreen(new LoadingScreen(App));
		return false;
	}
	

	public void render(float delta) {
		
		Gdx.gl.glClearColor(1, 0, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		App.batch.begin();
		font.draw(App.batch,"MainScreen",Gdx.graphics.getWidth()/2 ,Gdx.graphics.getHeight()/2);
		font.draw(App.batch,"press any key",Gdx.graphics.getWidth()/2 ,Gdx.graphics.getHeight()/2 - 20);
		
		App.batch.end();
		
	}
	
	public void dispose(){
		
		
	}

}
