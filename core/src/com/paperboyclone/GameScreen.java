package com.paperboyclone;

import javax.print.DocFlavor.INPUT_STREAM;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.math.Vector2;

//Hier beginnt das Spiel
public class GameScreen extends BasicScreen{

	BitmapFont font;
	boolean movingRight;
	boolean movingLeft;
	boolean movingUp;
	boolean movingDown;
	
	private OrthographicCamera camera;
	private BackgroundManager background;
	
	
	public GameScreen(PaperboyClone app) {
		super(app);
		

		font = new BitmapFont();
		//Groesse der Kamera noch unklar
		camera = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		camera.position.set(1000, Gdx.graphics.getHeight()/2f,0);
		camera.update();
		
		background = new BackgroundManager(new Vector2(0,0), Assets.getTexture("background.png"));
		
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
		
		//nur zu test zwecken
	    camera.translate(0,300 * delta,0);
	    //System.out.println("cam: "+camera.position.x +" | "+ camera.position.y);
	    camera.update();
	    App.batch.setProjectionMatrix(camera.combined);
	    background.update(camera);
	       
		Gdx.gl.glClearColor(0, 0, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		App.batch.begin();
		background.draw(App.batch);
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
