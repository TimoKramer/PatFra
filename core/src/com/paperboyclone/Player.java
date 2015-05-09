package com.paperboyclone;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;

public class Player extends BasicGameEntity {

	private Vector2 velocity; 


	public Player(Vector2 position, Texture texture){
		super(position, texture);
		velocity = new Vector2(0f, 300f);

	}

	public Player(){
		super();
	}

	public void update(float delta){
		
		checkForMovement(delta);
		this.position.x += velocity.x * delta;
		this.position.y += velocity.y * delta;
		//System.out.println("Geschwindigkeitsvektor: " + velocity.toString());
	}

	public void throwLeft() {
		
		gameworld.add(new Paper(this.position, true));
	}

	public void throwRight() {

		gameworld.add(new Paper(this.position, false));
	}

	public void moveRight(){
		velocity.x = 100f;
	}

	public void moveLeft(){
		velocity.x = -100f;
	}

	public void moveStraight(){
		velocity.x = 0;
		//velocity.y = 300;
	}

	public void moveFaster(float delta){
		if(velocity.y < 500) {
			velocity.y += 300f * delta;
		}
	}

	public void moveSlower(float delta) {
		if(velocity.y > 100) {
			velocity.y -= 300f * delta;
		}
	}
	
	public void checkForMovement(float delta) {
		if(Gdx.input.isKeyPressed(Keys.RIGHT)) {
			
			moveRight();
		}
		else if(Gdx.input.isKeyPressed(Keys.LEFT)) {
			moveLeft();
		}
		else if(Gdx.input.isKeyPressed(Keys.DOWN)) {
			moveSlower(delta);
		}
		else if(Gdx.input.isKeyPressed(Keys.UP)) {
			moveFaster(delta);
		}
		else {
			moveStraight();
		}
		
		if(Gdx.input.isKeyPressed(Keys.Y)){
			throwLeft();
		}
		else if(Gdx.input.isKeyPressed(Keys.X)){
			throwRight();
		}
	}


}
