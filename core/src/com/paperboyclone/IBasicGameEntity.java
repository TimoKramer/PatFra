package com.paperboyclone;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

/**
 * The interface for the basic game entities.
 * 
 * @author Martin Freudenberg
 * @see BasicGameEntity
 */
public interface IBasicGameEntity {
		
	
	public void update(float delta);
	public void draw(SpriteBatch batch);
	public Rectangle getBoundingBox();
	public void setGameWorld(GameWorld gameworld);
	public <T> void onCollision(IBasicGameEntity collidedObject, java.lang.Class<T> Type);
	public <T> void notColliding(IBasicGameEntity collidedObject, java.lang.Class<T> Type);
	
}
