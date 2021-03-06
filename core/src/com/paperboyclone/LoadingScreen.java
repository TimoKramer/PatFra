package com.paperboyclone;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;

/**
 * <code>Screen</code> for loading all assets used in the game.
 * Adds all assets to the loading queue of the the <code>AssetManager</code>.
 * Starts loading after it and goes on to the <code>GameScreen</code> when done.
 * 
 * @see <code>Assets</code>

 * @author Martin Freudenberg
 *	
 */
public class LoadingScreen extends BasicScreen{
	
		BitmapFont font;
		String mode;
		
	public LoadingScreen(PaperboyClone app) {
		super(app);
		mode = DifficultySettings.getInstance().getCurrentMode();
		font = Assets.getFont();
		
		// Alle benoetigten Assets  in die Warteschlange des AssetManagers einreihen
		Assets.addTexture("badlogic.jpg");
		Assets.addTexture("background.png");
		Assets.addTexture("error.jpg");
		Assets.addSound("testsound1.wav");
		Assets.addSound("hit.wav");
		Assets.addSound("crash.wav");
		Assets.addSound("throwright.mp3");
		Assets.addSound("throwleft.mp3");
		Assets.addSound("pop.ogg");
		Assets.addSound("scoreUP.ogg");
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
		Assets.addTexture("paperPile.png");
		Assets.addTexture("paper.png");
		Assets.addTexture("character.png");
		Assets.addTexture("character_big.png");
		Assets.addTexture("parked_car1.png");
		Assets.addTexture("parked_car2.png");
		Assets.addTexture("parked_car3.png");
		Assets.addTexture("parked_car4.png");
		Assets.addTexture("roadworks.png");
		Assets.addTexture("bench.png");
		//Assets.addMusic("8-Bit_Ninjas_-_12_-_Shiny_Spaceship.mp3");
		//hier weitere Assets die im Spiel gebraucht werden hinzufuegen
		
		if (!Gdx.files.external("PaperboyCloneHighScore.json").exists()) {
			Gdx.files.external("PaperboyCloneHighScore.json").writeString("[]", false);
		}
		
	}
	
	
	/**
	 * Repeatedly loading parts of the Assets in the loading queue.
	 * When not finished showing loading message.
	 * When finished skips to <code>GameScreen</code>
	 * 
	 * @param delta time between frames
	 */
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
			dispose();
		}
	
		
	}
	
	public void dispose(){
		
		
	}

}
