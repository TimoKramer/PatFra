package com.paperboyclone;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;

public class PlayerEntity extends BasicGameEntity {
	
	//private Vector2 velocity;
	//private float speed;
	
	public PlayerEntity(Vector2 position, Texture texture){
		super(position, texture);
		
	}
	
	public PlayerEntity(){
		super();
		
	}
	
	/*
	 * public void update(float delta){
	 * 
	 * 		this.position.x += velocity.x * delta;
	 * 		this.position.y += velocity.y * delta;
	 * }
	 * 
	 * 
	 */
	
	public void throwLeft() {
		PaperEntity paper = new PaperEntity(this.position, Assets.getTexture("yellowCircle.png"));
	}
	
	public void throwRight() {
		System.out.println(this.position.toString());
		PaperEntity paper = new PaperEntity(this.position, Assets.getTexture("yellowCircle.png"));
	}
	
	
	/*
	 * 
	 * public void moveRight(){
	 * 		velocity.x = speed;
	 *  }
	 *  
	 *  public void moveLeft(){
	 *  	velocity.x = -speed;
	 *  }
	 * 
	 * public void moveStraight(){
	 * 		velocity.x = 0;
	 *      velocity.y = speed;
	 * }
	 * 
	 * public void accelerate(float delta){
	 * 		speed limitieren if(speed<MaxSpeed)...
	 * 		speed+= [Beschleunigung] * delta;
	 * }
	 */
}
