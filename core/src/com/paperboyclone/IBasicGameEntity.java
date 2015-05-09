package com.paperboyclone;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public interface IBasicGameEntity {
		
	
	public void update(float delta);
	public void draw(SpriteBatch batch);
	public void setGameWorld(GameWorld gameworld);
}
