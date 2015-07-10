package com.paperboyclone;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
/**
 *Interface to handle all tasks the same way
 * 
 * @author Martin Freudenberg
 */
public interface ITask {
	
	public void doTask(GameWorld gameworld, float deltaTime);
	public void draw(SpriteBatch batch);
	public boolean isAlive();
	
}
