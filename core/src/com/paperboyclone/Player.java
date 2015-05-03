package com.paperboyclone;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;

public class Player extends BasicGameEntity {

	private Vector2 velocity = new Vector2(0f, 300f);


	public Player(Vector2 position, Texture texture){
		super(position, texture);

	}

	public Player(){
		super();
	}

	public void update(float delta){
		this.position.x += velocity.x * delta;
		this.position.y += velocity.y * delta;
		System.out.println("Geschwindigkeitsvektor: " + velocity.toString());
	}

	public void throwLeft() {
		Paper paper = new Paper(this.position, true);
	}

	public void throwRight() {
		Paper paper = new Paper(this.position, false);
	}

	public void moveRight(float delta){
		velocity.x += 300f * delta;
	}

	public void moveLeft(float delta){
		velocity.x -= 300f * delta;
	}

	public void moveStraight(){
		velocity.x = 0;
		//velocity.y = 300;
	}

	public void moveFaster(float delta){
		//	 		speed limitieren if(speed<MaxSpeed)...
		//	 		speed+= [Beschleunigung] * delta;
		if(velocity.y < 500) {
			velocity.y += 300f * delta;
		}
	}

	public void moveSlower(float delta) {
		if(velocity.y > 100) {
			velocity.y -= 300f * delta;
		}
	}

}
