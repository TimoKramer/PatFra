package com.paperboyclone;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;


public class PaperboyClone extends Game {
	public SpriteBatch batch;

	
	public void create () {

		batch = new SpriteBatch();
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
