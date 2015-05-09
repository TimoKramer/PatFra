package com.paperboyclone;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

public class Obstacle extends BasicGameEntity{
	
	
	public Obstacle(Vector2 position, Texture texture){
			super(position,texture);
	}

	public Obstacle() {
		super();
	}
}
