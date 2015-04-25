package com.paperboyclone;

import javax.print.DocFlavor.INPUT_STREAM;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.BitmapFont;

//Hier beginnt das Spiel
public class GameScreen extends BasicScreen{

	BitmapFont font;
	Sound sound;
	boolean movingRight;
	boolean movingLeft;
	boolean movingUp;
	boolean movingDown;
	
	public GameScreen(PaperboyClone app) {
		super(app);
		
		sound = Assets.getSound("testsound1.wav");
		sound.play();
		font = new BitmapFont();
		
	}
	
	public void render(float delta) {
		if(movingRight) {
			movingRight();
		}
		if(movingLeft) {
			movingLeft();
		}
		if(movingDown) {
			movingDown();
		}
		if(movingUp) {
			movingUp();
		}
		Gdx.gl.glClearColor(0, 0, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		App.batch.begin();
		font.draw(App.batch,"GameScreen",Gdx.graphics.getWidth()/2 ,Gdx.graphics.getHeight()/2);
		App.batch.end();
	}

	public boolean keyDown(int keycode) {
		if(keycode==Input.Keys.LEFT) {
			movingLeft();
			movingLeft = true;
		}
		if(keycode==Input.Keys.RIGHT) {
			movingRight();
			movingRight = true;
		}
		if(keycode==Input.Keys.UP) {
			movingUp();
			movingUp = true;
		}
		if(keycode==Input.Keys.DOWN) {
			movingDown();
			movingDown = true;
		}
		if(keycode==Input.Keys.X) {
			System.out.println("throwing right");
		}
		if(keycode==Input.Keys.Y) {
			System.out.println("throwing left");
		}
		return false;
	}
	
	public boolean keyUp(int keycode) {
		if(keycode==Input.Keys.LEFT) {
			movingLeft = false;
		}
		if(keycode==Input.Keys.RIGHT) {
			movingRight = false;
		}
		if(keycode==Input.Keys.UP) {
			movingUp = false;
		}
		if(keycode==Input.Keys.DOWN) {
			movingDown = false;
		}
		return false;
	}
	
	protected void movingLeft() {
		System.out.println("moving left");		
	}
	
	protected void movingRight() {
		System.out.println("moving right");		
	}
	
	protected void movingUp() {
		System.out.println("moving Up");
	}
	
	protected void movingDown() {
		System.out.println("moving Down");
	}
	
	public void dispose(){
		
		
	}
}
