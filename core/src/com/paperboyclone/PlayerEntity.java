package com.paperboyclone;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;

public class PlayerEntity extends BasicGameEntity {
	
	public PlayerEntity(Vector2 position, Texture texture){
		this.position = position;
		this.sprite = new Sprite(texture);
	}
	
	public PlayerEntity(){
		position = new Vector2(0,0);
		sprite = new Sprite();
	}

	
	public void throwLeft() {
		PaperEntity paper = new PaperEntity(this.position, Assets.getTexture("yellowCircle.png"));
	}
	
	public void throwRight() {
		System.out.println(this.position.toString());
		PaperEntity paper = new PaperEntity(this.position, Assets.getTexture("yellowCircle.png"));
	}
	
}
