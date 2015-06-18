package com.paperboyclone;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;

public class LoadingScreen extends BasicScreen{
	
		BitmapFont font;
		String mode;
		
	public LoadingScreen(PaperboyClone app, String Mode) {
		super(app);
		mode = Mode;
		font = Assets.getFont();
		
		// Alle benoetigten Assets  in die Warteschlange des AssetManagers einreihen
		Assets.addTexture("badlogic.jpg");
		Assets.addTexture("background.png");
		Assets.addTexture("error.jpg");
		Assets.addSound("testsound1.wav");
		Assets.addTexture("redCircle.png");
		Assets.addTexture("yellowCircle.png");
		Assets.addTexture("house_1.png");
		Assets.addTexture("house_2.png");
		Assets.addTexture("house_3.png");
		Assets.addTexture("house_4.png");
		Assets.addTexture("mailbox_empty.png");
		Assets.addTexture("mailbox_full.png");
		Assets.addTexture("mailbox_empty_subscriber.png");
		Assets.addTexture("mailbox_full_subscriber.png");
		Assets.addTexture("obstacleT_1.png");
		Assets.addTexture("obstacleT_2.png");
		Assets.addTexture("obstacleT_3.png");
		Assets.addTexture("paperPile.png");
		Assets.addTexture("paper.png");
		Assets.addTexture("Test_Character.png");
		Assets.addMusic("8-Bit_Ninjas_-_12_-_Shiny_Spaceship.mp3");
		//hier weitere Assets die im Spiel gebraucht werden hinzufuegen
		
		if (!Gdx.files.external("PaperboyCloneHighScore.json").exists()) {
			Gdx.files.external("PaperboyCloneHighScore.json").writeString("[]", false);
		}
		
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
			App.setScreen(new GameScreen(App,new DifficultySettings(mode)));
			dispose();
		}
	
		
	}
	
	public void dispose(){
		
		
	}

}
