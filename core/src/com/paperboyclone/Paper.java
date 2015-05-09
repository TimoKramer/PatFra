package com.paperboyclone;

import sun.font.TextRecord;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;

public class Paper extends BasicGameEntity {

	
	private boolean isThrownLeft;
	private Vector2 velocity;
	
	public Paper(Vector2 position, boolean isThrownLeft) {
		super(position, Assets.getTexture("yellowCircle.png"));
		this.isThrownLeft = isThrownLeft;
		this.velocity = new Vector2(0f,0f);
		
		if(this.isThrownLeft){
			velocity.x = -500f;
		}
		else{
			velocity.x = 500f;
		}
	}
	
	public Paper(Vector2 position, Texture texture){
		super(position, texture);
	}
	
	
	public void update(float delta){
		this.position.x += velocity.x * delta;
		//this.position.y += velocity.y * delta;
		//System.out.println("Geschwindigkeitsvektor: " + velocity.toString());
	}
	
	public Paper(){
		super();
	}
	
}
