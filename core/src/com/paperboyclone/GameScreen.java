package com.paperboyclone;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;

//Hier beginnt das Spiel
public class GameScreen extends BasicScreen{

	BitmapFont font;
	Sound sound;
	
	public GameScreen(PaperboyClone app) {
		super(app);
		
		sound = Assets.getSound("testsound1.wav");
		sound.play();
		font = new BitmapFont();
		
	}
	
	
	
	public void render(float delta) {
		

		Gdx.gl.glClearColor(0, 0, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		App.batch.begin();
		font.draw(App.batch,"GameScreen",Gdx.graphics.getWidth()/2 ,Gdx.graphics.getHeight()/2);
		App.batch.end();
	}

	
	public void dispose(){
		
		
	}
}
