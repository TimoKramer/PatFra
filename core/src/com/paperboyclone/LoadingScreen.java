package com.paperboyclone;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;

public class LoadingScreen extends BasicScreen{
	
		BitmapFont font;
	
	public LoadingScreen(PaperboyClone app) {
		super(app);
		
		font = new BitmapFont();
		
		// Alle benoetigten Assets  in die Warteschlange des AssetManagers einreihen
		Assets.addTexture("badlogic.jpg");
		Assets.addTexture("error.jpg");
		Assets.addSound("testsound1.wav");
		//hier weitere Assets die im Spiel gebraucht werden hinzufuegen
	}
	
	
	public void render(float delta) {
		
		//Laden starten 
		//Falls noch laedt -> Animation abspielen
		if(!Assets.continueLoading()){
			Gdx.gl.glClearColor(0, 0, 0, 1);
			Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
			App.batch.begin();
			font.draw(App.batch,"Loading...",Gdx.graphics.getWidth()/2 ,Gdx.graphics.getHeight()/2);
			App.batch.end();
			
		}
		else{
			//Laden beendet -> wechsel zum Game Screen
			App.setScreen(new GameScreen(App));
		
		}
	
		
	}
	
	public void dispose(){
		
		
	}

}
