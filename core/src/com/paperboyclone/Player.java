package com.paperboyclone;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

public class Player extends BasicGameEntity {

	private Vector2 velocity;
	private boolean isThrown;


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
	}

	public void throwLeft() {
		
		gameworld.add(new Paper(new Vector2(
				this.position.x,
				this.position.y + this.sprite.getHeight()/2),
				true));
	}

	public void throwRight() {

		gameworld.add(new Paper(new Vector2(
				this.position.x + this.sprite.getWidth(),
				this.position.y + this.sprite.getHeight()/2),
				false));
	}

	public void moveRight(){
		velocity.x = 200f;
	}

	public void moveLeft(){
		velocity.x = -200f;
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
		else if(Gdx.input.isKeyPressed(Keys.Y)){
			if(!isThrown) throwLeft();
			isThrown = true;
		}
		else if(Gdx.input.isKeyPressed(Keys.X)){
			if(!isThrown) throwRight();
			isThrown = true;
		}
		else {
			moveStraight();
			isThrown = false;
		}
	}
	
	
	public <T> void onCollision(IBasicGameEntity collidedObject, Class<T> Type) {
		
		if(Type == Obstacle.class){
			
			System.out.println("Player: collision with obstacle");
			
		}
		if(Type == PaperPile.class) {
			System.out.println("Player: collision with paperpile");
		}
	
		
	}



}
