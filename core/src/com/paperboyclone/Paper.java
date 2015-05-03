package com.paperboyclone;

import sun.font.TextRecord;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;

public class Paper extends BasicGameEntity {

	private Vector2 velocity;
	private boolean isThrownLeft;
	
	public Paper(Vector2 position, boolean isThrownLeft) {
		super.position = new Vector2(0f, 300f);
		super.sprite = new Sprite(Assets.getTexture("yellowCircle.png"));
		this.isThrownLeft = isThrownLeft;
	}
	
	public Paper(Vector2 position, Texture texture){
		super(position, texture);
	}
	
	public Paper(){
		super();
	}
	
}
