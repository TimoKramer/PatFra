package com.paperboyclone;

import javax.print.DocFlavor.INPUT_STREAM;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;

//Hier beginnt das Spiel
public class GameScreen extends BasicScreen{

	BitmapFont font;
	boolean pressingRight;
	boolean pressingLeft;
	boolean pressingUp;
	boolean pressingDown;
	
	private PlayerEntity player;
	private OrthographicCamera camera;
	private BackgroundManager background;
	private Array<House> Houses;
	private float speed;
	
	
	public GameScreen(PaperboyClone app) {
		super(app);
		
		font = new BitmapFont();
		//Groesse der Kamera noch unklar
		camera = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		camera.position.set(1000, Gdx.graphics.getHeight(),0);
		camera.update();
		
		player = new PlayerEntity(
				new Vector2(1000, Gdx.graphics.getHeight()),
				Assets.getTexture("redCircle.png")
				);
		speed = 200f;
		
		background = new BackgroundManager(new Vector2(0,0), Assets.getTexture("background.png"));
		
		Houses = new Array<House>(LevelGenerator.generateHouses());
	
	}
	
	public void render(float delta) {
	
	    update(delta);
	    
		Gdx.gl.glClearColor(0, 0, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		App.batch.begin();
		draw();
		App.batch.end();
	}
	
	//alles was aktualisiert werden muss, input -> aenderung der werte, kollisions checks etc.
	private void update(float delta){
		
		if(pressingRight) {
			pressingRight();
		}
		if(pressingLeft) {
			pressingLeft();
		}
		if(pressingDown) {
			pressingDown();
		}
		if(pressingUp) {
			pressingUp();
		}
		
		float actualSpeed = speed * delta;
		
		//nur zu test zwecken
	    camera.translate(0, actualSpeed, 0);
	    //player.update(delta);
	    
	    //Kamera auf Spieler-Position setzen
	    
	    //System.out.println("cam: "+camera.position.x +" | "+ camera.position.y);
	    camera.update();
	    App.batch.setProjectionMatrix(camera.combined);
	    background.update(camera);
	    player.setPosition(player.getPosition().x, camera.position.y-300);
	}
	
	//alles was angezeigt werden muss
	private void draw(){
		background.draw(App.batch);
		player.drawSprite(App.batch);
		
		for(House h : Houses){
			//todo: nur rendern was auf dem screen zu sehen ist
			h.draw(App.batch);
		}
		
	}

	public boolean keyDown(int keycode) {
		if(keycode==Input.Keys.LEFT) {
			pressingLeft();
			pressingLeft = true;
			//player.moveLeft();
		}
		if(keycode==Input.Keys.RIGHT) {
			pressingRight();
			pressingRight = true;
		}
		if(keycode==Input.Keys.UP) {
			pressingUp();
			pressingUp = true;
		}
		if(keycode==Input.Keys.DOWN) {
			pressingDown();
			pressingDown = true;
		}
		if(keycode==Input.Keys.X) {
			throwingRight();
		}
		if(keycode==Input.Keys.Y) {
			throwingLeft();
		}
		return false;
	}
	
	public boolean keyUp(int keycode) {
		if(keycode==Input.Keys.LEFT) {
			pressingLeft = false;
			//player.moveStraight();
		}
		if(keycode==Input.Keys.RIGHT) {
			pressingRight = false;
		}
		if(keycode==Input.Keys.UP) {
			pressingUp = false;
		}
		if(keycode==Input.Keys.DOWN) {
			pressingDown = false;
		}
		return false;
	}
	
	private void pressingLeft() {
		player.setPosition(new Vector2(player.getPosition().x-1, player.getPosition().y));
	}
	
	private void pressingRight() {
		player.setPosition(new Vector2(player.getPosition().x+1, player.getPosition().y));
	}
	
	private void pressingUp() {
		System.out.println("speeding Up: " + speed);
		if(speed < 500) {
			speed += 4;
		}
	}
	
	private void pressingDown() {
		System.out.println("breaking: " + speed);
		if(speed > 100) {
			speed -= 4;
		}
	}
	
	private void throwingLeft() {
		player.throwLeft();
	}

	private void throwingRight() {
		player.throwRight();		
	}
	
	public void dispose(){
		
	}
}
