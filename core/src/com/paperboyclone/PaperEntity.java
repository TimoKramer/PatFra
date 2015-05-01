package com.paperboyclone;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Vector2;

public class PaperEntity extends BasicGameEntity {

	public PaperEntity(Vector2 position, Texture texture){
		this.position = position;
		this.sprite = new Sprite(texture);
	}
	
	public PaperEntity(){
		position = new Vector2(0,0);
		sprite = new Sprite();
	}

}
